package com.iab.omid.library.adsbynimbus.internal;

import android.view.View;
import com.iab.omid.library.adsbynimbus.adsession.a;

public class b extends d {
    private static b d = new b();

    private b() {
    }

    public static b g() {
        return d;
    }

    public void b(boolean z) {
        for (a adSessionStatePublisher : c.c().b()) {
            adSessionStatePublisher.getAdSessionStatePublisher().a(z);
        }
    }

    public boolean d() {
        for (a c : c.c().a()) {
            View c2 = c.c();
            if (c2 != null && c2.hasWindowFocus()) {
                return true;
            }
        }
        return false;
    }
}
