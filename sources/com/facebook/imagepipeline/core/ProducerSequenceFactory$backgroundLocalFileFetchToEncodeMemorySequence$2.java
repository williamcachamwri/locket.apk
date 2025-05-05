package com.facebook.imagepipeline.core;

import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.producers.LocalFileFetchProducer;
import com.facebook.imagepipeline.producers.Producer;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0010\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00020\u00020\u0001H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Lcom/facebook/imagepipeline/producers/Producer;", "Lcom/facebook/imagepipeline/image/EncodedImage;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: ProducerSequenceFactory.kt */
final class ProducerSequenceFactory$backgroundLocalFileFetchToEncodeMemorySequence$2 extends Lambda implements Function0<Producer<EncodedImage>> {
    final /* synthetic */ ProducerSequenceFactory this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ProducerSequenceFactory$backgroundLocalFileFetchToEncodeMemorySequence$2(ProducerSequenceFactory producerSequenceFactory) {
        super(0);
        this.this$0 = producerSequenceFactory;
    }

    public final Producer<EncodedImage> invoke() {
        FrescoSystrace frescoSystrace = FrescoSystrace.INSTANCE;
        ProducerSequenceFactory producerSequenceFactory = this.this$0;
        if (!FrescoSystrace.isTracing()) {
            LocalFileFetchProducer newLocalFileFetchProducer = producerSequenceFactory.producerFactory.newLocalFileFetchProducer();
            Intrinsics.checkNotNullExpressionValue(newLocalFileFetchProducer, "producerFactory.newLocalFileFetchProducer()");
            return producerSequenceFactory.producerFactory.newBackgroundThreadHandoffProducer(producerSequenceFactory.newEncodedCacheMultiplexToTranscodeSequence(newLocalFileFetchProducer), producerSequenceFactory.threadHandoffProducerQueue);
        }
        FrescoSystrace.beginSection("ProducerSequenceFactory#getBackgroundLocalFileFetchToEncodeMemorySequence");
        try {
            LocalFileFetchProducer newLocalFileFetchProducer2 = producerSequenceFactory.producerFactory.newLocalFileFetchProducer();
            Intrinsics.checkNotNullExpressionValue(newLocalFileFetchProducer2, "producerFactory.newLocalFileFetchProducer()");
            return producerSequenceFactory.producerFactory.newBackgroundThreadHandoffProducer(producerSequenceFactory.newEncodedCacheMultiplexToTranscodeSequence(newLocalFileFetchProducer2), producerSequenceFactory.threadHandoffProducerQueue);
        } finally {
            FrescoSystrace.endSection();
        }
    }
}
