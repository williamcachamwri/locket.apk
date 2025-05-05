package com.reactnativekeyboardcontroller.modules;

import android.app.Activity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.UiThreadUtil;
import com.reactnativekeyboardcontroller.traversal.FocusedInputHolder;
import com.reactnativekeyboardcontroller.traversal.ViewHierarchyNavigator;
import io.sentry.protocol.SentryThread;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\b\u0010\u000b\u001a\u00020\u0006H\u0002J\u0006\u0010\f\u001a\u00020\bJ\u000e\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u0006J\u0010\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u0006H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/reactnativekeyboardcontroller/modules/KeyboardControllerModuleImpl;", "", "mReactContext", "Lcom/facebook/react/bridge/ReactApplicationContext;", "(Lcom/facebook/react/bridge/ReactApplicationContext;)V", "mDefaultMode", "", "dismiss", "", "keepFocus", "", "getCurrentMode", "setDefaultMode", "setFocusTo", "direction", "", "setInputMode", "mode", "setSoftInputMode", "Companion", "react-native-keyboard-controller_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: KeyboardControllerModuleImpl.kt */
public final class KeyboardControllerModuleImpl {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String NAME = "KeyboardController";
    private final int mDefaultMode = getCurrentMode();
    private final ReactApplicationContext mReactContext;

    public KeyboardControllerModuleImpl(ReactApplicationContext reactApplicationContext) {
        Intrinsics.checkNotNullParameter(reactApplicationContext, "mReactContext");
        this.mReactContext = reactApplicationContext;
    }

    public final void setInputMode(int i) {
        setSoftInputMode(i);
    }

    public final void setDefaultMode() {
        setSoftInputMode(this.mDefaultMode);
    }

    public final void dismiss(boolean z) {
        Activity currentActivity = this.mReactContext.getCurrentActivity();
        View view = FocusedInputHolder.INSTANCE.get();
        if (view != null) {
            UiThreadUtil.runOnUiThread(new KeyboardControllerModuleImpl$$ExternalSyntheticLambda0(currentActivity, view, z));
        }
    }

    /* access modifiers changed from: private */
    public static final void dismiss$lambda$0(Activity activity, View view, boolean z) {
        InputMethodManager inputMethodManager = null;
        Object systemService = activity != null ? activity.getSystemService("input_method") : null;
        if (systemService instanceof InputMethodManager) {
            inputMethodManager = (InputMethodManager) systemService;
        }
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
        if (!z) {
            view.clearFocus();
        }
    }

    public final void setFocusTo(String str) {
        Intrinsics.checkNotNullParameter(str, "direction");
        if (Intrinsics.areEqual((Object) str, (Object) SentryThread.JsonKeys.CURRENT)) {
            UiThreadUtil.runOnUiThread(new KeyboardControllerModuleImpl$$ExternalSyntheticLambda1());
            return;
        }
        View view = FocusedInputHolder.INSTANCE.get();
        if (view != null) {
            ViewHierarchyNavigator.INSTANCE.setFocusTo(str, view);
        }
    }

    /* access modifiers changed from: private */
    public static final void setFocusTo$lambda$1() {
        FocusedInputHolder.INSTANCE.focus();
    }

    private final void setSoftInputMode(int i) {
        UiThreadUtil.runOnUiThread(new KeyboardControllerModuleImpl$$ExternalSyntheticLambda2(this, i));
    }

    /* access modifiers changed from: private */
    public static final void setSoftInputMode$lambda$2(KeyboardControllerModuleImpl keyboardControllerModuleImpl, int i) {
        Activity currentActivity;
        Window window;
        Intrinsics.checkNotNullParameter(keyboardControllerModuleImpl, "this$0");
        if (keyboardControllerModuleImpl.getCurrentMode() != i && (currentActivity = keyboardControllerModuleImpl.mReactContext.getCurrentActivity()) != null && (window = currentActivity.getWindow()) != null) {
            window.setSoftInputMode(i);
        }
    }

    private final int getCurrentMode() {
        Window window;
        WindowManager.LayoutParams attributes;
        Activity currentActivity = this.mReactContext.getCurrentActivity();
        if (currentActivity == null || (window = currentActivity.getWindow()) == null || (attributes = window.getAttributes()) == null) {
            return 0;
        }
        return attributes.softInputMode;
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/reactnativekeyboardcontroller/modules/KeyboardControllerModuleImpl$Companion;", "", "()V", "NAME", "", "react-native-keyboard-controller_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: KeyboardControllerModuleImpl.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
