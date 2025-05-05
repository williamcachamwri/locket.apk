package androidx.media3.effect;

import android.content.Context;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLSurface;
import android.util.SparseArray;
import androidx.media3.common.C;
import androidx.media3.common.ColorInfo;
import androidx.media3.common.DebugViewProvider;
import androidx.media3.common.Effect;
import androidx.media3.common.FrameInfo;
import androidx.media3.common.GlObjectsProvider;
import androidx.media3.common.GlTextureInfo;
import androidx.media3.common.SurfaceInfo;
import androidx.media3.common.VideoFrameProcessingException;
import androidx.media3.common.VideoFrameProcessor;
import androidx.media3.common.VideoGraph;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.GlUtil;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.effect.DefaultVideoFrameProcessor;
import androidx.media3.effect.VideoCompositor;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public abstract class MultipleInputVideoGraph implements VideoGraph {
    private static final int COMPOSITOR_TEXTURE_OUTPUT_CAPACITY = 1;
    private static final int PRE_COMPOSITOR_TEXTURE_OUTPUT_CAPACITY = 2;
    private static final long RELEASE_WAIT_TIME_MS = 1000;
    private static final String SHARED_EXECUTOR_NAME = "Effect:MultipleInputVideoGraph:Thread";
    private static final String TAG = "MultiInputVG";
    private final List<Effect> compositionEffects;
    private VideoFrameProcessor compositionVideoFrameProcessor;
    private boolean compositionVideoFrameProcessorInputStreamRegistered;
    /* access modifiers changed from: private */
    public boolean compositionVideoFrameProcessorInputStreamRegistrationCompleted;
    private boolean compositorEnded;
    private final SparseArray<CompositorOutputTextureRelease> compositorOutputTextureReleases;
    private final Queue<CompositorOutputTextureInfo> compositorOutputTextures;
    private final Context context;
    private final DebugViewProvider debugViewProvider;
    private final SingleContextGlObjectsProvider glObjectsProvider;
    /* access modifiers changed from: private */
    public volatile boolean hasProducedFrameWithTimestampZero;
    private final long initialTimestampOffsetUs;
    /* access modifiers changed from: private */
    public long lastRenderedPresentationTimeUs = C.TIME_UNSET;
    /* access modifiers changed from: private */
    public final VideoGraph.Listener listener;
    /* access modifiers changed from: private */
    public final Executor listenerExecutor;
    private final ColorInfo outputColorInfo;
    private final SparseArray<VideoFrameProcessor> preProcessors = new SparseArray<>();
    private boolean released;
    private final boolean renderFramesAutomatically;
    private final ExecutorService sharedExecutorService;
    private VideoCompositor videoCompositor;
    private final VideoCompositorSettings videoCompositorSettings;
    private final DefaultVideoFrameProcessor.Factory videoFrameProcessorFactory;

    protected MultipleInputVideoGraph(Context context2, VideoFrameProcessor.Factory factory, ColorInfo colorInfo, DebugViewProvider debugViewProvider2, VideoGraph.Listener listener2, Executor executor, VideoCompositorSettings videoCompositorSettings2, List<Effect> list, long j, boolean z) {
        Assertions.checkArgument(factory instanceof DefaultVideoFrameProcessor.Factory);
        this.context = context2;
        this.outputColorInfo = colorInfo;
        this.debugViewProvider = debugViewProvider2;
        this.listener = listener2;
        this.listenerExecutor = executor;
        this.videoCompositorSettings = videoCompositorSettings2;
        this.compositionEffects = new ArrayList(list);
        this.initialTimestampOffsetUs = j;
        this.renderFramesAutomatically = z;
        ScheduledExecutorService newSingleThreadScheduledExecutor = Util.newSingleThreadScheduledExecutor(SHARED_EXECUTOR_NAME);
        this.sharedExecutorService = newSingleThreadScheduledExecutor;
        SingleContextGlObjectsProvider singleContextGlObjectsProvider = new SingleContextGlObjectsProvider();
        this.glObjectsProvider = singleContextGlObjectsProvider;
        this.videoFrameProcessorFactory = ((DefaultVideoFrameProcessor.Factory) factory).buildUpon().setGlObjectsProvider(singleContextGlObjectsProvider).setExecutorService(newSingleThreadScheduledExecutor).build();
        this.compositorOutputTextures = new ArrayDeque();
        this.compositorOutputTextureReleases = new SparseArray<>();
    }

    public void initialize() throws VideoFrameProcessingException {
        Assertions.checkState(this.preProcessors.size() == 0 && this.videoCompositor == null && this.compositionVideoFrameProcessor == null && !this.released);
        DefaultVideoFrameProcessor create = this.videoFrameProcessorFactory.create(this.context, this.debugViewProvider, this.outputColorInfo, this.renderFramesAutomatically, MoreExecutors.directExecutor(), (VideoFrameProcessor.Listener) new VideoFrameProcessor.Listener() {
            public void onInputStreamRegistered(int i, List<Effect> list, FrameInfo frameInfo) {
                boolean unused = MultipleInputVideoGraph.this.compositionVideoFrameProcessorInputStreamRegistrationCompleted = true;
                MultipleInputVideoGraph.this.queueCompositionOutputInternal();
            }

            /* access modifiers changed from: package-private */
            /* renamed from: lambda$onOutputSizeChanged$0$androidx-media3-effect-MultipleInputVideoGraph$1  reason: not valid java name */
            public /* synthetic */ void m449lambda$onOutputSizeChanged$0$androidxmedia3effectMultipleInputVideoGraph$1(int i, int i2) {
                MultipleInputVideoGraph.this.listener.onOutputSizeChanged(i, i2);
            }

            public void onOutputSizeChanged(int i, int i2) {
                MultipleInputVideoGraph.this.listenerExecutor.execute(new MultipleInputVideoGraph$1$$ExternalSyntheticLambda1(this, i, i2));
            }

            public void onOutputFrameAvailableForRendering(long j) {
                if (j == 0) {
                    boolean unused = MultipleInputVideoGraph.this.hasProducedFrameWithTimestampZero = true;
                }
                long unused2 = MultipleInputVideoGraph.this.lastRenderedPresentationTimeUs = j;
                MultipleInputVideoGraph.this.listenerExecutor.execute(new MultipleInputVideoGraph$1$$ExternalSyntheticLambda0(this, j));
            }

            /* access modifiers changed from: package-private */
            /* renamed from: lambda$onOutputFrameAvailableForRendering$1$androidx-media3-effect-MultipleInputVideoGraph$1  reason: not valid java name */
            public /* synthetic */ void m448lambda$onOutputFrameAvailableForRendering$1$androidxmedia3effectMultipleInputVideoGraph$1(long j) {
                MultipleInputVideoGraph.this.listener.onOutputFrameAvailableForRendering(j);
            }

            public void onError(VideoFrameProcessingException videoFrameProcessingException) {
                MultipleInputVideoGraph.this.handleVideoFrameProcessingException(videoFrameProcessingException);
            }

            /* access modifiers changed from: package-private */
            /* renamed from: lambda$onEnded$2$androidx-media3-effect-MultipleInputVideoGraph$1  reason: not valid java name */
            public /* synthetic */ void m447lambda$onEnded$2$androidxmedia3effectMultipleInputVideoGraph$1() {
                MultipleInputVideoGraph.this.listener.onEnded(MultipleInputVideoGraph.this.lastRenderedPresentationTimeUs);
            }

            public void onEnded() {
                MultipleInputVideoGraph.this.listenerExecutor.execute(new MultipleInputVideoGraph$1$$ExternalSyntheticLambda2(this));
            }
        });
        this.compositionVideoFrameProcessor = create;
        create.setOnInputFrameProcessedListener(new MultipleInputVideoGraph$$ExternalSyntheticLambda0(this));
        this.videoCompositor = new DefaultVideoCompositor(this.context, this.glObjectsProvider, this.videoCompositorSettings, this.sharedExecutorService, new VideoCompositor.Listener() {
            public void onError(VideoFrameProcessingException videoFrameProcessingException) {
                MultipleInputVideoGraph.this.handleVideoFrameProcessingException(videoFrameProcessingException);
            }

            public void onEnded() {
                MultipleInputVideoGraph.this.onVideoCompositorEnded();
            }
        }, new MultipleInputVideoGraph$$ExternalSyntheticLambda1(this), 1);
    }

    public void registerInput(final int i) throws VideoFrameProcessingException {
        Assertions.checkState(!Util.contains(this.preProcessors, i));
        ((VideoCompositor) Assertions.checkNotNull(this.videoCompositor)).registerInputSource(i);
        this.preProcessors.put(i, this.videoFrameProcessorFactory.buildUpon().setTextureOutput(new MultipleInputVideoGraph$$ExternalSyntheticLambda2(this, i), 2).build().create(this.context, DebugViewProvider.NONE, this.outputColorInfo, true, this.listenerExecutor, (VideoFrameProcessor.Listener) new VideoFrameProcessor.Listener() {
            public void onError(VideoFrameProcessingException videoFrameProcessingException) {
                MultipleInputVideoGraph.this.handleVideoFrameProcessingException(videoFrameProcessingException);
            }

            public void onEnded() {
                MultipleInputVideoGraph.this.onPreProcessingVideoFrameProcessorEnded(i);
            }
        }));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$registerInput$0$androidx-media3-effect-MultipleInputVideoGraph  reason: not valid java name */
    public /* synthetic */ void m445lambda$registerInput$0$androidxmedia3effectMultipleInputVideoGraph(int i, GlTextureProducer glTextureProducer, GlTextureInfo glTextureInfo, long j, long j2) throws VideoFrameProcessingException, GlUtil.GlException {
        queuePreProcessingOutputToCompositor(i, glTextureProducer, glTextureInfo, j);
    }

    public VideoFrameProcessor getProcessor(int i) {
        Assertions.checkState(Util.contains(this.preProcessors, i));
        return this.preProcessors.get(i);
    }

    public void setOutputSurfaceInfo(SurfaceInfo surfaceInfo) {
        ((VideoFrameProcessor) Assertions.checkNotNull(this.compositionVideoFrameProcessor)).setOutputSurfaceInfo(surfaceInfo);
    }

    public boolean hasProducedFrameWithTimestampZero() {
        return this.hasProducedFrameWithTimestampZero;
    }

    public void release() {
        if (!this.released) {
            for (int i = 0; i < this.preProcessors.size(); i++) {
                SparseArray<VideoFrameProcessor> sparseArray = this.preProcessors;
                sparseArray.get(sparseArray.keyAt(i)).release();
            }
            this.preProcessors.clear();
            VideoCompositor videoCompositor2 = this.videoCompositor;
            if (videoCompositor2 != null) {
                videoCompositor2.release();
                this.videoCompositor = null;
            }
            VideoFrameProcessor videoFrameProcessor = this.compositionVideoFrameProcessor;
            if (videoFrameProcessor != null) {
                videoFrameProcessor.release();
                this.compositionVideoFrameProcessor = null;
            }
            try {
                if (this.glObjectsProvider.singleEglContext != null) {
                    GlUtil.destroyEglContext(GlUtil.getDefaultEglDisplay(), this.glObjectsProvider.singleEglContext);
                }
            } catch (GlUtil.GlException e) {
                Log.e(TAG, "Error releasing GL context", e);
            }
            this.sharedExecutorService.shutdown();
            try {
                this.sharedExecutorService.awaitTermination(1000, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e2) {
                Thread.currentThread().interrupt();
                this.listenerExecutor.execute(new MultipleInputVideoGraph$$ExternalSyntheticLambda3(this, e2));
            }
            this.released = true;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$release$1$androidx-media3-effect-MultipleInputVideoGraph  reason: not valid java name */
    public /* synthetic */ void m446lambda$release$1$androidxmedia3effectMultipleInputVideoGraph(InterruptedException interruptedException) {
        this.listener.onError(VideoFrameProcessingException.from(interruptedException));
    }

    /* access modifiers changed from: protected */
    public VideoFrameProcessor getCompositionVideoFrameProcessor() {
        return (VideoFrameProcessor) Assertions.checkStateNotNull(this.compositionVideoFrameProcessor);
    }

    /* access modifiers changed from: protected */
    public long getInitialTimestampOffsetUs() {
        return this.initialTimestampOffsetUs;
    }

    private void queuePreProcessingOutputToCompositor(int i, GlTextureProducer glTextureProducer, GlTextureInfo glTextureInfo, long j) {
        DebugTraceUtil.logEvent(DebugTraceUtil.COMPONENT_VFP, DebugTraceUtil.EVENT_OUTPUT_TEXTURE_RENDERED, j);
        ((VideoCompositor) Assertions.checkNotNull(this.videoCompositor)).queueInputTexture(i, glTextureProducer, glTextureInfo, this.outputColorInfo, j);
    }

    /* access modifiers changed from: private */
    public void processCompositorOutputTexture(GlTextureProducer glTextureProducer, GlTextureInfo glTextureInfo, long j, long j2) {
        Assertions.checkStateNotNull(this.compositionVideoFrameProcessor);
        Assertions.checkState(!this.compositorEnded);
        DebugTraceUtil.logEvent(DebugTraceUtil.COMPONENT_COMPOSITOR, DebugTraceUtil.EVENT_OUTPUT_TEXTURE_RENDERED, j);
        this.compositorOutputTextures.add(new CompositorOutputTextureInfo(glTextureInfo, j));
        this.compositorOutputTextureReleases.put(glTextureInfo.texId, new CompositorOutputTextureRelease(glTextureProducer, j));
        if (!this.compositionVideoFrameProcessorInputStreamRegistered) {
            ((VideoFrameProcessor) Assertions.checkNotNull(this.compositionVideoFrameProcessor)).registerInputStream(3, this.compositionEffects, new FrameInfo.Builder(this.outputColorInfo, glTextureInfo.width, glTextureInfo.height).build());
            this.compositionVideoFrameProcessorInputStreamRegistered = true;
            return;
        }
        queueCompositionOutputInternal();
    }

    /* access modifiers changed from: private */
    public void onCompositionVideoFrameProcessorInputFrameProcessed(int i, long j) {
        Assertions.checkState(Util.contains(this.compositorOutputTextureReleases, i));
        this.compositorOutputTextureReleases.get(i).release();
        this.compositorOutputTextureReleases.remove(i);
        queueCompositionOutputInternal();
    }

    /* access modifiers changed from: private */
    public void onPreProcessingVideoFrameProcessorEnded(int i) {
        ((VideoCompositor) Assertions.checkNotNull(this.videoCompositor)).signalEndOfInputSource(i);
    }

    /* access modifiers changed from: private */
    public void onVideoCompositorEnded() {
        this.compositorEnded = true;
        if (this.compositorOutputTextures.isEmpty()) {
            ((VideoFrameProcessor) Assertions.checkNotNull(this.compositionVideoFrameProcessor)).signalEndOfInput();
        } else {
            queueCompositionOutputInternal();
        }
    }

    /* access modifiers changed from: private */
    public void queueCompositionOutputInternal() {
        CompositorOutputTextureInfo peek;
        Assertions.checkStateNotNull(this.compositionVideoFrameProcessor);
        if (this.compositionVideoFrameProcessorInputStreamRegistrationCompleted && (peek = this.compositorOutputTextures.peek()) != null) {
            Assertions.checkState(((VideoFrameProcessor) Assertions.checkNotNull(this.compositionVideoFrameProcessor)).queueInputTexture(peek.glTextureInfo.texId, peek.presentationTimeUs));
            this.compositorOutputTextures.remove();
            if (this.compositorEnded && this.compositorOutputTextures.isEmpty()) {
                ((VideoFrameProcessor) Assertions.checkNotNull(this.compositionVideoFrameProcessor)).signalEndOfInput();
            }
        }
    }

    /* access modifiers changed from: private */
    public void handleVideoFrameProcessingException(Exception exc) {
        this.listenerExecutor.execute(new MultipleInputVideoGraph$$ExternalSyntheticLambda4(this, exc));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$handleVideoFrameProcessingException$2$androidx-media3-effect-MultipleInputVideoGraph  reason: not valid java name */
    public /* synthetic */ void m444lambda$handleVideoFrameProcessingException$2$androidxmedia3effectMultipleInputVideoGraph(Exception exc) {
        VideoFrameProcessingException videoFrameProcessingException;
        VideoGraph.Listener listener2 = this.listener;
        if (exc instanceof VideoFrameProcessingException) {
            videoFrameProcessingException = (VideoFrameProcessingException) exc;
        } else {
            videoFrameProcessingException = VideoFrameProcessingException.from(exc);
        }
        listener2.onError(videoFrameProcessingException);
    }

    private static final class CompositorOutputTextureInfo {
        public final GlTextureInfo glTextureInfo;
        public final long presentationTimeUs;

        private CompositorOutputTextureInfo(GlTextureInfo glTextureInfo2, long j) {
            this.glTextureInfo = glTextureInfo2;
            this.presentationTimeUs = j;
        }
    }

    private static final class CompositorOutputTextureRelease {
        private final long presentationTimeUs;
        private final GlTextureProducer textureProducer;

        public CompositorOutputTextureRelease(GlTextureProducer glTextureProducer, long j) {
            this.textureProducer = glTextureProducer;
            this.presentationTimeUs = j;
        }

        public void release() {
            this.textureProducer.releaseOutputTexture(this.presentationTimeUs);
        }
    }

    private static final class SingleContextGlObjectsProvider implements GlObjectsProvider {
        private final GlObjectsProvider glObjectsProvider = new DefaultGlObjectsProvider();
        /* access modifiers changed from: private */
        public EGLContext singleEglContext;

        public void release(EGLDisplay eGLDisplay) {
        }

        public EGLContext createEglContext(EGLDisplay eGLDisplay, int i, int[] iArr) throws GlUtil.GlException {
            if (this.singleEglContext == null) {
                this.singleEglContext = this.glObjectsProvider.createEglContext(eGLDisplay, i, iArr);
            }
            return this.singleEglContext;
        }

        public EGLSurface createEglSurface(EGLDisplay eGLDisplay, Object obj, int i, boolean z) throws GlUtil.GlException {
            return this.glObjectsProvider.createEglSurface(eGLDisplay, obj, i, z);
        }

        public EGLSurface createFocusedPlaceholderEglSurface(EGLContext eGLContext, EGLDisplay eGLDisplay) throws GlUtil.GlException {
            return this.glObjectsProvider.createFocusedPlaceholderEglSurface(eGLContext, eGLDisplay);
        }

        public GlTextureInfo createBuffersForTexture(int i, int i2, int i3) throws GlUtil.GlException {
            return this.glObjectsProvider.createBuffersForTexture(i, i2, i3);
        }
    }
}
