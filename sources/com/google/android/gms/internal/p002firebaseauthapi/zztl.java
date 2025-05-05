package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzajy;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zztl  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zztl extends zzajy<zztl, zza> implements zzale {
    /* access modifiers changed from: private */
    public static final zztl zzc;
    private static volatile zzalp<zztl> zzd;
    /* access modifiers changed from: private */
    public int zze;
    private int zzf;

    public final int zza() {
        return this.zze;
    }

    public final int zzb() {
        return this.zzf;
    }

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zztl$zza */
    /* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
    public static final class zza extends zzajy.zza<zztl, zza> implements zzale {
        public final zza zza(int i) {
            zzh();
            ((zztl) this.zza).zze = i;
            return this;
        }

        private zza() {
            super(zztl.zzc);
        }
    }

    public static zza zzc() {
        return (zza) zzc.zzm();
    }

    public static zztl zza(zzaip zzaip, zzajk zzajk) throws zzakf {
        return (zztl) zzajy.zza(zzc, zzaip, zzajk);
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zztn.zza[i - 1]) {
            case 1:
                return new zztl();
            case 2:
                return new zza();
            case 3:
                return zza((zzalc) zzc, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u000b\u0002\u000b", new Object[]{"zze", "zzf"});
            case 4:
                return zzc;
            case 5:
                zzalp<zztl> zzalp = zzd;
                if (zzalp == null) {
                    synchronized (zztl.class) {
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

    static {
        zztl zztl = new zztl();
        zzc = zztl;
        zzajy.zza(zztl.class, zztl);
    }

    private zztl() {
    }
}
