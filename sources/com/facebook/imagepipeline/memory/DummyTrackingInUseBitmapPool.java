package com.facebook.imagepipeline.memory;

import android.graphics.Bitmap;
import com.facebook.common.internal.Sets;
import com.facebook.common.memory.MemoryTrimType;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0011\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0005H\u0016J\u0010\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000eH\u0016R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/facebook/imagepipeline/memory/DummyTrackingInUseBitmapPool;", "Lcom/facebook/imagepipeline/memory/BitmapPool;", "()V", "inUseValues", "", "Landroid/graphics/Bitmap;", "get", "size", "", "release", "", "value", "trim", "trimType", "Lcom/facebook/common/memory/MemoryTrimType;", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: DummyTrackingInUseBitmapPool.kt */
public final class DummyTrackingInUseBitmapPool implements BitmapPool {
    private final Set<Bitmap> inUseValues;

    public void trim(MemoryTrimType memoryTrimType) {
        Intrinsics.checkNotNullParameter(memoryTrimType, "trimType");
    }

    public DummyTrackingInUseBitmapPool() {
        Set<Bitmap> newIdentityHashSet = Sets.newIdentityHashSet();
        Intrinsics.checkNotNullExpressionValue(newIdentityHashSet, "newIdentityHashSet()");
        this.inUseValues = newIdentityHashSet;
    }

    public Bitmap get(int i) {
        Bitmap createBitmap = Bitmap.createBitmap(1, (int) Math.ceil(((double) i) / 2.0d), Bitmap.Config.RGB_565);
        Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(\n          …   Bitmap.Config.RGB_565)");
        this.inUseValues.add(createBitmap);
        return createBitmap;
    }

    public void release(Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(bitmap, "value");
        this.inUseValues.remove(bitmap);
        bitmap.recycle();
    }
}
