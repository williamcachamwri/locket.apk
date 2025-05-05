package com.google.android.play.core.integrity;

import com.google.android.gms.tasks.Task;
import com.google.android.play.core.integrity.StandardIntegrityManager;

/* compiled from: com.google.android.play:integrity@@1.3.0 */
public final /* synthetic */ class bs implements StandardIntegrityManager.StandardIntegrityTokenProvider {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ bt f56a;
    public final /* synthetic */ long b;
    public final /* synthetic */ long c;

    public /* synthetic */ bs(bt btVar, long j, long j2, int i) {
        this.f56a = btVar;
        this.b = j;
        this.c = j2;
    }

    public final Task request(StandardIntegrityManager.StandardIntegrityTokenRequest standardIntegrityTokenRequest) {
        return this.f56a.a(this.b, this.c, 0, standardIntegrityTokenRequest);
    }
}
