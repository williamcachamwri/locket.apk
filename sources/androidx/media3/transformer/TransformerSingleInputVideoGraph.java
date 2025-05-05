package androidx.media3.transformer;

import android.content.Context;
import androidx.media3.common.ColorInfo;
import androidx.media3.common.DebugViewProvider;
import androidx.media3.common.Effect;
import androidx.media3.common.VideoFrameProcessingException;
import androidx.media3.common.VideoFrameProcessor;
import androidx.media3.common.VideoGraph;
import androidx.media3.common.util.Assertions;
import androidx.media3.effect.SingleInputVideoGraph;
import androidx.media3.effect.VideoCompositorSettings;
import androidx.media3.transformer.TransformerVideoGraph;
import java.util.List;
import java.util.concurrent.Executor;

final class TransformerSingleInputVideoGraph extends SingleInputVideoGraph implements TransformerVideoGraph {
    private final List<Effect> compositionEffects;
    private VideoFrameProcessingWrapper videoFrameProcessingWrapper;

    public static final class Factory implements TransformerVideoGraph.Factory {
        private final VideoFrameProcessor.Factory videoFrameProcessorFactory;

        public Factory(VideoFrameProcessor.Factory factory) {
            this.videoFrameProcessorFactory = factory;
        }

        public TransformerSingleInputVideoGraph create(Context context, ColorInfo colorInfo, DebugViewProvider debugViewProvider, VideoGraph.Listener listener, Executor executor, VideoCompositorSettings videoCompositorSettings, List<Effect> list, long j, boolean z) {
            return new TransformerSingleInputVideoGraph(context, this.videoFrameProcessorFactory, colorInfo, listener, debugViewProvider, executor, videoCompositorSettings, z, list, j);
        }
    }

    private TransformerSingleInputVideoGraph(Context context, VideoFrameProcessor.Factory factory, ColorInfo colorInfo, VideoGraph.Listener listener, DebugViewProvider debugViewProvider, Executor executor, VideoCompositorSettings videoCompositorSettings, boolean z, List<Effect> list, long j) {
        super(context, factory, colorInfo, listener, debugViewProvider, executor, videoCompositorSettings, z, j);
        this.compositionEffects = list;
    }

    public GraphInput createInput(int i) throws VideoFrameProcessingException {
        Assertions.checkState(this.videoFrameProcessingWrapper == null);
        registerInput(i);
        VideoFrameProcessingWrapper videoFrameProcessingWrapper2 = new VideoFrameProcessingWrapper(getProcessor(i), this.compositionEffects, getInitialTimestampOffsetUs());
        this.videoFrameProcessingWrapper = videoFrameProcessingWrapper2;
        return videoFrameProcessingWrapper2;
    }

    public void renderOutputFrameWithMediaPresentationTime() {
        getProcessor(getInputIndex()).renderOutputFrame(-3);
    }
}
