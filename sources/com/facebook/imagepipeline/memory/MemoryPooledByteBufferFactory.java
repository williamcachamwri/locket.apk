package com.facebook.imagepipeline.memory;

import com.facebook.common.internal.Throwables;
import com.facebook.common.memory.PooledByteBufferFactory;
import com.facebook.common.memory.PooledByteStreams;
import com.facebook.common.references.CloseableReference;
import java.io.IOException;
import java.io.InputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0007J\u0010\u0010\r\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0018\u0010\r\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0010\u0010\r\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0010\u0010\r\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\u000fH\u0016J\b\u0010\u0013\u001a\u00020\fH\u0016J\u0010\u0010\u0013\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/facebook/imagepipeline/memory/MemoryPooledByteBufferFactory;", "Lcom/facebook/common/memory/PooledByteBufferFactory;", "pool", "Lcom/facebook/imagepipeline/memory/MemoryChunkPool;", "pooledByteStreams", "Lcom/facebook/common/memory/PooledByteStreams;", "(Lcom/facebook/imagepipeline/memory/MemoryChunkPool;Lcom/facebook/common/memory/PooledByteStreams;)V", "newByteBuf", "Lcom/facebook/imagepipeline/memory/MemoryPooledByteBuffer;", "inputStream", "Ljava/io/InputStream;", "outputStream", "Lcom/facebook/imagepipeline/memory/MemoryPooledByteBufferOutputStream;", "newByteBuffer", "initialCapacity", "", "bytes", "", "size", "newOutputStream", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: MemoryPooledByteBufferFactory.kt */
public final class MemoryPooledByteBufferFactory implements PooledByteBufferFactory {
    private final MemoryChunkPool pool;
    private final PooledByteStreams pooledByteStreams;

    public MemoryPooledByteBufferFactory(MemoryChunkPool memoryChunkPool, PooledByteStreams pooledByteStreams2) {
        Intrinsics.checkNotNullParameter(memoryChunkPool, "pool");
        Intrinsics.checkNotNullParameter(pooledByteStreams2, "pooledByteStreams");
        this.pool = memoryChunkPool;
        this.pooledByteStreams = pooledByteStreams2;
    }

    public MemoryPooledByteBuffer newByteBuffer(int i) {
        if (i > 0) {
            CloseableReference of = CloseableReference.of(this.pool.get(i), this.pool);
            Intrinsics.checkNotNullExpressionValue(of, "of(pool[size], pool)");
            try {
                return new MemoryPooledByteBuffer(of, i);
            } finally {
                of.close();
            }
        } else {
            throw new IllegalStateException("Check failed.".toString());
        }
    }

    public MemoryPooledByteBuffer newByteBuffer(InputStream inputStream) throws IOException {
        Intrinsics.checkNotNullParameter(inputStream, "inputStream");
        MemoryPooledByteBufferOutputStream memoryPooledByteBufferOutputStream = new MemoryPooledByteBufferOutputStream(this.pool, 0, 2, (DefaultConstructorMarker) null);
        try {
            return newByteBuf(inputStream, memoryPooledByteBufferOutputStream);
        } finally {
            memoryPooledByteBufferOutputStream.close();
        }
    }

    public MemoryPooledByteBuffer newByteBuffer(byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "bytes");
        MemoryPooledByteBufferOutputStream memoryPooledByteBufferOutputStream = new MemoryPooledByteBufferOutputStream(this.pool, bArr.length);
        try {
            memoryPooledByteBufferOutputStream.write(bArr, 0, bArr.length);
            MemoryPooledByteBuffer byteBuffer = memoryPooledByteBufferOutputStream.toByteBuffer();
            memoryPooledByteBufferOutputStream.close();
            return byteBuffer;
        } catch (IOException e) {
            RuntimeException propagate = Throwables.propagate(e);
            Intrinsics.checkNotNullExpressionValue(propagate, "propagate(ioe)");
            throw propagate;
        } catch (Throwable th) {
            memoryPooledByteBufferOutputStream.close();
            throw th;
        }
    }

    public MemoryPooledByteBuffer newByteBuffer(InputStream inputStream, int i) throws IOException {
        Intrinsics.checkNotNullParameter(inputStream, "inputStream");
        MemoryPooledByteBufferOutputStream memoryPooledByteBufferOutputStream = new MemoryPooledByteBufferOutputStream(this.pool, i);
        try {
            return newByteBuf(inputStream, memoryPooledByteBufferOutputStream);
        } finally {
            memoryPooledByteBufferOutputStream.close();
        }
    }

    public final MemoryPooledByteBuffer newByteBuf(InputStream inputStream, MemoryPooledByteBufferOutputStream memoryPooledByteBufferOutputStream) throws IOException {
        Intrinsics.checkNotNullParameter(inputStream, "inputStream");
        Intrinsics.checkNotNullParameter(memoryPooledByteBufferOutputStream, "outputStream");
        this.pooledByteStreams.copy(inputStream, memoryPooledByteBufferOutputStream);
        return memoryPooledByteBufferOutputStream.toByteBuffer();
    }

    public MemoryPooledByteBufferOutputStream newOutputStream() {
        return new MemoryPooledByteBufferOutputStream(this.pool, 0, 2, (DefaultConstructorMarker) null);
    }

    public MemoryPooledByteBufferOutputStream newOutputStream(int i) {
        return new MemoryPooledByteBufferOutputStream(this.pool, i);
    }
}
