package com.google.android.gms.internal.p002firebaseauthapi;

import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Locale;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaip  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public abstract class zzaip implements Serializable, Iterable<Byte> {
    public static final zzaip zza = new zzaiw(zzakb.zzb);
    private static final zzais zzb = new zzaiz();
    private int zzc = 0;

    static /* synthetic */ int zza(byte b) {
        return b & 255;
    }

    public abstract boolean equals(Object obj);

    public abstract byte zza(int i);

    public abstract zzaip zza(int i, int i2);

    /* access modifiers changed from: package-private */
    public abstract void zza(zzaim zzaim) throws IOException;

    /* access modifiers changed from: protected */
    public abstract void zza(byte[] bArr, int i, int i2, int i3);

    /* access modifiers changed from: package-private */
    public abstract byte zzb(int i);

    public abstract int zzb();

    /* access modifiers changed from: protected */
    public abstract int zzb(int i, int i2, int i3);

    public abstract zzajb zzc();

    static int zza(int i, int i2, int i3) {
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

    public final int hashCode() {
        int i = this.zzc;
        if (i == 0) {
            int zzb2 = zzb();
            i = zzb(zzb2, 0, zzb2);
            if (i == 0) {
                i = 1;
            }
            this.zzc = i;
        }
        return i;
    }

    /* access modifiers changed from: protected */
    public final int zza() {
        return this.zzc;
    }

    static zzaiu zzc(int i) {
        return new zzaiu(i);
    }

    public static zzaip zza(byte[] bArr) {
        return zza(bArr, 0, bArr.length);
    }

    public static zzaip zza(byte[] bArr, int i, int i2) {
        zza(i, i + i2, bArr.length);
        return new zzaiw(zzb.zza(bArr, i, i2));
    }

    public static zzaip zza(String str) {
        return new zzaiw(str.getBytes(zzakb.zza));
    }

    static zzaip zzb(byte[] bArr) {
        return new zzaiw(bArr);
    }

    public final String toString() {
        String str;
        Locale locale = Locale.ROOT;
        Object[] objArr = new Object[3];
        objArr[0] = Integer.toHexString(System.identityHashCode(this));
        objArr[1] = Integer.valueOf(zzb());
        if (zzb() <= 50) {
            str = zzamh.zza(this);
        } else {
            str = zzamh.zza(zza(0, 47)) + "...";
        }
        objArr[2] = str;
        return String.format(locale, "<ByteString@%s size=%d contents=\"%s\">", objArr);
    }

    public /* synthetic */ Iterator iterator() {
        return new zzaio(this);
    }

    static {
        new zzair();
    }

    zzaip() {
    }

    public final byte[] zzd() {
        int zzb2 = zzb();
        if (zzb2 == 0) {
            return zzakb.zzb;
        }
        byte[] bArr = new byte[zzb2];
        zza(bArr, 0, 0, zzb2);
        return bArr;
    }
}
