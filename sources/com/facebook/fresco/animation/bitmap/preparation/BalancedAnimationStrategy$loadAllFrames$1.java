package com.facebook.fresco.animation.bitmap.preparation;

import android.graphics.Bitmap;
import android.os.SystemClock;
import com.facebook.common.references.CloseableReference;
import com.facebook.fresco.animation.bitmap.preparation.loadframe.LoadFrameOutput;
import io.sentry.protocol.SentryStackTrace;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\"\u0010\u0004\u001a\u00020\u00032\u0018\u0010\u0005\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u0006H\u0016¨\u0006\n"}, d2 = {"com/facebook/fresco/animation/bitmap/preparation/BalancedAnimationStrategy$loadAllFrames$1", "Lcom/facebook/fresco/animation/bitmap/preparation/loadframe/LoadFrameOutput;", "onFail", "", "onSuccess", "frames", "", "", "Lcom/facebook/common/references/CloseableReference;", "Landroid/graphics/Bitmap;", "animated-drawable_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: BalancedAnimationStrategy.kt */
public final class BalancedAnimationStrategy$loadAllFrames$1 implements LoadFrameOutput {
    final /* synthetic */ Function0<Unit> $notifyOnLoad;
    final /* synthetic */ BalancedAnimationStrategy this$0;

    BalancedAnimationStrategy$loadAllFrames$1(BalancedAnimationStrategy balancedAnimationStrategy, Function0<Unit> function0) {
        this.this$0 = balancedAnimationStrategy;
        this.$notifyOnLoad = function0;
    }

    public void onSuccess(Map<Integer, ? extends CloseableReference<Bitmap>> map) {
        Intrinsics.checkNotNullParameter(map, SentryStackTrace.JsonKeys.FRAMES);
        this.this$0.onDemandFrames.clear();
        SortedSet access$getOnDemandFrames$p = this.this$0.onDemandFrames;
        BalancedAnimationStrategy balancedAnimationStrategy = this.this$0;
        Map linkedHashMap = new LinkedHashMap();
        for (Map.Entry next : map.entrySet()) {
            if (balancedAnimationStrategy.isOnDemandFrame(((Number) next.getKey()).intValue())) {
                linkedHashMap.put(next.getKey(), next.getValue());
            }
        }
        Collection arrayList = new ArrayList(linkedHashMap.size());
        for (Map.Entry key : linkedHashMap.entrySet()) {
            arrayList.add(Integer.valueOf(((Number) key.getKey()).intValue()));
        }
        access$getOnDemandFrames$p.addAll((List) arrayList);
        BalancedAnimationStrategy balancedAnimationStrategy2 = this.this$0;
        Map linkedHashMap2 = new LinkedHashMap();
        for (Map.Entry next2 : map.entrySet()) {
            if (!balancedAnimationStrategy2.onDemandFrames.contains(next2.getKey())) {
                linkedHashMap2.put(next2.getKey(), next2.getValue());
            }
        }
        if (!this.this$0.bitmapCache.onAnimationPrepared(linkedHashMap2)) {
            this.this$0.nextPrepareFrames = SystemClock.uptimeMillis() + BalancedAnimationStrategy.FETCH_FULL_ANIMATION_CACHE_DELAY_MS;
        }
        Function0<Unit> function0 = this.$notifyOnLoad;
        if (function0 != null) {
            function0.invoke();
        }
        this.this$0.fetchingFrames.set(false);
    }

    public void onFail() {
        this.this$0.bitmapCache.clear();
        this.this$0.fetchingFrames.set(false);
    }
}
