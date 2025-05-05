package com.google.android.play.core.integrity;

import android.app.Activity;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.play.integrity.internal.n;
import com.google.android.play.integrity.internal.t;

/* compiled from: com.google.android.play:integrity@@1.3.0 */
final class ag extends t {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Bundle f26a;
    final /* synthetic */ Activity b;
    final /* synthetic */ TaskCompletionSource c;
    final /* synthetic */ int d;
    final /* synthetic */ aj e;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ag(aj ajVar, TaskCompletionSource taskCompletionSource, Bundle bundle, Activity activity, TaskCompletionSource taskCompletionSource2, int i) {
        super(taskCompletionSource);
        this.e = ajVar;
        this.f26a = bundle;
        this.b = activity;
        this.c = taskCompletionSource2;
        this.d = i;
    }

    /* access modifiers changed from: protected */
    public final void b() {
        try {
            Bundle bundle = this.f26a;
            aj ajVar = this.e;
            ((n) this.e.f29a.e()).c(bundle, ajVar.d.a(this.b, this.c, ajVar.f29a));
        } catch (RemoteException e2) {
            this.e.b.c(e2, "requestAndShowDialog(%s)", Integer.valueOf(this.d));
            this.c.trySetException(new IntegrityServiceException(-100, e2));
        }
    }
}
