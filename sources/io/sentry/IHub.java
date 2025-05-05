package io.sentry;

import io.sentry.protocol.SentryId;
import io.sentry.protocol.SentryTransaction;
import io.sentry.protocol.User;
import java.util.List;

public interface IHub {
    void addBreadcrumb(Breadcrumb breadcrumb, Hint hint);

    void bindClient(ISentryClient iSentryClient);

    SentryId captureCheckIn(CheckIn checkIn);

    SentryId captureEnvelope(SentryEnvelope sentryEnvelope, Hint hint);

    SentryId captureEvent(SentryEvent sentryEvent, Hint hint);

    SentryId captureEvent(SentryEvent sentryEvent, Hint hint, ScopeCallback scopeCallback);

    SentryId captureException(Throwable th, Hint hint);

    SentryId captureException(Throwable th, Hint hint, ScopeCallback scopeCallback);

    SentryId captureMessage(String str, SentryLevel sentryLevel);

    SentryId captureMessage(String str, SentryLevel sentryLevel, ScopeCallback scopeCallback);

    SentryId captureTransaction(SentryTransaction sentryTransaction, TraceContext traceContext, Hint hint, ProfilingTraceData profilingTraceData);

    void captureUserFeedback(UserFeedback userFeedback);

    void clearBreadcrumbs();

    IHub clone();

    void close();

    void configureScope(ScopeCallback scopeCallback);

    TransactionContext continueTrace(String str, List<String> list);

    void endSession();

    void flush(long j);

    BaggageHeader getBaggage();

    SentryId getLastEventId();

    SentryOptions getOptions();

    ISpan getSpan();

    SentryTraceHeader getTraceparent();

    Boolean isCrashedLastRun();

    boolean isEnabled();

    void popScope();

    void pushScope();

    void removeExtra(String str);

    void removeTag(String str);

    void reportFullyDisplayed();

    void setExtra(String str, String str2);

    void setFingerprint(List<String> list);

    void setLevel(SentryLevel sentryLevel);

    void setSpanContext(Throwable th, ISpan iSpan, String str);

    void setTag(String str, String str2);

    void setTransaction(String str);

    void setUser(User user);

    void startSession();

    ITransaction startTransaction(TransactionContext transactionContext, CustomSamplingContext customSamplingContext, boolean z);

    ITransaction startTransaction(TransactionContext transactionContext, TransactionOptions transactionOptions);

    @Deprecated
    SentryTraceHeader traceHeaders();

    void withScope(ScopeCallback scopeCallback);

    SentryId captureEvent(SentryEvent sentryEvent) {
        return captureEvent(sentryEvent, new Hint());
    }

    SentryId captureEvent(SentryEvent sentryEvent, ScopeCallback scopeCallback) {
        return captureEvent(sentryEvent, new Hint(), scopeCallback);
    }

    SentryId captureMessage(String str) {
        return captureMessage(str, SentryLevel.INFO);
    }

    SentryId captureMessage(String str, ScopeCallback scopeCallback) {
        return captureMessage(str, SentryLevel.INFO, scopeCallback);
    }

    SentryId captureEnvelope(SentryEnvelope sentryEnvelope) {
        return captureEnvelope(sentryEnvelope, new Hint());
    }

    SentryId captureException(Throwable th) {
        return captureException(th, new Hint());
    }

    SentryId captureException(Throwable th, ScopeCallback scopeCallback) {
        return captureException(th, new Hint(), scopeCallback);
    }

    void addBreadcrumb(Breadcrumb breadcrumb) {
        addBreadcrumb(breadcrumb, new Hint());
    }

    void addBreadcrumb(String str) {
        addBreadcrumb(new Breadcrumb(str));
    }

    void addBreadcrumb(String str, String str2) {
        Breadcrumb breadcrumb = new Breadcrumb(str);
        breadcrumb.setCategory(str2);
        addBreadcrumb(breadcrumb);
    }

    SentryId captureTransaction(SentryTransaction sentryTransaction, TraceContext traceContext, Hint hint) {
        return captureTransaction(sentryTransaction, traceContext, hint, (ProfilingTraceData) null);
    }

    SentryId captureTransaction(SentryTransaction sentryTransaction, Hint hint) {
        return captureTransaction(sentryTransaction, (TraceContext) null, hint);
    }

    SentryId captureTransaction(SentryTransaction sentryTransaction, TraceContext traceContext) {
        return captureTransaction(sentryTransaction, traceContext, (Hint) null);
    }

    ITransaction startTransaction(TransactionContext transactionContext) {
        return startTransaction(transactionContext, false);
    }

    ITransaction startTransaction(TransactionContext transactionContext, boolean z) {
        return startTransaction(transactionContext, (CustomSamplingContext) null, z);
    }

    ITransaction startTransaction(String str, String str2, CustomSamplingContext customSamplingContext) {
        return startTransaction(str, str2, customSamplingContext, false);
    }

    ITransaction startTransaction(String str, String str2, CustomSamplingContext customSamplingContext, boolean z) {
        return startTransaction(new TransactionContext(str, str2), customSamplingContext, z);
    }

    ITransaction startTransaction(TransactionContext transactionContext, CustomSamplingContext customSamplingContext) {
        return startTransaction(transactionContext, customSamplingContext, false);
    }

    ITransaction startTransaction(String str, String str2) {
        return startTransaction(str, str2, (CustomSamplingContext) null);
    }

    ITransaction startTransaction(String str, String str2, boolean z) {
        CustomSamplingContext customSamplingContext = null;
        return startTransaction(str, str2, (CustomSamplingContext) null, z);
    }

    @Deprecated
    void reportFullDisplayed() {
        reportFullyDisplayed();
    }
}
