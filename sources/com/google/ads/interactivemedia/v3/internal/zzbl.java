package com.google.ads.interactivemedia.v3.internal;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzbl extends zzady implements zzafc {
    /* access modifiers changed from: private */
    public static final zzbl zzb;
    private int zzd;
    private long zze = -1;
    private long zzf = -1;
    private long zzg = -1;
    private long zzh = -1;
    private long zzi = -1;
    private long zzj = -1;
    private int zzk = 1000;
    private long zzl = -1;
    private long zzm = -1;
    private long zzn = -1;
    private int zzo = 1000;
    private long zzp = -1;
    private long zzq = -1;
    private long zzr = -1;
    private long zzs = -1;
    private long zzt;
    private long zzu;
    private long zzv = -1;
    private long zzw = -1;
    private long zzx = -1;
    private long zzy = -1;

    static {
        zzbl zzbl = new zzbl();
        zzb = zzbl;
        zzady.zzaM(zzbl.class, zzbl);
    }

    private zzbl() {
    }

    public static zzbk zza() {
        return (zzbk) zzb.zzay();
    }

    static /* synthetic */ void zzc(zzbl zzbl) {
        zzbl.zzd &= -9;
        zzbl.zzh = -1;
    }

    static /* synthetic */ void zzd(zzbl zzbl, long j) {
        zzbl.zzd |= 8;
        zzbl.zzh = j;
    }

    static /* synthetic */ void zze(zzbl zzbl, long j) {
        zzbl.zzd |= 32;
        zzbl.zzj = j;
    }

    static /* synthetic */ void zzf(zzbl zzbl, long j) {
        zzbl.zzd |= 4096;
        zzbl.zzq = j;
    }

    static /* synthetic */ void zzg(zzbl zzbl, long j) {
        zzbl.zzd |= 512;
        zzbl.zzn = j;
    }

    static /* synthetic */ void zzh(zzbl zzbl, long j) {
        zzbl.zzd |= 2048;
        zzbl.zzp = j;
    }

    static /* synthetic */ void zzi(zzbl zzbl, long j) {
        zzbl.zzd |= 4;
        zzbl.zzg = j;
    }

    static /* synthetic */ void zzj(zzbl zzbl, long j) {
        zzbl.zzd |= 16;
        zzbl.zzi = j;
    }

    static /* synthetic */ void zzk(zzbl zzbl, long j) {
        zzbl.zzd |= 128;
        zzbl.zzl = j;
    }

    static /* synthetic */ void zzl(zzbl zzbl, long j) {
        zzbl.zzd |= 131072;
        zzbl.zzv = j;
    }

    static /* synthetic */ void zzn(zzbl zzbl, long j) {
        zzbl.zzd |= 1;
        zzbl.zze = j;
    }

    static /* synthetic */ void zzo(zzbl zzbl, long j) {
        zzbl.zzd |= 262144;
        zzbl.zzw = j;
    }

    static /* synthetic */ void zzp(zzbl zzbl, long j) {
        zzbl.zzd |= 2;
        zzbl.zzf = j;
    }

    static /* synthetic */ void zzq(zzbl zzbl, long j) {
        zzbl.zzd |= 256;
        zzbl.zzm = j;
    }

    static /* synthetic */ void zzr(zzbl zzbl, long j) {
        zzbl.zzd |= 32768;
        zzbl.zzt = j;
    }

    static /* synthetic */ void zzs(zzbl zzbl, long j) {
        zzbl.zzd |= 65536;
        zzbl.zzu = j;
    }

    static /* synthetic */ void zzt(zzbl zzbl, long j) {
        zzbl.zzd |= 8192;
        zzbl.zzr = j;
    }

    static /* synthetic */ void zzu(zzbl zzbl, long j) {
        zzbl.zzd |= 16384;
        zzbl.zzs = j;
    }

    static /* synthetic */ void zzv(zzbl zzbl, int i) {
        zzbl.zzo = i - 1;
        zzbl.zzd |= 1024;
    }

    static /* synthetic */ void zzw(zzbl zzbl, int i) {
        zzbl.zzk = i - 1;
        zzbl.zzd |= 64;
    }

    /* access modifiers changed from: protected */
    public final Object zzm(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            zzaeb zzaeb = zzbx.zza;
            return zzaJ(zzb, "\u0001\u0015\u0000\u0001\u0001\u0015\u0015\u0000\u0000\u0000\u0001ဂ\u0000\u0002ဂ\u0001\u0003ဂ\u0002\u0004ဂ\u0003\u0005ဂ\u0004\u0006ဂ\u0005\u0007᠌\u0006\bဂ\u0007\tဂ\b\nဂ\t\u000b᠌\n\fဂ\u000b\rဂ\f\u000eဂ\r\u000fဂ\u000e\u0010ဂ\u000f\u0011ဂ\u0010\u0012ဂ\u0011\u0013ဂ\u0012\u0014ဂ\u0013\u0015ဂ\u0014", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", zzaeb, "zzl", "zzm", "zzn", "zzo", zzaeb, "zzp", "zzq", "zzr", "zzs", "zzt", "zzu", "zzv", "zzw", "zzx", "zzy"});
        } else if (i2 == 3) {
            return new zzbl();
        } else {
            if (i2 == 4) {
                return new zzbk((zzbj) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzb;
        }
    }
}
