package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzajy;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzry  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzry extends zzajy<zzry, zza> implements zzale {
    /* access modifiers changed from: private */
    public static final zzry zzc;
    private static volatile zzalp<zzry> zzd;
    private int zze;
    private int zzf;
    private zzse zzg;
    private zzuu zzh;

    public final int zza() {
        return this.zzf;
    }

    public static zza zzb() {
        return (zza) zzc.zzm();
    }

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzry$zza */
    /* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
    public static final class zza extends zzajy.zza<zzry, zza> implements zzale {
        public final zza zza(zzse zzse) {
            zzh();
            zzry.zza((zzry) this.zza, zzse);
            return this;
        }

        public final zza zza(zzuu zzuu) {
            zzh();
            zzry.zza((zzry) this.zza, zzuu);
            return this;
        }

        private zza() {
            super(zzry.zzc);
        }
    }

    public static zzry zza(zzaip zzaip, zzajk zzajk) throws zzakf {
        return (zzry) zzajy.zza(zzc, zzaip, zzajk);
    }

    public final zzse zzd() {
        zzse zzse = this.zzg;
        return zzse == null ? zzse.zzd() : zzse;
    }

    public final zzuu zze() {
        zzuu zzuu = this.zzh;
        return zzuu == null ? zzuu.zzd() : zzuu;
    }

    public static zzalp<zzry> zzf() {
        return (zzalp) zzc.zza(zzajy.zzf.zzg, (Object) null, (Object) null);
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzsa.zza[i - 1]) {
            case 1:
                return new zzry();
            case 2:
                return new zza();
            case 3:
                return zza((zzalc) zzc, "\u0000\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000b\u0002ဉ\u0000\u0003ဉ\u0001", new Object[]{"zze", "zzf", "zzg", "zzh"});
            case 4:
                return zzc;
            case 5:
                zzalp<zzry> zzalp = zzd;
                if (zzalp == null) {
                    synchronized (zzry.class) {
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

    static /* synthetic */ void zza(zzry zzry, zzse zzse) {
        zzse.getClass();
        zzry.zzg = zzse;
        zzry.zze |= 1;
    }

    static /* synthetic */ void zza(zzry zzry, zzuu zzuu) {
        zzuu.getClass();
        zzry.zzh = zzuu;
        zzry.zze |= 2;
    }

    static {
        zzry zzry = new zzry();
        zzc = zzry;
        zzajy.zza(zzry.class, zzry);
    }

    private zzry() {
    }
}
