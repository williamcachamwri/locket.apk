package com.google.android.gms.common.moduleinstall.internal;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public final /* synthetic */ class zao implements RemoteCall {
    public final /* synthetic */ zay zaa;
    public final /* synthetic */ ApiFeatureRequest zab;

    public /* synthetic */ zao(zay zay, ApiFeatureRequest apiFeatureRequest) {
        this.zaa = zay;
        this.zab = apiFeatureRequest;
    }

    public final void accept(Object obj, Object obj2) {
        ((zaf) ((zaz) obj).getService()).zag(new zat(this.zaa, (TaskCompletionSource) obj2), this.zab, (zah) null);
    }
}
