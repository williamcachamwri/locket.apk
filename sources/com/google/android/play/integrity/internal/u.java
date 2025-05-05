package com.google.android.play.integrity.internal;

import android.os.IBinder;

/* compiled from: com.google.android.play:integrity@@1.3.0 */
public final /* synthetic */ class u implements IBinder.DeathRecipient {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ae f85a;

    public /* synthetic */ u(ae aeVar) {
        this.f85a = aeVar;
    }

    public final void binderDied() {
        ae.k(this.f85a);
    }
}
