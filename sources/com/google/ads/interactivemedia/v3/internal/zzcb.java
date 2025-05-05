package com.google.ads.interactivemedia.v3.internal;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzcb extends zzady implements zzafc {
    /* access modifiers changed from: private */
    public static final zzcb zzb;
    private int zzd;
    private long zze;
    private String zzf = "";
    private zzacw zzg = zzacw.zzb;

    static {
        zzcb zzcb = new zzcb();
        zzb = zzcb;
        zzady.zzaM(zzcb.class, zzcb);
    }

    private zzcb() {
    }

    public static zzcb zzc() {
        return zzb;
    }

    public final long zza() {
        return this.zze;
    }

    public final boolean zzd() {
        return (this.zzd & 1) != 0;
    }

    /* access modifiers changed from: protected */
    public final Object zzm(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzaJ(zzb, "\u0001\u0003\u0000\u0001\u0001\u0004\u0003\u0000\u0000\u0000\u0001ဂ\u0000\u0003ဈ\u0001\u0004ည\u0002", new Object[]{"zzd", "zze", "zzf", "zzg"});
        } else if (i2 == 3) {
            return new zzcb();
        } else {
            if (i2 == 4) {
                return new zzca((zzbz) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzb;
        }
    }
}
