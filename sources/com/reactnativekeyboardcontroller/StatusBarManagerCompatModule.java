package com.reactnativekeyboardcontroller;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.reactnativekeyboardcontroller.modules.StatusBarManagerCompatModuleImpl;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\bH\u0016J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0003J\u0010\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u000eH\u0003J\u0010\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\bH\u0003J\u0010\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u000eH\u0003R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/reactnativekeyboardcontroller/StatusBarManagerCompatModule;", "Lcom/facebook/react/bridge/ReactContextBaseJavaModule;", "mReactContext", "Lcom/facebook/react/bridge/ReactApplicationContext;", "(Lcom/facebook/react/bridge/ReactApplicationContext;)V", "module", "Lcom/reactnativekeyboardcontroller/modules/StatusBarManagerCompatModuleImpl;", "getName", "", "setColor", "", "color", "", "animated", "", "setHidden", "hidden", "setStyle", "style", "setTranslucent", "translucent", "react-native-keyboard-controller_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: StatusBarManagerCompatModule.kt */
public final class StatusBarManagerCompatModule extends ReactContextBaseJavaModule {
    private final StatusBarManagerCompatModuleImpl module;

    public String getName() {
        return StatusBarManagerCompatModuleImpl.NAME;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public StatusBarManagerCompatModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        Intrinsics.checkNotNullParameter(reactApplicationContext, "mReactContext");
        this.module = new StatusBarManagerCompatModuleImpl(reactApplicationContext);
    }

    @ReactMethod
    private final void setHidden(boolean z) {
        this.module.setHidden(z);
    }

    @ReactMethod
    private final void setColor(int i, boolean z) {
        this.module.setColor(i, z);
    }

    @ReactMethod
    private final void setTranslucent(boolean z) {
        this.module.setTranslucent(z);
    }

    @ReactMethod
    private final void setStyle(String str) {
        this.module.setStyle(str);
    }
}
