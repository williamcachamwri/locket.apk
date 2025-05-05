package com.google.android.play.core.integrity;

import android.content.Context;
import com.google.android.play.integrity.internal.am;

/* compiled from: com.google.android.play:integrity@@1.3.0 */
final class q implements x {

    /* renamed from: a  reason: collision with root package name */
    private Context f67a;

    private q() {
    }

    /* synthetic */ q(p pVar) {
    }

    public final q a(Context context) {
        context.getClass();
        this.f67a = context;
        return this;
    }

    public final s b() {
        am.a(this.f67a, Context.class);
        return new s(this.f67a, (r) null);
    }
}
