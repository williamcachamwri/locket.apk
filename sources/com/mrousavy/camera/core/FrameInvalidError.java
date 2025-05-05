package com.mrousavy.camera.core;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/mrousavy/camera/core/FrameInvalidError;", "Lcom/mrousavy/camera/core/CameraError;", "()V", "react-native-vision-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: CameraError.kt */
public final class FrameInvalidError extends CameraError {
    public FrameInvalidError() {
        super("capture", "frame-invalid", "Trying to access an already closed Frame! Are you trying to access the Image data outside of a Frame Processor's lifetime?\n- If you want to use `console.log(frame)`, use `console.log(frame.toString())` instead.\n- If you want to do async processing, use `runAsync(...)` instead.\n- If you want to use runOnJS, increment it's ref-count: `frame.incrementRefCount()`", (Throwable) null, 8, (DefaultConstructorMarker) null);
    }
}
