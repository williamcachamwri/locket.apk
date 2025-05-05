package com.google.android.play.core.integrity;

/* compiled from: com.google.android.play:integrity@@1.3.0 */
final class b extends bq {

    /* renamed from: a  reason: collision with root package name */
    private String f42a;
    private y b;

    b() {
    }

    /* access modifiers changed from: package-private */
    public final bq a(y yVar) {
        this.b = yVar;
        return this;
    }

    /* access modifiers changed from: package-private */
    public final bq b(String str) {
        if (str != null) {
            this.f42a = str;
            return this;
        }
        throw new NullPointerException("Null token");
    }

    /* access modifiers changed from: package-private */
    public final br c() {
        y yVar;
        String str = this.f42a;
        if (str != null && (yVar = this.b) != null) {
            return new br(str, yVar);
        }
        StringBuilder sb = new StringBuilder();
        if (this.f42a == null) {
            sb.append(" token");
        }
        if (this.b == null) {
            sb.append(" integrityDialogWrapper");
        }
        throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
    }
}
