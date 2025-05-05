package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzajy;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzrp  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzrp extends zzajy<zzrp, zza> implements zzale {
    /* access modifiers changed from: private */
    public static final zzrp zzc;
    private static volatile zzalp<zzrp> zzd;
    private int zze;
    private int zzf;
    private zzaip zzg = zzaip.zza;
    private zzrv zzh;

    public final int zza() {
        return this.zzf;
    }

    public static zza zzb() {
        return (zza) zzc.zzm();
    }

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzrp$zza */
    /* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
    public static final class zza extends zzajy.zza<zzrp, zza> implements zzale {
        public final zza zza(zzaip zzaip) {
            zzh();
            zzrp.zza((zzrp) this.zza, zzaip);
            return this;
        }

        public final zza zza(zzrv zzrv) {
            zzh();
            zzrp.zza((zzrp) this.zza, zzrv);
            return this;
        }

        private zza() {
            super(zzrp.zzc);
        }
    }

    public static zzrp zza(zzaip zzaip, zzajk zzajk) throws zzakf {
        return (zzrp) zzajy.zza(zzc, zzaip, zzajk);
    }

    public final zzrv zzd() {
        zzrv zzrv = this.zzh;
        return zzrv == null ? zzrv.zzd() : zzrv;
    }

    public final zzaip zze() {
        return this.zzg;
    }

    public static zzalp<zzrp> zzf() {
        return (zzalp) zzc.zza(zzajy.zzf.zzg, (Object) null, (Object) null);
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzrr.zza[i - 1]) {
            case 1:
                return new zzrp();
            case 2:
                return new zza();
            case 3:
                return zza((zzalc) zzc, "\u0000\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000b\u0002\n\u0003ဉ\u0000", new Object[]{"zze", "zzf", "zzg", "zzh"});
            case 4:
                return zzc;
            case 5:
                zzalp<zzrp> zzalp = zzd;
                if (zzalp == null) {
                    synchronized (zzrp.class) {
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

    static /* synthetic */ void zza(zzrp zzrp, zzaip zzaip) {
        zzaip.getClass();
        zzrp.zzg = zzaip;
    }

    static /* synthetic */ void zza(zzrp zzrp, zzrv zzrv) {
        zzrv.getClass();
        zzrp.zzh = zzrv;
        zzrp.zze |= 1;
    }

    static {
        zzrp zzrp = new zzrp();
        zzc = zzrp;
        zzajy.zza(zzrp.class, zzrp);
    }

    private zzrp() {
    }
}
