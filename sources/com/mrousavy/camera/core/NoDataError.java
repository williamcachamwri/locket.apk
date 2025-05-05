package com.mrousavy.camera.core;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/mrousavy/camera/core/NoDataError;", "Lcom/mrousavy/camera/core/RecorderError;", "cause", "", "(Ljava/lang/Throwable;)V", "react-native-vision-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: CameraError.kt */
public final class NoDataError extends RecorderError {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public NoDataError(Throwable th) {
        super("no-data", "The Video Recording failed because no data was received! (" + (th != null ? th.getMessage() : null) + ") Did you stop the recording before any Frames arrived?", false, th);
    }
}
