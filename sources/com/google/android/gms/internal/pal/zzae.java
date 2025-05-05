package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzae extends zzacz implements zzaeg {
    /* access modifiers changed from: private */
    public static final zzae zzb;
    private int zze;
    private long zzf = -1;
    private long zzg = -1;
    private long zzh = -1;
    private long zzi = -1;
    private long zzj = -1;
    private long zzk = -1;
    private long zzl = -1;
    private long zzm = -1;

    static {
        zzae zzae = new zzae();
        zzb = zzae;
        zzacz.zzaF(zzae.class, zzae);
    }

    private zzae() {
    }

    public static zzad zza() {
        return (zzad) zzb.zzau();
    }

    static /* synthetic */ void zzd(zzae zzae, long j) {
        zzae.zze |= 1;
        zzae.zzf = j;
    }

    static /* synthetic */ void zze(zzae zzae, long j) {
        zzae.zze |= 4;
        zzae.zzh = j;
    }

    static /* synthetic */ void zzf(zzae zzae, long j) {
        zzae.zze |= 8;
        zzae.zzi = j;
    }

    static /* synthetic */ void zzg(zzae zzae, long j) {
        zzae.zze |= 16;
        zzae.zzj = j;
    }

    static /* synthetic */ void zzh(zzae zzae, long j) {
        zzae.zze |= 32;
        zzae.zzk = j;
    }

    /* access modifiers changed from: protected */
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzaE(zzb, "\u0001\b\u0000\u0001\u0001\b\b\u0000\u0000\u0000\u0001ဂ\u0000\u0002ဂ\u0001\u0003ဂ\u0002\u0004ဂ\u0003\u0005ဂ\u0004\u0006ဂ\u0005\u0007ဂ\u0006\bဂ\u0007", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", "zzl", "zzm"});
        } else if (i2 == 3) {
            return new zzae();
        } else {
            if (i2 == 4) {
                return new zzad((zzq) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzb;
        }
    }
}
