package com.google.android.recaptcha.internal;

import kotlin.LazyKt;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzge {
    public static final Class zza(Object obj) {
        Class cls;
        if (obj instanceof Class) {
            return (Class) obj;
        }
        if (obj instanceof Integer) {
            int intValue = ((Number) obj).intValue();
            if (intValue == 1) {
                cls = Integer.TYPE;
            } else {
                cls = intValue == 2 ? Short.TYPE : intValue == 3 ? Byte.TYPE : intValue == 4 ? Long.TYPE : intValue == 5 ? Character.TYPE : intValue == 6 ? Float.TYPE : intValue == 7 ? Double.TYPE : intValue == 8 ? Boolean.TYPE : null;
            }
            if (cls != null) {
                return cls;
            }
            throw new zzcg(4, 6, (Throwable) null);
        } else if (obj instanceof String) {
            try {
                String str = (String) obj;
                Class<?> cls2 = Class.forName(str);
                int i = zzax.zza;
                if (((zzfw) LazyKt.lazy(zzgd.zza).getValue()).zzb(str)) {
                    return cls2;
                }
                throw new zzcg(6, 47, (Throwable) null);
            } catch (Exception e) {
                throw new zzcg(6, 8, e);
            }
        } else {
            throw new zzcg(4, 5, (Throwable) null);
        }
    }
}
