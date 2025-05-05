package com.google.android.recaptcha;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0019\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014¨\u0006\u0015"}, d2 = {"Lcom/google/android/recaptcha/RecaptchaErrorCode;", "", "errorCode", "", "errorMessage", "", "<init>", "(Ljava/lang/String;IILjava/lang/String;)V", "getErrorCode", "()I", "getErrorMessage", "()Ljava/lang/String;", "UNKNOWN_ERROR", "NETWORK_ERROR", "INVALID_SITEKEY", "INVALID_KEYTYPE", "INVALID_PACKAGE_NAME", "INVALID_ACTION", "INVALID_TIMEOUT", "NO_NETWORK_FOUND", "INTERNAL_ERROR", "java.com.google.android.libraries.abuse.recaptcha.enterprise.public_public"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public enum RecaptchaErrorCode {
    UNKNOWN_ERROR(0, "Unknown Error"),
    NETWORK_ERROR(1, "Network Error"),
    INVALID_SITEKEY(2, "Site key invalid"),
    INVALID_KEYTYPE(3, "Key type invalid"),
    INVALID_PACKAGE_NAME(4, "Package name not allowed"),
    INVALID_ACTION(5, "Invalid action name, may only include alphanumeric characters like [A-Z], [a-z], [0-9], / and _. Do not include user-specific information"),
    INVALID_TIMEOUT(6, "Invalid timeout, minimum value is 5_000L milliseconds"),
    NO_NETWORK_FOUND(7, "No network found on device"),
    INTERNAL_ERROR(100, "Internal Error");
    
    private final int errorCode;
    private final String errorMessage;

    static {
        RecaptchaErrorCode[] $values;
        $ENTRIES = EnumEntriesKt.enumEntries((E[]) (Enum[]) $values);
    }

    private RecaptchaErrorCode(int i, String str) {
        this.errorCode = i;
        this.errorMessage = str;
    }

    public static EnumEntries<RecaptchaErrorCode> getEntries() {
        return $ENTRIES;
    }

    public final int getErrorCode() {
        return this.errorCode;
    }

    public final String getErrorMessage() {
        return this.errorMessage;
    }
}
