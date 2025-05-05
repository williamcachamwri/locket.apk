package com.reactnativekeyboardcontroller.modules;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.view.View;
import android.view.Window;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.UiThreadUtil;
import com.reactnativekeyboardcontroller.extensions.ReactContextKt;
import com.reactnativekeyboardcontroller.log.Logger;
import com.reactnativekeyboardcontroller.views.EdgeToEdgeReactViewGroup;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\n\u0010\n\u001a\u0004\u0018\u00010\u0006H\u0002J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0007J\u000e\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u0010J\u000e\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\u0015J\u000e\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u0010R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/reactnativekeyboardcontroller/modules/StatusBarManagerCompatModuleImpl;", "", "mReactContext", "Lcom/facebook/react/bridge/ReactApplicationContext;", "(Lcom/facebook/react/bridge/ReactApplicationContext;)V", "controller", "Landroidx/core/view/WindowInsetsControllerCompat;", "lastActivity", "Ljava/lang/ref/WeakReference;", "Landroid/app/Activity;", "getController", "setColor", "", "color", "", "animated", "", "setHidden", "hidden", "setStyle", "style", "", "setTranslucent", "translucent", "Companion", "react-native-keyboard-controller_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: StatusBarManagerCompatModuleImpl.kt */
public final class StatusBarManagerCompatModuleImpl {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final long DEFAULT_ANIMATION_TIME = 300;
    public static final String NAME = "StatusBarManagerCompat";
    private WindowInsetsControllerCompat controller;
    private WeakReference<Activity> lastActivity = new WeakReference<>((Object) null);
    private final ReactApplicationContext mReactContext;

    public StatusBarManagerCompatModuleImpl(ReactApplicationContext reactApplicationContext) {
        Intrinsics.checkNotNullParameter(reactApplicationContext, "mReactContext");
        this.mReactContext = reactApplicationContext;
    }

    public final void setHidden(boolean z) {
        UiThreadUtil.runOnUiThread(new StatusBarManagerCompatModuleImpl$$ExternalSyntheticLambda0(z, this));
    }

    /* access modifiers changed from: private */
    public static final void setHidden$lambda$0(boolean z, StatusBarManagerCompatModuleImpl statusBarManagerCompatModuleImpl) {
        Intrinsics.checkNotNullParameter(statusBarManagerCompatModuleImpl, "this$0");
        if (z) {
            WindowInsetsControllerCompat controller2 = statusBarManagerCompatModuleImpl.getController();
            if (controller2 != null) {
                controller2.hide(WindowInsetsCompat.Type.statusBars());
                return;
            }
            return;
        }
        WindowInsetsControllerCompat controller3 = statusBarManagerCompatModuleImpl.getController();
        if (controller3 != null) {
            controller3.show(WindowInsetsCompat.Type.statusBars());
        }
    }

    public final void setColor(int i, boolean z) {
        Activity currentActivity = this.mReactContext.getCurrentActivity();
        if (currentActivity == null) {
            Logger.w$default(Logger.INSTANCE, StatusBarManagerCompatModuleImplKt.TAG, "StatusBarManagerCompatModule: Ignored status bar change, current activity is null.", (Throwable) null, 4, (Object) null);
        } else {
            UiThreadUtil.runOnUiThread(new StatusBarManagerCompatModuleImpl$$ExternalSyntheticLambda4(currentActivity, z, i));
        }
    }

    /* access modifiers changed from: private */
    public static final void setColor$lambda$2(Activity activity, boolean z, int i) {
        Window window = activity.getWindow();
        if (z) {
            ValueAnimator ofObject = ValueAnimator.ofObject(new ArgbEvaluator(), new Object[]{Integer.valueOf(window.getStatusBarColor()), Integer.valueOf(i)});
            ofObject.addUpdateListener(new StatusBarManagerCompatModuleImpl$$ExternalSyntheticLambda1(window));
            ofObject.setDuration(DEFAULT_ANIMATION_TIME).setStartDelay(0);
            ofObject.start();
            return;
        }
        window.setStatusBarColor(i);
    }

    /* access modifiers changed from: private */
    public static final void setColor$lambda$2$lambda$1(Window window, ValueAnimator valueAnimator) {
        Intrinsics.checkNotNullParameter(valueAnimator, "animator");
        Object animatedValue = valueAnimator.getAnimatedValue();
        Intrinsics.checkNotNull(animatedValue, "null cannot be cast to non-null type kotlin.Int");
        window.setStatusBarColor(((Integer) animatedValue).intValue());
    }

    public final void setTranslucent(boolean z) {
        UiThreadUtil.runOnUiThread(new StatusBarManagerCompatModuleImpl$$ExternalSyntheticLambda2(this, z));
    }

    /* access modifiers changed from: private */
    public static final void setTranslucent$lambda$3(StatusBarManagerCompatModuleImpl statusBarManagerCompatModuleImpl, boolean z) {
        Intrinsics.checkNotNullParameter(statusBarManagerCompatModuleImpl, "this$0");
        View rootView = ReactContextKt.getRootView(statusBarManagerCompatModuleImpl.mReactContext);
        EdgeToEdgeReactViewGroup edgeToEdgeReactViewGroup = rootView != null ? (EdgeToEdgeReactViewGroup) rootView.findViewWithTag(EdgeToEdgeReactViewGroup.Companion.getVIEW_TAG()) : null;
        if (edgeToEdgeReactViewGroup != null) {
            edgeToEdgeReactViewGroup.forceStatusBarTranslucent(z);
        }
    }

    public final void setStyle(String str) {
        Intrinsics.checkNotNullParameter(str, TtmlNode.TAG_STYLE);
        UiThreadUtil.runOnUiThread(new StatusBarManagerCompatModuleImpl$$ExternalSyntheticLambda3(this, str));
    }

    /* access modifiers changed from: private */
    public static final void setStyle$lambda$4(StatusBarManagerCompatModuleImpl statusBarManagerCompatModuleImpl, String str) {
        Intrinsics.checkNotNullParameter(statusBarManagerCompatModuleImpl, "this$0");
        Intrinsics.checkNotNullParameter(str, "$style");
        WindowInsetsControllerCompat controller2 = statusBarManagerCompatModuleImpl.getController();
        if (controller2 != null) {
            controller2.setAppearanceLightStatusBars(Intrinsics.areEqual((Object) str, (Object) "dark-content"));
        }
    }

    private final WindowInsetsControllerCompat getController() {
        Activity currentActivity = this.mReactContext.getCurrentActivity();
        if (this.controller == null || !Intrinsics.areEqual((Object) currentActivity, this.lastActivity.get())) {
            if (currentActivity == null) {
                Logger.w$default(Logger.INSTANCE, StatusBarManagerCompatModuleImplKt.TAG, "StatusBarManagerCompatModule: can not get `WindowInsetsControllerCompat` because current activity is null.", (Throwable) null, 4, (Object) null);
                return this.controller;
            }
            Window window = currentActivity.getWindow();
            this.lastActivity = new WeakReference<>(currentActivity);
            this.controller = new WindowInsetsControllerCompat(window, window.getDecorView());
        }
        return this.controller;
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/reactnativekeyboardcontroller/modules/StatusBarManagerCompatModuleImpl$Companion;", "", "()V", "DEFAULT_ANIMATION_TIME", "", "NAME", "", "react-native-keyboard-controller_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: StatusBarManagerCompatModuleImpl.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
