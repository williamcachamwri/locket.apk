package io.sentry;

import io.sentry.protocol.SentryId;
import io.sentry.protocol.SentryTransaction;

final class NoOpSentryClient implements ISentryClient {
    private static final NoOpSentryClient instance = new NoOpSentryClient();

    public void captureSession(Session session, Hint hint) {
    }

    public void captureUserFeedback(UserFeedback userFeedback) {
    }

    public void close() {
    }

    public void flush(long j) {
    }

    public boolean isEnabled() {
        return false;
    }

    private NoOpSentryClient() {
    }

    public static NoOpSentryClient getInstance() {
        return instance;
    }

    public SentryId captureEvent(SentryEvent sentryEvent, Scope scope, Hint hint) {
        return SentryId.EMPTY_ID;
    }

    public SentryId captureEnvelope(SentryEnvelope sentryEnvelope, Hint hint) {
        return SentryId.EMPTY_ID;
    }

    public SentryId captureTransaction(SentryTransaction sentryTransaction, TraceContext traceContext, Scope scope, Hint hint, ProfilingTraceData profilingTraceData) {
        return SentryId.EMPTY_ID;
    }

    public SentryId captureCheckIn(CheckIn checkIn, Scope scope, Hint hint) {
        return SentryId.EMPTY_ID;
    }
}
