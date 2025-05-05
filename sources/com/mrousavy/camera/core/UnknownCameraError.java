package com.mrousavy.camera.core;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/mrousavy/camera/core/UnknownCameraError;", "Lcom/mrousavy/camera/core/CameraError;", "cause", "", "(Ljava/lang/Throwable;)V", "react-native-vision-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: CameraError.kt */
public final class UnknownCameraError extends CameraError {
    /* JADX WARNING: Code restructure failed: missing block: B:1:0x0002, code lost:
        r0 = r3.getMessage();
     */
    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public UnknownCameraError(java.lang.Throwable r3) {
        /*
            r2 = this;
            if (r3 == 0) goto L_0x0008
            java.lang.String r0 = r3.getMessage()
            if (r0 != 0) goto L_0x000a
        L_0x0008:
            java.lang.String r0 = "An unknown camera error occured."
        L_0x000a:
            java.lang.String r1 = "unknown"
            r2.<init>(r1, r1, r0, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mrousavy.camera.core.UnknownCameraError.<init>(java.lang.Throwable):void");
    }
}
