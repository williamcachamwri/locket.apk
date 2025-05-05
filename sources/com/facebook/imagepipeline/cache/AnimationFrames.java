package com.facebook.imagepipeline.cache;

import android.graphics.Bitmap;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.facebook.common.references.CloseableReference;
import com.facebook.imageutils.BitmapUtil;
import java.io.Closeable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B3\u0012\u0018\u0010\u0002\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0003\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\bJ\b\u0010\u0012\u001a\u00020\u0013H\u0016J\u0016\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00052\u0006\u0010\u0015\u001a\u00020\u0004J\u0012\u0010\u0016\u001a\u00020\u0017*\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0002R6\u0010\t\u001a*\u0012\f\u0012\n \u000b*\u0004\u0018\u00010\u00040\u0004\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020\u0006 \u000b*\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00050\u00050\nX\u0004¢\u0006\u0002\n\u0000R#\u0010\f\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u00038F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u000f\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0018"}, d2 = {"Lcom/facebook/imagepipeline/cache/AnimationFrames;", "Ljava/io/Closeable;", "bitmapsByFrame", "", "", "Lcom/facebook/common/references/CloseableReference;", "Landroid/graphics/Bitmap;", "realToCompressIndexMap", "(Ljava/util/Map;Ljava/util/Map;)V", "concurrentFrames", "Ljava/util/concurrent/ConcurrentHashMap;", "kotlin.jvm.PlatformType", "frames", "getFrames", "()Ljava/util/Map;", "sizeBytes", "getSizeBytes", "()I", "close", "", "getFrame", "frameIndex", "isValidBitmap", "", "imagepipeline-base_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: AnimatedCache.kt */
public final class AnimationFrames implements Closeable {
    private final ConcurrentHashMap<Integer, CloseableReference<Bitmap>> concurrentFrames;
    private final Map<Integer, Integer> realToCompressIndexMap;
    private final int sizeBytes;

    public AnimationFrames(Map<Integer, ? extends CloseableReference<Bitmap>> map, Map<Integer, Integer> map2) {
        Intrinsics.checkNotNullParameter(map, "bitmapsByFrame");
        Intrinsics.checkNotNullParameter(map2, "realToCompressIndexMap");
        this.realToCompressIndexMap = map2;
        this.concurrentFrames = new ConcurrentHashMap<>(map);
        int i = 0;
        for (CloseableReference closeableReference : map.values()) {
            i += closeableReference.isValid() ? BitmapUtil.getSizeInBytes((Bitmap) closeableReference.get()) : 0;
        }
        this.sizeBytes = i;
    }

    public final Map<Integer, CloseableReference<Bitmap>> getFrames() {
        Map<Integer, CloseableReference<Bitmap>> linkedHashMap = new LinkedHashMap<>();
        for (Map.Entry entry : this.concurrentFrames.entrySet()) {
            CloseableReference closeableReference = (CloseableReference) entry.getValue();
            Intrinsics.checkNotNullExpressionValue(closeableReference, TypedValues.AttributesType.S_FRAME);
            if (isValidBitmap(closeableReference)) {
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        return linkedHashMap;
    }

    public final int getSizeBytes() {
        return this.sizeBytes;
    }

    public final CloseableReference<Bitmap> getFrame(int i) {
        CloseableReference<Bitmap> closeableReference;
        if (this.realToCompressIndexMap.isEmpty()) {
            closeableReference = this.concurrentFrames.get(Integer.valueOf(i));
        } else {
            Integer num = this.realToCompressIndexMap.get(Integer.valueOf(i));
            if (num == null) {
                return null;
            }
            closeableReference = this.concurrentFrames.get(Integer.valueOf(num.intValue()));
        }
        boolean z = false;
        if (closeableReference != null && isValidBitmap(closeableReference)) {
            z = true;
        }
        if (z) {
            return closeableReference;
        }
        return null;
    }

    public void close() {
        Collection<CloseableReference<Bitmap>> values = this.concurrentFrames.values();
        Intrinsics.checkNotNullExpressionValue(values, "concurrentFrames.values");
        for (CloseableReference close : values) {
            close.close();
        }
        this.concurrentFrames.clear();
    }

    private final boolean isValidBitmap(CloseableReference<Bitmap> closeableReference) {
        return closeableReference.isValid() && !closeableReference.get().isRecycled();
    }
}
