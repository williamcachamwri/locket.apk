package androidx.camera.core.imagecapture;

import androidx.camera.core.imagecapture.TakePictureManager;
import androidx.core.util.Consumer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class CaptureNode$$ExternalSyntheticLambda3 implements Consumer {
    public final /* synthetic */ CaptureNode f$0;

    public /* synthetic */ CaptureNode$$ExternalSyntheticLambda3(CaptureNode captureNode) {
        this.f$0 = captureNode;
    }

    public final void accept(Object obj) {
        this.f$0.sendCaptureError((TakePictureManager.CaptureError) obj);
    }
}
