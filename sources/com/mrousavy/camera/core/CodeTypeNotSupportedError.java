package com.mrousavy.camera.core;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/mrousavy/camera/core/CodeTypeNotSupportedError;", "Lcom/mrousavy/camera/core/CameraError;", "codeType", "", "(Ljava/lang/String;)V", "react-native-vision-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: CameraError.kt */
public final class CodeTypeNotSupportedError extends CameraError {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CodeTypeNotSupportedError(String str) {
        super("code-scanner", "code-type-not-supported", "The codeType \"" + str + "\" is not supported by the Code Scanner!", (Throwable) null, 8, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(str, "codeType");
    }
}
