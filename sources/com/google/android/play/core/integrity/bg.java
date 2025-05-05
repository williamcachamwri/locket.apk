package com.google.android.play.core.integrity;

import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.play.integrity.internal.i;

/* compiled from: com.google.android.play:integrity@@1.3.0 */
final class bg extends bm {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ String f48a;
    final /* synthetic */ long b;
    final /* synthetic */ long c;
    final /* synthetic */ TaskCompletionSource d;
    final /* synthetic */ bn e;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    bg(bn bnVar, TaskCompletionSource taskCompletionSource, int i, String str, long j, long j2, TaskCompletionSource taskCompletionSource2) {
        super(bnVar, taskCompletionSource);
        this.e = bnVar;
        this.f48a = str;
        this.b = j;
        this.c = j2;
        this.d = taskCompletionSource2;
    }

    /* access modifiers changed from: protected */
    public final void b() {
        if (!bn.k(this.e)) {
            try {
                bn bnVar = this.e;
                ((i) bnVar.f52a.e()).d(bn.a(bnVar, this.f48a, this.b, this.c, 0), new bk(this.e, this.d, this.b));
            } catch (RemoteException e2) {
                this.e.b.c(e2, "requestExpressIntegrityToken(%s, %s)", this.f48a, Long.valueOf(this.b));
                this.d.trySetException(new StandardIntegrityException(-100, e2));
            }
        } else {
            super.a(new StandardIntegrityException(-2, (Throwable) null));
        }
    }
}
