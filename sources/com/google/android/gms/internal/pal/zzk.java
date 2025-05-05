package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzk extends zzacz implements zzaeg {
    /* access modifiers changed from: private */
    public static final zzk zzb;
    private int zze;
    private int zzf = 2;

    static {
        zzk zzk = new zzk();
        zzb = zzk;
        zzacz.zzaF(zzk.class, zzk);
    }

    private zzk() {
    }

    public static zzk zzc() {
        return zzb;
    }

    /* access modifiers changed from: protected */
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzaE(zzb, "\u0001\u0001\u0000\u0001\u001b\u001b\u0001\u0000\u0000\u0000\u001bဌ\u0000", new Object[]{"zze", "zzf", zzm.zza});
        } else if (i2 == 3) {
            return new zzk();
        } else {
            if (i2 == 4) {
                return new zzj((zzg) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzb;
        }
    }

    public final int zzd() {
        int zza = zzn.zza(this.zzf);
        if (zza == 0) {
            return 3;
        }
        return zza;
    }
}
