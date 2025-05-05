package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzup extends zzacz implements zzaeg {
    /* access modifiers changed from: private */
    public static final zzup zzb;
    /* access modifiers changed from: private */
    public int zze;
    private zzuv zzf;
    /* access modifiers changed from: private */
    public zzaby zzg = zzaby.zzb;

    static {
        zzup zzup = new zzup();
        zzb = zzup;
        zzacz.zzaF(zzup.class, zzup);
    }

    private zzup() {
    }

    public static zzuo zzc() {
        return (zzuo) zzb.zzau();
    }

    public static zzup zze() {
        return zzb;
    }

    public static zzup zzf(zzaby zzaby, zzacm zzacm) throws zzadi {
        return (zzup) zzacz.zzaw(zzb, zzaby, zzacm);
    }

    static /* synthetic */ void zzj(zzup zzup, zzuv zzuv) {
        zzuv.getClass();
        zzup.zzf = zzuv;
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
            return new zzup();
        } else {
            if (i2 == 4) {
                return new zzuo((zzun) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzb;
        }
    }

    public final zzuv zzg() {
        zzuv zzuv = this.zzf;
        return zzuv == null ? zzuv.zze() : zzuv;
    }

    public final zzaby zzh() {
        return this.zzg;
    }
}
