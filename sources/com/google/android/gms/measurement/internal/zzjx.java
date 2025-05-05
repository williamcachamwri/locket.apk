package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
public final /* synthetic */ class zzjx implements Runnable {
    private /* synthetic */ zzjq zza;
    private /* synthetic */ String zzb;

    public /* synthetic */ zzjx(zzjq zzjq, String str) {
        this.zza = zzjq;
        this.zzb = str;
    }

    public final void run() {
        this.zza.zzb(this.zzb);
    }
}
