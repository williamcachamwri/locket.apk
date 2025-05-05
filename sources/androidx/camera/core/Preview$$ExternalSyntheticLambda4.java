package androidx.camera.core;

import androidx.camera.core.impl.SessionConfig;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Preview$$ExternalSyntheticLambda4 implements SessionConfig.ErrorListener {
    public final /* synthetic */ Preview f$0;

    public /* synthetic */ Preview$$ExternalSyntheticLambda4(Preview preview) {
        this.f$0 = preview;
    }

    public final void onError(SessionConfig sessionConfig, SessionConfig.SessionError sessionError) {
        this.f$0.m150lambda$addCameraSurfaceAndErrorListener$1$androidxcameracorePreview(sessionConfig, sessionError);
    }
}
