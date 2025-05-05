package com.iab.omid.library.adsbynimbus.internal;

import android.view.View;
import com.iab.omid.library.adsbynimbus.adsession.FriendlyObstructionPurpose;
import com.iab.omid.library.adsbynimbus.weakreference.a;

public class e {

    /* renamed from: a  reason: collision with root package name */
    private final a f98a;
    private final String b;
    private final FriendlyObstructionPurpose c;
    private final String d;

    public e(View view, FriendlyObstructionPurpose friendlyObstructionPurpose, String str) {
        this.f98a = new a(view);
        this.b = view.getClass().getCanonicalName();
        this.c = friendlyObstructionPurpose;
        this.d = str;
    }

    public String a() {
        return this.d;
    }

    public FriendlyObstructionPurpose b() {
        return this.c;
    }

    public a c() {
        return this.f98a;
    }

    public String d() {
        return this.b;
    }
}
