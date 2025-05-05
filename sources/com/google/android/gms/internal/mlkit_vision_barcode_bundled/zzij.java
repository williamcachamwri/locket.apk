package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

/* compiled from: com.google.mlkit:barcode-scanning@@17.2.0 */
public final class zzij extends zzed implements zzfp {
    /* access modifiers changed from: private */
    public static final zzij zza;
    private int zzd;
    private int zze;
    private int zzf;
    private zzel zzg = zzed.zzO();
    private int zzh;

    static {
        zzij zzij = new zzij();
        zza = zzij;
        zzed.zzU(zzij.class, zzij);
    }

    private zzij() {
    }

    /* access modifiers changed from: protected */
    public final Object zzg(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzR(zza, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0001\u0000\u0001᠌\u0000\u0002င\u0001\u0003\u001a\u0004င\u0002", new Object[]{"zzd", "zze", zzii.zza, "zzf", "zzg", "zzh"});
        } else if (i2 == 3) {
            return new zzij();
        } else {
            if (i2 == 4) {
                return new zzih((zzhr) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zza;
        }
    }
}
