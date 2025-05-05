package com.facebook.imagepipeline.producers;

import com.facebook.imagepipeline.common.ResizeOptions;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H&¨\u0006\u0007"}, d2 = {"Lcom/facebook/imagepipeline/producers/ThumbnailProducer;", "T", "Lcom/facebook/imagepipeline/producers/Producer;", "canProvideImageForSize", "", "resizeOptions", "Lcom/facebook/imagepipeline/common/ResizeOptions;", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ThumbnailProducer.kt */
public interface ThumbnailProducer<T> extends Producer<T> {
    boolean canProvideImageForSize(ResizeOptions resizeOptions);
}
