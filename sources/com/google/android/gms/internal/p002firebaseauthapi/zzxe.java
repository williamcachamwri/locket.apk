package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzajy;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzxe  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzxe extends zzajy<zzxe, zza> implements zzale {
    /* access modifiers changed from: private */
    public static final zzxe zzc;
    private static volatile zzalp<zzxe> zzd;
    /* access modifiers changed from: private */
    public int zze;

    public final int zza() {
        return this.zze;
    }

    public static zza zzb() {
        return (zza) zzc.zzm();
    }

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzxe$zza */
    /* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
    public static final class zza extends zzajy.zza<zzxe, zza> implements zzale {
        public final zza zza(int i) {
            zzh();
            ((zzxe) this.zza).zze = i;
            return this;
        }

        private zza() {
            super(zzxe.zzc);
        }
    }

    public static zzxe zzd() {
        return zzc;
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzxd.zza[i - 1]) {
            case 1:
                return new zzxe();
            case 2:
                return new zza();
            case 3:
                return zza((zzalc) zzc, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\u000b", new Object[]{"zze"});
            case 4:
                return zzc;
            case 5:
                zzalp<zzxe> zzalp = zzd;
                if (zzalp == null) {
                    synchronized (zzxe.class) {
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
        zzxe zzxe = new zzxe();
        zzc = zzxe;
        zzajy.zza(zzxe.class, zzxe);
    }

    private zzxe() {
    }
}
