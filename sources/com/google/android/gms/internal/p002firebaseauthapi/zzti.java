package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzajy;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzti  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzti extends zzajy<zzti, zza> implements zzale {
    /* access modifiers changed from: private */
    public static final zzti zzc;
    private static volatile zzalp<zzti> zzd;
    private int zze;
    private zzaip zzf = zzaip.zza;

    public final int zza() {
        return this.zze;
    }

    public static zza zzb() {
        return (zza) zzc.zzm();
    }

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzti$zza */
    /* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
    public static final class zza extends zzajy.zza<zzti, zza> implements zzale {
        public final zza zza(zzaip zzaip) {
            zzh();
            zzti.zza((zzti) this.zza, zzaip);
            return this;
        }

        private zza() {
            super(zzti.zzc);
        }
    }

    public static zzti zza(zzaip zzaip, zzajk zzajk) throws zzakf {
        return (zzti) zzajy.zza(zzc, zzaip, zzajk);
    }

    public final zzaip zzd() {
        return this.zzf;
    }

    public static zzalp<zzti> zze() {
        return (zzalp) zzc.zza(zzajy.zzf.zzg, (Object) null, (Object) null);
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zztk.zza[i - 1]) {
            case 1:
                return new zzti();
            case 2:
                return new zza();
            case 3:
                return zza((zzalc) zzc, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u000b\u0002\n", new Object[]{"zze", "zzf"});
            case 4:
                return zzc;
            case 5:
                zzalp<zzti> zzalp = zzd;
                if (zzalp == null) {
                    synchronized (zzti.class) {
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

    static /* synthetic */ void zza(zzti zzti, zzaip zzaip) {
        zzaip.getClass();
        zzti.zzf = zzaip;
    }

    static {
        zzti zzti = new zzti();
        zzc = zzti;
        zzajy.zza(zzti.class, zzti);
    }

    private zzti() {
    }
}
