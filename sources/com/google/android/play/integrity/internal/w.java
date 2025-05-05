package com.google.android.play.integrity.internal;

import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.play:integrity@@1.3.0 */
final class w extends t {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ TaskCompletionSource f87a;
    final /* synthetic */ t b;
    final /* synthetic */ ae c;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    w(ae aeVar, TaskCompletionSource taskCompletionSource, TaskCompletionSource taskCompletionSource2, t tVar) {
        super(taskCompletionSource);
        this.c = aeVar;
        this.f87a = taskCompletionSource2;
        this.b = tVar;
    }

    public final void b() {
        synchronized (this.c.g) {
            ae.o(this.c, this.f87a);
            if (this.c.m.getAndIncrement() > 0) {
                this.c.c.d("Already connected to the service.", new Object[0]);
            }
            ae.q(this.c, this.b);
        }
    }
}
