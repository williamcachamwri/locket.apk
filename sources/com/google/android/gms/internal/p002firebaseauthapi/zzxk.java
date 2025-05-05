package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzajy;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzxk  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzxk extends zzajy<zzxk, zza> implements zzale {
    /* access modifiers changed from: private */
    public static final zzxk zzc;
    private static volatile zzalp<zzxk> zzd;
    private int zze;

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzxk$zza */
    /* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
    public static final class zza extends zzajy.zza<zzxk, zza> implements zzale {
        private zza() {
            super(zzxk.zzc);
        }
    }

    public final int zza() {
        return this.zze;
    }

    public static zzxk zzc() {
        return zzc;
    }

    public static zzxk zza(zzaip zzaip, zzajk zzajk) throws zzakf {
        return (zzxk) zzajy.zza(zzc, zzaip, zzajk);
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzxj.zza[i - 1]) {
            case 1:
                return new zzxk();
            case 2:
                return new zza();
            case 3:
                return zza((zzalc) zzc, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\u000b", new Object[]{"zze"});
            case 4:
                return zzc;
            case 5:
                zzalp<zzxk> zzalp = zzd;
                if (zzalp == null) {
                    synchronized (zzxk.class) {
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

    static {
        zzxk zzxk = new zzxk();
        zzc = zzxk;
        zzajy.zza(zzxk.class, zzxk);
    }

    private zzxk() {
    }
}
