package androidx.media3.transformer;

import android.content.Context;
import androidx.media3.common.ColorInfo;
import androidx.media3.common.DebugViewProvider;
import androidx.media3.common.Effect;
import androidx.media3.common.VideoFrameProcessingException;
import androidx.media3.common.VideoGraph;
import androidx.media3.effect.VideoCompositorSettings;
import java.util.List;
import java.util.concurrent.Executor;

interface TransformerVideoGraph extends VideoGraph {

    public interface Factory {
        TransformerVideoGraph create(Context context, ColorInfo colorInfo, DebugViewProvider debugViewProvider, VideoGraph.Listener listener, Executor executor, VideoCompositorSettings videoCompositorSettings, List<Effect> list, long j, boolean z) throws VideoFrameProcessingException;
    }

    GraphInput createInput(int i) throws VideoFrameProcessingException;

    void renderOutputFrameWithMediaPresentationTime();
}
