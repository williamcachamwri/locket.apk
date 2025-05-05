package io.sentry;

import io.sentry.protocol.Message;
import io.sentry.protocol.SentryId;
import io.sentry.protocol.SentryTransaction;

public interface ISentryClient {
    SentryId captureCheckIn(CheckIn checkIn, Scope scope, Hint hint);

    SentryId captureEnvelope(SentryEnvelope sentryEnvelope, Hint hint);

    SentryId captureEvent(SentryEvent sentryEvent, Scope scope, Hint hint);

    void captureSession(Session session, Hint hint);

    SentryId captureTransaction(SentryTransaction sentryTransaction, TraceContext traceContext, Scope scope, Hint hint, ProfilingTraceData profilingTraceData);

    void captureUserFeedback(UserFeedback userFeedback);

    void close();

    void flush(long j);

    boolean isEnabled();

    SentryId captureEvent(SentryEvent sentryEvent) {
        return captureEvent(sentryEvent, (Scope) null, (Hint) null);
    }

    SentryId captureEvent(SentryEvent sentryEvent, Scope scope) {
        return captureEvent(sentryEvent, scope, (Hint) null);
    }

    SentryId captureEvent(SentryEvent sentryEvent, Hint hint) {
        return captureEvent(sentryEvent, (Scope) null, hint);
    }

    SentryId captureMessage(String str, SentryLevel sentryLevel, Scope scope) {
        SentryEvent sentryEvent = new SentryEvent();
        Message message = new Message();
        message.setFormatted(str);
        sentryEvent.setMessage(message);
        sentryEvent.setLevel(sentryLevel);
        return captureEvent(sentryEvent, scope);
    }

    SentryId captureMessage(String str, SentryLevel sentryLevel) {
        return captureMessage(str, sentryLevel, (Scope) null);
    }

    SentryId captureException(Throwable th) {
        return captureException(th, (Scope) null, (Hint) null);
    }

    SentryId captureException(Throwable th, Scope scope, Hint hint) {
        return captureEvent(new SentryEvent(th), scope, hint);
    }

    SentryId captureException(Throwable th, Hint hint) {
        return captureException(th, (Scope) null, hint);
    }

    SentryId captureException(Throwable th, Scope scope) {
        return captureException(th, scope, (Hint) null);
    }

    void captureSession(Session session) {
        captureSession(session, (Hint) null);
    }

    SentryId captureEnvelope(SentryEnvelope sentryEnvelope) {
        return captureEnvelope(sentryEnvelope, (Hint) null);
    }

    SentryId captureTransaction(SentryTransaction sentryTransaction, Scope scope, Hint hint) {
        return captureTransaction(sentryTransaction, (TraceContext) null, scope, hint);
    }

    SentryId captureTransaction(SentryTransaction sentryTransaction, TraceContext traceContext, Scope scope, Hint hint) {
        return captureTransaction(sentryTransaction, traceContext, scope, hint, (ProfilingTraceData) null);
    }

    SentryId captureTransaction(SentryTransaction sentryTransaction, TraceContext traceContext) {
        return captureTransaction(sentryTransaction, traceContext, (Scope) null, (Hint) null);
    }

    SentryId captureTransaction(SentryTransaction sentryTransaction) {
        return captureTransaction(sentryTransaction, (TraceContext) null, (Scope) null, (Hint) null);
    }
}
