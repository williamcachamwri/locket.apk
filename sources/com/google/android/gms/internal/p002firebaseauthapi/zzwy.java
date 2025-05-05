package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzajy;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzwy  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzwy extends zzajy<zzwy, zza> implements zzale {
    /* access modifiers changed from: private */
    public static final zzwy zzc;
    private static volatile zzalp<zzwy> zzd;
    private int zze;
    private int zzf;
    private zzxe zzg;
    private zzaip zzh = zzaip.zza;

    public final int zza() {
        return this.zzf;
    }

    public static zza zzb() {
        return (zza) zzc.zzm();
    }

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzwy$zza */
    /* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
    public static final class zza extends zzajy.zza<zzwy, zza> implements zzale {
        public final zza zza(zzaip zzaip) {
            zzh();
            zzwy.zza((zzwy) this.zza, zzaip);
            return this;
        }

        public final zza zza(zzxe zzxe) {
            zzh();
            zzwy.zza((zzwy) this.zza, zzxe);
            return this;
        }

        private zza() {
            super(zzwy.zzc);
        }
    }

    public static zzwy zza(zzaip zzaip, zzajk zzajk) throws zzakf {
        return (zzwy) zzajy.zza(zzc, zzaip, zzajk);
    }

    public final zzxe zzd() {
        zzxe zzxe = this.zzg;
        return zzxe == null ? zzxe.zzd() : zzxe;
    }

    public final zzaip zze() {
        return this.zzh;
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzwx.zza[i - 1]) {
            case 1:
                return new zzwy();
            case 2:
                return new zza();
            case 3:
                return zza((zzalc) zzc, "\u0000\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000b\u0002ဉ\u0000\u0003\n", new Object[]{"zze", "zzf", "zzg", "zzh"});
            case 4:
                return zzc;
            case 5:
                zzalp<zzwy> zzalp = zzd;
                if (zzalp == null) {
                    synchronized (zzwy.class) {
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

    static /* synthetic */ void zza(zzwy zzwy, zzaip zzaip) {
        zzaip.getClass();
        zzwy.zzh = zzaip;
    }

    static /* synthetic */ void zza(zzwy zzwy, zzxe zzxe) {
        zzxe.getClass();
        zzwy.zzg = zzxe;
        zzwy.zze |= 1;
    }

    static {
        zzwy zzwy = new zzwy();
        zzc = zzwy;
        zzajy.zza(zzwy.class, zzwy);
    }

    private zzwy() {
    }
}
