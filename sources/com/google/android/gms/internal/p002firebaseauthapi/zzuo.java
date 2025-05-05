package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzajy;
import java.io.IOException;
import java.io.InputStream;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzuo  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzuo extends zzajy<zzuo, zza> implements zzale {
    /* access modifiers changed from: private */
    public static final zzuo zzc;
    private static volatile zzalp<zzuo> zzd;
    private int zze;
    private zzaip zzf = zzaip.zza;
    private zzwd zzg;

    public static zza zza() {
        return (zza) zzc.zzm();
    }

    public static zzuo zza(InputStream inputStream, zzajk zzajk) throws IOException {
        return (zzuo) zzajy.zza(zzc, inputStream, zzajk);
    }

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzuo$zza */
    /* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
    public static final class zza extends zzajy.zza<zzuo, zza> implements zzale {
        public final zza zza() {
            zzh();
            zzuo.zza((zzuo) this.zza);
            return this;
        }

        public final zza zza(zzaip zzaip) {
            zzh();
            zzuo.zza((zzuo) this.zza, zzaip);
            return this;
        }

        public final zza zza(zzwd zzwd) {
            zzh();
            zzuo.zza((zzuo) this.zza, zzwd);
            return this;
        }

        private zza() {
            super(zzuo.zzc);
        }
    }

    public final zzaip zzc() {
        return this.zzf;
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzuq.zza[i - 1]) {
            case 1:
                return new zzuo();
            case 2:
                return new zza();
            case 3:
                return zza((zzalc) zzc, "\u0000\u0002\u0000\u0001\u0002\u0003\u0002\u0000\u0000\u0000\u0002\n\u0003ဉ\u0000", new Object[]{"zze", "zzf", "zzg"});
            case 4:
                return zzc;
            case 5:
                zzalp<zzuo> zzalp = zzd;
                if (zzalp == null) {
                    synchronized (zzuo.class) {
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

    static /* synthetic */ void zza(zzuo zzuo) {
        zzuo.zzg = null;
        zzuo.zze &= -2;
    }

    static /* synthetic */ void zza(zzuo zzuo, zzaip zzaip) {
        zzaip.getClass();
        zzuo.zzf = zzaip;
    }

    static /* synthetic */ void zza(zzuo zzuo, zzwd zzwd) {
        zzwd.getClass();
        zzuo.zzg = zzwd;
        zzuo.zze |= 1;
    }

    static {
        zzuo zzuo = new zzuo();
        zzc = zzuo;
        zzajy.zza(zzuo.class, zzuo);
    }

    private zzuo() {
    }
}
