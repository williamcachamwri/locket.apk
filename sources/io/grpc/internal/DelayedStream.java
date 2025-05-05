package io.grpc.internal;

import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.common.base.Preconditions;
import io.grpc.Attributes;
import io.grpc.Compressor;
import io.grpc.Deadline;
import io.grpc.DecompressorRegistry;
import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.internal.ClientStreamListener;
import io.grpc.internal.StreamListener;
import io.sentry.clientreport.DiscardedEvent;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

class DelayedStream implements ClientStream {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private DelayedStreamListener delayedListener;
    private Status error;
    private ClientStreamListener listener;
    private volatile boolean passThrough;
    private List<Runnable> pendingCalls = new ArrayList();
    private List<Runnable> preStartPendingCalls = new ArrayList();
    /* access modifiers changed from: private */
    public ClientStream realStream;
    private long startTimeNanos;
    private long streamSetTimeNanos;

    /* access modifiers changed from: protected */
    public void onEarlyCancellation(Status status) {
    }

    DelayedStream() {
    }

    public void setMaxInboundMessageSize(final int i) {
        Preconditions.checkState(this.listener == null, "May only be called before start");
        this.preStartPendingCalls.add(new Runnable() {
            public void run() {
                DelayedStream.this.realStream.setMaxInboundMessageSize(i);
            }
        });
    }

    public void setMaxOutboundMessageSize(final int i) {
        Preconditions.checkState(this.listener == null, "May only be called before start");
        this.preStartPendingCalls.add(new Runnable() {
            public void run() {
                DelayedStream.this.realStream.setMaxOutboundMessageSize(i);
            }
        });
    }

    public void setDeadline(final Deadline deadline) {
        Preconditions.checkState(this.listener == null, "May only be called before start");
        this.preStartPendingCalls.add(new Runnable() {
            public void run() {
                DelayedStream.this.realStream.setDeadline(deadline);
            }
        });
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0035, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void appendTimeoutInsight(io.grpc.internal.InsightBuilder r6) {
        /*
            r5 = this;
            monitor-enter(r5)
            io.grpc.internal.ClientStreamListener r0 = r5.listener     // Catch:{ all -> 0x0036 }
            if (r0 != 0) goto L_0x0007
            monitor-exit(r5)     // Catch:{ all -> 0x0036 }
            return
        L_0x0007:
            io.grpc.internal.ClientStream r0 = r5.realStream     // Catch:{ all -> 0x0036 }
            if (r0 == 0) goto L_0x001f
            java.lang.String r0 = "buffered_nanos"
            long r1 = r5.streamSetTimeNanos     // Catch:{ all -> 0x0036 }
            long r3 = r5.startTimeNanos     // Catch:{ all -> 0x0036 }
            long r1 = r1 - r3
            java.lang.Long r1 = java.lang.Long.valueOf(r1)     // Catch:{ all -> 0x0036 }
            r6.appendKeyValue(r0, r1)     // Catch:{ all -> 0x0036 }
            io.grpc.internal.ClientStream r0 = r5.realStream     // Catch:{ all -> 0x0036 }
            r0.appendTimeoutInsight(r6)     // Catch:{ all -> 0x0036 }
            goto L_0x0034
        L_0x001f:
            java.lang.String r0 = "buffered_nanos"
            long r1 = java.lang.System.nanoTime()     // Catch:{ all -> 0x0036 }
            long r3 = r5.startTimeNanos     // Catch:{ all -> 0x0036 }
            long r1 = r1 - r3
            java.lang.Long r1 = java.lang.Long.valueOf(r1)     // Catch:{ all -> 0x0036 }
            r6.appendKeyValue(r0, r1)     // Catch:{ all -> 0x0036 }
            java.lang.String r0 = "waiting_for_connection"
            r6.append(r0)     // Catch:{ all -> 0x0036 }
        L_0x0034:
            monitor-exit(r5)     // Catch:{ all -> 0x0036 }
            return
        L_0x0036:
            r6 = move-exception
            monitor-exit(r5)     // Catch:{ all -> 0x0036 }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.internal.DelayedStream.appendTimeoutInsight(io.grpc.internal.InsightBuilder):void");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001d, code lost:
        if (r3 != null) goto L_0x0020;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001f, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0020, code lost:
        internalStart(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0028, code lost:
        return new io.grpc.internal.DelayedStream.AnonymousClass4(r2);
     */
    @javax.annotation.CheckReturnValue
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Runnable setStream(io.grpc.internal.ClientStream r3) {
        /*
            r2 = this;
            monitor-enter(r2)
            io.grpc.internal.ClientStream r0 = r2.realStream     // Catch:{ all -> 0x0029 }
            r1 = 0
            if (r0 == 0) goto L_0x0008
            monitor-exit(r2)     // Catch:{ all -> 0x0029 }
            return r1
        L_0x0008:
            java.lang.String r0 = "stream"
            java.lang.Object r3 = com.google.common.base.Preconditions.checkNotNull(r3, r0)     // Catch:{ all -> 0x0029 }
            io.grpc.internal.ClientStream r3 = (io.grpc.internal.ClientStream) r3     // Catch:{ all -> 0x0029 }
            r2.setRealStream(r3)     // Catch:{ all -> 0x0029 }
            io.grpc.internal.ClientStreamListener r3 = r2.listener     // Catch:{ all -> 0x0029 }
            if (r3 != 0) goto L_0x001c
            r2.pendingCalls = r1     // Catch:{ all -> 0x0029 }
            r0 = 1
            r2.passThrough = r0     // Catch:{ all -> 0x0029 }
        L_0x001c:
            monitor-exit(r2)     // Catch:{ all -> 0x0029 }
            if (r3 != 0) goto L_0x0020
            return r1
        L_0x0020:
            r2.internalStart(r3)
            io.grpc.internal.DelayedStream$4 r3 = new io.grpc.internal.DelayedStream$4
            r3.<init>()
            return r3
        L_0x0029:
            r3 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0029 }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.internal.DelayedStream.setStream(io.grpc.internal.ClientStream):java.lang.Runnable");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0022, code lost:
        r0 = r1.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002a, code lost:
        if (r0.hasNext() == false) goto L_0x0036;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002c, code lost:
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
        r0.drainPendingCallbacks();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void drainPendingCalls() {
        /*
            r3 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
        L_0x0005:
            monitor-enter(r3)
            java.util.List<java.lang.Runnable> r1 = r3.pendingCalls     // Catch:{ all -> 0x003b }
            boolean r1 = r1.isEmpty()     // Catch:{ all -> 0x003b }
            if (r1 == 0) goto L_0x001d
            r0 = 0
            r3.pendingCalls = r0     // Catch:{ all -> 0x003b }
            r0 = 1
            r3.passThrough = r0     // Catch:{ all -> 0x003b }
            io.grpc.internal.DelayedStream$DelayedStreamListener r0 = r3.delayedListener     // Catch:{ all -> 0x003b }
            monitor-exit(r3)     // Catch:{ all -> 0x003b }
            if (r0 == 0) goto L_0x001c
            r0.drainPendingCallbacks()
        L_0x001c:
            return
        L_0x001d:
            java.util.List<java.lang.Runnable> r1 = r3.pendingCalls     // Catch:{ all -> 0x003b }
            r3.pendingCalls = r0     // Catch:{ all -> 0x003b }
            monitor-exit(r3)     // Catch:{ all -> 0x003b }
            java.util.Iterator r0 = r1.iterator()
        L_0x0026:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x0036
            java.lang.Object r2 = r0.next()
            java.lang.Runnable r2 = (java.lang.Runnable) r2
            r2.run()
            goto L_0x0026
        L_0x0036:
            r1.clear()
            r0 = r1
            goto L_0x0005
        L_0x003b:
            r0 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x003b }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.internal.DelayedStream.drainPendingCalls():void");
    }

    private void delayOrExecute(Runnable runnable) {
        Preconditions.checkState(this.listener != null, "May only be called after start");
        synchronized (this) {
            if (!this.passThrough) {
                this.pendingCalls.add(runnable);
            } else {
                runnable.run();
            }
        }
    }

    public void setAuthority(final String str) {
        Preconditions.checkState(this.listener == null, "May only be called before start");
        Preconditions.checkNotNull(str, "authority");
        this.preStartPendingCalls.add(new Runnable() {
            public void run() {
                DelayedStream.this.realStream.setAuthority(str);
            }
        });
    }

    public void start(ClientStreamListener clientStreamListener) {
        Status status;
        boolean z;
        Preconditions.checkNotNull(clientStreamListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        Preconditions.checkState(this.listener == null, "already started");
        synchronized (this) {
            status = this.error;
            z = this.passThrough;
            if (!z) {
                DelayedStreamListener delayedStreamListener = new DelayedStreamListener(clientStreamListener);
                this.delayedListener = delayedStreamListener;
                clientStreamListener = delayedStreamListener;
            }
            this.listener = clientStreamListener;
            this.startTimeNanos = System.nanoTime();
        }
        if (status != null) {
            clientStreamListener.closed(status, ClientStreamListener.RpcProgress.PROCESSED, new Metadata());
        } else if (z) {
            internalStart(clientStreamListener);
        }
    }

    private void internalStart(ClientStreamListener clientStreamListener) {
        for (Runnable run : this.preStartPendingCalls) {
            run.run();
        }
        this.preStartPendingCalls = null;
        this.realStream.start(clientStreamListener);
    }

    public Attributes getAttributes() {
        ClientStream clientStream;
        synchronized (this) {
            clientStream = this.realStream;
        }
        if (clientStream != null) {
            return clientStream.getAttributes();
        }
        return Attributes.EMPTY;
    }

    public void writeMessage(final InputStream inputStream) {
        Preconditions.checkState(this.listener != null, "May only be called after start");
        Preconditions.checkNotNull(inputStream, "message");
        if (this.passThrough) {
            this.realStream.writeMessage(inputStream);
        } else {
            delayOrExecute(new Runnable() {
                public void run() {
                    DelayedStream.this.realStream.writeMessage(inputStream);
                }
            });
        }
    }

    public void flush() {
        Preconditions.checkState(this.listener != null, "May only be called after start");
        if (this.passThrough) {
            this.realStream.flush();
        } else {
            delayOrExecute(new Runnable() {
                public void run() {
                    DelayedStream.this.realStream.flush();
                }
            });
        }
    }

    public void cancel(final Status status) {
        boolean z = true;
        Preconditions.checkState(this.listener != null, "May only be called after start");
        Preconditions.checkNotNull(status, DiscardedEvent.JsonKeys.REASON);
        synchronized (this) {
            if (this.realStream == null) {
                setRealStream(NoopClientStream.INSTANCE);
                this.error = status;
                z = false;
            }
        }
        if (z) {
            delayOrExecute(new Runnable() {
                public void run() {
                    DelayedStream.this.realStream.cancel(status);
                }
            });
            return;
        }
        drainPendingCalls();
        onEarlyCancellation(status);
        this.listener.closed(status, ClientStreamListener.RpcProgress.PROCESSED, new Metadata());
    }

    private void setRealStream(ClientStream clientStream) {
        ClientStream clientStream2 = this.realStream;
        Preconditions.checkState(clientStream2 == null, "realStream already set to %s", (Object) clientStream2);
        this.realStream = clientStream;
        this.streamSetTimeNanos = System.nanoTime();
    }

    public void halfClose() {
        Preconditions.checkState(this.listener != null, "May only be called after start");
        delayOrExecute(new Runnable() {
            public void run() {
                DelayedStream.this.realStream.halfClose();
            }
        });
    }

    public void request(final int i) {
        Preconditions.checkState(this.listener != null, "May only be called after start");
        if (this.passThrough) {
            this.realStream.request(i);
        } else {
            delayOrExecute(new Runnable() {
                public void run() {
                    DelayedStream.this.realStream.request(i);
                }
            });
        }
    }

    public void optimizeForDirectExecutor() {
        Preconditions.checkState(this.listener == null, "May only be called before start");
        this.preStartPendingCalls.add(new Runnable() {
            public void run() {
                DelayedStream.this.realStream.optimizeForDirectExecutor();
            }
        });
    }

    public void setCompressor(final Compressor compressor) {
        Preconditions.checkState(this.listener == null, "May only be called before start");
        Preconditions.checkNotNull(compressor, "compressor");
        this.preStartPendingCalls.add(new Runnable() {
            public void run() {
                DelayedStream.this.realStream.setCompressor(compressor);
            }
        });
    }

    public void setFullStreamDecompression(final boolean z) {
        Preconditions.checkState(this.listener == null, "May only be called before start");
        this.preStartPendingCalls.add(new Runnable() {
            public void run() {
                DelayedStream.this.realStream.setFullStreamDecompression(z);
            }
        });
    }

    public void setDecompressorRegistry(final DecompressorRegistry decompressorRegistry) {
        Preconditions.checkState(this.listener == null, "May only be called before start");
        Preconditions.checkNotNull(decompressorRegistry, "decompressorRegistry");
        this.preStartPendingCalls.add(new Runnable() {
            public void run() {
                DelayedStream.this.realStream.setDecompressorRegistry(decompressorRegistry);
            }
        });
    }

    public boolean isReady() {
        if (this.passThrough) {
            return this.realStream.isReady();
        }
        return false;
    }

    public void setMessageCompression(final boolean z) {
        Preconditions.checkState(this.listener != null, "May only be called after start");
        if (this.passThrough) {
            this.realStream.setMessageCompression(z);
        } else {
            delayOrExecute(new Runnable() {
                public void run() {
                    DelayedStream.this.realStream.setMessageCompression(z);
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    public ClientStream getRealStream() {
        return this.realStream;
    }

    private static class DelayedStreamListener implements ClientStreamListener {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private volatile boolean passThrough;
        private List<Runnable> pendingCallbacks = new ArrayList();
        /* access modifiers changed from: private */
        public final ClientStreamListener realListener;

        static {
            Class<DelayedStream> cls = DelayedStream.class;
        }

        public DelayedStreamListener(ClientStreamListener clientStreamListener) {
            this.realListener = clientStreamListener;
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

        public void messagesAvailable(final StreamListener.MessageProducer messageProducer) {
            if (this.passThrough) {
                this.realListener.messagesAvailable(messageProducer);
            } else {
                delayOrExecute(new Runnable() {
                    public void run() {
                        DelayedStreamListener.this.realListener.messagesAvailable(messageProducer);
                    }
                });
            }
        }

        public void onReady() {
            if (this.passThrough) {
                this.realListener.onReady();
            } else {
                delayOrExecute(new Runnable() {
                    public void run() {
                        DelayedStreamListener.this.realListener.onReady();
                    }
                });
            }
        }

        public void headersRead(final Metadata metadata) {
            delayOrExecute(new Runnable() {
                public void run() {
                    DelayedStreamListener.this.realListener.headersRead(metadata);
                }
            });
        }

        public void closed(final Status status, final ClientStreamListener.RpcProgress rpcProgress, final Metadata metadata) {
            delayOrExecute(new Runnable() {
                public void run() {
                    DelayedStreamListener.this.realListener.closed(status, rpcProgress, metadata);
                }
            });
        }

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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.internal.DelayedStream.DelayedStreamListener.drainPendingCallbacks():void");
        }
    }
}
