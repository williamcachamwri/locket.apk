package com.facebook.imagepipeline.producers;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, d2 = {"com/facebook/imagepipeline/producers/ThreadHandoffProducer$produceResults$1$1", "Lcom/facebook/imagepipeline/producers/BaseProducerContextCallbacks;", "onCancellationRequested", "", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ThreadHandoffProducer.kt */
public final class ThreadHandoffProducer$produceResults$1$1 extends BaseProducerContextCallbacks {
    final /* synthetic */ StatefulProducerRunnable<T> $statefulRunnable;
    final /* synthetic */ ThreadHandoffProducer<T> this$0;

    ThreadHandoffProducer$produceResults$1$1(StatefulProducerRunnable<T> statefulProducerRunnable, ThreadHandoffProducer<T> threadHandoffProducer) {
        this.$statefulRunnable = statefulProducerRunnable;
        this.this$0 = threadHandoffProducer;
    }

    public void onCancellationRequested() {
        this.$statefulRunnable.cancel();
        this.this$0.getThreadHandoffProducerQueue().remove(this.$statefulRunnable);
    }
}
