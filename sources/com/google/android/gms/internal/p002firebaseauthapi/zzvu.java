package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzajy;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzvu  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzvu extends zzajy<zzvu, zza> implements zzale {
    /* access modifiers changed from: private */
    public static final zzvu zzc;
    private static volatile zzalp<zzvu> zzd;
    private String zze = "";
    private zzaip zzf = zzaip.zza;
    /* access modifiers changed from: private */
    public int zzg;

    public static zza zza() {
        return (zza) zzc.zzm();
    }

    public static zza zza(zzvu zzvu) {
        return (zza) zzc.zzm().zza(zzvu);
    }

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzvu$zza */
    /* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
    public static final class zza extends zzajy.zza<zzvu, zza> implements zzale {
        public final zza zza(zzws zzws) {
            zzh();
            ((zzvu) this.zza).zzg = zzws.zza();
            return this;
        }

        public final zza zza(String str) {
            zzh();
            zzvu.zza((zzvu) this.zza, str);
            return this;
        }

        public final zza zza(zzaip zzaip) {
            zzh();
            zzvu.zza((zzvu) this.zza, zzaip);
            return this;
        }

        private zza() {
            super(zzvu.zzc);
        }
    }

    public static zzvu zzc() {
        return zzc;
    }

    public static zzvu zza(byte[] bArr, zzajk zzajk) throws zzakf {
        return (zzvu) zzajy.zza(zzc, bArr, zzajk);
    }

    public final zzws zzd() {
        zzws zza2 = zzws.zza(this.zzg);
        return zza2 == null ? zzws.UNRECOGNIZED : zza2;
    }

    public final zzaip zze() {
        return this.zzf;
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzvw.zza[i - 1]) {
            case 1:
                return new zzvu();
            case 2:
                return new zza();
            case 3:
                return zza((zzalc) zzc, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001Ȉ\u0002\n\u0003\f", new Object[]{"zze", "zzf", "zzg"});
            case 4:
                return zzc;
            case 5:
                zzalp<zzvu> zzalp = zzd;
                if (zzalp == null) {
                    synchronized (zzvu.class) {
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

    public final String zzf() {
        return this.zze;
    }

    static /* synthetic */ void zza(zzvu zzvu, String str) {
        str.getClass();
        zzvu.zze = str;
    }

    static /* synthetic */ void zza(zzvu zzvu, zzaip zzaip) {
        zzaip.getClass();
        zzvu.zzf = zzaip;
    }

    static {
        zzvu zzvu = new zzvu();
        zzc = zzvu;
        zzajy.zza(zzvu.class, zzvu);
    }

    private zzvu() {
    }
}
