package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzajy;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzvn  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzvn extends zzajy<zzvn, zza> implements zzale {
    /* access modifiers changed from: private */
    public static final zzvn zzc;
    private static volatile zzalp<zzvn> zzd;
    private int zze;
    /* access modifiers changed from: private */
    public int zzf;
    private zzvh zzg;
    private zzaip zzh = zzaip.zza;

    public final int zza() {
        return this.zzf;
    }

    public final zzvh zzb() {
        zzvh zzvh = this.zzg;
        return zzvh == null ? zzvh.zzf() : zzvh;
    }

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzvn$zza */
    /* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
    public static final class zza extends zzajy.zza<zzvn, zza> implements zzale {
        public final zza zza(zzvh zzvh) {
            zzh();
            zzvn.zza((zzvn) this.zza, zzvh);
            return this;
        }

        public final zza zza(zzaip zzaip) {
            zzh();
            zzvn.zza((zzvn) this.zza, zzaip);
            return this;
        }

        public final zza zza(int i) {
            zzh();
            ((zzvn) this.zza).zzf = 0;
            return this;
        }

        private zza() {
            super(zzvn.zzc);
        }
    }

    public static zza zzc() {
        return (zza) zzc.zzm();
    }

    public static zzvn zze() {
        return zzc;
    }

    public static zzvn zza(zzaip zzaip, zzajk zzajk) throws zzakf {
        return (zzvn) zzajy.zza(zzc, zzaip, zzajk);
    }

    public final zzaip zzf() {
        return this.zzh;
    }

    public static zzalp<zzvn> d_() {
        return (zzalp) zzc.zza(zzajy.zzf.zzg, (Object) null, (Object) null);
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzvp.zza[i - 1]) {
            case 1:
                return new zzvn();
            case 2:
                return new zza();
            case 3:
                return zza((zzalc) zzc, "\u0000\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000b\u0002ဉ\u0000\u0003\n", new Object[]{"zze", "zzf", "zzg", "zzh"});
            case 4:
                return zzc;
            case 5:
                zzalp<zzvn> zzalp = zzd;
                if (zzalp == null) {
                    synchronized (zzvn.class) {
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

    static /* synthetic */ void zza(zzvn zzvn, zzvh zzvh) {
        zzvh.getClass();
        zzvn.zzg = zzvh;
        zzvn.zze |= 1;
    }

    static /* synthetic */ void zza(zzvn zzvn, zzaip zzaip) {
        zzaip.getClass();
        zzvn.zzh = zzaip;
    }

    static {
        zzvn zzvn = new zzvn();
        zzc = zzvn;
        zzajy.zza(zzvn.class, zzvn);
    }

    private zzvn() {
    }
}
