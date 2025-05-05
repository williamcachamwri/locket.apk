package androidx.media3.effect;

import androidx.media3.common.VideoFrameProcessor;
import androidx.media3.effect.FinalShaderProgramWrapper;
import java.util.concurrent.Executor;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DefaultVideoFrameProcessor$$ExternalSyntheticLambda5 implements FinalShaderProgramWrapper.OnInputStreamProcessedListener {
    public final /* synthetic */ DefaultVideoFrameProcessor f$0;
    public final /* synthetic */ Executor f$1;
    public final /* synthetic */ VideoFrameProcessor.Listener f$2;
    public final /* synthetic */ VideoFrameProcessingTaskExecutor f$3;

    public /* synthetic */ DefaultVideoFrameProcessor$$ExternalSyntheticLambda5(DefaultVideoFrameProcessor defaultVideoFrameProcessor, Executor executor, VideoFrameProcessor.Listener listener, VideoFrameProcessingTaskExecutor videoFrameProcessingTaskExecutor) {
        this.f$0 = defaultVideoFrameProcessor;
        this.f$1 = executor;
        this.f$2 = listener;
        this.f$3 = videoFrameProcessingTaskExecutor;
    }

    public final void onInputStreamProcessed() {
        this.f$0.m419lambda$new$1$androidxmedia3effectDefaultVideoFrameProcessor(this.f$1, this.f$2, this.f$3);
    }
}
