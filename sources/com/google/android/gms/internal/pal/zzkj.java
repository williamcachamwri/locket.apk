package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzkj {
    public static final zzkj zza = new zzkj("ENABLED");
    public static final zzkj zzb = new zzkj("DISABLED");
    public static final zzkj zzc = new zzkj("DESTROYED");
    private final String zzd;

    private zzkj(String str) {
        this.zzd = str;
    }

    public final String toString() {
        return this.zzd;
    }
}
