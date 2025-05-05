package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzrv extends zzacz implements zzaeg {
    /* access modifiers changed from: private */
    public static final zzrv zzb;
    /* access modifiers changed from: private */
    public int zze;
    private zzsb zzf;
    private zzup zzg;

    static {
        zzrv zzrv = new zzrv();
        zzb = zzrv;
        zzacz.zzaF(zzrv.class, zzrv);
    }

    private zzrv() {
    }

    public static zzru zzc() {
        return (zzru) zzb.zzau();
    }

    public static zzrv zze(zzaby zzaby, zzacm zzacm) throws zzadi {
        return (zzrv) zzacz.zzaw(zzb, zzaby, zzacm);
    }

    static /* synthetic */ void zzi(zzrv zzrv, zzsb zzsb) {
        zzsb.getClass();
        zzrv.zzf = zzsb;
    }

    static /* synthetic */ void zzj(zzrv zzrv, zzup zzup) {
        zzup.getClass();
        zzrv.zzg = zzup;
    }

    public final int zza() {
        return this.zze;
    }

    /* access modifiers changed from: protected */
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzaE(zzb, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000b\u0002\t\u0003\t", new Object[]{"zze", "zzf", "zzg"});
        } else if (i2 == 3) {
            return new zzrv();
        } else {
            if (i2 == 4) {
                return new zzru((zzrt) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzb;
        }
    }

    public final zzsb zzf() {
        zzsb zzsb = this.zzf;
        return zzsb == null ? zzsb.zze() : zzsb;
    }

    public final zzup zzg() {
        zzup zzup = this.zzg;
        return zzup == null ? zzup.zze() : zzup;
    }
}
