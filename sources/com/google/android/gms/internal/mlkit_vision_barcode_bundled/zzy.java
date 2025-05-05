package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

/* compiled from: com.google.mlkit:barcode-scanning@@17.2.0 */
public final class zzy extends zzed implements zzfp {
    /* access modifiers changed from: private */
    public static final zzy zza;
    private int zzd;
    private zzab zze;

    static {
        zzy zzy = new zzy();
        zza = zzy;
        zzed.zzU(zzy.class, zzy);
    }

    private zzy() {
    }

    /* access modifiers changed from: protected */
    public final Object zzg(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzR(zza, "\u0001\u0001\u0000\u0001\u000f\u000f\u0001\u0000\u0000\u0000\u000fဉ\u0000", new Object[]{"zzd", "zze"});
        } else if (i2 == 3) {
            return new zzy();
        } else {
            if (i2 == 4) {
                return new zzx((zzw) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zza;
        }
    }
}
