package com.google.firebase.appcheck.internal;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.appcheck.AppCheckProvider;
import com.google.firebase.appcheck.AppCheckProviderFactory;
import com.google.firebase.appcheck.AppCheckToken;
import com.google.firebase.appcheck.AppCheckTokenResult;
import com.google.firebase.appcheck.FirebaseAppCheck;
import com.google.firebase.appcheck.internal.util.Clock;
import com.google.firebase.appcheck.interop.AppCheckTokenListener;
import com.google.firebase.heartbeatinfo.HeartBeatController;
import com.google.firebase.inject.Provider;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;

public class DefaultFirebaseAppCheck extends FirebaseAppCheck {
    private static final long BUFFER_TIME_MILLIS = 300000;
    private final List<FirebaseAppCheck.AppCheckListener> appCheckListenerList = new ArrayList();
    private AppCheckProvider appCheckProvider;
    private AppCheckProviderFactory appCheckProviderFactory;
    private final List<AppCheckTokenListener> appCheckTokenListenerList = new ArrayList();
    private final Executor backgroundExecutor;
    private AppCheckToken cachedToken;
    private Task<AppCheckToken> cachedTokenTask;
    private final Clock clock;
    private final FirebaseApp firebaseApp;
    private final Provider<HeartBeatController> heartbeatControllerProvider;
    private final Executor liteExecutor;
    private final Task<Void> retrieveStoredTokenTask;
    private final StorageHelper storageHelper;
    private final TokenRefreshManager tokenRefreshManager;
    private final Executor uiExecutor;

    public DefaultFirebaseAppCheck(FirebaseApp firebaseApp2, Provider<HeartBeatController> provider, Executor executor, Executor executor2, Executor executor3, ScheduledExecutorService scheduledExecutorService) {
        Preconditions.checkNotNull(firebaseApp2);
        Preconditions.checkNotNull(provider);
        this.firebaseApp = firebaseApp2;
        this.heartbeatControllerProvider = provider;
        this.storageHelper = new StorageHelper(firebaseApp2.getApplicationContext(), firebaseApp2.getPersistenceKey());
        this.tokenRefreshManager = new TokenRefreshManager(firebaseApp2.getApplicationContext(), this, executor2, scheduledExecutorService);
        this.uiExecutor = executor;
        this.liteExecutor = executor2;
        this.backgroundExecutor = executor3;
        this.retrieveStoredTokenTask = retrieveStoredAppCheckTokenInBackground(executor3);
        this.clock = new Clock.DefaultClock();
    }

    private Task<Void> retrieveStoredAppCheckTokenInBackground(Executor executor) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        executor.execute(new DefaultFirebaseAppCheck$$ExternalSyntheticLambda6(this, taskCompletionSource));
        return taskCompletionSource.getTask();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$retrieveStoredAppCheckTokenInBackground$0$com-google-firebase-appcheck-internal-DefaultFirebaseAppCheck  reason: not valid java name */
    public /* synthetic */ void m575lambda$retrieveStoredAppCheckTokenInBackground$0$comgooglefirebaseappcheckinternalDefaultFirebaseAppCheck(TaskCompletionSource taskCompletionSource) {
        AppCheckToken retrieveAppCheckToken = this.storageHelper.retrieveAppCheckToken();
        if (retrieveAppCheckToken != null) {
            setCachedToken(retrieveAppCheckToken);
        }
        taskCompletionSource.setResult(null);
    }

    public void installAppCheckProviderFactory(AppCheckProviderFactory appCheckProviderFactory2) {
        installAppCheckProviderFactory(appCheckProviderFactory2, this.firebaseApp.isDataCollectionDefaultEnabled());
    }

    public void installAppCheckProviderFactory(AppCheckProviderFactory appCheckProviderFactory2, boolean z) {
        Preconditions.checkNotNull(appCheckProviderFactory2);
        this.appCheckProviderFactory = appCheckProviderFactory2;
        this.appCheckProvider = appCheckProviderFactory2.create(this.firebaseApp);
        this.tokenRefreshManager.setIsAutoRefreshEnabled(z);
    }

    public AppCheckProviderFactory getInstalledAppCheckProviderFactory() {
        return this.appCheckProviderFactory;
    }

    public void setTokenAutoRefreshEnabled(boolean z) {
        this.tokenRefreshManager.setIsAutoRefreshEnabled(z);
    }

    public void resetAppCheckState() {
        this.appCheckProviderFactory = null;
        this.appCheckProvider = null;
        this.cachedToken = null;
        this.storageHelper.clearSharedPrefs();
    }

    public void addAppCheckTokenListener(AppCheckTokenListener appCheckTokenListener) {
        Preconditions.checkNotNull(appCheckTokenListener);
        this.appCheckTokenListenerList.add(appCheckTokenListener);
        this.tokenRefreshManager.onListenerCountChanged(this.appCheckTokenListenerList.size() + this.appCheckListenerList.size());
        if (hasValidToken()) {
            appCheckTokenListener.onAppCheckTokenChanged(DefaultAppCheckTokenResult.constructFromAppCheckToken(this.cachedToken));
        }
    }

    public void removeAppCheckTokenListener(AppCheckTokenListener appCheckTokenListener) {
        Preconditions.checkNotNull(appCheckTokenListener);
        this.appCheckTokenListenerList.remove(appCheckTokenListener);
        this.tokenRefreshManager.onListenerCountChanged(this.appCheckTokenListenerList.size() + this.appCheckListenerList.size());
    }

    public void addAppCheckListener(FirebaseAppCheck.AppCheckListener appCheckListener) {
        Preconditions.checkNotNull(appCheckListener);
        this.appCheckListenerList.add(appCheckListener);
        this.tokenRefreshManager.onListenerCountChanged(this.appCheckTokenListenerList.size() + this.appCheckListenerList.size());
        if (hasValidToken()) {
            appCheckListener.onAppCheckTokenChanged(this.cachedToken);
        }
    }

    public void removeAppCheckListener(FirebaseAppCheck.AppCheckListener appCheckListener) {
        Preconditions.checkNotNull(appCheckListener);
        this.appCheckListenerList.remove(appCheckListener);
        this.tokenRefreshManager.onListenerCountChanged(this.appCheckTokenListenerList.size() + this.appCheckListenerList.size());
    }

    public Task<AppCheckTokenResult> getToken(boolean z) {
        return this.retrieveStoredTokenTask.continueWithTask(this.liteExecutor, new DefaultFirebaseAppCheck$$ExternalSyntheticLambda4(this, z));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getToken$2$com-google-firebase-appcheck-internal-DefaultFirebaseAppCheck  reason: not valid java name */
    public /* synthetic */ Task m574lambda$getToken$2$comgooglefirebaseappcheckinternalDefaultFirebaseAppCheck(boolean z, Task task) throws Exception {
        if (!z && hasValidToken()) {
            return Tasks.forResult(DefaultAppCheckTokenResult.constructFromAppCheckToken(this.cachedToken));
        }
        if (this.appCheckProvider == null) {
            return Tasks.forResult(DefaultAppCheckTokenResult.constructFromError(new FirebaseException("No AppCheckProvider installed.")));
        }
        Task<AppCheckToken> task2 = this.cachedTokenTask;
        if (task2 == null || task2.isComplete() || this.cachedTokenTask.isCanceled()) {
            this.cachedTokenTask = fetchTokenFromProvider();
        }
        return this.cachedTokenTask.continueWithTask(this.liteExecutor, new DefaultFirebaseAppCheck$$ExternalSyntheticLambda1());
    }

    static /* synthetic */ Task lambda$getToken$1(Task task) throws Exception {
        if (task.isSuccessful()) {
            return Tasks.forResult(DefaultAppCheckTokenResult.constructFromAppCheckToken((AppCheckToken) task.getResult()));
        }
        return Tasks.forResult(DefaultAppCheckTokenResult.constructFromError(new FirebaseException(task.getException().getMessage(), task.getException())));
    }

    public Task<AppCheckTokenResult> getLimitedUseToken() {
        return getLimitedUseAppCheckToken().continueWithTask(this.liteExecutor, new DefaultFirebaseAppCheck$$ExternalSyntheticLambda5());
    }

    static /* synthetic */ Task lambda$getLimitedUseToken$3(Task task) throws Exception {
        if (task.isSuccessful()) {
            return Tasks.forResult(DefaultAppCheckTokenResult.constructFromAppCheckToken((AppCheckToken) task.getResult()));
        }
        return Tasks.forResult(DefaultAppCheckTokenResult.constructFromError(new FirebaseException(task.getException().getMessage(), task.getException())));
    }

    public Task<AppCheckToken> getAppCheckToken(boolean z) {
        return this.retrieveStoredTokenTask.continueWithTask(this.liteExecutor, new DefaultFirebaseAppCheck$$ExternalSyntheticLambda3(this, z));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getAppCheckToken$4$com-google-firebase-appcheck-internal-DefaultFirebaseAppCheck  reason: not valid java name */
    public /* synthetic */ Task m573lambda$getAppCheckToken$4$comgooglefirebaseappcheckinternalDefaultFirebaseAppCheck(boolean z, Task task) throws Exception {
        if (!z && hasValidToken()) {
            return Tasks.forResult(this.cachedToken);
        }
        if (this.appCheckProvider == null) {
            return Tasks.forException(new FirebaseException("No AppCheckProvider installed."));
        }
        Task<AppCheckToken> task2 = this.cachedTokenTask;
        if (task2 == null || task2.isComplete() || this.cachedTokenTask.isCanceled()) {
            this.cachedTokenTask = fetchTokenFromProvider();
        }
        return this.cachedTokenTask;
    }

    public Task<AppCheckToken> getLimitedUseAppCheckToken() {
        AppCheckProvider appCheckProvider2 = this.appCheckProvider;
        if (appCheckProvider2 == null) {
            return Tasks.forException(new FirebaseException("No AppCheckProvider installed."));
        }
        return appCheckProvider2.getToken();
    }

    /* access modifiers changed from: package-private */
    public Task<AppCheckToken> fetchTokenFromProvider() {
        return this.appCheckProvider.getToken().onSuccessTask(this.uiExecutor, new DefaultFirebaseAppCheck$$ExternalSyntheticLambda2(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$fetchTokenFromProvider$5$com-google-firebase-appcheck-internal-DefaultFirebaseAppCheck  reason: not valid java name */
    public /* synthetic */ Task m572lambda$fetchTokenFromProvider$5$comgooglefirebaseappcheckinternalDefaultFirebaseAppCheck(AppCheckToken appCheckToken) throws Exception {
        updateStoredToken(appCheckToken);
        for (FirebaseAppCheck.AppCheckListener onAppCheckTokenChanged : this.appCheckListenerList) {
            onAppCheckTokenChanged.onAppCheckTokenChanged(appCheckToken);
        }
        DefaultAppCheckTokenResult constructFromAppCheckToken = DefaultAppCheckTokenResult.constructFromAppCheckToken(appCheckToken);
        for (AppCheckTokenListener onAppCheckTokenChanged2 : this.appCheckTokenListenerList) {
            onAppCheckTokenChanged2.onAppCheckTokenChanged(constructFromAppCheckToken);
        }
        return Tasks.forResult(appCheckToken);
    }

    /* access modifiers changed from: package-private */
    public Provider<HeartBeatController> getHeartbeatControllerProvider() {
        return this.heartbeatControllerProvider;
    }

    /* access modifiers changed from: package-private */
    public void setCachedToken(AppCheckToken appCheckToken) {
        this.cachedToken = appCheckToken;
    }

    private void updateStoredToken(AppCheckToken appCheckToken) {
        this.backgroundExecutor.execute(new DefaultFirebaseAppCheck$$ExternalSyntheticLambda0(this, appCheckToken));
        setCachedToken(appCheckToken);
        this.tokenRefreshManager.maybeScheduleTokenRefresh(appCheckToken);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$updateStoredToken$6$com-google-firebase-appcheck-internal-DefaultFirebaseAppCheck  reason: not valid java name */
    public /* synthetic */ void m576lambda$updateStoredToken$6$comgooglefirebaseappcheckinternalDefaultFirebaseAppCheck(AppCheckToken appCheckToken) {
        this.storageHelper.saveAppCheckToken(appCheckToken);
    }

    private boolean hasValidToken() {
        AppCheckToken appCheckToken = this.cachedToken;
        return appCheckToken != null && appCheckToken.getExpireTimeMillis() - this.clock.currentTimeMillis() > 300000;
    }
}
