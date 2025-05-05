package com.facebook.imagepipeline.memory;

import android.util.SparseIntArray;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u0004H\u0007J\b\u0010\u000e\u001a\u00020\u000fH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/facebook/imagepipeline/memory/DefaultFlexByteArrayPoolParams;", "", "()V", "DEFAULT_MAX_BYTE_ARRAY_SIZE", "", "DEFAULT_MAX_NUM_THREADS", "getDEFAULT_MAX_NUM_THREADS", "()I", "DEFAULT_MIN_BYTE_ARRAY_SIZE", "generateBuckets", "Landroid/util/SparseIntArray;", "min", "max", "numThreads", "get", "Lcom/facebook/imagepipeline/memory/PoolParams;", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: DefaultFlexByteArrayPoolParams.kt */
public final class DefaultFlexByteArrayPoolParams {
    public static final int DEFAULT_MAX_BYTE_ARRAY_SIZE = 4194304;
    private static final int DEFAULT_MAX_NUM_THREADS = Runtime.getRuntime().availableProcessors();
    private static final int DEFAULT_MIN_BYTE_ARRAY_SIZE = 131072;
    public static final DefaultFlexByteArrayPoolParams INSTANCE = new DefaultFlexByteArrayPoolParams();

    private DefaultFlexByteArrayPoolParams() {
    }

    public final int getDEFAULT_MAX_NUM_THREADS() {
        return DEFAULT_MAX_NUM_THREADS;
    }

    @JvmStatic
    public static final SparseIntArray generateBuckets(int i, int i2, int i3) {
        SparseIntArray sparseIntArray = new SparseIntArray();
        while (i <= i2) {
            sparseIntArray.put(i, i3);
            i *= 2;
        }
        return sparseIntArray;
    }

    @JvmStatic
    public static final PoolParams get() {
        int i = DEFAULT_MAX_NUM_THREADS;
        return new PoolParams(4194304, i * 4194304, generateBuckets(131072, 4194304, i), 131072, 4194304, i);
    }
}
