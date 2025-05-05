package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public abstract class zzada {
    public static final /* synthetic */ int zzd = 0;
    private static volatile int zze = 100;
    int zza;
    final int zzb = zze;
    zzadb zzc;

    private zzada() {
    }

    /* synthetic */ zzada(zzacz zzacz) {
    }

    public static int zzC(int i) {
        return (i >>> 1) ^ (-(i & 1));
    }

    public static long zzD(long j) {
        return (j >>> 1) ^ (-(1 & j));
    }

    static zzada zzE(byte[] bArr, int i, int i2, boolean z) {
        zzacy zzacy = new zzacy(bArr, i, i2, z, (zzacx) null);
        try {
            zzacy.zzd(i2);
            return zzacy;
        } catch (zzaeg e) {
            throw new IllegalArgumentException(e);
        }
    }

    public abstract boolean zzA() throws IOException;

    public abstract boolean zzB() throws IOException;

    public abstract double zza() throws IOException;

    public abstract float zzb() throws IOException;

    public abstract int zzc();

    public abstract int zzd(int i) throws zzaeg;

    public abstract int zze() throws IOException;

    public abstract int zzf() throws IOException;

    public abstract int zzg() throws IOException;

    public abstract int zzj() throws IOException;

    public abstract int zzk() throws IOException;

    public abstract int zzl() throws IOException;

    public abstract int zzm() throws IOException;

    public abstract long zzn() throws IOException;

    public abstract long zzo() throws IOException;

    public abstract long zzs() throws IOException;

    public abstract long zzt() throws IOException;

    public abstract long zzu() throws IOException;

    public abstract zzacw zzv() throws IOException;

    public abstract String zzw() throws IOException;

    public abstract String zzx() throws IOException;

    public abstract void zzy(int i) throws zzaeg;

    public abstract void zzz(int i);
}
