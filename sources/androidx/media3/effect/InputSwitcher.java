package androidx.media3.effect;

import android.content.Context;
import android.util.SparseArray;
import android.view.Surface;
import androidx.media3.common.ColorInfo;
import androidx.media3.common.FrameInfo;
import androidx.media3.common.GlObjectsProvider;
import androidx.media3.common.GlTextureInfo;
import androidx.media3.common.OnInputFrameProcessedListener;
import androidx.media3.common.VideoFrameProcessingException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.effect.GlShaderProgram;
import java.util.concurrent.Executor;

final class InputSwitcher {
    private TextureManager activeTextureManager;
    private final Context context;
    private GlShaderProgram downstreamShaderProgram;
    private final Executor errorListenerExecutor;
    private final boolean experimentalAdjustSurfaceTextureTransformationMatrix;
    private final GlObjectsProvider glObjectsProvider;
    private final SparseArray<Input> inputs;
    private final ColorInfo outputColorInfo;
    private final GlShaderProgram.ErrorListener samplingShaderProgramErrorListener;
    private final int sdrWorkingColorSpace;
    private final VideoFrameProcessingTaskExecutor videoFrameProcessingTaskExecutor;

    public InputSwitcher(Context context2, ColorInfo colorInfo, GlObjectsProvider glObjectsProvider2, VideoFrameProcessingTaskExecutor videoFrameProcessingTaskExecutor2, Executor executor, GlShaderProgram.ErrorListener errorListener, int i, boolean z, boolean z2, boolean z3) throws VideoFrameProcessingException {
        this.context = context2;
        this.outputColorInfo = colorInfo;
        this.glObjectsProvider = glObjectsProvider2;
        this.videoFrameProcessingTaskExecutor = videoFrameProcessingTaskExecutor2;
        this.errorListenerExecutor = executor;
        this.samplingShaderProgramErrorListener = errorListener;
        SparseArray<Input> sparseArray = new SparseArray<>();
        this.inputs = sparseArray;
        this.sdrWorkingColorSpace = i;
        this.experimentalAdjustSurfaceTextureTransformationMatrix = z2;
        Input input = new Input(new ExternalTextureManager(glObjectsProvider2, videoFrameProcessingTaskExecutor2, z, z2));
        sparseArray.put(1, input);
        sparseArray.put(4, input);
        sparseArray.put(2, new Input(new BitmapTextureManager(glObjectsProvider2, videoFrameProcessingTaskExecutor2, z3)));
        sparseArray.put(3, new Input(new TexIdTextureManager(glObjectsProvider2, videoFrameProcessingTaskExecutor2)));
    }

    private DefaultShaderProgram createSamplingShaderProgram(ColorInfo colorInfo, int i) throws VideoFrameProcessingException {
        DefaultShaderProgram defaultShaderProgram;
        if (i != 1) {
            if (i == 2 || i == 3) {
                defaultShaderProgram = DefaultShaderProgram.createWithInternalSampler(this.context, colorInfo, this.outputColorInfo, this.sdrWorkingColorSpace, i);
                defaultShaderProgram.setErrorListener(this.errorListenerExecutor, this.samplingShaderProgramErrorListener);
                return defaultShaderProgram;
            } else if (i != 4) {
                throw new VideoFrameProcessingException("Unsupported input type " + i);
            }
        }
        defaultShaderProgram = DefaultShaderProgram.createWithExternalSampler(this.context, colorInfo, this.outputColorInfo, this.sdrWorkingColorSpace, this.experimentalAdjustSurfaceTextureTransformationMatrix);
        defaultShaderProgram.setErrorListener(this.errorListenerExecutor, this.samplingShaderProgramErrorListener);
        return defaultShaderProgram;
    }

    public void setDownstreamShaderProgram(GlShaderProgram glShaderProgram) {
        this.downstreamShaderProgram = glShaderProgram;
    }

    public void switchToInput(int i, FrameInfo frameInfo) throws VideoFrameProcessingException {
        Assertions.checkStateNotNull(this.downstreamShaderProgram);
        Assertions.checkState(Util.contains(this.inputs, i), "Input type not registered: " + i);
        boolean z = false;
        for (int i2 = 0; i2 < this.inputs.size(); i2++) {
            SparseArray<Input> sparseArray = this.inputs;
            sparseArray.get(sparseArray.keyAt(i2)).setActive(false);
        }
        Input input = this.inputs.get(i);
        if (input.getInputColorInfo() == null || !frameInfo.colorInfo.equals(input.getInputColorInfo())) {
            input.setSamplingGlShaderProgram(createSamplingShaderProgram(frameInfo.colorInfo, i));
            input.setInputColorInfo(frameInfo.colorInfo);
        }
        input.setChainingListener(new GatedChainingListenerWrapper(this.glObjectsProvider, (GlShaderProgram) Assertions.checkNotNull(input.getSamplingGlShaderProgram()), this.downstreamShaderProgram, this.videoFrameProcessingTaskExecutor));
        input.setActive(true);
        this.downstreamShaderProgram.setInputListener((GlShaderProgram.InputListener) Assertions.checkNotNull(input.gatedChainingListenerWrapper));
        TextureManager textureManager = input.textureManager;
        this.activeTextureManager = textureManager;
        if (i == 4) {
            z = true;
        }
        ((TextureManager) Assertions.checkNotNull(textureManager)).setInputFrameInfo(frameInfo, z);
    }

    public boolean hasActiveInput() {
        return this.activeTextureManager != null;
    }

    public TextureManager activeTextureManager() {
        return (TextureManager) Assertions.checkStateNotNull(this.activeTextureManager);
    }

    public void signalEndOfInputStream() {
        ((TextureManager) Assertions.checkNotNull(this.activeTextureManager)).signalEndOfCurrentInputStream();
    }

    public Surface getInputSurface() {
        Assertions.checkState(Util.contains(this.inputs, 1));
        return this.inputs.get(1).textureManager.getInputSurface();
    }

    public void setInputDefaultBufferSize(int i, int i2) {
        Assertions.checkState(Util.contains(this.inputs, 1));
        this.inputs.get(1).textureManager.setDefaultBufferSize(i, i2);
    }

    public void setOnInputFrameProcessedListener(OnInputFrameProcessedListener onInputFrameProcessedListener) {
        Assertions.checkState(Util.contains(this.inputs, 3));
        this.inputs.get(3).textureManager.setOnInputFrameProcessedListener(onInputFrameProcessedListener);
    }

    public void release() throws VideoFrameProcessingException {
        for (int i = 0; i < this.inputs.size(); i++) {
            SparseArray<Input> sparseArray = this.inputs;
            sparseArray.get(sparseArray.keyAt(i)).release();
        }
    }

    private static final class Input {
        /* access modifiers changed from: private */
        public GatedChainingListenerWrapper gatedChainingListenerWrapper;
        private ColorInfo inputColorInfo;
        private boolean released;
        private ExternalShaderProgram samplingGlShaderProgram;
        public final TextureManager textureManager;

        public Input(TextureManager textureManager2) {
            this.textureManager = textureManager2;
        }

        public void setSamplingGlShaderProgram(ExternalShaderProgram externalShaderProgram) throws VideoFrameProcessingException {
            ExternalShaderProgram externalShaderProgram2 = this.samplingGlShaderProgram;
            if (externalShaderProgram2 != null) {
                externalShaderProgram2.release();
            }
            this.samplingGlShaderProgram = externalShaderProgram;
            this.textureManager.setSamplingGlShaderProgram(externalShaderProgram);
            externalShaderProgram.setInputListener(this.textureManager);
        }

        public void setInputColorInfo(ColorInfo colorInfo) {
            this.inputColorInfo = colorInfo;
        }

        public void setChainingListener(GatedChainingListenerWrapper gatedChainingListenerWrapper2) {
            this.gatedChainingListenerWrapper = gatedChainingListenerWrapper2;
            ((ExternalShaderProgram) Assertions.checkNotNull(this.samplingGlShaderProgram)).setOutputListener(gatedChainingListenerWrapper2);
        }

        public ExternalShaderProgram getSamplingGlShaderProgram() {
            return this.samplingGlShaderProgram;
        }

        public ColorInfo getInputColorInfo() {
            return this.inputColorInfo;
        }

        public void setActive(boolean z) {
            GatedChainingListenerWrapper gatedChainingListenerWrapper2 = this.gatedChainingListenerWrapper;
            if (gatedChainingListenerWrapper2 != null) {
                gatedChainingListenerWrapper2.setActive(z);
            }
        }

        public void release() throws VideoFrameProcessingException {
            if (!this.released) {
                this.released = true;
                this.textureManager.release();
                ExternalShaderProgram externalShaderProgram = this.samplingGlShaderProgram;
                if (externalShaderProgram != null) {
                    externalShaderProgram.release();
                }
            }
        }
    }

    private static final class GatedChainingListenerWrapper implements GlShaderProgram.OutputListener, GlShaderProgram.InputListener {
        private final ChainingGlShaderProgramListener chainingGlShaderProgramListener;
        private boolean isActive;

        public GatedChainingListenerWrapper(GlObjectsProvider glObjectsProvider, GlShaderProgram glShaderProgram, GlShaderProgram glShaderProgram2, VideoFrameProcessingTaskExecutor videoFrameProcessingTaskExecutor) {
            this.chainingGlShaderProgramListener = new ChainingGlShaderProgramListener(glObjectsProvider, glShaderProgram, glShaderProgram2, videoFrameProcessingTaskExecutor);
        }

        public void onReadyToAcceptInputFrame() {
            if (this.isActive) {
                this.chainingGlShaderProgramListener.onReadyToAcceptInputFrame();
            }
        }

        public void onInputFrameProcessed(GlTextureInfo glTextureInfo) {
            if (this.isActive) {
                this.chainingGlShaderProgramListener.onInputFrameProcessed(glTextureInfo);
            }
        }

        public synchronized void onFlush() {
            if (this.isActive) {
                this.chainingGlShaderProgramListener.onFlush();
            }
        }

        public synchronized void onOutputFrameAvailable(GlTextureInfo glTextureInfo, long j) {
            if (this.isActive) {
                this.chainingGlShaderProgramListener.onOutputFrameAvailable(glTextureInfo, j);
            }
        }

        public synchronized void onCurrentOutputStreamEnded() {
            if (this.isActive) {
                this.chainingGlShaderProgramListener.onCurrentOutputStreamEnded();
            }
        }

        public void setActive(boolean z) {
            this.isActive = z;
        }
    }
}
