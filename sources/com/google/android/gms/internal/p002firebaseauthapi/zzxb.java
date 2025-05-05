package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzajy;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzxb  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzxb extends zzajy<zzxb, zza> implements zzale {
    /* access modifiers changed from: private */
    public static final zzxb zzc;
    private static volatile zzalp<zzxb> zzd;
    private int zze;
    private int zzf;
    private zzxe zzg;

    public final int zza() {
        return this.zzf;
    }

    public static zza zzb() {
        return (zza) zzc.zzm();
    }

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzxb$zza */
    /* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
    public static final class zza extends zzajy.zza<zzxb, zza> implements zzale {
        public final zza zza(zzxe zzxe) {
            zzh();
            zzxb.zza((zzxb) this.zza, zzxe);
            return this;
        }

        private zza() {
            super(zzxb.zzc);
        }
    }

    public static zzxb zza(zzaip zzaip, zzajk zzajk) throws zzakf {
        return (zzxb) zzajy.zza(zzc, zzaip, zzajk);
    }

    public final zzxe zzd() {
        zzxe zzxe = this.zzg;
        return zzxe == null ? zzxe.zzd() : zzxe;
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzxa.zza[i - 1]) {
            case 1:
                return new zzxb();
            case 2:
                return new zza();
            case 3:
                return zza((zzalc) zzc, "\u0000\u0002\u0000\u0001\u0001\u0003\u0002\u0000\u0000\u0000\u0001\u000b\u0003ဉ\u0000", new Object[]{"zze", "zzf", "zzg"});
            case 4:
                return zzc;
            case 5:
                zzalp<zzxb> zzalp = zzd;
                if (zzalp == null) {
                    synchronized (zzxb.class) {
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

    static /* synthetic */ void zza(zzxb zzxb, zzxe zzxe) {
        zzxe.getClass();
        zzxb.zzg = zzxe;
        zzxb.zze |= 1;
    }

    static {
        zzxb zzxb = new zzxb();
        zzc = zzxb;
        zzajy.zza(zzxb.class, zzxb);
    }

    private zzxb() {
    }
}
