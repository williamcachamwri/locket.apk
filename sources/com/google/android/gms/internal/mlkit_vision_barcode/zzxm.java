package com.google.android.gms.internal.mlkit_vision_barcode;

import androidx.media3.common.C;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.1 */
public abstract class zzxm {
    public static final zzxm zza;

    static {
        zzm().zzm();
        zzxl zzm = zzm();
        zzm.zzi(false);
        zza = zzm.zzm();
    }

    public static zzxl zzm() {
        zzxd zzxd = new zzxd();
        zzxd.zzg(10);
        zzxd.zze(5);
        zzxd.zzf(0.25f);
        zzxd.zzd(0.8f);
        zzxd.zzi(true);
        zzxd.zzc(0.5f);
        zzxd.zzb(0.8f);
        zzxd.zzk(1500);
        zzxd.zzh(C.DEFAULT_MAX_SEEK_TO_PREVIOUS_POSITION_MS);
        zzxd.zza(true);
        zzxd.zzj(0.1f);
        zzxd.zzl(0.05f);
        return zzxd;
    }

    /* access modifiers changed from: package-private */
    public abstract float zza();

    /* access modifiers changed from: package-private */
    public abstract float zzb();

    /* access modifiers changed from: package-private */
    public abstract float zzc();

    /* access modifiers changed from: package-private */
    public abstract float zzd();

    /* access modifiers changed from: package-private */
    public abstract float zze();

    /* access modifiers changed from: package-private */
    public abstract float zzf();

    /* access modifiers changed from: package-private */
    public abstract int zzg();

    /* access modifiers changed from: package-private */
    public abstract int zzh();

    /* access modifiers changed from: package-private */
    public abstract long zzi();

    /* access modifiers changed from: package-private */
    public abstract long zzj();

    /* access modifiers changed from: package-private */
    public abstract boolean zzk();

    /* access modifiers changed from: package-private */
    public abstract boolean zzl();
}
