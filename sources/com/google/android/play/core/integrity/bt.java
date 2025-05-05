package com.google.android.play.core.integrity;

import com.google.android.gms.tasks.Task;
import com.google.android.play.core.integrity.StandardIntegrityManager;

/* compiled from: com.google.android.play:integrity@@1.3.0 */
final class bt {

    /* renamed from: a  reason: collision with root package name */
    private final bn f57a;

    bt(bn bnVar) {
        this.f57a = bnVar;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Task a(long j, long j2, int i, StandardIntegrityManager.StandardIntegrityTokenRequest standardIntegrityTokenRequest) {
        return this.f57a.d(standardIntegrityTokenRequest.a(), j, j2, 0);
    }
}
