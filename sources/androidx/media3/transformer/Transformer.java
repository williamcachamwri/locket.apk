package androidx.media3.transformer;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import androidx.media3.common.C;
import androidx.media3.common.DebugViewProvider;
import androidx.media3.common.Effect;
import androidx.media3.common.FlagSet;
import androidx.media3.common.Format;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MediaLibraryInfo;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.VideoFrameProcessor;
import androidx.media3.common.audio.AudioProcessor;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.HandlerWrapper;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.common.util.Util;
import androidx.media3.effect.DebugTraceUtil;
import androidx.media3.effect.DefaultVideoFrameProcessor;
import androidx.media3.exoplayer.video.PlaybackVideoGraphWrapper$$ExternalSyntheticLambda2;
import androidx.media3.muxer.Muxer;
import androidx.media3.transformer.AssetLoader;
import androidx.media3.transformer.AudioMixer;
import androidx.media3.transformer.Codec;
import androidx.media3.transformer.Composition;
import androidx.media3.transformer.DefaultAudioMixer;
import androidx.media3.transformer.DefaultDecoderFactory;
import androidx.media3.transformer.DefaultEncoderFactory;
import androidx.media3.transformer.DefaultMuxer;
import androidx.media3.transformer.EditedMediaItem;
import androidx.media3.transformer.EditedMediaItemSequence;
import androidx.media3.transformer.ExportResult;
import androidx.media3.transformer.MuxerWrapper;
import androidx.media3.transformer.TransformationRequest;
import androidx.media3.transformer.TransformationResult;
import androidx.media3.transformer.TransformerInternal;
import androidx.media3.transformer.TransmuxTranscodeHelper;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;
import java.util.Objects;

public final class Transformer {
    public static final long DEFAULT_MAX_DELAY_BETWEEN_MUXER_SAMPLES_MS = (Util.isRunningOnEmulator() ? 21000 : 10000);
    public static final int PROGRESS_STATE_AVAILABLE = 2;
    public static final int PROGRESS_STATE_NOT_STARTED = 0;
    @Deprecated
    public static final int PROGRESS_STATE_NO_TRANSFORMATION = 0;
    public static final int PROGRESS_STATE_UNAVAILABLE = 3;
    public static final int PROGRESS_STATE_WAITING_FOR_AVAILABILITY = 1;
    private static final int TRANSFORMER_STATE_COPY_OUTPUT = 4;
    private static final int TRANSFORMER_STATE_PROCESS_AUDIO = 3;
    private static final int TRANSFORMER_STATE_PROCESS_FULL_INPUT = 0;
    private static final int TRANSFORMER_STATE_PROCESS_MEDIA_START = 5;
    private static final int TRANSFORMER_STATE_PROCESS_REMAINING_VIDEO = 2;
    private static final int TRANSFORMER_STATE_REMUX_PROCESSED_VIDEO = 1;
    private static final int TRANSFORMER_STATE_REMUX_REMAINING_MEDIA = 6;
    private final HandlerWrapper applicationHandler;
    /* access modifiers changed from: private */
    public final AssetLoader.Factory assetLoaderFactory;
    /* access modifiers changed from: private */
    public final AudioMixer.Factory audioMixerFactory;
    /* access modifiers changed from: private */
    public final ImmutableList<AudioProcessor> audioProcessors;
    /* access modifiers changed from: private */
    public final Clock clock;
    /* access modifiers changed from: private */
    public final ComponentListener componentListener;
    /* access modifiers changed from: private */
    public Composition composition;
    /* access modifiers changed from: private */
    public final Context context;
    private ListenableFuture<Void> copyOutputFuture;
    /* access modifiers changed from: private */
    public final DebugViewProvider debugViewProvider;
    /* access modifiers changed from: private */
    public final Codec.EncoderFactory encoderFactory;
    /* access modifiers changed from: private */
    public final ExportResult.Builder exportResultBuilder;
    /* access modifiers changed from: private */
    public final boolean fileStartsOnVideoFrameEnabled;
    private final boolean flattenForSlowMotion;
    private ListenableFuture<TransmuxTranscodeHelper.ResumeMetadata> getResumeMetadataFuture;
    /* access modifiers changed from: private */
    public final ListenerSet<Listener> listeners;
    /* access modifiers changed from: private */
    public final Looper looper;
    /* access modifiers changed from: private */
    public final long maxDelayBetweenMuxerSamplesMs;
    /* access modifiers changed from: private */
    public final int maxFramesInEncoder;
    /* access modifiers changed from: private */
    public Mp4Info mediaItemInfo;
    /* access modifiers changed from: private */
    public final Muxer.Factory muxerFactory;
    /* access modifiers changed from: private */
    public String oldFilePath;
    /* access modifiers changed from: private */
    public String outputFilePath;
    /* access modifiers changed from: private */
    public final boolean portraitEncodingEnabled;
    /* access modifiers changed from: private */
    public final boolean removeAudio;
    /* access modifiers changed from: private */
    public final boolean removeVideo;
    /* access modifiers changed from: private */
    public MuxerWrapper remuxingMuxerWrapper;
    /* access modifiers changed from: private */
    public TransmuxTranscodeHelper.ResumeMetadata resumeMetadata;
    /* access modifiers changed from: private */
    public final TransformationRequest transformationRequest;
    /* access modifiers changed from: private */
    public TransformerInternal transformerInternal;
    /* access modifiers changed from: private */
    public int transformerState;
    /* access modifiers changed from: private */
    public final boolean trimOptimizationEnabled;
    /* access modifiers changed from: private */
    public final ImmutableList<Effect> videoEffects;
    /* access modifiers changed from: private */
    public final VideoFrameProcessor.Factory videoFrameProcessorFactory;

    @Documented
    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ProgressState {
    }

    static {
        MediaLibraryInfo.registerModule("media3.transformer");
    }

    public static final class Builder {
        private AssetLoader.Factory assetLoaderFactory;
        private String audioMimeType;
        private AudioMixer.Factory audioMixerFactory;
        private ImmutableList<AudioProcessor> audioProcessors;
        private Clock clock;
        private final Context context;
        private DebugViewProvider debugViewProvider;
        private Codec.EncoderFactory encoderFactory;
        private boolean fileStartsOnVideoFrameEnabled;
        private boolean flattenForSlowMotion;
        private ListenerSet<Listener> listeners;
        private Looper looper;
        private long maxDelayBetweenMuxerSamplesMs;
        private int maxFramesInEncoder;
        private Muxer.Factory muxerFactory;
        private boolean portraitEncodingEnabled;
        private boolean removeAudio;
        private boolean removeVideo;
        private TransformationRequest transformationRequest;
        private boolean trimOptimizationEnabled;
        private ImmutableList<Effect> videoEffects;
        private VideoFrameProcessor.Factory videoFrameProcessorFactory;
        private String videoMimeType;

        static /* synthetic */ void lambda$new$0(Listener listener, FlagSet flagSet) {
        }

        static /* synthetic */ void lambda$setClock$2(Listener listener, FlagSet flagSet) {
        }

        static /* synthetic */ void lambda$setLooper$1(Listener listener, FlagSet flagSet) {
        }

        public Builder(Context context2) {
            Context applicationContext = context2.getApplicationContext();
            this.context = applicationContext;
            this.maxDelayBetweenMuxerSamplesMs = Transformer.DEFAULT_MAX_DELAY_BETWEEN_MUXER_SAMPLES_MS;
            this.maxFramesInEncoder = -1;
            this.audioProcessors = ImmutableList.of();
            this.videoEffects = ImmutableList.of();
            this.audioMixerFactory = new DefaultAudioMixer.Factory();
            this.videoFrameProcessorFactory = new DefaultVideoFrameProcessor.Factory.Builder().build();
            this.encoderFactory = new DefaultEncoderFactory.Builder(applicationContext).build();
            this.muxerFactory = new DefaultMuxer.Factory();
            this.looper = Util.getCurrentOrMainLooper();
            this.debugViewProvider = DebugViewProvider.NONE;
            this.clock = Clock.DEFAULT;
            this.listeners = new ListenerSet<>(this.looper, this.clock, new Transformer$Builder$$ExternalSyntheticLambda1());
        }

        private Builder(Transformer transformer) {
            this.context = transformer.context;
            this.audioMimeType = transformer.transformationRequest.audioMimeType;
            this.videoMimeType = transformer.transformationRequest.videoMimeType;
            this.transformationRequest = transformer.transformationRequest;
            this.audioProcessors = transformer.audioProcessors;
            this.videoEffects = transformer.videoEffects;
            this.removeAudio = transformer.removeAudio;
            this.removeVideo = transformer.removeVideo;
            this.trimOptimizationEnabled = transformer.trimOptimizationEnabled;
            this.portraitEncodingEnabled = transformer.portraitEncodingEnabled;
            this.fileStartsOnVideoFrameEnabled = transformer.fileStartsOnVideoFrameEnabled;
            this.maxDelayBetweenMuxerSamplesMs = transformer.maxDelayBetweenMuxerSamplesMs;
            this.maxFramesInEncoder = transformer.maxFramesInEncoder;
            this.listeners = transformer.listeners;
            this.assetLoaderFactory = transformer.assetLoaderFactory;
            this.audioMixerFactory = transformer.audioMixerFactory;
            this.videoFrameProcessorFactory = transformer.videoFrameProcessorFactory;
            this.encoderFactory = transformer.encoderFactory;
            this.muxerFactory = transformer.muxerFactory;
            this.looper = transformer.looper;
            this.debugViewProvider = transformer.debugViewProvider;
            this.clock = transformer.clock;
        }

        public Builder setAudioMimeType(String str) {
            String normalizeMimeType = MimeTypes.normalizeMimeType(str);
            Assertions.checkArgument(MimeTypes.isAudio(normalizeMimeType), "Not an audio MIME type: " + normalizeMimeType);
            this.audioMimeType = normalizeMimeType;
            return this;
        }

        public Builder setVideoMimeType(String str) {
            String normalizeMimeType = MimeTypes.normalizeMimeType(str);
            Assertions.checkArgument(MimeTypes.isVideo(normalizeMimeType), "Not a video MIME type: " + normalizeMimeType);
            this.videoMimeType = normalizeMimeType;
            return this;
        }

        @Deprecated
        public Builder setTransformationRequest(TransformationRequest transformationRequest2) {
            this.transformationRequest = transformationRequest2;
            return this;
        }

        @Deprecated
        public Builder setAudioProcessors(List<AudioProcessor> list) {
            this.audioProcessors = ImmutableList.copyOf(list);
            return this;
        }

        @Deprecated
        public Builder setVideoEffects(List<Effect> list) {
            this.videoEffects = ImmutableList.copyOf(list);
            return this;
        }

        @Deprecated
        public Builder setRemoveAudio(boolean z) {
            this.removeAudio = z;
            return this;
        }

        @Deprecated
        public Builder setRemoveVideo(boolean z) {
            this.removeVideo = z;
            return this;
        }

        @Deprecated
        public Builder setFlattenForSlowMotion(boolean z) {
            this.flattenForSlowMotion = z;
            return this;
        }

        public Builder experimentalSetTrimOptimizationEnabled(boolean z) {
            this.trimOptimizationEnabled = z;
            return this;
        }

        public Builder setPortraitEncodingEnabled(boolean z) {
            this.portraitEncodingEnabled = z;
            return this;
        }

        public Builder experimentalSetMaxFramesInEncoder(int i) {
            Assertions.checkArgument(i > 0 || i == -1);
            this.maxFramesInEncoder = i;
            return this;
        }

        public Builder setEnsureFileStartsOnVideoFrameEnabled(boolean z) {
            this.fileStartsOnVideoFrameEnabled = z;
            return this;
        }

        public Builder setMaxDelayBetweenMuxerSamplesMs(long j) {
            this.maxDelayBetweenMuxerSamplesMs = j;
            return this;
        }

        @Deprecated
        public Builder setListener(Listener listener) {
            this.listeners.clear();
            this.listeners.add(listener);
            return this;
        }

        public Builder addListener(Listener listener) {
            this.listeners.add(listener);
            return this;
        }

        public Builder removeListener(Listener listener) {
            this.listeners.remove(listener);
            return this;
        }

        public Builder removeAllListeners() {
            this.listeners.clear();
            return this;
        }

        public Builder setAssetLoaderFactory(AssetLoader.Factory factory) {
            this.assetLoaderFactory = factory;
            return this;
        }

        public Builder setAudioMixerFactory(AudioMixer.Factory factory) {
            this.audioMixerFactory = factory;
            return this;
        }

        public Builder setVideoFrameProcessorFactory(VideoFrameProcessor.Factory factory) {
            this.videoFrameProcessorFactory = factory;
            return this;
        }

        public Builder setEncoderFactory(Codec.EncoderFactory encoderFactory2) {
            this.encoderFactory = encoderFactory2;
            return this;
        }

        public Builder setMuxerFactory(Muxer.Factory factory) {
            this.muxerFactory = factory;
            return this;
        }

        public Builder setLooper(Looper looper2) {
            this.looper = looper2;
            this.listeners = this.listeners.copy(looper2, new Transformer$Builder$$ExternalSyntheticLambda2());
            return this;
        }

        public Builder setDebugViewProvider(DebugViewProvider debugViewProvider2) {
            this.debugViewProvider = debugViewProvider2;
            return this;
        }

        /* access modifiers changed from: package-private */
        public Builder setClock(Clock clock2) {
            this.clock = clock2;
            this.listeners = this.listeners.copy(this.looper, clock2, new Transformer$Builder$$ExternalSyntheticLambda0());
            return this;
        }

        public Transformer build() {
            TransformationRequest.Builder builder;
            TransformationRequest transformationRequest2 = this.transformationRequest;
            if (transformationRequest2 == null) {
                builder = new TransformationRequest.Builder();
            } else {
                builder = transformationRequest2.buildUpon();
            }
            String str = this.audioMimeType;
            if (str != null) {
                builder.setAudioMimeType(str);
            }
            String str2 = this.videoMimeType;
            if (str2 != null) {
                builder.setVideoMimeType(str2);
            }
            TransformationRequest build = builder.build();
            this.transformationRequest = build;
            if (build.audioMimeType != null) {
                checkSampleMimeType(this.transformationRequest.audioMimeType);
            }
            if (this.transformationRequest.videoMimeType != null) {
                checkSampleMimeType(this.transformationRequest.videoMimeType);
            }
            Transformer transformer = r2;
            Transformer transformer2 = new Transformer(this.context, this.transformationRequest, this.audioProcessors, this.videoEffects, this.removeAudio, this.removeVideo, this.flattenForSlowMotion, this.trimOptimizationEnabled, this.portraitEncodingEnabled, this.fileStartsOnVideoFrameEnabled, this.maxDelayBetweenMuxerSamplesMs, this.maxFramesInEncoder, this.listeners, this.assetLoaderFactory, this.audioMixerFactory, this.videoFrameProcessorFactory, this.encoderFactory, this.muxerFactory, this.looper, this.debugViewProvider, this.clock);
            return transformer;
        }

        private void checkSampleMimeType(String str) {
            Assertions.checkState(this.muxerFactory.getSupportedSampleMimeTypes(MimeTypes.getTrackType(str)).contains(str), "Unsupported sample MIME type " + str);
        }
    }

    public interface Listener {
        @Deprecated
        void onFallbackApplied(MediaItem mediaItem, TransformationRequest transformationRequest, TransformationRequest transformationRequest2) {
        }

        @Deprecated
        void onTransformationCompleted(MediaItem mediaItem) {
        }

        @Deprecated
        void onTransformationError(MediaItem mediaItem, Exception exc) {
        }

        @Deprecated
        void onTransformationCompleted(MediaItem mediaItem, TransformationResult transformationResult) {
            onTransformationCompleted(mediaItem);
        }

        void onCompleted(Composition composition, ExportResult exportResult) {
            onTransformationCompleted(((EditedMediaItem) ((EditedMediaItemSequence) composition.sequences.get(0)).editedMediaItems.get(0)).mediaItem, new TransformationResult.Builder(exportResult).build());
        }

        @Deprecated
        void onTransformationError(MediaItem mediaItem, TransformationException transformationException) {
            onTransformationError(mediaItem, (Exception) transformationException);
        }

        @Deprecated
        void onTransformationError(MediaItem mediaItem, TransformationResult transformationResult, TransformationException transformationException) {
            onTransformationError(mediaItem, transformationException);
        }

        void onError(Composition composition, ExportResult exportResult, ExportException exportException) {
            onTransformationError(((EditedMediaItem) ((EditedMediaItemSequence) composition.sequences.get(0)).editedMediaItems.get(0)).mediaItem, new TransformationResult.Builder(exportResult).build(), new TransformationException(exportException));
        }

        void onFallbackApplied(Composition composition, TransformationRequest transformationRequest, TransformationRequest transformationRequest2) {
            onFallbackApplied(((EditedMediaItem) ((EditedMediaItemSequence) composition.sequences.get(0)).editedMediaItems.get(0)).mediaItem, transformationRequest, transformationRequest2);
        }
    }

    private Transformer(Context context2, TransformationRequest transformationRequest2, ImmutableList<AudioProcessor> immutableList, ImmutableList<Effect> immutableList2, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, long j, int i, ListenerSet<Listener> listenerSet, AssetLoader.Factory factory, AudioMixer.Factory factory2, VideoFrameProcessor.Factory factory3, Codec.EncoderFactory encoderFactory2, Muxer.Factory factory4, Looper looper2, DebugViewProvider debugViewProvider2, Clock clock2) {
        boolean z7 = z;
        boolean z8 = z2;
        Looper looper3 = looper2;
        Clock clock3 = clock2;
        Assertions.checkState(!z7 || !z8, "Audio and video cannot both be removed.");
        this.context = context2;
        this.transformationRequest = transformationRequest2;
        this.audioProcessors = immutableList;
        this.videoEffects = immutableList2;
        this.removeAudio = z7;
        this.removeVideo = z8;
        this.flattenForSlowMotion = z3;
        this.trimOptimizationEnabled = z4;
        this.portraitEncodingEnabled = z5;
        this.fileStartsOnVideoFrameEnabled = z6;
        this.maxDelayBetweenMuxerSamplesMs = j;
        this.maxFramesInEncoder = i;
        this.listeners = listenerSet;
        this.assetLoaderFactory = factory;
        this.audioMixerFactory = factory2;
        this.videoFrameProcessorFactory = factory3;
        this.encoderFactory = encoderFactory2;
        this.muxerFactory = factory4;
        this.looper = looper3;
        this.debugViewProvider = debugViewProvider2;
        this.clock = clock3;
        this.transformerState = 0;
        this.applicationHandler = clock3.createHandler(looper3, (Handler.Callback) null);
        this.componentListener = new ComponentListener();
        this.exportResultBuilder = new ExportResult.Builder();
    }

    public Builder buildUpon() {
        return new Builder();
    }

    @Deprecated
    public void setListener(Listener listener) {
        verifyApplicationThread();
        this.listeners.clear();
        this.listeners.add(listener);
    }

    public void addListener(Listener listener) {
        verifyApplicationThread();
        this.listeners.add(listener);
    }

    public void removeListener(Listener listener) {
        verifyApplicationThread();
        this.listeners.remove(listener);
    }

    public void removeAllListeners() {
        verifyApplicationThread();
        this.listeners.clear();
    }

    public void start(Composition composition2, String str) {
        verifyApplicationThread();
        initialize(composition2, str);
        if (!this.trimOptimizationEnabled || isMultiAsset()) {
            MuxerWrapper muxerWrapper = new MuxerWrapper(str, this.muxerFactory, this.componentListener, 0, this.fileStartsOnVideoFrameEnabled, (Format) null, this.maxDelayBetweenMuxerSamplesMs);
            startInternal(composition2, muxerWrapper, this.componentListener, 0, false);
            return;
        }
        processMediaBeforeFirstSyncSampleAfterTrimStartTime();
    }

    public void start(EditedMediaItem editedMediaItem, String str) {
        start(new Composition.Builder(new EditedMediaItemSequence.Builder(editedMediaItem).build(), new EditedMediaItemSequence[0]).build(), str);
    }

    public void start(MediaItem mediaItem, String str) {
        if (mediaItem.clippingConfiguration.equals(MediaItem.ClippingConfiguration.UNSET) || !this.flattenForSlowMotion) {
            start(new EditedMediaItem.Builder(mediaItem).setRemoveAudio(this.removeAudio).setRemoveVideo(this.removeVideo).setFlattenForSlowMotion(this.flattenForSlowMotion).setEffects(new Effects(this.audioProcessors, this.videoEffects)).build(), str);
            return;
        }
        throw new IllegalArgumentException("Clipping is not supported when slow motion flattening is requested");
    }

    @Deprecated
    public void startTransformation(MediaItem mediaItem, String str) {
        start(mediaItem, str);
    }

    public Looper getApplicationLooper() {
        return this.looper;
    }

    public int getProgress(ProgressHolder progressHolder) {
        verifyApplicationThread();
        if (isExportResumed()) {
            return 3;
        }
        if (isExportTrimOptimization()) {
            return getTrimOptimizationProgress(progressHolder);
        }
        TransformerInternal transformerInternal2 = this.transformerInternal;
        if (transformerInternal2 == null) {
            return 0;
        }
        return transformerInternal2.getProgress(progressHolder);
    }

    /* access modifiers changed from: private */
    public boolean isExportResumed() {
        int i = this.transformerState;
        return i == 1 || i == 2 || i == 3 || i == 4;
    }

    /* access modifiers changed from: private */
    public boolean isExportTrimOptimization() {
        int i = this.transformerState;
        return i == 5 || i == 6;
    }

    private int getTrimOptimizationProgress(ProgressHolder progressHolder) {
        int progress;
        if (this.mediaItemInfo == null) {
            return 1;
        }
        float f = ((float) (this.mediaItemInfo.firstSyncSampleTimestampUsAfterTimeUs - ((EditedMediaItem) ((EditedMediaItemSequence) ((Composition) Assertions.checkNotNull(this.composition)).sequences.get(0)).editedMediaItems.get(0)).mediaItem.clippingConfiguration.startPositionUs)) / ((float) this.mediaItemInfo.durationUs);
        if (this.transformerState == 5) {
            TransformerInternal transformerInternal2 = this.transformerInternal;
            if (transformerInternal2 == null || (progress = transformerInternal2.getProgress(progressHolder)) == 0 || progress == 1) {
                return 1;
            }
            if (progress == 2) {
                progressHolder.progress = Math.round(((float) progressHolder.progress) * f);
                return 2;
            } else if (progress == 3) {
                return 3;
            } else {
                throw new IllegalStateException();
            }
        } else {
            float f2 = 100.0f * f;
            TransformerInternal transformerInternal3 = this.transformerInternal;
            if (transformerInternal3 == null) {
                progressHolder.progress = Math.round(f2);
                return 2;
            }
            int progress2 = transformerInternal3.getProgress(progressHolder);
            if (progress2 == 0 || progress2 == 1) {
                progressHolder.progress = Math.round(f2);
                return 2;
            } else if (progress2 == 2) {
                progressHolder.progress = Math.round(f2 + ((1.0f - f) * ((float) progressHolder.progress)));
                return 2;
            } else if (progress2 == 3) {
                return 3;
            } else {
                throw new IllegalStateException();
            }
        }
    }

    /* JADX INFO: finally extract failed */
    public void cancel() {
        verifyApplicationThread();
        TransformerInternal transformerInternal2 = this.transformerInternal;
        if (transformerInternal2 != null) {
            try {
                transformerInternal2.cancel();
                this.transformerInternal = null;
                ListenableFuture<TransmuxTranscodeHelper.ResumeMetadata> listenableFuture = this.getResumeMetadataFuture;
                if (listenableFuture != null && !listenableFuture.isDone()) {
                    this.getResumeMetadataFuture.cancel(false);
                }
                ListenableFuture<Void> listenableFuture2 = this.copyOutputFuture;
                if (listenableFuture2 != null && !listenableFuture2.isDone()) {
                    this.copyOutputFuture.cancel(false);
                }
            } catch (Throwable th) {
                this.transformerInternal = null;
                throw th;
            }
        }
    }

    public void resume(Composition composition2, String str, String str2) {
        verifyApplicationThread();
        initialize(composition2, str);
        this.oldFilePath = str2;
        remuxProcessedVideo();
    }

    private void initialize(Composition composition2, String str) {
        this.composition = composition2;
        this.outputFilePath = str;
        this.exportResultBuilder.reset();
    }

    /* access modifiers changed from: private */
    public void processFullInput() {
        this.transformerState = 0;
        startInternal((Composition) Assertions.checkNotNull(this.composition), new MuxerWrapper((String) Assertions.checkNotNull(this.outputFilePath), this.muxerFactory, this.componentListener, 0, false, (Format) null, this.maxDelayBetweenMuxerSamplesMs), this.componentListener, 0, false);
    }

    private void remuxProcessedVideo() {
        this.transformerState = 1;
        ListenableFuture<TransmuxTranscodeHelper.ResumeMetadata> resumeMetadataAsync = TransmuxTranscodeHelper.getResumeMetadataAsync(this.context, (String) Assertions.checkNotNull(this.oldFilePath), (Composition) Assertions.checkNotNull(this.composition));
        this.getResumeMetadataFuture = resumeMetadataAsync;
        AnonymousClass1 r1 = new FutureCallback<TransmuxTranscodeHelper.ResumeMetadata>() {
            public void onSuccess(TransmuxTranscodeHelper.ResumeMetadata resumeMetadata) {
                TransmuxTranscodeHelper.ResumeMetadata resumeMetadata2 = resumeMetadata;
                if (resumeMetadata2.lastSyncSampleTimestampUs == C.TIME_UNSET || resumeMetadata2.lastSyncSampleTimestampUs == 0) {
                    Transformer.this.processFullInput();
                    return;
                }
                TransmuxTranscodeHelper.ResumeMetadata unused = Transformer.this.resumeMetadata = resumeMetadata2;
                MuxerWrapper unused2 = Transformer.this.remuxingMuxerWrapper = new MuxerWrapper((String) Assertions.checkNotNull(Transformer.this.outputFilePath), Transformer.this.muxerFactory, Transformer.this.componentListener, 1, false, resumeMetadata2.videoFormat, Transformer.this.maxDelayBetweenMuxerSamplesMs);
                Transformer transformer = Transformer.this;
                transformer.startInternal(TransmuxTranscodeHelper.createVideoOnlyComposition(transformer.oldFilePath, resumeMetadata2.lastSyncSampleTimestampUs), (MuxerWrapper) Assertions.checkNotNull(Transformer.this.remuxingMuxerWrapper), Transformer.this.componentListener, 0, true);
            }

            public void onFailure(Throwable th) {
                Transformer.this.processFullInput();
            }
        };
        HandlerWrapper handlerWrapper = this.applicationHandler;
        Objects.requireNonNull(handlerWrapper);
        Futures.addCallback(resumeMetadataAsync, r1, new PlaybackVideoGraphWrapper$$ExternalSyntheticLambda2(handlerWrapper));
    }

    /* access modifiers changed from: private */
    public void processRemainingVideo() {
        this.transformerState = 2;
        Composition buildUponComposition = TransmuxTranscodeHelper.buildUponComposition((Composition) Assertions.checkNotNull(this.composition), true, false, this.resumeMetadata);
        Assertions.checkNotNull(this.remuxingMuxerWrapper);
        this.remuxingMuxerWrapper.changeToAppendMode();
        startInternal(buildUponComposition, this.remuxingMuxerWrapper, this.componentListener, ((TransmuxTranscodeHelper.ResumeMetadata) Assertions.checkNotNull(this.resumeMetadata)).lastSyncSampleTimestampUs, false);
    }

    /* access modifiers changed from: private */
    public void processAudio() {
        this.transformerState = 3;
        startInternal(TransmuxTranscodeHelper.createAudioTranscodeAndVideoTransmuxComposition((Composition) Assertions.checkNotNull(this.composition), (String) Assertions.checkNotNull(this.outputFilePath)), new MuxerWrapper((String) Assertions.checkNotNull(this.oldFilePath), this.muxerFactory, this.componentListener, 0, false, (Format) null, this.maxDelayBetweenMuxerSamplesMs), this.componentListener, 0, false);
    }

    /* access modifiers changed from: private */
    public void copyOutput() {
        this.transformerState = 4;
        ListenableFuture<Void> copyFileAsync = TransmuxTranscodeHelper.copyFileAsync(new File((String) Assertions.checkNotNull(this.oldFilePath)), new File((String) Assertions.checkNotNull(this.outputFilePath)));
        this.copyOutputFuture = copyFileAsync;
        AnonymousClass2 r1 = new FutureCallback<Void>() {
            public void onSuccess(Void voidR) {
                Transformer.this.onExportCompletedWithSuccess();
            }

            public void onFailure(Throwable th) {
                Transformer.this.onExportCompletedWithError(ExportException.createForUnexpected(new IOException("Copy output task failed for the resumed export", th)));
            }
        };
        HandlerWrapper handlerWrapper = this.applicationHandler;
        Objects.requireNonNull(handlerWrapper);
        Futures.addCallback(copyFileAsync, r1, new PlaybackVideoGraphWrapper$$ExternalSyntheticLambda2(handlerWrapper));
    }

    private void processMediaBeforeFirstSyncSampleAfterTrimStartTime() {
        this.transformerState = 5;
        final EditedMediaItem editedMediaItem = (EditedMediaItem) ((EditedMediaItemSequence) ((Composition) Assertions.checkNotNull(this.composition)).sequences.get(0)).editedMediaItems.get(0);
        final long j = editedMediaItem.mediaItem.clippingConfiguration.startPositionUs;
        final long j2 = editedMediaItem.mediaItem.clippingConfiguration.endPositionUs;
        ListenableFuture<Mp4Info> mp4Info = TransmuxTranscodeHelper.getMp4Info(this.context, ((MediaItem.LocalConfiguration) Assertions.checkNotNull(editedMediaItem.mediaItem.localConfiguration)).uri.toString(), j);
        AnonymousClass3 r1 = new FutureCallback<Mp4Info>() {
            public void onSuccess(Mp4Info mp4Info) {
                Mp4Info mp4Info2 = mp4Info;
                if (mp4Info2.firstSyncSampleTimestampUsAfterTimeUs == C.TIME_UNSET) {
                    Transformer.this.exportResultBuilder.setOptimizationResult(4);
                    Transformer.this.processFullInput();
                    return;
                }
                if (mp4Info2.firstSyncSampleTimestampUsAfterTimeUs != Long.MIN_VALUE) {
                    long j = j2;
                    if (j == Long.MIN_VALUE || j >= mp4Info2.firstSyncSampleTimestampUsAfterTimeUs) {
                        if (mp4Info2.firstSyncSampleTimestampUsAfterTimeUs - j <= ((mp4Info2.audioFormat == null || mp4Info2.audioFormat.sampleRate == -1) ? 0 : Util.sampleCountToDurationUs(1024, mp4Info2.audioFormat.sampleRate)) || mp4Info2.isFirstVideoSampleAfterTimeUsSyncSample) {
                            Transformer transformer = Transformer.this;
                            Composition unused = transformer.composition = TransmuxTranscodeHelper.buildUponCompositionForTrimOptimization(transformer.composition, mp4Info2.firstSyncSampleTimestampUsAfterTimeUs, j2, mp4Info2.durationUs, true, false);
                            Transformer.this.exportResultBuilder.setOptimizationResult(2);
                            Transformer.this.processFullInput();
                            return;
                        }
                        MuxerWrapper unused2 = Transformer.this.remuxingMuxerWrapper = new MuxerWrapper((String) Assertions.checkNotNull(Transformer.this.outputFilePath), Transformer.this.muxerFactory, Transformer.this.componentListener, 1, false, mp4Info2.videoFormat, Transformer.this.maxDelayBetweenMuxerSamplesMs);
                        if (TransformerUtil.shouldTranscodeVideo((Format) Assertions.checkNotNull(mp4Info2.videoFormat), Transformer.this.composition, 0, Transformer.this.transformationRequest, Transformer.this.encoderFactory, Transformer.this.remuxingMuxerWrapper) || (mp4Info2.audioFormat != null && TransformerUtil.shouldTranscodeAudio(mp4Info2.audioFormat, Transformer.this.composition, 0, Transformer.this.transformationRequest, Transformer.this.encoderFactory, Transformer.this.remuxingMuxerWrapper))) {
                            MuxerWrapper unused3 = Transformer.this.remuxingMuxerWrapper = null;
                            Transformer.this.exportResultBuilder.setOptimizationResult(3);
                            Transformer.this.processFullInput();
                            return;
                        }
                        Mp4Info unused4 = Transformer.this.mediaItemInfo = mp4Info2;
                        TransformerUtil.maybeSetMuxerWrapperAdditionalRotationDegrees(Transformer.this.remuxingMuxerWrapper, editedMediaItem.effects.videoEffects, (Format) Assertions.checkNotNull(mp4Info2.videoFormat));
                        Composition buildUponCompositionForTrimOptimization = TransmuxTranscodeHelper.buildUponCompositionForTrimOptimization(Transformer.this.composition, j, mp4Info2.firstSyncSampleTimestampUsAfterTimeUs, mp4Info2.durationUs, false, true);
                        Transformer transformer2 = Transformer.this;
                        transformer2.startInternal(buildUponCompositionForTrimOptimization, (MuxerWrapper) Assertions.checkNotNull(transformer2.remuxingMuxerWrapper), Transformer.this.componentListener, 0, false);
                        return;
                    }
                }
                Transformer.this.exportResultBuilder.setOptimizationResult(2);
                Transformer.this.processFullInput();
            }

            public void onFailure(Throwable th) {
                Transformer.this.exportResultBuilder.setOptimizationResult(5);
                Transformer.this.processFullInput();
            }
        };
        HandlerWrapper handlerWrapper = this.applicationHandler;
        Objects.requireNonNull(handlerWrapper);
        Futures.addCallback(mp4Info, r1, new PlaybackVideoGraphWrapper$$ExternalSyntheticLambda2(handlerWrapper));
    }

    /* access modifiers changed from: private */
    public void remuxRemainingMedia() {
        this.transformerState = 6;
        EditedMediaItem editedMediaItem = (EditedMediaItem) ((EditedMediaItemSequence) ((Composition) Assertions.checkNotNull(this.composition)).sequences.get(0)).editedMediaItems.get(0);
        Mp4Info mp4Info = (Mp4Info) Assertions.checkNotNull(this.mediaItemInfo);
        long j = editedMediaItem.mediaItem.clippingConfiguration.startPositionUs;
        Composition buildUponCompositionForTrimOptimization = TransmuxTranscodeHelper.buildUponCompositionForTrimOptimization(this.composition, mp4Info.firstSyncSampleTimestampUsAfterTimeUs, editedMediaItem.mediaItem.clippingConfiguration.endPositionUs, mp4Info.durationUs, true, true);
        Assertions.checkNotNull(this.remuxingMuxerWrapper);
        this.remuxingMuxerWrapper.changeToAppendMode();
        startInternal(buildUponCompositionForTrimOptimization, this.remuxingMuxerWrapper, this.componentListener, mp4Info.firstSyncSampleTimestampUsAfterTimeUs - j, false);
    }

    private boolean isMultiAsset() {
        if (((Composition) Assertions.checkNotNull(this.composition)).sequences.size() > 1 || ((EditedMediaItemSequence) this.composition.sequences.get(0)).editedMediaItems.size() > 1) {
            return true;
        }
        return false;
    }

    private void verifyApplicationThread() {
        if (Looper.myLooper() != this.looper) {
            throw new IllegalStateException("Transformer is accessed on the wrong thread.");
        }
    }

    /* access modifiers changed from: private */
    public void startInternal(Composition composition2, MuxerWrapper muxerWrapper, ComponentListener componentListener2, long j, boolean z) {
        Composition composition3 = composition2;
        Assertions.checkState(this.transformerInternal == null, "There is already an export in progress.");
        TransformationRequest transformationRequest2 = this.transformationRequest;
        if (composition3.hdrMode != 0) {
            transformationRequest2 = transformationRequest2.buildUpon().setHdrMode(composition3.hdrMode).build();
        }
        TransformationRequest transformationRequest3 = transformationRequest2;
        FallbackListener fallbackListener = new FallbackListener(composition3, this.listeners, this.applicationHandler, transformationRequest3);
        AssetLoader.Factory factory = this.assetLoaderFactory;
        if (z || factory == null) {
            factory = new DefaultAssetLoaderFactory(this.context, new DefaultDecoderFactory.Builder(this.context).build(), this.clock);
        }
        DebugTraceUtil.reset();
        TransformerInternal transformerInternal2 = r1;
        TransformerInternal transformerInternal3 = new TransformerInternal(this.context, composition2, transformationRequest3, factory, this.audioMixerFactory, this.videoFrameProcessorFactory, this.encoderFactory, this.portraitEncodingEnabled, this.maxFramesInEncoder, muxerWrapper, componentListener2, fallbackListener, this.applicationHandler, this.debugViewProvider, this.clock, j);
        TransformerInternal transformerInternal4 = transformerInternal2;
        this.transformerInternal = transformerInternal4;
        transformerInternal4.start();
    }

    /* access modifiers changed from: private */
    public void onExportCompletedWithSuccess() {
        this.listeners.queueEvent(-1, new Transformer$$ExternalSyntheticLambda0(this));
        this.listeners.flushEvents();
        this.transformerState = 0;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onExportCompletedWithSuccess$0$androidx-media3-transformer-Transformer  reason: not valid java name */
    public /* synthetic */ void m1134lambda$onExportCompletedWithSuccess$0$androidxmedia3transformerTransformer(Listener listener) {
        listener.onCompleted((Composition) Assertions.checkNotNull(this.composition), this.exportResultBuilder.build());
    }

    /* access modifiers changed from: private */
    public void onExportCompletedWithError(ExportException exportException) {
        this.listeners.queueEvent(-1, new Transformer$$ExternalSyntheticLambda1(this, exportException));
        this.listeners.flushEvents();
        this.transformerState = 0;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onExportCompletedWithError$1$androidx-media3-transformer-Transformer  reason: not valid java name */
    public /* synthetic */ void m1133lambda$onExportCompletedWithError$1$androidxmedia3transformerTransformer(ExportException exportException, Listener listener) {
        listener.onError((Composition) Assertions.checkNotNull(this.composition), this.exportResultBuilder.build(), exportException);
    }

    private final class ComponentListener implements TransformerInternal.Listener, MuxerWrapper.Listener {
        private ComponentListener() {
        }

        public void onCompleted(ImmutableList<ExportResult.ProcessedInput> immutableList, String str, String str2) {
            Transformer.this.exportResultBuilder.addProcessedInputs(immutableList);
            if (str != null) {
                Transformer.this.exportResultBuilder.setAudioEncoderName(str);
            }
            if (str2 != null) {
                Transformer.this.exportResultBuilder.setVideoEncoderName(str2);
            }
            TransformerInternal unused = Transformer.this.transformerInternal = null;
            if (Transformer.this.transformerState == 1) {
                Transformer.this.processRemainingVideo();
            } else if (Transformer.this.transformerState == 2) {
                MuxerWrapper unused2 = Transformer.this.remuxingMuxerWrapper = null;
                Transformer.this.processAudio();
            } else if (Transformer.this.transformerState == 3) {
                Transformer.this.copyOutput();
            } else if (Transformer.this.transformerState == 5) {
                Transformer.this.remuxRemainingMedia();
            } else if (Transformer.this.transformerState == 6) {
                Mp4Info unused3 = Transformer.this.mediaItemInfo = null;
                Transformer.this.exportResultBuilder.setOptimizationResult(1);
                Transformer.this.onExportCompletedWithSuccess();
            } else {
                Transformer.this.onExportCompletedWithSuccess();
            }
        }

        public void onError(ImmutableList<ExportResult.ProcessedInput> immutableList, String str, String str2, ExportException exportException) {
            if (exportException.errorCode != 7003 || (!Transformer.this.isExportTrimOptimization() && !Transformer.this.isExportResumed())) {
                Transformer.this.exportResultBuilder.addProcessedInputs(immutableList);
                if (str != null) {
                    Transformer.this.exportResultBuilder.setAudioEncoderName(str);
                }
                if (str2 != null) {
                    Transformer.this.exportResultBuilder.setVideoEncoderName(str2);
                }
                Transformer.this.exportResultBuilder.setExportException(exportException);
                TransformerInternal unused = Transformer.this.transformerInternal = null;
                Transformer.this.onExportCompletedWithError(exportException);
                return;
            }
            MuxerWrapper unused2 = Transformer.this.remuxingMuxerWrapper = null;
            TransformerInternal unused3 = Transformer.this.transformerInternal = null;
            Transformer.this.exportResultBuilder.reset();
            Transformer.this.exportResultBuilder.setOptimizationResult(6);
            Transformer.this.processFullInput();
        }

        public void onTrackEnded(int i, Format format, int i2, int i3) {
            if (i == 1) {
                Transformer.this.exportResultBuilder.setAudioMimeType(format.sampleMimeType).setAverageAudioBitrate(i2);
                if (format.channelCount != -1) {
                    Transformer.this.exportResultBuilder.setChannelCount(format.channelCount);
                }
                if (format.sampleRate != -1) {
                    Transformer.this.exportResultBuilder.setSampleRate(format.sampleRate);
                }
            } else if (i == 2) {
                Transformer.this.exportResultBuilder.setVideoMimeType(format.sampleMimeType).setAverageVideoBitrate(i2).setColorInfo(format.colorInfo).setVideoFrameCount(i3);
                if (format.height != -1) {
                    Transformer.this.exportResultBuilder.setHeight(format.height);
                }
                if (format.width != -1) {
                    Transformer.this.exportResultBuilder.setWidth(format.width);
                }
            }
        }

        public void onEnded(long j, long j2) {
            Transformer.this.exportResultBuilder.setDurationMs(j).setFileSizeBytes(j2);
            ((TransformerInternal) Assertions.checkNotNull(Transformer.this.transformerInternal)).endWithCompletion();
        }

        public void onError(ExportException exportException) {
            ((TransformerInternal) Assertions.checkNotNull(Transformer.this.transformerInternal)).endWithException(exportException);
        }
    }
}
