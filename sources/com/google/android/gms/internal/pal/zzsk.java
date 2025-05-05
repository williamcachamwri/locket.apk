package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzsk extends zzacz implements zzaeg {
    /* access modifiers changed from: private */
    public static final zzsk zzb;
    /* access modifiers changed from: private */
    public int zze;
    private zzsq zzf;
    /* access modifiers changed from: private */
    public zzaby zzg = zzaby.zzb;

    static {
        zzsk zzsk = new zzsk();
        zzb = zzsk;
        zzacz.zzaF(zzsk.class, zzsk);
    }

    private zzsk() {
    }

    public static zzsj zzc() {
        return (zzsj) zzb.zzau();
    }

    public static zzsk zze(zzaby zzaby, zzacm zzacm) throws zzadi {
        return (zzsk) zzacz.zzaw(zzb, zzaby, zzacm);
    }

    static /* synthetic */ void zzi(zzsk zzsk, zzsq zzsq) {
        zzsq.getClass();
        zzsk.zzf = zzsq;
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
            return new zzsk();
        } else {
            if (i2 == 4) {
                return new zzsj((zzsi) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzb;
        }
    }

    public final zzsq zzf() {
        zzsq zzsq = this.zzf;
        return zzsq == null ? zzsq.zze() : zzsq;
    }

    public final zzaby zzg() {
        return this.zzg;
    }
}
