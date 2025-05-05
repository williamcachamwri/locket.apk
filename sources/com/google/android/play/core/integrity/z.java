package com.google.android.play.core.integrity;

import android.content.Context;
import com.google.android.play.integrity.internal.ag;

/* compiled from: com.google.android.play:integrity@@1.3.0 */
final class z {

    /* renamed from: a  reason: collision with root package name */
    private static s f72a;

    static synchronized s a(Context context) {
        s sVar;
        synchronized (z.class) {
            if (f72a == null) {
                q qVar = new q((p) null);
                qVar.a(ag.a(context));
                f72a = qVar.b();
            }
            sVar = f72a;
        }
        return sVar;
    }
}
