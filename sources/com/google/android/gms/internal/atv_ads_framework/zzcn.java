package com.google.android.gms.internal.atv_ads_framework;

import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Locale;

/* compiled from: com.google.android.tv:tv-ads@@1.0.0 */
public abstract class zzcn implements Iterable, Serializable {
    private static final Comparator zza = new zzcf();
    public static final zzcn zzb = new zzck(zzdp.zzd);
    private static final zzcm zzd = new zzcm((zzcl) null);
    private int zzc = 0;

    static {
        int i = zzcc.zza;
    }

    zzcn() {
    }

    static int zzj(int i, int i2, int i3) {
        if (((i3 - i2) | i2) >= 0) {
            return i2;
        }
        throw new IndexOutOfBoundsException("End index: " + i2 + " >= " + i3);
    }

    public abstract boolean equals(Object obj);

    public final int hashCode() {
        int i = this.zzc;
        if (i == 0) {
            int zzd2 = zzd();
            i = zze(zzd2, 0, zzd2);
            if (i == 0) {
                i = 1;
            }
            this.zzc = i;
        }
        return i;
    }

    public final /* synthetic */ Iterator iterator() {
        return new zzce(this);
    }

    public final String toString() {
        Locale locale = Locale.ROOT;
        Object[] objArr = new Object[3];
        objArr[0] = Integer.toHexString(System.identityHashCode(this));
        objArr[1] = Integer.valueOf(zzd());
        objArr[2] = zzd() <= 50 ? zzfn.zza(this) : zzfn.zza(zzf(0, 47)).concat("...");
        return String.format(locale, "<ByteString@%s size=%d contents=\"%s\">", objArr);
    }

    public abstract byte zza(int i);

    /* access modifiers changed from: package-private */
    public abstract byte zzb(int i);

    public abstract int zzd();

    /* access modifiers changed from: protected */
    public abstract int zze(int i, int i2, int i3);

    public abstract zzcn zzf(int i, int i2);

    /* access modifiers changed from: protected */
    public abstract String zzg(Charset charset);

    /* access modifiers changed from: package-private */
    public abstract void zzh(zzcd zzcd) throws IOException;

    public abstract boolean zzi();

    /* access modifiers changed from: protected */
    public final int zzk() {
        return this.zzc;
    }

    public final String zzl(Charset charset) {
        return zzd() == 0 ? "" : zzg(charset);
    }
}
