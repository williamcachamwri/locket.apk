package androidx.camera.extensions.internal.sessionprocessor;

import androidx.camera.extensions.internal.sessionprocessor.StillCaptureProcessor;
import java.util.HashMap;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class StillCaptureProcessor$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ StillCaptureProcessor f$0;
    public final /* synthetic */ boolean f$1;
    public final /* synthetic */ HashMap f$2;
    public final /* synthetic */ StillCaptureProcessor.OnCaptureResultCallback f$3;

    public /* synthetic */ StillCaptureProcessor$$ExternalSyntheticLambda1(StillCaptureProcessor stillCaptureProcessor, boolean z, HashMap hashMap, StillCaptureProcessor.OnCaptureResultCallback onCaptureResultCallback) {
        this.f$0 = stillCaptureProcessor;
        this.f$1 = z;
        this.f$2 = hashMap;
        this.f$3 = onCaptureResultCallback;
    }

    public final void run() {
        this.f$0.m232lambda$process$1$androidxcameraextensionsinternalsessionprocessorStillCaptureProcessor(this.f$1, this.f$2, this.f$3);
    }
}
