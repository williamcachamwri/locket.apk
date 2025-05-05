package androidx.camera.video;

import androidx.camera.core.impl.SessionConfig;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class VideoCapture$$ExternalSyntheticLambda7 implements SessionConfig.ErrorListener {
    public final /* synthetic */ VideoCapture f$0;

    public /* synthetic */ VideoCapture$$ExternalSyntheticLambda7(VideoCapture videoCapture) {
        this.f$0 = videoCapture;
    }

    public final void onError(SessionConfig sessionConfig, SessionConfig.SessionError sessionError) {
        this.f$0.m257lambda$createPipeline$3$androidxcameravideoVideoCapture(sessionConfig, sessionError);
    }
}
