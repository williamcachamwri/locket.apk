package com.iab.omid.library.adsbynimbus.walking.async;

import com.iab.omid.library.adsbynimbus.walking.async.b;
import java.util.HashSet;
import org.json.JSONObject;

public abstract class a extends b {
    protected final HashSet<String> c;
    protected final JSONObject d;
    protected final long e;

    public a(b.C0011b bVar, HashSet<String> hashSet, JSONObject jSONObject, long j) {
        super(bVar);
        this.c = new HashSet<>(hashSet);
        this.d = jSONObject;
        this.e = j;
    }
}
