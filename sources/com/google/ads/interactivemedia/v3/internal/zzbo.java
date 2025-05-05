package com.google.ads.interactivemedia.v3.internal;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzbo extends zzady implements zzafc {
    /* access modifiers changed from: private */
    public static final zzbo zzb;
    private int zzd;
    private long zze = -1;
    private long zzf = -1;
    private long zzg = -1;
    private long zzh = -1;
    private long zzi = -1;
    private long zzj = -1;
    private long zzk = -1;
    private long zzl = -1;

    static {
        zzbo zzbo = new zzbo();
        zzb = zzbo;
        zzady.zzaM(zzbo.class, zzbo);
    }

    private zzbo() {
    }

    public static zzbn zza() {
        return (zzbn) zzb.zzay();
    }

    static /* synthetic */ void zzc(zzbo zzbo, long j) {
        zzbo.zzd |= 32;
        zzbo.zzj = j;
    }

    static /* synthetic */ void zzd(zzbo zzbo, long j) {
        zzbo.zzd |= 4;
        zzbo.zzg = j;
    }

    static /* synthetic */ void zze(zzbo zzbo, long j) {
        zzbo.zzd |= 1;
        zzbo.zze = j;
    }

    static /* synthetic */ void zzf(zzbo zzbo, long j) {
        zzbo.zzd |= 8;
        zzbo.zzh = j;
    }

    static /* synthetic */ void zzg(zzbo zzbo, long j) {
        zzbo.zzd |= 16;
        zzbo.zzi = j;
    }

    /* access modifiers changed from: protected */
    public final Object zzm(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzaJ(zzb, "\u0001\b\u0000\u0001\u0001\b\b\u0000\u0000\u0000\u0001ဂ\u0000\u0002ဂ\u0001\u0003ဂ\u0002\u0004ဂ\u0003\u0005ဂ\u0004\u0006ဂ\u0005\u0007ဂ\u0006\bဂ\u0007", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", "zzl"});
        } else if (i2 == 3) {
            return new zzbo();
        } else {
            if (i2 == 4) {
                return new zzbn((zzbm) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzb;
        }
    }
}
