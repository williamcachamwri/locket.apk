package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzuc extends zzacz implements zzaeg {
    /* access modifiers changed from: private */
    public static final zzuc zzb;
    /* access modifiers changed from: private */
    public int zze;
    private zzuf zzf;
    /* access modifiers changed from: private */
    public zzaby zzg = zzaby.zzb;

    static {
        zzuc zzuc = new zzuc();
        zzb = zzuc;
        zzacz.zzaF(zzuc.class, zzuc);
    }

    private zzuc() {
    }

    public static zzub zzc() {
        return (zzub) zzb.zzau();
    }

    public static zzuc zze(zzaby zzaby, zzacm zzacm) throws zzadi {
        return (zzuc) zzacz.zzaw(zzb, zzaby, zzacm);
    }

    static /* synthetic */ void zzi(zzuc zzuc, zzuf zzuf) {
        zzuf.getClass();
        zzuc.zzf = zzuf;
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
            return new zzuc();
        } else {
            if (i2 == 4) {
                return new zzub((zzua) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzb;
        }
    }

    public final zzuf zzf() {
        zzuf zzuf = this.zzf;
        return zzuf == null ? zzuf.zzf() : zzuf;
    }

    public final zzaby zzg() {
        return this.zzg;
    }
}
