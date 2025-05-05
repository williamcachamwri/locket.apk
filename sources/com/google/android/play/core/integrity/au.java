package com.google.android.play.core.integrity;

import com.google.android.play.integrity.internal.ak;
import com.google.android.play.integrity.internal.an;

/* compiled from: com.google.android.play:integrity@@1.3.0 */
public final class au implements ak {

    /* renamed from: a  reason: collision with root package name */
    private final an f38a;
    private final an b;

    public au(an anVar, an anVar2) {
        this.f38a = anVar;
        this.b = anVar2;
    }

    /* renamed from: b */
    public final at a() {
        return new at(this.f38a, this.b);
    }
}
