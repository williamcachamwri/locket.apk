package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzajy;

@Deprecated
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzvx  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzvx extends zzajy<zzvx, zza> implements zzale {
    /* access modifiers changed from: private */
    public static final zzvx zzc;
    private static volatile zzalp<zzvx> zzd;
    private String zze = "";
    private String zzf = "";
    private int zzg;
    private boolean zzh;
    private String zzi = "";

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzvx$zza */
    /* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
    public static final class zza extends zzajy.zza<zzvx, zza> implements zzale {
        private zza() {
            super(zzvx.zzc);
        }
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzvz.zza[i - 1]) {
            case 1:
                return new zzvx();
            case 2:
                return new zza();
            case 3:
                return zza((zzalc) zzc, "\u0000\u0005\u0000\u0000\u0001\u0005\u0005\u0000\u0000\u0000\u0001Ȉ\u0002Ȉ\u0003\u000b\u0004\u0007\u0005Ȉ", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi"});
            case 4:
                return zzc;
            case 5:
                zzalp<zzvx> zzalp = zzd;
                if (zzalp == null) {
                    synchronized (zzvx.class) {
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
        zzvx zzvx = new zzvx();
        zzc = zzvx;
        zzajy.zza(zzvx.class, zzvx);
    }

    private zzvx() {
    }
}
