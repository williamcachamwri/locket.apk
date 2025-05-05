package com.jimmydaddy.imagemarker.base;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, d2 = {"Lcom/jimmydaddy/imagemarker/base/ErrorCode;", "", "value", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "INVALID_PARAMS", "LOAD_IMAGE_FAILED", "GET_RESOURCE_FAILED", "PARAMS_REQUIRED", "react-native-image-marker_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ErrorCode.kt */
public enum ErrorCode {
    INVALID_PARAMS("INVALID_PARAMS"),
    LOAD_IMAGE_FAILED("LOAD_IMAGE_FAILED"),
    GET_RESOURCE_FAILED("GET_RESOURCE_FAILED"),
    PARAMS_REQUIRED("PARAMS_REQUIRED");
    
    private final String value;

    public static EnumEntries<ErrorCode> getEntries() {
        return $ENTRIES;
    }

    private ErrorCode(String str) {
        this.value = str;
    }

    public final String getValue() {
        return this.value;
    }

    static {
        ErrorCode[] $values;
        $ENTRIES = EnumEntriesKt.enumEntries((E[]) (Enum[]) $values);
    }
}
