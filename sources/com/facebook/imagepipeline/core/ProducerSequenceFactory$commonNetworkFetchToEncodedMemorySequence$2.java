package com.facebook.imagepipeline.core;

import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.producers.Producer;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Lcom/facebook/imagepipeline/producers/Producer;", "Lcom/facebook/imagepipeline/image/EncodedImage;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: ProducerSequenceFactory.kt */
final class ProducerSequenceFactory$commonNetworkFetchToEncodedMemorySequence$2 extends Lambda implements Function0<Producer<EncodedImage>> {
    final /* synthetic */ ProducerSequenceFactory this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ProducerSequenceFactory$commonNetworkFetchToEncodedMemorySequence$2(ProducerSequenceFactory producerSequenceFactory) {
        super(0);
        this.this$0 = producerSequenceFactory;
    }

    public final Producer<EncodedImage> invoke() {
        FrescoSystrace frescoSystrace = FrescoSystrace.INSTANCE;
        ProducerSequenceFactory producerSequenceFactory = this.this$0;
        if (!FrescoSystrace.isTracing()) {
            return producerSequenceFactory.newCommonNetworkFetchToEncodedMemorySequence(producerSequenceFactory.networkFetcher);
        }
        FrescoSystrace.beginSection("ProducerSequenceFactory#getCommonNetworkFetchToEncodedMemorySequence");
        try {
            return producerSequenceFactory.newCommonNetworkFetchToEncodedMemorySequence(producerSequenceFactory.networkFetcher);
        } finally {
            FrescoSystrace.endSection();
        }
    }
}
