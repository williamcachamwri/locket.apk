package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzajy;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzxh  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzxh extends zzajy<zzxh, zza> implements zzale {
    /* access modifiers changed from: private */
    public static final zzxh zzc;
    private static volatile zzalp<zzxh> zzd;
    private int zze;
    private zzaip zzf = zzaip.zza;

    public final int zza() {
        return this.zze;
    }

    public static zza zzb() {
        return (zza) zzc.zzm();
    }

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzxh$zza */
    /* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
    public static final class zza extends zzajy.zza<zzxh, zza> implements zzale {
        public final zza zza(zzaip zzaip) {
            zzh();
            zzxh.zza((zzxh) this.zza, zzaip);
            return this;
        }

        private zza() {
            super(zzxh.zzc);
        }
    }

    public static zzxh zza(zzaip zzaip, zzajk zzajk) throws zzakf {
        return (zzxh) zzajy.zza(zzc, zzaip, zzajk);
    }

    public final zzaip zzd() {
        return this.zzf;
    }

    public static zzalp<zzxh> zze() {
        return (zzalp) zzc.zza(zzajy.zzf.zzg, (Object) null, (Object) null);
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzxg.zza[i - 1]) {
            case 1:
                return new zzxh();
            case 2:
                return new zza();
            case 3:
                return zza((zzalc) zzc, "\u0000\u0002\u0000\u0000\u0001\u0003\u0002\u0000\u0000\u0000\u0001\u000b\u0003\n", new Object[]{"zze", "zzf"});
            case 4:
                return zzc;
            case 5:
                zzalp<zzxh> zzalp = zzd;
                if (zzalp == null) {
                    synchronized (zzxh.class) {
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

    static /* synthetic */ void zza(zzxh zzxh, zzaip zzaip) {
        zzaip.getClass();
        zzxh.zzf = zzaip;
    }

    static {
        zzxh zzxh = new zzxh();
        zzc = zzxh;
        zzajy.zza(zzxh.class, zzxh);
    }

    private zzxh() {
    }
}
