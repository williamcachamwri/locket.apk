package com.google.android.play.core.integrity;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.play.integrity.internal.j;

/* compiled from: com.google.android.play:integrity@@1.3.0 */
class bi extends j {

    /* renamed from: a  reason: collision with root package name */
    final TaskCompletionSource f50a;
    final /* synthetic */ bn b;

    bi(bn bnVar, TaskCompletionSource taskCompletionSource) {
        this.b = bnVar;
        this.f50a = taskCompletionSource;
    }

    public final void b(Bundle bundle) throws RemoteException {
        this.b.f52a.v(this.f50a);
    }

    public void c(Bundle bundle) throws RemoteException {
        this.b.f52a.v(this.f50a);
    }

    public final void d(Bundle bundle) throws RemoteException {
        this.b.f52a.v(this.f50a);
    }

    public void e(Bundle bundle) throws RemoteException {
        this.b.f52a.v(this.f50a);
    }
}
