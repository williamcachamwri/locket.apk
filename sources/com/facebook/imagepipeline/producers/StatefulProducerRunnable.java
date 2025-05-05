package com.facebook.imagepipeline.producers;

import com.facebook.common.executors.StatefulRunnable;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\b&\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B+\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\u0017\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00018\u0000H$¢\u0006\u0002\u0010\u0013J \u0010\u0014\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n\u0018\u00010\r2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0014J)\u0010\u0017\u001a\u0014\u0012\u0006\u0012\u0004\u0018\u00010\n\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\r2\b\u0010\u0012\u001a\u0004\u0018\u00018\u0000H\u0014¢\u0006\u0002\u0010\u0018J\b\u0010\u0019\u001a\u00020\u0011H\u0014J\u0010\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u001b\u001a\u00020\u0016H\u0014J\u0017\u0010\u001c\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00018\u0000H\u0014¢\u0006\u0002\u0010\u0013R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004X\u0004¢\u0006\u0002\n\u0000R\"\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n\u0018\u00010\r8TX\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/facebook/imagepipeline/producers/StatefulProducerRunnable;", "T", "Lcom/facebook/common/executors/StatefulRunnable;", "consumer", "Lcom/facebook/imagepipeline/producers/Consumer;", "producerListener", "Lcom/facebook/imagepipeline/producers/ProducerListener2;", "producerContext", "Lcom/facebook/imagepipeline/producers/ProducerContext;", "producerName", "", "(Lcom/facebook/imagepipeline/producers/Consumer;Lcom/facebook/imagepipeline/producers/ProducerListener2;Lcom/facebook/imagepipeline/producers/ProducerContext;Ljava/lang/String;)V", "extraMapOnCancellation", "", "getExtraMapOnCancellation", "()Ljava/util/Map;", "disposeResult", "", "result", "(Ljava/lang/Object;)V", "getExtraMapOnFailure", "exception", "Ljava/lang/Exception;", "getExtraMapOnSuccess", "(Ljava/lang/Object;)Ljava/util/Map;", "onCancellation", "onFailure", "e", "onSuccess", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: StatefulProducerRunnable.kt */
public abstract class StatefulProducerRunnable<T> extends StatefulRunnable<T> {
    private final Consumer<T> consumer;
    private final ProducerContext producerContext;
    private final ProducerListener2 producerListener;
    private final String producerName;

    /* access modifiers changed from: protected */
    public abstract void disposeResult(T t);

    /* access modifiers changed from: protected */
    public Map<String, String> getExtraMapOnCancellation() {
        return null;
    }

    /* access modifiers changed from: protected */
    public Map<String, String> getExtraMapOnFailure(Exception exc) {
        return null;
    }

    /* access modifiers changed from: protected */
    public Map<String, String> getExtraMapOnSuccess(T t) {
        return null;
    }

    public StatefulProducerRunnable(Consumer<T> consumer2, ProducerListener2 producerListener2, ProducerContext producerContext2, String str) {
        Intrinsics.checkNotNullParameter(consumer2, "consumer");
        Intrinsics.checkNotNullParameter(producerListener2, "producerListener");
        Intrinsics.checkNotNullParameter(producerContext2, "producerContext");
        Intrinsics.checkNotNullParameter(str, "producerName");
        this.consumer = consumer2;
        this.producerListener = producerListener2;
        this.producerContext = producerContext2;
        this.producerName = str;
        producerListener2.onProducerStart(producerContext2, str);
    }

    /* access modifiers changed from: protected */
    public void onSuccess(T t) {
        ProducerListener2 producerListener2 = this.producerListener;
        ProducerContext producerContext2 = this.producerContext;
        String str = this.producerName;
        producerListener2.onProducerFinishWithSuccess(producerContext2, str, producerListener2.requiresExtraMap(producerContext2, str) ? getExtraMapOnSuccess(t) : null);
        this.consumer.onNewResult(t, 1);
    }

    /* access modifiers changed from: protected */
    public void onFailure(Exception exc) {
        Map<String, String> map;
        Intrinsics.checkNotNullParameter(exc, "e");
        ProducerListener2 producerListener2 = this.producerListener;
        ProducerContext producerContext2 = this.producerContext;
        String str = this.producerName;
        Throwable th = exc;
        if (producerListener2.requiresExtraMap(producerContext2, str)) {
            map = getExtraMapOnFailure(exc);
        } else {
            map = null;
        }
        producerListener2.onProducerFinishWithFailure(producerContext2, str, th, map);
        this.consumer.onFailure(th);
    }

    /* access modifiers changed from: protected */
    public void onCancellation() {
        ProducerListener2 producerListener2 = this.producerListener;
        ProducerContext producerContext2 = this.producerContext;
        String str = this.producerName;
        producerListener2.onProducerFinishWithCancellation(producerContext2, str, producerListener2.requiresExtraMap(producerContext2, str) ? getExtraMapOnCancellation() : null);
        this.consumer.onCancellation();
    }
}
