package com.google.android.recaptcha.internal;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public abstract class zzlp extends zzky {
    private static final Logger zzb = Logger.getLogger(zzlp.class.getName());
    /* access modifiers changed from: private */
    public static final boolean zzc = zzpu.zzx();
    zzlq zza;

    private zzlp() {
        throw null;
    }

    /* synthetic */ zzlp(zzlo zzlo) {
    }

    public static int zzA(int i) {
        return (352 - (Integer.numberOfLeadingZeros(i) * 9)) >>> 6;
    }

    public static int zzB(long j) {
        return (640 - (Long.numberOfLeadingZeros(j) * 9)) >>> 6;
    }

    @Deprecated
    static int zzw(int i, zzok zzok, zzoy zzoy) {
        int zzA = zzA(i << 3);
        return zzA + zzA + ((zzkq) zzok).zza(zzoy);
    }

    public static int zzx(zzok zzok) {
        int zzo = zzok.zzo();
        return zzA(zzo) + zzo;
    }

    static int zzy(zzok zzok, zzoy zzoy) {
        int zza2 = ((zzkq) zzok).zza(zzoy);
        return zzA(zza2) + zza2;
    }

    public static int zzz(String str) {
        int i;
        try {
            i = zzpx.zzc(str);
        } catch (zzpw unused) {
            i = str.getBytes(zznn.zza).length;
        }
        return zzA(i) + i;
    }

    public final void zzC() {
        if (zza() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzD(String str, zzpw zzpw) throws IOException {
        zzb.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", zzpw);
        byte[] bytes = str.getBytes(zznn.zza);
        try {
            int length = bytes.length;
            zzt(length);
            zzl(bytes, 0, length);
        } catch (IndexOutOfBoundsException e) {
            throw new zzln(e);
        }
    }

    public abstract int zza();

    public abstract void zzb(byte b) throws IOException;

    public abstract void zzd(int i, boolean z) throws IOException;

    public abstract void zze(int i, zzlg zzlg) throws IOException;

    public abstract void zzf(int i, int i2) throws IOException;

    public abstract void zzg(int i) throws IOException;

    public abstract void zzh(int i, long j) throws IOException;

    public abstract void zzi(long j) throws IOException;

    public abstract void zzj(int i, int i2) throws IOException;

    public abstract void zzk(int i) throws IOException;

    public abstract void zzl(byte[] bArr, int i, int i2) throws IOException;

    /* access modifiers changed from: package-private */
    public abstract void zzm(int i, zzok zzok, zzoy zzoy) throws IOException;

    public abstract void zzn(int i, zzok zzok) throws IOException;

    public abstract void zzo(int i, zzlg zzlg) throws IOException;

    public abstract void zzp(int i, String str) throws IOException;

    public abstract void zzr(int i, int i2) throws IOException;

    public abstract void zzs(int i, int i2) throws IOException;

    public abstract void zzt(int i) throws IOException;

    public abstract void zzu(int i, long j) throws IOException;

    public abstract void zzv(long j) throws IOException;
}
