package com.google.android.play.integrity.internal;

/* compiled from: com.google.android.play:integrity@@1.3.0 */
final class x extends t {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ ae f88a;

    x(ae aeVar) {
        this.f88a = aeVar;
    }

    public final void b() {
        synchronized (this.f88a.g) {
            if (this.f88a.m.get() <= 0 || this.f88a.m.decrementAndGet() <= 0) {
                ae aeVar = this.f88a;
                if (aeVar.o != null) {
                    aeVar.c.d("Unbind from service.", new Object[0]);
                    ae aeVar2 = this.f88a;
                    aeVar2.b.unbindService(aeVar2.n);
                    this.f88a.h = false;
                    this.f88a.o = null;
                    this.f88a.n = null;
                }
                this.f88a.x();
                return;
            }
            this.f88a.c.d("Leaving the connection open for other ongoing calls.", new Object[0]);
        }
    }
}
