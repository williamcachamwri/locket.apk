package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzajy;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzuj  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzuj extends zzajy<zzuj, zza> implements zzale {
    /* access modifiers changed from: private */
    public static final zzuj zzc;
    private static volatile zzalp<zzuj> zzd;
    private int zze;
    /* access modifiers changed from: private */
    public int zzf;
    private zzud zzg;
    private zzaip zzh = zzaip.zza;
    private zzaip zzi = zzaip.zza;

    public final int zza() {
        return this.zzf;
    }

    public final zzud zzb() {
        zzud zzud = this.zzg;
        return zzud == null ? zzud.zze() : zzud;
    }

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzuj$zza */
    /* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
    public static final class zza extends zzajy.zza<zzuj, zza> implements zzale {
        public final zza zza(zzud zzud) {
            zzh();
            zzuj.zza((zzuj) this.zza, zzud);
            return this;
        }

        public final zza zza(int i) {
            zzh();
            ((zzuj) this.zza).zzf = 0;
            return this;
        }

        public final zza zza(zzaip zzaip) {
            zzh();
            zzuj.zza((zzuj) this.zza, zzaip);
            return this;
        }

        public final zza zzb(zzaip zzaip) {
            zzh();
            zzuj.zzb((zzuj) this.zza, zzaip);
            return this;
        }

        private zza() {
            super(zzuj.zzc);
        }
    }

    public static zza zzc() {
        return (zza) zzc.zzm();
    }

    public static zzuj zze() {
        return zzc;
    }

    public static zzuj zza(zzaip zzaip, zzajk zzajk) throws zzakf {
        return (zzuj) zzajy.zza(zzc, zzaip, zzajk);
    }

    public final zzaip zzf() {
        return this.zzh;
    }

    public final zzaip b_() {
        return this.zzi;
    }

    public static zzalp<zzuj> zzh() {
        return (zzalp) zzc.zza(zzajy.zzf.zzg, (Object) null, (Object) null);
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzui.zza[i - 1]) {
            case 1:
                return new zzuj();
            case 2:
                return new zza();
            case 3:
                return zza((zzalc) zzc, "\u0000\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001\u000b\u0002ဉ\u0000\u0003\n\u0004\n", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi"});
            case 4:
                return zzc;
            case 5:
                zzalp<zzuj> zzalp = zzd;
                if (zzalp == null) {
                    synchronized (zzuj.class) {
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

    static /* synthetic */ void zza(zzuj zzuj, zzud zzud) {
        zzud.getClass();
        zzuj.zzg = zzud;
        zzuj.zze |= 1;
    }

    static /* synthetic */ void zza(zzuj zzuj, zzaip zzaip) {
        zzaip.getClass();
        zzuj.zzh = zzaip;
    }

    static /* synthetic */ void zzb(zzuj zzuj, zzaip zzaip) {
        zzaip.getClass();
        zzuj.zzi = zzaip;
    }

    static {
        zzuj zzuj = new zzuj();
        zzc = zzuj;
        zzajy.zza(zzuj.class, zzuj);
    }

    private zzuj() {
    }
}
