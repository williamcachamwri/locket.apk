package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzajy;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzve  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzve extends zzajy<zzve, zza> implements zzale {
    /* access modifiers changed from: private */
    public static final zzve zzc;
    private static volatile zzalp<zzve> zzd;
    private int zze;
    private zzvh zzf;

    public static zza zza() {
        return (zza) zzc.zzm();
    }

    public static zzve zza(zzaip zzaip, zzajk zzajk) throws zzakf {
        return (zzve) zzajy.zza(zzc, zzaip, zzajk);
    }

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzve$zza */
    /* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
    public static final class zza extends zzajy.zza<zzve, zza> implements zzale {
        public final zza zza(zzvh zzvh) {
            zzh();
            zzve.zza((zzve) this.zza, zzvh);
            return this;
        }

        private zza() {
            super(zzve.zzc);
        }
    }

    public final zzvh zzc() {
        zzvh zzvh = this.zzf;
        return zzvh == null ? zzvh.zzf() : zzvh;
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzvg.zza[i - 1]) {
            case 1:
                return new zzve();
            case 2:
                return new zza();
            case 3:
                return zza((zzalc) zzc, "\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001ဉ\u0000", new Object[]{"zze", "zzf"});
            case 4:
                return zzc;
            case 5:
                zzalp<zzve> zzalp = zzd;
                if (zzalp == null) {
                    synchronized (zzve.class) {
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

    static /* synthetic */ void zza(zzve zzve, zzvh zzvh) {
        zzvh.getClass();
        zzve.zzf = zzvh;
        zzve.zze |= 1;
    }

    static {
        zzve zzve = new zzve();
        zzc = zzve;
        zzajy.zza(zzve.class, zzve);
    }

    private zzve() {
    }
}
