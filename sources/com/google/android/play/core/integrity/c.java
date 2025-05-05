package com.google.android.play.core.integrity;

import com.google.android.play.core.integrity.StandardIntegrityManager;

/* compiled from: com.google.android.play:integrity@@1.3.0 */
final class c extends StandardIntegrityManager.PrepareIntegrityTokenRequest.Builder {

    /* renamed from: a  reason: collision with root package name */
    private long f59a;
    private byte b;

    c() {
    }

    public final StandardIntegrityManager.PrepareIntegrityTokenRequest.Builder a(int i) {
        this.b = (byte) (this.b | 2);
        return this;
    }

    public final StandardIntegrityManager.PrepareIntegrityTokenRequest build() {
        if (this.b == 3) {
            return new e(this.f59a, 0, (d) null);
        }
        StringBuilder sb = new StringBuilder();
        if ((this.b & 1) == 0) {
            sb.append(" cloudProjectNumber");
        }
        if ((this.b & 2) == 0) {
            sb.append(" webViewRequestMode");
        }
        throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
    }

    public final StandardIntegrityManager.PrepareIntegrityTokenRequest.Builder setCloudProjectNumber(long j) {
        this.f59a = j;
        this.b = (byte) (this.b | 1);
        return this;
    }
}
