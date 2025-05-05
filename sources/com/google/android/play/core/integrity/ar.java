package com.google.android.play.core.integrity;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

/* compiled from: com.google.android.play:integrity@@1.3.0 */
final class ar extends ResultReceiver {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ as f35a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ar(as asVar, Handler handler) {
        super(handler);
        this.f35a = asVar;
    }

    public final void onReceiveResult(int i, Bundle bundle) {
        if (i == 1) {
            this.f35a.f36a.trySetResult(3);
        } else if (i == 2) {
            this.f35a.f36a.trySetResult(2);
        } else if (i == 3) {
            this.f35a.f36a.trySetResult(1);
        }
    }
}
