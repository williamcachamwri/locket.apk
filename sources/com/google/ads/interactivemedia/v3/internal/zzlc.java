package com.google.ads.interactivemedia.v3.internal;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzlc extends zzady implements zzafc {
    /* access modifiers changed from: private */
    public static final zzlc zzb;
    private int zzd;
    private zzlf zze;
    private zzacw zzf = zzacw.zzb;
    private zzacw zzg = zzacw.zzb;

    static {
        zzlc zzlc = new zzlc();
        zzb = zzlc;
        zzady.zzaM(zzlc.class, zzlc);
    }

    private zzlc() {
    }

    public static zzlc zzb(zzacw zzacw, zzadk zzadk) throws zzaeg {
        return (zzlc) zzady.zzaD(zzb, zzacw, zzadk);
    }

    public final zzlf zzc() {
        zzlf zzlf = this.zze;
        return zzlf == null ? zzlf.zzf() : zzlf;
    }

    public final zzacw zzd() {
        return this.zzg;
    }

    public final zzacw zze() {
        return this.zzf;
    }

    /* access modifiers changed from: protected */
    public final Object zzm(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzaJ(zzb, "\u0004\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဉ\u0000\u0002ည\u0001\u0003ည\u0002", new Object[]{"zzd", "zze", "zzf", "zzg"});
        } else if (i2 == 3) {
            return new zzlc();
        } else {
            if (i2 == 4) {
                return new zzlb((zzla) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzb;
        }
    }
}
