package com.google.ads.interactivemedia.v3.internal;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzai extends zzady implements zzafc {
    /* access modifiers changed from: private */
    public static final zzai zzb;
    private int zzd;
    private boolean zze;
    private int zzf = 5000;
    private boolean zzg;
    private boolean zzh;

    static {
        zzai zzai = new zzai();
        zzb = zzai;
        zzady.zzaM(zzai.class, zzai);
    }

    private zzai() {
    }

    public static zzah zzb() {
        return (zzah) zzb.zzay();
    }

    public static zzai zzd() {
        return zzb;
    }

    static /* synthetic */ void zze(zzai zzai, boolean z) {
        zzai.zzd |= 1;
        zzai.zze = true;
    }

    public final int zza() {
        return this.zzf;
    }

    public final boolean zzf() {
        return this.zze;
    }

    public final boolean zzg() {
        return this.zzh;
    }

    public final boolean zzh() {
        return this.zzg;
    }

    /* access modifiers changed from: protected */
    public final Object zzm(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzaJ(zzb, "\u0004\u0004\u0000\u0001\u0001\u0005\u0004\u0000\u0000\u0000\u0001ဇ\u0000\u0003င\u0001\u0004ဇ\u0002\u0005ဇ\u0003", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh"});
        } else if (i2 == 3) {
            return new zzai();
        } else {
            if (i2 == 4) {
                return new zzah((zzag) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzb;
        }
    }
}
