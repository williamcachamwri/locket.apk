package io.grpc.internal;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import io.grpc.Attributes;
import io.grpc.ClientStreamTracer;
import io.grpc.Compressor;
import io.grpc.Deadline;
import io.grpc.DecompressorRegistry;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.Status;
import io.grpc.SynchronizationContext;
import io.grpc.internal.ClientStreamListener;
import io.grpc.internal.StreamListener;
import java.io.InputStream;
import java.lang.Thread;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.CheckForNull;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;

abstract class RetriableStream<ReqT> implements ClientStream {
    /* access modifiers changed from: private */
    public static final Status CANCELLED_BECAUSE_COMMITTED = Status.CANCELLED.withDescription("Stream thrown away because RetriableStream committed");
    static final Metadata.Key<String> GRPC_PREVIOUS_RPC_ATTEMPTS = Metadata.Key.of("grpc-previous-rpc-attempts", Metadata.ASCII_STRING_MARSHALLER);
    static final Metadata.Key<String> GRPC_RETRY_PUSHBACK_MS = Metadata.Key.of("grpc-retry-pushback-ms", Metadata.ASCII_STRING_MARSHALLER);
    /* access modifiers changed from: private */
    public static Random random = new Random();
    /* access modifiers changed from: private */
    public final Executor callExecutor;
    private Status cancellationStatus;
    /* access modifiers changed from: private */
    public final long channelBufferLimit;
    /* access modifiers changed from: private */
    public final ChannelBufferMeter channelBufferUsed;
    /* access modifiers changed from: private */
    public final InsightBuilder closedSubstreamsInsight = new InsightBuilder();
    private final Metadata headers;
    /* access modifiers changed from: private */
    @Nullable
    public final HedgingPolicy hedgingPolicy;
    /* access modifiers changed from: private */
    public final AtomicInteger inFlightSubStreams = new AtomicInteger();
    /* access modifiers changed from: private */
    public boolean isClosed;
    /* access modifiers changed from: private */
    public final boolean isHedging;
    /* access modifiers changed from: private */
    public final Executor listenerSerializeExecutor = new SynchronizationContext(new Thread.UncaughtExceptionHandler() {
        public void uncaughtException(Thread thread, Throwable th) {
            throw Status.fromThrowable(th).withDescription("Uncaught exception in the SynchronizationContext. Re-thrown.").asRuntimeException();
        }
    });
    /* access modifiers changed from: private */
    public final AtomicInteger localOnlyTransparentRetries = new AtomicInteger();
    /* access modifiers changed from: private */
    public final Object lock = new Object();
    /* access modifiers changed from: private */
    public ClientStreamListener masterListener;
    /* access modifiers changed from: private */
    public final MethodDescriptor<ReqT, ?> method;
    /* access modifiers changed from: private */
    public long nextBackoffIntervalNanos;
    /* access modifiers changed from: private */
    public final AtomicBoolean noMoreTransparentRetry = new AtomicBoolean();
    /* access modifiers changed from: private */
    public final long perRpcBufferLimit;
    /* access modifiers changed from: private */
    public long perRpcBufferUsed;
    /* access modifiers changed from: private */
    @Nullable
    public final RetryPolicy retryPolicy;
    /* access modifiers changed from: private */
    public SavedCloseMasterListenerReason savedCloseMasterListenerReason;
    /* access modifiers changed from: private */
    public final ScheduledExecutorService scheduledExecutorService;
    /* access modifiers changed from: private */
    public FutureCanceller scheduledHedging;
    /* access modifiers changed from: private */
    public FutureCanceller scheduledRetry;
    /* access modifiers changed from: private */
    public volatile State state = new State(new ArrayList(8), Collections.emptyList(), (Collection<Substream>) null, (Substream) null, false, false, false, 0);
    /* access modifiers changed from: private */
    @Nullable
    public final Throttle throttle;

    private interface BufferEntry {
        void runWith(Substream substream);
    }

    /* access modifiers changed from: package-private */
    public abstract ClientStream newSubstream(Metadata metadata, ClientStreamTracer.Factory factory, int i, boolean z);

    /* access modifiers changed from: package-private */
    public abstract void postCommit();

    /* access modifiers changed from: package-private */
    @CheckReturnValue
    @Nullable
    public abstract Status prestart();

    RetriableStream(MethodDescriptor<ReqT, ?> methodDescriptor, Metadata metadata, ChannelBufferMeter channelBufferMeter, long j, long j2, Executor executor, ScheduledExecutorService scheduledExecutorService2, @Nullable RetryPolicy retryPolicy2, @Nullable HedgingPolicy hedgingPolicy2, @Nullable Throttle throttle2) {
        RetryPolicy retryPolicy3 = retryPolicy2;
        HedgingPolicy hedgingPolicy3 = hedgingPolicy2;
        this.method = methodDescriptor;
        this.channelBufferUsed = channelBufferMeter;
        this.perRpcBufferLimit = j;
        this.channelBufferLimit = j2;
        this.callExecutor = executor;
        this.scheduledExecutorService = scheduledExecutorService2;
        this.headers = metadata;
        this.retryPolicy = retryPolicy3;
        if (retryPolicy3 != null) {
            this.nextBackoffIntervalNanos = retryPolicy3.initialBackoffNanos;
        }
        this.hedgingPolicy = hedgingPolicy3;
        boolean z = false;
        Preconditions.checkArgument(retryPolicy3 == null || hedgingPolicy3 == null, "Should not provide both retryPolicy and hedgingPolicy");
        this.isHedging = hedgingPolicy3 != null ? true : z;
        this.throttle = throttle2;
    }

    /* access modifiers changed from: private */
    @CheckReturnValue
    @Nullable
    public Runnable commit(Substream substream) {
        final Future<?> future;
        final Future<?> future2;
        synchronized (this.lock) {
            if (this.state.winningSubstream != null) {
                return null;
            }
            final Collection<Substream> collection = this.state.drainedSubstreams;
            this.state = this.state.committed(substream);
            this.channelBufferUsed.addAndGet(-this.perRpcBufferUsed);
            FutureCanceller futureCanceller = this.scheduledRetry;
            if (futureCanceller != null) {
                Future<?> markCancelled = futureCanceller.markCancelled();
                this.scheduledRetry = null;
                future = markCancelled;
            } else {
                future = null;
            }
            FutureCanceller futureCanceller2 = this.scheduledHedging;
            if (futureCanceller2 != null) {
                Future<?> markCancelled2 = futureCanceller2.markCancelled();
                this.scheduledHedging = null;
                future2 = markCancelled2;
            } else {
                future2 = null;
            }
            final Substream substream2 = substream;
            AnonymousClass1CommitTask r3 = new Runnable() {
                public void run() {
                    for (Substream substream : collection) {
                        if (substream != substream2) {
                            substream.stream.cancel(RetriableStream.CANCELLED_BECAUSE_COMMITTED);
                        }
                    }
                    Future future = future;
                    if (future != null) {
                        future.cancel(false);
                    }
                    Future future2 = future2;
                    if (future2 != null) {
                        future2.cancel(false);
                    }
                    RetriableStream.this.postCommit();
                }
            };
            return r3;
        }
    }

    /* access modifiers changed from: private */
    public void commitAndRun(Substream substream) {
        Runnable commit = commit(substream);
        if (commit != null) {
            this.callExecutor.execute(commit);
        }
    }

    /* access modifiers changed from: private */
    @Nullable
    public Substream createSubstream(int i, boolean z) {
        int i2;
        do {
            i2 = this.inFlightSubStreams.get();
            if (i2 < 0) {
                return null;
            }
        } while (!this.inFlightSubStreams.compareAndSet(i2, i2 + 1));
        Substream substream = new Substream(i);
        final BufferSizeTracer bufferSizeTracer = new BufferSizeTracer(substream);
        substream.stream = newSubstream(updateHeaders(this.headers, i), new ClientStreamTracer.Factory() {
            public ClientStreamTracer newClientStreamTracer(ClientStreamTracer.StreamInfo streamInfo, Metadata metadata) {
                return bufferSizeTracer;
            }
        }, i, z);
        return substream;
    }

    /* access modifiers changed from: package-private */
    public final Metadata updateHeaders(Metadata metadata, int i) {
        Metadata metadata2 = new Metadata();
        metadata2.merge(metadata);
        if (i > 0) {
            metadata2.put(GRPC_PREVIOUS_RPC_ATTEMPTS, String.valueOf(i));
        }
        return metadata2;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0087, code lost:
        r0 = r3.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x008f, code lost:
        if (r0.hasNext() == false) goto L_0x00ae;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0091, code lost:
        r4 = (io.grpc.internal.RetriableStream.BufferEntry) r0.next();
        r4.runWith(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x009c, code lost:
        if ((r4 instanceof io.grpc.internal.RetriableStream.StartEntry) == false) goto L_0x009f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x009e, code lost:
        r2 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x009f, code lost:
        r4 = r8.state;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00a3, code lost:
        if (r4.winningSubstream == null) goto L_0x00aa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00a7, code lost:
        if (r4.winningSubstream == r9) goto L_0x00aa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00ac, code lost:
        if (r4.cancelled == false) goto L_0x008b;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void drain(io.grpc.internal.RetriableStream.Substream r9) {
        /*
            r8 = this;
            r0 = 0
            r1 = 0
            r2 = r0
            r3 = r1
        L_0x0004:
            java.lang.Object r4 = r8.lock
            monitor-enter(r4)
            io.grpc.internal.RetriableStream$State r5 = r8.state     // Catch:{ all -> 0x00b1 }
            io.grpc.internal.RetriableStream$Substream r6 = r5.winningSubstream     // Catch:{ all -> 0x00b1 }
            if (r6 == 0) goto L_0x0013
            io.grpc.internal.RetriableStream$Substream r6 = r5.winningSubstream     // Catch:{ all -> 0x00b1 }
            if (r6 == r9) goto L_0x0013
            monitor-exit(r4)     // Catch:{ all -> 0x00b1 }
            goto L_0x0035
        L_0x0013:
            boolean r6 = r5.cancelled     // Catch:{ all -> 0x00b1 }
            if (r6 == 0) goto L_0x0019
            monitor-exit(r4)     // Catch:{ all -> 0x00b1 }
            goto L_0x0035
        L_0x0019:
            java.util.List<io.grpc.internal.RetriableStream$BufferEntry> r6 = r5.buffer     // Catch:{ all -> 0x00b1 }
            int r6 = r6.size()     // Catch:{ all -> 0x00b1 }
            if (r0 != r6) goto L_0x005a
            io.grpc.internal.RetriableStream$State r0 = r5.substreamDrained(r9)     // Catch:{ all -> 0x00b1 }
            r8.state = r0     // Catch:{ all -> 0x00b1 }
            boolean r0 = r8.isReady()     // Catch:{ all -> 0x00b1 }
            if (r0 != 0) goto L_0x002f
            monitor-exit(r4)     // Catch:{ all -> 0x00b1 }
            return
        L_0x002f:
            io.grpc.internal.RetriableStream$3 r1 = new io.grpc.internal.RetriableStream$3     // Catch:{ all -> 0x00b1 }
            r1.<init>()     // Catch:{ all -> 0x00b1 }
            monitor-exit(r4)     // Catch:{ all -> 0x00b1 }
        L_0x0035:
            if (r1 == 0) goto L_0x003d
            java.util.concurrent.Executor r9 = r8.listenerSerializeExecutor
            r9.execute(r1)
            return
        L_0x003d:
            if (r2 != 0) goto L_0x0049
            io.grpc.internal.ClientStream r0 = r9.stream
            io.grpc.internal.RetriableStream$Sublistener r1 = new io.grpc.internal.RetriableStream$Sublistener
            r1.<init>(r9)
            r0.start(r1)
        L_0x0049:
            io.grpc.internal.ClientStream r0 = r9.stream
            io.grpc.internal.RetriableStream$State r1 = r8.state
            io.grpc.internal.RetriableStream$Substream r1 = r1.winningSubstream
            if (r1 != r9) goto L_0x0054
            io.grpc.Status r9 = r8.cancellationStatus
            goto L_0x0056
        L_0x0054:
            io.grpc.Status r9 = CANCELLED_BECAUSE_COMMITTED
        L_0x0056:
            r0.cancel(r9)
            return
        L_0x005a:
            boolean r6 = r9.closed     // Catch:{ all -> 0x00b1 }
            if (r6 == 0) goto L_0x0060
            monitor-exit(r4)     // Catch:{ all -> 0x00b1 }
            return
        L_0x0060:
            int r6 = r0 + 128
            java.util.List<io.grpc.internal.RetriableStream$BufferEntry> r7 = r5.buffer     // Catch:{ all -> 0x00b1 }
            int r7 = r7.size()     // Catch:{ all -> 0x00b1 }
            int r6 = java.lang.Math.min(r6, r7)     // Catch:{ all -> 0x00b1 }
            if (r3 != 0) goto L_0x007a
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ all -> 0x00b1 }
            java.util.List<io.grpc.internal.RetriableStream$BufferEntry> r5 = r5.buffer     // Catch:{ all -> 0x00b1 }
            java.util.List r0 = r5.subList(r0, r6)     // Catch:{ all -> 0x00b1 }
            r3.<init>(r0)     // Catch:{ all -> 0x00b1 }
            goto L_0x0086
        L_0x007a:
            r3.clear()     // Catch:{ all -> 0x00b1 }
            java.util.List<io.grpc.internal.RetriableStream$BufferEntry> r5 = r5.buffer     // Catch:{ all -> 0x00b1 }
            java.util.List r0 = r5.subList(r0, r6)     // Catch:{ all -> 0x00b1 }
            r3.addAll(r0)     // Catch:{ all -> 0x00b1 }
        L_0x0086:
            monitor-exit(r4)     // Catch:{ all -> 0x00b1 }
            java.util.Iterator r0 = r3.iterator()
        L_0x008b:
            boolean r4 = r0.hasNext()
            if (r4 == 0) goto L_0x00ae
            java.lang.Object r4 = r0.next()
            io.grpc.internal.RetriableStream$BufferEntry r4 = (io.grpc.internal.RetriableStream.BufferEntry) r4
            r4.runWith(r9)
            boolean r4 = r4 instanceof io.grpc.internal.RetriableStream.StartEntry
            if (r4 == 0) goto L_0x009f
            r2 = 1
        L_0x009f:
            io.grpc.internal.RetriableStream$State r4 = r8.state
            io.grpc.internal.RetriableStream$Substream r5 = r4.winningSubstream
            if (r5 == 0) goto L_0x00aa
            io.grpc.internal.RetriableStream$Substream r5 = r4.winningSubstream
            if (r5 == r9) goto L_0x00aa
            goto L_0x00ae
        L_0x00aa:
            boolean r4 = r4.cancelled
            if (r4 == 0) goto L_0x008b
        L_0x00ae:
            r0 = r6
            goto L_0x0004
        L_0x00b1:
            r9 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x00b1 }
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.internal.RetriableStream.drain(io.grpc.internal.RetriableStream$Substream):void");
    }

    class StartEntry implements BufferEntry {
        StartEntry() {
        }

        public void runWith(Substream substream) {
            substream.stream.start(new Sublistener(substream));
        }
    }

    public final void start(ClientStreamListener clientStreamListener) {
        FutureCanceller futureCanceller;
        Throttle throttle2;
        this.masterListener = clientStreamListener;
        Status prestart = prestart();
        if (prestart != null) {
            cancel(prestart);
            return;
        }
        synchronized (this.lock) {
            this.state.buffer.add(new StartEntry());
        }
        Substream createSubstream = createSubstream(0, false);
        if (createSubstream != null) {
            if (this.isHedging) {
                synchronized (this.lock) {
                    this.state = this.state.addActiveHedge(createSubstream);
                    if (!hasPotentialHedging(this.state) || ((throttle2 = this.throttle) != null && !throttle2.isAboveThreshold())) {
                        futureCanceller = null;
                    } else {
                        futureCanceller = new FutureCanceller(this.lock);
                        this.scheduledHedging = futureCanceller;
                    }
                }
                if (futureCanceller != null) {
                    futureCanceller.setFuture(this.scheduledExecutorService.schedule(new HedgingRunnable(futureCanceller), this.hedgingPolicy.hedgingDelayNanos, TimeUnit.NANOSECONDS));
                }
            }
            drain(createSubstream);
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0024, code lost:
        if (r1 == null) goto L_0x002a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0026, code lost:
        r1.cancel(false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002a, code lost:
        r2.setFuture(r5.scheduledExecutorService.schedule(new io.grpc.internal.RetriableStream.HedgingRunnable(r5, r2), (long) r6.intValue(), java.util.concurrent.TimeUnit.MILLISECONDS));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x003f, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void pushbackHedging(@javax.annotation.Nullable java.lang.Integer r6) {
        /*
            r5 = this;
            if (r6 != 0) goto L_0x0003
            return
        L_0x0003:
            int r0 = r6.intValue()
            if (r0 >= 0) goto L_0x000d
            r5.freezeHedging()
            return
        L_0x000d:
            java.lang.Object r0 = r5.lock
            monitor-enter(r0)
            io.grpc.internal.RetriableStream$FutureCanceller r1 = r5.scheduledHedging     // Catch:{ all -> 0x0040 }
            if (r1 != 0) goto L_0x0016
            monitor-exit(r0)     // Catch:{ all -> 0x0040 }
            return
        L_0x0016:
            java.util.concurrent.Future r1 = r1.markCancelled()     // Catch:{ all -> 0x0040 }
            io.grpc.internal.RetriableStream$FutureCanceller r2 = new io.grpc.internal.RetriableStream$FutureCanceller     // Catch:{ all -> 0x0040 }
            java.lang.Object r3 = r5.lock     // Catch:{ all -> 0x0040 }
            r2.<init>(r3)     // Catch:{ all -> 0x0040 }
            r5.scheduledHedging = r2     // Catch:{ all -> 0x0040 }
            monitor-exit(r0)     // Catch:{ all -> 0x0040 }
            if (r1 == 0) goto L_0x002a
            r0 = 0
            r1.cancel(r0)
        L_0x002a:
            java.util.concurrent.ScheduledExecutorService r0 = r5.scheduledExecutorService
            io.grpc.internal.RetriableStream$HedgingRunnable r1 = new io.grpc.internal.RetriableStream$HedgingRunnable
            r1.<init>(r2)
            int r6 = r6.intValue()
            long r3 = (long) r6
            java.util.concurrent.TimeUnit r6 = java.util.concurrent.TimeUnit.MILLISECONDS
            java.util.concurrent.ScheduledFuture r6 = r0.schedule(r1, r3, r6)
            r2.setFuture(r6)
            return
        L_0x0040:
            r6 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0040 }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.internal.RetriableStream.pushbackHedging(java.lang.Integer):void");
    }

    private final class HedgingRunnable implements Runnable {
        final FutureCanceller scheduledHedgingRef;

        HedgingRunnable(FutureCanceller futureCanceller) {
            this.scheduledHedgingRef = futureCanceller;
        }

        public void run() {
            RetriableStream retriableStream = RetriableStream.this;
            final Substream access$400 = retriableStream.createSubstream(retriableStream.state.hedgingAttemptCount, false);
            if (access$400 != null) {
                RetriableStream.this.callExecutor.execute(new Runnable() {
                    public void run() {
                        FutureCanceller futureCanceller;
                        boolean z;
                        synchronized (RetriableStream.this.lock) {
                            futureCanceller = null;
                            if (HedgingRunnable.this.scheduledHedgingRef.isCancelled()) {
                                z = true;
                            } else {
                                State unused = RetriableStream.this.state = RetriableStream.this.state.addActiveHedge(access$400);
                                if (!RetriableStream.this.hasPotentialHedging(RetriableStream.this.state) || (RetriableStream.this.throttle != null && !RetriableStream.this.throttle.isAboveThreshold())) {
                                    State unused2 = RetriableStream.this.state = RetriableStream.this.state.freezeHedging();
                                    FutureCanceller unused3 = RetriableStream.this.scheduledHedging = null;
                                } else {
                                    RetriableStream retriableStream = RetriableStream.this;
                                    futureCanceller = new FutureCanceller(RetriableStream.this.lock);
                                    FutureCanceller unused4 = retriableStream.scheduledHedging = futureCanceller;
                                }
                                z = false;
                            }
                        }
                        if (z) {
                            access$400.stream.start(new Sublistener(access$400));
                            access$400.stream.cancel(Status.CANCELLED.withDescription("Unneeded hedging"));
                            return;
                        }
                        if (futureCanceller != null) {
                            futureCanceller.setFuture(RetriableStream.this.scheduledExecutorService.schedule(new HedgingRunnable(futureCanceller), RetriableStream.this.hedgingPolicy.hedgingDelayNanos, TimeUnit.NANOSECONDS));
                        }
                        RetriableStream.this.drain(access$400);
                    }
                });
            }
        }
    }

    public final void cancel(Status status) {
        Substream substream;
        Substream substream2 = new Substream(0);
        substream2.stream = new NoopClientStream();
        Runnable commit = commit(substream2);
        if (commit != null) {
            synchronized (this.lock) {
                this.state = this.state.substreamDrained(substream2);
            }
            commit.run();
            safeCloseMasterListener(status, ClientStreamListener.RpcProgress.PROCESSED, new Metadata());
            return;
        }
        synchronized (this.lock) {
            if (this.state.drainedSubstreams.contains(this.state.winningSubstream)) {
                substream = this.state.winningSubstream;
            } else {
                this.cancellationStatus = status;
                substream = null;
            }
            this.state = this.state.cancelled();
        }
        if (substream != null) {
            substream.stream.cancel(status);
        }
    }

    private void delayOrExecute(BufferEntry bufferEntry) {
        Collection<Substream> collection;
        synchronized (this.lock) {
            if (!this.state.passThrough) {
                this.state.buffer.add(bufferEntry);
            }
            collection = this.state.drainedSubstreams;
        }
        for (Substream runWith : collection) {
            bufferEntry.runWith(runWith);
        }
    }

    public final void writeMessage(InputStream inputStream) {
        throw new IllegalStateException("RetriableStream.writeMessage() should not be called directly");
    }

    /* access modifiers changed from: package-private */
    public final void sendMessage(final ReqT reqt) {
        State state2 = this.state;
        if (state2.passThrough) {
            state2.winningSubstream.stream.writeMessage(this.method.streamRequest(reqt));
        } else {
            delayOrExecute(new BufferEntry() {
                public void runWith(Substream substream) {
                    substream.stream.writeMessage(RetriableStream.this.method.streamRequest(reqt));
                    substream.stream.flush();
                }
            });
        }
    }

    public final void request(final int i) {
        State state2 = this.state;
        if (state2.passThrough) {
            state2.winningSubstream.stream.request(i);
        } else {
            delayOrExecute(new BufferEntry() {
                public void runWith(Substream substream) {
                    substream.stream.request(i);
                }
            });
        }
    }

    public final void flush() {
        State state2 = this.state;
        if (state2.passThrough) {
            state2.winningSubstream.stream.flush();
        } else {
            delayOrExecute(new BufferEntry() {
                public void runWith(Substream substream) {
                    substream.stream.flush();
                }
            });
        }
    }

    public final boolean isReady() {
        for (Substream substream : this.state.drainedSubstreams) {
            if (substream.stream.isReady()) {
                return true;
            }
        }
        return false;
    }

    public void optimizeForDirectExecutor() {
        delayOrExecute(new BufferEntry() {
            public void runWith(Substream substream) {
                substream.stream.optimizeForDirectExecutor();
            }
        });
    }

    public final void setCompressor(final Compressor compressor) {
        delayOrExecute(new BufferEntry() {
            public void runWith(Substream substream) {
                substream.stream.setCompressor(compressor);
            }
        });
    }

    public final void setFullStreamDecompression(final boolean z) {
        delayOrExecute(new BufferEntry() {
            public void runWith(Substream substream) {
                substream.stream.setFullStreamDecompression(z);
            }
        });
    }

    public final void setMessageCompression(final boolean z) {
        delayOrExecute(new BufferEntry() {
            public void runWith(Substream substream) {
                substream.stream.setMessageCompression(z);
            }
        });
    }

    public final void halfClose() {
        delayOrExecute(new BufferEntry() {
            public void runWith(Substream substream) {
                substream.stream.halfClose();
            }
        });
    }

    public final void setAuthority(final String str) {
        delayOrExecute(new BufferEntry() {
            public void runWith(Substream substream) {
                substream.stream.setAuthority(str);
            }
        });
    }

    public final void setDecompressorRegistry(final DecompressorRegistry decompressorRegistry) {
        delayOrExecute(new BufferEntry() {
            public void runWith(Substream substream) {
                substream.stream.setDecompressorRegistry(decompressorRegistry);
            }
        });
    }

    public final void setMaxInboundMessageSize(final int i) {
        delayOrExecute(new BufferEntry() {
            public void runWith(Substream substream) {
                substream.stream.setMaxInboundMessageSize(i);
            }
        });
    }

    public final void setMaxOutboundMessageSize(final int i) {
        delayOrExecute(new BufferEntry() {
            public void runWith(Substream substream) {
                substream.stream.setMaxOutboundMessageSize(i);
            }
        });
    }

    public final void setDeadline(final Deadline deadline) {
        delayOrExecute(new BufferEntry() {
            public void runWith(Substream substream) {
                substream.stream.setDeadline(deadline);
            }
        });
    }

    public final Attributes getAttributes() {
        if (this.state.winningSubstream != null) {
            return this.state.winningSubstream.stream.getAttributes();
        }
        return Attributes.EMPTY;
    }

    public void appendTimeoutInsight(InsightBuilder insightBuilder) {
        State state2;
        synchronized (this.lock) {
            insightBuilder.appendKeyValue("closed", this.closedSubstreamsInsight);
            state2 = this.state;
        }
        if (state2.winningSubstream != null) {
            InsightBuilder insightBuilder2 = new InsightBuilder();
            state2.winningSubstream.stream.appendTimeoutInsight(insightBuilder2);
            insightBuilder.appendKeyValue("committed", insightBuilder2);
            return;
        }
        InsightBuilder insightBuilder3 = new InsightBuilder();
        for (Substream substream : state2.drainedSubstreams) {
            InsightBuilder insightBuilder4 = new InsightBuilder();
            substream.stream.appendTimeoutInsight(insightBuilder4);
            insightBuilder3.append(insightBuilder4);
        }
        insightBuilder.appendKeyValue(TtmlNode.TEXT_EMPHASIS_MARK_OPEN, insightBuilder3);
    }

    static void setRandom(Random random2) {
        random = random2;
    }

    /* access modifiers changed from: private */
    public boolean hasPotentialHedging(State state2) {
        return state2.winningSubstream == null && state2.hedgingAttemptCount < this.hedgingPolicy.maxAttempts && !state2.hedgingFrozen;
    }

    /* access modifiers changed from: private */
    public void freezeHedging() {
        Future<?> future;
        synchronized (this.lock) {
            FutureCanceller futureCanceller = this.scheduledHedging;
            future = null;
            if (futureCanceller != null) {
                Future<?> markCancelled = futureCanceller.markCancelled();
                this.scheduledHedging = null;
                future = markCancelled;
            }
            this.state = this.state.freezeHedging();
        }
        if (future != null) {
            future.cancel(false);
        }
    }

    /* access modifiers changed from: private */
    public void safeCloseMasterListener(final Status status, final ClientStreamListener.RpcProgress rpcProgress, final Metadata metadata) {
        this.savedCloseMasterListenerReason = new SavedCloseMasterListenerReason(status, rpcProgress, metadata);
        if (this.inFlightSubStreams.addAndGet(Integer.MIN_VALUE) == Integer.MIN_VALUE) {
            this.listenerSerializeExecutor.execute(new Runnable() {
                public void run() {
                    boolean unused = RetriableStream.this.isClosed = true;
                    RetriableStream.this.masterListener.closed(status, rpcProgress, metadata);
                }
            });
        }
    }

    private static final class SavedCloseMasterListenerReason {
        /* access modifiers changed from: private */
        public final Metadata metadata;
        /* access modifiers changed from: private */
        public final ClientStreamListener.RpcProgress progress;
        /* access modifiers changed from: private */
        public final Status status;

        SavedCloseMasterListenerReason(Status status2, ClientStreamListener.RpcProgress rpcProgress, Metadata metadata2) {
            this.status = status2;
            this.progress = rpcProgress;
            this.metadata = metadata2;
        }
    }

    private final class Sublistener implements ClientStreamListener {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        final Substream substream;

        static {
            Class<RetriableStream> cls = RetriableStream.class;
        }

        Sublistener(Substream substream2) {
            this.substream = substream2;
        }

        public void headersRead(final Metadata metadata) {
            if (this.substream.previousAttemptCount > 0) {
                metadata.discardAll(RetriableStream.GRPC_PREVIOUS_RPC_ATTEMPTS);
                metadata.put(RetriableStream.GRPC_PREVIOUS_RPC_ATTEMPTS, String.valueOf(this.substream.previousAttemptCount));
            }
            RetriableStream.this.commitAndRun(this.substream);
            if (RetriableStream.this.state.winningSubstream == this.substream) {
                if (RetriableStream.this.throttle != null) {
                    RetriableStream.this.throttle.onSuccess();
                }
                RetriableStream.this.listenerSerializeExecutor.execute(new Runnable() {
                    public void run() {
                        RetriableStream.this.masterListener.headersRead(metadata);
                    }
                });
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:49:0x0125, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void closed(io.grpc.Status r6, io.grpc.internal.ClientStreamListener.RpcProgress r7, io.grpc.Metadata r8) {
            /*
                r5 = this;
                io.grpc.internal.RetriableStream r0 = io.grpc.internal.RetriableStream.this
                java.lang.Object r0 = r0.lock
                monitor-enter(r0)
                io.grpc.internal.RetriableStream r1 = io.grpc.internal.RetriableStream.this     // Catch:{ all -> 0x01cc }
                io.grpc.internal.RetriableStream$State r2 = r1.state     // Catch:{ all -> 0x01cc }
                io.grpc.internal.RetriableStream$Substream r3 = r5.substream     // Catch:{ all -> 0x01cc }
                io.grpc.internal.RetriableStream$State r2 = r2.substreamClosed(r3)     // Catch:{ all -> 0x01cc }
                io.grpc.internal.RetriableStream.State unused = r1.state = r2     // Catch:{ all -> 0x01cc }
                io.grpc.internal.RetriableStream r1 = io.grpc.internal.RetriableStream.this     // Catch:{ all -> 0x01cc }
                io.grpc.internal.InsightBuilder r1 = r1.closedSubstreamsInsight     // Catch:{ all -> 0x01cc }
                io.grpc.Status$Code r2 = r6.getCode()     // Catch:{ all -> 0x01cc }
                r1.append(r2)     // Catch:{ all -> 0x01cc }
                monitor-exit(r0)     // Catch:{ all -> 0x01cc }
                io.grpc.internal.RetriableStream r0 = io.grpc.internal.RetriableStream.this
                java.util.concurrent.atomic.AtomicInteger r0 = r0.inFlightSubStreams
                int r0 = r0.decrementAndGet()
                r1 = -2147483648(0xffffffff80000000, float:-0.0)
                if (r0 != r1) goto L_0x0041
                io.grpc.internal.RetriableStream r6 = io.grpc.internal.RetriableStream.this
                java.util.concurrent.Executor r6 = r6.listenerSerializeExecutor
                io.grpc.internal.RetriableStream$Sublistener$2 r7 = new io.grpc.internal.RetriableStream$Sublistener$2
                r7.<init>()
                r6.execute(r7)
                return
            L_0x0041:
                io.grpc.internal.RetriableStream$Substream r0 = r5.substream
                boolean r0 = r0.bufferLimitExceeded
                if (r0 == 0) goto L_0x0060
                io.grpc.internal.RetriableStream r0 = io.grpc.internal.RetriableStream.this
                io.grpc.internal.RetriableStream$Substream r1 = r5.substream
                r0.commitAndRun(r1)
                io.grpc.internal.RetriableStream r0 = io.grpc.internal.RetriableStream.this
                io.grpc.internal.RetriableStream$State r0 = r0.state
                io.grpc.internal.RetriableStream$Substream r0 = r0.winningSubstream
                io.grpc.internal.RetriableStream$Substream r1 = r5.substream
                if (r0 != r1) goto L_0x005f
                io.grpc.internal.RetriableStream r0 = io.grpc.internal.RetriableStream.this
                r0.safeCloseMasterListener(r6, r7, r8)
            L_0x005f:
                return
            L_0x0060:
                io.grpc.internal.ClientStreamListener$RpcProgress r0 = io.grpc.internal.ClientStreamListener.RpcProgress.MISCARRIED
                if (r7 != r0) goto L_0x009b
                io.grpc.internal.RetriableStream r0 = io.grpc.internal.RetriableStream.this
                java.util.concurrent.atomic.AtomicInteger r0 = r0.localOnlyTransparentRetries
                int r0 = r0.incrementAndGet()
                r1 = 1000(0x3e8, float:1.401E-42)
                if (r0 <= r1) goto L_0x009b
                io.grpc.internal.RetriableStream r0 = io.grpc.internal.RetriableStream.this
                io.grpc.internal.RetriableStream$Substream r1 = r5.substream
                r0.commitAndRun(r1)
                io.grpc.internal.RetriableStream r0 = io.grpc.internal.RetriableStream.this
                io.grpc.internal.RetriableStream$State r0 = r0.state
                io.grpc.internal.RetriableStream$Substream r0 = r0.winningSubstream
                io.grpc.internal.RetriableStream$Substream r1 = r5.substream
                if (r0 != r1) goto L_0x009a
                io.grpc.Status r0 = io.grpc.Status.INTERNAL
                java.lang.String r1 = "Too many transparent retries. Might be a bug in gRPC"
                io.grpc.Status r0 = r0.withDescription(r1)
                io.grpc.StatusRuntimeException r6 = r6.asRuntimeException()
                io.grpc.Status r6 = r0.withCause(r6)
                io.grpc.internal.RetriableStream r0 = io.grpc.internal.RetriableStream.this
                r0.safeCloseMasterListener(r6, r7, r8)
            L_0x009a:
                return
            L_0x009b:
                io.grpc.internal.RetriableStream r0 = io.grpc.internal.RetriableStream.this
                io.grpc.internal.RetriableStream$State r0 = r0.state
                io.grpc.internal.RetriableStream$Substream r0 = r0.winningSubstream
                if (r0 != 0) goto L_0x01b3
                io.grpc.internal.ClientStreamListener$RpcProgress r0 = io.grpc.internal.ClientStreamListener.RpcProgress.MISCARRIED
                r1 = 1
                if (r7 == r0) goto L_0x0174
                io.grpc.internal.ClientStreamListener$RpcProgress r0 = io.grpc.internal.ClientStreamListener.RpcProgress.REFUSED
                r2 = 0
                if (r7 != r0) goto L_0x00bd
                io.grpc.internal.RetriableStream r0 = io.grpc.internal.RetriableStream.this
                java.util.concurrent.atomic.AtomicBoolean r0 = r0.noMoreTransparentRetry
                boolean r0 = r0.compareAndSet(r2, r1)
                if (r0 == 0) goto L_0x00bd
                goto L_0x0174
            L_0x00bd:
                io.grpc.internal.ClientStreamListener$RpcProgress r0 = io.grpc.internal.ClientStreamListener.RpcProgress.DROPPED
                if (r7 != r0) goto L_0x00d0
                io.grpc.internal.RetriableStream r0 = io.grpc.internal.RetriableStream.this
                boolean r0 = r0.isHedging
                if (r0 == 0) goto L_0x01b3
                io.grpc.internal.RetriableStream r0 = io.grpc.internal.RetriableStream.this
                r0.freezeHedging()
                goto L_0x01b3
            L_0x00d0:
                io.grpc.internal.RetriableStream r0 = io.grpc.internal.RetriableStream.this
                java.util.concurrent.atomic.AtomicBoolean r0 = r0.noMoreTransparentRetry
                r0.set(r1)
                io.grpc.internal.RetriableStream r0 = io.grpc.internal.RetriableStream.this
                boolean r0 = r0.isHedging
                if (r0 == 0) goto L_0x012c
                io.grpc.internal.RetriableStream$HedgingPlan r0 = r5.makeHedgingDecision(r6, r8)
                boolean r1 = r0.isHedgeable
                if (r1 == 0) goto L_0x00f0
                io.grpc.internal.RetriableStream r1 = io.grpc.internal.RetriableStream.this
                java.lang.Integer r2 = r0.hedgingPushbackMillis
                r1.pushbackHedging(r2)
            L_0x00f0:
                io.grpc.internal.RetriableStream r1 = io.grpc.internal.RetriableStream.this
                java.lang.Object r3 = r1.lock
                monitor-enter(r3)
                io.grpc.internal.RetriableStream r1 = io.grpc.internal.RetriableStream.this     // Catch:{ all -> 0x0129 }
                io.grpc.internal.RetriableStream$State r2 = r1.state     // Catch:{ all -> 0x0129 }
                io.grpc.internal.RetriableStream$Substream r4 = r5.substream     // Catch:{ all -> 0x0129 }
                io.grpc.internal.RetriableStream$State r2 = r2.removeActiveHedge(r4)     // Catch:{ all -> 0x0129 }
                io.grpc.internal.RetriableStream.State unused = r1.state = r2     // Catch:{ all -> 0x0129 }
                boolean r0 = r0.isHedgeable     // Catch:{ all -> 0x0129 }
                if (r0 == 0) goto L_0x0126
                io.grpc.internal.RetriableStream r0 = io.grpc.internal.RetriableStream.this     // Catch:{ all -> 0x0129 }
                io.grpc.internal.RetriableStream$State r1 = r0.state     // Catch:{ all -> 0x0129 }
                boolean r0 = r0.hasPotentialHedging(r1)     // Catch:{ all -> 0x0129 }
                if (r0 != 0) goto L_0x0124
                io.grpc.internal.RetriableStream r0 = io.grpc.internal.RetriableStream.this     // Catch:{ all -> 0x0129 }
                io.grpc.internal.RetriableStream$State r0 = r0.state     // Catch:{ all -> 0x0129 }
                java.util.Collection<io.grpc.internal.RetriableStream$Substream> r0 = r0.activeHedges     // Catch:{ all -> 0x0129 }
                boolean r0 = r0.isEmpty()     // Catch:{ all -> 0x0129 }
                if (r0 != 0) goto L_0x0126
            L_0x0124:
                monitor-exit(r3)     // Catch:{ all -> 0x0129 }
                return
            L_0x0126:
                monitor-exit(r3)     // Catch:{ all -> 0x0129 }
                goto L_0x01b3
            L_0x0129:
                r6 = move-exception
                monitor-exit(r3)     // Catch:{ all -> 0x0129 }
                throw r6
            L_0x012c:
                io.grpc.internal.RetriableStream$RetryPlan r0 = r5.makeRetryDecision(r6, r8)
                boolean r3 = r0.shouldRetry
                if (r3 == 0) goto L_0x01b3
                io.grpc.internal.RetriableStream r6 = io.grpc.internal.RetriableStream.this
                io.grpc.internal.RetriableStream$Substream r7 = r5.substream
                int r7 = r7.previousAttemptCount
                int r7 = r7 + r1
                io.grpc.internal.RetriableStream$Substream r6 = r6.createSubstream(r7, r2)
                if (r6 != 0) goto L_0x0142
                return
            L_0x0142:
                io.grpc.internal.RetriableStream r7 = io.grpc.internal.RetriableStream.this
                java.lang.Object r1 = r7.lock
                monitor-enter(r1)
                io.grpc.internal.RetriableStream r7 = io.grpc.internal.RetriableStream.this     // Catch:{ all -> 0x0171 }
                io.grpc.internal.RetriableStream$FutureCanceller r8 = new io.grpc.internal.RetriableStream$FutureCanceller     // Catch:{ all -> 0x0171 }
                io.grpc.internal.RetriableStream r2 = io.grpc.internal.RetriableStream.this     // Catch:{ all -> 0x0171 }
                java.lang.Object r2 = r2.lock     // Catch:{ all -> 0x0171 }
                r8.<init>(r2)     // Catch:{ all -> 0x0171 }
                io.grpc.internal.RetriableStream.FutureCanceller unused = r7.scheduledRetry = r8     // Catch:{ all -> 0x0171 }
                monitor-exit(r1)     // Catch:{ all -> 0x0171 }
                io.grpc.internal.RetriableStream r7 = io.grpc.internal.RetriableStream.this
                java.util.concurrent.ScheduledExecutorService r7 = r7.scheduledExecutorService
                io.grpc.internal.RetriableStream$Sublistener$1RetryBackoffRunnable r1 = new io.grpc.internal.RetriableStream$Sublistener$1RetryBackoffRunnable
                r1.<init>(r6)
                long r2 = r0.backoffNanos
                java.util.concurrent.TimeUnit r6 = java.util.concurrent.TimeUnit.NANOSECONDS
                java.util.concurrent.ScheduledFuture r6 = r7.schedule(r1, r2, r6)
                r8.setFuture(r6)
                return
            L_0x0171:
                r6 = move-exception
                monitor-exit(r1)     // Catch:{ all -> 0x0171 }
                throw r6
            L_0x0174:
                io.grpc.internal.RetriableStream r6 = io.grpc.internal.RetriableStream.this
                io.grpc.internal.RetriableStream$Substream r7 = r5.substream
                int r7 = r7.previousAttemptCount
                io.grpc.internal.RetriableStream$Substream r6 = r6.createSubstream(r7, r1)
                if (r6 != 0) goto L_0x0181
                return
            L_0x0181:
                io.grpc.internal.RetriableStream r7 = io.grpc.internal.RetriableStream.this
                boolean r7 = r7.isHedging
                if (r7 == 0) goto L_0x01a4
                io.grpc.internal.RetriableStream r7 = io.grpc.internal.RetriableStream.this
                java.lang.Object r7 = r7.lock
                monitor-enter(r7)
                io.grpc.internal.RetriableStream r8 = io.grpc.internal.RetriableStream.this     // Catch:{ all -> 0x01a1 }
                io.grpc.internal.RetriableStream$State r0 = r8.state     // Catch:{ all -> 0x01a1 }
                io.grpc.internal.RetriableStream$Substream r1 = r5.substream     // Catch:{ all -> 0x01a1 }
                io.grpc.internal.RetriableStream$State r0 = r0.replaceActiveHedge(r1, r6)     // Catch:{ all -> 0x01a1 }
                io.grpc.internal.RetriableStream.State unused = r8.state = r0     // Catch:{ all -> 0x01a1 }
                monitor-exit(r7)     // Catch:{ all -> 0x01a1 }
                goto L_0x01a4
            L_0x01a1:
                r6 = move-exception
                monitor-exit(r7)     // Catch:{ all -> 0x01a1 }
                throw r6
            L_0x01a4:
                io.grpc.internal.RetriableStream r7 = io.grpc.internal.RetriableStream.this
                java.util.concurrent.Executor r7 = r7.callExecutor
                io.grpc.internal.RetriableStream$Sublistener$3 r8 = new io.grpc.internal.RetriableStream$Sublistener$3
                r8.<init>(r6)
                r7.execute(r8)
                return
            L_0x01b3:
                io.grpc.internal.RetriableStream r0 = io.grpc.internal.RetriableStream.this
                io.grpc.internal.RetriableStream$Substream r1 = r5.substream
                r0.commitAndRun(r1)
                io.grpc.internal.RetriableStream r0 = io.grpc.internal.RetriableStream.this
                io.grpc.internal.RetriableStream$State r0 = r0.state
                io.grpc.internal.RetriableStream$Substream r0 = r0.winningSubstream
                io.grpc.internal.RetriableStream$Substream r1 = r5.substream
                if (r0 != r1) goto L_0x01cb
                io.grpc.internal.RetriableStream r0 = io.grpc.internal.RetriableStream.this
                r0.safeCloseMasterListener(r6, r7, r8)
            L_0x01cb:
                return
            L_0x01cc:
                r6 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x01cc }
                throw r6
            */
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.internal.RetriableStream.Sublistener.closed(io.grpc.Status, io.grpc.internal.ClientStreamListener$RpcProgress, io.grpc.Metadata):void");
        }

        private RetryPlan makeRetryDecision(Status status, Metadata metadata) {
            long j = 0;
            boolean z = false;
            if (RetriableStream.this.retryPolicy == null) {
                return new RetryPlan(false, 0);
            }
            boolean contains = RetriableStream.this.retryPolicy.retryableStatusCodes.contains(status.getCode());
            Integer pushbackMills = getPushbackMills(metadata);
            boolean z2 = (RetriableStream.this.throttle == null || (!contains && (pushbackMills == null || pushbackMills.intValue() >= 0))) ? false : !RetriableStream.this.throttle.onQualifiedFailureThenCheckIsAboveThreshold();
            if (RetriableStream.this.retryPolicy.maxAttempts > this.substream.previousAttemptCount + 1 && !z2) {
                if (pushbackMills == null) {
                    if (contains) {
                        j = (long) (((double) RetriableStream.this.nextBackoffIntervalNanos) * RetriableStream.random.nextDouble());
                        RetriableStream retriableStream = RetriableStream.this;
                        long unused = retriableStream.nextBackoffIntervalNanos = Math.min((long) (((double) retriableStream.nextBackoffIntervalNanos) * RetriableStream.this.retryPolicy.backoffMultiplier), RetriableStream.this.retryPolicy.maxBackoffNanos);
                    }
                } else if (pushbackMills.intValue() >= 0) {
                    j = TimeUnit.MILLISECONDS.toNanos((long) pushbackMills.intValue());
                    RetriableStream retriableStream2 = RetriableStream.this;
                    long unused2 = retriableStream2.nextBackoffIntervalNanos = retriableStream2.retryPolicy.initialBackoffNanos;
                }
                z = true;
            }
            return new RetryPlan(z, j);
        }

        private HedgingPlan makeHedgingDecision(Status status, Metadata metadata) {
            Integer pushbackMills = getPushbackMills(metadata);
            boolean z = true;
            boolean z2 = !RetriableStream.this.hedgingPolicy.nonFatalStatusCodes.contains(status.getCode());
            boolean z3 = (RetriableStream.this.throttle == null || (z2 && (pushbackMills == null || pushbackMills.intValue() >= 0))) ? false : !RetriableStream.this.throttle.onQualifiedFailureThenCheckIsAboveThreshold();
            if (!z2 && !z3 && !status.isOk() && pushbackMills != null && pushbackMills.intValue() > 0) {
                pushbackMills = 0;
            }
            if (z2 || z3) {
                z = false;
            }
            return new HedgingPlan(z, pushbackMills);
        }

        @Nullable
        private Integer getPushbackMills(Metadata metadata) {
            String str = (String) metadata.get(RetriableStream.GRPC_RETRY_PUSHBACK_MS);
            if (str == null) {
                return null;
            }
            try {
                return Integer.valueOf(str);
            } catch (NumberFormatException unused) {
                return -1;
            }
        }

        public void messagesAvailable(final StreamListener.MessageProducer messageProducer) {
            State access$300 = RetriableStream.this.state;
            Preconditions.checkState(access$300.winningSubstream != null, "Headers should be received prior to messages.");
            if (access$300.winningSubstream != this.substream) {
                GrpcUtil.closeQuietly(messageProducer);
            } else {
                RetriableStream.this.listenerSerializeExecutor.execute(new Runnable() {
                    public void run() {
                        RetriableStream.this.masterListener.messagesAvailable(messageProducer);
                    }
                });
            }
        }

        public void onReady() {
            if (RetriableStream.this.isReady()) {
                RetriableStream.this.listenerSerializeExecutor.execute(new Runnable() {
                    public void run() {
                        if (!RetriableStream.this.isClosed) {
                            RetriableStream.this.masterListener.onReady();
                        }
                    }
                });
            }
        }
    }

    private static final class State {
        final Collection<Substream> activeHedges;
        @Nullable
        final List<BufferEntry> buffer;
        final boolean cancelled;
        final Collection<Substream> drainedSubstreams;
        final int hedgingAttemptCount;
        final boolean hedgingFrozen;
        final boolean passThrough;
        @Nullable
        final Substream winningSubstream;

        State(@Nullable List<BufferEntry> list, Collection<Substream> collection, Collection<Substream> collection2, @Nullable Substream substream, boolean z, boolean z2, boolean z3, int i) {
            this.buffer = list;
            this.drainedSubstreams = (Collection) Preconditions.checkNotNull(collection, "drainedSubstreams");
            this.winningSubstream = substream;
            this.activeHedges = collection2;
            this.cancelled = z;
            this.passThrough = z2;
            this.hedgingFrozen = z3;
            this.hedgingAttemptCount = i;
            boolean z4 = false;
            Preconditions.checkState(!z2 || list == null, "passThrough should imply buffer is null");
            Preconditions.checkState(!z2 || substream != null, "passThrough should imply winningSubstream != null");
            Preconditions.checkState(!z2 || (collection.size() == 1 && collection.contains(substream)) || (collection.size() == 0 && substream.closed), "passThrough should imply winningSubstream is drained");
            Preconditions.checkState((!z || substream != null) ? true : z4, "cancelled should imply committed");
        }

        /* access modifiers changed from: package-private */
        @CheckReturnValue
        public State cancelled() {
            return new State(this.buffer, this.drainedSubstreams, this.activeHedges, this.winningSubstream, true, this.passThrough, this.hedgingFrozen, this.hedgingAttemptCount);
        }

        /* access modifiers changed from: package-private */
        @CheckReturnValue
        public State substreamDrained(Substream substream) {
            Collection unmodifiableCollection;
            boolean z = true;
            Preconditions.checkState(!this.passThrough, "Already passThrough");
            if (substream.closed) {
                unmodifiableCollection = this.drainedSubstreams;
            } else if (this.drainedSubstreams.isEmpty()) {
                unmodifiableCollection = Collections.singletonList(substream);
            } else {
                ArrayList arrayList = new ArrayList(this.drainedSubstreams);
                arrayList.add(substream);
                unmodifiableCollection = Collections.unmodifiableCollection(arrayList);
            }
            Collection collection = unmodifiableCollection;
            Substream substream2 = this.winningSubstream;
            boolean z2 = substream2 != null;
            List<BufferEntry> list = this.buffer;
            if (z2) {
                if (substream2 != substream) {
                    z = false;
                }
                Preconditions.checkState(z, "Another RPC attempt has already committed");
                list = null;
            }
            return new State(list, collection, this.activeHedges, this.winningSubstream, this.cancelled, z2, this.hedgingFrozen, this.hedgingAttemptCount);
        }

        /* access modifiers changed from: package-private */
        @CheckReturnValue
        public State substreamClosed(Substream substream) {
            substream.closed = true;
            if (!this.drainedSubstreams.contains(substream)) {
                return this;
            }
            ArrayList arrayList = new ArrayList(this.drainedSubstreams);
            arrayList.remove(substream);
            return new State(this.buffer, Collections.unmodifiableCollection(arrayList), this.activeHedges, this.winningSubstream, this.cancelled, this.passThrough, this.hedgingFrozen, this.hedgingAttemptCount);
        }

        /* access modifiers changed from: package-private */
        @CheckReturnValue
        public State committed(Substream substream) {
            boolean z;
            Set set;
            List<BufferEntry> list;
            Preconditions.checkState(this.winningSubstream == null, "Already committed");
            List<BufferEntry> list2 = this.buffer;
            if (this.drainedSubstreams.contains(substream)) {
                set = Collections.singleton(substream);
                z = true;
                list = null;
            } else {
                list = list2;
                set = Collections.emptyList();
                z = false;
            }
            return new State(list, set, this.activeHedges, substream, this.cancelled, z, this.hedgingFrozen, this.hedgingAttemptCount);
        }

        /* access modifiers changed from: package-private */
        @CheckReturnValue
        public State freezeHedging() {
            if (this.hedgingFrozen) {
                return this;
            }
            return new State(this.buffer, this.drainedSubstreams, this.activeHedges, this.winningSubstream, this.cancelled, this.passThrough, true, this.hedgingAttemptCount);
        }

        /* access modifiers changed from: package-private */
        @CheckReturnValue
        public State addActiveHedge(Substream substream) {
            Collection collection;
            Preconditions.checkState(!this.hedgingFrozen, "hedging frozen");
            Preconditions.checkState(this.winningSubstream == null, "already committed");
            if (this.activeHedges == null) {
                collection = Collections.singleton(substream);
            } else {
                ArrayList arrayList = new ArrayList(this.activeHedges);
                arrayList.add(substream);
                collection = Collections.unmodifiableCollection(arrayList);
            }
            return new State(this.buffer, this.drainedSubstreams, collection, this.winningSubstream, this.cancelled, this.passThrough, this.hedgingFrozen, this.hedgingAttemptCount + 1);
        }

        /* access modifiers changed from: package-private */
        @CheckReturnValue
        public State removeActiveHedge(Substream substream) {
            ArrayList arrayList = new ArrayList(this.activeHedges);
            arrayList.remove(substream);
            return new State(this.buffer, this.drainedSubstreams, Collections.unmodifiableCollection(arrayList), this.winningSubstream, this.cancelled, this.passThrough, this.hedgingFrozen, this.hedgingAttemptCount);
        }

        /* access modifiers changed from: package-private */
        @CheckReturnValue
        public State replaceActiveHedge(Substream substream, Substream substream2) {
            ArrayList arrayList = new ArrayList(this.activeHedges);
            arrayList.remove(substream);
            arrayList.add(substream2);
            return new State(this.buffer, this.drainedSubstreams, Collections.unmodifiableCollection(arrayList), this.winningSubstream, this.cancelled, this.passThrough, this.hedgingFrozen, this.hedgingAttemptCount);
        }
    }

    private static final class Substream {
        boolean bufferLimitExceeded;
        boolean closed;
        final int previousAttemptCount;
        ClientStream stream;

        Substream(int i) {
            this.previousAttemptCount = i;
        }
    }

    class BufferSizeTracer extends ClientStreamTracer {
        long bufferNeeded;
        private final Substream substream;

        BufferSizeTracer(Substream substream2) {
            this.substream = substream2;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:26:0x007f, code lost:
            if (r6 == null) goto L_?;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:27:0x0081, code lost:
            r6.run();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:34:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:35:?, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void outboundWireSize(long r6) {
            /*
                r5 = this;
                io.grpc.internal.RetriableStream r0 = io.grpc.internal.RetriableStream.this
                io.grpc.internal.RetriableStream$State r0 = r0.state
                io.grpc.internal.RetriableStream$Substream r0 = r0.winningSubstream
                if (r0 == 0) goto L_0x000b
                return
            L_0x000b:
                io.grpc.internal.RetriableStream r0 = io.grpc.internal.RetriableStream.this
                java.lang.Object r0 = r0.lock
                monitor-enter(r0)
                io.grpc.internal.RetriableStream r1 = io.grpc.internal.RetriableStream.this     // Catch:{ all -> 0x0087 }
                io.grpc.internal.RetriableStream$State r1 = r1.state     // Catch:{ all -> 0x0087 }
                io.grpc.internal.RetriableStream$Substream r1 = r1.winningSubstream     // Catch:{ all -> 0x0087 }
                if (r1 != 0) goto L_0x0085
                io.grpc.internal.RetriableStream$Substream r1 = r5.substream     // Catch:{ all -> 0x0087 }
                boolean r1 = r1.closed     // Catch:{ all -> 0x0087 }
                if (r1 == 0) goto L_0x0023
                goto L_0x0085
            L_0x0023:
                long r1 = r5.bufferNeeded     // Catch:{ all -> 0x0087 }
                long r1 = r1 + r6
                r5.bufferNeeded = r1     // Catch:{ all -> 0x0087 }
                io.grpc.internal.RetriableStream r6 = io.grpc.internal.RetriableStream.this     // Catch:{ all -> 0x0087 }
                long r6 = r6.perRpcBufferUsed     // Catch:{ all -> 0x0087 }
                int r6 = (r1 > r6 ? 1 : (r1 == r6 ? 0 : -1))
                if (r6 > 0) goto L_0x0034
                monitor-exit(r0)     // Catch:{ all -> 0x0087 }
                return
            L_0x0034:
                long r6 = r5.bufferNeeded     // Catch:{ all -> 0x0087 }
                io.grpc.internal.RetriableStream r1 = io.grpc.internal.RetriableStream.this     // Catch:{ all -> 0x0087 }
                long r1 = r1.perRpcBufferLimit     // Catch:{ all -> 0x0087 }
                int r6 = (r6 > r1 ? 1 : (r6 == r1 ? 0 : -1))
                r7 = 1
                if (r6 <= 0) goto L_0x0046
                io.grpc.internal.RetriableStream$Substream r6 = r5.substream     // Catch:{ all -> 0x0087 }
                r6.bufferLimitExceeded = r7     // Catch:{ all -> 0x0087 }
                goto L_0x006e
            L_0x0046:
                io.grpc.internal.RetriableStream r6 = io.grpc.internal.RetriableStream.this     // Catch:{ all -> 0x0087 }
                io.grpc.internal.RetriableStream$ChannelBufferMeter r6 = r6.channelBufferUsed     // Catch:{ all -> 0x0087 }
                long r1 = r5.bufferNeeded     // Catch:{ all -> 0x0087 }
                io.grpc.internal.RetriableStream r3 = io.grpc.internal.RetriableStream.this     // Catch:{ all -> 0x0087 }
                long r3 = r3.perRpcBufferUsed     // Catch:{ all -> 0x0087 }
                long r1 = r1 - r3
                long r1 = r6.addAndGet(r1)     // Catch:{ all -> 0x0087 }
                io.grpc.internal.RetriableStream r6 = io.grpc.internal.RetriableStream.this     // Catch:{ all -> 0x0087 }
                long r3 = r5.bufferNeeded     // Catch:{ all -> 0x0087 }
                long unused = r6.perRpcBufferUsed = r3     // Catch:{ all -> 0x0087 }
                io.grpc.internal.RetriableStream r6 = io.grpc.internal.RetriableStream.this     // Catch:{ all -> 0x0087 }
                long r3 = r6.channelBufferLimit     // Catch:{ all -> 0x0087 }
                int r6 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
                if (r6 <= 0) goto L_0x006e
                io.grpc.internal.RetriableStream$Substream r6 = r5.substream     // Catch:{ all -> 0x0087 }
                r6.bufferLimitExceeded = r7     // Catch:{ all -> 0x0087 }
            L_0x006e:
                io.grpc.internal.RetriableStream$Substream r6 = r5.substream     // Catch:{ all -> 0x0087 }
                boolean r6 = r6.bufferLimitExceeded     // Catch:{ all -> 0x0087 }
                if (r6 == 0) goto L_0x007d
                io.grpc.internal.RetriableStream r6 = io.grpc.internal.RetriableStream.this     // Catch:{ all -> 0x0087 }
                io.grpc.internal.RetriableStream$Substream r7 = r5.substream     // Catch:{ all -> 0x0087 }
                java.lang.Runnable r6 = r6.commit(r7)     // Catch:{ all -> 0x0087 }
                goto L_0x007e
            L_0x007d:
                r6 = 0
            L_0x007e:
                monitor-exit(r0)     // Catch:{ all -> 0x0087 }
                if (r6 == 0) goto L_0x0084
                r6.run()
            L_0x0084:
                return
            L_0x0085:
                monitor-exit(r0)     // Catch:{ all -> 0x0087 }
                return
            L_0x0087:
                r6 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x0087 }
                throw r6
            */
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.internal.RetriableStream.BufferSizeTracer.outboundWireSize(long):void");
        }
    }

    static final class ChannelBufferMeter {
        private final AtomicLong bufferUsed = new AtomicLong();

        ChannelBufferMeter() {
        }

        /* access modifiers changed from: package-private */
        public long addAndGet(long j) {
            return this.bufferUsed.addAndGet(j);
        }
    }

    static final class Throttle {
        private static final int THREE_DECIMAL_PLACES_SCALE_UP = 1000;
        final int maxTokens;
        final int threshold;
        final AtomicInteger tokenCount;
        final int tokenRatio;

        Throttle(float f, float f2) {
            AtomicInteger atomicInteger = new AtomicInteger();
            this.tokenCount = atomicInteger;
            this.tokenRatio = (int) (f2 * 1000.0f);
            int i = (int) (f * 1000.0f);
            this.maxTokens = i;
            this.threshold = i / 2;
            atomicInteger.set(i);
        }

        /* access modifiers changed from: package-private */
        public boolean isAboveThreshold() {
            return this.tokenCount.get() > this.threshold;
        }

        /* access modifiers changed from: package-private */
        public boolean onQualifiedFailureThenCheckIsAboveThreshold() {
            int i;
            int i2;
            do {
                i = this.tokenCount.get();
                if (i == 0) {
                    return false;
                }
                i2 = i - 1000;
            } while (!this.tokenCount.compareAndSet(i, Math.max(i2, 0)));
            if (i2 > this.threshold) {
                return true;
            }
            return false;
        }

        /*  JADX ERROR: StackOverflow in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: 
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
            	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
            */
        void onSuccess() {
            /*
                r4 = this;
            L_0x0000:
                java.util.concurrent.atomic.AtomicInteger r0 = r4.tokenCount
                int r0 = r0.get()
                int r1 = r4.maxTokens
                if (r0 != r1) goto L_0x000b
                goto L_0x001a
            L_0x000b:
                int r2 = r4.tokenRatio
                int r2 = r2 + r0
                java.util.concurrent.atomic.AtomicInteger r3 = r4.tokenCount
                int r1 = java.lang.Math.min(r2, r1)
                boolean r0 = r3.compareAndSet(r0, r1)
                if (r0 == 0) goto L_0x0000
            L_0x001a:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.internal.RetriableStream.Throttle.onSuccess():void");
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Throttle)) {
                return false;
            }
            Throttle throttle = (Throttle) obj;
            if (this.maxTokens == throttle.maxTokens && this.tokenRatio == throttle.tokenRatio) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return Objects.hashCode(Integer.valueOf(this.maxTokens), Integer.valueOf(this.tokenRatio));
        }
    }

    private static final class RetryPlan {
        final long backoffNanos;
        final boolean shouldRetry;

        RetryPlan(boolean z, long j) {
            this.shouldRetry = z;
            this.backoffNanos = j;
        }
    }

    private static final class HedgingPlan {
        @Nullable
        final Integer hedgingPushbackMillis;
        final boolean isHedgeable;

        public HedgingPlan(boolean z, @Nullable Integer num) {
            this.isHedgeable = z;
            this.hedgingPushbackMillis = num;
        }
    }

    private static final class FutureCanceller {
        boolean cancelled;
        Future<?> future;
        final Object lock;

        FutureCanceller(Object obj) {
            this.lock = obj;
        }

        /* access modifiers changed from: package-private */
        public void setFuture(Future<?> future2) {
            synchronized (this.lock) {
                if (!this.cancelled) {
                    this.future = future2;
                }
            }
        }

        /* access modifiers changed from: package-private */
        @CheckForNull
        public Future<?> markCancelled() {
            this.cancelled = true;
            return this.future;
        }

        /* access modifiers changed from: package-private */
        public boolean isCancelled() {
            return this.cancelled;
        }
    }
}
