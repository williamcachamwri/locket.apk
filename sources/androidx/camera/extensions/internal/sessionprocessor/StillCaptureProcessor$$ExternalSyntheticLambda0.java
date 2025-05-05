package androidx.camera.extensions.internal.sessionprocessor;

import android.hardware.camera2.TotalCaptureResult;
import androidx.camera.extensions.internal.sessionprocessor.CaptureResultImageMatcher;
import androidx.camera.extensions.internal.sessionprocessor.StillCaptureProcessor;
import java.util.List;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class StillCaptureProcessor$$ExternalSyntheticLambda0 implements CaptureResultImageMatcher.ImageReferenceListener {
    public final /* synthetic */ StillCaptureProcessor f$0;
    public final /* synthetic */ List f$1;
    public final /* synthetic */ StillCaptureProcessor.OnCaptureResultCallback f$2;
    public final /* synthetic */ boolean f$3;

    public /* synthetic */ StillCaptureProcessor$$ExternalSyntheticLambda0(StillCaptureProcessor stillCaptureProcessor, List list, StillCaptureProcessor.OnCaptureResultCallback onCaptureResultCallback, boolean z) {
        this.f$0 = stillCaptureProcessor;
        this.f$1 = list;
        this.f$2 = onCaptureResultCallback;
        this.f$3 = z;
    }

    public final void onImageReferenceIncoming(ImageReference imageReference, TotalCaptureResult totalCaptureResult, int i) {
        this.f$0.m233lambda$startCapture$0$androidxcameraextensionsinternalsessionprocessorStillCaptureProcessor(this.f$1, this.f$2, this.f$3, imageReference, totalCaptureResult, i);
    }
}
