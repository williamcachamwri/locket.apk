package com.google.photos.vision.barhopper;

import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzed;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfp;

/* compiled from: com.google.mlkit:barcode-scanning@@17.2.0 */
public final class zzao extends zzed implements zzfp {
    /* access modifiers changed from: private */
    public static final zzao zza;
    private int zzd;
    private String zze = "";
    private int zzf;
    private String zzg = "";
    private boolean zzh;
    private byte zzi = 2;

    static {
        zzao zzao = new zzao();
        zza = zzao;
        zzed.zzU(zzao.class, zzao);
    }

    private zzao() {
    }

    public static zzao zzb() {
        return zza;
    }

    public final String zzc() {
        return this.zzg;
    }

    public final String zzd() {
        return this.zze;
    }

    public final int zze() {
        int zza2 = zzan.zza(this.zzf);
        if (zza2 == 0) {
            return 1;
        }
        return zza2;
    }

    /* access modifiers changed from: protected */
    public final Object zzg(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return Byte.valueOf(this.zzi);
        }
        if (i2 == 2) {
            return zzR(zza, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0001\u0001ᔈ\u0000\u0002᠌\u0001\u0003ဈ\u0002\u0004ဇ\u0003", new Object[]{"zzd", "zze", "zzf", zzam.zza, "zzg", "zzh"});
        } else if (i2 == 3) {
            return new zzao();
        } else {
            if (i2 == 4) {
                return new zzak((zza) null);
            }
            if (i2 == 5) {
                return zza;
            }
            this.zzi = obj == null ? (byte) 0 : 1;
            return null;
        }
    }
}
