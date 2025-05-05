package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzvo extends zzacz implements zzaeg {
    /* access modifiers changed from: private */
    public static final zzvo zzb;
    private String zze = "";
    /* access modifiers changed from: private */
    public zzaby zzf = zzaby.zzb;
    /* access modifiers changed from: private */
    public int zzg;

    static {
        zzvo zzvo = new zzvo();
        zzb = zzvo;
        zzacz.zzaF(zzvo.class, zzvo);
    }

    private zzvo() {
    }

    public static zzvl zza() {
        return (zzvl) zzb.zzau();
    }

    public static zzvo zze() {
        return zzb;
    }

    static /* synthetic */ void zzh(zzvo zzvo, String str) {
        str.getClass();
        zzvo.zze = str;
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
            return new zzvo();
        } else {
            if (i2 == 4) {
                return new zzvl((zzvk) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzb;
        }
    }

    public final zzvn zzc() {
        zzvn zzb2 = zzvn.zzb(this.zzg);
        return zzb2 == null ? zzvn.UNRECOGNIZED : zzb2;
    }

    public final zzaby zzf() {
        return this.zzf;
    }

    public final String zzg() {
        return this.zze;
    }
}
