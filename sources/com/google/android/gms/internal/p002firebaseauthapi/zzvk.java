package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzajy;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzvk  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzvk extends zzajy<zzvk, zza> implements zzale {
    /* access modifiers changed from: private */
    public static final zzvk zzc;
    private static volatile zzalp<zzvk> zzd;
    private int zze;
    /* access modifiers changed from: private */
    public int zzf;
    private zzvn zzg;
    private zzaip zzh = zzaip.zza;

    public final int zza() {
        return this.zzf;
    }

    public static zza zzb() {
        return (zza) zzc.zzm();
    }

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzvk$zza */
    /* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
    public static final class zza extends zzajy.zza<zzvk, zza> implements zzale {
        public final zza zza(zzaip zzaip) {
            zzh();
            zzvk.zza((zzvk) this.zza, zzaip);
            return this;
        }

        public final zza zza(zzvn zzvn) {
            zzh();
            zzvk.zza((zzvk) this.zza, zzvn);
            return this;
        }

        public final zza zza(int i) {
            zzh();
            ((zzvk) this.zza).zzf = 0;
            return this;
        }

        private zza() {
            super(zzvk.zzc);
        }
    }

    public static zzvk zza(zzaip zzaip, zzajk zzajk) throws zzakf {
        return (zzvk) zzajy.zza(zzc, zzaip, zzajk);
    }

    public final zzvn zzd() {
        zzvn zzvn = this.zzg;
        return zzvn == null ? zzvn.zze() : zzvn;
    }

    public final zzaip zze() {
        return this.zzh;
    }

    public static zzalp<zzvk> zzf() {
        return (zzalp) zzc.zza(zzajy.zzf.zzg, (Object) null, (Object) null);
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzvm.zza[i - 1]) {
            case 1:
                return new zzvk();
            case 2:
                return new zza();
            case 3:
                return zza((zzalc) zzc, "\u0000\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000b\u0002ဉ\u0000\u0003\n", new Object[]{"zze", "zzf", "zzg", "zzh"});
            case 4:
                return zzc;
            case 5:
                zzalp<zzvk> zzalp = zzd;
                if (zzalp == null) {
                    synchronized (zzvk.class) {
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

    static /* synthetic */ void zza(zzvk zzvk, zzaip zzaip) {
        zzaip.getClass();
        zzvk.zzh = zzaip;
    }

    static /* synthetic */ void zza(zzvk zzvk, zzvn zzvn) {
        zzvn.getClass();
        zzvk.zzg = zzvn;
        zzvk.zze |= 1;
    }

    static {
        zzvk zzvk = new zzvk();
        zzc = zzvk;
        zzajy.zza(zzvk.class, zzvk);
    }

    private zzvk() {
    }
}
