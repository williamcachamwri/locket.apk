package com.google.firebase.firestore.core;

import android.app.Activity;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ActivityScope$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ Activity f$0;
    public final /* synthetic */ Runnable f$1;

    public /* synthetic */ ActivityScope$$ExternalSyntheticLambda1(Activity activity, Runnable runnable) {
        this.f$0 = activity;
        this.f$1 = runnable;
    }

    public final void run() {
        ActivityScope.lambda$onActivityStopCallOnce$0(this.f$0, this.f$1);
    }
}
