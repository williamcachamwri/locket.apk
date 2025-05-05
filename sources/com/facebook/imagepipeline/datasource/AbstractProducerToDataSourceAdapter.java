package com.facebook.imagepipeline.datasource;

import com.facebook.common.internal.Preconditions;
import com.facebook.datasource.AbstractDataSource;
import com.facebook.imagepipeline.listener.RequestListener2;
import com.facebook.imagepipeline.producers.BaseConsumer;
import com.facebook.imagepipeline.producers.Consumer;
import com.facebook.imagepipeline.producers.ProducerContext;
import com.facebook.imagepipeline.producers.SettableProducerContext;
import com.facebook.imagepipeline.request.HasImageRequest;
import com.facebook.imagepipeline.request.ImageRequest;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b'\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\u00020\u0003B%\b\u0004\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\b\u0010\u0013\u001a\u00020\u0014H\u0016J\u000e\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00000\u0016H\u0002J\u001e\u0010\u0017\u001a\u0010\u0012\u0004\u0012\u00020\u0019\u0012\u0006\u0012\u0004\u0018\u00010\u001a0\u00182\u0006\u0010\u001b\u001a\u00020\u001cH\u0004J\b\u0010\u001d\u001a\u00020\u001eH\u0002J\u0010\u0010\u001f\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020!H\u0002J'\u0010\"\u001a\u00020\u001e2\b\u0010#\u001a\u0004\u0018\u00018\u00002\u0006\u0010$\u001a\u00020%2\u0006\u0010\u001b\u001a\u00020\u001cH\u0014¢\u0006\u0002\u0010&R\u0016\u0010\u000b\u001a\u0004\u0018\u00010\f8VX\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006'"}, d2 = {"Lcom/facebook/imagepipeline/datasource/AbstractProducerToDataSourceAdapter;", "T", "Lcom/facebook/datasource/AbstractDataSource;", "Lcom/facebook/imagepipeline/request/HasImageRequest;", "producer", "Lcom/facebook/imagepipeline/producers/Producer;", "settableProducerContext", "Lcom/facebook/imagepipeline/producers/SettableProducerContext;", "requestListener", "Lcom/facebook/imagepipeline/listener/RequestListener2;", "(Lcom/facebook/imagepipeline/producers/Producer;Lcom/facebook/imagepipeline/producers/SettableProducerContext;Lcom/facebook/imagepipeline/listener/RequestListener2;)V", "imageRequest", "Lcom/facebook/imagepipeline/request/ImageRequest;", "getImageRequest", "()Lcom/facebook/imagepipeline/request/ImageRequest;", "getRequestListener", "()Lcom/facebook/imagepipeline/listener/RequestListener2;", "getSettableProducerContext", "()Lcom/facebook/imagepipeline/producers/SettableProducerContext;", "close", "", "createConsumer", "Lcom/facebook/imagepipeline/producers/Consumer;", "getExtras", "", "", "", "producerContext", "Lcom/facebook/imagepipeline/producers/ProducerContext;", "onCancellationImpl", "", "onFailureImpl", "throwable", "", "onNewResultImpl", "result", "status", "", "(Ljava/lang/Object;ILcom/facebook/imagepipeline/producers/ProducerContext;)V", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: AbstractProducerToDataSourceAdapter.kt */
public abstract class AbstractProducerToDataSourceAdapter<T> extends AbstractDataSource<T> implements HasImageRequest {
    private final RequestListener2 requestListener;
    private final SettableProducerContext settableProducerContext;

    public final SettableProducerContext getSettableProducerContext() {
        return this.settableProducerContext;
    }

    public final RequestListener2 getRequestListener() {
        return this.requestListener;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00cc, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00d0, code lost:
        throw r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected AbstractProducerToDataSourceAdapter(com.facebook.imagepipeline.producers.Producer<T> r4, com.facebook.imagepipeline.producers.SettableProducerContext r5, com.facebook.imagepipeline.listener.RequestListener2 r6) {
        /*
            r3 = this;
            java.lang.String r0 = "producer"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            java.lang.String r0 = "settableProducerContext"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            java.lang.String r0 = "requestListener"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            r3.<init>()
            r3.settableProducerContext = r5
            r3.requestListener = r6
            com.facebook.imagepipeline.systrace.FrescoSystrace r0 = com.facebook.imagepipeline.systrace.FrescoSystrace.INSTANCE
            boolean r0 = com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing()
            java.lang.String r1 = "AbstractProducerToDataSourceAdapter()->produceResult"
            java.lang.String r2 = "AbstractProducerToDataSourceAdapter()->onRequestStart"
            if (r0 != 0) goto L_0x0075
            java.util.Map r0 = r5.getExtras()
            r3.setExtras(r0)
            com.facebook.imagepipeline.systrace.FrescoSystrace r0 = com.facebook.imagepipeline.systrace.FrescoSystrace.INSTANCE
            boolean r0 = com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing()
            if (r0 != 0) goto L_0x0039
            r0 = r5
            com.facebook.imagepipeline.producers.ProducerContext r0 = (com.facebook.imagepipeline.producers.ProducerContext) r0
            r6.onRequestStart(r0)
            goto L_0x0047
        L_0x0039:
            com.facebook.imagepipeline.systrace.FrescoSystrace.beginSection(r2)
            r0 = r5
            com.facebook.imagepipeline.producers.ProducerContext r0 = (com.facebook.imagepipeline.producers.ProducerContext) r0     // Catch:{ all -> 0x0070 }
            r6.onRequestStart(r0)     // Catch:{ all -> 0x0070 }
            kotlin.Unit r6 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0070 }
            com.facebook.imagepipeline.systrace.FrescoSystrace.endSection()
        L_0x0047:
            com.facebook.imagepipeline.systrace.FrescoSystrace r6 = com.facebook.imagepipeline.systrace.FrescoSystrace.INSTANCE
            boolean r6 = com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing()
            if (r6 != 0) goto L_0x0059
            com.facebook.imagepipeline.producers.Consumer r6 = r3.createConsumer()
            com.facebook.imagepipeline.producers.ProducerContext r5 = (com.facebook.imagepipeline.producers.ProducerContext) r5
            r4.produceResults(r6, r5)
            goto L_0x00c6
        L_0x0059:
            com.facebook.imagepipeline.systrace.FrescoSystrace.beginSection(r1)
            com.facebook.imagepipeline.producers.Consumer r6 = r3.createConsumer()     // Catch:{ all -> 0x006b }
            com.facebook.imagepipeline.producers.ProducerContext r5 = (com.facebook.imagepipeline.producers.ProducerContext) r5     // Catch:{ all -> 0x006b }
            r4.produceResults(r6, r5)     // Catch:{ all -> 0x006b }
            kotlin.Unit r4 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x006b }
            com.facebook.imagepipeline.systrace.FrescoSystrace.endSection()
            goto L_0x00c6
        L_0x006b:
            r4 = move-exception
            com.facebook.imagepipeline.systrace.FrescoSystrace.endSection()
            throw r4
        L_0x0070:
            r4 = move-exception
            com.facebook.imagepipeline.systrace.FrescoSystrace.endSection()
            throw r4
        L_0x0075:
            java.lang.String r0 = "AbstractProducerToDataSourceAdapter()"
            com.facebook.imagepipeline.systrace.FrescoSystrace.beginSection(r0)
            java.util.Map r0 = r5.getExtras()     // Catch:{ all -> 0x00d1 }
            r3.setExtras(r0)     // Catch:{ all -> 0x00d1 }
            com.facebook.imagepipeline.systrace.FrescoSystrace r0 = com.facebook.imagepipeline.systrace.FrescoSystrace.INSTANCE     // Catch:{ all -> 0x00d1 }
            boolean r0 = com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing()     // Catch:{ all -> 0x00d1 }
            if (r0 != 0) goto L_0x0090
            r0 = r5
            com.facebook.imagepipeline.producers.ProducerContext r0 = (com.facebook.imagepipeline.producers.ProducerContext) r0     // Catch:{ all -> 0x00d1 }
            r6.onRequestStart(r0)     // Catch:{ all -> 0x00d1 }
            goto L_0x009e
        L_0x0090:
            com.facebook.imagepipeline.systrace.FrescoSystrace.beginSection(r2)     // Catch:{ all -> 0x00d1 }
            r0 = r5
            com.facebook.imagepipeline.producers.ProducerContext r0 = (com.facebook.imagepipeline.producers.ProducerContext) r0     // Catch:{ all -> 0x00cc }
            r6.onRequestStart(r0)     // Catch:{ all -> 0x00cc }
            kotlin.Unit r6 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x00cc }
            com.facebook.imagepipeline.systrace.FrescoSystrace.endSection()     // Catch:{ all -> 0x00d1 }
        L_0x009e:
            com.facebook.imagepipeline.systrace.FrescoSystrace r6 = com.facebook.imagepipeline.systrace.FrescoSystrace.INSTANCE     // Catch:{ all -> 0x00d1 }
            boolean r6 = com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing()     // Catch:{ all -> 0x00d1 }
            if (r6 != 0) goto L_0x00b0
            com.facebook.imagepipeline.producers.Consumer r6 = r3.createConsumer()     // Catch:{ all -> 0x00d1 }
            com.facebook.imagepipeline.producers.ProducerContext r5 = (com.facebook.imagepipeline.producers.ProducerContext) r5     // Catch:{ all -> 0x00d1 }
            r4.produceResults(r6, r5)     // Catch:{ all -> 0x00d1 }
            goto L_0x00c1
        L_0x00b0:
            com.facebook.imagepipeline.systrace.FrescoSystrace.beginSection(r1)     // Catch:{ all -> 0x00d1 }
            com.facebook.imagepipeline.producers.Consumer r6 = r3.createConsumer()     // Catch:{ all -> 0x00c7 }
            com.facebook.imagepipeline.producers.ProducerContext r5 = (com.facebook.imagepipeline.producers.ProducerContext) r5     // Catch:{ all -> 0x00c7 }
            r4.produceResults(r6, r5)     // Catch:{ all -> 0x00c7 }
            kotlin.Unit r4 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x00c7 }
            com.facebook.imagepipeline.systrace.FrescoSystrace.endSection()     // Catch:{ all -> 0x00d1 }
        L_0x00c1:
            kotlin.Unit r4 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x00d1 }
            com.facebook.imagepipeline.systrace.FrescoSystrace.endSection()
        L_0x00c6:
            return
        L_0x00c7:
            r4 = move-exception
            com.facebook.imagepipeline.systrace.FrescoSystrace.endSection()     // Catch:{ all -> 0x00d1 }
            throw r4     // Catch:{ all -> 0x00d1 }
        L_0x00cc:
            r4 = move-exception
            com.facebook.imagepipeline.systrace.FrescoSystrace.endSection()     // Catch:{ all -> 0x00d1 }
            throw r4     // Catch:{ all -> 0x00d1 }
        L_0x00d1:
            r4 = move-exception
            com.facebook.imagepipeline.systrace.FrescoSystrace.endSection()
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.datasource.AbstractProducerToDataSourceAdapter.<init>(com.facebook.imagepipeline.producers.Producer, com.facebook.imagepipeline.producers.SettableProducerContext, com.facebook.imagepipeline.listener.RequestListener2):void");
    }

    private final Consumer<T> createConsumer() {
        return new AbstractProducerToDataSourceAdapter$createConsumer$1(this);
    }

    /* access modifiers changed from: protected */
    public void onNewResultImpl(T t, int i, ProducerContext producerContext) {
        Intrinsics.checkNotNullParameter(producerContext, "producerContext");
        boolean isLast = BaseConsumer.isLast(i);
        if (super.setResult(t, isLast, getExtras(producerContext)) && isLast) {
            this.requestListener.onRequestSuccess(this.settableProducerContext);
        }
    }

    /* access modifiers changed from: protected */
    public final Map<String, Object> getExtras(ProducerContext producerContext) {
        Intrinsics.checkNotNullParameter(producerContext, "producerContext");
        return producerContext.getExtras();
    }

    /* access modifiers changed from: private */
    public final void onFailureImpl(Throwable th) {
        if (super.setFailure(th, getExtras(this.settableProducerContext))) {
            this.requestListener.onRequestFailure(this.settableProducerContext, th);
        }
    }

    /* access modifiers changed from: private */
    public final synchronized void onCancellationImpl() {
        Preconditions.checkState(isClosed());
    }

    public ImageRequest getImageRequest() {
        return this.settableProducerContext.getImageRequest();
    }

    public boolean close() {
        if (!super.close()) {
            return false;
        }
        if (super.isFinished()) {
            return true;
        }
        this.requestListener.onRequestCancellation(this.settableProducerContext);
        this.settableProducerContext.cancel();
        return true;
    }
}
