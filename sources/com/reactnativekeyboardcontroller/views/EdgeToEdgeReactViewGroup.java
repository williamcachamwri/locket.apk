package com.reactnativekeyboardcontroller.views;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.views.view.ReactViewGroup;
import com.reactnativekeyboardcontroller.extensions.ReactContextKt;
import com.reactnativekeyboardcontroller.extensions.ThemedReactContextKt;
import com.reactnativekeyboardcontroller.extensions.ViewGroupKt;
import com.reactnativekeyboardcontroller.extensions.ViewKt;
import com.reactnativekeyboardcontroller.listeners.KeyboardAnimationCallback;
import com.reactnativekeyboardcontroller.listeners.KeyboardAnimationCallbackConfig;
import com.reactnativekeyboardcontroller.log.Logger;
import com.reactnativekeyboardcontroller.modal.ModalAttachedWatcher;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\f\b\u0007\u0018\u0000 &2\u00020\u0001:\u0001&B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0013\u001a\u00020\u0014H\u0002J\b\u0010\u0015\u001a\u00020\u0014H\u0002J\u000e\u0010\u0016\u001a\u00020\u00142\u0006\u0010\u000f\u001a\u00020\u0006J\n\u0010\u0017\u001a\u0004\u0018\u00010\bH\u0002J\b\u0010\u0018\u001a\u00020\u0014H\u0014J\u0012\u0010\u0019\u001a\u00020\u00142\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0014J\b\u0010\u001c\u001a\u00020\u0014H\u0014J\b\u0010\u001d\u001a\u00020\u0014H\u0002J\b\u0010\u001e\u001a\u00020\u0014H\u0002J\u000e\u0010\u001f\u001a\u00020\u00142\u0006\u0010\u0005\u001a\u00020\u0006J\u0006\u0010 \u001a\u00020\u0014J\u000e\u0010!\u001a\u00020\u00142\u0006\u0010\r\u001a\u00020\u0006J\u000e\u0010\"\u001a\u00020\u00142\u0006\u0010\u000e\u001a\u00020\u0006J\u000e\u0010#\u001a\u00020\u00142\u0006\u0010\u000f\u001a\u00020\u0006J\b\u0010$\u001a\u00020\u0014H\u0002J\b\u0010%\u001a\u00020\u0014H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\u0001X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lcom/reactnativekeyboardcontroller/views/EdgeToEdgeReactViewGroup;", "Lcom/facebook/react/views/view/ReactViewGroup;", "reactContext", "Lcom/facebook/react/uimanager/ThemedReactContext;", "(Lcom/facebook/react/uimanager/ThemedReactContext;)V", "active", "", "callback", "Lcom/reactnativekeyboardcontroller/listeners/KeyboardAnimationCallback;", "config", "Lcom/reactnativekeyboardcontroller/listeners/KeyboardAnimationCallbackConfig;", "eventView", "isEdgeToEdge", "isNavigationBarTranslucent", "isPreservingEdgeToEdge", "isStatusBarTranslucent", "modalAttachedWatcher", "Lcom/reactnativekeyboardcontroller/modal/ModalAttachedWatcher;", "wasMounted", "disable", "", "enable", "forceStatusBarTranslucent", "getKeyboardCallback", "onAttachedToWindow", "onConfigurationChanged", "newConfig", "Landroid/content/res/Configuration;", "onDetachedFromWindow", "reApplyWindowInsets", "removeKeyboardCallbacks", "setActive", "setEdgeToEdge", "setNavigationBarTranslucent", "setPreserveEdgeToEdge", "setStatusBarTranslucent", "setupKeyboardCallbacks", "setupWindowInsets", "Companion", "react-native-keyboard-controller_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: EdgeToEdgeReactViewGroup.kt */
public final class EdgeToEdgeReactViewGroup extends ReactViewGroup {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final String VIEW_TAG = Reflection.getOrCreateKotlinClass(EdgeToEdgeReactViewGroup.class).getSimpleName();
    private boolean active;
    private KeyboardAnimationCallback callback;
    private final KeyboardAnimationCallbackConfig config;
    private ReactViewGroup eventView;
    private boolean isEdgeToEdge;
    private boolean isNavigationBarTranslucent;
    private boolean isPreservingEdgeToEdge;
    private boolean isStatusBarTranslucent;
    private final ModalAttachedWatcher modalAttachedWatcher;
    private final ThemedReactContext reactContext;
    private boolean wasMounted;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public EdgeToEdgeReactViewGroup(ThemedReactContext themedReactContext) {
        super(themedReactContext);
        Intrinsics.checkNotNullParameter(themedReactContext, "reactContext");
        this.reactContext = themedReactContext;
        KeyboardAnimationCallbackConfig keyboardAnimationCallbackConfig = new KeyboardAnimationCallbackConfig(WindowInsetsCompat.Type.systemBars(), WindowInsetsCompat.Type.ime(), 1, this.isNavigationBarTranslucent);
        this.config = keyboardAnimationCallbackConfig;
        this.modalAttachedWatcher = new ModalAttachedWatcher(this, themedReactContext, keyboardAnimationCallbackConfig, new EdgeToEdgeReactViewGroup$modalAttachedWatcher$1(this));
        ThemedReactContextKt.setupWindowDimensionsListener(themedReactContext);
        setTag(VIEW_TAG);
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!this.wasMounted) {
            this.wasMounted = true;
        } else {
            setupKeyboardCallbacks();
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeKeyboardCallbacks();
    }

    /* access modifiers changed from: protected */
    public void onConfigurationChanged(Configuration configuration) {
        reApplyWindowInsets();
    }

    private final void setupWindowInsets() {
        View rootView = ReactContextKt.getRootView(this.reactContext);
        if (rootView != null) {
            ViewCompat.setOnApplyWindowInsetsListener(rootView, new EdgeToEdgeReactViewGroup$$ExternalSyntheticLambda1(this));
        }
    }

    /* access modifiers changed from: private */
    public static final WindowInsetsCompat setupWindowInsets$lambda$0(EdgeToEdgeReactViewGroup edgeToEdgeReactViewGroup, View view, WindowInsetsCompat windowInsetsCompat) {
        int i;
        int i2;
        Intrinsics.checkNotNullParameter(edgeToEdgeReactViewGroup, "this$0");
        Intrinsics.checkNotNullParameter(view, "v");
        Intrinsics.checkNotNullParameter(windowInsetsCompat, "insets");
        ViewGroup content = ReactContextKt.getContent(edgeToEdgeReactViewGroup.reactContext);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        boolean z = edgeToEdgeReactViewGroup.active;
        boolean z2 = true;
        int i3 = 0;
        boolean z3 = !z || edgeToEdgeReactViewGroup.isStatusBarTranslucent;
        if (z && !edgeToEdgeReactViewGroup.isNavigationBarTranslucent) {
            z2 = false;
        }
        Insets insets = windowInsetsCompat.getInsets(WindowInsetsCompat.Type.navigationBars());
        Intrinsics.checkNotNullExpressionValue(insets, "getInsets(...)");
        Insets insets2 = windowInsetsCompat.getInsets(WindowInsetsCompat.Type.systemBars());
        Intrinsics.checkNotNullExpressionValue(insets2, "getInsets(...)");
        int i4 = insets.left;
        if (z3) {
            i = 0;
        } else {
            i = insets2.top;
        }
        int i5 = insets.right;
        if (z2) {
            i2 = 0;
        } else {
            i2 = insets.bottom;
        }
        layoutParams.setMargins(i4, i, i5, i2);
        if (content != null) {
            content.setLayoutParams(layoutParams);
        }
        WindowInsetsCompat onApplyWindowInsets = ViewCompat.onApplyWindowInsets(view, windowInsetsCompat);
        Intrinsics.checkNotNullExpressionValue(onApplyWindowInsets, "onApplyWindowInsets(...)");
        int systemWindowInsetLeft = onApplyWindowInsets.getSystemWindowInsetLeft();
        if (!edgeToEdgeReactViewGroup.isStatusBarTranslucent) {
            i3 = onApplyWindowInsets.getSystemWindowInsetTop();
        }
        return onApplyWindowInsets.replaceSystemWindowInsets(systemWindowInsetLeft, i3, onApplyWindowInsets.getSystemWindowInsetRight(), onApplyWindowInsets.getSystemWindowInsetBottom());
    }

    public final void setEdgeToEdge() {
        boolean z = this.active || this.isPreservingEdgeToEdge;
        if (this.isEdgeToEdge != z) {
            this.isEdgeToEdge = z;
            Activity currentActivity = this.reactContext.getCurrentActivity();
            if (currentActivity != null) {
                WindowCompat.setDecorFitsSystemWindows(currentActivity.getWindow(), true ^ this.isEdgeToEdge);
            }
        }
    }

    private final void setupKeyboardCallbacks() {
        if (this.reactContext.getCurrentActivity() != null) {
            this.eventView = new ReactViewGroup(getContext());
            ViewGroup content = ReactContextKt.getContent(this.reactContext);
            if (content != null) {
                content.addView(this.eventView);
            }
            KeyboardAnimationCallback keyboardAnimationCallback = new KeyboardAnimationCallback(this, this, this.reactContext, this.config);
            this.callback = keyboardAnimationCallback;
            ReactViewGroup reactViewGroup = this.eventView;
            if (reactViewGroup != null) {
                View view = reactViewGroup;
                ViewCompat.setWindowInsetsAnimationCallback(view, keyboardAnimationCallback);
                ViewCompat.setOnApplyWindowInsetsListener(view, this.callback);
                ViewKt.requestApplyInsetsWhenAttached(view);
                return;
            }
            return;
        }
        Logger.w$default(Logger.INSTANCE, EdgeToEdgeReactViewGroupKt.TAG, "Can not setup keyboard animation listener, since `currentActivity` is null", (Throwable) null, 4, (Object) null);
    }

    private final void removeKeyboardCallbacks() {
        KeyboardAnimationCallback keyboardAnimationCallback = this.callback;
        if (keyboardAnimationCallback != null) {
            keyboardAnimationCallback.destroy();
        }
        new Handler(Looper.getMainLooper()).post(new EdgeToEdgeReactViewGroup$$ExternalSyntheticLambda0(this.eventView));
    }

    /* access modifiers changed from: private */
    public static final void removeKeyboardCallbacks$lambda$3(ReactViewGroup reactViewGroup) {
        ViewGroupKt.removeSelf(reactViewGroup);
    }

    private final void reApplyWindowInsets() {
        setupWindowInsets();
        ViewKt.requestApplyInsetsWhenAttached(this);
    }

    private final void enable() {
        setupWindowInsets();
        setupKeyboardCallbacks();
        this.modalAttachedWatcher.enable();
    }

    private final void disable() {
        setupWindowInsets();
        removeKeyboardCallbacks();
        this.modalAttachedWatcher.disable();
    }

    /* access modifiers changed from: private */
    public final KeyboardAnimationCallback getKeyboardCallback() {
        return this.callback;
    }

    public final void setStatusBarTranslucent(boolean z) {
        this.isStatusBarTranslucent = z;
    }

    public final void setNavigationBarTranslucent(boolean z) {
        this.isNavigationBarTranslucent = z;
        this.config.setHasTranslucentNavigationBar(z);
    }

    public final void setPreserveEdgeToEdge(boolean z) {
        this.isPreservingEdgeToEdge = z;
    }

    public final void setActive(boolean z) {
        this.active = z;
        if (z) {
            enable();
        } else {
            disable();
        }
    }

    public final void forceStatusBarTranslucent(boolean z) {
        if (this.active && this.isStatusBarTranslucent != z) {
            this.isStatusBarTranslucent = z;
            reApplyWindowInsets();
        }
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0013\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/reactnativekeyboardcontroller/views/EdgeToEdgeReactViewGroup$Companion;", "", "()V", "VIEW_TAG", "", "getVIEW_TAG", "()Ljava/lang/String;", "react-native-keyboard-controller_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: EdgeToEdgeReactViewGroup.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getVIEW_TAG() {
            return EdgeToEdgeReactViewGroup.VIEW_TAG;
        }
    }
}
