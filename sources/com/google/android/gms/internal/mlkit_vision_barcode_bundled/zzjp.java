package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

/* compiled from: com.google.mlkit:barcode-scanning@@17.2.0 */
public final class zzjp extends zzed implements zzfp {
    /* access modifiers changed from: private */
    public static final zzjp zza;
    private int zzd;
    private String zze = "";
    private String zzf = "";

    static {
        zzjp zzjp = new zzjp();
        zza = zzjp;
        zzed.zzU(zzjp.class, zzjp);
    }

    private zzjp() {
    }

    /* access modifiers changed from: protected */
    public final Object zzg(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzR(zza, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001", new Object[]{"zzd", "zze", "zzf"});
        } else if (i2 == 3) {
            return new zzjp();
        } else {
            if (i2 == 4) {
                return new zzjo((zzhr) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zza;
        }
    }
}
