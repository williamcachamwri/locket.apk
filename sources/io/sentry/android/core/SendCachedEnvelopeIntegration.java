package io.sentry.android.core;

import io.sentry.Integration;
import io.sentry.SendCachedEnvelopeFireAndForgetIntegration;
import io.sentry.SentryLevel;
import io.sentry.util.LazyEvaluator;
import io.sentry.util.Objects;

final class SendCachedEnvelopeIntegration implements Integration {
    private final SendCachedEnvelopeFireAndForgetIntegration.SendFireAndForgetFactory factory;
    private final LazyEvaluator<Boolean> startupCrashMarkerEvaluator;

    public SendCachedEnvelopeIntegration(SendCachedEnvelopeFireAndForgetIntegration.SendFireAndForgetFactory sendFireAndForgetFactory, LazyEvaluator<Boolean> lazyEvaluator) {
        this.factory = (SendCachedEnvelopeFireAndForgetIntegration.SendFireAndForgetFactory) Objects.requireNonNull(sendFireAndForgetFactory, "SendFireAndForgetFactory is required");
        this.startupCrashMarkerEvaluator = lazyEvaluator;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:15|16|17|18|19) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:18:0x007d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void register(io.sentry.IHub r6, io.sentry.SentryOptions r7) {
        /*
            r5 = this;
            java.lang.String r0 = "Hub is required"
            io.sentry.util.Objects.requireNonNull(r6, r0)
            boolean r0 = r7 instanceof io.sentry.android.core.SentryAndroidOptions
            if (r0 == 0) goto L_0x000d
            r0 = r7
            io.sentry.android.core.SentryAndroidOptions r0 = (io.sentry.android.core.SentryAndroidOptions) r0
            goto L_0x000e
        L_0x000d:
            r0 = 0
        L_0x000e:
            java.lang.String r1 = "SentryAndroidOptions is required"
            java.lang.Object r0 = io.sentry.util.Objects.requireNonNull(r0, r1)
            io.sentry.android.core.SentryAndroidOptions r0 = (io.sentry.android.core.SentryAndroidOptions) r0
            java.lang.String r1 = r7.getCacheDirPath()
            io.sentry.SendCachedEnvelopeFireAndForgetIntegration$SendFireAndForgetFactory r2 = r5.factory
            io.sentry.ILogger r3 = r7.getLogger()
            boolean r1 = r2.hasValidPath(r1, r3)
            r2 = 0
            if (r1 != 0) goto L_0x0035
            io.sentry.ILogger r6 = r7.getLogger()
            io.sentry.SentryLevel r7 = io.sentry.SentryLevel.ERROR
            java.lang.String r0 = "No cache dir path is defined in options."
            java.lang.Object[] r1 = new java.lang.Object[r2]
            r6.log((io.sentry.SentryLevel) r7, (java.lang.String) r0, (java.lang.Object[]) r1)
            return
        L_0x0035:
            io.sentry.SendCachedEnvelopeFireAndForgetIntegration$SendFireAndForgetFactory r7 = r5.factory
            io.sentry.SendCachedEnvelopeFireAndForgetIntegration$SendFireAndForget r6 = r7.create(r6, r0)
            if (r6 != 0) goto L_0x004b
            io.sentry.ILogger r6 = r0.getLogger()
            io.sentry.SentryLevel r7 = io.sentry.SentryLevel.ERROR
            java.lang.String r0 = "SendFireAndForget factory is null."
            java.lang.Object[] r1 = new java.lang.Object[r2]
            r6.log((io.sentry.SentryLevel) r7, (java.lang.String) r0, (java.lang.Object[]) r1)
            return
        L_0x004b:
            io.sentry.ISentryExecutorService r7 = r0.getExecutorService()     // Catch:{ RejectedExecutionException -> 0x00a5, all -> 0x0098 }
            io.sentry.android.core.SendCachedEnvelopeIntegration$$ExternalSyntheticLambda0 r1 = new io.sentry.android.core.SendCachedEnvelopeIntegration$$ExternalSyntheticLambda0     // Catch:{ RejectedExecutionException -> 0x00a5, all -> 0x0098 }
            r1.<init>(r6, r0)     // Catch:{ RejectedExecutionException -> 0x00a5, all -> 0x0098 }
            java.util.concurrent.Future r6 = r7.submit((java.lang.Runnable) r1)     // Catch:{ RejectedExecutionException -> 0x00a5, all -> 0x0098 }
            io.sentry.util.LazyEvaluator<java.lang.Boolean> r7 = r5.startupCrashMarkerEvaluator     // Catch:{ RejectedExecutionException -> 0x00a5, all -> 0x0098 }
            java.lang.Object r7 = r7.getValue()     // Catch:{ RejectedExecutionException -> 0x00a5, all -> 0x0098 }
            java.lang.Boolean r7 = (java.lang.Boolean) r7     // Catch:{ RejectedExecutionException -> 0x00a5, all -> 0x0098 }
            boolean r7 = r7.booleanValue()     // Catch:{ RejectedExecutionException -> 0x00a5, all -> 0x0098 }
            if (r7 == 0) goto L_0x008a
            io.sentry.ILogger r7 = r0.getLogger()     // Catch:{ RejectedExecutionException -> 0x00a5, all -> 0x0098 }
            io.sentry.SentryLevel r1 = io.sentry.SentryLevel.DEBUG     // Catch:{ RejectedExecutionException -> 0x00a5, all -> 0x0098 }
            java.lang.String r3 = "Startup Crash marker exists, blocking flush."
            java.lang.Object[] r4 = new java.lang.Object[r2]     // Catch:{ RejectedExecutionException -> 0x00a5, all -> 0x0098 }
            r7.log((io.sentry.SentryLevel) r1, (java.lang.String) r3, (java.lang.Object[]) r4)     // Catch:{ RejectedExecutionException -> 0x00a5, all -> 0x0098 }
            long r3 = r0.getStartupCrashFlushTimeoutMillis()     // Catch:{ TimeoutException -> 0x007d }
            java.util.concurrent.TimeUnit r7 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ TimeoutException -> 0x007d }
            r6.get(r3, r7)     // Catch:{ TimeoutException -> 0x007d }
            goto L_0x008a
        L_0x007d:
            io.sentry.ILogger r6 = r0.getLogger()     // Catch:{ RejectedExecutionException -> 0x00a5, all -> 0x0098 }
            io.sentry.SentryLevel r7 = io.sentry.SentryLevel.DEBUG     // Catch:{ RejectedExecutionException -> 0x00a5, all -> 0x0098 }
            java.lang.String r1 = "Synchronous send timed out, continuing in the background."
            java.lang.Object[] r3 = new java.lang.Object[r2]     // Catch:{ RejectedExecutionException -> 0x00a5, all -> 0x0098 }
            r6.log((io.sentry.SentryLevel) r7, (java.lang.String) r1, (java.lang.Object[]) r3)     // Catch:{ RejectedExecutionException -> 0x00a5, all -> 0x0098 }
        L_0x008a:
            io.sentry.ILogger r6 = r0.getLogger()     // Catch:{ RejectedExecutionException -> 0x00a5, all -> 0x0098 }
            io.sentry.SentryLevel r7 = io.sentry.SentryLevel.DEBUG     // Catch:{ RejectedExecutionException -> 0x00a5, all -> 0x0098 }
            java.lang.String r1 = "SendCachedEnvelopeIntegration installed."
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ RejectedExecutionException -> 0x00a5, all -> 0x0098 }
            r6.log((io.sentry.SentryLevel) r7, (java.lang.String) r1, (java.lang.Object[]) r2)     // Catch:{ RejectedExecutionException -> 0x00a5, all -> 0x0098 }
            goto L_0x00b1
        L_0x0098:
            r6 = move-exception
            io.sentry.ILogger r7 = r0.getLogger()
            io.sentry.SentryLevel r0 = io.sentry.SentryLevel.ERROR
            java.lang.String r1 = "Failed to call the executor. Cached events will not be sent"
            r7.log((io.sentry.SentryLevel) r0, (java.lang.String) r1, (java.lang.Throwable) r6)
            goto L_0x00b1
        L_0x00a5:
            r6 = move-exception
            io.sentry.ILogger r7 = r0.getLogger()
            io.sentry.SentryLevel r0 = io.sentry.SentryLevel.ERROR
            java.lang.String r1 = "Failed to call the executor. Cached events will not be sent. Did you call Sentry.close()?"
            r7.log((io.sentry.SentryLevel) r0, (java.lang.String) r1, (java.lang.Throwable) r6)
        L_0x00b1:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.android.core.SendCachedEnvelopeIntegration.register(io.sentry.IHub, io.sentry.SentryOptions):void");
    }

    static /* synthetic */ void lambda$register$0(SendCachedEnvelopeFireAndForgetIntegration.SendFireAndForget sendFireAndForget, SentryAndroidOptions sentryAndroidOptions) {
        try {
            sendFireAndForget.send();
        } catch (Throwable th) {
            sentryAndroidOptions.getLogger().log(SentryLevel.ERROR, "Failed trying to send cached events.", th);
        }
    }
}
