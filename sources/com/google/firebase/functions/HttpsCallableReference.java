package com.google.firebase.functions;

import com.google.android.gms.tasks.Task;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class HttpsCallableReference {
    private final FirebaseFunctions functionsClient;
    private final String name;
    final HttpsCallOptions options;
    private final URL url;

    HttpsCallableReference(FirebaseFunctions firebaseFunctions, String str, HttpsCallOptions httpsCallOptions) {
        this.functionsClient = firebaseFunctions;
        this.name = str;
        this.url = null;
        this.options = httpsCallOptions;
    }

    HttpsCallableReference(FirebaseFunctions firebaseFunctions, URL url2, HttpsCallOptions httpsCallOptions) {
        this.functionsClient = firebaseFunctions;
        this.name = null;
        this.url = url2;
        this.options = httpsCallOptions;
    }

    public Task<HttpsCallableResult> call(Object obj) {
        String str = this.name;
        if (str != null) {
            return this.functionsClient.call(str, obj, this.options);
        }
        return this.functionsClient.call(this.url, obj, this.options);
    }

    public Task<HttpsCallableResult> call() {
        String str = this.name;
        if (str != null) {
            return this.functionsClient.call(str, (Object) null, this.options);
        }
        return this.functionsClient.call(this.url, (Object) null, this.options);
    }

    public void setTimeout(long j, TimeUnit timeUnit) {
        this.options.setTimeout(j, timeUnit);
    }

    public long getTimeout() {
        return this.options.getTimeout();
    }

    public HttpsCallableReference withTimeout(long j, TimeUnit timeUnit) {
        HttpsCallableReference httpsCallableReference = new HttpsCallableReference(this.functionsClient, this.name, this.options);
        httpsCallableReference.setTimeout(j, timeUnit);
        return httpsCallableReference;
    }
}
