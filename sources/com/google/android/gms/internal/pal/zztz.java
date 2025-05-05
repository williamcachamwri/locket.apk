package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zztz extends zzacz implements zzaeg {
    /* access modifiers changed from: private */
    public static final zztz zzb;
    private zzui zze;
    private zztt zzf;
    /* access modifiers changed from: private */
    public int zzg;

    static {
        zztz zztz = new zztz();
        zzb = zztz;
        zzacz.zzaF(zztz.class, zztz);
    }

    private zztz() {
    }

    public static zzty zzc() {
        return (zzty) zzb.zzau();
    }

    public static zztz zze() {
        return zzb;
    }

    static /* synthetic */ void zzg(zztz zztz, zzui zzui) {
        zzui.getClass();
        zztz.zze = zzui;
    }

    static /* synthetic */ void zzh(zztz zztz, zztt zztt) {
        zztt.getClass();
        zztz.zzf = zztt;
    }

    public final zztt zza() {
        zztt zztt = this.zzf;
        return zztt == null ? zztt.zzd() : zztt;
    }

    /* access modifiers changed from: protected */
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzaE(zzb, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\t\u0002\t\u0003\f", new Object[]{"zze", "zzf", "zzg"});
        } else if (i2 == 3) {
            return new zztz();
        } else {
            if (i2 == 4) {
                return new zzty((zztx) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzb;
        }
    }

    public final zzui zzf() {
        zzui zzui = this.zze;
        return zzui == null ? zzui.zzd() : zzui;
    }

    public final int zzi() {
        int i = this.zzg;
        int i2 = 2;
        if (i != 0) {
            i2 = i != 1 ? i != 2 ? i != 3 ? 0 : 5 : 4 : 3;
        }
        if (i2 == 0) {
            return 1;
        }
        return i2;
    }
}
