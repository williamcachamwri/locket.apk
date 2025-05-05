package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzjt;
import java.util.List;
import kotlin.text.Typography;

/* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
public final class zzfo {

    /* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
    public static final class zza extends zzjt<zza, C0001zza> implements zzle {
        /* access modifiers changed from: private */
        public static final zza zzc;
        private static volatile zzll<zza> zzd;
        private int zze;
        private int zzf;
        private zzkc<zze> zzg = zzch();
        private zzkc<zzb> zzh = zzch();
        private boolean zzi;
        private boolean zzj;

        /* renamed from: com.google.android.gms.internal.measurement.zzfo$zza$zza  reason: collision with other inner class name */
        /* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
        public static final class C0001zza extends zzjt.zzb<zza, C0001zza> implements zzle {
            public final int zza() {
                return ((zza) this.zza).zzb();
            }

            public final int zzb() {
                return ((zza) this.zza).zzc();
            }

            public final C0001zza zza(int i, zzb.zza zza) {
                zzak();
                zza.zza((zza) this.zza, i, (zzb) ((zzjt) zza.zzai()));
                return this;
            }

            public final C0001zza zza(int i, zze.zza zza) {
                zzak();
                zza.zza((zza) this.zza, i, (zze) ((zzjt) zza.zzai()));
                return this;
            }

            public final zzb zza(int i) {
                return ((zza) this.zza).zza(i);
            }

            public final zze zzb(int i) {
                return ((zza) this.zza).zzb(i);
            }

            private C0001zza() {
                super(zza.zzc);
            }
        }

        public final int zza() {
            return this.zzf;
        }

        public final int zzb() {
            return this.zzh.size();
        }

        public final int zzc() {
            return this.zzg.size();
        }

        public final zzb zza(int i) {
            return (zzb) this.zzh.get(i);
        }

        public final zze zzb(int i) {
            return (zze) this.zzg.get(i);
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzfn.zza[i - 1]) {
                case 1:
                    return new zza();
                case 2:
                    return new C0001zza();
                case 3:
                    return zza((zzlc) zzc, "\u0004\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0002\u0000\u0001င\u0000\u0002\u001b\u0003\u001b\u0004ဇ\u0001\u0005ဇ\u0002", new Object[]{"zze", "zzf", "zzg", zze.class, "zzh", zzb.class, "zzi", "zzj"});
                case 4:
                    return zzc;
                case 5:
                    zzll<zza> zzll = zzd;
                    if (zzll == null) {
                        synchronized (zza.class) {
                            zzll = zzd;
                            if (zzll == null) {
                                zzll = new zzjt.zza<>(zzc);
                                zzd = zzll;
                            }
                        }
                    }
                    return zzll;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        public final List<zzb> zze() {
            return this.zzh;
        }

        public final List<zze> zzf() {
            return this.zzg;
        }

        static /* synthetic */ void zza(zza zza, int i, zzb zzb) {
            zzb.getClass();
            zzkc<zzb> zzkc = zza.zzh;
            if (!zzkc.zzc()) {
                zza.zzh = zzjt.zza(zzkc);
            }
            zza.zzh.set(i, zzb);
        }

        static /* synthetic */ void zza(zza zza, int i, zze zze2) {
            zze2.getClass();
            zzkc<zze> zzkc = zza.zzg;
            if (!zzkc.zzc()) {
                zza.zzg = zzjt.zza(zzkc);
            }
            zza.zzg.set(i, zze2);
        }

        static {
            zza zza = new zza();
            zzc = zza;
            zzjt.zza(zza.class, zza);
        }

        private zza() {
        }

        public final boolean zzg() {
            return (this.zze & 1) != 0;
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
    public static final class zzb extends zzjt<zzb, zza> implements zzle {
        /* access modifiers changed from: private */
        public static final zzb zzc;
        private static volatile zzll<zzb> zzd;
        private int zze;
        private int zzf;
        private String zzg = "";
        private zzkc<zzc> zzh = zzch();
        private boolean zzi;
        private zzd zzj;
        private boolean zzk;
        private boolean zzl;
        private boolean zzm;

        /* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
        public static final class zza extends zzjt.zzb<zzb, zza> implements zzle {
            public final int zza() {
                return ((zzb) this.zza).zza();
            }

            public final zza zza(String str) {
                zzak();
                zzb.zza((zzb) this.zza, str);
                return this;
            }

            public final zza zza(int i, zzc zzc) {
                zzak();
                zzb.zza((zzb) this.zza, i, zzc);
                return this;
            }

            public final zzc zza(int i) {
                return ((zzb) this.zza).zza(i);
            }

            public final String zzb() {
                return ((zzb) this.zza).zzf();
            }

            private zza() {
                super(zzb.zzc);
            }
        }

        public final int zza() {
            return this.zzh.size();
        }

        public final int zzb() {
            return this.zzf;
        }

        public static zza zzc() {
            return (zza) zzc.zzcc();
        }

        public final zzc zza(int i) {
            return (zzc) this.zzh.get(i);
        }

        public final zzd zze() {
            zzd zzd2 = this.zzj;
            return zzd2 == null ? zzd.zzc() : zzd2;
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzfn.zza[i - 1]) {
                case 1:
                    return new zzb();
                case 2:
                    return new zza();
                case 3:
                    return zza((zzlc) zzc, "\u0004\b\u0000\u0001\u0001\b\b\u0000\u0001\u0000\u0001င\u0000\u0002ဈ\u0001\u0003\u001b\u0004ဇ\u0002\u0005ဉ\u0003\u0006ဇ\u0004\u0007ဇ\u0005\bဇ\u0006", new Object[]{"zze", "zzf", "zzg", "zzh", zzc.class, "zzi", "zzj", "zzk", "zzl", "zzm"});
                case 4:
                    return zzc;
                case 5:
                    zzll<zzb> zzll = zzd;
                    if (zzll == null) {
                        synchronized (zzb.class) {
                            zzll = zzd;
                            if (zzll == null) {
                                zzll = new zzjt.zza<>(zzc);
                                zzd = zzll;
                            }
                        }
                    }
                    return zzll;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        public final String zzf() {
            return this.zzg;
        }

        public final List<zzc> zzg() {
            return this.zzh;
        }

        static /* synthetic */ void zza(zzb zzb, String str) {
            str.getClass();
            zzb.zze |= 2;
            zzb.zzg = str;
        }

        static /* synthetic */ void zza(zzb zzb, int i, zzc zzc2) {
            zzc2.getClass();
            zzkc<zzc> zzkc = zzb.zzh;
            if (!zzkc.zzc()) {
                zzb.zzh = zzjt.zza(zzkc);
            }
            zzb.zzh.set(i, zzc2);
        }

        static {
            zzb zzb = new zzb();
            zzc = zzb;
            zzjt.zza(zzb.class, zzb);
        }

        private zzb() {
        }

        public final boolean zzh() {
            return this.zzk;
        }

        public final boolean zzi() {
            return this.zzl;
        }

        public final boolean zzj() {
            return this.zzm;
        }

        public final boolean zzk() {
            return (this.zze & 8) != 0;
        }

        public final boolean zzl() {
            return (this.zze & 1) != 0;
        }

        public final boolean zzm() {
            return (this.zze & 64) != 0;
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
    public static final class zzc extends zzjt<zzc, zza> implements zzle {
        /* access modifiers changed from: private */
        public static final zzc zzc;
        private static volatile zzll<zzc> zzd;
        private int zze;
        private zzf zzf;
        private zzd zzg;
        private boolean zzh;
        private String zzi = "";

        public static zzc zzb() {
            return zzc;
        }

        public final zzd zzc() {
            zzd zzd2 = this.zzg;
            return zzd2 == null ? zzd.zzc() : zzd2;
        }

        /* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
        public static final class zza extends zzjt.zzb<zzc, zza> implements zzle {
            public final zza zza(String str) {
                zzak();
                zzc.zza((zzc) this.zza, str);
                return this;
            }

            private zza() {
                super(zzc.zzc);
            }
        }

        public final zzf zzd() {
            zzf zzf2 = this.zzf;
            return zzf2 == null ? zzf.zzd() : zzf2;
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzfn.zza[i - 1]) {
                case 1:
                    return new zzc();
                case 2:
                    return new zza();
                case 3:
                    return zza((zzlc) zzc, "\u0004\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001\u0003ဇ\u0002\u0004ဈ\u0003", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi"});
                case 4:
                    return zzc;
                case 5:
                    zzll<zzc> zzll = zzd;
                    if (zzll == null) {
                        synchronized (zzc.class) {
                            zzll = zzd;
                            if (zzll == null) {
                                zzll = new zzjt.zza<>(zzc);
                                zzd = zzll;
                            }
                        }
                    }
                    return zzll;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        public final String zze() {
            return this.zzi;
        }

        static /* synthetic */ void zza(zzc zzc2, String str) {
            str.getClass();
            zzc2.zze |= 8;
            zzc2.zzi = str;
        }

        static {
            zzc zzc2 = new zzc();
            zzc = zzc2;
            zzjt.zza(zzc.class, zzc2);
        }

        private zzc() {
        }

        public final boolean zzf() {
            return this.zzh;
        }

        public final boolean zzg() {
            return (this.zze & 4) != 0;
        }

        public final boolean zzh() {
            return (this.zze & 2) != 0;
        }

        public final boolean zzi() {
            return (this.zze & 8) != 0;
        }

        public final boolean zzj() {
            return (this.zze & 1) != 0;
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
    public static final class zze extends zzjt<zze, zza> implements zzle {
        /* access modifiers changed from: private */
        public static final zze zzc;
        private static volatile zzll<zze> zzd;
        private int zze;
        private int zzf;
        private String zzg = "";
        private zzc zzh;
        private boolean zzi;
        private boolean zzj;
        private boolean zzk;

        public final int zza() {
            return this.zzf;
        }

        public final zzc zzb() {
            zzc zzc2 = this.zzh;
            return zzc2 == null ? zzc.zzb() : zzc2;
        }

        /* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
        public static final class zza extends zzjt.zzb<zze, zza> implements zzle {
            public final zza zza(String str) {
                zzak();
                zze.zza((zze) this.zza, str);
                return this;
            }

            private zza() {
                super(zze.zzc);
            }
        }

        public static zza zzc() {
            return (zza) zzc.zzcc();
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzfn.zza[i - 1]) {
                case 1:
                    return new zze();
                case 2:
                    return new zza();
                case 3:
                    return zza((zzlc) zzc, "\u0004\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001င\u0000\u0002ဈ\u0001\u0003ဉ\u0002\u0004ဇ\u0003\u0005ဇ\u0004\u0006ဇ\u0005", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk"});
                case 4:
                    return zzc;
                case 5:
                    zzll<zze> zzll = zzd;
                    if (zzll == null) {
                        synchronized (zze.class) {
                            zzll = zzd;
                            if (zzll == null) {
                                zzll = new zzjt.zza<>(zzc);
                                zzd = zzll;
                            }
                        }
                    }
                    return zzll;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        public final String zze() {
            return this.zzg;
        }

        static /* synthetic */ void zza(zze zze2, String str) {
            str.getClass();
            zze2.zze |= 2;
            zze2.zzg = str;
        }

        static {
            zze zze2 = new zze();
            zzc = zze2;
            zzjt.zza(zze.class, zze2);
        }

        private zze() {
        }

        public final boolean zzf() {
            return this.zzi;
        }

        public final boolean zzg() {
            return this.zzj;
        }

        public final boolean zzh() {
            return this.zzk;
        }

        public final boolean zzi() {
            return (this.zze & 1) != 0;
        }

        public final boolean zzj() {
            return (this.zze & 32) != 0;
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
    public static final class zzf extends zzjt<zzf, zza> implements zzle {
        /* access modifiers changed from: private */
        public static final zzf zzc;
        private static volatile zzll<zzf> zzd;
        private int zze;
        private int zzf;
        private String zzg = "";
        private boolean zzh;
        private zzkc<String> zzi = zzjt.zzch();

        /* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
        public static final class zza extends zzjt.zzb<zzf, zza> implements zzle {
            private zza() {
                super(zzf.zzc);
            }
        }

        public final int zza() {
            return this.zzi.size();
        }

        public final zzb zzb() {
            zzb zza2 = zzb.zza(this.zzf);
            return zza2 == null ? zzb.UNKNOWN_MATCH_TYPE : zza2;
        }

        public static zzf zzd() {
            return zzc;
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzfn.zza[i - 1]) {
                case 1:
                    return new zzf();
                case 2:
                    return new zza();
                case 3:
                    return zza((zzlc) zzc, "\u0004\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0001\u0000\u0001᠌\u0000\u0002ဈ\u0001\u0003ဇ\u0002\u0004\u001a", new Object[]{"zze", "zzf", zzb.zzb(), "zzg", "zzh", "zzi"});
                case 4:
                    return zzc;
                case 5:
                    zzll<zzf> zzll = zzd;
                    if (zzll == null) {
                        synchronized (zzf.class) {
                            zzll = zzd;
                            if (zzll == null) {
                                zzll = new zzjt.zza<>(zzc);
                                zzd = zzll;
                            }
                        }
                    }
                    return zzll;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        /* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
        public enum zzb implements zzjy {
            UNKNOWN_MATCH_TYPE(0),
            REGEXP(1),
            BEGINS_WITH(2),
            ENDS_WITH(3),
            PARTIAL(4),
            EXACT(5),
            IN_LIST(6);
            
            private final int zzi;

            public final int zza() {
                return this.zzi;
            }

            public static zzb zza(int i) {
                switch (i) {
                    case 0:
                        return UNKNOWN_MATCH_TYPE;
                    case 1:
                        return REGEXP;
                    case 2:
                        return BEGINS_WITH;
                    case 3:
                        return ENDS_WITH;
                    case 4:
                        return PARTIAL;
                    case 5:
                        return EXACT;
                    case 6:
                        return IN_LIST;
                    default:
                        return null;
                }
            }

            public static zzjx zzb() {
                return zzfq.zza;
            }

            public final String toString() {
                StringBuilder sb = new StringBuilder("<");
                sb.append(getClass().getName()).append('@').append(Integer.toHexString(System.identityHashCode(this)));
                sb.append(" number=").append(this.zzi);
                return sb.append(" name=").append(name()).append(Typography.greater).toString();
            }

            private zzb(int i) {
                this.zzi = i;
            }
        }

        public final String zze() {
            return this.zzg;
        }

        public final List<String> zzf() {
            return this.zzi;
        }

        static {
            zzf zzf2 = new zzf();
            zzc = zzf2;
            zzjt.zza(zzf.class, zzf2);
        }

        private zzf() {
        }

        public final boolean zzg() {
            return this.zzh;
        }

        public final boolean zzh() {
            return (this.zze & 4) != 0;
        }

        public final boolean zzi() {
            return (this.zze & 2) != 0;
        }

        public final boolean zzj() {
            return (this.zze & 1) != 0;
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
    public static final class zzd extends zzjt<zzd, zza> implements zzle {
        /* access modifiers changed from: private */
        public static final zzd zzc;
        private static volatile zzll<zzd> zzd;
        private int zze;
        private int zzf;
        private boolean zzg;
        private String zzh = "";
        private String zzi = "";
        private String zzj = "";

        /* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
        public static final class zza extends zzjt.zzb<zzd, zza> implements zzle {
            private zza() {
                super(zzd.zzc);
            }
        }

        public final zzb zza() {
            zzb zza2 = zzb.zza(this.zzf);
            return zza2 == null ? zzb.UNKNOWN_COMPARISON_TYPE : zza2;
        }

        public static zzd zzc() {
            return zzc;
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzfn.zza[i - 1]) {
                case 1:
                    return new zzd();
                case 2:
                    return new zza();
                case 3:
                    return zza((zzlc) zzc, "\u0004\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001᠌\u0000\u0002ဇ\u0001\u0003ဈ\u0002\u0004ဈ\u0003\u0005ဈ\u0004", new Object[]{"zze", "zzf", zzb.zzb(), "zzg", "zzh", "zzi", "zzj"});
                case 4:
                    return zzc;
                case 5:
                    zzll<zzd> zzll = zzd;
                    if (zzll == null) {
                        synchronized (zzd.class) {
                            zzll = zzd;
                            if (zzll == null) {
                                zzll = new zzjt.zza<>(zzc);
                                zzd = zzll;
                            }
                        }
                    }
                    return zzll;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        /* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
        public enum zzb implements zzjy {
            UNKNOWN_COMPARISON_TYPE(0),
            LESS_THAN(1),
            GREATER_THAN(2),
            EQUAL(3),
            BETWEEN(4);
            
            private final int zzg;

            public final int zza() {
                return this.zzg;
            }

            public static zzb zza(int i) {
                if (i == 0) {
                    return UNKNOWN_COMPARISON_TYPE;
                }
                if (i == 1) {
                    return LESS_THAN;
                }
                if (i == 2) {
                    return GREATER_THAN;
                }
                if (i == 3) {
                    return EQUAL;
                }
                if (i != 4) {
                    return null;
                }
                return BETWEEN;
            }

            public static zzjx zzb() {
                return zzfp.zza;
            }

            public final String toString() {
                StringBuilder sb = new StringBuilder("<");
                sb.append(getClass().getName()).append('@').append(Integer.toHexString(System.identityHashCode(this)));
                sb.append(" number=").append(this.zzg);
                return sb.append(" name=").append(name()).append(Typography.greater).toString();
            }

            private zzb(int i) {
                this.zzg = i;
            }
        }

        public final String zzd() {
            return this.zzh;
        }

        public final String zze() {
            return this.zzj;
        }

        public final String zzf() {
            return this.zzi;
        }

        static {
            zzd zzd2 = new zzd();
            zzc = zzd2;
            zzjt.zza(zzd.class, zzd2);
        }

        private zzd() {
        }

        public final boolean zzg() {
            return this.zzg;
        }

        public final boolean zzh() {
            return (this.zze & 1) != 0;
        }

        public final boolean zzi() {
            return (this.zze & 4) != 0;
        }

        public final boolean zzj() {
            return (this.zze & 2) != 0;
        }

        public final boolean zzk() {
            return (this.zze & 16) != 0;
        }

        public final boolean zzl() {
            return (this.zze & 8) != 0;
        }
    }
}
