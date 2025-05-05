package androidx.camera.video;

import androidx.camera.core.impl.CameraCaptureCallback;
import androidx.camera.core.impl.SessionConfig;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class VideoCapture$$ExternalSyntheticLambda3 implements Runnable {
    public final /* synthetic */ AtomicBoolean f$0;
    public final /* synthetic */ SessionConfig.Builder f$1;
    public final /* synthetic */ CameraCaptureCallback f$2;

    public /* synthetic */ VideoCapture$$ExternalSyntheticLambda3(AtomicBoolean atomicBoolean, SessionConfig.Builder builder, CameraCaptureCallback cameraCaptureCallback) {
        this.f$0 = atomicBoolean;
        this.f$1 = builder;
        this.f$2 = cameraCaptureCallback;
    }

    public final void run() {
        VideoCapture.lambda$setupSurfaceUpdateNotifier$5(this.f$0, this.f$1, this.f$2);
    }
}
