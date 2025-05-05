package com.google.android.gms.internal.auth;

import com.google.android.gms.common.server.response.FastSafeParcelableJsonResponse;
import io.sentry.android.core.SentryLogcatAdapter;
import java.io.UnsupportedEncodingException;

/* compiled from: com.google.android.gms:play-services-auth-base@@18.0.10 */
public abstract class zzbz extends FastSafeParcelableJsonResponse {
    public final byte[] toByteArray() {
        try {
            return toString().getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            SentryLogcatAdapter.e("AUTH", "Error serializing object.", e);
            return null;
        }
    }
}
