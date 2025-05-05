package androidx.camera.video;

import androidx.camera.core.SurfaceRequest;
import androidx.camera.core.impl.Timebase;
import androidx.camera.video.internal.VideoValidatedEncoderProfilesProxy;
import androidx.concurrent.futures.CallbackToFutureAdapter;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class VideoEncoderSession$$ExternalSyntheticLambda6 implements CallbackToFutureAdapter.Resolver {
    public final /* synthetic */ VideoEncoderSession f$0;
    public final /* synthetic */ SurfaceRequest f$1;
    public final /* synthetic */ Timebase f$2;
    public final /* synthetic */ VideoValidatedEncoderProfilesProxy f$3;
    public final /* synthetic */ MediaSpec f$4;

    public /* synthetic */ VideoEncoderSession$$ExternalSyntheticLambda6(VideoEncoderSession videoEncoderSession, SurfaceRequest surfaceRequest, Timebase timebase, VideoValidatedEncoderProfilesProxy videoValidatedEncoderProfilesProxy, MediaSpec mediaSpec) {
        this.f$0 = videoEncoderSession;
        this.f$1 = surfaceRequest;
        this.f$2 = timebase;
        this.f$3 = videoValidatedEncoderProfilesProxy;
        this.f$4 = mediaSpec;
    }

    public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
        return this.f$0.m262lambda$configure$2$androidxcameravideoVideoEncoderSession(this.f$1, this.f$2, this.f$3, this.f$4, completer);
    }
}
