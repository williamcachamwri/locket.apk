package androidx.camera.video;

import androidx.camera.core.impl.SessionConfig;
import androidx.concurrent.futures.CallbackToFutureAdapter;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class VideoCapture$$ExternalSyntheticLambda1 implements CallbackToFutureAdapter.Resolver {
    public final /* synthetic */ VideoCapture f$0;
    public final /* synthetic */ SessionConfig.Builder f$1;

    public /* synthetic */ VideoCapture$$ExternalSyntheticLambda1(VideoCapture videoCapture, SessionConfig.Builder builder) {
        this.f$0 = videoCapture;
        this.f$1 = builder;
    }

    public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
        return this.f$0.m258lambda$setupSurfaceUpdateNotifier$6$androidxcameravideoVideoCapture(this.f$1, completer);
    }
}
