package com.facebook.imagepipeline.memory;

import android.util.SparseIntArray;
import com.facebook.common.memory.ByteArrayPool;
import com.facebook.common.memory.MemoryTrimmableRegistry;
import com.facebook.imagepipeline.memory.BasePool;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0006\b\u0017\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00012\u00020\u0003B\u001d\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0010\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u000eH\u0014J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0002H\u0014J\u0010\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u000eH\u0014J\u0010\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u0002H\u0014J\u0010\u0010\u0019\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u000eH\u0014R\u000e\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\r\u001a\u00020\u000e8F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001a"}, d2 = {"Lcom/facebook/imagepipeline/memory/GenericByteArrayPool;", "Lcom/facebook/imagepipeline/memory/BasePool;", "", "Lcom/facebook/common/memory/ByteArrayPool;", "memoryTrimmableRegistry", "Lcom/facebook/common/memory/MemoryTrimmableRegistry;", "poolParams", "Lcom/facebook/imagepipeline/memory/PoolParams;", "poolStatsTracker", "Lcom/facebook/imagepipeline/memory/PoolStatsTracker;", "(Lcom/facebook/common/memory/MemoryTrimmableRegistry;Lcom/facebook/imagepipeline/memory/PoolParams;Lcom/facebook/imagepipeline/memory/PoolStatsTracker;)V", "bucketSizes", "", "minBufferSize", "", "getMinBufferSize", "()I", "alloc", "bucketedSize", "free", "", "value", "getBucketedSize", "requestSize", "getBucketedSizeForValue", "getSizeInBytes", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: GenericByteArrayPool.kt */
public class GenericByteArrayPool extends BasePool<byte[]> implements ByteArrayPool {
    private final int[] bucketSizes;

    /* access modifiers changed from: protected */
    public void free(byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "value");
    }

    /* access modifiers changed from: protected */
    public int getSizeInBytes(int i) {
        return i;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public GenericByteArrayPool(MemoryTrimmableRegistry memoryTrimmableRegistry, PoolParams poolParams, PoolStatsTracker poolStatsTracker) {
        super(memoryTrimmableRegistry, poolParams, poolStatsTracker);
        Intrinsics.checkNotNullParameter(memoryTrimmableRegistry, "memoryTrimmableRegistry");
        Intrinsics.checkNotNullParameter(poolParams, "poolParams");
        Intrinsics.checkNotNullParameter(poolStatsTracker, "poolStatsTracker");
        SparseIntArray sparseIntArray = poolParams.bucketSizes;
        if (sparseIntArray != null) {
            this.bucketSizes = new int[sparseIntArray.size()];
            int size = sparseIntArray.size();
            for (int i = 0; i < size; i++) {
                this.bucketSizes[i] = sparseIntArray.keyAt(i);
            }
        } else {
            this.bucketSizes = new int[0];
        }
        initialize();
    }

    public final int getMinBufferSize() {
        int[] iArr = this.bucketSizes;
        if (iArr.length > 0) {
            return iArr[0];
        }
        return 0;
    }

    /* access modifiers changed from: protected */
    public byte[] alloc(int i) {
        return new byte[i];
    }

    /* access modifiers changed from: protected */
    public int getBucketedSize(int i) {
        if (i > 0) {
            for (int i2 : this.bucketSizes) {
                if (i2 >= i) {
                    return i2;
                }
            }
            return i;
        }
        throw new BasePool.InvalidSizeException(Integer.valueOf(i));
    }

    /* access modifiers changed from: protected */
    public int getBucketedSizeForValue(byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "value");
        return bArr.length;
    }
}
