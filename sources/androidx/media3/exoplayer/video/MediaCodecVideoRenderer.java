package androidx.media3.exoplayer.video;

import android.content.Context;
import android.graphics.Point;
import android.hardware.display.DisplayManager;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Pair;
import android.view.Display;
import android.view.Surface;
import androidx.media3.common.C;
import androidx.media3.common.Effect;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.Timeline;
import androidx.media3.common.VideoSize;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.MediaFormatUtil;
import androidx.media3.common.util.Size;
import androidx.media3.common.util.TraceUtil;
import androidx.media3.common.util.Util;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.exoplayer.DecoderReuseEvaluation;
import androidx.media3.exoplayer.ExoPlaybackException;
import androidx.media3.exoplayer.FormatHolder;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.exoplayer.mediacodec.MediaCodecAdapter;
import androidx.media3.exoplayer.mediacodec.MediaCodecDecoderException;
import androidx.media3.exoplayer.mediacodec.MediaCodecInfo;
import androidx.media3.exoplayer.mediacodec.MediaCodecRenderer;
import androidx.media3.exoplayer.mediacodec.MediaCodecSelector;
import androidx.media3.exoplayer.mediacodec.MediaCodecUtil;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.video.PlaybackVideoGraphWrapper;
import androidx.media3.exoplayer.video.VideoFrameReleaseControl;
import androidx.media3.exoplayer.video.VideoRendererEventListener;
import androidx.media3.exoplayer.video.VideoSink;
import com.google.android.gms.common.Scopes;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.MoreExecutors;
import expo.modules.notifications.notifications.channels.serializers.NotificationsChannelSerializer;
import io.sentry.protocol.SentryThread;
import io.sentry.protocol.ViewHierarchyNode;
import java.nio.ByteBuffer;
import java.util.List;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

public class MediaCodecVideoRenderer extends MediaCodecRenderer implements VideoFrameReleaseControl.FrameTimingEvaluator {
    private static final int HEVC_MAX_INPUT_SIZE_THRESHOLD = 2097152;
    private static final float INITIAL_FORMAT_MAX_INPUT_SIZE_SCALE_FACTOR = 1.5f;
    private static final String KEY_CROP_BOTTOM = "crop-bottom";
    private static final String KEY_CROP_LEFT = "crop-left";
    private static final String KEY_CROP_RIGHT = "crop-right";
    private static final String KEY_CROP_TOP = "crop-top";
    private static final long MIN_EARLY_US_LATE_THRESHOLD = -30000;
    private static final long MIN_EARLY_US_VERY_LATE_THRESHOLD = -500000;
    private static final long OFFSET_FROM_PERIOD_END_TO_TREAT_AS_LAST_US = 100000;
    private static final int[] STANDARD_LONG_EDGE_VIDEO_PX = {1920, 1600, 1440, 1280, 960, 854, 640, 540, 480};
    private static final String TAG = "MediaCodecVideoRenderer";
    private static final long TUNNELING_EOS_PRESENTATION_TIME_US = Long.MAX_VALUE;
    private static boolean deviceNeedsSetOutputSurfaceWorkaround;
    private static boolean evaluatedDeviceNeedsSetOutputSurfaceWorkaround;
    private int buffersInCodecCount;
    private int changeFrameRateStrategy;
    private boolean codecHandlesHdr10PlusOutOfBandMetadata;
    private CodecMaxValues codecMaxValues;
    private boolean codecNeedsSetOutputSurfaceWorkaround;
    private int consecutiveDroppedFrameCount;
    private final Context context;
    private VideoSize decodedVideoSize;
    private final boolean deviceNeedsNoPostProcessWorkaround;
    /* access modifiers changed from: private */
    public Surface displaySurface;
    private long droppedFrameAccumulationStartTimeMs;
    private int droppedFrames;
    private final VideoRendererEventListener.EventDispatcher eventDispatcher;
    private VideoFrameMetadataListener frameMetadataListener;
    private boolean hasSetVideoSink;
    private boolean haveReportedFirstFrameRenderedForCurrentSurface;
    private long lastFrameReleaseTimeNs;
    private final int maxDroppedFramesToNotify;
    private Size outputResolution;
    private final boolean ownsVideoSink;
    private boolean pendingVideoSinkInputStreamChange;
    private long periodDurationUs;
    private PlaceholderSurface placeholderSurface;
    private int rendererPriority;
    private VideoSize reportedVideoSize;
    private int scalingMode;
    private long startPositionUs;
    private long totalVideoFrameProcessingOffsetUs;
    private boolean tunneling;
    private int tunnelingAudioSessionId;
    OnFrameRenderedListenerV23 tunnelingOnFrameRenderedListener;
    private List<Effect> videoEffects;
    private int videoFrameProcessingOffsetCount;
    private final VideoFrameReleaseControl videoFrameReleaseControl;
    private final VideoFrameReleaseControl.FrameReleaseInfo videoFrameReleaseInfo;
    private VideoSink videoSink;

    public String getName() {
        return TAG;
    }

    /* access modifiers changed from: protected */
    public void onReadyToChangeVideoSinkInputStream() {
    }

    /* access modifiers changed from: protected */
    public boolean shouldDropBuffersToKeyframe(long j, long j2, boolean z) {
        return j < MIN_EARLY_US_VERY_LATE_THRESHOLD && !z;
    }

    /* access modifiers changed from: protected */
    public boolean shouldDropOutputBuffer(long j, long j2, boolean z) {
        return j < MIN_EARLY_US_LATE_THRESHOLD && !z;
    }

    /* access modifiers changed from: protected */
    public boolean shouldForceRenderOutputBuffer(long j, long j2) {
        return j < MIN_EARLY_US_LATE_THRESHOLD && j2 > 100000;
    }

    /* access modifiers changed from: protected */
    public boolean shouldSkipBuffersWithIdenticalReleaseTime() {
        return true;
    }

    public MediaCodecVideoRenderer(Context context2, MediaCodecSelector mediaCodecSelector) {
        this(context2, mediaCodecSelector, 0);
    }

    public MediaCodecVideoRenderer(Context context2, MediaCodecSelector mediaCodecSelector, long j) {
        this(context2, mediaCodecSelector, j, (Handler) null, (VideoRendererEventListener) null, 0);
    }

    public MediaCodecVideoRenderer(Context context2, MediaCodecSelector mediaCodecSelector, long j, Handler handler, VideoRendererEventListener videoRendererEventListener, int i) {
        this(context2, MediaCodecAdapter.Factory.getDefault(context2), mediaCodecSelector, j, false, handler, videoRendererEventListener, i, 30.0f);
    }

    public MediaCodecVideoRenderer(Context context2, MediaCodecSelector mediaCodecSelector, long j, boolean z, Handler handler, VideoRendererEventListener videoRendererEventListener, int i) {
        this(context2, MediaCodecAdapter.Factory.getDefault(context2), mediaCodecSelector, j, z, handler, videoRendererEventListener, i, 30.0f);
    }

    public MediaCodecVideoRenderer(Context context2, MediaCodecAdapter.Factory factory, MediaCodecSelector mediaCodecSelector, long j, boolean z, Handler handler, VideoRendererEventListener videoRendererEventListener, int i) {
        this(context2, factory, mediaCodecSelector, j, z, handler, videoRendererEventListener, i, 30.0f);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public MediaCodecVideoRenderer(Context context2, MediaCodecAdapter.Factory factory, MediaCodecSelector mediaCodecSelector, long j, boolean z, Handler handler, VideoRendererEventListener videoRendererEventListener, int i, float f) {
        this(context2, factory, mediaCodecSelector, j, z, handler, videoRendererEventListener, i, f, (VideoSink) null);
        VideoSink videoSink2 = null;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @Deprecated
    public MediaCodecVideoRenderer(Context context2, MediaCodecAdapter.Factory factory, MediaCodecSelector mediaCodecSelector, long j, boolean z, Handler handler, VideoRendererEventListener videoRendererEventListener, int i, float f, VideoSinkProvider videoSinkProvider) {
        this(context2, factory, mediaCodecSelector, j, z, handler, videoRendererEventListener, i, f, videoSinkProvider == null ? null : videoSinkProvider.getSink());
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public MediaCodecVideoRenderer(Context context2, MediaCodecAdapter.Factory factory, MediaCodecSelector mediaCodecSelector, long j, boolean z, Handler handler, VideoRendererEventListener videoRendererEventListener, int i, float f, VideoSink videoSink2) {
        super(2, factory, mediaCodecSelector, z, f);
        VideoSink videoSink3 = videoSink2;
        Context applicationContext = context2.getApplicationContext();
        this.context = applicationContext;
        this.maxDroppedFramesToNotify = i;
        this.videoSink = videoSink3;
        Handler handler2 = handler;
        this.eventDispatcher = new VideoRendererEventListener.EventDispatcher(handler, videoRendererEventListener);
        this.ownsVideoSink = videoSink3 == null;
        long j2 = j;
        this.videoFrameReleaseControl = new VideoFrameReleaseControl(applicationContext, this, j);
        this.videoFrameReleaseInfo = new VideoFrameReleaseControl.FrameReleaseInfo();
        this.deviceNeedsNoPostProcessWorkaround = deviceNeedsNoPostProcessWorkaround();
        this.outputResolution = Size.UNKNOWN;
        this.scalingMode = 1;
        this.changeFrameRateStrategy = 0;
        this.decodedVideoSize = VideoSize.UNKNOWN;
        this.tunnelingAudioSessionId = 0;
        this.reportedVideoSize = null;
        this.rendererPriority = -1000;
        this.startPositionUs = C.TIME_UNSET;
        this.periodDurationUs = C.TIME_UNSET;
    }

    public boolean shouldForceReleaseFrame(long j, long j2) {
        return shouldForceRenderOutputBuffer(j, j2);
    }

    public boolean shouldDropFrame(long j, long j2, boolean z) {
        return shouldDropOutputBuffer(j, j2, z);
    }

    public boolean shouldIgnoreFrame(long j, long j2, long j3, boolean z, boolean z2) throws ExoPlaybackException {
        return shouldDropBuffersToKeyframe(j, j3, z) && maybeDropBuffersToKeyframe(j2, z2);
    }

    public static int supportsFormat(Context context2, MediaCodecSelector mediaCodecSelector, Format format) throws MediaCodecUtil.DecoderQueryException {
        return supportsFormatInternal(context2, mediaCodecSelector, format);
    }

    /* access modifiers changed from: protected */
    public int supportsFormat(MediaCodecSelector mediaCodecSelector, Format format) throws MediaCodecUtil.DecoderQueryException {
        return supportsFormatInternal(this.context, mediaCodecSelector, format);
    }

    private static int supportsFormatInternal(Context context2, MediaCodecSelector mediaCodecSelector, Format format) throws MediaCodecUtil.DecoderQueryException {
        boolean z;
        int i = 0;
        if (!MimeTypes.isVideo(format.sampleMimeType)) {
            return RendererCapabilities.create(0);
        }
        boolean z2 = format.drmInitData != null;
        List<MediaCodecInfo> decoderInfos = getDecoderInfos(context2, mediaCodecSelector, format, z2, false);
        if (z2 && decoderInfos.isEmpty()) {
            decoderInfos = getDecoderInfos(context2, mediaCodecSelector, format, false, false);
        }
        if (decoderInfos.isEmpty()) {
            return RendererCapabilities.create(1);
        }
        if (!supportsFormatDrm(format)) {
            return RendererCapabilities.create(2);
        }
        MediaCodecInfo mediaCodecInfo = decoderInfos.get(0);
        boolean isFormatSupported = mediaCodecInfo.isFormatSupported(format);
        if (!isFormatSupported) {
            int i2 = 1;
            while (true) {
                if (i2 >= decoderInfos.size()) {
                    break;
                }
                MediaCodecInfo mediaCodecInfo2 = decoderInfos.get(i2);
                if (mediaCodecInfo2.isFormatSupported(format)) {
                    z = false;
                    isFormatSupported = true;
                    mediaCodecInfo = mediaCodecInfo2;
                    break;
                }
                i2++;
            }
        }
        z = true;
        int i3 = isFormatSupported ? 4 : 3;
        int i4 = mediaCodecInfo.isSeamlessAdaptationSupported(format) ? 16 : 8;
        int i5 = mediaCodecInfo.hardwareAccelerated ? 64 : 0;
        int i6 = z ? 128 : 0;
        if (Util.SDK_INT >= 26 && MimeTypes.VIDEO_DOLBY_VISION.equals(format.sampleMimeType) && !Api26.doesDisplaySupportDolbyVision(context2)) {
            i6 = 256;
        }
        if (isFormatSupported) {
            List<MediaCodecInfo> decoderInfos2 = getDecoderInfos(context2, mediaCodecSelector, format, z2, true);
            if (!decoderInfos2.isEmpty()) {
                MediaCodecInfo mediaCodecInfo3 = MediaCodecUtil.getDecoderInfosSortedByFormatSupport(decoderInfos2, format).get(0);
                if (mediaCodecInfo3.isFormatSupported(format) && mediaCodecInfo3.isSeamlessAdaptationSupported(format)) {
                    i = 32;
                }
            }
        }
        return RendererCapabilities.create(i3, i4, i, i5, i6);
    }

    /* access modifiers changed from: protected */
    public List<MediaCodecInfo> getDecoderInfos(MediaCodecSelector mediaCodecSelector, Format format, boolean z) throws MediaCodecUtil.DecoderQueryException {
        return MediaCodecUtil.getDecoderInfosSortedByFormatSupport(getDecoderInfos(this.context, mediaCodecSelector, format, z, this.tunneling), format);
    }

    private static List<MediaCodecInfo> getDecoderInfos(Context context2, MediaCodecSelector mediaCodecSelector, Format format, boolean z, boolean z2) throws MediaCodecUtil.DecoderQueryException {
        if (format.sampleMimeType == null) {
            return ImmutableList.of();
        }
        if (Util.SDK_INT >= 26 && MimeTypes.VIDEO_DOLBY_VISION.equals(format.sampleMimeType) && !Api26.doesDisplaySupportDolbyVision(context2)) {
            List<MediaCodecInfo> alternativeDecoderInfos = MediaCodecUtil.getAlternativeDecoderInfos(mediaCodecSelector, format, z, z2);
            if (!alternativeDecoderInfos.isEmpty()) {
                return alternativeDecoderInfos;
            }
        }
        return MediaCodecUtil.getDecoderInfosSoftMatch(mediaCodecSelector, format, z, z2);
    }

    private static final class Api26 {
        private Api26() {
        }

        public static boolean doesDisplaySupportDolbyVision(Context context) {
            DisplayManager displayManager = (DisplayManager) context.getSystemService("display");
            Display display = displayManager != null ? displayManager.getDisplay(0) : null;
            if (display == null || !display.isHdr()) {
                return false;
            }
            for (int i : display.getHdrCapabilities().getSupportedHdrTypes()) {
                if (i == 1) {
                    return true;
                }
            }
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public void onInit() {
        super.onInit();
    }

    /* access modifiers changed from: protected */
    public void onEnabled(boolean z, boolean z2) throws ExoPlaybackException {
        super.onEnabled(z, z2);
        boolean z3 = getConfiguration().tunneling;
        Assertions.checkState(!z3 || this.tunnelingAudioSessionId != 0);
        if (this.tunneling != z3) {
            this.tunneling = z3;
            releaseCodec();
        }
        this.eventDispatcher.enabled(this.decoderCounters);
        if (!this.hasSetVideoSink) {
            if (this.videoEffects != null && this.videoSink == null) {
                this.videoSink = new PlaybackVideoGraphWrapper.Builder(this.context, this.videoFrameReleaseControl).setClock(getClock()).build().getSink();
            }
            this.hasSetVideoSink = true;
        }
        VideoSink videoSink2 = this.videoSink;
        if (videoSink2 != null) {
            videoSink2.setListener(new VideoSink.Listener() {
                public void onVideoSizeChanged(VideoSink videoSink, VideoSize videoSize) {
                }

                public void onFirstFrameRendered(VideoSink videoSink) {
                    if (MediaCodecVideoRenderer.this.displaySurface != null) {
                        MediaCodecVideoRenderer.this.notifyRenderedFirstFrame();
                    }
                }

                public void onFrameDropped(VideoSink videoSink) {
                    if (MediaCodecVideoRenderer.this.displaySurface != null) {
                        MediaCodecVideoRenderer.this.updateDroppedBufferCounters(0, 1);
                    }
                }

                public void onError(VideoSink videoSink, VideoSink.VideoSinkException videoSinkException) {
                    MediaCodecVideoRenderer mediaCodecVideoRenderer = MediaCodecVideoRenderer.this;
                    mediaCodecVideoRenderer.setPendingPlaybackException(mediaCodecVideoRenderer.createRendererException(videoSinkException, videoSinkException.format, 7001));
                }
            }, MoreExecutors.directExecutor());
            VideoFrameMetadataListener videoFrameMetadataListener = this.frameMetadataListener;
            if (videoFrameMetadataListener != null) {
                this.videoSink.setVideoFrameMetadataListener(videoFrameMetadataListener);
            }
            if (this.displaySurface != null && !this.outputResolution.equals(Size.UNKNOWN)) {
                this.videoSink.setOutputSurfaceInfo(this.displaySurface, this.outputResolution);
            }
            this.videoSink.setChangeFrameRateStrategy(this.changeFrameRateStrategy);
            this.videoSink.setPlaybackSpeed(getPlaybackSpeed());
            List<Effect> list = this.videoEffects;
            if (list != null) {
                this.videoSink.setVideoEffects(list);
            }
            this.videoSink.onRendererEnabled(z2);
            return;
        }
        this.videoFrameReleaseControl.setClock(getClock());
        this.videoFrameReleaseControl.onEnabled(z2);
    }

    public void enableMayRenderStartOfStream() {
        VideoSink videoSink2 = this.videoSink;
        if (videoSink2 != null) {
            videoSink2.enableMayRenderStartOfStream();
        } else {
            this.videoFrameReleaseControl.allowReleaseFirstFrameBeforeStarted();
        }
    }

    /* access modifiers changed from: protected */
    public void onStreamChanged(Format[] formatArr, long j, long j2, MediaSource.MediaPeriodId mediaPeriodId) throws ExoPlaybackException {
        super.onStreamChanged(formatArr, j, j2, mediaPeriodId);
        if (this.startPositionUs == C.TIME_UNSET) {
            this.startPositionUs = j;
        }
        updatePeriodDurationUs(mediaPeriodId);
    }

    private void updatePeriodDurationUs(MediaSource.MediaPeriodId mediaPeriodId) {
        Timeline timeline = getTimeline();
        if (timeline.isEmpty()) {
            this.periodDurationUs = C.TIME_UNSET;
        } else {
            this.periodDurationUs = timeline.getPeriodByUid(((MediaSource.MediaPeriodId) Assertions.checkNotNull(mediaPeriodId)).periodUid, new Timeline.Period()).getDurationUs();
        }
    }

    /* access modifiers changed from: protected */
    public void onPositionReset(long j, boolean z) throws ExoPlaybackException {
        VideoSink videoSink2 = this.videoSink;
        if (videoSink2 != null) {
            videoSink2.flush(true);
            this.videoSink.setStreamTimestampInfo(getOutputStreamStartPositionUs(), getOutputStreamOffsetUs(), getBufferTimestampAdjustmentUs(), getLastResetPositionUs());
            this.pendingVideoSinkInputStreamChange = true;
        }
        super.onPositionReset(j, z);
        if (this.videoSink == null) {
            this.videoFrameReleaseControl.reset();
        }
        if (z) {
            VideoSink videoSink3 = this.videoSink;
            if (videoSink3 != null) {
                videoSink3.join(false);
            } else {
                this.videoFrameReleaseControl.join(false);
            }
        }
        maybeSetupTunnelingForFirstFrame();
        this.consecutiveDroppedFrameCount = 0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0006, code lost:
        r0 = r1.videoSink;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean isEnded() {
        /*
            r1 = this;
            boolean r0 = super.isEnded()
            if (r0 == 0) goto L_0x0012
            androidx.media3.exoplayer.video.VideoSink r0 = r1.videoSink
            if (r0 == 0) goto L_0x0010
            boolean r0 = r0.isEnded()
            if (r0 == 0) goto L_0x0012
        L_0x0010:
            r0 = 1
            goto L_0x0013
        L_0x0012:
            r0 = 0
        L_0x0013:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.video.MediaCodecVideoRenderer.isEnded():boolean");
    }

    public boolean isReady() {
        boolean isReady = super.isReady();
        VideoSink videoSink2 = this.videoSink;
        if (videoSink2 != null) {
            return videoSink2.isReady(isReady);
        }
        if (!isReady || (getCodec() != null && this.displaySurface != null && !this.tunneling)) {
            return this.videoFrameReleaseControl.isReady(isReady);
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void onStarted() {
        super.onStarted();
        this.droppedFrames = 0;
        this.droppedFrameAccumulationStartTimeMs = getClock().elapsedRealtime();
        this.totalVideoFrameProcessingOffsetUs = 0;
        this.videoFrameProcessingOffsetCount = 0;
        VideoSink videoSink2 = this.videoSink;
        if (videoSink2 != null) {
            videoSink2.onRendererStarted();
        } else {
            this.videoFrameReleaseControl.onStarted();
        }
    }

    /* access modifiers changed from: protected */
    public void onStopped() {
        maybeNotifyDroppedFrames();
        maybeNotifyVideoFrameProcessingOffset();
        VideoSink videoSink2 = this.videoSink;
        if (videoSink2 != null) {
            videoSink2.onRendererStopped();
        } else {
            this.videoFrameReleaseControl.onStopped();
        }
        super.onStopped();
    }

    /* access modifiers changed from: protected */
    public void onDisabled() {
        this.reportedVideoSize = null;
        this.periodDurationUs = C.TIME_UNSET;
        VideoSink videoSink2 = this.videoSink;
        if (videoSink2 != null) {
            videoSink2.onRendererDisabled();
        } else {
            this.videoFrameReleaseControl.onDisabled();
        }
        maybeSetupTunnelingForFirstFrame();
        this.haveReportedFirstFrameRenderedForCurrentSurface = false;
        this.tunnelingOnFrameRenderedListener = null;
        try {
            super.onDisabled();
        } finally {
            this.eventDispatcher.disabled(this.decoderCounters);
            this.eventDispatcher.videoSizeChanged(VideoSize.UNKNOWN);
        }
    }

    /* access modifiers changed from: protected */
    public void onReset() {
        try {
            super.onReset();
        } finally {
            this.hasSetVideoSink = false;
            this.startPositionUs = C.TIME_UNSET;
            releasePlaceholderSurface();
        }
    }

    /* access modifiers changed from: protected */
    public void onRelease() {
        super.onRelease();
        VideoSink videoSink2 = this.videoSink;
        if (videoSink2 != null && this.ownsVideoSink) {
            videoSink2.release();
        }
    }

    public void handleMessage(int i, Object obj) throws ExoPlaybackException {
        if (i == 1) {
            setOutput(obj);
        } else if (i == 7) {
            VideoFrameMetadataListener videoFrameMetadataListener = (VideoFrameMetadataListener) Assertions.checkNotNull(obj);
            this.frameMetadataListener = videoFrameMetadataListener;
            VideoSink videoSink2 = this.videoSink;
            if (videoSink2 != null) {
                videoSink2.setVideoFrameMetadataListener(videoFrameMetadataListener);
            }
        } else if (i == 10) {
            int intValue = ((Integer) Assertions.checkNotNull(obj)).intValue();
            if (this.tunnelingAudioSessionId != intValue) {
                this.tunnelingAudioSessionId = intValue;
                if (this.tunneling) {
                    releaseCodec();
                }
            }
        } else if (i == 16) {
            this.rendererPriority = ((Integer) Assertions.checkNotNull(obj)).intValue();
            updateCodecImportance();
        } else if (i == 4) {
            this.scalingMode = ((Integer) Assertions.checkNotNull(obj)).intValue();
            MediaCodecAdapter codec = getCodec();
            if (codec != null) {
                codec.setVideoScalingMode(this.scalingMode);
            }
        } else if (i == 5) {
            int intValue2 = ((Integer) Assertions.checkNotNull(obj)).intValue();
            this.changeFrameRateStrategy = intValue2;
            VideoSink videoSink3 = this.videoSink;
            if (videoSink3 != null) {
                videoSink3.setChangeFrameRateStrategy(intValue2);
            } else {
                this.videoFrameReleaseControl.setChangeFrameRateStrategy(intValue2);
            }
        } else if (i == 13) {
            setVideoEffects((List) Assertions.checkNotNull(obj));
        } else if (i != 14) {
            super.handleMessage(i, obj);
        } else {
            Size size = (Size) Assertions.checkNotNull(obj);
            if (size.getWidth() != 0 && size.getHeight() != 0) {
                this.outputResolution = size;
                VideoSink videoSink4 = this.videoSink;
                if (videoSink4 != null) {
                    videoSink4.setOutputSurfaceInfo((Surface) Assertions.checkStateNotNull(this.displaySurface), size);
                }
            }
        }
    }

    private void setOutput(Object obj) throws ExoPlaybackException {
        Surface surface = obj instanceof Surface ? (Surface) obj : null;
        if (this.displaySurface != surface) {
            this.displaySurface = surface;
            if (this.videoSink == null) {
                this.videoFrameReleaseControl.setOutputSurface(surface);
            }
            this.haveReportedFirstFrameRenderedForCurrentSurface = false;
            int state = getState();
            MediaCodecAdapter codec = getCodec();
            if (codec != null && this.videoSink == null) {
                MediaCodecInfo mediaCodecInfo = (MediaCodecInfo) Assertions.checkNotNull(getCodecInfo());
                boolean hasSurfaceForCodec = hasSurfaceForCodec(mediaCodecInfo);
                if (Util.SDK_INT < 23 || !hasSurfaceForCodec || this.codecNeedsSetOutputSurfaceWorkaround) {
                    releaseCodec();
                    maybeInitCodecOrBypass();
                } else {
                    setOutputSurface(codec, getSurfaceForCodec(mediaCodecInfo));
                }
            }
            if (surface != null) {
                maybeRenotifyVideoSizeChanged();
                if (state == 2) {
                    VideoSink videoSink2 = this.videoSink;
                    if (videoSink2 != null) {
                        videoSink2.join(true);
                    } else {
                        this.videoFrameReleaseControl.join(true);
                    }
                }
            } else {
                this.reportedVideoSize = null;
                VideoSink videoSink3 = this.videoSink;
                if (videoSink3 != null) {
                    videoSink3.clearOutputSurfaceInfo();
                }
            }
            maybeSetupTunnelingForFirstFrame();
        } else if (surface != null) {
            maybeRenotifyVideoSizeChanged();
            maybeRenotifyRenderedFirstFrame();
        }
    }

    /* access modifiers changed from: protected */
    public boolean shouldInitCodec(MediaCodecInfo mediaCodecInfo) {
        return hasSurfaceForCodec(mediaCodecInfo);
    }

    /* access modifiers changed from: protected */
    public boolean getCodecNeedsEosPropagation() {
        return this.tunneling && Util.SDK_INT < 23;
    }

    /* access modifiers changed from: protected */
    public MediaCodecAdapter.Configuration getMediaCodecConfiguration(MediaCodecInfo mediaCodecInfo, Format format, MediaCrypto mediaCrypto, float f) {
        String str = mediaCodecInfo.codecMimeType;
        CodecMaxValues codecMaxValues2 = getCodecMaxValues(mediaCodecInfo, format, getStreamFormats());
        this.codecMaxValues = codecMaxValues2;
        MediaFormat mediaFormat = getMediaFormat(format, str, codecMaxValues2, f, this.deviceNeedsNoPostProcessWorkaround, this.tunneling ? this.tunnelingAudioSessionId : 0);
        Surface surfaceForCodec = getSurfaceForCodec(mediaCodecInfo);
        maybeSetKeyAllowFrameDrop(mediaFormat);
        return MediaCodecAdapter.Configuration.createForVideoDecoding(mediaCodecInfo, mediaFormat, format, surfaceForCodec, mediaCrypto);
    }

    private void maybeSetKeyAllowFrameDrop(MediaFormat mediaFormat) {
        if (this.videoSink != null && !Util.isFrameDropAllowedOnSurfaceInput(this.context)) {
            mediaFormat.setInteger("allow-frame-drop", 0);
        }
    }

    /* access modifiers changed from: protected */
    public DecoderReuseEvaluation canReuseCodec(MediaCodecInfo mediaCodecInfo, Format format, Format format2) {
        int i;
        DecoderReuseEvaluation canReuseCodec = mediaCodecInfo.canReuseCodec(format, format2);
        int i2 = canReuseCodec.discardReasons;
        CodecMaxValues codecMaxValues2 = (CodecMaxValues) Assertions.checkNotNull(this.codecMaxValues);
        if (format2.width > codecMaxValues2.width || format2.height > codecMaxValues2.height) {
            i2 |= 256;
        }
        if (getMaxInputSize(mediaCodecInfo, format2) > codecMaxValues2.inputSize) {
            i2 |= 64;
        }
        int i3 = i2;
        String str = mediaCodecInfo.name;
        if (i3 != 0) {
            i = 0;
        } else {
            i = canReuseCodec.result;
        }
        return new DecoderReuseEvaluation(str, format, format2, i, i3);
    }

    public void render(long j, long j2) throws ExoPlaybackException {
        super.render(j, j2);
        VideoSink videoSink2 = this.videoSink;
        if (videoSink2 != null) {
            try {
                videoSink2.render(j, j2);
            } catch (VideoSink.VideoSinkException e) {
                throw createRendererException(e, e.format, 7001);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void resetCodecStateForFlush() {
        super.resetCodecStateForFlush();
        this.buffersInCodecCount = 0;
    }

    public void setPlaybackSpeed(float f, float f2) throws ExoPlaybackException {
        super.setPlaybackSpeed(f, f2);
        VideoSink videoSink2 = this.videoSink;
        if (videoSink2 != null) {
            videoSink2.setPlaybackSpeed(f);
        } else {
            this.videoFrameReleaseControl.setPlaybackSpeed(f);
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0087, code lost:
        if (r3.equals(androidx.media3.common.MimeTypes.VIDEO_AV1) == false) goto L_0x0048;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x002a, code lost:
        r10 = ((java.lang.Integer) r10.first).intValue();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int getCodecMaxInputSize(androidx.media3.exoplayer.mediacodec.MediaCodecInfo r9, androidx.media3.common.Format r10) {
        /*
            int r0 = r10.width
            int r1 = r10.height
            r2 = -1
            if (r0 == r2) goto L_0x00f1
            if (r1 != r2) goto L_0x000b
            goto L_0x00f1
        L_0x000b:
            java.lang.String r3 = r10.sampleMimeType
            java.lang.Object r3 = androidx.media3.common.util.Assertions.checkNotNull(r3)
            java.lang.String r3 = (java.lang.String) r3
            java.lang.String r4 = "video/dolby-vision"
            boolean r4 = r4.equals(r3)
            java.lang.String r5 = "video/avc"
            java.lang.String r6 = "video/hevc"
            r7 = 1
            r8 = 2
            if (r4 == 0) goto L_0x003d
            android.util.Pair r10 = androidx.media3.exoplayer.mediacodec.MediaCodecUtil.getCodecProfileAndLevel(r10)
            if (r10 == 0) goto L_0x003c
            java.lang.Object r10 = r10.first
            java.lang.Integer r10 = (java.lang.Integer) r10
            int r10 = r10.intValue()
            r3 = 512(0x200, float:7.175E-43)
            if (r10 == r3) goto L_0x003a
            if (r10 == r7) goto L_0x003a
            if (r10 != r8) goto L_0x003c
        L_0x003a:
            r3 = r5
            goto L_0x003d
        L_0x003c:
            r3 = r6
        L_0x003d:
            r3.hashCode()
            int r10 = r3.hashCode()
            r4 = 4
            switch(r10) {
                case -1664118616: goto L_0x008a;
                case -1662735862: goto L_0x0080;
                case -1662541442: goto L_0x0077;
                case 1187890754: goto L_0x006b;
                case 1331836730: goto L_0x0062;
                case 1599127256: goto L_0x0056;
                case 1599127257: goto L_0x004a;
                default: goto L_0x0048;
            }
        L_0x0048:
            r7 = r2
            goto L_0x0095
        L_0x004a:
            java.lang.String r10 = "video/x-vnd.on2.vp9"
            boolean r10 = r3.equals(r10)
            if (r10 != 0) goto L_0x0054
            goto L_0x0048
        L_0x0054:
            r7 = 6
            goto L_0x0095
        L_0x0056:
            java.lang.String r10 = "video/x-vnd.on2.vp8"
            boolean r10 = r3.equals(r10)
            if (r10 != 0) goto L_0x0060
            goto L_0x0048
        L_0x0060:
            r7 = 5
            goto L_0x0095
        L_0x0062:
            boolean r10 = r3.equals(r5)
            if (r10 != 0) goto L_0x0069
            goto L_0x0048
        L_0x0069:
            r7 = r4
            goto L_0x0095
        L_0x006b:
            java.lang.String r10 = "video/mp4v-es"
            boolean r10 = r3.equals(r10)
            if (r10 != 0) goto L_0x0075
            goto L_0x0048
        L_0x0075:
            r7 = 3
            goto L_0x0095
        L_0x0077:
            boolean r10 = r3.equals(r6)
            if (r10 != 0) goto L_0x007e
            goto L_0x0048
        L_0x007e:
            r7 = r8
            goto L_0x0095
        L_0x0080:
            java.lang.String r10 = "video/av01"
            boolean r10 = r3.equals(r10)
            if (r10 != 0) goto L_0x0095
            goto L_0x0048
        L_0x008a:
            java.lang.String r10 = "video/3gpp"
            boolean r10 = r3.equals(r10)
            if (r10 != 0) goto L_0x0094
            goto L_0x0048
        L_0x0094:
            r7 = 0
        L_0x0095:
            switch(r7) {
                case 0: goto L_0x00eb;
                case 1: goto L_0x00eb;
                case 2: goto L_0x00df;
                case 3: goto L_0x00eb;
                case 4: goto L_0x009f;
                case 5: goto L_0x00eb;
                case 6: goto L_0x0099;
                default: goto L_0x0098;
            }
        L_0x0098:
            return r2
        L_0x0099:
            int r0 = r0 * r1
            int r9 = getMaxSampleSize(r0, r4)
            return r9
        L_0x009f:
            java.lang.String r10 = "BRAVIA 4K 2015"
            java.lang.String r3 = androidx.media3.common.util.Util.MODEL
            boolean r10 = r10.equals(r3)
            if (r10 != 0) goto L_0x00de
            java.lang.String r10 = "Amazon"
            java.lang.String r3 = androidx.media3.common.util.Util.MANUFACTURER
            boolean r10 = r10.equals(r3)
            if (r10 == 0) goto L_0x00cc
            java.lang.String r10 = "KFSOWI"
            java.lang.String r3 = androidx.media3.common.util.Util.MODEL
            boolean r10 = r10.equals(r3)
            if (r10 != 0) goto L_0x00de
            java.lang.String r10 = "AFTS"
            java.lang.String r3 = androidx.media3.common.util.Util.MODEL
            boolean r10 = r10.equals(r3)
            if (r10 == 0) goto L_0x00cc
            boolean r9 = r9.secure
            if (r9 == 0) goto L_0x00cc
            goto L_0x00de
        L_0x00cc:
            r9 = 16
            int r10 = androidx.media3.common.util.Util.ceilDivide((int) r0, (int) r9)
            int r0 = androidx.media3.common.util.Util.ceilDivide((int) r1, (int) r9)
            int r10 = r10 * r0
            int r10 = r10 * r9
            int r10 = r10 * r9
            int r9 = getMaxSampleSize(r10, r8)
            return r9
        L_0x00de:
            return r2
        L_0x00df:
            int r0 = r0 * r1
            int r9 = getMaxSampleSize(r0, r8)
            r10 = 2097152(0x200000, float:2.938736E-39)
            int r9 = java.lang.Math.max(r10, r9)
            return r9
        L_0x00eb:
            int r0 = r0 * r1
            int r9 = getMaxSampleSize(r0, r8)
            return r9
        L_0x00f1:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.video.MediaCodecVideoRenderer.getCodecMaxInputSize(androidx.media3.exoplayer.mediacodec.MediaCodecInfo, androidx.media3.common.Format):int");
    }

    /* access modifiers changed from: protected */
    public float getCodecOperatingRateV23(float f, Format format, Format[] formatArr) {
        float f2 = -1.0f;
        for (Format format2 : formatArr) {
            float f3 = format2.frameRate;
            if (f3 != -1.0f) {
                f2 = Math.max(f2, f3);
            }
        }
        if (f2 == -1.0f) {
            return -1.0f;
        }
        return f2 * f;
    }

    /* access modifiers changed from: protected */
    public void onReadyToInitializeCodec(Format format) throws ExoPlaybackException {
        VideoSink videoSink2 = this.videoSink;
        if (videoSink2 != null && !videoSink2.isInitialized()) {
            try {
                this.videoSink.initialize(format);
            } catch (VideoSink.VideoSinkException e) {
                throw createRendererException(e, format, 7000);
            }
        }
    }

    public void setVideoEffects(List<Effect> list) {
        this.videoEffects = list;
        VideoSink videoSink2 = this.videoSink;
        if (videoSink2 != null) {
            videoSink2.setVideoEffects(list);
        }
    }

    /* access modifiers changed from: protected */
    public void onCodecInitialized(String str, MediaCodecAdapter.Configuration configuration, long j, long j2) {
        this.eventDispatcher.decoderInitialized(str, j, j2);
        this.codecNeedsSetOutputSurfaceWorkaround = codecNeedsSetOutputSurfaceWorkaround(str);
        this.codecHandlesHdr10PlusOutOfBandMetadata = ((MediaCodecInfo) Assertions.checkNotNull(getCodecInfo())).isHdr10PlusOutOfBandMetadataSupported();
        maybeSetupTunnelingForFirstFrame();
    }

    /* access modifiers changed from: protected */
    public void onCodecReleased(String str) {
        this.eventDispatcher.decoderReleased(str);
    }

    /* access modifiers changed from: protected */
    public void onCodecError(Exception exc) {
        Log.e(TAG, "Video codec error", exc);
        this.eventDispatcher.videoCodecError(exc);
    }

    /* access modifiers changed from: protected */
    public DecoderReuseEvaluation onInputFormatChanged(FormatHolder formatHolder) throws ExoPlaybackException {
        DecoderReuseEvaluation onInputFormatChanged = super.onInputFormatChanged(formatHolder);
        this.eventDispatcher.inputFormatChanged((Format) Assertions.checkNotNull(formatHolder.format), onInputFormatChanged);
        return onInputFormatChanged;
    }

    /* access modifiers changed from: protected */
    public void onQueueInputBuffer(DecoderInputBuffer decoderInputBuffer) throws ExoPlaybackException {
        if (!this.tunneling) {
            this.buffersInCodecCount++;
        }
        if (Util.SDK_INT < 23 && this.tunneling) {
            onProcessedTunneledBuffer(decoderInputBuffer.timeUs);
        }
    }

    /* access modifiers changed from: protected */
    public int getCodecBufferFlags(DecoderInputBuffer decoderInputBuffer) {
        return (Util.SDK_INT < 34 || !this.tunneling || !isBufferBeforeStartTime(decoderInputBuffer)) ? 0 : 32;
    }

    /* access modifiers changed from: protected */
    public boolean shouldSkipDecoderInputBuffer(DecoderInputBuffer decoderInputBuffer) {
        if (decoderInputBuffer.notDependedOn() && !isBufferProbablyLastSample(decoderInputBuffer) && !decoderInputBuffer.isEncrypted()) {
            return isBufferBeforeStartTime(decoderInputBuffer);
        }
        return false;
    }

    private boolean isBufferProbablyLastSample(DecoderInputBuffer decoderInputBuffer) {
        if (hasReadStreamToEnd() || decoderInputBuffer.isLastSample() || this.periodDurationUs == C.TIME_UNSET) {
            return true;
        }
        if (this.periodDurationUs - (decoderInputBuffer.timeUs - getOutputStreamOffsetUs()) <= 100000) {
            return true;
        }
        return false;
    }

    private boolean isBufferBeforeStartTime(DecoderInputBuffer decoderInputBuffer) {
        return decoderInputBuffer.timeUs < getLastResetPositionUs();
    }

    /* access modifiers changed from: protected */
    public void onOutputFormatChanged(Format format, MediaFormat mediaFormat) {
        int i;
        int i2;
        int i3;
        int i4;
        MediaCodecAdapter codec = getCodec();
        if (codec != null) {
            codec.setVideoScalingMode(this.scalingMode);
        }
        if (this.tunneling) {
            i = format.width;
            i2 = format.height;
        } else {
            Assertions.checkNotNull(mediaFormat);
            boolean z = mediaFormat.containsKey(KEY_CROP_RIGHT) && mediaFormat.containsKey(KEY_CROP_LEFT) && mediaFormat.containsKey(KEY_CROP_BOTTOM) && mediaFormat.containsKey(KEY_CROP_TOP);
            if (z) {
                i3 = (mediaFormat.getInteger(KEY_CROP_RIGHT) - mediaFormat.getInteger(KEY_CROP_LEFT)) + 1;
            } else {
                i3 = mediaFormat.getInteger("width");
            }
            if (z) {
                i4 = (mediaFormat.getInteger(KEY_CROP_BOTTOM) - mediaFormat.getInteger(KEY_CROP_TOP)) + 1;
            } else {
                i4 = mediaFormat.getInteger("height");
            }
            int i5 = i3;
            i2 = i4;
            i = i5;
        }
        float f = format.pixelWidthHeightRatio;
        if (format.rotationDegrees == 90 || format.rotationDegrees == 270) {
            f = 1.0f / f;
            int i6 = i2;
            i2 = i;
            i = i6;
        }
        this.decodedVideoSize = new VideoSize(i, i2, f);
        if (this.videoSink == null || !this.pendingVideoSinkInputStreamChange) {
            this.videoFrameReleaseControl.setFrameRate(format.frameRate);
        } else {
            onReadyToChangeVideoSinkInputStream();
            this.videoSink.onInputStreamChanged(1, format.buildUpon().setWidth(i).setHeight(i2).setPixelWidthHeightRatio(f).build());
        }
        this.pendingVideoSinkInputStreamChange = false;
    }

    /* access modifiers changed from: protected */
    public void handleInputBufferSupplementalData(DecoderInputBuffer decoderInputBuffer) throws ExoPlaybackException {
        if (this.codecHandlesHdr10PlusOutOfBandMetadata) {
            ByteBuffer byteBuffer = (ByteBuffer) Assertions.checkNotNull(decoderInputBuffer.supplementalData);
            if (byteBuffer.remaining() >= 7) {
                byte b = byteBuffer.get();
                short s = byteBuffer.getShort();
                short s2 = byteBuffer.getShort();
                byte b2 = byteBuffer.get();
                byte b3 = byteBuffer.get();
                byteBuffer.position(0);
                if (b != -75 || s != 60 || s2 != 1 || b2 != 4) {
                    return;
                }
                if (b3 == 0 || b3 == 1) {
                    byte[] bArr = new byte[byteBuffer.remaining()];
                    byteBuffer.get(bArr);
                    byteBuffer.position(0);
                    setHdr10PlusInfoV29((MediaCodecAdapter) Assertions.checkNotNull(getCodec()), bArr);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean processOutputBuffer(long j, long j2, MediaCodecAdapter mediaCodecAdapter, ByteBuffer byteBuffer, int i, int i2, int i3, long j3, boolean z, boolean z2, Format format) throws ExoPlaybackException {
        MediaCodecAdapter mediaCodecAdapter2 = mediaCodecAdapter;
        int i4 = i;
        Assertions.checkNotNull(mediaCodecAdapter);
        final long outputStreamOffsetUs = j3 - getOutputStreamOffsetUs();
        if (this.videoSink != null) {
            try {
                final MediaCodecAdapter mediaCodecAdapter3 = mediaCodecAdapter;
                final int i5 = i;
                return this.videoSink.handleInputFrame(j3 + getBufferTimestampAdjustmentUs(), z2, j, j2, new VideoSink.VideoFrameHandler() {
                    public void render(long j) {
                        MediaCodecVideoRenderer.this.renderOutputBuffer(mediaCodecAdapter3, i5, outputStreamOffsetUs, j);
                    }

                    public void skip() {
                        MediaCodecVideoRenderer.this.skipOutputBuffer(mediaCodecAdapter3, i5, outputStreamOffsetUs);
                    }
                });
            } catch (VideoSink.VideoSinkException e) {
                throw createRendererException(e, e.format, 7001);
            }
        } else {
            int frameReleaseAction = this.videoFrameReleaseControl.getFrameReleaseAction(j3, j, j2, getOutputStreamStartPositionUs(), z2, this.videoFrameReleaseInfo);
            if (frameReleaseAction == 4) {
                return false;
            }
            if (z && !z2) {
                skipOutputBuffer(mediaCodecAdapter2, i4, outputStreamOffsetUs);
                return true;
            } else if (this.displaySurface == null) {
                if (this.videoFrameReleaseInfo.getEarlyUs() >= 30000) {
                    return false;
                }
                skipOutputBuffer(mediaCodecAdapter2, i4, outputStreamOffsetUs);
                updateVideoFrameProcessingOffsetCounters(this.videoFrameReleaseInfo.getEarlyUs());
                return true;
            } else if (frameReleaseAction == 0) {
                long nanoTime = getClock().nanoTime();
                notifyFrameMetadataListener(outputStreamOffsetUs, nanoTime, format);
                renderOutputBuffer(mediaCodecAdapter, i, outputStreamOffsetUs, nanoTime);
                updateVideoFrameProcessingOffsetCounters(this.videoFrameReleaseInfo.getEarlyUs());
                return true;
            } else if (frameReleaseAction == 1) {
                releaseFrame((MediaCodecAdapter) Assertions.checkStateNotNull(mediaCodecAdapter), i, outputStreamOffsetUs, format);
                return true;
            } else if (frameReleaseAction == 2) {
                dropOutputBuffer(mediaCodecAdapter2, i4, outputStreamOffsetUs);
                updateVideoFrameProcessingOffsetCounters(this.videoFrameReleaseInfo.getEarlyUs());
                return true;
            } else if (frameReleaseAction == 3) {
                skipOutputBuffer(mediaCodecAdapter2, i4, outputStreamOffsetUs);
                updateVideoFrameProcessingOffsetCounters(this.videoFrameReleaseInfo.getEarlyUs());
                return true;
            } else if (frameReleaseAction == 5) {
                return false;
            } else {
                throw new IllegalStateException(String.valueOf(frameReleaseAction));
            }
        }
    }

    /* access modifiers changed from: protected */
    public long getBufferTimestampAdjustmentUs() {
        return -this.startPositionUs;
    }

    private void releaseFrame(MediaCodecAdapter mediaCodecAdapter, int i, long j, Format format) {
        long releaseTimeNs = this.videoFrameReleaseInfo.getReleaseTimeNs();
        long earlyUs = this.videoFrameReleaseInfo.getEarlyUs();
        if (!shouldSkipBuffersWithIdenticalReleaseTime() || releaseTimeNs != this.lastFrameReleaseTimeNs) {
            notifyFrameMetadataListener(j, releaseTimeNs, format);
            renderOutputBufferV21(mediaCodecAdapter, i, j, releaseTimeNs);
        } else {
            skipOutputBuffer(mediaCodecAdapter, i, j);
        }
        updateVideoFrameProcessingOffsetCounters(earlyUs);
        this.lastFrameReleaseTimeNs = releaseTimeNs;
    }

    private void notifyFrameMetadataListener(long j, long j2, Format format) {
        VideoFrameMetadataListener videoFrameMetadataListener = this.frameMetadataListener;
        if (videoFrameMetadataListener != null) {
            videoFrameMetadataListener.onVideoFrameAboutToBeRendered(j, j2, format, getCodecOutputMediaFormat());
        }
    }

    /* access modifiers changed from: protected */
    public void onProcessedTunneledBuffer(long j) throws ExoPlaybackException {
        updateOutputFormatForTime(j);
        maybeNotifyVideoSizeChanged(this.decodedVideoSize);
        this.decoderCounters.renderedOutputBufferCount++;
        maybeNotifyRenderedFirstFrame();
        onProcessedOutputBuffer(j);
    }

    /* access modifiers changed from: private */
    public void onProcessedTunneledEndOfStream() {
        setPendingOutputEndOfStream();
    }

    /* access modifiers changed from: protected */
    public void onProcessedOutputBuffer(long j) {
        super.onProcessedOutputBuffer(j);
        if (!this.tunneling) {
            this.buffersInCodecCount--;
        }
    }

    /* access modifiers changed from: protected */
    public void onProcessedStreamChange() {
        super.onProcessedStreamChange();
        VideoSink videoSink2 = this.videoSink;
        if (videoSink2 != null) {
            videoSink2.setStreamTimestampInfo(getOutputStreamStartPositionUs(), getOutputStreamOffsetUs(), getBufferTimestampAdjustmentUs(), getLastResetPositionUs());
        } else {
            this.videoFrameReleaseControl.onProcessedStreamChange();
        }
        this.pendingVideoSinkInputStreamChange = true;
        maybeSetupTunnelingForFirstFrame();
    }

    /* access modifiers changed from: protected */
    public void skipOutputBuffer(MediaCodecAdapter mediaCodecAdapter, int i, long j) {
        TraceUtil.beginSection("skipVideoBuffer");
        mediaCodecAdapter.releaseOutputBuffer(i, false);
        TraceUtil.endSection();
        this.decoderCounters.skippedOutputBufferCount++;
    }

    /* access modifiers changed from: protected */
    public void dropOutputBuffer(MediaCodecAdapter mediaCodecAdapter, int i, long j) {
        TraceUtil.beginSection("dropVideoBuffer");
        mediaCodecAdapter.releaseOutputBuffer(i, false);
        TraceUtil.endSection();
        updateDroppedBufferCounters(0, 1);
    }

    /* access modifiers changed from: protected */
    public boolean maybeDropBuffersToKeyframe(long j, boolean z) throws ExoPlaybackException {
        int skipSource = skipSource(j);
        if (skipSource == 0) {
            return false;
        }
        if (z) {
            this.decoderCounters.skippedInputBufferCount += skipSource;
            this.decoderCounters.skippedOutputBufferCount += this.buffersInCodecCount;
        } else {
            this.decoderCounters.droppedToKeyframeCount++;
            updateDroppedBufferCounters(skipSource, this.buffersInCodecCount);
        }
        flushOrReinitializeCodec();
        VideoSink videoSink2 = this.videoSink;
        if (videoSink2 != null) {
            videoSink2.flush(false);
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void updateDroppedBufferCounters(int i, int i2) {
        this.decoderCounters.droppedInputBufferCount += i;
        int i3 = i + i2;
        this.decoderCounters.droppedBufferCount += i3;
        this.droppedFrames += i3;
        this.consecutiveDroppedFrameCount += i3;
        this.decoderCounters.maxConsecutiveDroppedBufferCount = Math.max(this.consecutiveDroppedFrameCount, this.decoderCounters.maxConsecutiveDroppedBufferCount);
        int i4 = this.maxDroppedFramesToNotify;
        if (i4 > 0 && this.droppedFrames >= i4) {
            maybeNotifyDroppedFrames();
        }
    }

    /* access modifiers changed from: protected */
    public void updateVideoFrameProcessingOffsetCounters(long j) {
        this.decoderCounters.addVideoFrameProcessingOffset(j);
        this.totalVideoFrameProcessingOffsetUs += j;
        this.videoFrameProcessingOffsetCount++;
    }

    /* access modifiers changed from: private */
    public void renderOutputBuffer(MediaCodecAdapter mediaCodecAdapter, int i, long j, long j2) {
        renderOutputBufferV21(mediaCodecAdapter, i, j, j2);
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public void renderOutputBuffer(MediaCodecAdapter mediaCodecAdapter, int i, long j) {
        TraceUtil.beginSection("releaseOutputBuffer");
        mediaCodecAdapter.releaseOutputBuffer(i, true);
        TraceUtil.endSection();
        this.decoderCounters.renderedOutputBufferCount++;
        this.consecutiveDroppedFrameCount = 0;
        if (this.videoSink == null) {
            maybeNotifyVideoSizeChanged(this.decodedVideoSize);
            maybeNotifyRenderedFirstFrame();
        }
    }

    /* access modifiers changed from: protected */
    public void renderOutputBufferV21(MediaCodecAdapter mediaCodecAdapter, int i, long j, long j2) {
        TraceUtil.beginSection("releaseOutputBuffer");
        mediaCodecAdapter.releaseOutputBuffer(i, j2);
        TraceUtil.endSection();
        this.decoderCounters.renderedOutputBufferCount++;
        this.consecutiveDroppedFrameCount = 0;
        if (this.videoSink == null) {
            maybeNotifyVideoSizeChanged(this.decodedVideoSize);
            maybeNotifyRenderedFirstFrame();
        }
    }

    private boolean hasSurfaceForCodec(MediaCodecInfo mediaCodecInfo) {
        Surface surface = this.displaySurface;
        return (surface != null && surface.isValid()) || shouldUseDetachedSurface(mediaCodecInfo) || shouldUsePlaceholderSurface(mediaCodecInfo);
    }

    private Surface getSurfaceForCodec(MediaCodecInfo mediaCodecInfo) {
        VideoSink videoSink2 = this.videoSink;
        if (videoSink2 != null) {
            return videoSink2.getInputSurface();
        }
        Surface surface = this.displaySurface;
        if (surface != null) {
            return surface;
        }
        if (shouldUseDetachedSurface(mediaCodecInfo)) {
            return null;
        }
        Assertions.checkState(shouldUsePlaceholderSurface(mediaCodecInfo));
        PlaceholderSurface placeholderSurface2 = this.placeholderSurface;
        if (!(placeholderSurface2 == null || placeholderSurface2.secure == mediaCodecInfo.secure)) {
            releasePlaceholderSurface();
        }
        if (this.placeholderSurface == null) {
            this.placeholderSurface = PlaceholderSurface.newInstance(this.context, mediaCodecInfo.secure);
        }
        return this.placeholderSurface;
    }

    /* access modifiers changed from: protected */
    public boolean shouldUseDetachedSurface(MediaCodecInfo mediaCodecInfo) {
        return Util.SDK_INT >= 35 && mediaCodecInfo.detachedSurfaceSupported;
    }

    private boolean shouldUsePlaceholderSurface(MediaCodecInfo mediaCodecInfo) {
        return Util.SDK_INT >= 23 && !this.tunneling && !codecNeedsSetOutputSurfaceWorkaround(mediaCodecInfo.name) && (!mediaCodecInfo.secure || PlaceholderSurface.isSecureSupported(this.context));
    }

    private void releasePlaceholderSurface() {
        PlaceholderSurface placeholderSurface2 = this.placeholderSurface;
        if (placeholderSurface2 != null) {
            placeholderSurface2.release();
            this.placeholderSurface = null;
        }
    }

    private void maybeSetupTunnelingForFirstFrame() {
        MediaCodecAdapter codec;
        if (this.tunneling && Util.SDK_INT >= 23 && (codec = getCodec()) != null) {
            this.tunnelingOnFrameRenderedListener = new OnFrameRenderedListenerV23(codec);
            if (Util.SDK_INT >= 33) {
                Bundle bundle = new Bundle();
                bundle.putInt("tunnel-peek", 1);
                codec.setParameters(bundle);
            }
        }
    }

    private void updateCodecImportance() {
        MediaCodecAdapter codec = getCodec();
        if (codec != null && Util.SDK_INT >= 35) {
            Bundle bundle = new Bundle();
            bundle.putInt(NotificationsChannelSerializer.IMPORTANCE_KEY, Math.max(0, -this.rendererPriority));
            codec.setParameters(bundle);
        }
    }

    private void maybeNotifyRenderedFirstFrame() {
        if (this.videoFrameReleaseControl.onFrameReleasedIsFirstFrame() && this.displaySurface != null) {
            notifyRenderedFirstFrame();
        }
    }

    /* access modifiers changed from: private */
    @RequiresNonNull({"displaySurface"})
    public void notifyRenderedFirstFrame() {
        this.eventDispatcher.renderedFirstFrame(this.displaySurface);
        this.haveReportedFirstFrameRenderedForCurrentSurface = true;
    }

    private void maybeRenotifyRenderedFirstFrame() {
        Surface surface = this.displaySurface;
        if (surface != null && this.haveReportedFirstFrameRenderedForCurrentSurface) {
            this.eventDispatcher.renderedFirstFrame(surface);
        }
    }

    private void maybeNotifyVideoSizeChanged(VideoSize videoSize) {
        if (!videoSize.equals(VideoSize.UNKNOWN) && !videoSize.equals(this.reportedVideoSize)) {
            this.reportedVideoSize = videoSize;
            this.eventDispatcher.videoSizeChanged(videoSize);
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
            long elapsedRealtime = getClock().elapsedRealtime();
            this.eventDispatcher.droppedFrames(this.droppedFrames, elapsedRealtime - this.droppedFrameAccumulationStartTimeMs);
            this.droppedFrames = 0;
            this.droppedFrameAccumulationStartTimeMs = elapsedRealtime;
        }
    }

    private void maybeNotifyVideoFrameProcessingOffset() {
        int i = this.videoFrameProcessingOffsetCount;
        if (i != 0) {
            this.eventDispatcher.reportVideoFrameProcessingOffset(this.totalVideoFrameProcessingOffsetUs, i);
            this.totalVideoFrameProcessingOffsetUs = 0;
            this.videoFrameProcessingOffsetCount = 0;
        }
    }

    private static void setHdr10PlusInfoV29(MediaCodecAdapter mediaCodecAdapter, byte[] bArr) {
        Bundle bundle = new Bundle();
        bundle.putByteArray("hdr10-plus-info", bArr);
        mediaCodecAdapter.setParameters(bundle);
    }

    private void setOutputSurface(MediaCodecAdapter mediaCodecAdapter, Surface surface) {
        if (Util.SDK_INT >= 23 && surface != null) {
            setOutputSurfaceV23(mediaCodecAdapter, surface);
        } else if (Util.SDK_INT >= 35) {
            detachOutputSurfaceV35(mediaCodecAdapter);
        } else {
            throw new IllegalStateException();
        }
    }

    /* access modifiers changed from: protected */
    public void setOutputSurfaceV23(MediaCodecAdapter mediaCodecAdapter, Surface surface) {
        mediaCodecAdapter.setOutputSurface(surface);
    }

    /* access modifiers changed from: protected */
    public void detachOutputSurfaceV35(MediaCodecAdapter mediaCodecAdapter) {
        mediaCodecAdapter.detachOutputSurface();
    }

    /* access modifiers changed from: protected */
    public MediaFormat getMediaFormat(Format format, String str, CodecMaxValues codecMaxValues2, float f, boolean z, int i) {
        Pair<Integer, Integer> codecProfileAndLevel;
        MediaFormat mediaFormat = new MediaFormat();
        mediaFormat.setString("mime", str);
        mediaFormat.setInteger("width", format.width);
        mediaFormat.setInteger("height", format.height);
        MediaFormatUtil.setCsdBuffers(mediaFormat, format.initializationData);
        MediaFormatUtil.maybeSetFloat(mediaFormat, "frame-rate", format.frameRate);
        MediaFormatUtil.maybeSetInteger(mediaFormat, "rotation-degrees", format.rotationDegrees);
        MediaFormatUtil.maybeSetColorInfo(mediaFormat, format.colorInfo);
        if (MimeTypes.VIDEO_DOLBY_VISION.equals(format.sampleMimeType) && (codecProfileAndLevel = MediaCodecUtil.getCodecProfileAndLevel(format)) != null) {
            MediaFormatUtil.maybeSetInteger(mediaFormat, Scopes.PROFILE, ((Integer) codecProfileAndLevel.first).intValue());
        }
        mediaFormat.setInteger("max-width", codecMaxValues2.width);
        mediaFormat.setInteger("max-height", codecMaxValues2.height);
        MediaFormatUtil.maybeSetInteger(mediaFormat, "max-input-size", codecMaxValues2.inputSize);
        if (Util.SDK_INT >= 23) {
            mediaFormat.setInteger(SentryThread.JsonKeys.PRIORITY, 0);
            if (f != -1.0f) {
                mediaFormat.setFloat("operating-rate", f);
            }
        }
        if (z) {
            mediaFormat.setInteger("no-post-process", 1);
            mediaFormat.setInteger("auto-frc", 0);
        }
        if (i != 0) {
            mediaFormat.setFeatureEnabled("tunneled-playback", true);
            mediaFormat.setInteger("audio-session-id", i);
        }
        if (Util.SDK_INT >= 35) {
            mediaFormat.setInteger(NotificationsChannelSerializer.IMPORTANCE_KEY, Math.max(0, -this.rendererPriority));
        }
        return mediaFormat;
    }

    /* access modifiers changed from: protected */
    public CodecMaxValues getCodecMaxValues(MediaCodecInfo mediaCodecInfo, Format format, Format[] formatArr) {
        int codecMaxInputSize;
        int i = format.width;
        int i2 = format.height;
        int maxInputSize = getMaxInputSize(mediaCodecInfo, format);
        if (formatArr.length == 1) {
            if (!(maxInputSize == -1 || (codecMaxInputSize = getCodecMaxInputSize(mediaCodecInfo, format)) == -1)) {
                maxInputSize = Math.min((int) (((float) maxInputSize) * INITIAL_FORMAT_MAX_INPUT_SIZE_SCALE_FACTOR), codecMaxInputSize);
            }
            return new CodecMaxValues(i, i2, maxInputSize);
        }
        int length = formatArr.length;
        boolean z = false;
        for (int i3 = 0; i3 < length; i3++) {
            Format format2 = formatArr[i3];
            if (format.colorInfo != null && format2.colorInfo == null) {
                format2 = format2.buildUpon().setColorInfo(format.colorInfo).build();
            }
            if (mediaCodecInfo.canReuseCodec(format, format2).result != 0) {
                z |= format2.width == -1 || format2.height == -1;
                i = Math.max(i, format2.width);
                i2 = Math.max(i2, format2.height);
                maxInputSize = Math.max(maxInputSize, getMaxInputSize(mediaCodecInfo, format2));
            }
        }
        if (z) {
            Log.w(TAG, "Resolutions unknown. Codec max resolution: " + i + ViewHierarchyNode.JsonKeys.X + i2);
            Point codecMaxSize = getCodecMaxSize(mediaCodecInfo, format);
            if (codecMaxSize != null) {
                i = Math.max(i, codecMaxSize.x);
                i2 = Math.max(i2, codecMaxSize.y);
                maxInputSize = Math.max(maxInputSize, getCodecMaxInputSize(mediaCodecInfo, format.buildUpon().setWidth(i).setHeight(i2).build()));
                Log.w(TAG, "Codec max resolution adjusted to: " + i + ViewHierarchyNode.JsonKeys.X + i2);
            }
        }
        return new CodecMaxValues(i, i2, maxInputSize);
    }

    /* access modifiers changed from: protected */
    public MediaCodecDecoderException createDecoderException(Throwable th, MediaCodecInfo mediaCodecInfo) {
        return new MediaCodecVideoDecoderException(th, mediaCodecInfo, this.displaySurface);
    }

    private static Point getCodecMaxSize(MediaCodecInfo mediaCodecInfo, Format format) {
        boolean z = format.height > format.width;
        int i = z ? format.height : format.width;
        int i2 = z ? format.width : format.height;
        float f = ((float) i2) / ((float) i);
        for (int i3 : STANDARD_LONG_EDGE_VIDEO_PX) {
            int i4 = (int) (((float) i3) * f);
            if (i3 <= i || i4 <= i2) {
                break;
            }
            int i5 = z ? i4 : i3;
            if (!z) {
                i3 = i4;
            }
            Point alignVideoSizeV21 = mediaCodecInfo.alignVideoSizeV21(i5, i3);
            float f2 = format.frameRate;
            if (alignVideoSizeV21 != null && mediaCodecInfo.isVideoSizeAndRateSupportedV21(alignVideoSizeV21.x, alignVideoSizeV21.y, (double) f2)) {
                return alignVideoSizeV21;
            }
        }
        return null;
    }

    protected static int getMaxInputSize(MediaCodecInfo mediaCodecInfo, Format format) {
        if (format.maxInputSize == -1) {
            return getCodecMaxInputSize(mediaCodecInfo, format);
        }
        int size = format.initializationData.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            i += format.initializationData.get(i2).length;
        }
        return format.maxInputSize + i;
    }

    private static boolean deviceNeedsNoPostProcessWorkaround() {
        return "NVIDIA".equals(Util.MANUFACTURER);
    }

    /* access modifiers changed from: protected */
    public boolean codecNeedsSetOutputSurfaceWorkaround(String str) {
        if (str.startsWith("OMX.google")) {
            return false;
        }
        synchronized (MediaCodecVideoRenderer.class) {
            if (!evaluatedDeviceNeedsSetOutputSurfaceWorkaround) {
                deviceNeedsSetOutputSurfaceWorkaround = evaluateDeviceNeedsSetOutputSurfaceWorkaround();
                evaluatedDeviceNeedsSetOutputSurfaceWorkaround = true;
            }
        }
        return deviceNeedsSetOutputSurfaceWorkaround;
    }

    /* access modifiers changed from: protected */
    public Surface getSurface() {
        return this.displaySurface;
    }

    protected static final class CodecMaxValues {
        public final int height;
        public final int inputSize;
        public final int width;

        public CodecMaxValues(int i, int i2, int i3) {
            this.width = i;
            this.height = i2;
            this.inputSize = i3;
        }
    }

    private static int getMaxSampleSize(int i, int i2) {
        return (i * 3) / (i2 * 2);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:469:0x084f, code lost:
        if (r0.equals("PGN528") == false) goto L_0x0114;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:494:0x08b7, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean evaluateDeviceNeedsSetOutputSurfaceWorkaround() {
        /*
            int r0 = androidx.media3.common.util.Util.SDK_INT
            r1 = 7
            r2 = 6
            r3 = 5
            r4 = 4
            r5 = 3
            r6 = 2
            r7 = -1
            r8 = 0
            r9 = 1
            r10 = 28
            if (r0 > r10) goto L_0x007a
            java.lang.String r0 = androidx.media3.common.util.Util.DEVICE
            r0.hashCode()
            int r11 = r0.hashCode()
            switch(r11) {
                case -1339091551: goto L_0x006b;
                case -1220081023: goto L_0x0060;
                case -1220066608: goto L_0x0055;
                case -1012436106: goto L_0x004a;
                case -760312546: goto L_0x003f;
                case -64886864: goto L_0x0034;
                case 3415681: goto L_0x0029;
                case 825323514: goto L_0x001e;
                default: goto L_0x001b;
            }
        L_0x001b:
            r0 = r7
            goto L_0x0075
        L_0x001e:
            java.lang.String r11 = "machuca"
            boolean r0 = r0.equals(r11)
            if (r0 != 0) goto L_0x0027
            goto L_0x001b
        L_0x0027:
            r0 = r1
            goto L_0x0075
        L_0x0029:
            java.lang.String r11 = "once"
            boolean r0 = r0.equals(r11)
            if (r0 != 0) goto L_0x0032
            goto L_0x001b
        L_0x0032:
            r0 = r2
            goto L_0x0075
        L_0x0034:
            java.lang.String r11 = "magnolia"
            boolean r0 = r0.equals(r11)
            if (r0 != 0) goto L_0x003d
            goto L_0x001b
        L_0x003d:
            r0 = r3
            goto L_0x0075
        L_0x003f:
            java.lang.String r11 = "aquaman"
            boolean r0 = r0.equals(r11)
            if (r0 != 0) goto L_0x0048
            goto L_0x001b
        L_0x0048:
            r0 = r4
            goto L_0x0075
        L_0x004a:
            java.lang.String r11 = "oneday"
            boolean r0 = r0.equals(r11)
            if (r0 != 0) goto L_0x0053
            goto L_0x001b
        L_0x0053:
            r0 = r5
            goto L_0x0075
        L_0x0055:
            java.lang.String r11 = "dangalUHD"
            boolean r0 = r0.equals(r11)
            if (r0 != 0) goto L_0x005e
            goto L_0x001b
        L_0x005e:
            r0 = r6
            goto L_0x0075
        L_0x0060:
            java.lang.String r11 = "dangalFHD"
            boolean r0 = r0.equals(r11)
            if (r0 != 0) goto L_0x0069
            goto L_0x001b
        L_0x0069:
            r0 = r9
            goto L_0x0075
        L_0x006b:
            java.lang.String r11 = "dangal"
            boolean r0 = r0.equals(r11)
            if (r0 != 0) goto L_0x0074
            goto L_0x001b
        L_0x0074:
            r0 = r8
        L_0x0075:
            switch(r0) {
                case 0: goto L_0x0079;
                case 1: goto L_0x0079;
                case 2: goto L_0x0079;
                case 3: goto L_0x0079;
                case 4: goto L_0x0079;
                case 5: goto L_0x0079;
                case 6: goto L_0x0079;
                case 7: goto L_0x0079;
                default: goto L_0x0078;
            }
        L_0x0078:
            goto L_0x007a
        L_0x0079:
            return r9
        L_0x007a:
            int r0 = androidx.media3.common.util.Util.SDK_INT
            r11 = 27
            if (r0 > r11) goto L_0x008b
            java.lang.String r0 = "HWEML"
            java.lang.String r12 = androidx.media3.common.util.Util.DEVICE
            boolean r0 = r0.equals(r12)
            if (r0 == 0) goto L_0x008b
            return r9
        L_0x008b:
            java.lang.String r0 = androidx.media3.common.util.Util.MODEL
            r0.hashCode()
            int r12 = r0.hashCode()
            r13 = 8
            switch(r12) {
                case -349662828: goto L_0x00f5;
                case -321033677: goto L_0x00ea;
                case 2006354: goto L_0x00df;
                case 2006367: goto L_0x00d4;
                case 2006371: goto L_0x00c9;
                case 1785421873: goto L_0x00be;
                case 1785421876: goto L_0x00b3;
                case 1798172390: goto L_0x00a8;
                case 2119412532: goto L_0x009c;
                default: goto L_0x0099;
            }
        L_0x0099:
            r0 = r7
            goto L_0x00ff
        L_0x009c:
            java.lang.String r12 = "AFTEUFF014"
            boolean r0 = r0.equals(r12)
            if (r0 != 0) goto L_0x00a5
            goto L_0x0099
        L_0x00a5:
            r0 = r13
            goto L_0x00ff
        L_0x00a8:
            java.lang.String r12 = "AFTSO001"
            boolean r0 = r0.equals(r12)
            if (r0 != 0) goto L_0x00b1
            goto L_0x0099
        L_0x00b1:
            r0 = r1
            goto L_0x00ff
        L_0x00b3:
            java.lang.String r12 = "AFTEU014"
            boolean r0 = r0.equals(r12)
            if (r0 != 0) goto L_0x00bc
            goto L_0x0099
        L_0x00bc:
            r0 = r2
            goto L_0x00ff
        L_0x00be:
            java.lang.String r12 = "AFTEU011"
            boolean r0 = r0.equals(r12)
            if (r0 != 0) goto L_0x00c7
            goto L_0x0099
        L_0x00c7:
            r0 = r3
            goto L_0x00ff
        L_0x00c9:
            java.lang.String r12 = "AFTR"
            boolean r0 = r0.equals(r12)
            if (r0 != 0) goto L_0x00d2
            goto L_0x0099
        L_0x00d2:
            r0 = r4
            goto L_0x00ff
        L_0x00d4:
            java.lang.String r12 = "AFTN"
            boolean r0 = r0.equals(r12)
            if (r0 != 0) goto L_0x00dd
            goto L_0x0099
        L_0x00dd:
            r0 = r5
            goto L_0x00ff
        L_0x00df:
            java.lang.String r12 = "AFTA"
            boolean r0 = r0.equals(r12)
            if (r0 != 0) goto L_0x00e8
            goto L_0x0099
        L_0x00e8:
            r0 = r6
            goto L_0x00ff
        L_0x00ea:
            java.lang.String r12 = "AFTKMST12"
            boolean r0 = r0.equals(r12)
            if (r0 != 0) goto L_0x00f3
            goto L_0x0099
        L_0x00f3:
            r0 = r9
            goto L_0x00ff
        L_0x00f5:
            java.lang.String r12 = "AFTJMST12"
            boolean r0 = r0.equals(r12)
            if (r0 != 0) goto L_0x00fe
            goto L_0x0099
        L_0x00fe:
            r0 = r8
        L_0x00ff:
            switch(r0) {
                case 0: goto L_0x08b9;
                case 1: goto L_0x08b9;
                case 2: goto L_0x08b9;
                case 3: goto L_0x08b9;
                case 4: goto L_0x08b9;
                case 5: goto L_0x08b9;
                case 6: goto L_0x08b9;
                case 7: goto L_0x08b9;
                case 8: goto L_0x08b9;
                default: goto L_0x0102;
            }
        L_0x0102:
            int r0 = androidx.media3.common.util.Util.SDK_INT
            r12 = 26
            if (r0 > r12) goto L_0x08b8
            java.lang.String r0 = androidx.media3.common.util.Util.DEVICE
            r0.hashCode()
            int r14 = r0.hashCode()
            switch(r14) {
                case -2144781245: goto L_0x089b;
                case -2144781185: goto L_0x088f;
                case -2144781160: goto L_0x0883;
                case -2097309513: goto L_0x0877;
                case -2022874474: goto L_0x086b;
                case -1978993182: goto L_0x085f;
                case -1978990237: goto L_0x0853;
                case -1936688988: goto L_0x0849;
                case -1936688066: goto L_0x083c;
                case -1936688065: goto L_0x082e;
                case -1931988508: goto L_0x0820;
                case -1885099851: goto L_0x0812;
                case -1696512866: goto L_0x0804;
                case -1680025915: goto L_0x07f6;
                case -1615810839: goto L_0x07e8;
                case -1600724499: goto L_0x07da;
                case -1554255044: goto L_0x07cb;
                case -1481772737: goto L_0x07bd;
                case -1481772730: goto L_0x07af;
                case -1481772729: goto L_0x07a1;
                case -1320080169: goto L_0x0793;
                case -1217592143: goto L_0x0785;
                case -1180384755: goto L_0x0777;
                case -1139198265: goto L_0x0769;
                case -1052835013: goto L_0x075b;
                case -993250464: goto L_0x074d;
                case -993250458: goto L_0x0740;
                case -965403638: goto L_0x0733;
                case -958336948: goto L_0x0726;
                case -879245230: goto L_0x0717;
                case -842500323: goto L_0x0709;
                case -821392978: goto L_0x06fb;
                case -797483286: goto L_0x06ed;
                case -794946968: goto L_0x06de;
                case -788334647: goto L_0x06cf;
                case -782144577: goto L_0x06c1;
                case -575125681: goto L_0x06b3;
                case -521118391: goto L_0x06a5;
                case -430914369: goto L_0x0697;
                case -290434366: goto L_0x0688;
                case -282781963: goto L_0x067a;
                case -277133239: goto L_0x066c;
                case -173639913: goto L_0x065e;
                case -56598463: goto L_0x064f;
                case 2126: goto L_0x0641;
                case 2564: goto L_0x0633;
                case 2715: goto L_0x0625;
                case 2719: goto L_0x0617;
                case 3091: goto L_0x0609;
                case 3483: goto L_0x05fb;
                case 73405: goto L_0x05ed;
                case 75537: goto L_0x05df;
                case 75739: goto L_0x05d1;
                case 76779: goto L_0x05c3;
                case 78669: goto L_0x05b5;
                case 79305: goto L_0x05a7;
                case 80618: goto L_0x0599;
                case 88274: goto L_0x058b;
                case 98846: goto L_0x057d;
                case 98848: goto L_0x056f;
                case 99329: goto L_0x0561;
                case 101481: goto L_0x0553;
                case 1513190: goto L_0x0545;
                case 1514184: goto L_0x0537;
                case 1514185: goto L_0x0529;
                case 2133089: goto L_0x051b;
                case 2133091: goto L_0x050d;
                case 2133120: goto L_0x04ff;
                case 2133151: goto L_0x04f1;
                case 2133182: goto L_0x04e3;
                case 2133184: goto L_0x04d5;
                case 2436959: goto L_0x04c7;
                case 2463773: goto L_0x04b9;
                case 2464648: goto L_0x04ab;
                case 2689555: goto L_0x049d;
                case 3154429: goto L_0x048f;
                case 3284551: goto L_0x0481;
                case 3351335: goto L_0x0473;
                case 3386211: goto L_0x0465;
                case 41325051: goto L_0x0457;
                case 51349633: goto L_0x0449;
                case 51350594: goto L_0x043b;
                case 55178625: goto L_0x042d;
                case 61542055: goto L_0x041f;
                case 65355429: goto L_0x0411;
                case 66214468: goto L_0x0403;
                case 66214470: goto L_0x03f5;
                case 66214473: goto L_0x03e7;
                case 66215429: goto L_0x03d9;
                case 66215431: goto L_0x03cb;
                case 66215433: goto L_0x03bd;
                case 66216390: goto L_0x03af;
                case 76402249: goto L_0x03a1;
                case 76404105: goto L_0x0393;
                case 76404911: goto L_0x0385;
                case 80963634: goto L_0x0377;
                case 82882791: goto L_0x0369;
                case 98715550: goto L_0x035b;
                case 101370885: goto L_0x034d;
                case 102844228: goto L_0x033f;
                case 165221241: goto L_0x0331;
                case 182191441: goto L_0x0323;
                case 245388979: goto L_0x0315;
                case 287431619: goto L_0x0307;
                case 307593612: goto L_0x02f9;
                case 308517133: goto L_0x02eb;
                case 316215098: goto L_0x02dd;
                case 316215116: goto L_0x02cf;
                case 316246811: goto L_0x02c1;
                case 316246818: goto L_0x02b3;
                case 407160593: goto L_0x02a5;
                case 507412548: goto L_0x0297;
                case 793982701: goto L_0x0289;
                case 794038622: goto L_0x027b;
                case 794040393: goto L_0x026d;
                case 835649806: goto L_0x025f;
                case 917340916: goto L_0x0251;
                case 958008161: goto L_0x0243;
                case 1060579533: goto L_0x0235;
                case 1150207623: goto L_0x0227;
                case 1176899427: goto L_0x0219;
                case 1280332038: goto L_0x020b;
                case 1306947716: goto L_0x01fd;
                case 1349174697: goto L_0x01ef;
                case 1522194893: goto L_0x01e0;
                case 1691543273: goto L_0x01d2;
                case 1691544261: goto L_0x01c4;
                case 1709443163: goto L_0x01b6;
                case 1865889110: goto L_0x01a8;
                case 1906253259: goto L_0x019a;
                case 1977196784: goto L_0x018c;
                case 2006372676: goto L_0x017f;
                case 2019281702: goto L_0x0172;
                case 2029784656: goto L_0x0165;
                case 2030379515: goto L_0x0158;
                case 2033393791: goto L_0x014b;
                case 2047190025: goto L_0x013e;
                case 2047252157: goto L_0x0131;
                case 2048319463: goto L_0x0124;
                case 2048855701: goto L_0x0117;
                default: goto L_0x0114;
            }
        L_0x0114:
            r1 = r7
            goto L_0x08a6
        L_0x0117:
            java.lang.String r1 = "HWWAS-H"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0120
            goto L_0x0114
        L_0x0120:
            r1 = 139(0x8b, float:1.95E-43)
            goto L_0x08a6
        L_0x0124:
            java.lang.String r1 = "HWVNS-H"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x012d
            goto L_0x0114
        L_0x012d:
            r1 = 138(0x8a, float:1.93E-43)
            goto L_0x08a6
        L_0x0131:
            java.lang.String r1 = "ELUGA_Prim"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x013a
            goto L_0x0114
        L_0x013a:
            r1 = 137(0x89, float:1.92E-43)
            goto L_0x08a6
        L_0x013e:
            java.lang.String r1 = "ELUGA_Note"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0147
            goto L_0x0114
        L_0x0147:
            r1 = 136(0x88, float:1.9E-43)
            goto L_0x08a6
        L_0x014b:
            java.lang.String r1 = "ASUS_X00AD_2"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0154
            goto L_0x0114
        L_0x0154:
            r1 = 135(0x87, float:1.89E-43)
            goto L_0x08a6
        L_0x0158:
            java.lang.String r1 = "HWCAM-H"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0161
            goto L_0x0114
        L_0x0161:
            r1 = 134(0x86, float:1.88E-43)
            goto L_0x08a6
        L_0x0165:
            java.lang.String r1 = "HWBLN-H"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x016e
            goto L_0x0114
        L_0x016e:
            r1 = 133(0x85, float:1.86E-43)
            goto L_0x08a6
        L_0x0172:
            java.lang.String r1 = "DM-01K"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x017b
            goto L_0x0114
        L_0x017b:
            r1 = 132(0x84, float:1.85E-43)
            goto L_0x08a6
        L_0x017f:
            java.lang.String r1 = "BRAVIA_ATV3_4K"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0188
            goto L_0x0114
        L_0x0188:
            r1 = 131(0x83, float:1.84E-43)
            goto L_0x08a6
        L_0x018c:
            java.lang.String r1 = "Infinix-X572"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0196
            goto L_0x0114
        L_0x0196:
            r1 = 130(0x82, float:1.82E-43)
            goto L_0x08a6
        L_0x019a:
            java.lang.String r1 = "PB2-670M"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x01a4
            goto L_0x0114
        L_0x01a4:
            r1 = 129(0x81, float:1.81E-43)
            goto L_0x08a6
        L_0x01a8:
            java.lang.String r1 = "santoni"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x01b2
            goto L_0x0114
        L_0x01b2:
            r1 = 128(0x80, float:1.794E-43)
            goto L_0x08a6
        L_0x01b6:
            java.lang.String r1 = "iball8735_9806"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x01c0
            goto L_0x0114
        L_0x01c0:
            r1 = 127(0x7f, float:1.78E-43)
            goto L_0x08a6
        L_0x01c4:
            java.lang.String r1 = "CPH1715"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x01ce
            goto L_0x0114
        L_0x01ce:
            r1 = 126(0x7e, float:1.77E-43)
            goto L_0x08a6
        L_0x01d2:
            java.lang.String r1 = "CPH1609"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x01dc
            goto L_0x0114
        L_0x01dc:
            r1 = 125(0x7d, float:1.75E-43)
            goto L_0x08a6
        L_0x01e0:
            java.lang.String r1 = "woods_f"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x01eb
            goto L_0x0114
        L_0x01eb:
            r1 = 124(0x7c, float:1.74E-43)
            goto L_0x08a6
        L_0x01ef:
            java.lang.String r1 = "htc_e56ml_dtul"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x01f9
            goto L_0x0114
        L_0x01f9:
            r1 = 123(0x7b, float:1.72E-43)
            goto L_0x08a6
        L_0x01fd:
            java.lang.String r1 = "EverStar_S"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0207
            goto L_0x0114
        L_0x0207:
            r1 = 122(0x7a, float:1.71E-43)
            goto L_0x08a6
        L_0x020b:
            java.lang.String r1 = "hwALE-H"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0215
            goto L_0x0114
        L_0x0215:
            r1 = 121(0x79, float:1.7E-43)
            goto L_0x08a6
        L_0x0219:
            java.lang.String r1 = "itel_S41"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0223
            goto L_0x0114
        L_0x0223:
            r1 = 120(0x78, float:1.68E-43)
            goto L_0x08a6
        L_0x0227:
            java.lang.String r1 = "LS-5017"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0231
            goto L_0x0114
        L_0x0231:
            r1 = 119(0x77, float:1.67E-43)
            goto L_0x08a6
        L_0x0235:
            java.lang.String r1 = "panell_d"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x023f
            goto L_0x0114
        L_0x023f:
            r1 = 118(0x76, float:1.65E-43)
            goto L_0x08a6
        L_0x0243:
            java.lang.String r1 = "j2xlteins"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x024d
            goto L_0x0114
        L_0x024d:
            r1 = 117(0x75, float:1.64E-43)
            goto L_0x08a6
        L_0x0251:
            java.lang.String r1 = "A7000plus"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x025b
            goto L_0x0114
        L_0x025b:
            r1 = 116(0x74, float:1.63E-43)
            goto L_0x08a6
        L_0x025f:
            java.lang.String r1 = "manning"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0269
            goto L_0x0114
        L_0x0269:
            r1 = 115(0x73, float:1.61E-43)
            goto L_0x08a6
        L_0x026d:
            java.lang.String r1 = "GIONEE_WBL7519"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0277
            goto L_0x0114
        L_0x0277:
            r1 = 114(0x72, float:1.6E-43)
            goto L_0x08a6
        L_0x027b:
            java.lang.String r1 = "GIONEE_WBL7365"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0285
            goto L_0x0114
        L_0x0285:
            r1 = 113(0x71, float:1.58E-43)
            goto L_0x08a6
        L_0x0289:
            java.lang.String r1 = "GIONEE_WBL5708"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0293
            goto L_0x0114
        L_0x0293:
            r1 = 112(0x70, float:1.57E-43)
            goto L_0x08a6
        L_0x0297:
            java.lang.String r1 = "QM16XE_U"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x02a1
            goto L_0x0114
        L_0x02a1:
            r1 = 111(0x6f, float:1.56E-43)
            goto L_0x08a6
        L_0x02a5:
            java.lang.String r1 = "Pixi5-10_4G"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x02af
            goto L_0x0114
        L_0x02af:
            r1 = 110(0x6e, float:1.54E-43)
            goto L_0x08a6
        L_0x02b3:
            java.lang.String r1 = "TB3-850M"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x02bd
            goto L_0x0114
        L_0x02bd:
            r1 = 109(0x6d, float:1.53E-43)
            goto L_0x08a6
        L_0x02c1:
            java.lang.String r1 = "TB3-850F"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x02cb
            goto L_0x0114
        L_0x02cb:
            r1 = 108(0x6c, float:1.51E-43)
            goto L_0x08a6
        L_0x02cf:
            java.lang.String r1 = "TB3-730X"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x02d9
            goto L_0x0114
        L_0x02d9:
            r1 = 107(0x6b, float:1.5E-43)
            goto L_0x08a6
        L_0x02dd:
            java.lang.String r1 = "TB3-730F"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x02e7
            goto L_0x0114
        L_0x02e7:
            r1 = 106(0x6a, float:1.49E-43)
            goto L_0x08a6
        L_0x02eb:
            java.lang.String r1 = "A7020a48"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x02f5
            goto L_0x0114
        L_0x02f5:
            r1 = 105(0x69, float:1.47E-43)
            goto L_0x08a6
        L_0x02f9:
            java.lang.String r1 = "A7010a48"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0303
            goto L_0x0114
        L_0x0303:
            r1 = 104(0x68, float:1.46E-43)
            goto L_0x08a6
        L_0x0307:
            java.lang.String r1 = "griffin"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0311
            goto L_0x0114
        L_0x0311:
            r1 = 103(0x67, float:1.44E-43)
            goto L_0x08a6
        L_0x0315:
            java.lang.String r1 = "marino_f"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x031f
            goto L_0x0114
        L_0x031f:
            r1 = 102(0x66, float:1.43E-43)
            goto L_0x08a6
        L_0x0323:
            java.lang.String r1 = "CPY83_I00"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x032d
            goto L_0x0114
        L_0x032d:
            r1 = 101(0x65, float:1.42E-43)
            goto L_0x08a6
        L_0x0331:
            java.lang.String r1 = "A2016a40"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x033b
            goto L_0x0114
        L_0x033b:
            r1 = 100
            goto L_0x08a6
        L_0x033f:
            java.lang.String r1 = "le_x6"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0349
            goto L_0x0114
        L_0x0349:
            r1 = 99
            goto L_0x08a6
        L_0x034d:
            java.lang.String r1 = "l5460"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0357
            goto L_0x0114
        L_0x0357:
            r1 = 98
            goto L_0x08a6
        L_0x035b:
            java.lang.String r1 = "i9031"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0365
            goto L_0x0114
        L_0x0365:
            r1 = 97
            goto L_0x08a6
        L_0x0369:
            java.lang.String r1 = "X3_HK"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0373
            goto L_0x0114
        L_0x0373:
            r1 = 96
            goto L_0x08a6
        L_0x0377:
            java.lang.String r1 = "V23GB"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0381
            goto L_0x0114
        L_0x0381:
            r1 = 95
            goto L_0x08a6
        L_0x0385:
            java.lang.String r1 = "Q4310"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x038f
            goto L_0x0114
        L_0x038f:
            r1 = 94
            goto L_0x08a6
        L_0x0393:
            java.lang.String r1 = "Q4260"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x039d
            goto L_0x0114
        L_0x039d:
            r1 = 93
            goto L_0x08a6
        L_0x03a1:
            java.lang.String r1 = "PRO7S"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x03ab
            goto L_0x0114
        L_0x03ab:
            r1 = 92
            goto L_0x08a6
        L_0x03af:
            java.lang.String r1 = "F3311"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x03b9
            goto L_0x0114
        L_0x03b9:
            r1 = 91
            goto L_0x08a6
        L_0x03bd:
            java.lang.String r1 = "F3215"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x03c7
            goto L_0x0114
        L_0x03c7:
            r1 = 90
            goto L_0x08a6
        L_0x03cb:
            java.lang.String r1 = "F3213"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x03d5
            goto L_0x0114
        L_0x03d5:
            r1 = 89
            goto L_0x08a6
        L_0x03d9:
            java.lang.String r1 = "F3211"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x03e3
            goto L_0x0114
        L_0x03e3:
            r1 = 88
            goto L_0x08a6
        L_0x03e7:
            java.lang.String r1 = "F3116"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x03f1
            goto L_0x0114
        L_0x03f1:
            r1 = 87
            goto L_0x08a6
        L_0x03f5:
            java.lang.String r1 = "F3113"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x03ff
            goto L_0x0114
        L_0x03ff:
            r1 = 86
            goto L_0x08a6
        L_0x0403:
            java.lang.String r1 = "F3111"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x040d
            goto L_0x0114
        L_0x040d:
            r1 = 85
            goto L_0x08a6
        L_0x0411:
            java.lang.String r1 = "E5643"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x041b
            goto L_0x0114
        L_0x041b:
            r1 = 84
            goto L_0x08a6
        L_0x041f:
            java.lang.String r1 = "A1601"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0429
            goto L_0x0114
        L_0x0429:
            r1 = 83
            goto L_0x08a6
        L_0x042d:
            java.lang.String r1 = "Aura_Note_2"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0437
            goto L_0x0114
        L_0x0437:
            r1 = 82
            goto L_0x08a6
        L_0x043b:
            java.lang.String r1 = "602LV"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0445
            goto L_0x0114
        L_0x0445:
            r1 = 81
            goto L_0x08a6
        L_0x0449:
            java.lang.String r1 = "601LV"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0453
            goto L_0x0114
        L_0x0453:
            r1 = 80
            goto L_0x08a6
        L_0x0457:
            java.lang.String r1 = "MEIZU_M5"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0461
            goto L_0x0114
        L_0x0461:
            r1 = 79
            goto L_0x08a6
        L_0x0465:
            java.lang.String r1 = "p212"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x046f
            goto L_0x0114
        L_0x046f:
            r1 = 78
            goto L_0x08a6
        L_0x0473:
            java.lang.String r1 = "mido"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x047d
            goto L_0x0114
        L_0x047d:
            r1 = 77
            goto L_0x08a6
        L_0x0481:
            java.lang.String r1 = "kate"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x048b
            goto L_0x0114
        L_0x048b:
            r1 = 76
            goto L_0x08a6
        L_0x048f:
            java.lang.String r1 = "fugu"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0499
            goto L_0x0114
        L_0x0499:
            r1 = 75
            goto L_0x08a6
        L_0x049d:
            java.lang.String r1 = "XE2X"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x04a7
            goto L_0x0114
        L_0x04a7:
            r1 = 74
            goto L_0x08a6
        L_0x04ab:
            java.lang.String r1 = "Q427"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x04b5
            goto L_0x0114
        L_0x04b5:
            r1 = 73
            goto L_0x08a6
        L_0x04b9:
            java.lang.String r1 = "Q350"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x04c3
            goto L_0x0114
        L_0x04c3:
            r1 = 72
            goto L_0x08a6
        L_0x04c7:
            java.lang.String r1 = "P681"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x04d1
            goto L_0x0114
        L_0x04d1:
            r1 = 71
            goto L_0x08a6
        L_0x04d5:
            java.lang.String r1 = "F04J"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x04df
            goto L_0x0114
        L_0x04df:
            r1 = 70
            goto L_0x08a6
        L_0x04e3:
            java.lang.String r1 = "F04H"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x04ed
            goto L_0x0114
        L_0x04ed:
            r1 = 69
            goto L_0x08a6
        L_0x04f1:
            java.lang.String r1 = "F03H"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x04fb
            goto L_0x0114
        L_0x04fb:
            r1 = 68
            goto L_0x08a6
        L_0x04ff:
            java.lang.String r1 = "F02H"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0509
            goto L_0x0114
        L_0x0509:
            r1 = 67
            goto L_0x08a6
        L_0x050d:
            java.lang.String r1 = "F01J"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0517
            goto L_0x0114
        L_0x0517:
            r1 = 66
            goto L_0x08a6
        L_0x051b:
            java.lang.String r1 = "F01H"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0525
            goto L_0x0114
        L_0x0525:
            r1 = 65
            goto L_0x08a6
        L_0x0529:
            java.lang.String r1 = "1714"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0533
            goto L_0x0114
        L_0x0533:
            r1 = 64
            goto L_0x08a6
        L_0x0537:
            java.lang.String r1 = "1713"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0541
            goto L_0x0114
        L_0x0541:
            r1 = 63
            goto L_0x08a6
        L_0x0545:
            java.lang.String r1 = "1601"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x054f
            goto L_0x0114
        L_0x054f:
            r1 = 62
            goto L_0x08a6
        L_0x0553:
            java.lang.String r1 = "flo"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x055d
            goto L_0x0114
        L_0x055d:
            r1 = 61
            goto L_0x08a6
        L_0x0561:
            java.lang.String r1 = "deb"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x056b
            goto L_0x0114
        L_0x056b:
            r1 = 60
            goto L_0x08a6
        L_0x056f:
            java.lang.String r1 = "cv3"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0579
            goto L_0x0114
        L_0x0579:
            r1 = 59
            goto L_0x08a6
        L_0x057d:
            java.lang.String r1 = "cv1"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0587
            goto L_0x0114
        L_0x0587:
            r1 = 58
            goto L_0x08a6
        L_0x058b:
            java.lang.String r1 = "Z80"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0595
            goto L_0x0114
        L_0x0595:
            r1 = 57
            goto L_0x08a6
        L_0x0599:
            java.lang.String r1 = "QX1"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x05a3
            goto L_0x0114
        L_0x05a3:
            r1 = 56
            goto L_0x08a6
        L_0x05a7:
            java.lang.String r1 = "PLE"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x05b1
            goto L_0x0114
        L_0x05b1:
            r1 = 55
            goto L_0x08a6
        L_0x05b5:
            java.lang.String r1 = "P85"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x05bf
            goto L_0x0114
        L_0x05bf:
            r1 = 54
            goto L_0x08a6
        L_0x05c3:
            java.lang.String r1 = "MX6"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x05cd
            goto L_0x0114
        L_0x05cd:
            r1 = 53
            goto L_0x08a6
        L_0x05d1:
            java.lang.String r1 = "M5c"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x05db
            goto L_0x0114
        L_0x05db:
            r1 = 52
            goto L_0x08a6
        L_0x05df:
            java.lang.String r1 = "M04"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x05e9
            goto L_0x0114
        L_0x05e9:
            r1 = 51
            goto L_0x08a6
        L_0x05ed:
            java.lang.String r1 = "JGZ"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x05f7
            goto L_0x0114
        L_0x05f7:
            r1 = 50
            goto L_0x08a6
        L_0x05fb:
            java.lang.String r1 = "mh"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0605
            goto L_0x0114
        L_0x0605:
            r1 = 49
            goto L_0x08a6
        L_0x0609:
            java.lang.String r1 = "b5"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0613
            goto L_0x0114
        L_0x0613:
            r1 = 48
            goto L_0x08a6
        L_0x0617:
            java.lang.String r1 = "V5"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0621
            goto L_0x0114
        L_0x0621:
            r1 = 47
            goto L_0x08a6
        L_0x0625:
            java.lang.String r1 = "V1"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x062f
            goto L_0x0114
        L_0x062f:
            r1 = 46
            goto L_0x08a6
        L_0x0633:
            java.lang.String r1 = "Q5"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x063d
            goto L_0x0114
        L_0x063d:
            r1 = 45
            goto L_0x08a6
        L_0x0641:
            java.lang.String r1 = "C1"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x064b
            goto L_0x0114
        L_0x064b:
            r1 = 44
            goto L_0x08a6
        L_0x064f:
            java.lang.String r1 = "woods_fn"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x065a
            goto L_0x0114
        L_0x065a:
            r1 = 43
            goto L_0x08a6
        L_0x065e:
            java.lang.String r1 = "ELUGA_A3_Pro"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0668
            goto L_0x0114
        L_0x0668:
            r1 = 42
            goto L_0x08a6
        L_0x066c:
            java.lang.String r1 = "Z12_PRO"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0676
            goto L_0x0114
        L_0x0676:
            r1 = 41
            goto L_0x08a6
        L_0x067a:
            java.lang.String r1 = "BLACK-1X"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0684
            goto L_0x0114
        L_0x0684:
            r1 = 40
            goto L_0x08a6
        L_0x0688:
            java.lang.String r1 = "taido_row"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0693
            goto L_0x0114
        L_0x0693:
            r1 = 39
            goto L_0x08a6
        L_0x0697:
            java.lang.String r1 = "Pixi4-7_3G"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x06a1
            goto L_0x0114
        L_0x06a1:
            r1 = 38
            goto L_0x08a6
        L_0x06a5:
            java.lang.String r1 = "GIONEE_GBL7360"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x06af
            goto L_0x0114
        L_0x06af:
            r1 = 37
            goto L_0x08a6
        L_0x06b3:
            java.lang.String r1 = "GiONEE_CBL7513"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x06bd
            goto L_0x0114
        L_0x06bd:
            r1 = 36
            goto L_0x08a6
        L_0x06c1:
            java.lang.String r1 = "OnePlus5T"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x06cb
            goto L_0x0114
        L_0x06cb:
            r1 = 35
            goto L_0x08a6
        L_0x06cf:
            java.lang.String r1 = "whyred"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x06da
            goto L_0x0114
        L_0x06da:
            r1 = 34
            goto L_0x08a6
        L_0x06de:
            java.lang.String r1 = "watson"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x06e9
            goto L_0x0114
        L_0x06e9:
            r1 = 33
            goto L_0x08a6
        L_0x06ed:
            java.lang.String r1 = "SVP-DTV15"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x06f7
            goto L_0x0114
        L_0x06f7:
            r1 = 32
            goto L_0x08a6
        L_0x06fb:
            java.lang.String r1 = "A7000-a"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0705
            goto L_0x0114
        L_0x0705:
            r1 = 31
            goto L_0x08a6
        L_0x0709:
            java.lang.String r1 = "nicklaus_f"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0713
            goto L_0x0114
        L_0x0713:
            r1 = 30
            goto L_0x08a6
        L_0x0717:
            java.lang.String r1 = "tcl_eu"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0722
            goto L_0x0114
        L_0x0722:
            r1 = 29
            goto L_0x08a6
        L_0x0726:
            java.lang.String r1 = "ELUGA_Ray_X"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0730
            goto L_0x0114
        L_0x0730:
            r1 = r10
            goto L_0x08a6
        L_0x0733:
            java.lang.String r1 = "s905x018"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x073d
            goto L_0x0114
        L_0x073d:
            r1 = r11
            goto L_0x08a6
        L_0x0740:
            java.lang.String r1 = "A10-70L"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x074a
            goto L_0x0114
        L_0x074a:
            r1 = r12
            goto L_0x08a6
        L_0x074d:
            java.lang.String r1 = "A10-70F"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0757
            goto L_0x0114
        L_0x0757:
            r1 = 25
            goto L_0x08a6
        L_0x075b:
            java.lang.String r1 = "namath"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0765
            goto L_0x0114
        L_0x0765:
            r1 = 24
            goto L_0x08a6
        L_0x0769:
            java.lang.String r1 = "Slate_Pro"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0773
            goto L_0x0114
        L_0x0773:
            r1 = 23
            goto L_0x08a6
        L_0x0777:
            java.lang.String r1 = "iris60"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0781
            goto L_0x0114
        L_0x0781:
            r1 = 22
            goto L_0x08a6
        L_0x0785:
            java.lang.String r1 = "BRAVIA_ATV2"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x078f
            goto L_0x0114
        L_0x078f:
            r1 = 21
            goto L_0x08a6
        L_0x0793:
            java.lang.String r1 = "GiONEE_GBL7319"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x079d
            goto L_0x0114
        L_0x079d:
            r1 = 20
            goto L_0x08a6
        L_0x07a1:
            java.lang.String r1 = "panell_dt"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x07ab
            goto L_0x0114
        L_0x07ab:
            r1 = 19
            goto L_0x08a6
        L_0x07af:
            java.lang.String r1 = "panell_ds"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x07b9
            goto L_0x0114
        L_0x07b9:
            r1 = 18
            goto L_0x08a6
        L_0x07bd:
            java.lang.String r1 = "panell_dl"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x07c7
            goto L_0x0114
        L_0x07c7:
            r1 = 17
            goto L_0x08a6
        L_0x07cb:
            java.lang.String r1 = "vernee_M5"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x07d6
            goto L_0x0114
        L_0x07d6:
            r1 = 16
            goto L_0x08a6
        L_0x07da:
            java.lang.String r1 = "pacificrim"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x07e4
            goto L_0x0114
        L_0x07e4:
            r1 = 15
            goto L_0x08a6
        L_0x07e8:
            java.lang.String r1 = "Phantom6"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x07f2
            goto L_0x0114
        L_0x07f2:
            r1 = 14
            goto L_0x08a6
        L_0x07f6:
            java.lang.String r1 = "ComioS1"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0800
            goto L_0x0114
        L_0x0800:
            r1 = 13
            goto L_0x08a6
        L_0x0804:
            java.lang.String r1 = "XT1663"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x080e
            goto L_0x0114
        L_0x080e:
            r1 = 12
            goto L_0x08a6
        L_0x0812:
            java.lang.String r1 = "RAIJIN"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x081c
            goto L_0x0114
        L_0x081c:
            r1 = 11
            goto L_0x08a6
        L_0x0820:
            java.lang.String r1 = "AquaPowerM"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x082a
            goto L_0x0114
        L_0x082a:
            r1 = 10
            goto L_0x08a6
        L_0x082e:
            java.lang.String r1 = "PGN611"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0838
            goto L_0x0114
        L_0x0838:
            r1 = 9
            goto L_0x08a6
        L_0x083c:
            java.lang.String r1 = "PGN610"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0846
            goto L_0x0114
        L_0x0846:
            r1 = r13
            goto L_0x08a6
        L_0x0849:
            java.lang.String r2 = "PGN528"
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L_0x08a6
            goto L_0x0114
        L_0x0853:
            java.lang.String r1 = "NX573J"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x085d
            goto L_0x0114
        L_0x085d:
            r1 = r2
            goto L_0x08a6
        L_0x085f:
            java.lang.String r1 = "NX541J"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0869
            goto L_0x0114
        L_0x0869:
            r1 = r3
            goto L_0x08a6
        L_0x086b:
            java.lang.String r1 = "CP8676_I02"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0875
            goto L_0x0114
        L_0x0875:
            r1 = r4
            goto L_0x08a6
        L_0x0877:
            java.lang.String r1 = "K50a40"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0881
            goto L_0x0114
        L_0x0881:
            r1 = r5
            goto L_0x08a6
        L_0x0883:
            java.lang.String r1 = "GIONEE_SWW1631"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x088d
            goto L_0x0114
        L_0x088d:
            r1 = r6
            goto L_0x08a6
        L_0x088f:
            java.lang.String r1 = "GIONEE_SWW1627"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0899
            goto L_0x0114
        L_0x0899:
            r1 = r9
            goto L_0x08a6
        L_0x089b:
            java.lang.String r1 = "GIONEE_SWW1609"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x08a5
            goto L_0x0114
        L_0x08a5:
            r1 = r8
        L_0x08a6:
            switch(r1) {
                case 0: goto L_0x08b7;
                case 1: goto L_0x08b7;
                case 2: goto L_0x08b7;
                case 3: goto L_0x08b7;
                case 4: goto L_0x08b7;
                case 5: goto L_0x08b7;
                case 6: goto L_0x08b7;
                case 7: goto L_0x08b7;
                case 8: goto L_0x08b7;
                case 9: goto L_0x08b7;
                case 10: goto L_0x08b7;
                case 11: goto L_0x08b7;
                case 12: goto L_0x08b7;
                case 13: goto L_0x08b7;
                case 14: goto L_0x08b7;
                case 15: goto L_0x08b7;
                case 16: goto L_0x08b7;
                case 17: goto L_0x08b7;
                case 18: goto L_0x08b7;
                case 19: goto L_0x08b7;
                case 20: goto L_0x08b7;
                case 21: goto L_0x08b7;
                case 22: goto L_0x08b7;
                case 23: goto L_0x08b7;
                case 24: goto L_0x08b7;
                case 25: goto L_0x08b7;
                case 26: goto L_0x08b7;
                case 27: goto L_0x08b7;
                case 28: goto L_0x08b7;
                case 29: goto L_0x08b7;
                case 30: goto L_0x08b7;
                case 31: goto L_0x08b7;
                case 32: goto L_0x08b7;
                case 33: goto L_0x08b7;
                case 34: goto L_0x08b7;
                case 35: goto L_0x08b7;
                case 36: goto L_0x08b7;
                case 37: goto L_0x08b7;
                case 38: goto L_0x08b7;
                case 39: goto L_0x08b7;
                case 40: goto L_0x08b7;
                case 41: goto L_0x08b7;
                case 42: goto L_0x08b7;
                case 43: goto L_0x08b7;
                case 44: goto L_0x08b7;
                case 45: goto L_0x08b7;
                case 46: goto L_0x08b7;
                case 47: goto L_0x08b7;
                case 48: goto L_0x08b7;
                case 49: goto L_0x08b7;
                case 50: goto L_0x08b7;
                case 51: goto L_0x08b7;
                case 52: goto L_0x08b7;
                case 53: goto L_0x08b7;
                case 54: goto L_0x08b7;
                case 55: goto L_0x08b7;
                case 56: goto L_0x08b7;
                case 57: goto L_0x08b7;
                case 58: goto L_0x08b7;
                case 59: goto L_0x08b7;
                case 60: goto L_0x08b7;
                case 61: goto L_0x08b7;
                case 62: goto L_0x08b7;
                case 63: goto L_0x08b7;
                case 64: goto L_0x08b7;
                case 65: goto L_0x08b7;
                case 66: goto L_0x08b7;
                case 67: goto L_0x08b7;
                case 68: goto L_0x08b7;
                case 69: goto L_0x08b7;
                case 70: goto L_0x08b7;
                case 71: goto L_0x08b7;
                case 72: goto L_0x08b7;
                case 73: goto L_0x08b7;
                case 74: goto L_0x08b7;
                case 75: goto L_0x08b7;
                case 76: goto L_0x08b7;
                case 77: goto L_0x08b7;
                case 78: goto L_0x08b7;
                case 79: goto L_0x08b7;
                case 80: goto L_0x08b7;
                case 81: goto L_0x08b7;
                case 82: goto L_0x08b7;
                case 83: goto L_0x08b7;
                case 84: goto L_0x08b7;
                case 85: goto L_0x08b7;
                case 86: goto L_0x08b7;
                case 87: goto L_0x08b7;
                case 88: goto L_0x08b7;
                case 89: goto L_0x08b7;
                case 90: goto L_0x08b7;
                case 91: goto L_0x08b7;
                case 92: goto L_0x08b7;
                case 93: goto L_0x08b7;
                case 94: goto L_0x08b7;
                case 95: goto L_0x08b7;
                case 96: goto L_0x08b7;
                case 97: goto L_0x08b7;
                case 98: goto L_0x08b7;
                case 99: goto L_0x08b7;
                case 100: goto L_0x08b7;
                case 101: goto L_0x08b7;
                case 102: goto L_0x08b7;
                case 103: goto L_0x08b7;
                case 104: goto L_0x08b7;
                case 105: goto L_0x08b7;
                case 106: goto L_0x08b7;
                case 107: goto L_0x08b7;
                case 108: goto L_0x08b7;
                case 109: goto L_0x08b7;
                case 110: goto L_0x08b7;
                case 111: goto L_0x08b7;
                case 112: goto L_0x08b7;
                case 113: goto L_0x08b7;
                case 114: goto L_0x08b7;
                case 115: goto L_0x08b7;
                case 116: goto L_0x08b7;
                case 117: goto L_0x08b7;
                case 118: goto L_0x08b7;
                case 119: goto L_0x08b7;
                case 120: goto L_0x08b7;
                case 121: goto L_0x08b7;
                case 122: goto L_0x08b7;
                case 123: goto L_0x08b7;
                case 124: goto L_0x08b7;
                case 125: goto L_0x08b7;
                case 126: goto L_0x08b7;
                case 127: goto L_0x08b7;
                case 128: goto L_0x08b7;
                case 129: goto L_0x08b7;
                case 130: goto L_0x08b7;
                case 131: goto L_0x08b7;
                case 132: goto L_0x08b7;
                case 133: goto L_0x08b7;
                case 134: goto L_0x08b7;
                case 135: goto L_0x08b7;
                case 136: goto L_0x08b7;
                case 137: goto L_0x08b7;
                case 138: goto L_0x08b7;
                case 139: goto L_0x08b7;
                default: goto L_0x08a9;
            }
        L_0x08a9:
            java.lang.String r0 = androidx.media3.common.util.Util.MODEL
            r0.hashCode()
            java.lang.String r1 = "JSN-L21"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x08b7
            goto L_0x08b8
        L_0x08b7:
            return r9
        L_0x08b8:
            return r8
        L_0x08b9:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.video.MediaCodecVideoRenderer.evaluateDeviceNeedsSetOutputSurfaceWorkaround():boolean");
    }

    private final class OnFrameRenderedListenerV23 implements MediaCodecAdapter.OnFrameRenderedListener, Handler.Callback {
        private static final int HANDLE_FRAME_RENDERED = 0;
        private final Handler handler;

        public OnFrameRenderedListenerV23(MediaCodecAdapter mediaCodecAdapter) {
            Handler createHandlerForCurrentLooper = Util.createHandlerForCurrentLooper(this);
            this.handler = createHandlerForCurrentLooper;
            mediaCodecAdapter.setOnFrameRenderedListener(this, createHandlerForCurrentLooper);
        }

        public void onFrameRendered(MediaCodecAdapter mediaCodecAdapter, long j, long j2) {
            if (Util.SDK_INT < 30) {
                this.handler.sendMessageAtFrontOfQueue(Message.obtain(this.handler, 0, (int) (j >> 32), (int) j));
                return;
            }
            handleFrameRendered(j);
        }

        public boolean handleMessage(Message message) {
            if (message.what != 0) {
                return false;
            }
            handleFrameRendered(Util.toLong(message.arg1, message.arg2));
            return true;
        }

        private void handleFrameRendered(long j) {
            if (this == MediaCodecVideoRenderer.this.tunnelingOnFrameRenderedListener && MediaCodecVideoRenderer.this.getCodec() != null) {
                if (j == Long.MAX_VALUE) {
                    MediaCodecVideoRenderer.this.onProcessedTunneledEndOfStream();
                    return;
                }
                try {
                    MediaCodecVideoRenderer.this.onProcessedTunneledBuffer(j);
                } catch (ExoPlaybackException e) {
                    MediaCodecVideoRenderer.this.setPendingPlaybackException(e);
                }
            }
        }
    }
}
