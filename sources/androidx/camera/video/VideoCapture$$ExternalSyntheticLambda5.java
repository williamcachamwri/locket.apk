package androidx.camera.video;

import androidx.camera.core.impl.CameraInternal;
import androidx.camera.core.impl.Timebase;
import androidx.camera.core.processing.SurfaceEdge;
import androidx.camera.video.impl.VideoCaptureConfig;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class VideoCapture$$ExternalSyntheticLambda5 implements Runnable {
    public final /* synthetic */ VideoCapture f$0;
    public final /* synthetic */ SurfaceEdge f$1;
    public final /* synthetic */ CameraInternal f$2;
    public final /* synthetic */ VideoCaptureConfig f$3;
    public final /* synthetic */ Timebase f$4;

    public /* synthetic */ VideoCapture$$ExternalSyntheticLambda5(VideoCapture videoCapture, SurfaceEdge surfaceEdge, CameraInternal cameraInternal, VideoCaptureConfig videoCaptureConfig, Timebase timebase) {
        this.f$0 = videoCapture;
        this.f$1 = surfaceEdge;
        this.f$2 = cameraInternal;
        this.f$3 = videoCaptureConfig;
        this.f$4 = timebase;
    }

    public final void run() {
        this.f$0.m255lambda$createPipeline$1$androidxcameravideoVideoCapture(this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
