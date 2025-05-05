package com.facebook.imagepipeline.memory;

import com.facebook.common.memory.PooledByteBufferOutputStream;
import com.facebook.common.references.CloseableReference;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001:\u0001\u0018B\u0019\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\fH\u0002J\u0010\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u0005H\u0007J\b\u0010\u0010\u001a\u00020\u0005H\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016J \u0010\u0013\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u0005H\u0016J\u0010\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u0005H\u0016R\u0016\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/facebook/imagepipeline/memory/MemoryPooledByteBufferOutputStream;", "Lcom/facebook/common/memory/PooledByteBufferOutputStream;", "pool", "Lcom/facebook/imagepipeline/memory/MemoryChunkPool;", "initialCapacity", "", "(Lcom/facebook/imagepipeline/memory/MemoryChunkPool;I)V", "bufRef", "Lcom/facebook/common/references/CloseableReference;", "Lcom/facebook/imagepipeline/memory/MemoryChunk;", "count", "close", "", "ensureValid", "realloc", "newLength", "size", "toByteBuffer", "Lcom/facebook/imagepipeline/memory/MemoryPooledByteBuffer;", "write", "buffer", "", "offset", "oneByte", "InvalidStreamException", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: MemoryPooledByteBufferOutputStream.kt */
public final class MemoryPooledByteBufferOutputStream extends PooledByteBufferOutputStream {
    private CloseableReference<MemoryChunk> bufRef;
    private int count;
    private final MemoryChunkPool pool;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public MemoryPooledByteBufferOutputStream(MemoryChunkPool memoryChunkPool) {
        this(memoryChunkPool, 0, 2, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(memoryChunkPool, "pool");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ MemoryPooledByteBufferOutputStream(MemoryChunkPool memoryChunkPool, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(memoryChunkPool, (i2 & 2) != 0 ? memoryChunkPool.getMinBufferSize() : i);
    }

    public MemoryPooledByteBufferOutputStream(MemoryChunkPool memoryChunkPool, int i) {
        Intrinsics.checkNotNullParameter(memoryChunkPool, "pool");
        if (i > 0) {
            this.pool = memoryChunkPool;
            this.count = 0;
            this.bufRef = CloseableReference.of(memoryChunkPool.get(i), memoryChunkPool);
            return;
        }
        throw new IllegalStateException("Check failed.".toString());
    }

    public MemoryPooledByteBuffer toByteBuffer() {
        ensureValid();
        CloseableReference<MemoryChunk> closeableReference = this.bufRef;
        if (closeableReference != null) {
            return new MemoryPooledByteBuffer(closeableReference, this.count);
        }
        throw new IllegalStateException("Required value was null.".toString());
    }

    public int size() {
        return this.count;
    }

    public void write(int i) throws IOException {
        write(new byte[]{(byte) i});
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        Intrinsics.checkNotNullParameter(bArr, "buffer");
        if (i < 0 || i2 < 0 || i + i2 > bArr.length) {
            throw new ArrayIndexOutOfBoundsException("length=" + bArr.length + "; regionStart=" + i + "; regionLength=" + i2);
        }
        ensureValid();
        realloc(this.count + i2);
        CloseableReference<MemoryChunk> closeableReference = this.bufRef;
        if (closeableReference != null) {
            closeableReference.get().write(this.count, bArr, i, i2);
            this.count += i2;
            return;
        }
        throw new IllegalStateException("Required value was null.".toString());
    }

    public void close() {
        CloseableReference.closeSafely((CloseableReference<?>) this.bufRef);
        this.bufRef = null;
        this.count = -1;
        super.close();
    }

    public final void realloc(int i) {
        ensureValid();
        CloseableReference<MemoryChunk> closeableReference = this.bufRef;
        if (closeableReference != null) {
            Intrinsics.checkNotNull(closeableReference);
            if (i > closeableReference.get().getSize()) {
                Object obj = this.pool.get(i);
                Intrinsics.checkNotNullExpressionValue(obj, "this.pool[newLength]");
                MemoryChunk memoryChunk = (MemoryChunk) obj;
                CloseableReference<MemoryChunk> closeableReference2 = this.bufRef;
                if (closeableReference2 != null) {
                    Intrinsics.checkNotNull(closeableReference2);
                    closeableReference2.get().copy(0, memoryChunk, 0, this.count);
                    CloseableReference<MemoryChunk> closeableReference3 = this.bufRef;
                    Intrinsics.checkNotNull(closeableReference3);
                    closeableReference3.close();
                    this.bufRef = CloseableReference.of(memoryChunk, this.pool);
                    return;
                }
                throw new IllegalStateException("Required value was null.".toString());
            }
            return;
        }
        throw new IllegalStateException("Required value was null.".toString());
    }

    private final void ensureValid() {
        if (!CloseableReference.isValid(this.bufRef)) {
            throw new InvalidStreamException();
        }
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0005¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/facebook/imagepipeline/memory/MemoryPooledByteBufferOutputStream$InvalidStreamException;", "Ljava/lang/RuntimeException;", "Lkotlin/RuntimeException;", "()V", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: MemoryPooledByteBufferOutputStream.kt */
    public static final class InvalidStreamException extends RuntimeException {
        public InvalidStreamException() {
            super("OutputStream no longer valid");
        }
    }
}
