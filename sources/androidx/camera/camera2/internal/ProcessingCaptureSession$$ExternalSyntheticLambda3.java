package androidx.camera.camera2.internal;

import android.hardware.camera2.CameraDevice;
import androidx.camera.camera2.internal.SynchronizedCaptureSession;
import androidx.camera.core.impl.SessionConfig;
import androidx.camera.core.impl.utils.futures.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.List;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ProcessingCaptureSession$$ExternalSyntheticLambda3 implements AsyncFunction {
    public final /* synthetic */ ProcessingCaptureSession f$0;
    public final /* synthetic */ SessionConfig f$1;
    public final /* synthetic */ CameraDevice f$2;
    public final /* synthetic */ SynchronizedCaptureSession.Opener f$3;

    public /* synthetic */ ProcessingCaptureSession$$ExternalSyntheticLambda3(ProcessingCaptureSession processingCaptureSession, SessionConfig sessionConfig, CameraDevice cameraDevice, SynchronizedCaptureSession.Opener opener) {
        this.f$0 = processingCaptureSession;
        this.f$1 = sessionConfig;
        this.f$2 = cameraDevice;
        this.f$3 = opener;
    }

    public final ListenableFuture apply(Object obj) {
        return this.f$0.m83lambda$open$2$androidxcameracamera2internalProcessingCaptureSession(this.f$1, this.f$2, this.f$3, (List) obj);
    }
}
