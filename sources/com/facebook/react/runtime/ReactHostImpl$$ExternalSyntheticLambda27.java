package com.facebook.react.runtime;

import com.facebook.react.bridge.NativeArray;
import com.facebook.react.runtime.ReactHostImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ReactHostImpl$$ExternalSyntheticLambda27 implements ReactHostImpl.VeniceThenable {
    public final /* synthetic */ String f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ NativeArray f$2;

    public /* synthetic */ ReactHostImpl$$ExternalSyntheticLambda27(String str, String str2, NativeArray nativeArray) {
        this.f$0 = str;
        this.f$1 = str2;
        this.f$2 = nativeArray;
    }

    public final void then(Object obj) {
        ((ReactInstance) obj).callFunctionOnModule(this.f$0, this.f$1, this.f$2);
    }
}
