package com.mrousavy.camera.core;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/mrousavy/camera/core/FileIOError;", "Lcom/mrousavy/camera/core/CameraError;", "throwable", "", "(Ljava/lang/Throwable;)V", "react-native-vision-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: CameraError.kt */
public final class FileIOError extends CameraError {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FileIOError(Throwable th) {
        super("capture", "file-io-error", "An unexpected File IO error occurred! Error: " + th.getMessage() + ".", th);
        Intrinsics.checkNotNullParameter(th, "throwable");
    }
}
