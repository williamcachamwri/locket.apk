package com.jimmydaddy.imagemarker.base;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0017\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\r\u0010\b\u001a\u00020\u0006H\u0007¢\u0006\u0002\b\tJ\u0006\u0010\n\u001a\u00020\u0006R\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/jimmydaddy/imagemarker/base/MarkerError;", "Ljava/lang/Error;", "Lkotlin/Error;", "errorCode", "Lcom/jimmydaddy/imagemarker/base/ErrorCode;", "errMsg", "", "(Lcom/jimmydaddy/imagemarker/base/ErrorCode;Ljava/lang/String;)V", "getErrMsg", "functionOfKotlin", "getErrorCode", "react-native-image-marker_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MarkerError.kt */
public final class MarkerError extends Error {
    private String errMsg;
    private ErrorCode errorCode;

    public MarkerError(ErrorCode errorCode2, String str) {
        Intrinsics.checkNotNullParameter(errorCode2, "errorCode");
        Intrinsics.checkNotNullParameter(str, "errMsg");
        this.errorCode = errorCode2;
        this.errMsg = str;
    }

    public final String getErrorCode() {
        return this.errorCode.getValue();
    }

    public final String functionOfKotlin() {
        return this.errMsg;
    }
}
