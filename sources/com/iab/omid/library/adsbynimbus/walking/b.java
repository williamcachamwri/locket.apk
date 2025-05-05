package com.iab.omid.library.adsbynimbus.walking;

import com.iab.omid.library.adsbynimbus.walking.async.b;
import com.iab.omid.library.adsbynimbus.walking.async.c;
import com.iab.omid.library.adsbynimbus.walking.async.d;
import com.iab.omid.library.adsbynimbus.walking.async.e;
import com.iab.omid.library.adsbynimbus.walking.async.f;
import java.util.HashSet;
import org.json.JSONObject;

public class b implements b.C0011b {

    /* renamed from: a  reason: collision with root package name */
    private JSONObject f124a;
    private final c b;

    public b(c cVar) {
        this.b = cVar;
    }

    public JSONObject a() {
        return this.f124a;
    }

    public void a(JSONObject jSONObject) {
        this.f124a = jSONObject;
    }

    public void a(JSONObject jSONObject, HashSet<String> hashSet, long j) {
        this.b.b(new e(this, hashSet, jSONObject, j));
    }

    public void b() {
        this.b.b(new d(this));
    }

    public void b(JSONObject jSONObject, HashSet<String> hashSet, long j) {
        this.b.b(new f(this, hashSet, jSONObject, j));
    }
}
