package com.iab.omid.library.adsbynimbus.internal;

import android.view.View;
import com.iab.omid.library.adsbynimbus.adsession.FriendlyObstructionPurpose;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class f {
    private static final Pattern b = Pattern.compile("^[a-zA-Z0-9 ]+$");

    /* renamed from: a  reason: collision with root package name */
    private final List<e> f99a = new ArrayList();

    private void a(View view) {
        if (view == null) {
            throw new IllegalArgumentException("FriendlyObstruction is null");
        }
    }

    private void a(String str) {
        if (str == null) {
            return;
        }
        if (str.length() > 50) {
            throw new IllegalArgumentException("FriendlyObstruction has detailed reason over 50 characters in length");
        } else if (!b.matcher(str).matches()) {
            throw new IllegalArgumentException("FriendlyObstruction has detailed reason that contains characters not in [a-z][A-Z][0-9] or space");
        }
    }

    private e b(View view) {
        for (e next : this.f99a) {
            if (next.c().get() == view) {
                return next;
            }
        }
        return null;
    }

    public List<e> a() {
        return this.f99a;
    }

    public void a(View view, FriendlyObstructionPurpose friendlyObstructionPurpose, String str) {
        a(view);
        a(str);
        if (b(view) == null) {
            this.f99a.add(new e(view, friendlyObstructionPurpose, str));
        }
    }

    public void b() {
        this.f99a.clear();
    }

    public void c(View view) {
        a(view);
        e b2 = b(view);
        if (b2 != null) {
            this.f99a.remove(b2);
        }
    }
}
