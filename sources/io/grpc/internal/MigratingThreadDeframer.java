package io.grpc.internal;

import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.common.base.Preconditions;
import io.grpc.Decompressor;
import io.grpc.internal.ApplicationThreadDeframerListener;
import io.grpc.internal.MessageDeframer;
import io.grpc.internal.StreamListener;
import io.perfmark.Link;
import io.perfmark.PerfMark;
import io.perfmark.TaskCloseable;
import java.io.Closeable;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.Queue;

final class MigratingThreadDeframer implements ThreadOptimizedDeframer {
    /* access modifiers changed from: private */
    public final ApplicationThreadDeframerListener appListener;
    /* access modifiers changed from: private */
    public final MessageDeframer deframer;
    /* access modifiers changed from: private */
    public boolean deframerOnTransportThread;
    /* access modifiers changed from: private */
    public final Object lock = new Object();
    /* access modifiers changed from: private */
    public final DeframeMessageProducer messageProducer = new DeframeMessageProducer();
    /* access modifiers changed from: private */
    public boolean messageProducerEnqueued;
    /* access modifiers changed from: private */
    public final MigratingDeframerListener migratingListener;
    /* access modifiers changed from: private */
    public final Queue<Op> opQueue = new ArrayDeque();
    /* access modifiers changed from: private */
    public final ApplicationThreadDeframerListener.TransportExecutor transportExecutor;
    /* access modifiers changed from: private */
    public final MessageDeframer.Listener transportListener;

    private interface Op {
        void run(boolean z);
    }

    public MigratingThreadDeframer(MessageDeframer.Listener listener, ApplicationThreadDeframerListener.TransportExecutor transportExecutor2, MessageDeframer messageDeframer) {
        SquelchLateMessagesAvailableDeframerListener squelchLateMessagesAvailableDeframerListener = new SquelchLateMessagesAvailableDeframerListener((MessageDeframer.Listener) Preconditions.checkNotNull(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER));
        this.transportListener = squelchLateMessagesAvailableDeframerListener;
        this.transportExecutor = (ApplicationThreadDeframerListener.TransportExecutor) Preconditions.checkNotNull(transportExecutor2, "transportExecutor");
        ApplicationThreadDeframerListener applicationThreadDeframerListener = new ApplicationThreadDeframerListener(squelchLateMessagesAvailableDeframerListener, transportExecutor2);
        this.appListener = applicationThreadDeframerListener;
        MigratingDeframerListener migratingDeframerListener = new MigratingDeframerListener(applicationThreadDeframerListener);
        this.migratingListener = migratingDeframerListener;
        messageDeframer.setListener(migratingDeframerListener);
        this.deframer = messageDeframer;
    }

    public void setMaxInboundMessageSize(int i) {
        this.deframer.setMaxInboundMessageSize(i);
    }

    public void setDecompressor(Decompressor decompressor) {
        this.deframer.setDecompressor(decompressor);
    }

    public void setFullStreamDecompressor(GzipInflatingBuffer gzipInflatingBuffer) {
        this.deframer.setFullStreamDecompressor(gzipInflatingBuffer);
    }

    private boolean runWhereAppropriate(Op op) {
        return runWhereAppropriate(op, true);
    }

    private boolean runWhereAppropriate(Op op, boolean z) {
        boolean z2;
        boolean z3;
        synchronized (this.lock) {
            z2 = this.deframerOnTransportThread;
            z3 = this.messageProducerEnqueued;
            if (!z2) {
                this.opQueue.offer(op);
                this.messageProducerEnqueued = true;
            }
        }
        if (z2) {
            op.run(true);
            return true;
        } else if (z3) {
            return false;
        } else {
            if (z) {
                TaskCloseable traceTask = PerfMark.traceTask("MigratingThreadDeframer.messageAvailable");
                try {
                    this.transportListener.messagesAvailable(this.messageProducer);
                    if (traceTask == null) {
                        return false;
                    }
                    traceTask.close();
                    return false;
                } catch (Throwable th) {
                    th.addSuppressed(th);
                }
            } else {
                final Link linkOut = PerfMark.linkOut();
                this.transportExecutor.runOnTransportThread(new Runnable() {
                    public void run() {
                        TaskCloseable traceTask = PerfMark.traceTask("MigratingThreadDeframer.messageAvailable");
                        try {
                            PerfMark.linkIn(linkOut);
                            MigratingThreadDeframer.this.transportListener.messagesAvailable(MigratingThreadDeframer.this.messageProducer);
                            if (traceTask != null) {
                                traceTask.close();
                                return;
                            }
                            return;
                        } catch (Throwable th) {
                            th.addSuppressed(th);
                        }
                        throw th;
                    }
                });
                return false;
            }
        }
        throw th;
    }

    public void request(final int i) {
        runWhereAppropriate(new Op() {
            public void run(boolean z) {
                TaskCloseable traceTask;
                if (z) {
                    final Link linkOut = PerfMark.linkOut();
                    MigratingThreadDeframer.this.transportExecutor.runOnTransportThread(new Runnable() {
                        public void run() {
                            TaskCloseable traceTask = PerfMark.traceTask("MigratingThreadDeframer.request");
                            try {
                                PerfMark.linkIn(linkOut);
                                MigratingThreadDeframer.this.requestFromTransportThread(i);
                                if (traceTask != null) {
                                    traceTask.close();
                                    return;
                                }
                                return;
                            } catch (Throwable th) {
                                th.addSuppressed(th);
                            }
                            throw th;
                        }
                    });
                    return;
                }
                try {
                    traceTask = PerfMark.traceTask("MigratingThreadDeframer.request");
                    MigratingThreadDeframer.this.deframer.request(i);
                    if (traceTask != null) {
                        traceTask.close();
                        return;
                    }
                    return;
                } catch (Throwable th) {
                    MigratingThreadDeframer.this.appListener.deframeFailed(th);
                    MigratingThreadDeframer.this.deframer.close();
                    return;
                }
                throw th;
            }
        }, false);
    }

    /* access modifiers changed from: private */
    public void requestFromTransportThread(final int i) {
        runWhereAppropriate(new Op() {
            public void run(boolean z) {
                if (!z) {
                    MigratingThreadDeframer.this.request(i);
                    return;
                }
                try {
                    MigratingThreadDeframer.this.deframer.request(i);
                } catch (Throwable th) {
                    MigratingThreadDeframer.this.appListener.deframeFailed(th);
                    MigratingThreadDeframer.this.deframer.close();
                }
                if (!MigratingThreadDeframer.this.deframer.hasPendingDeliveries()) {
                    synchronized (MigratingThreadDeframer.this.lock) {
                        PerfMark.event("MigratingThreadDeframer.deframerOnApplicationThread");
                        MigratingThreadDeframer.this.migratingListener.setDelegate(MigratingThreadDeframer.this.appListener);
                        boolean unused = MigratingThreadDeframer.this.deframerOnTransportThread = false;
                    }
                }
            }
        });
    }

    public void deframe(final ReadableBuffer readableBuffer) {
        runWhereAppropriate(new Object() {
            public void run(boolean z) {
                TaskCloseable traceTask = PerfMark.traceTask("MigratingThreadDeframer.deframe");
                if (z) {
                    try {
                        MigratingThreadDeframer.this.deframer.deframe(readableBuffer);
                        if (traceTask != null) {
                            traceTask.close();
                            return;
                        }
                        return;
                    } catch (Throwable th) {
                        th.addSuppressed(th);
                    }
                } else {
                    try {
                        MigratingThreadDeframer.this.deframer.deframe(readableBuffer);
                    } catch (Throwable th2) {
                        MigratingThreadDeframer.this.appListener.deframeFailed(th2);
                        MigratingThreadDeframer.this.deframer.close();
                    }
                    if (traceTask != null) {
                        traceTask.close();
                        return;
                    }
                    return;
                }
                throw th;
            }

            public void close() {
                readableBuffer.close();
            }
        });
    }

    public void closeWhenComplete() {
        runWhereAppropriate(new Op() {
            public void run(boolean z) {
                MigratingThreadDeframer.this.deframer.closeWhenComplete();
            }
        });
    }

    public void close() {
        if (!runWhereAppropriate(new Op() {
            public void run(boolean z) {
                MigratingThreadDeframer.this.deframer.close();
            }
        })) {
            this.deframer.stopDelivery();
        }
    }

    class DeframeMessageProducer implements StreamListener.MessageProducer, Closeable {
        DeframeMessageProducer() {
        }

        public InputStream next() {
            Op op;
            while (true) {
                InputStream messageReadQueuePoll = MigratingThreadDeframer.this.appListener.messageReadQueuePoll();
                if (messageReadQueuePoll != null) {
                    return messageReadQueuePoll;
                }
                synchronized (MigratingThreadDeframer.this.lock) {
                    op = (Op) MigratingThreadDeframer.this.opQueue.poll();
                    if (op == null) {
                        if (MigratingThreadDeframer.this.deframer.hasPendingDeliveries()) {
                            PerfMark.event("MigratingThreadDeframer.deframerOnTransportThread");
                            MigratingThreadDeframer.this.migratingListener.setDelegate(MigratingThreadDeframer.this.transportListener);
                            boolean unused = MigratingThreadDeframer.this.deframerOnTransportThread = true;
                        }
                        boolean unused2 = MigratingThreadDeframer.this.messageProducerEnqueued = false;
                        return null;
                    }
                }
                op.run(false);
            }
            while (true) {
            }
        }

        /* JADX WARNING: Removed duplicated region for block: B:17:0x001b A[SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void close() {
            /*
                r3 = this;
            L_0x0000:
                io.grpc.internal.MigratingThreadDeframer r0 = io.grpc.internal.MigratingThreadDeframer.this
                java.lang.Object r0 = r0.lock
                monitor-enter(r0)
            L_0x0007:
                io.grpc.internal.MigratingThreadDeframer r1 = io.grpc.internal.MigratingThreadDeframer.this     // Catch:{ all -> 0x002a }
                java.util.Queue r1 = r1.opQueue     // Catch:{ all -> 0x002a }
                java.lang.Object r1 = r1.poll()     // Catch:{ all -> 0x002a }
                io.grpc.internal.MigratingThreadDeframer$Op r1 = (io.grpc.internal.MigratingThreadDeframer.Op) r1     // Catch:{ all -> 0x002a }
                if (r1 == 0) goto L_0x0019
                boolean r2 = r1 instanceof java.io.Closeable     // Catch:{ all -> 0x002a }
                if (r2 == 0) goto L_0x0007
            L_0x0019:
                if (r1 != 0) goto L_0x0023
                io.grpc.internal.MigratingThreadDeframer r1 = io.grpc.internal.MigratingThreadDeframer.this     // Catch:{ all -> 0x002a }
                r2 = 0
                boolean unused = r1.messageProducerEnqueued = r2     // Catch:{ all -> 0x002a }
                monitor-exit(r0)     // Catch:{ all -> 0x002a }
                return
            L_0x0023:
                monitor-exit(r0)     // Catch:{ all -> 0x002a }
                java.io.Closeable r1 = (java.io.Closeable) r1
                io.grpc.internal.GrpcUtil.closeQuietly((java.io.Closeable) r1)
                goto L_0x0000
            L_0x002a:
                r1 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x002a }
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.internal.MigratingThreadDeframer.DeframeMessageProducer.close():void");
        }
    }

    static class MigratingDeframerListener extends ForwardingDeframerListener {
        private MessageDeframer.Listener delegate;

        public MigratingDeframerListener(MessageDeframer.Listener listener) {
            setDelegate(listener);
        }

        /* access modifiers changed from: protected */
        public MessageDeframer.Listener delegate() {
            return this.delegate;
        }

        public void setDelegate(MessageDeframer.Listener listener) {
            this.delegate = (MessageDeframer.Listener) Preconditions.checkNotNull(listener, "delegate");
        }
    }
}
