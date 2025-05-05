package com.facebook.fresco.animation.bitmap.preparation.loadframe;

import android.graphics.Bitmap;
import com.facebook.common.references.CloseableReference;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0016\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u0003\u0018\u00010\u00012\u0006\u0010\u0005\u001a\u00020\u0002H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "Lkotlin/Pair;", "", "Lcom/facebook/common/references/CloseableReference;", "Landroid/graphics/Bitmap;", "it", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: LoadOnDemandFrameTask.kt */
final class LoadOnDemandFrameTask$run$nearestFrame$1 extends Lambda implements Function1<Integer, Pair<? extends Integer, ? extends CloseableReference<Bitmap>>> {
    final /* synthetic */ LoadOnDemandFrameTask this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    LoadOnDemandFrameTask$run$nearestFrame$1(LoadOnDemandFrameTask loadOnDemandFrameTask) {
        super(1);
        this.this$0 = loadOnDemandFrameTask;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        return invoke(((Number) obj).intValue());
    }

    public final Pair<Integer, CloseableReference<Bitmap>> invoke(int i) {
        CloseableReference closeableReference = (CloseableReference) this.this$0.getCachedBitmap.invoke(Integer.valueOf(i));
        if (closeableReference == null) {
            return null;
        }
        return new Pair<>(Integer.valueOf(i), closeableReference);
    }
}
