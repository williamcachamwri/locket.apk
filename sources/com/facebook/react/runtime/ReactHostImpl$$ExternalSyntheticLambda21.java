package com.facebook.react.runtime;

import com.facebook.react.devsupport.DevSupportManagerBase;
import com.facebook.react.devsupport.interfaces.BundleLoadCallback;
import com.facebook.react.runtime.internal.bolts.TaskCompletionSource;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ReactHostImpl$$ExternalSyntheticLambda21 implements BundleLoadCallback {
    public final /* synthetic */ ReactHostImpl f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ DevSupportManagerBase f$2;
    public final /* synthetic */ TaskCompletionSource f$3;

    public /* synthetic */ ReactHostImpl$$ExternalSyntheticLambda21(ReactHostImpl reactHostImpl, String str, DevSupportManagerBase devSupportManagerBase, TaskCompletionSource taskCompletionSource) {
        this.f$0 = reactHostImpl;
        this.f$1 = str;
        this.f$2 = devSupportManagerBase;
        this.f$3 = taskCompletionSource;
    }

    public final void onSuccess() {
        this.f$0.lambda$loadJSBundleFromMetro$37(this.f$1, this.f$2, this.f$3);
    }
}
