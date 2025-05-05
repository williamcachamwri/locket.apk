package com.google.firebase.iid;

import java.util.concurrent.Executor;

/* compiled from: com.google.firebase:firebase-iid@@21.1.0 */
final /* synthetic */ class FirebaseInstanceId$$Lambda$5 implements Executor {
    static final Executor $instance = new FirebaseInstanceId$$Lambda$5();

    private FirebaseInstanceId$$Lambda$5() {
    }

    public void execute(Runnable runnable) {
        runnable.run();
    }
}
