package com.google.android.gms.internal.atv_ads_framework;

import java.lang.reflect.Field;
import java.security.PrivilegedExceptionAction;
import sun.misc.Unsafe;

/* compiled from: com.google.android.tv:tv-ads@@1.0.0 */
final class zzfv implements PrivilegedExceptionAction {
    zzfv() {
    }

    public final /* bridge */ /* synthetic */ Object run() throws Exception {
        Class<Unsafe> cls = Unsafe.class;
        for (Field field : cls.getDeclaredFields()) {
            field.setAccessible(true);
            Object obj = field.get((Object) null);
            if (cls.isInstance(obj)) {
                return cls.cast(obj);
            }
        }
        return null;
    }
}
