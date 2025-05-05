package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzajy;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzse  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzse extends zzajy<zzse, zza> implements zzale {
    /* access modifiers changed from: private */
    public static final zzse zzc;
    private static volatile zzalp<zzse> zzd;
    private int zze;
    private int zzf;
    private zzsk zzg;
    private zzaip zzh = zzaip.zza;

    public final int zza() {
        return this.zzf;
    }

    public static zza zzb() {
        return (zza) zzc.zzm();
    }

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzse$zza */
    /* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
    public static final class zza extends zzajy.zza<zzse, zza> implements zzale {
        public final zza zza(zzaip zzaip) {
            zzh();
            zzse.zza((zzse) this.zza, zzaip);
            return this;
        }

        public final zza zza(zzsk zzsk) {
            zzh();
            zzse.zza((zzse) this.zza, zzsk);
            return this;
        }

        private zza() {
            super(zzse.zzc);
        }
    }

    public static zzse zzd() {
        return zzc;
    }

    public final zzsk zze() {
        zzsk zzsk = this.zzg;
        return zzsk == null ? zzsk.zzd() : zzsk;
    }

    public final zzaip zzf() {
        return this.zzh;
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzsg.zza[i - 1]) {
            case 1:
                return new zzse();
            case 2:
                return new zza();
            case 3:
                return zza((zzalc) zzc, "\u0000\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000b\u0002ဉ\u0000\u0003\n", new Object[]{"zze", "zzf", "zzg", "zzh"});
            case 4:
                return zzc;
            case 5:
                zzalp<zzse> zzalp = zzd;
                if (zzalp == null) {
                    synchronized (zzse.class) {
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

    static /* synthetic */ void zza(zzse zzse, zzaip zzaip) {
        zzaip.getClass();
        zzse.zzh = zzaip;
    }

    static /* synthetic */ void zza(zzse zzse, zzsk zzsk) {
        zzsk.getClass();
        zzse.zzg = zzsk;
        zzse.zze |= 1;
    }

    static {
        zzse zzse = new zzse();
        zzc = zzse;
        zzajy.zza(zzse.class, zzse);
    }

    private zzse() {
    }
}
