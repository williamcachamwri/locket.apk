package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzajy;
import com.google.android.gms.internal.p002firebaseauthapi.zzvq;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzwa  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzwa extends zzajy<zzwa, zza> implements zzale {
    /* access modifiers changed from: private */
    public static final zzwa zzc;
    private static volatile zzalp<zzwa> zzd;
    /* access modifiers changed from: private */
    public int zze;
    private zzakc<zzb> zzf = zzp();

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzwa$zzb */
    /* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
    public static final class zzb extends zzajy<zzb, zza> implements zzale {
        /* access modifiers changed from: private */
        public static final zzb zzc;
        private static volatile zzalp<zzb> zzd;
        private int zze;
        private zzvq zzf;
        /* access modifiers changed from: private */
        public int zzg;
        /* access modifiers changed from: private */
        public int zzh;
        /* access modifiers changed from: private */
        public int zzi;

        public final int zza() {
            return this.zzh;
        }

        public final zzvq zzb() {
            zzvq zzvq = this.zzf;
            return zzvq == null ? zzvq.zzd() : zzvq;
        }

        /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzwa$zzb$zza */
        /* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
        public static final class zza extends zzajy.zza<zzb, zza> implements zzale {
            public final zza zza(zzvq.zza zza) {
                zzh();
                zzb.zza((zzb) this.zza, (zzvq) ((zzajy) zza.zze()));
                return this;
            }

            public final zza zza(zzvq zzvq) {
                zzh();
                zzb.zza((zzb) this.zza, zzvq);
                return this;
            }

            public final zza zza(int i) {
                zzh();
                ((zzb) this.zza).zzh = i;
                return this;
            }

            public final zza zza(zzws zzws) {
                zzh();
                ((zzb) this.zza).zzi = zzws.zza();
                return this;
            }

            public final zza zza(zzvv zzvv) {
                zzh();
                ((zzb) this.zza).zzg = zzvv.zza();
                return this;
            }

            private zza() {
                super(zzb.zzc);
            }
        }

        public final zzvv zzc() {
            zzvv zza2 = zzvv.zza(this.zzg);
            return zza2 == null ? zzvv.UNRECOGNIZED : zza2;
        }

        public static zza zzd() {
            return (zza) zzc.zzm();
        }

        public final zzws zzf() {
            zzws zza2 = zzws.zza(this.zzi);
            return zza2 == null ? zzws.UNRECOGNIZED : zza2;
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzwc.zza[i - 1]) {
                case 1:
                    return new zzb();
                case 2:
                    return new zza();
                case 3:
                    return zza((zzalc) zzc, "\u0000\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဉ\u0000\u0002\f\u0003\u000b\u0004\f", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi"});
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

        static /* synthetic */ void zza(zzb zzb, zzvq zzvq) {
            zzvq.getClass();
            zzb.zzf = zzvq;
            zzb.zze |= 1;
        }

        static {
            zzb zzb = new zzb();
            zzc = zzb;
            zzajy.zza(zzb.class, zzb);
        }

        private zzb() {
        }

        public final boolean e_() {
            return (this.zze & 1) != 0;
        }
    }

    public final int zza() {
        return this.zzf.size();
    }

    public final int zzb() {
        return this.zze;
    }

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzwa$zza */
    /* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
    public static final class zza extends zzajy.zza<zzwa, zza> implements zzale {
        public final zza zza(zzb zzb) {
            zzh();
            zzwa.zza((zzwa) this.zza, zzb);
            return this;
        }

        public final zza zza(int i) {
            zzh();
            ((zzwa) this.zza).zze = i;
            return this;
        }

        private zza() {
            super(zzwa.zzc);
        }
    }

    public static zza zzc() {
        return (zza) zzc.zzm();
    }

    public final zzb zza(int i) {
        return (zzb) this.zzf.get(i);
    }

    public static zzwa zza(InputStream inputStream, zzajk zzajk) throws IOException {
        return (zzwa) zzajy.zza(zzc, inputStream, zzajk);
    }

    public static zzwa zza(byte[] bArr, zzajk zzajk) throws zzakf {
        return (zzwa) zzajy.zza(zzc, bArr, zzajk);
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzwc.zza[i - 1]) {
            case 1:
                return new zzwa();
            case 2:
                return new zza();
            case 3:
                return zza((zzalc) zzc, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u000b\u0002\u001b", new Object[]{"zze", "zzf", zzb.class});
            case 4:
                return zzc;
            case 5:
                zzalp<zzwa> zzalp = zzd;
                if (zzalp == null) {
                    synchronized (zzwa.class) {
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

    public final List<zzb> zze() {
        return this.zzf;
    }

    static /* synthetic */ void zza(zzwa zzwa, zzb zzb2) {
        zzb2.getClass();
        zzakc<zzb> zzakc = zzwa.zzf;
        if (!zzakc.zzc()) {
            zzwa.zzf = zzajy.zza(zzakc);
        }
        zzwa.zzf.add(zzb2);
    }

    static {
        zzwa zzwa = new zzwa();
        zzc = zzwa;
        zzajy.zza(zzwa.class, zzwa);
    }

    private zzwa() {
    }
}
