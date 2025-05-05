package com.facebook.imagepipeline.producers;

import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0001¢\u0006\u0002\u0010\u0005J$\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000fH\u0016J4\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0018\u0010\u0012\u001a\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u000f\u0018\u00010\u0013H\u0016J>\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0018\u0010\u0012\u001a\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u000f\u0018\u00010\u0013H\u0016J4\u0010\u0017\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0018\u0010\u0012\u001a\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u000f\u0018\u00010\u0013H\u0016J\u001a\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\"\u0010\u0019\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\u001a\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0001¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u001d"}, d2 = {"Lcom/facebook/imagepipeline/producers/InternalProducerListener;", "Lcom/facebook/imagepipeline/producers/ProducerListener2;", "producerListener", "Lcom/facebook/imagepipeline/producers/ProducerListener;", "producerListener2", "(Lcom/facebook/imagepipeline/producers/ProducerListener;Lcom/facebook/imagepipeline/producers/ProducerListener2;)V", "getProducerListener", "()Lcom/facebook/imagepipeline/producers/ProducerListener;", "getProducerListener2", "()Lcom/facebook/imagepipeline/producers/ProducerListener2;", "onProducerEvent", "", "context", "Lcom/facebook/imagepipeline/producers/ProducerContext;", "producerName", "", "eventName", "onProducerFinishWithCancellation", "extraMap", "", "onProducerFinishWithFailure", "t", "", "onProducerFinishWithSuccess", "onProducerStart", "onUltimateProducerReached", "successful", "", "requiresExtraMap", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: InternalProducerListener.kt */
public class InternalProducerListener implements ProducerListener2 {
    private final ProducerListener producerListener;
    private final ProducerListener2 producerListener2;

    public InternalProducerListener(ProducerListener producerListener3, ProducerListener2 producerListener22) {
        this.producerListener = producerListener3;
        this.producerListener2 = producerListener22;
    }

    public final ProducerListener getProducerListener() {
        return this.producerListener;
    }

    public final ProducerListener2 getProducerListener2() {
        return this.producerListener2;
    }

    public void onProducerStart(ProducerContext producerContext, String str) {
        Intrinsics.checkNotNullParameter(producerContext, "context");
        ProducerListener producerListener3 = this.producerListener;
        if (producerListener3 != null) {
            producerListener3.onProducerStart(producerContext.getId(), str);
        }
        ProducerListener2 producerListener22 = this.producerListener2;
        if (producerListener22 != null) {
            producerListener22.onProducerStart(producerContext, str);
        }
    }

    public void onProducerEvent(ProducerContext producerContext, String str, String str2) {
        Intrinsics.checkNotNullParameter(producerContext, "context");
        ProducerListener producerListener3 = this.producerListener;
        if (producerListener3 != null) {
            producerListener3.onProducerEvent(producerContext.getId(), str, str2);
        }
        ProducerListener2 producerListener22 = this.producerListener2;
        if (producerListener22 != null) {
            producerListener22.onProducerEvent(producerContext, str, str2);
        }
    }

    public void onProducerFinishWithSuccess(ProducerContext producerContext, String str, Map<String, String> map) {
        Intrinsics.checkNotNullParameter(producerContext, "context");
        ProducerListener producerListener3 = this.producerListener;
        if (producerListener3 != null) {
            producerListener3.onProducerFinishWithSuccess(producerContext.getId(), str, map);
        }
        ProducerListener2 producerListener22 = this.producerListener2;
        if (producerListener22 != null) {
            producerListener22.onProducerFinishWithSuccess(producerContext, str, map);
        }
    }

    public void onProducerFinishWithFailure(ProducerContext producerContext, String str, Throwable th, Map<String, String> map) {
        Intrinsics.checkNotNullParameter(producerContext, "context");
        ProducerListener producerListener3 = this.producerListener;
        if (producerListener3 != null) {
            producerListener3.onProducerFinishWithFailure(producerContext.getId(), str, th, map);
        }
        ProducerListener2 producerListener22 = this.producerListener2;
        if (producerListener22 != null) {
            producerListener22.onProducerFinishWithFailure(producerContext, str, th, map);
        }
    }

    public void onProducerFinishWithCancellation(ProducerContext producerContext, String str, Map<String, String> map) {
        Intrinsics.checkNotNullParameter(producerContext, "context");
        ProducerListener producerListener3 = this.producerListener;
        if (producerListener3 != null) {
            producerListener3.onProducerFinishWithCancellation(producerContext.getId(), str, map);
        }
        ProducerListener2 producerListener22 = this.producerListener2;
        if (producerListener22 != null) {
            producerListener22.onProducerFinishWithCancellation(producerContext, str, map);
        }
    }

    public void onUltimateProducerReached(ProducerContext producerContext, String str, boolean z) {
        Intrinsics.checkNotNullParameter(producerContext, "context");
        ProducerListener producerListener3 = this.producerListener;
        if (producerListener3 != null) {
            producerListener3.onUltimateProducerReached(producerContext.getId(), str, z);
        }
        ProducerListener2 producerListener22 = this.producerListener2;
        if (producerListener22 != null) {
            producerListener22.onUltimateProducerReached(producerContext, str, z);
        }
    }

    public boolean requiresExtraMap(ProducerContext producerContext, String str) {
        Intrinsics.checkNotNullParameter(producerContext, "context");
        ProducerListener producerListener3 = this.producerListener;
        Boolean bool = null;
        Boolean valueOf = producerListener3 != null ? Boolean.valueOf(producerListener3.requiresExtraMap(producerContext.getId())) : null;
        if (!Intrinsics.areEqual((Object) valueOf, (Object) true)) {
            ProducerListener2 producerListener22 = this.producerListener2;
            if (producerListener22 != null) {
                bool = Boolean.valueOf(producerListener22.requiresExtraMap(producerContext, str));
            }
            valueOf = bool;
        }
        if (valueOf != null) {
            return valueOf.booleanValue();
        }
        return false;
    }
}
