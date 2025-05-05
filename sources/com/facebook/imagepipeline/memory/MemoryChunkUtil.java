package com.facebook.imagepipeline.memory;

import com.facebook.common.internal.Preconditions;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0004H\u0007J0\u0010\b\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0004H\u0007¨\u0006\f"}, d2 = {"Lcom/facebook/imagepipeline/memory/MemoryChunkUtil;", "", "()V", "adjustByteCount", "", "offset", "count", "memorySize", "checkBounds", "", "otherLength", "otherOffset", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: MemoryChunkUtil.kt */
public final class MemoryChunkUtil {
    public static final MemoryChunkUtil INSTANCE = new MemoryChunkUtil();

    private MemoryChunkUtil() {
    }

    @JvmStatic
    public static final int adjustByteCount(int i, int i2, int i3) {
        return Math.min(Math.max(0, i3 - i), i2);
    }

    @JvmStatic
    public static final void checkBounds(int i, int i2, int i3, int i4, int i5) {
        boolean z = true;
        Preconditions.checkArgument(i4 >= 0, "count (%d) ! >= 0", Integer.valueOf(i4));
        Preconditions.checkArgument(i >= 0, "offset (%d) ! >= 0", Integer.valueOf(i));
        Preconditions.checkArgument(i3 >= 0, "otherOffset (%d) ! >= 0", Integer.valueOf(i3));
        Preconditions.checkArgument(i + i4 <= i5, "offset (%d) + count (%d) ! <= %d", Integer.valueOf(i), Integer.valueOf(i4), Integer.valueOf(i5));
        if (i3 + i4 > i2) {
            z = false;
        }
        Preconditions.checkArgument(z, "otherOffset (%d) + count (%d) ! <= %d", Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i2));
    }
}
