package com.iab.omid.library.adsbynimbus.utils;

import android.text.TextUtils;
import android.util.Log;
import com.iab.omid.library.adsbynimbus.a;

public final class d {
    public static void a(String str) {
        if (a.f89a.booleanValue() && !TextUtils.isEmpty(str)) {
            Log.i("OMIDLIB", str);
        }
    }

    public static void a(String str, Exception exc) {
        if ((a.f89a.booleanValue() && !TextUtils.isEmpty(str)) || exc != null) {
            Log.e("OMIDLIB", str, exc);
        }
    }

    public static void b(String str) {
        if (a.f89a.booleanValue() && !TextUtils.isEmpty(str)) {
            Log.w("OMIDLIB", str);
        }
    }
}
