package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzry extends zzacz implements zzaeg {
    /* access modifiers changed from: private */
    public static final zzry zzb;
    private zzse zze;
    private zzus zzf;

    static {
        zzry zzry = new zzry();
        zzb = zzry;
        zzacz.zzaF(zzry.class, zzry);
    }

    private zzry() {
    }

    public static zzrx zza() {
        return (zzrx) zzb.zzau();
    }

    public static zzry zzd(zzaby zzaby, zzacm zzacm) throws zzadi {
        return (zzry) zzacz.zzaw(zzb, zzaby, zzacm);
    }

    static /* synthetic */ void zzg(zzry zzry, zzse zzse) {
        zzse.getClass();
        zzry.zze = zzse;
    }

    static /* synthetic */ void zzh(zzry zzry, zzus zzus) {
        zzus.getClass();
        zzry.zzf = zzus;
    }

    /* access modifiers changed from: protected */
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzaE(zzb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\t\u0002\t", new Object[]{"zze", "zzf"});
        } else if (i2 == 3) {
            return new zzry();
        } else {
            if (i2 == 4) {
                return new zzrx((zzrw) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzb;
        }
    }

    public final zzse zze() {
        zzse zzse = this.zze;
        return zzse == null ? zzse.zze() : zzse;
    }

    public final zzus zzf() {
        zzus zzus = this.zzf;
        return zzus == null ? zzus.zze() : zzus;
    }
}
