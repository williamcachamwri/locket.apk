package com.google.android.play.core.integrity;

import android.content.Context;
import com.google.android.play.integrity.internal.ak;
import com.google.android.play.integrity.internal.an;
import com.google.android.play.integrity.internal.s;

/* compiled from: com.google.android.play:integrity@@1.3.0 */
public final class bp implements ak {

    /* renamed from: a  reason: collision with root package name */
    private final an f54a;
    private final an b;
    private final an c;
    private final an d;

    public bp(an anVar, an anVar2, an anVar3, an anVar4) {
        this.f54a = anVar;
        this.b = anVar2;
        this.c = anVar3;
        this.d = anVar4;
    }

    public final /* bridge */ /* synthetic */ Object a() {
        return new bn((Context) this.f54a.a(), (s) this.b.a(), ((au) this.c).a(), new j());
    }
}
