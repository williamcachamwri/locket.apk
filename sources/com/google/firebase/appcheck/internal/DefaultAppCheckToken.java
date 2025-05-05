package com.google.firebase.appcheck.internal;

import androidx.media3.exoplayer.upstream.CmcdData;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.appcheck.AppCheckToken;
import com.google.firebase.appcheck.internal.util.Clock;
import com.google.firebase.appcheck.internal.util.TokenParser;
import io.sentry.android.core.SentryLogcatAdapter;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public final class DefaultAppCheckToken extends AppCheckToken {
    static final String EXPIRATION_TIME_KEY = "exp";
    static final String EXPIRES_IN_MILLIS_KEY = "expiresIn";
    static final String ISSUED_AT_KEY = "iat";
    private static final long ONE_SECOND_MILLIS = 1000;
    static final String RECEIVED_AT_TIMESTAMP_KEY = "receivedAt";
    private static final String TAG = "com.google.firebase.appcheck.internal.DefaultAppCheckToken";
    static final String TOKEN_KEY = "token";
    private final long expiresInMillis;
    private final long receivedAtTimestamp;
    private final String token;

    DefaultAppCheckToken(String str, long j) {
        this(str, j, new Clock.DefaultClock().currentTimeMillis());
    }

    DefaultAppCheckToken(String str, long j, long j2) {
        Preconditions.checkNotEmpty(str);
        this.token = str;
        this.expiresInMillis = j;
        this.receivedAtTimestamp = j2;
    }

    public static DefaultAppCheckToken constructFromAppCheckTokenResponse(AppCheckTokenResponse appCheckTokenResponse) {
        long j;
        Preconditions.checkNotNull(appCheckTokenResponse);
        try {
            j = (long) (Double.parseDouble(appCheckTokenResponse.getTimeToLive().replace(CmcdData.Factory.STREAMING_FORMAT_SS, "")) * 1000.0d);
        } catch (NumberFormatException unused) {
            Map<String, Object> parseTokenClaims = TokenParser.parseTokenClaims(appCheckTokenResponse.getToken());
            j = 1000 * (getLongFromClaimsSafely(parseTokenClaims, EXPIRATION_TIME_KEY) - getLongFromClaimsSafely(parseTokenClaims, ISSUED_AT_KEY));
        }
        return new DefaultAppCheckToken(appCheckTokenResponse.getToken(), j);
    }

    public String getToken() {
        return this.token;
    }

    public long getExpireTimeMillis() {
        return this.receivedAtTimestamp + this.expiresInMillis;
    }

    /* access modifiers changed from: package-private */
    public long getReceivedAtTimestamp() {
        return this.receivedAtTimestamp;
    }

    /* access modifiers changed from: package-private */
    public long getExpiresInMillis() {
        return this.expiresInMillis;
    }

    /* access modifiers changed from: package-private */
    public String serializeTokenToString() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(TOKEN_KEY, this.token);
            jSONObject.put(RECEIVED_AT_TIMESTAMP_KEY, this.receivedAtTimestamp);
            jSONObject.put(EXPIRES_IN_MILLIS_KEY, this.expiresInMillis);
            return jSONObject.toString();
        } catch (JSONException e) {
            SentryLogcatAdapter.e(TAG, "Could not serialize token: " + e.getMessage());
            return null;
        }
    }

    static DefaultAppCheckToken deserializeTokenFromJsonString(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            return new DefaultAppCheckToken(jSONObject.getString(TOKEN_KEY), jSONObject.getLong(EXPIRES_IN_MILLIS_KEY), jSONObject.getLong(RECEIVED_AT_TIMESTAMP_KEY));
        } catch (JSONException e) {
            SentryLogcatAdapter.e(TAG, "Could not deserialize token: " + e.getMessage());
            return null;
        }
    }

    public static DefaultAppCheckToken constructFromRawToken(String str) {
        Preconditions.checkNotNull(str);
        Map<String, Object> parseTokenClaims = TokenParser.parseTokenClaims(str);
        long longFromClaimsSafely = getLongFromClaimsSafely(parseTokenClaims, ISSUED_AT_KEY);
        return new DefaultAppCheckToken(str, (getLongFromClaimsSafely(parseTokenClaims, EXPIRATION_TIME_KEY) - longFromClaimsSafely) * 1000, longFromClaimsSafely * 1000);
    }

    private static long getLongFromClaimsSafely(Map<String, Object> map, String str) {
        Preconditions.checkNotNull(map);
        Preconditions.checkNotEmpty(str);
        Integer num = (Integer) map.get(str);
        if (num == null) {
            return 0;
        }
        return num.longValue();
    }
}
