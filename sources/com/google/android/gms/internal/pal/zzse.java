package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzse extends zzacz implements zzaeg {
    /* access modifiers changed from: private */
    public static final zzse zzb;
    private zzsh zze;
    /* access modifiers changed from: private */
    public int zzf;

    static {
        zzse zzse = new zzse();
        zzb = zzse;
        zzacz.zzaF(zzse.class, zzse);
    }

    private zzse() {
    }

    public static zzsd zzc() {
        return (zzsd) zzb.zzau();
    }

    public static zzse zze() {
        return zzb;
    }

    public static zzse zzf(zzaby zzaby, zzacm zzacm) throws zzadi {
        return (zzse) zzacz.zzaw(zzb, zzaby, zzacm);
    }

    static /* synthetic */ void zzh(zzse zzse, zzsh zzsh) {
        zzsh.getClass();
        zzse.zze = zzsh;
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
            return zzaE(zzb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\t\u0002\u000b", new Object[]{"zze", "zzf"});
        } else if (i2 == 3) {
            return new zzse();
        } else {
            if (i2 == 4) {
                return new zzsd((zzsc) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzb;
        }
    }

    public final zzsh zzg() {
        zzsh zzsh = this.zze;
        return zzsh == null ? zzsh.zze() : zzsh;
    }
}
