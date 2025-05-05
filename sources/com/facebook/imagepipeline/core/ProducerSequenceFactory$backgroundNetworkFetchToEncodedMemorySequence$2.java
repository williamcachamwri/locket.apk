package com.facebook.imagepipeline.core;

import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.producers.Producer;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0010\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00020\u00020\u0001H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Lcom/facebook/imagepipeline/producers/Producer;", "Lcom/facebook/imagepipeline/image/EncodedImage;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: ProducerSequenceFactory.kt */
final class ProducerSequenceFactory$backgroundNetworkFetchToEncodedMemorySequence$2 extends Lambda implements Function0<Producer<EncodedImage>> {
    final /* synthetic */ ProducerSequenceFactory this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ProducerSequenceFactory$backgroundNetworkFetchToEncodedMemorySequence$2(ProducerSequenceFactory producerSequenceFactory) {
        super(0);
        this.this$0 = producerSequenceFactory;
    }

    public final Producer<EncodedImage> invoke() {
        FrescoSystrace frescoSystrace = FrescoSystrace.INSTANCE;
        ProducerSequenceFactory producerSequenceFactory = this.this$0;
        if (!FrescoSystrace.isTracing()) {
            return producerSequenceFactory.producerFactory.newBackgroundThreadHandoffProducer(producerSequenceFactory.getCommonNetworkFetchToEncodedMemorySequence(), producerSequenceFactory.threadHandoffProducerQueue);
        }
        FrescoSystrace.beginSection("ProducerSequenceFactory#getBackgroundNetworkFetchToEncodedMemorySequence:init");
        try {
            return producerSequenceFactory.producerFactory.newBackgroundThreadHandoffProducer(producerSequenceFactory.getCommonNetworkFetchToEncodedMemorySequence(), producerSequenceFactory.threadHandoffProducerQueue);
        } finally {
            FrescoSystrace.endSection();
        }
    }
}
