package com.facebook.imagepipeline.datasource;

import com.facebook.imagepipeline.producers.BaseConsumer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000)\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0014J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u0014J\u001f\u0010\u0007\u001a\u00020\u00032\b\u0010\b\u001a\u0004\u0018\u00018\u00002\u0006\u0010\t\u001a\u00020\nH\u0014¢\u0006\u0002\u0010\u000bJ\u0010\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000eH\u0014¨\u0006\u000f"}, d2 = {"com/facebook/imagepipeline/datasource/AbstractProducerToDataSourceAdapter$createConsumer$1", "Lcom/facebook/imagepipeline/producers/BaseConsumer;", "onCancellationImpl", "", "onFailureImpl", "throwable", "", "onNewResultImpl", "newResult", "status", "", "(Ljava/lang/Object;I)V", "onProgressUpdateImpl", "progress", "", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: AbstractProducerToDataSourceAdapter.kt */
public final class AbstractProducerToDataSourceAdapter$createConsumer$1 extends BaseConsumer<T> {
    final /* synthetic */ AbstractProducerToDataSourceAdapter<T> this$0;

    AbstractProducerToDataSourceAdapter$createConsumer$1(AbstractProducerToDataSourceAdapter<T> abstractProducerToDataSourceAdapter) {
        this.this$0 = abstractProducerToDataSourceAdapter;
    }

    /* access modifiers changed from: protected */
    public void onNewResultImpl(T t, int i) {
        AbstractProducerToDataSourceAdapter<T> abstractProducerToDataSourceAdapter = this.this$0;
        abstractProducerToDataSourceAdapter.onNewResultImpl(t, i, abstractProducerToDataSourceAdapter.getSettableProducerContext());
    }

    /* access modifiers changed from: protected */
    public void onFailureImpl(Throwable th) {
        Intrinsics.checkNotNullParameter(th, "throwable");
        this.this$0.onFailureImpl(th);
    }

    /* access modifiers changed from: protected */
    public void onCancellationImpl() {
        this.this$0.onCancellationImpl();
    }

    /* access modifiers changed from: protected */
    public void onProgressUpdateImpl(float f) {
        boolean unused = this.this$0.setProgress(f);
    }
}
