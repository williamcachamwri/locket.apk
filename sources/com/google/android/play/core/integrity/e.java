package com.google.android.play.core.integrity;

import com.google.android.play.core.integrity.StandardIntegrityManager;

/* compiled from: com.google.android.play:integrity@@1.3.0 */
final class e extends StandardIntegrityManager.PrepareIntegrityTokenRequest {

    /* renamed from: a  reason: collision with root package name */
    private final long f60a;

    /* synthetic */ e(long j, int i, d dVar) {
        this.f60a = j;
    }

    /* access modifiers changed from: package-private */
    public final int a() {
        return 0;
    }

    public final long b() {
        return this.f60a;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof StandardIntegrityManager.PrepareIntegrityTokenRequest) {
            StandardIntegrityManager.PrepareIntegrityTokenRequest prepareIntegrityTokenRequest = (StandardIntegrityManager.PrepareIntegrityTokenRequest) obj;
            if (this.f60a == prepareIntegrityTokenRequest.b()) {
                prepareIntegrityTokenRequest.a();
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        long j = this.f60a;
        return (((int) (j ^ (j >>> 32))) ^ 1000003) * 1000003;
    }

    public final String toString() {
        return "PrepareIntegrityTokenRequest{cloudProjectNumber=" + this.f60a + ", webViewRequestMode=0}";
    }
}
