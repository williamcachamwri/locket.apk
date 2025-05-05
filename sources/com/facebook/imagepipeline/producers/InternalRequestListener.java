package com.facebook.imagepipeline.producers;

import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.imagepipeline.listener.RequestListener2;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002B\u0019\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u001a\u0010\u000b\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016J\u0010\u0010\u000e\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0002X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/facebook/imagepipeline/producers/InternalRequestListener;", "Lcom/facebook/imagepipeline/producers/InternalProducerListener;", "Lcom/facebook/imagepipeline/listener/RequestListener2;", "requestListener", "Lcom/facebook/imagepipeline/listener/RequestListener;", "requestListener2", "(Lcom/facebook/imagepipeline/listener/RequestListener;Lcom/facebook/imagepipeline/listener/RequestListener2;)V", "onRequestCancellation", "", "producerContext", "Lcom/facebook/imagepipeline/producers/ProducerContext;", "onRequestFailure", "throwable", "", "onRequestStart", "onRequestSuccess", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: InternalRequestListener.kt */
public final class InternalRequestListener extends InternalProducerListener implements RequestListener2 {
    private final RequestListener requestListener;
    private final RequestListener2 requestListener2;

    public InternalRequestListener(RequestListener requestListener3, RequestListener2 requestListener22) {
        super(requestListener3, requestListener22);
        this.requestListener = requestListener3;
        this.requestListener2 = requestListener22;
    }

    public void onRequestStart(ProducerContext producerContext) {
        Intrinsics.checkNotNullParameter(producerContext, "producerContext");
        RequestListener requestListener3 = this.requestListener;
        if (requestListener3 != null) {
            requestListener3.onRequestStart(producerContext.getImageRequest(), producerContext.getCallerContext(), producerContext.getId(), producerContext.isPrefetch());
        }
        RequestListener2 requestListener22 = this.requestListener2;
        if (requestListener22 != null) {
            requestListener22.onRequestStart(producerContext);
        }
    }

    public void onRequestSuccess(ProducerContext producerContext) {
        Intrinsics.checkNotNullParameter(producerContext, "producerContext");
        RequestListener requestListener3 = this.requestListener;
        if (requestListener3 != null) {
            requestListener3.onRequestSuccess(producerContext.getImageRequest(), producerContext.getId(), producerContext.isPrefetch());
        }
        RequestListener2 requestListener22 = this.requestListener2;
        if (requestListener22 != null) {
            requestListener22.onRequestSuccess(producerContext);
        }
    }

    public void onRequestFailure(ProducerContext producerContext, Throwable th) {
        Intrinsics.checkNotNullParameter(producerContext, "producerContext");
        RequestListener requestListener3 = this.requestListener;
        if (requestListener3 != null) {
            requestListener3.onRequestFailure(producerContext.getImageRequest(), producerContext.getId(), th, producerContext.isPrefetch());
        }
        RequestListener2 requestListener22 = this.requestListener2;
        if (requestListener22 != null) {
            requestListener22.onRequestFailure(producerContext, th);
        }
    }

    public void onRequestCancellation(ProducerContext producerContext) {
        Intrinsics.checkNotNullParameter(producerContext, "producerContext");
        RequestListener requestListener3 = this.requestListener;
        if (requestListener3 != null) {
            requestListener3.onRequestCancellation(producerContext.getId());
        }
        RequestListener2 requestListener22 = this.requestListener2;
        if (requestListener22 != null) {
            requestListener22.onRequestCancellation(producerContext);
        }
    }
}
