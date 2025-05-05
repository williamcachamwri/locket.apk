package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzwm extends zzacz implements zzaeg {
    /* access modifiers changed from: private */
    public static final zzwm zzb;
    private String zze = "";

    static {
        zzwm zzwm = new zzwm();
        zzb = zzwm;
        zzacz.zzaF(zzwm.class, zzwm);
    }

    private zzwm() {
    }

    public static zzwm zzc() {
        return zzb;
    }

    public static zzwm zzd(zzaby zzaby, zzacm zzacm) throws zzadi {
        return (zzwm) zzacz.zzaw(zzb, zzaby, zzacm);
    }

    /* access modifiers changed from: protected */
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzaE(zzb, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001Ȉ", new Object[]{"zze"});
        } else if (i2 == 3) {
            return new zzwm();
        } else {
            if (i2 == 4) {
                return new zzwl((zzwk) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzb;
        }
    }

    public final String zze() {
        return this.zze;
    }
}
