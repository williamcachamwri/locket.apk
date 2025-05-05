package androidx.media3.effect;

import androidx.media3.common.VideoFrameProcessingException;
import androidx.media3.effect.VideoCompositor;
import androidx.media3.effect.VideoFrameProcessingTaskExecutor;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DefaultVideoCompositor$$ExternalSyntheticLambda4 implements VideoFrameProcessingTaskExecutor.ErrorListener {
    public final /* synthetic */ VideoCompositor.Listener f$0;

    public /* synthetic */ DefaultVideoCompositor$$ExternalSyntheticLambda4(VideoCompositor.Listener listener) {
        this.f$0 = listener;
    }

    public final void onError(VideoFrameProcessingException videoFrameProcessingException) {
        this.f$0.onError(videoFrameProcessingException);
    }
}
