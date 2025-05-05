package okio;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b\b&\u0018\u00002\u00060\u0001j\u0002`\u0002:\u0002-.B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0006\u0010\u0010\u001a\u00020\u0011J\u0006\u0010\u0012\u001a\u00020\u0013J\u0006\u0010\u0014\u001a\u00020\u0013J\u000e\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0011J\u000e\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u0019J\b\u0010\u001a\u001a\u00020\u0013H$J\b\u0010\u001b\u001a\u00020\u0013H$J(\u0010\u001c\u001a\u00020\r2\u0006\u0010\u001d\u001a\u00020\u00162\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\r2\u0006\u0010!\u001a\u00020\rH$J\u0010\u0010\"\u001a\u00020\u00132\u0006\u0010#\u001a\u00020\u0016H$J\b\u0010$\u001a\u00020\u0016H$J(\u0010%\u001a\u00020\u00132\u0006\u0010\u001d\u001a\u00020\u00162\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\r2\u0006\u0010!\u001a\u00020\rH$J&\u0010&\u001a\u00020\r2\u0006\u0010\u001d\u001a\u00020\u00162\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\r2\u0006\u0010!\u001a\u00020\rJ\u001e\u0010&\u001a\u00020\u00162\u0006\u0010\u001d\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020'2\u0006\u0010!\u001a\u00020\u0016J \u0010(\u001a\u00020\u00162\u0006\u0010\u001d\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020'2\u0006\u0010!\u001a\u00020\u0016H\u0002J\u0016\u0010)\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u0016J\u0016\u0010)\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0015\u001a\u00020\u0016J\u000e\u0010*\u001a\u00020\u00132\u0006\u0010#\u001a\u00020\u0016J\u0010\u0010\u0017\u001a\u00020\u00112\b\b\u0002\u0010\u001d\u001a\u00020\u0016J\u0006\u0010#\u001a\u00020\u0016J\u0010\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001d\u001a\u00020\u0016J&\u0010+\u001a\u00020\u00132\u0006\u0010\u001d\u001a\u00020\u00162\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\r2\u0006\u0010!\u001a\u00020\rJ\u001e\u0010+\u001a\u00020\u00132\u0006\u0010\u001d\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020'2\u0006\u0010!\u001a\u00020\u0016J \u0010,\u001a\u00020\u00132\u0006\u0010\u001d\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020'2\u0006\u0010!\u001a\u00020\u0016H\u0002R\u000e\u0010\u0006\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000R\u0015\u0010\u0007\u001a\u00060\bj\u0002`\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006/"}, d2 = {"Lokio/FileHandle;", "Ljava/io/Closeable;", "Lokio/Closeable;", "readWrite", "", "(Z)V", "closed", "lock", "Ljava/util/concurrent/locks/ReentrantLock;", "Lokio/Lock;", "getLock", "()Ljava/util/concurrent/locks/ReentrantLock;", "openStreamCount", "", "getReadWrite", "()Z", "appendingSink", "Lokio/Sink;", "close", "", "flush", "position", "", "sink", "source", "Lokio/Source;", "protectedClose", "protectedFlush", "protectedRead", "fileOffset", "array", "", "arrayOffset", "byteCount", "protectedResize", "size", "protectedSize", "protectedWrite", "read", "Lokio/Buffer;", "readNoCloseCheck", "reposition", "resize", "write", "writeNoCloseCheck", "FileHandleSink", "FileHandleSource", "okio"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: FileHandle.kt */
public abstract class FileHandle implements Closeable {
    /* access modifiers changed from: private */
    public boolean closed;
    private final ReentrantLock lock = _JvmPlatformKt.newLock();
    /* access modifiers changed from: private */
    public int openStreamCount;
    private final boolean readWrite;

    /* access modifiers changed from: protected */
    public abstract void protectedClose() throws IOException;

    /* access modifiers changed from: protected */
    public abstract void protectedFlush() throws IOException;

    /* access modifiers changed from: protected */
    public abstract int protectedRead(long j, byte[] bArr, int i, int i2) throws IOException;

    /* access modifiers changed from: protected */
    public abstract void protectedResize(long j) throws IOException;

    /* access modifiers changed from: protected */
    public abstract long protectedSize() throws IOException;

    /* access modifiers changed from: protected */
    public abstract void protectedWrite(long j, byte[] bArr, int i, int i2) throws IOException;

    public FileHandle(boolean z) {
        this.readWrite = z;
    }

    public final boolean getReadWrite() {
        return this.readWrite;
    }

    public final ReentrantLock getLock() {
        return this.lock;
    }

    /* JADX INFO: finally extract failed */
    public final int read(long j, byte[] bArr, int i, int i2) throws IOException {
        Intrinsics.checkNotNullParameter(bArr, "array");
        Lock lock2 = this.lock;
        lock2.lock();
        try {
            if (!this.closed) {
                Unit unit = Unit.INSTANCE;
                lock2.unlock();
                return protectedRead(j, bArr, i, i2);
            }
            throw new IllegalStateException("closed".toString());
        } catch (Throwable th) {
            lock2.unlock();
            throw th;
        }
    }

    /* JADX INFO: finally extract failed */
    public final long read(long j, Buffer buffer, long j2) throws IOException {
        Intrinsics.checkNotNullParameter(buffer, "sink");
        Lock lock2 = this.lock;
        lock2.lock();
        try {
            if (!this.closed) {
                Unit unit = Unit.INSTANCE;
                lock2.unlock();
                return readNoCloseCheck(j, buffer, j2);
            }
            throw new IllegalStateException("closed".toString());
        } catch (Throwable th) {
            lock2.unlock();
            throw th;
        }
    }

    /* JADX INFO: finally extract failed */
    public final long size() throws IOException {
        Lock lock2 = this.lock;
        lock2.lock();
        try {
            if (!this.closed) {
                Unit unit = Unit.INSTANCE;
                lock2.unlock();
                return protectedSize();
            }
            throw new IllegalStateException("closed".toString());
        } catch (Throwable th) {
            lock2.unlock();
            throw th;
        }
    }

    /* JADX INFO: finally extract failed */
    public final void resize(long j) throws IOException {
        if (this.readWrite) {
            Lock lock2 = this.lock;
            lock2.lock();
            try {
                if (!this.closed) {
                    Unit unit = Unit.INSTANCE;
                    lock2.unlock();
                    protectedResize(j);
                    return;
                }
                throw new IllegalStateException("closed".toString());
            } catch (Throwable th) {
                lock2.unlock();
                throw th;
            }
        } else {
            throw new IllegalStateException("file handle is read-only".toString());
        }
    }

    /* JADX INFO: finally extract failed */
    public final void write(long j, byte[] bArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(bArr, "array");
        if (this.readWrite) {
            Lock lock2 = this.lock;
            lock2.lock();
            try {
                if (!this.closed) {
                    Unit unit = Unit.INSTANCE;
                    lock2.unlock();
                    protectedWrite(j, bArr, i, i2);
                    return;
                }
                throw new IllegalStateException("closed".toString());
            } catch (Throwable th) {
                lock2.unlock();
                throw th;
            }
        } else {
            throw new IllegalStateException("file handle is read-only".toString());
        }
    }

    /* JADX INFO: finally extract failed */
    public final void write(long j, Buffer buffer, long j2) throws IOException {
        Intrinsics.checkNotNullParameter(buffer, "source");
        if (this.readWrite) {
            Lock lock2 = this.lock;
            lock2.lock();
            try {
                if (!this.closed) {
                    Unit unit = Unit.INSTANCE;
                    lock2.unlock();
                    writeNoCloseCheck(j, buffer, j2);
                    return;
                }
                throw new IllegalStateException("closed".toString());
            } catch (Throwable th) {
                lock2.unlock();
                throw th;
            }
        } else {
            throw new IllegalStateException("file handle is read-only".toString());
        }
    }

    /* JADX INFO: finally extract failed */
    public final void flush() throws IOException {
        if (this.readWrite) {
            Lock lock2 = this.lock;
            lock2.lock();
            try {
                if (!this.closed) {
                    Unit unit = Unit.INSTANCE;
                    lock2.unlock();
                    protectedFlush();
                    return;
                }
                throw new IllegalStateException("closed".toString());
            } catch (Throwable th) {
                lock2.unlock();
                throw th;
            }
        } else {
            throw new IllegalStateException("file handle is read-only".toString());
        }
    }

    public static /* synthetic */ Source source$default(FileHandle fileHandle, long j, int i, Object obj) throws IOException {
        if (obj == null) {
            if ((i & 1) != 0) {
                j = 0;
            }
            return fileHandle.source(j);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: source");
    }

    /* JADX INFO: finally extract failed */
    public final Source source(long j) throws IOException {
        Lock lock2 = this.lock;
        lock2.lock();
        try {
            if (!this.closed) {
                this.openStreamCount++;
                lock2.unlock();
                return new FileHandleSource(this, j);
            }
            throw new IllegalStateException("closed".toString());
        } catch (Throwable th) {
            lock2.unlock();
            throw th;
        }
    }

    public final long position(Source source) throws IOException {
        long j;
        Intrinsics.checkNotNullParameter(source, "source");
        if (source instanceof RealBufferedSource) {
            RealBufferedSource realBufferedSource = (RealBufferedSource) source;
            j = realBufferedSource.bufferField.size();
            source = realBufferedSource.source;
        } else {
            j = 0;
        }
        if ((source instanceof FileHandleSource) && ((FileHandleSource) source).getFileHandle() == this) {
            FileHandleSource fileHandleSource = (FileHandleSource) source;
            if (!fileHandleSource.getClosed()) {
                return fileHandleSource.getPosition() - j;
            }
            throw new IllegalStateException("closed".toString());
        }
        throw new IllegalArgumentException("source was not created by this FileHandle".toString());
    }

    public final void reposition(Source source, long j) throws IOException {
        Intrinsics.checkNotNullParameter(source, "source");
        boolean z = false;
        if (source instanceof RealBufferedSource) {
            RealBufferedSource realBufferedSource = (RealBufferedSource) source;
            Source source2 = realBufferedSource.source;
            if ((source2 instanceof FileHandleSource) && ((FileHandleSource) source2).getFileHandle() == this) {
                FileHandleSource fileHandleSource = (FileHandleSource) source2;
                if (!fileHandleSource.getClosed()) {
                    long size = realBufferedSource.bufferField.size();
                    long position = j - (fileHandleSource.getPosition() - size);
                    if (0 <= position && position < size) {
                        z = true;
                    }
                    if (z) {
                        realBufferedSource.skip(position);
                        return;
                    }
                    realBufferedSource.bufferField.clear();
                    fileHandleSource.setPosition(j);
                    return;
                }
                throw new IllegalStateException("closed".toString());
            }
            throw new IllegalArgumentException("source was not created by this FileHandle".toString());
        }
        if ((source instanceof FileHandleSource) && ((FileHandleSource) source).getFileHandle() == this) {
            z = true;
        }
        if (z) {
            FileHandleSource fileHandleSource2 = (FileHandleSource) source;
            if (!fileHandleSource2.getClosed()) {
                fileHandleSource2.setPosition(j);
                return;
            }
            throw new IllegalStateException("closed".toString());
        }
        throw new IllegalArgumentException("source was not created by this FileHandle".toString());
    }

    public static /* synthetic */ Sink sink$default(FileHandle fileHandle, long j, int i, Object obj) throws IOException {
        if (obj == null) {
            if ((i & 1) != 0) {
                j = 0;
            }
            return fileHandle.sink(j);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: sink");
    }

    /* JADX INFO: finally extract failed */
    public final Sink sink(long j) throws IOException {
        if (this.readWrite) {
            Lock lock2 = this.lock;
            lock2.lock();
            try {
                if (!this.closed) {
                    this.openStreamCount++;
                    lock2.unlock();
                    return new FileHandleSink(this, j);
                }
                throw new IllegalStateException("closed".toString());
            } catch (Throwable th) {
                lock2.unlock();
                throw th;
            }
        } else {
            throw new IllegalStateException("file handle is read-only".toString());
        }
    }

    public final Sink appendingSink() throws IOException {
        return sink(size());
    }

    public final long position(Sink sink) throws IOException {
        long j;
        Intrinsics.checkNotNullParameter(sink, "sink");
        if (sink instanceof RealBufferedSink) {
            RealBufferedSink realBufferedSink = (RealBufferedSink) sink;
            j = realBufferedSink.bufferField.size();
            sink = realBufferedSink.sink;
        } else {
            j = 0;
        }
        if ((sink instanceof FileHandleSink) && ((FileHandleSink) sink).getFileHandle() == this) {
            FileHandleSink fileHandleSink = (FileHandleSink) sink;
            if (!fileHandleSink.getClosed()) {
                return fileHandleSink.getPosition() + j;
            }
            throw new IllegalStateException("closed".toString());
        }
        throw new IllegalArgumentException("sink was not created by this FileHandle".toString());
    }

    public final void reposition(Sink sink, long j) throws IOException {
        Intrinsics.checkNotNullParameter(sink, "sink");
        boolean z = false;
        if (sink instanceof RealBufferedSink) {
            RealBufferedSink realBufferedSink = (RealBufferedSink) sink;
            Sink sink2 = realBufferedSink.sink;
            if ((sink2 instanceof FileHandleSink) && ((FileHandleSink) sink2).getFileHandle() == this) {
                z = true;
            }
            if (z) {
                FileHandleSink fileHandleSink = (FileHandleSink) sink2;
                if (!fileHandleSink.getClosed()) {
                    realBufferedSink.emit();
                    fileHandleSink.setPosition(j);
                    return;
                }
                throw new IllegalStateException("closed".toString());
            }
            throw new IllegalArgumentException("sink was not created by this FileHandle".toString());
        }
        if ((sink instanceof FileHandleSink) && ((FileHandleSink) sink).getFileHandle() == this) {
            z = true;
        }
        if (z) {
            FileHandleSink fileHandleSink2 = (FileHandleSink) sink;
            if (!fileHandleSink2.getClosed()) {
                fileHandleSink2.setPosition(j);
                return;
            }
            throw new IllegalStateException("closed".toString());
        }
        throw new IllegalArgumentException("sink was not created by this FileHandle".toString());
    }

    public final void close() throws IOException {
        Lock lock2 = this.lock;
        lock2.lock();
        try {
            if (!this.closed) {
                this.closed = true;
                if (this.openStreamCount != 0) {
                    lock2.unlock();
                    return;
                }
                Unit unit = Unit.INSTANCE;
                lock2.unlock();
                protectedClose();
            }
        } finally {
            lock2.unlock();
        }
    }

    /* access modifiers changed from: private */
    public final long readNoCloseCheck(long j, Buffer buffer, long j2) {
        Buffer buffer2 = buffer;
        long j3 = j2;
        if (j3 >= 0) {
            long j4 = j + j3;
            long j5 = j;
            while (true) {
                if (j5 >= j4) {
                    break;
                }
                Segment writableSegment$okio = buffer2.writableSegment$okio(1);
                int protectedRead = protectedRead(j5, writableSegment$okio.data, writableSegment$okio.limit, (int) Math.min(j4 - j5, (long) (8192 - writableSegment$okio.limit)));
                if (protectedRead == -1) {
                    if (writableSegment$okio.pos == writableSegment$okio.limit) {
                        buffer2.head = writableSegment$okio.pop();
                        SegmentPool.recycle(writableSegment$okio);
                    }
                    if (j == j5) {
                        return -1;
                    }
                } else {
                    writableSegment$okio.limit += protectedRead;
                    long j6 = (long) protectedRead;
                    j5 += j6;
                    buffer2.setSize$okio(buffer.size() + j6);
                }
            }
            return j5 - j;
        }
        throw new IllegalArgumentException(("byteCount < 0: " + j3).toString());
    }

    /* access modifiers changed from: private */
    public final void writeNoCloseCheck(long j, Buffer buffer, long j2) {
        SegmentedByteString.checkOffsetAndCount(buffer.size(), 0, j2);
        long j3 = j2 + j;
        while (j < j3) {
            Segment segment = buffer.head;
            Intrinsics.checkNotNull(segment);
            int min = (int) Math.min(j3 - j, (long) (segment.limit - segment.pos));
            protectedWrite(j, segment.data, segment.pos, min);
            segment.pos += min;
            long j4 = (long) min;
            j += j4;
            buffer.setSize$okio(buffer.size() - j4);
            if (segment.pos == segment.limit) {
                buffer.head = segment.pop();
                SegmentPool.recycle(segment);
            }
        }
    }

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u0014H\u0016J\b\u0010\u0016\u001a\u00020\u0017H\u0016J\u0018\u0010\u0018\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0005H\u0016R\u001a\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001a\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012¨\u0006\u001c"}, d2 = {"Lokio/FileHandle$FileHandleSink;", "Lokio/Sink;", "fileHandle", "Lokio/FileHandle;", "position", "", "(Lokio/FileHandle;J)V", "closed", "", "getClosed", "()Z", "setClosed", "(Z)V", "getFileHandle", "()Lokio/FileHandle;", "getPosition", "()J", "setPosition", "(J)V", "close", "", "flush", "timeout", "Lokio/Timeout;", "write", "source", "Lokio/Buffer;", "byteCount", "okio"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: FileHandle.kt */
    private static final class FileHandleSink implements Sink {
        private boolean closed;
        private final FileHandle fileHandle;
        private long position;

        public FileHandleSink(FileHandle fileHandle2, long j) {
            Intrinsics.checkNotNullParameter(fileHandle2, "fileHandle");
            this.fileHandle = fileHandle2;
            this.position = j;
        }

        public final FileHandle getFileHandle() {
            return this.fileHandle;
        }

        public final long getPosition() {
            return this.position;
        }

        public final void setPosition(long j) {
            this.position = j;
        }

        public final boolean getClosed() {
            return this.closed;
        }

        public final void setClosed(boolean z) {
            this.closed = z;
        }

        public void write(Buffer buffer, long j) {
            Intrinsics.checkNotNullParameter(buffer, "source");
            if (!this.closed) {
                this.fileHandle.writeNoCloseCheck(this.position, buffer, j);
                this.position += j;
                return;
            }
            throw new IllegalStateException("closed".toString());
        }

        public void flush() {
            if (!this.closed) {
                this.fileHandle.protectedFlush();
                return;
            }
            throw new IllegalStateException("closed".toString());
        }

        public Timeout timeout() {
            return Timeout.NONE;
        }

        public void close() {
            if (!this.closed) {
                this.closed = true;
                Lock lock = this.fileHandle.getLock();
                lock.lock();
                try {
                    FileHandle fileHandle2 = this.fileHandle;
                    fileHandle2.openStreamCount = fileHandle2.openStreamCount - 1;
                    if (this.fileHandle.openStreamCount == 0) {
                        if (this.fileHandle.closed) {
                            Unit unit = Unit.INSTANCE;
                            lock.unlock();
                            this.fileHandle.protectedClose();
                        }
                    }
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\u0018\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0005H\u0016J\b\u0010\u0019\u001a\u00020\u001aH\u0016R\u001a\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001a\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012¨\u0006\u001b"}, d2 = {"Lokio/FileHandle$FileHandleSource;", "Lokio/Source;", "fileHandle", "Lokio/FileHandle;", "position", "", "(Lokio/FileHandle;J)V", "closed", "", "getClosed", "()Z", "setClosed", "(Z)V", "getFileHandle", "()Lokio/FileHandle;", "getPosition", "()J", "setPosition", "(J)V", "close", "", "read", "sink", "Lokio/Buffer;", "byteCount", "timeout", "Lokio/Timeout;", "okio"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: FileHandle.kt */
    private static final class FileHandleSource implements Source {
        private boolean closed;
        private final FileHandle fileHandle;
        private long position;

        public FileHandleSource(FileHandle fileHandle2, long j) {
            Intrinsics.checkNotNullParameter(fileHandle2, "fileHandle");
            this.fileHandle = fileHandle2;
            this.position = j;
        }

        public final FileHandle getFileHandle() {
            return this.fileHandle;
        }

        public final long getPosition() {
            return this.position;
        }

        public final void setPosition(long j) {
            this.position = j;
        }

        public final boolean getClosed() {
            return this.closed;
        }

        public final void setClosed(boolean z) {
            this.closed = z;
        }

        public long read(Buffer buffer, long j) {
            Intrinsics.checkNotNullParameter(buffer, "sink");
            if (!this.closed) {
                long access$readNoCloseCheck = this.fileHandle.readNoCloseCheck(this.position, buffer, j);
                if (access$readNoCloseCheck != -1) {
                    this.position += access$readNoCloseCheck;
                }
                return access$readNoCloseCheck;
            }
            throw new IllegalStateException("closed".toString());
        }

        public Timeout timeout() {
            return Timeout.NONE;
        }

        public void close() {
            if (!this.closed) {
                this.closed = true;
                Lock lock = this.fileHandle.getLock();
                lock.lock();
                try {
                    FileHandle fileHandle2 = this.fileHandle;
                    fileHandle2.openStreamCount = fileHandle2.openStreamCount - 1;
                    if (this.fileHandle.openStreamCount == 0) {
                        if (this.fileHandle.closed) {
                            Unit unit = Unit.INSTANCE;
                            lock.unlock();
                            this.fileHandle.protectedClose();
                        }
                    }
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}
