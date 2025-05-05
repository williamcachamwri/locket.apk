package androidx.media3.exoplayer.ima;

import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import android.view.ViewGroup;
import androidx.media3.common.AdOverlayInfo;
import androidx.media3.common.AdPlaybackState;
import androidx.media3.common.AdViewProvider;
import androidx.media3.common.C;
import androidx.media3.common.MediaItem;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.Player;
import androidx.media3.common.Timeline;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSpec;
import androidx.media3.exoplayer.ima.ImaUtil;
import androidx.media3.exoplayer.source.ads.AdsLoader;
import androidx.media3.exoplayer.source.ads.AdsMediaSource;
import com.google.ads.interactivemedia.v3.api.AdDisplayContainer;
import com.google.ads.interactivemedia.v3.api.AdError;
import com.google.ads.interactivemedia.v3.api.AdErrorEvent;
import com.google.ads.interactivemedia.v3.api.AdEvent;
import com.google.ads.interactivemedia.v3.api.AdPodInfo;
import com.google.ads.interactivemedia.v3.api.AdsLoader;
import com.google.ads.interactivemedia.v3.api.AdsManager;
import com.google.ads.interactivemedia.v3.api.AdsManagerLoadedEvent;
import com.google.ads.interactivemedia.v3.api.AdsRenderingSettings;
import com.google.ads.interactivemedia.v3.api.AdsRequest;
import com.google.ads.interactivemedia.v3.api.ImaSdkSettings;
import com.google.ads.interactivemedia.v3.api.player.AdMediaInfo;
import com.google.ads.interactivemedia.v3.api.player.ContentProgressProvider;
import com.google.ads.interactivemedia.v3.api.player.VideoAdPlayer;
import com.google.ads.interactivemedia.v3.api.player.VideoProgressUpdate;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

final class AdTagLoader implements Player.Listener {
    private static final int AD_PROGRESS_UPDATE_INTERVAL_MS = 200;
    private static final int IMA_AD_STATE_NONE = 0;
    private static final int IMA_AD_STATE_PAUSED = 2;
    private static final int IMA_AD_STATE_PLAYING = 1;
    private static final long IMA_DURATION_UNSET = -1;
    private static final String IMA_SDK_SETTINGS_PLAYER_TYPE = "google/exo.ext.ima";
    private static final String IMA_SDK_SETTINGS_PLAYER_VERSION = "1.5.1";
    private static final String TAG = "AdTagLoader";
    private static final long THRESHOLD_AD_MATCH_US = 1000;
    private static final long THRESHOLD_AD_PRELOAD_MS = 4000;
    private static final long THRESHOLD_END_OF_CONTENT_MS = 5000;
    /* access modifiers changed from: private */
    public final List<VideoAdPlayer.VideoAdPlayerCallback> adCallbacks;
    private final AdDisplayContainer adDisplayContainer;
    private final BiMap<AdMediaInfo, AdInfo> adInfoByAdMediaInfo;
    private final Runnable adLoadTimeoutRunnable;
    /* access modifiers changed from: private */
    public AdPlaybackState adPlaybackState;
    private final DataSpec adTagDataSpec;
    /* access modifiers changed from: private */
    public final Object adsId;
    private final AdsLoader adsLoader;
    /* access modifiers changed from: private */
    public AdsManager adsManager;
    private boolean bufferingAd;
    private final ComponentListener componentListener;
    /* access modifiers changed from: private */
    public final ImaUtil.Configuration configuration;
    private long contentDurationMs;
    private final List<AdsLoader.EventListener> eventListeners;
    private long fakeContentProgressElapsedRealtimeMs;
    private long fakeContentProgressOffsetMs;
    private final Handler handler;
    private AdInfo imaAdInfo;
    private AdMediaInfo imaAdMediaInfo;
    private int imaAdState;
    private final ImaUtil.ImaFactory imaFactory;
    private boolean imaPausedContent;
    private boolean isAdsManagerInitialized;
    private VideoProgressUpdate lastAdProgress;
    private VideoProgressUpdate lastContentProgress;
    private int lastVolumePercent;
    /* access modifiers changed from: private */
    public AdsMediaSource.AdLoadException pendingAdLoadError;
    private String pendingAdMimeType;
    private AdInfo pendingAdPrepareErrorAdInfo;
    /* access modifiers changed from: private */
    public Object pendingAdRequestContext;
    /* access modifiers changed from: private */
    public long pendingContentPositionMs;
    private final Timeline.Period period;
    /* access modifiers changed from: private */
    public Player player;
    private boolean playingAd;
    private int playingAdIndexInAdGroup;
    private boolean released;
    private boolean sentContentComplete;
    private boolean sentPendingContentPositionMs;
    private final List<String> supportedMimeTypes;
    private Timeline timeline;
    private final Runnable updateAdProgressRunnable;
    /* access modifiers changed from: private */
    public long waitingForPreloadElapsedRealtimeMs;

    public AdTagLoader(Context context, ImaUtil.Configuration configuration2, ImaUtil.ImaFactory imaFactory2, List<String> list, DataSpec dataSpec, Object obj, ViewGroup viewGroup) {
        this.configuration = configuration2;
        this.imaFactory = imaFactory2;
        ImaSdkSettings imaSdkSettings = configuration2.imaSdkSettings;
        if (imaSdkSettings == null) {
            imaSdkSettings = imaFactory2.createImaSdkSettings();
            if (configuration2.debugModeEnabled) {
                imaSdkSettings.setDebugMode(true);
            }
        }
        imaSdkSettings.setPlayerType(IMA_SDK_SETTINGS_PLAYER_TYPE);
        imaSdkSettings.setPlayerVersion("1.5.1");
        this.supportedMimeTypes = list;
        this.adTagDataSpec = dataSpec;
        this.adsId = obj;
        this.period = new Timeline.Period();
        this.handler = Util.createHandler(ImaUtil.getImaLooper(), (Handler.Callback) null);
        ComponentListener componentListener2 = new ComponentListener(this, (AnonymousClass1) null);
        this.componentListener = componentListener2;
        this.eventListeners = new ArrayList();
        ArrayList arrayList = new ArrayList(1);
        this.adCallbacks = arrayList;
        if (configuration2.applicationVideoAdPlayerCallback != null) {
            arrayList.add(configuration2.applicationVideoAdPlayerCallback);
        }
        this.updateAdProgressRunnable = new AdTagLoader$$ExternalSyntheticLambda1(this);
        this.adInfoByAdMediaInfo = HashBiMap.create();
        this.lastContentProgress = VideoProgressUpdate.VIDEO_TIME_NOT_READY;
        this.lastAdProgress = VideoProgressUpdate.VIDEO_TIME_NOT_READY;
        this.fakeContentProgressElapsedRealtimeMs = C.TIME_UNSET;
        this.fakeContentProgressOffsetMs = C.TIME_UNSET;
        this.pendingContentPositionMs = C.TIME_UNSET;
        this.waitingForPreloadElapsedRealtimeMs = C.TIME_UNSET;
        this.contentDurationMs = C.TIME_UNSET;
        this.timeline = Timeline.EMPTY;
        this.adPlaybackState = AdPlaybackState.NONE;
        this.adLoadTimeoutRunnable = new AdTagLoader$$ExternalSyntheticLambda2(this);
        if (viewGroup != null) {
            this.adDisplayContainer = imaFactory2.createAdDisplayContainer(viewGroup, componentListener2);
        } else {
            this.adDisplayContainer = imaFactory2.createAudioAdDisplayContainer(context, componentListener2);
        }
        if (configuration2.companionAdSlots != null) {
            this.adDisplayContainer.setCompanionSlots(configuration2.companionAdSlots);
        }
        this.adsLoader = requestAds(context, imaSdkSettings, this.adDisplayContainer);
    }

    public com.google.ads.interactivemedia.v3.api.AdsLoader getAdsLoader() {
        return this.adsLoader;
    }

    public AdDisplayContainer getAdDisplayContainer() {
        return this.adDisplayContainer;
    }

    public void skipAd() {
        AdsManager adsManager2 = this.adsManager;
        if (adsManager2 != null) {
            adsManager2.skip();
        }
    }

    public void focusSkipButton() {
        AdsManager adsManager2 = this.adsManager;
        if (adsManager2 != null) {
            adsManager2.focus();
        }
    }

    public void addListenerWithAdView(AdsLoader.EventListener eventListener, AdViewProvider adViewProvider) {
        boolean z = !this.eventListeners.isEmpty();
        this.eventListeners.add(eventListener);
        if (!z) {
            this.lastVolumePercent = 0;
            this.lastAdProgress = VideoProgressUpdate.VIDEO_TIME_NOT_READY;
            this.lastContentProgress = VideoProgressUpdate.VIDEO_TIME_NOT_READY;
            maybeNotifyPendingAdLoadError();
            if (!AdPlaybackState.NONE.equals(this.adPlaybackState)) {
                eventListener.onAdPlaybackState(this.adPlaybackState);
            } else if (this.adsManager != null) {
                this.adPlaybackState = new AdPlaybackState(this.adsId, ImaUtil.getAdGroupTimesUsForCuePoints(this.adsManager.getAdCuePoints()));
                updateAdPlaybackState();
            }
            for (AdOverlayInfo next : adViewProvider.getAdOverlayInfos()) {
                this.adDisplayContainer.registerFriendlyObstruction(this.imaFactory.createFriendlyObstruction(next.view, ImaUtil.getFriendlyObstructionPurpose(next.purpose), next.reasonDetail));
            }
        } else if (!AdPlaybackState.NONE.equals(this.adPlaybackState)) {
            eventListener.onAdPlaybackState(this.adPlaybackState);
        }
    }

    public void maybePreloadAds(long j, long j2) {
        maybeInitializeAdsManager(j, j2);
    }

    public void activate(Player player2) {
        AdInfo adInfo;
        this.player = player2;
        player2.addListener(this);
        boolean playWhenReady = player2.getPlayWhenReady();
        onTimelineChanged(player2.getCurrentTimeline(), 1);
        AdsManager adsManager2 = this.adsManager;
        if (!AdPlaybackState.NONE.equals(this.adPlaybackState) && adsManager2 != null && this.imaPausedContent) {
            int adGroupIndexForPositionUs = this.adPlaybackState.getAdGroupIndexForPositionUs(Util.msToUs(getContentPeriodPositionMs(player2, this.timeline, this.period)), Util.msToUs(this.contentDurationMs));
            if (!(adGroupIndexForPositionUs == -1 || (adInfo = this.imaAdInfo) == null || adInfo.adGroupIndex == adGroupIndexForPositionUs)) {
                if (this.configuration.debugModeEnabled) {
                    Log.d(TAG, "Discarding preloaded ad " + this.imaAdInfo);
                }
                adsManager2.discardAdBreak();
            }
            if (playWhenReady) {
                adsManager2.resume();
            }
        }
    }

    public void deactivate() {
        Player player2 = (Player) Assertions.checkNotNull(this.player);
        if (!AdPlaybackState.NONE.equals(this.adPlaybackState) && this.imaPausedContent) {
            AdsManager adsManager2 = this.adsManager;
            if (adsManager2 != null) {
                adsManager2.pause();
            }
            this.adPlaybackState = this.adPlaybackState.withAdResumePositionUs(this.playingAd ? Util.msToUs(player2.getCurrentPosition()) : 0);
        }
        this.lastVolumePercent = getPlayerVolumePercent();
        this.lastAdProgress = getAdVideoProgressUpdate();
        this.lastContentProgress = getContentVideoProgressUpdate();
        this.handler.post(new AdTagLoader$$ExternalSyntheticLambda0(this, player2));
        this.player = null;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$deactivate$0$androidx-media3-exoplayer-ima-AdTagLoader  reason: not valid java name */
    public /* synthetic */ void m523lambda$deactivate$0$androidxmedia3exoplayerimaAdTagLoader(Player player2) {
        player2.removeListener(this);
    }

    public void removeListener(AdsLoader.EventListener eventListener) {
        this.eventListeners.remove(eventListener);
        if (this.eventListeners.isEmpty()) {
            this.adDisplayContainer.unregisterAllFriendlyObstructions();
        }
    }

    public void release() {
        if (!this.released) {
            this.released = true;
            this.pendingAdRequestContext = null;
            destroyAdsManager();
            this.adsLoader.removeAdsLoadedListener(this.componentListener);
            this.adsLoader.removeAdErrorListener(this.componentListener);
            if (this.configuration.applicationAdErrorListener != null) {
                this.adsLoader.removeAdErrorListener(this.configuration.applicationAdErrorListener);
            }
            this.adsLoader.release();
            this.imaPausedContent = false;
            this.imaAdState = 0;
            this.imaAdMediaInfo = null;
            stopUpdatingAdProgress();
            this.imaAdInfo = null;
            this.pendingAdLoadError = null;
            for (int i = 0; i < this.adPlaybackState.adGroupCount; i++) {
                this.adPlaybackState = this.adPlaybackState.withSkippedAdGroup(i);
            }
            updateAdPlaybackState();
        }
    }

    public void handlePrepareComplete(int i, int i2) {
        AdInfo adInfo = new AdInfo(i, i2);
        if (this.configuration.debugModeEnabled) {
            Log.d(TAG, "Prepared ad " + adInfo);
        }
        AdMediaInfo adMediaInfo = (AdMediaInfo) this.adInfoByAdMediaInfo.inverse().get(adInfo);
        if (adMediaInfo != null) {
            for (int i3 = 0; i3 < this.adCallbacks.size(); i3++) {
                this.adCallbacks.get(i3).onLoaded(adMediaInfo);
            }
            return;
        }
        Log.w(TAG, "Unexpected prepared ad " + adInfo);
    }

    public void handlePrepareError(int i, int i2, IOException iOException) {
        if (this.player != null) {
            try {
                handleAdPrepareError(i, i2, iOException);
            } catch (RuntimeException e) {
                maybeNotifyInternalError("handlePrepareError", e);
            }
        }
    }

    public void onTimelineChanged(Timeline timeline2, int i) {
        Player player2;
        if (!timeline2.isEmpty() && (player2 = this.player) != null) {
            this.timeline = timeline2;
            long j = timeline2.getPeriod(player2.getCurrentPeriodIndex(), this.period).durationUs;
            this.contentDurationMs = Util.usToMs(j);
            if (j != this.adPlaybackState.contentDurationUs) {
                this.adPlaybackState = this.adPlaybackState.withContentDurationUs(j);
                updateAdPlaybackState();
            }
            maybeInitializeAdsManager(getContentPeriodPositionMs(player2, timeline2, this.period), this.contentDurationMs);
            handleTimelineOrPositionChanged();
        }
    }

    public void onPositionDiscontinuity(Player.PositionInfo positionInfo, Player.PositionInfo positionInfo2, int i) {
        handleTimelineOrPositionChanged();
    }

    public void onPlaybackStateChanged(int i) {
        Player player2 = this.player;
        if (this.adsManager != null && player2 != null) {
            if (i == 2 && !player2.isPlayingAd() && isWaitingForFirstAdToPreload()) {
                this.waitingForPreloadElapsedRealtimeMs = SystemClock.elapsedRealtime();
            } else if (i == 3) {
                this.waitingForPreloadElapsedRealtimeMs = C.TIME_UNSET;
            }
            handlePlayerStateChanged(player2.getPlayWhenReady(), i);
        }
    }

    public void onPlayWhenReadyChanged(boolean z, int i) {
        Player player2;
        AdsManager adsManager2 = this.adsManager;
        if (adsManager2 != null && (player2 = this.player) != null) {
            int i2 = this.imaAdState;
            if (i2 == 1 && !z) {
                adsManager2.pause();
            } else if (i2 != 2 || !z) {
                handlePlayerStateChanged(z, player2.getPlaybackState());
            } else {
                adsManager2.resume();
            }
        }
    }

    public void onPlayerError(PlaybackException playbackException) {
        if (this.imaAdState != 0) {
            AdMediaInfo adMediaInfo = (AdMediaInfo) Assertions.checkNotNull(this.imaAdMediaInfo);
            for (int i = 0; i < this.adCallbacks.size(); i++) {
                this.adCallbacks.get(i).onError(adMediaInfo);
            }
        }
    }

    private com.google.ads.interactivemedia.v3.api.AdsLoader requestAds(Context context, ImaSdkSettings imaSdkSettings, AdDisplayContainer adDisplayContainer2) {
        com.google.ads.interactivemedia.v3.api.AdsLoader createAdsLoader = this.imaFactory.createAdsLoader(context, imaSdkSettings, adDisplayContainer2);
        createAdsLoader.addAdErrorListener(this.componentListener);
        if (this.configuration.applicationAdErrorListener != null) {
            createAdsLoader.addAdErrorListener(this.configuration.applicationAdErrorListener);
        }
        createAdsLoader.addAdsLoadedListener(this.componentListener);
        try {
            AdsRequest adsRequestForAdTagDataSpec = ImaUtil.getAdsRequestForAdTagDataSpec(this.imaFactory, this.adTagDataSpec);
            Object obj = new Object();
            this.pendingAdRequestContext = obj;
            adsRequestForAdTagDataSpec.setUserRequestContext(obj);
            if (this.configuration.enableContinuousPlayback != null) {
                adsRequestForAdTagDataSpec.setContinuousPlayback(this.configuration.enableContinuousPlayback.booleanValue());
            }
            if (this.configuration.vastLoadTimeoutMs != -1) {
                adsRequestForAdTagDataSpec.setVastLoadTimeout((float) this.configuration.vastLoadTimeoutMs);
            }
            adsRequestForAdTagDataSpec.setContentProgressProvider(this.componentListener);
            createAdsLoader.requestAds(adsRequestForAdTagDataSpec);
            return createAdsLoader;
        } catch (IOException e) {
            this.adPlaybackState = new AdPlaybackState(this.adsId, new long[0]);
            updateAdPlaybackState();
            this.pendingAdLoadError = AdsMediaSource.AdLoadException.createForAllAds(e);
            maybeNotifyPendingAdLoadError();
            return createAdsLoader;
        }
    }

    private void maybeInitializeAdsManager(long j, long j2) {
        AdsManager adsManager2 = this.adsManager;
        if (!this.isAdsManagerInitialized && adsManager2 != null) {
            this.isAdsManagerInitialized = true;
            AdsRenderingSettings adsRenderingSettings = setupAdsRendering(j, j2);
            if (adsRenderingSettings == null) {
                destroyAdsManager();
            } else {
                adsManager2.init(adsRenderingSettings);
                adsManager2.start();
                if (this.configuration.debugModeEnabled) {
                    Log.d(TAG, "Initialized with ads rendering settings: " + adsRenderingSettings);
                }
            }
            updateAdPlaybackState();
        }
    }

    private AdsRenderingSettings setupAdsRendering(long j, long j2) {
        List<String> list;
        AdsRenderingSettings createAdsRenderingSettings = this.imaFactory.createAdsRenderingSettings();
        createAdsRenderingSettings.setEnablePreloading(true);
        if (this.configuration.adMediaMimeTypes != null) {
            list = this.configuration.adMediaMimeTypes;
        } else {
            list = this.supportedMimeTypes;
        }
        createAdsRenderingSettings.setMimeTypes(list);
        if (this.configuration.mediaLoadTimeoutMs != -1) {
            createAdsRenderingSettings.setLoadVideoTimeout(this.configuration.mediaLoadTimeoutMs);
        }
        if (this.configuration.mediaBitrate != -1) {
            createAdsRenderingSettings.setBitrateKbps(this.configuration.mediaBitrate / 1000);
        }
        createAdsRenderingSettings.setFocusSkipButtonWhenAvailable(this.configuration.focusSkipButtonWhenAvailable);
        if (this.configuration.adUiElements != null) {
            createAdsRenderingSettings.setUiElements(this.configuration.adUiElements);
        }
        int adGroupIndexForPositionUs = this.adPlaybackState.getAdGroupIndexForPositionUs(Util.msToUs(j), Util.msToUs(j2));
        if (adGroupIndexForPositionUs != -1) {
            if (!(this.adPlaybackState.getAdGroup(adGroupIndexForPositionUs).timeUs == Util.msToUs(j) || this.configuration.playAdBeforeStartPosition)) {
                adGroupIndexForPositionUs++;
            } else if (hasMidrollAdGroups(this.adPlaybackState)) {
                this.pendingContentPositionMs = j;
            }
            if (adGroupIndexForPositionUs > 0) {
                for (int i = 0; i < adGroupIndexForPositionUs; i++) {
                    this.adPlaybackState = this.adPlaybackState.withSkippedAdGroup(i);
                }
                if (adGroupIndexForPositionUs == this.adPlaybackState.adGroupCount) {
                    return null;
                }
                long j3 = this.adPlaybackState.getAdGroup(adGroupIndexForPositionUs).timeUs;
                long j4 = this.adPlaybackState.getAdGroup(adGroupIndexForPositionUs - 1).timeUs;
                if (j3 == Long.MIN_VALUE) {
                    createAdsRenderingSettings.setPlayAdsAfterTime((((double) j4) / 1000000.0d) + 1.0d);
                } else {
                    createAdsRenderingSettings.setPlayAdsAfterTime((((double) (j3 + j4)) / 2.0d) / 1000000.0d);
                }
            }
        }
        return createAdsRenderingSettings;
    }

    /* access modifiers changed from: private */
    public VideoProgressUpdate getContentVideoProgressUpdate() {
        boolean z = this.contentDurationMs != C.TIME_UNSET;
        long j = this.pendingContentPositionMs;
        if (j != C.TIME_UNSET) {
            this.sentPendingContentPositionMs = true;
        } else {
            Player player2 = this.player;
            if (player2 == null) {
                return this.lastContentProgress;
            }
            if (this.fakeContentProgressElapsedRealtimeMs != C.TIME_UNSET) {
                j = this.fakeContentProgressOffsetMs + (SystemClock.elapsedRealtime() - this.fakeContentProgressElapsedRealtimeMs);
            } else if (this.imaAdState != 0 || this.playingAd || !z) {
                return VideoProgressUpdate.VIDEO_TIME_NOT_READY;
            } else {
                j = getContentPeriodPositionMs(player2, this.timeline, this.period);
            }
        }
        return new VideoProgressUpdate(j, z ? this.contentDurationMs : -1);
    }

    private VideoProgressUpdate getAdVideoProgressUpdate() {
        Player player2 = this.player;
        if (player2 == null) {
            return this.lastAdProgress;
        }
        if (this.imaAdState == 0 || !this.playingAd) {
            return VideoProgressUpdate.VIDEO_TIME_NOT_READY;
        }
        long duration = player2.getDuration();
        if (duration == C.TIME_UNSET) {
            return VideoProgressUpdate.VIDEO_TIME_NOT_READY;
        }
        return new VideoProgressUpdate(this.player.getCurrentPosition(), duration);
    }

    /* access modifiers changed from: private */
    public void updateAdProgress() {
        VideoProgressUpdate adVideoProgressUpdate = getAdVideoProgressUpdate();
        if (this.configuration.debugModeEnabled) {
            Log.d(TAG, "Ad progress: " + ImaUtil.getStringForVideoProgressUpdate(adVideoProgressUpdate));
        }
        AdMediaInfo adMediaInfo = (AdMediaInfo) Assertions.checkNotNull(this.imaAdMediaInfo);
        for (int i = 0; i < this.adCallbacks.size(); i++) {
            this.adCallbacks.get(i).onAdProgress(adMediaInfo, adVideoProgressUpdate);
        }
        this.handler.removeCallbacks(this.updateAdProgressRunnable);
        this.handler.postDelayed(this.updateAdProgressRunnable, 200);
    }

    private void stopUpdatingAdProgress() {
        this.handler.removeCallbacks(this.updateAdProgressRunnable);
    }

    /* access modifiers changed from: private */
    public int getPlayerVolumePercent() {
        Player player2 = this.player;
        if (player2 == null) {
            return this.lastVolumePercent;
        }
        if (player2.isCommandAvailable(22)) {
            return (int) (player2.getVolume() * 100.0f);
        }
        return player2.getCurrentTracks().isTypeSelected(1) ? 100 : 0;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x004d, code lost:
        r6.eventListeners.get(r2).onAdClicked();
        r2 = r2 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0061, code lost:
        if (r2 >= r6.eventListeners.size()) goto L_0x00be;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0063, code lost:
        r6.eventListeners.get(r2).onAdTapped();
        r2 = r2 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x004b, code lost:
        if (r2 >= r6.eventListeners.size()) goto L_0x00be;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void handleAdEvent(com.google.ads.interactivemedia.v3.api.AdEvent r7) {
        /*
            r6 = this;
            com.google.ads.interactivemedia.v3.api.AdsManager r0 = r6.adsManager
            if (r0 != 0) goto L_0x0005
            return
        L_0x0005:
            int[] r0 = androidx.media3.exoplayer.ima.AdTagLoader.AnonymousClass1.$SwitchMap$com$google$ads$interactivemedia$v3$api$AdEvent$AdEventType
            com.google.ads.interactivemedia.v3.api.AdEvent$AdEventType r1 = r7.getType()
            int r1 = r1.ordinal()
            r0 = r0[r1]
            java.lang.String r1 = "AdTagLoader"
            r2 = 0
            r3 = 1
            switch(r0) {
                case 1: goto L_0x0077;
                case 2: goto L_0x0071;
                case 3: goto L_0x005b;
                case 4: goto L_0x0045;
                case 5: goto L_0x003e;
                case 6: goto L_0x0026;
                case 7: goto L_0x001a;
                default: goto L_0x0018;
            }
        L_0x0018:
            goto L_0x00be
        L_0x001a:
            com.google.ads.interactivemedia.v3.api.Ad r7 = r7.getAd()
            java.lang.String r7 = r7.getContentType()
            r6.pendingAdMimeType = r7
            goto L_0x00be
        L_0x0026:
            java.util.Map r7 = r7.getAdData()
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r2 = "AdEvent: "
            r0.<init>(r2)
            java.lang.StringBuilder r7 = r0.append(r7)
            java.lang.String r7 = r7.toString()
            androidx.media3.common.util.Log.i(r1, r7)
            goto L_0x00be
        L_0x003e:
            r6.imaPausedContent = r2
            r6.resumeContentInternal()
            goto L_0x00be
        L_0x0045:
            java.util.List<androidx.media3.exoplayer.source.ads.AdsLoader$EventListener> r7 = r6.eventListeners
            int r7 = r7.size()
            if (r2 >= r7) goto L_0x00be
            java.util.List<androidx.media3.exoplayer.source.ads.AdsLoader$EventListener> r7 = r6.eventListeners
            java.lang.Object r7 = r7.get(r2)
            androidx.media3.exoplayer.source.ads.AdsLoader$EventListener r7 = (androidx.media3.exoplayer.source.ads.AdsLoader.EventListener) r7
            r7.onAdClicked()
            int r2 = r2 + 1
            goto L_0x0045
        L_0x005b:
            java.util.List<androidx.media3.exoplayer.source.ads.AdsLoader$EventListener> r7 = r6.eventListeners
            int r7 = r7.size()
            if (r2 >= r7) goto L_0x00be
            java.util.List<androidx.media3.exoplayer.source.ads.AdsLoader$EventListener> r7 = r6.eventListeners
            java.lang.Object r7 = r7.get(r2)
            androidx.media3.exoplayer.source.ads.AdsLoader$EventListener r7 = (androidx.media3.exoplayer.source.ads.AdsLoader.EventListener) r7
            r7.onAdTapped()
            int r2 = r2 + 1
            goto L_0x005b
        L_0x0071:
            r6.imaPausedContent = r3
            r6.pauseContentInternal()
            goto L_0x00be
        L_0x0077:
            java.util.Map r7 = r7.getAdData()
            java.lang.String r0 = "adBreakTime"
            java.lang.Object r7 = r7.get(r0)
            java.lang.String r7 = (java.lang.String) r7
            java.lang.Object r7 = androidx.media3.common.util.Assertions.checkNotNull(r7)
            java.lang.String r7 = (java.lang.String) r7
            androidx.media3.exoplayer.ima.ImaUtil$Configuration r0 = r6.configuration
            boolean r0 = r0.debugModeEnabled
            if (r0 == 0) goto L_0x00a7
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r2 = "Fetch error for ad at "
            r0.<init>(r2)
            java.lang.StringBuilder r0 = r0.append(r7)
            java.lang.String r2 = " seconds"
            java.lang.StringBuilder r0 = r0.append(r2)
            java.lang.String r0 = r0.toString()
            androidx.media3.common.util.Log.d(r1, r0)
        L_0x00a7:
            double r0 = java.lang.Double.parseDouble(r7)
            r4 = -4616189618054758400(0xbff0000000000000, double:-1.0)
            int r7 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r7 != 0) goto L_0x00b7
            androidx.media3.common.AdPlaybackState r7 = r6.adPlaybackState
            int r7 = r7.adGroupCount
            int r7 = r7 - r3
            goto L_0x00bb
        L_0x00b7:
            int r7 = r6.getAdGroupIndexForCuePointTimeSeconds(r0)
        L_0x00bb:
            r6.markAdGroupInErrorStateAndClearPendingContentPosition(r7)
        L_0x00be:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.ima.AdTagLoader.handleAdEvent(com.google.ads.interactivemedia.v3.api.AdEvent):void");
    }

    /* renamed from: androidx.media3.exoplayer.ima.AdTagLoader$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$ads$interactivemedia$v3$api$AdEvent$AdEventType;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|(3:13|14|16)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(16:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|16) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.google.ads.interactivemedia.v3.api.AdEvent$AdEventType[] r0 = com.google.ads.interactivemedia.v3.api.AdEvent.AdEventType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$google$ads$interactivemedia$v3$api$AdEvent$AdEventType = r0
                com.google.ads.interactivemedia.v3.api.AdEvent$AdEventType r1 = com.google.ads.interactivemedia.v3.api.AdEvent.AdEventType.AD_BREAK_FETCH_ERROR     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$google$ads$interactivemedia$v3$api$AdEvent$AdEventType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.ads.interactivemedia.v3.api.AdEvent$AdEventType r1 = com.google.ads.interactivemedia.v3.api.AdEvent.AdEventType.CONTENT_PAUSE_REQUESTED     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$google$ads$interactivemedia$v3$api$AdEvent$AdEventType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.ads.interactivemedia.v3.api.AdEvent$AdEventType r1 = com.google.ads.interactivemedia.v3.api.AdEvent.AdEventType.TAPPED     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$google$ads$interactivemedia$v3$api$AdEvent$AdEventType     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.google.ads.interactivemedia.v3.api.AdEvent$AdEventType r1 = com.google.ads.interactivemedia.v3.api.AdEvent.AdEventType.CLICKED     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$google$ads$interactivemedia$v3$api$AdEvent$AdEventType     // Catch:{ NoSuchFieldError -> 0x003e }
                com.google.ads.interactivemedia.v3.api.AdEvent$AdEventType r1 = com.google.ads.interactivemedia.v3.api.AdEvent.AdEventType.CONTENT_RESUME_REQUESTED     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$google$ads$interactivemedia$v3$api$AdEvent$AdEventType     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.google.ads.interactivemedia.v3.api.AdEvent$AdEventType r1 = com.google.ads.interactivemedia.v3.api.AdEvent.AdEventType.LOG     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$com$google$ads$interactivemedia$v3$api$AdEvent$AdEventType     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.google.ads.interactivemedia.v3.api.AdEvent$AdEventType r1 = com.google.ads.interactivemedia.v3.api.AdEvent.AdEventType.LOADED     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.ima.AdTagLoader.AnonymousClass1.<clinit>():void");
        }
    }

    private void pauseContentInternal() {
        this.imaAdState = 0;
        if (this.sentPendingContentPositionMs) {
            this.pendingContentPositionMs = C.TIME_UNSET;
            this.sentPendingContentPositionMs = false;
        }
    }

    private void resumeContentInternal() {
        AdInfo adInfo = this.imaAdInfo;
        if (adInfo != null) {
            this.adPlaybackState = this.adPlaybackState.withSkippedAdGroup(adInfo.adGroupIndex);
            updateAdPlaybackState();
        }
    }

    /* access modifiers changed from: private */
    public boolean isWaitingForFirstAdToPreload() {
        int loadingAdGroupIndex;
        Player player2 = this.player;
        if (player2 == null || (loadingAdGroupIndex = getLoadingAdGroupIndex()) == -1) {
            return false;
        }
        AdPlaybackState.AdGroup adGroup = this.adPlaybackState.getAdGroup(loadingAdGroupIndex);
        if ((adGroup.count == -1 || adGroup.count == 0 || adGroup.states[0] == 0) && Util.usToMs(adGroup.timeUs) - getContentPeriodPositionMs(player2, this.timeline, this.period) < this.configuration.adPreloadTimeoutMs) {
            return true;
        }
        return false;
    }

    private boolean isWaitingForCurrentAdToLoad() {
        int currentAdGroupIndex;
        Player player2 = this.player;
        if (player2 == null || (currentAdGroupIndex = player2.getCurrentAdGroupIndex()) == -1) {
            return false;
        }
        AdPlaybackState.AdGroup adGroup = this.adPlaybackState.getAdGroup(currentAdGroupIndex);
        int currentAdIndexInAdGroup = player2.getCurrentAdIndexInAdGroup();
        if (adGroup.count == -1 || adGroup.count <= currentAdIndexInAdGroup) {
            return true;
        }
        if (adGroup.states[currentAdIndexInAdGroup] == 0) {
            return true;
        }
        return false;
    }

    private void handlePlayerStateChanged(boolean z, int i) {
        if (this.playingAd && this.imaAdState == 1) {
            boolean z2 = this.bufferingAd;
            if (!z2 && i == 2) {
                this.bufferingAd = true;
                AdMediaInfo adMediaInfo = (AdMediaInfo) Assertions.checkNotNull(this.imaAdMediaInfo);
                for (int i2 = 0; i2 < this.adCallbacks.size(); i2++) {
                    this.adCallbacks.get(i2).onBuffering(adMediaInfo);
                }
                stopUpdatingAdProgress();
            } else if (z2 && i == 3) {
                this.bufferingAd = false;
                updateAdProgress();
            }
        }
        int i3 = this.imaAdState;
        if (i3 == 0 && i == 2 && z) {
            ensureSentContentCompleteIfAtEndOfStream();
        } else if (i3 != 0 && i == 4) {
            AdMediaInfo adMediaInfo2 = this.imaAdMediaInfo;
            if (adMediaInfo2 == null) {
                Log.w(TAG, "onEnded without ad media info");
            } else {
                for (int i4 = 0; i4 < this.adCallbacks.size(); i4++) {
                    this.adCallbacks.get(i4).onEnded(adMediaInfo2);
                }
            }
            if (this.configuration.debugModeEnabled) {
                Log.d(TAG, "VideoAdPlayerCallback.onEnded in onPlaybackStateChanged");
            }
        }
    }

    private void handleTimelineOrPositionChanged() {
        Player player2 = this.player;
        if (this.adsManager != null && player2 != null) {
            if (!this.playingAd && !player2.isPlayingAd()) {
                ensureSentContentCompleteIfAtEndOfStream();
                if (!this.sentContentComplete && !this.timeline.isEmpty()) {
                    long contentPeriodPositionMs = getContentPeriodPositionMs(player2, this.timeline, this.period);
                    this.timeline.getPeriod(player2.getCurrentPeriodIndex(), this.period);
                    if (this.period.getAdGroupIndexForPositionUs(Util.msToUs(contentPeriodPositionMs)) != -1) {
                        this.sentPendingContentPositionMs = false;
                        this.pendingContentPositionMs = contentPeriodPositionMs;
                    }
                }
            }
            boolean z = this.playingAd;
            int i = this.playingAdIndexInAdGroup;
            boolean isPlayingAd = player2.isPlayingAd();
            this.playingAd = isPlayingAd;
            int currentAdIndexInAdGroup = isPlayingAd ? player2.getCurrentAdIndexInAdGroup() : -1;
            this.playingAdIndexInAdGroup = currentAdIndexInAdGroup;
            if (z && currentAdIndexInAdGroup != i) {
                AdMediaInfo adMediaInfo = this.imaAdMediaInfo;
                if (adMediaInfo == null) {
                    Log.w(TAG, "onEnded without ad media info");
                } else {
                    AdInfo adInfo = (AdInfo) this.adInfoByAdMediaInfo.get(adMediaInfo);
                    if (this.playingAdIndexInAdGroup == -1 || (adInfo != null && adInfo.adIndexInAdGroup < this.playingAdIndexInAdGroup)) {
                        for (int i2 = 0; i2 < this.adCallbacks.size(); i2++) {
                            this.adCallbacks.get(i2).onEnded(adMediaInfo);
                        }
                        if (this.configuration.debugModeEnabled) {
                            Log.d(TAG, "VideoAdPlayerCallback.onEnded in onTimelineChanged/onPositionDiscontinuity");
                        }
                    }
                }
            }
            if (!this.sentContentComplete && !z && this.playingAd && this.imaAdState == 0) {
                AdPlaybackState.AdGroup adGroup = this.adPlaybackState.getAdGroup(player2.getCurrentAdGroupIndex());
                if (adGroup.timeUs == Long.MIN_VALUE) {
                    sendContentComplete();
                } else {
                    this.fakeContentProgressElapsedRealtimeMs = SystemClock.elapsedRealtime();
                    long usToMs = Util.usToMs(adGroup.timeUs);
                    this.fakeContentProgressOffsetMs = usToMs;
                    if (usToMs == Long.MIN_VALUE) {
                        this.fakeContentProgressOffsetMs = this.contentDurationMs;
                    }
                }
            }
            if (isWaitingForCurrentAdToLoad()) {
                this.handler.removeCallbacks(this.adLoadTimeoutRunnable);
                this.handler.postDelayed(this.adLoadTimeoutRunnable, this.configuration.adPreloadTimeoutMs);
            }
        }
    }

    /* access modifiers changed from: private */
    public void loadAdInternal(AdMediaInfo adMediaInfo, AdPodInfo adPodInfo) {
        if (this.adsManager != null) {
            int adGroupIndexForAdPod = getAdGroupIndexForAdPod(adPodInfo);
            int adPosition = adPodInfo.getAdPosition() - 1;
            AdInfo adInfo = new AdInfo(adGroupIndexForAdPod, adPosition);
            this.adInfoByAdMediaInfo.forcePut(adMediaInfo, adInfo);
            if (this.configuration.debugModeEnabled) {
                Log.d(TAG, "loadAd " + getAdMediaInfoString(adMediaInfo));
            }
            if (!this.adPlaybackState.isAdInErrorState(adGroupIndexForAdPod, adPosition)) {
                Player player2 = this.player;
                if (player2 != null && player2.getCurrentAdGroupIndex() == adGroupIndexForAdPod && this.player.getCurrentAdIndexInAdGroup() == adPosition) {
                    this.handler.removeCallbacks(this.adLoadTimeoutRunnable);
                }
                AdPlaybackState withAdCount = this.adPlaybackState.withAdCount(adInfo.adGroupIndex, Math.max(adPodInfo.getTotalAds(), this.adPlaybackState.getAdGroup(adInfo.adGroupIndex).states.length));
                this.adPlaybackState = withAdCount;
                AdPlaybackState.AdGroup adGroup = withAdCount.getAdGroup(adInfo.adGroupIndex);
                for (int i = 0; i < adPosition; i++) {
                    if (adGroup.states[i] == 0) {
                        this.adPlaybackState = this.adPlaybackState.withAdLoadError(adGroupIndexForAdPod, i);
                    }
                }
                MediaItem.Builder uri = new MediaItem.Builder().setUri(adMediaInfo.getUrl());
                String str = this.pendingAdMimeType;
                if (str != null) {
                    uri.setMimeType(str);
                    this.pendingAdMimeType = null;
                }
                this.adPlaybackState = this.adPlaybackState.withAvailableAdMediaItem(adInfo.adGroupIndex, adInfo.adIndexInAdGroup, uri.build());
                updateAdPlaybackState();
            }
        } else if (this.configuration.debugModeEnabled) {
            Log.d(TAG, "loadAd after release " + getAdMediaInfoString(adMediaInfo) + ", ad pod " + adPodInfo);
        }
    }

    /* access modifiers changed from: private */
    public void playAdInternal(AdMediaInfo adMediaInfo) {
        if (this.configuration.debugModeEnabled) {
            Log.d(TAG, "playAd " + getAdMediaInfoString(adMediaInfo));
        }
        if (this.adsManager != null) {
            if (this.imaAdState == 1) {
                Log.w(TAG, "Unexpected playAd without stopAd");
            }
            int i = 0;
            if (this.imaAdState == 0) {
                this.fakeContentProgressElapsedRealtimeMs = C.TIME_UNSET;
                this.fakeContentProgressOffsetMs = C.TIME_UNSET;
                this.imaAdState = 1;
                this.imaAdMediaInfo = adMediaInfo;
                this.imaAdInfo = (AdInfo) Assertions.checkNotNull((AdInfo) this.adInfoByAdMediaInfo.get(adMediaInfo));
                for (int i2 = 0; i2 < this.adCallbacks.size(); i2++) {
                    this.adCallbacks.get(i2).onPlay(adMediaInfo);
                }
                AdInfo adInfo = this.pendingAdPrepareErrorAdInfo;
                if (adInfo != null && adInfo.equals(this.imaAdInfo)) {
                    this.pendingAdPrepareErrorAdInfo = null;
                    while (i < this.adCallbacks.size()) {
                        this.adCallbacks.get(i).onError(adMediaInfo);
                        i++;
                    }
                }
                updateAdProgress();
            } else {
                this.imaAdState = 1;
                Assertions.checkState(adMediaInfo.equals(this.imaAdMediaInfo));
                while (i < this.adCallbacks.size()) {
                    this.adCallbacks.get(i).onResume(adMediaInfo);
                    i++;
                }
            }
            Player player2 = this.player;
            if (player2 == null || !player2.getPlayWhenReady()) {
                ((AdsManager) Assertions.checkNotNull(this.adsManager)).pause();
            }
        }
    }

    /* access modifiers changed from: private */
    public void pauseAdInternal(AdMediaInfo adMediaInfo) {
        if (this.configuration.debugModeEnabled) {
            Log.d(TAG, "pauseAd " + getAdMediaInfoString(adMediaInfo));
        }
        if (this.adsManager != null && this.imaAdState != 0) {
            if (this.configuration.debugModeEnabled && !adMediaInfo.equals(this.imaAdMediaInfo)) {
                Log.w(TAG, "Unexpected pauseAd for " + getAdMediaInfoString(adMediaInfo) + ", expected " + getAdMediaInfoString(this.imaAdMediaInfo));
            }
            this.imaAdState = 2;
            for (int i = 0; i < this.adCallbacks.size(); i++) {
                this.adCallbacks.get(i).onPause(adMediaInfo);
            }
        }
    }

    /* access modifiers changed from: private */
    public void stopAdInternal(AdMediaInfo adMediaInfo) {
        if (this.configuration.debugModeEnabled) {
            Log.d(TAG, "stopAd " + getAdMediaInfoString(adMediaInfo));
        }
        if (this.adsManager != null) {
            if (this.imaAdState == 0) {
                AdInfo adInfo = (AdInfo) this.adInfoByAdMediaInfo.get(adMediaInfo);
                if (adInfo != null) {
                    this.adPlaybackState = this.adPlaybackState.withSkippedAd(adInfo.adGroupIndex, adInfo.adIndexInAdGroup);
                    updateAdPlaybackState();
                    return;
                }
                return;
            }
            this.imaAdState = 0;
            stopUpdatingAdProgress();
            Assertions.checkNotNull(this.imaAdInfo);
            int i = this.imaAdInfo.adGroupIndex;
            int i2 = this.imaAdInfo.adIndexInAdGroup;
            if (!this.adPlaybackState.isAdInErrorState(i, i2)) {
                this.adPlaybackState = this.adPlaybackState.withPlayedAd(i, i2).withAdResumePositionUs(0);
                updateAdPlaybackState();
                if (!this.playingAd) {
                    this.imaAdMediaInfo = null;
                    this.imaAdInfo = null;
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void handleAdGroupLoadError(Exception exc) {
        int loadingAdGroupIndex = getLoadingAdGroupIndex();
        if (loadingAdGroupIndex == -1) {
            Log.w(TAG, "Unable to determine ad group index for ad group load error", exc);
            return;
        }
        markAdGroupInErrorStateAndClearPendingContentPosition(loadingAdGroupIndex);
        if (this.pendingAdLoadError == null) {
            this.pendingAdLoadError = AdsMediaSource.AdLoadException.createForAdGroup(exc, loadingAdGroupIndex);
        }
    }

    /* access modifiers changed from: private */
    public void handleAdLoadTimeout() {
        handleAdGroupLoadError(new IOException("Ad loading timed out"));
        maybeNotifyPendingAdLoadError();
    }

    private void markAdGroupInErrorStateAndClearPendingContentPosition(int i) {
        AdPlaybackState.AdGroup adGroup = this.adPlaybackState.getAdGroup(i);
        if (adGroup.count == -1) {
            AdPlaybackState withAdCount = this.adPlaybackState.withAdCount(i, Math.max(1, adGroup.states.length));
            this.adPlaybackState = withAdCount;
            adGroup = withAdCount.getAdGroup(i);
        }
        for (int i2 = 0; i2 < adGroup.count; i2++) {
            if (adGroup.states[i2] == 0) {
                if (this.configuration.debugModeEnabled) {
                    Log.d(TAG, "Removing ad " + i2 + " in ad group " + i);
                }
                this.adPlaybackState = this.adPlaybackState.withAdLoadError(i, i2);
            }
        }
        updateAdPlaybackState();
        this.pendingContentPositionMs = C.TIME_UNSET;
        this.fakeContentProgressElapsedRealtimeMs = C.TIME_UNSET;
    }

    private void handleAdPrepareError(int i, int i2, Exception exc) {
        if (this.configuration.debugModeEnabled) {
            Log.d(TAG, "Prepare error for ad " + i2 + " in group " + i, exc);
        }
        if (this.adsManager == null) {
            Log.w(TAG, "Ignoring ad prepare error after release");
            return;
        }
        if (this.imaAdState == 0) {
            this.fakeContentProgressElapsedRealtimeMs = SystemClock.elapsedRealtime();
            long usToMs = Util.usToMs(this.adPlaybackState.getAdGroup(i).timeUs);
            this.fakeContentProgressOffsetMs = usToMs;
            if (usToMs == Long.MIN_VALUE) {
                this.fakeContentProgressOffsetMs = this.contentDurationMs;
            }
            this.pendingAdPrepareErrorAdInfo = new AdInfo(i, i2);
        } else {
            AdMediaInfo adMediaInfo = (AdMediaInfo) Assertions.checkNotNull(this.imaAdMediaInfo);
            if (i2 > this.playingAdIndexInAdGroup) {
                for (int i3 = 0; i3 < this.adCallbacks.size(); i3++) {
                    this.adCallbacks.get(i3).onEnded(adMediaInfo);
                }
            }
            this.playingAdIndexInAdGroup = this.adPlaybackState.getAdGroup(i).getFirstAdIndexToPlay();
            for (int i4 = 0; i4 < this.adCallbacks.size(); i4++) {
                this.adCallbacks.get(i4).onError((AdMediaInfo) Assertions.checkNotNull(adMediaInfo));
            }
        }
        this.adPlaybackState = this.adPlaybackState.withAdLoadError(i, i2);
        updateAdPlaybackState();
    }

    private void ensureSentContentCompleteIfAtEndOfStream() {
        if (!this.sentContentComplete && this.contentDurationMs != C.TIME_UNSET && this.pendingContentPositionMs == C.TIME_UNSET) {
            long contentPeriodPositionMs = getContentPeriodPositionMs((Player) Assertions.checkNotNull(this.player), this.timeline, this.period);
            if (5000 + contentPeriodPositionMs >= this.contentDurationMs) {
                int adGroupIndexForPositionUs = this.adPlaybackState.getAdGroupIndexForPositionUs(Util.msToUs(contentPeriodPositionMs), Util.msToUs(this.contentDurationMs));
                if (adGroupIndexForPositionUs == -1 || this.adPlaybackState.getAdGroup(adGroupIndexForPositionUs).timeUs == Long.MIN_VALUE || !this.adPlaybackState.getAdGroup(adGroupIndexForPositionUs).shouldPlayAdGroup()) {
                    sendContentComplete();
                }
            }
        }
    }

    private void sendContentComplete() {
        for (int i = 0; i < this.adCallbacks.size(); i++) {
            this.adCallbacks.get(i).onContentComplete();
        }
        this.sentContentComplete = true;
        if (this.configuration.debugModeEnabled) {
            Log.d(TAG, "adsLoader.contentComplete");
        }
        for (int i2 = 0; i2 < this.adPlaybackState.adGroupCount; i2++) {
            if (this.adPlaybackState.getAdGroup(i2).timeUs != Long.MIN_VALUE) {
                this.adPlaybackState = this.adPlaybackState.withSkippedAdGroup(i2);
            }
        }
        updateAdPlaybackState();
    }

    /* access modifiers changed from: private */
    public void updateAdPlaybackState() {
        for (int i = 0; i < this.eventListeners.size(); i++) {
            this.eventListeners.get(i).onAdPlaybackState(this.adPlaybackState);
        }
    }

    /* access modifiers changed from: private */
    public void maybeNotifyPendingAdLoadError() {
        if (this.pendingAdLoadError != null) {
            for (int i = 0; i < this.eventListeners.size(); i++) {
                this.eventListeners.get(i).onAdLoadError(this.pendingAdLoadError, this.adTagDataSpec);
            }
            this.pendingAdLoadError = null;
        }
    }

    /* access modifiers changed from: private */
    public void maybeNotifyInternalError(String str, Exception exc) {
        String str2 = "Internal error in " + str;
        Log.e(TAG, str2, exc);
        for (int i = 0; i < this.adPlaybackState.adGroupCount; i++) {
            this.adPlaybackState = this.adPlaybackState.withSkippedAdGroup(i);
        }
        updateAdPlaybackState();
        for (int i2 = 0; i2 < this.eventListeners.size(); i2++) {
            this.eventListeners.get(i2).onAdLoadError(AdsMediaSource.AdLoadException.createForUnexpected(new RuntimeException(str2, exc)), this.adTagDataSpec);
        }
    }

    private int getAdGroupIndexForAdPod(AdPodInfo adPodInfo) {
        if (adPodInfo.getPodIndex() == -1) {
            return this.adPlaybackState.adGroupCount - 1;
        }
        return getAdGroupIndexForCuePointTimeSeconds(adPodInfo.getTimeOffset());
    }

    private int getLoadingAdGroupIndex() {
        Player player2 = this.player;
        if (player2 == null) {
            return -1;
        }
        long msToUs = Util.msToUs(getContentPeriodPositionMs(player2, this.timeline, this.period));
        int adGroupIndexForPositionUs = this.adPlaybackState.getAdGroupIndexForPositionUs(msToUs, Util.msToUs(this.contentDurationMs));
        return adGroupIndexForPositionUs == -1 ? this.adPlaybackState.getAdGroupIndexAfterPositionUs(msToUs, Util.msToUs(this.contentDurationMs)) : adGroupIndexForPositionUs;
    }

    private int getAdGroupIndexForCuePointTimeSeconds(double d) {
        long round = Math.round(((double) ((float) d)) * 1000000.0d);
        for (int i = 0; i < this.adPlaybackState.adGroupCount; i++) {
            long j = this.adPlaybackState.getAdGroup(i).timeUs;
            if (j != Long.MIN_VALUE && Math.abs(j - round) < 1000) {
                return i;
            }
        }
        throw new IllegalStateException("Failed to find cue point");
    }

    private String getAdMediaInfoString(AdMediaInfo adMediaInfo) {
        String str;
        AdInfo adInfo = (AdInfo) this.adInfoByAdMediaInfo.get(adMediaInfo);
        StringBuilder sb = new StringBuilder("AdMediaInfo[");
        if (adMediaInfo == null) {
            str = "null";
        } else {
            str = adMediaInfo.getUrl();
        }
        return sb.append(str).append(", ").append(adInfo).append("]").toString();
    }

    private static long getContentPeriodPositionMs(Player player2, Timeline timeline2, Timeline.Period period2) {
        long contentPosition = player2.getContentPosition();
        if (timeline2.isEmpty()) {
            return contentPosition;
        }
        return contentPosition - timeline2.getPeriod(player2.getCurrentPeriodIndex(), period2).getPositionInWindowMs();
    }

    private static boolean hasMidrollAdGroups(AdPlaybackState adPlaybackState2) {
        int i = adPlaybackState2.adGroupCount;
        if (i == 1) {
            long j = adPlaybackState2.getAdGroup(0).timeUs;
            if (j == 0 || j == Long.MIN_VALUE) {
                return false;
            }
            return true;
        } else if (i != 2) {
            return true;
        } else {
            if (adPlaybackState2.getAdGroup(0).timeUs == 0 && adPlaybackState2.getAdGroup(1).timeUs == Long.MIN_VALUE) {
                return false;
            }
            return true;
        }
    }

    private void destroyAdsManager() {
        AdsManager adsManager2 = this.adsManager;
        if (adsManager2 != null) {
            adsManager2.removeAdErrorListener(this.componentListener);
            if (this.configuration.applicationAdErrorListener != null) {
                this.adsManager.removeAdErrorListener(this.configuration.applicationAdErrorListener);
            }
            this.adsManager.removeAdEventListener(this.componentListener);
            if (this.configuration.applicationAdEventListener != null) {
                this.adsManager.removeAdEventListener(this.configuration.applicationAdEventListener);
            }
            this.adsManager.destroy();
            this.adsManager = null;
        }
    }

    private final class ComponentListener implements AdsLoader.AdsLoadedListener, ContentProgressProvider, AdEvent.AdEventListener, AdErrorEvent.AdErrorListener, VideoAdPlayer {
        public void release() {
        }

        private ComponentListener() {
        }

        /* synthetic */ ComponentListener(AdTagLoader adTagLoader, AnonymousClass1 r2) {
            this();
        }

        public void onAdsManagerLoaded(AdsManagerLoadedEvent adsManagerLoadedEvent) {
            AdsManager adsManager = adsManagerLoadedEvent.getAdsManager();
            if (!Util.areEqual(AdTagLoader.this.pendingAdRequestContext, adsManagerLoadedEvent.getUserRequestContext())) {
                adsManager.destroy();
                return;
            }
            Object unused = AdTagLoader.this.pendingAdRequestContext = null;
            AdsManager unused2 = AdTagLoader.this.adsManager = adsManager;
            adsManager.addAdErrorListener(this);
            if (AdTagLoader.this.configuration.applicationAdErrorListener != null) {
                adsManager.addAdErrorListener(AdTagLoader.this.configuration.applicationAdErrorListener);
            }
            adsManager.addAdEventListener(this);
            if (AdTagLoader.this.configuration.applicationAdEventListener != null) {
                adsManager.addAdEventListener(AdTagLoader.this.configuration.applicationAdEventListener);
            }
            try {
                AdPlaybackState unused3 = AdTagLoader.this.adPlaybackState = new AdPlaybackState(AdTagLoader.this.adsId, ImaUtil.getAdGroupTimesUsForCuePoints(adsManager.getAdCuePoints()));
                AdTagLoader.this.updateAdPlaybackState();
            } catch (RuntimeException e) {
                AdTagLoader.this.maybeNotifyInternalError("onAdsManagerLoaded", e);
            }
        }

        public VideoProgressUpdate getContentProgress() {
            VideoProgressUpdate access$800 = AdTagLoader.this.getContentVideoProgressUpdate();
            if (AdTagLoader.this.configuration.debugModeEnabled) {
                Log.d(AdTagLoader.TAG, "Content progress: " + ImaUtil.getStringForVideoProgressUpdate(access$800));
            }
            if (AdTagLoader.this.waitingForPreloadElapsedRealtimeMs != C.TIME_UNSET) {
                if (SystemClock.elapsedRealtime() - AdTagLoader.this.waitingForPreloadElapsedRealtimeMs >= AdTagLoader.THRESHOLD_AD_PRELOAD_MS) {
                    long unused = AdTagLoader.this.waitingForPreloadElapsedRealtimeMs = C.TIME_UNSET;
                    AdTagLoader.this.handleAdGroupLoadError(new IOException("Ad preloading timed out"));
                    AdTagLoader.this.maybeNotifyPendingAdLoadError();
                }
            } else if (AdTagLoader.this.pendingContentPositionMs != C.TIME_UNSET && AdTagLoader.this.player != null && AdTagLoader.this.player.getPlaybackState() == 2 && AdTagLoader.this.isWaitingForFirstAdToPreload()) {
                long unused2 = AdTagLoader.this.waitingForPreloadElapsedRealtimeMs = SystemClock.elapsedRealtime();
            }
            return access$800;
        }

        public void onAdEvent(AdEvent adEvent) {
            AdEvent.AdEventType type = adEvent.getType();
            if (AdTagLoader.this.configuration.debugModeEnabled && type != AdEvent.AdEventType.AD_PROGRESS) {
                Log.d(AdTagLoader.TAG, "onAdEvent: " + type);
            }
            try {
                AdTagLoader.this.handleAdEvent(adEvent);
            } catch (RuntimeException e) {
                AdTagLoader.this.maybeNotifyInternalError("onAdEvent", e);
            }
        }

        public void onAdError(AdErrorEvent adErrorEvent) {
            AdError error = adErrorEvent.getError();
            if (AdTagLoader.this.configuration.debugModeEnabled) {
                Log.d(AdTagLoader.TAG, "onAdError", error);
            }
            if (AdTagLoader.this.adsManager == null) {
                Object unused = AdTagLoader.this.pendingAdRequestContext = null;
                AdPlaybackState unused2 = AdTagLoader.this.adPlaybackState = new AdPlaybackState(AdTagLoader.this.adsId, new long[0]);
                AdTagLoader.this.updateAdPlaybackState();
            } else if (ImaUtil.isAdGroupLoadError(error)) {
                try {
                    AdTagLoader.this.handleAdGroupLoadError(error);
                } catch (RuntimeException e) {
                    AdTagLoader.this.maybeNotifyInternalError("onAdError", e);
                }
            }
            if (AdTagLoader.this.pendingAdLoadError == null) {
                AdsMediaSource.AdLoadException unused3 = AdTagLoader.this.pendingAdLoadError = AdsMediaSource.AdLoadException.createForAllAds(error);
            }
            AdTagLoader.this.maybeNotifyPendingAdLoadError();
        }

        public void addCallback(VideoAdPlayer.VideoAdPlayerCallback videoAdPlayerCallback) {
            AdTagLoader.this.adCallbacks.add(videoAdPlayerCallback);
        }

        public void removeCallback(VideoAdPlayer.VideoAdPlayerCallback videoAdPlayerCallback) {
            AdTagLoader.this.adCallbacks.remove(videoAdPlayerCallback);
        }

        public VideoProgressUpdate getAdProgress() {
            throw new IllegalStateException("Unexpected call to getAdProgress when using preloading");
        }

        public int getVolume() {
            return AdTagLoader.this.getPlayerVolumePercent();
        }

        public void loadAd(AdMediaInfo adMediaInfo, AdPodInfo adPodInfo) {
            try {
                AdTagLoader.this.loadAdInternal(adMediaInfo, adPodInfo);
            } catch (RuntimeException e) {
                AdTagLoader.this.maybeNotifyInternalError("loadAd", e);
            }
        }

        public void playAd(AdMediaInfo adMediaInfo) {
            try {
                AdTagLoader.this.playAdInternal(adMediaInfo);
            } catch (RuntimeException e) {
                AdTagLoader.this.maybeNotifyInternalError("playAd", e);
            }
        }

        public void pauseAd(AdMediaInfo adMediaInfo) {
            try {
                AdTagLoader.this.pauseAdInternal(adMediaInfo);
            } catch (RuntimeException e) {
                AdTagLoader.this.maybeNotifyInternalError("pauseAd", e);
            }
        }

        public void stopAd(AdMediaInfo adMediaInfo) {
            try {
                AdTagLoader.this.stopAdInternal(adMediaInfo);
            } catch (RuntimeException e) {
                AdTagLoader.this.maybeNotifyInternalError("stopAd", e);
            }
        }
    }

    private static final class AdInfo {
        public final int adGroupIndex;
        public final int adIndexInAdGroup;

        public AdInfo(int i, int i2) {
            this.adGroupIndex = i;
            this.adIndexInAdGroup = i2;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            AdInfo adInfo = (AdInfo) obj;
            if (this.adGroupIndex != adInfo.adGroupIndex) {
                return false;
            }
            if (this.adIndexInAdGroup == adInfo.adIndexInAdGroup) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return (this.adGroupIndex * 31) + this.adIndexInAdGroup;
        }

        public String toString() {
            return "(" + this.adGroupIndex + ", " + this.adIndexInAdGroup + ')';
        }
    }
}
