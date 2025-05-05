package io.sentry;

import io.sentry.protocol.SentryId;
import io.sentry.protocol.SentryTransaction;
import io.sentry.protocol.User;
import java.util.List;

public final class NoOpHub implements IHub {
    private static final NoOpHub instance = new NoOpHub();
    private final SentryOptions emptyOptions = SentryOptions.empty();

    public void addBreadcrumb(Breadcrumb breadcrumb, Hint hint) {
    }

    public void bindClient(ISentryClient iSentryClient) {
    }

    public void captureUserFeedback(UserFeedback userFeedback) {
    }

    public void clearBreadcrumbs() {
    }

    public void close() {
    }

    public void configureScope(ScopeCallback scopeCallback) {
    }

    public TransactionContext continueTrace(String str, List<String> list) {
        return null;
    }

    public void endSession() {
    }

    public void flush(long j) {
    }

    public BaggageHeader getBaggage() {
        return null;
    }

    public ISpan getSpan() {
        return null;
    }

    public SentryTraceHeader getTraceparent() {
        return null;
    }

    public Boolean isCrashedLastRun() {
        return null;
    }

    public boolean isEnabled() {
        return false;
    }

    public void popScope() {
    }

    public void pushScope() {
    }

    public void removeExtra(String str) {
    }

    public void removeTag(String str) {
    }

    public void reportFullyDisplayed() {
    }

    public void setExtra(String str, String str2) {
    }

    public void setFingerprint(List<String> list) {
    }

    public void setLevel(SentryLevel sentryLevel) {
    }

    public void setSpanContext(Throwable th, ISpan iSpan, String str) {
    }

    public void setTag(String str, String str2) {
    }

    public void setTransaction(String str) {
    }

    public void setUser(User user) {
    }

    public void startSession() {
    }

    public void withScope(ScopeCallback scopeCallback) {
    }

    private NoOpHub() {
    }

    public static NoOpHub getInstance() {
        return instance;
    }

    public SentryId captureEvent(SentryEvent sentryEvent, Hint hint) {
        return SentryId.EMPTY_ID;
    }

    public SentryId captureEvent(SentryEvent sentryEvent, Hint hint, ScopeCallback scopeCallback) {
        return SentryId.EMPTY_ID;
    }

    public SentryId captureMessage(String str, SentryLevel sentryLevel) {
        return SentryId.EMPTY_ID;
    }

    public SentryId captureMessage(String str, SentryLevel sentryLevel, ScopeCallback scopeCallback) {
        return SentryId.EMPTY_ID;
    }

    public SentryId captureEnvelope(SentryEnvelope sentryEnvelope, Hint hint) {
        return SentryId.EMPTY_ID;
    }

    public SentryId captureException(Throwable th, Hint hint) {
        return SentryId.EMPTY_ID;
    }

    public SentryId captureException(Throwable th, Hint hint, ScopeCallback scopeCallback) {
        return SentryId.EMPTY_ID;
    }

    public SentryId getLastEventId() {
        return SentryId.EMPTY_ID;
    }

    public IHub clone() {
        return instance;
    }

    public SentryId captureTransaction(SentryTransaction sentryTransaction, TraceContext traceContext, Hint hint, ProfilingTraceData profilingTraceData) {
        return SentryId.EMPTY_ID;
    }

    public ITransaction startTransaction(TransactionContext transactionContext) {
        return NoOpTransaction.getInstance();
    }

    public ITransaction startTransaction(TransactionContext transactionContext, CustomSamplingContext customSamplingContext, boolean z) {
        return NoOpTransaction.getInstance();
    }

    public ITransaction startTransaction(TransactionContext transactionContext, TransactionOptions transactionOptions) {
        return NoOpTransaction.getInstance();
    }

    @Deprecated
    public SentryTraceHeader traceHeaders() {
        return new SentryTraceHeader(SentryId.EMPTY_ID, SpanId.EMPTY_ID, true);
    }

    public SentryOptions getOptions() {
        return this.emptyOptions;
    }

    public SentryId captureCheckIn(CheckIn checkIn) {
        return SentryId.EMPTY_ID;
    }
}
