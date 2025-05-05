package androidx.camera.core.imagecapture;

import androidx.camera.core.ImageCaptureException;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ProcessingNode$$ExternalSyntheticLambda3 implements Runnable {
    public final /* synthetic */ ProcessingRequest f$0;
    public final /* synthetic */ ImageCaptureException f$1;

    public /* synthetic */ ProcessingNode$$ExternalSyntheticLambda3(ProcessingRequest processingRequest, ImageCaptureException imageCaptureException) {
        this.f$0 = processingRequest;
        this.f$1 = imageCaptureException;
    }

    public final void run() {
        this.f$0.onProcessFailure(this.f$1);
    }
}
