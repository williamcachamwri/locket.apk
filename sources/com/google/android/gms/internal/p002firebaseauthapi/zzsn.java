package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzajy;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzsn  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzsn extends zzajy<zzsn, zza> implements zzale {
    /* access modifiers changed from: private */
    public static final zzsn zzc;
    private static volatile zzalp<zzsn> zzd;
    private int zze;
    private int zzf;
    private zzst zzg;
    private zzaip zzh = zzaip.zza;

    public final int zza() {
        return this.zzf;
    }

    public static zza zzb() {
        return (zza) zzc.zzm();
    }

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzsn$zza */
    /* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
    public static final class zza extends zzajy.zza<zzsn, zza> implements zzale {
        public final zza zza(zzaip zzaip) {
            zzh();
            zzsn.zza((zzsn) this.zza, zzaip);
            return this;
        }

        public final zza zza(zzst zzst) {
            zzh();
            zzsn.zza((zzsn) this.zza, zzst);
            return this;
        }

        private zza() {
            super(zzsn.zzc);
        }
    }

    public static zzsn zza(zzaip zzaip, zzajk zzajk) throws zzakf {
        return (zzsn) zzajy.zza(zzc, zzaip, zzajk);
    }

    public final zzst zzd() {
        zzst zzst = this.zzg;
        return zzst == null ? zzst.zzd() : zzst;
    }

    public final zzaip zze() {
        return this.zzh;
    }

    public static zzalp<zzsn> zzf() {
        return (zzalp) zzc.zza(zzajy.zzf.zzg, (Object) null, (Object) null);
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzsp.zza[i - 1]) {
            case 1:
                return new zzsn();
            case 2:
                return new zza();
            case 3:
                return zza((zzalc) zzc, "\u0000\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000b\u0002ဉ\u0000\u0003\n", new Object[]{"zze", "zzf", "zzg", "zzh"});
            case 4:
                return zzc;
            case 5:
                zzalp<zzsn> zzalp = zzd;
                if (zzalp == null) {
                    synchronized (zzsn.class) {
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

    static /* synthetic */ void zza(zzsn zzsn, zzaip zzaip) {
        zzaip.getClass();
        zzsn.zzh = zzaip;
    }

    static /* synthetic */ void zza(zzsn zzsn, zzst zzst) {
        zzst.getClass();
        zzsn.zzg = zzst;
        zzsn.zze |= 1;
    }

    static {
        zzsn zzsn = new zzsn();
        zzc = zzsn;
        zzajy.zza(zzsn.class, zzsn);
    }

    private zzsn() {
    }
}
