package com.google.android.gms.internal.measurement;

import androidx.media3.common.C;
import com.google.android.gms.internal.measurement.zzjt;
import java.util.Collections;
import java.util.List;
import kotlin.text.Typography;

/* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
public final class zzfy {

    /* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
    public static final class zza extends zzjt<zza, C0006zza> implements zzle {
        /* access modifiers changed from: private */
        public static final zza zzc;
        private static volatile zzll<zza> zzd;
        private int zze;
        private String zzf = "";
        private String zzg = "";
        private String zzh = "";
        private long zzi;
        private String zzj = "";
        private String zzk = "";
        private String zzl = "";
        private long zzm;

        /* renamed from: com.google.android.gms.internal.measurement.zzfy$zza$zza  reason: collision with other inner class name */
        /* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
        public static final class C0006zza extends zzjt.zzb<zza, C0006zza> implements zzle {
            public final long zza() {
                return ((zza) this.zza).zza();
            }

            public final long zzb() {
                return ((zza) this.zza).zzb();
            }

            public final C0006zza zzc() {
                zzak();
                zza.zza((zza) this.zza);
                return this;
            }

            public final C0006zza zzd() {
                zzak();
                zza.zzb((zza) this.zza);
                return this;
            }

            public final C0006zza zze() {
                zzak();
                zza.zzc((zza) this.zza);
                return this;
            }

            public final C0006zza zzf() {
                zzak();
                zza.zzd((zza) this.zza);
                return this;
            }

            public final C0006zza zzg() {
                zzak();
                zza.zze((zza) this.zza);
                return this;
            }

            public final C0006zza zzh() {
                zzak();
                zza.zzf((zza) this.zza);
                return this;
            }

            public final C0006zza zza(String str) {
                zzak();
                zza.zza((zza) this.zza, str);
                return this;
            }

            public final C0006zza zzb(String str) {
                zzak();
                zza.zzb((zza) this.zza, str);
                return this;
            }

            public final C0006zza zzc(String str) {
                zzak();
                zza.zzc((zza) this.zza, str);
                return this;
            }

            public final C0006zza zza(long j) {
                zzak();
                zza.zza((zza) this.zza, j);
                return this;
            }

            public final C0006zza zzb(long j) {
                zzak();
                zza.zzb((zza) this.zza, j);
                return this;
            }

            public final C0006zza zzd(String str) {
                zzak();
                zza.zzd((zza) this.zza, str);
                return this;
            }

            public final C0006zza zze(String str) {
                zzak();
                zza.zze((zza) this.zza, str);
                return this;
            }

            public final C0006zza zzf(String str) {
                zzak();
                zza.zzf((zza) this.zza, str);
                return this;
            }

            private C0006zza() {
                super(zza.zzc);
            }
        }

        public final long zza() {
            return this.zzi;
        }

        public final long zzb() {
            return this.zzm;
        }

        public static C0006zza zzc() {
            return (C0006zza) zzc.zzcc();
        }

        public static zza zze() {
            return zzc;
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzfx.zza[i - 1]) {
                case 1:
                    return new zza();
                case 2:
                    return new C0006zza();
                case 3:
                    return zza((zzlc) zzc, "\u0004\b\u0000\u0001\u0001\b\b\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004ဂ\u0003\u0005ဈ\u0004\u0006ဈ\u0005\u0007ဈ\u0006\bဂ\u0007", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", "zzl", "zzm"});
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

        public final String zzf() {
            return this.zzh;
        }

        public final String zzg() {
            return this.zzg;
        }

        public final String zzh() {
            return this.zzf;
        }

        public final String zzi() {
            return this.zzl;
        }

        public final String zzj() {
            return this.zzk;
        }

        public final String zzk() {
            return this.zzj;
        }

        static /* synthetic */ void zza(zza zza) {
            zza.zze &= -5;
            zza.zzh = zzc.zzh;
        }

        static /* synthetic */ void zzb(zza zza) {
            zza.zze &= -3;
            zza.zzg = zzc.zzg;
        }

        static /* synthetic */ void zzc(zza zza) {
            zza.zze &= -2;
            zza.zzf = zzc.zzf;
        }

        static /* synthetic */ void zzd(zza zza) {
            zza.zze &= -65;
            zza.zzl = zzc.zzl;
        }

        static /* synthetic */ void zze(zza zza) {
            zza.zze &= -33;
            zza.zzk = zzc.zzk;
        }

        static /* synthetic */ void zzf(zza zza) {
            zza.zze &= -17;
            zza.zzj = zzc.zzj;
        }

        static /* synthetic */ void zza(zza zza, String str) {
            str.getClass();
            zza.zze |= 4;
            zza.zzh = str;
        }

        static /* synthetic */ void zzb(zza zza, String str) {
            str.getClass();
            zza.zze |= 2;
            zza.zzg = str;
        }

        static /* synthetic */ void zzc(zza zza, String str) {
            str.getClass();
            zza.zze |= 1;
            zza.zzf = str;
        }

        static /* synthetic */ void zza(zza zza, long j) {
            zza.zze |= 8;
            zza.zzi = j;
        }

        static /* synthetic */ void zzb(zza zza, long j) {
            zza.zze |= 128;
            zza.zzm = j;
        }

        static /* synthetic */ void zzd(zza zza, String str) {
            str.getClass();
            zza.zze |= 64;
            zza.zzl = str;
        }

        static /* synthetic */ void zze(zza zza, String str) {
            str.getClass();
            zza.zze |= 32;
            zza.zzk = str;
        }

        static /* synthetic */ void zzf(zza zza, String str) {
            str.getClass();
            zza.zze |= 16;
            zza.zzj = str;
        }

        static {
            zza zza = new zza();
            zzc = zza;
            zzjt.zza(zza.class, zza);
        }

        private zza() {
        }

        public final boolean zzl() {
            return (this.zze & 4) != 0;
        }

        public final boolean zzm() {
            return (this.zze & 2) != 0;
        }

        public final boolean zzn() {
            return (this.zze & 1) != 0;
        }

        public final boolean zzo() {
            return (this.zze & 8) != 0;
        }

        public final boolean zzp() {
            return (this.zze & 128) != 0;
        }

        public final boolean zzq() {
            return (this.zze & 64) != 0;
        }

        public final boolean zzr() {
            return (this.zze & 32) != 0;
        }

        public final boolean zzs() {
            return (this.zze & 16) != 0;
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
    public static final class zzc extends zzjt<zzc, zza> implements zzle {
        /* access modifiers changed from: private */
        public static final zzc zzc;
        private static volatile zzll<zzc> zzd;
        private int zze;
        private boolean zzf;
        private boolean zzg;
        private boolean zzh;
        private boolean zzi;
        private boolean zzj;
        private boolean zzk;
        private boolean zzl;

        public static zza zza() {
            return (zza) zzc.zzcc();
        }

        public static zzc zzc() {
            return zzc;
        }

        /* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
        public static final class zza extends zzjt.zzb<zzc, zza> implements zzle {
            public final zza zza(boolean z) {
                zzak();
                zzc.zza((zzc) this.zza, z);
                return this;
            }

            public final zza zzb(boolean z) {
                zzak();
                zzc.zzb((zzc) this.zza, z);
                return this;
            }

            public final zza zzc(boolean z) {
                zzak();
                zzc.zzc((zzc) this.zza, z);
                return this;
            }

            public final zza zzd(boolean z) {
                zzak();
                zzc.zzd((zzc) this.zza, z);
                return this;
            }

            public final zza zze(boolean z) {
                zzak();
                zzc.zze((zzc) this.zza, z);
                return this;
            }

            public final zza zzf(boolean z) {
                zzak();
                zzc.zzf((zzc) this.zza, z);
                return this;
            }

            public final zza zzg(boolean z) {
                zzak();
                zzc.zzg((zzc) this.zza, z);
                return this;
            }

            private zza() {
                super(zzc.zzc);
            }
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzfx.zza[i - 1]) {
                case 1:
                    return new zzc();
                case 2:
                    return new zza();
                case 3:
                    return zza((zzlc) zzc, "\u0004\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0000\u0000\u0001ဇ\u0000\u0002ဇ\u0001\u0003ဇ\u0002\u0004ဇ\u0003\u0005ဇ\u0004\u0006ဇ\u0005\u0007ဇ\u0006", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", "zzl"});
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

        static /* synthetic */ void zza(zzc zzc2, boolean z) {
            zzc2.zze |= 32;
            zzc2.zzk = z;
        }

        static /* synthetic */ void zzb(zzc zzc2, boolean z) {
            zzc2.zze |= 16;
            zzc2.zzj = z;
        }

        static /* synthetic */ void zzc(zzc zzc2, boolean z) {
            zzc2.zze |= 1;
            zzc2.zzf = z;
        }

        static /* synthetic */ void zzd(zzc zzc2, boolean z) {
            zzc2.zze |= 64;
            zzc2.zzl = z;
        }

        static /* synthetic */ void zze(zzc zzc2, boolean z) {
            zzc2.zze |= 2;
            zzc2.zzg = z;
        }

        static /* synthetic */ void zzf(zzc zzc2, boolean z) {
            zzc2.zze |= 4;
            zzc2.zzh = z;
        }

        static /* synthetic */ void zzg(zzc zzc2, boolean z) {
            zzc2.zze |= 8;
            zzc2.zzi = z;
        }

        static {
            zzc zzc2 = new zzc();
            zzc = zzc2;
            zzjt.zza(zzc.class, zzc2);
        }

        private zzc() {
        }

        public final boolean zzd() {
            return this.zzk;
        }

        public final boolean zze() {
            return this.zzj;
        }

        public final boolean zzf() {
            return this.zzf;
        }

        public final boolean zzg() {
            return this.zzl;
        }

        public final boolean zzh() {
            return this.zzg;
        }

        public final boolean zzi() {
            return this.zzh;
        }

        public final boolean zzj() {
            return this.zzi;
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
    public static final class zzd extends zzjt<zzd, zza> implements zzle {
        /* access modifiers changed from: private */
        public static final zzd zzc;
        private static volatile zzll<zzd> zzd;
        private int zze;
        private int zzf;
        private zzm zzg;
        private zzm zzh;
        private boolean zzi;

        public final int zza() {
            return this.zzf;
        }

        public static zza zzb() {
            return (zza) zzc.zzcc();
        }

        /* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
        public static final class zza extends zzjt.zzb<zzd, zza> implements zzle {
            public final zza zza(int i) {
                zzak();
                zzd.zza((zzd) this.zza, i);
                return this;
            }

            public final zza zza(zzm.zza zza) {
                zzak();
                zzd.zza((zzd) this.zza, (zzm) ((zzjt) zza.zzai()));
                return this;
            }

            public final zza zza(boolean z) {
                zzak();
                zzd.zza((zzd) this.zza, z);
                return this;
            }

            public final zza zza(zzm zzm) {
                zzak();
                zzd.zzb((zzd) this.zza, zzm);
                return this;
            }

            private zza() {
                super(zzd.zzc);
            }
        }

        public final zzm zzd() {
            zzm zzm = this.zzg;
            return zzm == null ? zzm.zzg() : zzm;
        }

        public final zzm zze() {
            zzm zzm = this.zzh;
            return zzm == null ? zzm.zzg() : zzm;
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzfx.zza[i - 1]) {
                case 1:
                    return new zzd();
                case 2:
                    return new zza();
                case 3:
                    return zza((zzlc) zzc, "\u0004\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001င\u0000\u0002ဉ\u0001\u0003ဉ\u0002\u0004ဇ\u0003", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi"});
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

        static /* synthetic */ void zza(zzd zzd2, int i) {
            zzd2.zze |= 1;
            zzd2.zzf = i;
        }

        static /* synthetic */ void zza(zzd zzd2, zzm zzm) {
            zzm.getClass();
            zzd2.zzg = zzm;
            zzd2.zze |= 2;
        }

        static /* synthetic */ void zza(zzd zzd2, boolean z) {
            zzd2.zze |= 8;
            zzd2.zzi = z;
        }

        static /* synthetic */ void zzb(zzd zzd2, zzm zzm) {
            zzm.getClass();
            zzd2.zzh = zzm;
            zzd2.zze |= 4;
        }

        static {
            zzd zzd2 = new zzd();
            zzc = zzd2;
            zzjt.zza(zzd.class, zzd2);
        }

        private zzd() {
        }

        public final boolean zzf() {
            return this.zzi;
        }

        public final boolean zzg() {
            return (this.zze & 1) != 0;
        }

        public final boolean zzh() {
            return (this.zze & 8) != 0;
        }

        public final boolean zzi() {
            return (this.zze & 4) != 0;
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
    public static final class zze extends zzjt<zze, zza> implements zzle {
        /* access modifiers changed from: private */
        public static final zze zzc;
        private static volatile zzll<zze> zzd;
        private int zze;
        private int zzf;
        private long zzg;

        public final int zza() {
            return this.zzf;
        }

        public final long zzb() {
            return this.zzg;
        }

        /* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
        public static final class zza extends zzjt.zzb<zze, zza> implements zzle {
            public final zza zza(long j) {
                zzak();
                zze.zza((zze) this.zza, j);
                return this;
            }

            public final zza zza(int i) {
                zzak();
                zze.zza((zze) this.zza, i);
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
            switch (zzfx.zza[i - 1]) {
                case 1:
                    return new zze();
                case 2:
                    return new zza();
                case 3:
                    return zza((zzlc) zzc, "\u0004\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001င\u0000\u0002ဂ\u0001", new Object[]{"zze", "zzf", "zzg"});
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

        static /* synthetic */ void zza(zze zze2, long j) {
            zze2.zze |= 2;
            zze2.zzg = j;
        }

        static /* synthetic */ void zza(zze zze2, int i) {
            zze2.zze |= 1;
            zze2.zzf = i;
        }

        static {
            zze zze2 = new zze();
            zzc = zze2;
            zzjt.zza(zze.class, zze2);
        }

        private zze() {
        }

        public final boolean zze() {
            return (this.zze & 2) != 0;
        }

        public final boolean zzf() {
            return (this.zze & 1) != 0;
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
    public static final class zzf extends zzjt<zzf, zza> implements zzle {
        /* access modifiers changed from: private */
        public static final zzf zzc;
        private static volatile zzll<zzf> zzd;
        private int zze;
        /* access modifiers changed from: private */
        public zzkc<zzh> zzf = zzch();
        private String zzg = "";
        private long zzh;
        private long zzi;
        private int zzj;

        /* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
        public static final class zza extends zzjt.zzb<zzf, zza> implements zzle {
            public final int zza() {
                return ((zzf) this.zza).zzb();
            }

            public final long zzb() {
                return ((zzf) this.zza).zzc();
            }

            public final long zzc() {
                return ((zzf) this.zza).zzd();
            }

            public final zza zza(Iterable<? extends zzh> iterable) {
                zzak();
                zzf.zza((zzf) this.zza, (Iterable) iterable);
                return this;
            }

            public final zza zza(zzh.zza zza) {
                zzak();
                zzf.zza((zzf) this.zza, (zzh) ((zzjt) zza.zzai()));
                return this;
            }

            public final zza zza(zzh zzh) {
                zzak();
                zzf.zza((zzf) this.zza, zzh);
                return this;
            }

            public final zza zzd() {
                zzak();
                ((zzf) this.zza).zzf = zzf.zzch();
                return this;
            }

            public final zza zza(int i) {
                zzak();
                zzf.zza((zzf) this.zza, i);
                return this;
            }

            public final zza zza(String str) {
                zzak();
                zzf.zza((zzf) this.zza, str);
                return this;
            }

            public final zza zza(int i, zzh.zza zza) {
                zzak();
                zzf.zza((zzf) this.zza, i, (zzh) ((zzjt) zza.zzai()));
                return this;
            }

            public final zza zza(int i, zzh zzh) {
                zzak();
                zzf.zza((zzf) this.zza, i, zzh);
                return this;
            }

            public final zza zza(long j) {
                zzak();
                zzf.zza((zzf) this.zza, j);
                return this;
            }

            public final zza zzb(long j) {
                zzak();
                zzf.zzb((zzf) this.zza, j);
                return this;
            }

            public final zzh zzb(int i) {
                return ((zzf) this.zza).zza(i);
            }

            public final String zze() {
                return ((zzf) this.zza).zzg();
            }

            public final List<zzh> zzf() {
                return Collections.unmodifiableList(((zzf) this.zza).zzh());
            }

            private zza() {
                super(zzf.zzc);
            }

            public final boolean zzg() {
                return ((zzf) this.zza).zzk();
            }
        }

        public final int zza() {
            return this.zzj;
        }

        public final int zzb() {
            return this.zzf.size();
        }

        public final long zzc() {
            return this.zzi;
        }

        public final long zzd() {
            return this.zzh;
        }

        public static zza zze() {
            return (zza) zzc.zzcc();
        }

        public final zzh zza(int i) {
            return (zzh) this.zzf.get(i);
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzfx.zza[i - 1]) {
                case 1:
                    return new zzf();
                case 2:
                    return new zza();
                case 3:
                    return zza((zzlc) zzc, "\u0004\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0001\u0000\u0001\u001b\u0002ဈ\u0000\u0003ဂ\u0001\u0004ဂ\u0002\u0005င\u0003", new Object[]{"zze", "zzf", zzh.class, "zzg", "zzh", "zzi", "zzj"});
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

        public final String zzg() {
            return this.zzg;
        }

        public final List<zzh> zzh() {
            return this.zzf;
        }

        static /* synthetic */ void zza(zzf zzf2, Iterable iterable) {
            zzf2.zzl();
            zzib.zza(iterable, zzf2.zzf);
        }

        static /* synthetic */ void zza(zzf zzf2, zzh zzh2) {
            zzh2.getClass();
            zzf2.zzl();
            zzf2.zzf.add(zzh2);
        }

        static /* synthetic */ void zza(zzf zzf2, int i) {
            zzf2.zzl();
            zzf2.zzf.remove(i);
        }

        static /* synthetic */ void zza(zzf zzf2, String str) {
            str.getClass();
            zzf2.zze |= 1;
            zzf2.zzg = str;
        }

        static /* synthetic */ void zza(zzf zzf2, int i, zzh zzh2) {
            zzh2.getClass();
            zzf2.zzl();
            zzf2.zzf.set(i, zzh2);
        }

        static /* synthetic */ void zza(zzf zzf2, long j) {
            zzf2.zze |= 4;
            zzf2.zzi = j;
        }

        static /* synthetic */ void zzb(zzf zzf2, long j) {
            zzf2.zze |= 2;
            zzf2.zzh = j;
        }

        static {
            zzf zzf2 = new zzf();
            zzc = zzf2;
            zzjt.zza(zzf.class, zzf2);
        }

        private zzf() {
        }

        private final void zzl() {
            zzkc<zzh> zzkc = this.zzf;
            if (!zzkc.zzc()) {
                this.zzf = zzjt.zza(zzkc);
            }
        }

        public final boolean zzi() {
            return (this.zze & 8) != 0;
        }

        public final boolean zzj() {
            return (this.zze & 4) != 0;
        }

        public final boolean zzk() {
            return (this.zze & 2) != 0;
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
    public static final class zzg extends zzjt<zzg, zza> implements zzle {
        /* access modifiers changed from: private */
        public static final zzg zzc;
        private static volatile zzll<zzg> zzd;
        private int zze;
        private String zzf = "";
        private long zzg;

        public static zza zza() {
            return (zza) zzc.zzcc();
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzfx.zza[i - 1]) {
                case 1:
                    return new zzg();
                case 2:
                    return new zza();
                case 3:
                    return zza((zzlc) zzc, "\u0004\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဂ\u0001", new Object[]{"zze", "zzf", "zzg"});
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

        /* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
        public static final class zza extends zzjt.zzb<zzg, zza> implements zzle {
            public final zza zza(long j) {
                zzak();
                zzg.zza((zzg) this.zza, j);
                return this;
            }

            public final zza zza(String str) {
                zzak();
                zzg.zza((zzg) this.zza, str);
                return this;
            }

            private zza() {
                super(zzg.zzc);
            }
        }

        static /* synthetic */ void zza(zzg zzg2, long j) {
            zzg2.zze |= 2;
            zzg2.zzg = j;
        }

        static /* synthetic */ void zza(zzg zzg2, String str) {
            str.getClass();
            zzg2.zze |= 1;
            zzg2.zzf = str;
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
        private long zzh;
        private float zzi;
        private double zzj;
        /* access modifiers changed from: private */
        public zzkc<zzh> zzk = zzch();

        /* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
        public static final class zza extends zzjt.zzb<zzh, zza> implements zzle {
            public final int zza() {
                return ((zzh) this.zza).zzc();
            }

            public final zza zza(Iterable<? extends zzh> iterable) {
                zzak();
                zzh.zza((zzh) this.zza, (Iterable) iterable);
                return this;
            }

            public final zza zza(zza zza) {
                zzak();
                zzh.zza((zzh) this.zza, (zzh) ((zzjt) zza.zzai()));
                return this;
            }

            public final zza zzb() {
                zzak();
                zzh.zza((zzh) this.zza);
                return this;
            }

            public final zza zzc() {
                zzak();
                zzh.zzb((zzh) this.zza);
                return this;
            }

            public final zza zzd() {
                zzak();
                ((zzh) this.zza).zzk = zzh.zzch();
                return this;
            }

            public final zza zze() {
                zzak();
                zzh.zzd((zzh) this.zza);
                return this;
            }

            public final zza zza(double d) {
                zzak();
                zzh.zza((zzh) this.zza, d);
                return this;
            }

            public final zza zza(long j) {
                zzak();
                zzh.zza((zzh) this.zza, j);
                return this;
            }

            public final zza zza(String str) {
                zzak();
                zzh.zza((zzh) this.zza, str);
                return this;
            }

            public final zza zzb(String str) {
                zzak();
                zzh.zzb((zzh) this.zza, str);
                return this;
            }

            public final String zzf() {
                return ((zzh) this.zza).zzg();
            }

            public final String zzg() {
                return ((zzh) this.zza).zzh();
            }

            private zza() {
                super(zzh.zzc);
            }
        }

        public final double zza() {
            return this.zzj;
        }

        public final float zzb() {
            return this.zzi;
        }

        public final int zzc() {
            return this.zzk.size();
        }

        public final long zzd() {
            return this.zzh;
        }

        public static zza zze() {
            return (zza) zzc.zzcc();
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzfx.zza[i - 1]) {
                case 1:
                    return new zzh();
                case 2:
                    return new zza();
                case 3:
                    return zza((zzlc) zzc, "\u0004\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0001\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဂ\u0002\u0004ခ\u0003\u0005က\u0004\u0006\u001b", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", zzh.class});
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

        public final String zzg() {
            return this.zzf;
        }

        public final String zzh() {
            return this.zzg;
        }

        public final List<zzh> zzi() {
            return this.zzk;
        }

        static /* synthetic */ void zza(zzh zzh2, Iterable iterable) {
            zzh2.zzo();
            zzib.zza(iterable, zzh2.zzk);
        }

        static /* synthetic */ void zza(zzh zzh2, zzh zzh3) {
            zzh3.getClass();
            zzh2.zzo();
            zzh2.zzk.add(zzh3);
        }

        static /* synthetic */ void zza(zzh zzh2) {
            zzh2.zze &= -17;
            zzh2.zzj = 0.0d;
        }

        static /* synthetic */ void zzb(zzh zzh2) {
            zzh2.zze &= -5;
            zzh2.zzh = 0;
        }

        static /* synthetic */ void zzd(zzh zzh2) {
            zzh2.zze &= -3;
            zzh2.zzg = zzc.zzg;
        }

        static /* synthetic */ void zza(zzh zzh2, double d) {
            zzh2.zze |= 16;
            zzh2.zzj = d;
        }

        static /* synthetic */ void zza(zzh zzh2, long j) {
            zzh2.zze |= 4;
            zzh2.zzh = j;
        }

        static /* synthetic */ void zza(zzh zzh2, String str) {
            str.getClass();
            zzh2.zze |= 1;
            zzh2.zzf = str;
        }

        static /* synthetic */ void zzb(zzh zzh2, String str) {
            str.getClass();
            zzh2.zze |= 2;
            zzh2.zzg = str;
        }

        static {
            zzh zzh2 = new zzh();
            zzc = zzh2;
            zzjt.zza(zzh.class, zzh2);
        }

        private zzh() {
        }

        private final void zzo() {
            zzkc<zzh> zzkc = this.zzk;
            if (!zzkc.zzc()) {
                this.zzk = zzjt.zza(zzkc);
            }
        }

        public final boolean zzj() {
            return (this.zze & 16) != 0;
        }

        public final boolean zzk() {
            return (this.zze & 8) != 0;
        }

        public final boolean zzl() {
            return (this.zze & 4) != 0;
        }

        public final boolean zzm() {
            return (this.zze & 1) != 0;
        }

        public final boolean zzn() {
            return (this.zze & 2) != 0;
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
    public static final class zzj extends zzjt<zzj, zza> implements zzle {
        /* access modifiers changed from: private */
        public static final zzj zzc;
        private static volatile zzll<zzj> zzd;
        private int zze;
        /* access modifiers changed from: private */
        public zzkc<zzk> zzf = zzch();
        private String zzg = "";
        private String zzh = "";
        private int zzi;

        /* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
        public static final class zza extends zzjt.zzb<zzj, zza> implements zzle {
            public final int zza() {
                return ((zzj) this.zza).zza();
            }

            public final zza zza(Iterable<? extends zzk> iterable) {
                zzak();
                zzj.zza((zzj) this.zza, (Iterable) iterable);
                return this;
            }

            public final zza zza(zzk.zza zza) {
                zzak();
                zzj.zza((zzj) this.zza, (zzk) ((zzjt) zza.zzai()));
                return this;
            }

            public final zza zzb() {
                zzak();
                ((zzj) this.zza).zzf = zzj.zzch();
                return this;
            }

            public final zza zza(String str) {
                zzak();
                zzj.zza((zzj) this.zza, str);
                return this;
            }

            public final zza zzb(String str) {
                zzak();
                zzj.zzb((zzj) this.zza, str);
                return this;
            }

            public final zzk zza(int i) {
                return ((zzj) this.zza).zza(0);
            }

            public final String zzc() {
                return ((zzj) this.zza).zzd();
            }

            public final List<zzk> zzd() {
                return Collections.unmodifiableList(((zzj) this.zza).zzf());
            }

            private zza() {
                super(zzj.zzc);
            }
        }

        public final int zza() {
            return this.zzf.size();
        }

        public static zza zzb() {
            return (zza) zzc.zzcc();
        }

        /* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
        public enum zzb implements zzjy {
            SDK(0),
            SGTM(1);
            
            private final int zzd;

            public final int zza() {
                return this.zzd;
            }

            public static zzb zza(int i) {
                if (i == 0) {
                    return SDK;
                }
                if (i != 1) {
                    return null;
                }
                return SGTM;
            }

            public static zzjx zzb() {
                return zzgb.zza;
            }

            public final String toString() {
                StringBuilder sb = new StringBuilder("<");
                sb.append(getClass().getName()).append('@').append(Integer.toHexString(System.identityHashCode(this)));
                sb.append(" number=").append(this.zzd);
                return sb.append(" name=").append(name()).append(Typography.greater).toString();
            }

            private zzb(int i) {
                this.zzd = i;
            }
        }

        public static zza zza(zzj zzj) {
            return (zza) zzc.zza(zzj);
        }

        public final zzk zza(int i) {
            return (zzk) this.zzf.get(0);
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzfx.zza[i - 1]) {
                case 1:
                    return new zzj();
                case 2:
                    return new zza();
                case 3:
                    return zza((zzlc) zzc, "\u0004\u0004\u0000\u0001\u0001\t\u0004\u0000\u0001\u0000\u0001\u001b\u0007ဈ\u0000\bဈ\u0001\t᠌\u0002", new Object[]{"zze", "zzf", zzk.class, "zzg", "zzh", "zzi", zzb.zzb()});
                case 4:
                    return zzc;
                case 5:
                    zzll<zzj> zzll = zzd;
                    if (zzll == null) {
                        synchronized (zzj.class) {
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
            return this.zzh;
        }

        public final List<zzk> zzf() {
            return this.zzf;
        }

        static /* synthetic */ void zza(zzj zzj, Iterable iterable) {
            zzj.zzi();
            zzib.zza(iterable, zzj.zzf);
        }

        static /* synthetic */ void zza(zzj zzj, zzk zzk) {
            zzk.getClass();
            zzj.zzi();
            zzj.zzf.add(zzk);
        }

        static /* synthetic */ void zza(zzj zzj, String str) {
            str.getClass();
            zzj.zze |= 1;
            zzj.zzg = str;
        }

        static /* synthetic */ void zzb(zzj zzj, String str) {
            str.getClass();
            zzj.zze |= 2;
            zzj.zzh = str;
        }

        static {
            zzj zzj = new zzj();
            zzc = zzj;
            zzjt.zza(zzj.class, zzj);
        }

        private zzj() {
        }

        private final void zzi() {
            zzkc<zzk> zzkc = this.zzf;
            if (!zzkc.zzc()) {
                this.zzf = zzjt.zza(zzkc);
            }
        }

        public final boolean zzg() {
            return (this.zze & 1) != 0;
        }

        public final boolean zzh() {
            return (this.zze & 2) != 0;
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
    public static final class zzk extends zzjt<zzk, zza> implements zzle {
        /* access modifiers changed from: private */
        public static final zzk zzc;
        private static volatile zzll<zzk> zzd;
        private String zzaa = "";
        private long zzab;
        private int zzac;
        private String zzad = "";
        private String zzae = "";
        private boolean zzaf;
        /* access modifiers changed from: private */
        public zzkc<zzd> zzag = zzch();
        private String zzah = "";
        private int zzai;
        private int zzaj;
        private int zzak;
        private String zzal = "";
        private long zzam;
        private long zzan;
        private String zzao = "";
        private String zzap = "";
        private int zzaq;
        private String zzar = "";
        private zzl zzas;
        private zzka zzat = zzcf();
        private long zzau;
        private long zzav;
        private String zzaw = "";
        private String zzax = "";
        private int zzay;
        private boolean zzaz;
        private String zzba = "";
        private boolean zzbb;
        private zzi zzbc;
        private String zzbd = "";
        private zzkc<String> zzbe = zzjt.zzch();
        private String zzbf = "";
        private long zzbg;
        private boolean zzbh;
        private String zzbi = "";
        private boolean zzbj;
        private String zzbk = "";
        private int zzbl;
        private String zzbm = "";
        private zzc zzbn;
        private int zzbo;
        private zza zzbp;
        private String zzbq = "";
        private int zze;
        private int zzf;
        private int zzg;
        /* access modifiers changed from: private */
        public zzkc<zzf> zzh = zzch();
        private zzkc<zzo> zzi = zzch();
        private long zzj;
        private long zzk;
        private long zzl;
        private long zzm;
        private long zzn;
        private String zzo = "";
        private String zzp = "";
        private String zzq = "";
        private String zzr = "";
        private int zzs;
        private String zzt = "";
        private String zzu = "";
        private String zzv = "";
        private long zzw;
        private long zzx;
        private String zzy = "";
        private boolean zzz;

        /* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
        public static final class zza extends zzjt.zzb<zzk, zza> implements zzle {
            public final int zza() {
                return ((zzk) this.zza).zza();
            }

            public final int zzb() {
                return ((zzk) this.zza).zzb();
            }

            public final int zzc() {
                return ((zzk) this.zza).zze();
            }

            public final int zzd() {
                return ((zzk) this.zza).zzi();
            }

            public final long zze() {
                return ((zzk) this.zza).zzm();
            }

            public final long zzf() {
                return ((zzk) this.zza).zzq();
            }

            public final zza zzg() {
                return ((zzk) this.zza).zzu();
            }

            public final zzf zza(int i) {
                return ((zzk) this.zza).zza(i);
            }

            public final zza zza(Iterable<? extends zzd> iterable) {
                zzak();
                zzk.zza((zzk) this.zza, (Iterable) iterable);
                return this;
            }

            public final zza zzb(Iterable<? extends zzf> iterable) {
                zzak();
                zzk.zzb((zzk) this.zza, (Iterable) iterable);
                return this;
            }

            public final zza zzc(Iterable<? extends Integer> iterable) {
                zzak();
                zzk.zzc((zzk) this.zza, (Iterable) iterable);
                return this;
            }

            public final zza zzd(Iterable<String> iterable) {
                zzak();
                zzk.zzd((zzk) this.zza, (Iterable) iterable);
                return this;
            }

            public final zza zze(Iterable<? extends zzo> iterable) {
                zzak();
                zzk.zze((zzk) this.zza, (Iterable) iterable);
                return this;
            }

            public final zza zza(zzf.zza zza) {
                zzak();
                zzk.zza((zzk) this.zza, (zzf) ((zzjt) zza.zzai()));
                return this;
            }

            public final zza zza(zzo.zza zza) {
                zzak();
                zzk.zza((zzk) this.zza, (zzo) ((zzjt) zza.zzai()));
                return this;
            }

            public final zza zza(zzo zzo) {
                zzak();
                zzk.zza((zzk) this.zza, zzo);
                return this;
            }

            public final zza zzh() {
                zzak();
                zzk.zzb((zzk) this.zza);
                return this;
            }

            public final zza zzi() {
                zzak();
                ((zzk) this.zza).zzag = zzk.zzch();
                return this;
            }

            public final zza zzj() {
                zzak();
                zzk.zzd((zzk) this.zza);
                return this;
            }

            public final zza zzk() {
                zzak();
                zzk.zze((zzk) this.zza);
                return this;
            }

            public final zza zzl() {
                zzak();
                ((zzk) this.zza).zzh = zzk.zzch();
                return this;
            }

            public final zza zzm() {
                zzak();
                zzk.zzg((zzk) this.zza);
                return this;
            }

            public final zza zzn() {
                zzak();
                zzk.zzh((zzk) this.zza);
                return this;
            }

            public final zza zzo() {
                zzak();
                zzk.zzi((zzk) this.zza);
                return this;
            }

            public final zza zzp() {
                zzak();
                zzk.zzj((zzk) this.zza);
                return this;
            }

            public final zza zzq() {
                zzak();
                zzk.zzk((zzk) this.zza);
                return this;
            }

            public final zza zzr() {
                zzak();
                zzk.zzl((zzk) this.zza);
                return this;
            }

            public final zza zzs() {
                zzak();
                zzk.zzm((zzk) this.zza);
                return this;
            }

            public final zza zzb(int i) {
                zzak();
                zzk.zza((zzk) this.zza, i);
                return this;
            }

            public final zza zzc(int i) {
                zzak();
                zzk.zzb((zzk) this.zza, i);
                return this;
            }

            public final zza zza(zza zza) {
                zzak();
                zzk.zza((zzk) this.zza, zza);
                return this;
            }

            public final zza zzd(int i) {
                zzak();
                zzk.zzc((zzk) this.zza, i);
                return this;
            }

            public final zza zza(String str) {
                zzak();
                zzk.zza((zzk) this.zza, str);
                return this;
            }

            public final zza zzb(String str) {
                zzak();
                zzk.zzb((zzk) this.zza, str);
                return this;
            }

            public final zza zzc(String str) {
                zzak();
                zzk.zzc((zzk) this.zza, str);
                return this;
            }

            public final zza zzd(String str) {
                zzak();
                zzk.zzd((zzk) this.zza, str);
                return this;
            }

            public final zza zze(String str) {
                zzak();
                zzk.zze((zzk) this.zza, str);
                return this;
            }

            public final zza zze(int i) {
                zzak();
                zzk.zzd((zzk) this.zza, i);
                return this;
            }

            public final zza zza(zzc zzc) {
                zzak();
                zzk.zza((zzk) this.zza, zzc);
                return this;
            }

            public final zza zzf(int i) {
                zzak();
                zzk.zze((zzk) this.zza, i);
                return this;
            }

            public final zza zza(long j) {
                zzak();
                zzk.zza((zzk) this.zza, j);
                return this;
            }

            public final zza zzb(long j) {
                zzak();
                zzk.zzb((zzk) this.zza, j);
                return this;
            }

            public final zza zzf(String str) {
                zzak();
                zzk.zzf((zzk) this.zza, str);
                return this;
            }

            public final zza zzg(String str) {
                zzak();
                zzk.zzg((zzk) this.zza, str);
                return this;
            }

            public final zza zzh(String str) {
                zzak();
                zzk.zzh((zzk) this.zza, str);
                return this;
            }

            public final zza zzg(int i) {
                zzak();
                zzk.zzf((zzk) this.zza, i);
                return this;
            }

            public final zza zzc(long j) {
                zzak();
                zzk.zzc((zzk) this.zza, j);
                return this;
            }

            public final zza zzi(String str) {
                zzak();
                zzk.zzi((zzk) this.zza, str);
                return this;
            }

            public final zza zzj(String str) {
                zzak();
                zzk.zzj((zzk) this.zza, str);
                return this;
            }

            public final zza zzd(long j) {
                zzak();
                zzk.zzd((zzk) this.zza, j);
                return this;
            }

            public final zza zza(boolean z) {
                zzak();
                zzk.zza((zzk) this.zza, z);
                return this;
            }

            public final zza zze(long j) {
                zzak();
                zzk.zze((zzk) this.zza, j);
                return this;
            }

            public final zza zzk(String str) {
                zzak();
                zzk.zzk((zzk) this.zza, str);
                return this;
            }

            public final zza zza(int i, zzf.zza zza) {
                zzak();
                zzk.zza((zzk) this.zza, i, (zzf) ((zzjt) zza.zzai()));
                return this;
            }

            public final zza zza(int i, zzf zzf) {
                zzak();
                zzk.zza((zzk) this.zza, i, zzf);
                return this;
            }

            public final zza zzl(String str) {
                zzak();
                zzk.zzl((zzk) this.zza, str);
                return this;
            }

            public final zza zzm(String str) {
                zzak();
                zzk.zzm((zzk) this.zza, str);
                return this;
            }

            public final zza zzf(long j) {
                zzak();
                zzk.zzf((zzk) this.zza, j);
                return this;
            }

            public final zza zzn(String str) {
                zzak();
                zzk.zzn((zzk) this.zza, str);
                return this;
            }

            public final zza zzb(boolean z) {
                zzak();
                zzk.zzb((zzk) this.zza, z);
                return this;
            }

            public final zza zzc(boolean z) {
                zzak();
                zzk.zzc((zzk) this.zza, z);
                return this;
            }

            public final zza zzo(String str) {
                zzak();
                zzk.zzo((zzk) this.zza, str);
                return this;
            }

            public final zza zzp(String str) {
                zzak();
                zzk.zzp((zzk) this.zza, str);
                return this;
            }

            public final zza zza(zzl.zza zza) {
                zzak();
                zzk.zza((zzk) this.zza, (zzl) ((zzjt) zza.zzai()));
                return this;
            }

            public final zza zzg(long j) {
                zzak();
                zzk.zzg((zzk) this.zza, j);
                return this;
            }

            public final zza zzh(long j) {
                zzak();
                zzk.zzh((zzk) this.zza, j);
                return this;
            }

            public final zza zzh(int i) {
                zzak();
                zzk.zzg((zzk) this.zza, 1);
                return this;
            }

            public final zza zzq(String str) {
                zzak();
                zzk.zzq((zzk) this.zza, str);
                return this;
            }

            public final zza zzi(int i) {
                zzak();
                zzk.zzh((zzk) this.zza, i);
                return this;
            }

            public final zza zzd(boolean z) {
                zzak();
                zzk.zzd((zzk) this.zza, z);
                return this;
            }

            public final zza zzr(String str) {
                zzak();
                zzk.zzr((zzk) this.zza, str);
                return this;
            }

            public final zza zzi(long j) {
                zzak();
                zzk.zzi((zzk) this.zza, j);
                return this;
            }

            public final zza zzj(long j) {
                zzak();
                zzk.zzj((zzk) this.zza, j);
                return this;
            }

            public final zza zzj(int i) {
                zzak();
                zzk.zzi((zzk) this.zza, i);
                return this;
            }

            public final zza zzk(long j) {
                zzak();
                zzk.zzk((zzk) this.zza, j);
                return this;
            }

            public final zza zzl(long j) {
                zzak();
                zzk.zzl((zzk) this.zza, j);
                return this;
            }

            public final zza zza(int i, zzo zzo) {
                zzak();
                zzk.zza((zzk) this.zza, i, zzo);
                return this;
            }

            public final zza zzs(String str) {
                zzak();
                zzk.zzs((zzk) this.zza, str);
                return this;
            }

            public final zzo zzk(int i) {
                return ((zzk) this.zza).zzb(i);
            }

            public final String zzt() {
                return ((zzk) this.zza).zzz();
            }

            public final String zzu() {
                return ((zzk) this.zza).zzaa();
            }

            public final String zzv() {
                return ((zzk) this.zza).zzad();
            }

            public final String zzw() {
                return ((zzk) this.zza).zzaf();
            }

            public final String zzx() {
                return ((zzk) this.zza).zzaj();
            }

            public final String zzy() {
                return ((zzk) this.zza).zzal();
            }

            public final String zzz() {
                return ((zzk) this.zza).zzan();
            }

            public final List<zzf> zzaa() {
                return Collections.unmodifiableList(((zzk) this.zza).zzar());
            }

            public final List<zzo> zzab() {
                return Collections.unmodifiableList(((zzk) this.zza).zzas());
            }

            private zza() {
                super(zzk.zzc);
            }

            public final boolean zzac() {
                return ((zzk) this.zza).zzau();
            }

            public final boolean zzad() {
                return ((zzk) this.zza).zzav();
            }

            public final boolean zzae() {
                return ((zzk) this.zza).zzax();
            }
        }

        public final int zza() {
            return this.zzbl;
        }

        public final int zzb() {
            return this.zzai;
        }

        public final int zzc() {
            return this.zzac;
        }

        public final int zzd() {
            return this.zzbo;
        }

        public final int zze() {
            return this.zzh.size();
        }

        public final int zzf() {
            return this.zzg;
        }

        public final int zzg() {
            return this.zzaq;
        }

        public final int zzh() {
            return this.zzs;
        }

        public final int zzi() {
            return this.zzi.size();
        }

        public final long zzj() {
            return this.zzam;
        }

        public final long zzk() {
            return this.zzab;
        }

        public final long zzl() {
            return this.zzau;
        }

        public final long zzm() {
            return this.zzl;
        }

        public final long zzn() {
            return this.zzw;
        }

        public final long zzo() {
            return this.zzn;
        }

        public final long zzp() {
            return this.zzm;
        }

        public final long zzq() {
            return this.zzk;
        }

        public final long zzr() {
            return this.zzbg;
        }

        public final long zzs() {
            return this.zzj;
        }

        public final long zzt() {
            return this.zzx;
        }

        public final zza zzu() {
            zza zza2 = this.zzbp;
            return zza2 == null ? zza.zze() : zza2;
        }

        public final zzc zzv() {
            zzc zzc2 = this.zzbn;
            return zzc2 == null ? zzc.zzc() : zzc2;
        }

        public final zzf zza(int i) {
            return (zzf) this.zzh.get(i);
        }

        public static zza zzw() {
            return (zza) zzc.zzcc();
        }

        public static zza zza(zzk zzk2) {
            return (zza) zzc.zza(zzk2);
        }

        public final zzo zzb(int i) {
            return (zzo) this.zzi.get(i);
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzfx.zza[i - 1]) {
                case 1:
                    return new zzk();
                case 2:
                    return new zza();
                case 3:
                    return zza((zzlc) zzc, "\u0004?\u0000\u0002\u0001P?\u0000\u0005\u0000\u0001င\u0000\u0002\u001b\u0003\u001b\u0004ဂ\u0001\u0005ဂ\u0002\u0006ဂ\u0003\u0007ဂ\u0005\bဈ\u0006\tဈ\u0007\nဈ\b\u000bဈ\t\fင\n\rဈ\u000b\u000eဈ\f\u0010ဈ\r\u0011ဂ\u000e\u0012ဂ\u000f\u0013ဈ\u0010\u0014ဇ\u0011\u0015ဈ\u0012\u0016ဂ\u0013\u0017င\u0014\u0018ဈ\u0015\u0019ဈ\u0016\u001aဂ\u0004\u001cဇ\u0017\u001d\u001b\u001eဈ\u0018\u001fင\u0019 င\u001a!င\u001b\"ဈ\u001c#ဂ\u001d$ဂ\u001e%ဈ\u001f&ဈ 'င!)ဈ\",ဉ#-\u001d.ဂ$/ဂ%2ဈ&4ဈ'5᠌(7ဇ)9ဈ*:ဇ+;ဉ,?ဈ-@\u001aAဈ.Cဂ/Dဇ0Gဈ1Hဇ2Iဈ3Jင4Kဈ5Lဉ6Mင7Oဉ8Pဈ9", new Object[]{"zze", "zzf", "zzg", "zzh", zzf.class, "zzi", zzo.class, "zzj", "zzk", "zzl", "zzn", "zzo", "zzp", "zzq", "zzr", "zzs", "zzt", "zzu", "zzv", "zzw", "zzx", "zzy", "zzz", "zzaa", "zzab", "zzac", "zzad", "zzae", "zzm", "zzaf", "zzag", zzd.class, "zzah", "zzai", "zzaj", "zzak", "zzal", "zzam", "zzan", "zzao", "zzap", "zzaq", "zzar", "zzas", "zzat", "zzau", "zzav", "zzaw", "zzax", "zzay", zzga.zzb(), "zzaz", "zzba", "zzbb", "zzbc", "zzbd", "zzbe", "zzbf", "zzbg", "zzbh", "zzbi", "zzbj", "zzbk", "zzbl", "zzbm", "zzbn", "zzbo", "zzbp", "zzbq"});
                case 4:
                    return zzc;
                case 5:
                    zzll<zzk> zzll = zzd;
                    if (zzll == null) {
                        synchronized (zzk.class) {
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

        public final String zzy() {
            return this.zzar;
        }

        public final String zzz() {
            return this.zzu;
        }

        public final String zzaa() {
            return this.zzaa;
        }

        public final String zzab() {
            return this.zzt;
        }

        public final String zzac() {
            return this.zzv;
        }

        public final String zzad() {
            return this.zzbi;
        }

        public final String zzae() {
            return this.zzax;
        }

        public final String zzaf() {
            return this.zzbk;
        }

        public final String zzag() {
            return this.zzq;
        }

        public final String zzah() {
            return this.zzao;
        }

        public final String zzai() {
            return this.zzah;
        }

        public final String zzaj() {
            return this.zzae;
        }

        public final String zzak() {
            return this.zzad;
        }

        public final String zzal() {
            return this.zzp;
        }

        public final String zzam() {
            return this.zzo;
        }

        public final String zzan() {
            return this.zzy;
        }

        public final String zzao() {
            return this.zzbd;
        }

        public final String zzap() {
            return this.zzr;
        }

        public final List<zzd> zzaq() {
            return this.zzag;
        }

        public final List<zzf> zzar() {
            return this.zzh;
        }

        public final List<zzo> zzas() {
            return this.zzi;
        }

        static /* synthetic */ void zza(zzk zzk2, Iterable iterable) {
            zzkc<zzd> zzkc = zzk2.zzag;
            if (!zzkc.zzc()) {
                zzk2.zzag = zzjt.zza(zzkc);
            }
            zzib.zza(iterable, zzk2.zzag);
        }

        static /* synthetic */ void zzb(zzk zzk2, Iterable iterable) {
            zzk2.zzcp();
            zzib.zza(iterable, zzk2.zzh);
        }

        static /* synthetic */ void zzc(zzk zzk2, Iterable iterable) {
            zzka zzka = zzk2.zzat;
            if (!zzka.zzc()) {
                zzk2.zzat = zzka.zzc(zzka.size() << 1);
            }
            zzib.zza(iterable, zzk2.zzat);
        }

        static /* synthetic */ void zzd(zzk zzk2, Iterable iterable) {
            zzkc<String> zzkc = zzk2.zzbe;
            if (!zzkc.zzc()) {
                zzk2.zzbe = zzjt.zza(zzkc);
            }
            zzib.zza(iterable, zzk2.zzbe);
        }

        static /* synthetic */ void zze(zzk zzk2, Iterable iterable) {
            zzk2.zzcq();
            zzib.zza(iterable, zzk2.zzi);
        }

        static /* synthetic */ void zza(zzk zzk2, zzf zzf2) {
            zzf2.getClass();
            zzk2.zzcp();
            zzk2.zzh.add(zzf2);
        }

        static /* synthetic */ void zza(zzk zzk2, zzo zzo2) {
            zzo2.getClass();
            zzk2.zzcq();
            zzk2.zzi.add(zzo2);
        }

        static /* synthetic */ void zzb(zzk zzk2) {
            zzk2.zze &= -262145;
            zzk2.zzaa = zzc.zzaa;
        }

        static /* synthetic */ void zzd(zzk zzk2) {
            zzk2.zze &= -257;
            zzk2.zzq = zzc.zzq;
        }

        static /* synthetic */ void zze(zzk zzk2) {
            zzk2.zze &= Integer.MAX_VALUE;
            zzk2.zzao = zzc.zzao;
        }

        static /* synthetic */ void zzg(zzk zzk2) {
            zzk2.zze &= -2097153;
            zzk2.zzad = zzc.zzad;
        }

        static /* synthetic */ void zzh(zzk zzk2) {
            zzk2.zze &= -131073;
            zzk2.zzz = false;
        }

        static /* synthetic */ void zzi(zzk zzk2) {
            zzk2.zze &= -33;
            zzk2.zzn = 0;
        }

        static /* synthetic */ void zzj(zzk zzk2) {
            zzk2.zze &= -17;
            zzk2.zzm = 0;
        }

        static /* synthetic */ void zzk(zzk zzk2) {
            zzk2.zze &= -65537;
            zzk2.zzy = zzc.zzy;
        }

        static /* synthetic */ void zzl(zzk zzk2) {
            zzk2.zzf &= -8193;
            zzk2.zzbd = zzc.zzbd;
        }

        static /* synthetic */ void zzm(zzk zzk2) {
            zzk2.zze &= -268435457;
            zzk2.zzal = zzc.zzal;
        }

        static /* synthetic */ void zza(zzk zzk2, int i) {
            zzk2.zzcp();
            zzk2.zzh.remove(i);
        }

        static /* synthetic */ void zzb(zzk zzk2, int i) {
            zzk2.zzcq();
            zzk2.zzi.remove(i);
        }

        static /* synthetic */ void zza(zzk zzk2, zza zza2) {
            zza2.getClass();
            zzk2.zzbp = zza2;
            zzk2.zzf |= 16777216;
        }

        static /* synthetic */ void zzc(zzk zzk2, int i) {
            zzk2.zzf |= 1048576;
            zzk2.zzbl = i;
        }

        static /* synthetic */ void zza(zzk zzk2, String str) {
            str.getClass();
            zzk2.zzf |= 4;
            zzk2.zzar = str;
        }

        static /* synthetic */ void zzb(zzk zzk2, String str) {
            str.getClass();
            zzk2.zze |= 4096;
            zzk2.zzu = str;
        }

        static /* synthetic */ void zzc(zzk zzk2, String str) {
            str.getClass();
            zzk2.zze |= 262144;
            zzk2.zzaa = str;
        }

        static /* synthetic */ void zzd(zzk zzk2, String str) {
            str.getClass();
            zzk2.zze |= 2048;
            zzk2.zzt = str;
        }

        static /* synthetic */ void zze(zzk zzk2, String str) {
            str.getClass();
            zzk2.zze |= 8192;
            zzk2.zzv = str;
        }

        static /* synthetic */ void zzd(zzk zzk2, int i) {
            zzk2.zze |= 33554432;
            zzk2.zzai = i;
        }

        static /* synthetic */ void zza(zzk zzk2, zzc zzc2) {
            zzc2.getClass();
            zzk2.zzbn = zzc2;
            zzk2.zzf |= 4194304;
        }

        static /* synthetic */ void zze(zzk zzk2, int i) {
            zzk2.zze |= 1048576;
            zzk2.zzac = i;
        }

        static /* synthetic */ void zza(zzk zzk2, long j) {
            zzk2.zzf |= 32;
            zzk2.zzav = j;
        }

        static /* synthetic */ void zzb(zzk zzk2, long j) {
            zzk2.zze |= C.BUFFER_FLAG_LAST_SAMPLE;
            zzk2.zzam = j;
        }

        static /* synthetic */ void zzf(zzk zzk2, String str) {
            str.getClass();
            zzk2.zzf |= 131072;
            zzk2.zzbi = str;
        }

        static /* synthetic */ void zzg(zzk zzk2, String str) {
            str.getClass();
            zzk2.zzf |= 128;
            zzk2.zzax = str;
        }

        static /* synthetic */ void zzh(zzk zzk2, String str) {
            str.getClass();
            zzk2.zzf |= 524288;
            zzk2.zzbk = str;
        }

        static /* synthetic */ void zzf(zzk zzk2, int i) {
            zzk2.zzf |= 8388608;
            zzk2.zzbo = i;
        }

        static /* synthetic */ void zzc(zzk zzk2, long j) {
            zzk2.zze |= 524288;
            zzk2.zzab = j;
        }

        static /* synthetic */ void zzi(zzk zzk2, String str) {
            str.getClass();
            zzk2.zze |= 256;
            zzk2.zzq = str;
        }

        static /* synthetic */ void zzj(zzk zzk2, String str) {
            str.getClass();
            zzk2.zze |= Integer.MIN_VALUE;
            zzk2.zzao = str;
        }

        static /* synthetic */ void zzd(zzk zzk2, long j) {
            zzk2.zzf |= 16;
            zzk2.zzau = j;
        }

        static /* synthetic */ void zza(zzk zzk2, boolean z) {
            zzk2.zzf |= 65536;
            zzk2.zzbh = z;
        }

        static /* synthetic */ void zze(zzk zzk2, long j) {
            zzk2.zze |= 8;
            zzk2.zzl = j;
        }

        static /* synthetic */ void zzk(zzk zzk2, String str) {
            str.getClass();
            zzk2.zzf |= 16384;
            zzk2.zzbf = str;
        }

        static /* synthetic */ void zza(zzk zzk2, int i, zzf zzf2) {
            zzf2.getClass();
            zzk2.zzcp();
            zzk2.zzh.set(i, zzf2);
        }

        static /* synthetic */ void zzl(zzk zzk2, String str) {
            str.getClass();
            zzk2.zze |= 16777216;
            zzk2.zzah = str;
        }

        static /* synthetic */ void zzm(zzk zzk2, String str) {
            str.getClass();
            zzk2.zze |= 4194304;
            zzk2.zzae = str;
        }

        static /* synthetic */ void zzf(zzk zzk2, long j) {
            zzk2.zze |= 16384;
            zzk2.zzw = j;
        }

        static /* synthetic */ void zzn(zzk zzk2, String str) {
            str.getClass();
            zzk2.zze |= 2097152;
            zzk2.zzad = str;
        }

        static /* synthetic */ void zzb(zzk zzk2, boolean z) {
            zzk2.zzf |= 262144;
            zzk2.zzbj = z;
        }

        static /* synthetic */ void zzc(zzk zzk2, boolean z) {
            zzk2.zze |= 131072;
            zzk2.zzz = z;
        }

        static /* synthetic */ void zzo(zzk zzk2, String str) {
            str.getClass();
            zzk2.zze |= 128;
            zzk2.zzp = str;
        }

        static /* synthetic */ void zzp(zzk zzk2, String str) {
            str.getClass();
            zzk2.zze |= 64;
            zzk2.zzo = str;
        }

        static /* synthetic */ void zza(zzk zzk2, zzl zzl2) {
            zzl2.getClass();
            zzk2.zzas = zzl2;
            zzk2.zzf |= 8;
        }

        static /* synthetic */ void zzg(zzk zzk2, long j) {
            zzk2.zze |= 32;
            zzk2.zzn = j;
        }

        static /* synthetic */ void zzh(zzk zzk2, long j) {
            zzk2.zze |= 16;
            zzk2.zzm = j;
        }

        static /* synthetic */ void zzg(zzk zzk2, int i) {
            zzk2.zze |= 1;
            zzk2.zzg = 1;
        }

        static /* synthetic */ void zzq(zzk zzk2, String str) {
            str.getClass();
            zzk2.zze |= 65536;
            zzk2.zzy = str;
        }

        static /* synthetic */ void zzh(zzk zzk2, int i) {
            zzk2.zzf |= 2;
            zzk2.zzaq = i;
        }

        static /* synthetic */ void zzd(zzk zzk2, boolean z) {
            zzk2.zze |= 8388608;
            zzk2.zzaf = z;
        }

        static /* synthetic */ void zzr(zzk zzk2, String str) {
            str.getClass();
            zzk2.zzf |= 8192;
            zzk2.zzbd = str;
        }

        static /* synthetic */ void zzi(zzk zzk2, long j) {
            zzk2.zze |= 4;
            zzk2.zzk = j;
        }

        static /* synthetic */ void zzj(zzk zzk2, long j) {
            zzk2.zzf |= 32768;
            zzk2.zzbg = j;
        }

        static /* synthetic */ void zzi(zzk zzk2, int i) {
            zzk2.zze |= 1024;
            zzk2.zzs = i;
        }

        static /* synthetic */ void zzk(zzk zzk2, long j) {
            zzk2.zze |= 2;
            zzk2.zzj = j;
        }

        static /* synthetic */ void zzl(zzk zzk2, long j) {
            zzk2.zze |= 32768;
            zzk2.zzx = j;
        }

        static /* synthetic */ void zza(zzk zzk2, int i, zzo zzo2) {
            zzo2.getClass();
            zzk2.zzcq();
            zzk2.zzi.set(i, zzo2);
        }

        static /* synthetic */ void zzs(zzk zzk2, String str) {
            str.getClass();
            zzk2.zze |= 512;
            zzk2.zzr = str;
        }

        static {
            zzk zzk2 = new zzk();
            zzc = zzk2;
            zzjt.zza(zzk.class, zzk2);
        }

        private zzk() {
        }

        private final void zzcp() {
            zzkc<zzf> zzkc = this.zzh;
            if (!zzkc.zzc()) {
                this.zzh = zzjt.zza(zzkc);
            }
        }

        private final void zzcq() {
            zzkc<zzo> zzkc = this.zzi;
            if (!zzkc.zzc()) {
                this.zzi = zzjt.zza(zzkc);
            }
        }

        public final boolean zzat() {
            return this.zzbh;
        }

        public final boolean zzau() {
            return this.zzbj;
        }

        public final boolean zzav() {
            return this.zzz;
        }

        public final boolean zzaw() {
            return this.zzaf;
        }

        public final boolean zzax() {
            return (this.zzf & 16777216) != 0;
        }

        public final boolean zzay() {
            return (this.zze & 33554432) != 0;
        }

        public final boolean zzaz() {
            return (this.zzf & 4194304) != 0;
        }

        public final boolean zzba() {
            return (this.zze & 1048576) != 0;
        }

        public final boolean zzbb() {
            return (this.zze & C.BUFFER_FLAG_LAST_SAMPLE) != 0;
        }

        public final boolean zzbc() {
            return (this.zzf & 131072) != 0;
        }

        public final boolean zzbd() {
            return (this.zzf & 128) != 0;
        }

        public final boolean zzbe() {
            return (this.zzf & 524288) != 0;
        }

        public final boolean zzbf() {
            return (this.zzf & 8388608) != 0;
        }

        public final boolean zzbg() {
            return (this.zze & 524288) != 0;
        }

        public final boolean zzbh() {
            return (this.zze & Integer.MIN_VALUE) != 0;
        }

        public final boolean zzbi() {
            return (this.zzf & 16) != 0;
        }

        public final boolean zzbj() {
            return (this.zze & 8) != 0;
        }

        public final boolean zzbk() {
            return (this.zze & 16384) != 0;
        }

        public final boolean zzbl() {
            return (this.zzf & 262144) != 0;
        }

        public final boolean zzbm() {
            return (this.zze & 131072) != 0;
        }

        public final boolean zzbn() {
            return (this.zze & 32) != 0;
        }

        public final boolean zzbo() {
            return (this.zze & 16) != 0;
        }

        public final boolean zzbp() {
            return (this.zze & 1) != 0;
        }

        public final boolean zzbq() {
            return (this.zzf & 2) != 0;
        }

        public final boolean zzbr() {
            return (this.zze & 8388608) != 0;
        }

        public final boolean zzbs() {
            return (this.zzf & 8192) != 0;
        }

        public final boolean zzbt() {
            return (this.zze & 4) != 0;
        }

        public final boolean zzbu() {
            return (this.zzf & 32768) != 0;
        }

        public final boolean zzbv() {
            return (this.zze & 1024) != 0;
        }

        public final boolean zzbw() {
            return (this.zze & 2) != 0;
        }

        public final boolean zzbx() {
            return (this.zze & 32768) != 0;
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
    public static final class zzl extends zzjt<zzl, zza> implements zzle {
        /* access modifiers changed from: private */
        public static final zzl zzc;
        private static volatile zzll<zzl> zzd;
        private int zze;
        private int zzf = 1;
        private zzkc<zzg> zzg = zzch();

        public static zza zza() {
            return (zza) zzc.zzcc();
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzfx.zza[i - 1]) {
                case 1:
                    return new zzl();
                case 2:
                    return new zza();
                case 3:
                    return zza((zzlc) zzc, "\u0004\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001᠌\u0000\u0002\u001b", new Object[]{"zze", "zzf", zzb.zzb(), "zzg", zzg.class});
                case 4:
                    return zzc;
                case 5:
                    zzll<zzl> zzll = zzd;
                    if (zzll == null) {
                        synchronized (zzl.class) {
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
        public static final class zza extends zzjt.zzb<zzl, zza> implements zzle {
            public final zza zza(zzg.zza zza) {
                zzak();
                zzl.zza((zzl) this.zza, (zzg) ((zzjt) zza.zzai()));
                return this;
            }

            private zza() {
                super(zzl.zzc);
            }
        }

        /* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
        public enum zzb implements zzjy {
            RADS(1),
            PROVISIONING(2);
            
            private final int zzd;

            public final int zza() {
                return this.zzd;
            }

            public static zzb zza(int i) {
                if (i == 1) {
                    return RADS;
                }
                if (i != 2) {
                    return null;
                }
                return PROVISIONING;
            }

            public static zzjx zzb() {
                return zzgc.zza;
            }

            public final String toString() {
                StringBuilder sb = new StringBuilder("<");
                sb.append(getClass().getName()).append('@').append(Integer.toHexString(System.identityHashCode(this)));
                sb.append(" number=").append(this.zzd);
                return sb.append(" name=").append(name()).append(Typography.greater).toString();
            }

            private zzb(int i) {
                this.zzd = i;
            }
        }

        static /* synthetic */ void zza(zzl zzl, zzg zzg2) {
            zzg2.getClass();
            zzkc<zzg> zzkc = zzl.zzg;
            if (!zzkc.zzc()) {
                zzl.zzg = zzjt.zza(zzkc);
            }
            zzl.zzg.add(zzg2);
        }

        static {
            zzl zzl = new zzl();
            zzc = zzl;
            zzjt.zza(zzl.class, zzl);
        }

        private zzl() {
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
    public static final class zzm extends zzjt<zzm, zza> implements zzle {
        /* access modifiers changed from: private */
        public static final zzm zzc;
        private static volatile zzll<zzm> zzd;
        /* access modifiers changed from: private */
        public zzjz zze = zzcg();
        /* access modifiers changed from: private */
        public zzjz zzf = zzcg();
        /* access modifiers changed from: private */
        public zzkc<zze> zzg = zzch();
        /* access modifiers changed from: private */
        public zzkc<zzn> zzh = zzch();

        public final int zza() {
            return this.zzg.size();
        }

        public final int zzb() {
            return this.zzf.size();
        }

        /* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
        public static final class zza extends zzjt.zzb<zzm, zza> implements zzle {
            public final zza zza(Iterable<? extends zze> iterable) {
                zzak();
                zzm.zza((zzm) this.zza, iterable);
                return this;
            }

            public final zza zzb(Iterable<? extends Long> iterable) {
                zzak();
                zzm.zzb((zzm) this.zza, iterable);
                return this;
            }

            public final zza zzc(Iterable<? extends zzn> iterable) {
                zzak();
                zzm.zzc((zzm) this.zza, iterable);
                return this;
            }

            public final zza zzd(Iterable<? extends Long> iterable) {
                zzak();
                zzm.zzd((zzm) this.zza, iterable);
                return this;
            }

            public final zza zza() {
                zzak();
                ((zzm) this.zza).zzg = zzm.zzch();
                return this;
            }

            public final zza zzb() {
                zzak();
                ((zzm) this.zza).zzf = zzm.zzcg();
                return this;
            }

            public final zza zzc() {
                zzak();
                ((zzm) this.zza).zzh = zzm.zzch();
                return this;
            }

            public final zza zzd() {
                zzak();
                ((zzm) this.zza).zze = zzm.zzcg();
                return this;
            }

            private zza() {
                super(zzm.zzc);
            }
        }

        public final int zzc() {
            return this.zzh.size();
        }

        public final int zzd() {
            return this.zze.size();
        }

        public static zza zze() {
            return (zza) zzc.zzcc();
        }

        public static zzm zzg() {
            return zzc;
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzfx.zza[i - 1]) {
                case 1:
                    return new zzm();
                case 2:
                    return new zza();
                case 3:
                    return zza((zzlc) zzc, "\u0004\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0004\u0000\u0001\u0015\u0002\u0015\u0003\u001b\u0004\u001b", new Object[]{"zze", "zzf", "zzg", zze.class, "zzh", zzn.class});
                case 4:
                    return zzc;
                case 5:
                    zzll<zzm> zzll = zzd;
                    if (zzll == null) {
                        synchronized (zzm.class) {
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

        public final List<zze> zzh() {
            return this.zzg;
        }

        public final List<Long> zzi() {
            return this.zzf;
        }

        public final List<zzn> zzj() {
            return this.zzh;
        }

        public final List<Long> zzk() {
            return this.zze;
        }

        static /* synthetic */ void zza(zzm zzm, Iterable iterable) {
            zzkc<zze> zzkc = zzm.zzg;
            if (!zzkc.zzc()) {
                zzm.zzg = zzjt.zza(zzkc);
            }
            zzib.zza(iterable, zzm.zzg);
        }

        static /* synthetic */ void zzb(zzm zzm, Iterable iterable) {
            zzjz zzjz = zzm.zzf;
            if (!zzjz.zzc()) {
                zzm.zzf = zzjt.zza(zzjz);
            }
            zzib.zza(iterable, zzm.zzf);
        }

        static /* synthetic */ void zzc(zzm zzm, Iterable iterable) {
            zzkc<zzn> zzkc = zzm.zzh;
            if (!zzkc.zzc()) {
                zzm.zzh = zzjt.zza(zzkc);
            }
            zzib.zza(iterable, zzm.zzh);
        }

        static /* synthetic */ void zzd(zzm zzm, Iterable iterable) {
            zzjz zzjz = zzm.zze;
            if (!zzjz.zzc()) {
                zzm.zze = zzjt.zza(zzjz);
            }
            zzib.zza(iterable, zzm.zze);
        }

        static {
            zzm zzm = new zzm();
            zzc = zzm;
            zzjt.zza(zzm.class, zzm);
        }

        private zzm() {
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
    public static final class zzn extends zzjt<zzn, zza> implements zzle {
        /* access modifiers changed from: private */
        public static final zzn zzc;
        private static volatile zzll<zzn> zzd;
        private int zze;
        private int zzf;
        private zzjz zzg = zzcg();

        public final int zza() {
            return this.zzg.size();
        }

        public final int zzb() {
            return this.zzf;
        }

        /* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
        public static final class zza extends zzjt.zzb<zzn, zza> implements zzle {
            public final zza zza(Iterable<? extends Long> iterable) {
                zzak();
                zzn.zza((zzn) this.zza, (Iterable) iterable);
                return this;
            }

            public final zza zza(int i) {
                zzak();
                zzn.zza((zzn) this.zza, i);
                return this;
            }

            private zza() {
                super(zzn.zzc);
            }
        }

        public final long zza(int i) {
            return this.zzg.zzb(i);
        }

        public static zza zzc() {
            return (zza) zzc.zzcc();
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzfx.zza[i - 1]) {
                case 1:
                    return new zzn();
                case 2:
                    return new zza();
                case 3:
                    return zza((zzlc) zzc, "\u0004\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001င\u0000\u0002\u0014", new Object[]{"zze", "zzf", "zzg"});
                case 4:
                    return zzc;
                case 5:
                    zzll<zzn> zzll = zzd;
                    if (zzll == null) {
                        synchronized (zzn.class) {
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

        public final List<Long> zze() {
            return this.zzg;
        }

        static /* synthetic */ void zza(zzn zzn, Iterable iterable) {
            zzjz zzjz = zzn.zzg;
            if (!zzjz.zzc()) {
                zzn.zzg = zzjt.zza(zzjz);
            }
            zzib.zza(iterable, zzn.zzg);
        }

        static /* synthetic */ void zza(zzn zzn, int i) {
            zzn.zze |= 1;
            zzn.zzf = i;
        }

        static {
            zzn zzn = new zzn();
            zzc = zzn;
            zzjt.zza(zzn.class, zzn);
        }

        private zzn() {
        }

        public final boolean zzf() {
            return (this.zze & 1) != 0;
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
    public static final class zzo extends zzjt<zzo, zza> implements zzle {
        /* access modifiers changed from: private */
        public static final zzo zzc;
        private static volatile zzll<zzo> zzd;
        private int zze;
        private long zzf;
        private String zzg = "";
        private String zzh = "";
        private long zzi;
        private float zzj;
        private double zzk;

        public final double zza() {
            return this.zzk;
        }

        public final float zzb() {
            return this.zzj;
        }

        /* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
        public static final class zza extends zzjt.zzb<zzo, zza> implements zzle {
            public final zza zza() {
                zzak();
                zzo.zza((zzo) this.zza);
                return this;
            }

            public final zza zzb() {
                zzak();
                zzo.zzb((zzo) this.zza);
                return this;
            }

            public final zza zzc() {
                zzak();
                zzo.zzc((zzo) this.zza);
                return this;
            }

            public final zza zza(double d) {
                zzak();
                zzo.zza((zzo) this.zza, d);
                return this;
            }

            public final zza zza(long j) {
                zzak();
                zzo.zza((zzo) this.zza, j);
                return this;
            }

            public final zza zza(String str) {
                zzak();
                zzo.zza((zzo) this.zza, str);
                return this;
            }

            public final zza zzb(long j) {
                zzak();
                zzo.zzb((zzo) this.zza, j);
                return this;
            }

            public final zza zzb(String str) {
                zzak();
                zzo.zzb((zzo) this.zza, str);
                return this;
            }

            private zza() {
                super(zzo.zzc);
            }
        }

        public final long zzc() {
            return this.zzi;
        }

        public final long zzd() {
            return this.zzf;
        }

        public static zza zze() {
            return (zza) zzc.zzcc();
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzfx.zza[i - 1]) {
                case 1:
                    return new zzo();
                case 2:
                    return new zza();
                case 3:
                    return zza((zzlc) zzc, "\u0004\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001ဂ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004ဂ\u0003\u0005ခ\u0004\u0006က\u0005", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk"});
                case 4:
                    return zzc;
                case 5:
                    zzll<zzo> zzll = zzd;
                    if (zzll == null) {
                        synchronized (zzo.class) {
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

        public final String zzg() {
            return this.zzg;
        }

        public final String zzh() {
            return this.zzh;
        }

        static /* synthetic */ void zza(zzo zzo) {
            zzo.zze &= -33;
            zzo.zzk = 0.0d;
        }

        static /* synthetic */ void zzb(zzo zzo) {
            zzo.zze &= -9;
            zzo.zzi = 0;
        }

        static /* synthetic */ void zzc(zzo zzo) {
            zzo.zze &= -5;
            zzo.zzh = zzc.zzh;
        }

        static /* synthetic */ void zza(zzo zzo, double d) {
            zzo.zze |= 32;
            zzo.zzk = d;
        }

        static /* synthetic */ void zza(zzo zzo, long j) {
            zzo.zze |= 8;
            zzo.zzi = j;
        }

        static /* synthetic */ void zza(zzo zzo, String str) {
            str.getClass();
            zzo.zze |= 2;
            zzo.zzg = str;
        }

        static /* synthetic */ void zzb(zzo zzo, long j) {
            zzo.zze |= 1;
            zzo.zzf = j;
        }

        static /* synthetic */ void zzb(zzo zzo, String str) {
            str.getClass();
            zzo.zze |= 4;
            zzo.zzh = str;
        }

        static {
            zzo zzo = new zzo();
            zzc = zzo;
            zzjt.zza(zzo.class, zzo);
        }

        private zzo() {
        }

        public final boolean zzi() {
            return (this.zze & 32) != 0;
        }

        public final boolean zzj() {
            return (this.zze & 16) != 0;
        }

        public final boolean zzk() {
            return (this.zze & 8) != 0;
        }

        public final boolean zzl() {
            return (this.zze & 1) != 0;
        }

        public final boolean zzm() {
            return (this.zze & 4) != 0;
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
    public static final class zzb extends zzjt<zzb, zza> implements zzle {
        /* access modifiers changed from: private */
        public static final zzb zzc;
        private static volatile zzll<zzb> zzd;
        private int zze;
        private String zzf = "";
        private String zzg = "";
        private String zzh = "";
        private String zzi = "";
        private String zzj = "";
        private String zzk = "";
        private String zzl = "";

        /* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
        public static final class zza extends zzjt.zzb<zzb, zza> implements zzle {
            private zza() {
                super(zzb.zzc);
            }
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzfx.zza[i - 1]) {
                case 1:
                    return new zzb();
                case 2:
                    return new zza();
                case 3:
                    return zza((zzlc) zzc, "\u0004\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004ဈ\u0003\u0005ဈ\u0004\u0006ဈ\u0005\u0007ဈ\u0006", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", "zzl"});
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

        static {
            zzb zzb = new zzb();
            zzc = zzb;
            zzjt.zza(zzb.class, zzb);
        }

        private zzb() {
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
        private zzb zzh;

        /* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
        public static final class zza extends zzjt.zzb<zzi, zza> implements zzle {
            private zza() {
                super(zzi.zzc);
            }
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzfx.zza[i - 1]) {
                case 1:
                    return new zzi();
                case 2:
                    return new zza();
                case 3:
                    return zza((zzlc) zzc, "\u0004\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဉ\u0002", new Object[]{"zze", "zzf", "zzg", "zzh"});
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

        static {
            zzi zzi = new zzi();
            zzc = zzi;
            zzjt.zza(zzi.class, zzi);
        }

        private zzi() {
        }
    }
}
