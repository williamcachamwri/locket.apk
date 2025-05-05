package androidx.camera.video;

import androidx.camera.core.impl.SessionConfig;
import androidx.camera.video.VideoCapture;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class VideoCapture$2$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ VideoCapture.AnonymousClass2 f$0;
    public final /* synthetic */ SessionConfig.Builder f$1;

    public /* synthetic */ VideoCapture$2$$ExternalSyntheticLambda0(VideoCapture.AnonymousClass2 r1, SessionConfig.Builder builder) {
        this.f$0 = r1;
        this.f$1 = builder;
    }

    public final void run() {
        this.f$0.m259lambda$onCaptureCompleted$0$androidxcameravideoVideoCapture$2(this.f$1);
    }
}
