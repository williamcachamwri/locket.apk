package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzajy;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzwp  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzwp extends zzajy<zzwp, zza> implements zzale {
    /* access modifiers changed from: private */
    public static final zzwp zzc;
    private static volatile zzalp<zzwp> zzd;
    private int zze;
    private String zzf = "";
    private zzvu zzg;

    public final zzvu zza() {
        zzvu zzvu = this.zzg;
        return zzvu == null ? zzvu.zzc() : zzvu;
    }

    public static zza zzb() {
        return (zza) zzc.zzm();
    }

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzwp$zza */
    /* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
    public static final class zza extends zzajy.zza<zzwp, zza> implements zzale {
        public final zza zza(zzvu zzvu) {
            zzh();
            zzwp.zza((zzwp) this.zza, zzvu);
            return this;
        }

        public final zza zza(String str) {
            zzh();
            zzwp.zza((zzwp) this.zza, str);
            return this;
        }

        private zza() {
            super(zzwp.zzc);
        }
    }

    public static zzwp zzd() {
        return zzc;
    }

    public static zzwp zza(zzaip zzaip, zzajk zzajk) throws zzakf {
        return (zzwp) zzajy.zza(zzc, zzaip, zzajk);
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzwr.zza[i - 1]) {
            case 1:
                return new zzwp();
            case 2:
                return new zza();
            case 3:
                return zza((zzalc) zzc, "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001Ȉ\u0002ဉ\u0000", new Object[]{"zze", "zzf", "zzg"});
            case 4:
                return zzc;
            case 5:
                zzalp<zzwp> zzalp = zzd;
                if (zzalp == null) {
                    synchronized (zzwp.class) {
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

    public final String zze() {
        return this.zzf;
    }

    static /* synthetic */ void zza(zzwp zzwp, zzvu zzvu) {
        zzvu.getClass();
        zzwp.zzg = zzvu;
        zzwp.zze |= 1;
    }

    static /* synthetic */ void zza(zzwp zzwp, String str) {
        str.getClass();
        zzwp.zzf = str;
    }

    static {
        zzwp zzwp = new zzwp();
        zzc = zzwp;
        zzajy.zza(zzwp.class, zzwp);
    }

    private zzwp() {
    }
}
