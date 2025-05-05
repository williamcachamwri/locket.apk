package com.facebook.imagepipeline.listener;

import com.facebook.imagepipeline.request.ImageRequest;
import io.sentry.SentryBaseEvent;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J \u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006H\u0016J.\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0014\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u000bH\u0016J6\u0010\f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u000e2\u0014\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u000bH\u0016J.\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0014\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u000bH\u0016J\u0018\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0016J\u0010\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J(\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J(\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J \u0010\u001b\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J \u0010\u001c\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u001d\u001a\u00020\u0017H\u0016J\u0010\u0010\u001e\u001a\u00020\u00172\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u001f"}, d2 = {"Lcom/facebook/imagepipeline/listener/BaseRequestListener;", "Lcom/facebook/imagepipeline/listener/RequestListener;", "()V", "onProducerEvent", "", "requestId", "", "producerName", "eventName", "onProducerFinishWithCancellation", "extraMap", "", "onProducerFinishWithFailure", "t", "", "onProducerFinishWithSuccess", "onProducerStart", "onRequestCancellation", "onRequestFailure", "request", "Lcom/facebook/imagepipeline/request/ImageRequest;", "throwable", "isPrefetch", "", "onRequestStart", "callerContext", "", "onRequestSuccess", "onUltimateProducerReached", "successful", "requiresExtraMap", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: BaseRequestListener.kt */
public class BaseRequestListener implements RequestListener {
    public void onProducerEvent(String str, String str2, String str3) {
        Intrinsics.checkNotNullParameter(str, "requestId");
        Intrinsics.checkNotNullParameter(str2, "producerName");
        Intrinsics.checkNotNullParameter(str3, "eventName");
    }

    public void onProducerFinishWithCancellation(String str, String str2, Map<String, String> map) {
        Intrinsics.checkNotNullParameter(str, "requestId");
        Intrinsics.checkNotNullParameter(str2, "producerName");
    }

    public void onProducerFinishWithFailure(String str, String str2, Throwable th, Map<String, String> map) {
        Intrinsics.checkNotNullParameter(str, "requestId");
        Intrinsics.checkNotNullParameter(str2, "producerName");
        Intrinsics.checkNotNullParameter(th, "t");
    }

    public void onProducerFinishWithSuccess(String str, String str2, Map<String, String> map) {
        Intrinsics.checkNotNullParameter(str, "requestId");
        Intrinsics.checkNotNullParameter(str2, "producerName");
    }

    public void onProducerStart(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "requestId");
        Intrinsics.checkNotNullParameter(str2, "producerName");
    }

    public void onRequestCancellation(String str) {
        Intrinsics.checkNotNullParameter(str, "requestId");
    }

    public void onRequestFailure(ImageRequest imageRequest, String str, Throwable th, boolean z) {
        Intrinsics.checkNotNullParameter(imageRequest, SentryBaseEvent.JsonKeys.REQUEST);
        Intrinsics.checkNotNullParameter(str, "requestId");
        Intrinsics.checkNotNullParameter(th, "throwable");
    }

    public void onRequestStart(ImageRequest imageRequest, Object obj, String str, boolean z) {
        Intrinsics.checkNotNullParameter(imageRequest, SentryBaseEvent.JsonKeys.REQUEST);
        Intrinsics.checkNotNullParameter(obj, "callerContext");
        Intrinsics.checkNotNullParameter(str, "requestId");
    }

    public void onRequestSuccess(ImageRequest imageRequest, String str, boolean z) {
        Intrinsics.checkNotNullParameter(imageRequest, SentryBaseEvent.JsonKeys.REQUEST);
        Intrinsics.checkNotNullParameter(str, "requestId");
    }

    public void onUltimateProducerReached(String str, String str2, boolean z) {
        Intrinsics.checkNotNullParameter(str, "requestId");
        Intrinsics.checkNotNullParameter(str2, "producerName");
    }

    public boolean requiresExtraMap(String str) {
        Intrinsics.checkNotNullParameter(str, "requestId");
        return false;
    }
}
