package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zztc extends zzacz implements zzaeg {
    /* access modifiers changed from: private */
    public static final zztc zzb;
    /* access modifiers changed from: private */
    public int zze;
    private int zzf;

    static {
        zztc zztc = new zztc();
        zzb = zztc;
        zzacz.zzaF(zztc.class, zztc);
    }

    private zztc() {
    }

    public static zztb zzc() {
        return (zztb) zzb.zzau();
    }

    public static zztc zze(zzaby zzaby, zzacm zzacm) throws zzadi {
        return (zztc) zzacz.zzaw(zzb, zzaby, zzacm);
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
            return zzaE(zzb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u000b\u0002\u000b", new Object[]{"zzf", "zze"});
        } else if (i2 == 3) {
            return new zztc();
        } else {
            if (i2 == 4) {
                return new zztb((zzta) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzb;
        }
    }
}
