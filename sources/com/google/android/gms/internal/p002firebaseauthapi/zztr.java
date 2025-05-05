package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzajy;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zztr  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zztr extends zzajy<zztr, zza> implements zzale {
    /* access modifiers changed from: private */
    public static final zztr zzc;
    private static volatile zzalp<zztr> zzd;

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zztr$zza */
    /* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
    public static final class zza extends zzajy.zza<zztr, zza> implements zzale {
        private zza() {
            super(zztr.zzc);
        }
    }

    public static zztr zzb() {
        return zzc;
    }

    public static zztr zza(zzaip zzaip, zzajk zzajk) throws zzakf {
        return (zztr) zzajy.zza(zzc, zzaip, zzajk);
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zztt.zza[i - 1]) {
            case 1:
                return new zztr();
            case 2:
                return new zza();
            case 3:
                return zza((zzalc) zzc, "\u0000\u0000", (Object[]) null);
            case 4:
                return zzc;
            case 5:
                zzalp<zztr> zzalp = zzd;
                if (zzalp == null) {
                    synchronized (zztr.class) {
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
        zztr zztr = new zztr();
        zzc = zztr;
        zzajy.zza(zztr.class, zztr);
    }

    private zztr() {
    }
}
