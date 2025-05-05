package com.google.photos.vision.barhopper;

import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzed;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfp;

/* compiled from: com.google.mlkit:barcode-scanning@@17.2.0 */
public final class zzae extends zzed implements zzfp {
    /* access modifiers changed from: private */
    public static final zzae zza;
    private int zzd;
    private int zze;
    private int zzf;
    private byte zzg = 2;

    static {
        zzae zzae = new zzae();
        zza = zzae;
        zzed.zzU(zzae.class, zzae);
    }

    private zzae() {
    }

    public static zzad zzc() {
        return (zzad) zza.zzF();
    }

    static /* synthetic */ void zze(zzae zzae, int i) {
        zzae.zzd |= 1;
        zzae.zze = i;
    }

    static /* synthetic */ void zzf(zzae zzae, int i) {
        zzae.zzd |= 2;
        zzae.zzf = i;
    }

    public final int zza() {
        return this.zze;
    }

    public final int zzb() {
        return this.zzf;
    }

    /* access modifiers changed from: protected */
    public final Object zzg(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return Byte.valueOf(this.zzg);
        }
        if (i2 == 2) {
            return zzR(zza, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0002\u0001ᔄ\u0000\u0002ᔄ\u0001", new Object[]{"zzd", "zze", "zzf"});
        } else if (i2 == 3) {
            return new zzae();
        } else {
            if (i2 == 4) {
                return new zzad((zza) null);
            }
            if (i2 == 5) {
                return zza;
            }
            this.zzg = obj == null ? (byte) 0 : 1;
            return null;
        }
    }
}
