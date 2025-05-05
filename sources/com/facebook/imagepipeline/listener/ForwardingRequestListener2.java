package com.facebook.imagepipeline.listener;

import com.facebook.common.logging.FLog;
import com.facebook.imagepipeline.producers.ProducerContext;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010!\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010%\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u0000 '2\u00020\u0001:\u0001'B\u0017\b\u0016\u0012\u000e\u0010\u0002\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0003¢\u0006\u0002\u0010\u0004B\u001f\b\u0016\u0012\u0016\u0010\u0002\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u0005\"\u0004\u0018\u00010\u0001¢\u0006\u0002\u0010\u0006J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0001J%\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000e2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\n0\u0010H\bJ \u0010\u0011\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u000eH\u0016J2\u0010\u0016\u001a\u00020\n2\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u000e2\u0014\u0010\u0017\u001a\u0010\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0018H\u0016J<\u0010\u0019\u001a\u00020\n2\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u000e2\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0014\u0010\u0017\u001a\u0010\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0018H\u0016J2\u0010\u001c\u001a\u00020\n2\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u000e2\u0014\u0010\u0017\u001a\u0010\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0018H\u0016J\u0018\u0010\u001d\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u000eH\u0016J\u0010\u0010\u001e\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0018\u0010\u001f\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010 \u001a\u00020\u001bH\u0016J\u0010\u0010!\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0010\u0010\"\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J \u0010#\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u000e2\u0006\u0010$\u001a\u00020%H\u0016J\u0018\u0010&\u001a\u00020%2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u000eH\u0016R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00010\bX\u000e¢\u0006\u0002\n\u0000¨\u0006("}, d2 = {"Lcom/facebook/imagepipeline/listener/ForwardingRequestListener2;", "Lcom/facebook/imagepipeline/listener/RequestListener2;", "listenersToAdd", "Ljava/util/Set;", "(Ljava/util/Set;)V", "", "([Lcom/facebook/imagepipeline/listener/RequestListener2;)V", "requestListeners", "", "addRequestListener", "", "requestListener", "forEachListener", "methodName", "", "block", "Lkotlin/Function1;", "onProducerEvent", "producerContext", "Lcom/facebook/imagepipeline/producers/ProducerContext;", "producerName", "producerEventName", "onProducerFinishWithCancellation", "extraMap", "", "onProducerFinishWithFailure", "t", "", "onProducerFinishWithSuccess", "onProducerStart", "onRequestCancellation", "onRequestFailure", "throwable", "onRequestStart", "onRequestSuccess", "onUltimateProducerReached", "successful", "", "requiresExtraMap", "Companion", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ForwardingRequestListener2.kt */
public final class ForwardingRequestListener2 implements RequestListener2 {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String TAG = "ForwardingRequestListener2";
    private List<RequestListener2> requestListeners;

    public ForwardingRequestListener2(Set<RequestListener2> set) {
        Intrinsics.checkNotNullParameter(set, "listenersToAdd");
        List<RequestListener2> arrayList = new ArrayList<>(set.size());
        this.requestListeners = arrayList;
        CollectionsKt.filterNotNullTo(set, arrayList);
    }

    public ForwardingRequestListener2(RequestListener2... requestListener2Arr) {
        Intrinsics.checkNotNullParameter(requestListener2Arr, "listenersToAdd");
        List<RequestListener2> arrayList = new ArrayList<>(requestListener2Arr.length);
        this.requestListeners = arrayList;
        ArraysKt.filterNotNullTo(requestListener2Arr, arrayList);
    }

    public final void addRequestListener(RequestListener2 requestListener2) {
        Intrinsics.checkNotNullParameter(requestListener2, "requestListener");
        this.requestListeners.add(requestListener2);
    }

    private final void forEachListener(String str, Function1<? super RequestListener2, Unit> function1) {
        for (RequestListener2 invoke : this.requestListeners) {
            try {
                function1.invoke(invoke);
            } catch (Exception e) {
                FLog.e(TAG, "InternalListener exception in " + str, (Throwable) e);
            }
        }
    }

    public boolean requiresExtraMap(ProducerContext producerContext, String str) {
        Intrinsics.checkNotNullParameter(producerContext, "producerContext");
        Intrinsics.checkNotNullParameter(str, "producerName");
        Iterable<RequestListener2> iterable = this.requestListeners;
        if ((iterable instanceof Collection) && ((Collection) iterable).isEmpty()) {
            return false;
        }
        for (RequestListener2 requiresExtraMap : iterable) {
            if (requiresExtraMap.requiresExtraMap(producerContext, str)) {
                return true;
            }
        }
        return false;
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/facebook/imagepipeline/listener/ForwardingRequestListener2$Companion;", "", "()V", "TAG", "", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: ForwardingRequestListener2.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public void onRequestStart(ProducerContext producerContext) {
        Intrinsics.checkNotNullParameter(producerContext, "producerContext");
        for (RequestListener2 onRequestStart : this.requestListeners) {
            try {
                onRequestStart.onRequestStart(producerContext);
            } catch (Exception e) {
                FLog.e(TAG, "InternalListener exception in onRequestStart", (Throwable) e);
            }
        }
    }

    public void onProducerStart(ProducerContext producerContext, String str) {
        Intrinsics.checkNotNullParameter(producerContext, "producerContext");
        Intrinsics.checkNotNullParameter(str, "producerName");
        for (RequestListener2 onProducerStart : this.requestListeners) {
            try {
                onProducerStart.onProducerStart(producerContext, str);
            } catch (Exception e) {
                FLog.e(TAG, "InternalListener exception in onProducerStart", (Throwable) e);
            }
        }
    }

    public void onProducerFinishWithSuccess(ProducerContext producerContext, String str, Map<String, String> map) {
        for (RequestListener2 onProducerFinishWithSuccess : this.requestListeners) {
            try {
                onProducerFinishWithSuccess.onProducerFinishWithSuccess(producerContext, str, map);
            } catch (Exception e) {
                FLog.e(TAG, "InternalListener exception in onProducerFinishWithSuccess", (Throwable) e);
            }
        }
    }

    public void onProducerFinishWithFailure(ProducerContext producerContext, String str, Throwable th, Map<String, String> map) {
        for (RequestListener2 onProducerFinishWithFailure : this.requestListeners) {
            try {
                onProducerFinishWithFailure.onProducerFinishWithFailure(producerContext, str, th, map);
            } catch (Exception e) {
                FLog.e(TAG, "InternalListener exception in onProducerFinishWithFailure", (Throwable) e);
            }
        }
    }

    public void onProducerFinishWithCancellation(ProducerContext producerContext, String str, Map<String, String> map) {
        for (RequestListener2 onProducerFinishWithCancellation : this.requestListeners) {
            try {
                onProducerFinishWithCancellation.onProducerFinishWithCancellation(producerContext, str, map);
            } catch (Exception e) {
                FLog.e(TAG, "InternalListener exception in onProducerFinishWithCancellation", (Throwable) e);
            }
        }
    }

    public void onProducerEvent(ProducerContext producerContext, String str, String str2) {
        Intrinsics.checkNotNullParameter(producerContext, "producerContext");
        Intrinsics.checkNotNullParameter(str, "producerName");
        Intrinsics.checkNotNullParameter(str2, "producerEventName");
        for (RequestListener2 onProducerEvent : this.requestListeners) {
            try {
                onProducerEvent.onProducerEvent(producerContext, str, str2);
            } catch (Exception e) {
                FLog.e(TAG, "InternalListener exception in onIntermediateChunkStart", (Throwable) e);
            }
        }
    }

    public void onUltimateProducerReached(ProducerContext producerContext, String str, boolean z) {
        Intrinsics.checkNotNullParameter(producerContext, "producerContext");
        Intrinsics.checkNotNullParameter(str, "producerName");
        for (RequestListener2 onUltimateProducerReached : this.requestListeners) {
            try {
                onUltimateProducerReached.onUltimateProducerReached(producerContext, str, z);
            } catch (Exception e) {
                FLog.e(TAG, "InternalListener exception in onProducerFinishWithSuccess", (Throwable) e);
            }
        }
    }

    public void onRequestSuccess(ProducerContext producerContext) {
        Intrinsics.checkNotNullParameter(producerContext, "producerContext");
        for (RequestListener2 onRequestSuccess : this.requestListeners) {
            try {
                onRequestSuccess.onRequestSuccess(producerContext);
            } catch (Exception e) {
                FLog.e(TAG, "InternalListener exception in onRequestSuccess", (Throwable) e);
            }
        }
    }

    public void onRequestFailure(ProducerContext producerContext, Throwable th) {
        Intrinsics.checkNotNullParameter(producerContext, "producerContext");
        Intrinsics.checkNotNullParameter(th, "throwable");
        for (RequestListener2 onRequestFailure : this.requestListeners) {
            try {
                onRequestFailure.onRequestFailure(producerContext, th);
            } catch (Exception e) {
                FLog.e(TAG, "InternalListener exception in onRequestFailure", (Throwable) e);
            }
        }
    }

    public void onRequestCancellation(ProducerContext producerContext) {
        Intrinsics.checkNotNullParameter(producerContext, "producerContext");
        for (RequestListener2 onRequestCancellation : this.requestListeners) {
            try {
                onRequestCancellation.onRequestCancellation(producerContext);
            } catch (Exception e) {
                FLog.e(TAG, "InternalListener exception in onRequestCancellation", (Throwable) e);
            }
        }
    }
}
