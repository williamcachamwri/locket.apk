package androidx.media3.effect;

import android.graphics.SurfaceTexture;
import android.view.Surface;
import androidx.media3.common.C;
import androidx.media3.common.FrameInfo;
import androidx.media3.common.GlObjectsProvider;
import androidx.media3.common.GlTextureInfo;
import androidx.media3.common.VideoFrameProcessingException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.GlUtil;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

final class ExternalTextureManager extends TextureManager {
    private static final int[] ADDITIONAL_CANDIDATE_BUFFER_SIZE_GUESSES = {1920, 1088};
    private static final float EPSILON = 1.0E-9f;
    private static final long SURFACE_TEXTURE_TIMEOUT_MS = (Util.isRunningOnEmulator() ? 20000 : 500);
    private static final String TAG = "ExtTexMgr";
    private static final String TIMER_THREAD_NAME = "ExtTexMgr:Timer";
    private static final int[] TRANSFORMATION_MATRIX_EXPECTED_ZERO_INDICES = {2, 3, 6, 7, 8, 9, 11, 14};
    private int availableFrameCount;
    private FrameInfo currentFrame;
    private boolean currentInputStreamEnded;
    private final boolean experimentalAdjustSurfaceTextureTransformationMatrix;
    private ExternalShaderProgram externalShaderProgram;
    private int externalShaderProgramInputCapacity;
    private final int externalTexId;
    private Future<?> forceSignalEndOfStreamFuture;
    private final GlObjectsProvider glObjectsProvider;
    private FrameInfo lastRegisteredFrame;
    private final Queue<FrameInfo> pendingFrames;
    private volatile RuntimeException releaseAllFramesException;
    private CountDownLatch releaseAllFramesLatch;
    private boolean repeatLastRegisteredFrame;
    private final ScheduledExecutorService scheduledExecutorService;
    private volatile boolean shouldRejectIncomingFrames;
    private final Surface surface;
    private final SurfaceTexture surfaceTexture;
    private final float[] textureTransformMatrix;

    public ExternalTextureManager(GlObjectsProvider glObjectsProvider2, VideoFrameProcessingTaskExecutor videoFrameProcessingTaskExecutor, boolean z, boolean z2) throws VideoFrameProcessingException {
        super(videoFrameProcessingTaskExecutor);
        this.glObjectsProvider = glObjectsProvider2;
        this.repeatLastRegisteredFrame = z;
        this.experimentalAdjustSurfaceTextureTransformationMatrix = z2;
        try {
            int createExternalTexture = GlUtil.createExternalTexture();
            this.externalTexId = createExternalTexture;
            SurfaceTexture surfaceTexture2 = new SurfaceTexture(createExternalTexture);
            this.surfaceTexture = surfaceTexture2;
            this.textureTransformMatrix = new float[16];
            this.pendingFrames = new ConcurrentLinkedQueue();
            this.scheduledExecutorService = Util.newSingleThreadScheduledExecutor(TIMER_THREAD_NAME);
            surfaceTexture2.setOnFrameAvailableListener(new ExternalTextureManager$$ExternalSyntheticLambda4(this, videoFrameProcessingTaskExecutor));
            this.surface = new Surface(surfaceTexture2);
        } catch (GlUtil.GlException e) {
            throw new VideoFrameProcessingException((Throwable) e);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$new$1$androidx-media3-effect-ExternalTextureManager  reason: not valid java name */
    public /* synthetic */ void m426lambda$new$1$androidxmedia3effectExternalTextureManager(VideoFrameProcessingTaskExecutor videoFrameProcessingTaskExecutor, SurfaceTexture surfaceTexture2) {
        videoFrameProcessingTaskExecutor.submit(new ExternalTextureManager$$ExternalSyntheticLambda6(this), false);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$new$0$androidx-media3-effect-ExternalTextureManager  reason: not valid java name */
    public /* synthetic */ void m425lambda$new$0$androidxmedia3effectExternalTextureManager() throws VideoFrameProcessingException, GlUtil.GlException {
        DebugTraceUtil.logEvent(DebugTraceUtil.COMPONENT_VFP, DebugTraceUtil.EVENT_SURFACE_TEXTURE_INPUT, C.TIME_UNSET);
        if (this.shouldRejectIncomingFrames) {
            this.surfaceTexture.updateTexImage();
            this.pendingFrames.poll();
            if (this.releaseAllFramesLatch != null && this.pendingFrames.isEmpty()) {
                this.releaseAllFramesLatch.countDown();
            }
            Log.w(TAG, "Dropping frame received on SurfaceTexture: " + (this.surfaceTexture.getTimestamp() / 1000));
            return;
        }
        if (this.currentInputStreamEnded) {
            restartForceSignalEndOfStreamTimer();
        }
        this.availableFrameCount++;
        maybeQueueFrameToExternalShaderProgram();
    }

    public void setSamplingGlShaderProgram(GlShaderProgram glShaderProgram) {
        Assertions.checkState(glShaderProgram instanceof ExternalShaderProgram);
        this.videoFrameProcessingTaskExecutor.submit(new ExternalTextureManager$$ExternalSyntheticLambda2(this, glShaderProgram));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setSamplingGlShaderProgram$2$androidx-media3-effect-ExternalTextureManager  reason: not valid java name */
    public /* synthetic */ void m432lambda$setSamplingGlShaderProgram$2$androidxmedia3effectExternalTextureManager(GlShaderProgram glShaderProgram) throws VideoFrameProcessingException, GlUtil.GlException {
        this.externalShaderProgramInputCapacity = 0;
        this.externalShaderProgram = (ExternalShaderProgram) glShaderProgram;
    }

    public void setDefaultBufferSize(int i, int i2) {
        this.surfaceTexture.setDefaultBufferSize(i, i2);
    }

    public Surface getInputSurface() {
        return this.surface;
    }

    public void onReadyToAcceptInputFrame() {
        this.videoFrameProcessingTaskExecutor.submit(new ExternalTextureManager$$ExternalSyntheticLambda9(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onReadyToAcceptInputFrame$3$androidx-media3-effect-ExternalTextureManager  reason: not valid java name */
    public /* synthetic */ void m428lambda$onReadyToAcceptInputFrame$3$androidxmedia3effectExternalTextureManager() throws VideoFrameProcessingException, GlUtil.GlException {
        this.externalShaderProgramInputCapacity++;
        maybeQueueFrameToExternalShaderProgram();
    }

    public void onInputFrameProcessed(GlTextureInfo glTextureInfo) {
        this.videoFrameProcessingTaskExecutor.submit(new ExternalTextureManager$$ExternalSyntheticLambda7(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onInputFrameProcessed$4$androidx-media3-effect-ExternalTextureManager  reason: not valid java name */
    public /* synthetic */ void m427lambda$onInputFrameProcessed$4$androidxmedia3effectExternalTextureManager() throws VideoFrameProcessingException, GlUtil.GlException {
        this.currentFrame = null;
        if (!this.currentInputStreamEnded || !this.pendingFrames.isEmpty()) {
            maybeQueueFrameToExternalShaderProgram();
            return;
        }
        this.currentInputStreamEnded = false;
        ((ExternalShaderProgram) Assertions.checkNotNull(this.externalShaderProgram)).signalEndOfCurrentInputStream();
        DebugTraceUtil.logEvent(DebugTraceUtil.COMPONENT_EXTERNAL_TEXTURE_MANAGER, DebugTraceUtil.EVENT_SIGNAL_EOS, Long.MIN_VALUE);
        cancelForceSignalEndOfStreamTimer();
    }

    public void setInputFrameInfo(FrameInfo frameInfo, boolean z) {
        this.repeatLastRegisteredFrame = z;
        if (z) {
            this.lastRegisteredFrame = frameInfo;
            this.surfaceTexture.setDefaultBufferSize(frameInfo.width, frameInfo.height);
        }
    }

    public void registerInputFrame(FrameInfo frameInfo) {
        this.lastRegisteredFrame = frameInfo;
        if (!this.repeatLastRegisteredFrame) {
            this.pendingFrames.add(frameInfo);
        }
        this.videoFrameProcessingTaskExecutor.submit(new ExternalTextureManager$$ExternalSyntheticLambda8(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$registerInputFrame$5$androidx-media3-effect-ExternalTextureManager  reason: not valid java name */
    public /* synthetic */ void m429lambda$registerInputFrame$5$androidxmedia3effectExternalTextureManager() throws VideoFrameProcessingException, GlUtil.GlException {
        this.shouldRejectIncomingFrames = false;
    }

    public int getPendingFrameCount() {
        return this.pendingFrames.size();
    }

    public void signalEndOfCurrentInputStream() {
        this.videoFrameProcessingTaskExecutor.submit(new ExternalTextureManager$$ExternalSyntheticLambda1(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$signalEndOfCurrentInputStream$6$androidx-media3-effect-ExternalTextureManager  reason: not valid java name */
    public /* synthetic */ void m433lambda$signalEndOfCurrentInputStream$6$androidxmedia3effectExternalTextureManager() throws VideoFrameProcessingException, GlUtil.GlException {
        if (!this.pendingFrames.isEmpty() || this.currentFrame != null) {
            this.currentInputStreamEnded = true;
            restartForceSignalEndOfStreamTimer();
            return;
        }
        ((ExternalShaderProgram) Assertions.checkNotNull(this.externalShaderProgram)).signalEndOfCurrentInputStream();
        DebugTraceUtil.logEvent(DebugTraceUtil.COMPONENT_EXTERNAL_TEXTURE_MANAGER, DebugTraceUtil.EVENT_SIGNAL_EOS, Long.MIN_VALUE);
        cancelForceSignalEndOfStreamTimer();
    }

    public void release() {
        this.surfaceTexture.release();
        this.surface.release();
        this.scheduledExecutorService.shutdownNow();
    }

    /* access modifiers changed from: protected */
    public void flush() throws VideoFrameProcessingException {
        this.externalShaderProgramInputCapacity = 0;
        this.currentFrame = null;
        this.pendingFrames.clear();
        this.lastRegisteredFrame = null;
        super.flush();
    }

    public void dropIncomingRegisteredFrames() {
        this.shouldRejectIncomingFrames = true;
    }

    public void releaseAllRegisteredFrames() {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        this.releaseAllFramesLatch = countDownLatch;
        this.videoFrameProcessingTaskExecutor.submit(new ExternalTextureManager$$ExternalSyntheticLambda0(this));
        try {
            if (!countDownLatch.await(SURFACE_TEXTURE_TIMEOUT_MS, TimeUnit.MILLISECONDS)) {
                Log.w(TAG, "Timeout reached while waiting for latch to be unblocked.");
            }
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
            Log.w(TAG, "Interrupted when waiting for MediaCodec frames to arrive.");
        }
        this.releaseAllFramesLatch = null;
        if (this.releaseAllFramesException != null) {
            throw this.releaseAllFramesException;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$releaseAllRegisteredFrames$7$androidx-media3-effect-ExternalTextureManager  reason: not valid java name */
    public /* synthetic */ void m430lambda$releaseAllRegisteredFrames$7$androidxmedia3effectExternalTextureManager() throws VideoFrameProcessingException, GlUtil.GlException {
        try {
            removeAllSurfaceTextureFrames();
        } catch (RuntimeException e) {
            this.releaseAllFramesException = e;
            Log.e(TAG, "Failed to remove texture frames", e);
            if (this.releaseAllFramesLatch != null) {
                this.releaseAllFramesLatch.countDown();
            }
        }
    }

    private void restartForceSignalEndOfStreamTimer() {
        cancelForceSignalEndOfStreamTimer();
        this.forceSignalEndOfStreamFuture = this.scheduledExecutorService.schedule(new ExternalTextureManager$$ExternalSyntheticLambda5(this), SURFACE_TEXTURE_TIMEOUT_MS, TimeUnit.MILLISECONDS);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$restartForceSignalEndOfStreamTimer$8$androidx-media3-effect-ExternalTextureManager  reason: not valid java name */
    public /* synthetic */ void m431lambda$restartForceSignalEndOfStreamTimer$8$androidxmedia3effectExternalTextureManager() {
        this.videoFrameProcessingTaskExecutor.submit(new ExternalTextureManager$$ExternalSyntheticLambda3(this));
    }

    private void cancelForceSignalEndOfStreamTimer() {
        Future<?> future = this.forceSignalEndOfStreamFuture;
        if (future != null) {
            future.cancel(false);
        }
        this.forceSignalEndOfStreamFuture = null;
    }

    /* access modifiers changed from: private */
    public void forceSignalEndOfStream() {
        if (this.availableFrameCount != this.pendingFrames.size()) {
            Log.w(TAG, Util.formatInvariant("Forcing EOS after missing %d frames for %d ms, with available frame count: %d", Integer.valueOf(this.pendingFrames.size()), Long.valueOf(SURFACE_TEXTURE_TIMEOUT_MS), Integer.valueOf(this.availableFrameCount)));
            this.currentInputStreamEnded = false;
            this.currentFrame = null;
            this.shouldRejectIncomingFrames = true;
            removeAllSurfaceTextureFrames();
            this.pendingFrames.clear();
            signalEndOfCurrentInputStream();
        }
    }

    private void removeAllSurfaceTextureFrames() {
        while (true) {
            int i = this.availableFrameCount;
            if (i <= 0) {
                break;
            }
            this.availableFrameCount = i - 1;
            this.surfaceTexture.updateTexImage();
            this.pendingFrames.remove();
        }
        if (this.releaseAllFramesLatch != null && this.pendingFrames.isEmpty()) {
            this.releaseAllFramesLatch.countDown();
        }
    }

    private void maybeQueueFrameToExternalShaderProgram() {
        if (this.externalShaderProgramInputCapacity != 0 && this.availableFrameCount != 0 && this.currentFrame == null) {
            this.surfaceTexture.updateTexImage();
            this.availableFrameCount--;
            FrameInfo frameInfo = (FrameInfo) (this.repeatLastRegisteredFrame ? Assertions.checkNotNull(this.lastRegisteredFrame) : this.pendingFrames.element());
            this.currentFrame = frameInfo;
            this.externalShaderProgramInputCapacity--;
            this.surfaceTexture.getTransformMatrix(this.textureTransformMatrix);
            long timestamp = (this.surfaceTexture.getTimestamp() / 1000) + frameInfo.offsetToAddUs;
            if (this.experimentalAdjustSurfaceTextureTransformationMatrix) {
                removeSurfaceTextureScaleFromTransformMatrix(this.textureTransformMatrix, timestamp, frameInfo.width, frameInfo.height);
            }
            ((ExternalShaderProgram) Assertions.checkNotNull(this.externalShaderProgram)).setTextureTransformMatrix(this.textureTransformMatrix);
            ((ExternalShaderProgram) Assertions.checkNotNull(this.externalShaderProgram)).queueInputFrame(this.glObjectsProvider, new GlTextureInfo(this.externalTexId, -1, -1, frameInfo.width, frameInfo.height), timestamp);
            if (!this.repeatLastRegisteredFrame) {
                Assertions.checkStateNotNull(this.pendingFrames.remove());
            }
            DebugTraceUtil.logEvent(DebugTraceUtil.COMPONENT_VFP, DebugTraceUtil.EVENT_QUEUE_FRAME, timestamp);
        }
    }

    private static void removeSurfaceTextureScaleFromTransformMatrix(float[] fArr, long j, int i, int i2) {
        char c;
        char c2;
        float[] fArr2 = fArr;
        boolean z = true;
        boolean z2 = (fArr2.length != 16) | false;
        int[] iArr = TRANSFORMATION_MATRIX_EXPECTED_ZERO_INDICES;
        int length = iArr.length;
        for (int i3 = 0; i3 < length; i3++) {
            z2 |= Math.abs(fArr2[iArr[i3]]) > EPSILON;
        }
        boolean z3 = z2 | (Math.abs(fArr2[10] - 1.0f) > EPSILON) | (Math.abs(fArr2[15] - 1.0f) > EPSILON);
        char c3 = 12;
        char c4 = 4;
        if (Math.abs(fArr2[0]) > EPSILON && Math.abs(fArr2[5]) > EPSILON) {
            boolean z4 = z3 | (Math.abs(fArr2[1]) > EPSILON);
            if (Math.abs(fArr2[4]) <= EPSILON) {
                z = false;
            }
            z |= z4;
            c2 = 13;
            c4 = 5;
            c = 0;
        } else if (Math.abs(fArr2[1]) <= EPSILON || Math.abs(fArr2[4]) <= EPSILON) {
            c = 65535;
            c2 = 65535;
            c3 = 65535;
            c4 = 65535;
        } else {
            z = z3 | (Math.abs(fArr2[0]) > EPSILON) | (Math.abs(fArr2[5]) > EPSILON);
            c2 = 12;
            c3 = 13;
            c = 1;
        }
        if (z) {
            DebugTraceUtil.logEvent(DebugTraceUtil.COMPONENT_EXTERNAL_TEXTURE_MANAGER, DebugTraceUtil.EVENT_SURFACE_TEXTURE_TRANSFORM_FIX, j, "Unable to apply SurfaceTexture fix", new Object[0]);
            return;
        }
        float f = fArr2[c];
        float f2 = fArr2[c3];
        if (Math.abs(f) + EPSILON < 1.0f) {
            float copySign = Math.copySign(guessScaleWithoutSurfaceTextureTrim(Math.abs(f), i), f);
            DebugTraceUtil.logEvent(DebugTraceUtil.COMPONENT_EXTERNAL_TEXTURE_MANAGER, DebugTraceUtil.EVENT_SURFACE_TEXTURE_TRANSFORM_FIX, j, "Width scale adjusted.", new Object[0]);
            fArr2[c] = copySign;
            fArr2[c3] = ((f - copySign) * 0.5f) + f2;
        }
        float f3 = fArr2[c4];
        float f4 = fArr2[c2];
        if (Math.abs(f3) + EPSILON < 1.0f) {
            float copySign2 = Math.copySign(guessScaleWithoutSurfaceTextureTrim(Math.abs(f3), i2), f3);
            DebugTraceUtil.logEvent(DebugTraceUtil.COMPONENT_EXTERNAL_TEXTURE_MANAGER, DebugTraceUtil.EVENT_SURFACE_TEXTURE_TRANSFORM_FIX, j, "Height scale adjusted.", new Object[0]);
            fArr2[c4] = copySign2;
            fArr2[c2] = ((f3 - copySign2) * 0.5f) + f4;
        }
    }

    private static float guessScaleWithoutSurfaceTextureTrim(float f, int i) {
        int i2 = i;
        for (int i3 = 2; i3 <= 256; i3 *= 2) {
            int i4 = (((i + i3) - 1) / i3) * i3;
            if (scoreForCandidateBufferSize(i4, f, i) < scoreForCandidateBufferSize(i2, f, i)) {
                i2 = i4;
            }
        }
        for (int i5 : ADDITIONAL_CANDIDATE_BUFFER_SIZE_GUESSES) {
            if (i5 >= i && scoreForCandidateBufferSize(i5, f, i) < scoreForCandidateBufferSize(i2, f, i)) {
                i2 = i5;
            }
        }
        return scoreForCandidateBufferSize(i2, f, i) > EPSILON ? f : ((float) i) / ((float) i2);
    }

    private static float scoreForCandidateBufferSize(int i, float f, int i2) {
        float f2 = 1.0f;
        for (int i3 = 0; i3 <= 2; i3++) {
            float f3 = ((((float) i2) - ((float) i3)) / ((float) i)) - f;
            if (Math.abs(f3) < f2) {
                f2 = Math.abs(f3);
            }
        }
        return f2;
    }
}
