package com.mrousavy.camera.core;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/mrousavy/camera/core/DoNotDisturbBugError;", "Lcom/mrousavy/camera/core/CameraError;", "cause", "", "(Ljava/lang/Throwable;)V", "react-native-vision-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: CameraError.kt */
public final class DoNotDisturbBugError extends CameraError {
    public DoNotDisturbBugError(Throwable th) {
        super("system", "do-not-disturb-bug", "The Camera Device could not be opened because of a bug in Android 9 (API 28) when do-not-disturb mode is enabled! Either update your Android version, or disable do-not-disturb.", th);
    }
}
