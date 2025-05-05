package okhttp3.internal.http;

import kotlin.Metadata;
import okhttp3.Interceptor;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lokhttp3/internal/http/CallServerInterceptor;", "Lokhttp3/Interceptor;", "forWebSocket", "", "(Z)V", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "shouldIgnoreAndWaitForRealResponse", "code", "", "okhttp"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: CallServerInterceptor.kt */
public final class CallServerInterceptor implements Interceptor {
    private final boolean forWebSocket;

    private final boolean shouldIgnoreAndWaitForRealResponse(int i) {
        if (i == 100) {
            return true;
        }
        return 102 <= i && i < 200;
    }

    public CallServerInterceptor(boolean z) {
        this.forWebSocket = z;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0093, code lost:
        if (r4.isDuplex() == false) goto L_0x0095;
     */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00a3  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00ab A[SYNTHETIC, Splitter:B:40:0x00ab] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00e2 A[Catch:{ IOException -> 0x019f }] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x011d A[Catch:{ IOException -> 0x019f }] */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x012c A[Catch:{ IOException -> 0x019f }] */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x0166 A[Catch:{ IOException -> 0x019f }] */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x016b A[Catch:{ IOException -> 0x019f }] */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x0173 A[Catch:{ IOException -> 0x019f }] */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x01ad  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public okhttp3.Response intercept(okhttp3.Interceptor.Chain r15) throws java.io.IOException {
        /*
            r14 = this;
            java.lang.String r0 = "Connection"
            java.lang.String r1 = "close"
            java.lang.String r2 = "HTTP "
            java.lang.String r3 = "chain"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r15, r3)
            okhttp3.internal.http.RealInterceptorChain r15 = (okhttp3.internal.http.RealInterceptorChain) r15
            okhttp3.internal.connection.Exchange r3 = r15.getExchange$okhttp()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)
            okhttp3.Request r15 = r15.getRequest$okhttp()
            okhttp3.RequestBody r4 = r15.body()
            long r5 = java.lang.System.currentTimeMillis()
            r7 = 0
            r8 = 1
            r9 = 0
            r3.writeRequestHeaders(r15)     // Catch:{ IOException -> 0x009c }
            java.lang.String r10 = r15.method()     // Catch:{ IOException -> 0x009c }
            boolean r10 = okhttp3.internal.http.HttpMethod.permitsRequestBody(r10)     // Catch:{ IOException -> 0x009c }
            if (r10 == 0) goto L_0x0088
            if (r4 == 0) goto L_0x0088
            java.lang.String r10 = "100-continue"
            java.lang.String r11 = "Expect"
            java.lang.String r11 = r15.header(r11)     // Catch:{ IOException -> 0x009c }
            boolean r10 = kotlin.text.StringsKt.equals(r10, r11, r8)     // Catch:{ IOException -> 0x009c }
            if (r10 == 0) goto L_0x004f
            r3.flushRequest()     // Catch:{ IOException -> 0x009c }
            okhttp3.Response$Builder r10 = r3.readResponseHeaders(r8)     // Catch:{ IOException -> 0x009c }
            r3.responseHeadersStart()     // Catch:{ IOException -> 0x004c }
            r11 = r7
            goto L_0x0051
        L_0x004c:
            r4 = move-exception
            r11 = r8
            goto L_0x009f
        L_0x004f:
            r11 = r8
            r10 = r9
        L_0x0051:
            if (r10 != 0) goto L_0x0077
            boolean r12 = r4.isDuplex()     // Catch:{ IOException -> 0x009a }
            if (r12 == 0) goto L_0x0068
            r3.flushRequest()     // Catch:{ IOException -> 0x009a }
            okio.Sink r12 = r3.createRequestBody(r15, r8)     // Catch:{ IOException -> 0x009a }
            okio.BufferedSink r12 = okio.Okio.buffer((okio.Sink) r12)     // Catch:{ IOException -> 0x009a }
            r4.writeTo(r12)     // Catch:{ IOException -> 0x009a }
            goto L_0x008d
        L_0x0068:
            okio.Sink r12 = r3.createRequestBody(r15, r7)     // Catch:{ IOException -> 0x009a }
            okio.BufferedSink r12 = okio.Okio.buffer((okio.Sink) r12)     // Catch:{ IOException -> 0x009a }
            r4.writeTo(r12)     // Catch:{ IOException -> 0x009a }
            r12.close()     // Catch:{ IOException -> 0x009a }
            goto L_0x008d
        L_0x0077:
            r3.noRequestBody()     // Catch:{ IOException -> 0x009a }
            okhttp3.internal.connection.RealConnection r12 = r3.getConnection$okhttp()     // Catch:{ IOException -> 0x009a }
            boolean r12 = r12.isMultiplexed$okhttp()     // Catch:{ IOException -> 0x009a }
            if (r12 != 0) goto L_0x008d
            r3.noNewExchangesOnConnection()     // Catch:{ IOException -> 0x009a }
            goto L_0x008d
        L_0x0088:
            r3.noRequestBody()     // Catch:{ IOException -> 0x009c }
            r11 = r8
            r10 = r9
        L_0x008d:
            if (r4 == 0) goto L_0x0095
            boolean r4 = r4.isDuplex()     // Catch:{ IOException -> 0x009a }
            if (r4 != 0) goto L_0x0098
        L_0x0095:
            r3.finishRequest()     // Catch:{ IOException -> 0x009a }
        L_0x0098:
            r4 = r9
            goto L_0x00a9
        L_0x009a:
            r4 = move-exception
            goto L_0x009f
        L_0x009c:
            r4 = move-exception
            r11 = r8
            r10 = r9
        L_0x009f:
            boolean r12 = r4 instanceof okhttp3.internal.http2.ConnectionShutdownException
            if (r12 != 0) goto L_0x01ad
            boolean r12 = r3.getHasFailure$okhttp()
            if (r12 == 0) goto L_0x01ac
        L_0x00a9:
            if (r10 != 0) goto L_0x00b8
            okhttp3.Response$Builder r10 = r3.readResponseHeaders(r7)     // Catch:{ IOException -> 0x019f }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r10)     // Catch:{ IOException -> 0x019f }
            if (r11 == 0) goto L_0x00b8
            r3.responseHeadersStart()     // Catch:{ IOException -> 0x019f }
            r11 = r7
        L_0x00b8:
            okhttp3.Response$Builder r10 = r10.request(r15)     // Catch:{ IOException -> 0x019f }
            okhttp3.internal.connection.RealConnection r12 = r3.getConnection$okhttp()     // Catch:{ IOException -> 0x019f }
            okhttp3.Handshake r12 = r12.handshake()     // Catch:{ IOException -> 0x019f }
            okhttp3.Response$Builder r10 = r10.handshake(r12)     // Catch:{ IOException -> 0x019f }
            okhttp3.Response$Builder r10 = r10.sentRequestAtMillis(r5)     // Catch:{ IOException -> 0x019f }
            long r12 = java.lang.System.currentTimeMillis()     // Catch:{ IOException -> 0x019f }
            okhttp3.Response$Builder r10 = r10.receivedResponseAtMillis(r12)     // Catch:{ IOException -> 0x019f }
            okhttp3.Response r10 = r10.build()     // Catch:{ IOException -> 0x019f }
            int r12 = r10.code()     // Catch:{ IOException -> 0x019f }
            boolean r13 = r14.shouldIgnoreAndWaitForRealResponse(r12)     // Catch:{ IOException -> 0x019f }
            if (r13 == 0) goto L_0x0112
            okhttp3.Response$Builder r7 = r3.readResponseHeaders(r7)     // Catch:{ IOException -> 0x019f }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r7)     // Catch:{ IOException -> 0x019f }
            if (r11 == 0) goto L_0x00ee
            r3.responseHeadersStart()     // Catch:{ IOException -> 0x019f }
        L_0x00ee:
            okhttp3.Response$Builder r15 = r7.request(r15)     // Catch:{ IOException -> 0x019f }
            okhttp3.internal.connection.RealConnection r7 = r3.getConnection$okhttp()     // Catch:{ IOException -> 0x019f }
            okhttp3.Handshake r7 = r7.handshake()     // Catch:{ IOException -> 0x019f }
            okhttp3.Response$Builder r15 = r15.handshake(r7)     // Catch:{ IOException -> 0x019f }
            okhttp3.Response$Builder r15 = r15.sentRequestAtMillis(r5)     // Catch:{ IOException -> 0x019f }
            long r5 = java.lang.System.currentTimeMillis()     // Catch:{ IOException -> 0x019f }
            okhttp3.Response$Builder r15 = r15.receivedResponseAtMillis(r5)     // Catch:{ IOException -> 0x019f }
            okhttp3.Response r10 = r15.build()     // Catch:{ IOException -> 0x019f }
            int r12 = r10.code()     // Catch:{ IOException -> 0x019f }
        L_0x0112:
            r3.responseHeadersEnd(r10)     // Catch:{ IOException -> 0x019f }
            boolean r15 = r14.forWebSocket     // Catch:{ IOException -> 0x019f }
            if (r15 == 0) goto L_0x012c
            r15 = 101(0x65, float:1.42E-43)
            if (r12 != r15) goto L_0x012c
            okhttp3.Response$Builder r15 = r10.newBuilder()     // Catch:{ IOException -> 0x019f }
            okhttp3.ResponseBody r5 = okhttp3.internal.Util.EMPTY_RESPONSE     // Catch:{ IOException -> 0x019f }
            okhttp3.Response$Builder r15 = r15.body(r5)     // Catch:{ IOException -> 0x019f }
            okhttp3.Response r15 = r15.build()     // Catch:{ IOException -> 0x019f }
            goto L_0x013c
        L_0x012c:
            okhttp3.Response$Builder r15 = r10.newBuilder()     // Catch:{ IOException -> 0x019f }
            okhttp3.ResponseBody r5 = r3.openResponseBody(r10)     // Catch:{ IOException -> 0x019f }
            okhttp3.Response$Builder r15 = r15.body(r5)     // Catch:{ IOException -> 0x019f }
            okhttp3.Response r15 = r15.build()     // Catch:{ IOException -> 0x019f }
        L_0x013c:
            okhttp3.Request r5 = r15.request()     // Catch:{ IOException -> 0x019f }
            java.lang.String r5 = r5.header(r0)     // Catch:{ IOException -> 0x019f }
            boolean r5 = kotlin.text.StringsKt.equals(r1, r5, r8)     // Catch:{ IOException -> 0x019f }
            if (r5 != 0) goto L_0x0155
            r5 = 2
            java.lang.String r0 = okhttp3.Response.header$default(r15, r0, r9, r5, r9)     // Catch:{ IOException -> 0x019f }
            boolean r0 = kotlin.text.StringsKt.equals(r1, r0, r8)     // Catch:{ IOException -> 0x019f }
            if (r0 == 0) goto L_0x0158
        L_0x0155:
            r3.noNewExchangesOnConnection()     // Catch:{ IOException -> 0x019f }
        L_0x0158:
            r0 = 204(0xcc, float:2.86E-43)
            if (r12 == r0) goto L_0x0160
            r0 = 205(0xcd, float:2.87E-43)
            if (r12 != r0) goto L_0x019e
        L_0x0160:
            okhttp3.ResponseBody r0 = r15.body()     // Catch:{ IOException -> 0x019f }
            if (r0 == 0) goto L_0x016b
            long r0 = r0.contentLength()     // Catch:{ IOException -> 0x019f }
            goto L_0x016d
        L_0x016b:
            r0 = -1
        L_0x016d:
            r5 = 0
            int r0 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r0 <= 0) goto L_0x019e
            java.net.ProtocolException r0 = new java.net.ProtocolException     // Catch:{ IOException -> 0x019f }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x019f }
            r1.<init>(r2)     // Catch:{ IOException -> 0x019f }
            java.lang.StringBuilder r1 = r1.append(r12)     // Catch:{ IOException -> 0x019f }
            java.lang.String r2 = " had non-zero Content-Length: "
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ IOException -> 0x019f }
            okhttp3.ResponseBody r15 = r15.body()     // Catch:{ IOException -> 0x019f }
            if (r15 == 0) goto L_0x0192
            long r2 = r15.contentLength()     // Catch:{ IOException -> 0x019f }
            java.lang.Long r9 = java.lang.Long.valueOf(r2)     // Catch:{ IOException -> 0x019f }
        L_0x0192:
            java.lang.StringBuilder r15 = r1.append(r9)     // Catch:{ IOException -> 0x019f }
            java.lang.String r15 = r15.toString()     // Catch:{ IOException -> 0x019f }
            r0.<init>(r15)     // Catch:{ IOException -> 0x019f }
            throw r0     // Catch:{ IOException -> 0x019f }
        L_0x019e:
            return r15
        L_0x019f:
            r15 = move-exception
            if (r4 == 0) goto L_0x01ab
            r0 = r4
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            java.lang.Throwable r15 = (java.lang.Throwable) r15
            kotlin.ExceptionsKt.addSuppressed(r0, r15)
            throw r4
        L_0x01ab:
            throw r15
        L_0x01ac:
            throw r4
        L_0x01ad:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http.CallServerInterceptor.intercept(okhttp3.Interceptor$Chain):okhttp3.Response");
    }
}
