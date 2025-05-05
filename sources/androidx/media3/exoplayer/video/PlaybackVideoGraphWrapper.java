package androidx.media3.exoplayer.video;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaFormat;
import android.os.Handler;
import android.os.Looper;
import android.util.Pair;
import android.view.Surface;
import androidx.media3.common.C;
import androidx.media3.common.ColorInfo;
import androidx.media3.common.DebugViewProvider;
import androidx.media3.common.Effect;
import androidx.media3.common.Format;
import androidx.media3.common.FrameInfo;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.PreviewingVideoGraph;
import androidx.media3.common.SurfaceInfo;
import androidx.media3.common.VideoFrameProcessingException;
import androidx.media3.common.VideoFrameProcessor;
import androidx.media3.common.VideoGraph;
import androidx.media3.common.VideoSize;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.HandlerWrapper;
import androidx.media3.common.util.Size;
import androidx.media3.common.util.TimestampIterator;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.ExoPlaybackException;
import androidx.media3.exoplayer.video.VideoFrameReleaseControl;
import androidx.media3.exoplayer.video.VideoFrameRenderControl;
import androidx.media3.exoplayer.video.VideoSink;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableList;
import io.sentry.protocol.OperatingSystem;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.Executor;
import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;

public final class PlaybackVideoGraphWrapper implements VideoSinkProvider, VideoGraph.Listener {
    /* access modifiers changed from: private */
    public static final Executor NO_OP_EXECUTOR = new PlaybackVideoGraphWrapper$$ExternalSyntheticLambda1();
    private static final int STATE_CREATED = 0;
    private static final int STATE_INITIALIZED = 1;
    private static final int STATE_RELEASED = 2;
    private long bufferTimestampAdjustmentUs;
    /* access modifiers changed from: private */
    public final Clock clock;
    /* access modifiers changed from: private */
    public final List<Effect> compositionEffects;
    private final Context context;
    /* access modifiers changed from: private */
    public Pair<Surface, Size> currentSurfaceAndSize;
    /* access modifiers changed from: private */
    public final VideoSink defaultVideoSink;
    private HandlerWrapper handler;
    private final InputVideoSink inputVideoSink;
    /* access modifiers changed from: private */
    public final CopyOnWriteArraySet<Listener> listeners;
    /* access modifiers changed from: private */
    public Format outputFormat;
    private int pendingFlushCount;
    private final PreviewingVideoGraph.Factory previewingVideoGraphFactory;
    private int state;
    /* access modifiers changed from: private */
    public VideoFrameMetadataListener videoFrameMetadataListener;
    /* access modifiers changed from: private */
    public final VideoFrameReleaseControl videoFrameReleaseControl;
    private final VideoFrameRenderControl videoFrameRenderControl;
    /* access modifiers changed from: private */
    public PreviewingVideoGraph videoGraph;

    public interface Listener {
        void onError(PlaybackVideoGraphWrapper playbackVideoGraphWrapper, VideoFrameProcessingException videoFrameProcessingException);

        void onFirstFrameRendered(PlaybackVideoGraphWrapper playbackVideoGraphWrapper);

        void onFrameDropped(PlaybackVideoGraphWrapper playbackVideoGraphWrapper);

        void onVideoSizeChanged(PlaybackVideoGraphWrapper playbackVideoGraphWrapper, VideoSize videoSize);
    }

    static /* synthetic */ void lambda$static$0(Runnable runnable) {
    }

    public static final class Builder {
        private boolean built;
        /* access modifiers changed from: private */
        public Clock clock = Clock.DEFAULT;
        /* access modifiers changed from: private */
        public List<Effect> compositionEffects = ImmutableList.of();
        /* access modifiers changed from: private */
        public final Context context;
        /* access modifiers changed from: private */
        public PreviewingVideoGraph.Factory previewingVideoGraphFactory;
        private VideoFrameProcessor.Factory videoFrameProcessorFactory;
        /* access modifiers changed from: private */
        public final VideoFrameReleaseControl videoFrameReleaseControl;

        public Builder(Context context2, VideoFrameReleaseControl videoFrameReleaseControl2) {
            this.context = context2.getApplicationContext();
            this.videoFrameReleaseControl = videoFrameReleaseControl2;
        }

        public Builder setVideoFrameProcessorFactory(VideoFrameProcessor.Factory factory) {
            this.videoFrameProcessorFactory = factory;
            return this;
        }

        public Builder setPreviewingVideoGraphFactory(PreviewingVideoGraph.Factory factory) {
            this.previewingVideoGraphFactory = factory;
            return this;
        }

        public Builder setCompositionEffects(List<Effect> list) {
            this.compositionEffects = list;
            return this;
        }

        public Builder setClock(Clock clock2) {
            this.clock = clock2;
            return this;
        }

        public PlaybackVideoGraphWrapper build() {
            Assertions.checkState(!this.built);
            if (this.previewingVideoGraphFactory == null) {
                if (this.videoFrameProcessorFactory == null) {
                    this.videoFrameProcessorFactory = new ReflectiveDefaultVideoFrameProcessorFactory();
                }
                this.previewingVideoGraphFactory = new ReflectivePreviewingSingleInputVideoGraphFactory(this.videoFrameProcessorFactory);
            }
            PlaybackVideoGraphWrapper playbackVideoGraphWrapper = new PlaybackVideoGraphWrapper(this);
            this.built = true;
            return playbackVideoGraphWrapper;
        }
    }

    private PlaybackVideoGraphWrapper(Builder builder) {
        Context access$200 = builder.context;
        this.context = access$200;
        InputVideoSink inputVideoSink2 = new InputVideoSink(access$200);
        this.inputVideoSink = inputVideoSink2;
        Clock access$300 = builder.clock;
        this.clock = access$300;
        VideoFrameReleaseControl access$400 = builder.videoFrameReleaseControl;
        this.videoFrameReleaseControl = access$400;
        access$400.setClock(access$300);
        VideoFrameRenderControl videoFrameRenderControl2 = new VideoFrameRenderControl(new FrameRendererImpl(), access$400);
        this.videoFrameRenderControl = videoFrameRenderControl2;
        this.previewingVideoGraphFactory = (PreviewingVideoGraph.Factory) Assertions.checkStateNotNull(builder.previewingVideoGraphFactory);
        this.compositionEffects = builder.compositionEffects;
        this.defaultVideoSink = new DefaultVideoSink(access$400, videoFrameRenderControl2);
        this.listeners = new CopyOnWriteArraySet<>();
        this.state = 0;
        addListener(inputVideoSink2);
    }

    public void addListener(Listener listener) {
        this.listeners.add(listener);
    }

    public void removeListener(Listener listener) {
        this.listeners.remove(listener);
    }

    public VideoSink getSink() {
        return this.inputVideoSink;
    }

    public void setOutputSurfaceInfo(Surface surface, Size size) {
        Pair<Surface, Size> pair = this.currentSurfaceAndSize;
        if (pair == null || !((Surface) pair.first).equals(surface) || !((Size) this.currentSurfaceAndSize.second).equals(size)) {
            this.currentSurfaceAndSize = Pair.create(surface, size);
            maybeSetOutputSurfaceInfo(surface, size.getWidth(), size.getHeight());
        }
    }

    public void clearOutputSurfaceInfo() {
        maybeSetOutputSurfaceInfo((Surface) null, Size.UNKNOWN.getWidth(), Size.UNKNOWN.getHeight());
        this.currentSurfaceAndSize = null;
    }

    public void release() {
        if (this.state != 2) {
            HandlerWrapper handlerWrapper = this.handler;
            if (handlerWrapper != null) {
                handlerWrapper.removeCallbacksAndMessages((Object) null);
            }
            PreviewingVideoGraph previewingVideoGraph = this.videoGraph;
            if (previewingVideoGraph != null) {
                previewingVideoGraph.release();
            }
            this.currentSurfaceAndSize = null;
            this.state = 2;
        }
    }

    public void onOutputSizeChanged(int i, int i2) {
        this.defaultVideoSink.onInputStreamChanged(1, new Format.Builder().setWidth(i).setHeight(i2).build());
    }

    public void onOutputFrameAvailableForRendering(long j) {
        if (this.pendingFlushCount <= 0) {
            this.videoFrameRenderControl.onOutputFrameAvailableForRendering(j - this.bufferTimestampAdjustmentUs);
        }
    }

    public void onEnded(long j) {
        throw new UnsupportedOperationException();
    }

    public void onError(VideoFrameProcessingException videoFrameProcessingException) {
        Iterator<Listener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onError(this, videoFrameProcessingException);
        }
    }

    /* access modifiers changed from: private */
    public VideoFrameProcessor initialize(Format format) throws VideoSink.VideoSinkException {
        Assertions.checkState(this.state == 0);
        ColorInfo adjustedInputColorInfo = getAdjustedInputColorInfo(format.colorInfo);
        if (adjustedInputColorInfo.colorTransfer == 7 && Util.SDK_INT < 34) {
            adjustedInputColorInfo = adjustedInputColorInfo.buildUpon().setColorTransfer(6).build();
        }
        ColorInfo colorInfo = adjustedInputColorInfo;
        this.handler = this.clock.createHandler((Looper) Assertions.checkStateNotNull(Looper.myLooper()), (Handler.Callback) null);
        try {
            PreviewingVideoGraph.Factory factory = this.previewingVideoGraphFactory;
            Context context2 = this.context;
            DebugViewProvider debugViewProvider = DebugViewProvider.NONE;
            HandlerWrapper handlerWrapper = this.handler;
            Objects.requireNonNull(handlerWrapper);
            this.videoGraph = factory.create(context2, colorInfo, debugViewProvider, this, new PlaybackVideoGraphWrapper$$ExternalSyntheticLambda2(handlerWrapper), ImmutableList.of(), 0);
            Pair<Surface, Size> pair = this.currentSurfaceAndSize;
            if (pair != null) {
                Size size = (Size) this.currentSurfaceAndSize.second;
                maybeSetOutputSurfaceInfo((Surface) pair.first, size.getWidth(), size.getHeight());
            }
            this.videoGraph.registerInput(0);
            this.defaultVideoSink.initialize(format);
            this.state = 1;
            return this.videoGraph.getProcessor(0);
        } catch (VideoFrameProcessingException e) {
            throw new VideoSink.VideoSinkException(e, format);
        }
    }

    private boolean isInitialized() {
        return this.state == 1;
    }

    private void maybeSetOutputSurfaceInfo(Surface surface, int i, int i2) {
        PreviewingVideoGraph previewingVideoGraph = this.videoGraph;
        if (previewingVideoGraph != null) {
            if (surface != null) {
                previewingVideoGraph.setOutputSurfaceInfo(new SurfaceInfo(surface, i, i2));
                this.defaultVideoSink.setOutputSurfaceInfo(surface, new Size(i, i2));
                return;
            }
            previewingVideoGraph.setOutputSurfaceInfo((SurfaceInfo) null);
            this.defaultVideoSink.clearOutputSurfaceInfo();
        }
    }

    /* access modifiers changed from: private */
    public boolean isReady(boolean z) {
        return this.defaultVideoSink.isReady(z && this.pendingFlushCount == 0);
    }

    /* access modifiers changed from: private */
    public boolean hasReleasedFrame(long j) {
        return this.pendingFlushCount == 0 && this.videoFrameRenderControl.hasReleasedFrame(j);
    }

    /* access modifiers changed from: private */
    public void render(long j, long j2) throws ExoPlaybackException {
        this.videoFrameRenderControl.render(j, j2);
    }

    /* access modifiers changed from: private */
    public void flush(boolean z) {
        if (isInitialized()) {
            this.pendingFlushCount++;
            this.defaultVideoSink.flush(z);
            ((HandlerWrapper) Assertions.checkStateNotNull(this.handler)).post(new PlaybackVideoGraphWrapper$$ExternalSyntheticLambda0(this));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$flush$1$androidx-media3-exoplayer-video-PlaybackVideoGraphWrapper  reason: not valid java name */
    public /* synthetic */ void m887lambda$flush$1$androidxmedia3exoplayervideoPlaybackVideoGraphWrapper() {
        this.pendingFlushCount--;
    }

    /* access modifiers changed from: private */
    public void setVideoFrameMetadataListener(VideoFrameMetadataListener videoFrameMetadataListener2) {
        this.videoFrameMetadataListener = videoFrameMetadataListener2;
    }

    /* access modifiers changed from: private */
    public void setPlaybackSpeed(float f) {
        this.defaultVideoSink.setPlaybackSpeed(f);
    }

    /* access modifiers changed from: private */
    public void onStreamOffsetChange(long j, long j2, long j3) {
        this.bufferTimestampAdjustmentUs = j;
        this.videoFrameRenderControl.onStreamOffsetChange(j2, j3);
    }

    /* access modifiers changed from: private */
    public static ColorInfo getAdjustedInputColorInfo(ColorInfo colorInfo) {
        if (colorInfo == null || !colorInfo.isDataSpaceValid()) {
            return ColorInfo.SDR_BT709_LIMITED;
        }
        return colorInfo;
    }

    private final class InputVideoSink implements VideoSink, Listener {
        private long finalBufferPresentationTimeUs = C.TIME_UNSET;
        private final VideoFrameReleaseControl.FrameReleaseInfo frameReleaseInfo = new VideoFrameReleaseControl.FrameReleaseInfo();
        private boolean hasRegisteredFirstInputStream;
        private long inputBufferTimestampAdjustmentUs;
        private Format inputFormat;
        private long inputStreamOffsetUs;
        private long inputStreamStartPositionUs;
        private int inputType;
        private boolean isInputStreamChangePending;
        private long lastBufferPresentationTimeUs = C.TIME_UNSET;
        private long lastResetPositionUs;
        private VideoSink.Listener listener = VideoSink.Listener.NO_OP;
        private Executor listenerExecutor = PlaybackVideoGraphWrapper.NO_OP_EXECUTOR;
        private long pendingInputStreamBufferPresentationTimeUs;
        private boolean pendingInputStreamOffsetChange;
        private final ArrayList<Effect> videoEffects = new ArrayList<>();
        private VideoFrameProcessor videoFrameProcessor;
        private final int videoFrameProcessorMaxPendingFrameCount;

        public InputVideoSink(Context context) {
            this.videoFrameProcessorMaxPendingFrameCount = Util.getMaxPendingFramesCountForMediaCodecDecoders(context);
        }

        public void onRendererEnabled(boolean z) {
            PlaybackVideoGraphWrapper.this.defaultVideoSink.onRendererEnabled(z);
        }

        public void onRendererDisabled() {
            PlaybackVideoGraphWrapper.this.defaultVideoSink.onRendererDisabled();
        }

        public void onRendererStarted() {
            PlaybackVideoGraphWrapper.this.defaultVideoSink.onRendererStarted();
        }

        public void onRendererStopped() {
            PlaybackVideoGraphWrapper.this.defaultVideoSink.onRendererStopped();
        }

        public void setListener(VideoSink.Listener listener2, Executor executor) {
            this.listener = listener2;
            this.listenerExecutor = executor;
        }

        public void initialize(Format format) throws VideoSink.VideoSinkException {
            Assertions.checkState(!isInitialized());
            this.videoFrameProcessor = PlaybackVideoGraphWrapper.this.initialize(format);
        }

        @EnsuresNonNullIf(expression = {"videoFrameProcessor"}, result = true)
        public boolean isInitialized() {
            return this.videoFrameProcessor != null;
        }

        public void flush(boolean z) {
            if (isInitialized()) {
                this.videoFrameProcessor.flush();
            }
            this.hasRegisteredFirstInputStream = false;
            this.finalBufferPresentationTimeUs = C.TIME_UNSET;
            this.lastBufferPresentationTimeUs = C.TIME_UNSET;
            PlaybackVideoGraphWrapper.this.flush(z);
            this.pendingInputStreamBufferPresentationTimeUs = C.TIME_UNSET;
        }

        public boolean isReady(boolean z) {
            return PlaybackVideoGraphWrapper.this.isReady(z && isInitialized());
        }

        public boolean isEnded() {
            if (isInitialized()) {
                long j = this.finalBufferPresentationTimeUs;
                return j != C.TIME_UNSET && PlaybackVideoGraphWrapper.this.hasReleasedFrame(j);
            }
        }

        public void onInputStreamChanged(int i, Format format) {
            Assertions.checkState(isInitialized());
            if (i == 1 || i == 2) {
                PlaybackVideoGraphWrapper.this.videoFrameReleaseControl.setFrameRate(format.frameRate);
                this.inputType = i;
                this.inputFormat = format;
                boolean z = false;
                if (!this.hasRegisteredFirstInputStream) {
                    maybeRegisterInputStream();
                    this.hasRegisteredFirstInputStream = true;
                    this.isInputStreamChangePending = false;
                    this.pendingInputStreamBufferPresentationTimeUs = C.TIME_UNSET;
                    return;
                }
                if (this.lastBufferPresentationTimeUs != C.TIME_UNSET) {
                    z = true;
                }
                Assertions.checkState(z);
                this.isInputStreamChangePending = true;
                this.pendingInputStreamBufferPresentationTimeUs = this.lastBufferPresentationTimeUs;
                return;
            }
            throw new UnsupportedOperationException("Unsupported input type " + i);
        }

        public Surface getInputSurface() {
            Assertions.checkState(isInitialized());
            return ((VideoFrameProcessor) Assertions.checkStateNotNull(this.videoFrameProcessor)).getInputSurface();
        }

        public void setVideoFrameMetadataListener(VideoFrameMetadataListener videoFrameMetadataListener) {
            PlaybackVideoGraphWrapper.this.setVideoFrameMetadataListener(videoFrameMetadataListener);
        }

        public void setPlaybackSpeed(float f) {
            PlaybackVideoGraphWrapper.this.setPlaybackSpeed(f);
        }

        public void setVideoEffects(List<Effect> list) {
            if (!this.videoEffects.equals(list)) {
                setPendingVideoEffects(list);
                maybeRegisterInputStream();
            }
        }

        public void setPendingVideoEffects(List<Effect> list) {
            this.videoEffects.clear();
            this.videoEffects.addAll(list);
            this.videoEffects.addAll(PlaybackVideoGraphWrapper.this.compositionEffects);
        }

        public void setStreamTimestampInfo(long j, long j2, long j3, long j4) {
            this.pendingInputStreamOffsetChange |= (this.inputStreamOffsetUs == j2 && this.inputBufferTimestampAdjustmentUs == j3) ? false : true;
            this.inputStreamStartPositionUs = j;
            this.inputStreamOffsetUs = j2;
            this.inputBufferTimestampAdjustmentUs = j3;
            this.lastResetPositionUs = j4;
        }

        public void setOutputSurfaceInfo(Surface surface, Size size) {
            PlaybackVideoGraphWrapper.this.setOutputSurfaceInfo(surface, size);
        }

        public void clearOutputSurfaceInfo() {
            PlaybackVideoGraphWrapper.this.clearOutputSurfaceInfo();
        }

        public void setChangeFrameRateStrategy(int i) {
            PlaybackVideoGraphWrapper.this.defaultVideoSink.setChangeFrameRateStrategy(i);
        }

        public void enableMayRenderStartOfStream() {
            PlaybackVideoGraphWrapper.this.defaultVideoSink.enableMayRenderStartOfStream();
        }

        public boolean handleInputFrame(long j, boolean z, long j2, long j3, VideoSink.VideoFrameHandler videoFrameHandler) throws VideoSink.VideoSinkException {
            Assertions.checkState(isInitialized());
            long j4 = j - this.inputBufferTimestampAdjustmentUs;
            try {
                if (PlaybackVideoGraphWrapper.this.videoFrameReleaseControl.getFrameReleaseAction(j4, j2, j3, this.inputStreamStartPositionUs, z, this.frameReleaseInfo) == 4) {
                    return false;
                }
                if (j4 >= this.lastResetPositionUs || z) {
                    render(j2, j3);
                    if (this.isInputStreamChangePending) {
                        long j5 = this.pendingInputStreamBufferPresentationTimeUs;
                        if (j5 != C.TIME_UNSET && !PlaybackVideoGraphWrapper.this.hasReleasedFrame(j5)) {
                            return false;
                        }
                        maybeRegisterInputStream();
                        this.isInputStreamChangePending = false;
                        this.pendingInputStreamBufferPresentationTimeUs = C.TIME_UNSET;
                    }
                    if (((VideoFrameProcessor) Assertions.checkStateNotNull(this.videoFrameProcessor)).getPendingInputFrameCount() >= this.videoFrameProcessorMaxPendingFrameCount || !((VideoFrameProcessor) Assertions.checkStateNotNull(this.videoFrameProcessor)).registerInputFrame()) {
                        return false;
                    }
                    maybeSetStreamOffsetChange(j4);
                    this.lastBufferPresentationTimeUs = j4;
                    if (z) {
                        this.finalBufferPresentationTimeUs = j4;
                    }
                    videoFrameHandler.render(1000 * j);
                    return true;
                }
                videoFrameHandler.skip();
                return true;
            } catch (ExoPlaybackException e) {
                throw new VideoSink.VideoSinkException(e, (Format) Assertions.checkStateNotNull(this.inputFormat));
            }
        }

        public boolean handleInputBitmap(Bitmap bitmap, TimestampIterator timestampIterator) {
            Assertions.checkState(isInitialized());
            boolean z = false;
            if (!maybeRegisterPendingInputStream() || !((VideoFrameProcessor) Assertions.checkStateNotNull(this.videoFrameProcessor)).queueInputBitmap(bitmap, timestampIterator)) {
                return false;
            }
            TimestampIterator copyOf = timestampIterator.copyOf();
            long next = copyOf.next();
            long lastTimestampUs = copyOf.getLastTimestampUs() - this.inputBufferTimestampAdjustmentUs;
            if (lastTimestampUs != C.TIME_UNSET) {
                z = true;
            }
            Assertions.checkState(z);
            maybeSetStreamOffsetChange(next);
            this.lastBufferPresentationTimeUs = lastTimestampUs;
            this.finalBufferPresentationTimeUs = lastTimestampUs;
            return true;
        }

        public void render(long j, long j2) throws VideoSink.VideoSinkException {
            try {
                PlaybackVideoGraphWrapper.this.render(j, j2);
            } catch (ExoPlaybackException e) {
                Format format = this.inputFormat;
                if (format == null) {
                    format = new Format.Builder().build();
                }
                throw new VideoSink.VideoSinkException(e, format);
            }
        }

        public void join(boolean z) {
            PlaybackVideoGraphWrapper.this.defaultVideoSink.join(z);
        }

        public void release() {
            PlaybackVideoGraphWrapper.this.release();
        }

        private void maybeSetStreamOffsetChange(long j) {
            if (this.pendingInputStreamOffsetChange) {
                PlaybackVideoGraphWrapper.this.onStreamOffsetChange(this.inputBufferTimestampAdjustmentUs, j, this.inputStreamOffsetUs);
                this.pendingInputStreamOffsetChange = false;
            }
        }

        private boolean maybeRegisterPendingInputStream() {
            if (!this.isInputStreamChangePending) {
                return true;
            }
            long j = this.pendingInputStreamBufferPresentationTimeUs;
            if (j != C.TIME_UNSET && !PlaybackVideoGraphWrapper.this.hasReleasedFrame(j)) {
                return false;
            }
            maybeRegisterInputStream();
            this.isInputStreamChangePending = false;
            this.pendingInputStreamBufferPresentationTimeUs = C.TIME_UNSET;
            return true;
        }

        private void maybeRegisterInputStream() {
            if (this.inputFormat != null) {
                ArrayList arrayList = new ArrayList(this.videoEffects);
                Format format = (Format) Assertions.checkNotNull(this.inputFormat);
                ((VideoFrameProcessor) Assertions.checkStateNotNull(this.videoFrameProcessor)).registerInputStream(this.inputType, arrayList, new FrameInfo.Builder(PlaybackVideoGraphWrapper.getAdjustedInputColorInfo(format.colorInfo), format.width, format.height).setPixelWidthHeightRatio(format.pixelWidthHeightRatio).build());
                this.finalBufferPresentationTimeUs = C.TIME_UNSET;
            }
        }

        public void onFirstFrameRendered(PlaybackVideoGraphWrapper playbackVideoGraphWrapper) {
            this.listenerExecutor.execute(new PlaybackVideoGraphWrapper$InputVideoSink$$ExternalSyntheticLambda3(this, this.listener));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onFirstFrameRendered$0$androidx-media3-exoplayer-video-PlaybackVideoGraphWrapper$InputVideoSink  reason: not valid java name */
        public /* synthetic */ void m889lambda$onFirstFrameRendered$0$androidxmedia3exoplayervideoPlaybackVideoGraphWrapper$InputVideoSink(VideoSink.Listener listener2) {
            listener2.onFirstFrameRendered(this);
        }

        public void onFrameDropped(PlaybackVideoGraphWrapper playbackVideoGraphWrapper) {
            this.listenerExecutor.execute(new PlaybackVideoGraphWrapper$InputVideoSink$$ExternalSyntheticLambda1(this, this.listener));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onFrameDropped$1$androidx-media3-exoplayer-video-PlaybackVideoGraphWrapper$InputVideoSink  reason: not valid java name */
        public /* synthetic */ void m890lambda$onFrameDropped$1$androidxmedia3exoplayervideoPlaybackVideoGraphWrapper$InputVideoSink(VideoSink.Listener listener2) {
            listener2.onFrameDropped((VideoSink) Assertions.checkStateNotNull(this));
        }

        public void onVideoSizeChanged(PlaybackVideoGraphWrapper playbackVideoGraphWrapper, VideoSize videoSize) {
            this.listenerExecutor.execute(new PlaybackVideoGraphWrapper$InputVideoSink$$ExternalSyntheticLambda0(this, this.listener, videoSize));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onVideoSizeChanged$2$androidx-media3-exoplayer-video-PlaybackVideoGraphWrapper$InputVideoSink  reason: not valid java name */
        public /* synthetic */ void m891lambda$onVideoSizeChanged$2$androidxmedia3exoplayervideoPlaybackVideoGraphWrapper$InputVideoSink(VideoSink.Listener listener2, VideoSize videoSize) {
            listener2.onVideoSizeChanged(this, videoSize);
        }

        public void onError(PlaybackVideoGraphWrapper playbackVideoGraphWrapper, VideoFrameProcessingException videoFrameProcessingException) {
            this.listenerExecutor.execute(new PlaybackVideoGraphWrapper$InputVideoSink$$ExternalSyntheticLambda2(this, this.listener, videoFrameProcessingException));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onError$3$androidx-media3-exoplayer-video-PlaybackVideoGraphWrapper$InputVideoSink  reason: not valid java name */
        public /* synthetic */ void m888lambda$onError$3$androidxmedia3exoplayervideoPlaybackVideoGraphWrapper$InputVideoSink(VideoSink.Listener listener2, VideoFrameProcessingException videoFrameProcessingException) {
            listener2.onError(this, new VideoSink.VideoSinkException(videoFrameProcessingException, (Format) Assertions.checkStateNotNull(this.inputFormat)));
        }
    }

    private final class FrameRendererImpl implements VideoFrameRenderControl.FrameRenderer {
        private FrameRendererImpl() {
        }

        public void onVideoSizeChanged(VideoSize videoSize) {
            Format unused = PlaybackVideoGraphWrapper.this.outputFormat = new Format.Builder().setWidth(videoSize.width).setHeight(videoSize.height).setSampleMimeType(MimeTypes.VIDEO_RAW).build();
            Iterator it = PlaybackVideoGraphWrapper.this.listeners.iterator();
            while (it.hasNext()) {
                ((Listener) it.next()).onVideoSizeChanged(PlaybackVideoGraphWrapper.this, videoSize);
            }
        }

        public void renderFrame(long j, long j2, long j3, boolean z) {
            if (z && PlaybackVideoGraphWrapper.this.currentSurfaceAndSize != null) {
                Iterator it = PlaybackVideoGraphWrapper.this.listeners.iterator();
                while (it.hasNext()) {
                    ((Listener) it.next()).onFirstFrameRendered(PlaybackVideoGraphWrapper.this);
                }
            }
            if (PlaybackVideoGraphWrapper.this.videoFrameMetadataListener != null) {
                PlaybackVideoGraphWrapper.this.videoFrameMetadataListener.onVideoFrameAboutToBeRendered(j2, PlaybackVideoGraphWrapper.this.clock.nanoTime(), PlaybackVideoGraphWrapper.this.outputFormat == null ? new Format.Builder().build() : PlaybackVideoGraphWrapper.this.outputFormat, (MediaFormat) null);
            }
            ((PreviewingVideoGraph) Assertions.checkStateNotNull(PlaybackVideoGraphWrapper.this.videoGraph)).renderOutputFrame(j);
        }

        public void dropFrame() {
            Iterator it = PlaybackVideoGraphWrapper.this.listeners.iterator();
            while (it.hasNext()) {
                ((Listener) it.next()).onFrameDropped(PlaybackVideoGraphWrapper.this);
            }
            ((PreviewingVideoGraph) Assertions.checkStateNotNull(PlaybackVideoGraphWrapper.this.videoGraph)).renderOutputFrame(-2);
        }
    }

    private static final class ReflectivePreviewingSingleInputVideoGraphFactory implements PreviewingVideoGraph.Factory {
        private final VideoFrameProcessor.Factory videoFrameProcessorFactory;

        public ReflectivePreviewingSingleInputVideoGraphFactory(VideoFrameProcessor.Factory factory) {
            this.videoFrameProcessorFactory = factory;
        }

        public PreviewingVideoGraph create(Context context, ColorInfo colorInfo, DebugViewProvider debugViewProvider, VideoGraph.Listener listener, Executor executor, List<Effect> list, long j) throws VideoFrameProcessingException {
            try {
                Constructor<?> constructor = Class.forName("androidx.media3.effect.PreviewingSingleInputVideoGraph$Factory").getConstructor(new Class[]{VideoFrameProcessor.Factory.class});
                Object[] objArr = new Object[1];
                try {
                    objArr[0] = this.videoFrameProcessorFactory;
                    return ((PreviewingVideoGraph.Factory) constructor.newInstance(objArr)).create(context, colorInfo, debugViewProvider, listener, executor, list, j);
                } catch (Exception e) {
                    e = e;
                    throw VideoFrameProcessingException.from(e);
                }
            } catch (Exception e2) {
                e = e2;
                throw VideoFrameProcessingException.from(e);
            }
        }
    }

    private static final class ReflectiveDefaultVideoFrameProcessorFactory implements VideoFrameProcessor.Factory {
        private static final Supplier<VideoFrameProcessor.Factory> VIDEO_FRAME_PROCESSOR_FACTORY_SUPPLIER = Suppliers.memoize(new PlaybackVideoGraphWrapper$ReflectiveDefaultVideoFrameProcessorFactory$$ExternalSyntheticLambda0());

        private ReflectiveDefaultVideoFrameProcessorFactory() {
        }

        static /* synthetic */ VideoFrameProcessor.Factory lambda$static$0() {
            try {
                Class<?> cls = Class.forName("androidx.media3.effect.DefaultVideoFrameProcessor$Factory$Builder");
                return (VideoFrameProcessor.Factory) Assertions.checkNotNull(cls.getMethod(OperatingSystem.JsonKeys.BUILD, new Class[0]).invoke(cls.getConstructor(new Class[0]).newInstance(new Object[0]), new Object[0]));
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }

        public VideoFrameProcessor create(Context context, DebugViewProvider debugViewProvider, ColorInfo colorInfo, boolean z, Executor executor, VideoFrameProcessor.Listener listener) throws VideoFrameProcessingException {
            return VIDEO_FRAME_PROCESSOR_FACTORY_SUPPLIER.get().create(context, debugViewProvider, colorInfo, z, executor, listener);
        }
    }
}
