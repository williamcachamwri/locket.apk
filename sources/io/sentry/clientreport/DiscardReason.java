package io.sentry.clientreport;

import io.sentry.TraceContext;

public enum DiscardReason {
    QUEUE_OVERFLOW("queue_overflow"),
    CACHE_OVERFLOW("cache_overflow"),
    RATELIMIT_BACKOFF("ratelimit_backoff"),
    NETWORK_ERROR("network_error"),
    SAMPLE_RATE(TraceContext.JsonKeys.SAMPLE_RATE),
    BEFORE_SEND("before_send"),
    EVENT_PROCESSOR("event_processor");
    
    private final String reason;

    private DiscardReason(String str) {
        this.reason = str;
    }

    public String getReason() {
        return this.reason;
    }
}
