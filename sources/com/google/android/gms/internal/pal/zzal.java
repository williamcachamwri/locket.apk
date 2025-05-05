package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzal extends zzacz implements zzaeg {
    /* access modifiers changed from: private */
    public static final zzal zzb;
    private int zze;
    private zzaby zzf = zzaby.zzb;
    private zzaby zzg;
    private zzaby zzh;
    private zzaby zzi;

    static {
        zzal zzal = new zzal();
        zzb = zzal;
        zzacz.zzaF(zzal.class, zzal);
    }

    private zzal() {
        zzaby zzaby = zzaby.zzb;
        this.zzg = zzaby;
        this.zzh = zzaby;
        this.zzi = zzaby;
    }

    public static zzak zza() {
        return (zzak) zzb.zzau();
    }

    public static zzal zzd(byte[] bArr, zzacm zzacm) throws zzadi {
        return (zzal) zzacz.zzax(zzb, bArr, zzacm);
    }

    static /* synthetic */ void zzi(zzal zzal, zzaby zzaby) {
        zzal.zze |= 1;
        zzal.zzf = zzaby;
    }

    static /* synthetic */ void zzj(zzal zzal, zzaby zzaby) {
        zzal.zze |= 2;
        zzal.zzg = zzaby;
    }

    static /* synthetic */ void zzk(zzal zzal, zzaby zzaby) {
        zzal.zze |= 4;
        zzal.zzh = zzaby;
    }

    static /* synthetic */ void zzl(zzal zzal, zzaby zzaby) {
        zzal.zze |= 8;
        zzal.zzi = zzaby;
    }

    /* access modifiers changed from: protected */
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzaE(zzb, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ည\u0000\u0002ည\u0001\u0003ည\u0002\u0004ည\u0003", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi"});
        } else if (i2 == 3) {
            return new zzal();
        } else {
            if (i2 == 4) {
                return new zzak((zzq) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzb;
        }
    }

    public final zzaby zze() {
        return this.zzf;
    }

    public final zzaby zzf() {
        return this.zzg;
    }

    public final zzaby zzg() {
        return this.zzi;
    }

    public final zzaby zzh() {
        return this.zzh;
    }
}
