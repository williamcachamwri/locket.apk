package com.facebook.imagepipeline.producers;

import android.os.Looper;
import com.facebook.imagepipeline.instrumentation.FrescoInstrumenter;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u0011*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002:\u0001\u0011B\u001b\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001e\u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0012"}, d2 = {"Lcom/facebook/imagepipeline/producers/ThreadHandoffProducer;", "T", "Lcom/facebook/imagepipeline/producers/Producer;", "inputProducer", "threadHandoffProducerQueue", "Lcom/facebook/imagepipeline/producers/ThreadHandoffProducerQueue;", "(Lcom/facebook/imagepipeline/producers/Producer;Lcom/facebook/imagepipeline/producers/ThreadHandoffProducerQueue;)V", "getInputProducer", "()Lcom/facebook/imagepipeline/producers/Producer;", "getThreadHandoffProducerQueue", "()Lcom/facebook/imagepipeline/producers/ThreadHandoffProducerQueue;", "produceResults", "", "consumer", "Lcom/facebook/imagepipeline/producers/Consumer;", "context", "Lcom/facebook/imagepipeline/producers/ProducerContext;", "Companion", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ThreadHandoffProducer.kt */
public final class ThreadHandoffProducer<T> implements Producer<T> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String PRODUCER_NAME = "BackgroundThreadHandoffProducer";
    private final Producer<T> inputProducer;
    private final ThreadHandoffProducerQueue threadHandoffProducerQueue;

    public ThreadHandoffProducer(Producer<T> producer, ThreadHandoffProducerQueue threadHandoffProducerQueue2) {
        Intrinsics.checkNotNullParameter(producer, "inputProducer");
        Intrinsics.checkNotNullParameter(threadHandoffProducerQueue2, "threadHandoffProducerQueue");
        this.inputProducer = producer;
        this.threadHandoffProducerQueue = threadHandoffProducerQueue2;
    }

    public final Producer<T> getInputProducer() {
        return this.inputProducer;
    }

    public final ThreadHandoffProducerQueue getThreadHandoffProducerQueue() {
        return this.threadHandoffProducerQueue;
    }

    public void produceResults(Consumer<T> consumer, ProducerContext producerContext) {
        Intrinsics.checkNotNullParameter(consumer, "consumer");
        Intrinsics.checkNotNullParameter(producerContext, "context");
        FrescoSystrace frescoSystrace = FrescoSystrace.INSTANCE;
        if (!FrescoSystrace.isTracing()) {
            ProducerListener2 producerListener = producerContext.getProducerListener();
            Companion companion = Companion;
            if (companion.shouldRunImmediately(producerContext)) {
                producerListener.onProducerStart(producerContext, PRODUCER_NAME);
                producerListener.onProducerFinishWithSuccess(producerContext, PRODUCER_NAME, (Map<String, String>) null);
                this.inputProducer.produceResults(consumer, producerContext);
                return;
            }
            StatefulProducerRunnable threadHandoffProducer$produceResults$1$statefulRunnable$1 = new ThreadHandoffProducer$produceResults$1$statefulRunnable$1(consumer, producerListener, producerContext, this);
            producerContext.addCallbacks(new ThreadHandoffProducer$produceResults$1$1(threadHandoffProducer$produceResults$1$statefulRunnable$1, this));
            this.threadHandoffProducerQueue.addToQueueOrExecute(FrescoInstrumenter.decorateRunnable(threadHandoffProducer$produceResults$1$statefulRunnable$1, companion.getInstrumentationTag(producerContext)));
            return;
        }
        FrescoSystrace.beginSection("ThreadHandoffProducer#produceResults");
        try {
            ProducerListener2 producerListener2 = producerContext.getProducerListener();
            Companion companion2 = Companion;
            if (companion2.shouldRunImmediately(producerContext)) {
                producerListener2.onProducerStart(producerContext, PRODUCER_NAME);
                producerListener2.onProducerFinishWithSuccess(producerContext, PRODUCER_NAME, (Map<String, String>) null);
                this.inputProducer.produceResults(consumer, producerContext);
                return;
            }
            StatefulProducerRunnable threadHandoffProducer$produceResults$1$statefulRunnable$12 = new ThreadHandoffProducer$produceResults$1$statefulRunnable$1(consumer, producerListener2, producerContext, this);
            producerContext.addCallbacks(new ThreadHandoffProducer$produceResults$1$1(threadHandoffProducer$produceResults$1$statefulRunnable$12, this));
            this.threadHandoffProducerQueue.addToQueueOrExecute(FrescoInstrumenter.decorateRunnable(threadHandoffProducer$produceResults$1$statefulRunnable$12, companion2.getInstrumentationTag(producerContext)));
            Unit unit = Unit.INSTANCE;
            FrescoSystrace.endSection();
        } finally {
            FrescoSystrace.endSection();
        }
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u0007H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/facebook/imagepipeline/producers/ThreadHandoffProducer$Companion;", "", "()V", "PRODUCER_NAME", "", "getInstrumentationTag", "context", "Lcom/facebook/imagepipeline/producers/ProducerContext;", "shouldRunImmediately", "", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: ThreadHandoffProducer.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* access modifiers changed from: private */
        public final String getInstrumentationTag(ProducerContext producerContext) {
            if (FrescoInstrumenter.isTracing()) {
                return "ThreadHandoffProducer_produceResults_" + producerContext.getId();
            }
            return null;
        }

        /* access modifiers changed from: private */
        public final boolean shouldRunImmediately(ProducerContext producerContext) {
            if (producerContext.getImagePipelineConfig().getExperiments().getHandOffOnUiThreadOnly() && Looper.getMainLooper().getThread() != Thread.currentThread()) {
                return true;
            }
            return false;
        }
    }
}
