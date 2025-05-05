package io.sentry;

import io.sentry.util.Objects;
import java.io.File;
import java.util.concurrent.RejectedExecutionException;

public final class SendCachedEnvelopeFireAndForgetIntegration implements Integration {
    private final SendFireAndForgetFactory factory;

    public interface SendFireAndForget {
        void send();
    }

    public interface SendFireAndForgetDirPath {
        String getDirPath();
    }

    public interface SendFireAndForgetFactory {
        SendFireAndForget create(IHub iHub, SentryOptions sentryOptions);

        boolean hasValidPath(String str, ILogger iLogger) {
            if (str != null && !str.isEmpty()) {
                return true;
            }
            iLogger.log(SentryLevel.INFO, "No cached dir path is defined in options.", new Object[0]);
            return false;
        }

        SendFireAndForget processDir(DirectoryProcessor directoryProcessor, String str, ILogger iLogger) {
            return new SendCachedEnvelopeFireAndForgetIntegration$SendFireAndForgetFactory$$ExternalSyntheticLambda0(iLogger, str, directoryProcessor, new File(str));
        }

        static /* synthetic */ void lambda$processDir$0(ILogger iLogger, String str, DirectoryProcessor directoryProcessor, File file) {
            iLogger.log(SentryLevel.DEBUG, "Started processing cached files from %s", str);
            directoryProcessor.processDirectory(file);
            iLogger.log(SentryLevel.DEBUG, "Finished processing cached files from %s", str);
        }
    }

    public SendCachedEnvelopeFireAndForgetIntegration(SendFireAndForgetFactory sendFireAndForgetFactory) {
        this.factory = (SendFireAndForgetFactory) Objects.requireNonNull(sendFireAndForgetFactory, "SendFireAndForgetFactory is required");
    }

    public final void register(IHub iHub, SentryOptions sentryOptions) {
        Objects.requireNonNull(iHub, "Hub is required");
        Objects.requireNonNull(sentryOptions, "SentryOptions is required");
        if (!this.factory.hasValidPath(sentryOptions.getCacheDirPath(), sentryOptions.getLogger())) {
            sentryOptions.getLogger().log(SentryLevel.ERROR, "No cache dir path is defined in options.", new Object[0]);
            return;
        }
        SendFireAndForget create = this.factory.create(iHub, sentryOptions);
        if (create == null) {
            sentryOptions.getLogger().log(SentryLevel.ERROR, "SendFireAndForget factory is null.", new Object[0]);
            return;
        }
        try {
            sentryOptions.getExecutorService().submit((Runnable) new SendCachedEnvelopeFireAndForgetIntegration$$ExternalSyntheticLambda0(create, sentryOptions));
            sentryOptions.getLogger().log(SentryLevel.DEBUG, "SendCachedEventFireAndForgetIntegration installed.", new Object[0]);
            addIntegrationToSdkVersion();
        } catch (RejectedExecutionException e) {
            sentryOptions.getLogger().log(SentryLevel.ERROR, "Failed to call the executor. Cached events will not be sent. Did you call Sentry.close()?", (Throwable) e);
        } catch (Throwable th) {
            sentryOptions.getLogger().log(SentryLevel.ERROR, "Failed to call the executor. Cached events will not be sent", th);
        }
    }

    static /* synthetic */ void lambda$register$0(SendFireAndForget sendFireAndForget, SentryOptions sentryOptions) {
        try {
            sendFireAndForget.send();
        } catch (Throwable th) {
            sentryOptions.getLogger().log(SentryLevel.ERROR, "Failed trying to send cached events.", th);
        }
    }
}
