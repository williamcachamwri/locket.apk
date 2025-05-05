package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzvt extends zzacz implements zzaeg {
    /* access modifiers changed from: private */
    public static final zzvt zzb;
    private String zze = "";
    /* access modifiers changed from: private */
    public zzaby zzf = zzaby.zzb;
    /* access modifiers changed from: private */
    public int zzg;

    static {
        zzvt zzvt = new zzvt();
        zzb = zzvt;
        zzacz.zzaF(zzvt.class, zzvt);
    }

    private zzvt() {
    }

    public static zzvs zza() {
        return (zzvs) zzb.zzau();
    }

    public static zzvt zzd() {
        return zzb;
    }

    static /* synthetic */ void zzg(zzvt zzvt, String str) {
        str.getClass();
        zzvt.zze = str;
    }

    /* access modifiers changed from: protected */
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzaE(zzb, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001Ȉ\u0002\n\u0003\f", new Object[]{"zze", "zzf", "zzg"});
        } else if (i2 == 3) {
            return new zzvt();
        } else {
            if (i2 == 4) {
                return new zzvs((zzvr) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzb;
        }
    }

    public final zzaby zze() {
        return this.zzf;
    }

    public final String zzf() {
        return this.zze;
    }

    public final int zzi() {
        int zzb2 = zzwu.zzb(this.zzg);
        if (zzb2 == 0) {
            return 1;
        }
        return zzb2;
    }
}
