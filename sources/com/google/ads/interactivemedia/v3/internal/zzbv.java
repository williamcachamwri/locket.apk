package com.google.ads.interactivemedia.v3.internal;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzbv extends zzady implements zzafc {
    /* access modifiers changed from: private */
    public static final zzbv zzb;
    private int zzd;
    private zzacw zze = zzacw.zzb;
    private zzacw zzf;
    private zzacw zzg;
    private zzacw zzh;

    static {
        zzbv zzbv = new zzbv();
        zzb = zzbv;
        zzady.zzaM(zzbv.class, zzbv);
    }

    private zzbv() {
        zzacw zzacw = zzacw.zzb;
        this.zzf = zzacw;
        this.zzg = zzacw;
        this.zzh = zzacw;
    }

    public static zzbu zza() {
        return (zzbu) zzb.zzay();
    }

    public static zzbv zzc(byte[] bArr, zzadk zzadk) throws zzaeg {
        return (zzbv) zzady.zzaE(zzb, bArr, zzadk);
    }

    static /* synthetic */ void zzh(zzbv zzbv, zzacw zzacw) {
        zzbv.zzd |= 1;
        zzbv.zze = zzacw;
    }

    static /* synthetic */ void zzi(zzbv zzbv, zzacw zzacw) {
        zzbv.zzd |= 2;
        zzbv.zzf = zzacw;
    }

    static /* synthetic */ void zzj(zzbv zzbv, zzacw zzacw) {
        zzbv.zzd |= 8;
        zzbv.zzh = zzacw;
    }

    static /* synthetic */ void zzk(zzbv zzbv, zzacw zzacw) {
        zzbv.zzd |= 4;
        zzbv.zzg = zzacw;
    }

    public final zzacw zzd() {
        return this.zze;
    }

    public final zzacw zze() {
        return this.zzf;
    }

    public final zzacw zzf() {
        return this.zzh;
    }

    public final zzacw zzg() {
        return this.zzg;
    }

    /* access modifiers changed from: protected */
    public final Object zzm(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzaJ(zzb, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ည\u0000\u0002ည\u0001\u0003ည\u0002\u0004ည\u0003", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh"});
        } else if (i2 == 3) {
            return new zzbv();
        } else {
            if (i2 == 4) {
                return new zzbu((zzbt) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzb;
        }
    }
}
