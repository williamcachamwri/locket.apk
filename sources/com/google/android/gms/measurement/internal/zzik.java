package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
final class zzik implements Runnable {
    private final /* synthetic */ String zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ String zzc;
    private final /* synthetic */ long zzd;
    private final /* synthetic */ zzic zze;

    zzik(zzic zzic, String str, String str2, String str3, long j) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = str3;
        this.zzd = j;
        this.zze = zzic;
    }

    public final void run() {
        if (this.zza == null) {
            this.zze.zza.zza(this.zzb, (zzlk) null);
            return;
        }
        this.zze.zza.zza(this.zzb, new zzlk(this.zzc, this.zza, this.zzd));
    }
}
