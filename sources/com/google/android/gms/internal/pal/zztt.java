package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zztt extends zzacz implements zzaeg {
    /* access modifiers changed from: private */
    public static final zztt zzb;
    private zzvt zze;

    static {
        zztt zztt = new zztt();
        zzb = zztt;
        zzacz.zzaF(zztt.class, zztt);
    }

    private zztt() {
    }

    public static zzts zza() {
        return (zzts) zzb.zzau();
    }

    public static zztt zzd() {
        return zzb;
    }

    static /* synthetic */ void zzf(zztt zztt, zzvt zzvt) {
        zzvt.getClass();
        zztt.zze = zzvt;
    }

    /* access modifiers changed from: protected */
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzaE(zzb, "\u0000\u0001\u0000\u0000\u0002\u0002\u0001\u0000\u0000\u0000\u0002\t", new Object[]{"zze"});
        } else if (i2 == 3) {
            return new zztt();
        } else {
            if (i2 == 4) {
                return new zzts((zztr) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzb;
        }
    }

    public final zzvt zze() {
        zzvt zzvt = this.zze;
        return zzvt == null ? zzvt.zzd() : zzvt;
    }
}
