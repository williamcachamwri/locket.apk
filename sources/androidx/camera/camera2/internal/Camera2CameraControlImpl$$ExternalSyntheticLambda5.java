package androidx.camera.camera2.internal;

import androidx.camera.core.impl.utils.futures.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Camera2CameraControlImpl$$ExternalSyntheticLambda5 implements AsyncFunction {
    public final /* synthetic */ Camera2CameraControlImpl f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ int f$3;

    public /* synthetic */ Camera2CameraControlImpl$$ExternalSyntheticLambda5(Camera2CameraControlImpl camera2CameraControlImpl, int i, int i2, int i3) {
        this.f$0 = camera2CameraControlImpl;
        this.f$1 = i;
        this.f$2 = i2;
        this.f$3 = i3;
    }

    public final ListenableFuture apply(Object obj) {
        return this.f$0.m12lambda$getCameraCapturePipelineAsync$5$androidxcameracamera2internalCamera2CameraControlImpl(this.f$1, this.f$2, this.f$3, (Void) obj);
    }
}
