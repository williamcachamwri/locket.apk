package androidx.media3.session;

import android.os.Bundle;
import android.os.SystemClock;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public final class SessionResult {
    private static final String FIELD_COMPLETION_TIME_MS = Util.intToStringMaxRadix(2);
    private static final String FIELD_EXTRAS = Util.intToStringMaxRadix(1);
    private static final String FIELD_RESULT_CODE = Util.intToStringMaxRadix(0);
    private static final String FIELD_SESSION_ERROR = Util.intToStringMaxRadix(3);
    public static final int RESULT_ERROR_BAD_VALUE = -3;
    public static final int RESULT_ERROR_INVALID_STATE = -2;
    public static final int RESULT_ERROR_IO = -5;
    public static final int RESULT_ERROR_NOT_SUPPORTED = -6;
    public static final int RESULT_ERROR_PERMISSION_DENIED = -4;
    public static final int RESULT_ERROR_SESSION_AUTHENTICATION_EXPIRED = -102;
    public static final int RESULT_ERROR_SESSION_CONCURRENT_STREAM_LIMIT = -104;
    public static final int RESULT_ERROR_SESSION_DISCONNECTED = -100;
    public static final int RESULT_ERROR_SESSION_NOT_AVAILABLE_IN_REGION = -106;
    public static final int RESULT_ERROR_SESSION_PARENTAL_CONTROL_RESTRICTED = -105;
    public static final int RESULT_ERROR_SESSION_PREMIUM_ACCOUNT_REQUIRED = -103;
    public static final int RESULT_ERROR_SESSION_SETUP_REQUIRED = -108;
    public static final int RESULT_ERROR_SESSION_SKIP_LIMIT_REACHED = -107;
    public static final int RESULT_ERROR_UNKNOWN = -1;
    public static final int RESULT_INFO_SKIPPED = 1;
    public static final int RESULT_SUCCESS = 0;
    public final long completionTimeMs;
    public final Bundle extras;
    public final int resultCode;
    public final SessionError sessionError;

    @Documented
    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Code {
    }

    public SessionResult(int i) {
        this(i, Bundle.EMPTY);
    }

    public SessionResult(int i, Bundle bundle) {
        this(i, bundle, SystemClock.elapsedRealtime(), (SessionError) null);
    }

    public SessionResult(SessionError sessionError2) {
        this(sessionError2.code, Bundle.EMPTY, SystemClock.elapsedRealtime(), sessionError2);
    }

    public SessionResult(SessionError sessionError2, Bundle bundle) {
        this(sessionError2.code, bundle, SystemClock.elapsedRealtime(), sessionError2);
    }

    private SessionResult(int i, Bundle bundle, long j, SessionError sessionError2) {
        Assertions.checkArgument(sessionError2 == null || i < 0);
        this.resultCode = i;
        this.extras = new Bundle(bundle);
        this.completionTimeMs = j;
        if (sessionError2 == null && i < 0) {
            sessionError2 = new SessionError(i, "no error message provided");
        }
        this.sessionError = sessionError2;
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putInt(FIELD_RESULT_CODE, this.resultCode);
        bundle.putBundle(FIELD_EXTRAS, this.extras);
        bundle.putLong(FIELD_COMPLETION_TIME_MS, this.completionTimeMs);
        SessionError sessionError2 = this.sessionError;
        if (sessionError2 != null) {
            bundle.putBundle(FIELD_SESSION_ERROR, sessionError2.toBundle());
        }
        return bundle;
    }

    public static SessionResult fromBundle(Bundle bundle) {
        SessionError sessionError2;
        int i = bundle.getInt(FIELD_RESULT_CODE, -1);
        Bundle bundle2 = bundle.getBundle(FIELD_EXTRAS);
        long j = bundle.getLong(FIELD_COMPLETION_TIME_MS, SystemClock.elapsedRealtime());
        Bundle bundle3 = bundle.getBundle(FIELD_SESSION_ERROR);
        if (bundle3 != null) {
            sessionError2 = SessionError.fromBundle(bundle3);
        } else {
            sessionError2 = i != 0 ? new SessionError(i, "no error message provided") : null;
        }
        SessionError sessionError3 = sessionError2;
        if (bundle2 == null) {
            bundle2 = Bundle.EMPTY;
        }
        return new SessionResult(i, bundle2, j, sessionError3);
    }
}
