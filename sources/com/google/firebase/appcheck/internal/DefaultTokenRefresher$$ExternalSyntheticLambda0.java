package com.google.firebase.appcheck.internal;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DefaultTokenRefresher$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ DefaultTokenRefresher f$0;

    public /* synthetic */ DefaultTokenRefresher$$ExternalSyntheticLambda0(DefaultTokenRefresher defaultTokenRefresher) {
        this.f$0 = defaultTokenRefresher;
    }

    public final void run() {
        this.f$0.onRefresh();
    }
}
