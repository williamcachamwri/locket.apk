package com.facebook.react.turbomodule.core;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.turbomodule.core.TurboModuleManager;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class TurboModuleManager$$ExternalSyntheticLambda2 implements TurboModuleManager.ModuleProvider {
    public final /* synthetic */ TurboModuleManagerDelegate f$0;

    public /* synthetic */ TurboModuleManager$$ExternalSyntheticLambda2(TurboModuleManagerDelegate turboModuleManagerDelegate) {
        this.f$0 = turboModuleManagerDelegate;
    }

    public final NativeModule getModule(String str) {
        return TurboModuleManager.lambda$new$2(this.f$0, str);
    }
}
