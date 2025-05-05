package io.sentry;

import io.sentry.SendCachedEnvelopeFireAndForgetIntegration;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SendCachedEnvelopeFireAndForgetIntegration$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ SendCachedEnvelopeFireAndForgetIntegration.SendFireAndForget f$0;
    public final /* synthetic */ SentryOptions f$1;

    public /* synthetic */ SendCachedEnvelopeFireAndForgetIntegration$$ExternalSyntheticLambda0(SendCachedEnvelopeFireAndForgetIntegration.SendFireAndForget sendFireAndForget, SentryOptions sentryOptions) {
        this.f$0 = sendFireAndForget;
        this.f$1 = sentryOptions;
    }

    public final void run() {
        SendCachedEnvelopeFireAndForgetIntegration.lambda$register$0(this.f$0, this.f$1);
    }
}
