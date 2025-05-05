package io.sentry.exception;

public final class SentryHttpClientException extends Exception {
    private static final long serialVersionUID = 1;

    public SentryHttpClientException(String str) {
        super(str);
    }
}
