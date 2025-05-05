package com.google.android.gms.internal.pal;

import java.io.Serializable;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzagc extends zzagf implements Serializable, zzagd {
    public static final zzagc zza = new zzagc(0);

    public zzagc(long j) {
        super(j);
    }

    public static zzagc zza(long j) {
        return j == 0 ? zza : new zzagc(j);
    }

    public static zzagc zzb(long j) {
        return new zzagc(zzagg.zza(j, 3600000));
    }

    public static zzagc zzc(long j) {
        return new zzagc(zzagg.zza(j, 1000));
    }
}
