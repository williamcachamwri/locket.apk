package com.google.ads.interactivemedia.v3.internal;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzm extends zzady implements zzafc {
    /* access modifiers changed from: private */
    public static final zzm zzb;
    private int zzd;
    private int zze;
    private boolean zzf = true;
    private String zzg = "unknown_host";
    private boolean zzh;
    private boolean zzi = true;
    private zzai zzj;
    private zzal zzk;

    static {
        zzm zzm = new zzm();
        zzb = zzm;
        zzady.zzaM(zzm.class, zzm);
    }

    private zzm() {
    }

    public static zzl zza() {
        return (zzl) zzb.zzay();
    }

    static /* synthetic */ void zzf(zzm zzm, zzai zzai) {
        zzai.getClass();
        zzm.zzj = zzai;
        zzm.zzd |= 32;
    }

    static /* synthetic */ void zzg(zzm zzm, boolean z) {
        zzm.zzd |= 8;
        zzm.zzh = false;
    }

    static /* synthetic */ void zzh(zzm zzm, boolean z) {
        zzm.zzd |= 16;
        zzm.zzi = false;
    }

    static /* synthetic */ void zzi(zzm zzm, String str) {
        zzm.zzd |= 4;
        zzm.zzg = "a.3.35.1";
    }

    static /* synthetic */ void zzo(zzm zzm, int i) {
        zzm.zze = 2;
        zzm.zzd |= 1;
    }

    public final zzai zzc() {
        zzai zzai = this.zzj;
        return zzai == null ? zzai.zzd() : zzai;
    }

    public final zzal zzd() {
        zzal zzal = this.zzk;
        return zzal == null ? zzal.zzb() : zzal;
    }

    public final String zze() {
        return this.zzg;
    }

    public final boolean zzj() {
        return this.zzh;
    }

    public final boolean zzk() {
        return this.zzi;
    }

    public final boolean zzl() {
        return this.zzf;
    }

    /* access modifiers changed from: protected */
    public final Object zzm(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzaJ(zzb, "\u0004\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0000\u0000\u0001᠌\u0000\u0002ဇ\u0001\u0003ဈ\u0002\u0004ဇ\u0003\u0005ဇ\u0004\u0006ဉ\u0005\u0007ဉ\u0006", new Object[]{"zzd", "zze", zzn.zza, "zzf", "zzg", "zzh", "zzi", "zzj", "zzk"});
        } else if (i2 == 3) {
            return new zzm();
        } else {
            if (i2 == 4) {
                return new zzl((zzk) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzb;
        }
    }

    public final int zzn() {
        int zza = zzo.zza(this.zze);
        if (zza == 0) {
            return 1;
        }
        return zza;
    }
}
