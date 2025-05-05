package com.iab.omid.library.adsbynimbus;

import android.content.Context;
import com.iab.omid.library.adsbynimbus.internal.i;
import com.iab.omid.library.adsbynimbus.internal.j;
import com.iab.omid.library.adsbynimbus.utils.a;
import com.iab.omid.library.adsbynimbus.utils.c;
import com.iab.omid.library.adsbynimbus.utils.e;
import com.iab.omid.library.adsbynimbus.utils.g;

public class b {

    /* renamed from: a  reason: collision with root package name */
    private boolean f92a;

    private void b(Context context) {
        g.a((Object) context, "Application Context cannot be null");
    }

    /* access modifiers changed from: package-private */
    public String a() {
        return "1.5.2-Adsbynimbus";
    }

    /* access modifiers changed from: package-private */
    public void a(Context context) {
        b(context);
        if (!b()) {
            a(true);
            i.c().a(context);
            com.iab.omid.library.adsbynimbus.internal.b.g().a(context);
            a.a(context);
            c.a(context);
            e.a(context);
            com.iab.omid.library.adsbynimbus.internal.g.b().a(context);
            com.iab.omid.library.adsbynimbus.internal.a.a().a(context);
            j.b().a(context);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(boolean z) {
        this.f92a = z;
    }

    /* access modifiers changed from: package-private */
    public boolean b() {
        return this.f92a;
    }

    /* access modifiers changed from: package-private */
    public void c() {
        g.a();
        com.iab.omid.library.adsbynimbus.internal.a.a().d();
    }
}
