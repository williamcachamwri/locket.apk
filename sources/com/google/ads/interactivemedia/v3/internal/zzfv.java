package com.google.ads.interactivemedia.v3.internal;

import com.google.android.gms.tasks.OnFailureListener;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final /* synthetic */ class zzfv implements OnFailureListener {
    public final /* synthetic */ zzgd zza;
    public final /* synthetic */ zzft zzb;

    public /* synthetic */ zzfv(zzgd zzgd, zzft zzft) {
        this.zza = zzgd;
        this.zzb = zzft;
    }

    public final void onFailure(Exception exc) {
        this.zza.zzh(this.zzb, exc);
    }
}
