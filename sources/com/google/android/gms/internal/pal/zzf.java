package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzf extends zzacz implements zzaeg {
    /* access modifiers changed from: private */
    public static final zzf zzb;
    private int zze;
    private String zzf = "";
    private long zzg;
    private String zzh = "";
    private String zzi = "";
    private String zzj = "";
    private long zzk;
    private long zzl;
    private String zzm = "";
    private long zzn;
    private String zzo = "";
    private String zzp = "";
    private zzadf zzq = zzaz();
    private int zzr;

    static {
        zzf zzf2 = new zzf();
        zzb = zzf2;
        zzacz.zzaF(zzf.class, zzf2);
    }

    private zzf() {
    }

    public static zzb zza() {
        return (zzb) zzb.zzau();
    }

    static /* synthetic */ void zzd(zzf zzf2, long j) {
        zzf2.zze |= 2;
        zzf2.zzg = j;
    }

    static /* synthetic */ void zze(zzf zzf2, String str) {
        str.getClass();
        zzf2.zze |= 4;
        zzf2.zzh = str;
    }

    static /* synthetic */ void zzf(zzf zzf2, String str) {
        str.getClass();
        zzf2.zze |= 8;
        zzf2.zzi = str;
    }

    static /* synthetic */ void zzg(zzf zzf2, String str) {
        zzf2.zze |= 16;
        zzf2.zzj = str;
    }

    static /* synthetic */ void zzh(zzf zzf2, String str) {
        str.getClass();
        zzf2.zze |= 1;
        zzf2.zzf = str;
    }

    /* access modifiers changed from: protected */
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzaE(zzb, "\u0001\r\u0000\u0001\u0001\r\r\u0000\u0001\u0000\u0001ဈ\u0000\u0002ဂ\u0001\u0003ဈ\u0002\u0004ဈ\u0003\u0005ဈ\u0004\u0006ဂ\u0005\u0007ဂ\u0006\bဈ\u0007\tဂ\b\nဈ\t\u000bဈ\n\f\u001b\rဌ\u000b", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", "zzl", "zzm", "zzn", "zzo", "zzp", "zzq", zzd.class, "zzr", zze.zza});
        } else if (i2 == 3) {
            return new zzf();
        } else {
            if (i2 == 4) {
                return new zzb((zza) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzb;
        }
    }
}
