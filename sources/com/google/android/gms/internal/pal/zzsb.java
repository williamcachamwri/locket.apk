package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzsb extends zzacz implements zzaeg {
    /* access modifiers changed from: private */
    public static final zzsb zzb;
    /* access modifiers changed from: private */
    public int zze;
    private zzsh zzf;
    /* access modifiers changed from: private */
    public zzaby zzg = zzaby.zzb;

    static {
        zzsb zzsb = new zzsb();
        zzb = zzsb;
        zzacz.zzaF(zzsb.class, zzsb);
    }

    private zzsb() {
    }

    public static zzsa zzc() {
        return (zzsa) zzb.zzau();
    }

    public static zzsb zze() {
        return zzb;
    }

    public static zzsb zzf(zzaby zzaby, zzacm zzacm) throws zzadi {
        return (zzsb) zzacz.zzaw(zzb, zzaby, zzacm);
    }

    static /* synthetic */ void zzj(zzsb zzsb, zzsh zzsh) {
        zzsh.getClass();
        zzsb.zzf = zzsh;
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
            return zzaE(zzb, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000b\u0002\t\u0003\n", new Object[]{"zze", "zzf", "zzg"});
        } else if (i2 == 3) {
            return new zzsb();
        } else {
            if (i2 == 4) {
                return new zzsa((zzrz) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzb;
        }
    }

    public final zzsh zzg() {
        zzsh zzsh = this.zzf;
        return zzsh == null ? zzsh.zze() : zzsh;
    }

    public final zzaby zzh() {
        return this.zzg;
    }
}
