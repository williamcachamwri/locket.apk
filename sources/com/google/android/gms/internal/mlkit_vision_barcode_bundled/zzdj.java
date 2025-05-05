package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/* compiled from: com.google.mlkit:barcode-scanning@@17.2.0 */
public abstract class zzdj extends zzcr {
    public static final /* synthetic */ int zzb = 0;
    private static final Logger zzc = Logger.getLogger(zzdj.class.getName());
    /* access modifiers changed from: private */
    public static final boolean zzd = zzhi.zzx();
    zzdk zza;

    private zzdj() {
    }

    /* synthetic */ zzdj(zzdi zzdi) {
    }

    public static zzdj zzA(byte[] bArr, int i, int i2) {
        return new zzdg(bArr, 0, i2);
    }

    @Deprecated
    static int zzt(int i, zzfo zzfo, zzgh zzgh) {
        int zzB = ((zzck) zzfo).zzB(zzgh);
        int zzy = zzy(i << 3);
        return zzy + zzy + zzB;
    }

    public static int zzu(int i) {
        if (i >= 0) {
            return zzy(i);
        }
        return 10;
    }

    public static int zzv(zzfo zzfo) {
        int zzE = zzfo.zzE();
        return zzy(zzE) + zzE;
    }

    static int zzw(zzfo zzfo, zzgh zzgh) {
        int zzB = ((zzck) zzfo).zzB(zzgh);
        return zzy(zzB) + zzB;
    }

    public static int zzx(String str) {
        int i;
        try {
            i = zzhn.zze(str);
        } catch (zzhm unused) {
            i = str.getBytes(zzem.zzb).length;
        }
        return zzy(i) + i;
    }

    public static int zzy(int i) {
        if ((i & -128) == 0) {
            return 1;
        }
        if ((i & -16384) == 0) {
            return 2;
        }
        if ((-2097152 & i) == 0) {
            return 3;
        }
        return (i & -268435456) == 0 ? 4 : 5;
    }

    public static int zzz(long j) {
        int i;
        if ((-128 & j) == 0) {
            return 1;
        }
        if (j < 0) {
            return 10;
        }
        if ((-34359738368L & j) != 0) {
            j >>>= 28;
            i = 6;
        } else {
            i = 2;
        }
        if ((-2097152 & j) != 0) {
            j >>>= 14;
            i += 2;
        }
        return (j & -16384) != 0 ? i + 1 : i;
    }

    public final void zzB() {
        if (zza() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzC(String str, zzhm zzhm) throws IOException {
        zzc.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", zzhm);
        byte[] bytes = str.getBytes(zzem.zzb);
        try {
            int length = bytes.length;
            zzq(length);
            zzl(bytes, 0, length);
        } catch (IndexOutOfBoundsException e) {
            throw new zzdh(e);
        }
    }

    public abstract int zza();

    public abstract void zzb(byte b) throws IOException;

    public abstract void zzd(int i, boolean z) throws IOException;

    public abstract void zze(int i, zzdb zzdb) throws IOException;

    public abstract void zzf(int i, int i2) throws IOException;

    public abstract void zzg(int i) throws IOException;

    public abstract void zzh(int i, long j) throws IOException;

    public abstract void zzi(long j) throws IOException;

    public abstract void zzj(int i, int i2) throws IOException;

    public abstract void zzk(int i) throws IOException;

    public abstract void zzl(byte[] bArr, int i, int i2) throws IOException;

    public abstract void zzm(int i, String str) throws IOException;

    public abstract void zzo(int i, int i2) throws IOException;

    public abstract void zzp(int i, int i2) throws IOException;

    public abstract void zzq(int i) throws IOException;

    public abstract void zzr(int i, long j) throws IOException;

    public abstract void zzs(long j) throws IOException;
}
