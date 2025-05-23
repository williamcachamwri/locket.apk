package androidx.lifecycle;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Handler;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ReportFragment;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 '2\u00020\u0001:\u0002&'B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\r\u0010\u0015\u001a\u00020\u0016H\u0000¢\u0006\u0002\b\u0017J\r\u0010\u0018\u001a\u00020\u0016H\u0000¢\u0006\u0002\b\u0019J\r\u0010\u001a\u001a\u00020\u0016H\u0000¢\u0006\u0002\b\u001bJ\r\u0010\u001c\u001a\u00020\u0016H\u0000¢\u0006\u0002\b\u001dJ\u0015\u0010\u001e\u001a\u00020\u00162\u0006\u0010\u001f\u001a\u00020 H\u0000¢\u0006\u0002\b!J\r\u0010\"\u001a\u00020\u0016H\u0000¢\u0006\u0002\b#J\r\u0010$\u001a\u00020\u0016H\u0000¢\u0006\u0002\b%R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\u00020\n8VX\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0012X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u000eX\u000e¢\u0006\u0002\n\u0000¨\u0006("}, d2 = {"Landroidx/lifecycle/ProcessLifecycleOwner;", "Landroidx/lifecycle/LifecycleOwner;", "()V", "delayedPauseRunnable", "Ljava/lang/Runnable;", "handler", "Landroid/os/Handler;", "initializationListener", "Landroidx/lifecycle/ReportFragment$ActivityInitializationListener;", "lifecycle", "Landroidx/lifecycle/Lifecycle;", "getLifecycle", "()Landroidx/lifecycle/Lifecycle;", "pauseSent", "", "registry", "Landroidx/lifecycle/LifecycleRegistry;", "resumedCounter", "", "startedCounter", "stopSent", "activityPaused", "", "activityPaused$lifecycle_process_release", "activityResumed", "activityResumed$lifecycle_process_release", "activityStarted", "activityStarted$lifecycle_process_release", "activityStopped", "activityStopped$lifecycle_process_release", "attach", "context", "Landroid/content/Context;", "attach$lifecycle_process_release", "dispatchPauseIfNeeded", "dispatchPauseIfNeeded$lifecycle_process_release", "dispatchStopIfNeeded", "dispatchStopIfNeeded$lifecycle_process_release", "Api29Impl", "Companion", "lifecycle-process_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ProcessLifecycleOwner.kt */
public final class ProcessLifecycleOwner implements LifecycleOwner {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final long TIMEOUT_MS = 700;
    /* access modifiers changed from: private */
    public static final ProcessLifecycleOwner newInstance = new ProcessLifecycleOwner();
    private final Runnable delayedPauseRunnable = new ProcessLifecycleOwner$$ExternalSyntheticLambda0(this);
    private Handler handler;
    /* access modifiers changed from: private */
    public final ReportFragment.ActivityInitializationListener initializationListener = new ProcessLifecycleOwner$initializationListener$1(this);
    private boolean pauseSent = true;
    private final LifecycleRegistry registry = new LifecycleRegistry(this);
    private int resumedCounter;
    private int startedCounter;
    private boolean stopSent = true;

    @JvmStatic
    public static final LifecycleOwner get() {
        return Companion.get();
    }

    @JvmStatic
    public static final void init$lifecycle_process_release(Context context) {
        Companion.init$lifecycle_process_release(context);
    }

    private ProcessLifecycleOwner() {
    }

    /* access modifiers changed from: private */
    public static final void delayedPauseRunnable$lambda$0(ProcessLifecycleOwner processLifecycleOwner) {
        Intrinsics.checkNotNullParameter(processLifecycleOwner, "this$0");
        processLifecycleOwner.dispatchPauseIfNeeded$lifecycle_process_release();
        processLifecycleOwner.dispatchStopIfNeeded$lifecycle_process_release();
    }

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\b\u001a\u00020\tH\u0007J\u0015\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0001¢\u0006\u0002\b\u000eR\u0016\u0010\u0003\u001a\u00020\u00048\u0000XT¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Landroidx/lifecycle/ProcessLifecycleOwner$Companion;", "", "()V", "TIMEOUT_MS", "", "getTIMEOUT_MS$lifecycle_process_release$annotations", "newInstance", "Landroidx/lifecycle/ProcessLifecycleOwner;", "get", "Landroidx/lifecycle/LifecycleOwner;", "init", "", "context", "Landroid/content/Context;", "init$lifecycle_process_release", "lifecycle-process_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: ProcessLifecycleOwner.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ void getTIMEOUT_MS$lifecycle_process_release$annotations() {
        }

        private Companion() {
        }

        @JvmStatic
        public final LifecycleOwner get() {
            return ProcessLifecycleOwner.newInstance;
        }

        @JvmStatic
        public final void init$lifecycle_process_release(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            ProcessLifecycleOwner.newInstance.attach$lifecycle_process_release(context);
        }
    }

    public final void activityStarted$lifecycle_process_release() {
        int i = this.startedCounter + 1;
        this.startedCounter = i;
        if (i == 1 && this.stopSent) {
            this.registry.handleLifecycleEvent(Lifecycle.Event.ON_START);
            this.stopSent = false;
        }
    }

    public final void activityResumed$lifecycle_process_release() {
        int i = this.resumedCounter + 1;
        this.resumedCounter = i;
        if (i != 1) {
            return;
        }
        if (this.pauseSent) {
            this.registry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME);
            this.pauseSent = false;
            return;
        }
        Handler handler2 = this.handler;
        Intrinsics.checkNotNull(handler2);
        handler2.removeCallbacks(this.delayedPauseRunnable);
    }

    public final void activityPaused$lifecycle_process_release() {
        int i = this.resumedCounter - 1;
        this.resumedCounter = i;
        if (i == 0) {
            Handler handler2 = this.handler;
            Intrinsics.checkNotNull(handler2);
            handler2.postDelayed(this.delayedPauseRunnable, 700);
        }
    }

    public final void activityStopped$lifecycle_process_release() {
        this.startedCounter--;
        dispatchStopIfNeeded$lifecycle_process_release();
    }

    public final void dispatchPauseIfNeeded$lifecycle_process_release() {
        if (this.resumedCounter == 0) {
            this.pauseSent = true;
            this.registry.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE);
        }
    }

    public final void dispatchStopIfNeeded$lifecycle_process_release() {
        if (this.startedCounter == 0 && this.pauseSent) {
            this.registry.handleLifecycleEvent(Lifecycle.Event.ON_STOP);
            this.stopSent = true;
        }
    }

    public final void attach$lifecycle_process_release(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.handler = new Handler();
        this.registry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE);
        Context applicationContext = context.getApplicationContext();
        Intrinsics.checkNotNull(applicationContext, "null cannot be cast to non-null type android.app.Application");
        ((Application) applicationContext).registerActivityLifecycleCallbacks(new ProcessLifecycleOwner$attach$1(this));
    }

    public Lifecycle getLifecycle() {
        return this.registry;
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÁ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007¨\u0006\t"}, d2 = {"Landroidx/lifecycle/ProcessLifecycleOwner$Api29Impl;", "", "()V", "registerActivityLifecycleCallbacks", "", "activity", "Landroid/app/Activity;", "callback", "Landroid/app/Application$ActivityLifecycleCallbacks;", "lifecycle-process_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: ProcessLifecycleOwner.kt */
    public static final class Api29Impl {
        public static final Api29Impl INSTANCE = new Api29Impl();

        private Api29Impl() {
        }

        @JvmStatic
        public static final void registerActivityLifecycleCallbacks(Activity activity, Application.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
            Intrinsics.checkNotNullParameter(activity, "activity");
            Intrinsics.checkNotNullParameter(activityLifecycleCallbacks, "callback");
            activity.registerActivityLifecycleCallbacks(activityLifecycleCallbacks);
        }
    }
}
