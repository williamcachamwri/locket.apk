package com.google.android.play.integrity.internal;

/* compiled from: com.google.android.play:integrity@@1.3.0 */
public final class am {
    public static void a(Object obj, Class cls) {
        if (obj == null) {
            throw new IllegalStateException(String.valueOf(cls.getCanonicalName()).concat(" must be set"));
        }
    }
}
