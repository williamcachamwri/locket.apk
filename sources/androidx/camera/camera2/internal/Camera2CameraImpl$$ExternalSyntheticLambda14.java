package androidx.camera.camera2.internal;

import androidx.camera.core.impl.DeferrableSurface;
import androidx.camera.core.impl.utils.futures.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Camera2CameraImpl$$ExternalSyntheticLambda14 implements AsyncFunction {
    public final /* synthetic */ CaptureSession f$0;
    public final /* synthetic */ DeferrableSurface f$1;

    public /* synthetic */ Camera2CameraImpl$$ExternalSyntheticLambda14(CaptureSession captureSession, DeferrableSurface deferrableSurface) {
        this.f$0 = captureSession;
        this.f$1 = deferrableSurface;
    }

    public final ListenableFuture apply(Object obj) {
        return Camera2CameraImpl.lambda$configAndClose$3(this.f$0, this.f$1, (Void) obj);
    }
}
