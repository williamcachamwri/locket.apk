package com.adsbynimbus.request;

import com.google.common.net.HttpHeaders;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000b"}, d2 = {"Lcom/adsbynimbus/request/GzipRequestInterceptor;", "Lokhttp3/Interceptor;", "jsonMediaType", "Lokhttp3/MediaType;", "(Lokhttp3/MediaType;)V", "getJsonMediaType", "()Lokhttp3/MediaType;", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "request_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: OkHttpNimbusClient.kt */
public final class GzipRequestInterceptor implements Interceptor {
    private final MediaType jsonMediaType;

    public GzipRequestInterceptor(MediaType mediaType) {
        Intrinsics.checkNotNullParameter(mediaType, "jsonMediaType");
        this.jsonMediaType = mediaType;
    }

    public final MediaType getJsonMediaType() {
        return this.jsonMediaType;
    }

    public Response intercept(Interceptor.Chain chain) throws IOException {
        Intrinsics.checkNotNullParameter(chain, "chain");
        Request request = chain.request();
        if (!(request.header(HttpHeaders.CONTENT_ENCODING) != null || Intrinsics.areEqual((Object) request.method(), (Object) "GET"))) {
            request = null;
        }
        if (request == null) {
            Request request2 = chain.request();
            request = request2.newBuilder().header(HttpHeaders.CONTENT_ENCODING, "gzip").method(request2.method(), new GzipRequestInterceptor$intercept$2$1(request2, this)).build();
        }
        return chain.proceed(request);
    }
}
