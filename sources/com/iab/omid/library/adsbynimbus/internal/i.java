package com.iab.omid.library.adsbynimbus.internal;

import android.content.Context;
import android.os.Handler;
import com.iab.omid.library.adsbynimbus.adsession.a;
import com.iab.omid.library.adsbynimbus.devicevolume.b;
import com.iab.omid.library.adsbynimbus.devicevolume.c;
import com.iab.omid.library.adsbynimbus.devicevolume.e;
import com.iab.omid.library.adsbynimbus.internal.d;
import com.iab.omid.library.adsbynimbus.walking.TreeWalker;

public class i implements d.a, c {
    private static i f;

    /* renamed from: a  reason: collision with root package name */
    private float f103a = 0.0f;
    private final e b;
    private final b c;
    private com.iab.omid.library.adsbynimbus.devicevolume.d d;
    private c e;

    public i(e eVar, b bVar) {
        this.b = eVar;
        this.c = bVar;
    }

    private c a() {
        if (this.e == null) {
            this.e = c.c();
        }
        return this.e;
    }

    public static i c() {
        if (f == null) {
            f = new i(new e(), new b());
        }
        return f;
    }

    public void a(float f2) {
        this.f103a = f2;
        for (a adSessionStatePublisher : a().a()) {
            adSessionStatePublisher.getAdSessionStatePublisher().a(f2);
        }
    }

    public void a(Context context) {
        this.d = this.b.a(new Handler(), context, this.c.a(), this);
    }

    public void a(boolean z) {
        if (z) {
            TreeWalker.getInstance().h();
        } else {
            TreeWalker.getInstance().g();
        }
    }

    public float b() {
        return this.f103a;
    }

    public void d() {
        b.g().a((d.a) this);
        b.g().e();
        TreeWalker.getInstance().h();
        this.d.c();
    }

    public void e() {
        TreeWalker.getInstance().j();
        b.g().f();
        this.d.d();
    }
}
