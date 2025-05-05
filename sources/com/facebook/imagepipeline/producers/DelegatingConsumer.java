package com.facebook.imagepipeline.producers;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\b&\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0013\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00010\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\t\u001a\u00020\nH\u0014J\u0010\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\rH\u0014J\u0010\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u0010H\u0014R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0011"}, d2 = {"Lcom/facebook/imagepipeline/producers/DelegatingConsumer;", "I", "O", "Lcom/facebook/imagepipeline/producers/BaseConsumer;", "consumer", "Lcom/facebook/imagepipeline/producers/Consumer;", "(Lcom/facebook/imagepipeline/producers/Consumer;)V", "getConsumer", "()Lcom/facebook/imagepipeline/producers/Consumer;", "onCancellationImpl", "", "onFailureImpl", "t", "", "onProgressUpdateImpl", "progress", "", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: DelegatingConsumer.kt */
public abstract class DelegatingConsumer<I, O> extends BaseConsumer<I> {
    private final Consumer<O> consumer;

    public DelegatingConsumer(Consumer<O> consumer2) {
        Intrinsics.checkNotNullParameter(consumer2, "consumer");
        this.consumer = consumer2;
    }

    public final Consumer<O> getConsumer() {
        return this.consumer;
    }

    /* access modifiers changed from: protected */
    public void onFailureImpl(Throwable th) {
        Intrinsics.checkNotNullParameter(th, "t");
        this.consumer.onFailure(th);
    }

    /* access modifiers changed from: protected */
    public void onCancellationImpl() {
        this.consumer.onCancellation();
    }

    /* access modifiers changed from: protected */
    public void onProgressUpdateImpl(float f) {
        this.consumer.onProgressUpdate(f);
    }
}
