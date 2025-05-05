package com.google.android.recaptcha.internal;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zztb extends zznf implements zzol {
    /* access modifiers changed from: private */
    public static final zztb zzb;
    private static volatile zzos zzd;
    private int zze;
    private String zzf = "";
    private zznm zzg = zzB();

    static {
        zztb zztb = new zztb();
        zzb = zztb;
        zznf.zzI(zztb.class, zztb);
    }

    private zztb() {
    }

    public static zzta zzf() {
        return (zzta) zzb.zzq();
    }

    static /* synthetic */ void zzi(zztb zztb, Iterable iterable) {
        zztb.zzl();
        zzkq.zzc(iterable, zztb.zzg);
    }

    static /* synthetic */ void zzj(zztb zztb, zzsz zzsz) {
        zzsz.getClass();
        zztb.zzl();
        zztb.zzg.add(zzsz);
    }

    static /* synthetic */ void zzk(zztb zztb, String str) {
        str.getClass();
        zztb.zze |= 1;
        zztb.zzf = str;
    }

    private final void zzl() {
        zznm zznm = this.zzg;
        if (!zznm.zzc()) {
            this.zzg = zznf.zzC(zznm);
        }
    }

    /* access modifiers changed from: protected */
    public final Object zzh(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u001b\u0002ဈ\u0000", new Object[]{"zze", "zzg", zzsz.class, "zzf"});
        } else if (i2 == 3) {
            return new zztb();
        } else {
            if (i2 == 4) {
                return new zzta((zztc) null);
            }
            if (i2 == 5) {
                return zzb;
            }
            if (i2 != 6) {
                return null;
            }
            zzos zzos = zzd;
            if (zzos == null) {
                synchronized (zztb.class) {
                    zzos = zzd;
                    if (zzos == null) {
                        zzos = new zzna(zzb);
                        zzd = zzos;
                    }
                }
            }
            return zzos;
        }
    }
}
