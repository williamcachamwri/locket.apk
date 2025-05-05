package androidx.camera.core.imagecapture;

import androidx.camera.core.SafeCloseImageReaderProxy;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class CaptureNode$$ExternalSyntheticLambda7 implements Runnable {
    public final /* synthetic */ SafeCloseImageReaderProxy f$0;

    public /* synthetic */ CaptureNode$$ExternalSyntheticLambda7(SafeCloseImageReaderProxy safeCloseImageReaderProxy) {
        this.f$0 = safeCloseImageReaderProxy;
    }

    public final void run() {
        CaptureNode.lambda$releaseInputResources$5(this.f$0);
    }
}
