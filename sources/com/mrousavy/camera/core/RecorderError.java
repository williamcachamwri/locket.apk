package com.mrousavy.camera.core;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0004\b\u0016\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/mrousavy/camera/core/RecorderError;", "Lcom/mrousavy/camera/core/CameraError;", "id", "", "message", "wasVideoRecorded", "", "cause", "", "(Ljava/lang/String;Ljava/lang/String;ZLjava/lang/Throwable;)V", "getWasVideoRecorded", "()Z", "react-native-vision-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: CameraError.kt */
public class RecorderError extends CameraError {
    private final boolean wasVideoRecorded;

    public final boolean getWasVideoRecorded() {
        return this.wasVideoRecorded;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public RecorderError(String str, String str2, boolean z, Throwable th) {
        super("capture", str, str2, th);
        Intrinsics.checkNotNullParameter(str, "id");
        Intrinsics.checkNotNullParameter(str2, "message");
        this.wasVideoRecorded = z;
    }
}
