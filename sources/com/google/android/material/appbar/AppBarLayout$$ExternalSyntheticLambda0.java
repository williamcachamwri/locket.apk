package com.google.android.material.appbar;

import android.animation.ValueAnimator;
import com.google.android.material.shape.MaterialShapeDrawable;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class AppBarLayout$$ExternalSyntheticLambda0 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ AppBarLayout f$0;
    public final /* synthetic */ MaterialShapeDrawable f$1;

    public /* synthetic */ AppBarLayout$$ExternalSyntheticLambda0(AppBarLayout appBarLayout, MaterialShapeDrawable materialShapeDrawable) {
        this.f$0 = appBarLayout;
        this.f$1 = materialShapeDrawable;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.f$0.m2122lambda$initializeLiftOnScrollWithColor$0$comgoogleandroidmaterialappbarAppBarLayout(this.f$1, valueAnimator);
    }
}
