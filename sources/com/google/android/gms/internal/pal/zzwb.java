package com.google.android.gms.internal.pal;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzwb extends zzacz implements zzaeg {
    /* access modifiers changed from: private */
    public static final zzwb zzb;
    /* access modifiers changed from: private */
    public int zze;
    private zzadf zzf = zzaz();

    static {
        zzwb zzwb = new zzwb();
        zzb = zzwb;
        zzacz.zzaF(zzwb.class, zzwb);
    }

    private zzwb() {
    }

    public static zzvy zzd() {
        return (zzvy) zzb.zzau();
    }

    public static zzwb zzf(byte[] bArr, zzacm zzacm) throws zzadi {
        return (zzwb) zzacz.zzax(zzb, bArr, zzacm);
    }

    static /* synthetic */ void zzi(zzwb zzwb, zzwa zzwa) {
        zzwa.getClass();
        zzadf zzadf = zzwb.zzf;
        if (!zzadf.zzc()) {
            zzwb.zzf = zzacz.zzaA(zzadf);
        }
        zzwb.zzf.add(zzwa);
    }

    public final int zza() {
        return this.zzf.size();
    }

    /* access modifiers changed from: protected */
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzaE(zzb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u000b\u0002\u001b", new Object[]{"zze", "zzf", zzwa.class});
        } else if (i2 == 3) {
            return new zzwb();
        } else {
            if (i2 == 4) {
                return new zzvy((zzvx) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzb;
        }
    }

    public final int zzc() {
        return this.zze;
    }

    public final List zzg() {
        return this.zzf;
    }
}
