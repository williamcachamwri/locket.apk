package io.sentry.android.core;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import androidx.core.app.NotificationCompat;
import io.sentry.Breadcrumb;
import io.sentry.FullyDisplayedReporter;
import io.sentry.Hint;
import io.sentry.IHub;
import io.sentry.ISpan;
import io.sentry.ITransaction;
import io.sentry.Instrumenter;
import io.sentry.Integration;
import io.sentry.MeasurementUnit;
import io.sentry.NoOpTransaction;
import io.sentry.Scope;
import io.sentry.SentryDate;
import io.sentry.SentryLevel;
import io.sentry.SentryOptions;
import io.sentry.Session;
import io.sentry.SpanStatus;
import io.sentry.TransactionContext;
import io.sentry.TransactionOptions;
import io.sentry.TypeCheckHint;
import io.sentry.android.core.internal.util.ClassUtil;
import io.sentry.android.core.internal.util.FirstDrawDoneListener;
import io.sentry.protocol.MeasurementValue;
import io.sentry.protocol.TransactionNameSource;
import io.sentry.util.Objects;
import io.sentry.util.TracingUtils;
import java.io.Closeable;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

public final class ActivityLifecycleIntegration implements Integration, Closeable, Application.ActivityLifecycleCallbacks {
    static final String APP_START_COLD = "app.start.cold";
    static final String APP_START_WARM = "app.start.warm";
    private static final String TRACE_ORIGIN = "auto.ui.activity";
    static final String TTFD_OP = "ui.load.full_display";
    static final long TTFD_TIMEOUT_MILLIS = 30000;
    static final String TTID_OP = "ui.load.initial_display";
    static final String UI_LOAD_OP = "ui.load";
    private final WeakHashMap<Activity, ITransaction> activitiesWithOngoingTransactions = new WeakHashMap<>();
    private final ActivityFramesTracker activityFramesTracker;
    private ISpan appStartSpan;
    private final Application application;
    private final BuildInfoProvider buildInfoProvider;
    private boolean firstActivityCreated = false;
    private final boolean foregroundImportance;
    private FullyDisplayedReporter fullyDisplayedReporter = null;
    private IHub hub;
    private boolean isAllActivityCallbacksAvailable;
    private SentryDate lastPausedTime = AndroidDateUtils.getCurrentSentryDateTime();
    private final Handler mainHandler = new Handler(Looper.getMainLooper());
    private SentryAndroidOptions options;
    private boolean performanceEnabled = false;
    private boolean timeToFullDisplaySpanEnabled = false;
    private Future<?> ttfdAutoCloseFuture = null;
    private final WeakHashMap<Activity, ISpan> ttfdSpanMap = new WeakHashMap<>();
    private final WeakHashMap<Activity, ISpan> ttidSpanMap = new WeakHashMap<>();

    private String getAppStartDesc(boolean z) {
        return z ? "Cold Start" : "Warm Start";
    }

    private String getAppStartOp(boolean z) {
        return z ? APP_START_COLD : APP_START_WARM;
    }

    public void onActivityPostResumed(Activity activity) {
    }

    public ActivityLifecycleIntegration(Application application2, BuildInfoProvider buildInfoProvider2, ActivityFramesTracker activityFramesTracker2) {
        Application application3 = (Application) Objects.requireNonNull(application2, "Application is required");
        this.application = application3;
        this.buildInfoProvider = (BuildInfoProvider) Objects.requireNonNull(buildInfoProvider2, "BuildInfoProvider is required");
        this.activityFramesTracker = (ActivityFramesTracker) Objects.requireNonNull(activityFramesTracker2, "ActivityFramesTracker is required");
        if (buildInfoProvider2.getSdkInfoVersion() >= 29) {
            this.isAllActivityCallbacksAvailable = true;
        }
        this.foregroundImportance = ContextUtils.isForegroundImportance(application3);
    }

    public void register(IHub iHub, SentryOptions sentryOptions) {
        this.options = (SentryAndroidOptions) Objects.requireNonNull(sentryOptions instanceof SentryAndroidOptions ? (SentryAndroidOptions) sentryOptions : null, "SentryAndroidOptions is required");
        this.hub = (IHub) Objects.requireNonNull(iHub, "Hub is required");
        this.options.getLogger().log(SentryLevel.DEBUG, "ActivityLifecycleIntegration enabled: %s", Boolean.valueOf(this.options.isEnableActivityLifecycleBreadcrumbs()));
        this.performanceEnabled = isPerformanceEnabled(this.options);
        this.fullyDisplayedReporter = this.options.getFullyDisplayedReporter();
        this.timeToFullDisplaySpanEnabled = this.options.isEnableTimeToFullDisplayTracing();
        this.application.registerActivityLifecycleCallbacks(this);
        this.options.getLogger().log(SentryLevel.DEBUG, "ActivityLifecycleIntegration installed.", new Object[0]);
        addIntegrationToSdkVersion();
    }

    private boolean isPerformanceEnabled(SentryAndroidOptions sentryAndroidOptions) {
        return sentryAndroidOptions.isTracingEnabled() && sentryAndroidOptions.isEnableAutoActivityLifecycleTracing();
    }

    public void close() throws IOException {
        this.application.unregisterActivityLifecycleCallbacks(this);
        SentryAndroidOptions sentryAndroidOptions = this.options;
        if (sentryAndroidOptions != null) {
            sentryAndroidOptions.getLogger().log(SentryLevel.DEBUG, "ActivityLifecycleIntegration removed.", new Object[0]);
        }
        this.activityFramesTracker.stop();
    }

    private void addBreadcrumb(Activity activity, String str) {
        SentryAndroidOptions sentryAndroidOptions = this.options;
        if (sentryAndroidOptions != null && this.hub != null && sentryAndroidOptions.isEnableActivityLifecycleBreadcrumbs()) {
            Breadcrumb breadcrumb = new Breadcrumb();
            breadcrumb.setType(NotificationCompat.CATEGORY_NAVIGATION);
            breadcrumb.setData("state", str);
            breadcrumb.setData("screen", getActivityName(activity));
            breadcrumb.setCategory("ui.lifecycle");
            breadcrumb.setLevel(SentryLevel.INFO);
            Hint hint = new Hint();
            hint.set(TypeCheckHint.ANDROID_ACTIVITY, activity);
            this.hub.addBreadcrumb(breadcrumb, hint);
        }
    }

    private String getActivityName(Activity activity) {
        return activity.getClass().getSimpleName();
    }

    private void stopPreviousTransactions() {
        for (Map.Entry next : this.activitiesWithOngoingTransactions.entrySet()) {
            finishTransaction((ITransaction) next.getValue(), this.ttidSpanMap.get(next.getKey()), this.ttfdSpanMap.get(next.getKey()));
        }
    }

    private void startTracing(Activity activity) {
        SentryDate sentryDate;
        WeakReference weakReference = new WeakReference(activity);
        if (this.hub != null && !isRunningTransactionOrTrace(activity)) {
            boolean z = this.performanceEnabled;
            if (!z) {
                this.activitiesWithOngoingTransactions.put(activity, NoOpTransaction.getInstance());
                TracingUtils.startNewTrace(this.hub);
            } else if (z) {
                stopPreviousTransactions();
                String activityName = getActivityName(activity);
                SentryDate appStartTime = this.foregroundImportance ? AppStartState.getInstance().getAppStartTime() : null;
                Boolean isColdStart = AppStartState.getInstance().isColdStart();
                TransactionOptions transactionOptions = new TransactionOptions();
                if (this.options.isEnableActivityLifecycleTracingAutoFinish()) {
                    transactionOptions.setIdleTimeout(this.options.getIdleTimeout());
                    transactionOptions.setTrimEnd(true);
                }
                transactionOptions.setWaitForChildren(true);
                transactionOptions.setTransactionFinishedCallback(new ActivityLifecycleIntegration$$ExternalSyntheticLambda5(this, weakReference, activityName));
                if (this.firstActivityCreated || appStartTime == null || isColdStart == null) {
                    sentryDate = this.lastPausedTime;
                } else {
                    sentryDate = appStartTime;
                }
                transactionOptions.setStartTimestamp(sentryDate);
                ITransaction startTransaction = this.hub.startTransaction(new TransactionContext(activityName, TransactionNameSource.COMPONENT, UI_LOAD_OP), transactionOptions);
                setSpanOrigin(startTransaction);
                if (!(this.firstActivityCreated || appStartTime == null || isColdStart == null)) {
                    ISpan startChild = startTransaction.startChild(getAppStartOp(isColdStart.booleanValue()), getAppStartDesc(isColdStart.booleanValue()), appStartTime, Instrumenter.SENTRY);
                    this.appStartSpan = startChild;
                    setSpanOrigin(startChild);
                    finishAppStartSpan();
                }
                ISpan startChild2 = startTransaction.startChild(TTID_OP, getTtidDesc(activityName), sentryDate, Instrumenter.SENTRY);
                this.ttidSpanMap.put(activity, startChild2);
                setSpanOrigin(startChild2);
                if (!(!this.timeToFullDisplaySpanEnabled || this.fullyDisplayedReporter == null || this.options == null)) {
                    ISpan startChild3 = startTransaction.startChild(TTFD_OP, getTtfdDesc(activityName), sentryDate, Instrumenter.SENTRY);
                    setSpanOrigin(startChild3);
                    try {
                        this.ttfdSpanMap.put(activity, startChild3);
                        this.ttfdAutoCloseFuture = this.options.getExecutorService().schedule(new ActivityLifecycleIntegration$$ExternalSyntheticLambda6(this, startChild3, startChild2), 30000);
                    } catch (RejectedExecutionException e) {
                        this.options.getLogger().log(SentryLevel.ERROR, "Failed to call the executor. Time to full display span will not be finished automatically. Did you call Sentry.close()?", (Throwable) e);
                    }
                }
                this.hub.configureScope(new ActivityLifecycleIntegration$$ExternalSyntheticLambda7(this, startTransaction));
                this.activitiesWithOngoingTransactions.put(activity, startTransaction);
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$startTracing$0$io-sentry-android-core-ActivityLifecycleIntegration  reason: not valid java name */
    public /* synthetic */ void m2393lambda$startTracing$0$iosentryandroidcoreActivityLifecycleIntegration(WeakReference weakReference, String str, ITransaction iTransaction) {
        Activity activity = (Activity) weakReference.get();
        if (activity != null) {
            this.activityFramesTracker.setMetrics(activity, iTransaction.getEventId());
            return;
        }
        SentryAndroidOptions sentryAndroidOptions = this.options;
        if (sentryAndroidOptions != null) {
            sentryAndroidOptions.getLogger().log(SentryLevel.WARNING, "Unable to track activity frames as the Activity %s has been destroyed.", str);
        }
    }

    private void setSpanOrigin(ISpan iSpan) {
        if (iSpan != null) {
            iSpan.getSpanContext().setOrigin(TRACE_ORIGIN);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: applyScope */
    public void m2395lambda$startTracing$2$iosentryandroidcoreActivityLifecycleIntegration(Scope scope, ITransaction iTransaction) {
        scope.withTransaction(new ActivityLifecycleIntegration$$ExternalSyntheticLambda3(this, scope, iTransaction));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$applyScope$3$io-sentry-android-core-ActivityLifecycleIntegration  reason: not valid java name */
    public /* synthetic */ void m2388lambda$applyScope$3$iosentryandroidcoreActivityLifecycleIntegration(Scope scope, ITransaction iTransaction, ITransaction iTransaction2) {
        if (iTransaction2 == null) {
            scope.setTransaction(iTransaction);
            return;
        }
        SentryAndroidOptions sentryAndroidOptions = this.options;
        if (sentryAndroidOptions != null) {
            sentryAndroidOptions.getLogger().log(SentryLevel.DEBUG, "Transaction '%s' won't be bound to the Scope since there's one already in there.", iTransaction.getName());
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: clearScope */
    public void m2389lambda$finishTransaction$5$iosentryandroidcoreActivityLifecycleIntegration(Scope scope, ITransaction iTransaction) {
        scope.withTransaction(new ActivityLifecycleIntegration$$ExternalSyntheticLambda2(iTransaction, scope));
    }

    static /* synthetic */ void lambda$clearScope$4(ITransaction iTransaction, Scope scope, ITransaction iTransaction2) {
        if (iTransaction2 == iTransaction) {
            scope.clearTransaction();
        }
    }

    private boolean isRunningTransactionOrTrace(Activity activity) {
        return this.activitiesWithOngoingTransactions.containsKey(activity);
    }

    private void stopTracing(Activity activity, boolean z) {
        if (this.performanceEnabled && z) {
            finishTransaction(this.activitiesWithOngoingTransactions.get(activity), (ISpan) null, (ISpan) null);
        }
    }

    private void finishTransaction(ITransaction iTransaction, ISpan iSpan, ISpan iSpan2) {
        if (iTransaction != null && !iTransaction.isFinished()) {
            finishSpan(iSpan, SpanStatus.DEADLINE_EXCEEDED);
            m2394lambda$startTracing$1$iosentryandroidcoreActivityLifecycleIntegration(iSpan2, iSpan);
            cancelTtfdAutoClose();
            SpanStatus status = iTransaction.getStatus();
            if (status == null) {
                status = SpanStatus.OK;
            }
            iTransaction.finish(status);
            IHub iHub = this.hub;
            if (iHub != null) {
                iHub.configureScope(new ActivityLifecycleIntegration$$ExternalSyntheticLambda4(this, iTransaction));
            }
        }
    }

    public synchronized void onActivityCreated(Activity activity, Bundle bundle) {
        setColdStart(bundle);
        addBreadcrumb(activity, "created");
        if (this.hub != null) {
            this.hub.configureScope(new ActivityLifecycleIntegration$$ExternalSyntheticLambda8(ClassUtil.getClassName(activity)));
        }
        startTracing(activity);
        ISpan iSpan = this.ttfdSpanMap.get(activity);
        this.firstActivityCreated = true;
        FullyDisplayedReporter fullyDisplayedReporter2 = this.fullyDisplayedReporter;
        if (fullyDisplayedReporter2 != null) {
            fullyDisplayedReporter2.registerFullyDrawnListener(new ActivityLifecycleIntegration$$ExternalSyntheticLambda9(this, iSpan));
        }
    }

    public synchronized void onActivityStarted(Activity activity) {
        if (this.performanceEnabled) {
            this.activityFramesTracker.addActivity(activity);
        }
        addBreadcrumb(activity, Session.JsonKeys.STARTED);
    }

    public synchronized void onActivityResumed(Activity activity) {
        if (this.performanceEnabled) {
            SentryDate appStartTime = AppStartState.getInstance().getAppStartTime();
            SentryDate appStartEndTime = AppStartState.getInstance().getAppStartEndTime();
            if (appStartTime != null && appStartEndTime == null) {
                AppStartState.getInstance().setAppStartEnd();
            }
            finishAppStartSpan();
            ISpan iSpan = this.ttidSpanMap.get(activity);
            ISpan iSpan2 = this.ttfdSpanMap.get(activity);
            View findViewById = activity.findViewById(16908290);
            if (this.buildInfoProvider.getSdkInfoVersion() < 16 || findViewById == null) {
                this.mainHandler.post(new ActivityLifecycleIntegration$$ExternalSyntheticLambda1(this, iSpan2, iSpan));
            } else {
                FirstDrawDoneListener.registerForNextDraw(findViewById, new ActivityLifecycleIntegration$$ExternalSyntheticLambda0(this, iSpan2, iSpan), this.buildInfoProvider);
            }
        }
        addBreadcrumb(activity, "resumed");
    }

    public void onActivityPrePaused(Activity activity) {
        if (this.isAllActivityCallbacksAvailable) {
            IHub iHub = this.hub;
            if (iHub == null) {
                this.lastPausedTime = AndroidDateUtils.getCurrentSentryDateTime();
            } else {
                this.lastPausedTime = iHub.getOptions().getDateProvider().now();
            }
        }
    }

    public synchronized void onActivityPaused(Activity activity) {
        if (!this.isAllActivityCallbacksAvailable) {
            IHub iHub = this.hub;
            if (iHub == null) {
                this.lastPausedTime = AndroidDateUtils.getCurrentSentryDateTime();
            } else {
                this.lastPausedTime = iHub.getOptions().getDateProvider().now();
            }
        }
        addBreadcrumb(activity, "paused");
    }

    public synchronized void onActivityStopped(Activity activity) {
        addBreadcrumb(activity, "stopped");
    }

    public synchronized void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        addBreadcrumb(activity, "saveInstanceState");
    }

    public synchronized void onActivityDestroyed(Activity activity) {
        if (this.performanceEnabled || this.options.isEnableActivityLifecycleBreadcrumbs()) {
            addBreadcrumb(activity, "destroyed");
            finishSpan(this.appStartSpan, SpanStatus.CANCELLED);
            ISpan iSpan = this.ttidSpanMap.get(activity);
            finishSpan(iSpan, SpanStatus.DEADLINE_EXCEEDED);
            m2394lambda$startTracing$1$iosentryandroidcoreActivityLifecycleIntegration(this.ttfdSpanMap.get(activity), iSpan);
            cancelTtfdAutoClose();
            stopTracing(activity, true);
            this.appStartSpan = null;
            this.ttidSpanMap.remove(activity);
            this.ttfdSpanMap.remove(activity);
        }
        this.activitiesWithOngoingTransactions.remove(activity);
    }

    private void finishSpan(ISpan iSpan) {
        if (iSpan != null && !iSpan.isFinished()) {
            iSpan.finish();
        }
    }

    private void finishSpan(ISpan iSpan, SentryDate sentryDate) {
        finishSpan(iSpan, sentryDate, (SpanStatus) null);
    }

    private void finishSpan(ISpan iSpan, SentryDate sentryDate, SpanStatus spanStatus) {
        if (iSpan != null && !iSpan.isFinished()) {
            if (spanStatus == null) {
                spanStatus = iSpan.getStatus() != null ? iSpan.getStatus() : SpanStatus.OK;
            }
            iSpan.finish(spanStatus, sentryDate);
        }
    }

    private void finishSpan(ISpan iSpan, SpanStatus spanStatus) {
        if (iSpan != null && !iSpan.isFinished()) {
            iSpan.finish(spanStatus);
        }
    }

    private void cancelTtfdAutoClose() {
        Future<?> future = this.ttfdAutoCloseFuture;
        if (future != null) {
            future.cancel(false);
            this.ttfdAutoCloseFuture = null;
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: onFirstFrameDrawn */
    public void m2392lambda$onActivityResumed$9$iosentryandroidcoreActivityLifecycleIntegration(ISpan iSpan, ISpan iSpan2) {
        SentryAndroidOptions sentryAndroidOptions = this.options;
        if (sentryAndroidOptions == null || iSpan2 == null) {
            finishSpan(iSpan2);
            return;
        }
        SentryDate now = sentryAndroidOptions.getDateProvider().now();
        long millis = TimeUnit.NANOSECONDS.toMillis(now.diff(iSpan2.getStartDate()));
        iSpan2.setMeasurement(MeasurementValue.KEY_TIME_TO_INITIAL_DISPLAY, Long.valueOf(millis), MeasurementUnit.Duration.MILLISECOND);
        if (iSpan != null && iSpan.isFinished()) {
            iSpan.updateEndDate(now);
            iSpan2.setMeasurement(MeasurementValue.KEY_TIME_TO_FULL_DISPLAY, Long.valueOf(millis), MeasurementUnit.Duration.MILLISECOND);
        }
        finishSpan(iSpan2, now);
    }

    /* access modifiers changed from: private */
    /* renamed from: onFullFrameDrawn */
    public void m2390lambda$onActivityCreated$7$iosentryandroidcoreActivityLifecycleIntegration(ISpan iSpan) {
        SentryAndroidOptions sentryAndroidOptions = this.options;
        if (sentryAndroidOptions == null || iSpan == null) {
            finishSpan(iSpan);
        } else {
            SentryDate now = sentryAndroidOptions.getDateProvider().now();
            iSpan.setMeasurement(MeasurementValue.KEY_TIME_TO_FULL_DISPLAY, Long.valueOf(TimeUnit.NANOSECONDS.toMillis(now.diff(iSpan.getStartDate()))), MeasurementUnit.Duration.MILLISECOND);
            finishSpan(iSpan, now);
        }
        cancelTtfdAutoClose();
    }

    /* access modifiers changed from: private */
    /* renamed from: finishExceededTtfdSpan */
    public void m2394lambda$startTracing$1$iosentryandroidcoreActivityLifecycleIntegration(ISpan iSpan, ISpan iSpan2) {
        if (iSpan != null && !iSpan.isFinished()) {
            iSpan.setDescription(getExceededTtfdDesc(iSpan));
            SentryDate finishDate = iSpan2 != null ? iSpan2.getFinishDate() : null;
            if (finishDate == null) {
                finishDate = iSpan.getStartDate();
            }
            finishSpan(iSpan, finishDate, SpanStatus.DEADLINE_EXCEEDED);
        }
    }

    /* access modifiers changed from: package-private */
    public WeakHashMap<Activity, ITransaction> getActivitiesWithOngoingTransactions() {
        return this.activitiesWithOngoingTransactions;
    }

    /* access modifiers changed from: package-private */
    public ActivityFramesTracker getActivityFramesTracker() {
        return this.activityFramesTracker;
    }

    /* access modifiers changed from: package-private */
    public ISpan getAppStartSpan() {
        return this.appStartSpan;
    }

    /* access modifiers changed from: package-private */
    public WeakHashMap<Activity, ISpan> getTtidSpanMap() {
        return this.ttidSpanMap;
    }

    /* access modifiers changed from: package-private */
    public WeakHashMap<Activity, ISpan> getTtfdSpanMap() {
        return this.ttfdSpanMap;
    }

    private void setColdStart(Bundle bundle) {
        if (!this.firstActivityCreated) {
            AppStartState.getInstance().setColdStart(bundle == null);
        }
    }

    private String getTtidDesc(String str) {
        return str + " initial display";
    }

    private String getTtfdDesc(String str) {
        return str + " full display";
    }

    private String getExceededTtfdDesc(ISpan iSpan) {
        String description = iSpan.getDescription();
        if (description == null || !description.endsWith(" - Deadline Exceeded")) {
            return iSpan.getDescription() + " - Deadline Exceeded";
        }
        return description;
    }

    private void finishAppStartSpan() {
        SentryDate appStartEndTime = AppStartState.getInstance().getAppStartEndTime();
        if (this.performanceEnabled && appStartEndTime != null) {
            finishSpan(this.appStartSpan, appStartEndTime);
        }
    }
}
