package com.facebook.fresco.animation.bitmap.preparation;

import android.graphics.Bitmap;
import com.facebook.common.logging.FLog;
import com.facebook.common.references.CloseableReference;
import com.facebook.fresco.animation.backend.AnimationBackend;
import com.facebook.fresco.animation.bitmap.BitmapFrameCache;
import com.facebook.fresco.animation.bitmap.preparation.BitmapFramePreparationStrategy;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0011\b\u0007\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J8\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00032\u000e\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0011H\u0016R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00000\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/facebook/fresco/animation/bitmap/preparation/FixedNumberBitmapFramePreparationStrategy;", "Lcom/facebook/fresco/animation/bitmap/preparation/BitmapFramePreparationStrategy;", "framesToPrepare", "", "(I)V", "TAG", "Ljava/lang/Class;", "prepareFrames", "", "bitmapFramePreparer", "Lcom/facebook/fresco/animation/bitmap/preparation/BitmapFramePreparer;", "bitmapFrameCache", "Lcom/facebook/fresco/animation/bitmap/BitmapFrameCache;", "animationBackend", "Lcom/facebook/fresco/animation/backend/AnimationBackend;", "lastDrawnFrameNumber", "onAnimationLoaded", "Lkotlin/Function0;", "animated-drawable_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: FixedNumberBitmapFramePreparationStrategy.kt */
public final class FixedNumberBitmapFramePreparationStrategy implements BitmapFramePreparationStrategy {
    private final Class<FixedNumberBitmapFramePreparationStrategy> TAG;
    private final int framesToPrepare;

    public FixedNumberBitmapFramePreparationStrategy() {
        this(0, 1, (DefaultConstructorMarker) null);
    }

    public FixedNumberBitmapFramePreparationStrategy(int i) {
        this.framesToPrepare = i;
        this.TAG = FixedNumberBitmapFramePreparationStrategy.class;
    }

    public void clearFrames() {
        BitmapFramePreparationStrategy.DefaultImpls.clearFrames(this);
    }

    public CloseableReference<Bitmap> getBitmapFrame(int i, int i2, int i3) {
        return BitmapFramePreparationStrategy.DefaultImpls.getBitmapFrame(this, i, i2, i3);
    }

    public void onStop() {
        BitmapFramePreparationStrategy.DefaultImpls.onStop(this);
    }

    public void prepareFrames(int i, int i2, Function0<Unit> function0) {
        BitmapFramePreparationStrategy.DefaultImpls.prepareFrames(this, i, i2, function0);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ FixedNumberBitmapFramePreparationStrategy(int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 3 : i);
    }

    public void prepareFrames(BitmapFramePreparer bitmapFramePreparer, BitmapFrameCache bitmapFrameCache, AnimationBackend animationBackend, int i, Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(bitmapFramePreparer, "bitmapFramePreparer");
        Intrinsics.checkNotNullParameter(bitmapFrameCache, "bitmapFrameCache");
        Intrinsics.checkNotNullParameter(animationBackend, "animationBackend");
        int i2 = this.framesToPrepare;
        int i3 = 1;
        if (1 <= i2) {
            while (true) {
                int frameCount = (i + i3) % animationBackend.getFrameCount();
                if (FLog.isLoggable(2)) {
                    FLog.v((Class<?>) this.TAG, "Preparing frame %d, last drawn: %d", (Object) Integer.valueOf(frameCount), (Object) Integer.valueOf(i));
                }
                if (bitmapFramePreparer.prepareFrame(bitmapFrameCache, animationBackend, frameCount)) {
                    if (i3 == i2) {
                        break;
                    }
                    i3++;
                } else {
                    return;
                }
            }
        }
        if (function0 != null) {
            function0.invoke();
        }
    }
}
