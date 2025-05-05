package com.google.firebase.auth.internal;

/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzo extends zzk {
    private String zza;
    private String zzb;
    private String zzc;

    public final zzk zza(String str) {
        this.zzb = str;
        return this;
    }

    public final zzk zzb(String str) {
        this.zzc = str;
        return this;
    }

    public final zzk zzc(String str) {
        this.zza = str;
        return this;
    }

    public final zzh zza() {
        return new zzl(this.zza, this.zzb, this.zzc);
    }

    zzo() {
    }
}
