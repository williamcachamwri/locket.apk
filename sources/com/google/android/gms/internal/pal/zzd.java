package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzd extends zzacz implements zzaeg {
    /* access modifiers changed from: private */
    public static final zzd zzb;
    private int zze;
    private String zzf = "";
    private String zzg = "";

    static {
        zzd zzd = new zzd();
        zzb = zzd;
        zzacz.zzaF(zzd.class, zzd);
    }

    private zzd() {
    }

    /* access modifiers changed from: protected */
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzaE(zzb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001", new Object[]{"zze", "zzf", "zzg"});
        } else if (i2 == 3) {
            return new zzd();
        } else {
            if (i2 == 4) {
                return new zzc((zza) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzb;
        }
    }
}
