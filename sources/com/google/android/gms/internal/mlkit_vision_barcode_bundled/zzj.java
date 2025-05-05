package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

/* compiled from: com.google.mlkit:barcode-scanning@@17.2.0 */
public final class zzj extends zzed implements zzfp {
    /* access modifiers changed from: private */
    public static final zzj zza;
    private int zzd;
    private int zze = 17;
    private zzel zzf = zzO();
    private int zzg;
    private zzv zzh;
    private zzp zzi;
    private zzkd zzj;
    private int zzk;
    private zzel zzl = zzO();
    private byte zzm = 2;

    static {
        zzj zzj2 = new zzj();
        zza = zzj2;
        zzed.zzU(zzj.class, zzj2);
    }

    private zzj() {
    }

    /* access modifiers changed from: protected */
    public final Object zzg(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return Byte.valueOf(this.zzm);
        }
        if (i2 == 2) {
            return zzR(zza, "\u0001\b\u0000\u0001\u0001\u000f\b\u0000\u0002\u0004\u0001᠌\u0000\u0003Л\u0004င\u0001\u0005ᐉ\u0002\u0006ᐉ\u0003\u0007င\u0005\b\u001b\u000fᐉ\u0004", new Object[]{"zzd", "zze", zzi.zza, "zzf", zzaj.class, "zzg", "zzh", "zzi", "zzk", "zzl", zzam.class, "zzj"});
        } else if (i2 == 3) {
            return new zzj();
        } else {
            if (i2 == 4) {
                return new zzh((zzg) null);
            }
            if (i2 == 5) {
                return zza;
            }
            this.zzm = obj == null ? (byte) 0 : 1;
            return null;
        }
    }
}
