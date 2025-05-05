package com.google.android.play.core.integrity;

import com.google.android.play.core.integrity.IntegrityTokenRequest;

/* compiled from: com.google.android.play:integrity@@1.3.0 */
final class am extends IntegrityTokenRequest.Builder {

    /* renamed from: a  reason: collision with root package name */
    private String f32a;
    private Long b;

    am() {
    }

    public final IntegrityTokenRequest build() {
        String str = this.f32a;
        if (str != null) {
            return new ao(str, this.b, (Object) null, (an) null);
        }
        throw new IllegalStateException("Missing required properties: nonce");
    }

    public final IntegrityTokenRequest.Builder setCloudProjectNumber(long j) {
        this.b = Long.valueOf(j);
        return this;
    }

    public final IntegrityTokenRequest.Builder setNonce(String str) {
        if (str != null) {
            this.f32a = str;
            return this;
        }
        throw new NullPointerException("Null nonce");
    }
}
