package com.facebook.react.modules.debug;

import com.facebook.fbreact.specs.NativeDevSettingsSpec;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.devsupport.interfaces.DevOptionHandler;
import com.facebook.react.devsupport.interfaces.DevSupportManager;
import com.facebook.react.module.annotations.ReactModule;

@ReactModule(name = "DevSettings")
public class DevSettingsModule extends NativeDevSettingsSpec {
    /* access modifiers changed from: private */
    public final DevSupportManager mDevSupportManager;

    public void addListener(String str) {
    }

    public void onFastRefresh() {
    }

    public void removeListeners(double d) {
    }

    public void setIsShakeToShowDevMenuEnabled(boolean z) {
    }

    public DevSettingsModule(ReactApplicationContext reactApplicationContext, DevSupportManager devSupportManager) {
        super(reactApplicationContext);
        this.mDevSupportManager = devSupportManager;
    }

    public void reload() {
        if (this.mDevSupportManager.getDevSupportEnabled()) {
            UiThreadUtil.runOnUiThread(new Runnable() {
                public void run() {
                    DevSettingsModule.this.mDevSupportManager.handleReloadJS();
                }
            });
        }
    }

    public void reloadWithReason(String str) {
        reload();
    }

    public void setHotLoadingEnabled(boolean z) {
        this.mDevSupportManager.setHotModuleReplacementEnabled(z);
    }

    public void setIsDebuggingRemotely(boolean z) {
        this.mDevSupportManager.setRemoteJSDebugEnabled(z);
    }

    public void setProfilingEnabled(boolean z) {
        this.mDevSupportManager.setFpsDebugEnabled(z);
    }

    public void toggleElementInspector() {
        this.mDevSupportManager.toggleElementInspector();
    }

    public void addMenuItem(final String str) {
        this.mDevSupportManager.addCustomDevOption(str, new DevOptionHandler() {
            public void onOptionSelected() {
                WritableMap createMap = Arguments.createMap();
                createMap.putString("title", str);
                ReactApplicationContext access$000 = DevSettingsModule.this.getReactApplicationContextIfActiveOrWarn();
                if (access$000 != null) {
                    access$000.emitDeviceEvent("didPressMenuItem", createMap);
                }
            }
        });
    }
}
