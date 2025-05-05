package com.mrousavy.camera.core;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/mrousavy/camera/core/ViewNotFoundError;", "Lcom/mrousavy/camera/core/CameraError;", "viewId", "", "(I)V", "react-native-vision-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: CameraError.kt */
public final class ViewNotFoundError extends CameraError {
    public ViewNotFoundError(int i) {
        super("system", "view-not-found", "The given view (ID " + i + ") was not found in the view manager.", (Throwable) null, 8, (DefaultConstructorMarker) null);
    }
}
