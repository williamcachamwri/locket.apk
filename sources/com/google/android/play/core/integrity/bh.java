package com.google.android.play.core.integrity;

import android.app.Activity;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.play.integrity.internal.ae;
import com.google.android.play.integrity.internal.i;

/* compiled from: com.google.android.play:integrity@@1.3.0 */
final class bh extends bm {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Bundle f49a;
    final /* synthetic */ Activity b;
    final /* synthetic */ TaskCompletionSource c;
    final /* synthetic */ int d;
    final /* synthetic */ bn e;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    bh(bn bnVar, TaskCompletionSource taskCompletionSource, Bundle bundle, Activity activity, TaskCompletionSource taskCompletionSource2, int i) {
        super(bnVar, taskCompletionSource);
        this.e = bnVar;
        this.f49a = bundle;
        this.b = activity;
        this.c = taskCompletionSource2;
        this.d = i;
    }

    /* access modifiers changed from: protected */
    public final void b() {
        if (bn.k(this.e)) {
            super.a(new StandardIntegrityException(-2, (Throwable) null));
            return;
        }
        try {
            bn bnVar = this.e;
            ae aeVar = bnVar.f52a;
            ((i) aeVar.e()).c(this.f49a, bnVar.e.a(this.b, this.c, aeVar));
        } catch (RemoteException e2) {
            this.e.b.c(e2, "requestAndShowDialog(%s)", Integer.valueOf(this.d));
            this.c.trySetException(new StandardIntegrityException(-100, e2));
        }
    }
}
