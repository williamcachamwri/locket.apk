package com.google.android.play.integrity.internal;

import android.content.Context;

/* compiled from: com.google.android.play:integrity@@1.3.0 */
public final class ag {
    public static Context a(Context context) {
        Context applicationContext = context.getApplicationContext();
        return applicationContext != null ? applicationContext : context;
    }
}
