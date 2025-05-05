package androidx.media3.transformer;

import android.content.Context;
import androidx.media3.common.ColorInfo;
import androidx.media3.common.DebugViewProvider;
import androidx.media3.common.Effect;
import androidx.media3.common.VideoFrameProcessingException;
import androidx.media3.common.VideoFrameProcessor;
import androidx.media3.common.VideoGraph;
import androidx.media3.effect.MultipleInputVideoGraph;
import androidx.media3.effect.VideoCompositorSettings;
import androidx.media3.transformer.TransformerVideoGraph;
import com.google.common.collect.ImmutableList;
import java.util.List;
import java.util.concurrent.Executor;

final class TransformerMultipleInputVideoGraph extends MultipleInputVideoGraph implements TransformerVideoGraph {

    public static final class Factory implements TransformerVideoGraph.Factory {
        private final VideoFrameProcessor.Factory videoFrameProcessorFactory;

        public Factory(VideoFrameProcessor.Factory factory) {
            this.videoFrameProcessorFactory = factory;
        }

        public TransformerMultipleInputVideoGraph create(Context context, ColorInfo colorInfo, DebugViewProvider debugViewProvider, VideoGraph.Listener listener, Executor executor, VideoCompositorSettings videoCompositorSettings, List<Effect> list, long j, boolean z) {
            return new TransformerMultipleInputVideoGraph(context, this.videoFrameProcessorFactory, colorInfo, debugViewProvider, listener, executor, videoCompositorSettings, list, j, z);
        }
    }

    private TransformerMultipleInputVideoGraph(Context context, VideoFrameProcessor.Factory factory, ColorInfo colorInfo, DebugViewProvider debugViewProvider, VideoGraph.Listener listener, Executor executor, VideoCompositorSettings videoCompositorSettings, List<Effect> list, long j, boolean z) {
        super(context, factory, colorInfo, debugViewProvider, listener, executor, videoCompositorSettings, list, j, z);
    }

    public GraphInput createInput(int i) throws VideoFrameProcessingException {
        registerInput(i);
        return new VideoFrameProcessingWrapper(getProcessor(i), ImmutableList.of(), getInitialTimestampOffsetUs());
    }

    public void renderOutputFrameWithMediaPresentationTime() {
        getCompositionVideoFrameProcessor().renderOutputFrame(-3);
    }
}
