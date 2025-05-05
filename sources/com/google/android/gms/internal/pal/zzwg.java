package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzwg extends zzacz implements zzaeg {
    /* access modifiers changed from: private */
    public static final zzwg zzb;
    /* access modifiers changed from: private */
    public int zze;
    private zzadf zzf = zzaz();

    static {
        zzwg zzwg = new zzwg();
        zzb = zzwg;
        zzacz.zzaF(zzwg.class, zzwg);
    }

    private zzwg() {
    }

    public static zzwd zza() {
        return (zzwd) zzb.zzau();
    }

    static /* synthetic */ void zze(zzwg zzwg, zzwf zzwf) {
        zzwf.getClass();
        zzadf zzadf = zzwg.zzf;
        if (!zzadf.zzc()) {
            zzwg.zzf = zzacz.zzaA(zzadf);
        }
        zzwg.zzf.add(zzwf);
    }

    /* access modifiers changed from: protected */
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzaE(zzb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u000b\u0002\u001b", new Object[]{"zze", "zzf", zzwf.class});
        } else if (i2 == 3) {
            return new zzwg();
        } else {
            if (i2 == 4) {
                return new zzwd((zzwc) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzb;
        }
    }
}
