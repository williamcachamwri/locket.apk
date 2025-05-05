package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzti extends zzacz implements zzaeg {
    /* access modifiers changed from: private */
    public static final zzti zzb;
    /* access modifiers changed from: private */
    public int zze;
    private int zzf;

    static {
        zzti zzti = new zzti();
        zzb = zzti;
        zzacz.zzaF(zzti.class, zzti);
    }

    private zzti() {
    }

    public static zzth zzc() {
        return (zzth) zzb.zzau();
    }

    public static zzti zze(zzaby zzaby, zzacm zzacm) throws zzadi {
        return (zzti) zzacz.zzaw(zzb, zzaby, zzacm);
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
            return zzaE(zzb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u000b\u0002\u000b", new Object[]{"zze", "zzf"});
        } else if (i2 == 3) {
            return new zzti();
        } else {
            if (i2 == 4) {
                return new zzth((zztg) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzb;
        }
    }
}
