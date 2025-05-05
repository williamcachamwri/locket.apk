package androidx.camera.camera2.internal;

import android.graphics.SurfaceTexture;
import android.view.Surface;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Camera2CameraImpl$$ExternalSyntheticLambda11 implements Runnable {
    public final /* synthetic */ Surface f$0;
    public final /* synthetic */ SurfaceTexture f$1;

    public /* synthetic */ Camera2CameraImpl$$ExternalSyntheticLambda11(Surface surface, SurfaceTexture surfaceTexture) {
        this.f$0 = surface;
        this.f$1 = surfaceTexture;
    }

    public final void run() {
        Camera2CameraImpl.lambda$configAndClose$2(this.f$0, this.f$1);
    }
}
