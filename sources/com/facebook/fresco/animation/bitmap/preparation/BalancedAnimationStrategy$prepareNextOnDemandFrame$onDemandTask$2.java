package com.facebook.fresco.animation.bitmap.preparation;

import android.graphics.Bitmap;
import com.facebook.common.references.CloseableReference;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "bitmap", "Lcom/facebook/common/references/CloseableReference;", "Landroid/graphics/Bitmap;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: BalancedAnimationStrategy.kt */
final class BalancedAnimationStrategy$prepareNextOnDemandFrame$onDemandTask$2 extends Lambda implements Function1<CloseableReference<Bitmap>, Unit> {
    final /* synthetic */ Integer $nextFrame;
    final /* synthetic */ BalancedAnimationStrategy this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BalancedAnimationStrategy$prepareNextOnDemandFrame$onDemandTask$2(BalancedAnimationStrategy balancedAnimationStrategy, Integer num) {
        super(1);
        this.this$0 = balancedAnimationStrategy;
        this.$nextFrame = num;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((CloseableReference<Bitmap>) (CloseableReference) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(CloseableReference<Bitmap> closeableReference) {
        if (closeableReference != null) {
            this.this$0.onDemandBitmap = new OnDemandFrame(this.$nextFrame.intValue(), closeableReference);
        }
        this.this$0.fetchingOnDemand.set(false);
    }
}
