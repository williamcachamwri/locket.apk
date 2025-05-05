package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public abstract class zzacw implements Iterable, Serializable {
    public static final zzacw zzb = new zzacv(zzaee.zzb);
    private int zza = 0;

    static {
        int i = zzack.zza;
    }

    zzacw() {
    }

    public static zzacw zzp(byte[] bArr, int i, int i2) {
        zzm(i, i + i2, bArr.length);
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        return new zzacv(bArr2);
    }

    public static zzacw zzq(InputStream inputStream) throws IOException {
        zzacw zzacw;
        ArrayList arrayList = new ArrayList();
        int i = 256;
        while (true) {
            byte[] bArr = new byte[i];
            int i2 = 0;
            while (i2 < i) {
                int read = inputStream.read(bArr, i2, i - i2);
                if (read == -1) {
                    break;
                }
                i2 += read;
            }
            if (i2 == 0) {
                zzacw = null;
            } else {
                zzacw = zzp(bArr, 0, i2);
            }
            if (zzacw == null) {
                break;
            }
            arrayList.add(zzacw);
            i = Math.min(i + i, 8192);
        }
        int size = arrayList.size();
        if (size == 0) {
            return zzb;
        }
        return zzc(arrayList.iterator(), size);
    }

    static void zzr(int i, int i2) {
        if (((i2 - (i + 1)) | i) >= 0) {
            return;
        }
        if (i < 0) {
            throw new ArrayIndexOutOfBoundsException("Index < 0: " + i);
        }
        throw new ArrayIndexOutOfBoundsException("Index > length: " + i + ", " + i2);
    }

    public abstract boolean equals(Object obj);

    public final int hashCode() {
        int i = this.zza;
        if (i == 0) {
            int zzd = zzd();
            i = zzi(zzd, 0, zzd);
            if (i == 0) {
                i = 1;
            }
            this.zza = i;
        }
        return i;
    }

    public final String toString() {
        Locale locale = Locale.ROOT;
        Object[] objArr = new Object[3];
        objArr[0] = Integer.toHexString(System.identityHashCode(this));
        objArr[1] = Integer.valueOf(zzd());
        objArr[2] = zzd() <= 50 ? zzagf.zza(this) : zzagf.zza(zzj(0, 47)).concat("...");
        return String.format(locale, "<ByteString@%s size=%d contents=\"%s\">", objArr);
    }

    public abstract byte zza(int i);

    /* access modifiers changed from: package-private */
    public abstract byte zzb(int i);

    public abstract int zzd();

    /* access modifiers changed from: protected */
    public abstract void zze(byte[] bArr, int i, int i2, int i3);

    /* access modifiers changed from: protected */
    public abstract int zzf();

    /* access modifiers changed from: protected */
    public abstract boolean zzh();

    /* access modifiers changed from: protected */
    public abstract int zzi(int i, int i2, int i3);

    public abstract zzacw zzj(int i, int i2);

    public abstract zzada zzk();

    /* access modifiers changed from: package-private */
    public abstract void zzl(zzaco zzaco) throws IOException;

    /* access modifiers changed from: protected */
    public final int zzn() {
        return this.zza;
    }

    /* renamed from: zzo */
    public zzacs iterator() {
        return new zzacp(this);
    }

    @Deprecated
    public final void zzs(byte[] bArr, int i, int i2, int i3) {
        zzm(0, i3, zzd());
        zzm(i2, i2 + i3, bArr.length);
        if (i3 > 0) {
            zze(bArr, 0, i2, i3);
        }
    }

    public final byte[] zzt() {
        int zzd = zzd();
        if (zzd == 0) {
            return zzaee.zzb;
        }
        byte[] bArr = new byte[zzd];
        zze(bArr, 0, 0, zzd);
        return bArr;
    }

    private static zzacw zzc(Iterator it, int i) {
        if (i <= 0) {
            throw new IllegalArgumentException(String.format("length (%s) must be >= 1", new Object[]{Integer.valueOf(i)}));
        } else if (i == 1) {
            return (zzacw) it.next();
        } else {
            int i2 = i >>> 1;
            zzacw zzc = zzc(it, i2);
            zzacw zzc2 = zzc(it, i - i2);
            if (Integer.MAX_VALUE - zzc.zzd() >= zzc2.zzd()) {
                return zzafs.zzv(zzc, zzc2);
            }
            int zzd = zzc.zzd();
            int zzd2 = zzc2.zzd();
            throw new IllegalArgumentException("ByteString would be too long: " + zzd + "+" + zzd2);
        }
    }

    static int zzm(int i, int i2, int i3) {
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
