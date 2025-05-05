package com.google.android.gms.internal.atv_ads_framework;

import java.util.Iterator;

/* compiled from: com.google.android.tv:tv-ads@@1.0.0 */
public final class zzaa extends zzdh implements zzep {
    private static final zzdn zzb = new zzy();
    /* access modifiers changed from: private */
    public static final zzaa zzd;
    private zzdm zze = zzr();

    static {
        zzaa zzaa = new zzaa();
        zzd = zzaa;
        zzdh.zzx(zzaa.class, zzaa);
    }

    private zzaa() {
    }

    public static zzz zza() {
        return (zzz) zzd.zzo();
    }

    static /* synthetic */ void zzc(zzaa zzaa, zzx zzx) {
        zzx.getClass();
        zzaa.zzf();
        zzaa.zze.zzh(zzx.zza());
    }

    static /* synthetic */ void zzd(zzaa zzaa, Iterable iterable) {
        zzaa.zzf();
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            zzaa.zze.zzh(((zzx) it.next()).zza());
        }
    }

    private final void zzf() {
        zzdm zzdm = this.zze;
        if (!zzdm.zzc()) {
            int size = zzdm.size();
            this.zze = zzdm.zzf(size == 0 ? 10 : size + size);
        }
    }

    /* access modifiers changed from: protected */
    public final Object zze(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzu(zzd, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001e", new Object[]{"zze", zzw.zza});
        } else if (i2 == 3) {
            return new zzaa();
        } else {
            if (i2 == 4) {
                return new zzz((zzg) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzd;
        }
    }
}
