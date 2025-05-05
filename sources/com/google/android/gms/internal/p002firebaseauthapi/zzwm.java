package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzajy;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzwm  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzwm extends zzajy<zzwm, zza> implements zzale {
    /* access modifiers changed from: private */
    public static final zzwm zzc;
    private static volatile zzalp<zzwm> zzd;
    private int zze;
    private int zzf;
    private zzwp zzg;

    public final int zza() {
        return this.zzf;
    }

    public static zza zzb() {
        return (zza) zzc.zzm();
    }

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzwm$zza */
    /* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
    public static final class zza extends zzajy.zza<zzwm, zza> implements zzale {
        public final zza zza(zzwp zzwp) {
            zzh();
            zzwm.zza((zzwm) this.zza, zzwp);
            return this;
        }

        private zza() {
            super(zzwm.zzc);
        }
    }

    public static zzwm zza(zzaip zzaip, zzajk zzajk) throws zzakf {
        return (zzwm) zzajy.zza(zzc, zzaip, zzajk);
    }

    public final zzwp zzd() {
        zzwp zzwp = this.zzg;
        return zzwp == null ? zzwp.zzd() : zzwp;
    }

    public static zzalp<zzwm> zze() {
        return (zzalp) zzc.zza(zzajy.zzf.zzg, (Object) null, (Object) null);
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzwo.zza[i - 1]) {
            case 1:
                return new zzwm();
            case 2:
                return new zza();
            case 3:
                return zza((zzalc) zzc, "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u000b\u0002ဉ\u0000", new Object[]{"zze", "zzf", "zzg"});
            case 4:
                return zzc;
            case 5:
                zzalp<zzwm> zzalp = zzd;
                if (zzalp == null) {
                    synchronized (zzwm.class) {
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

    static /* synthetic */ void zza(zzwm zzwm, zzwp zzwp) {
        zzwp.getClass();
        zzwm.zzg = zzwp;
        zzwm.zze |= 1;
    }

    static {
        zzwm zzwm = new zzwm();
        zzc = zzwm;
        zzajy.zza(zzwm.class, zzwm);
    }

    private zzwm() {
    }
}
