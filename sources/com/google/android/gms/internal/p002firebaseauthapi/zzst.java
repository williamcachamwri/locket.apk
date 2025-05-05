package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzajy;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzst  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzst extends zzajy<zzst, zza> implements zzale {
    /* access modifiers changed from: private */
    public static final zzst zzc;
    private static volatile zzalp<zzst> zzd;
    /* access modifiers changed from: private */
    public int zze;

    public final int zza() {
        return this.zze;
    }

    public static zza zzb() {
        return (zza) zzc.zzm();
    }

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzst$zza */
    /* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
    public static final class zza extends zzajy.zza<zzst, zza> implements zzale {
        public final zza zza(int i) {
            zzh();
            ((zzst) this.zza).zze = i;
            return this;
        }

        private zza() {
            super(zzst.zzc);
        }
    }

    public static zzst zzd() {
        return zzc;
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzsv.zza[i - 1]) {
            case 1:
                return new zzst();
            case 2:
                return new zza();
            case 3:
                return zza((zzalc) zzc, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\u000b", new Object[]{"zze"});
            case 4:
                return zzc;
            case 5:
                zzalp<zzst> zzalp = zzd;
                if (zzalp == null) {
                    synchronized (zzst.class) {
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
        zzst zzst = new zzst();
        zzc = zzst;
        zzajy.zza(zzst.class, zzst);
    }

    private zzst() {
    }
}
