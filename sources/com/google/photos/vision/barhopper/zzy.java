package com.google.photos.vision.barhopper;

import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzed;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfp;

/* compiled from: com.google.mlkit:barcode-scanning@@17.2.0 */
public final class zzy extends zzed implements zzfp {
    /* access modifiers changed from: private */
    public static final zzy zza;
    private int zzd;
    private int zze;
    private String zzf = "";
    private String zzg = "";
    private String zzh = "";

    static {
        zzy zzy = new zzy();
        zza = zzy;
        zzed.zzU(zzy.class, zzy);
    }

    private zzy() {
    }

    public static zzy zzb() {
        return zza;
    }

    public final String zzc() {
        return this.zzf;
    }

    public final String zzd() {
        return this.zzh;
    }

    public final String zze() {
        return this.zzg;
    }

    public final int zzf() {
        int zza2 = zzx.zza(this.zze);
        if (zza2 == 0) {
            return 1;
        }
        return zza2;
    }

    /* access modifiers changed from: protected */
    public final Object zzg(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzR(zza, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001᠌\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004ဈ\u0003", new Object[]{"zzd", "zze", zzw.zza, "zzf", "zzg", "zzh"});
        } else if (i2 == 3) {
            return new zzy();
        } else {
            if (i2 == 4) {
                return new zzu((zza) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zza;
        }
    }
}
