package com.facebook.fresco.animation.drawable;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, d2 = {"com/facebook/fresco/animation/drawable/KAnimatedDrawable2$invalidateRunnable$1", "Ljava/lang/Runnable;", "run", "", "animated-drawable_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: KAnimatedDrawable2.kt */
public final class KAnimatedDrawable2$invalidateRunnable$1 implements Runnable {
    final /* synthetic */ KAnimatedDrawable2 this$0;

    KAnimatedDrawable2$invalidateRunnable$1(KAnimatedDrawable2 kAnimatedDrawable2) {
        this.this$0 = kAnimatedDrawable2;
    }

    public void run() {
        this.this$0.unscheduleSelf(this);
        this.this$0.invalidateSelf();
    }
}
