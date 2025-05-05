package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzajy;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzug  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzug extends zzajy<zzug, zza> implements zzale {
    /* access modifiers changed from: private */
    public static final zzug zzc;
    private static volatile zzalp<zzug> zzd;
    private int zze;
    /* access modifiers changed from: private */
    public int zzf;
    private zzuj zzg;
    private zzaip zzh = zzaip.zza;

    public final int zza() {
        return this.zzf;
    }

    public static zza zzb() {
        return (zza) zzc.zzm();
    }

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzug$zza */
    /* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
    public static final class zza extends zzajy.zza<zzug, zza> implements zzale {
        public final zza zza(zzaip zzaip) {
            zzh();
            zzug.zza((zzug) this.zza, zzaip);
            return this;
        }

        public final zza zza(zzuj zzuj) {
            zzh();
            zzug.zza((zzug) this.zza, zzuj);
            return this;
        }

        public final zza zza(int i) {
            zzh();
            ((zzug) this.zza).zzf = 0;
            return this;
        }

        private zza() {
            super(zzug.zzc);
        }
    }

    public static zzug zza(zzaip zzaip, zzajk zzajk) throws zzakf {
        return (zzug) zzajy.zza(zzc, zzaip, zzajk);
    }

    public final zzuj zzd() {
        zzuj zzuj = this.zzg;
        return zzuj == null ? zzuj.zze() : zzuj;
    }

    public final zzaip zze() {
        return this.zzh;
    }

    public static zzalp<zzug> zzf() {
        return (zzalp) zzc.zza(zzajy.zzf.zzg, (Object) null, (Object) null);
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzuf.zza[i - 1]) {
            case 1:
                return new zzug();
            case 2:
                return new zza();
            case 3:
                return zza((zzalc) zzc, "\u0000\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000b\u0002ဉ\u0000\u0003\n", new Object[]{"zze", "zzf", "zzg", "zzh"});
            case 4:
                return zzc;
            case 5:
                zzalp<zzug> zzalp = zzd;
                if (zzalp == null) {
                    synchronized (zzug.class) {
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

    static /* synthetic */ void zza(zzug zzug, zzaip zzaip) {
        zzaip.getClass();
        zzug.zzh = zzaip;
    }

    static /* synthetic */ void zza(zzug zzug, zzuj zzuj) {
        zzuj.getClass();
        zzug.zzg = zzuj;
        zzug.zze |= 1;
    }

    static {
        zzug zzug = new zzug();
        zzc = zzug;
        zzajy.zza(zzug.class, zzug);
    }

    private zzug() {
    }
}
