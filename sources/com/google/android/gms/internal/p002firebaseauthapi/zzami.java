package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzajy;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzami  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzami extends zzajy<zzami, zza> implements zzale {
    /* access modifiers changed from: private */
    public static final zzami zzc;
    private static volatile zzalp<zzami> zzd;
    /* access modifiers changed from: private */
    public long zze;
    /* access modifiers changed from: private */
    public int zzf;

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzami$zza */
    /* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
    public static final class zza extends zzajy.zza<zzami, zza> implements zzale {
        public final zza zza(int i) {
            if (!this.zza.zzu()) {
                zzi();
            }
            ((zzami) this.zza).zzf = i;
            return this;
        }

        public final zza zza(long j) {
            if (!this.zza.zzu()) {
                zzi();
            }
            ((zzami) this.zza).zze = j;
            return this;
        }

        private zza() {
            super(zzami.zzc);
        }
    }

    public final int zza() {
        return this.zzf;
    }

    public final long zzb() {
        return this.zze;
    }

    public static zza zzc() {
        return (zza) zzc.zzm();
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzamk.zza[i - 1]) {
            case 1:
                return new zzami();
            case 2:
                return new zza();
            case 3:
                return zza((zzalc) zzc, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u0002\u0002\u0004", new Object[]{"zze", "zzf"});
            case 4:
                return zzc;
            case 5:
                zzalp<zzami> zzalp = zzd;
                if (zzalp == null) {
                    synchronized (zzami.class) {
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
        zzami zzami = new zzami();
        zzc = zzami;
        zzajy.zza(zzami.class, zzami);
    }

    private zzami() {
    }
}
