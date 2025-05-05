package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

/* compiled from: com.google.mlkit:barcode-scanning@@17.2.0 */
public final class zzhx extends zzed implements zzfp {
    /* access modifiers changed from: private */
    public static final zzhx zza;
    private int zzd;
    private int zze = -1;

    static {
        zzhx zzhx = new zzhx();
        zza = zzhx;
        zzed.zzU(zzhx.class, zzhx);
    }

    private zzhx() {
    }

    /* access modifiers changed from: protected */
    public final Object zzg(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzR(zza, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001င\u0000", new Object[]{"zzd", "zze"});
        } else if (i2 == 3) {
            return new zzhx();
        } else {
            if (i2 == 4) {
                return new zzhw((zzhr) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zza;
        }
    }
}
