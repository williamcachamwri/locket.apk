package androidx.camera.core.impl;

import androidx.concurrent.futures.CallbackToFutureAdapter;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class LiveDataObservable$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ LiveDataObservable f$0;
    public final /* synthetic */ CallbackToFutureAdapter.Completer f$1;

    public /* synthetic */ LiveDataObservable$$ExternalSyntheticLambda1(LiveDataObservable liveDataObservable, CallbackToFutureAdapter.Completer completer) {
        this.f$0 = liveDataObservable;
        this.f$1 = completer;
    }

    public final void run() {
        this.f$0.m182lambda$fetchData$0$androidxcameracoreimplLiveDataObservable(this.f$1);
    }
}
