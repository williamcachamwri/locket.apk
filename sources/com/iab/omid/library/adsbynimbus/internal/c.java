package com.iab.omid.library.adsbynimbus.internal;

import com.iab.omid.library.adsbynimbus.adsession.a;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class c {
    private static c c = new c();

    /* renamed from: a  reason: collision with root package name */
    private final ArrayList<a> f96a = new ArrayList<>();
    private final ArrayList<a> b = new ArrayList<>();

    private c() {
    }

    public static c c() {
        return c;
    }

    public Collection<a> a() {
        return Collections.unmodifiableCollection(this.b);
    }

    public void a(a aVar) {
        this.f96a.add(aVar);
    }

    public Collection<a> b() {
        return Collections.unmodifiableCollection(this.f96a);
    }

    public void b(a aVar) {
        boolean d = d();
        this.f96a.remove(aVar);
        this.b.remove(aVar);
        if (d && !d()) {
            i.c().e();
        }
    }

    public void c(a aVar) {
        boolean d = d();
        this.b.add(aVar);
        if (!d) {
            i.c().d();
        }
    }

    public boolean d() {
        return this.b.size() > 0;
    }
}
