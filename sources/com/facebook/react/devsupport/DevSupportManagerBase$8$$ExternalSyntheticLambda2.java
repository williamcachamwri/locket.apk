package com.facebook.react.devsupport;

import com.facebook.react.devsupport.DevSupportManagerBase;
import com.facebook.react.packagerconnection.Responder;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DevSupportManagerBase$8$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ DevSupportManagerBase.AnonymousClass8 f$0;
    public final /* synthetic */ Responder f$1;

    public /* synthetic */ DevSupportManagerBase$8$$ExternalSyntheticLambda2(DevSupportManagerBase.AnonymousClass8 r1, Responder responder) {
        this.f$0 = r1;
        this.f$1 = responder;
    }

    public final void run() {
        this.f$0.lambda$onCaptureHeapCommand$2(this.f$1);
    }
}
