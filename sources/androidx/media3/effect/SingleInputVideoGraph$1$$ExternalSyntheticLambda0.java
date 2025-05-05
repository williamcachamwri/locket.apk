package androidx.media3.effect;

import androidx.media3.common.VideoFrameProcessingException;
import androidx.media3.effect.SingleInputVideoGraph;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SingleInputVideoGraph$1$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ SingleInputVideoGraph.AnonymousClass1 f$0;
    public final /* synthetic */ VideoFrameProcessingException f$1;

    public /* synthetic */ SingleInputVideoGraph$1$$ExternalSyntheticLambda0(SingleInputVideoGraph.AnonymousClass1 r1, VideoFrameProcessingException videoFrameProcessingException) {
        this.f$0 = r1;
        this.f$1 = videoFrameProcessingException;
    }

    public final void run() {
        this.f$0.m454lambda$onError$2$androidxmedia3effectSingleInputVideoGraph$1(this.f$1);
    }
}
