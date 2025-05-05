package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

/* compiled from: com.google.mlkit:barcode-scanning@@17.2.0 */
public final class zzci extends zzed implements zzfp {
    /* access modifiers changed from: private */
    public static final zzci zza;
    private int zzd;
    private int zze;
    private String zzf = "";

    static {
        zzci zzci = new zzci();
        zza = zzci;
        zzed.zzU(zzci.class, zzci);
    }

    private zzci() {
    }

    public static zzci zzb() {
        return zza;
    }

    public final String zzc() {
        return this.zzf;
    }

    public final int zzd() {
        int zza2 = zzch.zza(this.zze);
        if (zza2 == 0) {
            return 1;
        }
        return zza2;
    }

    /* access modifiers changed from: protected */
    public final Object zzg(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzR(zza, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001᠌\u0000\u0002ဈ\u0001", new Object[]{"zzd", "zze", zzcg.zza, "zzf"});
        } else if (i2 == 3) {
            return new zzci();
        } else {
            if (i2 == 4) {
                return new zzce((zzbw) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zza;
        }
    }
}
