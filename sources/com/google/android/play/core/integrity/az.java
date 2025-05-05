package com.google.android.play.core.integrity;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.android.play.core.integrity.StandardIntegrityManager;

/* compiled from: com.google.android.play:integrity@@1.3.0 */
final class az implements StandardIntegrityManager {

    /* renamed from: a  reason: collision with root package name */
    private final bn f41a;
    private final bt b;

    az(bn bnVar, bt btVar) {
        this.f41a = bnVar;
        this.b = btVar;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Task a(StandardIntegrityManager.PrepareIntegrityTokenRequest prepareIntegrityTokenRequest, Long l) throws Exception {
        long b2 = prepareIntegrityTokenRequest.b();
        long longValue = l.longValue();
        prepareIntegrityTokenRequest.a();
        return Tasks.forResult(new bs(this.b, b2, longValue, 0));
    }

    public final Task<StandardIntegrityManager.StandardIntegrityTokenProvider> prepareIntegrityToken(StandardIntegrityManager.PrepareIntegrityTokenRequest prepareIntegrityTokenRequest) {
        long b2 = prepareIntegrityTokenRequest.b();
        prepareIntegrityTokenRequest.a();
        return this.f41a.e(b2, 0).onSuccessTask(new ay(this, prepareIntegrityTokenRequest));
    }
}
