package io.sentry;

public final class TransactionOptions extends SpanOptions {
    private boolean bindToScope = false;
    private CustomSamplingContext customSamplingContext = null;
    private Long idleTimeout = null;
    private SentryDate startTimestamp = null;
    private TransactionFinishedCallback transactionFinishedCallback = null;
    private boolean waitForChildren = false;

    public CustomSamplingContext getCustomSamplingContext() {
        return this.customSamplingContext;
    }

    public void setCustomSamplingContext(CustomSamplingContext customSamplingContext2) {
        this.customSamplingContext = customSamplingContext2;
    }

    public boolean isBindToScope() {
        return this.bindToScope;
    }

    public void setBindToScope(boolean z) {
        this.bindToScope = z;
    }

    public SentryDate getStartTimestamp() {
        return this.startTimestamp;
    }

    public void setStartTimestamp(SentryDate sentryDate) {
        this.startTimestamp = sentryDate;
    }

    public boolean isWaitForChildren() {
        return this.waitForChildren;
    }

    public void setWaitForChildren(boolean z) {
        this.waitForChildren = z;
    }

    public Long getIdleTimeout() {
        return this.idleTimeout;
    }

    public void setIdleTimeout(Long l) {
        this.idleTimeout = l;
    }

    public TransactionFinishedCallback getTransactionFinishedCallback() {
        return this.transactionFinishedCallback;
    }

    public void setTransactionFinishedCallback(TransactionFinishedCallback transactionFinishedCallback2) {
        this.transactionFinishedCallback = transactionFinishedCallback2;
    }
}
