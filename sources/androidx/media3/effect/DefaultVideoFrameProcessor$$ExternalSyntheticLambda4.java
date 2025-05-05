package androidx.media3.effect;

import androidx.media3.common.VideoFrameProcessingException;
import androidx.media3.common.VideoFrameProcessor;
import androidx.media3.effect.GlShaderProgram;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DefaultVideoFrameProcessor$$ExternalSyntheticLambda4 implements GlShaderProgram.ErrorListener {
    public final /* synthetic */ VideoFrameProcessor.Listener f$0;

    public /* synthetic */ DefaultVideoFrameProcessor$$ExternalSyntheticLambda4(VideoFrameProcessor.Listener listener) {
        this.f$0 = listener;
    }

    public final void onError(VideoFrameProcessingException videoFrameProcessingException) {
        this.f$0.onError(videoFrameProcessingException);
    }
}
