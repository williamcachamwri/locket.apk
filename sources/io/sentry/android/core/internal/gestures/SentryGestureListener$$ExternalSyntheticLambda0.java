package io.sentry.android.core.internal.gestures;

import io.sentry.ITransaction;
import io.sentry.Scope;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SentryGestureListener$$ExternalSyntheticLambda0 implements Scope.IWithTransaction {
    public final /* synthetic */ SentryGestureListener f$0;
    public final /* synthetic */ Scope f$1;

    public /* synthetic */ SentryGestureListener$$ExternalSyntheticLambda0(SentryGestureListener sentryGestureListener, Scope scope) {
        this.f$0 = sentryGestureListener;
        this.f$1 = scope;
    }

    public final void accept(ITransaction iTransaction) {
        this.f$0.m2403lambda$clearScope$2$iosentryandroidcoreinternalgesturesSentryGestureListener(this.f$1, iTransaction);
    }
}
