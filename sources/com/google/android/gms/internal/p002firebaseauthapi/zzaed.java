package com.google.android.gms.internal.p002firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaed  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzaed implements Runnable {
    private final /* synthetic */ zzaeg zza;
    private final /* synthetic */ zzaea zzb;

    zzaed(zzaea zzaea, zzaeg zzaeg) {
        this.zza = zzaeg;
        this.zzb = zzaea;
    }

    public final void run() {
        synchronized (this.zzb.zza.zzh) {
            if (!this.zzb.zza.zzh.isEmpty()) {
                this.zza.zza(this.zzb.zza.zzh.get(0), new Object[0]);
            }
        }
    }
}
