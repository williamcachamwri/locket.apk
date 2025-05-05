package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

/* compiled from: com.google.mlkit:barcode-scanning@@17.2.0 */
public final class zzht extends zzed implements zzfp {
    /* access modifiers changed from: private */
    public static final zzht zza;
    private int zzd;
    private int zze;
    private zzjx zzf;
    private zzjj zzg;
    private zzjl zzh;

    static {
        zzht zzht = new zzht();
        zza = zzht;
        zzed.zzU(zzht.class, zzht);
    }

    private zzht() {
    }

    /* access modifiers changed from: protected */
    public final Object zzg(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzR(zza, "\u0001\u0004\u0000\u0001\u0001\u0005\u0004\u0000\u0000\u0000\u0001᠌\u0000\u0002ဉ\u0001\u0003ဉ\u0002\u0005ဉ\u0003", new Object[]{"zzd", "zze", zzir.zza, "zzf", "zzg", "zzh"});
        } else if (i2 == 3) {
            return new zzht();
        } else {
            if (i2 == 4) {
                return new zzhs((zzhr) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zza;
        }
    }
}
