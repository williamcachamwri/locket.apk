package io.grpc.internal;

import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import io.grpc.Attributes;
import io.grpc.ClientCall;
import io.grpc.Context;
import io.grpc.Deadline;
import io.grpc.Metadata;
import io.grpc.Status;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;

public class DelayedClientCall<ReqT, RespT> extends ClientCall<ReqT, RespT> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final ClientCall<Object, Object> NOOP_CALL = new ClientCall<Object, Object>() {
        public void cancel(String str, Throwable th) {
        }

        public void halfClose() {
        }

        public boolean isReady() {
            return false;
        }

        public void request(int i) {
        }

        public void sendMessage(Object obj) {
        }

        public void start(ClientCall.Listener<Object> listener, Metadata metadata) {
        }
    };
    private static final Logger logger = Logger.getLogger(DelayedClientCall.class.getName());
    private final Executor callExecutor;
    /* access modifiers changed from: private */
    public final Context context;
    private DelayedListener<RespT> delayedListener;
    private Status error;
    @Nullable
    private final ScheduledFuture<?> initialDeadlineMonitor;
    private ClientCall.Listener<RespT> listener;
    private volatile boolean passThrough;
    private List<Runnable> pendingRunnables = new ArrayList();
    /* access modifiers changed from: private */
    public ClientCall<ReqT, RespT> realCall;

    /* access modifiers changed from: protected */
    public void callCancelled() {
    }

    protected DelayedClientCall(Executor executor, ScheduledExecutorService scheduledExecutorService, @Nullable Deadline deadline) {
        this.callExecutor = (Executor) Preconditions.checkNotNull(executor, "callExecutor");
        Preconditions.checkNotNull(scheduledExecutorService, "scheduler");
        this.context = Context.current();
        this.initialDeadlineMonitor = scheduleDeadlineIfNeeded(scheduledExecutorService, deadline);
    }

    private boolean isAbeforeB(@Nullable Deadline deadline, @Nullable Deadline deadline2) {
        if (deadline2 == null) {
            return true;
        }
        if (deadline == null) {
            return false;
        }
        return deadline.isBefore(deadline2);
    }

    @Nullable
    private ScheduledFuture<?> scheduleDeadlineIfNeeded(ScheduledExecutorService scheduledExecutorService, @Nullable Deadline deadline) {
        Deadline deadline2 = this.context.getDeadline();
        if (deadline == null && deadline2 == null) {
            return null;
        }
        long timeRemaining = deadline != null ? deadline.timeRemaining(TimeUnit.NANOSECONDS) : Long.MAX_VALUE;
        if (deadline2 != null && deadline2.timeRemaining(TimeUnit.NANOSECONDS) < timeRemaining) {
            timeRemaining = deadline2.timeRemaining(TimeUnit.NANOSECONDS);
            Logger logger2 = logger;
            if (logger2.isLoggable(Level.FINE)) {
                StringBuilder sb = new StringBuilder(String.format(Locale.US, "Call timeout set to '%d' ns, due to context deadline.", new Object[]{Long.valueOf(timeRemaining)}));
                if (deadline == null) {
                    sb.append(" Explicit call timeout was not set.");
                } else {
                    sb.append(String.format(Locale.US, " Explicit call timeout was '%d' ns.", new Object[]{Long.valueOf(deadline.timeRemaining(TimeUnit.NANOSECONDS))}));
                }
                logger2.fine(sb.toString());
            }
        }
        long abs = Math.abs(timeRemaining) / TimeUnit.SECONDS.toNanos(1);
        long abs2 = Math.abs(timeRemaining) % TimeUnit.SECONDS.toNanos(1);
        final StringBuilder sb2 = new StringBuilder();
        String str = isAbeforeB(deadline2, deadline) ? "Context" : "CallOptions";
        if (timeRemaining < 0) {
            sb2.append("ClientCall started after ");
            sb2.append(str);
            sb2.append(" deadline was exceeded. Deadline has been exceeded for ");
        } else {
            sb2.append("Deadline ");
            sb2.append(str);
            sb2.append(" will be exceeded in ");
        }
        sb2.append(abs);
        sb2.append(String.format(Locale.US, ".%09d", new Object[]{Long.valueOf(abs2)}));
        sb2.append("s. ");
        return scheduledExecutorService.schedule(new Runnable() {
            public void run() {
                DelayedClientCall.this.cancel(Status.DEADLINE_EXCEEDED.withDescription(sb2.toString()), true);
            }
        }, timeRemaining, TimeUnit.NANOSECONDS);
    }

    public final Runnable setCall(ClientCall<ReqT, RespT> clientCall) {
        synchronized (this) {
            if (this.realCall != null) {
                return null;
            }
            setRealCall((ClientCall) Preconditions.checkNotNull(clientCall, NotificationCompat.CATEGORY_CALL));
            return new ContextRunnable(this.context) {
                public void runInContext() {
                    DelayedClientCall.this.drainPendingCalls();
                }
            };
        }
    }

    public final void start(final ClientCall.Listener<RespT> listener2, final Metadata metadata) {
        Status status;
        boolean z;
        Preconditions.checkState(this.listener == null, "already started");
        synchronized (this) {
            this.listener = (ClientCall.Listener) Preconditions.checkNotNull(listener2, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
            status = this.error;
            z = this.passThrough;
            if (!z) {
                DelayedListener<RespT> delayedListener2 = new DelayedListener<>(listener2);
                this.delayedListener = delayedListener2;
                listener2 = delayedListener2;
            }
        }
        if (status != null) {
            this.callExecutor.execute(new CloseListenerRunnable(listener2, status));
        } else if (z) {
            this.realCall.start(listener2, metadata);
        } else {
            delayOrExecute(new Runnable() {
                public void run() {
                    DelayedClientCall.this.realCall.start(listener2, metadata);
                }
            });
        }
    }

    public final void cancel(@Nullable String str, @Nullable Throwable th) {
        Status status;
        Status status2 = Status.CANCELLED;
        if (str != null) {
            status = status2.withDescription(str);
        } else {
            status = status2.withDescription("Call cancelled without message");
        }
        if (th != null) {
            status = status.withCause(th);
        }
        cancel(status, false);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0017, code lost:
        if (r0 == false) goto L_0x0022;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0019, code lost:
        delayOrExecute(new io.grpc.internal.DelayedClientCall.AnonymousClass3(r2));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0022, code lost:
        if (r4 == null) goto L_0x002e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0024, code lost:
        r2.callExecutor.execute(new io.grpc.internal.DelayedClientCall.CloseListenerRunnable(r2, r4, r3));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002e, code lost:
        drainPendingCalls();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0031, code lost:
        callCancelled();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0034, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void cancel(final io.grpc.Status r3, boolean r4) {
        /*
            r2 = this;
            monitor-enter(r2)
            io.grpc.ClientCall<ReqT, RespT> r0 = r2.realCall     // Catch:{ all -> 0x0035 }
            if (r0 != 0) goto L_0x0010
            io.grpc.ClientCall<java.lang.Object, java.lang.Object> r4 = NOOP_CALL     // Catch:{ all -> 0x0035 }
            r2.setRealCall(r4)     // Catch:{ all -> 0x0035 }
            io.grpc.ClientCall$Listener<RespT> r4 = r2.listener     // Catch:{ all -> 0x0035 }
            r2.error = r3     // Catch:{ all -> 0x0035 }
            r0 = 0
            goto L_0x0016
        L_0x0010:
            if (r4 == 0) goto L_0x0014
            monitor-exit(r2)     // Catch:{ all -> 0x0035 }
            return
        L_0x0014:
            r0 = 1
            r4 = 0
        L_0x0016:
            monitor-exit(r2)     // Catch:{ all -> 0x0035 }
            if (r0 == 0) goto L_0x0022
            io.grpc.internal.DelayedClientCall$3 r4 = new io.grpc.internal.DelayedClientCall$3
            r4.<init>(r3)
            r2.delayOrExecute(r4)
            goto L_0x0031
        L_0x0022:
            if (r4 == 0) goto L_0x002e
            java.util.concurrent.Executor r0 = r2.callExecutor
            io.grpc.internal.DelayedClientCall$CloseListenerRunnable r1 = new io.grpc.internal.DelayedClientCall$CloseListenerRunnable
            r1.<init>(r4, r3)
            r0.execute(r1)
        L_0x002e:
            r2.drainPendingCalls()
        L_0x0031:
            r2.callCancelled()
            return
        L_0x0035:
            r3 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0035 }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.internal.DelayedClientCall.cancel(io.grpc.Status, boolean):void");
    }

    private void delayOrExecute(Runnable runnable) {
        synchronized (this) {
            if (!this.passThrough) {
                this.pendingRunnables.add(runnable);
            } else {
                runnable.run();
            }
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0029, code lost:
        r0 = r1.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0031, code lost:
        if (r0.hasNext() == false) goto L_0x003d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0033, code lost:
        ((java.lang.Runnable) r0.next()).run();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0017, code lost:
        if (r0 == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0019, code lost:
        r3.callExecutor.execute(new io.grpc.internal.DelayedClientCall.AnonymousClass1DrainListenerRunnable(r3));
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void drainPendingCalls() {
        /*
            r3 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
        L_0x0005:
            monitor-enter(r3)
            java.util.List<java.lang.Runnable> r1 = r3.pendingRunnables     // Catch:{ all -> 0x0042 }
            boolean r1 = r1.isEmpty()     // Catch:{ all -> 0x0042 }
            if (r1 == 0) goto L_0x0024
            r0 = 0
            r3.pendingRunnables = r0     // Catch:{ all -> 0x0042 }
            r0 = 1
            r3.passThrough = r0     // Catch:{ all -> 0x0042 }
            io.grpc.internal.DelayedClientCall$DelayedListener<RespT> r0 = r3.delayedListener     // Catch:{ all -> 0x0042 }
            monitor-exit(r3)     // Catch:{ all -> 0x0042 }
            if (r0 == 0) goto L_0x0023
            java.util.concurrent.Executor r1 = r3.callExecutor
            io.grpc.internal.DelayedClientCall$1DrainListenerRunnable r2 = new io.grpc.internal.DelayedClientCall$1DrainListenerRunnable
            r2.<init>(r0)
            r1.execute(r2)
        L_0x0023:
            return
        L_0x0024:
            java.util.List<java.lang.Runnable> r1 = r3.pendingRunnables     // Catch:{ all -> 0x0042 }
            r3.pendingRunnables = r0     // Catch:{ all -> 0x0042 }
            monitor-exit(r3)     // Catch:{ all -> 0x0042 }
            java.util.Iterator r0 = r1.iterator()
        L_0x002d:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x003d
            java.lang.Object r2 = r0.next()
            java.lang.Runnable r2 = (java.lang.Runnable) r2
            r2.run()
            goto L_0x002d
        L_0x003d:
            r1.clear()
            r0 = r1
            goto L_0x0005
        L_0x0042:
            r0 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x0042 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.internal.DelayedClientCall.drainPendingCalls():void");
    }

    private void setRealCall(ClientCall<ReqT, RespT> clientCall) {
        ClientCall<ReqT, RespT> clientCall2 = this.realCall;
        Preconditions.checkState(clientCall2 == null, "realCall already set to %s", (Object) clientCall2);
        ScheduledFuture<?> scheduledFuture = this.initialDeadlineMonitor;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
        }
        this.realCall = clientCall;
    }

    /* access modifiers changed from: package-private */
    public final ClientCall<ReqT, RespT> getRealCall() {
        return this.realCall;
    }

    public final void sendMessage(final ReqT reqt) {
        if (this.passThrough) {
            this.realCall.sendMessage(reqt);
        } else {
            delayOrExecute(new Runnable() {
                public void run() {
                    DelayedClientCall.this.realCall.sendMessage(reqt);
                }
            });
        }
    }

    public final void setMessageCompression(final boolean z) {
        if (this.passThrough) {
            this.realCall.setMessageCompression(z);
        } else {
            delayOrExecute(new Runnable() {
                public void run() {
                    DelayedClientCall.this.realCall.setMessageCompression(z);
                }
            });
        }
    }

    public final void request(final int i) {
        if (this.passThrough) {
            this.realCall.request(i);
        } else {
            delayOrExecute(new Runnable() {
                public void run() {
                    DelayedClientCall.this.realCall.request(i);
                }
            });
        }
    }

    public final void halfClose() {
        delayOrExecute(new Runnable() {
            public void run() {
                DelayedClientCall.this.realCall.halfClose();
            }
        });
    }

    public final boolean isReady() {
        if (this.passThrough) {
            return this.realCall.isReady();
        }
        return false;
    }

    public final Attributes getAttributes() {
        ClientCall<ReqT, RespT> clientCall;
        synchronized (this) {
            clientCall = this.realCall;
        }
        if (clientCall != null) {
            return clientCall.getAttributes();
        }
        return Attributes.EMPTY;
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object) this).add("realCall", (Object) this.realCall).toString();
    }

    private final class CloseListenerRunnable extends ContextRunnable {
        final ClientCall.Listener<RespT> listener;
        final Status status;

        CloseListenerRunnable(ClientCall.Listener<RespT> listener2, Status status2) {
            super(DelayedClientCall.this.context);
            this.listener = listener2;
            this.status = status2;
        }

        public void runInContext() {
            this.listener.onClose(this.status, new Metadata());
        }
    }

    private static final class DelayedListener<RespT> extends ClientCall.Listener<RespT> {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private volatile boolean passThrough;
        private List<Runnable> pendingCallbacks = new ArrayList();
        /* access modifiers changed from: private */
        public final ClientCall.Listener<RespT> realListener;

        static {
            Class<DelayedClientCall> cls = DelayedClientCall.class;
        }

        public DelayedListener(ClientCall.Listener<RespT> listener) {
            this.realListener = listener;
        }

        private void delayOrExecute(Runnable runnable) {
            synchronized (this) {
                if (!this.passThrough) {
                    this.pendingCallbacks.add(runnable);
                } else {
                    runnable.run();
                }
            }
        }

        public void onHeaders(final Metadata metadata) {
            if (this.passThrough) {
                this.realListener.onHeaders(metadata);
            } else {
                delayOrExecute(new Runnable() {
                    public void run() {
                        DelayedListener.this.realListener.onHeaders(metadata);
                    }
                });
            }
        }

        public void onMessage(final RespT respt) {
            if (this.passThrough) {
                this.realListener.onMessage(respt);
            } else {
                delayOrExecute(new Runnable() {
                    public void run() {
                        DelayedListener.this.realListener.onMessage(respt);
                    }
                });
            }
        }

        public void onClose(final Status status, final Metadata metadata) {
            delayOrExecute(new Runnable() {
                public void run() {
                    DelayedListener.this.realListener.onClose(status, metadata);
                }
            });
        }

        public void onReady() {
            if (this.passThrough) {
                this.realListener.onReady();
            } else {
                delayOrExecute(new Runnable() {
                    public void run() {
                        DelayedListener.this.realListener.onReady();
                    }
                });
            }
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Code restructure failed: missing block: B:10:0x001b, code lost:
            r0 = r1.iterator();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0023, code lost:
            if (r0.hasNext() == false) goto L_0x002f;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:13:0x0025, code lost:
            ((java.lang.Runnable) r0.next()).run();
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void drainPendingCallbacks() {
            /*
                r3 = this;
                java.util.ArrayList r0 = new java.util.ArrayList
                r0.<init>()
            L_0x0005:
                monitor-enter(r3)
                java.util.List<java.lang.Runnable> r1 = r3.pendingCallbacks     // Catch:{ all -> 0x0034 }
                boolean r1 = r1.isEmpty()     // Catch:{ all -> 0x0034 }
                if (r1 == 0) goto L_0x0016
                r0 = 0
                r3.pendingCallbacks = r0     // Catch:{ all -> 0x0034 }
                r0 = 1
                r3.passThrough = r0     // Catch:{ all -> 0x0034 }
                monitor-exit(r3)     // Catch:{ all -> 0x0034 }
                return
            L_0x0016:
                java.util.List<java.lang.Runnable> r1 = r3.pendingCallbacks     // Catch:{ all -> 0x0034 }
                r3.pendingCallbacks = r0     // Catch:{ all -> 0x0034 }
                monitor-exit(r3)     // Catch:{ all -> 0x0034 }
                java.util.Iterator r0 = r1.iterator()
            L_0x001f:
                boolean r2 = r0.hasNext()
                if (r2 == 0) goto L_0x002f
                java.lang.Object r2 = r0.next()
                java.lang.Runnable r2 = (java.lang.Runnable) r2
                r2.run()
                goto L_0x001f
            L_0x002f:
                r1.clear()
                r0 = r1
                goto L_0x0005
            L_0x0034:
                r0 = move-exception
                monitor-exit(r3)     // Catch:{ all -> 0x0034 }
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.internal.DelayedClientCall.DelayedListener.drainPendingCallbacks():void");
        }
    }
}
