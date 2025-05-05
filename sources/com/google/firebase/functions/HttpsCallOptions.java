package com.google.firebase.functions;

import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;

class HttpsCallOptions {
    private static final long DEFAULT_TIMEOUT = 70;
    private static final TimeUnit DEFAULT_TIMEOUT_UNITS = TimeUnit.SECONDS;
    private final boolean limitedUseAppCheckTokens;
    private long timeout;
    private TimeUnit timeoutUnits;

    HttpsCallOptions(HttpsCallableOptions httpsCallableOptions) {
        this.timeout = DEFAULT_TIMEOUT;
        this.timeoutUnits = DEFAULT_TIMEOUT_UNITS;
        this.limitedUseAppCheckTokens = httpsCallableOptions.getLimitedUseAppCheckTokens();
    }

    HttpsCallOptions() {
        this.timeout = DEFAULT_TIMEOUT;
        this.timeoutUnits = DEFAULT_TIMEOUT_UNITS;
        this.limitedUseAppCheckTokens = false;
    }

    /* access modifiers changed from: package-private */
    public void setTimeout(long j, TimeUnit timeUnit) {
        this.timeout = j;
        this.timeoutUnits = timeUnit;
    }

    /* access modifiers changed from: package-private */
    public long getTimeout() {
        return this.timeoutUnits.toMillis(this.timeout);
    }

    /* access modifiers changed from: package-private */
    public boolean getLimitedUseAppCheckTokens() {
        return this.limitedUseAppCheckTokens;
    }

    /* access modifiers changed from: package-private */
    public OkHttpClient apply(OkHttpClient okHttpClient) {
        return okHttpClient.newBuilder().callTimeout(this.timeout, this.timeoutUnits).readTimeout(this.timeout, this.timeoutUnits).build();
    }
}
