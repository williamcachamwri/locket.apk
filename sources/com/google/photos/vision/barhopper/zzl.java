package com.google.photos.vision.barhopper;

import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzed;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzel;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfp;

/* compiled from: com.google.mlkit:barcode-scanning@@17.2.0 */
public final class zzl extends zzed implements zzfp {
    /* access modifiers changed from: private */
    public static final zzl zza;
    private int zzd;
    private String zze = "";
    private zzel zzf = zzO();

    static {
        zzl zzl = new zzl();
        zza = zzl;
        zzed.zzU(zzl.class, zzl);
    }

    private zzl() {
    }

    /* access modifiers changed from: protected */
    public final Object zzg(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzR(zza, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001ဈ\u0000\u0002\u001b", new Object[]{"zzd", "zze", "zzf", zzaa.class});
        } else if (i2 == 3) {
            return new zzl();
        } else {
            if (i2 == 4) {
                return new zzk((zza) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zza;
        }
    }
}
