package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzajy;

@Deprecated
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzwv  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzwv extends zzajy<zzwv, zza> implements zzale {
    /* access modifiers changed from: private */
    public static final zzwv zzc;
    private static volatile zzalp<zzwv> zzd;
    private String zze = "";
    private zzakc<zzvx> zzf = zzp();

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzwv$zza */
    /* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
    public static final class zza extends zzajy.zza<zzwv, zza> implements zzale {
        private zza() {
            super(zzwv.zzc);
        }
    }

    public static zzwv zzb() {
        return zzc;
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzwu.zza[i - 1]) {
            case 1:
                return new zzwv();
            case 2:
                return new zza();
            case 3:
                return zza((zzalc) zzc, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001Ȉ\u0002\u001b", new Object[]{"zze", "zzf", zzvx.class});
            case 4:
                return zzc;
            case 5:
                zzalp<zzwv> zzalp = zzd;
                if (zzalp == null) {
                    synchronized (zzwv.class) {
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
        zzwv zzwv = new zzwv();
        zzc = zzwv;
        zzajy.zza(zzwv.class, zzwv);
    }

    private zzwv() {
    }
}
