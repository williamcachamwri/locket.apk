package com.google.android.play.core.integrity;

import android.os.Bundle;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.play.integrity.internal.o;
import com.google.android.play.integrity.internal.s;

/* compiled from: com.google.android.play:integrity@@1.3.0 */
final class ai extends o {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ aj f28a;
    private final s b = new s("OnRequestIntegrityTokenCallback");
    private final TaskCompletionSource c;

    ai(aj ajVar, TaskCompletionSource taskCompletionSource) {
        this.f28a = ajVar;
        this.c = taskCompletionSource;
    }

    public final void b(Bundle bundle) {
        this.f28a.f29a.v(this.c);
        this.b.d("onRequestIntegrityToken", new Object[0]);
        ApiException a2 = this.f28a.e.a(bundle);
        if (a2 != null) {
            this.c.trySetException(a2);
            return;
        }
        String string = bundle.getString("token");
        if (string == null) {
            this.c.trySetException(new IntegrityServiceException(-100, (Throwable) null));
            return;
        }
        ah ahVar = new ah(this, this.f28a.c, bundle.getLong("request.token.sid"));
        TaskCompletionSource taskCompletionSource = this.c;
        a aVar = new a();
        aVar.b(string);
        aVar.a(ahVar);
        taskCompletionSource.trySetResult(aVar.c());
    }
}
