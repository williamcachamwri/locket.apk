package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzrp extends zzacz implements zzaeg {
    /* access modifiers changed from: private */
    public static final zzrp zzb;
    /* access modifiers changed from: private */
    public int zze;
    private zzrs zzf;

    static {
        zzrp zzrp = new zzrp();
        zzb = zzrp;
        zzacz.zzaF(zzrp.class, zzrp);
    }

    private zzrp() {
    }

    public static zzro zzc() {
        return (zzro) zzb.zzau();
    }

    public static zzrp zze(zzaby zzaby, zzacm zzacm) throws zzadi {
        return (zzrp) zzacz.zzaw(zzb, zzaby, zzacm);
    }

    static /* synthetic */ void zzh(zzrp zzrp, zzrs zzrs) {
        zzrs.getClass();
        zzrp.zzf = zzrs;
    }

    public final int zza() {
        return this.zze;
    }

    /* access modifiers changed from: protected */
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzaE(zzb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u000b\u0002\t", new Object[]{"zze", "zzf"});
        } else if (i2 == 3) {
            return new zzrp();
        } else {
            if (i2 == 4) {
                return new zzro((zzrn) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzb;
        }
    }

    public final zzrs zzf() {
        zzrs zzrs = this.zzf;
        return zzrs == null ? zzrs.zze() : zzrs;
    }
}
