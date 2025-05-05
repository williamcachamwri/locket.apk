package com.facebook.fresco.animation.bitmap.preparation.loadframe;

import android.graphics.Bitmap;
import com.facebook.common.references.CloseableReference;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u0013B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J*\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u00062\u0006\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0003J0\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\u00032\u0018\u0010\f\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\u00062\u0006\u0010\t\u001a\u00020\u0003J6\u0010\u000f\u001a\u00020\u000b2\u0018\u0010\f\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\u00062\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0006H\u0002J\n\u0010\u0011\u001a\u00020\u0012*\u00020\u0003R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/facebook/fresco/animation/bitmap/preparation/loadframe/FpsCompressorInfo;", "", "maxFpsLimit", "", "(I)V", "calculateReducedIndexes", "", "durationMs", "frameCount", "targetFps", "compress", "Lcom/facebook/fresco/animation/bitmap/preparation/loadframe/FpsCompressorInfo$CompressionResult;", "frameBitmaps", "Lcom/facebook/common/references/CloseableReference;", "Landroid/graphics/Bitmap;", "compressAnimation", "realToReducedIndex", "millisecondsToSeconds", "", "CompressionResult", "animated-drawable_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: FpsCompressorInfo.kt */
public final class FpsCompressorInfo {
    private final int maxFpsLimit;

    public final float millisecondsToSeconds(int i) {
        return ((float) i) / 1000.0f;
    }

    public FpsCompressorInfo(int i) {
        this.maxFpsLimit = i;
    }

    public final CompressionResult compress(int i, Map<Integer, ? extends CloseableReference<Bitmap>> map, int i2) {
        Intrinsics.checkNotNullParameter(map, "frameBitmaps");
        return compressAnimation(map, calculateReducedIndexes(i, map.size(), i2));
    }

    public final Map<Integer, Integer> calculateReducedIndexes(int i, int i2, int i3) {
        float coerceAtLeast = RangesKt.coerceAtLeast(((float) RangesKt.coerceAtMost(RangesKt.coerceAtLeast(i3, 1), this.maxFpsLimit)) * millisecondsToSeconds(i), 0.0f);
        float f = (float) i2;
        float coerceAtMost = f / RangesKt.coerceAtMost(coerceAtLeast, f);
        int i4 = 0;
        Iterable until = RangesKt.until(0, i2);
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(until, 10)), 16));
        for (Object next : until) {
            Map map = linkedHashMap;
            int intValue = ((Number) next).intValue();
            if (((int) (((float) intValue) % coerceAtMost)) == 0) {
                i4 = intValue;
            }
            map.put(next, Integer.valueOf(i4));
        }
        return linkedHashMap;
    }

    private final CompressionResult compressAnimation(Map<Integer, ? extends CloseableReference<Bitmap>> map, Map<Integer, Integer> map2) {
        Map linkedHashMap = new LinkedHashMap();
        List arrayList = new ArrayList();
        for (Map.Entry next : map.entrySet()) {
            int intValue = ((Number) next.getKey()).intValue();
            CloseableReference closeableReference = (CloseableReference) next.getValue();
            Integer num = map2.get(Integer.valueOf(intValue));
            if (num != null) {
                int intValue2 = num.intValue();
                if (linkedHashMap.containsKey(Integer.valueOf(intValue2))) {
                    arrayList.add(closeableReference);
                } else {
                    linkedHashMap.put(Integer.valueOf(intValue2), closeableReference);
                }
            }
        }
        return new CompressionResult(linkedHashMap, map2, arrayList);
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0007\u0018\u00002\u00020\u0001BG\u0012\u0018\u0010\u0002\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0003\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0012\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\t¢\u0006\u0002\u0010\nR#\u0010\u0002\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001d\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u001d\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\t¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lcom/facebook/fresco/animation/bitmap/preparation/loadframe/FpsCompressorInfo$CompressionResult;", "", "compressedAnim", "", "", "Lcom/facebook/common/references/CloseableReference;", "Landroid/graphics/Bitmap;", "realToReducedIndex", "removedFrames", "", "(Ljava/util/Map;Ljava/util/Map;Ljava/util/List;)V", "getCompressedAnim", "()Ljava/util/Map;", "getRealToReducedIndex", "getRemovedFrames", "()Ljava/util/List;", "animated-drawable_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: FpsCompressorInfo.kt */
    public static final class CompressionResult {
        private final Map<Integer, CloseableReference<Bitmap>> compressedAnim;
        private final Map<Integer, Integer> realToReducedIndex;
        private final List<CloseableReference<Bitmap>> removedFrames;

        public CompressionResult(Map<Integer, ? extends CloseableReference<Bitmap>> map, Map<Integer, Integer> map2, List<? extends CloseableReference<Bitmap>> list) {
            Intrinsics.checkNotNullParameter(map, "compressedAnim");
            Intrinsics.checkNotNullParameter(map2, "realToReducedIndex");
            Intrinsics.checkNotNullParameter(list, "removedFrames");
            this.compressedAnim = map;
            this.realToReducedIndex = map2;
            this.removedFrames = list;
        }

        public final Map<Integer, CloseableReference<Bitmap>> getCompressedAnim() {
            return this.compressedAnim;
        }

        public final Map<Integer, Integer> getRealToReducedIndex() {
            return this.realToReducedIndex;
        }

        public final List<CloseableReference<Bitmap>> getRemovedFrames() {
            return this.removedFrames;
        }
    }
}
