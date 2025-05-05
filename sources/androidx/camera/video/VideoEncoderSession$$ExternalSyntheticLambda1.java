package androidx.camera.video;

import androidx.camera.core.SurfaceRequest;
import androidx.core.util.Consumer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class VideoEncoderSession$$ExternalSyntheticLambda1 implements Consumer {
    public final /* synthetic */ VideoEncoderSession f$0;

    public /* synthetic */ VideoEncoderSession$$ExternalSyntheticLambda1(VideoEncoderSession videoEncoderSession) {
        this.f$0 = videoEncoderSession;
    }

    public final void accept(Object obj) {
        this.f$0.onSurfaceRequestComplete((SurfaceRequest.Result) obj);
    }
}
