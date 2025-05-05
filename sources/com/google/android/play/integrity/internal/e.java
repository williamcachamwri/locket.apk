package com.google.android.play.integrity.internal;

/* compiled from: com.google.android.play:integrity@@1.3.0 */
final class e extends f {

    /* renamed from: a  reason: collision with root package name */
    private final int f82a;
    private final long b;

    e(int i, long j) {
        this.f82a = i;
        this.b = j;
    }

    public final int a() {
        return this.f82a;
    }

    public final long b() {
        return this.b;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof f) {
            f fVar = (f) obj;
            return this.f82a == fVar.a() && this.b == fVar.b();
        }
    }

    public final int hashCode() {
        long j = this.b;
        return ((int) (j ^ (j >>> 32))) ^ ((this.f82a ^ 1000003) * 1000003);
    }

    public final String toString() {
        return "EventRecord{eventType=" + this.f82a + ", eventTimestamp=" + this.b + "}";
    }
}
