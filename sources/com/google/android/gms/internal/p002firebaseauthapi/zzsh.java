package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzajy;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzsh  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzsh extends zzajy<zzsh, zza> implements zzale {
    /* access modifiers changed from: private */
    public static final zzsh zzc;
    private static volatile zzalp<zzsh> zzd;
    private int zze;
    private zzsk zzf;
    /* access modifiers changed from: private */
    public int zzg;

    public final int zza() {
        return this.zzg;
    }

    public static zza zzb() {
        return (zza) zzc.zzm();
    }

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzsh$zza */
    /* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
    public static final class zza extends zzajy.zza<zzsh, zza> implements zzale {
        public final zza zza(int i) {
            zzh();
            ((zzsh) this.zza).zzg = i;
            return this;
        }

        public final zza zza(zzsk zzsk) {
            zzh();
            zzsh.zza((zzsh) this.zza, zzsk);
            return this;
        }

        private zza() {
            super(zzsh.zzc);
        }
    }

    public static zzsh zzd() {
        return zzc;
    }

    public final zzsk zze() {
        zzsk zzsk = this.zzf;
        return zzsk == null ? zzsk.zzd() : zzsk;
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzsj.zza[i - 1]) {
            case 1:
                return new zzsh();
            case 2:
                return new zza();
            case 3:
                return zza((zzalc) zzc, "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဉ\u0000\u0002\u000b", new Object[]{"zze", "zzf", "zzg"});
            case 4:
                return zzc;
            case 5:
                zzalp<zzsh> zzalp = zzd;
                if (zzalp == null) {
                    synchronized (zzsh.class) {
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

    static /* synthetic */ void zza(zzsh zzsh, zzsk zzsk) {
        zzsk.getClass();
        zzsh.zzf = zzsk;
        zzsh.zze |= 1;
    }

    static {
        zzsh zzsh = new zzsh();
        zzc = zzsh;
        zzajy.zza(zzsh.class, zzsh);
    }

    private zzsh() {
    }
}
