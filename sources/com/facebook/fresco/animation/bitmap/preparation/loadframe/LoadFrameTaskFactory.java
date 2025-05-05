package com.facebook.fresco.animation.bitmap.preparation.loadframe;

import android.graphics.Bitmap;
import com.facebook.common.references.CloseableReference;
import com.facebook.fresco.animation.bitmap.BitmapFrameRenderer;
import com.facebook.fresco.animation.bitmap.preparation.loadframe.LoadFramePriorityTask;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\rJ&\u0010\u000e\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\rJF\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\n2\u001a\u0010\u0013\u001a\u0016\u0012\u0004\u0012\u00020\n\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u00150\u00142\u001a\u0010\f\u001a\u0016\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0015\u0012\u0004\u0012\u00020\u00170\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/facebook/fresco/animation/bitmap/preparation/loadframe/LoadFrameTaskFactory;", "", "platformBitmapFactory", "Lcom/facebook/imagepipeline/bitmaps/PlatformBitmapFactory;", "bitmapFrameRenderer", "Lcom/facebook/fresco/animation/bitmap/BitmapFrameRenderer;", "(Lcom/facebook/imagepipeline/bitmaps/PlatformBitmapFactory;Lcom/facebook/fresco/animation/bitmap/BitmapFrameRenderer;)V", "createFirstFrameTask", "Lcom/facebook/fresco/animation/bitmap/preparation/loadframe/LoadFrameTask;", "width", "", "height", "output", "Lcom/facebook/fresco/animation/bitmap/preparation/loadframe/LoadFrameOutput;", "createLoadFullAnimationTask", "frameCount", "createOnDemandTask", "Lcom/facebook/fresco/animation/bitmap/preparation/loadframe/LoadOnDemandFrameTask;", "frameNumber", "getCachedBitmap", "Lkotlin/Function1;", "Lcom/facebook/common/references/CloseableReference;", "Landroid/graphics/Bitmap;", "", "animated-drawable_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: LoadFrameTaskFactory.kt */
public final class LoadFrameTaskFactory {
    private final BitmapFrameRenderer bitmapFrameRenderer;
    private final PlatformBitmapFactory platformBitmapFactory;

    public LoadFrameTaskFactory(PlatformBitmapFactory platformBitmapFactory2, BitmapFrameRenderer bitmapFrameRenderer2) {
        Intrinsics.checkNotNullParameter(platformBitmapFactory2, "platformBitmapFactory");
        Intrinsics.checkNotNullParameter(bitmapFrameRenderer2, "bitmapFrameRenderer");
        this.platformBitmapFactory = platformBitmapFactory2;
        this.bitmapFrameRenderer = bitmapFrameRenderer2;
    }

    public final LoadFrameTask createFirstFrameTask(int i, int i2, LoadFrameOutput loadFrameOutput) {
        Intrinsics.checkNotNullParameter(loadFrameOutput, "output");
        return new LoadFrameTask(i, i2, 1, LoadFramePriorityTask.Priority.HIGH, loadFrameOutput, this.platformBitmapFactory, this.bitmapFrameRenderer);
    }

    public final LoadFrameTask createLoadFullAnimationTask(int i, int i2, int i3, LoadFrameOutput loadFrameOutput) {
        Intrinsics.checkNotNullParameter(loadFrameOutput, "output");
        return new LoadFrameTask(i, i2, i3, LoadFramePriorityTask.Priority.LOW, loadFrameOutput, this.platformBitmapFactory, this.bitmapFrameRenderer);
    }

    public final LoadOnDemandFrameTask createOnDemandTask(int i, Function1<? super Integer, ? extends CloseableReference<Bitmap>> function1, Function1<? super CloseableReference<Bitmap>, Unit> function12) {
        Intrinsics.checkNotNullParameter(function1, "getCachedBitmap");
        Intrinsics.checkNotNullParameter(function12, "output");
        return new LoadOnDemandFrameTask(i, function1, LoadFramePriorityTask.Priority.MEDIUM, function12, this.platformBitmapFactory, this.bitmapFrameRenderer);
    }
}
