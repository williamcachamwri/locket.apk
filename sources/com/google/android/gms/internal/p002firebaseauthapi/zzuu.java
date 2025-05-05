package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzajy;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzuu  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzuu extends zzajy<zzuu, zza> implements zzale {
    /* access modifiers changed from: private */
    public static final zzuu zzc;
    private static volatile zzalp<zzuu> zzd;
    private int zze;
    private int zzf;
    private zzva zzg;
    private zzaip zzh = zzaip.zza;

    public final int zza() {
        return this.zzf;
    }

    public static zza zzb() {
        return (zza) zzc.zzm();
    }

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzuu$zza */
    /* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
    public static final class zza extends zzajy.zza<zzuu, zza> implements zzale {
        public final zza zza(zzaip zzaip) {
            zzh();
            zzuu.zza((zzuu) this.zza, zzaip);
            return this;
        }

        public final zza zza(zzva zzva) {
            zzh();
            zzuu.zza((zzuu) this.zza, zzva);
            return this;
        }

        private zza() {
            super(zzuu.zzc);
        }
    }

    public static zzuu zzd() {
        return zzc;
    }

    public static zzuu zza(zzaip zzaip, zzajk zzajk) throws zzakf {
        return (zzuu) zzajy.zza(zzc, zzaip, zzajk);
    }

    public final zzva zze() {
        zzva zzva = this.zzg;
        return zzva == null ? zzva.zze() : zzva;
    }

    public final zzaip zzf() {
        return this.zzh;
    }

    public static zzalp<zzuu> c_() {
        return (zzalp) zzc.zza(zzajy.zzf.zzg, (Object) null, (Object) null);
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzut.zza[i - 1]) {
            case 1:
                return new zzuu();
            case 2:
                return new zza();
            case 3:
                return zza((zzalc) zzc, "\u0000\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000b\u0002ဉ\u0000\u0003\n", new Object[]{"zze", "zzf", "zzg", "zzh"});
            case 4:
                return zzc;
            case 5:
                zzalp<zzuu> zzalp = zzd;
                if (zzalp == null) {
                    synchronized (zzuu.class) {
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

    static /* synthetic */ void zza(zzuu zzuu, zzaip zzaip) {
        zzaip.getClass();
        zzuu.zzh = zzaip;
    }

    static /* synthetic */ void zza(zzuu zzuu, zzva zzva) {
        zzva.getClass();
        zzuu.zzg = zzva;
        zzuu.zze |= 1;
    }

    static {
        zzuu zzuu = new zzuu();
        zzc = zzuu;
        zzajy.zza(zzuu.class, zzuu);
    }

    private zzuu() {
    }
}
