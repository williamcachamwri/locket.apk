package com.iab.omid.library.adsbynimbus.internal;

import android.content.Context;

public class g {
    private static g b = new g();

    /* renamed from: a  reason: collision with root package name */
    private Context f100a;

    private g() {
    }

    public static g b() {
        return b;
    }

    public Context a() {
        return this.f100a;
    }

    public void a(Context context) {
        this.f100a = context != null ? context.getApplicationContext() : null;
    }
}
