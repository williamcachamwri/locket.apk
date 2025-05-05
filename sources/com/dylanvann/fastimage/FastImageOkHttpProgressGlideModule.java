package com.dylanvann.fastimage;

import android.content.Context;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Registry;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.LibraryGlideModule;
import com.facebook.react.modules.network.OkHttpClientProvider;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

public class FastImageOkHttpProgressGlideModule extends LibraryGlideModule {
    private static final DispatchingProgressListener progressListener = new DispatchingProgressListener();

    private interface ResponseProgressListener {
        void update(String str, long j, long j2);
    }

    public void registerComponents(Context context, Glide glide, Registry registry) {
        registry.replace(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory(OkHttpClientProvider.getOkHttpClient().newBuilder().addInterceptor(createInterceptor(progressListener)).build()));
    }

    private static Interceptor createInterceptor(final ResponseProgressListener responseProgressListener) {
        return new Interceptor() {
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request request = chain.request();
                Response proceed = chain.proceed(request);
                return proceed.newBuilder().body(new OkHttpProgressResponseBody(request.url().toString(), proceed.body(), ResponseProgressListener.this)).build();
            }
        };
    }

    static void forget(String str) {
        progressListener.forget(str);
    }

    static void expect(String str, FastImageProgressListener fastImageProgressListener) {
        progressListener.expect(str, fastImageProgressListener);
    }

    private static class DispatchingProgressListener implements ResponseProgressListener {
        private final Map<String, FastImageProgressListener> LISTENERS;
        private final Map<String, Long> PROGRESSES;

        private DispatchingProgressListener() {
            this.LISTENERS = new WeakHashMap();
            this.PROGRESSES = new HashMap();
        }

        /* access modifiers changed from: package-private */
        public void forget(String str) {
            this.LISTENERS.remove(str);
            this.PROGRESSES.remove(str);
        }

        /* access modifiers changed from: package-private */
        public void expect(String str, FastImageProgressListener fastImageProgressListener) {
            this.LISTENERS.put(str, fastImageProgressListener);
        }

        public void update(String str, long j, long j2) {
            FastImageProgressListener fastImageProgressListener = this.LISTENERS.get(str);
            if (fastImageProgressListener != null) {
                if (j2 <= j) {
                    forget(str);
                }
                if (needsDispatch(str, j, j2, fastImageProgressListener.getGranularityPercentage())) {
                    fastImageProgressListener.onProgress(str, j, j2);
                }
            }
        }

        private boolean needsDispatch(String str, long j, long j2, float f) {
            if (!(f == 0.0f || j == 0 || j2 == j)) {
                long j3 = (long) (((((float) j) * 100.0f) / ((float) j2)) / f);
                Long l = this.PROGRESSES.get(str);
                if (l != null && j3 == l.longValue()) {
                    return false;
                }
                this.PROGRESSES.put(str, Long.valueOf(j3));
            }
            return true;
        }
    }

    private static class OkHttpProgressResponseBody extends ResponseBody {
        private BufferedSource bufferedSource;
        /* access modifiers changed from: private */
        public final String key;
        /* access modifiers changed from: private */
        public final ResponseProgressListener progressListener;
        /* access modifiers changed from: private */
        public final ResponseBody responseBody;

        OkHttpProgressResponseBody(String str, ResponseBody responseBody2, ResponseProgressListener responseProgressListener) {
            this.key = str;
            this.responseBody = responseBody2;
            this.progressListener = responseProgressListener;
        }

        public MediaType contentType() {
            return this.responseBody.contentType();
        }

        public long contentLength() {
            return this.responseBody.contentLength();
        }

        public BufferedSource source() {
            if (this.bufferedSource == null) {
                this.bufferedSource = Okio.buffer(source(this.responseBody.source()));
            }
            return this.bufferedSource;
        }

        private Source source(Source source) {
            return new ForwardingSource(source) {
                long totalBytesRead = 0;

                public long read(Buffer buffer, long j) throws IOException {
                    long read = super.read(buffer, j);
                    long contentLength = OkHttpProgressResponseBody.this.responseBody.contentLength();
                    if (read == -1) {
                        this.totalBytesRead = contentLength;
                    } else {
                        this.totalBytesRead += read;
                    }
                    OkHttpProgressResponseBody.this.progressListener.update(OkHttpProgressResponseBody.this.key, this.totalBytesRead, contentLength);
                    return read;
                }
            };
        }
    }
}
