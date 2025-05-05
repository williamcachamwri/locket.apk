package androidx.media3.effect;

import androidx.media3.common.GlObjectsProvider;
import androidx.media3.common.GlTextureInfo;
import androidx.media3.common.VideoFrameProcessingException;
import androidx.media3.common.util.GlUtil;
import androidx.media3.effect.GlShaderProgram;
import java.util.Objects;

final class ChainingGlShaderProgramListener implements GlShaderProgram.InputListener, GlShaderProgram.OutputListener {
    private final FrameConsumptionManager frameConsumptionManager;
    private final GlShaderProgram producingGlShaderProgram;
    private final VideoFrameProcessingTaskExecutor videoFrameProcessingTaskExecutor;

    public ChainingGlShaderProgramListener(GlObjectsProvider glObjectsProvider, GlShaderProgram glShaderProgram, GlShaderProgram glShaderProgram2, VideoFrameProcessingTaskExecutor videoFrameProcessingTaskExecutor2) {
        this.producingGlShaderProgram = glShaderProgram;
        this.frameConsumptionManager = new FrameConsumptionManager(glObjectsProvider, glShaderProgram2, videoFrameProcessingTaskExecutor2);
        this.videoFrameProcessingTaskExecutor = videoFrameProcessingTaskExecutor2;
    }

    public synchronized void onReadyToAcceptInputFrame() {
        this.frameConsumptionManager.onReadyToAcceptInputFrame();
    }

    public void onInputFrameProcessed(GlTextureInfo glTextureInfo) {
        this.videoFrameProcessingTaskExecutor.submit(new ChainingGlShaderProgramListener$$ExternalSyntheticLambda1(this, glTextureInfo));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onInputFrameProcessed$0$androidx-media3-effect-ChainingGlShaderProgramListener  reason: not valid java name */
    public /* synthetic */ void m414lambda$onInputFrameProcessed$0$androidxmedia3effectChainingGlShaderProgramListener(GlTextureInfo glTextureInfo) throws VideoFrameProcessingException, GlUtil.GlException {
        this.producingGlShaderProgram.releaseOutputFrame(glTextureInfo);
    }

    public synchronized void onFlush() {
        this.frameConsumptionManager.onFlush();
        VideoFrameProcessingTaskExecutor videoFrameProcessingTaskExecutor2 = this.videoFrameProcessingTaskExecutor;
        GlShaderProgram glShaderProgram = this.producingGlShaderProgram;
        Objects.requireNonNull(glShaderProgram);
        videoFrameProcessingTaskExecutor2.submit(new ChainingGlShaderProgramListener$$ExternalSyntheticLambda0(glShaderProgram));
    }

    public synchronized void onOutputFrameAvailable(GlTextureInfo glTextureInfo, long j) {
        this.frameConsumptionManager.queueInputFrame(glTextureInfo, j);
    }

    public synchronized void onCurrentOutputStreamEnded() {
        this.frameConsumptionManager.signalEndOfCurrentStream();
    }
}
