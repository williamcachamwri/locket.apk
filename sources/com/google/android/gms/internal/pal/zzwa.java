package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzwa extends zzacz implements zzaeg {
    /* access modifiers changed from: private */
    public static final zzwa zzb;
    private zzvo zze;
    /* access modifiers changed from: private */
    public int zzf;
    /* access modifiers changed from: private */
    public int zzg;
    /* access modifiers changed from: private */
    public int zzh;

    static {
        zzwa zzwa = new zzwa();
        zzb = zzwa;
        zzacz.zzaF(zzwa.class, zzwa);
    }

    private zzwa() {
    }

    public static zzvz zzd() {
        return (zzvz) zzb.zzau();
    }

    static /* synthetic */ void zzf(zzwa zzwa, zzvo zzvo) {
        zzvo.getClass();
        zzwa.zze = zzvo;
    }

    public final int zza() {
        return this.zzg;
    }

    /* access modifiers changed from: protected */
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzaE(zzb, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001\t\u0002\f\u0003\u000b\u0004\f", new Object[]{"zze", "zzf", "zzg", "zzh"});
        } else if (i2 == 3) {
            return new zzwa();
        } else {
            if (i2 == 4) {
                return new zzvz((zzvx) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzb;
        }
    }

    public final zzvo zzc() {
        zzvo zzvo = this.zze;
        return zzvo == null ? zzvo.zze() : zzvo;
    }

    public final boolean zzh() {
        return this.zze != null;
    }

    public final int zzi() {
        int i = this.zzf;
        int i2 = 2;
        if (i != 0) {
            i2 = i != 1 ? i != 2 ? i != 3 ? 0 : 5 : 4 : 3;
        }
        if (i2 == 0) {
            return 1;
        }
        return i2;
    }

    public final int zzj() {
        int zzb2 = zzwu.zzb(this.zzh);
        if (zzb2 == 0) {
            return 1;
        }
        return zzb2;
    }
}
