package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public abstract class zzadf extends zzaco {
    private static final Logger zzb = Logger.getLogger(zzadf.class.getName());
    /* access modifiers changed from: private */
    public static final boolean zzc = zzago.zzx();
    zzadg zza;

    private zzadf() {
        throw null;
    }

    /* synthetic */ zzadf(zzade zzade) {
    }

    public static int zzA(long j) {
        return (640 - (Long.numberOfLeadingZeros(j) * 9)) >>> 6;
    }

    @Deprecated
    static int zzw(int i, zzafb zzafb, zzaft zzaft) {
        int zzz = zzz(i << 3);
        return zzz + zzz + ((zzach) zzafb).zzat(zzaft);
    }

    static int zzx(zzafb zzafb, zzaft zzaft) {
        int zzat = ((zzach) zzafb).zzat(zzaft);
        return zzz(zzat) + zzat;
    }

    public static int zzy(String str) {
        int i;
        try {
            i = zzagr.zzc(str);
        } catch (zzagq unused) {
            i = str.getBytes(zzaee.zza).length;
        }
        return zzz(i) + i;
    }

    public static int zzz(int i) {
        return (352 - (Integer.numberOfLeadingZeros(i) * 9)) >>> 6;
    }

    public final void zzB() {
        if (zza() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzC(String str, zzagq zzagq) throws IOException {
        zzb.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", zzagq);
        byte[] bytes = str.getBytes(zzaee.zza);
        try {
            int length = bytes.length;
            zzt(length);
            zzl(bytes, 0, length);
        } catch (IndexOutOfBoundsException e) {
            throw new zzadd(e);
        }
    }

    public abstract int zza();

    public abstract void zzb(byte b) throws IOException;

    public abstract void zzd(int i, boolean z) throws IOException;

    public abstract void zze(int i, zzacw zzacw) throws IOException;

    public abstract void zzf(int i, int i2) throws IOException;

    public abstract void zzg(int i) throws IOException;

    public abstract void zzh(int i, long j) throws IOException;

    public abstract void zzi(long j) throws IOException;

    public abstract void zzj(int i, int i2) throws IOException;

    public abstract void zzk(int i) throws IOException;

    public abstract void zzl(byte[] bArr, int i, int i2) throws IOException;

    /* access modifiers changed from: package-private */
    public abstract void zzm(int i, zzafb zzafb, zzaft zzaft) throws IOException;

    public abstract void zzn(int i, zzafb zzafb) throws IOException;

    public abstract void zzo(int i, zzacw zzacw) throws IOException;

    public abstract void zzp(int i, String str) throws IOException;

    public abstract void zzr(int i, int i2) throws IOException;

    public abstract void zzs(int i, int i2) throws IOException;

    public abstract void zzt(int i) throws IOException;

    public abstract void zzu(int i, long j) throws IOException;

    public abstract void zzv(long j) throws IOException;
}
