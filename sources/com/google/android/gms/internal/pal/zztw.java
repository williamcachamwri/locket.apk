package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zztw extends zzacz implements zzaeg {
    /* access modifiers changed from: private */
    public static final zztw zzb;
    private zztz zze;

    static {
        zztw zztw = new zztw();
        zzb = zztw;
        zzacz.zzaF(zztw.class, zztw);
    }

    private zztw() {
    }

    public static zztv zza() {
        return (zztv) zzb.zzau();
    }

    public static zztw zzd(zzaby zzaby, zzacm zzacm) throws zzadi {
        return (zztw) zzacz.zzaw(zzb, zzaby, zzacm);
    }

    static /* synthetic */ void zzf(zztw zztw, zztz zztz) {
        zztz.getClass();
        zztw.zze = zztz;
    }

    /* access modifiers changed from: protected */
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzaE(zzb, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\t", new Object[]{"zze"});
        } else if (i2 == 3) {
            return new zztw();
        } else {
            if (i2 == 4) {
                return new zztv((zztu) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzb;
        }
    }

    public final zztz zze() {
        zztz zztz = this.zze;
        return zztz == null ? zztz.zze() : zztz;
    }
}
