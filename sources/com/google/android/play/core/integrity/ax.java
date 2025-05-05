package com.google.android.play.core.integrity;

import android.content.Context;
import com.google.android.play.integrity.internal.ag;

/* compiled from: com.google.android.play:integrity@@1.3.0 */
final class ax {

    /* renamed from: a  reason: collision with root package name */
    private static aw f39a;

    static synchronized aw a(Context context) {
        aw awVar;
        synchronized (ax.class) {
            if (f39a == null) {
                u uVar = new u((t) null);
                uVar.a(ag.a(context));
                f39a = uVar.b();
            }
            awVar = f39a;
        }
        return awVar;
    }
}
