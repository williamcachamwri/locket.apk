package com.google.android.play.core.integrity;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.play.core.integrity.model.a;
import java.util.Locale;

/* compiled from: com.google.android.play:integrity@@1.3.0 */
public class IntegrityServiceException extends ApiException {

    /* renamed from: a  reason: collision with root package name */
    private final Throwable f18a;

    IntegrityServiceException(int i, Throwable th) {
        super(new Status(i, String.format(Locale.ROOT, "Integrity API error (%d): %s.", new Object[]{Integer.valueOf(i), a.a(i)})));
        if (i != 0) {
            this.f18a = th;
            return;
        }
        throw new IllegalArgumentException("ErrorCode should not be 0.");
    }

    public final synchronized Throwable getCause() {
        return this.f18a;
    }

    public int getErrorCode() {
        return super.getStatusCode();
    }
}
