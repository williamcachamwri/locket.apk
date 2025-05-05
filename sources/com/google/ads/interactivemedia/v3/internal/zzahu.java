package com.google.ads.interactivemedia.v3.internal;

import java.lang.reflect.Field;
import java.util.Objects;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzahu {
    static Object zza(Field field, Object obj) {
        try {
            return ((Field) Objects.requireNonNull(field, "field")).get(obj);
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
