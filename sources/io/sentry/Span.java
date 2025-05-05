package io.sentry;

import io.sentry.protocol.SentryId;
import io.sentry.util.Objects;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public final class Span implements ISpan {
    private final SpanContext context;
    private final Map<String, Object> data;
    private final AtomicBoolean finished;
    private final IHub hub;
    private final SpanOptions options;
    private SpanFinishedCallback spanFinishedCallback;
    private SentryDate startTimestamp;
    private Throwable throwable;
    private SentryDate timestamp;
    private final SentryTracer transaction;

    public boolean isNoOp() {
        return false;
    }

    Span(SentryId sentryId, SpanId spanId, SentryTracer sentryTracer, String str, IHub iHub) {
        this(sentryId, spanId, sentryTracer, str, iHub, (SentryDate) null, new SpanOptions(), (SpanFinishedCallback) null);
    }

    Span(SentryId sentryId, SpanId spanId, SentryTracer sentryTracer, String str, IHub iHub, SentryDate sentryDate, SpanOptions spanOptions, SpanFinishedCallback spanFinishedCallback2) {
        this.finished = new AtomicBoolean(false);
        this.data = new ConcurrentHashMap();
        this.context = new SpanContext(sentryId, new SpanId(), str, spanId, sentryTracer.getSamplingDecision());
        this.transaction = (SentryTracer) Objects.requireNonNull(sentryTracer, "transaction is required");
        this.hub = (IHub) Objects.requireNonNull(iHub, "hub is required");
        this.options = spanOptions;
        this.spanFinishedCallback = spanFinishedCallback2;
        if (sentryDate != null) {
            this.startTimestamp = sentryDate;
        } else {
            this.startTimestamp = iHub.getOptions().getDateProvider().now();
        }
    }

    public Span(TransactionContext transactionContext, SentryTracer sentryTracer, IHub iHub, SentryDate sentryDate, SpanOptions spanOptions) {
        this.finished = new AtomicBoolean(false);
        this.data = new ConcurrentHashMap();
        this.context = (SpanContext) Objects.requireNonNull(transactionContext, "context is required");
        this.transaction = (SentryTracer) Objects.requireNonNull(sentryTracer, "sentryTracer is required");
        this.hub = (IHub) Objects.requireNonNull(iHub, "hub is required");
        this.spanFinishedCallback = null;
        if (sentryDate != null) {
            this.startTimestamp = sentryDate;
        } else {
            this.startTimestamp = iHub.getOptions().getDateProvider().now();
        }
        this.options = spanOptions;
    }

    public SentryDate getStartDate() {
        return this.startTimestamp;
    }

    public SentryDate getFinishDate() {
        return this.timestamp;
    }

    public ISpan startChild(String str) {
        String str2 = null;
        return startChild(str, (String) null);
    }

    public ISpan startChild(String str, String str2, SentryDate sentryDate, Instrumenter instrumenter, SpanOptions spanOptions) {
        if (this.finished.get()) {
            return NoOpSpan.getInstance();
        }
        return this.transaction.startChild(this.context.getSpanId(), str, str2, sentryDate, instrumenter, spanOptions);
    }

    public ISpan startChild(String str, String str2) {
        if (this.finished.get()) {
            return NoOpSpan.getInstance();
        }
        return this.transaction.startChild(this.context.getSpanId(), str, str2);
    }

    public ISpan startChild(String str, String str2, SpanOptions spanOptions) {
        if (this.finished.get()) {
            return NoOpSpan.getInstance();
        }
        return this.transaction.startChild(this.context.getSpanId(), str, str2, spanOptions);
    }

    public ISpan startChild(String str, String str2, SentryDate sentryDate, Instrumenter instrumenter) {
        return startChild(str, str2, sentryDate, instrumenter, new SpanOptions());
    }

    public SentryTraceHeader toSentryTrace() {
        return new SentryTraceHeader(this.context.getTraceId(), this.context.getSpanId(), this.context.getSampled());
    }

    public TraceContext traceContext() {
        return this.transaction.traceContext();
    }

    public BaggageHeader toBaggageHeader(List<String> list) {
        return this.transaction.toBaggageHeader(list);
    }

    public void finish() {
        finish(this.context.getStatus());
    }

    public void finish(SpanStatus spanStatus) {
        finish(spanStatus, this.hub.getOptions().getDateProvider().now());
    }

    public void finish(SpanStatus spanStatus, SentryDate sentryDate) {
        List<Span> list;
        SentryDate sentryDate2;
        if (this.finished.compareAndSet(false, true)) {
            this.context.setStatus(spanStatus);
            if (sentryDate == null) {
                sentryDate = this.hub.getOptions().getDateProvider().now();
            }
            this.timestamp = sentryDate;
            if (this.options.isTrimStart() || this.options.isTrimEnd()) {
                if (this.transaction.getRoot().getSpanId().equals(getSpanId())) {
                    list = this.transaction.getChildren();
                } else {
                    list = getDirectChildren();
                }
                SentryDate sentryDate3 = null;
                SentryDate sentryDate4 = null;
                for (Span next : list) {
                    if (sentryDate3 == null || next.getStartDate().isBefore(sentryDate3)) {
                        sentryDate3 = next.getStartDate();
                    }
                    if (sentryDate4 == null || (next.getFinishDate() != null && next.getFinishDate().isAfter(sentryDate4))) {
                        sentryDate4 = next.getFinishDate();
                    }
                }
                if (this.options.isTrimStart() && sentryDate3 != null && this.startTimestamp.isBefore(sentryDate3)) {
                    updateStartDate(sentryDate3);
                }
                if (this.options.isTrimEnd() && sentryDate4 != null && ((sentryDate2 = this.timestamp) == null || sentryDate2.isAfter(sentryDate4))) {
                    updateEndDate(sentryDate4);
                }
            }
            Throwable th = this.throwable;
            if (th != null) {
                this.hub.setSpanContext(th, this, this.transaction.getName());
            }
            SpanFinishedCallback spanFinishedCallback2 = this.spanFinishedCallback;
            if (spanFinishedCallback2 != null) {
                spanFinishedCallback2.execute(this);
            }
        }
    }

    public void setOperation(String str) {
        if (!this.finished.get()) {
            this.context.setOperation(str);
        }
    }

    public String getOperation() {
        return this.context.getOperation();
    }

    public void setDescription(String str) {
        if (!this.finished.get()) {
            this.context.setDescription(str);
        }
    }

    public String getDescription() {
        return this.context.getDescription();
    }

    public void setStatus(SpanStatus spanStatus) {
        if (!this.finished.get()) {
            this.context.setStatus(spanStatus);
        }
    }

    public SpanStatus getStatus() {
        return this.context.getStatus();
    }

    public SpanContext getSpanContext() {
        return this.context;
    }

    public void setTag(String str, String str2) {
        if (!this.finished.get()) {
            this.context.setTag(str, str2);
        }
    }

    public String getTag(String str) {
        return this.context.getTags().get(str);
    }

    public boolean isFinished() {
        return this.finished.get();
    }

    public Map<String, Object> getData() {
        return this.data;
    }

    public Boolean isSampled() {
        return this.context.getSampled();
    }

    public Boolean isProfileSampled() {
        return this.context.getProfileSampled();
    }

    public TracesSamplingDecision getSamplingDecision() {
        return this.context.getSamplingDecision();
    }

    public void setThrowable(Throwable th) {
        if (!this.finished.get()) {
            this.throwable = th;
        }
    }

    public Throwable getThrowable() {
        return this.throwable;
    }

    public SentryId getTraceId() {
        return this.context.getTraceId();
    }

    public SpanId getSpanId() {
        return this.context.getSpanId();
    }

    public SpanId getParentSpanId() {
        return this.context.getParentSpanId();
    }

    public Map<String, String> getTags() {
        return this.context.getTags();
    }

    public void setData(String str, Object obj) {
        if (!this.finished.get()) {
            this.data.put(str, obj);
        }
    }

    public Object getData(String str) {
        return this.data.get(str);
    }

    public void setMeasurement(String str, Number number) {
        this.transaction.setMeasurement(str, number);
    }

    public void setMeasurement(String str, Number number, MeasurementUnit measurementUnit) {
        this.transaction.setMeasurement(str, number, measurementUnit);
    }

    public boolean updateEndDate(SentryDate sentryDate) {
        if (this.timestamp == null) {
            return false;
        }
        this.timestamp = sentryDate;
        return true;
    }

    /* access modifiers changed from: package-private */
    public void setSpanFinishedCallback(SpanFinishedCallback spanFinishedCallback2) {
        this.spanFinishedCallback = spanFinishedCallback2;
    }

    private void updateStartDate(SentryDate sentryDate) {
        this.startTimestamp = sentryDate;
    }

    /* access modifiers changed from: package-private */
    public SpanOptions getOptions() {
        return this.options;
    }

    private List<Span> getDirectChildren() {
        ArrayList arrayList = new ArrayList();
        for (Span next : this.transaction.getSpans()) {
            if (next.getParentSpanId() != null && next.getParentSpanId().equals(getSpanId())) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }
}
