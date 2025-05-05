package androidx.media3.effect;

import android.content.Context;
import androidx.media3.common.ColorInfo;
import androidx.media3.common.DebugViewProvider;
import androidx.media3.common.SurfaceInfo;
import androidx.media3.common.VideoFrameProcessingException;
import androidx.media3.common.VideoFrameProcessor;
import androidx.media3.common.VideoGraph;
import androidx.media3.common.util.Assertions;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.concurrent.Executor;

public abstract class SingleInputVideoGraph implements VideoGraph {
    private final Context context;
    private final DebugViewProvider debugViewProvider;
    /* access modifiers changed from: private */
    public volatile boolean hasProducedFrameWithTimestampZero;
    private final long initialTimestampOffsetUs;
    private int inputIndex = -1;
    /* access modifiers changed from: private */
    public boolean isEnded;
    /* access modifiers changed from: private */
    public final VideoGraph.Listener listener;
    /* access modifiers changed from: private */
    public final Executor listenerExecutor;
    private final ColorInfo outputColorInfo;
    private SurfaceInfo outputSurfaceInfo;
    private boolean released;
    private final boolean renderFramesAutomatically;
    private VideoFrameProcessor videoFrameProcessor;
    private final VideoFrameProcessor.Factory videoFrameProcessorFactory;

    public void initialize() {
    }

    public SingleInputVideoGraph(Context context2, VideoFrameProcessor.Factory factory, ColorInfo colorInfo, VideoGraph.Listener listener2, DebugViewProvider debugViewProvider2, Executor executor, VideoCompositorSettings videoCompositorSettings, boolean z, long j) {
        Assertions.checkState(VideoCompositorSettings.DEFAULT.equals(videoCompositorSettings), "SingleInputVideoGraph does not use VideoCompositor, and therefore cannot apply VideoCompositorSettings");
        this.context = context2;
        this.videoFrameProcessorFactory = factory;
        this.outputColorInfo = colorInfo;
        this.listener = listener2;
        this.debugViewProvider = debugViewProvider2;
        this.listenerExecutor = executor;
        this.renderFramesAutomatically = z;
        this.initialTimestampOffsetUs = j;
    }

    public void registerInput(int i) throws VideoFrameProcessingException {
        boolean z = true;
        Assertions.checkStateNotNull(Boolean.valueOf(this.videoFrameProcessor == null && !this.released));
        if (this.inputIndex != -1) {
            z = false;
        }
        Assertions.checkState(z);
        this.inputIndex = i;
        VideoFrameProcessor create = this.videoFrameProcessorFactory.create(this.context, this.debugViewProvider, this.outputColorInfo, this.renderFramesAutomatically, MoreExecutors.directExecutor(), new VideoFrameProcessor.Listener() {
            private long lastProcessedFramePresentationTimeUs;

            /* access modifiers changed from: package-private */
            /* renamed from: lambda$onOutputSizeChanged$0$androidx-media3-effect-SingleInputVideoGraph$1  reason: not valid java name */
            public /* synthetic */ void m456lambda$onOutputSizeChanged$0$androidxmedia3effectSingleInputVideoGraph$1(int i, int i2) {
                SingleInputVideoGraph.this.listener.onOutputSizeChanged(i, i2);
            }

            public void onOutputSizeChanged(int i, int i2) {
                SingleInputVideoGraph.this.listenerExecutor.execute(new SingleInputVideoGraph$1$$ExternalSyntheticLambda1(this, i, i2));
            }

            public void onOutputFrameAvailableForRendering(long j) {
                if (SingleInputVideoGraph.this.isEnded) {
                    onError(new VideoFrameProcessingException("onOutputFrameAvailableForRendering() received after onEnded()"));
                    return;
                }
                if (j == 0) {
                    boolean unused = SingleInputVideoGraph.this.hasProducedFrameWithTimestampZero = true;
                }
                this.lastProcessedFramePresentationTimeUs = j;
                SingleInputVideoGraph.this.listenerExecutor.execute(new SingleInputVideoGraph$1$$ExternalSyntheticLambda2(this, j));
            }

            /* access modifiers changed from: package-private */
            /* renamed from: lambda$onOutputFrameAvailableForRendering$1$androidx-media3-effect-SingleInputVideoGraph$1  reason: not valid java name */
            public /* synthetic */ void m455lambda$onOutputFrameAvailableForRendering$1$androidxmedia3effectSingleInputVideoGraph$1(long j) {
                SingleInputVideoGraph.this.listener.onOutputFrameAvailableForRendering(j);
            }

            /* access modifiers changed from: package-private */
            /* renamed from: lambda$onError$2$androidx-media3-effect-SingleInputVideoGraph$1  reason: not valid java name */
            public /* synthetic */ void m454lambda$onError$2$androidxmedia3effectSingleInputVideoGraph$1(VideoFrameProcessingException videoFrameProcessingException) {
                SingleInputVideoGraph.this.listener.onError(videoFrameProcessingException);
            }

            public void onError(VideoFrameProcessingException videoFrameProcessingException) {
                SingleInputVideoGraph.this.listenerExecutor.execute(new SingleInputVideoGraph$1$$ExternalSyntheticLambda0(this, videoFrameProcessingException));
            }

            public void onEnded() {
                if (SingleInputVideoGraph.this.isEnded) {
                    onError(new VideoFrameProcessingException("onEnded() received multiple times"));
                    return;
                }
                boolean unused = SingleInputVideoGraph.this.isEnded = true;
                SingleInputVideoGraph.this.listenerExecutor.execute(new SingleInputVideoGraph$1$$ExternalSyntheticLambda3(this));
            }

            /* access modifiers changed from: package-private */
            /* renamed from: lambda$onEnded$3$androidx-media3-effect-SingleInputVideoGraph$1  reason: not valid java name */
            public /* synthetic */ void m453lambda$onEnded$3$androidxmedia3effectSingleInputVideoGraph$1() {
                SingleInputVideoGraph.this.listener.onEnded(this.lastProcessedFramePresentationTimeUs);
            }
        });
        this.videoFrameProcessor = create;
        SurfaceInfo surfaceInfo = this.outputSurfaceInfo;
        if (surfaceInfo != null) {
            create.setOutputSurfaceInfo(surfaceInfo);
        }
    }

    public VideoFrameProcessor getProcessor(int i) {
        int i2 = this.inputIndex;
        Assertions.checkArgument(i2 != -1 && i2 == i);
        return (VideoFrameProcessor) Assertions.checkStateNotNull(this.videoFrameProcessor);
    }

    public void setOutputSurfaceInfo(SurfaceInfo surfaceInfo) {
        this.outputSurfaceInfo = surfaceInfo;
        VideoFrameProcessor videoFrameProcessor2 = this.videoFrameProcessor;
        if (videoFrameProcessor2 != null) {
            videoFrameProcessor2.setOutputSurfaceInfo(surfaceInfo);
        }
    }

    public boolean hasProducedFrameWithTimestampZero() {
        return this.hasProducedFrameWithTimestampZero;
    }

    public void release() {
        if (!this.released) {
            VideoFrameProcessor videoFrameProcessor2 = this.videoFrameProcessor;
            if (videoFrameProcessor2 != null) {
                videoFrameProcessor2.release();
                this.videoFrameProcessor = null;
            }
            this.released = true;
        }
    }

    /* access modifiers changed from: protected */
    public int getInputIndex() {
        return this.inputIndex;
    }

    /* access modifiers changed from: protected */
    public long getInitialTimestampOffsetUs() {
        return this.initialTimestampOffsetUs;
    }
}
