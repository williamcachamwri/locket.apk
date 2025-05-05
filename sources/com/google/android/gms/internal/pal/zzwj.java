package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzwj extends zzacz implements zzaeg {
    /* access modifiers changed from: private */
    public static final zzwj zzb;
    /* access modifiers changed from: private */
    public int zze;
    private zzwm zzf;

    static {
        zzwj zzwj = new zzwj();
        zzb = zzwj;
        zzacz.zzaF(zzwj.class, zzwj);
    }

    private zzwj() {
    }

    public static zzwi zzc() {
        return (zzwi) zzb.zzau();
    }

    public static zzwj zze(zzaby zzaby, zzacm zzacm) throws zzadi {
        return (zzwj) zzacz.zzaw(zzb, zzaby, zzacm);
    }

    static /* synthetic */ void zzh(zzwj zzwj, zzwm zzwm) {
        zzwm.getClass();
        zzwj.zzf = zzwm;
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
            return zzaE(zzb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u000b\u0002\t", new Object[]{"zze", "zzf"});
        } else if (i2 == 3) {
            return new zzwj();
        } else {
            if (i2 == 4) {
                return new zzwi((zzwh) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzb;
        }
    }

    public final zzwm zzf() {
        zzwm zzwm = this.zzf;
        return zzwm == null ? zzwm.zzc() : zzwm;
    }
}
