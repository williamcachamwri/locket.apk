package com.google.android.gms.internal.pal;

import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Locale;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public abstract class zzaby implements Iterable, Serializable {
    private static final Comparator zza = new zzabq();
    public static final zzaby zzb = new zzabv(zzadg.zzd);
    private static final zzabx zzd = new zzabx((zzabw) null);
    private int zzc = 0;

    static {
        int i = zzabk.zza;
    }

    zzaby() {
    }

    public static zzaby zzn(byte[] bArr) {
        return zzo(bArr, 0, bArr.length);
    }

    public static zzaby zzo(byte[] bArr, int i, int i2) {
        zzl(i, i + i2, bArr.length);
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        return new zzabv(bArr2);
    }

    public static zzaby zzp(String str) {
        return new zzabv(str.getBytes(zzadg.zzb));
    }

    static zzaby zzq(byte[] bArr) {
        return new zzabv(bArr);
    }

    public abstract boolean equals(Object obj);

    public final int hashCode() {
        int i = this.zzc;
        if (i == 0) {
            int zzd2 = zzd();
            i = zzf(zzd2, 0, zzd2);
            if (i == 0) {
                i = 1;
            }
            this.zzc = i;
        }
        return i;
    }

    public final /* synthetic */ Iterator iterator() {
        return new zzabp(this);
    }

    public final String toString() {
        Locale locale = Locale.ROOT;
        Object[] objArr = new Object[3];
        objArr[0] = Integer.toHexString(System.identityHashCode(this));
        objArr[1] = Integer.valueOf(zzd());
        objArr[2] = zzd() <= 50 ? zzafg.zza(this) : zzafg.zza(zzg(0, 47)).concat("...");
        return String.format(locale, "<ByteString@%s size=%d contents=\"%s\">", objArr);
    }

    public abstract byte zza(int i);

    /* access modifiers changed from: package-private */
    public abstract byte zzb(int i);

    public abstract int zzd();

    /* access modifiers changed from: protected */
    public abstract void zze(byte[] bArr, int i, int i2, int i3);

    /* access modifiers changed from: protected */
    public abstract int zzf(int i, int i2, int i3);

    public abstract zzaby zzg(int i, int i2);

    public abstract zzacc zzh();

    /* access modifiers changed from: protected */
    public abstract String zzi(Charset charset);

    /* access modifiers changed from: package-private */
    public abstract void zzj(zzabo zzabo) throws IOException;

    public abstract boolean zzk();

    /* access modifiers changed from: protected */
    public final int zzm() {
        return this.zzc;
    }

    public final String zzr(Charset charset) {
        return zzd() == 0 ? "" : zzi(charset);
    }

    public final boolean zzs() {
        return zzd() == 0;
    }

    public final byte[] zzt() {
        int zzd2 = zzd();
        if (zzd2 == 0) {
            return zzadg.zzd;
        }
        byte[] bArr = new byte[zzd2];
        zze(bArr, 0, 0, zzd2);
        return bArr;
    }

    static int zzl(int i, int i2, int i3) {
        int i4 = i2 - i;
        if ((i | i2 | i4 | (i3 - i2)) >= 0) {
            return i4;
        }
        if (i < 0) {
            throw new IndexOutOfBoundsException("Beginning index: " + i + " < 0");
        } else if (i2 < i) {
            throw new IndexOutOfBoundsException("Beginning index larger than ending index: " + i + ", " + i2);
        } else {
            throw new IndexOutOfBoundsException("End index: " + i2 + " >= " + i3);
        }
    }
}
