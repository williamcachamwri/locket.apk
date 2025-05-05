package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzajy;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzwd  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzwd extends zzajy<zzwd, zza> implements zzale {
    /* access modifiers changed from: private */
    public static final zzwd zzc;
    private static volatile zzalp<zzwd> zzd;
    /* access modifiers changed from: private */
    public int zze;
    private zzakc<zzb> zzf = zzp();

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzwd$zzb */
    /* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
    public static final class zzb extends zzajy<zzb, zza> implements zzale {
        /* access modifiers changed from: private */
        public static final zzb zzc;
        private static volatile zzalp<zzb> zzd;
        private String zze = "";
        /* access modifiers changed from: private */
        public int zzf;
        /* access modifiers changed from: private */
        public int zzg;
        /* access modifiers changed from: private */
        public int zzh;

        public static zza zza() {
            return (zza) zzc.zzm();
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzwf.zza[i - 1]) {
                case 1:
                    return new zzb();
                case 2:
                    return new zza();
                case 3:
                    return zza((zzalc) zzc, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001Ȉ\u0002\f\u0003\u000b\u0004\f", new Object[]{"zze", "zzf", "zzg", "zzh"});
                case 4:
                    return zzc;
                case 5:
                    zzalp<zzb> zzalp = zzd;
                    if (zzalp == null) {
                        synchronized (zzb.class) {
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

        /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzwd$zzb$zza */
        /* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
        public static final class zza extends zzajy.zza<zzb, zza> implements zzale {
            public final zza zza(int i) {
                zzh();
                ((zzb) this.zza).zzg = i;
                return this;
            }

            public final zza zza(zzws zzws) {
                zzh();
                ((zzb) this.zza).zzh = zzws.zza();
                return this;
            }

            public final zza zza(zzvv zzvv) {
                zzh();
                ((zzb) this.zza).zzf = zzvv.zza();
                return this;
            }

            public final zza zza(String str) {
                zzh();
                zzb.zza((zzb) this.zza, str);
                return this;
            }

            private zza() {
                super(zzb.zzc);
            }
        }

        static /* synthetic */ void zza(zzb zzb, String str) {
            str.getClass();
            zzb.zze = str;
        }

        static {
            zzb zzb = new zzb();
            zzc = zzb;
            zzajy.zza(zzb.class, zzb);
        }

        private zzb() {
        }
    }

    public static zza zza() {
        return (zza) zzc.zzm();
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzwf.zza[i - 1]) {
            case 1:
                return new zzwd();
            case 2:
                return new zza();
            case 3:
                return zza((zzalc) zzc, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u000b\u0002\u001b", new Object[]{"zze", "zzf", zzb.class});
            case 4:
                return zzc;
            case 5:
                zzalp<zzwd> zzalp = zzd;
                if (zzalp == null) {
                    synchronized (zzwd.class) {
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

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzwd$zza */
    /* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
    public static final class zza extends zzajy.zza<zzwd, zza> implements zzale {
        public final zza zza(zzb zzb) {
            zzh();
            zzwd.zza((zzwd) this.zza, zzb);
            return this;
        }

        public final zza zza(int i) {
            zzh();
            ((zzwd) this.zza).zze = i;
            return this;
        }

        private zza() {
            super(zzwd.zzc);
        }
    }

    static /* synthetic */ void zza(zzwd zzwd, zzb zzb2) {
        zzb2.getClass();
        zzakc<zzb> zzakc = zzwd.zzf;
        if (!zzakc.zzc()) {
            zzwd.zzf = zzajy.zza(zzakc);
        }
        zzwd.zzf.add(zzb2);
    }

    static {
        zzwd zzwd = new zzwd();
        zzc = zzwd;
        zzajy.zza(zzwd.class, zzwd);
    }

    private zzwd() {
    }
}
