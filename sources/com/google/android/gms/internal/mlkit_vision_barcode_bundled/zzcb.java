package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.util.List;

/* compiled from: com.google.mlkit:barcode-scanning@@17.2.0 */
public final class zzcb extends zzed implements zzfp {
    /* access modifiers changed from: private */
    public static final zzcb zza;
    private int zzd;
    private int zze;
    private zzel zzf = zzed.zzO();
    private zzf zzg;
    private byte zzh = 2;

    static {
        zzcb zzcb = new zzcb();
        zza = zzcb;
        zzed.zzU(zzcb.class, zzcb);
    }

    private zzcb() {
    }

    public final List zzb() {
        return this.zzf;
    }

    public final int zzc() {
        int zza2 = zzca.zza(this.zze);
        if (zza2 == 0) {
            return 1;
        }
        return zza2;
    }

    /* access modifiers changed from: protected */
    public final Object zzg(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return Byte.valueOf(this.zzh);
        }
        if (i2 == 2) {
            return zzR(zza, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0001\u0001\u0001᠌\u0000\u0002\u001a\u0003ᐉ\u0001", new Object[]{"zzd", "zze", zzbz.zza, "zzf", "zzg"});
        } else if (i2 == 3) {
            return new zzcb();
        } else {
            if (i2 == 4) {
                return new zzbx((zzbw) null);
            }
            if (i2 == 5) {
                return zza;
            }
            this.zzh = obj == null ? (byte) 0 : 1;
            return null;
        }
    }
}
