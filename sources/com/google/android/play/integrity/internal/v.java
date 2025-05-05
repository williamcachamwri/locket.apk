package com.google.android.play.integrity.internal;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.play:integrity@@1.3.0 */
public final /* synthetic */ class v implements OnCompleteListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ae f86a;
    public final /* synthetic */ TaskCompletionSource b;

    public /* synthetic */ v(ae aeVar, TaskCompletionSource taskCompletionSource) {
        this.f86a = aeVar;
        this.b = taskCompletionSource;
    }

    public final void onComplete(Task task) {
        this.f86a.u(this.b, task);
    }
}
