package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

/* compiled from: com.google.mlkit:barcode-scanning@@17.2.0 */
public final class zziw extends zzed implements zzfp {
    /* access modifiers changed from: private */
    public static final zziw zza;
    private int zzd;
    private boolean zze;
    private int zzf;
    private boolean zzg = true;
    private int zzh;
    private int zzi;
    private int zzj;
    private int zzk;
    private String zzl = "";
    private String zzm = "";

    static {
        zziw zziw = new zziw();
        zza = zziw;
        zzed.zzU(zziw.class, zziw);
    }

    private zziw() {
    }

    /* access modifiers changed from: protected */
    public final Object zzg(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            zzeh zzeh = zziy.zza;
            zzeh zzeh2 = zziu.zza;
            zzeh zzeh3 = zzix.zza;
            return zzR(zza, "\u0001\t\u0000\u0001\u0001\t\t\u0000\u0000\u0000\u0001ဇ\u0000\u0002᠌\u0001\u0003ဇ\u0002\u0004᠌\u0003\u0005᠌\u0004\u0006᠌\u0005\u0007᠌\u0006\bဈ\u0007\tဈ\b", new Object[]{"zzd", "zze", "zzf", zzeh, "zzg", "zzh", zzeh2, "zzi", zzeh3, "zzj", zzeh3, "zzk", zzeh3, "zzl", "zzm"});
        } else if (i2 == 3) {
            return new zziw();
        } else {
            if (i2 == 4) {
                return new zziv((zzhr) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zza;
        }
    }
}
