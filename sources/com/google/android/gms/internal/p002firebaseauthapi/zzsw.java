package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzajy;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzsw  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzsw extends zzajy<zzsw, zza> implements zzale {
    /* access modifiers changed from: private */
    public static final zzsw zzc;
    private static volatile zzalp<zzsw> zzd;
    private int zze;
    private zzaip zzf = zzaip.zza;

    public final int zza() {
        return this.zze;
    }

    public static zza zzb() {
        return (zza) zzc.zzm();
    }

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzsw$zza */
    /* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
    public static final class zza extends zzajy.zza<zzsw, zza> implements zzale {
        public final zza zza(zzaip zzaip) {
            zzh();
            zzsw.zza((zzsw) this.zza, zzaip);
            return this;
        }

        private zza() {
            super(zzsw.zzc);
        }
    }

    public static zzsw zza(zzaip zzaip, zzajk zzajk) throws zzakf {
        return (zzsw) zzajy.zza(zzc, zzaip, zzajk);
    }

    public final zzaip zzd() {
        return this.zzf;
    }

    public static zzalp<zzsw> zze() {
        return (zzalp) zzc.zza(zzajy.zzf.zzg, (Object) null, (Object) null);
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzsy.zza[i - 1]) {
            case 1:
                return new zzsw();
            case 2:
                return new zza();
            case 3:
                return zza((zzalc) zzc, "\u0000\u0002\u0000\u0000\u0001\u0003\u0002\u0000\u0000\u0000\u0001\u000b\u0003\n", new Object[]{"zze", "zzf"});
            case 4:
                return zzc;
            case 5:
                zzalp<zzsw> zzalp = zzd;
                if (zzalp == null) {
                    synchronized (zzsw.class) {
                        zzalp = zzd;
                        if (zzalp == null) {
                            zzalp = new zzajy.zzc<>(zzc);
                            zzd = zzalp;
                        }
                    }
                }
                return zzalp;
            case 6:
                return (byte) 1;
            case 7:
                return null;
            default:
                throw new UnsupportedOperationException();
        }
    }

    static /* synthetic */ void zza(zzsw zzsw, zzaip zzaip) {
        zzaip.getClass();
        zzsw.zzf = zzaip;
    }

    static {
        zzsw zzsw = new zzsw();
        zzc = zzsw;
        zzajy.zza(zzsw.class, zzsw);
    }

    private zzsw() {
    }
}
