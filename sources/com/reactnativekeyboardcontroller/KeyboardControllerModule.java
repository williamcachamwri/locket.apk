package com.reactnativekeyboardcontroller;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.reactnativekeyboardcontroller.modules.KeyboardControllerModuleImpl;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0012\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0007J\u0010\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\rH\u0007J\b\u0010\u000e\u001a\u00020\nH\u0016J\u0017\u0010\u000f\u001a\u00020\b2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0007¢\u0006\u0002\u0010\u0012J\b\u0010\u0013\u001a\u00020\bH\u0007J\u0010\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\nH\u0007J\u0010\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\u0011H\u0007R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/reactnativekeyboardcontroller/KeyboardControllerModule;", "Lcom/facebook/react/bridge/ReactContextBaseJavaModule;", "mReactContext", "Lcom/facebook/react/bridge/ReactApplicationContext;", "(Lcom/facebook/react/bridge/ReactApplicationContext;)V", "module", "Lcom/reactnativekeyboardcontroller/modules/KeyboardControllerModuleImpl;", "addListener", "", "eventName", "", "dismiss", "keepFocus", "", "getName", "removeListeners", "count", "", "(Ljava/lang/Integer;)V", "setDefaultMode", "setFocusTo", "direction", "setInputMode", "mode", "react-native-keyboard-controller_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: KeyboardControllerModule.kt */
public final class KeyboardControllerModule extends ReactContextBaseJavaModule {
    private final KeyboardControllerModuleImpl module;

    @ReactMethod
    public final void addListener(String str) {
    }

    public String getName() {
        return KeyboardControllerModuleImpl.NAME;
    }

    @ReactMethod
    public final void removeListeners(Integer num) {
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public KeyboardControllerModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        Intrinsics.checkNotNullParameter(reactApplicationContext, "mReactContext");
        this.module = new KeyboardControllerModuleImpl(reactApplicationContext);
    }

    @ReactMethod
    public final void setInputMode(int i) {
        this.module.setInputMode(i);
    }

    @ReactMethod
    public final void setDefaultMode() {
        this.module.setDefaultMode();
    }

    @ReactMethod
    public final void dismiss(boolean z) {
        this.module.dismiss(z);
    }

    @ReactMethod
    public final void setFocusTo(String str) {
        Intrinsics.checkNotNullParameter(str, "direction");
        this.module.setFocusTo(str);
    }
}
