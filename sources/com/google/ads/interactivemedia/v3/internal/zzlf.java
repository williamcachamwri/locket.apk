package com.google.ads.interactivemedia.v3.internal;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzlf extends zzady implements zzafc {
    /* access modifiers changed from: private */
    public static final zzlf zzb;
    private int zzd;
    private String zze = "";
    private String zzf = "";
    private long zzg;
    private long zzh;
    private long zzi;

    static {
        zzlf zzlf = new zzlf();
        zzb = zzlf;
        zzady.zzaM(zzlf.class, zzlf);
    }

    private zzlf() {
    }

    public static zzle zzd() {
        return (zzle) zzb.zzay();
    }

    public static zzlf zzf() {
        return zzb;
    }

    public static zzlf zzg(zzacw zzacw) throws zzaeg {
        return (zzlf) zzady.zzaC(zzb, zzacw);
    }

    public static zzlf zzh(zzacw zzacw, zzadk zzadk) throws zzaeg {
        return (zzlf) zzady.zzaD(zzb, zzacw, zzadk);
    }

    static /* synthetic */ void zzk(zzlf zzlf, String str) {
        str.getClass();
        zzlf.zzd |= 2;
        zzlf.zzf = str;
    }

    static /* synthetic */ void zzl(zzlf zzlf, long j) {
        zzlf.zzd |= 8;
        zzlf.zzh = j;
    }

    static /* synthetic */ void zzn(zzlf zzlf, long j) {
        zzlf.zzd |= 4;
        zzlf.zzg = j;
    }

    static /* synthetic */ void zzo(zzlf zzlf, long j) {
        zzlf.zzd |= 16;
        zzlf.zzi = j;
    }

    static /* synthetic */ void zzp(zzlf zzlf, String str) {
        str.getClass();
        zzlf.zzd |= 1;
        zzlf.zze = str;
    }

    public final long zza() {
        return this.zzh;
    }

    public final long zzb() {
        return this.zzg;
    }

    public final long zzc() {
        return this.zzi;
    }

    public final String zzi() {
        return this.zzf;
    }

    public final String zzj() {
        return this.zze;
    }

    /* access modifiers changed from: protected */
    public final Object zzm(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzaJ(zzb, "\u0004\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဃ\u0002\u0004ဃ\u0003\u0005ဃ\u0004", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh", "zzi"});
        } else if (i2 == 3) {
            return new zzlf();
        } else {
            if (i2 == 4) {
                return new zzle((zzld) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzb;
        }
    }
}
