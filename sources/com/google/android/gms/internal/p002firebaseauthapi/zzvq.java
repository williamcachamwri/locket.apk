package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzajy;
import kotlin.text.Typography;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzvq  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzvq extends zzajy<zzvq, zza> implements zzale {
    /* access modifiers changed from: private */
    public static final zzvq zzc;
    private static volatile zzalp<zzvq> zzd;
    private String zze = "";
    private zzaip zzf = zzaip.zza;
    /* access modifiers changed from: private */
    public int zzg;

    public static zza zza() {
        return (zza) zzc.zzm();
    }

    public final zzb zzb() {
        zzb zza2 = zzb.zza(this.zzg);
        return zza2 == null ? zzb.UNRECOGNIZED : zza2;
    }

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzvq$zza */
    /* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
    public static final class zza extends zzajy.zza<zzvq, zza> implements zzale {
        public final zza zza(zzb zzb) {
            zzh();
            ((zzvq) this.zza).zzg = zzb.zza();
            return this;
        }

        public final zza zza(String str) {
            zzh();
            zzvq.zza((zzvq) this.zza, str);
            return this;
        }

        public final zza zza(zzaip zzaip) {
            zzh();
            zzvq.zza((zzvq) this.zza, zzaip);
            return this;
        }

        private zza() {
            super(zzvq.zzc);
        }
    }

    public static zzvq zzd() {
        return zzc;
    }

    public final zzaip zze() {
        return this.zzf;
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzvs.zza[i - 1]) {
            case 1:
                return new zzvq();
            case 2:
                return new zza();
            case 3:
                return zza((zzalc) zzc, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001Ȉ\u0002\n\u0003\f", new Object[]{"zze", "zzf", "zzg"});
            case 4:
                return zzc;
            case 5:
                zzalp<zzvq> zzalp = zzd;
                if (zzalp == null) {
                    synchronized (zzvq.class) {
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

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzvq$zzb */
    /* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
    public enum zzb implements zzaka {
        UNKNOWN_KEYMATERIAL(0),
        SYMMETRIC(1),
        ASYMMETRIC_PRIVATE(2),
        ASYMMETRIC_PUBLIC(3),
        REMOTE(4),
        UNRECOGNIZED(-1);
        
        private final int zzh;

        public final int zza() {
            if (this != UNRECOGNIZED) {
                return this.zzh;
            }
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }

        public static zzb zza(int i) {
            if (i == 0) {
                return UNKNOWN_KEYMATERIAL;
            }
            if (i == 1) {
                return SYMMETRIC;
            }
            if (i == 2) {
                return ASYMMETRIC_PRIVATE;
            }
            if (i == 3) {
                return ASYMMETRIC_PUBLIC;
            }
            if (i != 4) {
                return null;
            }
            return REMOTE;
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder("<");
            sb.append(getClass().getName()).append('@').append(Integer.toHexString(System.identityHashCode(this)));
            if (this != UNRECOGNIZED) {
                sb.append(" number=").append(zza());
            }
            return sb.append(" name=").append(name()).append(Typography.greater).toString();
        }

        private zzb(int i) {
            this.zzh = i;
        }
    }

    public final String zzf() {
        return this.zze;
    }

    static /* synthetic */ void zza(zzvq zzvq, String str) {
        str.getClass();
        zzvq.zze = str;
    }

    static /* synthetic */ void zza(zzvq zzvq, zzaip zzaip) {
        zzaip.getClass();
        zzvq.zzf = zzaip;
    }

    static {
        zzvq zzvq = new zzvq();
        zzc = zzvq;
        zzajy.zza(zzvq.class, zzvq);
    }

    private zzvq() {
    }
}
