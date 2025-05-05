package com.facebook.react.devsupport;

import com.facebook.react.bridge.ReadableArray;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DevSupportManagerBase$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ DevSupportManagerBase f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ ReadableArray f$3;

    public /* synthetic */ DevSupportManagerBase$$ExternalSyntheticLambda0(DevSupportManagerBase devSupportManagerBase, int i, String str, ReadableArray readableArray) {
        this.f$0 = devSupportManagerBase;
        this.f$1 = i;
        this.f$2 = str;
        this.f$3 = readableArray;
    }

    public final void run() {
        this.f$0.lambda$updateJSError$1(this.f$1, this.f$2, this.f$3);
    }
}
