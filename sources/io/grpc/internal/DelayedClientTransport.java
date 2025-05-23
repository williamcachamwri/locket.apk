package io.grpc.internal;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import io.grpc.CallOptions;
import io.grpc.ClientStreamTracer;
import io.grpc.Context;
import io.grpc.InternalChannelz;
import io.grpc.InternalLogId;
import io.grpc.LoadBalancer;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.Status;
import io.grpc.SynchronizationContext;
import io.grpc.internal.ClientStreamListener;
import io.grpc.internal.ClientTransport;
import io.grpc.internal.ManagedClientTransport;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.concurrent.Executor;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

final class DelayedClientTransport implements ManagedClientTransport {
    private final Executor defaultAppExecutor;
    @Nullable
    private LoadBalancer.SubchannelPicker lastPicker;
    private long lastPickerVersion;
    /* access modifiers changed from: private */
    public ManagedClientTransport.Listener listener;
    /* access modifiers changed from: private */
    public final Object lock = new Object();
    private final InternalLogId logId = InternalLogId.allocate((Class<?>) DelayedClientTransport.class, (String) null);
    /* access modifiers changed from: private */
    @Nonnull
    public Collection<PendingStream> pendingStreams = new LinkedHashSet();
    private Runnable reportTransportInUse;
    /* access modifiers changed from: private */
    public Runnable reportTransportNotInUse;
    /* access modifiers changed from: private */
    public Runnable reportTransportTerminated;
    /* access modifiers changed from: private */
    public Status shutdownStatus;
    /* access modifiers changed from: private */
    public final SynchronizationContext syncContext;

    DelayedClientTransport(Executor executor, SynchronizationContext synchronizationContext) {
        this.defaultAppExecutor = executor;
        this.syncContext = synchronizationContext;
    }

    public final Runnable start(final ManagedClientTransport.Listener listener2) {
        this.listener = listener2;
        this.reportTransportInUse = new Runnable() {
            public void run() {
                listener2.transportInUse(true);
            }
        };
        this.reportTransportNotInUse = new Runnable() {
            public void run() {
                listener2.transportInUse(false);
            }
        };
        this.reportTransportTerminated = new Runnable() {
            public void run() {
                listener2.transportTerminated();
            }
        };
        return null;
    }

    public final ClientStream newStream(MethodDescriptor<?, ?> methodDescriptor, Metadata metadata, CallOptions callOptions, ClientStreamTracer[] clientStreamTracerArr) {
        ClientStream failingClientStream;
        try {
            PickSubchannelArgsImpl pickSubchannelArgsImpl = new PickSubchannelArgsImpl(methodDescriptor, metadata, callOptions);
            LoadBalancer.SubchannelPicker subchannelPicker = null;
            long j = -1;
            while (true) {
                synchronized (this.lock) {
                    if (this.shutdownStatus == null) {
                        LoadBalancer.SubchannelPicker subchannelPicker2 = this.lastPicker;
                        if (subchannelPicker2 != null) {
                            if (subchannelPicker != null && j == this.lastPickerVersion) {
                                failingClientStream = createPendingStream(pickSubchannelArgsImpl, clientStreamTracerArr);
                                break;
                            }
                            j = this.lastPickerVersion;
                            ClientTransport transportFromPickResult = GrpcUtil.getTransportFromPickResult(subchannelPicker2.pickSubchannel(pickSubchannelArgsImpl), callOptions.isWaitForReady());
                            if (transportFromPickResult != null) {
                                failingClientStream = transportFromPickResult.newStream(pickSubchannelArgsImpl.getMethodDescriptor(), pickSubchannelArgsImpl.getHeaders(), pickSubchannelArgsImpl.getCallOptions(), clientStreamTracerArr);
                                break;
                            }
                            subchannelPicker = subchannelPicker2;
                        } else {
                            failingClientStream = createPendingStream(pickSubchannelArgsImpl, clientStreamTracerArr);
                            break;
                        }
                    } else {
                        failingClientStream = new FailingClientStream(this.shutdownStatus, clientStreamTracerArr);
                        break;
                    }
                }
            }
            this.syncContext.drain();
            return failingClientStream;
        } catch (Throwable th) {
            this.syncContext.drain();
            throw th;
        }
    }

    private PendingStream createPendingStream(LoadBalancer.PickSubchannelArgs pickSubchannelArgs, ClientStreamTracer[] clientStreamTracerArr) {
        PendingStream pendingStream = new PendingStream(pickSubchannelArgs, clientStreamTracerArr);
        this.pendingStreams.add(pendingStream);
        if (getPendingStreamsCount() == 1) {
            this.syncContext.executeLater(this.reportTransportInUse);
        }
        for (ClientStreamTracer createPendingStream : clientStreamTracerArr) {
            createPendingStream.createPendingStream();
        }
        return pendingStream;
    }

    public final void ping(ClientTransport.PingCallback pingCallback, Executor executor) {
        throw new UnsupportedOperationException("This method is not expected to be called");
    }

    public ListenableFuture<InternalChannelz.SocketStats> getStats() {
        SettableFuture create = SettableFuture.create();
        create.set(null);
        return create;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0028, code lost:
        r3.syncContext.drain();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002d, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void shutdown(final io.grpc.Status r4) {
        /*
            r3 = this;
            java.lang.Object r0 = r3.lock
            monitor-enter(r0)
            io.grpc.Status r1 = r3.shutdownStatus     // Catch:{ all -> 0x002e }
            if (r1 == 0) goto L_0x0009
            monitor-exit(r0)     // Catch:{ all -> 0x002e }
            return
        L_0x0009:
            r3.shutdownStatus = r4     // Catch:{ all -> 0x002e }
            io.grpc.SynchronizationContext r1 = r3.syncContext     // Catch:{ all -> 0x002e }
            io.grpc.internal.DelayedClientTransport$4 r2 = new io.grpc.internal.DelayedClientTransport$4     // Catch:{ all -> 0x002e }
            r2.<init>(r4)     // Catch:{ all -> 0x002e }
            r1.executeLater(r2)     // Catch:{ all -> 0x002e }
            boolean r4 = r3.hasPendingStreams()     // Catch:{ all -> 0x002e }
            if (r4 != 0) goto L_0x0027
            java.lang.Runnable r4 = r3.reportTransportTerminated     // Catch:{ all -> 0x002e }
            if (r4 == 0) goto L_0x0027
            io.grpc.SynchronizationContext r1 = r3.syncContext     // Catch:{ all -> 0x002e }
            r1.executeLater(r4)     // Catch:{ all -> 0x002e }
            r4 = 0
            r3.reportTransportTerminated = r4     // Catch:{ all -> 0x002e }
        L_0x0027:
            monitor-exit(r0)     // Catch:{ all -> 0x002e }
            io.grpc.SynchronizationContext r4 = r3.syncContext
            r4.drain()
            return
        L_0x002e:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002e }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.internal.DelayedClientTransport.shutdown(io.grpc.Status):void");
    }

    public final void shutdownNow(Status status) {
        Collection<PendingStream> collection;
        Runnable runnable;
        shutdown(status);
        synchronized (this.lock) {
            collection = this.pendingStreams;
            runnable = this.reportTransportTerminated;
            this.reportTransportTerminated = null;
            if (!collection.isEmpty()) {
                this.pendingStreams = Collections.emptyList();
            }
        }
        if (runnable != null) {
            for (PendingStream next : collection) {
                Runnable stream = next.setStream(new FailingClientStream(status, ClientStreamListener.RpcProgress.REFUSED, next.tracers));
                if (stream != null) {
                    stream.run();
                }
            }
            this.syncContext.execute(runnable);
        }
    }

    public final boolean hasPendingStreams() {
        boolean z;
        synchronized (this.lock) {
            z = !this.pendingStreams.isEmpty();
        }
        return z;
    }

    /* access modifiers changed from: package-private */
    public final int getPendingStreamsCount() {
        int size;
        synchronized (this.lock) {
            size = this.pendingStreams.size();
        }
        return size;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001e, code lost:
        r0 = new java.util.ArrayList();
        r1 = r1.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002b, code lost:
        if (r1.hasNext() == false) goto L_0x0066;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002d, code lost:
        r2 = (io.grpc.internal.DelayedClientTransport.PendingStream) r1.next();
        r3 = r8.pickSubchannel(io.grpc.internal.DelayedClientTransport.PendingStream.access$300(r2));
        r4 = io.grpc.internal.DelayedClientTransport.PendingStream.access$300(r2).getCallOptions();
        r3 = io.grpc.internal.GrpcUtil.getTransportFromPickResult(r3, r4.isWaitForReady());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x004b, code lost:
        if (r3 == null) goto L_0x0027;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x004d, code lost:
        r5 = r7.defaultAppExecutor;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0053, code lost:
        if (r4.getExecutor() == null) goto L_0x0059;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0055, code lost:
        r5 = r4.getExecutor();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0059, code lost:
        r3 = io.grpc.internal.DelayedClientTransport.PendingStream.access$400(r2, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x005d, code lost:
        if (r3 == null) goto L_0x0062;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x005f, code lost:
        r5.execute(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0062, code lost:
        r0.add(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0066, code lost:
        r8 = r7.lock;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0068, code lost:
        monitor-enter(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x006d, code lost:
        if (hasPendingStreams() != false) goto L_0x0071;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x006f, code lost:
        monitor-exit(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0070, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0071, code lost:
        r7.pendingStreams.removeAll(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x007c, code lost:
        if (r7.pendingStreams.isEmpty() == false) goto L_0x0085;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x007e, code lost:
        r7.pendingStreams = new java.util.LinkedHashSet();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0089, code lost:
        if (hasPendingStreams() != false) goto L_0x00a2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x008b, code lost:
        r7.syncContext.executeLater(r7.reportTransportNotInUse);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0094, code lost:
        if (r7.shutdownStatus == null) goto L_0x00a2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0096, code lost:
        r0 = r7.reportTransportTerminated;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0098, code lost:
        if (r0 == null) goto L_0x00a2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x009a, code lost:
        r7.syncContext.executeLater(r0);
        r7.reportTransportTerminated = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00a2, code lost:
        monitor-exit(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00a3, code lost:
        r7.syncContext.drain();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00a8, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void reprocess(@javax.annotation.Nullable io.grpc.LoadBalancer.SubchannelPicker r8) {
        /*
            r7 = this;
            java.lang.Object r0 = r7.lock
            monitor-enter(r0)
            r7.lastPicker = r8     // Catch:{ all -> 0x00ae }
            long r1 = r7.lastPickerVersion     // Catch:{ all -> 0x00ae }
            r3 = 1
            long r1 = r1 + r3
            r7.lastPickerVersion = r1     // Catch:{ all -> 0x00ae }
            if (r8 == 0) goto L_0x00ac
            boolean r1 = r7.hasPendingStreams()     // Catch:{ all -> 0x00ae }
            if (r1 != 0) goto L_0x0016
            goto L_0x00ac
        L_0x0016:
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ all -> 0x00ae }
            java.util.Collection<io.grpc.internal.DelayedClientTransport$PendingStream> r2 = r7.pendingStreams     // Catch:{ all -> 0x00ae }
            r1.<init>(r2)     // Catch:{ all -> 0x00ae }
            monitor-exit(r0)     // Catch:{ all -> 0x00ae }
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.Iterator r1 = r1.iterator()
        L_0x0027:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0066
            java.lang.Object r2 = r1.next()
            io.grpc.internal.DelayedClientTransport$PendingStream r2 = (io.grpc.internal.DelayedClientTransport.PendingStream) r2
            io.grpc.LoadBalancer$PickSubchannelArgs r3 = r2.args
            io.grpc.LoadBalancer$PickResult r3 = r8.pickSubchannel(r3)
            io.grpc.LoadBalancer$PickSubchannelArgs r4 = r2.args
            io.grpc.CallOptions r4 = r4.getCallOptions()
            boolean r5 = r4.isWaitForReady()
            io.grpc.internal.ClientTransport r3 = io.grpc.internal.GrpcUtil.getTransportFromPickResult(r3, r5)
            if (r3 == 0) goto L_0x0027
            java.util.concurrent.Executor r5 = r7.defaultAppExecutor
            java.util.concurrent.Executor r6 = r4.getExecutor()
            if (r6 == 0) goto L_0x0059
            java.util.concurrent.Executor r5 = r4.getExecutor()
        L_0x0059:
            java.lang.Runnable r3 = r2.createRealStream(r3)
            if (r3 == 0) goto L_0x0062
            r5.execute(r3)
        L_0x0062:
            r0.add(r2)
            goto L_0x0027
        L_0x0066:
            java.lang.Object r8 = r7.lock
            monitor-enter(r8)
            boolean r1 = r7.hasPendingStreams()     // Catch:{ all -> 0x00a9 }
            if (r1 != 0) goto L_0x0071
            monitor-exit(r8)     // Catch:{ all -> 0x00a9 }
            return
        L_0x0071:
            java.util.Collection<io.grpc.internal.DelayedClientTransport$PendingStream> r1 = r7.pendingStreams     // Catch:{ all -> 0x00a9 }
            r1.removeAll(r0)     // Catch:{ all -> 0x00a9 }
            java.util.Collection<io.grpc.internal.DelayedClientTransport$PendingStream> r0 = r7.pendingStreams     // Catch:{ all -> 0x00a9 }
            boolean r0 = r0.isEmpty()     // Catch:{ all -> 0x00a9 }
            if (r0 == 0) goto L_0x0085
            java.util.LinkedHashSet r0 = new java.util.LinkedHashSet     // Catch:{ all -> 0x00a9 }
            r0.<init>()     // Catch:{ all -> 0x00a9 }
            r7.pendingStreams = r0     // Catch:{ all -> 0x00a9 }
        L_0x0085:
            boolean r0 = r7.hasPendingStreams()     // Catch:{ all -> 0x00a9 }
            if (r0 != 0) goto L_0x00a2
            io.grpc.SynchronizationContext r0 = r7.syncContext     // Catch:{ all -> 0x00a9 }
            java.lang.Runnable r1 = r7.reportTransportNotInUse     // Catch:{ all -> 0x00a9 }
            r0.executeLater(r1)     // Catch:{ all -> 0x00a9 }
            io.grpc.Status r0 = r7.shutdownStatus     // Catch:{ all -> 0x00a9 }
            if (r0 == 0) goto L_0x00a2
            java.lang.Runnable r0 = r7.reportTransportTerminated     // Catch:{ all -> 0x00a9 }
            if (r0 == 0) goto L_0x00a2
            io.grpc.SynchronizationContext r1 = r7.syncContext     // Catch:{ all -> 0x00a9 }
            r1.executeLater(r0)     // Catch:{ all -> 0x00a9 }
            r0 = 0
            r7.reportTransportTerminated = r0     // Catch:{ all -> 0x00a9 }
        L_0x00a2:
            monitor-exit(r8)     // Catch:{ all -> 0x00a9 }
            io.grpc.SynchronizationContext r8 = r7.syncContext
            r8.drain()
            return
        L_0x00a9:
            r0 = move-exception
            monitor-exit(r8)     // Catch:{ all -> 0x00a9 }
            throw r0
        L_0x00ac:
            monitor-exit(r0)     // Catch:{ all -> 0x00ae }
            return
        L_0x00ae:
            r8 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x00ae }
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.internal.DelayedClientTransport.reprocess(io.grpc.LoadBalancer$SubchannelPicker):void");
    }

    public InternalLogId getLogId() {
        return this.logId;
    }

    private class PendingStream extends DelayedStream {
        /* access modifiers changed from: private */
        public final LoadBalancer.PickSubchannelArgs args;
        private final Context context;
        /* access modifiers changed from: private */
        public final ClientStreamTracer[] tracers;

        private PendingStream(LoadBalancer.PickSubchannelArgs pickSubchannelArgs, ClientStreamTracer[] clientStreamTracerArr) {
            this.context = Context.current();
            this.args = pickSubchannelArgs;
            this.tracers = clientStreamTracerArr;
        }

        /* JADX INFO: finally extract failed */
        /* access modifiers changed from: private */
        public Runnable createRealStream(ClientTransport clientTransport) {
            Context attach = this.context.attach();
            try {
                ClientStream newStream = clientTransport.newStream(this.args.getMethodDescriptor(), this.args.getHeaders(), this.args.getCallOptions(), this.tracers);
                this.context.detach(attach);
                return setStream(newStream);
            } catch (Throwable th) {
                this.context.detach(attach);
                throw th;
            }
        }

        public void cancel(Status status) {
            super.cancel(status);
            synchronized (DelayedClientTransport.this.lock) {
                if (DelayedClientTransport.this.reportTransportTerminated != null) {
                    boolean remove = DelayedClientTransport.this.pendingStreams.remove(this);
                    if (!DelayedClientTransport.this.hasPendingStreams() && remove) {
                        DelayedClientTransport.this.syncContext.executeLater(DelayedClientTransport.this.reportTransportNotInUse);
                        if (DelayedClientTransport.this.shutdownStatus != null) {
                            DelayedClientTransport.this.syncContext.executeLater(DelayedClientTransport.this.reportTransportTerminated);
                            Runnable unused = DelayedClientTransport.this.reportTransportTerminated = null;
                        }
                    }
                }
            }
            DelayedClientTransport.this.syncContext.drain();
        }

        /* access modifiers changed from: protected */
        public void onEarlyCancellation(Status status) {
            for (ClientStreamTracer streamClosed : this.tracers) {
                streamClosed.streamClosed(status);
            }
        }

        public void appendTimeoutInsight(InsightBuilder insightBuilder) {
            if (this.args.getCallOptions().isWaitForReady()) {
                insightBuilder.append("wait_for_ready");
            }
            super.appendTimeoutInsight(insightBuilder);
        }
    }
}
