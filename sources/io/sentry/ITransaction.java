package io.sentry;

import io.sentry.protocol.Contexts;
import io.sentry.protocol.SentryId;
import io.sentry.protocol.TransactionNameSource;
import java.util.List;

public interface ITransaction extends ISpan {
    void finish(SpanStatus spanStatus, SentryDate sentryDate, boolean z);

    void forceFinish(SpanStatus spanStatus, boolean z);

    Contexts getContexts();

    SentryId getEventId();

    Span getLatestActiveSpan();

    String getName();

    TracesSamplingDecision getSamplingDecision();

    List<Span> getSpans();

    TransactionNameSource getTransactionNameSource();

    Boolean isProfileSampled();

    Boolean isSampled();

    void scheduleFinish();

    void setContext(String str, Object obj);

    void setName(String str);

    void setName(String str, TransactionNameSource transactionNameSource);

    ISpan startChild(String str, String str2, SentryDate sentryDate);
}
