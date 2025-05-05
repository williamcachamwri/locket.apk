package androidx.camera.video;

import android.view.Surface;
import androidx.camera.core.SurfaceRequest;
import androidx.camera.video.internal.encoder.Encoder;
import androidx.concurrent.futures.CallbackToFutureAdapter;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class VideoEncoderSession$$ExternalSyntheticLambda3 implements Encoder.SurfaceInput.OnSurfaceUpdateListener {
    public final /* synthetic */ VideoEncoderSession f$0;
    public final /* synthetic */ CallbackToFutureAdapter.Completer f$1;
    public final /* synthetic */ SurfaceRequest f$2;

    public /* synthetic */ VideoEncoderSession$$ExternalSyntheticLambda3(VideoEncoderSession videoEncoderSession, CallbackToFutureAdapter.Completer completer, SurfaceRequest surfaceRequest) {
        this.f$0 = videoEncoderSession;
        this.f$1 = completer;
        this.f$2 = surfaceRequest;
    }

    public final void onSurfaceUpdate(Surface surface) {
        this.f$0.m264lambda$configureVideoEncoderInternal$5$androidxcameravideoVideoEncoderSession(this.f$1, this.f$2, surface);
    }
}
