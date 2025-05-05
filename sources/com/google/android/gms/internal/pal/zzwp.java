package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzwp extends zzacz implements zzaeg {
    /* access modifiers changed from: private */
    public static final zzwp zzb;
    /* access modifiers changed from: private */
    public int zze;
    private zzws zzf;

    static {
        zzwp zzwp = new zzwp();
        zzb = zzwp;
        zzacz.zzaF(zzwp.class, zzwp);
    }

    private zzwp() {
    }

    public static zzwo zzc() {
        return (zzwo) zzb.zzau();
    }

    public static zzwp zze(zzaby zzaby, zzacm zzacm) throws zzadi {
        return (zzwp) zzacz.zzaw(zzb, zzaby, zzacm);
    }

    static /* synthetic */ void zzh(zzwp zzwp, zzws zzws) {
        zzws.getClass();
        zzwp.zzf = zzws;
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
            return zzaE(zzb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u000b\u0002\t", new Object[]{"zze", "zzf"});
        } else if (i2 == 3) {
            return new zzwp();
        } else {
            if (i2 == 4) {
                return new zzwo((zzwn) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzb;
        }
    }

    public final zzws zzf() {
        zzws zzws = this.zzf;
        return zzws == null ? zzws.zzd() : zzws;
    }
}
