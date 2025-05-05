package com.google.android.recaptcha.internal;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zztk extends zznf implements zzol {
    /* access modifiers changed from: private */
    public static final zztk zzb;
    private static volatile zzos zzd;
    private zznm zze = zzB();
    /* access modifiers changed from: private */
    public int zzf;

    static {
        zztk zztk = new zztk();
        zzb = zztk;
        zznf.zzI(zztk.class, zztk);
    }

    private zztk() {
    }

    public static zzth zzf() {
        return (zzth) zzb.zzq();
    }

    static /* synthetic */ void zzi(zztk zztk, Iterable iterable) {
        zztk.zzl();
        zzkq.zzc(iterable, zztk.zze);
    }

    static /* synthetic */ void zzj(zztk zztk, zztj zztj) {
        zztj.getClass();
        zztk.zzl();
        zztk.zze.add(zztj);
    }

    private final void zzl() {
        zznm zznm = this.zze;
        if (!zznm.zzc()) {
            this.zze = zznf.zzC(zznm);
        }
    }

    /* access modifiers changed from: protected */
    public final Object zzh(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u001b\u0002\u000b", new Object[]{"zze", zztj.class, "zzf"});
        } else if (i2 == 3) {
            return new zztk();
        } else {
            if (i2 == 4) {
                return new zzth((zztl) null);
            }
            if (i2 == 5) {
                return zzb;
            }
            if (i2 != 6) {
                return null;
            }
            zzos zzos = zzd;
            if (zzos == null) {
                synchronized (zztk.class) {
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
