package com.facebook.imagepipeline.memory;

import android.graphics.Bitmap;
import com.facebook.common.memory.MemoryTrimType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0011\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0004H\u0016J\u0010\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\fH\u0016¨\u0006\r"}, d2 = {"Lcom/facebook/imagepipeline/memory/DummyBitmapPool;", "Lcom/facebook/imagepipeline/memory/BitmapPool;", "()V", "get", "Landroid/graphics/Bitmap;", "size", "", "release", "", "value", "trim", "trimType", "Lcom/facebook/common/memory/MemoryTrimType;", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: DummyBitmapPool.kt */
public final class DummyBitmapPool implements BitmapPool {
    public void trim(MemoryTrimType memoryTrimType) {
        Intrinsics.checkNotNullParameter(memoryTrimType, "trimType");
    }

    public Bitmap get(int i) {
        Bitmap createBitmap = Bitmap.createBitmap(1, (int) Math.ceil(((double) i) / 2.0d), Bitmap.Config.RGB_565);
        Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(\n          …   Bitmap.Config.RGB_565)");
        return createBitmap;
    }

    public void release(Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(bitmap, "value");
        bitmap.recycle();
    }
}
