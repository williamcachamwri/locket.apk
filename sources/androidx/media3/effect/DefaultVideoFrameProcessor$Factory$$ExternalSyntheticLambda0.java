package androidx.media3.effect;

import androidx.media3.common.VideoFrameProcessingException;
import androidx.media3.common.VideoFrameProcessor;
import androidx.media3.effect.VideoFrameProcessingTaskExecutor;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DefaultVideoFrameProcessor$Factory$$ExternalSyntheticLambda0 implements VideoFrameProcessingTaskExecutor.ErrorListener {
    public final /* synthetic */ VideoFrameProcessor.Listener f$0;

    public /* synthetic */ DefaultVideoFrameProcessor$Factory$$ExternalSyntheticLambda0(VideoFrameProcessor.Listener listener) {
        this.f$0 = listener;
    }

    public final void onError(VideoFrameProcessingException videoFrameProcessingException) {
        this.f$0.onError(videoFrameProcessingException);
    }
}
