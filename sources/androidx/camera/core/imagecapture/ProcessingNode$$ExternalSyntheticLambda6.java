package androidx.camera.core.imagecapture;

import androidx.camera.core.ImageProxy;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ProcessingNode$$ExternalSyntheticLambda6 implements Runnable {
    public final /* synthetic */ ProcessingRequest f$0;
    public final /* synthetic */ ImageProxy f$1;

    public /* synthetic */ ProcessingNode$$ExternalSyntheticLambda6(ProcessingRequest processingRequest, ImageProxy imageProxy) {
        this.f$0 = processingRequest;
        this.f$1 = imageProxy;
    }

    public final void run() {
        this.f$0.onFinalResult(this.f$1);
    }
}
