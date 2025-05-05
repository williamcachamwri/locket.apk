package com.google.android.play.integrity.internal;

import android.os.IBinder;
import android.os.IInterface;

/* compiled from: com.google.android.play:integrity@@1.3.0 */
final class aa extends t {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ IBinder f74a;
    final /* synthetic */ ad b;

    aa(ad adVar, IBinder iBinder) {
        this.b = adVar;
        this.f74a = iBinder;
    }

    public final void b() {
        this.b.f76a.o = (IInterface) this.b.f76a.j.a(this.f74a);
        ae.r(this.b.f76a);
        this.b.f76a.h = false;
        for (Runnable run : this.b.f76a.e) {
            run.run();
        }
        this.b.f76a.e.clear();
    }
}
