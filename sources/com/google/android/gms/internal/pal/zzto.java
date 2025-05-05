package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzto extends zzacz implements zzaeg {
    /* access modifiers changed from: private */
    public static final zzto zzb;

    static {
        zzto zzto = new zzto();
        zzb = zzto;
        zzacz.zzaF(zzto.class, zzto);
    }

    private zzto() {
    }

    public static zzto zzc() {
        return zzb;
    }

    public static zzto zzd(zzaby zzaby, zzacm zzacm) throws zzadi {
        return (zzto) zzacz.zzaw(zzb, zzaby, zzacm);
    }

    /* access modifiers changed from: protected */
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzaE(zzb, "\u0000\u0000", (Object[]) null);
        }
        if (i2 == 3) {
            return new zzto();
        }
        if (i2 == 4) {
            return new zztn((zztm) null);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
