package androidx.media3.exoplayer.mediacodec;

import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaCryptoException;
import android.media.MediaFormat;
import android.media.metrics.LogSessionId;
import android.os.Bundle;
import androidx.media3.common.C;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.TimedValueQueue;
import androidx.media3.common.util.TraceUtil;
import androidx.media3.common.util.Util;
import androidx.media3.decoder.CryptoConfig;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.exoplayer.BaseRenderer;
import androidx.media3.exoplayer.DecoderCounters;
import androidx.media3.exoplayer.DecoderReuseEvaluation;
import androidx.media3.exoplayer.ExoPlaybackException;
import androidx.media3.exoplayer.FormatHolder;
import androidx.media3.exoplayer.Renderer;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.audio.OggOpusAudioPacketizer;
import androidx.media3.exoplayer.drm.DrmSession;
import androidx.media3.exoplayer.drm.FrameworkCryptoConfig;
import androidx.media3.exoplayer.mediacodec.MediaCodecAdapter;
import androidx.media3.exoplayer.mediacodec.MediaCodecUtil;
import com.google.common.base.Ascii;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayDeque;
import java.util.List;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

public abstract class MediaCodecRenderer extends BaseRenderer {
    private static final byte[] ADAPTATION_WORKAROUND_BUFFER = {0, 0, 1, 103, 66, -64, 11, -38, 37, -112, 0, 0, 1, 104, -50, 15, 19, 32, 0, 0, 1, 101, -120, -124, 13, -50, 113, Ascii.CAN, -96, 0, 47, -65, Ascii.FS, 49, -61, 39, 93, 120};
    private static final int ADAPTATION_WORKAROUND_MODE_ALWAYS = 2;
    private static final int ADAPTATION_WORKAROUND_MODE_NEVER = 0;
    private static final int ADAPTATION_WORKAROUND_MODE_SAME_RESOLUTION = 1;
    private static final int ADAPTATION_WORKAROUND_SLICE_WIDTH_HEIGHT = 32;
    protected static final float CODEC_OPERATING_RATE_UNSET = -1.0f;
    private static final int DRAIN_ACTION_FLUSH = 1;
    private static final int DRAIN_ACTION_FLUSH_AND_UPDATE_DRM_SESSION = 2;
    private static final int DRAIN_ACTION_NONE = 0;
    private static final int DRAIN_ACTION_REINITIALIZE = 3;
    private static final int DRAIN_STATE_NONE = 0;
    private static final int DRAIN_STATE_SIGNAL_END_OF_STREAM = 1;
    private static final int DRAIN_STATE_WAIT_END_OF_STREAM = 2;
    private static final long MAX_CODEC_HOTSWAP_TIME_MS = 1000;
    private static final int RECONFIGURATION_STATE_NONE = 0;
    private static final int RECONFIGURATION_STATE_QUEUE_PENDING = 2;
    private static final int RECONFIGURATION_STATE_WRITE_PENDING = 1;
    private static final String TAG = "MediaCodecRenderer";
    private final float assumedMinimumCodecOperatingRate;
    private ArrayDeque<MediaCodecInfo> availableCodecInfos;
    private final DecoderInputBuffer buffer = new DecoderInputBuffer(0);
    private final BatchBuffer bypassBatchBuffer;
    private boolean bypassDrainAndReinitialize;
    private boolean bypassEnabled;
    private final DecoderInputBuffer bypassSampleBuffer = new DecoderInputBuffer(2);
    private boolean bypassSampleBufferPending;
    private MediaCodecAdapter codec;
    private int codecAdaptationWorkaroundMode;
    private final MediaCodecAdapter.Factory codecAdapterFactory;
    private int codecDrainAction;
    private int codecDrainState;
    private DrmSession codecDrmSession;
    private boolean codecHasOutputMediaFormat;
    private long codecHotswapDeadlineMs;
    private MediaCodecInfo codecInfo;
    private Format codecInputFormat;
    private boolean codecNeedsAdaptationWorkaroundBuffer;
    private boolean codecNeedsEosFlushWorkaround;
    private boolean codecNeedsEosOutputExceptionWorkaround;
    private boolean codecNeedsEosPropagation;
    private boolean codecNeedsSosFlushWorkaround;
    private float codecOperatingRate;
    private MediaFormat codecOutputMediaFormat;
    private boolean codecOutputMediaFormatChanged;
    private boolean codecReceivedBuffers;
    private boolean codecReceivedEos;
    private int codecReconfigurationState;
    private boolean codecReconfigured;
    private boolean codecRegisteredOnBufferAvailableListener;
    private float currentPlaybackSpeed;
    protected DecoderCounters decoderCounters;
    private final boolean enableDecoderFallback;
    private boolean experimentalEnableProcessedStreamChangedAtStart;
    private Format inputFormat;
    private int inputIndex;
    private boolean inputStreamEnded;
    private boolean isDecodeOnlyOutputBuffer;
    private boolean isLastOutputBuffer;
    private long largestQueuedPresentationTimeUs;
    private long lastBufferInStreamPresentationTimeUs;
    private long lastOutputBufferProcessedRealtimeMs;
    private long lastProcessedOutputBufferTimeUs;
    private final MediaCodecSelector mediaCodecSelector;
    private MediaCrypto mediaCrypto;
    private boolean needToNotifyOutputFormatChangeAfterStreamChange;
    private final DecoderInputBuffer noDataBuffer = DecoderInputBuffer.newNoDataInstance();
    private final OggOpusAudioPacketizer oggOpusAudioPacketizer;
    private ByteBuffer outputBuffer;
    private final MediaCodec.BufferInfo outputBufferInfo;
    private Format outputFormat;
    private int outputIndex;
    private boolean outputStreamEnded;
    private OutputStreamInfo outputStreamInfo;
    private boolean pendingOutputEndOfStream;
    private final ArrayDeque<OutputStreamInfo> pendingOutputStreamChanges;
    private ExoPlaybackException pendingPlaybackException;
    private DecoderInitializationException preferredDecoderInitializationException;
    private long renderTimeLimitMs;
    private boolean shouldSkipAdaptationWorkaroundOutputBuffer;
    private DrmSession sourceDrmSession;
    private float targetPlaybackSpeed;
    private boolean waitingForFirstSampleInFormat;
    /* access modifiers changed from: private */
    public Renderer.WakeupListener wakeupListener;

    /* access modifiers changed from: protected */
    public int getCodecBufferFlags(DecoderInputBuffer decoderInputBuffer) {
        return 0;
    }

    /* access modifiers changed from: protected */
    public boolean getCodecNeedsEosPropagation() {
        return false;
    }

    /* access modifiers changed from: protected */
    public float getCodecOperatingRateV23(float f, Format format, Format[] formatArr) {
        return CODEC_OPERATING_RATE_UNSET;
    }

    /* access modifiers changed from: protected */
    public abstract List<MediaCodecInfo> getDecoderInfos(MediaCodecSelector mediaCodecSelector2, Format format, boolean z) throws MediaCodecUtil.DecoderQueryException;

    /* access modifiers changed from: protected */
    public abstract MediaCodecAdapter.Configuration getMediaCodecConfiguration(MediaCodecInfo mediaCodecInfo, Format format, MediaCrypto mediaCrypto2, float f);

    /* access modifiers changed from: protected */
    public void handleInputBufferSupplementalData(DecoderInputBuffer decoderInputBuffer) throws ExoPlaybackException {
    }

    /* access modifiers changed from: protected */
    public void onCodecError(Exception exc) {
    }

    /* access modifiers changed from: protected */
    public void onCodecInitialized(String str, MediaCodecAdapter.Configuration configuration, long j, long j2) {
    }

    /* access modifiers changed from: protected */
    public void onCodecReleased(String str) {
    }

    /* access modifiers changed from: protected */
    public void onOutputFormatChanged(Format format, MediaFormat mediaFormat) throws ExoPlaybackException {
    }

    /* access modifiers changed from: protected */
    public void onOutputStreamOffsetUsChanged(long j) {
    }

    /* access modifiers changed from: protected */
    public void onProcessedStreamChange() {
    }

    /* access modifiers changed from: protected */
    public void onQueueInputBuffer(DecoderInputBuffer decoderInputBuffer) throws ExoPlaybackException {
    }

    /* access modifiers changed from: protected */
    public void onReadyToInitializeCodec(Format format) throws ExoPlaybackException {
    }

    /* access modifiers changed from: protected */
    public void onStarted() {
    }

    /* access modifiers changed from: protected */
    public void onStopped() {
    }

    /* access modifiers changed from: protected */
    public abstract boolean processOutputBuffer(long j, long j2, MediaCodecAdapter mediaCodecAdapter, ByteBuffer byteBuffer, int i, int i2, int i3, long j3, boolean z, boolean z2, Format format) throws ExoPlaybackException;

    /* access modifiers changed from: protected */
    public void renderToEndOfStream() throws ExoPlaybackException {
    }

    /* access modifiers changed from: protected */
    public boolean shouldInitCodec(MediaCodecInfo mediaCodecInfo) {
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean shouldReinitCodec() {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean shouldSkipDecoderInputBuffer(DecoderInputBuffer decoderInputBuffer) {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean shouldUseBypass(Format format) {
        return false;
    }

    /* access modifiers changed from: protected */
    public abstract int supportsFormat(MediaCodecSelector mediaCodecSelector2, Format format) throws MediaCodecUtil.DecoderQueryException;

    public final int supportsMixedMimeTypeAdaptation() {
        return 8;
    }

    public static class DecoderInitializationException extends Exception {
        private static final int CUSTOM_ERROR_CODE_BASE = -50000;
        private static final int DECODER_QUERY_ERROR = -49998;
        private static final int NO_SUITABLE_DECODER_ERROR = -49999;
        public final MediaCodecInfo codecInfo;
        public final String diagnosticInfo;
        public final DecoderInitializationException fallbackDecoderInitializationException;
        public final String mimeType;
        public final boolean secureDecoderRequired;

        public DecoderInitializationException(Format format, Throwable th, boolean z, int i) {
            this("Decoder init failed: [" + i + "], " + format, th, format.sampleMimeType, z, (MediaCodecInfo) null, buildCustomDiagnosticInfo(i), (DecoderInitializationException) null);
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public DecoderInitializationException(Format format, Throwable th, boolean z, MediaCodecInfo mediaCodecInfo) {
            this("Decoder init failed: " + mediaCodecInfo.name + ", " + format, th, format.sampleMimeType, z, mediaCodecInfo, th instanceof MediaCodec.CodecException ? ((MediaCodec.CodecException) th).getDiagnosticInfo() : null, (DecoderInitializationException) null);
        }

        private DecoderInitializationException(String str, Throwable th, String str2, boolean z, MediaCodecInfo mediaCodecInfo, String str3, DecoderInitializationException decoderInitializationException) {
            super(str, th);
            this.mimeType = str2;
            this.secureDecoderRequired = z;
            this.codecInfo = mediaCodecInfo;
            this.diagnosticInfo = str3;
            this.fallbackDecoderInitializationException = decoderInitializationException;
        }

        /* access modifiers changed from: private */
        public DecoderInitializationException copyWithFallbackException(DecoderInitializationException decoderInitializationException) {
            return new DecoderInitializationException(getMessage(), getCause(), this.mimeType, this.secureDecoderRequired, this.codecInfo, this.diagnosticInfo, decoderInitializationException);
        }

        private static String buildCustomDiagnosticInfo(int i) {
            return "androidx.media3.exoplayer.mediacodec.MediaCodecRenderer_" + (i < 0 ? "neg_" : "") + Math.abs(i);
        }
    }

    public MediaCodecRenderer(int i, MediaCodecAdapter.Factory factory, MediaCodecSelector mediaCodecSelector2, boolean z, float f) {
        super(i);
        this.codecAdapterFactory = factory;
        this.mediaCodecSelector = (MediaCodecSelector) Assertions.checkNotNull(mediaCodecSelector2);
        this.enableDecoderFallback = z;
        this.assumedMinimumCodecOperatingRate = f;
        BatchBuffer batchBuffer = new BatchBuffer();
        this.bypassBatchBuffer = batchBuffer;
        this.outputBufferInfo = new MediaCodec.BufferInfo();
        this.currentPlaybackSpeed = 1.0f;
        this.targetPlaybackSpeed = 1.0f;
        this.renderTimeLimitMs = C.TIME_UNSET;
        this.pendingOutputStreamChanges = new ArrayDeque<>();
        this.outputStreamInfo = OutputStreamInfo.UNSET;
        batchBuffer.ensureSpaceForWrite(0);
        batchBuffer.data.order(ByteOrder.nativeOrder());
        this.oggOpusAudioPacketizer = new OggOpusAudioPacketizer();
        this.codecOperatingRate = CODEC_OPERATING_RATE_UNSET;
        this.codecAdaptationWorkaroundMode = 0;
        this.codecReconfigurationState = 0;
        this.inputIndex = -1;
        this.outputIndex = -1;
        this.codecHotswapDeadlineMs = C.TIME_UNSET;
        this.largestQueuedPresentationTimeUs = C.TIME_UNSET;
        this.lastBufferInStreamPresentationTimeUs = C.TIME_UNSET;
        this.lastProcessedOutputBufferTimeUs = C.TIME_UNSET;
        this.lastOutputBufferProcessedRealtimeMs = C.TIME_UNSET;
        this.codecDrainState = 0;
        this.codecDrainAction = 0;
        this.decoderCounters = new DecoderCounters();
    }

    public void setRenderTimeLimitMs(long j) {
        this.renderTimeLimitMs = j;
    }

    public final int supportsFormat(Format format) throws ExoPlaybackException {
        try {
            return supportsFormat(this.mediaCodecSelector, format);
        } catch (MediaCodecUtil.DecoderQueryException e) {
            throw createRendererException(e, format, 4002);
        }
    }

    public final long getDurationToProgressUs(long j, long j2) {
        return getDurationToProgressUs(this.codecRegisteredOnBufferAvailableListener, j, j2);
    }

    public void experimentalEnableProcessedStreamChangedAtStart() {
        this.experimentalEnableProcessedStreamChangedAtStart = true;
    }

    /* access modifiers changed from: protected */
    public long getDurationToProgressUs(boolean z, long j, long j2) {
        return super.getDurationToProgressUs(j, j2);
    }

    /* access modifiers changed from: protected */
    public final void maybeInitCodecOrBypass() throws ExoPlaybackException {
        Format format;
        if (this.codec == null && !this.bypassEnabled && (format = this.inputFormat) != null) {
            if (isBypassPossible(format)) {
                initBypass(format);
                return;
            }
            setCodecDrmSession(this.sourceDrmSession);
            if (this.codecDrmSession == null || initMediaCryptoIfDrmSessionReady()) {
                try {
                    DrmSession drmSession = this.codecDrmSession;
                    maybeInitCodecWithFallback(this.mediaCrypto, drmSession != null && (drmSession.getState() == 3 || this.codecDrmSession.getState() == 4) && this.codecDrmSession.requiresSecureDecoder((String) Assertions.checkStateNotNull(format.sampleMimeType)));
                } catch (DecoderInitializationException e) {
                    throw createRendererException(e, format, 4001);
                }
            }
            MediaCrypto mediaCrypto2 = this.mediaCrypto;
            if (mediaCrypto2 != null && this.codec == null) {
                mediaCrypto2.release();
                this.mediaCrypto = null;
            }
        }
    }

    /* access modifiers changed from: protected */
    public final boolean isBypassPossible(Format format) {
        return this.sourceDrmSession == null && shouldUseBypass(format);
    }

    /* access modifiers changed from: protected */
    public final boolean isBypassEnabled() {
        return this.bypassEnabled;
    }

    /* access modifiers changed from: protected */
    public final void setPendingPlaybackException(ExoPlaybackException exoPlaybackException) {
        this.pendingPlaybackException = exoPlaybackException;
    }

    /* access modifiers changed from: protected */
    public final void updateOutputFormatForTime(long j) throws ExoPlaybackException {
        boolean z;
        Format pollFloor = this.outputStreamInfo.formatQueue.pollFloor(j);
        if (pollFloor == null && this.needToNotifyOutputFormatChangeAfterStreamChange && this.codecOutputMediaFormat != null) {
            pollFloor = this.outputStreamInfo.formatQueue.pollFirst();
        }
        if (pollFloor != null) {
            this.outputFormat = pollFloor;
            z = true;
        } else {
            z = false;
        }
        if (z || (this.codecOutputMediaFormatChanged && this.outputFormat != null)) {
            onOutputFormatChanged((Format) Assertions.checkNotNull(this.outputFormat), this.codecOutputMediaFormat);
            this.codecOutputMediaFormatChanged = false;
            this.needToNotifyOutputFormatChangeAfterStreamChange = false;
        }
    }

    /* access modifiers changed from: protected */
    public final MediaCodecAdapter getCodec() {
        return this.codec;
    }

    /* access modifiers changed from: protected */
    public final MediaFormat getCodecOutputMediaFormat() {
        return this.codecOutputMediaFormat;
    }

    /* access modifiers changed from: protected */
    public final MediaCodecInfo getCodecInfo() {
        return this.codecInfo;
    }

    /* access modifiers changed from: protected */
    public void onEnabled(boolean z, boolean z2) throws ExoPlaybackException {
        this.decoderCounters = new DecoderCounters();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x003d, code lost:
        if (r5 >= r1) goto L_0x003f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onStreamChanged(androidx.media3.common.Format[] r13, long r14, long r16, androidx.media3.exoplayer.source.MediaSource.MediaPeriodId r18) throws androidx.media3.exoplayer.ExoPlaybackException {
        /*
            r12 = this;
            r0 = r12
            androidx.media3.exoplayer.mediacodec.MediaCodecRenderer$OutputStreamInfo r1 = r0.outputStreamInfo
            long r1 = r1.streamOffsetUs
            r3 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 != 0) goto L_0x0027
            androidx.media3.exoplayer.mediacodec.MediaCodecRenderer$OutputStreamInfo r1 = new androidx.media3.exoplayer.mediacodec.MediaCodecRenderer$OutputStreamInfo
            r6 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r5 = r1
            r8 = r14
            r10 = r16
            r5.<init>(r6, r8, r10)
            r12.setOutputStreamInfo(r1)
            boolean r1 = r0.experimentalEnableProcessedStreamChangedAtStart
            if (r1 == 0) goto L_0x006c
            r12.onProcessedStreamChange()
            goto L_0x006c
        L_0x0027:
            java.util.ArrayDeque<androidx.media3.exoplayer.mediacodec.MediaCodecRenderer$OutputStreamInfo> r1 = r0.pendingOutputStreamChanges
            boolean r1 = r1.isEmpty()
            if (r1 == 0) goto L_0x005c
            long r1 = r0.largestQueuedPresentationTimeUs
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 == 0) goto L_0x003f
            long r5 = r0.lastProcessedOutputBufferTimeUs
            int r7 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r7 == 0) goto L_0x005c
            int r1 = (r5 > r1 ? 1 : (r5 == r1 ? 0 : -1))
            if (r1 < 0) goto L_0x005c
        L_0x003f:
            androidx.media3.exoplayer.mediacodec.MediaCodecRenderer$OutputStreamInfo r1 = new androidx.media3.exoplayer.mediacodec.MediaCodecRenderer$OutputStreamInfo
            r6 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r5 = r1
            r8 = r14
            r10 = r16
            r5.<init>(r6, r8, r10)
            r12.setOutputStreamInfo(r1)
            androidx.media3.exoplayer.mediacodec.MediaCodecRenderer$OutputStreamInfo r1 = r0.outputStreamInfo
            long r1 = r1.streamOffsetUs
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 == 0) goto L_0x006c
            r12.onProcessedStreamChange()
            goto L_0x006c
        L_0x005c:
            java.util.ArrayDeque<androidx.media3.exoplayer.mediacodec.MediaCodecRenderer$OutputStreamInfo> r1 = r0.pendingOutputStreamChanges
            androidx.media3.exoplayer.mediacodec.MediaCodecRenderer$OutputStreamInfo r9 = new androidx.media3.exoplayer.mediacodec.MediaCodecRenderer$OutputStreamInfo
            long r3 = r0.largestQueuedPresentationTimeUs
            r2 = r9
            r5 = r14
            r7 = r16
            r2.<init>(r3, r5, r7)
            r1.add(r9)
        L_0x006c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.mediacodec.MediaCodecRenderer.onStreamChanged(androidx.media3.common.Format[], long, long, androidx.media3.exoplayer.source.MediaSource$MediaPeriodId):void");
    }

    /* access modifiers changed from: protected */
    public void onPositionReset(long j, boolean z) throws ExoPlaybackException {
        this.inputStreamEnded = false;
        this.outputStreamEnded = false;
        this.pendingOutputEndOfStream = false;
        if (this.bypassEnabled) {
            this.bypassBatchBuffer.clear();
            this.bypassSampleBuffer.clear();
            this.bypassSampleBufferPending = false;
            this.oggOpusAudioPacketizer.reset();
        } else {
            flushOrReinitializeCodec();
        }
        if (this.outputStreamInfo.formatQueue.size() > 0) {
            this.waitingForFirstSampleInFormat = true;
        }
        this.outputStreamInfo.formatQueue.clear();
        this.pendingOutputStreamChanges.clear();
    }

    public void setPlaybackSpeed(float f, float f2) throws ExoPlaybackException {
        this.currentPlaybackSpeed = f;
        this.targetPlaybackSpeed = f2;
        updateCodecOperatingRate(this.codecInputFormat);
    }

    /* access modifiers changed from: protected */
    public void onDisabled() {
        this.inputFormat = null;
        setOutputStreamInfo(OutputStreamInfo.UNSET);
        this.pendingOutputStreamChanges.clear();
        flushOrReleaseCodec();
    }

    /* access modifiers changed from: protected */
    public void onReset() {
        try {
            disableBypass();
            releaseCodec();
        } finally {
            setSourceDrmSession((DrmSession) null);
        }
    }

    private void disableBypass() {
        this.bypassDrainAndReinitialize = false;
        this.bypassBatchBuffer.clear();
        this.bypassSampleBuffer.clear();
        this.bypassSampleBufferPending = false;
        this.bypassEnabled = false;
        this.oggOpusAudioPacketizer.reset();
    }

    /* access modifiers changed from: protected */
    public void releaseCodec() {
        try {
            MediaCodecAdapter mediaCodecAdapter = this.codec;
            if (mediaCodecAdapter != null) {
                mediaCodecAdapter.release();
                this.decoderCounters.decoderReleaseCount++;
                onCodecReleased(((MediaCodecInfo) Assertions.checkNotNull(this.codecInfo)).name);
            }
            this.codec = null;
            try {
                MediaCrypto mediaCrypto2 = this.mediaCrypto;
                if (mediaCrypto2 != null) {
                    mediaCrypto2.release();
                }
            } finally {
                this.mediaCrypto = null;
                setCodecDrmSession((DrmSession) null);
                resetCodecStateForRelease();
            }
        } catch (Throwable th) {
            this.codec = null;
            MediaCrypto mediaCrypto3 = this.mediaCrypto;
            if (mediaCrypto3 != null) {
                mediaCrypto3.release();
            }
            throw th;
        } finally {
            this.mediaCrypto = null;
            setCodecDrmSession((DrmSession) null);
            resetCodecStateForRelease();
        }
    }

    public void handleMessage(int i, Object obj) throws ExoPlaybackException {
        if (i == 11) {
            this.wakeupListener = (Renderer.WakeupListener) obj;
        } else {
            super.handleMessage(i, obj);
        }
    }

    public void render(long j, long j2) throws ExoPlaybackException {
        boolean z = false;
        if (this.pendingOutputEndOfStream) {
            this.pendingOutputEndOfStream = false;
            processEndOfStream();
        }
        ExoPlaybackException exoPlaybackException = this.pendingPlaybackException;
        if (exoPlaybackException == null) {
            try {
                if (this.outputStreamEnded) {
                    renderToEndOfStream();
                } else if (this.inputFormat != null || readSourceOmittingSampleData(2)) {
                    maybeInitCodecOrBypass();
                    if (this.bypassEnabled) {
                        TraceUtil.beginSection("bypassRender");
                        while (bypassRender(j, j2)) {
                        }
                        TraceUtil.endSection();
                    } else if (this.codec != null) {
                        long elapsedRealtime = getClock().elapsedRealtime();
                        TraceUtil.beginSection("drainAndFeed");
                        while (drainOutputBuffer(j, j2) && shouldContinueRendering(elapsedRealtime)) {
                        }
                        while (feedInputBuffer() && shouldContinueRendering(elapsedRealtime)) {
                        }
                        TraceUtil.endSection();
                    } else {
                        this.decoderCounters.skippedInputBufferCount += skipSource(j);
                        readSourceOmittingSampleData(1);
                    }
                    this.decoderCounters.ensureUpdated();
                }
            } catch (MediaCodec.CryptoException e) {
                throw createRendererException(e, this.inputFormat, Util.getErrorCodeForMediaDrmErrorCode(e.getErrorCode()));
            } catch (IllegalStateException e2) {
                if (isMediaCodecException(e2)) {
                    onCodecError(e2);
                    if ((e2 instanceof MediaCodec.CodecException) && ((MediaCodec.CodecException) e2).isRecoverable()) {
                        z = true;
                    }
                    if (z) {
                        releaseCodec();
                    }
                    MediaCodecDecoderException createDecoderException = createDecoderException(e2, getCodecInfo());
                    throw createRendererException(createDecoderException, this.inputFormat, z, createDecoderException.errorCode == 1101 ? PlaybackException.ERROR_CODE_DECODING_RESOURCES_RECLAIMED : 4003);
                }
                throw e2;
            }
        } else {
            this.pendingPlaybackException = null;
            throw exoPlaybackException;
        }
    }

    /* access modifiers changed from: protected */
    public final boolean flushOrReinitializeCodec() throws ExoPlaybackException {
        boolean flushOrReleaseCodec = flushOrReleaseCodec();
        if (flushOrReleaseCodec) {
            maybeInitCodecOrBypass();
        }
        return flushOrReleaseCodec;
    }

    /* access modifiers changed from: protected */
    public boolean flushOrReleaseCodec() {
        if (this.codec == null) {
            return false;
        }
        int i = this.codecDrainAction;
        if (i == 3 || ((this.codecNeedsSosFlushWorkaround && !this.codecHasOutputMediaFormat) || (this.codecNeedsEosFlushWorkaround && this.codecReceivedEos))) {
            releaseCodec();
            return true;
        }
        if (i == 2) {
            Assertions.checkState(Util.SDK_INT >= 23);
            if (Util.SDK_INT >= 23) {
                try {
                    updateDrmSessionV23();
                } catch (ExoPlaybackException e) {
                    Log.w(TAG, "Failed to update the DRM session, releasing the codec instead.", e);
                    releaseCodec();
                    return true;
                }
            }
        }
        flushCodec();
        return false;
    }

    private void flushCodec() {
        try {
            ((MediaCodecAdapter) Assertions.checkStateNotNull(this.codec)).flush();
        } finally {
            resetCodecStateForFlush();
        }
    }

    /* access modifiers changed from: protected */
    public void resetCodecStateForFlush() {
        resetInputBuffer();
        resetOutputBuffer();
        this.codecHotswapDeadlineMs = C.TIME_UNSET;
        this.codecReceivedEos = false;
        this.lastOutputBufferProcessedRealtimeMs = C.TIME_UNSET;
        this.codecReceivedBuffers = false;
        this.codecNeedsAdaptationWorkaroundBuffer = false;
        this.shouldSkipAdaptationWorkaroundOutputBuffer = false;
        this.isDecodeOnlyOutputBuffer = false;
        this.isLastOutputBuffer = false;
        this.largestQueuedPresentationTimeUs = C.TIME_UNSET;
        this.lastBufferInStreamPresentationTimeUs = C.TIME_UNSET;
        this.lastProcessedOutputBufferTimeUs = C.TIME_UNSET;
        this.codecDrainState = 0;
        this.codecDrainAction = 0;
        this.codecReconfigurationState = this.codecReconfigured ? 1 : 0;
    }

    /* access modifiers changed from: protected */
    public void resetCodecStateForRelease() {
        resetCodecStateForFlush();
        this.pendingPlaybackException = null;
        this.availableCodecInfos = null;
        this.codecInfo = null;
        this.codecInputFormat = null;
        this.codecOutputMediaFormat = null;
        this.codecOutputMediaFormatChanged = false;
        this.codecHasOutputMediaFormat = false;
        this.codecOperatingRate = CODEC_OPERATING_RATE_UNSET;
        this.codecAdaptationWorkaroundMode = 0;
        this.codecNeedsSosFlushWorkaround = false;
        this.codecNeedsEosFlushWorkaround = false;
        this.codecNeedsEosOutputExceptionWorkaround = false;
        this.codecNeedsEosPropagation = false;
        this.codecRegisteredOnBufferAvailableListener = false;
        this.codecReconfigured = false;
        this.codecReconfigurationState = 0;
    }

    /* access modifiers changed from: protected */
    public MediaCodecDecoderException createDecoderException(Throwable th, MediaCodecInfo mediaCodecInfo) {
        return new MediaCodecDecoderException(th, mediaCodecInfo);
    }

    private boolean readSourceOmittingSampleData(int i) throws ExoPlaybackException {
        FormatHolder formatHolder = getFormatHolder();
        this.noDataBuffer.clear();
        int readSource = readSource(formatHolder, this.noDataBuffer, i | 4);
        if (readSource == -5) {
            onInputFormatChanged(formatHolder);
            return true;
        } else if (readSource != -4 || !this.noDataBuffer.isEndOfStream()) {
            return false;
        } else {
            this.inputStreamEnded = true;
            processEndOfStream();
            return false;
        }
    }

    @RequiresNonNull({"this.codecDrmSession"})
    private boolean initMediaCryptoIfDrmSessionReady() throws ExoPlaybackException {
        Assertions.checkState(this.mediaCrypto == null);
        DrmSession drmSession = this.codecDrmSession;
        CryptoConfig cryptoConfig = drmSession.getCryptoConfig();
        if (FrameworkCryptoConfig.WORKAROUND_DEVICE_NEEDS_KEYS_TO_CONFIGURE_CODEC && (cryptoConfig instanceof FrameworkCryptoConfig)) {
            int state = drmSession.getState();
            if (state == 1) {
                DrmSession.DrmSessionException drmSessionException = (DrmSession.DrmSessionException) Assertions.checkNotNull(drmSession.getError());
                throw createRendererException(drmSessionException, this.inputFormat, drmSessionException.errorCode);
            } else if (state != 4) {
                return false;
            }
        }
        if (cryptoConfig == null) {
            return drmSession.getError() != null;
        }
        if (cryptoConfig instanceof FrameworkCryptoConfig) {
            FrameworkCryptoConfig frameworkCryptoConfig = (FrameworkCryptoConfig) cryptoConfig;
            try {
                this.mediaCrypto = new MediaCrypto(frameworkCryptoConfig.uuid, frameworkCryptoConfig.sessionId);
            } catch (MediaCryptoException e) {
                throw createRendererException(e, this.inputFormat, PlaybackException.ERROR_CODE_DRM_SYSTEM_ERROR);
            }
        }
        return true;
    }

    private void maybeInitCodecWithFallback(MediaCrypto mediaCrypto2, boolean z) throws DecoderInitializationException {
        Format format = (Format) Assertions.checkNotNull(this.inputFormat);
        if (this.availableCodecInfos == null) {
            try {
                List<MediaCodecInfo> availableCodecInfos2 = getAvailableCodecInfos(z);
                ArrayDeque<MediaCodecInfo> arrayDeque = new ArrayDeque<>();
                this.availableCodecInfos = arrayDeque;
                if (this.enableDecoderFallback) {
                    arrayDeque.addAll(availableCodecInfos2);
                } else if (!availableCodecInfos2.isEmpty()) {
                    this.availableCodecInfos.add(availableCodecInfos2.get(0));
                }
                this.preferredDecoderInitializationException = null;
            } catch (MediaCodecUtil.DecoderQueryException e) {
                throw new DecoderInitializationException(format, (Throwable) e, z, -49998);
            }
        }
        if (!this.availableCodecInfos.isEmpty()) {
            ArrayDeque arrayDeque2 = (ArrayDeque) Assertions.checkNotNull(this.availableCodecInfos);
            while (this.codec == null) {
                MediaCodecInfo mediaCodecInfo = (MediaCodecInfo) Assertions.checkNotNull((MediaCodecInfo) arrayDeque2.peekFirst());
                if (shouldInitCodec(mediaCodecInfo)) {
                    try {
                        initCodec(mediaCodecInfo, mediaCrypto2);
                    } catch (Exception e2) {
                        Log.w(TAG, "Failed to initialize decoder: " + mediaCodecInfo, e2);
                        arrayDeque2.removeFirst();
                        DecoderInitializationException decoderInitializationException = new DecoderInitializationException(format, (Throwable) e2, z, mediaCodecInfo);
                        onCodecError(decoderInitializationException);
                        if (this.preferredDecoderInitializationException == null) {
                            this.preferredDecoderInitializationException = decoderInitializationException;
                        } else {
                            this.preferredDecoderInitializationException = this.preferredDecoderInitializationException.copyWithFallbackException(decoderInitializationException);
                        }
                        if (arrayDeque2.isEmpty()) {
                            throw this.preferredDecoderInitializationException;
                        }
                    }
                } else {
                    return;
                }
            }
            this.availableCodecInfos = null;
            return;
        }
        throw new DecoderInitializationException(format, (Throwable) null, z, -49999);
    }

    private List<MediaCodecInfo> getAvailableCodecInfos(boolean z) throws MediaCodecUtil.DecoderQueryException {
        Format format = (Format) Assertions.checkNotNull(this.inputFormat);
        List<MediaCodecInfo> decoderInfos = getDecoderInfos(this.mediaCodecSelector, format, z);
        if (decoderInfos.isEmpty() && z) {
            decoderInfos = getDecoderInfos(this.mediaCodecSelector, format, false);
            if (!decoderInfos.isEmpty()) {
                Log.w(TAG, "Drm session requires secure decoder for " + format.sampleMimeType + ", but no secure decoder available. Trying to proceed with " + decoderInfos + ".");
            }
        }
        return decoderInfos;
    }

    private void initBypass(Format format) {
        disableBypass();
        String str = format.sampleMimeType;
        if (MimeTypes.AUDIO_AAC.equals(str) || MimeTypes.AUDIO_MPEG.equals(str) || MimeTypes.AUDIO_OPUS.equals(str)) {
            this.bypassBatchBuffer.setMaxSampleCount(32);
        } else {
            this.bypassBatchBuffer.setMaxSampleCount(1);
        }
        this.bypassEnabled = true;
    }

    /* JADX INFO: finally extract failed */
    private void initCodec(MediaCodecInfo mediaCodecInfo, MediaCrypto mediaCrypto2) throws Exception {
        float f;
        Format format = (Format) Assertions.checkNotNull(this.inputFormat);
        String str = mediaCodecInfo.name;
        int i = Util.SDK_INT;
        float f2 = CODEC_OPERATING_RATE_UNSET;
        if (i < 23) {
            f = -1.0f;
        } else {
            f = getCodecOperatingRateV23(this.targetPlaybackSpeed, format, getStreamFormats());
        }
        if (f > this.assumedMinimumCodecOperatingRate) {
            f2 = f;
        }
        onReadyToInitializeCodec(format);
        long elapsedRealtime = getClock().elapsedRealtime();
        MediaCodecAdapter.Configuration mediaCodecConfiguration = getMediaCodecConfiguration(mediaCodecInfo, format, mediaCrypto2, f2);
        if (Util.SDK_INT >= 31) {
            Api31.setLogSessionIdToMediaCodecFormat(mediaCodecConfiguration, getPlayerId());
        }
        try {
            TraceUtil.beginSection("createCodec:" + str);
            MediaCodecAdapter createAdapter = this.codecAdapterFactory.createAdapter(mediaCodecConfiguration);
            this.codec = createAdapter;
            this.codecRegisteredOnBufferAvailableListener = createAdapter.registerOnBufferAvailableListener(new MediaCodecRendererCodecAdapterListener());
            TraceUtil.endSection();
            long elapsedRealtime2 = getClock().elapsedRealtime();
            if (!mediaCodecInfo.isFormatSupported(format)) {
                Log.w(TAG, Util.formatInvariant("Format exceeds selected codec's capabilities [%s, %s]", Format.toLogString(format), str));
            }
            this.codecInfo = mediaCodecInfo;
            this.codecOperatingRate = f2;
            this.codecInputFormat = format;
            this.codecAdaptationWorkaroundMode = codecAdaptationWorkaroundMode(str);
            this.codecNeedsSosFlushWorkaround = codecNeedsSosFlushWorkaround(str);
            this.codecNeedsEosFlushWorkaround = codecNeedsEosFlushWorkaround(str);
            this.codecNeedsEosOutputExceptionWorkaround = codecNeedsEosOutputExceptionWorkaround(str);
            boolean z = false;
            this.codecNeedsEosPropagation = codecNeedsEosPropagationWorkaround(mediaCodecInfo) || getCodecNeedsEosPropagation();
            if (((MediaCodecAdapter) Assertions.checkNotNull(this.codec)).needsReconfiguration()) {
                this.codecReconfigured = true;
                this.codecReconfigurationState = 1;
                if (this.codecAdaptationWorkaroundMode != 0) {
                    z = true;
                }
                this.codecNeedsAdaptationWorkaroundBuffer = z;
            }
            if (getState() == 2) {
                this.codecHotswapDeadlineMs = getClock().elapsedRealtime() + 1000;
            }
            this.decoderCounters.decoderInitCount++;
            onCodecInitialized(str, mediaCodecConfiguration, elapsedRealtime2, elapsedRealtime2 - elapsedRealtime);
        } catch (Throwable th) {
            TraceUtil.endSection();
            throw th;
        }
    }

    private boolean shouldContinueRendering(long j) {
        return this.renderTimeLimitMs == C.TIME_UNSET || getClock().elapsedRealtime() - j < this.renderTimeLimitMs;
    }

    private boolean hasOutputBuffer() {
        return this.outputIndex >= 0;
    }

    private void resetInputBuffer() {
        this.inputIndex = -1;
        this.buffer.data = null;
    }

    private void resetOutputBuffer() {
        this.outputIndex = -1;
        this.outputBuffer = null;
    }

    private void setSourceDrmSession(DrmSession drmSession) {
        DrmSession.replaceSession(this.sourceDrmSession, drmSession);
        this.sourceDrmSession = drmSession;
    }

    private void setCodecDrmSession(DrmSession drmSession) {
        DrmSession.replaceSession(this.codecDrmSession, drmSession);
        this.codecDrmSession = drmSession;
    }

    private boolean feedInputBuffer() throws ExoPlaybackException {
        int i;
        if (this.codec == null || (i = this.codecDrainState) == 2 || this.inputStreamEnded) {
            return false;
        }
        if (i == 0 && shouldReinitCodec()) {
            drainAndReinitializeCodec();
        }
        MediaCodecAdapter mediaCodecAdapter = (MediaCodecAdapter) Assertions.checkNotNull(this.codec);
        if (this.inputIndex < 0) {
            int dequeueInputBufferIndex = mediaCodecAdapter.dequeueInputBufferIndex();
            this.inputIndex = dequeueInputBufferIndex;
            if (dequeueInputBufferIndex < 0) {
                return false;
            }
            this.buffer.data = mediaCodecAdapter.getInputBuffer(dequeueInputBufferIndex);
            this.buffer.clear();
        }
        if (this.codecDrainState == 1) {
            if (!this.codecNeedsEosPropagation) {
                this.codecReceivedEos = true;
                mediaCodecAdapter.queueInputBuffer(this.inputIndex, 0, 0, 0, 4);
                resetInputBuffer();
            }
            this.codecDrainState = 2;
            return false;
        } else if (this.codecNeedsAdaptationWorkaroundBuffer) {
            this.codecNeedsAdaptationWorkaroundBuffer = false;
            byte[] bArr = ADAPTATION_WORKAROUND_BUFFER;
            ((ByteBuffer) Assertions.checkNotNull(this.buffer.data)).put(bArr);
            mediaCodecAdapter.queueInputBuffer(this.inputIndex, 0, bArr.length, 0, 0);
            resetInputBuffer();
            this.codecReceivedBuffers = true;
            return true;
        } else {
            if (this.codecReconfigurationState == 1) {
                for (int i2 = 0; i2 < ((Format) Assertions.checkNotNull(this.codecInputFormat)).initializationData.size(); i2++) {
                    ((ByteBuffer) Assertions.checkNotNull(this.buffer.data)).put(this.codecInputFormat.initializationData.get(i2));
                }
                this.codecReconfigurationState = 2;
            }
            int position = ((ByteBuffer) Assertions.checkNotNull(this.buffer.data)).position();
            FormatHolder formatHolder = getFormatHolder();
            try {
                int readSource = readSource(formatHolder, this.buffer, 0);
                if (readSource == -3) {
                    if (hasReadStreamToEnd()) {
                        this.lastBufferInStreamPresentationTimeUs = this.largestQueuedPresentationTimeUs;
                    }
                    return false;
                } else if (readSource == -5) {
                    if (this.codecReconfigurationState == 2) {
                        this.buffer.clear();
                        this.codecReconfigurationState = 1;
                    }
                    onInputFormatChanged(formatHolder);
                    return true;
                } else if (this.buffer.isEndOfStream()) {
                    this.lastBufferInStreamPresentationTimeUs = this.largestQueuedPresentationTimeUs;
                    if (this.codecReconfigurationState == 2) {
                        this.buffer.clear();
                        this.codecReconfigurationState = 1;
                    }
                    this.inputStreamEnded = true;
                    if (!this.codecReceivedBuffers) {
                        processEndOfStream();
                        return false;
                    }
                    if (!this.codecNeedsEosPropagation) {
                        this.codecReceivedEos = true;
                        mediaCodecAdapter.queueInputBuffer(this.inputIndex, 0, 0, 0, 4);
                        resetInputBuffer();
                    }
                    return false;
                } else if (!this.codecReceivedBuffers && !this.buffer.isKeyFrame()) {
                    this.buffer.clear();
                    if (this.codecReconfigurationState == 2) {
                        this.codecReconfigurationState = 1;
                    }
                    return true;
                } else if (shouldSkipDecoderInputBuffer(this.buffer)) {
                    this.buffer.clear();
                    this.decoderCounters.skippedInputBufferCount++;
                    return true;
                } else {
                    boolean isEncrypted = this.buffer.isEncrypted();
                    if (isEncrypted) {
                        this.buffer.cryptoInfo.increaseClearDataFirstSubSampleBy(position);
                    }
                    long j = this.buffer.timeUs;
                    if (this.waitingForFirstSampleInFormat) {
                        if (!this.pendingOutputStreamChanges.isEmpty()) {
                            this.pendingOutputStreamChanges.peekLast().formatQueue.add(j, (Format) Assertions.checkNotNull(this.inputFormat));
                        } else {
                            this.outputStreamInfo.formatQueue.add(j, (Format) Assertions.checkNotNull(this.inputFormat));
                        }
                        this.waitingForFirstSampleInFormat = false;
                    }
                    this.largestQueuedPresentationTimeUs = Math.max(this.largestQueuedPresentationTimeUs, j);
                    if (hasReadStreamToEnd() || this.buffer.isLastSample()) {
                        this.lastBufferInStreamPresentationTimeUs = this.largestQueuedPresentationTimeUs;
                    }
                    this.buffer.flip();
                    if (this.buffer.hasSupplementalData()) {
                        handleInputBufferSupplementalData(this.buffer);
                    }
                    onQueueInputBuffer(this.buffer);
                    int codecBufferFlags = getCodecBufferFlags(this.buffer);
                    if (isEncrypted) {
                        ((MediaCodecAdapter) Assertions.checkNotNull(mediaCodecAdapter)).queueSecureInputBuffer(this.inputIndex, 0, this.buffer.cryptoInfo, j, codecBufferFlags);
                    } else {
                        ((MediaCodecAdapter) Assertions.checkNotNull(mediaCodecAdapter)).queueInputBuffer(this.inputIndex, 0, ((ByteBuffer) Assertions.checkNotNull(this.buffer.data)).limit(), j, codecBufferFlags);
                    }
                    resetInputBuffer();
                    this.codecReceivedBuffers = true;
                    this.codecReconfigurationState = 0;
                    this.decoderCounters.queuedInputBufferCount++;
                    return true;
                }
            } catch (DecoderInputBuffer.InsufficientCapacityException e) {
                onCodecError(e);
                readSourceOmittingSampleData(0);
                flushCodec();
                return true;
            }
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00ac, code lost:
        if (drainAndUpdateCodecDrmSessionV23() == false) goto L_0x00f9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00de, code lost:
        if (drainAndUpdateCodecDrmSessionV23() == false) goto L_0x00f9;
     */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x010b  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x0115 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public androidx.media3.exoplayer.DecoderReuseEvaluation onInputFormatChanged(androidx.media3.exoplayer.FormatHolder r12) throws androidx.media3.exoplayer.ExoPlaybackException {
        /*
            r11 = this;
            r0 = 1
            r11.waitingForFirstSampleInFormat = r0
            androidx.media3.common.Format r1 = r12.format
            java.lang.Object r1 = androidx.media3.common.util.Assertions.checkNotNull(r1)
            androidx.media3.common.Format r1 = (androidx.media3.common.Format) r1
            java.lang.String r2 = r1.sampleMimeType
            if (r2 == 0) goto L_0x0116
            java.lang.String r2 = r1.sampleMimeType
            java.lang.String r3 = "video/av01"
            boolean r2 = java.util.Objects.equals(r2, r3)
            r3 = 0
            if (r2 == 0) goto L_0x002f
            java.util.List<byte[]> r2 = r1.initializationData
            boolean r2 = r2.isEmpty()
            if (r2 != 0) goto L_0x002f
            androidx.media3.common.Format$Builder r1 = r1.buildUpon()
            androidx.media3.common.Format$Builder r1 = r1.setInitializationData(r3)
            androidx.media3.common.Format r1 = r1.build()
        L_0x002f:
            r7 = r1
            androidx.media3.exoplayer.drm.DrmSession r12 = r12.drmSession
            r11.setSourceDrmSession(r12)
            r11.inputFormat = r7
            boolean r12 = r11.bypassEnabled
            if (r12 == 0) goto L_0x003e
            r11.bypassDrainAndReinitialize = r0
            return r3
        L_0x003e:
            androidx.media3.exoplayer.mediacodec.MediaCodecAdapter r12 = r11.codec
            if (r12 != 0) goto L_0x0048
            r11.availableCodecInfos = r3
            r11.maybeInitCodecOrBypass()
            return r3
        L_0x0048:
            androidx.media3.exoplayer.mediacodec.MediaCodecInfo r1 = r11.codecInfo
            java.lang.Object r1 = androidx.media3.common.util.Assertions.checkNotNull(r1)
            androidx.media3.exoplayer.mediacodec.MediaCodecInfo r1 = (androidx.media3.exoplayer.mediacodec.MediaCodecInfo) r1
            androidx.media3.common.Format r2 = r11.codecInputFormat
            java.lang.Object r2 = androidx.media3.common.util.Assertions.checkNotNull(r2)
            r6 = r2
            androidx.media3.common.Format r6 = (androidx.media3.common.Format) r6
            androidx.media3.exoplayer.drm.DrmSession r2 = r11.codecDrmSession
            androidx.media3.exoplayer.drm.DrmSession r3 = r11.sourceDrmSession
            boolean r2 = r11.drmNeedsCodecReinitialization(r1, r7, r2, r3)
            if (r2 == 0) goto L_0x0072
            r11.drainAndReinitializeCodec()
            androidx.media3.exoplayer.DecoderReuseEvaluation r12 = new androidx.media3.exoplayer.DecoderReuseEvaluation
            java.lang.String r5 = r1.name
            r8 = 0
            r9 = 128(0x80, float:1.794E-43)
            r4 = r12
            r4.<init>(r5, r6, r7, r8, r9)
            return r12
        L_0x0072:
            androidx.media3.exoplayer.drm.DrmSession r2 = r11.sourceDrmSession
            androidx.media3.exoplayer.drm.DrmSession r3 = r11.codecDrmSession
            r4 = 0
            if (r2 == r3) goto L_0x007b
            r2 = r0
            goto L_0x007c
        L_0x007b:
            r2 = r4
        L_0x007c:
            if (r2 == 0) goto L_0x0087
            int r3 = androidx.media3.common.util.Util.SDK_INT
            r5 = 23
            if (r3 < r5) goto L_0x0085
            goto L_0x0087
        L_0x0085:
            r3 = r4
            goto L_0x0088
        L_0x0087:
            r3 = r0
        L_0x0088:
            androidx.media3.common.util.Assertions.checkState(r3)
            androidx.media3.exoplayer.DecoderReuseEvaluation r3 = r11.canReuseCodec(r1, r6, r7)
            int r5 = r3.result
            r8 = 3
            if (r5 == 0) goto L_0x00fb
            r9 = 16
            r10 = 2
            if (r5 == r0) goto L_0x00e1
            if (r5 == r10) goto L_0x00b5
            if (r5 != r8) goto L_0x00af
            boolean r0 = r11.updateCodecOperatingRate(r7)
            if (r0 != 0) goto L_0x00a4
            goto L_0x00ff
        L_0x00a4:
            r11.codecInputFormat = r7
            if (r2 == 0) goto L_0x00fe
            boolean r0 = r11.drainAndUpdateCodecDrmSessionV23()
            if (r0 != 0) goto L_0x00fe
            goto L_0x00f9
        L_0x00af:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            r12.<init>()
            throw r12
        L_0x00b5:
            boolean r5 = r11.updateCodecOperatingRate(r7)
            if (r5 != 0) goto L_0x00bc
            goto L_0x00ff
        L_0x00bc:
            r11.codecReconfigured = r0
            r11.codecReconfigurationState = r0
            int r5 = r11.codecAdaptationWorkaroundMode
            if (r5 == r10) goto L_0x00d4
            if (r5 != r0) goto L_0x00d3
            int r5 = r7.width
            int r9 = r6.width
            if (r5 != r9) goto L_0x00d3
            int r5 = r7.height
            int r9 = r6.height
            if (r5 != r9) goto L_0x00d3
            goto L_0x00d4
        L_0x00d3:
            r0 = r4
        L_0x00d4:
            r11.codecNeedsAdaptationWorkaroundBuffer = r0
            r11.codecInputFormat = r7
            if (r2 == 0) goto L_0x00fe
            boolean r0 = r11.drainAndUpdateCodecDrmSessionV23()
            if (r0 != 0) goto L_0x00fe
            goto L_0x00f9
        L_0x00e1:
            boolean r0 = r11.updateCodecOperatingRate(r7)
            if (r0 != 0) goto L_0x00e8
            goto L_0x00ff
        L_0x00e8:
            r11.codecInputFormat = r7
            if (r2 == 0) goto L_0x00f3
            boolean r0 = r11.drainAndUpdateCodecDrmSessionV23()
            if (r0 != 0) goto L_0x00fe
            goto L_0x00f9
        L_0x00f3:
            boolean r0 = r11.drainAndFlushCodec()
            if (r0 != 0) goto L_0x00fe
        L_0x00f9:
            r9 = r10
            goto L_0x00ff
        L_0x00fb:
            r11.drainAndReinitializeCodec()
        L_0x00fe:
            r9 = r4
        L_0x00ff:
            int r0 = r3.result
            if (r0 == 0) goto L_0x0115
            androidx.media3.exoplayer.mediacodec.MediaCodecAdapter r0 = r11.codec
            if (r0 != r12) goto L_0x010b
            int r12 = r11.codecDrainAction
            if (r12 != r8) goto L_0x0115
        L_0x010b:
            androidx.media3.exoplayer.DecoderReuseEvaluation r12 = new androidx.media3.exoplayer.DecoderReuseEvaluation
            java.lang.String r5 = r1.name
            r8 = 0
            r4 = r12
            r4.<init>(r5, r6, r7, r8, r9)
            return r12
        L_0x0115:
            return r3
        L_0x0116:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "Sample MIME type is null."
            r12.<init>(r0)
            r0 = 4005(0xfa5, float:5.612E-42)
            androidx.media3.exoplayer.ExoPlaybackException r12 = r11.createRendererException(r12, r1, r0)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.mediacodec.MediaCodecRenderer.onInputFormatChanged(androidx.media3.exoplayer.FormatHolder):androidx.media3.exoplayer.DecoderReuseEvaluation");
    }

    /* access modifiers changed from: protected */
    public long getLastBufferInStreamPresentationTimeUs() {
        return this.lastBufferInStreamPresentationTimeUs;
    }

    /* access modifiers changed from: protected */
    public void onProcessedOutputBuffer(long j) {
        this.lastProcessedOutputBufferTimeUs = j;
        while (!this.pendingOutputStreamChanges.isEmpty() && j >= this.pendingOutputStreamChanges.peek().previousStreamLastBufferTimeUs) {
            setOutputStreamInfo((OutputStreamInfo) Assertions.checkNotNull(this.pendingOutputStreamChanges.poll()));
            onProcessedStreamChange();
        }
    }

    /* access modifiers changed from: protected */
    public DecoderReuseEvaluation canReuseCodec(MediaCodecInfo mediaCodecInfo, Format format, Format format2) {
        return new DecoderReuseEvaluation(mediaCodecInfo.name, format, format2, 0, 1);
    }

    public boolean isEnded() {
        return this.outputStreamEnded;
    }

    public boolean isReady() {
        return this.inputFormat != null && (isSourceReady() || hasOutputBuffer() || (this.codecHotswapDeadlineMs != C.TIME_UNSET && getClock().elapsedRealtime() < this.codecHotswapDeadlineMs));
    }

    /* access modifiers changed from: protected */
    public float getPlaybackSpeed() {
        return this.currentPlaybackSpeed;
    }

    /* access modifiers changed from: protected */
    public float getCodecOperatingRate() {
        return this.codecOperatingRate;
    }

    /* access modifiers changed from: protected */
    public final Renderer.WakeupListener getWakeupListener() {
        return this.wakeupListener;
    }

    /* access modifiers changed from: protected */
    public final boolean updateCodecOperatingRate() throws ExoPlaybackException {
        return updateCodecOperatingRate(this.codecInputFormat);
    }

    private boolean updateCodecOperatingRate(Format format) throws ExoPlaybackException {
        if (!(Util.SDK_INT < 23 || this.codec == null || this.codecDrainAction == 3 || getState() == 0)) {
            float codecOperatingRateV23 = getCodecOperatingRateV23(this.targetPlaybackSpeed, (Format) Assertions.checkNotNull(format), getStreamFormats());
            float f = this.codecOperatingRate;
            if (f == codecOperatingRateV23) {
                return true;
            }
            if (codecOperatingRateV23 == CODEC_OPERATING_RATE_UNSET) {
                drainAndReinitializeCodec();
                return false;
            } else if (f == CODEC_OPERATING_RATE_UNSET && codecOperatingRateV23 <= this.assumedMinimumCodecOperatingRate) {
                return true;
            } else {
                Bundle bundle = new Bundle();
                bundle.putFloat("operating-rate", codecOperatingRateV23);
                ((MediaCodecAdapter) Assertions.checkNotNull(this.codec)).setParameters(bundle);
                this.codecOperatingRate = codecOperatingRateV23;
            }
        }
        return true;
    }

    private boolean drainAndFlushCodec() {
        if (this.codecReceivedBuffers) {
            this.codecDrainState = 1;
            if (this.codecNeedsEosFlushWorkaround) {
                this.codecDrainAction = 3;
                return false;
            }
            this.codecDrainAction = 1;
        }
        return true;
    }

    private boolean drainAndUpdateCodecDrmSessionV23() throws ExoPlaybackException {
        if (this.codecReceivedBuffers) {
            this.codecDrainState = 1;
            if (this.codecNeedsEosFlushWorkaround) {
                this.codecDrainAction = 3;
                return false;
            }
            this.codecDrainAction = 2;
        } else {
            updateDrmSessionV23();
        }
        return true;
    }

    private void drainAndReinitializeCodec() throws ExoPlaybackException {
        if (this.codecReceivedBuffers) {
            this.codecDrainState = 1;
            this.codecDrainAction = 3;
            return;
        }
        reinitializeCodec();
    }

    /* JADX WARNING: Removed duplicated region for block: B:71:0x010e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean drainOutputBuffer(long r20, long r22) throws androidx.media3.exoplayer.ExoPlaybackException {
        /*
            r19 = this;
            r15 = r19
            androidx.media3.exoplayer.mediacodec.MediaCodecAdapter r0 = r15.codec
            java.lang.Object r0 = androidx.media3.common.util.Assertions.checkNotNull(r0)
            r5 = r0
            androidx.media3.exoplayer.mediacodec.MediaCodecAdapter r5 = (androidx.media3.exoplayer.mediacodec.MediaCodecAdapter) r5
            boolean r0 = r19.hasOutputBuffer()
            r16 = 1
            r14 = 0
            if (r0 != 0) goto L_0x00d3
            boolean r0 = r15.codecNeedsEosOutputExceptionWorkaround
            if (r0 == 0) goto L_0x002e
            boolean r0 = r15.codecReceivedEos
            if (r0 == 0) goto L_0x002e
            android.media.MediaCodec$BufferInfo r0 = r15.outputBufferInfo     // Catch:{ IllegalStateException -> 0x0023 }
            int r0 = r5.dequeueOutputBufferIndex(r0)     // Catch:{ IllegalStateException -> 0x0023 }
            goto L_0x0034
        L_0x0023:
            r19.processEndOfStream()
            boolean r0 = r15.outputStreamEnded
            if (r0 == 0) goto L_0x002d
            r19.releaseCodec()
        L_0x002d:
            return r14
        L_0x002e:
            android.media.MediaCodec$BufferInfo r0 = r15.outputBufferInfo
            int r0 = r5.dequeueOutputBufferIndex(r0)
        L_0x0034:
            r1 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            if (r0 >= 0) goto L_0x006b
            r3 = -2
            if (r0 != r3) goto L_0x0042
            r19.processOutputMediaFormatChanged()
            return r16
        L_0x0042:
            boolean r0 = r15.codecNeedsEosPropagation
            if (r0 == 0) goto L_0x0052
            boolean r0 = r15.inputStreamEnded
            if (r0 != 0) goto L_0x004f
            int r0 = r15.codecDrainState
            r3 = 2
            if (r0 != r3) goto L_0x0052
        L_0x004f:
            r19.processEndOfStream()
        L_0x0052:
            long r3 = r15.lastOutputBufferProcessedRealtimeMs
            int r0 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r0 == 0) goto L_0x006a
            r0 = 100
            long r3 = r3 + r0
            androidx.media3.common.util.Clock r0 = r19.getClock()
            long r0 = r0.currentTimeMillis()
            int r0 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1))
            if (r0 >= 0) goto L_0x006a
            r19.processEndOfStream()
        L_0x006a:
            return r14
        L_0x006b:
            boolean r3 = r15.shouldSkipAdaptationWorkaroundOutputBuffer
            if (r3 == 0) goto L_0x0075
            r15.shouldSkipAdaptationWorkaroundOutputBuffer = r14
            r5.releaseOutputBuffer((int) r0, (boolean) r14)
            return r16
        L_0x0075:
            android.media.MediaCodec$BufferInfo r3 = r15.outputBufferInfo
            int r3 = r3.size
            if (r3 != 0) goto L_0x0087
            android.media.MediaCodec$BufferInfo r3 = r15.outputBufferInfo
            int r3 = r3.flags
            r3 = r3 & 4
            if (r3 == 0) goto L_0x0087
            r19.processEndOfStream()
            return r14
        L_0x0087:
            r15.outputIndex = r0
            java.nio.ByteBuffer r0 = r5.getOutputBuffer(r0)
            r15.outputBuffer = r0
            if (r0 == 0) goto L_0x00a6
            android.media.MediaCodec$BufferInfo r3 = r15.outputBufferInfo
            int r3 = r3.offset
            r0.position(r3)
            java.nio.ByteBuffer r0 = r15.outputBuffer
            android.media.MediaCodec$BufferInfo r3 = r15.outputBufferInfo
            int r3 = r3.offset
            android.media.MediaCodec$BufferInfo r4 = r15.outputBufferInfo
            int r4 = r4.size
            int r3 = r3 + r4
            r0.limit(r3)
        L_0x00a6:
            android.media.MediaCodec$BufferInfo r0 = r15.outputBufferInfo
            long r3 = r0.presentationTimeUs
            long r6 = r19.getLastResetPositionUs()
            int r0 = (r3 > r6 ? 1 : (r3 == r6 ? 0 : -1))
            if (r0 >= 0) goto L_0x00b5
            r0 = r16
            goto L_0x00b6
        L_0x00b5:
            r0 = r14
        L_0x00b6:
            r15.isDecodeOnlyOutputBuffer = r0
            long r3 = r15.lastBufferInStreamPresentationTimeUs
            int r0 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r0 == 0) goto L_0x00c9
            android.media.MediaCodec$BufferInfo r0 = r15.outputBufferInfo
            long r0 = r0.presentationTimeUs
            int r0 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1))
            if (r0 > 0) goto L_0x00c9
            r0 = r16
            goto L_0x00ca
        L_0x00c9:
            r0 = r14
        L_0x00ca:
            r15.isLastOutputBuffer = r0
            android.media.MediaCodec$BufferInfo r0 = r15.outputBufferInfo
            long r0 = r0.presentationTimeUs
            r15.updateOutputFormatForTime(r0)
        L_0x00d3:
            boolean r0 = r15.codecNeedsEosOutputExceptionWorkaround
            if (r0 == 0) goto L_0x0112
            boolean r0 = r15.codecReceivedEos
            if (r0 == 0) goto L_0x0112
            java.nio.ByteBuffer r6 = r15.outputBuffer     // Catch:{ IllegalStateException -> 0x0105 }
            int r7 = r15.outputIndex     // Catch:{ IllegalStateException -> 0x0105 }
            android.media.MediaCodec$BufferInfo r0 = r15.outputBufferInfo     // Catch:{ IllegalStateException -> 0x0105 }
            int r8 = r0.flags     // Catch:{ IllegalStateException -> 0x0105 }
            r9 = 1
            android.media.MediaCodec$BufferInfo r0 = r15.outputBufferInfo     // Catch:{ IllegalStateException -> 0x0105 }
            long r10 = r0.presentationTimeUs     // Catch:{ IllegalStateException -> 0x0105 }
            boolean r12 = r15.isDecodeOnlyOutputBuffer     // Catch:{ IllegalStateException -> 0x0105 }
            boolean r13 = r15.isLastOutputBuffer     // Catch:{ IllegalStateException -> 0x0105 }
            androidx.media3.common.Format r0 = r15.outputFormat     // Catch:{ IllegalStateException -> 0x0105 }
            java.lang.Object r0 = androidx.media3.common.util.Assertions.checkNotNull(r0)     // Catch:{ IllegalStateException -> 0x0105 }
            r17 = r0
            androidx.media3.common.Format r17 = (androidx.media3.common.Format) r17     // Catch:{ IllegalStateException -> 0x0105 }
            r0 = r19
            r1 = r20
            r3 = r22
            r18 = r14
            r14 = r17
            boolean r0 = r0.processOutputBuffer(r1, r3, r5, r6, r7, r8, r9, r10, r12, r13, r14)     // Catch:{ IllegalStateException -> 0x0107 }
            goto L_0x0138
        L_0x0105:
            r18 = r14
        L_0x0107:
            r19.processEndOfStream()
            boolean r0 = r15.outputStreamEnded
            if (r0 == 0) goto L_0x0111
            r19.releaseCodec()
        L_0x0111:
            return r18
        L_0x0112:
            r18 = r14
            java.nio.ByteBuffer r6 = r15.outputBuffer
            int r7 = r15.outputIndex
            android.media.MediaCodec$BufferInfo r0 = r15.outputBufferInfo
            int r8 = r0.flags
            r9 = 1
            android.media.MediaCodec$BufferInfo r0 = r15.outputBufferInfo
            long r10 = r0.presentationTimeUs
            boolean r12 = r15.isDecodeOnlyOutputBuffer
            boolean r13 = r15.isLastOutputBuffer
            androidx.media3.common.Format r0 = r15.outputFormat
            java.lang.Object r0 = androidx.media3.common.util.Assertions.checkNotNull(r0)
            r14 = r0
            androidx.media3.common.Format r14 = (androidx.media3.common.Format) r14
            r0 = r19
            r1 = r20
            r3 = r22
            boolean r0 = r0.processOutputBuffer(r1, r3, r5, r6, r7, r8, r9, r10, r12, r13, r14)
        L_0x0138:
            if (r0 == 0) goto L_0x016b
            android.media.MediaCodec$BufferInfo r0 = r15.outputBufferInfo
            long r0 = r0.presentationTimeUs
            r15.onProcessedOutputBuffer(r0)
            android.media.MediaCodec$BufferInfo r0 = r15.outputBufferInfo
            int r0 = r0.flags
            r0 = r0 & 4
            if (r0 == 0) goto L_0x014c
            r14 = r16
            goto L_0x014e
        L_0x014c:
            r14 = r18
        L_0x014e:
            if (r14 != 0) goto L_0x0162
            boolean r0 = r15.codecReceivedEos
            if (r0 == 0) goto L_0x0162
            boolean r0 = r15.isLastOutputBuffer
            if (r0 == 0) goto L_0x0162
            androidx.media3.common.util.Clock r0 = r19.getClock()
            long r0 = r0.currentTimeMillis()
            r15.lastOutputBufferProcessedRealtimeMs = r0
        L_0x0162:
            r19.resetOutputBuffer()
            if (r14 != 0) goto L_0x0168
            return r16
        L_0x0168:
            r19.processEndOfStream()
        L_0x016b:
            return r18
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.mediacodec.MediaCodecRenderer.drainOutputBuffer(long, long):boolean");
    }

    private void processOutputMediaFormatChanged() {
        this.codecHasOutputMediaFormat = true;
        MediaFormat outputFormat2 = ((MediaCodecAdapter) Assertions.checkNotNull(this.codec)).getOutputFormat();
        if (this.codecAdaptationWorkaroundMode != 0 && outputFormat2.getInteger("width") == 32 && outputFormat2.getInteger("height") == 32) {
            this.shouldSkipAdaptationWorkaroundOutputBuffer = true;
            return;
        }
        this.codecOutputMediaFormat = outputFormat2;
        this.codecOutputMediaFormatChanged = true;
    }

    private void processEndOfStream() throws ExoPlaybackException {
        int i = this.codecDrainAction;
        if (i == 1) {
            flushCodec();
        } else if (i == 2) {
            flushCodec();
            updateDrmSessionV23();
        } else if (i != 3) {
            this.outputStreamEnded = true;
            renderToEndOfStream();
        } else {
            reinitializeCodec();
        }
    }

    /* access modifiers changed from: protected */
    public final void setPendingOutputEndOfStream() {
        this.pendingOutputEndOfStream = true;
    }

    /* access modifiers changed from: protected */
    public final long getOutputStreamOffsetUs() {
        return this.outputStreamInfo.streamOffsetUs;
    }

    /* access modifiers changed from: protected */
    public final long getOutputStreamStartPositionUs() {
        return this.outputStreamInfo.startPositionUs;
    }

    private void setOutputStreamInfo(OutputStreamInfo outputStreamInfo2) {
        this.outputStreamInfo = outputStreamInfo2;
        if (outputStreamInfo2.streamOffsetUs != C.TIME_UNSET) {
            this.needToNotifyOutputFormatChangeAfterStreamChange = true;
            onOutputStreamOffsetUsChanged(outputStreamInfo2.streamOffsetUs);
        }
    }

    protected static boolean supportsFormatDrm(Format format) {
        return format.cryptoType == 0 || format.cryptoType == 2;
    }

    private boolean drmNeedsCodecReinitialization(MediaCodecInfo mediaCodecInfo, Format format, DrmSession drmSession, DrmSession drmSession2) throws ExoPlaybackException {
        CryptoConfig cryptoConfig;
        CryptoConfig cryptoConfig2;
        if (drmSession == drmSession2) {
            return false;
        }
        if (!(drmSession2 == null || drmSession == null || (cryptoConfig = drmSession2.getCryptoConfig()) == null || (cryptoConfig2 = drmSession.getCryptoConfig()) == null || !cryptoConfig.getClass().equals(cryptoConfig2.getClass()))) {
            if (!(cryptoConfig instanceof FrameworkCryptoConfig)) {
                return false;
            }
            if (drmSession2.getSchemeUuid().equals(drmSession.getSchemeUuid()) && Util.SDK_INT >= 23 && !C.PLAYREADY_UUID.equals(drmSession.getSchemeUuid()) && !C.PLAYREADY_UUID.equals(drmSession2.getSchemeUuid())) {
                if (mediaCodecInfo.secure) {
                    return false;
                }
                if (drmSession2.getState() == 2 || ((drmSession2.getState() == 3 || drmSession2.getState() == 4) && drmSession2.requiresSecureDecoder((String) Assertions.checkNotNull(format.sampleMimeType)))) {
                    return true;
                }
                return false;
            }
        }
        return true;
    }

    private void reinitializeCodec() throws ExoPlaybackException {
        releaseCodec();
        maybeInitCodecOrBypass();
    }

    private void updateDrmSessionV23() throws ExoPlaybackException {
        CryptoConfig cryptoConfig = ((DrmSession) Assertions.checkNotNull(this.sourceDrmSession)).getCryptoConfig();
        if (cryptoConfig instanceof FrameworkCryptoConfig) {
            try {
                ((MediaCrypto) Assertions.checkNotNull(this.mediaCrypto)).setMediaDrmSession(((FrameworkCryptoConfig) cryptoConfig).sessionId);
            } catch (MediaCryptoException e) {
                throw createRendererException(e, this.inputFormat, PlaybackException.ERROR_CODE_DRM_SYSTEM_ERROR);
            }
        }
        setCodecDrmSession(this.sourceDrmSession);
        this.codecDrainState = 0;
        this.codecDrainAction = 0;
    }

    private boolean bypassRender(long j, long j2) throws ExoPlaybackException {
        boolean z;
        Assertions.checkState(!this.outputStreamEnded);
        if (this.bypassBatchBuffer.hasSamples()) {
            if (!processOutputBuffer(j, j2, (MediaCodecAdapter) null, this.bypassBatchBuffer.data, this.outputIndex, 0, this.bypassBatchBuffer.getSampleCount(), this.bypassBatchBuffer.getFirstSampleTimeUs(), isDecodeOnly(getLastResetPositionUs(), this.bypassBatchBuffer.getLastSampleTimeUs()), this.bypassBatchBuffer.isEndOfStream(), (Format) Assertions.checkNotNull(this.outputFormat))) {
                return false;
            }
            onProcessedOutputBuffer(this.bypassBatchBuffer.getLastSampleTimeUs());
            this.bypassBatchBuffer.clear();
            z = false;
        } else {
            z = false;
        }
        if (this.inputStreamEnded) {
            this.outputStreamEnded = true;
            return z;
        }
        if (this.bypassSampleBufferPending) {
            Assertions.checkState(this.bypassBatchBuffer.append(this.bypassSampleBuffer));
            this.bypassSampleBufferPending = z;
        }
        if (this.bypassDrainAndReinitialize) {
            if (this.bypassBatchBuffer.hasSamples()) {
                return true;
            }
            disableBypass();
            this.bypassDrainAndReinitialize = z;
            maybeInitCodecOrBypass();
            if (!this.bypassEnabled) {
                return z;
            }
        }
        bypassRead();
        if (this.bypassBatchBuffer.hasSamples()) {
            this.bypassBatchBuffer.flip();
        }
        if (this.bypassBatchBuffer.hasSamples() || this.inputStreamEnded || this.bypassDrainAndReinitialize) {
            return true;
        }
        return z;
    }

    /* JADX WARNING: Removed duplicated region for block: B:3:0x001f  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0107 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void bypassRead() throws androidx.media3.exoplayer.ExoPlaybackException {
        /*
            r8 = this;
            boolean r0 = r8.inputStreamEnded
            r1 = 1
            r0 = r0 ^ r1
            androidx.media3.common.util.Assertions.checkState(r0)
            androidx.media3.exoplayer.FormatHolder r0 = r8.getFormatHolder()
            androidx.media3.decoder.DecoderInputBuffer r2 = r8.bypassSampleBuffer
            r2.clear()
        L_0x0010:
            androidx.media3.decoder.DecoderInputBuffer r2 = r8.bypassSampleBuffer
            r2.clear()
            androidx.media3.decoder.DecoderInputBuffer r2 = r8.bypassSampleBuffer
            r3 = 0
            int r2 = r8.readSource(r0, r2, r3)
            r4 = -5
            if (r2 == r4) goto L_0x0107
            r4 = -4
            if (r2 == r4) goto L_0x0036
            r0 = -3
            if (r2 != r0) goto L_0x0030
            boolean r0 = r8.hasReadStreamToEnd()
            if (r0 == 0) goto L_0x002f
            long r0 = r8.largestQueuedPresentationTimeUs
            r8.lastBufferInStreamPresentationTimeUs = r0
        L_0x002f:
            return
        L_0x0030:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>()
            throw r0
        L_0x0036:
            androidx.media3.decoder.DecoderInputBuffer r2 = r8.bypassSampleBuffer
            boolean r2 = r2.isEndOfStream()
            if (r2 == 0) goto L_0x0045
            r8.inputStreamEnded = r1
            long r0 = r8.largestQueuedPresentationTimeUs
            r8.lastBufferInStreamPresentationTimeUs = r0
            return
        L_0x0045:
            long r4 = r8.largestQueuedPresentationTimeUs
            androidx.media3.decoder.DecoderInputBuffer r2 = r8.bypassSampleBuffer
            long r6 = r2.timeUs
            long r4 = java.lang.Math.max(r4, r6)
            r8.largestQueuedPresentationTimeUs = r4
            boolean r2 = r8.hasReadStreamToEnd()
            if (r2 != 0) goto L_0x005f
            androidx.media3.decoder.DecoderInputBuffer r2 = r8.buffer
            boolean r2 = r2.isLastSample()
            if (r2 == 0) goto L_0x0063
        L_0x005f:
            long r4 = r8.largestQueuedPresentationTimeUs
            r8.lastBufferInStreamPresentationTimeUs = r4
        L_0x0063:
            boolean r2 = r8.waitingForFirstSampleInFormat
            java.lang.String r4 = "audio/opus"
            if (r2 == 0) goto L_0x00b1
            androidx.media3.common.Format r2 = r8.inputFormat
            java.lang.Object r2 = androidx.media3.common.util.Assertions.checkNotNull(r2)
            androidx.media3.common.Format r2 = (androidx.media3.common.Format) r2
            r8.outputFormat = r2
            java.lang.String r2 = r2.sampleMimeType
            boolean r2 = java.util.Objects.equals(r2, r4)
            if (r2 == 0) goto L_0x00a9
            androidx.media3.common.Format r2 = r8.outputFormat
            java.util.List<byte[]> r2 = r2.initializationData
            boolean r2 = r2.isEmpty()
            if (r2 != 0) goto L_0x00a9
            androidx.media3.common.Format r2 = r8.outputFormat
            java.util.List<byte[]> r2 = r2.initializationData
            java.lang.Object r2 = r2.get(r3)
            byte[] r2 = (byte[]) r2
            int r2 = androidx.media3.extractor.OpusUtil.getPreSkipSamples(r2)
            androidx.media3.common.Format r5 = r8.outputFormat
            java.lang.Object r5 = androidx.media3.common.util.Assertions.checkNotNull(r5)
            androidx.media3.common.Format r5 = (androidx.media3.common.Format) r5
            androidx.media3.common.Format$Builder r5 = r5.buildUpon()
            androidx.media3.common.Format$Builder r2 = r5.setEncoderDelay(r2)
            androidx.media3.common.Format r2 = r2.build()
            r8.outputFormat = r2
        L_0x00a9:
            androidx.media3.common.Format r2 = r8.outputFormat
            r5 = 0
            r8.onOutputFormatChanged(r2, r5)
            r8.waitingForFirstSampleInFormat = r3
        L_0x00b1:
            androidx.media3.decoder.DecoderInputBuffer r2 = r8.bypassSampleBuffer
            r2.flip()
            androidx.media3.common.Format r2 = r8.outputFormat
            if (r2 == 0) goto L_0x00f4
            java.lang.String r2 = r2.sampleMimeType
            boolean r2 = java.util.Objects.equals(r2, r4)
            if (r2 == 0) goto L_0x00f4
            androidx.media3.decoder.DecoderInputBuffer r2 = r8.bypassSampleBuffer
            boolean r2 = r2.hasSupplementalData()
            if (r2 == 0) goto L_0x00d5
            androidx.media3.decoder.DecoderInputBuffer r2 = r8.bypassSampleBuffer
            androidx.media3.common.Format r3 = r8.outputFormat
            r2.format = r3
            androidx.media3.decoder.DecoderInputBuffer r2 = r8.bypassSampleBuffer
            r8.handleInputBufferSupplementalData(r2)
        L_0x00d5:
            long r2 = r8.getLastResetPositionUs()
            androidx.media3.decoder.DecoderInputBuffer r4 = r8.bypassSampleBuffer
            long r4 = r4.timeUs
            boolean r2 = androidx.media3.extractor.OpusUtil.needToDecodeOpusFrame(r2, r4)
            if (r2 == 0) goto L_0x00f4
            androidx.media3.exoplayer.audio.OggOpusAudioPacketizer r2 = r8.oggOpusAudioPacketizer
            androidx.media3.decoder.DecoderInputBuffer r3 = r8.bypassSampleBuffer
            androidx.media3.common.Format r4 = r8.outputFormat
            java.lang.Object r4 = androidx.media3.common.util.Assertions.checkNotNull(r4)
            androidx.media3.common.Format r4 = (androidx.media3.common.Format) r4
            java.util.List<byte[]> r4 = r4.initializationData
            r2.packetize(r3, r4)
        L_0x00f4:
            boolean r2 = r8.haveBypassBatchBufferAndNewSampleSameDecodeOnlyState()
            if (r2 == 0) goto L_0x0104
            androidx.media3.exoplayer.mediacodec.BatchBuffer r2 = r8.bypassBatchBuffer
            androidx.media3.decoder.DecoderInputBuffer r3 = r8.bypassSampleBuffer
            boolean r2 = r2.append(r3)
            if (r2 != 0) goto L_0x0010
        L_0x0104:
            r8.bypassSampleBufferPending = r1
            return
        L_0x0107:
            r8.onInputFormatChanged(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.mediacodec.MediaCodecRenderer.bypassRead():void");
    }

    private boolean haveBypassBatchBufferAndNewSampleSameDecodeOnlyState() {
        if (!this.bypassBatchBuffer.hasSamples()) {
            return true;
        }
        long lastResetPositionUs = getLastResetPositionUs();
        if (isDecodeOnly(lastResetPositionUs, this.bypassBatchBuffer.getLastSampleTimeUs()) == isDecodeOnly(lastResetPositionUs, this.bypassSampleBuffer.timeUs)) {
            return true;
        }
        return false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r0 = r2.outputFormat;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean isDecodeOnly(long r3, long r5) {
        /*
            r2 = this;
            int r0 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r0 >= 0) goto L_0x001a
            androidx.media3.common.Format r0 = r2.outputFormat
            if (r0 == 0) goto L_0x0018
            java.lang.String r0 = r0.sampleMimeType
            java.lang.String r1 = "audio/opus"
            boolean r0 = java.util.Objects.equals(r0, r1)
            if (r0 == 0) goto L_0x0018
            boolean r3 = androidx.media3.extractor.OpusUtil.needToDecodeOpusFrame(r3, r5)
            if (r3 != 0) goto L_0x001a
        L_0x0018:
            r3 = 1
            goto L_0x001b
        L_0x001a:
            r3 = 0
        L_0x001b:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.mediacodec.MediaCodecRenderer.isDecodeOnly(long, long):boolean");
    }

    private static boolean isMediaCodecException(IllegalStateException illegalStateException) {
        if (illegalStateException instanceof MediaCodec.CodecException) {
            return true;
        }
        StackTraceElement[] stackTrace = illegalStateException.getStackTrace();
        if (stackTrace.length <= 0 || !stackTrace[0].getClassName().equals("android.media.MediaCodec")) {
            return false;
        }
        return true;
    }

    private int codecAdaptationWorkaroundMode(String str) {
        if (Util.SDK_INT <= 25 && "OMX.Exynos.avc.dec.secure".equals(str) && (Util.MODEL.startsWith("SM-T585") || Util.MODEL.startsWith("SM-A510") || Util.MODEL.startsWith("SM-A520") || Util.MODEL.startsWith("SM-J700"))) {
            return 2;
        }
        if (Util.SDK_INT >= 24) {
            return 0;
        }
        if ("OMX.Nvidia.h264.decode".equals(str) || "OMX.Nvidia.h264.decode.secure".equals(str)) {
            return ("flounder".equals(Util.DEVICE) || "flounder_lte".equals(Util.DEVICE) || "grouper".equals(Util.DEVICE) || "tilapia".equals(Util.DEVICE)) ? 1 : 0;
        }
        return 0;
    }

    private static boolean codecNeedsSosFlushWorkaround(String str) {
        return Util.SDK_INT == 29 && "c2.android.aac.decoder".equals(str);
    }

    private static boolean codecNeedsEosPropagationWorkaround(MediaCodecInfo mediaCodecInfo) {
        String str = mediaCodecInfo.name;
        return (Util.SDK_INT <= 25 && "OMX.rk.video_decoder.avc".equals(str)) || (Util.SDK_INT <= 29 && ("OMX.broadcom.video_decoder.tunnel".equals(str) || "OMX.broadcom.video_decoder.tunnel.secure".equals(str) || "OMX.bcm.vdec.avc.tunnel".equals(str) || "OMX.bcm.vdec.avc.tunnel.secure".equals(str) || "OMX.bcm.vdec.hevc.tunnel".equals(str) || "OMX.bcm.vdec.hevc.tunnel.secure".equals(str))) || ("Amazon".equals(Util.MANUFACTURER) && "AFTS".equals(Util.MODEL) && mediaCodecInfo.secure);
    }

    private static boolean codecNeedsEosFlushWorkaround(String str) {
        return Util.SDK_INT <= 23 && "OMX.google.vorbis.decoder".equals(str);
    }

    private static boolean codecNeedsEosOutputExceptionWorkaround(String str) {
        return Util.SDK_INT == 21 && "OMX.google.aac.decoder".equals(str);
    }

    private static final class OutputStreamInfo {
        public static final OutputStreamInfo UNSET = new OutputStreamInfo(C.TIME_UNSET, C.TIME_UNSET, C.TIME_UNSET);
        public final TimedValueQueue<Format> formatQueue = new TimedValueQueue<>();
        public final long previousStreamLastBufferTimeUs;
        public final long startPositionUs;
        public final long streamOffsetUs;

        public OutputStreamInfo(long j, long j2, long j3) {
            this.previousStreamLastBufferTimeUs = j;
            this.startPositionUs = j2;
            this.streamOffsetUs = j3;
        }
    }

    private static final class Api31 {
        private Api31() {
        }

        public static void setLogSessionIdToMediaCodecFormat(MediaCodecAdapter.Configuration configuration, PlayerId playerId) {
            LogSessionId logSessionId = playerId.getLogSessionId();
            if (!logSessionId.equals(LogSessionId.LOG_SESSION_ID_NONE)) {
                configuration.mediaFormat.setString("log-session-id", logSessionId.getStringId());
            }
        }
    }

    private final class MediaCodecRendererCodecAdapterListener implements MediaCodecAdapter.OnBufferAvailableListener {
        private MediaCodecRendererCodecAdapterListener() {
        }

        public void onInputBufferAvailable() {
            if (MediaCodecRenderer.this.wakeupListener != null) {
                MediaCodecRenderer.this.wakeupListener.onWakeup();
            }
        }

        public void onOutputBufferAvailable() {
            if (MediaCodecRenderer.this.wakeupListener != null) {
                MediaCodecRenderer.this.wakeupListener.onWakeup();
            }
        }
    }
}
