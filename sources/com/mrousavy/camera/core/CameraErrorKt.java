package com.mrousavy.camera.core;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0002¨\u0006\u0004"}, d2 = {"getVideoCapturedMessage", "", "wasVideoCaptured", "", "react-native-vision-camera_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: CameraError.kt */
public final class CameraErrorKt {
    /* access modifiers changed from: private */
    public static final String getVideoCapturedMessage(boolean z) {
        return z ? "The output file was generated, so the recording may be valid." : "The output file was generated but the recording will not be valid, so you should delete the file.";
    }
}
