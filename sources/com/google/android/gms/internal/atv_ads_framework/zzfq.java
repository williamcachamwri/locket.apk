package com.google.android.gms.internal.atv_ads_framework;

import java.util.Arrays;

/* compiled from: com.google.android.tv:tv-ads@@1.0.0 */
public final class zzfq {
    private static final zzfq zza = new zzfq(0, new int[0], new Object[0], false);
    private int zzb;
    private int[] zzc;
    private Object[] zzd;
    private int zze;
    private boolean zzf;

    private zzfq() {
        this(0, new int[8], new Object[8], true);
    }

    private zzfq(int i, int[] iArr, Object[] objArr, boolean z) {
        this.zze = -1;
        this.zzb = 0;
        this.zzc = iArr;
        this.zzd = objArr;
        this.zzf = z;
    }

    public static zzfq zzc() {
        return zza;
    }

    static zzfq zze(zzfq zzfq, zzfq zzfq2) {
        int i = zzfq.zzb;
        int i2 = zzfq2.zzb;
        int[] copyOf = Arrays.copyOf(zzfq.zzc, 0);
        int[] iArr = zzfq2.zzc;
        int i3 = zzfq.zzb;
        int i4 = zzfq2.zzb;
        System.arraycopy(iArr, 0, copyOf, 0, 0);
        Object[] copyOf2 = Arrays.copyOf(zzfq.zzd, 0);
        Object[] objArr = zzfq2.zzd;
        int i5 = zzfq.zzb;
        int i6 = zzfq2.zzb;
        System.arraycopy(objArr, 0, copyOf2, 0, 0);
        return new zzfq(0, copyOf, copyOf2, true);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof zzfq)) {
            return false;
        }
        zzfq zzfq = (zzfq) obj;
        int[] iArr = zzfq.zzc;
        Object[] objArr = zzfq.zzd;
        return true;
    }

    public final int hashCode() {
        return 506991;
    }

    public final int zza() {
        int i = this.zze;
        if (i != -1) {
            return i;
        }
        this.zze = 0;
        return 0;
    }

    public final int zzb() {
        int i = this.zze;
        if (i != -1) {
            return i;
        }
        this.zze = 0;
        return 0;
    }

    /* access modifiers changed from: package-private */
    public final zzfq zzd(zzfq zzfq) {
        if (zzfq.equals(zza)) {
            return this;
        }
        if (this.zzf) {
            int i = zzfq.zzb;
            int[] iArr = this.zzc;
            int length = iArr.length;
            System.arraycopy(zzfq.zzc, 0, iArr, 0, 0);
            Object[] objArr = zzfq.zzd;
            Object[] objArr2 = this.zzd;
            int i2 = zzfq.zzb;
            System.arraycopy(objArr, 0, objArr2, 0, 0);
            this.zzb = 0;
            return this;
        }
        throw new UnsupportedOperationException();
    }

    public final void zzf() {
        if (this.zzf) {
            this.zzf = false;
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzg(StringBuilder sb, int i) {
    }
}
