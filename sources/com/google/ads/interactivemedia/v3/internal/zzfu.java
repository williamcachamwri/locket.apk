package com.google.ads.interactivemedia.v3.internal;

import com.google.android.gms.tasks.OnFailureListener;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final /* synthetic */ class zzfu implements OnFailureListener {
    public final /* synthetic */ zzgd zza;
    public final /* synthetic */ zzft zzb;

    public /* synthetic */ zzfu(zzgd zzgd, zzft zzft) {
        this.zza = zzgd;
        this.zzb = zzft;
    }

    public final void onFailure(Exception exc) {
        this.zza.zzg(this.zzb, exc);
    }
}
