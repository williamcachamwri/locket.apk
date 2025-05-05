package androidx.camera.core.impl;

import android.content.Context;
import androidx.camera.core.CameraInfo;

public interface CameraConfigProvider {
    public static final CameraConfigProvider EMPTY = new CameraConfigProvider$$ExternalSyntheticLambda0();

    static /* synthetic */ CameraConfig lambda$static$0(CameraInfo cameraInfo, Context context) {
        return null;
    }

    CameraConfig getConfig(CameraInfo cameraInfo, Context context);
}
