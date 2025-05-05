package com.google.photos.vision.barhopper;

import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzed;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfp;

/* compiled from: com.google.mlkit:barcode-scanning@@17.2.0 */
public final class zzaj extends zzed implements zzfp {
    /* access modifiers changed from: private */
    public static final zzaj zza;
    private int zzd;
    private String zze = "";
    private String zzf = "";
    private byte zzg = 2;

    static {
        zzaj zzaj = new zzaj();
        zza = zzaj;
        zzed.zzU(zzaj.class, zzaj);
    }

    private zzaj() {
    }

    public static zzaj zzb() {
        return zza;
    }

    public final String zzc() {
        return this.zze;
    }

    public final String zzd() {
        return this.zzf;
    }

    /* access modifiers changed from: protected */
    public final Object zzg(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return Byte.valueOf(this.zzg);
        }
        if (i2 == 2) {
            return zzR(zza, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0001\u0001ဈ\u0000\u0002ᔈ\u0001", new Object[]{"zzd", "zze", "zzf"});
        } else if (i2 == 3) {
            return new zzaj();
        } else {
            if (i2 == 4) {
                return new zzai((zza) null);
            }
            if (i2 == 5) {
                return zza;
            }
            this.zzg = obj == null ? (byte) 0 : 1;
            return null;
        }
    }
}
