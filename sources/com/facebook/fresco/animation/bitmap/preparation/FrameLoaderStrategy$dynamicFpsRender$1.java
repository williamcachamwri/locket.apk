package com.facebook.fresco.animation.bitmap.preparation;

import com.facebook.fresco.animation.bitmap.preparation.ondemandanimation.DynamicRenderingFps;
import com.facebook.fresco.animation.bitmap.preparation.ondemandanimation.FrameLoader;
import kotlin.Metadata;
import kotlin.ranges.RangesKt;

@Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u0003H\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0005R\u0014\u0010\u0006\u001a\u00020\u00038VX\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005¨\u0006\n"}, d2 = {"com/facebook/fresco/animation/bitmap/preparation/FrameLoaderStrategy$dynamicFpsRender$1", "Lcom/facebook/fresco/animation/bitmap/preparation/ondemandanimation/DynamicRenderingFps;", "animationFps", "", "getAnimationFps", "()I", "renderingFps", "getRenderingFps", "setRenderingFps", "", "animated-drawable_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: FrameLoaderStrategy.kt */
public final class FrameLoaderStrategy$dynamicFpsRender$1 implements DynamicRenderingFps {
    private final int animationFps;
    final /* synthetic */ FrameLoaderStrategy this$0;

    FrameLoaderStrategy$dynamicFpsRender$1(FrameLoaderStrategy frameLoaderStrategy) {
        this.this$0 = frameLoaderStrategy;
        this.animationFps = frameLoaderStrategy.maxAnimationFps;
    }

    public int getAnimationFps() {
        return this.animationFps;
    }

    public int getRenderingFps() {
        return this.this$0.currentFps;
    }

    public void setRenderingFps(int i) {
        if (i != this.this$0.currentFps) {
            FrameLoaderStrategy frameLoaderStrategy = this.this$0;
            frameLoaderStrategy.currentFps = RangesKt.coerceIn(i, 1, frameLoaderStrategy.maxAnimationFps);
            FrameLoader access$getFrameLoader = this.this$0.getFrameLoader();
            if (access$getFrameLoader != null) {
                access$getFrameLoader.compressToFps(this.this$0.currentFps);
            }
        }
    }
}
