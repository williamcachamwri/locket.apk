package com.google.firebase.firestore.core;

import androidx.fragment.app.FragmentActivity;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ActivityScope$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ FragmentActivity f$0;
    public final /* synthetic */ Runnable f$1;

    public /* synthetic */ ActivityScope$$ExternalSyntheticLambda2(FragmentActivity fragmentActivity, Runnable runnable) {
        this.f$0 = fragmentActivity;
        this.f$1 = runnable;
    }

    public final void run() {
        ActivityScope.lambda$onFragmentActivityStopCallOnce$1(this.f$0, this.f$1);
    }
}
