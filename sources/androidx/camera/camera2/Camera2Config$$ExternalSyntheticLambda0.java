package androidx.camera.camera2;

import android.content.Context;
import androidx.camera.camera2.internal.Camera2CameraFactory;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.impl.CameraFactory;
import androidx.camera.core.impl.CameraThreadConfig;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Camera2Config$$ExternalSyntheticLambda0 implements CameraFactory.Provider {
    public final CameraFactory newInstance(Context context, CameraThreadConfig cameraThreadConfig, CameraSelector cameraSelector, long j) {
        return new Camera2CameraFactory(context, cameraThreadConfig, cameraSelector, j);
    }
}
