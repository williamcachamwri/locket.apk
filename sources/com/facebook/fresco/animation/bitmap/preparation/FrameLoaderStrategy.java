package com.facebook.fresco.animation.bitmap.preparation;

import android.graphics.Bitmap;
import com.facebook.common.references.CloseableReference;
import com.facebook.fresco.animation.backend.AnimationBackend;
import com.facebook.fresco.animation.backend.AnimationInformation;
import com.facebook.fresco.animation.bitmap.BitmapFrameCache;
import com.facebook.fresco.animation.bitmap.BitmapFrameRenderer;
import com.facebook.fresco.animation.bitmap.preparation.BitmapFramePreparationStrategy;
import com.facebook.fresco.animation.bitmap.preparation.ondemandanimation.AnimationCoordinator;
import com.facebook.fresco.animation.bitmap.preparation.ondemandanimation.FrameLoader;
import com.facebook.fresco.animation.bitmap.preparation.ondemandanimation.FrameLoaderFactory;
import com.facebook.fresco.animation.bitmap.preparation.ondemandanimation.FrameResult;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

@Metadata(d1 = {"\u0000c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0013\u0018\u00002\u00020\u0001B/\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u0018\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\u000eH\u0002J\b\u0010\u001e\u001a\u00020\u001fH\u0016J(\u0010 \u001a\n\u0012\u0004\u0012\u00020\"\u0018\u00010!2\u0006\u0010#\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\u000eH\u0017J\b\u0010$\u001a\u00020\u001fH\u0016J(\u0010%\u001a\u00020\u001f2\u0006\u0010\u001c\u001a\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\u000e2\u000e\u0010&\u001a\n\u0012\u0004\u0012\u00020\u001f\u0018\u00010'H\u0017J\f\u0010(\u001a\u00020\u000e*\u00020\u0005H\u0002R\u000e\u0010\r\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000eX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u00020\u0013X\u0004¢\u0006\u0004\n\u0002\u0010\u0014R\u0018\u0010\u0015\u001a\u0004\u0018\u00010\u00168BX\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000¨\u0006)"}, d2 = {"Lcom/facebook/fresco/animation/bitmap/preparation/FrameLoaderStrategy;", "Lcom/facebook/fresco/animation/bitmap/preparation/BitmapFramePreparationStrategy;", "source", "", "animationInformation", "Lcom/facebook/fresco/animation/backend/AnimationInformation;", "bitmapFrameRenderer", "Lcom/facebook/fresco/animation/bitmap/BitmapFrameRenderer;", "frameLoaderFactory", "Lcom/facebook/fresco/animation/bitmap/preparation/ondemandanimation/FrameLoaderFactory;", "downscaleFrameToDrawableDimensions", "", "(Ljava/lang/String;Lcom/facebook/fresco/animation/backend/AnimationInformation;Lcom/facebook/fresco/animation/bitmap/BitmapFrameRenderer;Lcom/facebook/fresco/animation/bitmap/preparation/ondemandanimation/FrameLoaderFactory;Z)V", "animationHeight", "", "animationWidth", "cacheKey", "currentFps", "dynamicFpsRender", "com/facebook/fresco/animation/bitmap/preparation/FrameLoaderStrategy$dynamicFpsRender$1", "Lcom/facebook/fresco/animation/bitmap/preparation/FrameLoaderStrategy$dynamicFpsRender$1;", "frameLoader", "Lcom/facebook/fresco/animation/bitmap/preparation/ondemandanimation/FrameLoader;", "getFrameLoader", "()Lcom/facebook/fresco/animation/bitmap/preparation/ondemandanimation/FrameLoader;", "maxAnimationFps", "calculateFrameSize", "Lcom/facebook/fresco/animation/bitmap/preparation/FrameSize;", "canvasWidth", "canvasHeight", "clearFrames", "", "getBitmapFrame", "Lcom/facebook/common/references/CloseableReference;", "Landroid/graphics/Bitmap;", "frameNumber", "onStop", "prepareFrames", "onAnimationLoaded", "Lkotlin/Function0;", "fps", "animated-drawable_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: FrameLoaderStrategy.kt */
public final class FrameLoaderStrategy implements BitmapFramePreparationStrategy {
    private final int animationHeight;
    private final AnimationInformation animationInformation;
    private final int animationWidth;
    private final BitmapFrameRenderer bitmapFrameRenderer;
    private final String cacheKey;
    /* access modifiers changed from: private */
    public int currentFps;
    private final boolean downscaleFrameToDrawableDimensions;
    private final FrameLoaderStrategy$dynamicFpsRender$1 dynamicFpsRender;
    private FrameLoader frameLoader;
    private final FrameLoaderFactory frameLoaderFactory;
    /* access modifiers changed from: private */
    public final int maxAnimationFps;

    public FrameLoaderStrategy(String str, AnimationInformation animationInformation2, BitmapFrameRenderer bitmapFrameRenderer2, FrameLoaderFactory frameLoaderFactory2, boolean z) {
        Intrinsics.checkNotNullParameter(animationInformation2, "animationInformation");
        Intrinsics.checkNotNullParameter(bitmapFrameRenderer2, "bitmapFrameRenderer");
        Intrinsics.checkNotNullParameter(frameLoaderFactory2, "frameLoaderFactory");
        this.animationInformation = animationInformation2;
        this.bitmapFrameRenderer = bitmapFrameRenderer2;
        this.frameLoaderFactory = frameLoaderFactory2;
        this.downscaleFrameToDrawableDimensions = z;
        this.cacheKey = str == null ? String.valueOf(hashCode()) : str;
        this.animationWidth = animationInformation2.width();
        this.animationHeight = animationInformation2.height();
        int fps = fps(animationInformation2);
        this.maxAnimationFps = fps;
        this.currentFps = fps;
        this.dynamicFpsRender = new FrameLoaderStrategy$dynamicFpsRender$1(this);
    }

    public void prepareFrames(BitmapFramePreparer bitmapFramePreparer, BitmapFrameCache bitmapFrameCache, AnimationBackend animationBackend, int i, Function0<Unit> function0) {
        BitmapFramePreparationStrategy.DefaultImpls.prepareFrames(this, bitmapFramePreparer, bitmapFrameCache, animationBackend, i, function0);
    }

    /* access modifiers changed from: private */
    public final FrameLoader getFrameLoader() {
        if (this.frameLoader == null) {
            this.frameLoader = this.frameLoaderFactory.createBufferLoader(this.cacheKey, this.bitmapFrameRenderer, this.animationInformation);
        }
        return this.frameLoader;
    }

    public void prepareFrames(int i, int i2, Function0<Unit> function0) {
        if (i > 0 && i2 > 0 && this.animationWidth > 0 && this.animationHeight > 0) {
            FrameSize calculateFrameSize = calculateFrameSize(i, i2);
            FrameLoader frameLoader2 = getFrameLoader();
            if (frameLoader2 != null) {
                int width = calculateFrameSize.getWidth();
                int width2 = calculateFrameSize.getWidth();
                if (function0 == null) {
                    function0 = FrameLoaderStrategy$prepareFrames$1.INSTANCE;
                }
                frameLoader2.prepareFrames(width, width2, function0);
            }
        }
    }

    public CloseableReference<Bitmap> getBitmapFrame(int i, int i2, int i3) {
        FrameSize calculateFrameSize = calculateFrameSize(i2, i3);
        FrameLoader frameLoader2 = getFrameLoader();
        FrameResult frame = frameLoader2 != null ? frameLoader2.getFrame(i, calculateFrameSize.getWidth(), calculateFrameSize.getHeight()) : null;
        if (frame != null) {
            AnimationCoordinator.INSTANCE.onRenderFrame(this.dynamicFpsRender, frame);
        }
        if (frame != null) {
            return frame.getBitmapRef();
        }
        return null;
    }

    public void onStop() {
        FrameLoader frameLoader2 = getFrameLoader();
        if (frameLoader2 != null) {
            frameLoader2.onStop();
        }
        clearFrames();
    }

    public void clearFrames() {
        FrameLoader frameLoader2 = getFrameLoader();
        if (frameLoader2 != null) {
            FrameLoaderFactory.Companion.saveUnusedFrame(this.cacheKey, frameLoader2);
        }
        this.frameLoader = null;
    }

    private final FrameSize calculateFrameSize(int i, int i2) {
        if (!this.downscaleFrameToDrawableDimensions) {
            return new FrameSize(this.animationWidth, this.animationHeight);
        }
        int i3 = this.animationWidth;
        int i4 = this.animationHeight;
        if (i < i3 || i2 < i4) {
            double d = ((double) i3) / ((double) i4);
            if (i2 > i) {
                i4 = RangesKt.coerceAtMost(i2, i4);
                i3 = (int) (((double) i4) * d);
            } else {
                i3 = RangesKt.coerceAtMost(i, i3);
                i4 = (int) (((double) i3) / d);
            }
        }
        return new FrameSize(i3, i4);
    }

    private final int fps(AnimationInformation animationInformation2) {
        return (int) RangesKt.coerceAtLeast(TimeUnit.SECONDS.toMillis(1) / ((long) (animationInformation2.getLoopDurationMs() / animationInformation2.getFrameCount())), 1);
    }
}
