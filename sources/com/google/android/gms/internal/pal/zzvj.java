package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzvj extends zzacz implements zzaeg {
    /* access modifiers changed from: private */
    public static final zzvj zzb;
    /* access modifiers changed from: private */
    public int zze;
    private zzvd zzf;
    /* access modifiers changed from: private */
    public zzaby zzg = zzaby.zzb;

    static {
        zzvj zzvj = new zzvj();
        zzb = zzvj;
        zzacz.zzaF(zzvj.class, zzvj);
    }

    private zzvj() {
    }

    public static zzvi zzd() {
        return (zzvi) zzb.zzau();
    }

    public static zzvj zzf() {
        return zzb;
    }

    public static zzvj zzg(zzaby zzaby, zzacm zzacm) throws zzadi {
        return (zzvj) zzacz.zzaw(zzb, zzaby, zzacm);
    }

    static /* synthetic */ void zzj(zzvj zzvj, zzvd zzvd) {
        zzvd.getClass();
        zzvj.zzf = zzvd;
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
            return new zzvj();
        } else {
            if (i2 == 4) {
                return new zzvi((zzvh) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzb;
        }
    }

    public final zzvd zzc() {
        zzvd zzvd = this.zzf;
        return zzvd == null ? zzvd.zzd() : zzvd;
    }

    public final zzaby zzh() {
        return this.zzg;
    }

    public final boolean zzl() {
        return this.zzf != null;
    }
}
