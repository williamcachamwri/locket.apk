package com.google.android.gms.measurement.internal;

import com.google.android.gms.tasks.OnFailureListener;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
public final /* synthetic */ class zzgl implements OnFailureListener {
    private /* synthetic */ zzgm zza;
    private /* synthetic */ long zzb;

    public /* synthetic */ zzgl(zzgm zzgm, long j) {
        this.zza = zzgm;
        this.zzb = j;
    }

    public final void onFailure(Exception exc) {
        this.zza.zza(this.zzb, exc);
    }
}
