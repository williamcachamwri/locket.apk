package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzus extends zzacz implements zzaeg {
    /* access modifiers changed from: private */
    public static final zzus zzb;
    private zzuv zze;
    /* access modifiers changed from: private */
    public int zzf;
    private int zzg;

    static {
        zzus zzus = new zzus();
        zzb = zzus;
        zzacz.zzaF(zzus.class, zzus);
    }

    private zzus() {
    }

    public static zzur zzc() {
        return (zzur) zzb.zzau();
    }

    public static zzus zze() {
        return zzb;
    }

    public static zzus zzf(zzaby zzaby, zzacm zzacm) throws zzadi {
        return (zzus) zzacz.zzaw(zzb, zzaby, zzacm);
    }

    static /* synthetic */ void zzh(zzus zzus, zzuv zzuv) {
        zzuv.getClass();
        zzus.zze = zzuv;
    }

    public final int zza() {
        return this.zzf;
    }

    /* access modifiers changed from: protected */
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzaE(zzb, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\t\u0002\u000b\u0003\u000b", new Object[]{"zze", "zzf", "zzg"});
        } else if (i2 == 3) {
            return new zzus();
        } else {
            if (i2 == 4) {
                return new zzur((zzuq) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzb;
        }
    }

    public final zzuv zzg() {
        zzuv zzuv = this.zze;
        return zzuv == null ? zzuv.zze() : zzuv;
    }
}
