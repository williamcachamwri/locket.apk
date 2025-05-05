package com.facebook.imagepipeline.backends.okhttp3;

import android.net.Uri;
import android.os.SystemClock;
import com.facebook.imagepipeline.common.BytesRange;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.producers.BaseNetworkFetcher;
import com.facebook.imagepipeline.producers.Consumer;
import com.facebook.imagepipeline.producers.FetchState;
import com.facebook.imagepipeline.producers.NetworkFetcher;
import com.facebook.imagepipeline.producers.ProducerContext;
import com.google.common.net.HttpHeaders;
import com.google.firebase.perf.network.FirebasePerfOkHttpClient;
import io.sentry.SentryBaseEvent;
import java.util.Map;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Request;

@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u0000 (2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002()B\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005B!\b\u0007\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u001e\u0010\u000f\u001a\u00020\u00022\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0018\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J \u0010\u001a\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u001cH\u0014J&\u0010\u001d\u001a\u0010\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u001f\u0018\u00010\u001e2\u0006\u0010\u0017\u001a\u00020\u00022\u0006\u0010 \u001a\u00020!H\u0016J \u0010\"\u001a\u00020\u00162\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&2\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u0018\u0010'\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00022\u0006\u0010 \u001a\u00020!H\u0016R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000¨\u0006*"}, d2 = {"Lcom/facebook/imagepipeline/backends/okhttp3/OkHttpNetworkFetcher;", "Lcom/facebook/imagepipeline/producers/BaseNetworkFetcher;", "Lcom/facebook/imagepipeline/backends/okhttp3/OkHttpNetworkFetcher$OkHttpNetworkFetchState;", "okHttpClient", "Lokhttp3/OkHttpClient;", "(Lokhttp3/OkHttpClient;)V", "callFactory", "Lokhttp3/Call$Factory;", "cancellationExecutor", "Ljava/util/concurrent/Executor;", "disableOkHttpCache", "", "(Lokhttp3/Call$Factory;Ljava/util/concurrent/Executor;Z)V", "cacheControl", "Lokhttp3/CacheControl;", "createFetchState", "consumer", "Lcom/facebook/imagepipeline/producers/Consumer;", "Lcom/facebook/imagepipeline/image/EncodedImage;", "context", "Lcom/facebook/imagepipeline/producers/ProducerContext;", "fetch", "", "fetchState", "callback", "Lcom/facebook/imagepipeline/producers/NetworkFetcher$Callback;", "fetchWithRequest", "request", "Lokhttp3/Request;", "getExtraMap", "", "", "byteSize", "", "handleException", "call", "Lokhttp3/Call;", "e", "Ljava/lang/Exception;", "onFetchCompletion", "Companion", "OkHttpNetworkFetchState", "imagepipeline-okhttp3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: OkHttpNetworkFetcher.kt */
public class OkHttpNetworkFetcher extends BaseNetworkFetcher<OkHttpNetworkFetchState> {
    private static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String FETCH_TIME = "fetch_time";
    private static final String IMAGE_SIZE = "image_size";
    private static final String QUEUE_TIME = "queue_time";
    private static final String TOTAL_TIME = "total_time";
    private final CacheControl cacheControl;
    private final Call.Factory callFactory;
    /* access modifiers changed from: private */
    public final Executor cancellationExecutor;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public OkHttpNetworkFetcher(Call.Factory factory, Executor executor) {
        this(factory, executor, false, 4, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(factory, "callFactory");
        Intrinsics.checkNotNullParameter(executor, "cancellationExecutor");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ OkHttpNetworkFetcher(Call.Factory factory, Executor executor, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(factory, executor, (i & 4) != 0 ? true : z);
    }

    public OkHttpNetworkFetcher(Call.Factory factory, Executor executor, boolean z) {
        Intrinsics.checkNotNullParameter(factory, "callFactory");
        Intrinsics.checkNotNullParameter(executor, "cancellationExecutor");
        this.callFactory = factory;
        this.cancellationExecutor = executor;
        this.cacheControl = z ? new CacheControl.Builder().noStore().build() : null;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public OkHttpNetworkFetcher(okhttp3.OkHttpClient r8) {
        /*
            r7 = this;
            java.lang.String r0 = "okHttpClient"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
            r2 = r8
            okhttp3.Call$Factory r2 = (okhttp3.Call.Factory) r2
            okhttp3.Dispatcher r8 = r8.dispatcher()
            java.util.concurrent.ExecutorService r8 = r8.executorService()
            java.lang.String r0 = "okHttpClient.dispatcher().executorService()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, r0)
            r3 = r8
            java.util.concurrent.Executor r3 = (java.util.concurrent.Executor) r3
            r4 = 0
            r5 = 4
            r6 = 0
            r1 = r7
            r1.<init>(r2, r3, r4, r5, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.backends.okhttp3.OkHttpNetworkFetcher.<init>(okhttp3.OkHttpClient):void");
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u001b\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007R\u0012\u0010\b\u001a\u00020\t8\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\n\u001a\u00020\t8\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u000b\u001a\u00020\t8\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/facebook/imagepipeline/backends/okhttp3/OkHttpNetworkFetcher$OkHttpNetworkFetchState;", "Lcom/facebook/imagepipeline/producers/FetchState;", "consumer", "Lcom/facebook/imagepipeline/producers/Consumer;", "Lcom/facebook/imagepipeline/image/EncodedImage;", "producerContext", "Lcom/facebook/imagepipeline/producers/ProducerContext;", "(Lcom/facebook/imagepipeline/producers/Consumer;Lcom/facebook/imagepipeline/producers/ProducerContext;)V", "fetchCompleteTime", "", "responseTime", "submitTime", "imagepipeline-okhttp3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: OkHttpNetworkFetcher.kt */
    public static final class OkHttpNetworkFetchState extends FetchState {
        public long fetchCompleteTime;
        public long responseTime;
        public long submitTime;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public OkHttpNetworkFetchState(Consumer<EncodedImage> consumer, ProducerContext producerContext) {
            super(consumer, producerContext);
            Intrinsics.checkNotNullParameter(consumer, "consumer");
            Intrinsics.checkNotNullParameter(producerContext, "producerContext");
        }
    }

    public OkHttpNetworkFetchState createFetchState(Consumer<EncodedImage> consumer, ProducerContext producerContext) {
        Intrinsics.checkNotNullParameter(consumer, "consumer");
        Intrinsics.checkNotNullParameter(producerContext, "context");
        return new OkHttpNetworkFetchState(consumer, producerContext);
    }

    public void fetch(OkHttpNetworkFetchState okHttpNetworkFetchState, NetworkFetcher.Callback callback) {
        Intrinsics.checkNotNullParameter(okHttpNetworkFetchState, "fetchState");
        Intrinsics.checkNotNullParameter(callback, "callback");
        okHttpNetworkFetchState.submitTime = SystemClock.elapsedRealtime();
        Uri uri = okHttpNetworkFetchState.getUri();
        Intrinsics.checkNotNullExpressionValue(uri, "fetchState.uri");
        try {
            Request.Builder builder = new Request.Builder().url(uri.toString()).get();
            CacheControl cacheControl2 = this.cacheControl;
            if (cacheControl2 != null) {
                Intrinsics.checkNotNullExpressionValue(builder, "requestBuilder");
                builder.cacheControl(cacheControl2);
            }
            BytesRange bytesRange = okHttpNetworkFetchState.getContext().getImageRequest().getBytesRange();
            if (bytesRange != null) {
                builder.addHeader(HttpHeaders.RANGE, bytesRange.toHttpRangeHeaderValue());
            }
            Request build = builder.build();
            Intrinsics.checkNotNullExpressionValue(build, "requestBuilder.build()");
            fetchWithRequest(okHttpNetworkFetchState, callback, build);
        } catch (Exception e) {
            callback.onFailure(e);
        }
    }

    public void onFetchCompletion(OkHttpNetworkFetchState okHttpNetworkFetchState, int i) {
        Intrinsics.checkNotNullParameter(okHttpNetworkFetchState, "fetchState");
        okHttpNetworkFetchState.fetchCompleteTime = SystemClock.elapsedRealtime();
    }

    public Map<String, String> getExtraMap(OkHttpNetworkFetchState okHttpNetworkFetchState, int i) {
        Intrinsics.checkNotNullParameter(okHttpNetworkFetchState, "fetchState");
        return MapsKt.mapOf(TuplesKt.to(QUEUE_TIME, String.valueOf(okHttpNetworkFetchState.responseTime - okHttpNetworkFetchState.submitTime)), TuplesKt.to(FETCH_TIME, String.valueOf(okHttpNetworkFetchState.fetchCompleteTime - okHttpNetworkFetchState.responseTime)), TuplesKt.to(TOTAL_TIME, String.valueOf(okHttpNetworkFetchState.fetchCompleteTime - okHttpNetworkFetchState.submitTime)), TuplesKt.to("image_size", String.valueOf(i)));
    }

    /* access modifiers changed from: protected */
    public void fetchWithRequest(OkHttpNetworkFetchState okHttpNetworkFetchState, NetworkFetcher.Callback callback, Request request) {
        Intrinsics.checkNotNullParameter(okHttpNetworkFetchState, "fetchState");
        Intrinsics.checkNotNullParameter(callback, "callback");
        Intrinsics.checkNotNullParameter(request, SentryBaseEvent.JsonKeys.REQUEST);
        Call newCall = this.callFactory.newCall(request);
        okHttpNetworkFetchState.getContext().addCallbacks(new OkHttpNetworkFetcher$fetchWithRequest$1(newCall, this));
        FirebasePerfOkHttpClient.enqueue(newCall, new OkHttpNetworkFetcher$fetchWithRequest$2(okHttpNetworkFetchState, this, callback));
    }

    /* access modifiers changed from: private */
    public final void handleException(Call call, Exception exc, NetworkFetcher.Callback callback) {
        if (call.isCanceled()) {
            callback.onCancellation();
        } else {
            callback.onFailure(exc);
        }
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/facebook/imagepipeline/backends/okhttp3/OkHttpNetworkFetcher$Companion;", "", "()V", "FETCH_TIME", "", "IMAGE_SIZE", "QUEUE_TIME", "TOTAL_TIME", "imagepipeline-okhttp3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: OkHttpNetworkFetcher.kt */
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
