package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzvg extends zzacz implements zzaeg {
    /* access modifiers changed from: private */
    public static final zzvg zzb;
    /* access modifiers changed from: private */
    public int zze;
    private zzvj zzf;
    /* access modifiers changed from: private */
    public zzaby zzg = zzaby.zzb;

    static {
        zzvg zzvg = new zzvg();
        zzb = zzvg;
        zzacz.zzaF(zzvg.class, zzvg);
    }

    private zzvg() {
    }

    public static zzvf zzc() {
        return (zzvf) zzb.zzau();
    }

    public static zzvg zze(zzaby zzaby, zzacm zzacm) throws zzadi {
        return (zzvg) zzacz.zzaw(zzb, zzaby, zzacm);
    }

    static /* synthetic */ void zzi(zzvg zzvg, zzvj zzvj) {
        zzvj.getClass();
        zzvg.zzf = zzvj;
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
            return new zzvg();
        } else {
            if (i2 == 4) {
                return new zzvf((zzve) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzb;
        }
    }

    public final zzvj zzf() {
        zzvj zzvj = this.zzf;
        return zzvj == null ? zzvj.zzf() : zzvj;
    }

    public final zzaby zzg() {
        return this.zzg;
    }

    public final boolean zzk() {
        return this.zzf != null;
    }
}
