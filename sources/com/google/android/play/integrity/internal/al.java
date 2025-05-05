package com.google.android.play.integrity.internal;

/* compiled from: com.google.android.play:integrity@@1.3.0 */
public final class al implements ak {

    /* renamed from: a  reason: collision with root package name */
    private static final al f80a = new al((Object) null);
    private final Object b;

    private al(Object obj) {
        this.b = obj;
    }

    public static ak b(Object obj) {
        if (obj != null) {
            return new al(obj);
        }
        throw new NullPointerException("instance cannot be null");
    }

    public final Object a() {
        return this.b;
    }
}
