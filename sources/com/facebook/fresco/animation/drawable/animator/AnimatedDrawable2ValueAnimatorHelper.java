package com.facebook.fresco.animation.drawable.animator;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.graphics.drawable.Drawable;
import com.facebook.fresco.animation.drawable.AnimatedDrawable2;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J \u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0007J\u001a\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000bH\u0007¨\u0006\u0010"}, d2 = {"Lcom/facebook/fresco/animation/drawable/animator/AnimatedDrawable2ValueAnimatorHelper;", "", "()V", "createAnimatorUpdateListener", "Landroid/animation/ValueAnimator$AnimatorUpdateListener;", "drawable", "Landroid/graphics/drawable/Drawable;", "createValueAnimator", "Landroid/animation/ValueAnimator;", "animatedDrawable", "loopCount", "", "loopDurationMs", "", "Lcom/facebook/fresco/animation/drawable/AnimatedDrawable2;", "maxDurationMs", "animated-drawable_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: AnimatedDrawable2ValueAnimatorHelper.kt */
public final class AnimatedDrawable2ValueAnimatorHelper {
    public static final AnimatedDrawable2ValueAnimatorHelper INSTANCE = new AnimatedDrawable2ValueAnimatorHelper();

    private AnimatedDrawable2ValueAnimatorHelper() {
    }

    @JvmStatic
    public static final ValueAnimator createValueAnimator(AnimatedDrawable2 animatedDrawable2, int i) {
        Intrinsics.checkNotNullParameter(animatedDrawable2, "animatedDrawable");
        ValueAnimator createValueAnimator = createValueAnimator(animatedDrawable2, animatedDrawable2.getLoopCount(), animatedDrawable2.getLoopDurationMs());
        if (createValueAnimator == null) {
            return null;
        }
        createValueAnimator.setRepeatCount((int) Math.max(((long) i) / animatedDrawable2.getLoopDurationMs(), 1));
        return createValueAnimator;
    }

    @JvmStatic
    public static final ValueAnimator createValueAnimator(Drawable drawable, int i, long j) {
        Intrinsics.checkNotNullParameter(drawable, "animatedDrawable");
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setIntValues(new int[]{0, (int) j});
        valueAnimator.setDuration(j);
        if (i == 0) {
            i = -1;
        }
        valueAnimator.setRepeatCount(i);
        valueAnimator.setRepeatMode(1);
        valueAnimator.setInterpolator((TimeInterpolator) null);
        valueAnimator.addUpdateListener(createAnimatorUpdateListener(drawable));
        return valueAnimator;
    }

    @JvmStatic
    public static final ValueAnimator.AnimatorUpdateListener createAnimatorUpdateListener(Drawable drawable) {
        Intrinsics.checkNotNullParameter(drawable, "drawable");
        return new AnimatedDrawable2ValueAnimatorHelper$$ExternalSyntheticLambda0(drawable);
    }

    /* access modifiers changed from: private */
    public static final void createAnimatorUpdateListener$lambda$0(Drawable drawable, ValueAnimator valueAnimator) {
        Intrinsics.checkNotNullParameter(drawable, "$drawable");
        Intrinsics.checkNotNullParameter(valueAnimator, "animation");
        Object animatedValue = valueAnimator.getAnimatedValue();
        Intrinsics.checkNotNull(animatedValue, "null cannot be cast to non-null type kotlin.Int");
        drawable.setLevel(((Integer) animatedValue).intValue());
    }
}
