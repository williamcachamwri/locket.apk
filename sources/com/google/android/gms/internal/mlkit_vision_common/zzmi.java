package com.google.android.gms.internal.mlkit_vision_common;

/* compiled from: com.google.mlkit:vision-common@@17.3.0 */
public final /* synthetic */ class zzmi implements Runnable {
    public final /* synthetic */ zzmj zza;
    public final /* synthetic */ zzmb zzb;
    public final /* synthetic */ zziv zzc;
    public final /* synthetic */ String zzd;

    public /* synthetic */ zzmi(zzmj zzmj, zzmb zzmb, zziv zziv, String str) {
        this.zza = zzmj;
        this.zzb = zzmb;
        this.zzc = zziv;
        this.zzd = str;
    }

    public final void run() {
        this.zza.zzb(this.zzb, this.zzc, this.zzd);
    }
}
