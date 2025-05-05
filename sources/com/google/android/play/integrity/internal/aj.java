package com.google.android.play.integrity.internal;

/* compiled from: com.google.android.play:integrity@@1.3.0 */
public final class aj implements an {

    /* renamed from: a  reason: collision with root package name */
    private static final Object f79a = new Object();
    private volatile an b;
    private volatile Object c = f79a;

    private aj(an anVar) {
        this.b = anVar;
    }

    public static an b(an anVar) {
        return anVar instanceof aj ? anVar : new aj(anVar);
    }

    public final Object a() {
        Object obj = this.c;
        Object obj2 = f79a;
        if (obj == obj2) {
            synchronized (this) {
                obj = this.c;
                if (obj == obj2) {
                    obj = this.b.a();
                    Object obj3 = this.c;
                    if (obj3 != obj2) {
                        if (obj3 != obj) {
                            throw new IllegalStateException("Scoped provider was invoked recursively returning different results: " + obj3 + " & " + obj + ". This is likely due to a circular dependency.");
                        }
                    }
                    this.c = obj;
                    this.b = null;
                }
            }
        }
        return obj;
    }
}
