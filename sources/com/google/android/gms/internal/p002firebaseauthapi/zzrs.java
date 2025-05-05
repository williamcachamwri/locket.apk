package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzajy;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzrs  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzrs extends zzajy<zzrs, zza> implements zzale {
    /* access modifiers changed from: private */
    public static final zzrs zzc;
    private static volatile zzalp<zzrs> zzd;
    private int zze;
    /* access modifiers changed from: private */
    public int zzf;
    private zzrv zzg;

    public final int zza() {
        return this.zzf;
    }

    public static zza zzb() {
        return (zza) zzc.zzm();
    }

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzrs$zza */
    /* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
    public static final class zza extends zzajy.zza<zzrs, zza> implements zzale {
        public final zza zza(int i) {
            zzh();
            ((zzrs) this.zza).zzf = i;
            return this;
        }

        public final zza zza(zzrv zzrv) {
            zzh();
            zzrs.zza((zzrs) this.zza, zzrv);
            return this;
        }

        private zza() {
            super(zzrs.zzc);
        }
    }

    public static zzrs zza(zzaip zzaip, zzajk zzajk) throws zzakf {
        return (zzrs) zzajy.zza(zzc, zzaip, zzajk);
    }

    public final zzrv zzd() {
        zzrv zzrv = this.zzg;
        return zzrv == null ? zzrv.zzd() : zzrv;
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzru.zza[i - 1]) {
            case 1:
                return new zzrs();
            case 2:
                return new zza();
            case 3:
                return zza((zzalc) zzc, "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u000b\u0002ဉ\u0000", new Object[]{"zze", "zzf", "zzg"});
            case 4:
                return zzc;
            case 5:
                zzalp<zzrs> zzalp = zzd;
                if (zzalp == null) {
                    synchronized (zzrs.class) {
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

    static /* synthetic */ void zza(zzrs zzrs, zzrv zzrv) {
        zzrv.getClass();
        zzrs.zzg = zzrv;
        zzrs.zze |= 1;
    }

    static {
        zzrs zzrs = new zzrs();
        zzc = zzrs;
        zzajy.zza(zzrs.class, zzrs);
    }

    private zzrs() {
    }
}
