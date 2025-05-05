package androidx.camera.video;

import android.view.Surface;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class VideoEncoderSession$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ VideoEncoderSession f$0;
    public final /* synthetic */ Surface f$1;

    public /* synthetic */ VideoEncoderSession$$ExternalSyntheticLambda2(VideoEncoderSession videoEncoderSession, Surface surface) {
        this.f$0 = videoEncoderSession;
        this.f$1 = surface;
    }

    public final void run() {
        this.f$0.m263lambda$configureVideoEncoderInternal$4$androidxcameravideoVideoEncoderSession(this.f$1);
    }
}
