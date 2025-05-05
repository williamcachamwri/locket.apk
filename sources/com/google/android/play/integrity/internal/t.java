package com.google.android.play.integrity.internal;

import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.play:integrity@@1.3.0 */
public abstract class t implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    private final TaskCompletionSource f84a;

    t() {
        this.f84a = null;
    }

    public t(TaskCompletionSource taskCompletionSource) {
        this.f84a = taskCompletionSource;
    }

    public void a(Exception exc) {
        TaskCompletionSource taskCompletionSource = this.f84a;
        if (taskCompletionSource != null) {
            taskCompletionSource.trySetException(exc);
        }
    }

    /* access modifiers changed from: protected */
    public abstract void b();

    /* access modifiers changed from: package-private */
    public final TaskCompletionSource c() {
        return this.f84a;
    }

    public final void run() {
        try {
            b();
        } catch (Exception e) {
            a(e);
        }
    }
}
