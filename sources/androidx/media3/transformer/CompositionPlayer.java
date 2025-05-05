package androidx.media3.transformer;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Pair;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import androidx.media3.common.C;
import androidx.media3.common.Effect;
import androidx.media3.common.MediaItem;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.Player;
import androidx.media3.common.PreviewingVideoGraph;
import androidx.media3.common.SimpleBasePlayer;
import androidx.media3.common.Timeline;
import androidx.media3.common.VideoFrameProcessingException;
import androidx.media3.common.VideoSize;
import androidx.media3.common.audio.SpeedProvider;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.HandlerWrapper;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Size;
import androidx.media3.common.util.Util;
import androidx.media3.effect.PreviewingSingleInputVideoGraph;
import androidx.media3.effect.TimestampAdjustment;
import androidx.media3.exoplayer.ExoPlaybackException;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.exoplayer.audio.AudioSink;
import androidx.media3.exoplayer.audio.DefaultAudioSink;
import androidx.media3.exoplayer.image.ImageDecoder;
import androidx.media3.exoplayer.source.ClippingMediaSource;
import androidx.media3.exoplayer.source.ConcatenatingMediaSource2;
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory;
import androidx.media3.exoplayer.source.ExternalLoader;
import androidx.media3.exoplayer.source.FilteringMediaSource;
import androidx.media3.exoplayer.source.ForwardingTimeline;
import androidx.media3.exoplayer.source.MediaPeriod;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.MergingMediaSource;
import androidx.media3.exoplayer.source.SilenceMediaSource;
import androidx.media3.exoplayer.source.TrackGroupArray;
import androidx.media3.exoplayer.source.WrappingMediaSource;
import androidx.media3.exoplayer.trackselection.DefaultTrackSelector;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import androidx.media3.exoplayer.trackselection.MappingTrackSelector;
import androidx.media3.exoplayer.upstream.Allocator;
import androidx.media3.exoplayer.util.EventLogger;
import androidx.media3.exoplayer.video.PlaybackVideoGraphWrapper;
import androidx.media3.exoplayer.video.VideoFrameReleaseControl;
import androidx.media3.transformer.CompositionPlayerInternal;
import androidx.media3.transformer.DefaultAudioMixer;
import androidx.media3.transformer.EditedMediaItemSequence;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public final class CompositionPlayer extends SimpleBasePlayer implements CompositionPlayerInternal.Listener, PlaybackVideoGraphWrapper.Listener, SurfaceHolder.Callback {
    private static final Player.Commands AVAILABLE_COMMANDS = new Player.Commands.Builder().addAll(1, 2, 3, 5, 8, 11, 12, 16, 17, 15, 27, 22, 24, 32).build();
    /* access modifiers changed from: private */
    public static final int[] SUPPORTED_LISTENER_EVENTS = {4, 5, 10, 11, 1};
    private static final String TAG = "CompositionPlayer";
    private final HandlerWrapper applicationHandler;
    private final Clock clock;
    private Composition composition;
    private long compositionDurationUs;
    private final HandlerWrapper compositionInternalListenerHandler;
    private CompositionPlayerInternal compositionPlayerInternal;
    private final Context context;
    private Surface displaySurface;
    private final ExternalLoader externalImageLoader;
    private final AudioSink finalAudioSink;
    private final ImageDecoder.Factory imageDecoderFactory;
    private boolean playWhenReady;
    /* access modifiers changed from: private */
    public int playWhenReadyChangeReason;
    private PlaybackException playbackException;
    private int playbackState;
    private HandlerThread playbackThread;
    private final List<ExoPlayer> players;
    private ImmutableList<SimpleBasePlayer.MediaItemData> playlist;
    private final PreviewingVideoGraph.Factory previewingVideoGraphFactory;
    private boolean renderedFirstFrame;
    /* access modifiers changed from: private */
    public int repeatMode;
    private boolean repeatingCompositionSeekInProgress;
    private SurfaceHolder surfaceHolder;
    private Object videoOutput;
    private Size videoOutputSize;
    private float volume;

    public void onFrameDropped(PlaybackVideoGraphWrapper playbackVideoGraphWrapper) {
    }

    public void onVideoSizeChanged(PlaybackVideoGraphWrapper playbackVideoGraphWrapper, VideoSize videoSize) {
    }

    public static final class Builder {
        /* access modifiers changed from: private */
        public AudioSink audioSink;
        private boolean built;
        /* access modifiers changed from: private */
        public Clock clock = Clock.DEFAULT;
        /* access modifiers changed from: private */
        public final Context context;
        /* access modifiers changed from: private */
        public ExternalLoader externalImageLoader;
        /* access modifiers changed from: private */
        public ImageDecoder.Factory imageDecoderFactory = ImageDecoder.Factory.DEFAULT;
        /* access modifiers changed from: private */
        public Looper looper;
        /* access modifiers changed from: private */
        public PreviewingVideoGraph.Factory previewingVideoGraphFactory;

        public Builder(Context context2) {
            this.context = context2.getApplicationContext();
        }

        public Builder setLooper(Looper looper2) {
            this.looper = looper2;
            return this;
        }

        public Builder setAudioSink(AudioSink audioSink2) {
            this.audioSink = audioSink2;
            return this;
        }

        public Builder setExternalImageLoader(ExternalLoader externalLoader) {
            this.externalImageLoader = externalLoader;
            return this;
        }

        public Builder setImageDecoderFactory(ImageDecoder.Factory factory) {
            this.imageDecoderFactory = factory;
            return this;
        }

        public Builder setClock(Clock clock2) {
            this.clock = clock2;
            return this;
        }

        public Builder setPreviewingVideoGraphFactory(PreviewingVideoGraph.Factory factory) {
            this.previewingVideoGraphFactory = factory;
            return this;
        }

        public CompositionPlayer build() {
            Assertions.checkState(!this.built);
            if (this.looper == null) {
                this.looper = (Looper) Assertions.checkStateNotNull(Looper.myLooper());
            }
            if (this.audioSink == null) {
                this.audioSink = new DefaultAudioSink.Builder(this.context).build();
            }
            if (this.previewingVideoGraphFactory == null) {
                this.previewingVideoGraphFactory = new PreviewingSingleInputVideoGraph.Factory();
            }
            CompositionPlayer compositionPlayer = new CompositionPlayer(this);
            this.built = true;
            return compositionPlayer;
        }
    }

    private CompositionPlayer(Builder builder) {
        super((Looper) Assertions.checkNotNull(builder.looper), builder.clock);
        this.context = builder.context;
        Clock access$200 = builder.clock;
        this.clock = access$200;
        this.applicationHandler = access$200.createHandler(builder.looper, (Handler.Callback) null);
        this.finalAudioSink = (AudioSink) Assertions.checkNotNull(builder.audioSink);
        this.externalImageLoader = builder.externalImageLoader;
        this.imageDecoderFactory = builder.imageDecoderFactory;
        this.previewingVideoGraphFactory = (PreviewingVideoGraph.Factory) Assertions.checkNotNull(builder.previewingVideoGraphFactory);
        this.compositionInternalListenerHandler = access$200.createHandler(builder.looper, (Handler.Callback) null);
        this.players = new ArrayList();
        this.compositionDurationUs = C.TIME_UNSET;
        this.playbackState = 1;
        this.volume = 1.0f;
    }

    public void setComposition(Composition composition2) {
        verifyApplicationThread();
        boolean z = true;
        Assertions.checkArgument(!composition2.sequences.isEmpty());
        Assertions.checkArgument(!composition2.hasGaps());
        if (this.composition != null) {
            z = false;
        }
        Assertions.checkState(z);
        Composition deactivateSpeedAdjustingVideoEffects = deactivateSpeedAdjustingVideoEffects(composition2);
        setCompositionInternal(deactivateSpeedAdjustingVideoEffects);
        Object obj = this.videoOutput;
        if (obj != null) {
            if (obj instanceof SurfaceHolder) {
                setVideoSurfaceHolderInternal((SurfaceHolder) obj);
            } else if (obj instanceof SurfaceView) {
                setVideoSurfaceHolderInternal(((SurfaceView) obj).getHolder());
            } else if (obj instanceof Surface) {
                setVideoSurfaceInternal((Surface) obj, (Size) Assertions.checkNotNull(this.videoOutputSize));
            } else {
                throw new IllegalStateException(this.videoOutput.getClass().toString());
            }
        }
        this.composition = deactivateSpeedAdjustingVideoEffects;
    }

    public void setVideoSurface(Surface surface, Size size) {
        this.videoOutput = surface;
        this.videoOutputSize = size;
        setVideoSurfaceInternal(surface, size);
    }

    public void onFirstFrameRendered(PlaybackVideoGraphWrapper playbackVideoGraphWrapper) {
        this.applicationHandler.post(new CompositionPlayer$$ExternalSyntheticLambda4(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onFirstFrameRendered$0$androidx-media3-transformer-CompositionPlayer  reason: not valid java name */
    public /* synthetic */ void m1120lambda$onFirstFrameRendered$0$androidxmedia3transformerCompositionPlayer() {
        this.renderedFirstFrame = true;
        invalidateState();
    }

    public void onError(PlaybackVideoGraphWrapper playbackVideoGraphWrapper, VideoFrameProcessingException videoFrameProcessingException) {
        this.applicationHandler.post(new CompositionPlayer$$ExternalSyntheticLambda0(this, videoFrameProcessingException));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onError$1$androidx-media3-transformer-CompositionPlayer  reason: not valid java name */
    public /* synthetic */ void m1119lambda$onError$1$androidxmedia3transformerCompositionPlayer(VideoFrameProcessingException videoFrameProcessingException) {
        maybeUpdatePlaybackError("Error processing video frames", videoFrameProcessingException, 7001);
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder2) {
        this.videoOutputSize = new Size(surfaceHolder2.getSurfaceFrame().width(), surfaceHolder2.getSurfaceFrame().height());
        setVideoSurfaceInternal(surfaceHolder2.getSurface(), this.videoOutputSize);
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder2, int i, int i2, int i3) {
        maybeSetOutputSurfaceInfo(i2, i3);
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder2) {
        clearVideoSurfaceInternal();
    }

    /* access modifiers changed from: protected */
    public SimpleBasePlayer.State getState() {
        int i = this.playbackState;
        updatePlaybackState();
        if (i != 3 && this.playbackState == 3 && this.playWhenReady) {
            for (int i2 = 0; i2 < this.players.size(); i2++) {
                this.players.get(i2).setPlayWhenReady(true);
            }
        } else if (i == 3 && this.playWhenReady && this.playbackState == 2) {
            for (int i3 = 0; i3 < this.players.size(); i3++) {
                this.players.get(i3).setPlayWhenReady(false);
            }
        }
        SimpleBasePlayer.State.Builder newlyRenderedFirstFrame = new SimpleBasePlayer.State.Builder().setAvailableCommands(AVAILABLE_COMMANDS).setPlaybackState(this.playbackState).setPlayerError(this.playbackException).setPlayWhenReady(this.playWhenReady, this.playWhenReadyChangeReason).setRepeatMode(this.repeatMode).setVolume(this.volume).setContentPositionMs((SimpleBasePlayer.PositionSupplier) new CompositionPlayer$$ExternalSyntheticLambda1(this)).setContentBufferedPositionMs(new CompositionPlayer$$ExternalSyntheticLambda2(this)).setTotalBufferedDurationMs(new CompositionPlayer$$ExternalSyntheticLambda3(this)).setNewlyRenderedFirstFrame(getRenderedFirstFrameAndReset());
        if (this.repeatingCompositionSeekInProgress) {
            newlyRenderedFirstFrame.setPositionDiscontinuity(0, C.TIME_UNSET);
            this.repeatingCompositionSeekInProgress = false;
        }
        ImmutableList<SimpleBasePlayer.MediaItemData> immutableList = this.playlist;
        if (immutableList != null) {
            newlyRenderedFirstFrame.setPlaylist(immutableList);
        }
        return newlyRenderedFirstFrame.build();
    }

    /* access modifiers changed from: protected */
    public ListenableFuture<?> handlePrepare() {
        Assertions.checkStateNotNull(this.composition, "No composition set");
        if (this.playbackState != 1) {
            return Futures.immediateVoidFuture();
        }
        for (int i = 0; i < this.players.size(); i++) {
            this.players.get(i).prepare();
        }
        return Futures.immediateVoidFuture();
    }

    /* access modifiers changed from: protected */
    public ListenableFuture<?> handleSetPlayWhenReady(boolean z) {
        this.playWhenReady = z;
        this.playWhenReadyChangeReason = 1;
        if (this.playbackState == 3) {
            for (int i = 0; i < this.players.size(); i++) {
                this.players.get(i).setPlayWhenReady(z);
            }
        }
        return Futures.immediateVoidFuture();
    }

    /* access modifiers changed from: protected */
    public ListenableFuture<?> handleSetRepeatMode(int i) {
        boolean z = true;
        if (i == 1) {
            z = false;
        }
        Assertions.checkArgument(z);
        this.repeatMode = i;
        return Futures.immediateVoidFuture();
    }

    /* access modifiers changed from: protected */
    public ListenableFuture<?> handleStop() {
        for (int i = 0; i < this.players.size(); i++) {
            this.players.get(i).stop();
        }
        return Futures.immediateVoidFuture();
    }

    /* access modifiers changed from: protected */
    public ListenableFuture<?> handleRelease() {
        if (this.composition == null) {
            return Futures.immediateVoidFuture();
        }
        Assertions.checkState(((HandlerThread) Assertions.checkStateNotNull(this.playbackThread)).isAlive());
        for (int i = 0; i < this.players.size(); i++) {
            this.players.get(i).release();
        }
        ((CompositionPlayerInternal) Assertions.checkStateNotNull(this.compositionPlayerInternal)).release();
        removeSurfaceCallbacks();
        this.compositionInternalListenerHandler.removeCallbacksAndMessages((Object) null);
        this.displaySurface = null;
        ((HandlerThread) Assertions.checkStateNotNull(this.playbackThread)).quitSafely();
        this.applicationHandler.removeCallbacksAndMessages((Object) null);
        return Futures.immediateVoidFuture();
    }

    /* access modifiers changed from: protected */
    public ListenableFuture<?> handleClearVideoOutput(Object obj) {
        Assertions.checkArgument(Util.areEqual(obj, this.videoOutput));
        this.videoOutput = null;
        if (this.composition == null) {
            return Futures.immediateVoidFuture();
        }
        removeSurfaceCallbacks();
        clearVideoSurfaceInternal();
        return Futures.immediateVoidFuture();
    }

    /* access modifiers changed from: protected */
    public ListenableFuture<?> handleSetVideoOutput(Object obj) {
        boolean z = obj instanceof SurfaceHolder;
        if (z || (obj instanceof SurfaceView)) {
            this.videoOutput = obj;
            if (this.composition == null) {
                return Futures.immediateVoidFuture();
            }
            if (z) {
                setVideoSurfaceHolderInternal((SurfaceHolder) obj);
            } else {
                setVideoSurfaceHolderInternal(((SurfaceView) obj).getHolder());
            }
            return Futures.immediateVoidFuture();
        }
        throw new UnsupportedOperationException(obj.getClass().toString());
    }

    /* access modifiers changed from: protected */
    public ListenableFuture<?> handleSetVolume(float f) {
        float constrainValue = Util.constrainValue(f, 0.0f, 1.0f);
        this.volume = constrainValue;
        this.finalAudioSink.setVolume(constrainValue);
        return Futures.immediateVoidFuture();
    }

    /* access modifiers changed from: protected */
    public ListenableFuture<?> handleSeek(int i, long j, int i2) {
        CompositionPlayerInternal compositionPlayerInternal2 = (CompositionPlayerInternal) Assertions.checkStateNotNull(this.compositionPlayerInternal);
        compositionPlayerInternal2.startSeek(j);
        for (int i3 = 0; i3 < this.players.size(); i3++) {
            this.players.get(i3).seekTo(j);
        }
        compositionPlayerInternal2.endSeek();
        return Futures.immediateVoidFuture();
    }

    public void onError(String str, Exception exc, int i) {
        maybeUpdatePlaybackError(str, exc, i);
    }

    private static Composition deactivateSpeedAdjustingVideoEffects(Composition composition2) {
        ArrayList arrayList = new ArrayList();
        UnmodifiableIterator<EditedMediaItemSequence> it = composition2.sequences.iterator();
        while (it.hasNext()) {
            EditedMediaItemSequence next = it.next();
            ArrayList arrayList2 = new ArrayList();
            UnmodifiableIterator<EditedMediaItem> it2 = next.editedMediaItems.iterator();
            while (it2.hasNext()) {
                EditedMediaItem next2 = it2.next();
                ImmutableList<Effect> immutableList = next2.effects.videoEffects;
                ArrayList arrayList3 = new ArrayList();
                UnmodifiableIterator<Effect> it3 = immutableList.iterator();
                while (it3.hasNext()) {
                    Effect next3 = it3.next();
                    if (next3 instanceof TimestampAdjustment) {
                        arrayList3.add(new InactiveTimestampAdjustment(((TimestampAdjustment) next3).speedProvider));
                    } else {
                        arrayList3.add(next3);
                    }
                }
                arrayList2.add(next2.buildUpon().setEffects(new Effects(next2.effects.audioProcessors, arrayList3)).build());
            }
            arrayList.add(new EditedMediaItemSequence.Builder((List<EditedMediaItem>) arrayList2).setIsLooping(next.isLooping).build());
        }
        return composition2.buildUpon().setSequences(arrayList).build();
    }

    private void updatePlaybackState() {
        if (this.players.isEmpty() || this.playbackException != null) {
            this.playbackState = 1;
            return;
        }
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < this.players.size(); i4++) {
            int playbackState2 = this.players.get(i4).getPlaybackState();
            if (playbackState2 == 1) {
                i++;
            } else if (playbackState2 == 2) {
                i2++;
            } else if (playbackState2 == 3) {
                continue;
            } else if (playbackState2 == 4) {
                i3++;
            } else {
                throw new IllegalStateException(String.valueOf(playbackState2));
            }
        }
        if (i > 0) {
            this.playbackState = 1;
        } else if (i2 > 0) {
            this.playbackState = 2;
        } else if (i3 == this.players.size()) {
            this.playbackState = 4;
        } else {
            this.playbackState = 3;
        }
    }

    private void setCompositionInternal(Composition composition2) {
        SequenceRenderersFactory sequenceRenderersFactory;
        boolean z;
        this.compositionDurationUs = getCompositionDurationUs(composition2);
        HandlerThread handlerThread = new HandlerThread("CompositionPlaybackThread", -16);
        this.playbackThread = handlerThread;
        handlerThread.start();
        PlaybackAudioGraphWrapper playbackAudioGraphWrapper = new PlaybackAudioGraphWrapper(new DefaultAudioMixer.Factory(), composition2.effects.audioProcessors, (AudioSink) Assertions.checkNotNull(this.finalAudioSink));
        PlaybackVideoGraphWrapper build = new PlaybackVideoGraphWrapper.Builder(this.context, new VideoFrameReleaseControl(this.context, new CompositionFrameTimingEvaluator(), 0)).setPreviewingVideoGraphFactory((PreviewingVideoGraph.Factory) Assertions.checkNotNull(this.previewingVideoGraphFactory)).setCompositionEffects(composition2.effects.videoEffects).setClock(this.clock).build();
        build.addListener(this);
        long sequenceDurationUs = getSequenceDurationUs((EditedMediaItemSequence) Assertions.checkNotNull((EditedMediaItemSequence) composition2.sequences.get(0)));
        for (int i = 0; i < composition2.sequences.size(); i++) {
            EditedMediaItemSequence editedMediaItemSequence = (EditedMediaItemSequence) composition2.sequences.get(i);
            if (i == 0) {
                sequenceRenderersFactory = SequenceRenderersFactory.create(this.context, editedMediaItemSequence, playbackAudioGraphWrapper, build.getSink(), this.imageDecoderFactory, i);
            } else {
                sequenceRenderersFactory = SequenceRenderersFactory.createForAudio(this.context, editedMediaItemSequence, playbackAudioGraphWrapper, i);
            }
            ExoPlayer.Builder clock2 = new ExoPlayer.Builder(this.context).setLooper(getApplicationLooper()).setPlaybackLooper(this.playbackThread.getLooper()).setRenderersFactory(sequenceRenderersFactory).setHandleAudioBecomingNoisy(true).setClock(this.clock);
            int i2 = 0;
            while (true) {
                if (i2 >= editedMediaItemSequence.editedMediaItems.size()) {
                    z = false;
                    break;
                } else if (((EditedMediaItem) editedMediaItemSequence.editedMediaItems.get(i2)).removeVideo) {
                    z = true;
                    break;
                } else {
                    i2++;
                }
            }
            clock2.setTrackSelector(new CompositionTrackSelector(this.context, i, z));
            ExoPlayer build2 = clock2.build();
            build2.addListener(new PlayerListener(i));
            build2.addAnalyticsListener(new EventLogger());
            build2.setPauseAtEndOfMediaItems(true);
            if (i == 0) {
                setPrimaryPlayerSequence(build2, editedMediaItemSequence);
            } else {
                setSecondaryPlayerSequence(build2, editedMediaItemSequence, sequenceDurationUs);
            }
            this.players.add(build2);
            if (i == 0) {
                invalidateState();
                this.playlist = createPlaylist();
            }
        }
        this.compositionPlayerInternal = new CompositionPlayerInternal(this.playbackThread.getLooper(), this.clock, playbackAudioGraphWrapper, build, this, this.compositionInternalListenerHandler);
    }

    private void setPrimaryPlayerSequence(ExoPlayer exoPlayer, EditedMediaItemSequence editedMediaItemSequence) {
        EditedMediaItemSequence editedMediaItemSequence2 = editedMediaItemSequence;
        ConcatenatingMediaSource2.Builder useDefaultMediaSourceFactory = new ConcatenatingMediaSource2.Builder().useDefaultMediaSourceFactory(this.context);
        for (int i = 0; i < editedMediaItemSequence2.editedMediaItems.size(); i++) {
            EditedMediaItem editedMediaItem = (EditedMediaItem) editedMediaItemSequence2.editedMediaItems.get(i);
            Assertions.checkArgument(editedMediaItem.durationUs != C.TIME_UNSET);
            long presentationDurationUs = editedMediaItem.getPresentationDurationUs();
            DefaultMediaSourceFactory defaultMediaSourceFactory = new DefaultMediaSourceFactory(this.context);
            ExternalLoader externalLoader = this.externalImageLoader;
            if (externalLoader != null) {
                defaultMediaSourceFactory.setExternalImageLoader(externalLoader);
            }
            long j = presentationDurationUs;
            ClippingMediaSource clippingMediaSource = new ClippingMediaSource(new SilenceMediaSource(editedMediaItem.durationUs), editedMediaItem.mediaItem.clippingConfiguration.startPositionUs, editedMediaItem.mediaItem.clippingConfiguration.endPositionUs);
            MediaSource createMediaSource = defaultMediaSourceFactory.createMediaSource(editedMediaItem.mediaItem);
            if (editedMediaItem.removeAudio) {
                createMediaSource = new FilteringMediaSource(createMediaSource, (Set<Integer>) ImmutableSet.of(2, 4));
            }
            long j2 = j;
            useDefaultMediaSourceFactory.add(wrapWithVideoEffectsBasedMediaSources(new MergingMediaSource(createMediaSource, clippingMediaSource), editedMediaItem.effects.videoEffects, j2), Util.usToMs(j2));
        }
        exoPlayer.setMediaSource(useDefaultMediaSourceFactory.build());
    }

    private void setSecondaryPlayerSequence(ExoPlayer exoPlayer, EditedMediaItemSequence editedMediaItemSequence, long j) {
        ConcatenatingMediaSource2.Builder useDefaultMediaSourceFactory = new ConcatenatingMediaSource2.Builder().useDefaultMediaSourceFactory(this.context);
        long j2 = 0;
        int i = 0;
        while (true) {
            if (j2 >= j) {
                break;
            }
            EditedMediaItem editedMediaItem = (EditedMediaItem) editedMediaItemSequence.editedMediaItems.get(i);
            long presentationDurationUs = editedMediaItem.getPresentationDurationUs();
            long j3 = j2 + presentationDurationUs;
            if (j3 > j) {
                MediaItem mediaItem = editedMediaItem.mediaItem;
                long j4 = j - j2;
                useDefaultMediaSourceFactory.add((MediaSource) new ClippingMediaSource(new DefaultMediaSourceFactory(this.context).createMediaSource(mediaItem), mediaItem.clippingConfiguration.startPositionUs, mediaItem.clippingConfiguration.startPositionUs + j4), Util.usToMs(j4));
                break;
            }
            useDefaultMediaSourceFactory.add(editedMediaItem.mediaItem, Util.usToMs(presentationDurationUs));
            i = (i + 1) % editedMediaItemSequence.editedMediaItems.size();
            j2 = j3;
        }
        exoPlayer.setMediaSource(useDefaultMediaSourceFactory.build());
    }

    private MediaSource wrapWithVideoEffectsBasedMediaSources(MediaSource mediaSource, ImmutableList<Effect> immutableList, long j) {
        UnmodifiableIterator<Effect> it = immutableList.iterator();
        while (it.hasNext()) {
            Effect next = it.next();
            if (next instanceof InactiveTimestampAdjustment) {
                mediaSource = wrapWithSpeedChangingMediaSource(mediaSource, ((InactiveTimestampAdjustment) next).speedProvider, j);
            }
        }
        return mediaSource;
    }

    private MediaSource wrapWithSpeedChangingMediaSource(MediaSource mediaSource, SpeedProvider speedProvider, long j) {
        final SpeedProvider speedProvider2 = speedProvider;
        final long j2 = j;
        return new WrappingMediaSource(mediaSource) {
            public MediaPeriod createPeriod(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator, long j) {
                return new SpeedProviderMediaPeriod(super.createPeriod(mediaPeriodId, allocator, j), speedProvider2);
            }

            public void releasePeriod(MediaPeriod mediaPeriod) {
                super.releasePeriod(((SpeedProviderMediaPeriod) mediaPeriod).mediaPeriod);
            }

            /* access modifiers changed from: protected */
            public void onChildSourceInfoRefreshed(final Timeline timeline) {
                super.onChildSourceInfoRefreshed(new ForwardingTimeline(timeline) {
                    public Timeline.Window getWindow(int i, Timeline.Window window, long j) {
                        Timeline.Window window2 = timeline.getWindow(i, window, j);
                        window2.durationUs = j2;
                        return window2;
                    }

                    public Timeline.Period getPeriod(int i, Timeline.Period period, boolean z) {
                        Timeline.Period period2 = timeline.getPeriod(i, period, z);
                        period2.durationUs = j2;
                        return period2;
                    }
                });
            }
        };
    }

    /* access modifiers changed from: private */
    public long getContentPositionMs() {
        return this.players.isEmpty() ? C.TIME_UNSET : this.players.get(0).getContentPosition();
    }

    /* access modifiers changed from: private */
    public long getBufferedPositionMs() {
        if (this.players.isEmpty()) {
            return 0;
        }
        long j = 2147483647L;
        for (int i = 0; i < this.players.size(); i++) {
            j = Math.min(j, this.players.get(i).getBufferedPosition());
        }
        return j;
    }

    /* access modifiers changed from: private */
    public long getTotalBufferedDurationMs() {
        if (this.players.isEmpty()) {
            return 0;
        }
        long j = 2147483647L;
        for (int i = 0; i < this.players.size(); i++) {
            j = Math.min(j, this.players.get(i).getTotalBufferedDuration());
        }
        return j;
    }

    private boolean getRenderedFirstFrameAndReset() {
        boolean z = this.renderedFirstFrame;
        this.renderedFirstFrame = false;
        return z;
    }

    /* access modifiers changed from: private */
    public void maybeUpdatePlaybackError(String str, Exception exc, int i) {
        if (this.playbackException == null) {
            this.playbackException = new PlaybackException(str, exc, i);
            for (int i2 = 0; i2 < this.players.size(); i2++) {
                this.players.get(i2).stop();
            }
            invalidateState();
            return;
        }
        Log.w(TAG, str, exc);
    }

    private void setVideoSurfaceHolderInternal(SurfaceHolder surfaceHolder2) {
        removeSurfaceCallbacks();
        this.surfaceHolder = surfaceHolder2;
        surfaceHolder2.addCallback(this);
        Surface surface = surfaceHolder2.getSurface();
        if (surface == null || !surface.isValid()) {
            clearVideoSurfaceInternal();
            return;
        }
        Size size = new Size(surfaceHolder2.getSurfaceFrame().width(), surfaceHolder2.getSurfaceFrame().height());
        this.videoOutputSize = size;
        setVideoSurfaceInternal(surface, size);
    }

    private void setVideoSurfaceInternal(Surface surface, Size size) {
        this.displaySurface = surface;
        maybeSetOutputSurfaceInfo(size.getWidth(), size.getHeight());
    }

    private void maybeSetOutputSurfaceInfo(int i, int i2) {
        CompositionPlayerInternal compositionPlayerInternal2;
        Surface surface = this.displaySurface;
        if (i != 0 && i2 != 0 && surface != null && (compositionPlayerInternal2 = this.compositionPlayerInternal) != null) {
            compositionPlayerInternal2.setOutputSurfaceInfo(surface, new Size(i, i2));
        }
    }

    private void clearVideoSurfaceInternal() {
        this.displaySurface = null;
        CompositionPlayerInternal compositionPlayerInternal2 = this.compositionPlayerInternal;
        if (compositionPlayerInternal2 != null) {
            compositionPlayerInternal2.clearOutputSurface();
        }
    }

    private void removeSurfaceCallbacks() {
        SurfaceHolder surfaceHolder2 = this.surfaceHolder;
        if (surfaceHolder2 != null) {
            surfaceHolder2.removeCallback(this);
            this.surfaceHolder = null;
        }
    }

    /* access modifiers changed from: private */
    public void repeatCompositionPlayback() {
        this.repeatingCompositionSeekInProgress = true;
        seekTo(getCurrentMediaItemIndex(), C.TIME_UNSET, 8, true);
    }

    private ImmutableList<SimpleBasePlayer.MediaItemData> createPlaylist() {
        Assertions.checkNotNull(Boolean.valueOf(this.compositionDurationUs != C.TIME_UNSET));
        return ImmutableList.of(new SimpleBasePlayer.MediaItemData.Builder((Object) "CompositionTimeline").setMediaItem(MediaItem.EMPTY).setDurationUs(this.compositionDurationUs).build());
    }

    private static long getCompositionDurationUs(Composition composition2) {
        Assertions.checkState(!composition2.sequences.isEmpty());
        return getSequenceDurationUs((EditedMediaItemSequence) composition2.sequences.get(0));
    }

    private static long getSequenceDurationUs(EditedMediaItemSequence editedMediaItemSequence) {
        boolean z = false;
        long j = 0;
        for (int i = 0; i < editedMediaItemSequence.editedMediaItems.size(); i++) {
            j += ((EditedMediaItem) editedMediaItemSequence.editedMediaItems.get(i)).getPresentationDurationUs();
        }
        if (j > 0) {
            z = true;
        }
        Assertions.checkState(z, String.valueOf(j));
        return j;
    }

    private static final class CompositionFrameTimingEvaluator implements VideoFrameReleaseControl.FrameTimingEvaluator {
        private static final long FRAME_LATE_THRESHOLD_US = -30000;
        private static final long FRAME_RELEASE_THRESHOLD_US = 100000;

        public boolean shouldDropFrame(long j, long j2, boolean z) {
            return j < FRAME_LATE_THRESHOLD_US && !z;
        }

        public boolean shouldForceReleaseFrame(long j, long j2) {
            return j < FRAME_LATE_THRESHOLD_US && j2 > 100000;
        }

        public boolean shouldIgnoreFrame(long j, long j2, long j3, boolean z, boolean z2) {
            return false;
        }

        private CompositionFrameTimingEvaluator() {
        }
    }

    private final class PlayerListener implements Player.Listener {
        private final int playerIndex;

        public PlayerListener(int i) {
            this.playerIndex = i;
        }

        public void onEvents(Player player, Player.Events events) {
            if (events.containsAny(CompositionPlayer.SUPPORTED_LISTENER_EVENTS)) {
                CompositionPlayer.this.invalidateState();
            }
        }

        public void onPlayWhenReadyChanged(boolean z, int i) {
            int unused = CompositionPlayer.this.playWhenReadyChangeReason = i;
            if (i == 5 && CompositionPlayer.this.repeatMode != 0 && this.playerIndex == 0) {
                CompositionPlayer.this.repeatCompositionPlayback();
            }
        }

        public void onPlayerError(PlaybackException playbackException) {
            CompositionPlayer.this.maybeUpdatePlaybackError("error from player " + this.playerIndex, playbackException, playbackException.errorCode);
        }
    }

    private static final class CompositionTrackSelector extends DefaultTrackSelector {
        private static final String SILENCE_AUDIO_TRACK_GROUP_ID = "1:";
        private final boolean disableVideoPlayback;
        private final int sequenceIndex;

        public CompositionTrackSelector(Context context, int i, boolean z) {
            super(context);
            this.sequenceIndex = i;
            this.disableVideoPlayback = z;
        }

        /* access modifiers changed from: protected */
        public Pair<ExoTrackSelection.Definition, Integer> selectAudioTrack(MappingTrackSelector.MappedTrackInfo mappedTrackInfo, int[][][] iArr, int[] iArr2, DefaultTrackSelector.Parameters parameters) throws ExoPlaybackException {
            if (this.sequenceIndex == 0) {
                int i = 0;
                while (true) {
                    if (i >= mappedTrackInfo.getRendererCount()) {
                        i = -1;
                        break;
                    } else if (mappedTrackInfo.getRendererType(i) == 1) {
                        break;
                    } else {
                        i++;
                    }
                }
                Assertions.checkState(i != -1);
                TrackGroupArray trackGroups = mappedTrackInfo.getTrackGroups(i);
                if (trackGroups.length > 1) {
                    boolean z = false;
                    int i2 = -1;
                    for (int i3 = 0; i3 < trackGroups.length; i3++) {
                        if (trackGroups.get(i3).id.startsWith(SILENCE_AUDIO_TRACK_GROUP_ID)) {
                            i2 = i3;
                        } else {
                            for (int i4 = 0; i4 < trackGroups.get(i3).length; i4++) {
                                z |= RendererCapabilities.getFormatSupport(iArr[i][i3][i4]) == 4;
                            }
                        }
                    }
                    Assertions.checkState(i2 != -1);
                    if (z) {
                        iArr[i][trackGroups.length - 1][0] = RendererCapabilities.create(0);
                    }
                }
            }
            return super.selectAudioTrack(mappedTrackInfo, iArr, iArr2, parameters);
        }

        /* access modifiers changed from: protected */
        public Pair<ExoTrackSelection.Definition, Integer> selectVideoTrack(MappingTrackSelector.MappedTrackInfo mappedTrackInfo, int[][][] iArr, int[] iArr2, DefaultTrackSelector.Parameters parameters) throws ExoPlaybackException {
            if (this.disableVideoPlayback) {
                return null;
            }
            return super.selectVideoTrack(mappedTrackInfo, iArr, iArr2, parameters);
        }

        /* access modifiers changed from: protected */
        public Pair<ExoTrackSelection.Definition, Integer> selectImageTrack(MappingTrackSelector.MappedTrackInfo mappedTrackInfo, int[][][] iArr, DefaultTrackSelector.Parameters parameters) throws ExoPlaybackException {
            if (this.disableVideoPlayback) {
                return null;
            }
            return super.selectImageTrack(mappedTrackInfo, iArr, parameters);
        }
    }
}
