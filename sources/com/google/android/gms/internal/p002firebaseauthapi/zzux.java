package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzajy;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzux  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzux extends zzajy<zzux, zza> implements zzale {
    /* access modifiers changed from: private */
    public static final zzux zzc;
    private static volatile zzalp<zzux> zzd;
    private int zze;
    private zzva zzf;
    /* access modifiers changed from: private */
    public int zzg;
    private int zzh;

    public final int zza() {
        return this.zzg;
    }

    public final int zzb() {
        return this.zzh;
    }

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzux$zza */
    /* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
    public static final class zza extends zzajy.zza<zzux, zza> implements zzale {
        public final zza zza(int i) {
            zzh();
            ((zzux) this.zza).zzg = i;
            return this;
        }

        public final zza zza(zzva zzva) {
            zzh();
            zzux.zza((zzux) this.zza, zzva);
            return this;
        }

        private zza() {
            super(zzux.zzc);
        }
    }

    public static zza zzc() {
        return (zza) zzc.zzm();
    }

    public static zzux zze() {
        return zzc;
    }

    public static zzux zza(zzaip zzaip, zzajk zzajk) throws zzakf {
        return (zzux) zzajy.zza(zzc, zzaip, zzajk);
    }

    public final zzva zzf() {
        zzva zzva = this.zzf;
        return zzva == null ? zzva.zze() : zzva;
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzuw.zza[i - 1]) {
            case 1:
                return new zzux();
            case 2:
                return new zza();
            case 3:
                return zza((zzalc) zzc, "\u0000\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဉ\u0000\u0002\u000b\u0003\u000b", new Object[]{"zze", "zzf", "zzg", "zzh"});
            case 4:
                return zzc;
            case 5:
                zzalp<zzux> zzalp = zzd;
                if (zzalp == null) {
                    synchronized (zzux.class) {
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

    static /* synthetic */ void zza(zzux zzux, zzva zzva) {
        zzva.getClass();
        zzux.zzf = zzva;
        zzux.zze |= 1;
    }

    static {
        zzux zzux = new zzux();
        zzc = zzux;
        zzajy.zza(zzux.class, zzux);
    }

    private zzux() {
    }
}
