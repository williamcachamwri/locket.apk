package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzrm extends zzacz implements zzaeg {
    /* access modifiers changed from: private */
    public static final zzrm zzb;
    /* access modifiers changed from: private */
    public int zze;
    /* access modifiers changed from: private */
    public zzaby zzf = zzaby.zzb;
    private zzrs zzg;

    static {
        zzrm zzrm = new zzrm();
        zzb = zzrm;
        zzacz.zzaF(zzrm.class, zzrm);
    }

    private zzrm() {
    }

    public static zzrl zzc() {
        return (zzrl) zzb.zzau();
    }

    public static zzrm zze(zzaby zzaby, zzacm zzacm) throws zzadi {
        return (zzrm) zzacz.zzaw(zzb, zzaby, zzacm);
    }

    static /* synthetic */ void zzj(zzrm zzrm, zzrs zzrs) {
        zzrs.getClass();
        zzrm.zzg = zzrs;
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
            return zzaE(zzb, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000b\u0002\n\u0003\t", new Object[]{"zze", "zzf", "zzg"});
        } else if (i2 == 3) {
            return new zzrm();
        } else {
            if (i2 == 4) {
                return new zzrl((zzrk) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzb;
        }
    }

    public final zzrs zzf() {
        zzrs zzrs = this.zzg;
        return zzrs == null ? zzrs.zze() : zzrs;
    }

    public final zzaby zzg() {
        return this.zzf;
    }
}
