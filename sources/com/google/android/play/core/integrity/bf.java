package com.google.android.play.core.integrity;

import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.play.integrity.internal.i;

/* compiled from: com.google.android.play:integrity@@1.3.0 */
final class bf extends bm {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ long f47a;
    final /* synthetic */ TaskCompletionSource b;
    final /* synthetic */ bn c;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    bf(bn bnVar, TaskCompletionSource taskCompletionSource, int i, long j, TaskCompletionSource taskCompletionSource2) {
        super(bnVar, taskCompletionSource);
        this.c = bnVar;
        this.f47a = j;
        this.b = taskCompletionSource2;
    }

    /* access modifiers changed from: protected */
    public final void b() {
        if (!bn.k(this.c)) {
            try {
                bn bnVar = this.c;
                ((i) bnVar.f52a.e()).e(bn.b(bnVar, this.f47a, 0), new bl(this.c, this.b));
            } catch (RemoteException e) {
                this.c.b.c(e, "warmUpIntegrityToken(%s)", Long.valueOf(this.f47a));
                this.b.trySetException(new StandardIntegrityException(-100, e));
            }
        } else {
            super.a(new StandardIntegrityException(-2, (Throwable) null));
        }
    }
}
