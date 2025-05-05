package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzajy;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzwj  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzwj extends zzajy<zzwj, zza> implements zzale {
    /* access modifiers changed from: private */
    public static final zzwj zzc;
    private static volatile zzalp<zzwj> zzd;
    private String zze = "";

    public static zza zza() {
        return (zza) zzc.zzm();
    }

    public static zzwj zzc() {
        return zzc;
    }

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzwj$zza */
    /* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
    public static final class zza extends zzajy.zza<zzwj, zza> implements zzale {
        public final zza zza(String str) {
            zzh();
            zzwj.zza((zzwj) this.zza, str);
            return this;
        }

        private zza() {
            super(zzwj.zzc);
        }
    }

    public static zzwj zza(zzaip zzaip, zzajk zzajk) throws zzakf {
        return (zzwj) zzajy.zza(zzc, zzaip, zzajk);
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzwl.zza[i - 1]) {
            case 1:
                return new zzwj();
            case 2:
                return new zza();
            case 3:
                return zza((zzalc) zzc, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001Ȉ", new Object[]{"zze"});
            case 4:
                return zzc;
            case 5:
                zzalp<zzwj> zzalp = zzd;
                if (zzalp == null) {
                    synchronized (zzwj.class) {
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

    public final String zzd() {
        return this.zze;
    }

    static /* synthetic */ void zza(zzwj zzwj, String str) {
        str.getClass();
        zzwj.zze = str;
    }

    static {
        zzwj zzwj = new zzwj();
        zzc = zzwj;
        zzajy.zza(zzwj.class, zzwj);
    }

    private zzwj() {
    }
}
