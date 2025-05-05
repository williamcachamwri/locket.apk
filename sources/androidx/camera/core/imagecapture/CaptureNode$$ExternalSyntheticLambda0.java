package androidx.camera.core.imagecapture;

import androidx.core.util.Consumer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class CaptureNode$$ExternalSyntheticLambda0 implements Consumer {
    public final /* synthetic */ CaptureNode f$0;

    public /* synthetic */ CaptureNode$$ExternalSyntheticLambda0(CaptureNode captureNode) {
        this.f$0 = captureNode;
    }

    public final void accept(Object obj) {
        this.f$0.onRequestAvailable((ProcessingRequest) obj);
    }
}
