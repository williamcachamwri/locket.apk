package com.google.barhopper.deeplearning;

import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzed;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfp;

/* compiled from: com.google.mlkit:barcode-scanning@@17.2.0 */
public final class BarhopperV3Options extends zzed<BarhopperV3Options, zzk> implements zzfp {
    /* access modifiers changed from: private */
    public static final BarhopperV3Options zza;
    private int zzd;
    private zzi zze;
    private zzn zzf;

    static {
        BarhopperV3Options barhopperV3Options = new BarhopperV3Options();
        zza = barhopperV3Options;
        zzed.zzU(BarhopperV3Options.class, barhopperV3Options);
    }

    private BarhopperV3Options() {
    }

    public static zzk zza() {
        return (zzk) zza.zzF();
    }

    static /* synthetic */ void zzc(BarhopperV3Options barhopperV3Options, zzi zzi) {
        zzi.getClass();
        barhopperV3Options.zze = zzi;
        barhopperV3Options.zzd |= 1;
    }

    static /* synthetic */ void zzd(BarhopperV3Options barhopperV3Options, zzn zzn) {
        zzn.getClass();
        barhopperV3Options.zzf = zzn;
        barhopperV3Options.zzd |= 2;
    }

    /* access modifiers changed from: protected */
    public final Object zzg(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzR(zza, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001", new Object[]{"zzd", "zze", "zzf"});
        } else if (i2 == 3) {
            return new BarhopperV3Options();
        } else {
            if (i2 == 4) {
                return new zzk((zzj) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zza;
        }
    }
}
