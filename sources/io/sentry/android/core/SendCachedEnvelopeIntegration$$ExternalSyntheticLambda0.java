package io.sentry.android.core;

import io.sentry.SendCachedEnvelopeFireAndForgetIntegration;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SendCachedEnvelopeIntegration$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ SendCachedEnvelopeFireAndForgetIntegration.SendFireAndForget f$0;
    public final /* synthetic */ SentryAndroidOptions f$1;

    public /* synthetic */ SendCachedEnvelopeIntegration$$ExternalSyntheticLambda0(SendCachedEnvelopeFireAndForgetIntegration.SendFireAndForget sendFireAndForget, SentryAndroidOptions sentryAndroidOptions) {
        this.f$0 = sendFireAndForget;
        this.f$1 = sentryAndroidOptions;
    }

    public final void run() {
        SendCachedEnvelopeIntegration.lambda$register$0(this.f$0, this.f$1);
    }
}
