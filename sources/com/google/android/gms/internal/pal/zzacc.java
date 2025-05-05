package com.google.android.gms.internal.pal;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public abstract class zzacc {
    public static final /* synthetic */ int zzd = 0;
    private static volatile int zze = 100;
    int zza;
    final int zzb = zze;
    zzacd zzc;

    /* synthetic */ zzacc(zzacb zzacb) {
    }

    public static int zzs(int i) {
        return (-(i & 1)) ^ (i >>> 1);
    }

    public static long zzt(long j) {
        return (-(j & 1)) ^ (j >>> 1);
    }

    static zzacc zzu(byte[] bArr, int i, int i2, boolean z) {
        zzaca zzaca = new zzaca(bArr, 0, i2, z, (zzabz) null);
        try {
            zzaca.zzc(i2);
            return zzaca;
        } catch (zzadi e) {
            throw new IllegalArgumentException(e);
        }
    }

    public abstract int zzb();

    public abstract int zzc(int i) throws zzadi;

    public abstract int zzf() throws IOException;

    public abstract zzaby zzj() throws IOException;

    public abstract String zzk() throws IOException;

    public abstract String zzl() throws IOException;

    public abstract void zzm(int i) throws zzadi;

    public abstract void zzn(int i);

    public abstract boolean zzp() throws IOException;

    public abstract boolean zzq() throws IOException;

    public abstract boolean zzr(int i) throws IOException;
}
