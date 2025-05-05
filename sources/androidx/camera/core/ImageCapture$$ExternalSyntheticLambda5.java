package androidx.camera.core;

import androidx.camera.core.impl.SessionConfig;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ImageCapture$$ExternalSyntheticLambda5 implements SessionConfig.ErrorListener {
    public final /* synthetic */ ImageCapture f$0;

    public /* synthetic */ ImageCapture$$ExternalSyntheticLambda5(ImageCapture imageCapture) {
        this.f$0 = imageCapture;
    }

    public final void onError(SessionConfig sessionConfig, SessionConfig.SessionError sessionError) {
        this.f$0.m144lambda$createPipeline$4$androidxcameracoreImageCapture(sessionConfig, sessionError);
    }
}
