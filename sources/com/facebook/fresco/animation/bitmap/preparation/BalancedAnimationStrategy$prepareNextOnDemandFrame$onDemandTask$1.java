package com.facebook.fresco.animation.bitmap.preparation;

import android.graphics.Bitmap;
import com.facebook.common.references.CloseableReference;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Lcom/facebook/common/references/CloseableReference;", "Landroid/graphics/Bitmap;", "it", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: BalancedAnimationStrategy.kt */
final class BalancedAnimationStrategy$prepareNextOnDemandFrame$onDemandTask$1 extends Lambda implements Function1<Integer, CloseableReference<Bitmap>> {
    final /* synthetic */ BalancedAnimationStrategy this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BalancedAnimationStrategy$prepareNextOnDemandFrame$onDemandTask$1(BalancedAnimationStrategy balancedAnimationStrategy) {
        super(1);
        this.this$0 = balancedAnimationStrategy;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        return invoke(((Number) obj).intValue());
    }

    public final CloseableReference<Bitmap> invoke(int i) {
        return this.this$0.bitmapCache.getCachedFrame(i);
    }
}
