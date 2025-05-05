package com.iab.omid.library.adsbynimbus.walking;

import android.view.View;
import android.view.ViewParent;
import com.iab.omid.library.adsbynimbus.internal.c;
import com.iab.omid.library.adsbynimbus.internal.e;
import com.iab.omid.library.adsbynimbus.utils.h;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.WeakHashMap;

public class a {

    /* renamed from: a  reason: collision with root package name */
    private final HashMap<View, String> f120a = new HashMap<>();
    private final HashMap<View, C0010a> b = new HashMap<>();
    private final HashMap<String, View> c = new HashMap<>();
    private final HashSet<View> d = new HashSet<>();
    private final HashSet<String> e = new HashSet<>();
    private final HashSet<String> f = new HashSet<>();
    private final HashMap<String, String> g = new HashMap<>();
    private final HashSet<String> h = new HashSet<>();
    private final Map<View, Boolean> i = new WeakHashMap();
    private boolean j;

    /* renamed from: com.iab.omid.library.adsbynimbus.walking.a$a  reason: collision with other inner class name */
    public static class C0010a {

        /* renamed from: a  reason: collision with root package name */
        private final e f121a;
        private final ArrayList<String> b = new ArrayList<>();

        public C0010a(e eVar, String str) {
            this.f121a = eVar;
            a(str);
        }

        public e a() {
            return this.f121a;
        }

        public void a(String str) {
            this.b.add(str);
        }

        public ArrayList<String> b() {
            return this.b;
        }
    }

    private Boolean a(View view) {
        if (view.hasWindowFocus()) {
            this.i.remove(view);
            return Boolean.FALSE;
        } else if (this.i.containsKey(view)) {
            return this.i.get(view);
        } else {
            Map<View, Boolean> map = this.i;
            Boolean bool = Boolean.FALSE;
            map.put(view, bool);
            return bool;
        }
    }

    private String a(View view, boolean z) {
        if (!view.isAttachedToWindow()) {
            return "notAttached";
        }
        if (a(view).booleanValue() && !z) {
            return "noWindowFocus";
        }
        HashSet hashSet = new HashSet();
        while (view != null) {
            String a2 = h.a(view);
            if (a2 != null) {
                return a2;
            }
            hashSet.add(view);
            ViewParent parent = view.getParent();
            view = parent instanceof View ? (View) parent : null;
        }
        this.d.addAll(hashSet);
        return null;
    }

    private void a(com.iab.omid.library.adsbynimbus.adsession.a aVar) {
        for (e a2 : aVar.d()) {
            a(a2, aVar);
        }
    }

    private void a(e eVar, com.iab.omid.library.adsbynimbus.adsession.a aVar) {
        View view = (View) eVar.c().get();
        if (view != null) {
            C0010a aVar2 = this.b.get(view);
            if (aVar2 != null) {
                aVar2.a(aVar.getAdSessionId());
            } else {
                this.b.put(view, new C0010a(eVar, aVar.getAdSessionId()));
            }
        }
    }

    public View a(String str) {
        return this.c.get(str);
    }

    public void a() {
        this.f120a.clear();
        this.b.clear();
        this.c.clear();
        this.d.clear();
        this.e.clear();
        this.f.clear();
        this.g.clear();
        this.j = false;
        this.h.clear();
    }

    public C0010a b(View view) {
        C0010a aVar = this.b.get(view);
        if (aVar != null) {
            this.b.remove(view);
        }
        return aVar;
    }

    public String b(String str) {
        return this.g.get(str);
    }

    public HashSet<String> b() {
        return this.f;
    }

    public String c(View view) {
        if (this.f120a.size() == 0) {
            return null;
        }
        String str = this.f120a.get(view);
        if (str != null) {
            this.f120a.remove(view);
        }
        return str;
    }

    public HashSet<String> c() {
        return this.e;
    }

    public boolean c(String str) {
        return this.h.contains(str);
    }

    public c d(View view) {
        return this.d.contains(view) ? c.PARENT_VIEW : this.j ? c.OBSTRUCTION_VIEW : c.UNDERLYING_VIEW;
    }

    public void d() {
        this.j = true;
    }

    public void e() {
        c c2 = c.c();
        if (c2 != null) {
            for (com.iab.omid.library.adsbynimbus.adsession.a next : c2.a()) {
                View c3 = next.c();
                if (next.f()) {
                    String adSessionId = next.getAdSessionId();
                    if (c3 != null) {
                        boolean e2 = h.e(c3);
                        if (e2) {
                            this.h.add(adSessionId);
                        }
                        String a2 = a(c3, e2);
                        if (a2 == null) {
                            this.e.add(adSessionId);
                            this.f120a.put(c3, adSessionId);
                            a(next);
                        } else if (a2 != "noWindowFocus") {
                            this.f.add(adSessionId);
                            this.c.put(adSessionId, c3);
                            this.g.put(adSessionId, a2);
                        }
                    } else {
                        this.f.add(adSessionId);
                        this.g.put(adSessionId, "noAdView");
                    }
                }
            }
        }
    }

    public boolean e(View view) {
        if (!this.i.containsKey(view)) {
            return true;
        }
        this.i.put(view, Boolean.TRUE);
        return false;
    }
}
