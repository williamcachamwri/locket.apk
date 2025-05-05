package com.google.android.gms.internal.pal;

@Deprecated
/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzvw extends zzacz implements zzaeg {
    /* access modifiers changed from: private */
    public static final zzvw zzb;
    private String zze = "";
    private String zzf = "";
    private int zzg;
    private boolean zzh;
    private String zzi = "";

    static {
        zzvw zzvw = new zzvw();
        zzb = zzvw;
        zzacz.zzaF(zzvw.class, zzvw);
    }

    private zzvw() {
    }

    public final int zza() {
        return this.zzg;
    }

    /* access modifiers changed from: protected */
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzaE(zzb, "\u0000\u0005\u0000\u0000\u0001\u0005\u0005\u0000\u0000\u0000\u0001Ȉ\u0002Ȉ\u0003\u000b\u0004\u0007\u0005Ȉ", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi"});
        } else if (i2 == 3) {
            return new zzvw();
        } else {
            if (i2 == 4) {
                return new zzvv((zzvu) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzb;
        }
    }

    public final String zzd() {
        return this.zzi;
    }

    public final String zze() {
        return this.zze;
    }

    public final String zzf() {
        return this.zzf;
    }

    public final boolean zzg() {
        return this.zzh;
    }
}
