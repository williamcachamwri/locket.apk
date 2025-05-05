package androidx.media3.effect;

import androidx.media3.common.VideoFrameProcessor;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DefaultVideoFrameProcessor$$ExternalSyntheticLambda6 implements Runnable {
    public final /* synthetic */ VideoFrameProcessor.Listener f$0;

    public /* synthetic */ DefaultVideoFrameProcessor$$ExternalSyntheticLambda6(VideoFrameProcessor.Listener listener) {
        this.f$0 = listener;
    }

    public final void run() {
        this.f$0.onEnded();
    }
}
