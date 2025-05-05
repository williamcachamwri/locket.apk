package androidx.camera.core.streamsharing;

import androidx.camera.core.impl.SessionConfig;
import androidx.camera.core.impl.StreamSpec;
import androidx.camera.core.impl.UseCaseConfig;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class StreamSharing$$ExternalSyntheticLambda3 implements SessionConfig.ErrorListener {
    public final /* synthetic */ StreamSharing f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ UseCaseConfig f$3;
    public final /* synthetic */ StreamSpec f$4;
    public final /* synthetic */ StreamSpec f$5;

    public /* synthetic */ StreamSharing$$ExternalSyntheticLambda3(StreamSharing streamSharing, String str, String str2, UseCaseConfig useCaseConfig, StreamSpec streamSpec, StreamSpec streamSpec2) {
        this.f$0 = streamSharing;
        this.f$1 = str;
        this.f$2 = str2;
        this.f$3 = useCaseConfig;
        this.f$4 = streamSpec;
        this.f$5 = streamSpec2;
    }

    public final void onError(SessionConfig sessionConfig, SessionConfig.SessionError sessionError) {
        this.f$0.m224lambda$addCameraErrorListener$1$androidxcameracorestreamsharingStreamSharing(this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, sessionConfig, sessionError);
    }
}
