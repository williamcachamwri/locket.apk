package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzajy;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzua  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzua extends zzajy<zzua, zza> implements zzale {
    /* access modifiers changed from: private */
    public static final zzua zzc;
    private static volatile zzalp<zzua> zzd;
    private int zze;
    private zzud zzf;

    public static zza zza() {
        return (zza) zzc.zzm();
    }

    public static zzua zza(zzaip zzaip, zzajk zzajk) throws zzakf {
        return (zzua) zzajy.zza(zzc, zzaip, zzajk);
    }

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzua$zza */
    /* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
    public static final class zza extends zzajy.zza<zzua, zza> implements zzale {
        public final zza zza(zzud zzud) {
            zzh();
            zzua.zza((zzua) this.zza, zzud);
            return this;
        }

        private zza() {
            super(zzua.zzc);
        }
    }

    public final zzud zzc() {
        zzud zzud = this.zzf;
        return zzud == null ? zzud.zze() : zzud;
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zztz.zza[i - 1]) {
            case 1:
                return new zzua();
            case 2:
                return new zza();
            case 3:
                return zza((zzalc) zzc, "\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001ဉ\u0000", new Object[]{"zze", "zzf"});
            case 4:
                return zzc;
            case 5:
                zzalp<zzua> zzalp = zzd;
                if (zzalp == null) {
                    synchronized (zzua.class) {
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

    static /* synthetic */ void zza(zzua zzua, zzud zzud) {
        zzud.getClass();
        zzua.zzf = zzud;
        zzua.zze |= 1;
    }

    static {
        zzua zzua = new zzua();
        zzc = zzua;
        zzajy.zza(zzua.class, zzua);
    }

    private zzua() {
    }
}
