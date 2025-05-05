package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzajy;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzum  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzum extends zzajy<zzum, zza> implements zzale {
    /* access modifiers changed from: private */
    public static final zzum zzc;
    private static volatile zzalp<zzum> zzd;
    /* access modifiers changed from: private */
    public int zze;
    /* access modifiers changed from: private */
    public int zzf;
    private zzaip zzg = zzaip.zza;

    public static zza zza() {
        return (zza) zzc.zzm();
    }

    public static zzum zzc() {
        return zzc;
    }

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzum$zza */
    /* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
    public static final class zza extends zzajy.zza<zzum, zza> implements zzale {
        public final zza zza(zzup zzup) {
            zzh();
            ((zzum) this.zza).zze = zzup.zza();
            return this;
        }

        public final zza zza(zzur zzur) {
            zzh();
            ((zzum) this.zza).zzf = zzur.zza();
            return this;
        }

        public final zza zza(zzaip zzaip) {
            zzh();
            zzum.zza((zzum) this.zza, zzaip);
            return this;
        }

        private zza() {
            super(zzum.zzc);
        }
    }

    public final zzup zzd() {
        zzup zza2 = zzup.zza(this.zze);
        return zza2 == null ? zzup.UNRECOGNIZED : zza2;
    }

    public final zzur zze() {
        zzur zza2 = zzur.zza(this.zzf);
        return zza2 == null ? zzur.UNRECOGNIZED : zza2;
    }

    public final zzaip zzf() {
        return this.zzg;
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzul.zza[i - 1]) {
            case 1:
                return new zzum();
            case 2:
                return new zza();
            case 3:
                return zza((zzalc) zzc, "\u0000\u0003\u0000\u0000\u0001\u000b\u0003\u0000\u0000\u0000\u0001\f\u0002\f\u000b\n", new Object[]{"zze", "zzf", "zzg"});
            case 4:
                return zzc;
            case 5:
                zzalp<zzum> zzalp = zzd;
                if (zzalp == null) {
                    synchronized (zzum.class) {
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

    static /* synthetic */ void zza(zzum zzum, zzaip zzaip) {
        zzaip.getClass();
        zzum.zzg = zzaip;
    }

    static {
        zzum zzum = new zzum();
        zzc = zzum;
        zzajy.zza(zzum.class, zzum);
    }

    private zzum() {
    }
}
