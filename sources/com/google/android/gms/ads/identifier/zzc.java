package com.google.android.gms.ads.identifier;

import com.google.android.gms.tasks.OnFailureListener;

/* compiled from: com.google.android.gms:play-services-ads-identifier@@18.2.0 */
public final /* synthetic */ class zzc implements OnFailureListener {
    public final /* synthetic */ zzd zza;
    public final /* synthetic */ long zzb;

    public /* synthetic */ zzc(zzd zzd, long j) {
        this.zza = zzd;
        this.zzb = j;
    }

    public final void onFailure(Exception exc) {
        zzd.zzb(this.zza, this.zzb, exc);
    }
}
