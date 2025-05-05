package com.google.android.play.core.integrity;

import android.content.Context;
import com.google.android.play.integrity.internal.aj;
import com.google.android.play.integrity.internal.ak;
import com.google.android.play.integrity.internal.al;
import com.google.android.play.integrity.internal.an;

/* compiled from: com.google.android.play:integrity@@1.3.0 */
final class s {

    /* renamed from: a  reason: collision with root package name */
    private final s f68a = this;
    private final an b;
    private final an c;
    private final an d;
    private final an e;
    private final an f;

    /* synthetic */ s(Context context, r rVar) {
        ak b2 = al.b(context);
        this.b = b2;
        an b3 = aj.b(ac.f23a);
        this.c = b3;
        au auVar = new au(b2, l.f63a);
        this.d = auVar;
        an b4 = aj.b(new al(b2, b3, auVar, l.f63a));
        this.e = b4;
        this.f = aj.b(new ab(b4));
    }

    public final IntegrityManager a() {
        return (IntegrityManager) this.f.a();
    }
}
