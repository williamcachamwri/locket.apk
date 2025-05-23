package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zzja {
    private final Object zza;
    private final Object zzb;
    private final Object zzc;

    zzja(Object obj, Object obj2, Object obj3) {
        this.zza = obj;
        this.zzb = obj2;
        this.zzc = obj3;
    }

    /* access modifiers changed from: package-private */
    public final IllegalArgumentException zza() {
        return new IllegalArgumentException("Multiple entries with same key: " + this.zza + "=" + this.zzb + " and " + this.zza + "=" + this.zzc);
    }
}
