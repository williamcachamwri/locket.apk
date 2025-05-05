package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzajy;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzsz  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzsz extends zzajy<zzsz, zza> implements zzale {
    /* access modifiers changed from: private */
    public static final zzsz zzc;
    private static volatile zzalp<zzsz> zzd;
    /* access modifiers changed from: private */
    public int zze;
    private int zzf;

    public final int zza() {
        return this.zze;
    }

    public final int zzb() {
        return this.zzf;
    }

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzsz$zza */
    /* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
    public static final class zza extends zzajy.zza<zzsz, zza> implements zzale {
        public final zza zza(int i) {
            zzh();
            ((zzsz) this.zza).zze = i;
            return this;
        }

        private zza() {
            super(zzsz.zzc);
        }
    }

    public static zza zzc() {
        return (zza) zzc.zzm();
    }

    public static zzsz zza(zzaip zzaip, zzajk zzajk) throws zzakf {
        return (zzsz) zzajy.zza(zzc, zzaip, zzajk);
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zztb.zza[i - 1]) {
            case 1:
                return new zzsz();
            case 2:
                return new zza();
            case 3:
                return zza((zzalc) zzc, "\u0000\u0002\u0000\u0000\u0002\u0003\u0002\u0000\u0000\u0000\u0002\u000b\u0003\u000b", new Object[]{"zze", "zzf"});
            case 4:
                return zzc;
            case 5:
                zzalp<zzsz> zzalp = zzd;
                if (zzalp == null) {
                    synchronized (zzsz.class) {
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
        zzsz zzsz = new zzsz();
        zzc = zzsz;
        zzajy.zza(zzsz.class, zzsz);
    }

    private zzsz() {
    }
}
