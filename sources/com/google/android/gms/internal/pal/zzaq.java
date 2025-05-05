package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzaq extends zzacz implements zzaeg {
    /* access modifiers changed from: private */
    public static final zzaq zzb;
    private int zze;
    private long zzf;
    private String zzg = "";
    private zzaby zzh = zzaby.zzb;

    static {
        zzaq zzaq = new zzaq();
        zzb = zzaq;
        zzacz.zzaF(zzaq.class, zzaq);
    }

    private zzaq() {
    }

    public static zzaq zzd() {
        return zzb;
    }

    public final long zza() {
        return this.zzf;
    }

    /* access modifiers changed from: protected */
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzaE(zzb, "\u0001\u0003\u0000\u0001\u0001\u0004\u0003\u0000\u0000\u0000\u0001ဂ\u0000\u0003ဈ\u0001\u0004ည\u0002", new Object[]{"zze", "zzf", "zzg", "zzh"});
        } else if (i2 == 3) {
            return new zzaq();
        } else {
            if (i2 == 4) {
                return new zzap((zzq) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzb;
        }
    }

    public final boolean zze() {
        return (this.zze & 1) != 0;
    }
}
