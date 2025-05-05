package com.mrousavy.camera.core;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, d2 = {"Lcom/mrousavy/camera/core/InvalidTypeScriptUnionError;", "Lcom/mrousavy/camera/core/CameraError;", "unionName", "", "unionValue", "(Ljava/lang/String;Ljava/lang/String;)V", "react-native-vision-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: CameraError.kt */
public final class InvalidTypeScriptUnionError extends CameraError {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public InvalidTypeScriptUnionError(String str, String str2) {
        super("parameter", "invalid-parameter", "The given value for " + str + " could not be parsed! (Received: " + str2 + ")", (Throwable) null, 8, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(str, "unionName");
    }
}
