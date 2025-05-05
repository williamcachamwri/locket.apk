package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzajy;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzsq  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzsq extends zzajy<zzsq, zza> implements zzale {
    /* access modifiers changed from: private */
    public static final zzsq zzc;
    private static volatile zzalp<zzsq> zzd;
    private int zze;
    private zzst zzf;
    /* access modifiers changed from: private */
    public int zzg;

    public final int zza() {
        return this.zzg;
    }

    public static zza zzb() {
        return (zza) zzc.zzm();
    }

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzsq$zza */
    /* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
    public static final class zza extends zzajy.zza<zzsq, zza> implements zzale {
        public final zza zza(int i) {
            zzh();
            ((zzsq) this.zza).zzg = i;
            return this;
        }

        public final zza zza(zzst zzst) {
            zzh();
            zzsq.zza((zzsq) this.zza, zzst);
            return this;
        }

        private zza() {
            super(zzsq.zzc);
        }
    }

    public static zzsq zza(zzaip zzaip, zzajk zzajk) throws zzakf {
        return (zzsq) zzajy.zza(zzc, zzaip, zzajk);
    }

    public final zzst zzd() {
        zzst zzst = this.zzf;
        return zzst == null ? zzst.zzd() : zzst;
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzss.zza[i - 1]) {
            case 1:
                return new zzsq();
            case 2:
                return new zza();
            case 3:
                return zza((zzalc) zzc, "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဉ\u0000\u0002\u000b", new Object[]{"zze", "zzf", "zzg"});
            case 4:
                return zzc;
            case 5:
                zzalp<zzsq> zzalp = zzd;
                if (zzalp == null) {
                    synchronized (zzsq.class) {
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

    static /* synthetic */ void zza(zzsq zzsq, zzst zzst) {
        zzst.getClass();
        zzsq.zzf = zzst;
        zzsq.zze |= 1;
    }

    static {
        zzsq zzsq = new zzsq();
        zzc = zzsq;
        zzajy.zza(zzsq.class, zzsq);
    }

    private zzsq() {
    }
}
