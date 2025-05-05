package com.locket.Locket.Ads;

import com.adsbynimbus.render.AdController;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactContext;
import java.util.function.Supplier;

public final class NimbusAdLifecycleListener implements LifecycleEventListener {
    private final Supplier<AdController> provider;

    public NimbusAdLifecycleListener(ReactContext reactContext, Supplier<AdController> supplier) {
        this.provider = supplier;
        reactContext.addLifecycleEventListener(this);
    }

    public void onHostResume() {
        AdController adController = this.provider.get();
        if (adController != null) {
            adController.start();
        }
    }

    public void onHostPause() {
        AdController adController = this.provider.get();
        if (adController != null) {
            adController.stop();
        }
    }

    public void onHostDestroy() {
        AdController adController = this.provider.get();
        if (adController != null) {
            adController.destroy();
        }
    }
}
