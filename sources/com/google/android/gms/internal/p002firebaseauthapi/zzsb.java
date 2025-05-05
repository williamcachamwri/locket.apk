package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzajy;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzsb  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzsb extends zzajy<zzsb, zza> implements zzale {
    /* access modifiers changed from: private */
    public static final zzsb zzc;
    private static volatile zzalp<zzsb> zzd;
    private int zze;
    private zzsh zzf;
    private zzux zzg;

    public static zza zza() {
        return (zza) zzc.zzm();
    }

    public static zzsb zza(zzaip zzaip, zzajk zzajk) throws zzakf {
        return (zzsb) zzajy.zza(zzc, zzaip, zzajk);
    }

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzsb$zza */
    /* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
    public static final class zza extends zzajy.zza<zzsb, zza> implements zzale {
        public final zza zza(zzsh zzsh) {
            zzh();
            zzsb.zza((zzsb) this.zza, zzsh);
            return this;
        }

        public final zza zza(zzux zzux) {
            zzh();
            zzsb.zza((zzsb) this.zza, zzux);
            return this;
        }

        private zza() {
            super(zzsb.zzc);
        }
    }

    public final zzsh zzc() {
        zzsh zzsh = this.zzf;
        return zzsh == null ? zzsh.zzd() : zzsh;
    }

    public final zzux zzd() {
        zzux zzux = this.zzg;
        return zzux == null ? zzux.zze() : zzux;
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzsd.zza[i - 1]) {
            case 1:
                return new zzsb();
            case 2:
                return new zza();
            case 3:
                return zza((zzalc) zzc, "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001", new Object[]{"zze", "zzf", "zzg"});
            case 4:
                return zzc;
            case 5:
                zzalp<zzsb> zzalp = zzd;
                if (zzalp == null) {
                    synchronized (zzsb.class) {
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

    static /* synthetic */ void zza(zzsb zzsb, zzsh zzsh) {
        zzsh.getClass();
        zzsb.zzf = zzsh;
        zzsb.zze |= 1;
    }

    static /* synthetic */ void zza(zzsb zzsb, zzux zzux) {
        zzux.getClass();
        zzsb.zzg = zzux;
        zzsb.zze |= 2;
    }

    static {
        zzsb zzsb = new zzsb();
        zzc = zzsb;
        zzajy.zza(zzsb.class, zzsb);
    }

    private zzsb() {
    }
}
