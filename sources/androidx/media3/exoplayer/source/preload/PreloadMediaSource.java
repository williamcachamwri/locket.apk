package androidx.media3.exoplayer.source.preload;

import android.os.Handler;
import android.os.Looper;
import android.util.Pair;
import androidx.media3.common.C;
import androidx.media3.common.MediaItem;
import androidx.media3.common.Timeline;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.ExoPlaybackException;
import androidx.media3.exoplayer.LoadingInfo;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.drm.DrmSessionManagerProvider;
import androidx.media3.exoplayer.source.MediaPeriod;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.WrappingMediaSource;
import androidx.media3.exoplayer.trackselection.TrackSelector;
import androidx.media3.exoplayer.trackselection.TrackSelectorResult;
import androidx.media3.exoplayer.upstream.Allocator;
import androidx.media3.exoplayer.upstream.BandwidthMeter;
import androidx.media3.exoplayer.upstream.CmcdConfiguration;
import androidx.media3.exoplayer.upstream.LoadErrorHandlingPolicy;
import java.io.IOException;
import java.util.Arrays;

public final class PreloadMediaSource extends WrappingMediaSource {
    private static final long CHECK_FOR_PRELOAD_ERROR_INTERVAL_MS = 100;
    private static final String TAG = "PreloadMediaSource";
    private final Allocator allocator;
    private final BandwidthMeter bandwidthMeter;
    private boolean onSourcePreparedNotified;
    private boolean onUsedByPlayerNotified;
    private Pair<PreloadMediaPeriod, MediaSource.MediaPeriodId> playingPreloadedMediaPeriodAndId;
    private boolean preloadCalled;
    /* access modifiers changed from: private */
    public final PreloadControl preloadControl;
    /* access modifiers changed from: private */
    public final Handler preloadHandler;
    /* access modifiers changed from: private */
    public Pair<PreloadMediaPeriod, MediaPeriodKey> preloadingMediaPeriodAndKey;
    private boolean prepareChildSourceCalled;
    private final Handler releaseHandler;
    /* access modifiers changed from: private */
    public final RendererCapabilities[] rendererCapabilities;
    private long startPositionUs;
    /* access modifiers changed from: private */
    public Timeline timeline;
    /* access modifiers changed from: private */
    public final TrackSelector trackSelector;

    public interface PreloadControl {
        boolean onContinueLoadingRequested(PreloadMediaSource preloadMediaSource, long j);

        void onLoadedToTheEndOfSource(PreloadMediaSource preloadMediaSource) {
        }

        void onPreloadError(PreloadException preloadException, PreloadMediaSource preloadMediaSource);

        boolean onSourcePrepared(PreloadMediaSource preloadMediaSource);

        boolean onTracksSelected(PreloadMediaSource preloadMediaSource);

        void onUsedByPlayer(PreloadMediaSource preloadMediaSource);
    }

    public static final class Factory implements MediaSource.Factory {
        private final Allocator allocator;
        private final BandwidthMeter bandwidthMeter;
        private final MediaSource.Factory mediaSourceFactory;
        private final PreloadControl preloadControl;
        private final Looper preloadLooper;
        private final RendererCapabilities[] rendererCapabilities;
        private final TrackSelector trackSelector;

        public Factory(MediaSource.Factory factory, PreloadControl preloadControl2, TrackSelector trackSelector2, BandwidthMeter bandwidthMeter2, RendererCapabilities[] rendererCapabilitiesArr, Allocator allocator2, Looper looper) {
            this.mediaSourceFactory = factory;
            this.preloadControl = preloadControl2;
            this.trackSelector = trackSelector2;
            this.bandwidthMeter = bandwidthMeter2;
            this.rendererCapabilities = (RendererCapabilities[]) Arrays.copyOf(rendererCapabilitiesArr, rendererCapabilitiesArr.length);
            this.allocator = allocator2;
            this.preloadLooper = looper;
        }

        public Factory setCmcdConfigurationFactory(CmcdConfiguration.Factory factory) {
            this.mediaSourceFactory.setCmcdConfigurationFactory(factory);
            return this;
        }

        public Factory setDrmSessionManagerProvider(DrmSessionManagerProvider drmSessionManagerProvider) {
            this.mediaSourceFactory.setDrmSessionManagerProvider(drmSessionManagerProvider);
            return this;
        }

        public Factory setLoadErrorHandlingPolicy(LoadErrorHandlingPolicy loadErrorHandlingPolicy) {
            this.mediaSourceFactory.setLoadErrorHandlingPolicy(loadErrorHandlingPolicy);
            return this;
        }

        public int[] getSupportedTypes() {
            return this.mediaSourceFactory.getSupportedTypes();
        }

        public PreloadMediaSource createMediaSource(MediaItem mediaItem) {
            return new PreloadMediaSource(this.mediaSourceFactory.createMediaSource(mediaItem), this.preloadControl, this.trackSelector, this.bandwidthMeter, this.rendererCapabilities, this.allocator, this.preloadLooper);
        }

        public PreloadMediaSource createMediaSource(MediaSource mediaSource) {
            return new PreloadMediaSource(mediaSource, this.preloadControl, this.trackSelector, this.bandwidthMeter, this.rendererCapabilities, this.allocator, this.preloadLooper);
        }
    }

    private PreloadMediaSource(MediaSource mediaSource, PreloadControl preloadControl2, TrackSelector trackSelector2, BandwidthMeter bandwidthMeter2, RendererCapabilities[] rendererCapabilitiesArr, Allocator allocator2, Looper looper) {
        super(mediaSource);
        this.preloadControl = preloadControl2;
        this.trackSelector = trackSelector2;
        this.bandwidthMeter = bandwidthMeter2;
        this.rendererCapabilities = rendererCapabilitiesArr;
        this.allocator = allocator2;
        this.preloadHandler = Util.createHandler(looper, (Handler.Callback) null);
        this.releaseHandler = Util.createHandler(looper, (Handler.Callback) null);
        this.startPositionUs = C.TIME_UNSET;
    }

    public void preload(long j) {
        this.preloadHandler.post(new PreloadMediaSource$$ExternalSyntheticLambda2(this, j));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$preload$0$androidx-media3-exoplayer-source-preload-PreloadMediaSource  reason: not valid java name */
    public /* synthetic */ void m878lambda$preload$0$androidxmedia3exoplayersourcepreloadPreloadMediaSource(long j) {
        this.preloadCalled = true;
        this.startPositionUs = j;
        this.onSourcePreparedNotified = false;
        if (isUsedByPlayer()) {
            onUsedByPlayer();
            return;
        }
        setPlayerId(PlayerId.UNSET);
        prepareSourceInternal(this.bandwidthMeter.getTransferListener());
        checkForPreloadError();
    }

    public void clear() {
        Util.postOrRun(this.preloadHandler, new PreloadMediaSource$$ExternalSyntheticLambda0(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$clear$1$androidx-media3-exoplayer-source-preload-PreloadMediaSource  reason: not valid java name */
    public /* synthetic */ void m876lambda$clear$1$androidxmedia3exoplayersourcepreloadPreloadMediaSource() {
        if (this.preloadingMediaPeriodAndKey != null) {
            this.mediaSource.releasePeriod(((PreloadMediaPeriod) this.preloadingMediaPeriodAndKey.first).mediaPeriod);
            this.preloadingMediaPeriodAndKey = null;
        }
    }

    /* access modifiers changed from: protected */
    public void prepareSourceInternal() {
        if (isUsedByPlayer() && !this.onUsedByPlayerNotified) {
            onUsedByPlayer();
        }
        Timeline timeline2 = this.timeline;
        if (timeline2 != null) {
            onChildSourceInfoRefreshed(timeline2);
        } else if (!this.prepareChildSourceCalled) {
            this.prepareChildSourceCalled = true;
            prepareChildSource();
        }
    }

    /* access modifiers changed from: protected */
    public void onChildSourceInfoRefreshed(Timeline timeline2) {
        this.timeline = timeline2;
        refreshSourceInfo(timeline2);
        this.preloadHandler.post(new PreloadMediaSource$$ExternalSyntheticLambda1(this, timeline2));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onChildSourceInfoRefreshed$2$androidx-media3-exoplayer-source-preload-PreloadMediaSource  reason: not valid java name */
    public /* synthetic */ void m877lambda$onChildSourceInfoRefreshed$2$androidxmedia3exoplayersourcepreloadPreloadMediaSource(Timeline timeline2) {
        if (!isUsedByPlayer() && !this.onSourcePreparedNotified) {
            this.onSourcePreparedNotified = true;
            if (!this.preloadControl.onSourcePrepared(this)) {
                stopPreloading();
                return;
            }
            Pair<Object, Long> periodPositionUs = timeline2.getPeriodPositionUs(new Timeline.Window(), new Timeline.Period(), 0, this.startPositionUs);
            createPeriod(new MediaSource.MediaPeriodId(periodPositionUs.first), this.allocator, ((Long) periodPositionUs.second).longValue()).preload(new PreloadMediaPeriodCallback(((Long) periodPositionUs.second).longValue()), ((Long) periodPositionUs.second).longValue());
        }
    }

    public PreloadMediaPeriod createPeriod(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator2, long j) {
        MediaPeriodKey mediaPeriodKey = new MediaPeriodKey(mediaPeriodId, j);
        Pair<PreloadMediaPeriod, MediaPeriodKey> pair = this.preloadingMediaPeriodAndKey;
        if (pair == null || !mediaPeriodKey.equals(pair.second)) {
            if (this.preloadingMediaPeriodAndKey != null) {
                this.mediaSource.releasePeriod(((PreloadMediaPeriod) ((Pair) Assertions.checkNotNull(this.preloadingMediaPeriodAndKey)).first).mediaPeriod);
                this.preloadingMediaPeriodAndKey = null;
            }
            PreloadMediaPeriod preloadMediaPeriod = new PreloadMediaPeriod(this.mediaSource.createPeriod(mediaPeriodId, allocator2, j));
            if (!isUsedByPlayer()) {
                this.preloadingMediaPeriodAndKey = new Pair<>(preloadMediaPeriod, mediaPeriodKey);
            }
            return preloadMediaPeriod;
        }
        PreloadMediaPeriod preloadMediaPeriod2 = (PreloadMediaPeriod) ((Pair) Assertions.checkNotNull(this.preloadingMediaPeriodAndKey)).first;
        if (isUsedByPlayer()) {
            this.preloadingMediaPeriodAndKey = null;
            this.playingPreloadedMediaPeriodAndId = new Pair<>(preloadMediaPeriod2, mediaPeriodId);
        }
        return preloadMediaPeriod2;
    }

    /* access modifiers changed from: protected */
    public MediaSource.MediaPeriodId getMediaPeriodIdForChildMediaPeriodId(MediaSource.MediaPeriodId mediaPeriodId) {
        Pair<PreloadMediaPeriod, MediaSource.MediaPeriodId> pair = this.playingPreloadedMediaPeriodAndId;
        return (pair == null || !mediaPeriodIdEqualsWithoutWindowSequenceNumber(mediaPeriodId, (MediaSource.MediaPeriodId) ((Pair) Assertions.checkNotNull(pair)).second)) ? mediaPeriodId : (MediaSource.MediaPeriodId) ((Pair) Assertions.checkNotNull(this.playingPreloadedMediaPeriodAndId)).second;
    }

    public void releasePeriod(MediaPeriod mediaPeriod) {
        PreloadMediaPeriod preloadMediaPeriod = (PreloadMediaPeriod) mediaPeriod;
        Pair<PreloadMediaPeriod, MediaPeriodKey> pair = this.preloadingMediaPeriodAndKey;
        if (pair == null || preloadMediaPeriod != ((Pair) Assertions.checkNotNull(pair)).first) {
            Pair<PreloadMediaPeriod, MediaSource.MediaPeriodId> pair2 = this.playingPreloadedMediaPeriodAndId;
            if (pair2 != null && preloadMediaPeriod == ((Pair) Assertions.checkNotNull(pair2)).first) {
                this.playingPreloadedMediaPeriodAndId = null;
            }
        } else {
            this.preloadingMediaPeriodAndKey = null;
        }
        this.mediaSource.releasePeriod(preloadMediaPeriod.mediaPeriod);
    }

    /* access modifiers changed from: protected */
    public void releaseSourceInternal() {
        if (!isUsedByPlayer()) {
            this.onUsedByPlayerNotified = false;
            if (!this.preloadCalled) {
                this.timeline = null;
                this.prepareChildSourceCalled = false;
                super.releaseSourceInternal();
            }
        }
    }

    public void releasePreloadMediaSource() {
        this.releaseHandler.post(new PreloadMediaSource$$ExternalSyntheticLambda3(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$releasePreloadMediaSource$3$androidx-media3-exoplayer-source-preload-PreloadMediaSource  reason: not valid java name */
    public /* synthetic */ void m879lambda$releasePreloadMediaSource$3$androidxmedia3exoplayersourcepreloadPreloadMediaSource() {
        this.preloadCalled = false;
        this.startPositionUs = C.TIME_UNSET;
        this.onSourcePreparedNotified = false;
        if (this.preloadingMediaPeriodAndKey != null) {
            this.mediaSource.releasePeriod(((PreloadMediaPeriod) this.preloadingMediaPeriodAndKey.first).mediaPeriod);
            this.preloadingMediaPeriodAndKey = null;
        }
        releaseSourceInternal();
        this.preloadHandler.removeCallbacksAndMessages((Object) null);
        this.releaseHandler.removeCallbacksAndMessages((Object) null);
    }

    /* access modifiers changed from: private */
    public boolean isUsedByPlayer() {
        return prepareSourceCalled();
    }

    private void onUsedByPlayer() {
        this.preloadControl.onUsedByPlayer(this);
        stopPreloading();
        this.onUsedByPlayerNotified = true;
    }

    /* access modifiers changed from: private */
    public void checkForPreloadError() {
        try {
            maybeThrowSourceInfoRefreshError();
            Pair<PreloadMediaPeriod, MediaPeriodKey> pair = this.preloadingMediaPeriodAndKey;
            if (pair != null) {
                PreloadMediaPeriod preloadMediaPeriod = (PreloadMediaPeriod) pair.first;
                if (!preloadMediaPeriod.prepared) {
                    preloadMediaPeriod.maybeThrowPrepareError();
                } else {
                    preloadMediaPeriod.maybeThrowStreamError();
                }
            }
            this.preloadHandler.postDelayed(new PreloadMediaSource$$ExternalSyntheticLambda4(this), 100);
        } catch (IOException e) {
            this.preloadControl.onPreloadError(new PreloadException(getMediaItem(), (String) null, e), this);
            stopPreloading();
        }
    }

    /* access modifiers changed from: private */
    public void stopPreloading() {
        this.preloadHandler.removeCallbacksAndMessages((Object) null);
    }

    /* access modifiers changed from: private */
    public static boolean mediaPeriodIdEqualsWithoutWindowSequenceNumber(MediaSource.MediaPeriodId mediaPeriodId, MediaSource.MediaPeriodId mediaPeriodId2) {
        return mediaPeriodId.periodUid.equals(mediaPeriodId2.periodUid) && mediaPeriodId.adGroupIndex == mediaPeriodId2.adGroupIndex && mediaPeriodId.adIndexInAdGroup == mediaPeriodId2.adIndexInAdGroup && mediaPeriodId.nextAdGroupIndex == mediaPeriodId2.nextAdGroupIndex;
    }

    private class PreloadMediaPeriodCallback implements MediaPeriod.Callback {
        private final long periodStartPositionUs;
        private boolean prepared;

        public PreloadMediaPeriodCallback(long j) {
            this.periodStartPositionUs = j;
        }

        public void onPrepared(MediaPeriod mediaPeriod) {
            this.prepared = true;
            PreloadMediaSource.this.preloadHandler.post(new PreloadMediaSource$PreloadMediaPeriodCallback$$ExternalSyntheticLambda1(this, mediaPeriod));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onPrepared$0$androidx-media3-exoplayer-source-preload-PreloadMediaSource$PreloadMediaPeriodCallback  reason: not valid java name */
        public /* synthetic */ void m881lambda$onPrepared$0$androidxmedia3exoplayersourcepreloadPreloadMediaSource$PreloadMediaPeriodCallback(MediaPeriod mediaPeriod) {
            TrackSelectorResult trackSelectorResult;
            if (!PreloadMediaSource.this.isUsedByPlayer()) {
                PreloadMediaPeriod preloadMediaPeriod = (PreloadMediaPeriod) mediaPeriod;
                try {
                    trackSelectorResult = PreloadMediaSource.this.trackSelector.selectTracks(PreloadMediaSource.this.rendererCapabilities, preloadMediaPeriod.getTrackGroups(), ((MediaPeriodKey) ((Pair) Assertions.checkNotNull(PreloadMediaSource.this.preloadingMediaPeriodAndKey)).second).mediaPeriodId, (Timeline) Assertions.checkNotNull(PreloadMediaSource.this.timeline));
                } catch (ExoPlaybackException e) {
                    Log.e(PreloadMediaSource.TAG, "Failed to select tracks", e);
                    trackSelectorResult = null;
                }
                if (trackSelectorResult == null) {
                    PreloadMediaSource.this.stopPreloading();
                    return;
                }
                preloadMediaPeriod.selectTracksForPreloading(trackSelectorResult.selections, this.periodStartPositionUs);
                if (!PreloadMediaSource.this.preloadControl.onTracksSelected(PreloadMediaSource.this)) {
                    PreloadMediaSource.this.stopPreloading();
                } else {
                    preloadMediaPeriod.continueLoading(new LoadingInfo.Builder().setPlaybackPositionUs(this.periodStartPositionUs).build());
                }
            }
        }

        public void onContinueLoadingRequested(MediaPeriod mediaPeriod) {
            PreloadMediaSource.this.preloadHandler.post(new PreloadMediaSource$PreloadMediaPeriodCallback$$ExternalSyntheticLambda0(this, mediaPeriod));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onContinueLoadingRequested$1$androidx-media3-exoplayer-source-preload-PreloadMediaSource$PreloadMediaPeriodCallback  reason: not valid java name */
        public /* synthetic */ void m880lambda$onContinueLoadingRequested$1$androidxmedia3exoplayersourcepreloadPreloadMediaSource$PreloadMediaPeriodCallback(MediaPeriod mediaPeriod) {
            if (!PreloadMediaSource.this.isUsedByPlayer()) {
                PreloadMediaPeriod preloadMediaPeriod = (PreloadMediaPeriod) mediaPeriod;
                long bufferedPositionUs = mediaPeriod.getBufferedPositionUs();
                boolean z = this.prepared;
                if (z && bufferedPositionUs == Long.MIN_VALUE) {
                    PreloadMediaSource.this.preloadControl.onLoadedToTheEndOfSource(PreloadMediaSource.this);
                    PreloadMediaSource.this.stopPreloading();
                } else if (!z || PreloadMediaSource.this.preloadControl.onContinueLoadingRequested(PreloadMediaSource.this, bufferedPositionUs - this.periodStartPositionUs)) {
                    preloadMediaPeriod.continueLoading(new LoadingInfo.Builder().setPlaybackPositionUs(this.periodStartPositionUs).build());
                } else {
                    PreloadMediaSource.this.stopPreloading();
                }
            }
        }
    }

    private static class MediaPeriodKey {
        public final MediaSource.MediaPeriodId mediaPeriodId;
        private final Long startPositionUs;

        public MediaPeriodKey(MediaSource.MediaPeriodId mediaPeriodId2, long j) {
            this.mediaPeriodId = mediaPeriodId2;
            this.startPositionUs = Long.valueOf(j);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof MediaPeriodKey)) {
                return false;
            }
            MediaPeriodKey mediaPeriodKey = (MediaPeriodKey) obj;
            if (!PreloadMediaSource.mediaPeriodIdEqualsWithoutWindowSequenceNumber(this.mediaPeriodId, mediaPeriodKey.mediaPeriodId) || !this.startPositionUs.equals(mediaPeriodKey.startPositionUs)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return ((((((((527 + this.mediaPeriodId.periodUid.hashCode()) * 31) + this.mediaPeriodId.adGroupIndex) * 31) + this.mediaPeriodId.adIndexInAdGroup) * 31) + this.mediaPeriodId.nextAdGroupIndex) * 31) + this.startPositionUs.intValue();
        }
    }
}
