package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

/* compiled from: com.google.mlkit:barcode-scanning@@17.2.0 */
public final class zzit extends zzed implements zzfp {
    /* access modifiers changed from: private */
    public static final zzit zza;
    private int zzd;
    private boolean zze;
    private boolean zzf;

    static {
        zzit zzit = new zzit();
        zza = zzit;
        zzed.zzU(zzit.class, zzit);
    }

    private zzit() {
    }

    /* access modifiers changed from: protected */
    public final Object zzg(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzR(zza, "\u0001\u0002\u0000\u0001\u0005\u0006\u0002\u0000\u0000\u0000\u0005ဇ\u0000\u0006ဇ\u0001", new Object[]{"zzd", "zze", "zzf"});
        } else if (i2 == 3) {
            return new zzit();
        } else {
            if (i2 == 4) {
                return new zzis((zzhr) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zza;
        }
    }
}
