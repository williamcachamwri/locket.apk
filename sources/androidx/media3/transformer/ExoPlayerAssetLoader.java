package androidx.media3.transformer;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import androidx.media3.common.C;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.Player;
import androidx.media3.common.Timeline;
import androidx.media3.common.TrackSelectionParameters;
import androidx.media3.common.Tracks;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.DefaultLoadControl;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.exoplayer.Renderer;
import androidx.media3.exoplayer.RenderersFactory;
import androidx.media3.exoplayer.audio.AudioRendererEventListener;
import androidx.media3.exoplayer.metadata.MetadataOutput;
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.text.TextOutput;
import androidx.media3.exoplayer.trackselection.DefaultTrackSelector;
import androidx.media3.exoplayer.video.VideoRendererEventListener;
import androidx.media3.extractor.DefaultExtractorsFactory;
import androidx.media3.extractor.ExtractorsFactory;
import androidx.media3.transformer.AssetLoader;
import androidx.media3.transformer.Codec;
import com.google.common.collect.ImmutableMap;
import java.util.ArrayList;

public final class ExoPlayerAssetLoader implements AssetLoader {
    private static final long EMULATOR_RELEASE_TIMEOUT_MS = 5000;
    private static final String TAG = "ExoPlayerAssetLoader";
    /* access modifiers changed from: private */
    public final Context context;
    private final CapturingDecoderFactory decoderFactory;
    /* access modifiers changed from: private */
    public final EditedMediaItem editedMediaItem;
    /* access modifiers changed from: private */
    public final ExoPlayer player;
    /* access modifiers changed from: private */
    public int progressState;

    public static final class Factory implements AssetLoader.Factory {
        private final Clock clock;
        private final Context context;
        private final Codec.DecoderFactory decoderFactory;
        private final MediaSource.Factory mediaSourceFactory;

        public Factory(Context context2, Codec.DecoderFactory decoderFactory2, Clock clock2) {
            this.context = context2;
            this.decoderFactory = decoderFactory2;
            this.clock = clock2;
            this.mediaSourceFactory = null;
        }

        public Factory(Context context2, Codec.DecoderFactory decoderFactory2, Clock clock2, MediaSource.Factory factory) {
            this.context = context2;
            this.decoderFactory = decoderFactory2;
            this.clock = clock2;
            this.mediaSourceFactory = factory;
        }

        public AssetLoader createAssetLoader(EditedMediaItem editedMediaItem, Looper looper, AssetLoader.Listener listener, AssetLoader.CompositionSettings compositionSettings) {
            DefaultMediaSourceFactory defaultMediaSourceFactory;
            MediaSource.Factory factory = this.mediaSourceFactory;
            if (factory == null) {
                DefaultExtractorsFactory defaultExtractorsFactory = new DefaultExtractorsFactory();
                if (editedMediaItem.flattenForSlowMotion) {
                    defaultExtractorsFactory.setMp4ExtractorFlags(4);
                }
                defaultMediaSourceFactory = new DefaultMediaSourceFactory(this.context, (ExtractorsFactory) defaultExtractorsFactory);
            } else {
                EditedMediaItem editedMediaItem2 = editedMediaItem;
                defaultMediaSourceFactory = factory;
            }
            return new ExoPlayerAssetLoader(this.context, editedMediaItem, defaultMediaSourceFactory, this.decoderFactory, compositionSettings.hdrMode, looper, listener, this.clock);
        }
    }

    private ExoPlayerAssetLoader(Context context2, EditedMediaItem editedMediaItem2, MediaSource.Factory factory, Codec.DecoderFactory decoderFactory2, int i, Looper looper, AssetLoader.Listener listener, Clock clock) {
        Context context3 = context2;
        EditedMediaItem editedMediaItem3 = editedMediaItem2;
        Codec.DecoderFactory decoderFactory3 = decoderFactory2;
        Clock clock2 = clock;
        this.context = context3;
        this.editedMediaItem = editedMediaItem3;
        CapturingDecoderFactory capturingDecoderFactory = new CapturingDecoderFactory(decoderFactory3);
        this.decoderFactory = capturingDecoderFactory;
        DefaultTrackSelector defaultTrackSelector = new DefaultTrackSelector(context3);
        defaultTrackSelector.setParameters((TrackSelectionParameters) new DefaultTrackSelector.Parameters.Builder(context3).setForceHighestSupportedBitrate(true).setConstrainAudioChannelCountToDeviceCapabilities(false).build());
        DefaultLoadControl build = new DefaultLoadControl.Builder().setBufferDurationsMs(50000, 50000, 250, 500).build();
        RenderersFactoryImpl renderersFactoryImpl = r5;
        RenderersFactoryImpl renderersFactoryImpl2 = new RenderersFactoryImpl(editedMediaItem3.removeAudio, editedMediaItem3.removeVideo, editedMediaItem3.flattenForSlowMotion, capturingDecoderFactory, i, listener);
        ExoPlayer.Builder releaseTimeoutMs = new ExoPlayer.Builder(context3, (RenderersFactory) renderersFactoryImpl).setMediaSourceFactory(factory).setTrackSelector(defaultTrackSelector).setLoadControl(build).setLooper(looper).setUsePlatformDiagnostics(false).setReleaseTimeoutMs(getReleaseTimeoutMs());
        if (decoderFactory3 instanceof DefaultDecoderFactory) {
            releaseTimeoutMs.experimentalSetDynamicSchedulingEnabled(((DefaultDecoderFactory) decoderFactory3).isDynamicSchedulingEnabled());
        }
        if (clock2 != Clock.DEFAULT) {
            releaseTimeoutMs.setClock(clock2);
        }
        ExoPlayer build2 = releaseTimeoutMs.build();
        this.player = build2;
        build2.addListener(new PlayerListener(listener));
        this.progressState = 0;
    }

    public void start() {
        this.player.setMediaItem(this.editedMediaItem.mediaItem);
        this.player.prepare();
        this.progressState = 1;
    }

    public int getProgress(ProgressHolder progressHolder) {
        if (this.progressState == 2) {
            progressHolder.progress = Math.min((int) ((this.player.getCurrentPosition() * 100) / this.player.getDuration()), 99);
        }
        return this.progressState;
    }

    public ImmutableMap<Integer, String> getDecoderNames() {
        ImmutableMap.Builder builder = new ImmutableMap.Builder();
        String audioDecoderName = this.decoderFactory.getAudioDecoderName();
        if (audioDecoderName != null) {
            builder.put(1, audioDecoderName);
        }
        String videoDecoderName = this.decoderFactory.getVideoDecoderName();
        if (videoDecoderName != null) {
            builder.put(2, videoDecoderName);
        }
        return builder.buildOrThrow();
    }

    public void release() {
        this.player.release();
        this.progressState = 0;
    }

    private static final class RenderersFactoryImpl implements RenderersFactory {
        private final AssetLoader.Listener assetLoaderListener;
        private final Codec.DecoderFactory decoderFactory;
        private final boolean flattenForSlowMotion;
        private final int hdrMode;
        private final TransformerMediaClock mediaClock = new TransformerMediaClock();
        private final boolean removeAudio;
        private final boolean removeVideo;

        public RenderersFactoryImpl(boolean z, boolean z2, boolean z3, Codec.DecoderFactory decoderFactory2, int i, AssetLoader.Listener listener) {
            this.removeAudio = z;
            this.removeVideo = z2;
            this.flattenForSlowMotion = z3;
            this.decoderFactory = decoderFactory2;
            this.hdrMode = i;
            this.assetLoaderListener = listener;
        }

        public Renderer[] createRenderers(Handler handler, VideoRendererEventListener videoRendererEventListener, AudioRendererEventListener audioRendererEventListener, TextOutput textOutput, MetadataOutput metadataOutput) {
            ArrayList arrayList = new ArrayList();
            if (!this.removeAudio) {
                arrayList.add(new ExoAssetLoaderAudioRenderer(this.decoderFactory, this.mediaClock, this.assetLoaderListener));
            }
            if (!this.removeVideo) {
                arrayList.add(new ExoAssetLoaderVideoRenderer(this.flattenForSlowMotion, this.decoderFactory, this.hdrMode, this.mediaClock, this.assetLoaderListener));
            }
            return (Renderer[]) arrayList.toArray(new Renderer[arrayList.size()]);
        }
    }

    private final class PlayerListener implements Player.Listener {
        private final AssetLoader.Listener assetLoaderListener;

        public PlayerListener(AssetLoader.Listener listener) {
            this.assetLoaderListener = listener;
        }

        public void onTimelineChanged(Timeline timeline, int i) {
            int i2;
            try {
                if (ExoPlayerAssetLoader.this.progressState == 1) {
                    Timeline.Window window = new Timeline.Window();
                    timeline.getWindow(0, window);
                    if (!window.isPlaceholder) {
                        long j = window.durationUs;
                        ExoPlayerAssetLoader exoPlayerAssetLoader = ExoPlayerAssetLoader.this;
                        if (j > 0) {
                            if (j != C.TIME_UNSET) {
                                i2 = 2;
                                int unused = exoPlayerAssetLoader.progressState = i2;
                                this.assetLoaderListener.onDurationUs(window.durationUs);
                            }
                        }
                        i2 = 3;
                        int unused2 = exoPlayerAssetLoader.progressState = i2;
                        this.assetLoaderListener.onDurationUs(window.durationUs);
                    }
                }
            } catch (RuntimeException e) {
                this.assetLoaderListener.onError(ExportException.createForAssetLoader(e, 1000));
            }
        }

        public void onTracksChanged(Tracks tracks) {
            try {
                int isTypeSelected = tracks.isTypeSelected(1);
                if (tracks.isTypeSelected(2)) {
                    isTypeSelected++;
                }
                ExoPlayerAssetLoader.maybeWarnUnsupportedTrackTypes(tracks);
                if (isTypeSelected > 0) {
                    this.assetLoaderListener.onTrackCount(isTypeSelected);
                    ExoPlayerAssetLoader.this.player.play();
                    return;
                }
                String str = "The asset loader has no audio or video track to output.";
                if (TransformerUtil.isImage(ExoPlayerAssetLoader.this.context, ExoPlayerAssetLoader.this.editedMediaItem.mediaItem)) {
                    str = "The asset loader has no audio or video track to output. Try setting an image duration on input image MediaItems.";
                }
                this.assetLoaderListener.onError(ExportException.createForAssetLoader(new IllegalStateException(str), 1001));
            } catch (RuntimeException e) {
                this.assetLoaderListener.onError(ExportException.createForAssetLoader(e, 1000));
            }
        }

        public void onPlayerError(PlaybackException playbackException) {
            this.assetLoaderListener.onError(ExportException.createForAssetLoader(playbackException, ((Integer) Assertions.checkNotNull(ExportException.NAME_TO_ERROR_CODE.getOrDefault(playbackException.getErrorCodeName(), 1000))).intValue()));
        }
    }

    /* access modifiers changed from: private */
    public static void maybeWarnUnsupportedTrackTypes(Tracks tracks) {
        for (int i = 0; i < tracks.getGroups().size(); i++) {
            int type = ((Tracks.Group) tracks.getGroups().get(i)).getType();
            if (!(type == 1 || type == 2)) {
                Log.w(TAG, "Unsupported track type: " + type);
            }
        }
    }

    private static long getReleaseTimeoutMs() {
        return Util.isRunningOnEmulator() ? 5000 : 500;
    }
}
