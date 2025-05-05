package com.google.android.play.integrity.internal;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

/* compiled from: com.google.android.play:integrity@@1.3.0 */
final class ad implements ServiceConnection {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ ae f76a;

    /* synthetic */ ad(ae aeVar, ac acVar) {
        this.f76a = aeVar;
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        this.f76a.c.d("ServiceConnectionImpl.onServiceConnected(%s)", componentName);
        this.f76a.c().post(new aa(this, iBinder));
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        this.f76a.c.d("ServiceConnectionImpl.onServiceDisconnected(%s)", componentName);
        this.f76a.c().post(new ab(this));
    }
}
