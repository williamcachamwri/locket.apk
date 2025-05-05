package androidx.media3.effect;

import android.content.Context;
import android.graphics.Bitmap;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLSurface;
import android.util.Pair;
import android.view.Surface;
import androidx.media3.common.ColorInfo;
import androidx.media3.common.DebugViewProvider;
import androidx.media3.common.Effect;
import androidx.media3.common.FrameInfo;
import androidx.media3.common.GlObjectsProvider;
import androidx.media3.common.MediaLibraryInfo;
import androidx.media3.common.OnInputFrameProcessedListener;
import androidx.media3.common.SurfaceInfo;
import androidx.media3.common.VideoFrameProcessingException;
import androidx.media3.common.VideoFrameProcessor;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ConditionVariable;
import androidx.media3.common.util.GlUtil;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.TimestampIterator;
import androidx.media3.common.util.Util;
import androidx.media3.effect.GlTextureProducer;
import androidx.media3.effect.VideoFrameProcessingTaskExecutor;
import com.bumptech.glide.Registry;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

public final class DefaultVideoFrameProcessor implements VideoFrameProcessor {
    private static final String TAG = "DefaultFrameProcessor";
    public static final int WORKING_COLOR_SPACE_DEFAULT = 0;
    public static final int WORKING_COLOR_SPACE_LINEAR = 2;
    public static final int WORKING_COLOR_SPACE_ORIGINAL = 1;
    private final List<Effect> activeEffects = new ArrayList();
    private final Context context;
    private final EGLDisplay eglDisplay;
    private final FinalShaderProgramWrapper finalShaderProgramWrapper;
    private final GlObjectsProvider glObjectsProvider;
    private volatile boolean inputStreamEnded;
    private final ConditionVariable inputStreamRegisteredCondition;
    private final InputSwitcher inputSwitcher;
    private final List<GlShaderProgram> intermediateGlShaderPrograms;
    private final VideoFrameProcessor.Listener listener;
    private final Executor listenerExecutor;
    private final Object lock = new Object();
    private volatile FrameInfo nextInputFrameInfo;
    private Runnable onInputSurfaceReadyListener;
    private final ColorInfo outputColorInfo;
    private InputStreamInfo pendingInputStreamInfo;
    private boolean registeredFirstInputStream;
    private final boolean renderFramesAutomatically;
    private final VideoFrameProcessingTaskExecutor videoFrameProcessingTaskExecutor;

    public interface ReleaseOutputTextureCallback {
        void release(long j);
    }

    @Documented
    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface WorkingColorSpace {
    }

    static {
        MediaLibraryInfo.registerModule("media3.effect");
    }

    public static final class Factory implements VideoFrameProcessor.Factory {
        private static final String THREAD_NAME = "Effect:DefaultVideoFrameProcessor:GlThread";
        /* access modifiers changed from: private */
        public final ExecutorService executorService;
        /* access modifiers changed from: private */
        public final boolean experimentalAdjustSurfaceTextureTransformationMatrix;
        /* access modifiers changed from: private */
        public final boolean experimentalRepeatInputBitmapWithoutResampling;
        /* access modifiers changed from: private */
        public final GlObjectsProvider glObjectsProvider;
        /* access modifiers changed from: private */
        public final boolean repeatLastRegisteredFrame;
        /* access modifiers changed from: private */
        public final int sdrWorkingColorSpace;
        /* access modifiers changed from: private */
        public final int textureOutputCapacity;
        /* access modifiers changed from: private */
        public final GlTextureProducer.Listener textureOutputListener;

        public static final class Builder {
            private ExecutorService executorService;
            private boolean experimentalAdjustSurfaceTextureTransformationMatrix;
            private boolean experimentalRepeatInputBitmapWithoutResampling;
            private GlObjectsProvider glObjectsProvider;
            private boolean requireRegisteringAllInputFrames;
            private int sdrWorkingColorSpace;
            private int textureOutputCapacity;
            private GlTextureProducer.Listener textureOutputListener;

            public Builder() {
                this.sdrWorkingColorSpace = 0;
                this.requireRegisteringAllInputFrames = true;
                this.experimentalAdjustSurfaceTextureTransformationMatrix = true;
                this.experimentalRepeatInputBitmapWithoutResampling = true;
            }

            private Builder(Factory factory) {
                this.sdrWorkingColorSpace = factory.sdrWorkingColorSpace;
                this.executorService = factory.executorService;
                this.glObjectsProvider = factory.glObjectsProvider;
                this.textureOutputListener = factory.textureOutputListener;
                this.textureOutputCapacity = factory.textureOutputCapacity;
                this.requireRegisteringAllInputFrames = !factory.repeatLastRegisteredFrame;
                this.experimentalAdjustSurfaceTextureTransformationMatrix = factory.experimentalAdjustSurfaceTextureTransformationMatrix;
                this.experimentalRepeatInputBitmapWithoutResampling = factory.experimentalRepeatInputBitmapWithoutResampling;
            }

            public Builder setSdrWorkingColorSpace(int i) {
                this.sdrWorkingColorSpace = i;
                return this;
            }

            @Deprecated
            public Builder setRequireRegisteringAllInputFrames(boolean z) {
                this.requireRegisteringAllInputFrames = z;
                return this;
            }

            public Builder setGlObjectsProvider(GlObjectsProvider glObjectsProvider2) {
                this.glObjectsProvider = glObjectsProvider2;
                return this;
            }

            public Builder setExecutorService(ExecutorService executorService2) {
                this.executorService = executorService2;
                return this;
            }

            public Builder setTextureOutput(GlTextureProducer.Listener listener, int i) {
                this.textureOutputListener = listener;
                boolean z = true;
                if (i < 1) {
                    z = false;
                }
                Assertions.checkArgument(z);
                this.textureOutputCapacity = i;
                return this;
            }

            @Deprecated
            public Builder setExperimentalAdjustSurfaceTextureTransformationMatrix(boolean z) {
                this.experimentalAdjustSurfaceTextureTransformationMatrix = z;
                return this;
            }

            @Deprecated
            public Builder setExperimentalRepeatInputBitmapWithoutResampling(boolean z) {
                this.experimentalRepeatInputBitmapWithoutResampling = z;
                return this;
            }

            public Factory build() {
                int i = this.sdrWorkingColorSpace;
                boolean z = !this.requireRegisteringAllInputFrames;
                GlObjectsProvider glObjectsProvider2 = this.glObjectsProvider;
                if (glObjectsProvider2 == null) {
                    glObjectsProvider2 = new DefaultGlObjectsProvider();
                }
                return new Factory(i, z, glObjectsProvider2, this.executorService, this.textureOutputListener, this.textureOutputCapacity, this.experimentalAdjustSurfaceTextureTransformationMatrix, this.experimentalRepeatInputBitmapWithoutResampling);
            }
        }

        private Factory(int i, boolean z, GlObjectsProvider glObjectsProvider2, ExecutorService executorService2, GlTextureProducer.Listener listener, int i2, boolean z2, boolean z3) {
            this.sdrWorkingColorSpace = i;
            this.repeatLastRegisteredFrame = z;
            this.glObjectsProvider = glObjectsProvider2;
            this.executorService = executorService2;
            this.textureOutputListener = listener;
            this.textureOutputCapacity = i2;
            this.experimentalAdjustSurfaceTextureTransformationMatrix = z2;
            this.experimentalRepeatInputBitmapWithoutResampling = z3;
        }

        public Builder buildUpon() {
            return new Builder();
        }

        public DefaultVideoFrameProcessor create(Context context, DebugViewProvider debugViewProvider, ColorInfo colorInfo, boolean z, Executor executor, VideoFrameProcessor.Listener listener) throws VideoFrameProcessingException {
            ExecutorService executorService2 = this.executorService;
            boolean z2 = executorService2 == null;
            if (executorService2 == null) {
                executorService2 = Util.newSingleThreadExecutor(THREAD_NAME);
            }
            Objects.requireNonNull(listener);
            VideoFrameProcessor.Listener listener2 = listener;
            try {
                return (DefaultVideoFrameProcessor) executorService2.submit(new DefaultVideoFrameProcessor$Factory$$ExternalSyntheticLambda1(this, context, debugViewProvider, colorInfo, z, new VideoFrameProcessingTaskExecutor(executorService2, z2, new DefaultVideoFrameProcessor$Factory$$ExternalSyntheticLambda0(listener2)), executor, listener2)).get();
            } catch (ExecutionException e) {
                throw new VideoFrameProcessingException((Throwable) e);
            } catch (InterruptedException e2) {
                Thread.currentThread().interrupt();
                throw new VideoFrameProcessingException((Throwable) e2);
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$create$0$androidx-media3-effect-DefaultVideoFrameProcessor$Factory  reason: not valid java name */
        public /* synthetic */ DefaultVideoFrameProcessor m423lambda$create$0$androidxmedia3effectDefaultVideoFrameProcessor$Factory(Context context, DebugViewProvider debugViewProvider, ColorInfo colorInfo, boolean z, VideoFrameProcessingTaskExecutor videoFrameProcessingTaskExecutor, Executor executor, VideoFrameProcessor.Listener listener) throws Exception {
            return DefaultVideoFrameProcessor.createOpenGlObjectsAndFrameProcessor(context, debugViewProvider, colorInfo, this.sdrWorkingColorSpace, z, videoFrameProcessingTaskExecutor, executor, listener, this.glObjectsProvider, this.textureOutputListener, this.textureOutputCapacity, this.repeatLastRegisteredFrame, this.experimentalAdjustSurfaceTextureTransformationMatrix, this.experimentalRepeatInputBitmapWithoutResampling);
        }
    }

    private DefaultVideoFrameProcessor(Context context2, GlObjectsProvider glObjectsProvider2, EGLDisplay eGLDisplay, InputSwitcher inputSwitcher2, VideoFrameProcessingTaskExecutor videoFrameProcessingTaskExecutor2, VideoFrameProcessor.Listener listener2, Executor executor, FinalShaderProgramWrapper finalShaderProgramWrapper2, boolean z, ColorInfo colorInfo) {
        this.context = context2;
        this.glObjectsProvider = glObjectsProvider2;
        this.eglDisplay = eGLDisplay;
        this.inputSwitcher = inputSwitcher2;
        this.videoFrameProcessingTaskExecutor = videoFrameProcessingTaskExecutor2;
        this.listener = listener2;
        this.listenerExecutor = executor;
        this.renderFramesAutomatically = z;
        this.outputColorInfo = colorInfo;
        this.finalShaderProgramWrapper = finalShaderProgramWrapper2;
        this.intermediateGlShaderPrograms = new ArrayList();
        ConditionVariable conditionVariable = new ConditionVariable();
        this.inputStreamRegisteredCondition = conditionVariable;
        conditionVariable.open();
        finalShaderProgramWrapper2.setOnInputStreamProcessedListener(new DefaultVideoFrameProcessor$$ExternalSyntheticLambda5(this, executor, listener2, videoFrameProcessingTaskExecutor2));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$new$1$androidx-media3-effect-DefaultVideoFrameProcessor  reason: not valid java name */
    public /* synthetic */ void m419lambda$new$1$androidxmedia3effectDefaultVideoFrameProcessor(Executor executor, VideoFrameProcessor.Listener listener2, VideoFrameProcessingTaskExecutor videoFrameProcessingTaskExecutor2) {
        if (this.inputStreamEnded) {
            Objects.requireNonNull(listener2);
            executor.execute(new DefaultVideoFrameProcessor$$ExternalSyntheticLambda6(listener2));
            DebugTraceUtil.logEvent(DebugTraceUtil.COMPONENT_VFP, DebugTraceUtil.EVENT_SIGNAL_ENDED, Long.MIN_VALUE);
            return;
        }
        synchronized (this.lock) {
            InputStreamInfo inputStreamInfo = this.pendingInputStreamInfo;
            if (inputStreamInfo != null) {
                videoFrameProcessingTaskExecutor2.submit(new DefaultVideoFrameProcessor$$ExternalSyntheticLambda7(this, inputStreamInfo));
                this.pendingInputStreamInfo = null;
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$new$0$androidx-media3-effect-DefaultVideoFrameProcessor  reason: not valid java name */
    public /* synthetic */ void m418lambda$new$0$androidxmedia3effectDefaultVideoFrameProcessor(InputStreamInfo inputStreamInfo) throws VideoFrameProcessingException, GlUtil.GlException {
        configureEffects(inputStreamInfo, false);
    }

    public VideoFrameProcessingTaskExecutor getTaskExecutor() {
        return this.videoFrameProcessingTaskExecutor;
    }

    @Deprecated
    public void setInputDefaultBufferSize(int i, int i2) {
        this.inputSwitcher.setInputDefaultBufferSize(i, i2);
    }

    public boolean queueInputBitmap(Bitmap bitmap, TimestampIterator timestampIterator) {
        Assertions.checkState(!this.inputStreamEnded);
        boolean z = false;
        if (!this.inputStreamRegisteredCondition.isOpen()) {
            return false;
        }
        if (ColorInfo.isTransferHdr(this.outputColorInfo)) {
            if (Util.SDK_INT >= 34 && bitmap.hasGainmap()) {
                z = true;
            }
            Assertions.checkArgument(z, "VideoFrameProcessor configured for HDR output, but either received SDR input, or is on an API level that doesn't support gainmaps. SDR to HDR tonemapping is not supported.");
        }
        FrameInfo frameInfo = (FrameInfo) Assertions.checkNotNull(this.nextInputFrameInfo);
        this.inputSwitcher.activeTextureManager().queueInputBitmap(bitmap, new FrameInfo.Builder(frameInfo).setOffsetToAddUs(frameInfo.offsetToAddUs).build(), timestampIterator);
        return true;
    }

    public boolean queueInputTexture(int i, long j) {
        Assertions.checkState(!this.inputStreamEnded);
        if (!this.inputStreamRegisteredCondition.isOpen()) {
            return false;
        }
        this.inputSwitcher.activeTextureManager().queueInputTexture(i, j);
        return true;
    }

    public void setOnInputFrameProcessedListener(OnInputFrameProcessedListener onInputFrameProcessedListener) {
        this.inputSwitcher.setOnInputFrameProcessedListener(onInputFrameProcessedListener);
    }

    public void setOnInputSurfaceReadyListener(Runnable runnable) {
        synchronized (this.lock) {
            if (this.inputStreamRegisteredCondition.isOpen()) {
                runnable.run();
            } else {
                this.onInputSurfaceReadyListener = runnable;
            }
        }
    }

    public Surface getInputSurface() {
        return this.inputSwitcher.getInputSurface();
    }

    public void registerInputStream(int i, List<Effect> list, FrameInfo frameInfo) {
        DebugTraceUtil.logEvent(DebugTraceUtil.COMPONENT_VFP, DebugTraceUtil.EVENT_REGISTER_NEW_INPUT_STREAM, frameInfo.offsetToAddUs, "InputType %s - %dx%d", getInputTypeString(i), Integer.valueOf(frameInfo.width), Integer.valueOf(frameInfo.height));
        this.nextInputFrameInfo = adjustForPixelWidthHeightRatio(frameInfo);
        try {
            this.inputStreamRegisteredCondition.block();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            this.listenerExecutor.execute(new DefaultVideoFrameProcessor$$ExternalSyntheticLambda0(this, e));
        }
        synchronized (this.lock) {
            InputStreamInfo inputStreamInfo = new InputStreamInfo(i, list, frameInfo);
            if (!this.registeredFirstInputStream) {
                this.registeredFirstInputStream = true;
                this.inputStreamRegisteredCondition.close();
                this.videoFrameProcessingTaskExecutor.submit(new DefaultVideoFrameProcessor$$ExternalSyntheticLambda2(this, inputStreamInfo));
            } else {
                this.pendingInputStreamInfo = inputStreamInfo;
                this.inputStreamRegisteredCondition.close();
                this.inputSwitcher.activeTextureManager().signalEndOfCurrentInputStream();
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$registerInputStream$2$androidx-media3-effect-DefaultVideoFrameProcessor  reason: not valid java name */
    public /* synthetic */ void m420lambda$registerInputStream$2$androidxmedia3effectDefaultVideoFrameProcessor(InterruptedException interruptedException) {
        this.listener.onError(VideoFrameProcessingException.from(interruptedException));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$registerInputStream$3$androidx-media3-effect-DefaultVideoFrameProcessor  reason: not valid java name */
    public /* synthetic */ void m421lambda$registerInputStream$3$androidxmedia3effectDefaultVideoFrameProcessor(InputStreamInfo inputStreamInfo) throws VideoFrameProcessingException, GlUtil.GlException {
        configureEffects(inputStreamInfo, true);
    }

    public boolean registerInputFrame() {
        Assertions.checkState(!this.inputStreamEnded);
        Assertions.checkStateNotNull(this.nextInputFrameInfo, "registerInputStream must be called before registering input frames");
        if (!this.inputStreamRegisteredCondition.isOpen()) {
            return false;
        }
        this.inputSwitcher.activeTextureManager().registerInputFrame(this.nextInputFrameInfo);
        return true;
    }

    public int getPendingInputFrameCount() {
        if (this.inputSwitcher.hasActiveInput()) {
            return this.inputSwitcher.activeTextureManager().getPendingFrameCount();
        }
        return 0;
    }

    public void setOutputSurfaceInfo(SurfaceInfo surfaceInfo) {
        this.finalShaderProgramWrapper.setOutputSurfaceInfo(surfaceInfo);
    }

    public void renderOutputFrame(long j) {
        Assertions.checkState(!this.renderFramesAutomatically, "Calling this method is not allowed when renderFramesAutomatically is enabled");
        this.videoFrameProcessingTaskExecutor.submitWithHighPriority(new DefaultVideoFrameProcessor$$ExternalSyntheticLambda8(this, j));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$renderOutputFrame$4$androidx-media3-effect-DefaultVideoFrameProcessor  reason: not valid java name */
    public /* synthetic */ void m422lambda$renderOutputFrame$4$androidxmedia3effectDefaultVideoFrameProcessor(long j) throws VideoFrameProcessingException, GlUtil.GlException {
        this.finalShaderProgramWrapper.renderOutputFrame(this.glObjectsProvider, j);
    }

    public void signalEndOfInput() {
        DebugTraceUtil.logEvent(DebugTraceUtil.COMPONENT_VFP, DebugTraceUtil.EVENT_RECEIVE_END_OF_ALL_INPUT, Long.MIN_VALUE);
        Assertions.checkState(!this.inputStreamEnded);
        this.inputStreamEnded = true;
        this.inputSwitcher.signalEndOfInputStream();
    }

    public void flush() {
        if (this.inputSwitcher.hasActiveInput()) {
            try {
                TextureManager activeTextureManager = this.inputSwitcher.activeTextureManager();
                activeTextureManager.dropIncomingRegisteredFrames();
                this.videoFrameProcessingTaskExecutor.flush();
                activeTextureManager.releaseAllRegisteredFrames();
                CountDownLatch countDownLatch = new CountDownLatch(1);
                Objects.requireNonNull(countDownLatch);
                activeTextureManager.setOnFlushCompleteListener(new DefaultVideoFrameProcessor$$ExternalSyntheticLambda10(countDownLatch));
                VideoFrameProcessingTaskExecutor videoFrameProcessingTaskExecutor2 = this.videoFrameProcessingTaskExecutor;
                FinalShaderProgramWrapper finalShaderProgramWrapper2 = this.finalShaderProgramWrapper;
                Objects.requireNonNull(finalShaderProgramWrapper2);
                videoFrameProcessingTaskExecutor2.submit(new DefaultVideoFrameProcessor$$ExternalSyntheticLambda1(finalShaderProgramWrapper2));
                countDownLatch.await();
                activeTextureManager.setOnFlushCompleteListener((VideoFrameProcessingTaskExecutor.Task) null);
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void release() {
        try {
            this.videoFrameProcessingTaskExecutor.release(new DefaultVideoFrameProcessor$$ExternalSyntheticLambda3(this));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException(e);
        }
    }

    private FrameInfo adjustForPixelWidthHeightRatio(FrameInfo frameInfo) {
        if (frameInfo.pixelWidthHeightRatio > 1.0f) {
            return new FrameInfo.Builder(frameInfo).setWidth((int) (((float) frameInfo.width) * frameInfo.pixelWidthHeightRatio)).setPixelWidthHeightRatio(1.0f).build();
        }
        return frameInfo.pixelWidthHeightRatio < 1.0f ? new FrameInfo.Builder(frameInfo).setHeight((int) (((float) frameInfo.height) / frameInfo.pixelWidthHeightRatio)).setPixelWidthHeightRatio(1.0f).build() : frameInfo;
    }

    /* access modifiers changed from: private */
    public static DefaultVideoFrameProcessor createOpenGlObjectsAndFrameProcessor(Context context2, DebugViewProvider debugViewProvider, ColorInfo colorInfo, int i, boolean z, VideoFrameProcessingTaskExecutor videoFrameProcessingTaskExecutor2, Executor executor, VideoFrameProcessor.Listener listener2, GlObjectsProvider glObjectsProvider2, GlTextureProducer.Listener listener3, int i2, boolean z2, boolean z3, boolean z4) throws GlUtil.GlException, VideoFrameProcessingException {
        int[] iArr;
        ColorInfo colorInfo2;
        EGLDisplay defaultEglDisplay = GlUtil.getDefaultEglDisplay();
        if (ColorInfo.isTransferHdr(colorInfo)) {
            iArr = GlUtil.EGL_CONFIG_ATTRIBUTES_RGBA_1010102;
        } else {
            iArr = GlUtil.EGL_CONFIG_ATTRIBUTES_RGBA_8888;
        }
        Pair<EGLContext, EGLSurface> createFocusedEglContextWithFallback = createFocusedEglContextWithFallback(glObjectsProvider2, defaultEglDisplay, iArr);
        ColorInfo build = colorInfo.buildUpon().setColorTransfer(1).setHdrStaticInfo((byte[]) null).build();
        if (ColorInfo.isTransferHdr(colorInfo)) {
            int i3 = i;
        } else if (i != 2) {
            colorInfo2 = colorInfo;
            Objects.requireNonNull(listener2);
            Context context3 = context2;
            return new DefaultVideoFrameProcessor(context3, glObjectsProvider2, defaultEglDisplay, new InputSwitcher(context2, colorInfo2, glObjectsProvider2, videoFrameProcessingTaskExecutor2, executor, new DefaultVideoFrameProcessor$$ExternalSyntheticLambda4(listener2), i, z2, z3, z4), videoFrameProcessingTaskExecutor2, listener2, executor, new FinalShaderProgramWrapper(context3, defaultEglDisplay, (EGLContext) createFocusedEglContextWithFallback.first, (EGLSurface) createFocusedEglContextWithFallback.second, debugViewProvider, colorInfo, videoFrameProcessingTaskExecutor2, executor, listener2, listener3, i2, i, z), z, colorInfo);
        }
        colorInfo2 = build;
        Objects.requireNonNull(listener2);
        Context context32 = context2;
        return new DefaultVideoFrameProcessor(context32, glObjectsProvider2, defaultEglDisplay, new InputSwitcher(context2, colorInfo2, glObjectsProvider2, videoFrameProcessingTaskExecutor2, executor, new DefaultVideoFrameProcessor$$ExternalSyntheticLambda4(listener2), i, z2, z3, z4), videoFrameProcessingTaskExecutor2, listener2, executor, new FinalShaderProgramWrapper(context32, defaultEglDisplay, (EGLContext) createFocusedEglContextWithFallback.first, (EGLSurface) createFocusedEglContextWithFallback.second, debugViewProvider, colorInfo, videoFrameProcessingTaskExecutor2, executor, listener2, listener3, i2, i, z), z, colorInfo);
    }

    private static ImmutableList<GlShaderProgram> createGlShaderPrograms(Context context2, List<Effect> list, ColorInfo colorInfo, FinalShaderProgramWrapper finalShaderProgramWrapper2) throws VideoFrameProcessingException {
        ImmutableList.Builder builder = new ImmutableList.Builder();
        ImmutableList.Builder builder2 = new ImmutableList.Builder();
        ImmutableList.Builder builder3 = new ImmutableList.Builder();
        for (int i = 0; i < list.size(); i++) {
            Effect effect = list.get(i);
            Assertions.checkArgument(effect instanceof GlEffect, "DefaultVideoFrameProcessor only supports GlEffects");
            GlEffect glEffect = (GlEffect) effect;
            if (glEffect instanceof GlMatrixTransformation) {
                builder2.add((Object) (GlMatrixTransformation) glEffect);
            } else if (glEffect instanceof RgbMatrix) {
                builder3.add((Object) (RgbMatrix) glEffect);
            } else {
                ImmutableList build = builder2.build();
                ImmutableList build2 = builder3.build();
                boolean isTransferHdr = ColorInfo.isTransferHdr(colorInfo);
                if (!build.isEmpty() || !build2.isEmpty()) {
                    builder.add((Object) DefaultShaderProgram.create(context2, build, build2, isTransferHdr));
                    builder2 = new ImmutableList.Builder();
                    builder3 = new ImmutableList.Builder();
                }
                builder.add((Object) glEffect.toGlShaderProgram(context2, isTransferHdr));
            }
        }
        finalShaderProgramWrapper2.setMatrixTransformations(builder2.build(), builder3.build());
        return builder.build();
    }

    private static void chainShaderProgramsWithListeners(GlObjectsProvider glObjectsProvider2, List<GlShaderProgram> list, FinalShaderProgramWrapper finalShaderProgramWrapper2, VideoFrameProcessingTaskExecutor videoFrameProcessingTaskExecutor2, VideoFrameProcessor.Listener listener2, Executor executor) {
        ArrayList arrayList = new ArrayList(list);
        arrayList.add(finalShaderProgramWrapper2);
        int i = 0;
        while (i < arrayList.size() - 1) {
            GlShaderProgram glShaderProgram = (GlShaderProgram) arrayList.get(i);
            i++;
            GlShaderProgram glShaderProgram2 = (GlShaderProgram) arrayList.get(i);
            ChainingGlShaderProgramListener chainingGlShaderProgramListener = new ChainingGlShaderProgramListener(glObjectsProvider2, glShaderProgram, glShaderProgram2, videoFrameProcessingTaskExecutor2);
            glShaderProgram.setOutputListener(chainingGlShaderProgramListener);
            Objects.requireNonNull(listener2);
            glShaderProgram.setErrorListener(executor, new DefaultVideoFrameProcessor$$ExternalSyntheticLambda4(listener2));
            glShaderProgram2.setInputListener(chainingGlShaderProgramListener);
        }
    }

    private static String getInputTypeString(int i) {
        if (i == 1) {
            return "Surface";
        }
        if (i == 2) {
            return Registry.BUCKET_BITMAP;
        }
        if (i == 3) {
            return "Texture ID";
        }
        if (i == 4) {
            return "Surface with automatic frame registration";
        }
        throw new IllegalArgumentException(String.valueOf(i));
    }

    private void configureEffects(InputStreamInfo inputStreamInfo, boolean z) throws VideoFrameProcessingException {
        checkColors(inputStreamInfo.frameInfo.colorInfo, this.outputColorInfo);
        if (z || !this.activeEffects.equals(inputStreamInfo.effects)) {
            if (!this.intermediateGlShaderPrograms.isEmpty()) {
                for (int i = 0; i < this.intermediateGlShaderPrograms.size(); i++) {
                    this.intermediateGlShaderPrograms.get(i).release();
                }
                this.intermediateGlShaderPrograms.clear();
            }
            this.intermediateGlShaderPrograms.addAll(createGlShaderPrograms(this.context, inputStreamInfo.effects, this.outputColorInfo, this.finalShaderProgramWrapper));
            this.inputSwitcher.setDownstreamShaderProgram((GlShaderProgram) Iterables.getFirst(this.intermediateGlShaderPrograms, this.finalShaderProgramWrapper));
            chainShaderProgramsWithListeners(this.glObjectsProvider, this.intermediateGlShaderPrograms, this.finalShaderProgramWrapper, this.videoFrameProcessingTaskExecutor, this.listener, this.listenerExecutor);
            this.activeEffects.clear();
            this.activeEffects.addAll(inputStreamInfo.effects);
        }
        this.inputSwitcher.switchToInput(inputStreamInfo.inputType, inputStreamInfo.frameInfo);
        this.inputStreamRegisteredCondition.open();
        synchronized (this.lock) {
            Runnable runnable = this.onInputSurfaceReadyListener;
            if (runnable != null) {
                runnable.run();
                this.onInputSurfaceReadyListener = null;
            }
        }
        this.listenerExecutor.execute(new DefaultVideoFrameProcessor$$ExternalSyntheticLambda9(this, inputStreamInfo));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$configureEffects$5$androidx-media3-effect-DefaultVideoFrameProcessor  reason: not valid java name */
    public /* synthetic */ void m417lambda$configureEffects$5$androidxmedia3effectDefaultVideoFrameProcessor(InputStreamInfo inputStreamInfo) {
        this.listener.onInputStreamRegistered(inputStreamInfo.inputType, inputStreamInfo.effects, inputStreamInfo.frameInfo);
    }

    private static void checkColors(ColorInfo colorInfo, ColorInfo colorInfo2) throws VideoFrameProcessingException {
        boolean z = false;
        if (ColorInfo.isTransferHdr(colorInfo)) {
            Assertions.checkArgument(colorInfo.colorSpace == 6);
        }
        if (ColorInfo.isTransferHdr(colorInfo) || ColorInfo.isTransferHdr(colorInfo2)) {
            try {
                if (GlUtil.getContextMajorVersion() != 3) {
                    throw new VideoFrameProcessingException("OpenGL ES 3.0 context support is required for HDR input or output.");
                }
            } catch (GlUtil.GlException e) {
                throw VideoFrameProcessingException.from(e);
            }
        }
        Assertions.checkArgument(colorInfo.isDataSpaceValid());
        Assertions.checkArgument(colorInfo.colorTransfer != 1);
        Assertions.checkArgument(colorInfo2.isDataSpaceValid());
        Assertions.checkArgument(colorInfo2.colorTransfer != 1);
        if (ColorInfo.isTransferHdr(colorInfo) != ColorInfo.isTransferHdr(colorInfo2)) {
            if (isSupportedToneMapping(colorInfo, colorInfo2) || isUltraHdr(colorInfo, colorInfo2)) {
                z = true;
            }
            Assertions.checkArgument(z);
        }
    }

    private static boolean isSupportedToneMapping(ColorInfo colorInfo, ColorInfo colorInfo2) {
        return colorInfo.colorSpace == 6 && colorInfo2.colorSpace != 6 && ColorInfo.isTransferHdr(colorInfo) && (colorInfo2.colorTransfer == 10 || colorInfo2.colorTransfer == 3);
    }

    private static boolean isUltraHdr(ColorInfo colorInfo, ColorInfo colorInfo2) {
        return colorInfo.equals(ColorInfo.SRGB_BT709_FULL) && colorInfo2.colorSpace == 6 && ColorInfo.isTransferHdr(colorInfo2);
    }

    /* access modifiers changed from: private */
    public void releaseGlObjects() {
        try {
            this.inputSwitcher.release();
            for (int i = 0; i < this.intermediateGlShaderPrograms.size(); i++) {
                this.intermediateGlShaderPrograms.get(i).release();
            }
            this.finalShaderProgramWrapper.release();
        } catch (Exception e) {
            Log.e(TAG, "Error releasing shader program", e);
        } catch (Throwable th) {
            try {
                this.glObjectsProvider.release(this.eglDisplay);
            } catch (GlUtil.GlException e2) {
                Log.e(TAG, "Error releasing GL objects", e2);
            }
            throw th;
        }
        try {
            this.glObjectsProvider.release(this.eglDisplay);
        } catch (GlUtil.GlException e3) {
            Log.e(TAG, "Error releasing GL objects", e3);
        }
    }

    private static Pair<EGLContext, EGLSurface> createFocusedEglContextWithFallback(GlObjectsProvider glObjectsProvider2, EGLDisplay eGLDisplay, int[] iArr) throws GlUtil.GlException {
        try {
            return createFocusedEglContext(glObjectsProvider2, eGLDisplay, 3, iArr);
        } catch (GlUtil.GlException unused) {
            return createFocusedEglContext(glObjectsProvider2, eGLDisplay, 2, iArr);
        }
    }

    private static Pair<EGLContext, EGLSurface> createFocusedEglContext(GlObjectsProvider glObjectsProvider2, EGLDisplay eGLDisplay, int i, int[] iArr) throws GlUtil.GlException {
        EGLContext createEglContext = glObjectsProvider2.createEglContext(eGLDisplay, i, iArr);
        return Pair.create(createEglContext, glObjectsProvider2.createFocusedPlaceholderEglSurface(createEglContext, eGLDisplay));
    }

    private static final class InputStreamInfo {
        public final List<Effect> effects;
        public final FrameInfo frameInfo;
        public final int inputType;

        public InputStreamInfo(int i, List<Effect> list, FrameInfo frameInfo2) {
            this.inputType = i;
            this.effects = list;
            this.frameInfo = frameInfo2;
        }
    }
}
