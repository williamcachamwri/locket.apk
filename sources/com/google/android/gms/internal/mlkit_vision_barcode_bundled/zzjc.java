package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

/* compiled from: com.google.mlkit:barcode-scanning@@17.2.0 */
public final class zzjc extends zzed implements zzfp {
    /* access modifiers changed from: private */
    public static final zzjc zza;
    private int zzd;
    private int zze = -1;
    private boolean zzf;
    private int zzg;
    private zzdb zzh = zzdb.zzb;
    private String zzi = "";
    private boolean zzj;
    private boolean zzk = true;
    private boolean zzl = true;
    private int zzm;
    private int zzn;
    private boolean zzo;

    static {
        zzjc zzjc = new zzjc();
        zza = zzjc;
        zzed.zzU(zzjc.class, zzjc);
    }

    private zzjc() {
    }

    /* access modifiers changed from: protected */
    public final Object zzg(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            zzeh zzeh = zzja.zza;
            zzeh zzeh2 = zzjb.zza;
            return zzR(zza, "\u0001\u000b\u0000\u0001\u0001\u000b\u000b\u0000\u0000\u0000\u0001င\u0000\u0002ဇ\u0001\u0003᠌\u0002\u0004ည\u0003\u0005ဈ\u0004\u0006ဇ\u0005\u0007ဇ\u0006\bဇ\u0007\t᠌\b\n᠌\t\u000bဇ\n", new Object[]{"zzd", "zze", "zzf", "zzg", zzeh, "zzh", "zzi", "zzj", "zzk", "zzl", "zzm", zzeh2, "zzn", zzeh2, "zzo"});
        } else if (i2 == 3) {
            return new zzjc();
        } else {
            if (i2 == 4) {
                return new zziz((zzhr) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zza;
        }
    }
}
