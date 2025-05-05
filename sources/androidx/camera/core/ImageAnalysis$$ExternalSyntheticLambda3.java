package androidx.camera.core;

import androidx.camera.core.impl.SessionConfig;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ImageAnalysis$$ExternalSyntheticLambda3 implements SessionConfig.ErrorListener {
    public final /* synthetic */ ImageAnalysis f$0;

    public /* synthetic */ ImageAnalysis$$ExternalSyntheticLambda3(ImageAnalysis imageAnalysis) {
        this.f$0 = imageAnalysis;
    }

    public final void onError(SessionConfig sessionConfig, SessionConfig.SessionError sessionError) {
        this.f$0.m140lambda$createPipeline$2$androidxcameracoreImageAnalysis(sessionConfig, sessionError);
    }
}
