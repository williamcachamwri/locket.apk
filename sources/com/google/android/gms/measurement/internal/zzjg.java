package com.google.android.gms.measurement.internal;

import com.google.android.gms.measurement.internal.zzje;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
public enum zzjg {
    STORAGE(zzje.zza.AD_STORAGE, zzje.zza.ANALYTICS_STORAGE),
    DMA(zzje.zza.AD_USER_DATA);
    
    /* access modifiers changed from: private */
    public final zzje.zza[] zzd;

    private zzjg(zzje.zza... zzaArr) {
        this.zzd = zzaArr;
    }

    public final zzje.zza[] zza() {
        return this.zzd;
    }
}
