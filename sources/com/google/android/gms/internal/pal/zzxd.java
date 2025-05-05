package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzxd extends zzacz implements zzaeg {
    /* access modifiers changed from: private */
    public static final zzxd zzb;
    private int zze;

    static {
        zzxd zzxd = new zzxd();
        zzb = zzxd;
        zzacz.zzaF(zzxd.class, zzxd);
    }

    private zzxd() {
    }

    public static zzxd zzc() {
        return zzb;
    }

    public static zzxd zzd(zzaby zzaby, zzacm zzacm) throws zzadi {
        return (zzxd) zzacz.zzaw(zzb, zzaby, zzacm);
    }

    /* access modifiers changed from: protected */
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzaE(zzb, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\u000b", new Object[]{"zze"});
        } else if (i2 == 3) {
            return new zzxd();
        } else {
            if (i2 == 4) {
                return new zzxc((zzxb) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzb;
        }
    }
}
