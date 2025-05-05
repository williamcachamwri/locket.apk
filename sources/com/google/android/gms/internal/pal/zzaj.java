package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzaj extends zzacz implements zzaeg {
    /* access modifiers changed from: private */
    public static final zzaj zzb;
    private int zze;
    private zzaby zzf = zzaby.zzb;
    private zzaby zzg = zzaby.zzb;

    static {
        zzaj zzaj = new zzaj();
        zzb = zzaj;
        zzacz.zzaF(zzaj.class, zzaj);
    }

    private zzaj() {
    }

    public static zzai zza() {
        return (zzai) zzb.zzau();
    }

    static /* synthetic */ void zzd(zzaj zzaj, zzaby zzaby) {
        zzaj.zze |= 1;
        zzaj.zzf = zzaby;
    }

    static /* synthetic */ void zze(zzaj zzaj, zzaby zzaby) {
        zzaj.zze |= 2;
        zzaj.zzg = zzaby;
    }

    /* access modifiers changed from: protected */
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzaE(zzb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ည\u0000\u0002ည\u0001", new Object[]{"zze", "zzf", "zzg"});
        } else if (i2 == 3) {
            return new zzaj();
        } else {
            if (i2 == 4) {
                return new zzai((zzq) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzb;
        }
    }
}
