package androidx.media3.decoder;

import androidx.media3.common.C;
import androidx.media3.common.util.Assertions;
import androidx.media3.decoder.DecoderException;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.decoder.DecoderOutputBuffer;
import java.util.ArrayDeque;

public abstract class SimpleDecoder<I extends DecoderInputBuffer, O extends DecoderOutputBuffer, E extends DecoderException> implements Decoder<I, O, E> {
    private int availableInputBufferCount;
    private final I[] availableInputBuffers;
    private int availableOutputBufferCount;
    private final O[] availableOutputBuffers;
    private final Thread decodeThread;
    private I dequeuedInputBuffer;
    private E exception;
    private boolean flushed;
    private final Object lock = new Object();
    private long outputStartTimeUs = C.TIME_UNSET;
    private final ArrayDeque<I> queuedInputBuffers = new ArrayDeque<>();
    private final ArrayDeque<O> queuedOutputBuffers = new ArrayDeque<>();
    private boolean released;
    private int skippedOutputBufferCount;

    /* access modifiers changed from: protected */
    public abstract I createInputBuffer();

    /* access modifiers changed from: protected */
    public abstract O createOutputBuffer();

    /* access modifiers changed from: protected */
    public abstract E createUnexpectedDecodeException(Throwable th);

    /* access modifiers changed from: protected */
    public abstract E decode(I i, O o, boolean z);

    protected SimpleDecoder(I[] iArr, O[] oArr) {
        this.availableInputBuffers = iArr;
        this.availableInputBufferCount = iArr.length;
        for (int i = 0; i < this.availableInputBufferCount; i++) {
            this.availableInputBuffers[i] = createInputBuffer();
        }
        this.availableOutputBuffers = oArr;
        this.availableOutputBufferCount = oArr.length;
        for (int i2 = 0; i2 < this.availableOutputBufferCount; i2++) {
            this.availableOutputBuffers[i2] = createOutputBuffer();
        }
        AnonymousClass1 r4 = new Thread("ExoPlayer:SimpleDecoder") {
            public void run() {
                SimpleDecoder.this.run();
            }
        };
        this.decodeThread = r4;
        r4.start();
    }

    /* access modifiers changed from: protected */
    public final void setInitialInputBufferSize(int i) {
        Assertions.checkState(this.availableInputBufferCount == this.availableInputBuffers.length);
        for (I ensureSpaceForWrite : this.availableInputBuffers) {
            ensureSpaceForWrite.ensureSpaceForWrite(i);
        }
    }

    /* access modifiers changed from: protected */
    public final boolean isAtLeastOutputStartTimeUs(long j) {
        boolean z;
        synchronized (this.lock) {
            long j2 = this.outputStartTimeUs;
            if (j2 != C.TIME_UNSET) {
                if (j < j2) {
                    z = false;
                }
            }
            z = true;
        }
        return z;
    }

    public final void setOutputStartTimeUs(long j) {
        boolean z;
        synchronized (this.lock) {
            if (this.availableInputBufferCount != this.availableInputBuffers.length) {
                if (!this.flushed) {
                    z = false;
                    Assertions.checkState(z);
                    this.outputStartTimeUs = j;
                }
            }
            z = true;
            Assertions.checkState(z);
            this.outputStartTimeUs = j;
        }
    }

    public final I dequeueInputBuffer() throws DecoderException {
        I i;
        synchronized (this.lock) {
            maybeThrowException();
            Assertions.checkState(this.dequeuedInputBuffer == null);
            int i2 = this.availableInputBufferCount;
            if (i2 == 0) {
                i = null;
            } else {
                I[] iArr = this.availableInputBuffers;
                int i3 = i2 - 1;
                this.availableInputBufferCount = i3;
                i = iArr[i3];
            }
            this.dequeuedInputBuffer = i;
        }
        return i;
    }

    public final void queueInputBuffer(I i) throws DecoderException {
        synchronized (this.lock) {
            maybeThrowException();
            Assertions.checkArgument(i == this.dequeuedInputBuffer);
            this.queuedInputBuffers.addLast(i);
            maybeNotifyDecodeLoop();
            this.dequeuedInputBuffer = null;
        }
    }

    public final O dequeueOutputBuffer() throws DecoderException {
        synchronized (this.lock) {
            maybeThrowException();
            if (this.queuedOutputBuffers.isEmpty()) {
                return null;
            }
            O o = (DecoderOutputBuffer) this.queuedOutputBuffers.removeFirst();
            return o;
        }
    }

    /* access modifiers changed from: protected */
    public void releaseOutputBuffer(O o) {
        synchronized (this.lock) {
            releaseOutputBufferInternal(o);
            maybeNotifyDecodeLoop();
        }
    }

    public final void flush() {
        synchronized (this.lock) {
            this.flushed = true;
            this.skippedOutputBufferCount = 0;
            I i = this.dequeuedInputBuffer;
            if (i != null) {
                releaseInputBufferInternal(i);
                this.dequeuedInputBuffer = null;
            }
            while (!this.queuedInputBuffers.isEmpty()) {
                releaseInputBufferInternal((DecoderInputBuffer) this.queuedInputBuffers.removeFirst());
            }
            while (!this.queuedOutputBuffers.isEmpty()) {
                ((DecoderOutputBuffer) this.queuedOutputBuffers.removeFirst()).release();
            }
        }
    }

    public void release() {
        synchronized (this.lock) {
            this.released = true;
            this.lock.notify();
        }
        try {
            this.decodeThread.join();
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
        }
    }

    private void maybeThrowException() throws DecoderException {
        E e = this.exception;
        if (e != null) {
            throw e;
        }
    }

    private void maybeNotifyDecodeLoop() {
        if (canDecodeBuffer()) {
            this.lock.notify();
        }
    }

    /* access modifiers changed from: private */
    public void run() {
        do {
            try {
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
        } while (decode());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0035, code lost:
        if (r1.isEndOfStream() == false) goto L_0x003c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0037, code lost:
        r3.addFlag(4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x003c, code lost:
        r3.timeUs = r1.timeUs;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0044, code lost:
        if (r1.isFirstSample() == false) goto L_0x004b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0046, code lost:
        r3.addFlag(androidx.media3.common.C.BUFFER_FLAG_FIRST_SAMPLE);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0051, code lost:
        if (isAtLeastOutputStartTimeUs(r1.timeUs) != false) goto L_0x0055;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0053, code lost:
        r3.shouldBeSkipped = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        r0 = decode(r1, r3, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x005a, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x005b, code lost:
        r0 = createUnexpectedDecodeException(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0060, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0061, code lost:
        r0 = createUnexpectedDecodeException(r0);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean decode() throws java.lang.InterruptedException {
        /*
            r8 = this;
            java.lang.Object r0 = r8.lock
            monitor-enter(r0)
        L_0x0003:
            boolean r1 = r8.released     // Catch:{ all -> 0x009c }
            if (r1 != 0) goto L_0x0013
            boolean r1 = r8.canDecodeBuffer()     // Catch:{ all -> 0x009c }
            if (r1 != 0) goto L_0x0013
            java.lang.Object r1 = r8.lock     // Catch:{ all -> 0x009c }
            r1.wait()     // Catch:{ all -> 0x009c }
            goto L_0x0003
        L_0x0013:
            boolean r1 = r8.released     // Catch:{ all -> 0x009c }
            r2 = 0
            if (r1 == 0) goto L_0x001a
            monitor-exit(r0)     // Catch:{ all -> 0x009c }
            return r2
        L_0x001a:
            java.util.ArrayDeque<I> r1 = r8.queuedInputBuffers     // Catch:{ all -> 0x009c }
            java.lang.Object r1 = r1.removeFirst()     // Catch:{ all -> 0x009c }
            androidx.media3.decoder.DecoderInputBuffer r1 = (androidx.media3.decoder.DecoderInputBuffer) r1     // Catch:{ all -> 0x009c }
            O[] r3 = r8.availableOutputBuffers     // Catch:{ all -> 0x009c }
            int r4 = r8.availableOutputBufferCount     // Catch:{ all -> 0x009c }
            r5 = 1
            int r4 = r4 - r5
            r8.availableOutputBufferCount = r4     // Catch:{ all -> 0x009c }
            r3 = r3[r4]     // Catch:{ all -> 0x009c }
            boolean r4 = r8.flushed     // Catch:{ all -> 0x009c }
            r8.flushed = r2     // Catch:{ all -> 0x009c }
            monitor-exit(r0)     // Catch:{ all -> 0x009c }
            boolean r0 = r1.isEndOfStream()
            if (r0 == 0) goto L_0x003c
            r0 = 4
            r3.addFlag(r0)
            goto L_0x0071
        L_0x003c:
            long r6 = r1.timeUs
            r3.timeUs = r6
            boolean r0 = r1.isFirstSample()
            if (r0 == 0) goto L_0x004b
            r0 = 134217728(0x8000000, float:3.85186E-34)
            r3.addFlag(r0)
        L_0x004b:
            long r6 = r1.timeUs
            boolean r0 = r8.isAtLeastOutputStartTimeUs(r6)
            if (r0 != 0) goto L_0x0055
            r3.shouldBeSkipped = r5
        L_0x0055:
            androidx.media3.decoder.DecoderException r0 = r8.decode(r1, r3, r4)     // Catch:{ RuntimeException -> 0x0060, OutOfMemoryError -> 0x005a }
            goto L_0x0065
        L_0x005a:
            r0 = move-exception
            androidx.media3.decoder.DecoderException r0 = r8.createUnexpectedDecodeException(r0)
            goto L_0x0065
        L_0x0060:
            r0 = move-exception
            androidx.media3.decoder.DecoderException r0 = r8.createUnexpectedDecodeException(r0)
        L_0x0065:
            if (r0 == 0) goto L_0x0071
            java.lang.Object r4 = r8.lock
            monitor-enter(r4)
            r8.exception = r0     // Catch:{ all -> 0x006e }
            monitor-exit(r4)     // Catch:{ all -> 0x006e }
            return r2
        L_0x006e:
            r0 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x006e }
            throw r0
        L_0x0071:
            java.lang.Object r4 = r8.lock
            monitor-enter(r4)
            boolean r0 = r8.flushed     // Catch:{ all -> 0x0099 }
            if (r0 == 0) goto L_0x007c
            r3.release()     // Catch:{ all -> 0x0099 }
            goto L_0x0094
        L_0x007c:
            boolean r0 = r3.shouldBeSkipped     // Catch:{ all -> 0x0099 }
            if (r0 == 0) goto L_0x0089
            int r0 = r8.skippedOutputBufferCount     // Catch:{ all -> 0x0099 }
            int r0 = r0 + r5
            r8.skippedOutputBufferCount = r0     // Catch:{ all -> 0x0099 }
            r3.release()     // Catch:{ all -> 0x0099 }
            goto L_0x0094
        L_0x0089:
            int r0 = r8.skippedOutputBufferCount     // Catch:{ all -> 0x0099 }
            r3.skippedOutputBufferCount = r0     // Catch:{ all -> 0x0099 }
            r8.skippedOutputBufferCount = r2     // Catch:{ all -> 0x0099 }
            java.util.ArrayDeque<O> r0 = r8.queuedOutputBuffers     // Catch:{ all -> 0x0099 }
            r0.addLast(r3)     // Catch:{ all -> 0x0099 }
        L_0x0094:
            r8.releaseInputBufferInternal(r1)     // Catch:{ all -> 0x0099 }
            monitor-exit(r4)     // Catch:{ all -> 0x0099 }
            return r5
        L_0x0099:
            r0 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x0099 }
            throw r0
        L_0x009c:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x009c }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.decoder.SimpleDecoder.decode():boolean");
    }

    private boolean canDecodeBuffer() {
        return !this.queuedInputBuffers.isEmpty() && this.availableOutputBufferCount > 0;
    }

    private void releaseInputBufferInternal(I i) {
        i.clear();
        I[] iArr = this.availableInputBuffers;
        int i2 = this.availableInputBufferCount;
        this.availableInputBufferCount = i2 + 1;
        iArr[i2] = i;
    }

    private void releaseOutputBufferInternal(O o) {
        o.clear();
        O[] oArr = this.availableOutputBuffers;
        int i = this.availableOutputBufferCount;
        this.availableOutputBufferCount = i + 1;
        oArr[i] = o;
    }
}
