package com.adsbynimbus.request;

import kotlin.Metadata;
import okhttp3.Request;
import okhttp3.RequestBody;

@Metadata(d1 = {"\u0000#\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016¨\u0006\n"}, d2 = {"com/adsbynimbus/request/GzipRequestInterceptor$intercept$2$1", "Lokhttp3/RequestBody;", "contentLength", "", "contentType", "Lokhttp3/MediaType;", "writeTo", "", "sink", "Lokio/BufferedSink;", "request_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: OkHttpNimbusClient.kt */
public final class GzipRequestInterceptor$intercept$2$1 extends RequestBody {
    final /* synthetic */ Request $req;
    final /* synthetic */ GzipRequestInterceptor this$0;

    public long contentLength() {
        return -1;
    }

    GzipRequestInterceptor$intercept$2$1(Request request, GzipRequestInterceptor gzipRequestInterceptor) {
        this.$req = request;
        this.this$0 = gzipRequestInterceptor;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0008, code lost:
        r0 = r0.contentType();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public okhttp3.MediaType contentType() {
        /*
            r1 = this;
            okhttp3.Request r0 = r1.$req
            okhttp3.RequestBody r0 = r0.body()
            if (r0 == 0) goto L_0x000e
            okhttp3.MediaType r0 = r0.contentType()
            if (r0 != 0) goto L_0x0014
        L_0x000e:
            com.adsbynimbus.request.GzipRequestInterceptor r0 = r1.this$0
            okhttp3.MediaType r0 = r0.getJsonMediaType()
        L_0x0014:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adsbynimbus.request.GzipRequestInterceptor$intercept$2$1.contentType():okhttp3.MediaType");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x002c, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002d, code lost:
        kotlin.io.CloseableKt.closeFinally(r3, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0030, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void writeTo(okio.BufferedSink r3) throws java.io.IOException {
        /*
            r2 = this;
            java.lang.String r0 = "sink"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            okio.GzipSink r0 = new okio.GzipSink
            okio.Sink r3 = (okio.Sink) r3
            r0.<init>(r3)
            okio.Sink r0 = (okio.Sink) r0
            okio.BufferedSink r3 = okio.Okio.buffer((okio.Sink) r0)
            java.io.Closeable r3 = (java.io.Closeable) r3
            okhttp3.Request r0 = r2.$req
            r1 = r3
            okio.BufferedSink r1 = (okio.BufferedSink) r1     // Catch:{ all -> 0x002a }
            okhttp3.RequestBody r0 = r0.body()     // Catch:{ all -> 0x002a }
            if (r0 == 0) goto L_0x0025
            r0.writeTo(r1)     // Catch:{ all -> 0x002a }
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x002a }
        L_0x0025:
            r0 = 0
            kotlin.io.CloseableKt.closeFinally(r3, r0)
            return
        L_0x002a:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x002c }
        L_0x002c:
            r1 = move-exception
            kotlin.io.CloseableKt.closeFinally(r3, r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adsbynimbus.request.GzipRequestInterceptor$intercept$2$1.writeTo(okio.BufferedSink):void");
    }
}
