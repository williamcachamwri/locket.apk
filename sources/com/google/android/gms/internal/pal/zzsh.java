package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzsh extends zzacz implements zzaeg {
    /* access modifiers changed from: private */
    public static final zzsh zzb;
    /* access modifiers changed from: private */
    public int zze;

    static {
        zzsh zzsh = new zzsh();
        zzb = zzsh;
        zzacz.zzaF(zzsh.class, zzsh);
    }

    private zzsh() {
    }

    public static zzsg zzc() {
        return (zzsg) zzb.zzau();
    }

    public static zzsh zze() {
        return zzb;
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
            return zzaE(zzb, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\u000b", new Object[]{"zze"});
        } else if (i2 == 3) {
            return new zzsh();
        } else {
            if (i2 == 4) {
                return new zzsg((zzsf) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzb;
        }
    }
}
