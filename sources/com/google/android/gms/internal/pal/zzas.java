package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzas extends zzacz implements zzaeg {
    /* access modifiers changed from: private */
    public static final zzas zzb;
    private int zze;
    private String zzf = "";

    static {
        zzas zzas = new zzas();
        zzb = zzas;
        zzacz.zzaF(zzas.class, zzas);
    }

    private zzas() {
    }

    public static zzar zza() {
        return (zzar) zzb.zzau();
    }

    static /* synthetic */ void zzd(zzas zzas, String str) {
        str.getClass();
        zzas.zze |= 1;
        zzas.zzf = str;
    }

    /* access modifiers changed from: protected */
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzaE(zzb, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001ဈ\u0000", new Object[]{"zze", "zzf"});
        } else if (i2 == 3) {
            return new zzas();
        } else {
            if (i2 == 4) {
                return new zzar((zzq) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzb;
        }
    }
}
