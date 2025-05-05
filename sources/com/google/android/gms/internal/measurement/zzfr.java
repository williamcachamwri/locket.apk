package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzfo;
import com.google.android.gms.internal.measurement.zzgd;
import com.google.android.gms.internal.measurement.zzjt;
import java.util.Collections;
import java.util.List;
import kotlin.text.Typography;

/* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
public final class zzfr {

    /* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
    public static final class zza extends zzjt<zza, zzb> implements zzle {
        /* access modifiers changed from: private */
        public static final zza zzc;
        private static volatile zzll<zza> zzd;
        private int zze;
        private zzkc<C0002zza> zzf = zzch();
        private zzkc<zzc> zzg = zzch();
        private zzkc<zzf> zzh = zzch();
        private boolean zzi;
        private zzkc<C0002zza> zzj = zzch();

        /* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
        public static final class zzb extends zzjt.zzb<zza, zzb> implements zzle {
            private zzb() {
                super(zza.zzc);
            }
        }

        public static zza zzb() {
            return zzc;
        }

        /* renamed from: com.google.android.gms.internal.measurement.zzfr$zza$zza  reason: collision with other inner class name */
        /* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
        public static final class C0002zza extends zzjt<C0002zza, C0003zza> implements zzle {
            /* access modifiers changed from: private */
            public static final C0002zza zzc;
            private static volatile zzll<C0002zza> zzd;
            private int zze;
            private int zzf;
            private int zzg;

            /* renamed from: com.google.android.gms.internal.measurement.zzfr$zza$zza$zza  reason: collision with other inner class name */
            /* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
            public static final class C0003zza extends zzjt.zzb<C0002zza, C0003zza> implements zzle {
                private C0003zza() {
                    super(C0002zza.zzc);
                }
            }

            public final zzd zzb() {
                zzd zza = zzd.zza(this.zzg);
                return zza == null ? zzd.CONSENT_STATUS_UNSPECIFIED : zza;
            }

            public final zze zzc() {
                zze zza = zze.zza(this.zzf);
                return zza == null ? zze.CONSENT_TYPE_UNSPECIFIED : zza;
            }

            /* access modifiers changed from: protected */
            public final Object zza(int i, Object obj, Object obj2) {
                switch (zzft.zza[i - 1]) {
                    case 1:
                        return new C0002zza();
                    case 2:
                        return new C0003zza();
                    case 3:
                        return zza((zzlc) zzc, "\u0004\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001᠌\u0000\u0002᠌\u0001", new Object[]{"zze", "zzf", zze.zzb(), "zzg", zzd.zzb()});
                    case 4:
                        return zzc;
                    case 5:
                        zzll<C0002zza> zzll = zzd;
                        if (zzll == null) {
                            synchronized (C0002zza.class) {
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

            static {
                C0002zza zza = new C0002zza();
                zzc = zza;
                zzjt.zza(C0002zza.class, zza);
            }

            private C0002zza() {
            }
        }

        /* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
        public static final class zzc extends zzjt<zzc, C0004zza> implements zzle {
            /* access modifiers changed from: private */
            public static final zzc zzc;
            private static volatile zzll<zzc> zzd;
            private int zze;
            private int zzf;
            private int zzg;

            /* renamed from: com.google.android.gms.internal.measurement.zzfr$zza$zzc$zza  reason: collision with other inner class name */
            /* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
            public static final class C0004zza extends zzjt.zzb<zzc, C0004zza> implements zzle {
                private C0004zza() {
                    super(zzc.zzc);
                }
            }

            public final zze zzb() {
                zze zza = zze.zza(this.zzg);
                return zza == null ? zze.CONSENT_TYPE_UNSPECIFIED : zza;
            }

            public final zze zzc() {
                zze zza = zze.zza(this.zzf);
                return zza == null ? zze.CONSENT_TYPE_UNSPECIFIED : zza;
            }

            /* access modifiers changed from: protected */
            public final Object zza(int i, Object obj, Object obj2) {
                switch (zzft.zza[i - 1]) {
                    case 1:
                        return new zzc();
                    case 2:
                        return new C0004zza();
                    case 3:
                        return zza((zzlc) zzc, "\u0004\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001᠌\u0000\u0002᠌\u0001", new Object[]{"zze", "zzf", zze.zzb(), "zzg", zze.zzb()});
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

            static {
                zzc zzc2 = new zzc();
                zzc = zzc2;
                zzjt.zza(zzc.class, zzc2);
            }

            private zzc() {
            }
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzft.zza[i - 1]) {
                case 1:
                    return new zza();
                case 2:
                    return new zzb();
                case 3:
                    return zza((zzlc) zzc, "\u0004\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0004\u0000\u0001\u001b\u0002\u001b\u0003\u001b\u0004ဇ\u0000\u0005\u001b", new Object[]{"zze", "zzf", C0002zza.class, "zzg", zzc.class, "zzh", zzf.class, "zzi", "zzj", C0002zza.class});
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

        /* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
        public enum zzd implements zzjy {
            CONSENT_STATUS_UNSPECIFIED(0),
            GRANTED(1),
            DENIED(2);
            
            private final int zze;

            public final int zza() {
                return this.zze;
            }

            public static zzd zza(int i) {
                if (i == 0) {
                    return CONSENT_STATUS_UNSPECIFIED;
                }
                if (i == 1) {
                    return GRANTED;
                }
                if (i != 2) {
                    return null;
                }
                return DENIED;
            }

            public static zzjx zzb() {
                return zzfu.zza;
            }

            public final String toString() {
                StringBuilder sb = new StringBuilder("<");
                sb.append(getClass().getName()).append('@').append(Integer.toHexString(System.identityHashCode(this)));
                sb.append(" number=").append(this.zze);
                return sb.append(" name=").append(name()).append(Typography.greater).toString();
            }

            private zzd(int i) {
                this.zze = i;
            }
        }

        /* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
        public enum zze implements zzjy {
            CONSENT_TYPE_UNSPECIFIED(0),
            AD_STORAGE(1),
            ANALYTICS_STORAGE(2),
            AD_USER_DATA(3),
            AD_PERSONALIZATION(4);
            
            private final int zzg;

            public final int zza() {
                return this.zzg;
            }

            public static zze zza(int i) {
                if (i == 0) {
                    return CONSENT_TYPE_UNSPECIFIED;
                }
                if (i == 1) {
                    return AD_STORAGE;
                }
                if (i == 2) {
                    return ANALYTICS_STORAGE;
                }
                if (i == 3) {
                    return AD_USER_DATA;
                }
                if (i != 4) {
                    return null;
                }
                return AD_PERSONALIZATION;
            }

            public static zzjx zzb() {
                return zzfv.zza;
            }

            public final String toString() {
                StringBuilder sb = new StringBuilder("<");
                sb.append(getClass().getName()).append('@').append(Integer.toHexString(System.identityHashCode(this)));
                sb.append(" number=").append(this.zzg);
                return sb.append(" name=").append(name()).append(Typography.greater).toString();
            }

            private zze(int i) {
                this.zzg = i;
            }
        }

        /* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
        public static final class zzf extends zzjt<zzf, C0005zza> implements zzle {
            /* access modifiers changed from: private */
            public static final zzf zzc;
            private static volatile zzll<zzf> zzd;
            private int zze;
            private String zzf = "";
            private String zzg = "";

            /* renamed from: com.google.android.gms.internal.measurement.zzfr$zza$zzf$zza  reason: collision with other inner class name */
            /* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
            public static final class C0005zza extends zzjt.zzb<zzf, C0005zza> implements zzle {
                private C0005zza() {
                    super(zzf.zzc);
                }
            }

            /* access modifiers changed from: protected */
            public final Object zza(int i, Object obj, Object obj2) {
                switch (zzft.zza[i - 1]) {
                    case 1:
                        return new zzf();
                    case 2:
                        return new C0005zza();
                    case 3:
                        return zza((zzlc) zzc, "\u0004\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001", new Object[]{"zze", "zzf", "zzg"});
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

            public final String zzb() {
                return this.zzf;
            }

            static {
                zzf zzf2 = new zzf();
                zzc = zzf2;
                zzjt.zza(zzf.class, zzf2);
            }

            private zzf() {
            }
        }

        public final List<zzf> zzc() {
            return this.zzh;
        }

        public final List<C0002zza> zzd() {
            return this.zzf;
        }

        public final List<zzc> zze() {
            return this.zzg;
        }

        public final List<C0002zza> zzf() {
            return this.zzj;
        }

        static {
            zza zza = new zza();
            zzc = zza;
            zzjt.zza(zza.class, zza);
        }

        private zza() {
        }

        public final boolean zzg() {
            return this.zzi;
        }

        public final boolean zzh() {
            return (this.zze & 1) != 0;
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
    public static final class zzc extends zzjt<zzc, zza> implements zzle {
        /* access modifiers changed from: private */
        public static final zzc zzc;
        private static volatile zzll<zzc> zzd;
        private int zze;
        private String zzf = "";
        private boolean zzg;
        private boolean zzh;
        private int zzi;

        /* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
        public static final class zza extends zzjt.zzb<zzc, zza> implements zzle {
            public final int zza() {
                return ((zzc) this.zza).zza();
            }

            public final zza zza(String str) {
                zzak();
                zzc.zza((zzc) this.zza, str);
                return this;
            }

            public final String zzb() {
                return ((zzc) this.zza).zzc();
            }

            private zza() {
                super(zzc.zzc);
            }

            public final boolean zzc() {
                return ((zzc) this.zza).zzd();
            }

            public final boolean zzd() {
                return ((zzc) this.zza).zze();
            }

            public final boolean zze() {
                return ((zzc) this.zza).zzf();
            }

            public final boolean zzf() {
                return ((zzc) this.zza).zzg();
            }

            public final boolean zzg() {
                return ((zzc) this.zza).zzh();
            }
        }

        public final int zza() {
            return this.zzi;
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzft.zza[i - 1]) {
                case 1:
                    return new zzc();
                case 2:
                    return new zza();
                case 3:
                    return zza((zzlc) zzc, "\u0004\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဇ\u0001\u0003ဇ\u0002\u0004င\u0003", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi"});
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

        public final String zzc() {
            return this.zzf;
        }

        static /* synthetic */ void zza(zzc zzc2, String str) {
            str.getClass();
            zzc2.zze |= 1;
            zzc2.zzf = str;
        }

        static {
            zzc zzc2 = new zzc();
            zzc = zzc2;
            zzjt.zza(zzc.class, zzc2);
        }

        private zzc() {
        }

        public final boolean zzd() {
            return this.zzg;
        }

        public final boolean zze() {
            return this.zzh;
        }

        public final boolean zzf() {
            return (this.zze & 2) != 0;
        }

        public final boolean zzg() {
            return (this.zze & 4) != 0;
        }

        public final boolean zzh() {
            return (this.zze & 8) != 0;
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
    public static final class zzd extends zzjt<zzd, zza> implements zzle {
        /* access modifiers changed from: private */
        public static final zzd zzc;
        private static volatile zzll<zzd> zzd;
        private int zze;
        private long zzf;
        private String zzg = "";
        private int zzh;
        private zzkc<zzh> zzi = zzch();
        private zzkc<zzc> zzj = zzch();
        /* access modifiers changed from: private */
        public zzkc<zzfo.zza> zzk = zzch();
        private String zzl = "";
        private boolean zzm;
        private zzkc<zzgd.zzc> zzn = zzch();
        private zzkc<zzb> zzo = zzch();
        private String zzp = "";
        private String zzq = "";
        private zza zzr;
        private zzf zzs;
        private zzi zzt;
        private zzg zzu;
        private zze zzv;

        /* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
        public static final class zza extends zzjt.zzb<zzd, zza> implements zzle {
            public final int zza() {
                return ((zzd) this.zza).zzb();
            }

            public final zzc zza(int i) {
                return ((zzd) this.zza).zza(i);
            }

            public final zza zzb() {
                zzak();
                ((zzd) this.zza).zzk = zzd.zzch();
                return this;
            }

            public final zza zza(int i, zzc.zza zza) {
                zzak();
                zzd.zza((zzd) this.zza, i, (zzc) ((zzjt) zza.zzai()));
                return this;
            }

            public final String zzc() {
                return ((zzd) this.zza).zzj();
            }

            public final List<zzfo.zza> zzd() {
                return Collections.unmodifiableList(((zzd) this.zza).zzk());
            }

            public final List<zzb> zze() {
                return Collections.unmodifiableList(((zzd) this.zza).zzl());
            }

            private zza() {
                super(zzd.zzc);
            }
        }

        public final int zza() {
            return this.zzn.size();
        }

        public final int zzb() {
            return this.zzj.size();
        }

        public final long zzc() {
            return this.zzf;
        }

        public final zza zzd() {
            zza zza2 = this.zzr;
            return zza2 == null ? zza.zzb() : zza2;
        }

        public final zzc zza(int i) {
            return (zzc) this.zzj.get(i);
        }

        public static zza zze() {
            return (zza) zzc.zzcc();
        }

        public static zzd zzg() {
            return zzc;
        }

        public final zzi zzh() {
            zzi zzi2 = this.zzt;
            return zzi2 == null ? zzi.zzc() : zzi2;
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzft.zza[i - 1]) {
                case 1:
                    return new zzd();
                case 2:
                    return new zza();
                case 3:
                    return zza((zzlc) zzc, "\u0004\u0011\u0000\u0001\u0001\u0013\u0011\u0000\u0005\u0000\u0001ဂ\u0000\u0002ဈ\u0001\u0003င\u0002\u0004\u001b\u0005\u001b\u0006\u001b\u0007ဈ\u0003\bဇ\u0004\t\u001b\n\u001b\u000bဈ\u0005\u000eဈ\u0006\u000fဉ\u0007\u0010ဉ\b\u0011ဉ\t\u0012ဉ\n\u0013ဉ\u000b", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", zzh.class, "zzj", zzc.class, "zzk", zzfo.zza.class, "zzl", "zzm", "zzn", zzgd.zzc.class, "zzo", zzb.class, "zzp", "zzq", "zzr", "zzs", "zzt", "zzu", "zzv"});
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

        public final String zzi() {
            return this.zzg;
        }

        public final String zzj() {
            return this.zzp;
        }

        public final List<zzfo.zza> zzk() {
            return this.zzk;
        }

        public final List<zzb> zzl() {
            return this.zzo;
        }

        public final List<zzgd.zzc> zzm() {
            return this.zzn;
        }

        public final List<zzh> zzn() {
            return this.zzi;
        }

        static /* synthetic */ void zza(zzd zzd2, int i, zzc zzc2) {
            zzc2.getClass();
            zzkc<zzc> zzkc = zzd2.zzj;
            if (!zzkc.zzc()) {
                zzd2.zzj = zzjt.zza(zzkc);
            }
            zzd2.zzj.set(i, zzc2);
        }

        static {
            zzd zzd2 = new zzd();
            zzc = zzd2;
            zzjt.zza(zzd.class, zzd2);
        }

        private zzd() {
        }

        public final boolean zzo() {
            return (this.zze & 128) != 0;
        }

        public final boolean zzp() {
            return (this.zze & 2) != 0;
        }

        public final boolean zzq() {
            return (this.zze & 512) != 0;
        }

        public final boolean zzr() {
            return (this.zze & 1) != 0;
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
    public static final class zzi extends zzjt<zzi, zza> implements zzle {
        /* access modifiers changed from: private */
        public static final zzi zzc;
        private static volatile zzll<zzi> zzd;
        private int zze;
        private String zzf = "";
        private String zzg = "";
        private String zzh = "";
        private int zzi;

        /* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
        public static final class zza extends zzjt.zzb<zzi, zza> implements zzle {
            private zza() {
                super(zzi.zzc);
            }
        }

        public final int zza() {
            return this.zzi;
        }

        public static zzi zzc() {
            return zzc;
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzft.zza[i - 1]) {
                case 1:
                    return new zzi();
                case 2:
                    return new zza();
                case 3:
                    return zza((zzlc) zzc, "\u0004\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004င\u0003", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi"});
                case 4:
                    return zzc;
                case 5:
                    zzll<zzi> zzll = zzd;
                    if (zzll == null) {
                        synchronized (zzi.class) {
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

        public final String zzd() {
            return this.zzg;
        }

        public final String zze() {
            return this.zzf;
        }

        static {
            zzi zzi2 = new zzi();
            zzc = zzi2;
            zzjt.zza(zzi.class, zzi2);
        }

        private zzi() {
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
    public static final class zzb extends zzjt<zzb, zza> implements zzle {
        /* access modifiers changed from: private */
        public static final zzb zzc;
        private static volatile zzll<zzb> zzd;
        private int zze;
        private String zzf = "";
        private zzkc<zzg> zzg = zzch();
        private boolean zzh;

        /* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
        public static final class zza extends zzjt.zzb<zzb, zza> implements zzle {
            private zza() {
                super(zzb.zzc);
            }
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzft.zza[i - 1]) {
                case 1:
                    return new zzb();
                case 2:
                    return new zza();
                case 3:
                    return zza((zzlc) zzc, "\u0004\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0001\u0000\u0001ဈ\u0000\u0002\u001b\u0003ဇ\u0001", new Object[]{"zze", "zzf", "zzg", zzg.class, "zzh"});
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

        public final String zzb() {
            return this.zzf;
        }

        static {
            zzb zzb = new zzb();
            zzc = zzb;
            zzjt.zza(zzb.class, zzb);
        }

        private zzb() {
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
    public static final class zze extends zzjt<zze, zza> implements zzle {
        /* access modifiers changed from: private */
        public static final zze zzc;
        private static volatile zzll<zze> zzd;
        private zzkc<String> zze = zzjt.zzch();

        /* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
        public static final class zza extends zzjt.zzb<zze, zza> implements zzle {
            private zza() {
                super(zze.zzc);
            }
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzft.zza[i - 1]) {
                case 1:
                    return new zze();
                case 2:
                    return new zza();
                case 3:
                    return zza((zzlc) zzc, "\u0004\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001a", new Object[]{"zze"});
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

        static {
            zze zze2 = new zze();
            zzc = zze2;
            zzjt.zza(zze.class, zze2);
        }

        private zze() {
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
    public static final class zzf extends zzjt<zzf, zza> implements zzle {
        /* access modifiers changed from: private */
        public static final zzf zzc;
        private static volatile zzll<zzf> zzd;
        private int zze;
        private int zzf = 14;
        private int zzg = 11;
        private int zzh = 60;

        /* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
        public static final class zza extends zzjt.zzb<zzf, zza> implements zzle {
            private zza() {
                super(zzf.zzc);
            }
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzft.zza[i - 1]) {
                case 1:
                    return new zzf();
                case 2:
                    return new zza();
                case 3:
                    return zza((zzlc) zzc, "\u0004\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001င\u0000\u0002င\u0001\u0003င\u0002", new Object[]{"zze", "zzf", "zzg", "zzh"});
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

        static {
            zzf zzf2 = new zzf();
            zzc = zzf2;
            zzjt.zza(zzf.class, zzf2);
        }

        private zzf() {
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
    public static final class zzg extends zzjt<zzg, zza> implements zzle {
        /* access modifiers changed from: private */
        public static final zzg zzc;
        private static volatile zzll<zzg> zzd;
        private int zze;
        private String zzf = "";
        private String zzg = "";

        /* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
        public static final class zza extends zzjt.zzb<zzg, zza> implements zzle {
            private zza() {
                super(zzg.zzc);
            }
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzft.zza[i - 1]) {
                case 1:
                    return new zzg();
                case 2:
                    return new zza();
                case 3:
                    return zza((zzlc) zzc, "\u0004\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001", new Object[]{"zze", "zzf", "zzg"});
                case 4:
                    return zzc;
                case 5:
                    zzll<zzg> zzll = zzd;
                    if (zzll == null) {
                        synchronized (zzg.class) {
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

        static {
            zzg zzg2 = new zzg();
            zzc = zzg2;
            zzjt.zza(zzg.class, zzg2);
        }

        private zzg() {
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
    public static final class zzh extends zzjt<zzh, zza> implements zzle {
        /* access modifiers changed from: private */
        public static final zzh zzc;
        private static volatile zzll<zzh> zzd;
        private int zze;
        private String zzf = "";
        private String zzg = "";

        /* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
        public static final class zza extends zzjt.zzb<zzh, zza> implements zzle {
            private zza() {
                super(zzh.zzc);
            }
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzft.zza[i - 1]) {
                case 1:
                    return new zzh();
                case 2:
                    return new zza();
                case 3:
                    return zza((zzlc) zzc, "\u0004\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001", new Object[]{"zze", "zzf", "zzg"});
                case 4:
                    return zzc;
                case 5:
                    zzll<zzh> zzll = zzd;
                    if (zzll == null) {
                        synchronized (zzh.class) {
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

        public final String zzb() {
            return this.zzf;
        }

        public final String zzc() {
            return this.zzg;
        }

        static {
            zzh zzh = new zzh();
            zzc = zzh;
            zzjt.zza(zzh.class, zzh);
        }

        private zzh() {
        }
    }
}
