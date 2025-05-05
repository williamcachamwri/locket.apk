package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzi extends zzacz implements zzaeg {
    /* access modifiers changed from: private */
    public static final zzi zzb;
    private int zze;
    private zzk zzf;
    private zzp zzg;

    static {
        zzi zzi = new zzi();
        zzb = zzi;
        zzacz.zzaF(zzi.class, zzi);
    }

    private zzi() {
    }

    public static zzi zzc(byte[] bArr, zzacm zzacm) throws zzadi {
        return (zzi) zzacz.zzax(zzb, bArr, zzacm);
    }

    /* access modifiers changed from: protected */
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzaE(zzb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001", new Object[]{"zze", "zzf", "zzg"});
        } else if (i2 == 3) {
            return new zzi();
        } else {
            if (i2 == 4) {
                return new zzh((zzg) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzb;
        }
    }

    public final zzk zzd() {
        zzk zzk = this.zzf;
        return zzk == null ? zzk.zzc() : zzk;
    }

    public final zzp zze() {
        zzp zzp = this.zzg;
        return zzp == null ? zzp.zzc() : zzp;
    }

    public final boolean zzf() {
        return (this.zze & 1) != 0;
    }

    public final boolean zzg() {
        return (this.zze & 2) != 0;
    }
}
