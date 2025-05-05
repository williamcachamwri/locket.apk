package com.mrousavy.camera.core;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/mrousavy/camera/core/UnknownRecorderError;", "Lcom/mrousavy/camera/core/RecorderError;", "wasVideoRecorded", "", "cause", "", "(ZLjava/lang/Throwable;)V", "react-native-vision-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: CameraError.kt */
public final class UnknownRecorderError extends RecorderError {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public UnknownRecorderError(boolean z, Throwable th) {
        super("recorder-error", "An error occurred while recording a video! " + CameraErrorKt.getVideoCapturedMessage(z) + " " + (th != null ? th.getMessage() : null), z, th);
    }
}
