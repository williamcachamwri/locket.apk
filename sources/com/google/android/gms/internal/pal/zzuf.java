package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzuf extends zzacz implements zzaeg {
    /* access modifiers changed from: private */
    public static final zzuf zzb;
    /* access modifiers changed from: private */
    public int zze;
    private zztz zzf;
    /* access modifiers changed from: private */
    public zzaby zzg = zzaby.zzb;
    /* access modifiers changed from: private */
    public zzaby zzh = zzaby.zzb;

    static {
        zzuf zzuf = new zzuf();
        zzb = zzuf;
        zzacz.zzaF(zzuf.class, zzuf);
    }

    private zzuf() {
    }

    public static zzue zzd() {
        return (zzue) zzb.zzau();
    }

    public static zzuf zzf() {
        return zzb;
    }

    public static zzuf zzg(zzaby zzaby, zzacm zzacm) throws zzadi {
        return (zzuf) zzacz.zzaw(zzb, zzaby, zzacm);
    }

    static /* synthetic */ void zzk(zzuf zzuf, zztz zztz) {
        zztz.getClass();
        zzuf.zzf = zztz;
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
            return zzaE(zzb, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001\u000b\u0002\t\u0003\n\u0004\n", new Object[]{"zze", "zzf", "zzg", "zzh"});
        } else if (i2 == 3) {
            return new zzuf();
        } else {
            if (i2 == 4) {
                return new zzue((zzud) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzb;
        }
    }

    public final zztz zzc() {
        zztz zztz = this.zzf;
        return zztz == null ? zztz.zze() : zztz;
    }

    public final zzaby zzh() {
        return this.zzg;
    }

    public final zzaby zzi() {
        return this.zzh;
    }
}
