package com.google.android.gms.internal.atv_ads_framework;

import java.util.Arrays;

/* compiled from: com.google.android.tv:tv-ads@@1.0.0 */
public final class zzbg {
    Object[] zza = new Object[8];
    int zzb = 0;
    zzbf zzc;

    private final void zzd(int i) {
        Object[] objArr = this.zza;
        int length = objArr.length;
        int i2 = i + i;
        if (i2 > length) {
            int i3 = length + (length >> 1) + 1;
            if (i3 < i2) {
                int highestOneBit = Integer.highestOneBit(i2 - 1);
                i3 = highestOneBit + highestOneBit;
            }
            if (i3 < 0) {
                i3 = Integer.MAX_VALUE;
            }
            this.zza = Arrays.copyOf(objArr, i3);
        }
    }

    public final zzbg zza(Object obj, Object obj2) {
        zzd(this.zzb + 1);
        zzaz.zza(obj, obj2);
        Object[] objArr = this.zza;
        int i = this.zzb;
        int i2 = i + i;
        objArr[i2] = obj;
        objArr[i2 + 1] = obj2;
        this.zzb = i + 1;
        return this;
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [java.util.Collection, java.lang.Iterable] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.atv_ads_framework.zzbg zzb(java.lang.Iterable r3) {
        /*
            r2 = this;
            int r0 = r2.zzb
            int r1 = r3.size()
            int r0 = r0 + r1
            r2.zzd(r0)
            java.util.Iterator r3 = r3.iterator()
        L_0x000e:
            boolean r0 = r3.hasNext()
            if (r0 == 0) goto L_0x0026
            java.lang.Object r0 = r3.next()
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0
            java.lang.Object r1 = r0.getKey()
            java.lang.Object r0 = r0.getValue()
            r2.zza(r1, r0)
            goto L_0x000e
        L_0x0026:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.atv_ads_framework.zzbg.zzb(java.lang.Iterable):com.google.android.gms.internal.atv_ads_framework.zzbg");
    }

    public final zzbh zzc() {
        zzbf zzbf = this.zzc;
        if (zzbf == null) {
            zzbq zzf = zzbq.zzf(this.zzb, this.zza, this);
            zzbf zzbf2 = this.zzc;
            if (zzbf2 == null) {
                return zzf;
            }
            throw zzbf2.zza();
        }
        throw zzbf.zza();
    }
}
