package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

/* compiled from: com.google.mlkit:barcode-scanning@@17.2.0 */
public final class zzjf extends zzed implements zzfp {
    /* access modifiers changed from: private */
    public static final zzjf zza;
    private int zzd;
    private int zze;
    private int zzf = 100;
    private int zzg;

    static {
        zzjf zzjf = new zzjf();
        zza = zzjf;
        zzed.zzU(zzjf.class, zzjf);
    }

    private zzjf() {
    }

    /* access modifiers changed from: protected */
    public final Object zzg(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzR(zza, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001᠌\u0000\u0002င\u0001\u0003င\u0002", new Object[]{"zzd", "zze", zzjd.zza, "zzf", "zzg"});
        } else if (i2 == 3) {
            return new zzjf();
        } else {
            if (i2 == 4) {
                return new zzje((zzhr) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zza;
        }
    }
}
