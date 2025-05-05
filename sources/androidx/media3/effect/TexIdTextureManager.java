package androidx.media3.effect;

import androidx.media3.common.FrameInfo;
import androidx.media3.common.GlObjectsProvider;
import androidx.media3.common.GlTextureInfo;
import androidx.media3.common.OnInputFrameProcessedListener;
import androidx.media3.common.VideoFrameProcessingException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.GlUtil;
import java.util.Objects;

final class TexIdTextureManager extends TextureManager {
    private FrameConsumptionManager frameConsumptionManager;
    private OnInputFrameProcessedListener frameProcessedListener;
    private final GlObjectsProvider glObjectsProvider;
    private FrameInfo inputFrameInfo;

    public void release() {
    }

    public TexIdTextureManager(GlObjectsProvider glObjectsProvider2, VideoFrameProcessingTaskExecutor videoFrameProcessingTaskExecutor) {
        super(videoFrameProcessingTaskExecutor);
        this.glObjectsProvider = glObjectsProvider2;
    }

    public void onReadyToAcceptInputFrame() {
        Assertions.checkNotNull(this.frameConsumptionManager);
        VideoFrameProcessingTaskExecutor videoFrameProcessingTaskExecutor = this.videoFrameProcessingTaskExecutor;
        FrameConsumptionManager frameConsumptionManager2 = this.frameConsumptionManager;
        Objects.requireNonNull(frameConsumptionManager2);
        videoFrameProcessingTaskExecutor.submit(new TexIdTextureManager$$ExternalSyntheticLambda2(frameConsumptionManager2));
    }

    public void onInputFrameProcessed(GlTextureInfo glTextureInfo) {
        this.videoFrameProcessingTaskExecutor.submit(new TexIdTextureManager$$ExternalSyntheticLambda3(this, glTextureInfo));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onInputFrameProcessed$0$androidx-media3-effect-TexIdTextureManager  reason: not valid java name */
    public /* synthetic */ void m457lambda$onInputFrameProcessed$0$androidxmedia3effectTexIdTextureManager(GlTextureInfo glTextureInfo) throws VideoFrameProcessingException, GlUtil.GlException {
        ((OnInputFrameProcessedListener) Assertions.checkNotNull(this.frameProcessedListener)).onInputFrameProcessed(glTextureInfo.texId, GlUtil.createGlSyncFence());
    }

    public void setSamplingGlShaderProgram(GlShaderProgram glShaderProgram) {
        this.frameConsumptionManager = new FrameConsumptionManager(this.glObjectsProvider, glShaderProgram, this.videoFrameProcessingTaskExecutor);
    }

    public void queueInputTexture(int i, long j) {
        Assertions.checkNotNull(this.frameProcessedListener);
        this.videoFrameProcessingTaskExecutor.submit(new TexIdTextureManager$$ExternalSyntheticLambda1(this, i, (FrameInfo) Assertions.checkNotNull(this.inputFrameInfo), j));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$queueInputTexture$1$androidx-media3-effect-TexIdTextureManager  reason: not valid java name */
    public /* synthetic */ void m458lambda$queueInputTexture$1$androidxmedia3effectTexIdTextureManager(int i, FrameInfo frameInfo, long j) throws VideoFrameProcessingException, GlUtil.GlException {
        ((FrameConsumptionManager) Assertions.checkNotNull(this.frameConsumptionManager)).queueInputFrame(new GlTextureInfo(i, -1, -1, frameInfo.width, frameInfo.height), j);
        DebugTraceUtil.logEvent(DebugTraceUtil.COMPONENT_VFP, DebugTraceUtil.EVENT_QUEUE_TEXTURE, j, "%dx%d", Integer.valueOf(frameInfo.width), Integer.valueOf(frameInfo.height));
    }

    public void setOnInputFrameProcessedListener(OnInputFrameProcessedListener onInputFrameProcessedListener) {
        this.frameProcessedListener = onInputFrameProcessedListener;
    }

    public void setInputFrameInfo(FrameInfo frameInfo, boolean z) {
        this.inputFrameInfo = frameInfo;
    }

    public int getPendingFrameCount() {
        return ((FrameConsumptionManager) Assertions.checkNotNull(this.frameConsumptionManager)).getPendingFrameCount();
    }

    public void signalEndOfCurrentInputStream() {
        this.videoFrameProcessingTaskExecutor.submit(new TexIdTextureManager$$ExternalSyntheticLambda0(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$signalEndOfCurrentInputStream$2$androidx-media3-effect-TexIdTextureManager  reason: not valid java name */
    public /* synthetic */ void m459lambda$signalEndOfCurrentInputStream$2$androidxmedia3effectTexIdTextureManager() throws VideoFrameProcessingException, GlUtil.GlException {
        ((FrameConsumptionManager) Assertions.checkNotNull(this.frameConsumptionManager)).signalEndOfCurrentStream();
        DebugTraceUtil.logEvent(DebugTraceUtil.COMPONENT_TEX_ID_TEXTURE_MANAGER, DebugTraceUtil.EVENT_SIGNAL_EOS, Long.MIN_VALUE);
    }

    /* access modifiers changed from: protected */
    public synchronized void flush() throws VideoFrameProcessingException {
        ((FrameConsumptionManager) Assertions.checkNotNull(this.frameConsumptionManager)).onFlush();
        super.flush();
    }
}
