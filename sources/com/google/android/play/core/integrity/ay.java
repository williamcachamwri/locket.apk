package com.google.android.play.core.integrity;

import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.android.play.core.integrity.StandardIntegrityManager;

/* compiled from: com.google.android.play:integrity@@1.3.0 */
public final /* synthetic */ class ay implements SuccessContinuation {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ az f40a;
    public final /* synthetic */ StandardIntegrityManager.PrepareIntegrityTokenRequest b;

    public /* synthetic */ ay(az azVar, StandardIntegrityManager.PrepareIntegrityTokenRequest prepareIntegrityTokenRequest) {
        this.f40a = azVar;
        this.b = prepareIntegrityTokenRequest;
    }

    public final Task then(Object obj) {
        return this.f40a.a(this.b, (Long) obj);
    }
}
