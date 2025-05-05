package com.google.android.play.core.integrity;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.play.integrity.internal.s;

/* compiled from: com.google.android.play:integrity@@1.3.0 */
final class bk extends bi {
    final /* synthetic */ bn c;
    private final s d = new s("OnRequestIntegrityTokenCallback");
    /* access modifiers changed from: private */
    public final long e;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    bk(bn bnVar, TaskCompletionSource taskCompletionSource, long j) {
        super(bnVar, taskCompletionSource);
        this.c = bnVar;
        this.e = j;
    }

    public final void c(Bundle bundle) throws RemoteException {
        super.c(bundle);
        this.d.d("onRequestExpressIntegrityToken", new Object[0]);
        ApiException a2 = this.c.f.a(bundle);
        if (a2 != null) {
            this.f50a.trySetException(a2);
            return;
        }
        bj bjVar = new bj(this, this.c.c, bundle.getLong("request.token.sid"));
        TaskCompletionSource taskCompletionSource = this.f50a;
        b bVar = new b();
        bVar.b(bundle.getString("token"));
        bVar.a(bjVar);
        taskCompletionSource.trySetResult(bVar.c());
    }
}
