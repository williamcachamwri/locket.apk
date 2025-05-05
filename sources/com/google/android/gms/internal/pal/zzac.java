package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzac extends zzacz implements zzaeg {
    /* access modifiers changed from: private */
    public static final zzac zzb;
    private int zze;
    private long zzf = -1;
    private long zzg = -1;
    private long zzh = -1;
    private long zzi = -1;
    private long zzj = -1;
    private long zzk = -1;
    private int zzl = 1000;
    private long zzm = -1;
    private long zzn = -1;
    private long zzo = -1;
    private int zzp = 1000;
    private long zzq = -1;
    private long zzr = -1;
    private long zzs = -1;
    private long zzt = -1;
    private long zzu;
    private long zzv;
    private long zzw = -1;
    private long zzx = -1;
    private long zzy = -1;
    private long zzz = -1;

    static {
        zzac zzac = new zzac();
        zzb = zzac;
        zzacz.zzaF(zzac.class, zzac);
    }

    private zzac() {
    }

    public static zzab zza() {
        return (zzab) zzb.zzau();
    }

    static /* synthetic */ void zzd(zzac zzac, long j) {
        zzac.zze |= 1;
        zzac.zzf = j;
    }

    static /* synthetic */ void zze(zzac zzac, long j) {
        zzac.zze |= 2;
        zzac.zzg = j;
    }

    static /* synthetic */ void zzf(zzac zzac, long j) {
        zzac.zze |= 4;
        zzac.zzh = j;
    }

    static /* synthetic */ void zzg(zzac zzac, long j) {
        zzac.zze |= 8;
        zzac.zzi = j;
    }

    static /* synthetic */ void zzh(zzac zzac) {
        zzac.zze &= -9;
        zzac.zzi = -1;
    }

    static /* synthetic */ void zzi(zzac zzac, long j) {
        zzac.zze |= 16;
        zzac.zzj = j;
    }

    static /* synthetic */ void zzj(zzac zzac, long j) {
        zzac.zze |= 32;
        zzac.zzk = j;
    }

    static /* synthetic */ void zzk(zzac zzac, long j) {
        zzac.zze |= 128;
        zzac.zzm = j;
    }

    static /* synthetic */ void zzl(zzac zzac, long j) {
        zzac.zze |= 256;
        zzac.zzn = j;
    }

    static /* synthetic */ void zzm(zzac zzac, long j) {
        zzac.zze |= 512;
        zzac.zzo = j;
    }

    static /* synthetic */ void zzn(zzac zzac, long j) {
        zzac.zze |= 2048;
        zzac.zzq = j;
    }

    static /* synthetic */ void zzo(zzac zzac, long j) {
        zzac.zze |= 4096;
        zzac.zzr = j;
    }

    static /* synthetic */ void zzp(zzac zzac, long j) {
        zzac.zze |= 8192;
        zzac.zzs = j;
    }

    static /* synthetic */ void zzq(zzac zzac, long j) {
        zzac.zze |= 16384;
        zzac.zzt = j;
    }

    static /* synthetic */ void zzr(zzac zzac, long j) {
        zzac.zze |= 32768;
        zzac.zzu = j;
    }

    static /* synthetic */ void zzs(zzac zzac, long j) {
        zzac.zze |= 65536;
        zzac.zzv = j;
    }

    static /* synthetic */ void zzt(zzac zzac, long j) {
        zzac.zze |= 131072;
        zzac.zzw = j;
    }

    static /* synthetic */ void zzu(zzac zzac, long j) {
        zzac.zze |= 262144;
        zzac.zzx = j;
    }

    static /* synthetic */ void zzv(zzac zzac, int i) {
        zzac.zzl = i - 1;
        zzac.zze |= 64;
    }

    static /* synthetic */ void zzw(zzac zzac, int i) {
        zzac.zzp = i - 1;
        zzac.zze |= 1024;
    }

    /* access modifiers changed from: protected */
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            zzadd zzadd = zzan.zza;
            return zzaE(zzb, "\u0001\u0015\u0000\u0001\u0001\u0015\u0015\u0000\u0000\u0000\u0001ဂ\u0000\u0002ဂ\u0001\u0003ဂ\u0002\u0004ဂ\u0003\u0005ဂ\u0004\u0006ဂ\u0005\u0007ဌ\u0006\bဂ\u0007\tဂ\b\nဂ\t\u000bဌ\n\fဂ\u000b\rဂ\f\u000eဂ\r\u000fဂ\u000e\u0010ဂ\u000f\u0011ဂ\u0010\u0012ဂ\u0011\u0013ဂ\u0012\u0014ဂ\u0013\u0015ဂ\u0014", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", "zzl", zzadd, "zzm", "zzn", "zzo", "zzp", zzadd, "zzq", "zzr", "zzs", "zzt", "zzu", "zzv", "zzw", "zzx", "zzy", "zzz"});
        } else if (i2 == 3) {
            return new zzac();
        } else {
            if (i2 == 4) {
                return new zzab((zzq) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzb;
        }
    }
}
