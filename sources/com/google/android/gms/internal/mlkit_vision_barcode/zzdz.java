package com.google.android.gms.internal.mlkit_vision_barcode;

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

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.1 */
public abstract class zzdz<V> extends zzex implements zzet<V> {
    static final boolean zza;
    static final zzes zzb;
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
    public volatile zzj waiters;

    /* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.1 */
    abstract class zza {
        /* synthetic */ zza(zzdy zzdy) {
        }

        /* access modifiers changed from: package-private */
        public abstract zzd zza(zzdz zzdz, zzd zzd);

        /* access modifiers changed from: package-private */
        public abstract zzj zzb(zzdz zzdz, zzj zzj);

        /* access modifiers changed from: package-private */
        public abstract void zzc(zzj zzj, @CheckForNull zzj zzj2);

        /* access modifiers changed from: package-private */
        public abstract void zzd(zzj zzj, Thread thread);

        /* access modifiers changed from: package-private */
        public abstract boolean zze(zzdz zzdz, @CheckForNull zzd zzd, zzd zzd2);

        /* access modifiers changed from: package-private */
        public abstract boolean zzf(zzdz zzdz, @CheckForNull Object obj, Object obj2);

        /* access modifiers changed from: package-private */
        public abstract boolean zzg(zzdz zzdz, @CheckForNull zzj zzj, @CheckForNull zzj zzj2);
    }

    /* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.1 */
    final class zzb {
        @CheckForNull
        static final zzb zza;
        @CheckForNull
        static final zzb zzb;
        final boolean zzc;
        @CheckForNull
        final Throwable zzd;

        static {
            if (zzdz.zza) {
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

    /* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.1 */
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

    /* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.1 */
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

    /* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.1 */
    final class zze extends zza {
        final AtomicReferenceFieldUpdater<zzj, Thread> zza;
        final AtomicReferenceFieldUpdater<zzj, zzj> zzb;
        final AtomicReferenceFieldUpdater<? super zzdz<?>, zzj> zzc;
        final AtomicReferenceFieldUpdater<? super zzdz<?>, zzd> zzd;
        final AtomicReferenceFieldUpdater<? super zzdz<?>, Object> zze;

        zze(AtomicReferenceFieldUpdater atomicReferenceFieldUpdater, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater3, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater4, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater5) {
            super((zzdy) null);
            this.zza = atomicReferenceFieldUpdater;
            this.zzb = atomicReferenceFieldUpdater2;
            this.zzc = atomicReferenceFieldUpdater3;
            this.zzd = atomicReferenceFieldUpdater4;
            this.zze = atomicReferenceFieldUpdater5;
        }

        /* access modifiers changed from: package-private */
        public final zzd zza(zzdz zzdz, zzd zzd2) {
            return this.zzd.getAndSet(zzdz, zzd2);
        }

        /* access modifiers changed from: package-private */
        public final zzj zzb(zzdz zzdz, zzj zzj) {
            return this.zzc.getAndSet(zzdz, zzj);
        }

        /* access modifiers changed from: package-private */
        public final void zzc(zzj zzj, @CheckForNull zzj zzj2) {
            this.zzb.lazySet(zzj, zzj2);
        }

        /* access modifiers changed from: package-private */
        public final void zzd(zzj zzj, Thread thread) {
            this.zza.lazySet(zzj, thread);
        }

        /* access modifiers changed from: package-private */
        public final boolean zze(zzdz zzdz, @CheckForNull zzd zzd2, zzd zzd3) {
            return zzea.zza(this.zzd, zzdz, zzd2, zzd3);
        }

        /* access modifiers changed from: package-private */
        public final boolean zzf(zzdz zzdz, @CheckForNull Object obj, Object obj2) {
            return zzea.zza(this.zze, zzdz, obj, obj2);
        }

        /* access modifiers changed from: package-private */
        public final boolean zzg(zzdz zzdz, @CheckForNull zzj zzj, @CheckForNull zzj zzj2) {
            return zzea.zza(this.zzc, zzdz, zzj, zzj2);
        }
    }

    /* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.1 */
    final class zzf<V> implements Runnable {
        final zzdz<V> zza;
        final zzet<? extends V> zzb;

        zzf(zzdz zzdz, zzet zzet) {
            this.zza = zzdz;
            this.zzb = zzet;
        }

        public final void run() {
            if (this.zza.value == this) {
                zzet<? extends V> zzet = this.zzb;
                if (zzdz.zzc.zzf(this.zza, this, zzdz.zzq(zzet))) {
                    zzdz.zzv(this.zza, false);
                }
            }
        }
    }

    /* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.1 */
    final class zzg extends zza {
        private zzg() {
            throw null;
        }

        /* synthetic */ zzg(zzeb zzeb) {
            super((zzdy) null);
        }

        /* access modifiers changed from: package-private */
        public final zzd zza(zzdz zzdz, zzd zzd) {
            zzd zzb;
            synchronized (zzdz) {
                zzb = zzdz.listeners;
                if (zzb != zzd) {
                    zzdz.listeners = zzd;
                }
            }
            return zzb;
        }

        /* access modifiers changed from: package-private */
        public final zzj zzb(zzdz zzdz, zzj zzj) {
            zzj zzc;
            synchronized (zzdz) {
                zzc = zzdz.waiters;
                if (zzc != zzj) {
                    zzdz.waiters = zzj;
                }
            }
            return zzc;
        }

        /* access modifiers changed from: package-private */
        public final void zzc(zzj zzj, @CheckForNull zzj zzj2) {
            zzj.next = zzj2;
        }

        /* access modifiers changed from: package-private */
        public final void zzd(zzj zzj, Thread thread) {
            zzj.thread = thread;
        }

        /* access modifiers changed from: package-private */
        public final boolean zze(zzdz zzdz, @CheckForNull zzd zzd, zzd zzd2) {
            synchronized (zzdz) {
                if (zzdz.listeners != zzd) {
                    return false;
                }
                zzdz.listeners = zzd2;
                return true;
            }
        }

        /* access modifiers changed from: package-private */
        public final boolean zzf(zzdz zzdz, @CheckForNull Object obj, Object obj2) {
            synchronized (zzdz) {
                if (zzdz.value != obj) {
                    return false;
                }
                zzdz.value = obj2;
                return true;
            }
        }

        /* access modifiers changed from: package-private */
        public final boolean zzg(zzdz zzdz, @CheckForNull zzj zzj, @CheckForNull zzj zzj2) {
            synchronized (zzdz) {
                if (zzdz.waiters != zzj) {
                    return false;
                }
                zzdz.waiters = zzj2;
                return true;
            }
        }
    }

    /* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.1 */
    interface zzh<V> extends zzet<V> {
    }

    /* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.1 */
    final class zzi extends zza {
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
            r0 = (sun.misc.Unsafe) java.security.AccessController.doPrivileged(new com.google.android.gms.internal.mlkit_vision_barcode.zzdz.zzi.AnonymousClass1());
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:2:0x0005 */
        static {
            /*
                sun.misc.Unsafe r0 = sun.misc.Unsafe.getUnsafe()     // Catch:{ SecurityException -> 0x0005 }
                goto L_0x0010
            L_0x0005:
                com.google.android.gms.internal.mlkit_vision_barcode.zzdz$zzi$1 r0 = new com.google.android.gms.internal.mlkit_vision_barcode.zzdz$zzi$1     // Catch:{ PrivilegedActionException -> 0x005c }
                r0.<init>()     // Catch:{ PrivilegedActionException -> 0x005c }
                java.lang.Object r0 = java.security.AccessController.doPrivileged(r0)     // Catch:{ PrivilegedActionException -> 0x005c }
                sun.misc.Unsafe r0 = (sun.misc.Unsafe) r0     // Catch:{ PrivilegedActionException -> 0x005c }
            L_0x0010:
                java.lang.Class<com.google.android.gms.internal.mlkit_vision_barcode.zzdz> r1 = com.google.android.gms.internal.mlkit_vision_barcode.zzdz.class
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
                java.lang.Class<com.google.android.gms.internal.mlkit_vision_barcode.zzdz$zzj> r1 = com.google.android.gms.internal.mlkit_vision_barcode.zzdz.zzj.class
                java.lang.String r2 = "thread"
                java.lang.reflect.Field r1 = r1.getDeclaredField(r2)     // Catch:{ NoSuchFieldException -> 0x0055 }
                long r1 = r0.objectFieldOffset(r1)     // Catch:{ NoSuchFieldException -> 0x0055 }
                zze = r1     // Catch:{ NoSuchFieldException -> 0x0055 }
                java.lang.Class<com.google.android.gms.internal.mlkit_vision_barcode.zzdz$zzj> r1 = com.google.android.gms.internal.mlkit_vision_barcode.zzdz.zzj.class
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_barcode.zzdz.zzi.<clinit>():void");
        }

        private zzi() {
            throw null;
        }

        /* synthetic */ zzi(zzed zzed) {
            super((zzdy) null);
        }

        /*  JADX ERROR: StackOverflow in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: 
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
            	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
            */
        final com.google.android.gms.internal.mlkit_vision_barcode.zzdz.zzd zza(com.google.android.gms.internal.mlkit_vision_barcode.zzdz r3, com.google.android.gms.internal.mlkit_vision_barcode.zzdz.zzd r4) {
            /*
                r2 = this;
            L_0x0000:
                com.google.android.gms.internal.mlkit_vision_barcode.zzdz$zzd r0 = r3.listeners
                if (r4 != r0) goto L_0x0007
                goto L_0x000d
            L_0x0007:
                boolean r1 = r2.zze(r3, r0, r4)
                if (r1 == 0) goto L_0x0000
            L_0x000d:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_barcode.zzdz.zzi.zza(com.google.android.gms.internal.mlkit_vision_barcode.zzdz, com.google.android.gms.internal.mlkit_vision_barcode.zzdz$zzd):com.google.android.gms.internal.mlkit_vision_barcode.zzdz$zzd");
        }

        /*  JADX ERROR: StackOverflow in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: 
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
            	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
            */
        final com.google.android.gms.internal.mlkit_vision_barcode.zzdz.zzj zzb(com.google.android.gms.internal.mlkit_vision_barcode.zzdz r3, com.google.android.gms.internal.mlkit_vision_barcode.zzdz.zzj r4) {
            /*
                r2 = this;
            L_0x0000:
                com.google.android.gms.internal.mlkit_vision_barcode.zzdz$zzj r0 = r3.waiters
                if (r4 != r0) goto L_0x0007
                goto L_0x000d
            L_0x0007:
                boolean r1 = r2.zzg(r3, r0, r4)
                if (r1 == 0) goto L_0x0000
            L_0x000d:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_barcode.zzdz.zzi.zzb(com.google.android.gms.internal.mlkit_vision_barcode.zzdz, com.google.android.gms.internal.mlkit_vision_barcode.zzdz$zzj):com.google.android.gms.internal.mlkit_vision_barcode.zzdz$zzj");
        }

        /* access modifiers changed from: package-private */
        public final void zzc(zzj zzj, @CheckForNull zzj zzj2) {
            zza.putObject(zzj, zzf, zzj2);
        }

        /* access modifiers changed from: package-private */
        public final void zzd(zzj zzj, Thread thread) {
            zza.putObject(zzj, zze, thread);
        }

        /* access modifiers changed from: package-private */
        public final boolean zze(zzdz zzdz, @CheckForNull zzd zzd2, zzd zzd3) {
            return zzec.zza(zza, zzdz, zzb, zzd2, zzd3);
        }

        /* access modifiers changed from: package-private */
        public final boolean zzf(zzdz zzdz, @CheckForNull Object obj, Object obj2) {
            return zzec.zza(zza, zzdz, zzd, obj, obj2);
        }

        /* access modifiers changed from: package-private */
        public final boolean zzg(zzdz zzdz, @CheckForNull zzj zzj, @CheckForNull zzj zzj2) {
            return zzec.zza(zza, zzdz, zzc, zzj, zzj2);
        }
    }

    /* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.1 */
    final class zzj {
        static final zzj zza = new zzj(false);
        @CheckForNull
        volatile zzj next;
        @CheckForNull
        volatile Thread thread;

        zzj() {
            zzdz.zzc.zzd(this, Thread.currentThread());
        }

        zzj(boolean z) {
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
        Class<zzdz> cls = zzdz.class;
        zzb = new zzes(cls);
        try {
            zza2 = new zzi((zzed) null);
            th2 = null;
            th = null;
        } catch (Error | Exception e) {
            try {
                th2 = e;
                zza2 = new zze(AtomicReferenceFieldUpdater.newUpdater(zzj.class, Thread.class, "thread"), AtomicReferenceFieldUpdater.newUpdater(zzj.class, zzj.class, "next"), AtomicReferenceFieldUpdater.newUpdater(cls, zzj.class, "waiters"), AtomicReferenceFieldUpdater.newUpdater(cls, zzd.class, "listeners"), AtomicReferenceFieldUpdater.newUpdater(cls, Object.class, "value"));
                th = null;
            } catch (Error | Exception e2) {
                th = e2;
                th2 = e;
                zza2 = new zzg((zzeb) null);
            }
        }
        zzc = zza2;
        if (th != null) {
            zzes zzes = zzb;
            zzes.zza().logp(Level.SEVERE, "com.google.common.util.concurrent.AbstractFuture", "<clinit>", "UnsafeAtomicHelper is broken!", th2);
            zzes.zza().logp(Level.SEVERE, "com.google.common.util.concurrent.AbstractFuture", "<clinit>", "SafeAtomicHelper is broken!", th);
        }
    }

    protected zzdz() {
    }

    /* access modifiers changed from: private */
    public static Object zzq(zzet zzet) {
        Throwable zzg2;
        if (zzet instanceof zzh) {
            Object obj = ((zzdz) zzet).value;
            if (obj instanceof zzb) {
                zzb zzb2 = (zzb) obj;
                if (zzb2.zzc) {
                    Throwable th = zzb2.zzd;
                    obj = th != null ? new zzb(false, th) : zzb.zzb;
                }
            }
            return Objects.requireNonNull(obj);
        } else if ((zzet instanceof zzex) && (zzg2 = ((zzex) zzet).zzg()) != null) {
            return new zzc(zzg2);
        } else {
            boolean isCancelled = zzet.isCancelled();
            if ((!zza) && isCancelled) {
                return Objects.requireNonNull(zzb.zzb);
            }
            try {
                Object zzr = zzr(zzet);
                if (!isCancelled) {
                    return zzr == null ? zzd : zzr;
                }
                String valueOf = String.valueOf(zzet);
                return new zzb(false, new IllegalArgumentException("get() did not throw CancellationException, despite reporting isCancelled() == true: " + valueOf));
            } catch (ExecutionException e) {
                if (isCancelled) {
                    return new zzb(false, new IllegalArgumentException("get() did not throw CancellationException, despite reporting isCancelled() == true: ".concat(String.valueOf(String.valueOf(zzet))), e));
                }
                return new zzc(e.getCause());
            } catch (CancellationException e2) {
                if (!isCancelled) {
                    return new zzc(new IllegalArgumentException("get() threw CancellationException, despite reporting isCancelled() == false: ".concat(String.valueOf(String.valueOf(zzet))), e2));
                }
                return new zzb(false, e2);
            } catch (Error | Exception e3) {
                return new zzc(e3);
            }
        }
    }

    private static Object zzr(Future future) throws ExecutionException {
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

    private final void zzs(StringBuilder sb) {
        try {
            Object zzr = zzr(this);
            sb.append("SUCCESS, result=[");
            if (zzr == null) {
                sb.append("null");
            } else if (zzr == this) {
                sb.append("this future");
            } else {
                sb.append(zzr.getClass().getName());
                sb.append("@");
                sb.append(Integer.toHexString(System.identityHashCode(zzr)));
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

    private final void zzt(StringBuilder sb) {
        String str;
        int length = sb.length();
        sb.append("PENDING");
        Object obj = this.value;
        if (obj instanceof zzf) {
            sb.append(", setFuture=[");
            zzu(sb, ((zzf) obj).zzb);
            sb.append("]");
        } else {
            try {
                str = zzba.zza(zzf());
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
            zzs(sb);
        }
    }

    private final void zzu(StringBuilder sb, @CheckForNull Object obj) {
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
    public static void zzv(zzdz<V> zzdz, boolean z) {
        zzd zzd2 = null;
        while (true) {
            for (zzj zzb2 = zzc.zzb(zzdz, zzj.zza); zzb2 != null; zzb2 = zzb2.next) {
                Thread thread = zzb2.thread;
                if (thread != null) {
                    zzb2.thread = null;
                    LockSupport.unpark(thread);
                }
            }
            zzdz.zzm();
            zzd zzd3 = zzd2;
            zzd zza2 = zzc.zza(zzdz, zzd.zza);
            zzd zzd4 = zzd3;
            while (zza2 != null) {
                zzd zzd5 = zza2.next;
                zza2.next = zzd4;
                zzd4 = zza2;
                zza2 = zzd5;
            }
            while (zzd4 != null) {
                Runnable runnable = zzd4.zzb;
                zzd zzd6 = zzd4.next;
                Runnable runnable2 = (Runnable) Objects.requireNonNull(runnable);
                if (runnable2 instanceof zzf) {
                    zzf zzf2 = (zzf) runnable2;
                    zzdz = zzf2.zza;
                    if (zzdz.value == zzf2) {
                        if (zzc.zzf(zzdz, zzf2, zzq(zzf2.zzb))) {
                            zzd2 = zzd6;
                        }
                    } else {
                        continue;
                    }
                } else {
                    zzw(runnable2, (Executor) Objects.requireNonNull(zzd4.zzc));
                }
                zzd4 = zzd6;
            }
            return;
        }
    }

    private static void zzw(Runnable runnable, Executor executor) {
        try {
            executor.execute(runnable);
        } catch (Exception e) {
            Logger zza2 = zzb.zza();
            Level level = Level.SEVERE;
            String valueOf = String.valueOf(runnable);
            String valueOf2 = String.valueOf(executor);
            zza2.logp(level, "com.google.common.util.concurrent.AbstractFuture", "executeListener", "RuntimeException while executing runnable " + valueOf + " with executor " + valueOf2, e);
        }
    }

    private final void zzx(zzj zzj2) {
        zzj2.thread = null;
        while (true) {
            zzj zzj3 = this.waiters;
            if (zzj3 != zzj.zza) {
                zzj zzj4 = null;
                while (zzj3 != null) {
                    zzj zzj5 = zzj3.next;
                    if (zzj3.thread != null) {
                        zzj4 = zzj3;
                    } else if (zzj4 != null) {
                        zzj4.next = zzj5;
                        if (zzj4.thread == null) {
                        }
                    } else if (!zzc.zzg(this, zzj3, zzj5)) {
                    }
                    zzj3 = zzj5;
                }
                return;
            }
            return;
        }
    }

    private static final Object zzy(Object obj) throws ExecutionException {
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

    /* JADX WARNING: type inference failed for: r0v5, types: [com.google.android.gms.internal.mlkit_vision_barcode.zzet, com.google.android.gms.internal.mlkit_vision_barcode.zzet<? extends V>] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean cancel(boolean r8) {
        /*
            r7 = this;
            java.lang.Object r0 = r7.value
            boolean r1 = r0 instanceof com.google.android.gms.internal.mlkit_vision_barcode.zzdz.zzf
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
            boolean r1 = zza
            if (r1 == 0) goto L_0x001f
            com.google.android.gms.internal.mlkit_vision_barcode.zzdz$zzb r1 = new com.google.android.gms.internal.mlkit_vision_barcode.zzdz$zzb
            java.util.concurrent.CancellationException r4 = new java.util.concurrent.CancellationException
            java.lang.String r5 = "Future.cancel() was called."
            r4.<init>(r5)
            r1.<init>(r8, r4)
            goto L_0x002a
        L_0x001f:
            if (r8 == 0) goto L_0x0024
            com.google.android.gms.internal.mlkit_vision_barcode.zzdz$zzb r1 = com.google.android.gms.internal.mlkit_vision_barcode.zzdz.zzb.zza
            goto L_0x0026
        L_0x0024:
            com.google.android.gms.internal.mlkit_vision_barcode.zzdz$zzb r1 = com.google.android.gms.internal.mlkit_vision_barcode.zzdz.zzb.zzb
        L_0x0026:
            java.lang.Object r1 = java.util.Objects.requireNonNull(r1)
        L_0x002a:
            r4 = r7
            r5 = r2
        L_0x002c:
            com.google.android.gms.internal.mlkit_vision_barcode.zzdz$zza r6 = zzc
            boolean r6 = r6.zzf(r4, r0, r1)
            if (r6 == 0) goto L_0x0059
            zzv(r4, r8)
            boolean r4 = r0 instanceof com.google.android.gms.internal.mlkit_vision_barcode.zzdz.zzf
            if (r4 == 0) goto L_0x0057
            com.google.android.gms.internal.mlkit_vision_barcode.zzdz$zzf r0 = (com.google.android.gms.internal.mlkit_vision_barcode.zzdz.zzf) r0
            com.google.android.gms.internal.mlkit_vision_barcode.zzet<? extends V> r0 = r0.zzb
            boolean r4 = r0 instanceof com.google.android.gms.internal.mlkit_vision_barcode.zzdz.zzh
            if (r4 == 0) goto L_0x0054
            r4 = r0
            com.google.android.gms.internal.mlkit_vision_barcode.zzdz r4 = (com.google.android.gms.internal.mlkit_vision_barcode.zzdz) r4
            java.lang.Object r0 = r4.value
            if (r0 != 0) goto L_0x004c
            r5 = r3
            goto L_0x004d
        L_0x004c:
            r5 = r2
        L_0x004d:
            boolean r6 = r0 instanceof com.google.android.gms.internal.mlkit_vision_barcode.zzdz.zzf
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
            boolean r6 = r0 instanceof com.google.android.gms.internal.mlkit_vision_barcode.zzdz.zzf
            if (r6 != 0) goto L_0x002c
            r2 = r5
        L_0x0060:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_barcode.zzdz.cancel(boolean):boolean");
    }

    public final Object get() throws InterruptedException, ExecutionException {
        Object obj;
        if (!Thread.interrupted()) {
            Object obj2 = this.value;
            if ((obj2 != null) && (!(obj2 instanceof zzf))) {
                return zzy(obj2);
            }
            zzj zzj2 = this.waiters;
            if (zzj2 != zzj.zza) {
                zzj zzj3 = new zzj();
                do {
                    zza zza2 = zzc;
                    zza2.zzc(zzj3, zzj2);
                    if (zza2.zzg(this, zzj2, zzj3)) {
                        do {
                            LockSupport.park(this);
                            if (!Thread.interrupted()) {
                                obj = this.value;
                            } else {
                                zzx(zzj3);
                                throw new InterruptedException();
                            }
                        } while (!((obj != null) & (!(obj instanceof zzf))));
                        return zzy(obj);
                    }
                    zzj2 = this.waiters;
                } while (zzj2 != zzj.zza);
            }
            return zzy(Objects.requireNonNull(this.value));
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
            zzs(sb);
        } else {
            zzt(sb);
        }
        sb.append("]");
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    @CheckForNull
    public String zzf() {
        if (!(this instanceof ScheduledFuture)) {
            return null;
        }
        long delay = ((ScheduledFuture) this).getDelay(TimeUnit.MILLISECONDS);
        return "remaining delay=[" + delay + " ms]";
    }

    /* access modifiers changed from: protected */
    @CheckForNull
    public final Throwable zzg() {
        if (!(this instanceof zzh)) {
            return null;
        }
        Object obj = this.value;
        if (obj instanceof zzc) {
            return ((zzc) obj).zzb;
        }
        return null;
    }

    public final void zzl(Runnable runnable, Executor executor) {
        zzd zzd2;
        zzaz.zzc(executor, "Executor was null.");
        if (isDone() || (zzd2 = this.listeners) == zzd.zza) {
            zzw(runnable, executor);
        }
        zzd zzd3 = new zzd(runnable, executor);
        do {
            zzd3.next = zzd2;
            if (!zzc.zze(this, zzd2, zzd3)) {
                zzd2 = this.listeners;
            } else {
                return;
            }
        } while (zzd2 != zzd.zza);
        zzw(runnable, executor);
    }

    /* access modifiers changed from: protected */
    public void zzm() {
    }

    /* access modifiers changed from: protected */
    public final boolean zzn(Throwable th) {
        Throwable th2 = th;
        if (!zzc.zzf(this, (Object) null, new zzc(th))) {
            return false;
        }
        zzv(this, false);
        return true;
    }

    /* access modifiers changed from: protected */
    public final boolean zzp() {
        Object obj = this.value;
        return (obj instanceof zzb) && ((zzb) obj).zzc;
    }

    /* access modifiers changed from: protected */
    public final boolean zzo(zzet zzet) {
        zzc zzc2;
        zzet.getClass();
        Object obj = this.value;
        if (obj == null) {
            if (zzet.isDone()) {
                if (!zzc.zzf(this, (Object) null, zzq(zzet))) {
                    return false;
                }
                zzv(this, false);
                return true;
            }
            zzf zzf2 = new zzf(this, zzet);
            if (zzc.zzf(this, (Object) null, zzf2)) {
                try {
                    zzet.zzl(zzf2, zzee.INSTANCE);
                } catch (Throwable th) {
                    try {
                        zzc2 = new zzc(th);
                    } catch (Error | Exception unused) {
                        zzc2 = zzc.zza;
                    }
                    zzc.zzf(this, zzf2, zzc2);
                }
                return true;
            }
            obj = this.value;
        }
        if (obj instanceof zzb) {
            zzet.cancel(((zzb) obj).zzc);
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
                return zzy(obj);
            }
            long nanoTime = nanos > 0 ? System.nanoTime() + nanos : 0;
            if (nanos >= 1000) {
                zzj zzj2 = this.waiters;
                if (zzj2 != zzj.zza) {
                    zzj zzj3 = new zzj();
                    do {
                        zza zza2 = zzc;
                        zza2.zzc(zzj3, zzj2);
                        if (zza2.zzg(this, zzj2, zzj3)) {
                            do {
                                LockSupport.parkNanos(this, Math.min(nanos, 2147483647999999999L));
                                if (!Thread.interrupted()) {
                                    Object obj2 = this.value;
                                    if ((obj2 != null) && (!(obj2 instanceof zzf))) {
                                        return zzy(obj2);
                                    }
                                    nanos = nanoTime - System.nanoTime();
                                } else {
                                    zzx(zzj3);
                                    throw new InterruptedException();
                                }
                            } while (nanos >= 1000);
                            zzx(zzj3);
                        } else {
                            zzj2 = this.waiters;
                        }
                    } while (zzj2 != zzj.zza);
                }
                return zzy(Objects.requireNonNull(this.value));
            }
            while (nanos > 0) {
                Object obj3 = this.value;
                if ((obj3 != null) && (!(obj3 instanceof zzf))) {
                    return zzy(obj3);
                }
                if (!Thread.interrupted()) {
                    nanos = nanoTime - System.nanoTime();
                } else {
                    throw new InterruptedException();
                }
            }
            String zzdz = toString();
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
            throw new TimeoutException(str + " for " + zzdz);
        }
        throw new InterruptedException();
    }
}
