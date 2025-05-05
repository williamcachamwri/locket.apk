package com.google.android.gms.internal.pal;

import com.facebook.hermes.intl.Constants;
import java.util.Locale;
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

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public abstract class zzjn<V> extends zzjs implements zzjq<V> {
    static final boolean zza;
    private static final Logger zzb;
    /* access modifiers changed from: private */
    public static final zza zzc;
    private static final Object zzd = new Object();
    /* access modifiers changed from: private */
    @CheckForNull
    public volatile zzd listeners;
    /* access modifiers changed from: private */
    @CheckForNull
    public volatile Object value;
    /* access modifiers changed from: private */
    @CheckForNull
    public volatile zzk waiters;

    /* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
    abstract class zza {
        /* synthetic */ zza(AnonymousClass1 r1) {
        }

        /* access modifiers changed from: package-private */
        public abstract zzd zza(zzjn zzjn, zzd zzd);

        /* access modifiers changed from: package-private */
        public abstract zzk zzb(zzjn zzjn, zzk zzk);

        /* access modifiers changed from: package-private */
        public abstract void zzc(zzk zzk, @CheckForNull zzk zzk2);

        /* access modifiers changed from: package-private */
        public abstract void zzd(zzk zzk, Thread thread);

        /* access modifiers changed from: package-private */
        public abstract boolean zze(zzjn zzjn, @CheckForNull Object obj, Object obj2);

        /* access modifiers changed from: package-private */
        public abstract boolean zzf(zzjn zzjn, @CheckForNull zzk zzk, @CheckForNull zzk zzk2);
    }

    /* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
    final class zzb {
        @CheckForNull
        static final zzb zza;
        @CheckForNull
        static final zzb zzb;
        final boolean zzc;
        @CheckForNull
        final Throwable zzd;

        static {
            if (zzjn.zza) {
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

    /* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
    final class zzc {
        static final zzc zza = new zzc(new Throwable("Failure occurred while trying to finish a future.") {
            public final synchronized Throwable fillInStackTrace() {
                return this;
            }
        });
        final Throwable zzb;

        zzc(Throwable th) {
            th.getClass();
            this.zzb = th;
        }
    }

    /* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
    final class zzd {
        static final zzd zza = new zzd();
        @CheckForNull
        zzd next;
        @CheckForNull
        final Runnable zzb = null;
        @CheckForNull
        final Executor zzc = null;

        zzd() {
        }
    }

    /* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
    final class zze extends zza {
        final AtomicReferenceFieldUpdater<zzk, Thread> zza;
        final AtomicReferenceFieldUpdater<zzk, zzk> zzb;
        final AtomicReferenceFieldUpdater<zzjn, zzk> zzc;
        final AtomicReferenceFieldUpdater<zzjn, zzd> zzd;
        final AtomicReferenceFieldUpdater<zzjn, Object> zze;

        zze(AtomicReferenceFieldUpdater atomicReferenceFieldUpdater, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater3, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater4, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater5) {
            super((AnonymousClass1) null);
            this.zza = atomicReferenceFieldUpdater;
            this.zzb = atomicReferenceFieldUpdater2;
            this.zzc = atomicReferenceFieldUpdater3;
            this.zzd = atomicReferenceFieldUpdater4;
            this.zze = atomicReferenceFieldUpdater5;
        }

        /* access modifiers changed from: package-private */
        public final zzd zza(zzjn zzjn, zzd zzd2) {
            return this.zzd.getAndSet(zzjn, zzd2);
        }

        /* access modifiers changed from: package-private */
        public final zzk zzb(zzjn zzjn, zzk zzk) {
            return this.zzc.getAndSet(zzjn, zzk);
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
        public final boolean zze(zzjn zzjn, @CheckForNull Object obj, Object obj2) {
            return zzjo.zza(this.zze, zzjn, obj, obj2);
        }

        /* access modifiers changed from: package-private */
        public final boolean zzf(zzjn zzjn, @CheckForNull zzk zzk, @CheckForNull zzk zzk2) {
            return zzjo.zza(this.zzc, zzjn, zzk, zzk2);
        }
    }

    /* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
    final class zzf<V> implements Runnable {
        final zzjn<V> zza;
        final zzjq<? extends V> zzb;

        public final void run() {
            throw null;
        }
    }

    /* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
    final class zzg extends zza {
        private zzg() {
            super((AnonymousClass1) null);
        }

        /* synthetic */ zzg(AnonymousClass1 r1) {
            super((AnonymousClass1) null);
        }

        /* access modifiers changed from: package-private */
        public final zzd zza(zzjn zzjn, zzd zzd) {
            zzd zzb;
            synchronized (zzjn) {
                zzb = zzjn.listeners;
                if (zzb != zzd) {
                    zzd unused = zzjn.listeners = zzd;
                }
            }
            return zzb;
        }

        /* access modifiers changed from: package-private */
        public final zzk zzb(zzjn zzjn, zzk zzk) {
            zzk zzd;
            synchronized (zzjn) {
                zzd = zzjn.waiters;
                if (zzd != zzk) {
                    zzk unused = zzjn.waiters = zzk;
                }
            }
            return zzd;
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
        public final boolean zze(zzjn zzjn, @CheckForNull Object obj, Object obj2) {
            synchronized (zzjn) {
                if (zzjn.value != obj) {
                    return false;
                }
                Object unused = zzjn.value = obj2;
                return true;
            }
        }

        /* access modifiers changed from: package-private */
        public final boolean zzf(zzjn zzjn, @CheckForNull zzk zzk, @CheckForNull zzk zzk2) {
            synchronized (zzjn) {
                if (zzjn.waiters != zzk) {
                    return false;
                }
                zzk unused = zzjn.waiters = zzk2;
                return true;
            }
        }
    }

    /* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
    interface zzh<V> extends zzjq<V> {
    }

    /* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
    abstract class zzi<V> extends zzjn<V> implements zzh<V> {
        zzi() {
        }
    }

    /* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
    final class zzj extends zza {
        static final Unsafe zza;
        static final long zzb;
        static final long zzc;
        static final long zzd;
        static final long zze;
        static final long zzf;

        /* JADX WARNING: Code restructure failed: missing block: B:13:0x005e, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:15:0x006a, code lost:
            throw new java.lang.RuntimeException("Could not initialize intrinsics", r0.getCause());
         */
        /* JADX WARNING: Code restructure failed: missing block: B:3:?, code lost:
            r0 = (sun.misc.Unsafe) java.security.AccessController.doPrivileged(new com.google.android.gms.internal.pal.zzjn.zzj.AnonymousClass1());
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:2:0x0005 */
        static {
            /*
                sun.misc.Unsafe r0 = sun.misc.Unsafe.getUnsafe()     // Catch:{ SecurityException -> 0x0005 }
                goto L_0x0010
            L_0x0005:
                com.google.android.gms.internal.pal.zzjn$zzj$1 r0 = new com.google.android.gms.internal.pal.zzjn$zzj$1     // Catch:{ PrivilegedActionException -> 0x005e }
                r0.<init>()     // Catch:{ PrivilegedActionException -> 0x005e }
                java.lang.Object r0 = java.security.AccessController.doPrivileged(r0)     // Catch:{ PrivilegedActionException -> 0x005e }
                sun.misc.Unsafe r0 = (sun.misc.Unsafe) r0     // Catch:{ PrivilegedActionException -> 0x005e }
            L_0x0010:
                java.lang.Class<com.google.android.gms.internal.pal.zzjn> r1 = com.google.android.gms.internal.pal.zzjn.class
                java.lang.String r2 = "waiters"
                java.lang.reflect.Field r2 = r1.getDeclaredField(r2)     // Catch:{ NoSuchFieldException -> 0x0057, RuntimeException -> 0x0055 }
                long r2 = r0.objectFieldOffset(r2)     // Catch:{ NoSuchFieldException -> 0x0057, RuntimeException -> 0x0055 }
                zzc = r2     // Catch:{ NoSuchFieldException -> 0x0057, RuntimeException -> 0x0055 }
                java.lang.String r2 = "listeners"
                java.lang.reflect.Field r2 = r1.getDeclaredField(r2)     // Catch:{ NoSuchFieldException -> 0x0057, RuntimeException -> 0x0055 }
                long r2 = r0.objectFieldOffset(r2)     // Catch:{ NoSuchFieldException -> 0x0057, RuntimeException -> 0x0055 }
                zzb = r2     // Catch:{ NoSuchFieldException -> 0x0057, RuntimeException -> 0x0055 }
                java.lang.String r2 = "value"
                java.lang.reflect.Field r1 = r1.getDeclaredField(r2)     // Catch:{ NoSuchFieldException -> 0x0057, RuntimeException -> 0x0055 }
                long r1 = r0.objectFieldOffset(r1)     // Catch:{ NoSuchFieldException -> 0x0057, RuntimeException -> 0x0055 }
                zzd = r1     // Catch:{ NoSuchFieldException -> 0x0057, RuntimeException -> 0x0055 }
                java.lang.Class<com.google.android.gms.internal.pal.zzjn$zzk> r1 = com.google.android.gms.internal.pal.zzjn.zzk.class
                java.lang.String r2 = "thread"
                java.lang.reflect.Field r1 = r1.getDeclaredField(r2)     // Catch:{ NoSuchFieldException -> 0x0057, RuntimeException -> 0x0055 }
                long r1 = r0.objectFieldOffset(r1)     // Catch:{ NoSuchFieldException -> 0x0057, RuntimeException -> 0x0055 }
                zze = r1     // Catch:{ NoSuchFieldException -> 0x0057, RuntimeException -> 0x0055 }
                java.lang.Class<com.google.android.gms.internal.pal.zzjn$zzk> r1 = com.google.android.gms.internal.pal.zzjn.zzk.class
                java.lang.String r2 = "next"
                java.lang.reflect.Field r1 = r1.getDeclaredField(r2)     // Catch:{ NoSuchFieldException -> 0x0057, RuntimeException -> 0x0055 }
                long r1 = r0.objectFieldOffset(r1)     // Catch:{ NoSuchFieldException -> 0x0057, RuntimeException -> 0x0055 }
                zzf = r1     // Catch:{ NoSuchFieldException -> 0x0057, RuntimeException -> 0x0055 }
                zza = r0     // Catch:{ NoSuchFieldException -> 0x0057, RuntimeException -> 0x0055 }
                return
            L_0x0055:
                r0 = move-exception
                throw r0
            L_0x0057:
                r0 = move-exception
                java.lang.RuntimeException r1 = new java.lang.RuntimeException
                r1.<init>(r0)
                throw r1
            L_0x005e:
                r0 = move-exception
                java.lang.RuntimeException r1 = new java.lang.RuntimeException
                java.lang.String r2 = "Could not initialize intrinsics"
                java.lang.Throwable r0 = r0.getCause()
                r1.<init>(r2, r0)
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.pal.zzjn.zzj.<clinit>():void");
        }

        private zzj() {
            super((AnonymousClass1) null);
        }

        /* synthetic */ zzj(AnonymousClass1 r1) {
            super((AnonymousClass1) null);
        }

        /* access modifiers changed from: package-private */
        public final zzd zza(zzjn zzjn, zzd zzd2) {
            zzd zzb2;
            do {
                zzb2 = zzjn.listeners;
                if (zzd2 == zzb2) {
                    break;
                }
            } while (!zzjp.zza(zza, zzjn, zzb, zzb2, zzd2));
            return zzb2;
        }

        /*  JADX ERROR: StackOverflow in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: 
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
            	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
            */
        final com.google.android.gms.internal.pal.zzjn.zzk zzb(com.google.android.gms.internal.pal.zzjn r3, com.google.android.gms.internal.pal.zzjn.zzk r4) {
            /*
                r2 = this;
            L_0x0000:
                com.google.android.gms.internal.pal.zzjn$zzk r0 = r3.waiters
                if (r4 != r0) goto L_0x0007
                return r0
            L_0x0007:
                boolean r1 = r2.zzf(r3, r0, r4)
                if (r1 == 0) goto L_0x0000
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.pal.zzjn.zzj.zzb(com.google.android.gms.internal.pal.zzjn, com.google.android.gms.internal.pal.zzjn$zzk):com.google.android.gms.internal.pal.zzjn$zzk");
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
        public final boolean zze(zzjn zzjn, @CheckForNull Object obj, Object obj2) {
            return zzjp.zza(zza, zzjn, zzd, obj, obj2);
        }

        /* access modifiers changed from: package-private */
        public final boolean zzf(zzjn zzjn, @CheckForNull zzk zzk, @CheckForNull zzk zzk2) {
            return zzjp.zza(zza, zzjn, zzc, zzk, zzk2);
        }
    }

    /* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
    final class zzk {
        static final zzk zza = new zzk(false);
        @CheckForNull
        volatile zzk next;
        @CheckForNull
        volatile Thread thread;

        zzk() {
            zzjn.zzc.zzd(this, Thread.currentThread());
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
        zza = z;
        Class<zzjn> cls = zzjn.class;
        zzb = Logger.getLogger(cls.getName());
        try {
            zza2 = new zzj((AnonymousClass1) null);
            th2 = null;
            th = null;
        } catch (Error | RuntimeException e) {
            try {
                th2 = e;
                zza2 = new zze(AtomicReferenceFieldUpdater.newUpdater(zzk.class, Thread.class, "thread"), AtomicReferenceFieldUpdater.newUpdater(zzk.class, zzk.class, "next"), AtomicReferenceFieldUpdater.newUpdater(cls, zzk.class, "waiters"), AtomicReferenceFieldUpdater.newUpdater(cls, zzd.class, "listeners"), AtomicReferenceFieldUpdater.newUpdater(cls, Object.class, "value"));
                th = null;
            } catch (Error | RuntimeException e2) {
                th = e2;
                th2 = e;
                zza2 = new zzg((AnonymousClass1) null);
            }
        }
        zzc = zza2;
        if (th != null) {
            Logger logger = zzb;
            logger.logp(Level.SEVERE, "com.google.common.util.concurrent.AbstractFuture", "<clinit>", "UnsafeAtomicHelper is broken!", th2);
            logger.logp(Level.SEVERE, "com.google.common.util.concurrent.AbstractFuture", "<clinit>", "SafeAtomicHelper is broken!", th);
        }
    }

    protected zzjn() {
    }

    private static Object zzj(zzjq zzjq) {
        Throwable zzh2;
        if (zzjq instanceof zzh) {
            Object obj = ((zzjn) zzjq).value;
            if (obj instanceof zzb) {
                zzb zzb2 = (zzb) obj;
                if (zzb2.zzc) {
                    Throwable th = zzb2.zzd;
                    obj = th != null ? new zzb(false, th) : zzb.zzb;
                }
            }
            obj.getClass();
            return obj;
        } else if ((zzjq instanceof zzjs) && (zzh2 = ((zzjs) zzjq).zzh()) != null) {
            return new zzc(zzh2);
        } else {
            boolean isCancelled = zzjq.isCancelled();
            if ((!zza) && isCancelled) {
                zzb zzb3 = zzb.zzb;
                zzb3.getClass();
                return zzb3;
            }
            try {
                Object zzk2 = zzk(zzjq);
                if (!isCancelled) {
                    return zzk2 == null ? zzd : zzk2;
                }
                return new zzb(false, new IllegalArgumentException("get() did not throw CancellationException, despite reporting isCancelled() == true: " + zzjq));
            } catch (ExecutionException e) {
                if (!isCancelled) {
                    return new zzc(e.getCause());
                }
                new StringBuilder("get() did not throw CancellationException, despite reporting isCancelled() == true: ").append(zzjq);
                return new zzb(false, new IllegalArgumentException("get() did not throw CancellationException, despite reporting isCancelled() == true: ".concat(String.valueOf(zzjq)), e));
            } catch (CancellationException e2) {
                if (isCancelled) {
                    return new zzb(false, e2);
                }
                new StringBuilder("get() threw CancellationException, despite reporting isCancelled() == false: ").append(zzjq);
                return new zzc(new IllegalArgumentException("get() threw CancellationException, despite reporting isCancelled() == false: ".concat(String.valueOf(zzjq)), e2));
            } catch (Error | RuntimeException e3) {
                return new zzc(e3);
            }
        }
    }

    private static Object zzk(Future future) throws ExecutionException {
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

    private final void zzl(StringBuilder sb) {
        try {
            Object zzk2 = zzk(this);
            sb.append("SUCCESS, result=[");
            if (zzk2 == null) {
                sb.append("null");
            } else if (zzk2 == this) {
                sb.append("this future");
            } else {
                sb.append(zzk2.getClass().getName());
                sb.append("@");
                sb.append(Integer.toHexString(System.identityHashCode(zzk2)));
            }
            sb.append("]");
        } catch (ExecutionException e) {
            sb.append("FAILURE, cause=[");
            sb.append(e.getCause());
            sb.append("]");
        } catch (CancellationException unused) {
            sb.append("CANCELLED");
        } catch (RuntimeException e2) {
            sb.append("UNKNOWN, cause=[");
            sb.append(e2.getClass());
            sb.append(" thrown from get()]");
        }
    }

    private final void zzm(StringBuilder sb) {
        String str;
        String str2;
        int length = sb.length();
        sb.append("PENDING");
        Object obj = this.value;
        if (obj instanceof zzf) {
            sb.append(", setFuture=[");
            zzn(sb, ((zzf) obj).zzb);
            sb.append("]");
        } else {
            try {
                if (this instanceof ScheduledFuture) {
                    str2 = "remaining delay=[" + ((ScheduledFuture) this).getDelay(TimeUnit.MILLISECONDS) + " ms]";
                } else {
                    str2 = null;
                }
                str = zzir.zza(str2);
            } catch (RuntimeException | StackOverflowError e) {
                StringBuilder sb2 = new StringBuilder("Exception thrown from implementation: ");
                Class<?> cls = e.getClass();
                sb2.append(cls);
                str = "Exception thrown from implementation: ".concat(String.valueOf(cls));
            }
            if (str != null) {
                sb.append(", info=[");
                sb.append(str);
                sb.append("]");
            }
        }
        if (isDone()) {
            sb.delete(length, sb.length());
            zzl(sb);
        }
    }

    private final void zzn(StringBuilder sb, @CheckForNull Object obj) {
        if (obj == this) {
            try {
                sb.append("this future");
            } catch (RuntimeException | StackOverflowError e) {
                sb.append("Exception thrown from implementation: ");
                sb.append(e.getClass());
            }
        } else {
            sb.append(obj);
        }
    }

    private static void zzo(zzjn<V> zzjn) {
        zzd zzd2 = null;
        while (true) {
            for (zzk zzb2 = zzc.zzb(zzjn, zzk.zza); zzb2 != null; zzb2 = zzb2.next) {
                Thread thread = zzb2.thread;
                if (thread != null) {
                    zzb2.thread = null;
                    LockSupport.unpark(thread);
                }
            }
            zzd zzd3 = zzd2;
            zzd zza2 = zzc.zza(zzjn, zzd.zza);
            zzd zzd4 = zzd3;
            while (zza2 != null) {
                zzd zzd5 = zza2.next;
                zza2.next = zzd4;
                zzd4 = zza2;
                zza2 = zzd5;
            }
            while (true) {
                if (zzd4 != null) {
                    zzd2 = zzd4.next;
                    Runnable runnable = zzd4.zzb;
                    runnable.getClass();
                    if (runnable instanceof zzf) {
                        zzf zzf2 = (zzf) runnable;
                        zzjn = zzf2.zza;
                        if (zzjn.value == zzf2) {
                            if (zzc.zze(zzjn, zzf2, zzj(zzf2.zzb))) {
                            }
                        } else {
                            continue;
                        }
                    } else {
                        Executor executor = zzd4.zzc;
                        executor.getClass();
                        try {
                            executor.execute(runnable);
                        } catch (RuntimeException e) {
                            zzb.logp(Level.SEVERE, "com.google.common.util.concurrent.AbstractFuture", "executeListener", "RuntimeException while executing runnable " + runnable + " with executor " + executor, e);
                        }
                    }
                    zzd4 = zzd2;
                } else {
                    return;
                }
            }
        }
    }

    private final void zzp(zzk zzk2) {
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
                    } else if (!zzc.zzf(this, zzk3, zzk5)) {
                    }
                    zzk3 = zzk5;
                }
                return;
            }
            return;
        }
    }

    private static final Object zzq(Object obj) throws ExecutionException {
        if (obj instanceof zzb) {
            Throwable th = ((zzb) obj).zzd;
            CancellationException cancellationException = new CancellationException("Task was cancelled.");
            cancellationException.initCause(th);
            throw cancellationException;
        } else if (obj instanceof zzc) {
            throw new ExecutionException(((zzc) obj).zzb);
        } else if (obj == zzd) {
            return null;
        } else {
            return obj;
        }
    }

    /* JADX WARNING: type inference failed for: r0v5, types: [com.google.android.gms.internal.pal.zzjq, com.google.android.gms.internal.pal.zzjq<? extends V>] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean cancel(boolean r8) {
        /*
            r7 = this;
            java.lang.Object r0 = r7.value
            r1 = 0
            r2 = 1
            if (r0 != 0) goto L_0x0008
            r3 = r2
            goto L_0x0009
        L_0x0008:
            r3 = r1
        L_0x0009:
            boolean r4 = r0 instanceof com.google.android.gms.internal.pal.zzjn.zzf
            r3 = r3 | r4
            if (r3 == 0) goto L_0x005f
            boolean r3 = zza
            if (r3 == 0) goto L_0x001f
            com.google.android.gms.internal.pal.zzjn$zzb r3 = new com.google.android.gms.internal.pal.zzjn$zzb
            java.util.concurrent.CancellationException r4 = new java.util.concurrent.CancellationException
            java.lang.String r5 = "Future.cancel() was called."
            r4.<init>(r5)
            r3.<init>(r8, r4)
            goto L_0x0029
        L_0x001f:
            if (r8 == 0) goto L_0x0024
            com.google.android.gms.internal.pal.zzjn$zzb r3 = com.google.android.gms.internal.pal.zzjn.zzb.zza
            goto L_0x0026
        L_0x0024:
            com.google.android.gms.internal.pal.zzjn$zzb r3 = com.google.android.gms.internal.pal.zzjn.zzb.zzb
        L_0x0026:
            r3.getClass()
        L_0x0029:
            r4 = r7
            r5 = r1
        L_0x002b:
            com.google.android.gms.internal.pal.zzjn$zza r6 = zzc
            boolean r6 = r6.zze(r4, r0, r3)
            if (r6 == 0) goto L_0x0058
            zzo(r4)
            boolean r4 = r0 instanceof com.google.android.gms.internal.pal.zzjn.zzf
            if (r4 == 0) goto L_0x0056
            com.google.android.gms.internal.pal.zzjn$zzf r0 = (com.google.android.gms.internal.pal.zzjn.zzf) r0
            com.google.android.gms.internal.pal.zzjq<? extends V> r0 = r0.zzb
            boolean r4 = r0 instanceof com.google.android.gms.internal.pal.zzjn.zzh
            if (r4 == 0) goto L_0x0053
            r4 = r0
            com.google.android.gms.internal.pal.zzjn r4 = (com.google.android.gms.internal.pal.zzjn) r4
            java.lang.Object r0 = r4.value
            if (r0 != 0) goto L_0x004b
            r5 = r2
            goto L_0x004c
        L_0x004b:
            r5 = r1
        L_0x004c:
            boolean r6 = r0 instanceof com.google.android.gms.internal.pal.zzjn.zzf
            r5 = r5 | r6
            if (r5 == 0) goto L_0x0056
            r5 = r2
            goto L_0x002b
        L_0x0053:
            r0.cancel(r8)
        L_0x0056:
            r1 = r2
            goto L_0x005f
        L_0x0058:
            java.lang.Object r0 = r4.value
            boolean r6 = r0 instanceof com.google.android.gms.internal.pal.zzjn.zzf
            if (r6 != 0) goto L_0x002b
            r1 = r5
        L_0x005f:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.pal.zzjn.cancel(boolean):boolean");
    }

    public final Object get() throws InterruptedException, ExecutionException {
        Object obj;
        if (!Thread.interrupted()) {
            Object obj2 = this.value;
            if ((obj2 != null) && (!(obj2 instanceof zzf))) {
                return zzq(obj2);
            }
            zzk zzk2 = this.waiters;
            if (zzk2 != zzk.zza) {
                zzk zzk3 = new zzk();
                do {
                    zza zza2 = zzc;
                    zza2.zzc(zzk3, zzk2);
                    if (zza2.zzf(this, zzk2, zzk3)) {
                        do {
                            LockSupport.park(this);
                            if (!Thread.interrupted()) {
                                obj = this.value;
                            } else {
                                zzp(zzk3);
                                throw new InterruptedException();
                            }
                        } while (!((obj != null) & (!(obj instanceof zzf))));
                        return zzq(obj);
                    }
                    zzk2 = this.waiters;
                } while (zzk2 != zzk.zza);
            }
            Object obj3 = this.value;
            obj3.getClass();
            return zzq(obj3);
        }
        throw new InterruptedException();
    }

    public final boolean isCancelled() {
        return this.value instanceof zzb;
    }

    public final boolean isDone() {
        Object obj = this.value;
        return (!(obj instanceof zzf)) & (obj != null);
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
            zzl(sb);
        } else {
            zzm(sb);
        }
        sb.append("]");
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    @CheckForNull
    public final Throwable zzh() {
        if (!(this instanceof zzh)) {
            return null;
        }
        Object obj = this.value;
        if (obj instanceof zzc) {
            return ((zzc) obj).zzb;
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public boolean zzi(Object obj) {
        if (obj == null) {
            obj = zzd;
        }
        if (!zzc.zze(this, (Object) null, obj)) {
            return false;
        }
        zzo(this);
        return true;
    }

    public final Object get(long j, TimeUnit timeUnit) throws InterruptedException, TimeoutException, ExecutionException {
        long j2 = j;
        TimeUnit timeUnit2 = timeUnit;
        long nanos = timeUnit2.toNanos(j2);
        if (!Thread.interrupted()) {
            Object obj = this.value;
            boolean z = true;
            if ((obj != null) && (!(obj instanceof zzf))) {
                return zzq(obj);
            }
            long nanoTime = nanos > 0 ? System.nanoTime() + nanos : 0;
            if (nanos >= 1000) {
                zzk zzk2 = this.waiters;
                if (zzk2 != zzk.zza) {
                    zzk zzk3 = new zzk();
                    do {
                        zza zza2 = zzc;
                        zza2.zzc(zzk3, zzk2);
                        if (zza2.zzf(this, zzk2, zzk3)) {
                            do {
                                LockSupport.parkNanos(this, Math.min(nanos, 2147483647999999999L));
                                if (!Thread.interrupted()) {
                                    Object obj2 = this.value;
                                    if ((obj2 != null) && (!(obj2 instanceof zzf))) {
                                        return zzq(obj2);
                                    }
                                    nanos = nanoTime - System.nanoTime();
                                } else {
                                    zzp(zzk3);
                                    throw new InterruptedException();
                                }
                            } while (nanos >= 1000);
                            zzp(zzk3);
                        } else {
                            zzk2 = this.waiters;
                        }
                    } while (zzk2 != zzk.zza);
                }
                Object obj3 = this.value;
                obj3.getClass();
                return zzq(obj3);
            }
            while (nanos > 0) {
                Object obj4 = this.value;
                if ((obj4 != null) && (!(obj4 instanceof zzf))) {
                    return zzq(obj4);
                }
                if (!Thread.interrupted()) {
                    nanos = nanoTime - System.nanoTime();
                } else {
                    throw new InterruptedException();
                }
            }
            String zzjn = toString();
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
            throw new TimeoutException(str + " for " + zzjn);
        }
        throw new InterruptedException();
    }
}
