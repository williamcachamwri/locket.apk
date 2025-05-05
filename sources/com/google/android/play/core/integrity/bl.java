package com.google.android.play.core.integrity;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.play.integrity.internal.s;

/* compiled from: com.google.android.play:integrity@@1.3.0 */
final class bl extends bi {
    final /* synthetic */ bn c;
    private final s d = new s("OnWarmUpIntegrityTokenCallback");

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    bl(bn bnVar, TaskCompletionSource taskCompletionSource) {
        super(bnVar, taskCompletionSource);
        this.c = bnVar;
    }

    public final void e(Bundle bundle) throws RemoteException {
        super.e(bundle);
        this.d.d("onWarmUpExpressIntegrityToken", new Object[0]);
        ApiException a2 = this.c.f.a(bundle);
        if (a2 != null) {
            this.f50a.trySetException(a2);
        } else {
            this.f50a.trySetResult(Long.valueOf(bundle.getLong("warm.up.sid")));
        }
    }
}
