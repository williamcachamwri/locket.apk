package io.grpc.okhttp.internal.proxy;

import io.grpc.okhttp.internal.Headers;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

public final class Request {
    private final Headers headers;
    private final HttpUrl url;

    private Request(Builder builder) {
        this.url = builder.url;
        this.headers = builder.headers.build();
    }

    public HttpUrl httpUrl() {
        return this.url;
    }

    public Headers headers() {
        return this.headers;
    }

    public Builder newBuilder() {
        return new Builder();
    }

    public String toString() {
        return "Request{url=" + this.url + AbstractJsonLexerKt.END_OBJ;
    }

    public static class Builder {
        /* access modifiers changed from: private */
        public Headers.Builder headers = new Headers.Builder();
        /* access modifiers changed from: private */
        public HttpUrl url;

        public Builder url(HttpUrl httpUrl) {
            if (httpUrl != null) {
                this.url = httpUrl;
                return this;
            }
            throw new IllegalArgumentException("url == null");
        }

        public Builder header(String str, String str2) {
            this.headers.set(str, str2);
            return this;
        }

        public Request build() {
            if (this.url != null) {
                return new Request(this);
            }
            throw new IllegalStateException("url == null");
        }
    }
}
