package com.facebook.imagepipeline.core;

import com.facebook.imagepipeline.producers.RemoveImageTransformMetaDataProducer;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lcom/facebook/imagepipeline/producers/RemoveImageTransformMetaDataProducer;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: ProducerSequenceFactory.kt */
final class ProducerSequenceFactory$localContentUriFetchEncodedImageProducerSequence$2 extends Lambda implements Function0<RemoveImageTransformMetaDataProducer> {
    final /* synthetic */ ProducerSequenceFactory this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ProducerSequenceFactory$localContentUriFetchEncodedImageProducerSequence$2(ProducerSequenceFactory producerSequenceFactory) {
        super(0);
        this.this$0 = producerSequenceFactory;
    }

    public final RemoveImageTransformMetaDataProducer invoke() {
        FrescoSystrace frescoSystrace = FrescoSystrace.INSTANCE;
        ProducerSequenceFactory producerSequenceFactory = this.this$0;
        if (!FrescoSystrace.isTracing()) {
            return new RemoveImageTransformMetaDataProducer(producerSequenceFactory.getBackgroundLocalContentUriFetchToEncodeMemorySequence());
        }
        FrescoSystrace.beginSection("ProducerSequenceFactory#getLocalContentUriFetchEncodedImageProducerSequence:init");
        try {
            return new RemoveImageTransformMetaDataProducer(producerSequenceFactory.getBackgroundLocalContentUriFetchToEncodeMemorySequence());
        } finally {
            FrescoSystrace.endSection();
        }
    }
}
