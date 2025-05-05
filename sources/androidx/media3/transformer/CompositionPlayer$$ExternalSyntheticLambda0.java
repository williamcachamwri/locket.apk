package androidx.media3.transformer;

import androidx.media3.common.VideoFrameProcessingException;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class CompositionPlayer$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ CompositionPlayer f$0;
    public final /* synthetic */ VideoFrameProcessingException f$1;

    public /* synthetic */ CompositionPlayer$$ExternalSyntheticLambda0(CompositionPlayer compositionPlayer, VideoFrameProcessingException videoFrameProcessingException) {
        this.f$0 = compositionPlayer;
        this.f$1 = videoFrameProcessingException;
    }

    public final void run() {
        this.f$0.m1119lambda$onError$1$androidxmedia3transformerCompositionPlayer(this.f$1);
    }
}
