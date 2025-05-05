package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzva extends zzacz implements zzaeg {
    /* access modifiers changed from: private */
    public static final zzva zzb;
    private zzvd zze;

    static {
        zzva zzva = new zzva();
        zzb = zzva;
        zzacz.zzaF(zzva.class, zzva);
    }

    private zzva() {
    }

    public static zzuz zza() {
        return (zzuz) zzb.zzau();
    }

    public static zzva zzd(zzaby zzaby, zzacm zzacm) throws zzadi {
        return (zzva) zzacz.zzaw(zzb, zzaby, zzacm);
    }

    static /* synthetic */ void zzf(zzva zzva, zzvd zzvd) {
        zzvd.getClass();
        zzva.zze = zzvd;
    }

    /* access modifiers changed from: protected */
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzaE(zzb, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\t", new Object[]{"zze"});
        } else if (i2 == 3) {
            return new zzva();
        } else {
            if (i2 == 4) {
                return new zzuz((zzuy) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzb;
        }
    }

    public final zzvd zze() {
        zzvd zzvd = this.zze;
        return zzvd == null ? zzvd.zzd() : zzvd;
    }
}
