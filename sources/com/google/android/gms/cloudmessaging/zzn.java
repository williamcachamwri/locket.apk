package com.google.android.gms.cloudmessaging;

/* compiled from: com.google.android.gms:play-services-cloud-messaging@@17.2.0 */
public final /* synthetic */ class zzn implements Runnable {
    public final /* synthetic */ zzp zza;
    public final /* synthetic */ zzs zzb;

    public /* synthetic */ zzn(zzp zzp, zzs zzs) {
        this.zza = zzp;
        this.zzb = zzs;
    }

    public final void run() {
        this.zza.zze(this.zzb.zza);
    }
}
