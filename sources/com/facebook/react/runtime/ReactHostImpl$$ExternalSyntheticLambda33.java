package com.facebook.react.runtime;

import com.facebook.react.runtime.ReactHostImpl;
import com.facebook.react.runtime.internal.bolts.Task;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ReactHostImpl$$ExternalSyntheticLambda33 implements ReactHostImpl.ReactInstanceTaskUnwrapper {
    public final /* synthetic */ ReactHostImpl f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ String f$3;

    public /* synthetic */ ReactHostImpl$$ExternalSyntheticLambda33(ReactHostImpl reactHostImpl, String str, String str2, String str3) {
        this.f$0 = reactHostImpl;
        this.f$1 = str;
        this.f$2 = str2;
        this.f$3 = str3;
    }

    public final ReactInstance unwrap(Task task, String str) {
        return this.f$0.lambda$createReactInstanceUnwraper$38(this.f$1, this.f$2, this.f$3, task, str);
    }
}
