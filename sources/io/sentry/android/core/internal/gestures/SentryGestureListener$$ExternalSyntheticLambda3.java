package io.sentry.android.core.internal.gestures;

import io.sentry.ITransaction;
import io.sentry.Scope;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SentryGestureListener$$ExternalSyntheticLambda3 implements Scope.IWithTransaction {
    public final /* synthetic */ SentryGestureListener f$0;
    public final /* synthetic */ Scope f$1;
    public final /* synthetic */ ITransaction f$2;

    public /* synthetic */ SentryGestureListener$$ExternalSyntheticLambda3(SentryGestureListener sentryGestureListener, Scope scope, ITransaction iTransaction) {
        this.f$0 = sentryGestureListener;
        this.f$1 = scope;
        this.f$2 = iTransaction;
    }

    public final void accept(ITransaction iTransaction) {
        this.f$0.m2402lambda$applyScope$3$iosentryandroidcoreinternalgesturesSentryGestureListener(this.f$1, this.f$2, iTransaction);
    }
}
