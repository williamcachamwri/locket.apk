package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

/* compiled from: com.google.mlkit:barcode-scanning@@17.2.0 */
public final class zzp extends zzed implements zzfp {
    /* access modifiers changed from: private */
    public static final zzp zza;
    private int zzd;
    private zzej zze = zzN();
    private zzei zzf = zzL();
    private boolean zzg = true;
    private String zzh = "";
    private String zzi = "";
    private zzkd zzj;
    private byte zzk = 2;

    static {
        zzp zzp = new zzp();
        zza = zzp;
        zzed.zzU(zzp.class, zzp);
    }

    private zzp() {
    }

    /* access modifiers changed from: protected */
    public final Object zzg(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return Byte.valueOf(this.zzk);
        }
        if (i2 == 2) {
            return zzR(zza, "\u0001\u0006\u0000\u0001\u0001\u000f\u0006\u0000\u0002\u0001\u0001\u0016\u0002\u0013\u0003ဇ\u0000\u0004ဈ\u0001\u0005ဈ\u0002\u000fᐉ\u0003", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj"});
        } else if (i2 == 3) {
            return new zzp();
        } else {
            if (i2 == 4) {
                return new zzo((zzn) null);
            }
            if (i2 == 5) {
                return zza;
            }
            this.zzk = obj == null ? (byte) 0 : 1;
            return null;
        }
    }
}
