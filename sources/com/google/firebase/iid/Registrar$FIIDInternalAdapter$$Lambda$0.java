package com.google.firebase.iid;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

/* compiled from: com.google.firebase:firebase-iid@@21.1.0 */
final /* synthetic */ class Registrar$FIIDInternalAdapter$$Lambda$0 implements Continuation {
    static final Continuation $instance = new Registrar$FIIDInternalAdapter$$Lambda$0();

    private Registrar$FIIDInternalAdapter$$Lambda$0() {
    }

    public Object then(Task task) {
        return ((InstanceIdResult) task.getResult()).getToken();
    }
}
