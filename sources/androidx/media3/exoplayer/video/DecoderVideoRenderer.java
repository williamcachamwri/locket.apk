package androidx.media3.exoplayer.video;

import android.media.MediaFormat;
import android.os.Handler;
import android.os.SystemClock;
import android.view.Surface;
import androidx.media3.common.C;
import androidx.media3.common.Format;
import androidx.media3.common.VideoSize;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.TimedValueQueue;
import androidx.media3.common.util.TraceUtil;
import androidx.media3.common.util.Util;
import androidx.media3.decoder.CryptoConfig;
import androidx.media3.decoder.Decoder;
import androidx.media3.decoder.DecoderException;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.decoder.VideoDecoderOutputBuffer;
import androidx.media3.exoplayer.BaseRenderer;
import androidx.media3.exoplayer.DecoderCounters;
import androidx.media3.exoplayer.DecoderReuseEvaluation;
import androidx.media3.exoplayer.ExoPlaybackException;
import androidx.media3.exoplayer.FormatHolder;
import androidx.media3.exoplayer.audio.SilenceSkippingAudioProcessor;
import androidx.media3.exoplayer.drm.DrmSession;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.video.VideoRendererEventListener;

public abstract class DecoderVideoRenderer extends BaseRenderer {
    private static final int REINITIALIZATION_STATE_NONE = 0;
    private static final int REINITIALIZATION_STATE_SIGNAL_END_OF_STREAM = 1;
    private static final int REINITIALIZATION_STATE_WAIT_END_OF_STREAM = 2;
    private static final String TAG = "DecoderVideoRenderer";
    private final long allowedJoiningTimeMs;
    private int buffersInCodecCount;
    private int consecutiveDroppedFrameCount;
    private Decoder<DecoderInputBuffer, ? extends VideoDecoderOutputBuffer, ? extends DecoderException> decoder;
    protected DecoderCounters decoderCounters;
    private DrmSession decoderDrmSession;
    private boolean decoderReceivedBuffers;
    private int decoderReinitializationState;
    private long droppedFrameAccumulationStartTimeMs;
    private int droppedFrames;
    private final VideoRendererEventListener.EventDispatcher eventDispatcher;
    private int firstFrameState;
    private final DecoderInputBuffer flagsOnlyBuffer = DecoderInputBuffer.newNoDataInstance();
    private final TimedValueQueue<Format> formatQueue = new TimedValueQueue<>();
    private VideoFrameMetadataListener frameMetadataListener;
    private long initialPositionUs;
    private DecoderInputBuffer inputBuffer;
    private Format inputFormat;
    private boolean inputStreamEnded;
    private long joiningDeadlineMs = C.TIME_UNSET;
    private long lastRenderTimeUs;
    private final int maxDroppedFramesToNotify;
    private Object output;
    private VideoDecoderOutputBuffer outputBuffer;
    private VideoDecoderOutputBufferRenderer outputBufferRenderer;
    private Format outputFormat;
    private int outputMode;
    private boolean outputStreamEnded;
    private Surface outputSurface;
    private VideoSize reportedVideoSize;
    private DrmSession sourceDrmSession;
    private boolean waitingForFirstSampleInFormat;

    private static boolean isBufferLate(long j) {
        return j < -30000;
    }

    private static boolean isBufferVeryLate(long j) {
        return j < -500000;
    }

    /* access modifiers changed from: protected */
    public abstract Decoder<DecoderInputBuffer, ? extends VideoDecoderOutputBuffer, ? extends DecoderException> createDecoder(Format format, CryptoConfig cryptoConfig) throws DecoderException;

    /* access modifiers changed from: protected */
    public void onQueueInputBuffer(DecoderInputBuffer decoderInputBuffer) {
    }

    /* access modifiers changed from: protected */
    public abstract void renderOutputBufferToSurface(VideoDecoderOutputBuffer videoDecoderOutputBuffer, Surface surface) throws DecoderException;

    /* access modifiers changed from: protected */
    public abstract void setDecoderOutputMode(int i);

    protected DecoderVideoRenderer(long j, Handler handler, VideoRendererEventListener videoRendererEventListener, int i) {
        super(2);
        this.allowedJoiningTimeMs = j;
        this.maxDroppedFramesToNotify = i;
        this.eventDispatcher = new VideoRendererEventListener.EventDispatcher(handler, videoRendererEventListener);
        this.decoderReinitializationState = 0;
        this.outputMode = -1;
        this.firstFrameState = 0;
        this.decoderCounters = new DecoderCounters();
    }

    public void render(long j, long j2) throws ExoPlaybackException {
        if (!this.outputStreamEnded) {
            if (this.inputFormat == null) {
                FormatHolder formatHolder = getFormatHolder();
                this.flagsOnlyBuffer.clear();
                int readSource = readSource(formatHolder, this.flagsOnlyBuffer, 2);
                if (readSource == -5) {
                    onInputFormatChanged(formatHolder);
                } else if (readSource == -4) {
                    Assertions.checkState(this.flagsOnlyBuffer.isEndOfStream());
                    this.inputStreamEnded = true;
                    this.outputStreamEnded = true;
                    return;
                } else {
                    return;
                }
            }
            maybeInitDecoder();
            if (this.decoder != null) {
                try {
                    TraceUtil.beginSection("drainAndFeed");
                    while (drainOutputBuffer(j, j2)) {
                    }
                    while (feedInputBuffer()) {
                    }
                    TraceUtil.endSection();
                    this.decoderCounters.ensureUpdated();
                } catch (DecoderException e) {
                    Log.e(TAG, "Video codec error", e);
                    this.eventDispatcher.videoCodecError(e);
                    throw createRendererException(e, this.inputFormat, 4003);
                }
            }
        }
    }

    public boolean isEnded() {
        return this.outputStreamEnded;
    }

    public boolean isReady() {
        if (this.inputFormat != null && ((isSourceReady() || this.outputBuffer != null) && (this.firstFrameState == 3 || !hasOutput()))) {
            this.joiningDeadlineMs = C.TIME_UNSET;
            return true;
        } else if (this.joiningDeadlineMs == C.TIME_UNSET) {
            return false;
        } else {
            if (SystemClock.elapsedRealtime() < this.joiningDeadlineMs) {
                return true;
            }
            this.joiningDeadlineMs = C.TIME_UNSET;
            return false;
        }
    }

    public void handleMessage(int i, Object obj) throws ExoPlaybackException {
        if (i == 1) {
            setOutput(obj);
        } else if (i == 7) {
            this.frameMetadataListener = (VideoFrameMetadataListener) obj;
        } else {
            super.handleMessage(i, obj);
        }
    }

    /* access modifiers changed from: protected */
    public void onEnabled(boolean z, boolean z2) throws ExoPlaybackException {
        DecoderCounters decoderCounters2 = new DecoderCounters();
        this.decoderCounters = decoderCounters2;
        this.eventDispatcher.enabled(decoderCounters2);
        this.firstFrameState = z2 ? 1 : 0;
    }

    public void enableMayRenderStartOfStream() {
        if (this.firstFrameState == 0) {
            this.firstFrameState = 1;
        }
    }

    /* access modifiers changed from: protected */
    public void onPositionReset(long j, boolean z) throws ExoPlaybackException {
        this.inputStreamEnded = false;
        this.outputStreamEnded = false;
        lowerFirstFrameState(1);
        this.initialPositionUs = C.TIME_UNSET;
        this.consecutiveDroppedFrameCount = 0;
        if (this.decoder != null) {
            flushDecoder();
        }
        if (z) {
            setJoiningDeadlineMs();
        } else {
            this.joiningDeadlineMs = C.TIME_UNSET;
        }
        this.formatQueue.clear();
    }

    /* access modifiers changed from: protected */
    public void onStarted() {
        this.droppedFrames = 0;
        this.droppedFrameAccumulationStartTimeMs = SystemClock.elapsedRealtime();
        this.lastRenderTimeUs = Util.msToUs(SystemClock.elapsedRealtime());
    }

    /* access modifiers changed from: protected */
    public void onStopped() {
        this.joiningDeadlineMs = C.TIME_UNSET;
        maybeNotifyDroppedFrames();
    }

    /* access modifiers changed from: protected */
    public void onDisabled() {
        this.inputFormat = null;
        this.reportedVideoSize = null;
        lowerFirstFrameState(0);
        try {
            setSourceDrmSession((DrmSession) null);
            releaseDecoder();
        } finally {
            this.eventDispatcher.disabled(this.decoderCounters);
        }
    }

    /* access modifiers changed from: protected */
    public void onStreamChanged(Format[] formatArr, long j, long j2, MediaSource.MediaPeriodId mediaPeriodId) throws ExoPlaybackException {
        super.onStreamChanged(formatArr, j, j2, mediaPeriodId);
    }

    /* access modifiers changed from: protected */
    public void flushDecoder() throws ExoPlaybackException {
        this.buffersInCodecCount = 0;
        if (this.decoderReinitializationState != 0) {
            releaseDecoder();
            maybeInitDecoder();
            return;
        }
        this.inputBuffer = null;
        VideoDecoderOutputBuffer videoDecoderOutputBuffer = this.outputBuffer;
        if (videoDecoderOutputBuffer != null) {
            videoDecoderOutputBuffer.release();
            this.outputBuffer = null;
        }
        Decoder decoder2 = (Decoder) Assertions.checkNotNull(this.decoder);
        decoder2.flush();
        decoder2.setOutputStartTimeUs(getLastResetPositionUs());
        this.decoderReceivedBuffers = false;
    }

    /* access modifiers changed from: protected */
    public void releaseDecoder() {
        this.inputBuffer = null;
        this.outputBuffer = null;
        this.decoderReinitializationState = 0;
        this.decoderReceivedBuffers = false;
        this.buffersInCodecCount = 0;
        if (this.decoder != null) {
            this.decoderCounters.decoderReleaseCount++;
            this.decoder.release();
            this.eventDispatcher.decoderReleased(this.decoder.getName());
            this.decoder = null;
        }
        setDecoderDrmSession((DrmSession) null);
    }

    /* access modifiers changed from: protected */
    public void onInputFormatChanged(FormatHolder formatHolder) throws ExoPlaybackException {
        DecoderReuseEvaluation decoderReuseEvaluation;
        this.waitingForFirstSampleInFormat = true;
        Format format = (Format) Assertions.checkNotNull(formatHolder.format);
        setSourceDrmSession(formatHolder.drmSession);
        Format format2 = this.inputFormat;
        this.inputFormat = format;
        Decoder<DecoderInputBuffer, ? extends VideoDecoderOutputBuffer, ? extends DecoderException> decoder2 = this.decoder;
        if (decoder2 == null) {
            maybeInitDecoder();
            this.eventDispatcher.inputFormatChanged((Format) Assertions.checkNotNull(this.inputFormat), (DecoderReuseEvaluation) null);
            return;
        }
        if (this.sourceDrmSession != this.decoderDrmSession) {
            decoderReuseEvaluation = new DecoderReuseEvaluation(this.decoder.getName(), (Format) Assertions.checkNotNull(format2), format, 0, 128);
        } else {
            decoderReuseEvaluation = canReuseDecoder(decoder2.getName(), (Format) Assertions.checkNotNull(format2), format);
        }
        if (decoderReuseEvaluation.result == 0) {
            if (this.decoderReceivedBuffers) {
                this.decoderReinitializationState = 1;
            } else {
                releaseDecoder();
                maybeInitDecoder();
            }
        }
        this.eventDispatcher.inputFormatChanged((Format) Assertions.checkNotNull(this.inputFormat), decoderReuseEvaluation);
    }

    /* access modifiers changed from: protected */
    public void onProcessedOutputBuffer(long j) {
        this.buffersInCodecCount--;
    }

    /* access modifiers changed from: protected */
    public boolean shouldDropOutputBuffer(long j, long j2) {
        return isBufferLate(j);
    }

    /* access modifiers changed from: protected */
    public boolean shouldDropBuffersToKeyframe(long j, long j2) {
        return isBufferVeryLate(j);
    }

    /* access modifiers changed from: protected */
    public boolean shouldForceRenderOutputBuffer(long j, long j2) {
        return isBufferLate(j) && j2 > SilenceSkippingAudioProcessor.DEFAULT_MINIMUM_SILENCE_DURATION_US;
    }

    /* access modifiers changed from: protected */
    public void skipOutputBuffer(VideoDecoderOutputBuffer videoDecoderOutputBuffer) {
        this.decoderCounters.skippedOutputBufferCount++;
        videoDecoderOutputBuffer.release();
    }

    /* access modifiers changed from: protected */
    public void dropOutputBuffer(VideoDecoderOutputBuffer videoDecoderOutputBuffer) {
        updateDroppedBufferCounters(0, 1);
        videoDecoderOutputBuffer.release();
    }

    /* access modifiers changed from: protected */
    public boolean maybeDropBuffersToKeyframe(long j) throws ExoPlaybackException {
        int skipSource = skipSource(j);
        if (skipSource == 0) {
            return false;
        }
        this.decoderCounters.droppedToKeyframeCount++;
        updateDroppedBufferCounters(skipSource, this.buffersInCodecCount);
        flushDecoder();
        return true;
    }

    /* access modifiers changed from: protected */
    public void updateDroppedBufferCounters(int i, int i2) {
        this.decoderCounters.droppedInputBufferCount += i;
        int i3 = i + i2;
        this.decoderCounters.droppedBufferCount += i3;
        this.droppedFrames += i3;
        int i4 = this.consecutiveDroppedFrameCount + i3;
        this.consecutiveDroppedFrameCount = i4;
        DecoderCounters decoderCounters2 = this.decoderCounters;
        decoderCounters2.maxConsecutiveDroppedBufferCount = Math.max(i4, decoderCounters2.maxConsecutiveDroppedBufferCount);
        int i5 = this.maxDroppedFramesToNotify;
        if (i5 > 0 && this.droppedFrames >= i5) {
            maybeNotifyDroppedFrames();
        }
    }

    /* access modifiers changed from: protected */
    public void renderOutputBuffer(VideoDecoderOutputBuffer videoDecoderOutputBuffer, long j, Format format) throws DecoderException {
        VideoFrameMetadataListener videoFrameMetadataListener = this.frameMetadataListener;
        if (videoFrameMetadataListener != null) {
            videoFrameMetadataListener.onVideoFrameAboutToBeRendered(j, getClock().nanoTime(), format, (MediaFormat) null);
        }
        this.lastRenderTimeUs = Util.msToUs(SystemClock.elapsedRealtime());
        int i = videoDecoderOutputBuffer.mode;
        boolean z = i == 1 && this.outputSurface != null;
        boolean z2 = i == 0 && this.outputBufferRenderer != null;
        if (z2 || z) {
            maybeNotifyVideoSizeChanged(videoDecoderOutputBuffer.width, videoDecoderOutputBuffer.height);
            if (z2) {
                ((VideoDecoderOutputBufferRenderer) Assertions.checkNotNull(this.outputBufferRenderer)).setOutputBuffer(videoDecoderOutputBuffer);
            } else {
                renderOutputBufferToSurface(videoDecoderOutputBuffer, (Surface) Assertions.checkNotNull(this.outputSurface));
            }
            this.consecutiveDroppedFrameCount = 0;
            this.decoderCounters.renderedOutputBufferCount++;
            maybeNotifyRenderedFirstFrame();
            return;
        }
        dropOutputBuffer(videoDecoderOutputBuffer);
    }

    /* access modifiers changed from: protected */
    public final void setOutput(Object obj) {
        if (obj instanceof Surface) {
            this.outputSurface = (Surface) obj;
            this.outputBufferRenderer = null;
            this.outputMode = 1;
        } else if (obj instanceof VideoDecoderOutputBufferRenderer) {
            this.outputSurface = null;
            this.outputBufferRenderer = (VideoDecoderOutputBufferRenderer) obj;
            this.outputMode = 0;
        } else {
            this.outputSurface = null;
            this.outputBufferRenderer = null;
            this.outputMode = -1;
            obj = null;
        }
        if (this.output != obj) {
            this.output = obj;
            if (obj != null) {
                if (this.decoder != null) {
                    setDecoderOutputMode(this.outputMode);
                }
                onOutputChanged();
                return;
            }
            onOutputRemoved();
        } else if (obj != null) {
            onOutputReset();
        }
    }

    /* access modifiers changed from: protected */
    public DecoderReuseEvaluation canReuseDecoder(String str, Format format, Format format2) {
        return new DecoderReuseEvaluation(str, format, format2, 0, 1);
    }

    private void setSourceDrmSession(DrmSession drmSession) {
        DrmSession.replaceSession(this.sourceDrmSession, drmSession);
        this.sourceDrmSession = drmSession;
    }

    private void setDecoderDrmSession(DrmSession drmSession) {
        DrmSession.replaceSession(this.decoderDrmSession, drmSession);
        this.decoderDrmSession = drmSession;
    }

    private void maybeInitDecoder() throws ExoPlaybackException {
        CryptoConfig cryptoConfig;
        if (this.decoder == null) {
            setDecoderDrmSession(this.sourceDrmSession);
            DrmSession drmSession = this.decoderDrmSession;
            if (drmSession != null) {
                cryptoConfig = drmSession.getCryptoConfig();
                if (cryptoConfig == null && this.decoderDrmSession.getError() == null) {
                    return;
                }
            } else {
                cryptoConfig = null;
            }
            try {
                long elapsedRealtime = SystemClock.elapsedRealtime();
                Decoder<DecoderInputBuffer, ? extends VideoDecoderOutputBuffer, ? extends DecoderException> createDecoder = createDecoder((Format) Assertions.checkNotNull(this.inputFormat), cryptoConfig);
                this.decoder = createDecoder;
                createDecoder.setOutputStartTimeUs(getLastResetPositionUs());
                setDecoderOutputMode(this.outputMode);
                long elapsedRealtime2 = SystemClock.elapsedRealtime();
                this.eventDispatcher.decoderInitialized(((Decoder) Assertions.checkNotNull(this.decoder)).getName(), elapsedRealtime2, elapsedRealtime2 - elapsedRealtime);
                this.decoderCounters.decoderInitCount++;
            } catch (DecoderException e) {
                Log.e(TAG, "Video codec error", e);
                this.eventDispatcher.videoCodecError(e);
                throw createRendererException(e, this.inputFormat, 4001);
            } catch (OutOfMemoryError e2) {
                throw createRendererException(e2, this.inputFormat, 4001);
            }
        }
    }

    private boolean feedInputBuffer() throws DecoderException, ExoPlaybackException {
        Decoder<DecoderInputBuffer, ? extends VideoDecoderOutputBuffer, ? extends DecoderException> decoder2 = this.decoder;
        if (decoder2 == null || this.decoderReinitializationState == 2 || this.inputStreamEnded) {
            return false;
        }
        if (this.inputBuffer == null) {
            DecoderInputBuffer dequeueInputBuffer = decoder2.dequeueInputBuffer();
            this.inputBuffer = dequeueInputBuffer;
            if (dequeueInputBuffer == null) {
                return false;
            }
        }
        DecoderInputBuffer decoderInputBuffer = (DecoderInputBuffer) Assertions.checkNotNull(this.inputBuffer);
        if (this.decoderReinitializationState == 1) {
            decoderInputBuffer.setFlags(4);
            ((Decoder) Assertions.checkNotNull(this.decoder)).queueInputBuffer(decoderInputBuffer);
            this.inputBuffer = null;
            this.decoderReinitializationState = 2;
            return false;
        }
        FormatHolder formatHolder = getFormatHolder();
        int readSource = readSource(formatHolder, decoderInputBuffer, 0);
        if (readSource == -5) {
            onInputFormatChanged(formatHolder);
            return true;
        } else if (readSource != -4) {
            if (readSource == -3) {
                return false;
            }
            throw new IllegalStateException();
        } else if (decoderInputBuffer.isEndOfStream()) {
            this.inputStreamEnded = true;
            ((Decoder) Assertions.checkNotNull(this.decoder)).queueInputBuffer(decoderInputBuffer);
            this.inputBuffer = null;
            return false;
        } else {
            if (this.waitingForFirstSampleInFormat) {
                this.formatQueue.add(decoderInputBuffer.timeUs, (Format) Assertions.checkNotNull(this.inputFormat));
                this.waitingForFirstSampleInFormat = false;
            }
            decoderInputBuffer.flip();
            decoderInputBuffer.format = this.inputFormat;
            onQueueInputBuffer(decoderInputBuffer);
            ((Decoder) Assertions.checkNotNull(this.decoder)).queueInputBuffer(decoderInputBuffer);
            this.buffersInCodecCount++;
            this.decoderReceivedBuffers = true;
            this.decoderCounters.queuedInputBufferCount++;
            this.inputBuffer = null;
            return true;
        }
    }

    private boolean drainOutputBuffer(long j, long j2) throws ExoPlaybackException, DecoderException {
        if (this.outputBuffer == null) {
            VideoDecoderOutputBuffer videoDecoderOutputBuffer = (VideoDecoderOutputBuffer) ((Decoder) Assertions.checkNotNull(this.decoder)).dequeueOutputBuffer();
            this.outputBuffer = videoDecoderOutputBuffer;
            if (videoDecoderOutputBuffer == null) {
                return false;
            }
            this.decoderCounters.skippedOutputBufferCount += this.outputBuffer.skippedOutputBufferCount;
            this.buffersInCodecCount -= this.outputBuffer.skippedOutputBufferCount;
        }
        if (this.outputBuffer.isEndOfStream()) {
            if (this.decoderReinitializationState == 2) {
                releaseDecoder();
                maybeInitDecoder();
            } else {
                this.outputBuffer.release();
                this.outputBuffer = null;
                this.outputStreamEnded = true;
            }
            return false;
        }
        boolean processOutputBuffer = processOutputBuffer(j, j2);
        if (processOutputBuffer) {
            onProcessedOutputBuffer(((VideoDecoderOutputBuffer) Assertions.checkNotNull(this.outputBuffer)).timeUs);
            this.outputBuffer = null;
        }
        return processOutputBuffer;
    }

    private boolean processOutputBuffer(long j, long j2) throws ExoPlaybackException, DecoderException {
        if (this.initialPositionUs == C.TIME_UNSET) {
            this.initialPositionUs = j;
        }
        VideoDecoderOutputBuffer videoDecoderOutputBuffer = (VideoDecoderOutputBuffer) Assertions.checkNotNull(this.outputBuffer);
        long j3 = videoDecoderOutputBuffer.timeUs;
        long j4 = j3 - j;
        if (hasOutput()) {
            Format pollFloor = this.formatQueue.pollFloor(j3);
            if (pollFloor != null) {
                this.outputFormat = pollFloor;
            } else if (this.outputFormat == null) {
                this.outputFormat = this.formatQueue.pollFirst();
            }
            long streamOffsetUs = j3 - getStreamOffsetUs();
            if (shouldForceRender(j4)) {
                renderOutputBuffer(videoDecoderOutputBuffer, streamOffsetUs, (Format) Assertions.checkNotNull(this.outputFormat));
                return true;
            }
            if (!(getState() == 2) || j == this.initialPositionUs || (shouldDropBuffersToKeyframe(j4, j2) && maybeDropBuffersToKeyframe(j))) {
                return false;
            }
            if (shouldDropOutputBuffer(j4, j2)) {
                dropOutputBuffer(videoDecoderOutputBuffer);
                return true;
            }
            if (j4 < 30000) {
                renderOutputBuffer(videoDecoderOutputBuffer, streamOffsetUs, (Format) Assertions.checkNotNull(this.outputFormat));
                return true;
            }
            return false;
        } else if (!isBufferLate(j4)) {
            return false;
        } else {
            skipOutputBuffer(videoDecoderOutputBuffer);
            return true;
        }
    }

    private boolean shouldForceRender(long j) {
        boolean z = getState() == 2;
        int i = this.firstFrameState;
        if (i == 0) {
            return z;
        }
        if (i == 1) {
            return true;
        }
        if (i == 3) {
            long msToUs = Util.msToUs(SystemClock.elapsedRealtime()) - this.lastRenderTimeUs;
            if (!z || !shouldForceRenderOutputBuffer(j, msToUs)) {
                return false;
            }
            return true;
        }
        throw new IllegalStateException();
    }

    private boolean hasOutput() {
        return this.outputMode != -1;
    }

    private void onOutputChanged() {
        maybeRenotifyVideoSizeChanged();
        lowerFirstFrameState(1);
        if (getState() == 2) {
            setJoiningDeadlineMs();
        }
    }

    private void onOutputRemoved() {
        this.reportedVideoSize = null;
        lowerFirstFrameState(1);
    }

    private void onOutputReset() {
        maybeRenotifyVideoSizeChanged();
        maybeRenotifyRenderedFirstFrame();
    }

    private void setJoiningDeadlineMs() {
        this.joiningDeadlineMs = this.allowedJoiningTimeMs > 0 ? SystemClock.elapsedRealtime() + this.allowedJoiningTimeMs : C.TIME_UNSET;
    }

    private void lowerFirstFrameState(int i) {
        this.firstFrameState = Math.min(this.firstFrameState, i);
    }

    private void maybeNotifyRenderedFirstFrame() {
        if (this.firstFrameState != 3) {
            this.firstFrameState = 3;
            Object obj = this.output;
            if (obj != null) {
                this.eventDispatcher.renderedFirstFrame(obj);
            }
        }
    }

    private void maybeRenotifyRenderedFirstFrame() {
        Object obj;
        if (this.firstFrameState == 3 && (obj = this.output) != null) {
            this.eventDispatcher.renderedFirstFrame(obj);
        }
    }

    private void maybeNotifyVideoSizeChanged(int i, int i2) {
        VideoSize videoSize = this.reportedVideoSize;
        if (videoSize == null || videoSize.width != i || this.reportedVideoSize.height != i2) {
            VideoSize videoSize2 = new VideoSize(i, i2);
            this.reportedVideoSize = videoSize2;
            this.eventDispatcher.videoSizeChanged(videoSize2);
        }
    }

    private void maybeRenotifyVideoSizeChanged() {
        VideoSize videoSize = this.reportedVideoSize;
        if (videoSize != null) {
            this.eventDispatcher.videoSizeChanged(videoSize);
        }
    }

    private void maybeNotifyDroppedFrames() {
        if (this.droppedFrames > 0) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            this.eventDispatcher.droppedFrames(this.droppedFrames, elapsedRealtime - this.droppedFrameAccumulationStartTimeMs);
            this.droppedFrames = 0;
            this.droppedFrameAccumulationStartTimeMs = elapsedRealtime;
        }
    }
}
