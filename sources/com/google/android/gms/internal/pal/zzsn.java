package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzsn extends zzacz implements zzaeg {
    /* access modifiers changed from: private */
    public static final zzsn zzb;
    private zzsq zze;
    /* access modifiers changed from: private */
    public int zzf;

    static {
        zzsn zzsn = new zzsn();
        zzb = zzsn;
        zzacz.zzaF(zzsn.class, zzsn);
    }

    private zzsn() {
    }

    public static zzsm zzc() {
        return (zzsm) zzb.zzau();
    }

    public static zzsn zze(zzaby zzaby, zzacm zzacm) throws zzadi {
        return (zzsn) zzacz.zzaw(zzb, zzaby, zzacm);
    }

    static /* synthetic */ void zzg(zzsn zzsn, zzsq zzsq) {
        zzsq.getClass();
        zzsn.zze = zzsq;
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
            return new zzsn();
        } else {
            if (i2 == 4) {
                return new zzsm((zzsl) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzb;
        }
    }

    public final zzsq zzf() {
        zzsq zzsq = this.zze;
        return zzsq == null ? zzsq.zze() : zzsq;
    }
}
