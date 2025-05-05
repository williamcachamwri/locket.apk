package io.grpc.okhttp;

import com.google.common.base.Preconditions;
import io.grpc.internal.SerializingExecutor;
import io.grpc.okhttp.ExceptionHandlingFrameWriter;
import io.grpc.okhttp.internal.framed.ErrorCode;
import io.grpc.okhttp.internal.framed.FrameWriter;
import io.grpc.okhttp.internal.framed.Settings;
import java.io.IOException;
import java.net.Socket;
import javax.annotation.Nullable;
import okio.Buffer;
import okio.Sink;
import okio.Timeout;

final class AsyncSink implements Sink {
    /* access modifiers changed from: private */
    public final Buffer buffer = new Buffer();
    private boolean closed = false;
    private boolean controlFramesExceeded;
    private int controlFramesInWrite;
    /* access modifiers changed from: private */
    public boolean flushEnqueued = false;
    /* access modifiers changed from: private */
    public final Object lock = new Object();
    private final int maxQueuedControlFrames;
    /* access modifiers changed from: private */
    public int queuedControlFrames;
    private final SerializingExecutor serializingExecutor;
    /* access modifiers changed from: private */
    @Nullable
    public Sink sink;
    /* access modifiers changed from: private */
    @Nullable
    public Socket socket;
    /* access modifiers changed from: private */
    public final ExceptionHandlingFrameWriter.TransportExceptionHandler transportExceptionHandler;
    /* access modifiers changed from: private */
    public boolean writeEnqueued = false;

    static /* synthetic */ int access$420(AsyncSink asyncSink, int i) {
        int i2 = asyncSink.queuedControlFrames - i;
        asyncSink.queuedControlFrames = i2;
        return i2;
    }

    static /* synthetic */ int access$908(AsyncSink asyncSink) {
        int i = asyncSink.controlFramesInWrite;
        asyncSink.controlFramesInWrite = i + 1;
        return i;
    }

    private AsyncSink(SerializingExecutor serializingExecutor2, ExceptionHandlingFrameWriter.TransportExceptionHandler transportExceptionHandler2, int i) {
        this.serializingExecutor = (SerializingExecutor) Preconditions.checkNotNull(serializingExecutor2, "executor");
        this.transportExceptionHandler = (ExceptionHandlingFrameWriter.TransportExceptionHandler) Preconditions.checkNotNull(transportExceptionHandler2, "exceptionHandler");
        this.maxQueuedControlFrames = i;
    }

    static AsyncSink sink(SerializingExecutor serializingExecutor2, ExceptionHandlingFrameWriter.TransportExceptionHandler transportExceptionHandler2, int i) {
        return new AsyncSink(serializingExecutor2, transportExceptionHandler2, i);
    }

    /* access modifiers changed from: package-private */
    public void becomeConnected(Sink sink2, Socket socket2) {
        Preconditions.checkState(this.sink == null, "AsyncSink's becomeConnected should only be called once.");
        this.sink = (Sink) Preconditions.checkNotNull(sink2, "sink");
        this.socket = (Socket) Preconditions.checkNotNull(socket2, "socket");
    }

    /* access modifiers changed from: package-private */
    public FrameWriter limitControlFramesWriter(FrameWriter frameWriter) {
        return new LimitControlFramesWriter(frameWriter);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0046, code lost:
        if (r9 == false) goto L_0x005a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        r7.socket.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        r7.serializingExecutor.execute(new io.grpc.okhttp.AsyncSink.AnonymousClass1(r7));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0064, code lost:
        if (r0 == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0066, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x006b, code lost:
        if (r0 == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x006d, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void write(okio.Buffer r8, long r9) throws java.io.IOException {
        /*
            r7 = this;
            java.lang.String r0 = "source"
            com.google.common.base.Preconditions.checkNotNull(r8, r0)
            boolean r0 = r7.closed
            if (r0 != 0) goto L_0x0080
            java.lang.String r0 = "AsyncSink.write"
            io.perfmark.TaskCloseable r0 = io.perfmark.PerfMark.traceTask(r0)
            java.lang.Object r1 = r7.lock     // Catch:{ all -> 0x0074 }
            monitor-enter(r1)     // Catch:{ all -> 0x0074 }
            okio.Buffer r2 = r7.buffer     // Catch:{ all -> 0x0071 }
            r2.write((okio.Buffer) r8, (long) r9)     // Catch:{ all -> 0x0071 }
            int r8 = r7.queuedControlFrames     // Catch:{ all -> 0x0071 }
            int r9 = r7.controlFramesInWrite     // Catch:{ all -> 0x0071 }
            int r8 = r8 + r9
            r7.queuedControlFrames = r8     // Catch:{ all -> 0x0071 }
            r9 = 0
            r7.controlFramesInWrite = r9     // Catch:{ all -> 0x0071 }
            boolean r10 = r7.controlFramesExceeded     // Catch:{ all -> 0x0071 }
            r2 = 1
            if (r10 != 0) goto L_0x002e
            int r10 = r7.maxQueuedControlFrames     // Catch:{ all -> 0x0071 }
            if (r8 <= r10) goto L_0x002e
            r7.controlFramesExceeded = r2     // Catch:{ all -> 0x0071 }
            r9 = r2
            goto L_0x0045
        L_0x002e:
            boolean r8 = r7.writeEnqueued     // Catch:{ all -> 0x0071 }
            if (r8 != 0) goto L_0x006a
            boolean r8 = r7.flushEnqueued     // Catch:{ all -> 0x0071 }
            if (r8 != 0) goto L_0x006a
            okio.Buffer r8 = r7.buffer     // Catch:{ all -> 0x0071 }
            long r3 = r8.completeSegmentByteCount()     // Catch:{ all -> 0x0071 }
            r5 = 0
            int r8 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r8 > 0) goto L_0x0043
            goto L_0x006a
        L_0x0043:
            r7.writeEnqueued = r2     // Catch:{ all -> 0x0071 }
        L_0x0045:
            monitor-exit(r1)     // Catch:{ all -> 0x0071 }
            if (r9 == 0) goto L_0x005a
            java.net.Socket r8 = r7.socket     // Catch:{ IOException -> 0x004e }
            r8.close()     // Catch:{ IOException -> 0x004e }
            goto L_0x0054
        L_0x004e:
            r8 = move-exception
            io.grpc.okhttp.ExceptionHandlingFrameWriter$TransportExceptionHandler r9 = r7.transportExceptionHandler     // Catch:{ all -> 0x0074 }
            r9.onException(r8)     // Catch:{ all -> 0x0074 }
        L_0x0054:
            if (r0 == 0) goto L_0x0059
            r0.close()
        L_0x0059:
            return
        L_0x005a:
            io.grpc.internal.SerializingExecutor r8 = r7.serializingExecutor     // Catch:{ all -> 0x0074 }
            io.grpc.okhttp.AsyncSink$1 r9 = new io.grpc.okhttp.AsyncSink$1     // Catch:{ all -> 0x0074 }
            r9.<init>()     // Catch:{ all -> 0x0074 }
            r8.execute(r9)     // Catch:{ all -> 0x0074 }
            if (r0 == 0) goto L_0x0069
            r0.close()
        L_0x0069:
            return
        L_0x006a:
            monitor-exit(r1)     // Catch:{ all -> 0x0071 }
            if (r0 == 0) goto L_0x0070
            r0.close()
        L_0x0070:
            return
        L_0x0071:
            r8 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0071 }
            throw r8     // Catch:{ all -> 0x0074 }
        L_0x0074:
            r8 = move-exception
            if (r0 == 0) goto L_0x007f
            r0.close()     // Catch:{ all -> 0x007b }
            goto L_0x007f
        L_0x007b:
            r9 = move-exception
            r8.addSuppressed(r9)
        L_0x007f:
            throw r8
        L_0x0080:
            java.io.IOException r8 = new java.io.IOException
            java.lang.String r9 = "closed"
            r8.<init>(r9)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.okhttp.AsyncSink.write(okio.Buffer, long):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0012, code lost:
        if (r0 == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0014, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        r3.serializingExecutor.execute(new io.grpc.okhttp.AsyncSink.AnonymousClass2(r3));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0026, code lost:
        if (r0 == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0028, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void flush() throws java.io.IOException {
        /*
            r3 = this;
            boolean r0 = r3.closed
            if (r0 != 0) goto L_0x003b
            java.lang.String r0 = "AsyncSink.flush"
            io.perfmark.TaskCloseable r0 = io.perfmark.PerfMark.traceTask(r0)
            java.lang.Object r1 = r3.lock     // Catch:{ all -> 0x002f }
            monitor-enter(r1)     // Catch:{ all -> 0x002f }
            boolean r2 = r3.flushEnqueued     // Catch:{ all -> 0x002c }
            if (r2 == 0) goto L_0x0018
            monitor-exit(r1)     // Catch:{ all -> 0x002c }
            if (r0 == 0) goto L_0x0017
            r0.close()
        L_0x0017:
            return
        L_0x0018:
            r2 = 1
            r3.flushEnqueued = r2     // Catch:{ all -> 0x002c }
            monitor-exit(r1)     // Catch:{ all -> 0x002c }
            io.grpc.internal.SerializingExecutor r1 = r3.serializingExecutor     // Catch:{ all -> 0x002f }
            io.grpc.okhttp.AsyncSink$2 r2 = new io.grpc.okhttp.AsyncSink$2     // Catch:{ all -> 0x002f }
            r2.<init>()     // Catch:{ all -> 0x002f }
            r1.execute(r2)     // Catch:{ all -> 0x002f }
            if (r0 == 0) goto L_0x002b
            r0.close()
        L_0x002b:
            return
        L_0x002c:
            r2 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x002c }
            throw r2     // Catch:{ all -> 0x002f }
        L_0x002f:
            r1 = move-exception
            if (r0 == 0) goto L_0x003a
            r0.close()     // Catch:{ all -> 0x0036 }
            goto L_0x003a
        L_0x0036:
            r0 = move-exception
            r1.addSuppressed(r0)
        L_0x003a:
            throw r1
        L_0x003b:
            java.io.IOException r0 = new java.io.IOException
            java.lang.String r1 = "closed"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.okhttp.AsyncSink.flush():void");
    }

    public Timeout timeout() {
        return Timeout.NONE;
    }

    public void close() {
        if (!this.closed) {
            this.closed = true;
            this.serializingExecutor.execute(new Runnable() {
                public void run() {
                    try {
                        if (AsyncSink.this.sink != null && AsyncSink.this.buffer.size() > 0) {
                            AsyncSink.this.sink.write(AsyncSink.this.buffer, AsyncSink.this.buffer.size());
                        }
                    } catch (IOException e) {
                        AsyncSink.this.transportExceptionHandler.onException(e);
                    }
                    AsyncSink.this.buffer.close();
                    try {
                        if (AsyncSink.this.sink != null) {
                            AsyncSink.this.sink.close();
                        }
                    } catch (IOException e2) {
                        AsyncSink.this.transportExceptionHandler.onException(e2);
                    }
                    try {
                        if (AsyncSink.this.socket != null) {
                            AsyncSink.this.socket.close();
                        }
                    } catch (IOException e3) {
                        AsyncSink.this.transportExceptionHandler.onException(e3);
                    }
                }
            });
        }
    }

    private abstract class WriteRunnable implements Runnable {
        public abstract void doRun() throws IOException;

        private WriteRunnable() {
        }

        public final void run() {
            try {
                if (AsyncSink.this.sink != null) {
                    doRun();
                    return;
                }
                throw new IOException("Unable to perform write due to unavailable sink.");
            } catch (Exception e) {
                AsyncSink.this.transportExceptionHandler.onException(e);
            }
        }
    }

    private class LimitControlFramesWriter extends ForwardingFrameWriter {
        public LimitControlFramesWriter(FrameWriter frameWriter) {
            super(frameWriter);
        }

        public void ackSettings(Settings settings) throws IOException {
            AsyncSink.access$908(AsyncSink.this);
            super.ackSettings(settings);
        }

        public void rstStream(int i, ErrorCode errorCode) throws IOException {
            AsyncSink.access$908(AsyncSink.this);
            super.rstStream(i, errorCode);
        }

        public void ping(boolean z, int i, int i2) throws IOException {
            if (z) {
                AsyncSink.access$908(AsyncSink.this);
            }
            super.ping(z, i, i2);
        }
    }
}
