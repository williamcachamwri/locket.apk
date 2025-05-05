package androidx.camera.extensions.internal.sessionprocessor;

import android.hardware.camera2.TotalCaptureResult;
import androidx.camera.extensions.internal.sessionprocessor.CaptureResultImageMatcher;
import androidx.camera.extensions.internal.sessionprocessor.PreviewProcessor;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class PreviewProcessor$$ExternalSyntheticLambda0 implements CaptureResultImageMatcher.ImageReferenceListener {
    public final /* synthetic */ PreviewProcessor f$0;
    public final /* synthetic */ PreviewProcessor.OnCaptureResultCallback f$1;

    public /* synthetic */ PreviewProcessor$$ExternalSyntheticLambda0(PreviewProcessor previewProcessor, PreviewProcessor.OnCaptureResultCallback onCaptureResultCallback) {
        this.f$0 = previewProcessor;
        this.f$1 = onCaptureResultCallback;
    }

    public final void onImageReferenceIncoming(ImageReference imageReference, TotalCaptureResult totalCaptureResult, int i) {
        this.f$0.m231lambda$start$0$androidxcameraextensionsinternalsessionprocessorPreviewProcessor(this.f$1, imageReference, totalCaptureResult, i);
    }
}
