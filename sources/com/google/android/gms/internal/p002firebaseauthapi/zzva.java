package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzajy;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzva  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzva extends zzajy<zzva, zza> implements zzale {
    /* access modifiers changed from: private */
    public static final zzva zzc;
    private static volatile zzalp<zzva> zzd;
    /* access modifiers changed from: private */
    public int zze;
    /* access modifiers changed from: private */
    public int zzf;

    public final int zza() {
        return this.zzf;
    }

    public final zzur zzb() {
        zzur zza2 = zzur.zza(this.zze);
        return zza2 == null ? zzur.UNRECOGNIZED : zza2;
    }

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzva$zza */
    /* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
    public static final class zza extends zzajy.zza<zzva, zza> implements zzale {
        public final zza zza(zzur zzur) {
            zzh();
            ((zzva) this.zza).zze = zzur.zza();
            return this;
        }

        public final zza zza(int i) {
            zzh();
            ((zzva) this.zza).zzf = i;
            return this;
        }

        private zza() {
            super(zzva.zzc);
        }
    }

    public static zza zzc() {
        return (zza) zzc.zzm();
    }

    public static zzva zze() {
        return zzc;
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzuz.zza[i - 1]) {
            case 1:
                return new zzva();
            case 2:
                return new zza();
            case 3:
                return zza((zzalc) zzc, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\f\u0002\u000b", new Object[]{"zze", "zzf"});
            case 4:
                return zzc;
            case 5:
                zzalp<zzva> zzalp = zzd;
                if (zzalp == null) {
                    synchronized (zzva.class) {
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
        zzva zzva = new zzva();
        zzc = zzva;
        zzajy.zza(zzva.class, zzva);
    }

    private zzva() {
    }
}
