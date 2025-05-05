package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzajy;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzud  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzud extends zzajy<zzud, zza> implements zzale {
    /* access modifiers changed from: private */
    public static final zzud zzc;
    private static volatile zzalp<zzud> zzd;
    private int zze;
    private zzum zzf;
    private zztx zzg;
    /* access modifiers changed from: private */
    public int zzh;

    public final zztu zza() {
        zztu zza2 = zztu.zza(this.zzh);
        return zza2 == null ? zztu.UNRECOGNIZED : zza2;
    }

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzud$zza */
    /* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
    public static final class zza extends zzajy.zza<zzud, zza> implements zzale {
        public final zza zza(zztx zztx) {
            zzh();
            zzud.zza((zzud) this.zza, zztx);
            return this;
        }

        public final zza zza(zztu zztu) {
            zzh();
            ((zzud) this.zza).zzh = zztu.zza();
            return this;
        }

        public final zza zza(zzum zzum) {
            zzh();
            zzud.zza((zzud) this.zza, zzum);
            return this;
        }

        private zza() {
            super(zzud.zzc);
        }
    }

    public final zztx zzb() {
        zztx zztx = this.zzg;
        return zztx == null ? zztx.zzc() : zztx;
    }

    public static zza zzc() {
        return (zza) zzc.zzm();
    }

    public static zzud zze() {
        return zzc;
    }

    public final zzum zzf() {
        zzum zzum = this.zzf;
        return zzum == null ? zzum.zzc() : zzum;
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzuc.zza[i - 1]) {
            case 1:
                return new zzud();
            case 2:
                return new zza();
            case 3:
                return zza((zzalc) zzc, "\u0000\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001\u0003\f", new Object[]{"zze", "zzf", "zzg", "zzh"});
            case 4:
                return zzc;
            case 5:
                zzalp<zzud> zzalp = zzd;
                if (zzalp == null) {
                    synchronized (zzud.class) {
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

    static /* synthetic */ void zza(zzud zzud, zztx zztx) {
        zztx.getClass();
        zzud.zzg = zztx;
        zzud.zze |= 2;
    }

    static /* synthetic */ void zza(zzud zzud, zzum zzum) {
        zzum.getClass();
        zzud.zzf = zzum;
        zzud.zze |= 1;
    }

    static {
        zzud zzud = new zzud();
        zzc = zzud;
        zzajy.zza(zzud.class, zzud);
    }

    private zzud() {
    }
}
