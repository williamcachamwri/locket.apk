package com.facebook.imagepipeline.producers;

import java.util.Map;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0017\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00018\u0000H\u0014¢\u0006\u0002\u0010\u0005J\u000f\u0010\u0006\u001a\u0004\u0018\u00018\u0000H\u0014¢\u0006\u0002\u0010\u0007J\u0017\u0010\b\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00018\u0000H\u0014¢\u0006\u0002\u0010\u0005¨\u0006\t"}, d2 = {"com/facebook/imagepipeline/producers/ThreadHandoffProducer$produceResults$1$statefulRunnable$1", "Lcom/facebook/imagepipeline/producers/StatefulProducerRunnable;", "disposeResult", "", "ignored", "(Ljava/lang/Object;)V", "getResult", "()Ljava/lang/Object;", "onSuccess", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ThreadHandoffProducer.kt */
public final class ThreadHandoffProducer$produceResults$1$statefulRunnable$1 extends StatefulProducerRunnable<T> {
    final /* synthetic */ Consumer<T> $consumer;
    final /* synthetic */ ProducerContext $context;
    final /* synthetic */ ProducerListener2 $producerListener;
    final /* synthetic */ ThreadHandoffProducer<T> this$0;

    /* access modifiers changed from: protected */
    public void disposeResult(T t) {
    }

    /* access modifiers changed from: protected */
    public T getResult() throws Exception {
        return null;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ThreadHandoffProducer$produceResults$1$statefulRunnable$1(Consumer<T> consumer, ProducerListener2 producerListener2, ProducerContext producerContext, ThreadHandoffProducer<T> threadHandoffProducer) {
        super(consumer, producerListener2, producerContext, ThreadHandoffProducer.PRODUCER_NAME);
        this.$consumer = consumer;
        this.$producerListener = producerListener2;
        this.$context = producerContext;
        this.this$0 = threadHandoffProducer;
    }

    /* access modifiers changed from: protected */
    public void onSuccess(T t) {
        this.$producerListener.onProducerFinishWithSuccess(this.$context, ThreadHandoffProducer.PRODUCER_NAME, (Map<String, String>) null);
        this.this$0.getInputProducer().produceResults(this.$consumer, this.$context);
    }
}
