package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzajy;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzwg  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzwg extends zzajy<zzwg, zza> implements zzale {
    /* access modifiers changed from: private */
    public static final zzwg zzc;
    private static volatile zzalp<zzwg> zzd;
    private int zze;
    private int zzf;
    private zzwj zzg;

    public final int zza() {
        return this.zzf;
    }

    public static zza zzb() {
        return (zza) zzc.zzm();
    }

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzwg$zza */
    /* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
    public static final class zza extends zzajy.zza<zzwg, zza> implements zzale {
        public final zza zza(zzwj zzwj) {
            zzh();
            zzwg.zza((zzwg) this.zza, zzwj);
            return this;
        }

        private zza() {
            super(zzwg.zzc);
        }
    }

    public static zzwg zza(zzaip zzaip, zzajk zzajk) throws zzakf {
        return (zzwg) zzajy.zza(zzc, zzaip, zzajk);
    }

    public final zzwj zzd() {
        zzwj zzwj = this.zzg;
        return zzwj == null ? zzwj.zzc() : zzwj;
    }

    public static zzalp<zzwg> zze() {
        return (zzalp) zzc.zza(zzajy.zzf.zzg, (Object) null, (Object) null);
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzwi.zza[i - 1]) {
            case 1:
                return new zzwg();
            case 2:
                return new zza();
            case 3:
                return zza((zzalc) zzc, "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u000b\u0002ဉ\u0000", new Object[]{"zze", "zzf", "zzg"});
            case 4:
                return zzc;
            case 5:
                zzalp<zzwg> zzalp = zzd;
                if (zzalp == null) {
                    synchronized (zzwg.class) {
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

    static /* synthetic */ void zza(zzwg zzwg, zzwj zzwj) {
        zzwj.getClass();
        zzwg.zzg = zzwj;
        zzwg.zze |= 1;
    }

    static {
        zzwg zzwg = new zzwg();
        zzc = zzwg;
        zzajy.zza(zzwg.class, zzwg);
    }

    private zzwg() {
    }
}
