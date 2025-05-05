package io.sentry;

import io.sentry.SendCachedEnvelopeFireAndForgetIntegration;
import io.sentry.util.Objects;

public final class SendFireAndForgetOutboxSender implements SendCachedEnvelopeFireAndForgetIntegration.SendFireAndForgetFactory {
    private final SendCachedEnvelopeFireAndForgetIntegration.SendFireAndForgetDirPath sendFireAndForgetDirPath;

    public SendFireAndForgetOutboxSender(SendCachedEnvelopeFireAndForgetIntegration.SendFireAndForgetDirPath sendFireAndForgetDirPath2) {
        this.sendFireAndForgetDirPath = (SendCachedEnvelopeFireAndForgetIntegration.SendFireAndForgetDirPath) Objects.requireNonNull(sendFireAndForgetDirPath2, "SendFireAndForgetDirPath is required");
    }

    public SendCachedEnvelopeFireAndForgetIntegration.SendFireAndForget create(IHub iHub, SentryOptions sentryOptions) {
        Objects.requireNonNull(iHub, "Hub is required");
        Objects.requireNonNull(sentryOptions, "SentryOptions is required");
        String dirPath = this.sendFireAndForgetDirPath.getDirPath();
        if (dirPath == null || !hasValidPath(dirPath, sentryOptions.getLogger())) {
            sentryOptions.getLogger().log(SentryLevel.ERROR, "No outbox dir path is defined in options.", new Object[0]);
            return null;
        }
        return processDir(new OutboxSender(iHub, sentryOptions.getEnvelopeReader(), sentryOptions.getSerializer(), sentryOptions.getLogger(), sentryOptions.getFlushTimeoutMillis()), dirPath, sentryOptions.getLogger());
    }
}
