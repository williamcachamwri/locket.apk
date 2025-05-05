package com.google.android.play.core.integrity;

import android.content.Context;
import com.google.android.play.integrity.internal.aj;
import com.google.android.play.integrity.internal.ak;
import com.google.android.play.integrity.internal.al;
import com.google.android.play.integrity.internal.an;

/* compiled from: com.google.android.play:integrity@@1.3.0 */
final class w implements aw {

    /* renamed from: a  reason: collision with root package name */
    private final w f70a = this;
    private final an b;
    private final an c;
    private final an d;
    private final an e;
    private final an f;
    private final an g;

    /* synthetic */ w(Context context, v vVar) {
        ak b2 = al.b(context);
        this.b = b2;
        an b3 = aj.b(bb.f44a);
        this.c = b3;
        au auVar = new au(b2, n.f66a);
        this.d = auVar;
        an b4 = aj.b(new bp(b2, b3, auVar, n.f66a));
        this.e = b4;
        an b5 = aj.b(new bu(b4));
        this.f = b5;
        this.g = aj.b(new ba(b4, b5));
    }

    public final StandardIntegrityManager a() {
        return (StandardIntegrityManager) this.g.a();
    }
}
