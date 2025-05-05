package com.google.photos.vision.barhopper;

import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzed;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfp;

/* compiled from: com.google.mlkit:barcode-scanning@@17.2.0 */
public final class zzac extends zzed implements zzfp {
    /* access modifiers changed from: private */
    public static final zzac zza;
    private int zzd;
    private double zze;
    private double zzf;
    private byte zzg = 2;

    static {
        zzac zzac = new zzac();
        zza = zzac;
        zzed.zzU(zzac.class, zzac);
    }

    private zzac() {
    }

    public static zzac zzd() {
        return zza;
    }

    public final double zza() {
        return this.zze;
    }

    public final double zzb() {
        return this.zzf;
    }

    /* access modifiers changed from: protected */
    public final Object zzg(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return Byte.valueOf(this.zzg);
        }
        if (i2 == 2) {
            return zzR(zza, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0002\u0001ᔀ\u0000\u0002ᔀ\u0001", new Object[]{"zzd", "zze", "zzf"});
        } else if (i2 == 3) {
            return new zzac();
        } else {
            if (i2 == 4) {
                return new zzab((zza) null);
            }
            if (i2 == 5) {
                return zza;
            }
            this.zzg = obj == null ? (byte) 0 : 1;
            return null;
        }
    }
}
