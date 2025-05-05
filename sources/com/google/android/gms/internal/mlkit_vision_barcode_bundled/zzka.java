package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

/* compiled from: com.google.mlkit:barcode-scanning@@17.2.0 */
public final class zzka extends zzed implements zzfp {
    /* access modifiers changed from: private */
    public static final zzka zza;
    private int zzd;
    private int zze;
    private int zzf;

    static {
        zzka zzka = new zzka();
        zza = zzka;
        zzed.zzU(zzka.class, zzka);
    }

    private zzka() {
    }

    /* access modifiers changed from: protected */
    public final Object zzg(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzR(zza, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001င\u0000\u0002᠌\u0001", new Object[]{"zzd", "zze", "zzf", zzjy.zza});
        } else if (i2 == 3) {
            return new zzka();
        } else {
            if (i2 == 4) {
                return new zzjz((zzhr) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zza;
        }
    }
}
