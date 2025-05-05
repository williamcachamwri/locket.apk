package io.grpc;

import io.grpc.PersistentHashArrayMappedTrie;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Context {
    static final int CONTEXT_DEPTH_WARN_THRESH = 1000;
    public static final Context ROOT = new Context();
    static final Logger log = Logger.getLogger(Context.class.getName());
    final CancellableContext cancellableAncestor;
    final int generation;
    final PersistentHashArrayMappedTrie.Node<Key<?>, Object> keyValueEntries;

    @interface CanIgnoreReturnValue {
    }

    public interface CancellationListener {
        void cancelled(Context context);
    }

    @interface CheckReturnValue {
    }

    public static abstract class Storage {
        public abstract Context current();

        public abstract void detach(Context context, Context context2);

        public abstract Context doAttach(Context context);
    }

    static Storage storage() {
        return LazyStorage.storage;
    }

    private static final class LazyStorage {
        static final Storage storage;

        private LazyStorage() {
        }

        static {
            AtomicReference atomicReference = new AtomicReference();
            storage = createStorage(atomicReference);
            Throwable th = (Throwable) atomicReference.get();
            if (th != null) {
                Context.log.log(Level.FINE, "Storage override doesn't exist. Using default", th);
            }
        }

        private static Storage createStorage(AtomicReference<? super ClassNotFoundException> atomicReference) {
            try {
                return (Storage) Class.forName("io.grpc.override.ContextStorageOverride").asSubclass(Storage.class).getConstructor(new Class[0]).newInstance(new Object[0]);
            } catch (ClassNotFoundException e) {
                atomicReference.set(e);
                return new ThreadLocalContextStorage();
            } catch (Exception e2) {
                throw new RuntimeException("Storage override failed to initialize", e2);
            }
        }
    }

    public static <T> Key<T> key(String str) {
        return new Key<>(str);
    }

    public static <T> Key<T> keyWithDefault(String str, T t) {
        return new Key<>(str, t);
    }

    public static Context current() {
        Context current = storage().current();
        return current == null ? ROOT : current;
    }

    private Context(PersistentHashArrayMappedTrie.Node<Key<?>, Object> node, int i) {
        this.cancellableAncestor = null;
        this.keyValueEntries = node;
        this.generation = i;
        validateGeneration(i);
    }

    private Context(Context context, PersistentHashArrayMappedTrie.Node<Key<?>, Object> node) {
        this.cancellableAncestor = cancellableAncestor(context);
        this.keyValueEntries = node;
        int i = context.generation + 1;
        this.generation = i;
        validateGeneration(i);
    }

    private Context() {
        this.cancellableAncestor = null;
        this.keyValueEntries = null;
        this.generation = 0;
        validateGeneration(0);
    }

    public CancellableContext withCancellation() {
        return new CancellableContext();
    }

    public CancellableContext withDeadlineAfter(long j, TimeUnit timeUnit, ScheduledExecutorService scheduledExecutorService) {
        return withDeadline(Deadline.after(j, timeUnit), scheduledExecutorService);
    }

    public CancellableContext withDeadline(Deadline deadline, ScheduledExecutorService scheduledExecutorService) {
        boolean z;
        checkNotNull(deadline, "deadline");
        checkNotNull(scheduledExecutorService, "scheduler");
        Deadline deadline2 = getDeadline();
        if (deadline2 == null || deadline2.compareTo(deadline) > 0) {
            z = true;
        } else {
            Deadline deadline3 = deadline2;
            z = false;
            deadline = deadline3;
        }
        CancellableContext cancellableContext = new CancellableContext(deadline);
        if (z) {
            cancellableContext.setUpDeadlineCancellation(deadline, scheduledExecutorService);
        }
        return cancellableContext;
    }

    public <V> Context withValue(Key<V> key, V v) {
        return new Context(this, PersistentHashArrayMappedTrie.put(this.keyValueEntries, key, v));
    }

    public <V1, V2> Context withValues(Key<V1> key, V1 v1, Key<V2> key2, V2 v2) {
        return new Context(this, PersistentHashArrayMappedTrie.put(PersistentHashArrayMappedTrie.put(this.keyValueEntries, key, v1), key2, v2));
    }

    public <V1, V2, V3> Context withValues(Key<V1> key, V1 v1, Key<V2> key2, V2 v2, Key<V3> key3, V3 v3) {
        return new Context(this, PersistentHashArrayMappedTrie.put(PersistentHashArrayMappedTrie.put(PersistentHashArrayMappedTrie.put(this.keyValueEntries, key, v1), key2, v2), key3, v3));
    }

    public <V1, V2, V3, V4> Context withValues(Key<V1> key, V1 v1, Key<V2> key2, V2 v2, Key<V3> key3, V3 v3, Key<V4> key4, V4 v4) {
        return new Context(this, PersistentHashArrayMappedTrie.put(PersistentHashArrayMappedTrie.put(PersistentHashArrayMappedTrie.put(PersistentHashArrayMappedTrie.put(this.keyValueEntries, key, v1), key2, v2), key3, v3), key4, v4));
    }

    public Context fork() {
        return new Context(this.keyValueEntries, this.generation + 1);
    }

    public Context attach() {
        Context doAttach = storage().doAttach(this);
        return doAttach == null ? ROOT : doAttach;
    }

    public void detach(Context context) {
        checkNotNull(context, "toAttach");
        storage().detach(this, context);
    }

    /* access modifiers changed from: package-private */
    public boolean isCurrent() {
        return current() == this;
    }

    public boolean isCancelled() {
        CancellableContext cancellableContext = this.cancellableAncestor;
        if (cancellableContext == null) {
            return false;
        }
        return cancellableContext.isCancelled();
    }

    public Throwable cancellationCause() {
        CancellableContext cancellableContext = this.cancellableAncestor;
        if (cancellableContext == null) {
            return null;
        }
        return cancellableContext.cancellationCause();
    }

    public Deadline getDeadline() {
        CancellableContext cancellableContext = this.cancellableAncestor;
        if (cancellableContext == null) {
            return null;
        }
        return cancellableContext.getDeadline();
    }

    public void addListener(CancellationListener cancellationListener, Executor executor) {
        checkNotNull(cancellationListener, "cancellationListener");
        checkNotNull(executor, "executor");
        CancellableContext cancellableContext = this.cancellableAncestor;
        if (cancellableContext != null) {
            cancellableContext.addListenerInternal(new ExecutableListener(executor, cancellationListener, this));
        }
    }

    public void removeListener(CancellationListener cancellationListener) {
        CancellableContext cancellableContext = this.cancellableAncestor;
        if (cancellableContext != null) {
            cancellableContext.removeListenerInternal(cancellationListener, this);
        }
    }

    /* access modifiers changed from: package-private */
    public int listenerCount() {
        CancellableContext cancellableContext = this.cancellableAncestor;
        if (cancellableContext == null) {
            return 0;
        }
        return cancellableContext.listenerCount();
    }

    public void run(Runnable runnable) {
        Context attach = attach();
        try {
            runnable.run();
        } finally {
            detach(attach);
        }
    }

    public <V> V call(Callable<V> callable) throws Exception {
        Context attach = attach();
        try {
            return callable.call();
        } finally {
            detach(attach);
        }
    }

    public Runnable wrap(final Runnable runnable) {
        return new Runnable() {
            public void run() {
                Context attach = Context.this.attach();
                try {
                    runnable.run();
                } finally {
                    Context.this.detach(attach);
                }
            }
        };
    }

    public <C> Callable<C> wrap(final Callable<C> callable) {
        return new Callable<C>() {
            public C call() throws Exception {
                Context attach = Context.this.attach();
                try {
                    return callable.call();
                } finally {
                    Context.this.detach(attach);
                }
            }
        };
    }

    public Executor fixedContextExecutor(final Executor executor) {
        return new Executor() {
            public void execute(Runnable runnable) {
                executor.execute(Context.this.wrap(runnable));
            }
        };
    }

    public static Executor currentContextExecutor(final Executor executor) {
        return new Executor() {
            public void execute(Runnable runnable) {
                executor.execute(Context.current().wrap(runnable));
            }
        };
    }

    public static final class CancellableContext extends Context implements Closeable {
        private Throwable cancellationCause;
        private boolean cancelled;
        private final Deadline deadline;
        private ArrayList<ExecutableListener> listeners;
        private CancellationListener parentListener;
        private ScheduledFuture<?> pendingDeadline;
        private final Context uncancellableSurrogate;

        private CancellableContext(Context context) {
            super(context.keyValueEntries);
            this.deadline = context.getDeadline();
            this.uncancellableSurrogate = new Context(this.keyValueEntries);
        }

        private CancellableContext(Context context, Deadline deadline2) {
            super(context.keyValueEntries);
            this.deadline = deadline2;
            this.uncancellableSurrogate = new Context(this.keyValueEntries);
        }

        /* access modifiers changed from: private */
        public void setUpDeadlineCancellation(Deadline deadline2, ScheduledExecutorService scheduledExecutorService) {
            if (!deadline2.isExpired()) {
                synchronized (this) {
                    this.pendingDeadline = deadline2.runOnExpiration(new Runnable() {
                        public void run() {
                            try {
                                CancellableContext.this.cancel(new TimeoutException("context timed out"));
                            } catch (Throwable th) {
                                Context.log.log(Level.SEVERE, "Cancel threw an exception, which should not happen", th);
                            }
                        }
                    }, scheduledExecutorService);
                }
                return;
            }
            cancel(new TimeoutException("context timed out"));
        }

        public Context attach() {
            return this.uncancellableSurrogate.attach();
        }

        public void detach(Context context) {
            this.uncancellableSurrogate.detach(context);
        }

        public void addListener(CancellationListener cancellationListener, Executor executor) {
            checkNotNull(cancellationListener, "cancellationListener");
            checkNotNull(executor, "executor");
            addListenerInternal(new ExecutableListener(executor, cancellationListener, this));
        }

        /* access modifiers changed from: private */
        public void addListenerInternal(ExecutableListener executableListener) {
            synchronized (this) {
                if (isCancelled()) {
                    executableListener.deliver();
                } else {
                    ArrayList<ExecutableListener> arrayList = this.listeners;
                    if (arrayList == null) {
                        ArrayList<ExecutableListener> arrayList2 = new ArrayList<>();
                        this.listeners = arrayList2;
                        arrayList2.add(executableListener);
                        if (this.cancellableAncestor != null) {
                            this.parentListener = new CancellationListener() {
                                public void cancelled(Context context) {
                                    CancellableContext.this.cancel(context.cancellationCause());
                                }
                            };
                            this.cancellableAncestor.addListenerInternal(new ExecutableListener(DirectExecutor.INSTANCE, this.parentListener, this));
                        }
                    } else {
                        arrayList.add(executableListener);
                    }
                }
            }
        }

        public void removeListener(CancellationListener cancellationListener) {
            removeListenerInternal(cancellationListener, this);
        }

        /* access modifiers changed from: private */
        public void removeListenerInternal(CancellationListener cancellationListener, Context context) {
            synchronized (this) {
                ArrayList<ExecutableListener> arrayList = this.listeners;
                if (arrayList != null) {
                    int size = arrayList.size() - 1;
                    while (true) {
                        if (size < 0) {
                            break;
                        }
                        ExecutableListener executableListener = this.listeners.get(size);
                        if (executableListener.listener == cancellationListener && executableListener.context == context) {
                            this.listeners.remove(size);
                            break;
                        }
                        size--;
                    }
                    if (this.listeners.isEmpty()) {
                        if (this.cancellableAncestor != null) {
                            this.cancellableAncestor.removeListener(this.parentListener);
                        }
                        this.parentListener = null;
                        this.listeners = null;
                    }
                }
            }
        }

        @Deprecated
        public boolean isCurrent() {
            return this.uncancellableSurrogate.isCurrent();
        }

        public boolean cancel(Throwable th) {
            ScheduledFuture<?> scheduledFuture;
            boolean z;
            synchronized (this) {
                scheduledFuture = null;
                if (!this.cancelled) {
                    z = true;
                    this.cancelled = true;
                    ScheduledFuture<?> scheduledFuture2 = this.pendingDeadline;
                    if (scheduledFuture2 != null) {
                        this.pendingDeadline = null;
                        scheduledFuture = scheduledFuture2;
                    }
                    this.cancellationCause = th;
                } else {
                    z = false;
                }
            }
            if (scheduledFuture != null) {
                scheduledFuture.cancel(false);
            }
            if (z) {
                notifyAndClearListeners();
            }
            return z;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:10:0x0017, code lost:
            if (r2.hasNext() == false) goto L_0x0029;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:11:0x0019, code lost:
            r3 = r2.next();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0023, code lost:
            if (io.grpc.Context.ExecutableListener.access$600(r3) != r5) goto L_0x0013;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:13:0x0025, code lost:
            r3.deliver();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:14:0x0029, code lost:
            r0 = r0.iterator();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:16:0x0031, code lost:
            if (r0.hasNext() == false) goto L_0x0043;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x0033, code lost:
            r2 = r0.next();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:18:0x003d, code lost:
            if (io.grpc.Context.ExecutableListener.access$600(r2) == r5) goto L_0x002d;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:0x003f, code lost:
            r2.deliver();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:21:0x0045, code lost:
            if (r5.cancellableAncestor == null) goto L_?;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:22:0x0047, code lost:
            r5.cancellableAncestor.removeListener(r1);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:38:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:39:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:8:0x000f, code lost:
            r2 = r0.iterator();
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void notifyAndClearListeners() {
            /*
                r5 = this;
                monitor-enter(r5)
                java.util.ArrayList<io.grpc.Context$ExecutableListener> r0 = r5.listeners     // Catch:{ all -> 0x004d }
                if (r0 != 0) goto L_0x0007
                monitor-exit(r5)     // Catch:{ all -> 0x004d }
                return
            L_0x0007:
                io.grpc.Context$CancellationListener r1 = r5.parentListener     // Catch:{ all -> 0x004d }
                r2 = 0
                r5.parentListener = r2     // Catch:{ all -> 0x004d }
                r5.listeners = r2     // Catch:{ all -> 0x004d }
                monitor-exit(r5)     // Catch:{ all -> 0x004d }
                java.util.Iterator r2 = r0.iterator()
            L_0x0013:
                boolean r3 = r2.hasNext()
                if (r3 == 0) goto L_0x0029
                java.lang.Object r3 = r2.next()
                io.grpc.Context$ExecutableListener r3 = (io.grpc.Context.ExecutableListener) r3
                io.grpc.Context r4 = r3.context
                if (r4 != r5) goto L_0x0013
                r3.deliver()
                goto L_0x0013
            L_0x0029:
                java.util.Iterator r0 = r0.iterator()
            L_0x002d:
                boolean r2 = r0.hasNext()
                if (r2 == 0) goto L_0x0043
                java.lang.Object r2 = r0.next()
                io.grpc.Context$ExecutableListener r2 = (io.grpc.Context.ExecutableListener) r2
                io.grpc.Context r3 = r2.context
                if (r3 == r5) goto L_0x002d
                r2.deliver()
                goto L_0x002d
            L_0x0043:
                io.grpc.Context$CancellableContext r0 = r5.cancellableAncestor
                if (r0 == 0) goto L_0x004c
                io.grpc.Context$CancellableContext r0 = r5.cancellableAncestor
                r0.removeListener(r1)
            L_0x004c:
                return
            L_0x004d:
                r0 = move-exception
                monitor-exit(r5)     // Catch:{ all -> 0x004d }
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.Context.CancellableContext.notifyAndClearListeners():void");
        }

        /* access modifiers changed from: package-private */
        public int listenerCount() {
            int size;
            synchronized (this) {
                ArrayList<ExecutableListener> arrayList = this.listeners;
                size = arrayList == null ? 0 : arrayList.size();
            }
            return size;
        }

        public void detachAndCancel(Context context, Throwable th) {
            try {
                detach(context);
            } finally {
                cancel(th);
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:10:0x0016, code lost:
            return true;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:11:0x0017, code lost:
            return false;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:8:0x000d, code lost:
            if (io.grpc.Context.super.isCancelled() == false) goto L_0x0017;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:9:0x000f, code lost:
            cancel(io.grpc.Context.super.cancellationCause());
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean isCancelled() {
            /*
                r2 = this;
                monitor-enter(r2)
                boolean r0 = r2.cancelled     // Catch:{ all -> 0x0019 }
                r1 = 1
                if (r0 == 0) goto L_0x0008
                monitor-exit(r2)     // Catch:{ all -> 0x0019 }
                return r1
            L_0x0008:
                monitor-exit(r2)     // Catch:{ all -> 0x0019 }
                boolean r0 = io.grpc.Context.super.isCancelled()
                if (r0 == 0) goto L_0x0017
                java.lang.Throwable r0 = io.grpc.Context.super.cancellationCause()
                r2.cancel(r0)
                return r1
            L_0x0017:
                r0 = 0
                return r0
            L_0x0019:
                r0 = move-exception
                monitor-exit(r2)     // Catch:{ all -> 0x0019 }
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.Context.CancellableContext.isCancelled():boolean");
        }

        public Throwable cancellationCause() {
            if (isCancelled()) {
                return this.cancellationCause;
            }
            return null;
        }

        public Deadline getDeadline() {
            return this.deadline;
        }

        public void close() {
            cancel((Throwable) null);
        }
    }

    public static final class Key<T> {
        private final T defaultValue;
        private final String name;

        Key(String str) {
            this(str, (Object) null);
        }

        Key(String str, T t) {
            this.name = (String) Context.checkNotNull(str, "name");
            this.defaultValue = t;
        }

        public T get() {
            return get(Context.current());
        }

        public T get(Context context) {
            T t = PersistentHashArrayMappedTrie.get(context.keyValueEntries, this);
            return t == null ? this.defaultValue : t;
        }

        public String toString() {
            return this.name;
        }
    }

    private static final class ExecutableListener implements Runnable {
        /* access modifiers changed from: private */
        public final Context context;
        private final Executor executor;
        final CancellationListener listener;

        ExecutableListener(Executor executor2, CancellationListener cancellationListener, Context context2) {
            this.executor = executor2;
            this.listener = cancellationListener;
            this.context = context2;
        }

        /* access modifiers changed from: package-private */
        public void deliver() {
            try {
                this.executor.execute(this);
            } catch (Throwable th) {
                Context.log.log(Level.INFO, "Exception notifying context listener", th);
            }
        }

        public void run() {
            this.listener.cancelled(this.context);
        }
    }

    static <T> T checkNotNull(T t, Object obj) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(String.valueOf(obj));
    }

    private enum DirectExecutor implements Executor {
        INSTANCE;

        public String toString() {
            return "Context.DirectExecutor";
        }

        public void execute(Runnable runnable) {
            runnable.run();
        }
    }

    static CancellableContext cancellableAncestor(Context context) {
        if (context instanceof CancellableContext) {
            return (CancellableContext) context;
        }
        return context.cancellableAncestor;
    }

    private static void validateGeneration(int i) {
        if (i == 1000) {
            log.log(Level.SEVERE, "Context ancestry chain length is abnormally long. This suggests an error in application code. Length exceeded: 1000", new Exception());
        }
    }
}
