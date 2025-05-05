package com.facebook.imagepipeline.core;

import com.facebook.imagepipeline.core.ImagePipelineExperiments;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: ImagePipelineExperiments.kt */
final class ImagePipelineExperiments$Builder$setBitmapPrepareToDraw$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ int $maxBitmapSizeBytes;
    final /* synthetic */ int $minBitmapSizeBytes;
    final /* synthetic */ boolean $preparePrefetch;
    final /* synthetic */ boolean $useBitmapPrepareToDraw;
    final /* synthetic */ ImagePipelineExperiments.Builder this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ImagePipelineExperiments$Builder$setBitmapPrepareToDraw$1(ImagePipelineExperiments.Builder builder, boolean z, int i, int i2, boolean z2) {
        super(0);
        this.this$0 = builder;
        this.$useBitmapPrepareToDraw = z;
        this.$minBitmapSizeBytes = i;
        this.$maxBitmapSizeBytes = i2;
        this.$preparePrefetch = z2;
    }

    public final void invoke() {
        this.this$0.useBitmapPrepareToDraw = this.$useBitmapPrepareToDraw;
        this.this$0.bitmapPrepareToDrawMinSizeBytes = this.$minBitmapSizeBytes;
        this.this$0.bitmapPrepareToDrawMaxSizeBytes = this.$maxBitmapSizeBytes;
        this.this$0.bitmapPrepareToDrawForPrefetch = this.$preparePrefetch;
    }
}
