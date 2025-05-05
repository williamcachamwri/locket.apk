package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzajy;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzvh  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzvh extends zzajy<zzvh, zza> implements zzale {
    /* access modifiers changed from: private */
    public static final zzvh zzc;
    private static volatile zzalp<zzvh> zzd;
    /* access modifiers changed from: private */
    public int zze;
    /* access modifiers changed from: private */
    public int zzf;
    /* access modifiers changed from: private */
    public int zzg;

    public final zzvd zza() {
        zzvd zza2 = zzvd.zza(this.zzg);
        return zza2 == null ? zzvd.UNRECOGNIZED : zza2;
    }

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzvh$zza */
    /* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
    public static final class zza extends zzajy.zza<zzvh, zza> implements zzale {
        public final zza zza(zzvd zzvd) {
            zzh();
            ((zzvh) this.zza).zzg = zzvd.zza();
            return this;
        }

        public final zza zza(zzvc zzvc) {
            zzh();
            ((zzvh) this.zza).zzf = zzvc.zza();
            return this;
        }

        public final zza zza(zzvf zzvf) {
            zzh();
            ((zzvh) this.zza).zze = zzvf.zza();
            return this;
        }

        private zza() {
            super(zzvh.zzc);
        }
    }

    public final zzvc zzb() {
        zzvc zza2 = zzvc.zza(this.zzf);
        return zza2 == null ? zzvc.UNRECOGNIZED : zza2;
    }

    public final zzvf zzc() {
        zzvf zza2 = zzvf.zza(this.zze);
        return zza2 == null ? zzvf.UNRECOGNIZED : zza2;
    }

    public static zza zzd() {
        return (zza) zzc.zzm();
    }

    public static zzvh zzf() {
        return zzc;
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzvj.zza[i - 1]) {
            case 1:
                return new zzvh();
            case 2:
                return new zza();
            case 3:
                return zza((zzalc) zzc, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\f\u0002\f\u0003\f", new Object[]{"zze", "zzf", "zzg"});
            case 4:
                return zzc;
            case 5:
                zzalp<zzvh> zzalp = zzd;
                if (zzalp == null) {
                    synchronized (zzvh.class) {
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
        zzvh zzvh = new zzvh();
        zzc = zzvh;
        zzajy.zza(zzvh.class, zzvh);
    }

    private zzvh() {
    }
}
