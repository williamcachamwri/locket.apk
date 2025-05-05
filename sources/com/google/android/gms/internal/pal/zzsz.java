package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzsz extends zzacz implements zzaeg {
    /* access modifiers changed from: private */
    public static final zzsz zzb;
    /* access modifiers changed from: private */
    public int zze;
    /* access modifiers changed from: private */
    public zzaby zzf = zzaby.zzb;

    static {
        zzsz zzsz = new zzsz();
        zzb = zzsz;
        zzacz.zzaF(zzsz.class, zzsz);
    }

    private zzsz() {
    }

    public static zzsy zzc() {
        return (zzsy) zzb.zzau();
    }

    public static zzsz zze(zzaby zzaby, zzacm zzacm) throws zzadi {
        return (zzsz) zzacz.zzaw(zzb, zzaby, zzacm);
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
            return zzaE(zzb, "\u0000\u0002\u0000\u0000\u0001\u0003\u0002\u0000\u0000\u0000\u0001\u000b\u0003\n", new Object[]{"zze", "zzf"});
        } else if (i2 == 3) {
            return new zzsz();
        } else {
            if (i2 == 4) {
                return new zzsy((zzsx) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzb;
        }
    }

    public final zzaby zzf() {
        return this.zzf;
    }
}
