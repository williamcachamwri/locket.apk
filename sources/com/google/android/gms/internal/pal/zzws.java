package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzws extends zzacz implements zzaeg {
    /* access modifiers changed from: private */
    public static final zzws zzb;
    private String zze = "";
    private zzvt zzf;

    static {
        zzws zzws = new zzws();
        zzb = zzws;
        zzacz.zzaF(zzws.class, zzws);
    }

    private zzws() {
    }

    public static zzws zzd() {
        return zzb;
    }

    public static zzws zze(zzaby zzaby, zzacm zzacm) throws zzadi {
        return (zzws) zzacz.zzaw(zzb, zzaby, zzacm);
    }

    public final zzvt zza() {
        zzvt zzvt = this.zzf;
        return zzvt == null ? zzvt.zzd() : zzvt;
    }

    /* access modifiers changed from: protected */
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzaE(zzb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001Ȉ\u0002\t", new Object[]{"zze", "zzf"});
        } else if (i2 == 3) {
            return new zzws();
        } else {
            if (i2 == 4) {
                return new zzwr((zzwq) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzb;
        }
    }

    public final String zzf() {
        return this.zze;
    }

    public final boolean zzg() {
        return this.zzf != null;
    }
}
