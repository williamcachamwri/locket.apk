package androidx.camera.extensions.internal.sessionprocessor;

import androidx.camera.core.impl.SessionProcessor;
import androidx.camera.core.impl.TagBundle;
import androidx.camera.extensions.internal.sessionprocessor.PreviewProcessor;
import java.util.List;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class BasicExtenderSessionProcessor$$ExternalSyntheticLambda0 implements PreviewProcessor.OnCaptureResultCallback {
    public final /* synthetic */ BasicExtenderSessionProcessor f$0;
    public final /* synthetic */ SessionProcessor.CaptureCallback f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ TagBundle f$3;

    public /* synthetic */ BasicExtenderSessionProcessor$$ExternalSyntheticLambda0(BasicExtenderSessionProcessor basicExtenderSessionProcessor, SessionProcessor.CaptureCallback captureCallback, int i, TagBundle tagBundle) {
        this.f$0 = basicExtenderSessionProcessor;
        this.f$1 = captureCallback;
        this.f$2 = i;
        this.f$3 = tagBundle;
    }

    public final void onCaptureResult(long j, List list) {
        this.f$0.m230lambda$startRepeating$0$androidxcameraextensionsinternalsessionprocessorBasicExtenderSessionProcessor(this.f$1, this.f$2, this.f$3, j, list);
    }
}
