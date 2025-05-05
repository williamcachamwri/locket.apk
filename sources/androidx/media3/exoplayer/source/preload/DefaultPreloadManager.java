package androidx.media3.exoplayer.source.preload;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import androidx.media3.common.C;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.DefaultRendererCapabilitiesList;
import androidx.media3.exoplayer.DefaultRenderersFactory;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.exoplayer.ExoPlayer$Builder$$ExternalSyntheticLambda10;
import androidx.media3.exoplayer.LoadControl;
import androidx.media3.exoplayer.PlaybackLooperProvider;
import androidx.media3.exoplayer.RendererCapabilitiesList;
import androidx.media3.exoplayer.RenderersFactory;
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.preload.BasePreloadManager;
import androidx.media3.exoplayer.source.preload.PreloadMediaSource;
import androidx.media3.exoplayer.source.preload.TargetPreloadStatusControl;
import androidx.media3.exoplayer.trackselection.TrackSelector;
import androidx.media3.exoplayer.upstream.Allocator;
import androidx.media3.exoplayer.upstream.BandwidthMeter;
import com.google.common.base.Predicate;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Comparator;

public final class DefaultPreloadManager extends BasePreloadManager<Integer> {
    private final boolean deprecatedConstructorCalled;
    private final Handler preloadHandler;
    private final PlaybackLooperProvider preloadLooperProvider;
    private final PreloadMediaSource.Factory preloadMediaSourceFactory;
    private final RendererCapabilitiesList rendererCapabilitiesList;
    private final TrackSelector trackSelector;

    static /* synthetic */ void lambda$new$0() {
    }

    public static final class Builder extends BasePreloadManager.BuilderBase<Integer> {
        /* access modifiers changed from: private */
        public Supplier<BandwidthMeter> bandwidthMeterSupplier;
        private boolean buildCalled;
        private boolean buildExoPlayerCalled;
        /* access modifiers changed from: private */
        public final Context context;
        /* access modifiers changed from: private */
        public Supplier<LoadControl> loadControlSupplier;
        /* access modifiers changed from: private */
        public PlaybackLooperProvider preloadLooperProvider = new PlaybackLooperProvider();
        /* access modifiers changed from: private */
        public Supplier<RenderersFactory> renderersFactorySupplier;
        /* access modifiers changed from: private */
        public TrackSelector.Factory trackSelectorFactory = new DefaultPreloadManager$Builder$$ExternalSyntheticLambda4();

        static /* synthetic */ BandwidthMeter lambda$setBandwidthMeter$6(BandwidthMeter bandwidthMeter) {
            return bandwidthMeter;
        }

        static /* synthetic */ LoadControl lambda$setLoadControl$5(LoadControl loadControl) {
            return loadControl;
        }

        static /* synthetic */ MediaSource.Factory lambda$setMediaSourceFactory$3(MediaSource.Factory factory) {
            return factory;
        }

        static /* synthetic */ RenderersFactory lambda$setRenderersFactory$4(RenderersFactory renderersFactory) {
            return renderersFactory;
        }

        public Builder(Context context2, TargetPreloadStatusControl<Integer> targetPreloadStatusControl) {
            super(new RankingDataComparator(), targetPreloadStatusControl, Suppliers.memoize(new DefaultPreloadManager$Builder$$ExternalSyntheticLambda3(context2)));
            this.context = context2;
            this.bandwidthMeterSupplier = new DefaultPreloadManager$Builder$$ExternalSyntheticLambda5(context2);
            this.renderersFactorySupplier = Suppliers.memoize(new DefaultPreloadManager$Builder$$ExternalSyntheticLambda6(context2));
            this.loadControlSupplier = Suppliers.memoize(new ExoPlayer$Builder$$ExternalSyntheticLambda10());
        }

        static /* synthetic */ MediaSource.Factory lambda$new$0(Context context2) {
            return new DefaultMediaSourceFactory(context2);
        }

        static /* synthetic */ RenderersFactory lambda$new$2(Context context2) {
            return new DefaultRenderersFactory(context2);
        }

        public Builder setMediaSourceFactory(MediaSource.Factory factory) {
            Assertions.checkState(!this.buildCalled && !this.buildExoPlayerCalled);
            this.mediaSourceFactorySupplier = new DefaultPreloadManager$Builder$$ExternalSyntheticLambda0(factory);
            return this;
        }

        public Builder setRenderersFactory(RenderersFactory renderersFactory) {
            Assertions.checkState(!this.buildCalled && !this.buildExoPlayerCalled);
            this.renderersFactorySupplier = new DefaultPreloadManager$Builder$$ExternalSyntheticLambda7(renderersFactory);
            return this;
        }

        public Builder setTrackSelectorFactory(TrackSelector.Factory factory) {
            Assertions.checkState(!this.buildCalled && !this.buildExoPlayerCalled);
            this.trackSelectorFactory = factory;
            return this;
        }

        public Builder setLoadControl(LoadControl loadControl) {
            Assertions.checkState(!this.buildCalled && !this.buildExoPlayerCalled);
            this.loadControlSupplier = new DefaultPreloadManager$Builder$$ExternalSyntheticLambda2(loadControl);
            return this;
        }

        public Builder setBandwidthMeter(BandwidthMeter bandwidthMeter) {
            Assertions.checkState(!this.buildCalled && !this.buildExoPlayerCalled);
            this.bandwidthMeterSupplier = new DefaultPreloadManager$Builder$$ExternalSyntheticLambda1(bandwidthMeter);
            return this;
        }

        public Builder setPreloadLooper(Looper looper) {
            Assertions.checkState(!this.buildCalled && !this.buildExoPlayerCalled);
            this.preloadLooperProvider = new PlaybackLooperProvider(looper);
            return this;
        }

        public ExoPlayer buildExoPlayer() {
            return buildExoPlayer(new ExoPlayer.Builder(this.context));
        }

        public ExoPlayer buildExoPlayer(ExoPlayer.Builder builder) {
            this.buildExoPlayerCalled = true;
            return builder.setMediaSourceFactory((MediaSource.Factory) this.mediaSourceFactorySupplier.get()).setBandwidthMeter(this.bandwidthMeterSupplier.get()).setRenderersFactory(this.renderersFactorySupplier.get()).setLoadControl(this.loadControlSupplier.get()).setPlaybackLooperProvider(this.preloadLooperProvider).setTrackSelector(this.trackSelectorFactory.createTrackSelector(this.context)).build();
        }

        public DefaultPreloadManager build() {
            Assertions.checkState(!this.buildCalled);
            this.buildCalled = true;
            return new DefaultPreloadManager(this);
        }
    }

    public static class Status implements TargetPreloadStatusControl.PreloadStatus {
        public static final int STAGE_LOADED_FOR_DURATION_MS = 2;
        public static final int STAGE_SOURCE_PREPARED = 0;
        public static final int STAGE_TRACKS_SELECTED = 1;
        private final int stage;
        private final long value;

        @Documented
        @Target({ElementType.TYPE_USE})
        @Retention(RetentionPolicy.SOURCE)
        public @interface Stage {
        }

        public Status(int i, long j) {
            this.stage = i;
            this.value = j;
        }

        public Status(int i) {
            this(i, C.TIME_UNSET);
        }

        public int getStage() {
            return this.stage;
        }

        public long getValue() {
            return this.value;
        }
    }

    private DefaultPreloadManager(Builder builder) {
        super(new RankingDataComparator(), builder.targetPreloadStatusControl, (MediaSource.Factory) builder.mediaSourceFactorySupplier.get());
        DefaultRendererCapabilitiesList createRendererCapabilitiesList = new DefaultRendererCapabilitiesList.Factory((RenderersFactory) builder.renderersFactorySupplier.get()).createRendererCapabilitiesList();
        this.rendererCapabilitiesList = createRendererCapabilitiesList;
        PlaybackLooperProvider access$200 = builder.preloadLooperProvider;
        this.preloadLooperProvider = access$200;
        TrackSelector createTrackSelector = builder.trackSelectorFactory.createTrackSelector(builder.context);
        this.trackSelector = createTrackSelector;
        BandwidthMeter bandwidthMeter = (BandwidthMeter) builder.bandwidthMeterSupplier.get();
        createTrackSelector.init(new DefaultPreloadManager$$ExternalSyntheticLambda0(), bandwidthMeter);
        Looper obtainLooper = access$200.obtainLooper();
        this.preloadMediaSourceFactory = new PreloadMediaSource.Factory((MediaSource.Factory) builder.mediaSourceFactorySupplier.get(), new SourcePreloadControl(), createTrackSelector, bandwidthMeter, createRendererCapabilitiesList.getRendererCapabilities(), ((LoadControl) builder.loadControlSupplier.get()).getAllocator(), obtainLooper);
        this.preloadHandler = Util.createHandler(obtainLooper, (Handler.Callback) null);
        this.deprecatedConstructorCalled = false;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    @Deprecated
    public DefaultPreloadManager(TargetPreloadStatusControl<Integer> targetPreloadStatusControl, MediaSource.Factory factory, TrackSelector trackSelector2, BandwidthMeter bandwidthMeter, RendererCapabilitiesList.Factory factory2, Allocator allocator, Looper looper) {
        super(new RankingDataComparator(), targetPreloadStatusControl, factory);
        TargetPreloadStatusControl<Integer> targetPreloadStatusControl2 = targetPreloadStatusControl;
        RendererCapabilitiesList createRendererCapabilitiesList = factory2.createRendererCapabilitiesList();
        this.rendererCapabilitiesList = createRendererCapabilitiesList;
        PlaybackLooperProvider playbackLooperProvider = new PlaybackLooperProvider(looper);
        this.preloadLooperProvider = playbackLooperProvider;
        TrackSelector trackSelector3 = trackSelector2;
        this.trackSelector = trackSelector3;
        Looper obtainLooper = playbackLooperProvider.obtainLooper();
        this.preloadMediaSourceFactory = new PreloadMediaSource.Factory(factory, new SourcePreloadControl(), trackSelector3, bandwidthMeter, createRendererCapabilitiesList.getRendererCapabilities(), allocator, obtainLooper);
        this.preloadHandler = Util.createHandler(obtainLooper, (Handler.Callback) null);
        this.deprecatedConstructorCalled = true;
    }

    public void setCurrentPlayingIndex(int i) {
        ((RankingDataComparator) this.rankingDataComparator).currentPlayingIndex = i;
    }

    public MediaSource createMediaSourceForPreloading(MediaSource mediaSource) {
        return this.preloadMediaSourceFactory.createMediaSource(mediaSource);
    }

    /* access modifiers changed from: protected */
    public void preloadSourceInternal(MediaSource mediaSource, long j) {
        Assertions.checkArgument(mediaSource instanceof PreloadMediaSource);
        ((PreloadMediaSource) mediaSource).preload(j);
    }

    /* access modifiers changed from: protected */
    public void clearSourceInternal(MediaSource mediaSource) {
        Assertions.checkArgument(mediaSource instanceof PreloadMediaSource);
        ((PreloadMediaSource) mediaSource).clear();
    }

    /* access modifiers changed from: protected */
    public void releaseSourceInternal(MediaSource mediaSource) {
        Assertions.checkArgument(mediaSource instanceof PreloadMediaSource);
        ((PreloadMediaSource) mediaSource).releasePreloadMediaSource();
    }

    /* access modifiers changed from: protected */
    public void releaseInternal() {
        this.preloadHandler.post(new DefaultPreloadManager$$ExternalSyntheticLambda1(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$releaseInternal$1$androidx-media3-exoplayer-source-preload-DefaultPreloadManager  reason: not valid java name */
    public /* synthetic */ void m875lambda$releaseInternal$1$androidxmedia3exoplayersourcepreloadDefaultPreloadManager() {
        this.rendererCapabilitiesList.release();
        if (!this.deprecatedConstructorCalled) {
            this.trackSelector.release();
        }
        this.preloadLooperProvider.releaseLooper();
    }

    private static final class RankingDataComparator implements Comparator<Integer> {
        public int currentPlayingIndex = -1;

        public int compare(Integer num, Integer num2) {
            return Integer.compare(Math.abs(num.intValue() - this.currentPlayingIndex), Math.abs(num2.intValue() - this.currentPlayingIndex));
        }
    }

    private final class SourcePreloadControl implements PreloadMediaSource.PreloadControl {
        private SourcePreloadControl() {
        }

        public boolean onSourcePrepared(PreloadMediaSource preloadMediaSource) {
            return continueOrCompletePreloading(preloadMediaSource, new DefaultPreloadManager$SourcePreloadControl$$ExternalSyntheticLambda0(), true);
        }

        static /* synthetic */ boolean lambda$onSourcePrepared$0(Status status) {
            return status.getStage() > 0;
        }

        public boolean onTracksSelected(PreloadMediaSource preloadMediaSource) {
            return continueOrCompletePreloading(preloadMediaSource, new DefaultPreloadManager$SourcePreloadControl$$ExternalSyntheticLambda2(), false);
        }

        static /* synthetic */ boolean lambda$onTracksSelected$1(Status status) {
            return status.getStage() > 1;
        }

        public boolean onContinueLoadingRequested(PreloadMediaSource preloadMediaSource, long j) {
            return continueOrCompletePreloading(preloadMediaSource, new DefaultPreloadManager$SourcePreloadControl$$ExternalSyntheticLambda1(j), false);
        }

        static /* synthetic */ boolean lambda$onContinueLoadingRequested$2(long j, Status status) {
            return status.getStage() == 2 && status.getValue() > Util.usToMs(j);
        }

        public void onUsedByPlayer(PreloadMediaSource preloadMediaSource) {
            DefaultPreloadManager.this.onPreloadSkipped(preloadMediaSource);
        }

        public void onLoadedToTheEndOfSource(PreloadMediaSource preloadMediaSource) {
            DefaultPreloadManager.this.onPreloadCompleted(preloadMediaSource);
        }

        public void onPreloadError(PreloadException preloadException, PreloadMediaSource preloadMediaSource) {
            DefaultPreloadManager.this.onPreloadError(preloadException, preloadMediaSource);
        }

        private boolean continueOrCompletePreloading(PreloadMediaSource preloadMediaSource, Predicate<Status> predicate, boolean z) {
            TargetPreloadStatusControl.PreloadStatus targetPreloadStatus = DefaultPreloadManager.this.getTargetPreloadStatus(preloadMediaSource);
            if (targetPreloadStatus == null) {
                DefaultPreloadManager.this.onPreloadSkipped(preloadMediaSource);
                return false;
            } else if (predicate.apply((Status) Assertions.checkNotNull((Status) targetPreloadStatus))) {
                return true;
            } else {
                if (z) {
                    DefaultPreloadManager.this.clearSourceInternal(preloadMediaSource);
                }
                DefaultPreloadManager.this.onPreloadCompleted(preloadMediaSource);
                return false;
            }
        }
    }
}
