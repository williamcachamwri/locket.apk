package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzajy;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zztx  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zztx extends zzajy<zztx, zza> implements zzale {
    /* access modifiers changed from: private */
    public static final zztx zzc;
    private static volatile zzalp<zztx> zzd;
    private int zze;
    private zzvu zzf;

    public static zza zza() {
        return (zza) zzc.zzm();
    }

    public static zztx zzc() {
        return zzc;
    }

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zztx$zza */
    /* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
    public static final class zza extends zzajy.zza<zztx, zza> implements zzale {
        public final zza zza(zzvu zzvu) {
            zzh();
            zztx.zza((zztx) this.zza, zzvu);
            return this;
        }

        private zza() {
            super(zztx.zzc);
        }
    }

    public final zzvu zzd() {
        zzvu zzvu = this.zzf;
        return zzvu == null ? zzvu.zzc() : zzvu;
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zztw.zza[i - 1]) {
            case 1:
                return new zztx();
            case 2:
                return new zza();
            case 3:
                return zza((zzalc) zzc, "\u0000\u0001\u0000\u0001\u0002\u0002\u0001\u0000\u0000\u0000\u0002ဉ\u0000", new Object[]{"zze", "zzf"});
            case 4:
                return zzc;
            case 5:
                zzalp<zztx> zzalp = zzd;
                if (zzalp == null) {
                    synchronized (zztx.class) {
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

    static /* synthetic */ void zza(zztx zztx, zzvu zzvu) {
        zzvu.getClass();
        zztx.zzf = zzvu;
        zztx.zze |= 1;
    }

    static {
        zztx zztx = new zztx();
        zzc = zztx;
        zzajy.zza(zztx.class, zztx);
    }

    private zztx() {
    }
}
