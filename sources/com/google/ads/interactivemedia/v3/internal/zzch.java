package com.google.ads.interactivemedia.v3.internal;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzch extends zzady implements zzafc {
    /* access modifiers changed from: private */
    public static final zzch zzb;
    private int zzd;
    private zzaed zze = zzaG();
    private zzacw zzf = zzacw.zzb;
    private int zzg = 1;
    private int zzh = 1;

    static {
        zzch zzch = new zzch();
        zzb = zzch;
        zzady.zzaM(zzch.class, zzch);
    }

    private zzch() {
    }

    public static zzcg zza() {
        return (zzcg) zzb.zzay();
    }

    static /* synthetic */ void zzc(zzch zzch, zzacw zzacw) {
        zzaed zzaed = zzch.zze;
        if (!zzaed.zzc()) {
            zzch.zze = zzady.zzaH(zzaed);
        }
        zzch.zze.add(zzacw);
    }

    static /* synthetic */ void zzd(zzch zzch, zzacw zzacw) {
        zzch.zzd |= 1;
        zzch.zzf = zzacw;
    }

    static /* synthetic */ void zze(zzch zzch, int i) {
        zzch.zzh = 4;
        zzch.zzd = 4 | zzch.zzd;
    }

    /* access modifiers changed from: protected */
    public final Object zzm(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzaJ(zzb, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0001\u0000\u0001\u001c\u0002ည\u0000\u0003᠌\u0001\u0004᠌\u0002", new Object[]{"zzd", "zze", "zzf", "zzg", zzby.zza, "zzh", zzbw.zza});
        } else if (i2 == 3) {
            return new zzch();
        } else {
            if (i2 == 4) {
                return new zzcg((zzcf) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzb;
        }
    }
}
