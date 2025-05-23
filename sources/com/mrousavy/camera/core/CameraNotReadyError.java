package com.mrousavy.camera.core;

import io.sentry.cache.EnvelopeCache;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/mrousavy/camera/core/CameraNotReadyError;", "Lcom/mrousavy/camera/core/CameraError;", "()V", "react-native-vision-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: CameraError.kt */
public final class CameraNotReadyError extends CameraError {
    public CameraNotReadyError() {
        super(EnvelopeCache.PREFIX_CURRENT_SESSION_FILE, "camera-not-ready", "The Camera is not ready yet! Wait for the onInitialized() callback!", (Throwable) null, 8, (DefaultConstructorMarker) null);
    }
}
