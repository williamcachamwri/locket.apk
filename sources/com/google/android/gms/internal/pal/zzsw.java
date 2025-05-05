package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzsw extends zzacz implements zzaeg {
    /* access modifiers changed from: private */
    public static final zzsw zzb;
    /* access modifiers changed from: private */
    public int zze;
    private int zzf;

    static {
        zzsw zzsw = new zzsw();
        zzb = zzsw;
        zzacz.zzaF(zzsw.class, zzsw);
    }

    private zzsw() {
    }

    public static zzsv zzc() {
        return (zzsv) zzb.zzau();
    }

    public static zzsw zze(zzaby zzaby, zzacm zzacm) throws zzadi {
        return (zzsw) zzacz.zzaw(zzb, zzaby, zzacm);
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
            return zzaE(zzb, "\u0000\u0002\u0000\u0000\u0002\u0003\u0002\u0000\u0000\u0000\u0002\u000b\u0003\u000b", new Object[]{"zze", "zzf"});
        } else if (i2 == 3) {
            return new zzsw();
        } else {
            if (i2 == 4) {
                return new zzsv((zzsu) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzb;
        }
    }
}
