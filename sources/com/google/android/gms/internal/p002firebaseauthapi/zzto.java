package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzajy;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzto  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzto extends zzajy<zzto, zza> implements zzale {
    /* access modifiers changed from: private */
    public static final zzto zzc;
    private static volatile zzalp<zzto> zzd;
    private int zze;
    private zzaip zzf = zzaip.zza;

    public final int zza() {
        return this.zze;
    }

    public static zza zzb() {
        return (zza) zzc.zzm();
    }

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzto$zza */
    /* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
    public static final class zza extends zzajy.zza<zzto, zza> implements zzale {
        public final zza zza(zzaip zzaip) {
            zzh();
            zzto.zza((zzto) this.zza, zzaip);
            return this;
        }

        private zza() {
            super(zzto.zzc);
        }
    }

    public static zzto zza(zzaip zzaip, zzajk zzajk) throws zzakf {
        return (zzto) zzajy.zza(zzc, zzaip, zzajk);
    }

    public final zzaip zzd() {
        return this.zzf;
    }

    public static zzalp<zzto> zze() {
        return (zzalp) zzc.zza(zzajy.zzf.zzg, (Object) null, (Object) null);
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zztq.zza[i - 1]) {
            case 1:
                return new zzto();
            case 2:
                return new zza();
            case 3:
                return zza((zzalc) zzc, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u000b\u0002\n", new Object[]{"zze", "zzf"});
            case 4:
                return zzc;
            case 5:
                zzalp<zzto> zzalp = zzd;
                if (zzalp == null) {
                    synchronized (zzto.class) {
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

    static /* synthetic */ void zza(zzto zzto, zzaip zzaip) {
        zzaip.getClass();
        zzto.zzf = zzaip;
    }

    static {
        zzto zzto = new zzto();
        zzc = zzto;
        zzajy.zza(zzto.class, zzto);
    }

    private zzto() {
    }
}
