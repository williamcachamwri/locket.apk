package com.google.ads.interactivemedia.v3.internal;

import com.facebook.hermes.intl.Constants;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.LockSupport;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.CheckForNull;
import sun.misc.Unsafe;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public abstract class zztg<V> extends zzvi implements zzuu<V> {
    /* access modifiers changed from: private */
    public static final zza zza;
    static final boolean zzb;
    private static final Object zzba = new Object();
    static final zzut zzc;
    /* access modifiers changed from: private */
    @CheckForNull
    public volatile zzd listeners;
    /* access modifiers changed from: private */
    @CheckForNull
    public volatile Object value;
    /* access modifiers changed from: private */
    @CheckForNull
    public volatile zzk waiters;

    /* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
    abstract class zza {
        /* synthetic */ zza(zztf zztf) {
        }

        /* access modifiers changed from: package-private */
        public abstract zzd zza(zztg zztg, zzd zzd);

        /* access modifiers changed from: package-private */
        public abstract zzk zzb(zztg zztg, zzk zzk);

        /* access modifiers changed from: package-private */
        public abstract void zzc(zzk zzk, @CheckForNull zzk zzk2);

        /* access modifiers changed from: package-private */
        public abstract void zzd(zzk zzk, Thread thread);

        /* access modifiers changed from: package-private */
        public abstract boolean zze(zztg zztg, @CheckForNull zzd zzd, zzd zzd2);

        /* access modifiers changed from: package-private */
        public abstract boolean zzf(zztg zztg, @CheckForNull Object obj, Object obj2);

        /* access modifiers changed from: package-private */
        public abstract boolean zzg(zztg zztg, @CheckForNull zzk zzk, @CheckForNull zzk zzk2);
    }

    /* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
    final class zzb {
        @CheckForNull
        static final zzb zza;
        @CheckForNull
        static final zzb zzb;
        final boolean zzc;
        @CheckForNull
        final Throwable zzd;

        static {
            if (zztg.zzb) {
                zzb = null;
                zza = null;
                return;
            }
            zzb = new zzb(false, (Throwable) null);
            zza = new zzb(true, (Throwable) null);
        }

        zzb(boolean z, @CheckForNull Throwable th) {
            this.zzc = z;
            this.zzd = th;
        }
    }

    /* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
    final class zzc {
        static final zzc zza = new zzc(new Throwable("Failure occurred while trying to finish a future.") {
            public final synchronized Throwable fillInStackTrace() {
                return this;
            }
        });
        final Throwable zzb;

        zzc(Throwable th) {
            th.getClass();
            Throwable th2 = th;
            this.zzb = th;
        }
    }

    /* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
    final class zzd {
        static final zzd zza = new zzd();
        @CheckForNull
        zzd next;
        @CheckForNull
        final Runnable zzb;
        @CheckForNull
        final Executor zzc;

        zzd() {
            this.zzb = null;
            this.zzc = null;
        }

        zzd(Runnable runnable, Executor executor) {
            this.zzb = runnable;
            this.zzc = executor;
        }
    }

    /* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
    final class zze extends zza {
        final AtomicReferenceFieldUpdater<zzk, Thread> zza;
        final AtomicReferenceFieldUpdater<zzk, zzk> zzb;
        final AtomicReferenceFieldUpdater<? super zztg<?>, zzk> zzc;
        final AtomicReferenceFieldUpdater<? super zztg<?>, zzd> zzd;
        final AtomicReferenceFieldUpdater<? super zztg<?>, Object> zze;

        zze(AtomicReferenceFieldUpdater atomicReferenceFieldUpdater, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater3, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater4, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater5) {
            super((zztf) null);
            this.zza = atomicReferenceFieldUpdater;
            this.zzb = atomicReferenceFieldUpdater2;
            this.zzc = atomicReferenceFieldUpdater3;
            this.zzd = atomicReferenceFieldUpdater4;
            this.zze = atomicReferenceFieldUpdater5;
        }

        /* access modifiers changed from: package-private */
        public final zzd zza(zztg zztg, zzd zzd2) {
            return this.zzd.getAndSet(zztg, zzd2);
        }

        /* access modifiers changed from: package-private */
        public final zzk zzb(zztg zztg, zzk zzk) {
            return this.zzc.getAndSet(zztg, zzk);
        }

        /* access modifiers changed from: package-private */
        public final void zzc(zzk zzk, @CheckForNull zzk zzk2) {
            this.zzb.lazySet(zzk, zzk2);
        }

        /* access modifiers changed from: package-private */
        public final void zzd(zzk zzk, Thread thread) {
            this.zza.lazySet(zzk, thread);
        }

        /* access modifiers changed from: package-private */
        public final boolean zze(zztg zztg, @CheckForNull zzd zzd2, zzd zzd3) {
            return zzth.zza(this.zzd, zztg, zzd2, zzd3);
        }

        /* access modifiers changed from: package-private */
        public final boolean zzf(zztg zztg, @CheckForNull Object obj, Object obj2) {
            return zzth.zza(this.zze, zztg, obj, obj2);
        }

        /* access modifiers changed from: package-private */
        public final boolean zzg(zztg zztg, @CheckForNull zzk zzk, @CheckForNull zzk zzk2) {
            return zzth.zza(this.zzc, zztg, zzk, zzk2);
        }
    }

    /* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
    final class zzf<V> implements Runnable {
        final zztg<V> zza;
        final zzuu<? extends V> zzb;

        zzf(zztg zztg, zzuu zzuu) {
            this.zza = zztg;
            this.zzb = zzuu;
        }

        public final void run() {
            if (this.zza.value == this) {
                zzuu<? extends V> zzuu = this.zzb;
                if (zztg.zza.zzf(this.zza, this, zztg.zzs(zzuu))) {
                    zztg.zzx(this.zza, false);
                }
            }
        }
    }

    /* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
    final class zzg extends zza {
        private zzg() {
            throw null;
        }

        /* synthetic */ zzg(zzti zzti) {
            super((zztf) null);
        }

        /* access modifiers changed from: package-private */
        public final zzd zza(zztg zztg, zzd zzd) {
            zzd zzf;
            synchronized (zztg) {
                zzf = zztg.listeners;
                if (zzf != zzd) {
                    zztg.listeners = zzd;
                }
            }
            return zzf;
        }

        /* access modifiers changed from: package-private */
        public final zzk zzb(zztg zztg, zzk zzk) {
            zzk zzg;
            synchronized (zztg) {
                zzg = zztg.waiters;
                if (zzg != zzk) {
                    zztg.waiters = zzk;
                }
            }
            return zzg;
        }

        /* access modifiers changed from: package-private */
        public final void zzc(zzk zzk, @CheckForNull zzk zzk2) {
            zzk.next = zzk2;
        }

        /* access modifiers changed from: package-private */
        public final void zzd(zzk zzk, Thread thread) {
            zzk.thread = thread;
        }

        /* access modifiers changed from: package-private */
        public final boolean zze(zztg zztg, @CheckForNull zzd zzd, zzd zzd2) {
            synchronized (zztg) {
                if (zztg.listeners != zzd) {
                    return false;
                }
                zztg.listeners = zzd2;
                return true;
            }
        }

        /* access modifiers changed from: package-private */
        public final boolean zzf(zztg zztg, @CheckForNull Object obj, Object obj2) {
            synchronized (zztg) {
                if (zztg.value != obj) {
                    return false;
                }
                zztg.value = obj2;
                return true;
            }
        }

        /* access modifiers changed from: package-private */
        public final boolean zzg(zztg zztg, @CheckForNull zzk zzk, @CheckForNull zzk zzk2) {
            synchronized (zztg) {
                if (zztg.waiters != zzk) {
                    return false;
                }
                zztg.waiters = zzk2;
                return true;
            }
        }
    }

    /* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
    interface zzh<V> extends zzuu<V> {
    }

    /* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
    abstract class zzi<V> extends zztg<V> implements zzh<V> {
        zzi() {
        }
    }

    /* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
    final class zzj extends zza {
        static final Unsafe zza;
        static final long zzb;
        static final long zzc;
        static final long zzd;
        static final long zze;
        static final long zzf;

        /* JADX WARNING: Code restructure failed: missing block: B:11:0x005c, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:13:0x0068, code lost:
            throw new java.lang.RuntimeException("Could not initialize intrinsics", r0.getCause());
         */
        /* JADX WARNING: Code restructure failed: missing block: B:3:?, code lost:
            r0 = (sun.misc.Unsafe) java.security.AccessController.doPrivileged(new com.google.ads.interactivemedia.v3.internal.zztg.zzj.AnonymousClass1());
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:2:0x0005 */
        static {
            /*
                sun.misc.Unsafe r0 = sun.misc.Unsafe.getUnsafe()     // Catch:{ SecurityException -> 0x0005 }
                goto L_0x0010
            L_0x0005:
                com.google.ads.interactivemedia.v3.internal.zztg$zzj$1 r0 = new com.google.ads.interactivemedia.v3.internal.zztg$zzj$1     // Catch:{ PrivilegedActionException -> 0x005c }
                r0.<init>()     // Catch:{ PrivilegedActionException -> 0x005c }
                java.lang.Object r0 = java.security.AccessController.doPrivileged(r0)     // Catch:{ PrivilegedActionException -> 0x005c }
                sun.misc.Unsafe r0 = (sun.misc.Unsafe) r0     // Catch:{ PrivilegedActionException -> 0x005c }
            L_0x0010:
                java.lang.Class<com.google.ads.interactivemedia.v3.internal.zztg> r1 = com.google.ads.interactivemedia.v3.internal.zztg.class
                java.lang.String r2 = "waiters"
                java.lang.reflect.Field r2 = r1.getDeclaredField(r2)     // Catch:{ NoSuchFieldException -> 0x0055 }
                long r2 = r0.objectFieldOffset(r2)     // Catch:{ NoSuchFieldException -> 0x0055 }
                zzc = r2     // Catch:{ NoSuchFieldException -> 0x0055 }
                java.lang.String r2 = "listeners"
                java.lang.reflect.Field r2 = r1.getDeclaredField(r2)     // Catch:{ NoSuchFieldException -> 0x0055 }
                long r2 = r0.objectFieldOffset(r2)     // Catch:{ NoSuchFieldException -> 0x0055 }
                zzb = r2     // Catch:{ NoSuchFieldException -> 0x0055 }
                java.lang.String r2 = "value"
                java.lang.reflect.Field r1 = r1.getDeclaredField(r2)     // Catch:{ NoSuchFieldException -> 0x0055 }
                long r1 = r0.objectFieldOffset(r1)     // Catch:{ NoSuchFieldException -> 0x0055 }
                zzd = r1     // Catch:{ NoSuchFieldException -> 0x0055 }
                java.lang.Class<com.google.ads.interactivemedia.v3.internal.zztg$zzk> r1 = com.google.ads.interactivemedia.v3.internal.zztg.zzk.class
                java.lang.String r2 = "thread"
                java.lang.reflect.Field r1 = r1.getDeclaredField(r2)     // Catch:{ NoSuchFieldException -> 0x0055 }
                long r1 = r0.objectFieldOffset(r1)     // Catch:{ NoSuchFieldException -> 0x0055 }
                zze = r1     // Catch:{ NoSuchFieldException -> 0x0055 }
                java.lang.Class<com.google.ads.interactivemedia.v3.internal.zztg$zzk> r1 = com.google.ads.interactivemedia.v3.internal.zztg.zzk.class
                java.lang.String r2 = "next"
                java.lang.reflect.Field r1 = r1.getDeclaredField(r2)     // Catch:{ NoSuchFieldException -> 0x0055 }
                long r1 = r0.objectFieldOffset(r1)     // Catch:{ NoSuchFieldException -> 0x0055 }
                zzf = r1     // Catch:{ NoSuchFieldException -> 0x0055 }
                zza = r0     // Catch:{ NoSuchFieldException -> 0x0055 }
                return
            L_0x0055:
                r0 = move-exception
                java.lang.RuntimeException r1 = new java.lang.RuntimeException
                r1.<init>(r0)
                throw r1
            L_0x005c:
                r0 = move-exception
                java.lang.RuntimeException r1 = new java.lang.RuntimeException
                java.lang.String r2 = "Could not initialize intrinsics"
                java.lang.Throwable r0 = r0.getCause()
                r1.<init>(r2, r0)
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.zztg.zzj.<clinit>():void");
        }

        private zzj() {
            throw null;
        }

        /* synthetic */ zzj(zztk zztk) {
            super((zztf) null);
        }

        /*  JADX ERROR: StackOverflow in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: 
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
            	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
            */
        final com.google.ads.interactivemedia.v3.internal.zztg.zzd zza(com.google.ads.interactivemedia.v3.internal.zztg r3, com.google.ads.interactivemedia.v3.internal.zztg.zzd r4) {
            /*
                r2 = this;
            L_0x0000:
                com.google.ads.interactivemedia.v3.internal.zztg$zzd r0 = r3.listeners
                if (r4 != r0) goto L_0x0007
                goto L_0x000d
            L_0x0007:
                boolean r1 = r2.zze(r3, r0, r4)
                if (r1 == 0) goto L_0x0000
            L_0x000d:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.zztg.zzj.zza(com.google.ads.interactivemedia.v3.internal.zztg, com.google.ads.interactivemedia.v3.internal.zztg$zzd):com.google.ads.interactivemedia.v3.internal.zztg$zzd");
        }

        /*  JADX ERROR: StackOverflow in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: 
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
            	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
            */
        final com.google.ads.interactivemedia.v3.internal.zztg.zzk zzb(com.google.ads.interactivemedia.v3.internal.zztg r3, com.google.ads.interactivemedia.v3.internal.zztg.zzk r4) {
            /*
                r2 = this;
            L_0x0000:
                com.google.ads.interactivemedia.v3.internal.zztg$zzk r0 = r3.waiters
                if (r4 != r0) goto L_0x0007
                goto L_0x000d
            L_0x0007:
                boolean r1 = r2.zzg(r3, r0, r4)
                if (r1 == 0) goto L_0x0000
            L_0x000d:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.zztg.zzj.zzb(com.google.ads.interactivemedia.v3.internal.zztg, com.google.ads.interactivemedia.v3.internal.zztg$zzk):com.google.ads.interactivemedia.v3.internal.zztg$zzk");
        }

        /* access modifiers changed from: package-private */
        public final void zzc(zzk zzk, @CheckForNull zzk zzk2) {
            zza.putObject(zzk, zzf, zzk2);
        }

        /* access modifiers changed from: package-private */
        public final void zzd(zzk zzk, Thread thread) {
            zza.putObject(zzk, zze, thread);
        }

        /* access modifiers changed from: package-private */
        public final boolean zze(zztg zztg, @CheckForNull zzd zzd2, zzd zzd3) {
            return zztj.zza(zza, zztg, zzb, zzd2, zzd3);
        }

        /* access modifiers changed from: package-private */
        public final boolean zzf(zztg zztg, @CheckForNull Object obj, Object obj2) {
            return zztj.zza(zza, zztg, zzd, obj, obj2);
        }

        /* access modifiers changed from: package-private */
        public final boolean zzg(zztg zztg, @CheckForNull zzk zzk, @CheckForNull zzk zzk2) {
            return zztj.zza(zza, zztg, zzc, zzk, zzk2);
        }
    }

    /* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
    final class zzk {
        static final zzk zza = new zzk(false);
        @CheckForNull
        volatile zzk next;
        @CheckForNull
        volatile Thread thread;

        zzk() {
            zztg.zza.zzd(this, Thread.currentThread());
        }

        zzk(boolean z) {
        }
    }

    static {
        boolean z;
        Throwable th;
        Throwable th2;
        zza zza2;
        try {
            z = Boolean.parseBoolean(System.getProperty("guava.concurrent.generate_cancellation_cause", Constants.CASEFIRST_FALSE));
        } catch (SecurityException unused) {
            z = false;
        }
        zzb = z;
        Class<zztg> cls = zztg.class;
        zzc = new zzut(cls);
        try {
            zza2 = new zzj((zztk) null);
            th2 = null;
            th = null;
        } catch (Error | Exception e) {
            try {
                th2 = e;
                zza2 = new zze(AtomicReferenceFieldUpdater.newUpdater(zzk.class, Thread.class, "thread"), AtomicReferenceFieldUpdater.newUpdater(zzk.class, zzk.class, "next"), AtomicReferenceFieldUpdater.newUpdater(cls, zzk.class, "waiters"), AtomicReferenceFieldUpdater.newUpdater(cls, zzd.class, "listeners"), AtomicReferenceFieldUpdater.newUpdater(cls, Object.class, "value"));
                th = null;
            } catch (Error | Exception e2) {
                th = e2;
                th2 = e;
                zza2 = new zzg((zzti) null);
            }
        }
        zza = zza2;
        if (th != null) {
            zzut zzut = zzc;
            zzut.zza().logp(Level.SEVERE, "com.google.common.util.concurrent.AbstractFuture", "<clinit>", "UnsafeAtomicHelper is broken!", th2);
            zzut.zza().logp(Level.SEVERE, "com.google.common.util.concurrent.AbstractFuture", "<clinit>", "SafeAtomicHelper is broken!", th);
        }
    }

    protected zztg() {
    }

    private static final Object zzA(Object obj) throws ExecutionException {
        if (obj instanceof zzb) {
            Throwable th = ((zzb) obj).zzd;
            CancellationException cancellationException = new CancellationException("Task was cancelled.");
            cancellationException.initCause(th);
            throw cancellationException;
        } else if (obj instanceof zzc) {
            throw new ExecutionException(((zzc) obj).zzb);
        } else if (obj == zzba) {
            return null;
        } else {
            return obj;
        }
    }

    /* access modifiers changed from: private */
    public static Object zzs(zzuu zzuu) {
        Throwable zzj2;
        if (zzuu instanceof zzh) {
            Object obj = ((zztg) zzuu).value;
            if (obj instanceof zzb) {
                zzb zzb2 = (zzb) obj;
                if (zzb2.zzc) {
                    Throwable th = zzb2.zzd;
                    obj = th != null ? new zzb(false, th) : zzb.zzb;
                }
            }
            return Objects.requireNonNull(obj);
        } else if ((zzuu instanceof zzvi) && (zzj2 = ((zzvi) zzuu).zzj()) != null) {
            return new zzc(zzj2);
        } else {
            boolean isCancelled = zzuu.isCancelled();
            if ((!zzb) && isCancelled) {
                return Objects.requireNonNull(zzb.zzb);
            }
            try {
                Object zzt = zzt(zzuu);
                if (!isCancelled) {
                    return zzt == null ? zzba : zzt;
                }
                String valueOf = String.valueOf(zzuu);
                return new zzb(false, new IllegalArgumentException("get() did not throw CancellationException, despite reporting isCancelled() == true: " + valueOf));
            } catch (ExecutionException e) {
                if (isCancelled) {
                    return new zzb(false, new IllegalArgumentException("get() did not throw CancellationException, despite reporting isCancelled() == true: ".concat(String.valueOf(String.valueOf(zzuu))), e));
                }
                return new zzc(e.getCause());
            } catch (CancellationException e2) {
                if (!isCancelled) {
                    return new zzc(new IllegalArgumentException("get() threw CancellationException, despite reporting isCancelled() == false: ".concat(String.valueOf(String.valueOf(zzuu))), e2));
                }
                return new zzb(false, e2);
            } catch (Error | Exception e3) {
                return new zzc(e3);
            }
        }
    }

    private static Object zzt(Future future) throws ExecutionException {
        Object obj;
        boolean z = false;
        while (true) {
            try {
                obj = future.get();
                break;
            } catch (InterruptedException unused) {
                z = true;
            } catch (Throwable th) {
                if (z) {
                    Thread.currentThread().interrupt();
                }
                throw th;
            }
        }
        if (z) {
            Thread.currentThread().interrupt();
        }
        return obj;
    }

    private final void zzu(StringBuilder sb) {
        try {
            Object zzt = zzt(this);
            sb.append("SUCCESS, result=[");
            if (zzt == null) {
                sb.append("null");
            } else if (zzt == this) {
                sb.append("this future");
            } else {
                sb.append(zzt.getClass().getName());
                sb.append("@");
                sb.append(Integer.toHexString(System.identityHashCode(zzt)));
            }
            sb.append("]");
        } catch (ExecutionException e) {
            sb.append("FAILURE, cause=[");
            sb.append(e.getCause());
            sb.append("]");
        } catch (CancellationException unused) {
            sb.append("CANCELLED");
        } catch (Exception e2) {
            sb.append("UNKNOWN, cause=[");
            sb.append(e2.getClass());
            sb.append(" thrown from get()]");
        }
    }

    private final void zzv(StringBuilder sb) {
        String str;
        int length = sb.length();
        sb.append("PENDING");
        Object obj = this.value;
        if (obj instanceof zzf) {
            sb.append(", setFuture=[");
            zzw(sb, ((zzf) obj).zzb);
            sb.append("]");
        } else {
            try {
                str = zzqm.zza(zza());
            } catch (Exception | StackOverflowError e) {
                str = "Exception thrown from implementation: ".concat(String.valueOf(String.valueOf(e.getClass())));
            }
            if (str != null) {
                sb.append(", info=[");
                sb.append(str);
                sb.append("]");
            }
        }
        if (isDone()) {
            sb.delete(length, sb.length());
            zzu(sb);
        }
    }

    private final void zzw(StringBuilder sb, @CheckForNull Object obj) {
        if (obj == this) {
            try {
                sb.append("this future");
            } catch (Exception | StackOverflowError e) {
                sb.append("Exception thrown from implementation: ");
                sb.append(e.getClass());
            }
        } else {
            sb.append(obj);
        }
    }

    /* access modifiers changed from: private */
    public static void zzx(zztg<V> zztg, boolean z) {
        zzd zzd2 = null;
        while (true) {
            for (zzk zzb2 = zza.zzb(zztg, zzk.zza); zzb2 != null; zzb2 = zzb2.next) {
                Thread thread = zzb2.thread;
                if (thread != null) {
                    zzb2.thread = null;
                    LockSupport.unpark(thread);
                }
            }
            if (z) {
                zztg.zzp();
            }
            zztg.zzb();
            zzd zzd3 = zzd2;
            zzd zza2 = zza.zza(zztg, zzd.zza);
            zzd zzd4 = zzd3;
            while (zza2 != null) {
                zzd zzd5 = zza2.next;
                zza2.next = zzd4;
                zzd4 = zza2;
                zza2 = zzd5;
            }
            while (zzd4 != null) {
                zzd2 = zzd4.next;
                Runnable runnable = (Runnable) Objects.requireNonNull(zzd4.zzb);
                if (runnable instanceof zzf) {
                    zzf zzf2 = (zzf) runnable;
                    zztg = zzf2.zza;
                    if (zztg.value == zzf2) {
                        if (zza.zzf(zztg, zzf2, zzs(zzf2.zzb))) {
                            z = false;
                        }
                    } else {
                        continue;
                    }
                } else {
                    zzy(runnable, (Executor) Objects.requireNonNull(zzd4.zzc));
                }
                zzd4 = zzd2;
            }
            return;
        }
    }

    private static void zzy(Runnable runnable, Executor executor) {
        try {
            executor.execute(runnable);
        } catch (Exception e) {
            Logger zza2 = zzc.zza();
            Level level = Level.SEVERE;
            String valueOf = String.valueOf(runnable);
            String valueOf2 = String.valueOf(executor);
            zza2.logp(level, "com.google.common.util.concurrent.AbstractFuture", "executeListener", "RuntimeException while executing runnable " + valueOf + " with executor " + valueOf2, e);
        }
    }

    private final void zzz(zzk zzk2) {
        zzk2.thread = null;
        while (true) {
            zzk zzk3 = this.waiters;
            if (zzk3 != zzk.zza) {
                zzk zzk4 = null;
                while (zzk3 != null) {
                    zzk zzk5 = zzk3.next;
                    if (zzk3.thread != null) {
                        zzk4 = zzk3;
                    } else if (zzk4 != null) {
                        zzk4.next = zzk5;
                        if (zzk4.thread == null) {
                        }
                    } else if (!zza.zzg(this, zzk3, zzk5)) {
                    }
                    zzk3 = zzk5;
                }
                return;
            }
            return;
        }
    }

    /* JADX WARNING: type inference failed for: r0v5, types: [com.google.ads.interactivemedia.v3.internal.zzuu<? extends V>, com.google.ads.interactivemedia.v3.internal.zzuu] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean cancel(boolean r8) {
        /*
            r7 = this;
            java.lang.Object r0 = r7.value
            boolean r1 = r0 instanceof com.google.ads.interactivemedia.v3.internal.zztg.zzf
            r2 = 0
            r3 = 1
            if (r0 != 0) goto L_0x000a
            r4 = r3
            goto L_0x000b
        L_0x000a:
            r4 = r2
        L_0x000b:
            r1 = r1 | r4
            if (r1 == 0) goto L_0x0060
            boolean r1 = zzb
            if (r1 == 0) goto L_0x001f
            com.google.ads.interactivemedia.v3.internal.zztg$zzb r1 = new com.google.ads.interactivemedia.v3.internal.zztg$zzb
            java.util.concurrent.CancellationException r4 = new java.util.concurrent.CancellationException
            java.lang.String r5 = "Future.cancel() was called."
            r4.<init>(r5)
            r1.<init>(r8, r4)
            goto L_0x002a
        L_0x001f:
            if (r8 == 0) goto L_0x0024
            com.google.ads.interactivemedia.v3.internal.zztg$zzb r1 = com.google.ads.interactivemedia.v3.internal.zztg.zzb.zza
            goto L_0x0026
        L_0x0024:
            com.google.ads.interactivemedia.v3.internal.zztg$zzb r1 = com.google.ads.interactivemedia.v3.internal.zztg.zzb.zzb
        L_0x0026:
            java.lang.Object r1 = java.util.Objects.requireNonNull(r1)
        L_0x002a:
            r4 = r7
            r5 = r2
        L_0x002c:
            com.google.ads.interactivemedia.v3.internal.zztg$zza r6 = zza
            boolean r6 = r6.zzf(r4, r0, r1)
            if (r6 == 0) goto L_0x0059
            zzx(r4, r8)
            boolean r4 = r0 instanceof com.google.ads.interactivemedia.v3.internal.zztg.zzf
            if (r4 == 0) goto L_0x0057
            com.google.ads.interactivemedia.v3.internal.zztg$zzf r0 = (com.google.ads.interactivemedia.v3.internal.zztg.zzf) r0
            com.google.ads.interactivemedia.v3.internal.zzuu<? extends V> r0 = r0.zzb
            boolean r4 = r0 instanceof com.google.ads.interactivemedia.v3.internal.zztg.zzh
            if (r4 == 0) goto L_0x0054
            r4 = r0
            com.google.ads.interactivemedia.v3.internal.zztg r4 = (com.google.ads.interactivemedia.v3.internal.zztg) r4
            java.lang.Object r0 = r4.value
            if (r0 != 0) goto L_0x004c
            r5 = r3
            goto L_0x004d
        L_0x004c:
            r5 = r2
        L_0x004d:
            boolean r6 = r0 instanceof com.google.ads.interactivemedia.v3.internal.zztg.zzf
            r5 = r5 | r6
            if (r5 == 0) goto L_0x0057
            r5 = r3
            goto L_0x002c
        L_0x0054:
            r0.cancel(r8)
        L_0x0057:
            r2 = r3
            goto L_0x0060
        L_0x0059:
            java.lang.Object r0 = r4.value
            boolean r6 = r0 instanceof com.google.ads.interactivemedia.v3.internal.zztg.zzf
            if (r6 != 0) goto L_0x002c
            r2 = r5
        L_0x0060:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.zztg.cancel(boolean):boolean");
    }

    public final Object get() throws InterruptedException, ExecutionException {
        Object obj;
        if (!Thread.interrupted()) {
            Object obj2 = this.value;
            if ((obj2 != null) && (!(obj2 instanceof zzf))) {
                return zzA(obj2);
            }
            zzk zzk2 = this.waiters;
            if (zzk2 != zzk.zza) {
                zzk zzk3 = new zzk();
                do {
                    zza zza2 = zza;
                    zza2.zzc(zzk3, zzk2);
                    if (zza2.zzg(this, zzk2, zzk3)) {
                        do {
                            LockSupport.park(this);
                            if (!Thread.interrupted()) {
                                obj = this.value;
                            } else {
                                zzz(zzk3);
                                throw new InterruptedException();
                            }
                        } while (!((obj != null) & (!(obj instanceof zzf))));
                        return zzA(obj);
                    }
                    zzk2 = this.waiters;
                } while (zzk2 != zzk.zza);
            }
            return zzA(Objects.requireNonNull(this.value));
        }
        throw new InterruptedException();
    }

    public final boolean isCancelled() {
        return this.value instanceof zzb;
    }

    public final boolean isDone() {
        Object obj = this.value;
        return (obj != null) & (!(obj instanceof zzf));
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        if (getClass().getName().startsWith("com.google.common.util.concurrent.")) {
            sb.append(getClass().getSimpleName());
        } else {
            sb.append(getClass().getName());
        }
        sb.append('@');
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append("[status=");
        if (this.value instanceof zzb) {
            sb.append("CANCELLED");
        } else if (isDone()) {
            zzu(sb);
        } else {
            zzv(sb);
        }
        sb.append("]");
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    @CheckForNull
    public String zza() {
        if (!(this instanceof ScheduledFuture)) {
            return null;
        }
        long delay = ((ScheduledFuture) this).getDelay(TimeUnit.MILLISECONDS);
        return "remaining delay=[" + delay + " ms]";
    }

    /* access modifiers changed from: protected */
    public void zzb() {
    }

    /* access modifiers changed from: protected */
    public boolean zzc(Object obj) {
        if (obj == null) {
            obj = zzba;
        }
        if (!zza.zzf(this, (Object) null, obj)) {
            return false;
        }
        zzx(this, false);
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean zzd(Throwable th) {
        th.getClass();
        Throwable th2 = th;
        if (!zza.zzf(this, (Object) null, new zzc(th))) {
            return false;
        }
        zzx(this, false);
        return true;
    }

    /* access modifiers changed from: protected */
    @CheckForNull
    public final Throwable zzj() {
        if (!(this instanceof zzh)) {
            return null;
        }
        Object obj = this.value;
        if (obj instanceof zzc) {
            return ((zzc) obj).zzb;
        }
        return null;
    }

    public final void zzo(Runnable runnable, Executor executor) {
        zzd zzd2;
        zzqh.zzc(executor, "Executor was null.");
        if (isDone() || (zzd2 = this.listeners) == zzd.zza) {
            zzy(runnable, executor);
        }
        zzd zzd3 = new zzd(runnable, executor);
        do {
            zzd3.next = zzd2;
            if (!zza.zze(this, zzd2, zzd3)) {
                zzd2 = this.listeners;
            } else {
                return;
            }
        } while (zzd2 != zzd.zza);
        zzy(runnable, executor);
    }

    /* access modifiers changed from: protected */
    public void zzp() {
    }

    /* access modifiers changed from: protected */
    public final boolean zzr() {
        Object obj = this.value;
        return (obj instanceof zzb) && ((zzb) obj).zzc;
    }

    /* access modifiers changed from: protected */
    public final boolean zzq(zzuu zzuu) {
        zzc zzc2;
        zzuu.getClass();
        Object obj = this.value;
        if (obj == null) {
            if (zzuu.isDone()) {
                if (!zza.zzf(this, (Object) null, zzs(zzuu))) {
                    return false;
                }
                zzx(this, false);
                return true;
            }
            zzf zzf2 = new zzf(this, zzuu);
            if (zza.zzf(this, (Object) null, zzf2)) {
                try {
                    zzuu.zzo(zzf2, zzua.INSTANCE);
                } catch (Throwable th) {
                    try {
                        zzc2 = new zzc(th);
                    } catch (Error | Exception unused) {
                        zzc2 = zzc.zza;
                    }
                    zza.zzf(this, zzf2, zzc2);
                }
                return true;
            }
            obj = this.value;
        }
        if (obj instanceof zzb) {
            zzuu.cancel(((zzb) obj).zzc);
        }
        return false;
    }

    public final Object get(long j, TimeUnit timeUnit) throws InterruptedException, TimeoutException, ExecutionException {
        long j2 = j;
        TimeUnit timeUnit2 = timeUnit;
        long nanos = timeUnit2.toNanos(j2);
        if (!Thread.interrupted()) {
            Object obj = this.value;
            boolean z = true;
            if ((obj != null) && (!(obj instanceof zzf))) {
                return zzA(obj);
            }
            long nanoTime = nanos > 0 ? System.nanoTime() + nanos : 0;
            if (nanos >= 1000) {
                zzk zzk2 = this.waiters;
                if (zzk2 != zzk.zza) {
                    zzk zzk3 = new zzk();
                    do {
                        zza zza2 = zza;
                        zza2.zzc(zzk3, zzk2);
                        if (zza2.zzg(this, zzk2, zzk3)) {
                            do {
                                LockSupport.parkNanos(this, Math.min(nanos, 2147483647999999999L));
                                if (!Thread.interrupted()) {
                                    Object obj2 = this.value;
                                    if ((obj2 != null) && (!(obj2 instanceof zzf))) {
                                        return zzA(obj2);
                                    }
                                    nanos = nanoTime - System.nanoTime();
                                } else {
                                    zzz(zzk3);
                                    throw new InterruptedException();
                                }
                            } while (nanos >= 1000);
                            zzz(zzk3);
                        } else {
                            zzk2 = this.waiters;
                        }
                    } while (zzk2 != zzk.zza);
                }
                return zzA(Objects.requireNonNull(this.value));
            }
            while (nanos > 0) {
                Object obj3 = this.value;
                if ((obj3 != null) && (!(obj3 instanceof zzf))) {
                    return zzA(obj3);
                }
                if (!Thread.interrupted()) {
                    nanos = nanoTime - System.nanoTime();
                } else {
                    throw new InterruptedException();
                }
            }
            String zztg = toString();
            String lowerCase = timeUnit.toString().toLowerCase(Locale.ROOT);
            String str = "Waited " + j2 + " " + timeUnit.toString().toLowerCase(Locale.ROOT);
            if (nanos + 1000 < 0) {
                String concat = str.concat(" (plus ");
                long j3 = -nanos;
                long convert = timeUnit2.convert(j3, TimeUnit.NANOSECONDS);
                long nanos2 = j3 - timeUnit2.toNanos(convert);
                int i = (convert > 0 ? 1 : (convert == 0 ? 0 : -1));
                if (i != 0 && nanos2 <= 1000) {
                    z = false;
                }
                if (i > 0) {
                    String str2 = concat + convert + " " + lowerCase;
                    if (z) {
                        str2 = str2.concat(",");
                    }
                    concat = str2.concat(" ");
                }
                if (z) {
                    concat = concat + nanos2 + " nanoseconds ";
                }
                str = concat.concat("delay)");
            }
            if (isDone()) {
                throw new TimeoutException(str.concat(" but future completed as timeout expired"));
            }
            throw new TimeoutException(str + " for " + zztg);
        }
        throw new InterruptedException();
    }
}
