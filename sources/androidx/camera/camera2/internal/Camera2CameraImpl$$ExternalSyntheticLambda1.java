package androidx.camera.camera2.internal;

import androidx.camera.core.impl.SessionConfig;
import androidx.camera.core.impl.StreamSpec;
import androidx.camera.core.impl.UseCaseConfig;
import java.util.List;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Camera2CameraImpl$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ Camera2CameraImpl f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ SessionConfig f$2;
    public final /* synthetic */ UseCaseConfig f$3;
    public final /* synthetic */ StreamSpec f$4;
    public final /* synthetic */ List f$5;

    public /* synthetic */ Camera2CameraImpl$$ExternalSyntheticLambda1(Camera2CameraImpl camera2CameraImpl, String str, SessionConfig sessionConfig, UseCaseConfig useCaseConfig, StreamSpec streamSpec, List list) {
        this.f$0 = camera2CameraImpl;
        this.f$1 = str;
        this.f$2 = sessionConfig;
        this.f$3 = useCaseConfig;
        this.f$4 = streamSpec;
        this.f$5 = list;
    }

    public final void run() {
        this.f$0.m29lambda$onUseCaseActive$7$androidxcameracamera2internalCamera2CameraImpl(this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}
