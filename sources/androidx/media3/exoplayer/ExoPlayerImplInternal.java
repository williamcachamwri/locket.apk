package androidx.media3.exoplayer;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Pair;
import androidx.media3.common.C;
import androidx.media3.common.Format;
import androidx.media3.common.MediaItem;
import androidx.media3.common.Metadata;
import androidx.media3.common.ParserException;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.Timeline;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.HandlerWrapper;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSourceException;
import androidx.media3.exoplayer.DefaultMediaClock;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.exoplayer.LoadControl;
import androidx.media3.exoplayer.LoadingInfo;
import androidx.media3.exoplayer.MediaSourceList;
import androidx.media3.exoplayer.PlayerMessage;
import androidx.media3.exoplayer.Renderer;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.exoplayer.analytics.AnalyticsCollector;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.drm.DrmSession;
import androidx.media3.exoplayer.metadata.MetadataRenderer;
import androidx.media3.exoplayer.source.BehindLiveWindowException;
import androidx.media3.exoplayer.source.MediaPeriod;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.SampleStream;
import androidx.media3.exoplayer.source.ShuffleOrder;
import androidx.media3.exoplayer.source.TrackGroupArray;
import androidx.media3.exoplayer.text.TextRenderer;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import androidx.media3.exoplayer.trackselection.TrackSelector;
import androidx.media3.exoplayer.trackselection.TrackSelectorResult;
import androidx.media3.exoplayer.upstream.BandwidthMeter;
import com.google.common.base.Supplier;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Sets;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

final class ExoPlayerImplInternal implements Handler.Callback, MediaPeriod.Callback, TrackSelector.InvalidationListener, MediaSourceList.MediaSourceListInfoRefreshListener, DefaultMediaClock.PlaybackParametersListener, PlayerMessage.Sender {
    private static final long BUFFERING_MAXIMUM_INTERVAL_MS = Util.usToMs(10000);
    private static final int MSG_ADD_MEDIA_SOURCES = 18;
    private static final int MSG_ATTEMPT_RENDERER_ERROR_RECOVERY = 25;
    private static final int MSG_DO_SOME_WORK = 2;
    private static final int MSG_MOVE_MEDIA_SOURCES = 19;
    private static final int MSG_PERIOD_PREPARED = 8;
    private static final int MSG_PLAYBACK_PARAMETERS_CHANGED_INTERNAL = 16;
    private static final int MSG_PLAYLIST_UPDATE_REQUESTED = 22;
    private static final int MSG_PREPARE = 29;
    private static final int MSG_RELEASE = 7;
    private static final int MSG_REMOVE_MEDIA_SOURCES = 20;
    private static final int MSG_RENDERER_CAPABILITIES_CHANGED = 26;
    private static final int MSG_SEEK_TO = 3;
    private static final int MSG_SEND_MESSAGE = 14;
    private static final int MSG_SEND_MESSAGE_TO_TARGET_THREAD = 15;
    private static final int MSG_SET_FOREGROUND_MODE = 13;
    private static final int MSG_SET_MEDIA_SOURCES = 17;
    private static final int MSG_SET_PAUSE_AT_END_OF_WINDOW = 23;
    private static final int MSG_SET_PLAYBACK_PARAMETERS = 4;
    private static final int MSG_SET_PLAY_WHEN_READY = 1;
    private static final int MSG_SET_PRELOAD_CONFIGURATION = 28;
    private static final int MSG_SET_REPEAT_MODE = 11;
    private static final int MSG_SET_SEEK_PARAMETERS = 5;
    private static final int MSG_SET_SHUFFLE_ENABLED = 12;
    private static final int MSG_SET_SHUFFLE_ORDER = 21;
    private static final int MSG_SOURCE_CONTINUE_LOADING_REQUESTED = 9;
    private static final int MSG_STOP = 6;
    private static final int MSG_TRACK_SELECTION_INVALIDATED = 10;
    private static final int MSG_UPDATE_MEDIA_SOURCES_WITH_MEDIA_ITEMS = 27;
    private static final long PLAYBACK_BUFFER_EMPTY_THRESHOLD_US = 500000;
    private static final long PLAYBACK_STUCK_AFTER_MS = 4000;
    private static final long READY_MAXIMUM_INTERVAL_MS = 1000;
    private static final String TAG = "ExoPlayerImplInternal";
    private final AnalyticsCollector analyticsCollector;
    private final HandlerWrapper applicationLooperHandler;
    private final long backBufferDurationUs;
    private final BandwidthMeter bandwidthMeter;
    private final Clock clock;
    private boolean deliverPendingMessageAtStartPositionRequired;
    /* access modifiers changed from: private */
    public final boolean dynamicSchedulingEnabled;
    private final TrackSelectorResult emptyTrackSelectorResult;
    private int enabledRendererCount;
    private boolean foregroundMode;
    /* access modifiers changed from: private */
    public final HandlerWrapper handler;
    private boolean isRebuffering;
    private Timeline lastPreloadPoolInvalidationTimeline;
    private long lastRebufferRealtimeMs = C.TIME_UNSET;
    private final LivePlaybackSpeedControl livePlaybackSpeedControl;
    private final LoadControl loadControl;
    private final DefaultMediaClock mediaClock;
    private final MediaSourceList mediaSourceList;
    private int nextPendingMessageIndexHint;
    /* access modifiers changed from: private */
    public boolean offloadSchedulingEnabled;
    private boolean pauseAtEndOfWindow;
    private SeekPosition pendingInitialSeekPosition;
    private final ArrayList<PendingMessageInfo> pendingMessages;
    private boolean pendingPauseAtEndOfPeriod;
    private ExoPlaybackException pendingRecoverableRendererError;
    private final Timeline.Period period;
    private PlaybackInfo playbackInfo;
    private PlaybackInfoUpdate playbackInfoUpdate;
    private final PlaybackInfoUpdateListener playbackInfoUpdateListener;
    private final Looper playbackLooper;
    private final PlaybackLooperProvider playbackLooperProvider;
    private long playbackMaybeBecameStuckAtMs = C.TIME_UNSET;
    private final PlayerId playerId;
    private ExoPlayer.PreloadConfiguration preloadConfiguration;
    private final MediaPeriodQueue queue;
    private final long releaseTimeoutMs;
    private boolean released;
    private final RendererCapabilities[] rendererCapabilities;
    private long rendererPositionElapsedRealtimeUs;
    private long rendererPositionUs;
    private final boolean[] rendererReportedReady;
    private final Renderer[] renderers;
    private final Set<Renderer> renderersToReset;
    private int repeatMode;
    /* access modifiers changed from: private */
    public boolean requestForRendererSleep;
    private final boolean retainBackBufferFromKeyframe;
    private SeekParameters seekParameters;
    private long setForegroundModeTimeoutMs;
    private boolean shouldContinueLoading;
    private boolean shuffleModeEnabled;
    private final TrackSelector trackSelector;
    private final Timeline.Window window;

    public interface PlaybackInfoUpdateListener {
        void onPlaybackInfoUpdate(PlaybackInfoUpdate playbackInfoUpdate);
    }

    public static final class PlaybackInfoUpdate {
        public int discontinuityReason;
        /* access modifiers changed from: private */
        public boolean hasPendingChange;
        public int operationAcks;
        public PlaybackInfo playbackInfo;
        public boolean positionDiscontinuity;

        public PlaybackInfoUpdate(PlaybackInfo playbackInfo2) {
            this.playbackInfo = playbackInfo2;
        }

        public void incrementPendingOperationAcks(int i) {
            this.hasPendingChange |= i > 0;
            this.operationAcks += i;
        }

        public void setPlaybackInfo(PlaybackInfo playbackInfo2) {
            this.hasPendingChange |= this.playbackInfo != playbackInfo2;
            this.playbackInfo = playbackInfo2;
        }

        public void setPositionDiscontinuity(int i) {
            boolean z = true;
            if (!this.positionDiscontinuity || this.discontinuityReason == 5) {
                this.hasPendingChange = true;
                this.positionDiscontinuity = true;
                this.discontinuityReason = i;
                return;
            }
            if (i != 5) {
                z = false;
            }
            Assertions.checkArgument(z);
        }
    }

    public ExoPlayerImplInternal(Renderer[] rendererArr, TrackSelector trackSelector2, TrackSelectorResult trackSelectorResult, LoadControl loadControl2, BandwidthMeter bandwidthMeter2, int i, boolean z, AnalyticsCollector analyticsCollector2, SeekParameters seekParameters2, LivePlaybackSpeedControl livePlaybackSpeedControl2, long j, boolean z2, boolean z3, Looper looper, Clock clock2, PlaybackInfoUpdateListener playbackInfoUpdateListener2, PlayerId playerId2, PlaybackLooperProvider playbackLooperProvider2, ExoPlayer.PreloadConfiguration preloadConfiguration2) {
        Renderer[] rendererArr2 = rendererArr;
        LoadControl loadControl3 = loadControl2;
        BandwidthMeter bandwidthMeter3 = bandwidthMeter2;
        AnalyticsCollector analyticsCollector3 = analyticsCollector2;
        long j2 = j;
        Clock clock3 = clock2;
        PlayerId playerId3 = playerId2;
        ExoPlayer.PreloadConfiguration preloadConfiguration3 = preloadConfiguration2;
        this.playbackInfoUpdateListener = playbackInfoUpdateListener2;
        this.renderers = rendererArr2;
        this.trackSelector = trackSelector2;
        this.emptyTrackSelectorResult = trackSelectorResult;
        this.loadControl = loadControl3;
        this.bandwidthMeter = bandwidthMeter3;
        this.repeatMode = i;
        this.shuffleModeEnabled = z;
        this.seekParameters = seekParameters2;
        this.livePlaybackSpeedControl = livePlaybackSpeedControl2;
        this.releaseTimeoutMs = j2;
        this.setForegroundModeTimeoutMs = j2;
        this.pauseAtEndOfWindow = z2;
        this.dynamicSchedulingEnabled = z3;
        this.clock = clock3;
        this.playerId = playerId3;
        this.preloadConfiguration = preloadConfiguration3;
        this.analyticsCollector = analyticsCollector3;
        this.backBufferDurationUs = loadControl3.getBackBufferDurationUs(playerId3);
        this.retainBackBufferFromKeyframe = loadControl3.retainBackBufferFromKeyframe(playerId3);
        this.lastPreloadPoolInvalidationTimeline = Timeline.EMPTY;
        this.playbackInfo = PlaybackInfo.createDummy(trackSelectorResult);
        this.playbackInfoUpdate = new PlaybackInfoUpdate(this.playbackInfo);
        this.rendererCapabilities = new RendererCapabilities[rendererArr2.length];
        this.rendererReportedReady = new boolean[rendererArr2.length];
        RendererCapabilities.Listener rendererCapabilitiesListener = trackSelector2.getRendererCapabilitiesListener();
        for (int i2 = 0; i2 < rendererArr2.length; i2++) {
            rendererArr2[i2].init(i2, playerId3, clock3);
            this.rendererCapabilities[i2] = rendererArr2[i2].getCapabilities();
            if (rendererCapabilitiesListener != null) {
                this.rendererCapabilities[i2].setListener(rendererCapabilitiesListener);
            }
        }
        this.mediaClock = new DefaultMediaClock(this, clock3);
        this.pendingMessages = new ArrayList<>();
        this.renderersToReset = Sets.newIdentityHashSet();
        this.window = new Timeline.Window();
        this.period = new Timeline.Period();
        trackSelector2.init(this, bandwidthMeter3);
        this.deliverPendingMessageAtStartPositionRequired = true;
        HandlerWrapper createHandler = clock3.createHandler(looper, (Handler.Callback) null);
        this.applicationLooperHandler = createHandler;
        this.queue = new MediaPeriodQueue(analyticsCollector3, createHandler, new ExoPlayerImplInternal$$ExternalSyntheticLambda4(this), preloadConfiguration3);
        this.mediaSourceList = new MediaSourceList(this, analyticsCollector3, createHandler, playerId3);
        PlaybackLooperProvider playbackLooperProvider3 = playbackLooperProvider2 == null ? new PlaybackLooperProvider() : playbackLooperProvider2;
        this.playbackLooperProvider = playbackLooperProvider3;
        Looper obtainLooper = playbackLooperProvider3.obtainLooper();
        this.playbackLooper = obtainLooper;
        this.handler = clock3.createHandler(obtainLooper, this);
    }

    /* access modifiers changed from: private */
    public MediaPeriodHolder createMediaPeriodHolder(MediaPeriodInfo mediaPeriodInfo, long j) {
        return new MediaPeriodHolder(this.rendererCapabilities, j, this.trackSelector, this.loadControl.getAllocator(), this.mediaSourceList, mediaPeriodInfo, this.emptyTrackSelectorResult, this.preloadConfiguration.targetPreloadDurationUs);
    }

    public void experimentalSetForegroundModeTimeoutMs(long j) {
        this.setForegroundModeTimeoutMs = j;
    }

    public void prepare() {
        this.handler.obtainMessage(29).sendToTarget();
    }

    public void setPlayWhenReady(boolean z, int i, int i2) {
        this.handler.obtainMessage(1, z ? 1 : 0, i | (i2 << 4)).sendToTarget();
    }

    public void setPauseAtEndOfWindow(boolean z) {
        this.handler.obtainMessage(23, z ? 1 : 0, 0).sendToTarget();
    }

    public void setRepeatMode(int i) {
        this.handler.obtainMessage(11, i, 0).sendToTarget();
    }

    public void setShuffleModeEnabled(boolean z) {
        this.handler.obtainMessage(12, z ? 1 : 0, 0).sendToTarget();
    }

    public void setPreloadConfiguration(ExoPlayer.PreloadConfiguration preloadConfiguration2) {
        this.handler.obtainMessage(28, preloadConfiguration2).sendToTarget();
    }

    public void seekTo(Timeline timeline, int i, long j) {
        this.handler.obtainMessage(3, new SeekPosition(timeline, i, j)).sendToTarget();
    }

    public void setPlaybackParameters(PlaybackParameters playbackParameters) {
        this.handler.obtainMessage(4, playbackParameters).sendToTarget();
    }

    public void setSeekParameters(SeekParameters seekParameters2) {
        this.handler.obtainMessage(5, seekParameters2).sendToTarget();
    }

    public void stop() {
        this.handler.obtainMessage(6).sendToTarget();
    }

    public void setMediaSources(List<MediaSourceList.MediaSourceHolder> list, int i, long j, ShuffleOrder shuffleOrder) {
        this.handler.obtainMessage(17, new MediaSourceListUpdateMessage(list, shuffleOrder, i, j)).sendToTarget();
    }

    public void addMediaSources(int i, List<MediaSourceList.MediaSourceHolder> list, ShuffleOrder shuffleOrder) {
        this.handler.obtainMessage(18, i, 0, new MediaSourceListUpdateMessage(list, shuffleOrder, -1, C.TIME_UNSET)).sendToTarget();
    }

    public void removeMediaSources(int i, int i2, ShuffleOrder shuffleOrder) {
        this.handler.obtainMessage(20, i, i2, shuffleOrder).sendToTarget();
    }

    public void moveMediaSources(int i, int i2, int i3, ShuffleOrder shuffleOrder) {
        this.handler.obtainMessage(19, new MoveMediaItemsMessage(i, i2, i3, shuffleOrder)).sendToTarget();
    }

    public void setShuffleOrder(ShuffleOrder shuffleOrder) {
        this.handler.obtainMessage(21, shuffleOrder).sendToTarget();
    }

    public void updateMediaSourcesWithMediaItems(int i, int i2, List<MediaItem> list) {
        this.handler.obtainMessage(27, i, i2, list).sendToTarget();
    }

    public synchronized void sendMessage(PlayerMessage playerMessage) {
        if (!this.released) {
            if (this.playbackLooper.getThread().isAlive()) {
                this.handler.obtainMessage(14, playerMessage).sendToTarget();
                return;
            }
        }
        Log.w(TAG, "Ignoring messages sent after release.");
        playerMessage.markAsProcessed(false);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0045, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean setForegroundMode(boolean r4) {
        /*
            r3 = this;
            monitor-enter(r3)
            boolean r0 = r3.released     // Catch:{ all -> 0x0046 }
            r1 = 1
            if (r0 != 0) goto L_0x0044
            android.os.Looper r0 = r3.playbackLooper     // Catch:{ all -> 0x0046 }
            java.lang.Thread r0 = r0.getThread()     // Catch:{ all -> 0x0046 }
            boolean r0 = r0.isAlive()     // Catch:{ all -> 0x0046 }
            if (r0 != 0) goto L_0x0013
            goto L_0x0044
        L_0x0013:
            r0 = 13
            r2 = 0
            if (r4 == 0) goto L_0x0023
            androidx.media3.common.util.HandlerWrapper r4 = r3.handler     // Catch:{ all -> 0x0046 }
            androidx.media3.common.util.HandlerWrapper$Message r4 = r4.obtainMessage(r0, r1, r2)     // Catch:{ all -> 0x0046 }
            r4.sendToTarget()     // Catch:{ all -> 0x0046 }
            monitor-exit(r3)
            return r1
        L_0x0023:
            java.util.concurrent.atomic.AtomicBoolean r4 = new java.util.concurrent.atomic.AtomicBoolean     // Catch:{ all -> 0x0046 }
            r4.<init>()     // Catch:{ all -> 0x0046 }
            androidx.media3.common.util.HandlerWrapper r1 = r3.handler     // Catch:{ all -> 0x0046 }
            androidx.media3.common.util.HandlerWrapper$Message r0 = r1.obtainMessage(r0, r2, r2, r4)     // Catch:{ all -> 0x0046 }
            r0.sendToTarget()     // Catch:{ all -> 0x0046 }
            java.util.Objects.requireNonNull(r4)     // Catch:{ all -> 0x0046 }
            androidx.media3.exoplayer.ExoPlayerImplInternal$$ExternalSyntheticLambda2 r0 = new androidx.media3.exoplayer.ExoPlayerImplInternal$$ExternalSyntheticLambda2     // Catch:{ all -> 0x0046 }
            r0.<init>(r4)     // Catch:{ all -> 0x0046 }
            long r1 = r3.setForegroundModeTimeoutMs     // Catch:{ all -> 0x0046 }
            r3.waitUninterruptibly(r0, r1)     // Catch:{ all -> 0x0046 }
            boolean r4 = r4.get()     // Catch:{ all -> 0x0046 }
            monitor-exit(r3)
            return r4
        L_0x0044:
            monitor-exit(r3)
            return r1
        L_0x0046:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.ExoPlayerImplInternal.setForegroundMode(boolean):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0027, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean release() {
        /*
            r3 = this;
            monitor-enter(r3)
            boolean r0 = r3.released     // Catch:{ all -> 0x0029 }
            if (r0 != 0) goto L_0x0026
            android.os.Looper r0 = r3.playbackLooper     // Catch:{ all -> 0x0029 }
            java.lang.Thread r0 = r0.getThread()     // Catch:{ all -> 0x0029 }
            boolean r0 = r0.isAlive()     // Catch:{ all -> 0x0029 }
            if (r0 != 0) goto L_0x0012
            goto L_0x0026
        L_0x0012:
            androidx.media3.common.util.HandlerWrapper r0 = r3.handler     // Catch:{ all -> 0x0029 }
            r1 = 7
            r0.sendEmptyMessage(r1)     // Catch:{ all -> 0x0029 }
            androidx.media3.exoplayer.ExoPlayerImplInternal$$ExternalSyntheticLambda1 r0 = new androidx.media3.exoplayer.ExoPlayerImplInternal$$ExternalSyntheticLambda1     // Catch:{ all -> 0x0029 }
            r0.<init>(r3)     // Catch:{ all -> 0x0029 }
            long r1 = r3.releaseTimeoutMs     // Catch:{ all -> 0x0029 }
            r3.waitUninterruptibly(r0, r1)     // Catch:{ all -> 0x0029 }
            boolean r0 = r3.released     // Catch:{ all -> 0x0029 }
            monitor-exit(r3)
            return r0
        L_0x0026:
            monitor-exit(r3)
            r0 = 1
            return r0
        L_0x0029:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.ExoPlayerImplInternal.release():boolean");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$release$0$androidx-media3-exoplayer-ExoPlayerImplInternal  reason: not valid java name */
    public /* synthetic */ Boolean m472lambda$release$0$androidxmedia3exoplayerExoPlayerImplInternal() {
        return Boolean.valueOf(this.released);
    }

    public Looper getPlaybackLooper() {
        return this.playbackLooper;
    }

    public void onPlaylistUpdateRequested() {
        this.handler.removeMessages(2);
        this.handler.sendEmptyMessage(22);
    }

    public void onPrepared(MediaPeriod mediaPeriod) {
        this.handler.obtainMessage(8, mediaPeriod).sendToTarget();
    }

    public void onContinueLoadingRequested(MediaPeriod mediaPeriod) {
        this.handler.obtainMessage(9, mediaPeriod).sendToTarget();
    }

    public void onTrackSelectionsInvalidated() {
        this.handler.sendEmptyMessage(10);
    }

    public void onRendererCapabilitiesChanged(Renderer renderer) {
        this.handler.sendEmptyMessage(26);
    }

    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
        this.handler.obtainMessage(16, playbackParameters).sendToTarget();
    }

    public boolean handleMessage(Message message) {
        MediaPeriodHolder readingPeriod;
        int i;
        Message message2 = message;
        int i2 = 1000;
        try {
            switch (message2.what) {
                case 1:
                    setPlayWhenReadyInternal(message2.arg1 != 0, message2.arg2 >> 4, true, message2.arg2 & 15);
                    break;
                case 2:
                    doSomeWork();
                    break;
                case 3:
                    seekToInternal((SeekPosition) message2.obj);
                    break;
                case 4:
                    setPlaybackParametersInternal((PlaybackParameters) message2.obj);
                    break;
                case 5:
                    setSeekParametersInternal((SeekParameters) message2.obj);
                    break;
                case 6:
                    stopInternal(false, true);
                    break;
                case 7:
                    releaseInternal();
                    return true;
                case 8:
                    handlePeriodPrepared((MediaPeriod) message2.obj);
                    break;
                case 9:
                    handleContinueLoadingRequested((MediaPeriod) message2.obj);
                    break;
                case 10:
                    reselectTracksInternal();
                    break;
                case 11:
                    setRepeatModeInternal(message2.arg1);
                    break;
                case 12:
                    setShuffleModeEnabledInternal(message2.arg1 != 0);
                    break;
                case 13:
                    setForegroundModeInternal(message2.arg1 != 0, (AtomicBoolean) message2.obj);
                    break;
                case 14:
                    sendMessageInternal((PlayerMessage) message2.obj);
                    break;
                case 15:
                    sendMessageToTargetThread((PlayerMessage) message2.obj);
                    break;
                case 16:
                    handlePlaybackParameters((PlaybackParameters) message2.obj, false);
                    break;
                case 17:
                    setMediaItemsInternal((MediaSourceListUpdateMessage) message2.obj);
                    break;
                case 18:
                    addMediaItemsInternal((MediaSourceListUpdateMessage) message2.obj, message2.arg1);
                    break;
                case 19:
                    moveMediaItemsInternal((MoveMediaItemsMessage) message2.obj);
                    break;
                case 20:
                    removeMediaItemsInternal(message2.arg1, message2.arg2, (ShuffleOrder) message2.obj);
                    break;
                case 21:
                    setShuffleOrderInternal((ShuffleOrder) message2.obj);
                    break;
                case 22:
                    mediaSourceListUpdateRequestedInternal();
                    break;
                case 23:
                    setPauseAtEndOfWindowInternal(message2.arg1 != 0);
                    break;
                case 25:
                    attemptRendererErrorRecovery();
                    break;
                case 26:
                    reselectTracksInternalAndSeek();
                    break;
                case 27:
                    updateMediaSourcesWithMediaItemsInternal(message2.arg1, message2.arg2, (List) message2.obj);
                    break;
                case 28:
                    setPreloadConfigurationInternal((ExoPlayer.PreloadConfiguration) message2.obj);
                    break;
                case 29:
                    prepareInternal();
                    break;
                default:
                    return false;
            }
        } catch (ExoPlaybackException e) {
            ExoPlaybackException exoPlaybackException = e;
            if (exoPlaybackException.type == 1 && (readingPeriod = this.queue.getReadingPeriod()) != null) {
                exoPlaybackException = exoPlaybackException.copyWithMediaPeriodId(readingPeriod.info.id);
            }
            if (!exoPlaybackException.isRecoverable || !(this.pendingRecoverableRendererError == null || exoPlaybackException.errorCode == 5004 || exoPlaybackException.errorCode == 5003)) {
                ExoPlaybackException exoPlaybackException2 = this.pendingRecoverableRendererError;
                if (exoPlaybackException2 != null) {
                    exoPlaybackException2.addSuppressed(exoPlaybackException);
                    exoPlaybackException = this.pendingRecoverableRendererError;
                }
                ExoPlaybackException exoPlaybackException3 = exoPlaybackException;
                Log.e(TAG, "Playback error", exoPlaybackException3);
                if (exoPlaybackException3.type == 1 && this.queue.getPlayingPeriod() != this.queue.getReadingPeriod()) {
                    while (this.queue.getPlayingPeriod() != this.queue.getReadingPeriod()) {
                        this.queue.advancePlayingPeriod();
                    }
                    MediaPeriodHolder mediaPeriodHolder = (MediaPeriodHolder) Assertions.checkNotNull(this.queue.getPlayingPeriod());
                    maybeNotifyPlaybackInfoChanged();
                    this.playbackInfo = handlePositionDiscontinuity(mediaPeriodHolder.info.id, mediaPeriodHolder.info.startPositionUs, mediaPeriodHolder.info.requestedContentPositionUs, mediaPeriodHolder.info.startPositionUs, true, 0);
                }
                stopInternal(true, false);
                this.playbackInfo = this.playbackInfo.copyWithPlaybackError(exoPlaybackException3);
            } else {
                Log.w(TAG, "Recoverable renderer error", exoPlaybackException);
                ExoPlaybackException exoPlaybackException4 = this.pendingRecoverableRendererError;
                if (exoPlaybackException4 != null) {
                    exoPlaybackException4.addSuppressed(exoPlaybackException);
                    exoPlaybackException = this.pendingRecoverableRendererError;
                } else {
                    this.pendingRecoverableRendererError = exoPlaybackException;
                }
                HandlerWrapper handlerWrapper = this.handler;
                handlerWrapper.sendMessageAtFrontOfQueue(handlerWrapper.obtainMessage(25, exoPlaybackException));
            }
        } catch (DrmSession.DrmSessionException e2) {
            DrmSession.DrmSessionException drmSessionException = e2;
            handleIoException(drmSessionException, drmSessionException.errorCode);
        } catch (ParserException e3) {
            ParserException parserException = e3;
            if (parserException.dataType == 1) {
                i = parserException.contentIsMalformed ? 3001 : 3003;
            } else {
                if (parserException.dataType == 4) {
                    i = parserException.contentIsMalformed ? 3002 : 3004;
                }
                handleIoException(parserException, i2);
            }
            i2 = i;
            handleIoException(parserException, i2);
        } catch (DataSourceException e4) {
            DataSourceException dataSourceException = e4;
            handleIoException(dataSourceException, dataSourceException.reason);
        } catch (BehindLiveWindowException e5) {
            handleIoException(e5, 1002);
        } catch (IOException e6) {
            handleIoException(e6, 2000);
        } catch (RuntimeException e7) {
            RuntimeException runtimeException = e7;
            if ((runtimeException instanceof IllegalStateException) || (runtimeException instanceof IllegalArgumentException)) {
                i2 = 1004;
            }
            ExoPlaybackException createForUnexpected = ExoPlaybackException.createForUnexpected(runtimeException, i2);
            Log.e(TAG, "Playback error", createForUnexpected);
            stopInternal(true, false);
            this.playbackInfo = this.playbackInfo.copyWithPlaybackError(createForUnexpected);
        }
        maybeNotifyPlaybackInfoChanged();
        return true;
    }

    private void handleIoException(IOException iOException, int i) {
        ExoPlaybackException createForSource = ExoPlaybackException.createForSource(iOException, i);
        MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod();
        if (playingPeriod != null) {
            createForSource = createForSource.copyWithMediaPeriodId(playingPeriod.info.id);
        }
        Log.e(TAG, "Playback error", createForSource);
        stopInternal(false, false);
        this.playbackInfo = this.playbackInfo.copyWithPlaybackError(createForSource);
    }

    private synchronized void waitUninterruptibly(Supplier<Boolean> supplier, long j) {
        long elapsedRealtime = this.clock.elapsedRealtime() + j;
        boolean z = false;
        while (!supplier.get().booleanValue() && j > 0) {
            try {
                this.clock.onThreadBlocked();
                wait(j);
            } catch (InterruptedException unused) {
                z = true;
            }
            j = elapsedRealtime - this.clock.elapsedRealtime();
        }
        if (z) {
            Thread.currentThread().interrupt();
        }
    }

    private void setState(int i) {
        if (this.playbackInfo.playbackState != i) {
            if (i != 2) {
                this.playbackMaybeBecameStuckAtMs = C.TIME_UNSET;
            }
            this.playbackInfo = this.playbackInfo.copyWithPlaybackState(i);
        }
    }

    private void maybeNotifyPlaybackInfoChanged() {
        this.playbackInfoUpdate.setPlaybackInfo(this.playbackInfo);
        if (this.playbackInfoUpdate.hasPendingChange) {
            this.playbackInfoUpdateListener.onPlaybackInfoUpdate(this.playbackInfoUpdate);
            this.playbackInfoUpdate = new PlaybackInfoUpdate(this.playbackInfo);
        }
    }

    private void prepareInternal() {
        this.playbackInfoUpdate.incrementPendingOperationAcks(1);
        resetInternal(false, false, false, true);
        this.loadControl.onPrepared(this.playerId);
        setState(this.playbackInfo.timeline.isEmpty() ? 4 : 2);
        this.mediaSourceList.prepare(this.bandwidthMeter.getTransferListener());
        this.handler.sendEmptyMessage(2);
    }

    private void setMediaItemsInternal(MediaSourceListUpdateMessage mediaSourceListUpdateMessage) throws ExoPlaybackException {
        this.playbackInfoUpdate.incrementPendingOperationAcks(1);
        if (mediaSourceListUpdateMessage.windowIndex != -1) {
            this.pendingInitialSeekPosition = new SeekPosition(new PlaylistTimeline(mediaSourceListUpdateMessage.mediaSourceHolders, mediaSourceListUpdateMessage.shuffleOrder), mediaSourceListUpdateMessage.windowIndex, mediaSourceListUpdateMessage.positionUs);
        }
        handleMediaSourceListInfoRefreshed(this.mediaSourceList.setMediaSources(mediaSourceListUpdateMessage.mediaSourceHolders, mediaSourceListUpdateMessage.shuffleOrder), false);
    }

    private void addMediaItemsInternal(MediaSourceListUpdateMessage mediaSourceListUpdateMessage, int i) throws ExoPlaybackException {
        this.playbackInfoUpdate.incrementPendingOperationAcks(1);
        MediaSourceList mediaSourceList2 = this.mediaSourceList;
        if (i == -1) {
            i = mediaSourceList2.getSize();
        }
        handleMediaSourceListInfoRefreshed(mediaSourceList2.addMediaSources(i, mediaSourceListUpdateMessage.mediaSourceHolders, mediaSourceListUpdateMessage.shuffleOrder), false);
    }

    private void moveMediaItemsInternal(MoveMediaItemsMessage moveMediaItemsMessage) throws ExoPlaybackException {
        this.playbackInfoUpdate.incrementPendingOperationAcks(1);
        handleMediaSourceListInfoRefreshed(this.mediaSourceList.moveMediaSourceRange(moveMediaItemsMessage.fromIndex, moveMediaItemsMessage.toIndex, moveMediaItemsMessage.newFromIndex, moveMediaItemsMessage.shuffleOrder), false);
    }

    private void removeMediaItemsInternal(int i, int i2, ShuffleOrder shuffleOrder) throws ExoPlaybackException {
        this.playbackInfoUpdate.incrementPendingOperationAcks(1);
        handleMediaSourceListInfoRefreshed(this.mediaSourceList.removeMediaSourceRange(i, i2, shuffleOrder), false);
    }

    private void mediaSourceListUpdateRequestedInternal() throws ExoPlaybackException {
        handleMediaSourceListInfoRefreshed(this.mediaSourceList.createTimeline(), true);
    }

    private void setShuffleOrderInternal(ShuffleOrder shuffleOrder) throws ExoPlaybackException {
        this.playbackInfoUpdate.incrementPendingOperationAcks(1);
        handleMediaSourceListInfoRefreshed(this.mediaSourceList.setShuffleOrder(shuffleOrder), false);
    }

    private void updateMediaSourcesWithMediaItemsInternal(int i, int i2, List<MediaItem> list) throws ExoPlaybackException {
        this.playbackInfoUpdate.incrementPendingOperationAcks(1);
        handleMediaSourceListInfoRefreshed(this.mediaSourceList.updateMediaSourcesWithMediaItems(i, i2, list), false);
    }

    private void notifyTrackSelectionPlayWhenReadyChanged(boolean z) {
        for (MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod(); playingPeriod != null; playingPeriod = playingPeriod.getNext()) {
            for (ExoTrackSelection exoTrackSelection : playingPeriod.getTrackSelectorResult().selections) {
                if (exoTrackSelection != null) {
                    exoTrackSelection.onPlayWhenReadyChanged(z);
                }
            }
        }
    }

    private void setPlayWhenReadyInternal(boolean z, int i, boolean z2, int i2) throws ExoPlaybackException {
        this.playbackInfoUpdate.incrementPendingOperationAcks(z2 ? 1 : 0);
        this.playbackInfo = this.playbackInfo.copyWithPlayWhenReady(z, i2, i);
        updateRebufferingState(false, false);
        notifyTrackSelectionPlayWhenReadyChanged(z);
        if (!shouldPlayWhenReady()) {
            stopRenderers();
            updatePlaybackPositions();
        } else if (this.playbackInfo.playbackState == 3) {
            this.mediaClock.start();
            startRenderers();
            this.handler.sendEmptyMessage(2);
        } else if (this.playbackInfo.playbackState == 2) {
            this.handler.sendEmptyMessage(2);
        }
    }

    private void setPauseAtEndOfWindowInternal(boolean z) throws ExoPlaybackException {
        this.pauseAtEndOfWindow = z;
        resetPendingPauseAtEndOfPeriod();
        if (this.pendingPauseAtEndOfPeriod && this.queue.getReadingPeriod() != this.queue.getPlayingPeriod()) {
            seekToCurrentPosition(true);
            handleLoadingMediaPeriodChanged(false);
        }
    }

    private void setOffloadSchedulingEnabled(boolean z) {
        if (z != this.offloadSchedulingEnabled) {
            this.offloadSchedulingEnabled = z;
            if (!z && this.playbackInfo.sleepingForOffload) {
                this.handler.sendEmptyMessage(2);
            }
        }
    }

    private void setRepeatModeInternal(int i) throws ExoPlaybackException {
        this.repeatMode = i;
        if (!this.queue.updateRepeatMode(this.playbackInfo.timeline, i)) {
            seekToCurrentPosition(true);
        }
        handleLoadingMediaPeriodChanged(false);
    }

    private void setShuffleModeEnabledInternal(boolean z) throws ExoPlaybackException {
        this.shuffleModeEnabled = z;
        if (!this.queue.updateShuffleModeEnabled(this.playbackInfo.timeline, z)) {
            seekToCurrentPosition(true);
        }
        handleLoadingMediaPeriodChanged(false);
    }

    private void setPreloadConfigurationInternal(ExoPlayer.PreloadConfiguration preloadConfiguration2) {
        this.preloadConfiguration = preloadConfiguration2;
        this.queue.updatePreloadConfiguration(this.playbackInfo.timeline, preloadConfiguration2);
    }

    private void seekToCurrentPosition(boolean z) throws ExoPlaybackException {
        MediaSource.MediaPeriodId mediaPeriodId = this.queue.getPlayingPeriod().info.id;
        long seekToPeriodPosition = seekToPeriodPosition(mediaPeriodId, this.playbackInfo.positionUs, true, false);
        if (seekToPeriodPosition != this.playbackInfo.positionUs) {
            this.playbackInfo = handlePositionDiscontinuity(mediaPeriodId, seekToPeriodPosition, this.playbackInfo.requestedContentPositionUs, this.playbackInfo.discontinuityStartPositionUs, z, 5);
        }
    }

    private void startRenderers() throws ExoPlaybackException {
        MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod();
        if (playingPeriod != null) {
            TrackSelectorResult trackSelectorResult = playingPeriod.getTrackSelectorResult();
            for (int i = 0; i < this.renderers.length; i++) {
                if (trackSelectorResult.isRendererEnabled(i) && this.renderers[i].getState() == 1) {
                    this.renderers[i].start();
                }
            }
        }
    }

    private void stopRenderers() throws ExoPlaybackException {
        this.mediaClock.stop();
        for (Renderer renderer : this.renderers) {
            if (isRendererEnabled(renderer)) {
                ensureStopped(renderer);
            }
        }
    }

    private void attemptRendererErrorRecovery() throws ExoPlaybackException {
        reselectTracksInternalAndSeek();
    }

    private void updatePlaybackPositions() throws ExoPlaybackException {
        MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod();
        if (playingPeriod != null) {
            long readDiscontinuity = playingPeriod.prepared ? playingPeriod.mediaPeriod.readDiscontinuity() : -9223372036854775807L;
            if (readDiscontinuity != C.TIME_UNSET) {
                if (!playingPeriod.isFullyBuffered()) {
                    this.queue.removeAfter(playingPeriod);
                    handleLoadingMediaPeriodChanged(false);
                    maybeContinueLoading();
                }
                resetRendererPosition(readDiscontinuity);
                if (readDiscontinuity != this.playbackInfo.positionUs) {
                    this.playbackInfo = handlePositionDiscontinuity(this.playbackInfo.periodId, readDiscontinuity, this.playbackInfo.requestedContentPositionUs, readDiscontinuity, true, 5);
                }
            } else {
                long syncAndGetPositionUs = this.mediaClock.syncAndGetPositionUs(playingPeriod != this.queue.getReadingPeriod());
                this.rendererPositionUs = syncAndGetPositionUs;
                long periodTime = playingPeriod.toPeriodTime(syncAndGetPositionUs);
                maybeTriggerPendingMessages(this.playbackInfo.positionUs, periodTime);
                if (this.mediaClock.hasSkippedSilenceSinceLastCall()) {
                    this.playbackInfo = handlePositionDiscontinuity(this.playbackInfo.periodId, periodTime, this.playbackInfo.requestedContentPositionUs, periodTime, !this.playbackInfoUpdate.positionDiscontinuity, 6);
                } else {
                    this.playbackInfo.updatePositionUs(periodTime);
                }
            }
            this.playbackInfo.bufferedPositionUs = this.queue.getLoadingPeriod().getBufferedPositionUs();
            this.playbackInfo.totalBufferedDurationUs = getTotalBufferedDurationUs();
            if (this.playbackInfo.playWhenReady && this.playbackInfo.playbackState == 3 && shouldUseLivePlaybackSpeedControl(this.playbackInfo.timeline, this.playbackInfo.periodId) && this.playbackInfo.playbackParameters.speed == 1.0f) {
                float adjustedPlaybackSpeed = this.livePlaybackSpeedControl.getAdjustedPlaybackSpeed(getCurrentLiveOffsetUs(), this.playbackInfo.totalBufferedDurationUs);
                if (this.mediaClock.getPlaybackParameters().speed != adjustedPlaybackSpeed) {
                    setMediaClockPlaybackParameters(this.playbackInfo.playbackParameters.withSpeed(adjustedPlaybackSpeed));
                    handlePlaybackParameters(this.playbackInfo.playbackParameters, this.mediaClock.getPlaybackParameters().speed, false, false);
                }
            }
        }
    }

    private void setMediaClockPlaybackParameters(PlaybackParameters playbackParameters) {
        this.handler.removeMessages(16);
        this.mediaClock.setPlaybackParameters(playbackParameters);
    }

    private void notifyTrackSelectionRebuffer() {
        for (MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod(); playingPeriod != null; playingPeriod = playingPeriod.getNext()) {
            for (ExoTrackSelection exoTrackSelection : playingPeriod.getTrackSelectorResult().selections) {
                if (exoTrackSelection != null) {
                    exoTrackSelection.onRebuffer();
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:105:0x0197  */
    /* JADX WARNING: Removed duplicated region for block: B:106:0x019f  */
    /* JADX WARNING: Removed duplicated region for block: B:115:0x01ce  */
    /* JADX WARNING: Removed duplicated region for block: B:116:0x01d0  */
    /* JADX WARNING: Removed duplicated region for block: B:125:0x01e3  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void doSomeWork() throws androidx.media3.exoplayer.ExoPlaybackException, java.io.IOException {
        /*
            r16 = this;
            r0 = r16
            androidx.media3.common.util.Clock r1 = r0.clock
            long r1 = r1.uptimeMillis()
            androidx.media3.common.util.HandlerWrapper r3 = r0.handler
            r4 = 2
            r3.removeMessages(r4)
            r16.updatePeriods()
            androidx.media3.exoplayer.PlaybackInfo r3 = r0.playbackInfo
            int r3 = r3.playbackState
            r5 = 1
            if (r3 == r5) goto L_0x0217
            androidx.media3.exoplayer.PlaybackInfo r3 = r0.playbackInfo
            int r3 = r3.playbackState
            r6 = 4
            if (r3 != r6) goto L_0x0021
            goto L_0x0217
        L_0x0021:
            androidx.media3.exoplayer.MediaPeriodQueue r3 = r0.queue
            androidx.media3.exoplayer.MediaPeriodHolder r3 = r3.getPlayingPeriod()
            if (r3 != 0) goto L_0x002d
            r0.scheduleNextWork(r1)
            return
        L_0x002d:
            java.lang.String r7 = "doSomeWork"
            androidx.media3.common.util.TraceUtil.beginSection(r7)
            r16.updatePlaybackPositions()
            boolean r7 = r3.prepared
            r8 = 0
            if (r7 == 0) goto L_0x00b8
            androidx.media3.common.util.Clock r7 = r0.clock
            long r9 = r7.elapsedRealtime()
            long r9 = androidx.media3.common.util.Util.msToUs(r9)
            r0.rendererPositionElapsedRealtimeUs = r9
            androidx.media3.exoplayer.source.MediaPeriod r7 = r3.mediaPeriod
            androidx.media3.exoplayer.PlaybackInfo r9 = r0.playbackInfo
            long r9 = r9.positionUs
            long r11 = r0.backBufferDurationUs
            long r9 = r9 - r11
            boolean r11 = r0.retainBackBufferFromKeyframe
            r7.discardBuffer(r9, r11)
            r9 = r5
            r10 = r9
            r7 = r8
        L_0x0057:
            androidx.media3.exoplayer.Renderer[] r11 = r0.renderers
            int r12 = r11.length
            if (r7 >= r12) goto L_0x00bf
            r11 = r11[r7]
            boolean r12 = isRendererEnabled(r11)
            if (r12 != 0) goto L_0x0068
            r0.maybeTriggerOnRendererReadyChanged(r7, r8)
            goto L_0x00b5
        L_0x0068:
            long r12 = r0.rendererPositionUs
            long r14 = r0.rendererPositionElapsedRealtimeUs
            r11.render(r12, r14)
            if (r9 == 0) goto L_0x0079
            boolean r9 = r11.isEnded()
            if (r9 == 0) goto L_0x0079
            r9 = r5
            goto L_0x007a
        L_0x0079:
            r9 = r8
        L_0x007a:
            androidx.media3.exoplayer.source.SampleStream[] r12 = r3.sampleStreams
            r12 = r12[r7]
            androidx.media3.exoplayer.source.SampleStream r13 = r11.getStream()
            if (r12 == r13) goto L_0x0086
            r12 = r5
            goto L_0x0087
        L_0x0086:
            r12 = r8
        L_0x0087:
            if (r12 != 0) goto L_0x0091
            boolean r13 = r11.hasReadStreamToEnd()
            if (r13 == 0) goto L_0x0091
            r13 = r5
            goto L_0x0092
        L_0x0091:
            r13 = r8
        L_0x0092:
            if (r12 != 0) goto L_0x00a5
            if (r13 != 0) goto L_0x00a5
            boolean r12 = r11.isReady()
            if (r12 != 0) goto L_0x00a5
            boolean r11 = r11.isEnded()
            if (r11 == 0) goto L_0x00a3
            goto L_0x00a5
        L_0x00a3:
            r11 = r8
            goto L_0x00a6
        L_0x00a5:
            r11 = r5
        L_0x00a6:
            r0.maybeTriggerOnRendererReadyChanged(r7, r11)
            if (r10 == 0) goto L_0x00af
            if (r11 == 0) goto L_0x00af
            r10 = r5
            goto L_0x00b0
        L_0x00af:
            r10 = r8
        L_0x00b0:
            if (r11 != 0) goto L_0x00b5
            r0.maybeThrowRendererStreamError(r7)
        L_0x00b5:
            int r7 = r7 + 1
            goto L_0x0057
        L_0x00b8:
            androidx.media3.exoplayer.source.MediaPeriod r7 = r3.mediaPeriod
            r7.maybeThrowPrepareError()
            r9 = r5
            r10 = r9
        L_0x00bf:
            androidx.media3.exoplayer.MediaPeriodInfo r7 = r3.info
            long r11 = r7.durationUs
            r13 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            if (r9 == 0) goto L_0x00dc
            boolean r7 = r3.prepared
            if (r7 == 0) goto L_0x00dc
            int r7 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r7 == 0) goto L_0x00da
            androidx.media3.exoplayer.PlaybackInfo r7 = r0.playbackInfo
            long r13 = r7.positionUs
            int r7 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r7 > 0) goto L_0x00dc
        L_0x00da:
            r7 = r5
            goto L_0x00dd
        L_0x00dc:
            r7 = r8
        L_0x00dd:
            if (r7 == 0) goto L_0x00ed
            boolean r9 = r0.pendingPauseAtEndOfPeriod
            if (r9 == 0) goto L_0x00ed
            r0.pendingPauseAtEndOfPeriod = r8
            androidx.media3.exoplayer.PlaybackInfo r9 = r0.playbackInfo
            int r9 = r9.playbackSuppressionReason
            r11 = 5
            r0.setPlayWhenReadyInternal(r8, r9, r8, r11)
        L_0x00ed:
            r9 = 3
            if (r7 == 0) goto L_0x00fd
            androidx.media3.exoplayer.MediaPeriodInfo r7 = r3.info
            boolean r7 = r7.isFinal
            if (r7 == 0) goto L_0x00fd
            r0.setState(r6)
            r16.stopRenderers()
            goto L_0x014d
        L_0x00fd:
            androidx.media3.exoplayer.PlaybackInfo r7 = r0.playbackInfo
            int r7 = r7.playbackState
            if (r7 != r4) goto L_0x0121
            boolean r7 = r0.shouldTransitionToReadyState(r10)
            if (r7 == 0) goto L_0x0121
            r0.setState(r9)
            r7 = 0
            r0.pendingRecoverableRendererError = r7
            boolean r7 = r16.shouldPlayWhenReady()
            if (r7 == 0) goto L_0x014d
            r0.updateRebufferingState(r8, r8)
            androidx.media3.exoplayer.DefaultMediaClock r7 = r0.mediaClock
            r7.start()
            r16.startRenderers()
            goto L_0x014d
        L_0x0121:
            androidx.media3.exoplayer.PlaybackInfo r7 = r0.playbackInfo
            int r7 = r7.playbackState
            if (r7 != r9) goto L_0x014d
            int r7 = r0.enabledRendererCount
            if (r7 != 0) goto L_0x0132
            boolean r7 = r16.isTimelineReady()
            if (r7 == 0) goto L_0x0134
            goto L_0x014d
        L_0x0132:
            if (r10 != 0) goto L_0x014d
        L_0x0134:
            boolean r7 = r16.shouldPlayWhenReady()
            r0.updateRebufferingState(r7, r8)
            r0.setState(r4)
            boolean r7 = r0.isRebuffering
            if (r7 == 0) goto L_0x014a
            r16.notifyTrackSelectionRebuffer()
            androidx.media3.exoplayer.LivePlaybackSpeedControl r7 = r0.livePlaybackSpeedControl
            r7.notifyRebuffer()
        L_0x014a:
            r16.stopRenderers()
        L_0x014d:
            androidx.media3.exoplayer.PlaybackInfo r7 = r0.playbackInfo
            int r7 = r7.playbackState
            if (r7 != r4) goto L_0x0194
            r7 = r8
        L_0x0154:
            androidx.media3.exoplayer.Renderer[] r10 = r0.renderers
            int r11 = r10.length
            if (r7 >= r11) goto L_0x0175
            r10 = r10[r7]
            boolean r10 = isRendererEnabled(r10)
            if (r10 == 0) goto L_0x0172
            androidx.media3.exoplayer.Renderer[] r10 = r0.renderers
            r10 = r10[r7]
            androidx.media3.exoplayer.source.SampleStream r10 = r10.getStream()
            androidx.media3.exoplayer.source.SampleStream[] r11 = r3.sampleStreams
            r11 = r11[r7]
            if (r10 != r11) goto L_0x0172
            r0.maybeThrowRendererStreamError(r7)
        L_0x0172:
            int r7 = r7 + 1
            goto L_0x0154
        L_0x0175:
            androidx.media3.exoplayer.PlaybackInfo r3 = r0.playbackInfo
            boolean r3 = r3.isLoading
            if (r3 != 0) goto L_0x0194
            androidx.media3.exoplayer.PlaybackInfo r3 = r0.playbackInfo
            long r10 = r3.totalBufferedDurationUs
            r12 = 500000(0x7a120, double:2.47033E-318)
            int r3 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r3 >= 0) goto L_0x0194
            androidx.media3.exoplayer.MediaPeriodQueue r3 = r0.queue
            androidx.media3.exoplayer.MediaPeriodHolder r3 = r3.getLoadingPeriod()
            boolean r3 = r0.isLoadingPossible(r3)
            if (r3 == 0) goto L_0x0194
            r3 = r5
            goto L_0x0195
        L_0x0194:
            r3 = r8
        L_0x0195:
            if (r3 != 0) goto L_0x019f
            r10 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r0.playbackMaybeBecameStuckAtMs = r10
            goto L_0x01c2
        L_0x019f:
            r10 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            long r12 = r0.playbackMaybeBecameStuckAtMs
            int r3 = (r12 > r10 ? 1 : (r12 == r10 ? 0 : -1))
            if (r3 != 0) goto L_0x01b3
            androidx.media3.common.util.Clock r3 = r0.clock
            long r10 = r3.elapsedRealtime()
            r0.playbackMaybeBecameStuckAtMs = r10
            goto L_0x01c2
        L_0x01b3:
            androidx.media3.common.util.Clock r3 = r0.clock
            long r10 = r3.elapsedRealtime()
            long r12 = r0.playbackMaybeBecameStuckAtMs
            long r10 = r10 - r12
            r12 = 4000(0xfa0, double:1.9763E-320)
            int r3 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r3 >= 0) goto L_0x020f
        L_0x01c2:
            boolean r3 = r16.shouldPlayWhenReady()
            if (r3 == 0) goto L_0x01d0
            androidx.media3.exoplayer.PlaybackInfo r3 = r0.playbackInfo
            int r3 = r3.playbackState
            if (r3 != r9) goto L_0x01d0
            r3 = r5
            goto L_0x01d1
        L_0x01d0:
            r3 = r8
        L_0x01d1:
            boolean r7 = r0.offloadSchedulingEnabled
            if (r7 == 0) goto L_0x01dc
            boolean r7 = r0.requestForRendererSleep
            if (r7 == 0) goto L_0x01dc
            if (r3 == 0) goto L_0x01dc
            goto L_0x01dd
        L_0x01dc:
            r5 = r8
        L_0x01dd:
            androidx.media3.exoplayer.PlaybackInfo r7 = r0.playbackInfo
            boolean r7 = r7.sleepingForOffload
            if (r7 == r5) goto L_0x01eb
            androidx.media3.exoplayer.PlaybackInfo r7 = r0.playbackInfo
            androidx.media3.exoplayer.PlaybackInfo r7 = r7.copyWithSleepingForOffload(r5)
            r0.playbackInfo = r7
        L_0x01eb:
            r0.requestForRendererSleep = r8
            if (r5 != 0) goto L_0x020b
            androidx.media3.exoplayer.PlaybackInfo r5 = r0.playbackInfo
            int r5 = r5.playbackState
            if (r5 != r6) goto L_0x01f6
            goto L_0x020b
        L_0x01f6:
            if (r3 != 0) goto L_0x0208
            androidx.media3.exoplayer.PlaybackInfo r3 = r0.playbackInfo
            int r3 = r3.playbackState
            if (r3 == r4) goto L_0x0208
            androidx.media3.exoplayer.PlaybackInfo r3 = r0.playbackInfo
            int r3 = r3.playbackState
            if (r3 != r9) goto L_0x020b
            int r3 = r0.enabledRendererCount
            if (r3 == 0) goto L_0x020b
        L_0x0208:
            r0.scheduleNextWork(r1)
        L_0x020b:
            androidx.media3.common.util.TraceUtil.endSection()
            return
        L_0x020f:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "Playback stuck buffering and not loading"
            r1.<init>(r2)
            throw r1
        L_0x0217:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.ExoPlayerImplInternal.doSomeWork():void");
    }

    private void maybeTriggerOnRendererReadyChanged(int i, boolean z) {
        boolean[] zArr = this.rendererReportedReady;
        if (zArr[i] != z) {
            zArr[i] = z;
            this.applicationLooperHandler.post(new ExoPlayerImplInternal$$ExternalSyntheticLambda0(this, i, z));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$maybeTriggerOnRendererReadyChanged$1$androidx-media3-exoplayer-ExoPlayerImplInternal  reason: not valid java name */
    public /* synthetic */ void m471lambda$maybeTriggerOnRendererReadyChanged$1$androidxmedia3exoplayerExoPlayerImplInternal(int i, boolean z) {
        this.analyticsCollector.onRendererReadyChanged(i, this.renderers[i].getTrackType(), z);
    }

    private long getCurrentLiveOffsetUs() {
        return getLiveOffsetUs(this.playbackInfo.timeline, this.playbackInfo.periodId.periodUid, this.playbackInfo.positionUs);
    }

    private long getLiveOffsetUs(Timeline timeline, Object obj, long j) {
        timeline.getWindow(timeline.getPeriodByUid(obj, this.period).windowIndex, this.window);
        if (this.window.windowStartTimeMs == C.TIME_UNSET || !this.window.isLive() || !this.window.isDynamic) {
            return C.TIME_UNSET;
        }
        return Util.msToUs(this.window.getCurrentUnixTimeMs() - this.window.windowStartTimeMs) - (j + this.period.getPositionInWindowUs());
    }

    private boolean shouldUseLivePlaybackSpeedControl(Timeline timeline, MediaSource.MediaPeriodId mediaPeriodId) {
        if (mediaPeriodId.isAd() || timeline.isEmpty()) {
            return false;
        }
        timeline.getWindow(timeline.getPeriodByUid(mediaPeriodId.periodUid, this.period).windowIndex, this.window);
        if (!this.window.isLive() || !this.window.isDynamic || this.window.windowStartTimeMs == C.TIME_UNSET) {
            return false;
        }
        return true;
    }

    private void scheduleNextWork(long j) {
        long j2;
        if (this.playbackInfo.playbackState != 3 || (!this.dynamicSchedulingEnabled && shouldPlayWhenReady())) {
            j2 = BUFFERING_MAXIMUM_INTERVAL_MS;
        } else {
            j2 = 1000;
        }
        if (this.dynamicSchedulingEnabled && shouldPlayWhenReady()) {
            for (Renderer renderer : this.renderers) {
                if (isRendererEnabled(renderer)) {
                    j2 = Math.min(j2, Util.usToMs(renderer.getDurationToProgressUs(this.rendererPositionUs, this.rendererPositionElapsedRealtimeUs)));
                }
            }
        }
        this.handler.sendEmptyMessageAtTime(2, j + j2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x00ac A[Catch:{ all -> 0x0152 }] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x00af A[Catch:{ all -> 0x0152 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void seekToInternal(androidx.media3.exoplayer.ExoPlayerImplInternal.SeekPosition r19) throws androidx.media3.exoplayer.ExoPlaybackException {
        /*
            r18 = this;
            r11 = r18
            r0 = r19
            androidx.media3.exoplayer.ExoPlayerImplInternal$PlaybackInfoUpdate r1 = r11.playbackInfoUpdate
            r8 = 1
            r1.incrementPendingOperationAcks(r8)
            androidx.media3.exoplayer.PlaybackInfo r1 = r11.playbackInfo
            androidx.media3.common.Timeline r1 = r1.timeline
            r3 = 1
            int r4 = r11.repeatMode
            boolean r5 = r11.shuffleModeEnabled
            androidx.media3.common.Timeline$Window r6 = r11.window
            androidx.media3.common.Timeline$Period r7 = r11.period
            r2 = r19
            android.util.Pair r1 = resolveSeekPositionUs(r1, r2, r3, r4, r5, r6, r7)
            r2 = 0
            r4 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r6 = 0
            if (r1 != 0) goto L_0x004c
            androidx.media3.exoplayer.PlaybackInfo r7 = r11.playbackInfo
            androidx.media3.common.Timeline r7 = r7.timeline
            android.util.Pair r7 = r11.getPlaceholderFirstMediaPeriodPositionUs(r7)
            java.lang.Object r9 = r7.first
            androidx.media3.exoplayer.source.MediaSource$MediaPeriodId r9 = (androidx.media3.exoplayer.source.MediaSource.MediaPeriodId) r9
            java.lang.Object r7 = r7.second
            java.lang.Long r7 = (java.lang.Long) r7
            long r12 = r7.longValue()
            androidx.media3.exoplayer.PlaybackInfo r7 = r11.playbackInfo
            androidx.media3.common.Timeline r7 = r7.timeline
            boolean r7 = r7.isEmpty()
            r7 = r7 ^ r8
            r10 = r7
            r16 = r4
        L_0x0047:
            r4 = r12
            r12 = r16
            goto L_0x00a2
        L_0x004c:
            java.lang.Object r7 = r1.first
            java.lang.Object r9 = r1.second
            java.lang.Long r9 = (java.lang.Long) r9
            long r12 = r9.longValue()
            long r9 = r0.windowPositionUs
            int r9 = (r9 > r4 ? 1 : (r9 == r4 ? 0 : -1))
            if (r9 != 0) goto L_0x005e
            r9 = r4
            goto L_0x005f
        L_0x005e:
            r9 = r12
        L_0x005f:
            androidx.media3.exoplayer.MediaPeriodQueue r14 = r11.queue
            androidx.media3.exoplayer.PlaybackInfo r15 = r11.playbackInfo
            androidx.media3.common.Timeline r15 = r15.timeline
            androidx.media3.exoplayer.source.MediaSource$MediaPeriodId r7 = r14.resolveMediaPeriodIdForAdsAfterPeriodPositionChange(r15, r7, r12)
            boolean r14 = r7.isAd()
            if (r14 == 0) goto L_0x0094
            androidx.media3.exoplayer.PlaybackInfo r4 = r11.playbackInfo
            androidx.media3.common.Timeline r4 = r4.timeline
            java.lang.Object r5 = r7.periodUid
            androidx.media3.common.Timeline$Period r12 = r11.period
            r4.getPeriodByUid(r5, r12)
            androidx.media3.common.Timeline$Period r4 = r11.period
            int r5 = r7.adGroupIndex
            int r4 = r4.getFirstAdIndexToPlay(r5)
            int r5 = r7.adIndexInAdGroup
            if (r4 != r5) goto L_0x008e
            androidx.media3.common.Timeline$Period r4 = r11.period
            long r4 = r4.getAdResumePositionUs()
            r12 = r4
            goto L_0x008f
        L_0x008e:
            r12 = r2
        L_0x008f:
            r4 = r12
            r12 = r9
            r9 = r7
            r10 = r8
            goto L_0x00a2
        L_0x0094:
            long r14 = r0.windowPositionUs
            int r4 = (r14 > r4 ? 1 : (r14 == r4 ? 0 : -1))
            if (r4 != 0) goto L_0x009c
            r4 = r8
            goto L_0x009d
        L_0x009c:
            r4 = r6
        L_0x009d:
            r16 = r9
            r10 = r4
            r9 = r7
            goto L_0x0047
        L_0x00a2:
            androidx.media3.exoplayer.PlaybackInfo r7 = r11.playbackInfo     // Catch:{ all -> 0x0152 }
            androidx.media3.common.Timeline r7 = r7.timeline     // Catch:{ all -> 0x0152 }
            boolean r7 = r7.isEmpty()     // Catch:{ all -> 0x0152 }
            if (r7 == 0) goto L_0x00af
            r11.pendingInitialSeekPosition = r0     // Catch:{ all -> 0x0152 }
            goto L_0x00be
        L_0x00af:
            r0 = 4
            if (r1 != 0) goto L_0x00c1
            androidx.media3.exoplayer.PlaybackInfo r1 = r11.playbackInfo     // Catch:{ all -> 0x0152 }
            int r1 = r1.playbackState     // Catch:{ all -> 0x0152 }
            if (r1 == r8) goto L_0x00bb
            r11.setState(r0)     // Catch:{ all -> 0x0152 }
        L_0x00bb:
            r11.resetInternal(r6, r8, r6, r8)     // Catch:{ all -> 0x0152 }
        L_0x00be:
            r7 = r4
            goto L_0x0140
        L_0x00c1:
            androidx.media3.exoplayer.PlaybackInfo r1 = r11.playbackInfo     // Catch:{ all -> 0x0152 }
            androidx.media3.exoplayer.source.MediaSource$MediaPeriodId r1 = r1.periodId     // Catch:{ all -> 0x0152 }
            boolean r1 = r9.equals(r1)     // Catch:{ all -> 0x0152 }
            if (r1 == 0) goto L_0x0116
            androidx.media3.exoplayer.MediaPeriodQueue r1 = r11.queue     // Catch:{ all -> 0x0152 }
            androidx.media3.exoplayer.MediaPeriodHolder r1 = r1.getPlayingPeriod()     // Catch:{ all -> 0x0152 }
            if (r1 == 0) goto L_0x00e4
            boolean r7 = r1.prepared     // Catch:{ all -> 0x0152 }
            if (r7 == 0) goto L_0x00e4
            int r2 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r2 == 0) goto L_0x00e4
            androidx.media3.exoplayer.source.MediaPeriod r1 = r1.mediaPeriod     // Catch:{ all -> 0x0152 }
            androidx.media3.exoplayer.SeekParameters r2 = r11.seekParameters     // Catch:{ all -> 0x0152 }
            long r1 = r1.getAdjustedSeekPositionUs(r4, r2)     // Catch:{ all -> 0x0152 }
            goto L_0x00e5
        L_0x00e4:
            r1 = r4
        L_0x00e5:
            long r14 = androidx.media3.common.util.Util.usToMs(r1)     // Catch:{ all -> 0x0152 }
            androidx.media3.exoplayer.PlaybackInfo r3 = r11.playbackInfo     // Catch:{ all -> 0x0152 }
            long r6 = r3.positionUs     // Catch:{ all -> 0x0152 }
            long r6 = androidx.media3.common.util.Util.usToMs(r6)     // Catch:{ all -> 0x0152 }
            int r3 = (r14 > r6 ? 1 : (r14 == r6 ? 0 : -1))
            if (r3 != 0) goto L_0x0117
            androidx.media3.exoplayer.PlaybackInfo r3 = r11.playbackInfo     // Catch:{ all -> 0x0152 }
            int r3 = r3.playbackState     // Catch:{ all -> 0x0152 }
            r6 = 2
            if (r3 == r6) goto L_0x0103
            androidx.media3.exoplayer.PlaybackInfo r3 = r11.playbackInfo     // Catch:{ all -> 0x0152 }
            int r3 = r3.playbackState     // Catch:{ all -> 0x0152 }
            r6 = 3
            if (r3 != r6) goto L_0x0117
        L_0x0103:
            androidx.media3.exoplayer.PlaybackInfo r0 = r11.playbackInfo     // Catch:{ all -> 0x0152 }
            long r7 = r0.positionUs     // Catch:{ all -> 0x0152 }
            r0 = 2
            r1 = r18
            r2 = r9
            r3 = r7
            r5 = r12
            r9 = r10
            r10 = r0
            androidx.media3.exoplayer.PlaybackInfo r0 = r1.handlePositionDiscontinuity(r2, r3, r5, r7, r9, r10)
            r11.playbackInfo = r0
            return
        L_0x0116:
            r1 = r4
        L_0x0117:
            androidx.media3.exoplayer.PlaybackInfo r3 = r11.playbackInfo     // Catch:{ all -> 0x0152 }
            int r3 = r3.playbackState     // Catch:{ all -> 0x0152 }
            if (r3 != r0) goto L_0x011f
            r0 = r8
            goto L_0x0120
        L_0x011f:
            r0 = 0
        L_0x0120:
            long r14 = r11.seekToPeriodPosition(r9, r1, r0)     // Catch:{ all -> 0x0152 }
            int r0 = (r4 > r14 ? 1 : (r4 == r14 ? 0 : -1))
            if (r0 == 0) goto L_0x0129
            goto L_0x012a
        L_0x0129:
            r8 = 0
        L_0x012a:
            r10 = r10 | r8
            androidx.media3.exoplayer.PlaybackInfo r0 = r11.playbackInfo     // Catch:{ all -> 0x014f }
            androidx.media3.common.Timeline r2 = r0.timeline     // Catch:{ all -> 0x014f }
            androidx.media3.exoplayer.PlaybackInfo r0 = r11.playbackInfo     // Catch:{ all -> 0x014f }
            androidx.media3.common.Timeline r4 = r0.timeline     // Catch:{ all -> 0x014f }
            androidx.media3.exoplayer.PlaybackInfo r0 = r11.playbackInfo     // Catch:{ all -> 0x014f }
            androidx.media3.exoplayer.source.MediaSource$MediaPeriodId r5 = r0.periodId     // Catch:{ all -> 0x014f }
            r8 = 1
            r1 = r18
            r3 = r9
            r6 = r12
            r1.updatePlaybackSpeedSettingsForNewPeriod(r2, r3, r4, r5, r6, r8)     // Catch:{ all -> 0x014f }
            r7 = r14
        L_0x0140:
            r0 = 2
            r1 = r18
            r2 = r9
            r3 = r7
            r5 = r12
            r9 = r10
            r10 = r0
            androidx.media3.exoplayer.PlaybackInfo r0 = r1.handlePositionDiscontinuity(r2, r3, r5, r7, r9, r10)
            r11.playbackInfo = r0
            return
        L_0x014f:
            r0 = move-exception
            r7 = r14
            goto L_0x0154
        L_0x0152:
            r0 = move-exception
            r7 = r4
        L_0x0154:
            r14 = 2
            r1 = r18
            r2 = r9
            r3 = r7
            r5 = r12
            r9 = r10
            r10 = r14
            androidx.media3.exoplayer.PlaybackInfo r1 = r1.handlePositionDiscontinuity(r2, r3, r5, r7, r9, r10)
            r11.playbackInfo = r1
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.ExoPlayerImplInternal.seekToInternal(androidx.media3.exoplayer.ExoPlayerImplInternal$SeekPosition):void");
    }

    private long seekToPeriodPosition(MediaSource.MediaPeriodId mediaPeriodId, long j, boolean z) throws ExoPlaybackException {
        return seekToPeriodPosition(mediaPeriodId, j, this.queue.getPlayingPeriod() != this.queue.getReadingPeriod(), z);
    }

    private long seekToPeriodPosition(MediaSource.MediaPeriodId mediaPeriodId, long j, boolean z, boolean z2) throws ExoPlaybackException {
        stopRenderers();
        updateRebufferingState(false, true);
        if (z2 || this.playbackInfo.playbackState == 3) {
            setState(2);
        }
        MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod();
        MediaPeriodHolder mediaPeriodHolder = playingPeriod;
        while (mediaPeriodHolder != null && !mediaPeriodId.equals(mediaPeriodHolder.info.id)) {
            mediaPeriodHolder = mediaPeriodHolder.getNext();
        }
        if (z || playingPeriod != mediaPeriodHolder || (mediaPeriodHolder != null && mediaPeriodHolder.toRendererTime(j) < 0)) {
            for (int i = 0; i < this.renderers.length; i++) {
                disableRenderer(i);
            }
            if (mediaPeriodHolder != null) {
                while (this.queue.getPlayingPeriod() != mediaPeriodHolder) {
                    this.queue.advancePlayingPeriod();
                }
                this.queue.removeAfter(mediaPeriodHolder);
                mediaPeriodHolder.setRendererOffset(MediaPeriodQueue.INITIAL_RENDERER_POSITION_OFFSET_US);
                enableRenderers();
            }
        }
        if (mediaPeriodHolder != null) {
            this.queue.removeAfter(mediaPeriodHolder);
            if (!mediaPeriodHolder.prepared) {
                mediaPeriodHolder.info = mediaPeriodHolder.info.copyWithStartPositionUs(j);
            } else if (mediaPeriodHolder.hasEnabledTracks) {
                long seekToUs = mediaPeriodHolder.mediaPeriod.seekToUs(j);
                mediaPeriodHolder.mediaPeriod.discardBuffer(seekToUs - this.backBufferDurationUs, this.retainBackBufferFromKeyframe);
                j = seekToUs;
            }
            resetRendererPosition(j);
            maybeContinueLoading();
        } else {
            this.queue.clear();
            resetRendererPosition(j);
        }
        handleLoadingMediaPeriodChanged(false);
        this.handler.sendEmptyMessage(2);
        return j;
    }

    private void resetRendererPosition(long j) throws ExoPlaybackException {
        long j2;
        MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod();
        if (playingPeriod == null) {
            j2 = j + MediaPeriodQueue.INITIAL_RENDERER_POSITION_OFFSET_US;
        } else {
            j2 = playingPeriod.toRendererTime(j);
        }
        this.rendererPositionUs = j2;
        this.mediaClock.resetPosition(j2);
        for (Renderer renderer : this.renderers) {
            if (isRendererEnabled(renderer)) {
                renderer.resetPosition(this.rendererPositionUs);
            }
        }
        notifyTrackSelectionDiscontinuity();
    }

    private void setPlaybackParametersInternal(PlaybackParameters playbackParameters) throws ExoPlaybackException {
        setMediaClockPlaybackParameters(playbackParameters);
        handlePlaybackParameters(this.mediaClock.getPlaybackParameters(), true);
    }

    private void setSeekParametersInternal(SeekParameters seekParameters2) {
        this.seekParameters = seekParameters2;
    }

    private void setForegroundModeInternal(boolean z, AtomicBoolean atomicBoolean) {
        if (this.foregroundMode != z) {
            this.foregroundMode = z;
            if (!z) {
                for (Renderer renderer : this.renderers) {
                    if (!isRendererEnabled(renderer) && this.renderersToReset.remove(renderer)) {
                        renderer.reset();
                    }
                }
            }
        }
        if (atomicBoolean != null) {
            synchronized (this) {
                atomicBoolean.set(true);
                notifyAll();
            }
        }
    }

    private void stopInternal(boolean z, boolean z2) {
        resetInternal(z || !this.foregroundMode, false, true, false);
        this.playbackInfoUpdate.incrementPendingOperationAcks(z2 ? 1 : 0);
        this.loadControl.onStopped(this.playerId);
        setState(1);
    }

    private void releaseInternal() {
        try {
            resetInternal(true, false, true, false);
            releaseRenderers();
            this.loadControl.onReleased(this.playerId);
            setState(1);
            this.playbackLooperProvider.releaseLooper();
            synchronized (this) {
                this.released = true;
                notifyAll();
            }
        } catch (Throwable th) {
            this.playbackLooperProvider.releaseLooper();
            synchronized (this) {
                this.released = true;
                notifyAll();
                throw th;
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:42:0x0100  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0108  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x010b  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0112  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x0115  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x011d  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0122  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0154  */
    /* JADX WARNING: Removed duplicated region for block: B:66:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void resetInternal(boolean r33, boolean r34, boolean r35, boolean r36) {
        /*
            r32 = this;
            r1 = r32
            androidx.media3.common.util.HandlerWrapper r0 = r1.handler
            r2 = 2
            r0.removeMessages(r2)
            r2 = 0
            r1.pendingRecoverableRendererError = r2
            r3 = 0
            r4 = 1
            r1.updateRebufferingState(r3, r4)
            androidx.media3.exoplayer.DefaultMediaClock r0 = r1.mediaClock
            r0.stop()
            r5 = 1000000000000(0xe8d4a51000, double:4.94065645841E-312)
            r1.rendererPositionUs = r5
            r5 = r3
        L_0x001d:
            androidx.media3.exoplayer.Renderer[] r6 = r1.renderers
            int r0 = r6.length
            java.lang.String r7 = "ExoPlayerImplInternal"
            if (r5 >= r0) goto L_0x0033
            r1.disableRenderer(r5)     // Catch:{ ExoPlaybackException -> 0x002a, RuntimeException -> 0x0028 }
            goto L_0x0030
        L_0x0028:
            r0 = move-exception
            goto L_0x002b
        L_0x002a:
            r0 = move-exception
        L_0x002b:
            java.lang.String r6 = "Disable failed."
            androidx.media3.common.util.Log.e(r7, r6, r0)
        L_0x0030:
            int r5 = r5 + 1
            goto L_0x001d
        L_0x0033:
            if (r33 == 0) goto L_0x0051
            int r5 = r6.length
            r8 = r3
        L_0x0037:
            if (r8 >= r5) goto L_0x0051
            r0 = r6[r8]
            java.util.Set<androidx.media3.exoplayer.Renderer> r9 = r1.renderersToReset
            boolean r9 = r9.remove(r0)
            if (r9 == 0) goto L_0x004e
            r0.reset()     // Catch:{ RuntimeException -> 0x0047 }
            goto L_0x004e
        L_0x0047:
            r0 = move-exception
            r9 = r0
            java.lang.String r0 = "Reset failed."
            androidx.media3.common.util.Log.e(r7, r0, r9)
        L_0x004e:
            int r8 = r8 + 1
            goto L_0x0037
        L_0x0051:
            r1.enabledRendererCount = r3
            androidx.media3.exoplayer.PlaybackInfo r0 = r1.playbackInfo
            androidx.media3.exoplayer.source.MediaSource$MediaPeriodId r0 = r0.periodId
            androidx.media3.exoplayer.PlaybackInfo r5 = r1.playbackInfo
            long r5 = r5.positionUs
            androidx.media3.exoplayer.PlaybackInfo r7 = r1.playbackInfo
            androidx.media3.exoplayer.source.MediaSource$MediaPeriodId r7 = r7.periodId
            boolean r7 = r7.isAd()
            if (r7 != 0) goto L_0x0075
            androidx.media3.exoplayer.PlaybackInfo r7 = r1.playbackInfo
            androidx.media3.common.Timeline$Period r8 = r1.period
            boolean r7 = isUsingPlaceholderPeriod(r7, r8)
            if (r7 == 0) goto L_0x0070
            goto L_0x0075
        L_0x0070:
            androidx.media3.exoplayer.PlaybackInfo r7 = r1.playbackInfo
            long r7 = r7.positionUs
            goto L_0x0079
        L_0x0075:
            androidx.media3.exoplayer.PlaybackInfo r7 = r1.playbackInfo
            long r7 = r7.requestedContentPositionUs
        L_0x0079:
            if (r34 == 0) goto L_0x00a6
            r1.pendingInitialSeekPosition = r2
            androidx.media3.exoplayer.PlaybackInfo r0 = r1.playbackInfo
            androidx.media3.common.Timeline r0 = r0.timeline
            android.util.Pair r0 = r1.getPlaceholderFirstMediaPeriodPositionUs(r0)
            java.lang.Object r5 = r0.first
            androidx.media3.exoplayer.source.MediaSource$MediaPeriodId r5 = (androidx.media3.exoplayer.source.MediaSource.MediaPeriodId) r5
            java.lang.Object r0 = r0.second
            java.lang.Long r0 = (java.lang.Long) r0
            long r6 = r0.longValue()
            androidx.media3.exoplayer.PlaybackInfo r0 = r1.playbackInfo
            androidx.media3.exoplayer.source.MediaSource$MediaPeriodId r0 = r0.periodId
            boolean r0 = r5.equals(r0)
            r8 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            if (r0 != 0) goto L_0x00a1
            goto L_0x00a2
        L_0x00a1:
            r4 = r3
        L_0x00a2:
            r0 = r5
            r27 = r6
            goto L_0x00aa
        L_0x00a6:
            r4 = r3
            r27 = r5
            r8 = r7
        L_0x00aa:
            androidx.media3.exoplayer.MediaPeriodQueue r5 = r1.queue
            r5.clear()
            r1.shouldContinueLoading = r3
            androidx.media3.exoplayer.PlaybackInfo r3 = r1.playbackInfo
            androidx.media3.common.Timeline r3 = r3.timeline
            if (r35 == 0) goto L_0x00f4
            boolean r5 = r3 instanceof androidx.media3.exoplayer.PlaylistTimeline
            if (r5 == 0) goto L_0x00f4
            androidx.media3.exoplayer.PlaybackInfo r3 = r1.playbackInfo
            androidx.media3.common.Timeline r3 = r3.timeline
            androidx.media3.exoplayer.PlaylistTimeline r3 = (androidx.media3.exoplayer.PlaylistTimeline) r3
            androidx.media3.exoplayer.MediaSourceList r5 = r1.mediaSourceList
            androidx.media3.exoplayer.source.ShuffleOrder r5 = r5.getShuffleOrder()
            androidx.media3.exoplayer.PlaylistTimeline r3 = r3.copyWithPlaceholderTimeline(r5)
            int r5 = r0.adGroupIndex
            r6 = -1
            if (r5 == r6) goto L_0x00f4
            java.lang.Object r5 = r0.periodUid
            androidx.media3.common.Timeline$Period r6 = r1.period
            r3.getPeriodByUid(r5, r6)
            androidx.media3.common.Timeline$Period r5 = r1.period
            int r5 = r5.windowIndex
            androidx.media3.common.Timeline$Window r6 = r1.window
            androidx.media3.common.Timeline$Window r5 = r3.getWindow(r5, r6)
            boolean r5 = r5.isLive()
            if (r5 == 0) goto L_0x00f4
            androidx.media3.exoplayer.source.MediaSource$MediaPeriodId r5 = new androidx.media3.exoplayer.source.MediaSource$MediaPeriodId
            java.lang.Object r6 = r0.periodUid
            long r10 = r0.windowSequenceNumber
            r5.<init>(r6, r10)
            r6 = r3
            r18 = r5
            goto L_0x00f7
        L_0x00f4:
            r18 = r0
            r6 = r3
        L_0x00f7:
            androidx.media3.exoplayer.PlaybackInfo r0 = new androidx.media3.exoplayer.PlaybackInfo
            androidx.media3.exoplayer.PlaybackInfo r3 = r1.playbackInfo
            int r12 = r3.playbackState
            if (r36 == 0) goto L_0x0100
            goto L_0x0104
        L_0x0100:
            androidx.media3.exoplayer.PlaybackInfo r2 = r1.playbackInfo
            androidx.media3.exoplayer.ExoPlaybackException r2 = r2.playbackError
        L_0x0104:
            r13 = r2
            r14 = 0
            if (r4 == 0) goto L_0x010b
            androidx.media3.exoplayer.source.TrackGroupArray r2 = androidx.media3.exoplayer.source.TrackGroupArray.EMPTY
            goto L_0x010f
        L_0x010b:
            androidx.media3.exoplayer.PlaybackInfo r2 = r1.playbackInfo
            androidx.media3.exoplayer.source.TrackGroupArray r2 = r2.trackGroups
        L_0x010f:
            r15 = r2
            if (r4 == 0) goto L_0x0115
            androidx.media3.exoplayer.trackselection.TrackSelectorResult r2 = r1.emptyTrackSelectorResult
            goto L_0x0119
        L_0x0115:
            androidx.media3.exoplayer.PlaybackInfo r2 = r1.playbackInfo
            androidx.media3.exoplayer.trackselection.TrackSelectorResult r2 = r2.trackSelectorResult
        L_0x0119:
            r16 = r2
            if (r4 == 0) goto L_0x0122
            com.google.common.collect.ImmutableList r2 = com.google.common.collect.ImmutableList.of()
            goto L_0x0126
        L_0x0122:
            androidx.media3.exoplayer.PlaybackInfo r2 = r1.playbackInfo
            java.util.List<androidx.media3.common.Metadata> r2 = r2.staticMetadata
        L_0x0126:
            r17 = r2
            androidx.media3.exoplayer.PlaybackInfo r2 = r1.playbackInfo
            boolean r2 = r2.playWhenReady
            r19 = r2
            androidx.media3.exoplayer.PlaybackInfo r2 = r1.playbackInfo
            int r2 = r2.playWhenReadyChangeReason
            r20 = r2
            androidx.media3.exoplayer.PlaybackInfo r2 = r1.playbackInfo
            int r2 = r2.playbackSuppressionReason
            r21 = r2
            androidx.media3.exoplayer.PlaybackInfo r2 = r1.playbackInfo
            androidx.media3.common.PlaybackParameters r2 = r2.playbackParameters
            r22 = r2
            r25 = 0
            r29 = 0
            r31 = 0
            r5 = r0
            r7 = r18
            r10 = r27
            r23 = r27
            r5.<init>(r6, r7, r8, r10, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r25, r27, r29, r31)
            r1.playbackInfo = r0
            if (r35 == 0) goto L_0x015e
            androidx.media3.exoplayer.MediaPeriodQueue r0 = r1.queue
            r0.releasePreloadPool()
            androidx.media3.exoplayer.MediaSourceList r0 = r1.mediaSourceList
            r0.release()
        L_0x015e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.ExoPlayerImplInternal.resetInternal(boolean, boolean, boolean, boolean):void");
    }

    private Pair<MediaSource.MediaPeriodId, Long> getPlaceholderFirstMediaPeriodPositionUs(Timeline timeline) {
        long j = 0;
        if (timeline.isEmpty()) {
            return Pair.create(PlaybackInfo.getDummyPeriodForEmptyTimeline(), 0L);
        }
        Timeline timeline2 = timeline;
        Pair<Object, Long> periodPositionUs = timeline2.getPeriodPositionUs(this.window, this.period, timeline.getFirstWindowIndex(this.shuffleModeEnabled), C.TIME_UNSET);
        MediaSource.MediaPeriodId resolveMediaPeriodIdForAdsAfterPeriodPositionChange = this.queue.resolveMediaPeriodIdForAdsAfterPeriodPositionChange(timeline, periodPositionUs.first, 0);
        long longValue = ((Long) periodPositionUs.second).longValue();
        if (resolveMediaPeriodIdForAdsAfterPeriodPositionChange.isAd()) {
            timeline.getPeriodByUid(resolveMediaPeriodIdForAdsAfterPeriodPositionChange.periodUid, this.period);
            if (resolveMediaPeriodIdForAdsAfterPeriodPositionChange.adIndexInAdGroup == this.period.getFirstAdIndexToPlay(resolveMediaPeriodIdForAdsAfterPeriodPositionChange.adGroupIndex)) {
                j = this.period.getAdResumePositionUs();
            }
            longValue = j;
        }
        return Pair.create(resolveMediaPeriodIdForAdsAfterPeriodPositionChange, Long.valueOf(longValue));
    }

    private void sendMessageInternal(PlayerMessage playerMessage) throws ExoPlaybackException {
        if (playerMessage.getPositionMs() == C.TIME_UNSET) {
            sendMessageToTarget(playerMessage);
        } else if (this.playbackInfo.timeline.isEmpty()) {
            this.pendingMessages.add(new PendingMessageInfo(playerMessage));
        } else {
            PendingMessageInfo pendingMessageInfo = new PendingMessageInfo(playerMessage);
            if (resolvePendingMessagePosition(pendingMessageInfo, this.playbackInfo.timeline, this.playbackInfo.timeline, this.repeatMode, this.shuffleModeEnabled, this.window, this.period)) {
                this.pendingMessages.add(pendingMessageInfo);
                Collections.sort(this.pendingMessages);
                return;
            }
            playerMessage.markAsProcessed(false);
        }
    }

    private void sendMessageToTarget(PlayerMessage playerMessage) throws ExoPlaybackException {
        if (playerMessage.getLooper() == this.playbackLooper) {
            deliverMessage(playerMessage);
            if (this.playbackInfo.playbackState == 3 || this.playbackInfo.playbackState == 2) {
                this.handler.sendEmptyMessage(2);
                return;
            }
            return;
        }
        this.handler.obtainMessage(15, playerMessage).sendToTarget();
    }

    private void sendMessageToTargetThread(PlayerMessage playerMessage) {
        Looper looper = playerMessage.getLooper();
        if (!looper.getThread().isAlive()) {
            Log.w("TAG", "Trying to send message on a dead thread.");
            playerMessage.markAsProcessed(false);
            return;
        }
        this.clock.createHandler(looper, (Handler.Callback) null).post(new ExoPlayerImplInternal$$ExternalSyntheticLambda3(this, playerMessage));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$sendMessageToTargetThread$2$androidx-media3-exoplayer-ExoPlayerImplInternal  reason: not valid java name */
    public /* synthetic */ void m473lambda$sendMessageToTargetThread$2$androidxmedia3exoplayerExoPlayerImplInternal(PlayerMessage playerMessage) {
        try {
            deliverMessage(playerMessage);
        } catch (ExoPlaybackException e) {
            Log.e(TAG, "Unexpected error delivering message on external thread.", e);
            throw new RuntimeException(e);
        }
    }

    private void deliverMessage(PlayerMessage playerMessage) throws ExoPlaybackException {
        if (!playerMessage.isCanceled()) {
            try {
                playerMessage.getTarget().handleMessage(playerMessage.getType(), playerMessage.getPayload());
            } finally {
                playerMessage.markAsProcessed(true);
            }
        }
    }

    private void resolvePendingMessagePositions(Timeline timeline, Timeline timeline2) {
        if (!timeline.isEmpty() || !timeline2.isEmpty()) {
            for (int size = this.pendingMessages.size() - 1; size >= 0; size--) {
                if (!resolvePendingMessagePosition(this.pendingMessages.get(size), timeline, timeline2, this.repeatMode, this.shuffleModeEnabled, this.window, this.period)) {
                    this.pendingMessages.get(size).message.markAsProcessed(false);
                    this.pendingMessages.remove(size);
                }
            }
            Collections.sort(this.pendingMessages);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0057  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0066 A[EDGE_INSN: B:66:0x0066->B:21:0x0066 ?: BREAK  
    EDGE_INSN: B:67:0x0066->B:21:0x0066 ?: BREAK  
    EDGE_INSN: B:69:0x0066->B:21:0x0066 ?: BREAK  ] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x006e  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x008c  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00b5 A[SYNTHETIC, Splitter:B:46:0x00b5] */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x009f A[EDGE_INSN: B:72:0x009f->B:85:0x009f ?: BREAK  
    EDGE_INSN: B:73:0x009f->B:85:0x009f ?: BREAK  
    EDGE_INSN: B:74:0x009f->B:85:0x009f ?: BREAK  
    EDGE_INSN: B:75:0x009f->B:85:0x009f ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void maybeTriggerPendingMessages(long r7, long r9) throws androidx.media3.exoplayer.ExoPlaybackException {
        /*
            r6 = this;
            java.util.ArrayList<androidx.media3.exoplayer.ExoPlayerImplInternal$PendingMessageInfo> r0 = r6.pendingMessages
            boolean r0 = r0.isEmpty()
            if (r0 != 0) goto L_0x00ff
            androidx.media3.exoplayer.PlaybackInfo r0 = r6.playbackInfo
            androidx.media3.exoplayer.source.MediaSource$MediaPeriodId r0 = r0.periodId
            boolean r0 = r0.isAd()
            if (r0 == 0) goto L_0x0014
            goto L_0x00ff
        L_0x0014:
            boolean r0 = r6.deliverPendingMessageAtStartPositionRequired
            if (r0 == 0) goto L_0x001e
            r0 = 1
            long r7 = r7 - r0
            r0 = 0
            r6.deliverPendingMessageAtStartPositionRequired = r0
        L_0x001e:
            androidx.media3.exoplayer.PlaybackInfo r0 = r6.playbackInfo
            androidx.media3.common.Timeline r0 = r0.timeline
            androidx.media3.exoplayer.PlaybackInfo r1 = r6.playbackInfo
            androidx.media3.exoplayer.source.MediaSource$MediaPeriodId r1 = r1.periodId
            java.lang.Object r1 = r1.periodUid
            int r0 = r0.getIndexOfPeriod(r1)
            int r1 = r6.nextPendingMessageIndexHint
            java.util.ArrayList<androidx.media3.exoplayer.ExoPlayerImplInternal$PendingMessageInfo> r2 = r6.pendingMessages
            int r2 = r2.size()
            int r1 = java.lang.Math.min(r1, r2)
            r2 = 0
            if (r1 <= 0) goto L_0x0046
            java.util.ArrayList<androidx.media3.exoplayer.ExoPlayerImplInternal$PendingMessageInfo> r3 = r6.pendingMessages
            int r4 = r1 + -1
            java.lang.Object r3 = r3.get(r4)
            androidx.media3.exoplayer.ExoPlayerImplInternal$PendingMessageInfo r3 = (androidx.media3.exoplayer.ExoPlayerImplInternal.PendingMessageInfo) r3
            goto L_0x0047
        L_0x0046:
            r3 = r2
        L_0x0047:
            if (r3 == 0) goto L_0x0066
            int r4 = r3.resolvedPeriodIndex
            if (r4 > r0) goto L_0x0057
            int r4 = r3.resolvedPeriodIndex
            if (r4 != r0) goto L_0x0066
            long r3 = r3.resolvedPeriodTimeUs
            int r3 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1))
            if (r3 <= 0) goto L_0x0066
        L_0x0057:
            int r1 = r1 + -1
            if (r1 <= 0) goto L_0x0046
            java.util.ArrayList<androidx.media3.exoplayer.ExoPlayerImplInternal$PendingMessageInfo> r3 = r6.pendingMessages
            int r4 = r1 + -1
            java.lang.Object r3 = r3.get(r4)
            androidx.media3.exoplayer.ExoPlayerImplInternal$PendingMessageInfo r3 = (androidx.media3.exoplayer.ExoPlayerImplInternal.PendingMessageInfo) r3
            goto L_0x0047
        L_0x0066:
            java.util.ArrayList<androidx.media3.exoplayer.ExoPlayerImplInternal$PendingMessageInfo> r3 = r6.pendingMessages
            int r3 = r3.size()
            if (r1 >= r3) goto L_0x0077
            java.util.ArrayList<androidx.media3.exoplayer.ExoPlayerImplInternal$PendingMessageInfo> r3 = r6.pendingMessages
            java.lang.Object r3 = r3.get(r1)
            androidx.media3.exoplayer.ExoPlayerImplInternal$PendingMessageInfo r3 = (androidx.media3.exoplayer.ExoPlayerImplInternal.PendingMessageInfo) r3
            goto L_0x0078
        L_0x0077:
            r3 = r2
        L_0x0078:
            if (r3 == 0) goto L_0x009f
            java.lang.Object r4 = r3.resolvedPeriodUid
            if (r4 == 0) goto L_0x009f
            int r4 = r3.resolvedPeriodIndex
            if (r4 < r0) goto L_0x008c
            int r4 = r3.resolvedPeriodIndex
            if (r4 != r0) goto L_0x009f
            long r4 = r3.resolvedPeriodTimeUs
            int r4 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1))
            if (r4 > 0) goto L_0x009f
        L_0x008c:
            int r1 = r1 + 1
            java.util.ArrayList<androidx.media3.exoplayer.ExoPlayerImplInternal$PendingMessageInfo> r3 = r6.pendingMessages
            int r3 = r3.size()
            if (r1 >= r3) goto L_0x0077
            java.util.ArrayList<androidx.media3.exoplayer.ExoPlayerImplInternal$PendingMessageInfo> r3 = r6.pendingMessages
            java.lang.Object r3 = r3.get(r1)
            androidx.media3.exoplayer.ExoPlayerImplInternal$PendingMessageInfo r3 = (androidx.media3.exoplayer.ExoPlayerImplInternal.PendingMessageInfo) r3
            goto L_0x0078
        L_0x009f:
            if (r3 == 0) goto L_0x00fd
            java.lang.Object r4 = r3.resolvedPeriodUid
            if (r4 == 0) goto L_0x00fd
            int r4 = r3.resolvedPeriodIndex
            if (r4 != r0) goto L_0x00fd
            long r4 = r3.resolvedPeriodTimeUs
            int r4 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1))
            if (r4 <= 0) goto L_0x00fd
            long r4 = r3.resolvedPeriodTimeUs
            int r4 = (r4 > r9 ? 1 : (r4 == r9 ? 0 : -1))
            if (r4 > 0) goto L_0x00fd
            androidx.media3.exoplayer.PlayerMessage r4 = r3.message     // Catch:{ all -> 0x00e6 }
            r6.sendMessageToTarget(r4)     // Catch:{ all -> 0x00e6 }
            androidx.media3.exoplayer.PlayerMessage r4 = r3.message
            boolean r4 = r4.getDeleteAfterDelivery()
            if (r4 != 0) goto L_0x00ce
            androidx.media3.exoplayer.PlayerMessage r3 = r3.message
            boolean r3 = r3.isCanceled()
            if (r3 == 0) goto L_0x00cb
            goto L_0x00ce
        L_0x00cb:
            int r1 = r1 + 1
            goto L_0x00d3
        L_0x00ce:
            java.util.ArrayList<androidx.media3.exoplayer.ExoPlayerImplInternal$PendingMessageInfo> r3 = r6.pendingMessages
            r3.remove(r1)
        L_0x00d3:
            java.util.ArrayList<androidx.media3.exoplayer.ExoPlayerImplInternal$PendingMessageInfo> r3 = r6.pendingMessages
            int r3 = r3.size()
            if (r1 >= r3) goto L_0x00e4
            java.util.ArrayList<androidx.media3.exoplayer.ExoPlayerImplInternal$PendingMessageInfo> r3 = r6.pendingMessages
            java.lang.Object r3 = r3.get(r1)
            androidx.media3.exoplayer.ExoPlayerImplInternal$PendingMessageInfo r3 = (androidx.media3.exoplayer.ExoPlayerImplInternal.PendingMessageInfo) r3
            goto L_0x009f
        L_0x00e4:
            r3 = r2
            goto L_0x009f
        L_0x00e6:
            r7 = move-exception
            androidx.media3.exoplayer.PlayerMessage r8 = r3.message
            boolean r8 = r8.getDeleteAfterDelivery()
            if (r8 != 0) goto L_0x00f7
            androidx.media3.exoplayer.PlayerMessage r8 = r3.message
            boolean r8 = r8.isCanceled()
            if (r8 == 0) goto L_0x00fc
        L_0x00f7:
            java.util.ArrayList<androidx.media3.exoplayer.ExoPlayerImplInternal$PendingMessageInfo> r8 = r6.pendingMessages
            r8.remove(r1)
        L_0x00fc:
            throw r7
        L_0x00fd:
            r6.nextPendingMessageIndexHint = r1
        L_0x00ff:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.ExoPlayerImplInternal.maybeTriggerPendingMessages(long, long):void");
    }

    private void ensureStopped(Renderer renderer) {
        if (renderer.getState() == 2) {
            renderer.stop();
        }
    }

    private void disableRenderer(int i) throws ExoPlaybackException {
        Renderer renderer = this.renderers[i];
        if (isRendererEnabled(renderer)) {
            maybeTriggerOnRendererReadyChanged(i, false);
            this.mediaClock.onRendererDisabled(renderer);
            ensureStopped(renderer);
            renderer.disable();
            this.enabledRendererCount--;
        }
    }

    private void reselectTracksInternalAndSeek() throws ExoPlaybackException {
        reselectTracksInternal();
        seekToCurrentPosition(true);
    }

    private void reselectTracksInternal() throws ExoPlaybackException {
        float f = this.mediaClock.getPlaybackParameters().speed;
        MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod();
        MediaPeriodHolder readingPeriod = this.queue.getReadingPeriod();
        TrackSelectorResult trackSelectorResult = null;
        boolean z = true;
        while (playingPeriod != null && playingPeriod.prepared) {
            TrackSelectorResult selectTracks = playingPeriod.selectTracks(f, this.playbackInfo.timeline, this.playbackInfo.playWhenReady);
            if (playingPeriod == this.queue.getPlayingPeriod()) {
                trackSelectorResult = selectTracks;
            }
            if (!selectTracks.isEquivalent(playingPeriod.getTrackSelectorResult())) {
                if (z) {
                    MediaPeriodHolder playingPeriod2 = this.queue.getPlayingPeriod();
                    boolean removeAfter = this.queue.removeAfter(playingPeriod2);
                    boolean[] zArr = new boolean[this.renderers.length];
                    long applyTrackSelection = playingPeriod2.applyTrackSelection((TrackSelectorResult) Assertions.checkNotNull(trackSelectorResult), this.playbackInfo.positionUs, removeAfter, zArr);
                    boolean z2 = (this.playbackInfo.playbackState == 4 || applyTrackSelection == this.playbackInfo.positionUs) ? false : true;
                    long j = applyTrackSelection;
                    this.playbackInfo = handlePositionDiscontinuity(this.playbackInfo.periodId, applyTrackSelection, this.playbackInfo.requestedContentPositionUs, this.playbackInfo.discontinuityStartPositionUs, z2, 5);
                    if (z2) {
                        resetRendererPosition(j);
                    }
                    boolean[] zArr2 = new boolean[this.renderers.length];
                    int i = 0;
                    while (true) {
                        Renderer[] rendererArr = this.renderers;
                        if (i >= rendererArr.length) {
                            break;
                        }
                        Renderer renderer = rendererArr[i];
                        zArr2[i] = isRendererEnabled(renderer);
                        SampleStream sampleStream = playingPeriod2.sampleStreams[i];
                        if (zArr2[i]) {
                            if (sampleStream != renderer.getStream()) {
                                disableRenderer(i);
                            } else if (zArr[i]) {
                                renderer.resetPosition(this.rendererPositionUs);
                            }
                        }
                        i++;
                    }
                    enableRenderers(zArr2, this.rendererPositionUs);
                } else {
                    this.queue.removeAfter(playingPeriod);
                    if (playingPeriod.prepared) {
                        playingPeriod.applyTrackSelection(selectTracks, Math.max(playingPeriod.info.startPositionUs, playingPeriod.toPeriodTime(this.rendererPositionUs)), false);
                    }
                }
                handleLoadingMediaPeriodChanged(true);
                if (this.playbackInfo.playbackState != 4) {
                    maybeContinueLoading();
                    updatePlaybackPositions();
                    this.handler.sendEmptyMessage(2);
                    return;
                }
                return;
            }
            if (playingPeriod == readingPeriod) {
                z = false;
            }
            playingPeriod = playingPeriod.getNext();
        }
    }

    private void updateTrackSelectionPlaybackSpeed(float f) {
        for (MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod(); playingPeriod != null; playingPeriod = playingPeriod.getNext()) {
            for (ExoTrackSelection exoTrackSelection : playingPeriod.getTrackSelectorResult().selections) {
                if (exoTrackSelection != null) {
                    exoTrackSelection.onPlaybackSpeed(f);
                }
            }
        }
    }

    private void notifyTrackSelectionDiscontinuity() {
        for (MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod(); playingPeriod != null; playingPeriod = playingPeriod.getNext()) {
            for (ExoTrackSelection exoTrackSelection : playingPeriod.getTrackSelectorResult().selections) {
                if (exoTrackSelection != null) {
                    exoTrackSelection.onDiscontinuity();
                }
            }
        }
    }

    private boolean shouldTransitionToReadyState(boolean z) {
        if (this.enabledRendererCount == 0) {
            return isTimelineReady();
        }
        boolean z2 = false;
        if (!z) {
            return false;
        }
        if (!this.playbackInfo.isLoading) {
            return true;
        }
        MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod();
        long targetLiveOffsetUs = shouldUseLivePlaybackSpeedControl(this.playbackInfo.timeline, playingPeriod.info.id) ? this.livePlaybackSpeedControl.getTargetLiveOffsetUs() : C.TIME_UNSET;
        MediaPeriodHolder loadingPeriod = this.queue.getLoadingPeriod();
        boolean z3 = loadingPeriod.isFullyBuffered() && loadingPeriod.info.isFinal;
        if (loadingPeriod.info.id.isAd() && !loadingPeriod.prepared) {
            z2 = true;
        }
        if (z3 || z2) {
            return true;
        }
        return this.loadControl.shouldStartPlayback(new LoadControl.Parameters(this.playerId, this.playbackInfo.timeline, playingPeriod.info.id, playingPeriod.toPeriodTime(this.rendererPositionUs), getTotalBufferedDurationUs(loadingPeriod.getBufferedPositionUs()), this.mediaClock.getPlaybackParameters().speed, this.playbackInfo.playWhenReady, this.isRebuffering, targetLiveOffsetUs));
    }

    private boolean isTimelineReady() {
        MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod();
        long j = playingPeriod.info.durationUs;
        return playingPeriod.prepared && (j == C.TIME_UNSET || this.playbackInfo.positionUs < j || !shouldPlayWhenReady());
    }

    /* JADX WARNING: Removed duplicated region for block: B:101:0x01be  */
    /* JADX WARNING: Removed duplicated region for block: B:102:0x01c1  */
    /* JADX WARNING: Removed duplicated region for block: B:106:0x01e9  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00d5  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00d8  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x011c  */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x011f  */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x0147  */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x0173  */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x019d A[ADDED_TO_REGION] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void handleMediaSourceListInfoRefreshed(androidx.media3.common.Timeline r29, boolean r30) throws androidx.media3.exoplayer.ExoPlaybackException {
        /*
            r28 = this;
            r11 = r28
            r12 = r29
            androidx.media3.exoplayer.PlaybackInfo r2 = r11.playbackInfo
            androidx.media3.exoplayer.ExoPlayerImplInternal$SeekPosition r3 = r11.pendingInitialSeekPosition
            androidx.media3.exoplayer.MediaPeriodQueue r4 = r11.queue
            int r5 = r11.repeatMode
            boolean r6 = r11.shuffleModeEnabled
            androidx.media3.common.Timeline$Window r7 = r11.window
            androidx.media3.common.Timeline$Period r8 = r11.period
            r1 = r29
            androidx.media3.exoplayer.ExoPlayerImplInternal$PositionUpdateForPlaylistChange r7 = resolvePositionForPlaylistChange(r1, r2, r3, r4, r5, r6, r7, r8)
            androidx.media3.exoplayer.source.MediaSource$MediaPeriodId r9 = r7.periodId
            long r13 = r7.requestedContentPositionUs
            boolean r0 = r7.forceBufferingState
            long r5 = r7.periodPositionUs
            androidx.media3.exoplayer.PlaybackInfo r1 = r11.playbackInfo
            androidx.media3.exoplayer.source.MediaSource$MediaPeriodId r1 = r1.periodId
            boolean r1 = r1.equals(r9)
            r10 = 1
            r15 = 0
            if (r1 == 0) goto L_0x0038
            androidx.media3.exoplayer.PlaybackInfo r1 = r11.playbackInfo
            long r1 = r1.positionUs
            int r1 = (r5 > r1 ? 1 : (r5 == r1 ? 0 : -1))
            if (r1 == 0) goto L_0x0035
            goto L_0x0038
        L_0x0035:
            r16 = r15
            goto L_0x003a
        L_0x0038:
            r16 = r10
        L_0x003a:
            r17 = 3
            r8 = 0
            r18 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r2 = 4
            boolean r1 = r7.endPlayback     // Catch:{ all -> 0x0161 }
            if (r1 == 0) goto L_0x0053
            androidx.media3.exoplayer.PlaybackInfo r1 = r11.playbackInfo     // Catch:{ all -> 0x0161 }
            int r1 = r1.playbackState     // Catch:{ all -> 0x0161 }
            if (r1 == r10) goto L_0x0050
            r11.setState(r2)     // Catch:{ all -> 0x0161 }
        L_0x0050:
            r11.resetInternal(r15, r15, r15, r10)     // Catch:{ all -> 0x0161 }
        L_0x0053:
            androidx.media3.exoplayer.Renderer[] r1 = r11.renderers     // Catch:{ all -> 0x0161 }
            int r2 = r1.length     // Catch:{ all -> 0x015a }
            r3 = r15
        L_0x0057:
            if (r3 >= r2) goto L_0x0061
            r4 = r1[r3]     // Catch:{ all -> 0x015a }
            r4.setTimeline(r12)     // Catch:{ all -> 0x015a }
            int r3 = r3 + 1
            goto L_0x0057
        L_0x0061:
            if (r16 != 0) goto L_0x0087
            androidx.media3.exoplayer.MediaPeriodQueue r1 = r11.queue     // Catch:{ all -> 0x007e }
            long r3 = r11.rendererPositionUs     // Catch:{ all -> 0x007e }
            long r23 = r28.getMaxRendererReadPositionUs()     // Catch:{ all -> 0x007e }
            r20 = 4
            r2 = r29
            r10 = -1
            r26 = r5
            r5 = r23
            boolean r0 = r1.updateQueuedPeriods(r2, r3, r5)     // Catch:{ all -> 0x0154 }
            if (r0 != 0) goto L_0x00c5
            r11.seekToCurrentPosition(r15)     // Catch:{ all -> 0x0154 }
            goto L_0x00c5
        L_0x007e:
            r0 = move-exception
            r26 = r5
            r10 = -1
            r20 = 4
        L_0x0084:
            r15 = r8
            goto L_0x0158
        L_0x0087:
            r26 = r5
            r10 = -1
            r20 = 4
            boolean r1 = r29.isEmpty()     // Catch:{ all -> 0x0154 }
            if (r1 != 0) goto L_0x00c5
            androidx.media3.exoplayer.MediaPeriodQueue r1 = r11.queue     // Catch:{ all -> 0x00c1 }
            androidx.media3.exoplayer.MediaPeriodHolder r1 = r1.getPlayingPeriod()     // Catch:{ all -> 0x00c1 }
        L_0x0098:
            if (r1 == 0) goto L_0x00b6
            androidx.media3.exoplayer.MediaPeriodInfo r2 = r1.info     // Catch:{ all -> 0x0154 }
            androidx.media3.exoplayer.source.MediaSource$MediaPeriodId r2 = r2.id     // Catch:{ all -> 0x0154 }
            boolean r2 = r2.equals(r9)     // Catch:{ all -> 0x0154 }
            if (r2 == 0) goto L_0x00b1
            androidx.media3.exoplayer.MediaPeriodQueue r2 = r11.queue     // Catch:{ all -> 0x0154 }
            androidx.media3.exoplayer.MediaPeriodInfo r3 = r1.info     // Catch:{ all -> 0x0154 }
            androidx.media3.exoplayer.MediaPeriodInfo r2 = r2.getUpdatedMediaPeriodInfo(r12, r3)     // Catch:{ all -> 0x0154 }
            r1.info = r2     // Catch:{ all -> 0x0154 }
            r1.updateClipping()     // Catch:{ all -> 0x0154 }
        L_0x00b1:
            androidx.media3.exoplayer.MediaPeriodHolder r1 = r1.getNext()     // Catch:{ all -> 0x0154 }
            goto L_0x0098
        L_0x00b6:
            r5 = r26
            long r0 = r11.seekToPeriodPosition(r9, r5, r0)     // Catch:{ all -> 0x00bf }
            r22 = r0
            goto L_0x00c9
        L_0x00bf:
            r0 = move-exception
            goto L_0x0084
        L_0x00c1:
            r0 = move-exception
            r5 = r26
            goto L_0x0084
        L_0x00c5:
            r5 = r26
            r22 = r5
        L_0x00c9:
            androidx.media3.exoplayer.PlaybackInfo r0 = r11.playbackInfo
            androidx.media3.common.Timeline r4 = r0.timeline
            androidx.media3.exoplayer.PlaybackInfo r0 = r11.playbackInfo
            androidx.media3.exoplayer.source.MediaSource$MediaPeriodId r5 = r0.periodId
            boolean r0 = r7.setTargetLiveOffset
            if (r0 == 0) goto L_0x00d8
            r6 = r22
            goto L_0x00da
        L_0x00d8:
            r6 = r18
        L_0x00da:
            r0 = 0
            r1 = r28
            r2 = r29
            r3 = r9
            r15 = r8
            r8 = r0
            r1.updatePlaybackSpeedSettingsForNewPeriod(r2, r3, r4, r5, r6, r8)
            if (r16 != 0) goto L_0x00ef
            androidx.media3.exoplayer.PlaybackInfo r0 = r11.playbackInfo
            long r0 = r0.requestedContentPositionUs
            int r0 = (r13 > r0 ? 1 : (r13 == r0 ? 0 : -1))
            if (r0 == 0) goto L_0x012f
        L_0x00ef:
            androidx.media3.exoplayer.PlaybackInfo r0 = r11.playbackInfo
            androidx.media3.exoplayer.source.MediaSource$MediaPeriodId r0 = r0.periodId
            java.lang.Object r0 = r0.periodUid
            androidx.media3.exoplayer.PlaybackInfo r1 = r11.playbackInfo
            androidx.media3.common.Timeline r1 = r1.timeline
            if (r16 == 0) goto L_0x0110
            if (r30 == 0) goto L_0x0110
            boolean r2 = r1.isEmpty()
            if (r2 != 0) goto L_0x0110
            androidx.media3.common.Timeline$Period r2 = r11.period
            androidx.media3.common.Timeline$Period r1 = r1.getPeriodByUid(r0, r2)
            boolean r1 = r1.isPlaceholder
            if (r1 != 0) goto L_0x0110
            r21 = 1
            goto L_0x0112
        L_0x0110:
            r21 = 0
        L_0x0112:
            androidx.media3.exoplayer.PlaybackInfo r1 = r11.playbackInfo
            long r7 = r1.discontinuityStartPositionUs
            int r0 = r12.getIndexOfPeriod(r0)
            if (r0 != r10) goto L_0x011f
            r10 = r20
            goto L_0x0121
        L_0x011f:
            r10 = r17
        L_0x0121:
            r1 = r28
            r2 = r9
            r3 = r22
            r5 = r13
            r9 = r21
            androidx.media3.exoplayer.PlaybackInfo r0 = r1.handlePositionDiscontinuity(r2, r3, r5, r7, r9, r10)
            r11.playbackInfo = r0
        L_0x012f:
            r28.resetPendingPauseAtEndOfPeriod()
            androidx.media3.exoplayer.PlaybackInfo r0 = r11.playbackInfo
            androidx.media3.common.Timeline r0 = r0.timeline
            r11.resolvePendingMessagePositions(r12, r0)
            androidx.media3.exoplayer.PlaybackInfo r0 = r11.playbackInfo
            androidx.media3.exoplayer.PlaybackInfo r0 = r0.copyWithTimeline(r12)
            r11.playbackInfo = r0
            boolean r0 = r29.isEmpty()
            if (r0 != 0) goto L_0x0149
            r11.pendingInitialSeekPosition = r15
        L_0x0149:
            r1 = 0
            r11.handleLoadingMediaPeriodChanged(r1)
            androidx.media3.common.util.HandlerWrapper r0 = r11.handler
            r8 = 2
            r0.sendEmptyMessage(r8)
            return
        L_0x0154:
            r0 = move-exception
            r15 = r8
            r5 = r26
        L_0x0158:
            r8 = 2
            goto L_0x0167
        L_0x015a:
            r0 = move-exception
            r15 = r8
            r8 = 2
            r10 = -1
            r20 = 4
            goto L_0x0167
        L_0x0161:
            r0 = move-exception
            r20 = r2
            r15 = r8
            r8 = 2
            r10 = -1
        L_0x0167:
            androidx.media3.exoplayer.PlaybackInfo r1 = r11.playbackInfo
            androidx.media3.common.Timeline r4 = r1.timeline
            androidx.media3.exoplayer.PlaybackInfo r1 = r11.playbackInfo
            androidx.media3.exoplayer.source.MediaSource$MediaPeriodId r3 = r1.periodId
            boolean r1 = r7.setTargetLiveOffset
            if (r1 == 0) goto L_0x0175
            r18 = r5
        L_0x0175:
            r22 = 0
            r1 = r28
            r2 = r29
            r7 = r3
            r3 = r9
            r25 = r5
            r5 = r7
            r6 = r18
            r8 = r22
            r1.updatePlaybackSpeedSettingsForNewPeriod(r2, r3, r4, r5, r6, r8)
            if (r16 != 0) goto L_0x0191
            androidx.media3.exoplayer.PlaybackInfo r1 = r11.playbackInfo
            long r1 = r1.requestedContentPositionUs
            int r1 = (r13 > r1 ? 1 : (r13 == r1 ? 0 : -1))
            if (r1 == 0) goto L_0x01d1
        L_0x0191:
            androidx.media3.exoplayer.PlaybackInfo r1 = r11.playbackInfo
            androidx.media3.exoplayer.source.MediaSource$MediaPeriodId r1 = r1.periodId
            java.lang.Object r1 = r1.periodUid
            androidx.media3.exoplayer.PlaybackInfo r2 = r11.playbackInfo
            androidx.media3.common.Timeline r2 = r2.timeline
            if (r16 == 0) goto L_0x01b2
            if (r30 == 0) goto L_0x01b2
            boolean r3 = r2.isEmpty()
            if (r3 != 0) goto L_0x01b2
            androidx.media3.common.Timeline$Period r3 = r11.period
            androidx.media3.common.Timeline$Period r2 = r2.getPeriodByUid(r1, r3)
            boolean r2 = r2.isPlaceholder
            if (r2 != 0) goto L_0x01b2
            r21 = 1
            goto L_0x01b4
        L_0x01b2:
            r21 = 0
        L_0x01b4:
            androidx.media3.exoplayer.PlaybackInfo r2 = r11.playbackInfo
            long r7 = r2.discontinuityStartPositionUs
            int r1 = r12.getIndexOfPeriod(r1)
            if (r1 != r10) goto L_0x01c1
            r10 = r20
            goto L_0x01c3
        L_0x01c1:
            r10 = r17
        L_0x01c3:
            r1 = r28
            r2 = r9
            r3 = r25
            r5 = r13
            r9 = r21
            androidx.media3.exoplayer.PlaybackInfo r1 = r1.handlePositionDiscontinuity(r2, r3, r5, r7, r9, r10)
            r11.playbackInfo = r1
        L_0x01d1:
            r28.resetPendingPauseAtEndOfPeriod()
            androidx.media3.exoplayer.PlaybackInfo r1 = r11.playbackInfo
            androidx.media3.common.Timeline r1 = r1.timeline
            r11.resolvePendingMessagePositions(r12, r1)
            androidx.media3.exoplayer.PlaybackInfo r1 = r11.playbackInfo
            androidx.media3.exoplayer.PlaybackInfo r1 = r1.copyWithTimeline(r12)
            r11.playbackInfo = r1
            boolean r1 = r29.isEmpty()
            if (r1 != 0) goto L_0x01eb
            r11.pendingInitialSeekPosition = r15
        L_0x01eb:
            r1 = 0
            r11.handleLoadingMediaPeriodChanged(r1)
            androidx.media3.common.util.HandlerWrapper r1 = r11.handler
            r2 = 2
            r1.sendEmptyMessage(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.ExoPlayerImplInternal.handleMediaSourceListInfoRefreshed(androidx.media3.common.Timeline, boolean):void");
    }

    private void updatePlaybackSpeedSettingsForNewPeriod(Timeline timeline, MediaSource.MediaPeriodId mediaPeriodId, Timeline timeline2, MediaSource.MediaPeriodId mediaPeriodId2, long j, boolean z) throws ExoPlaybackException {
        if (!shouldUseLivePlaybackSpeedControl(timeline, mediaPeriodId)) {
            PlaybackParameters playbackParameters = mediaPeriodId.isAd() ? PlaybackParameters.DEFAULT : this.playbackInfo.playbackParameters;
            if (!this.mediaClock.getPlaybackParameters().equals(playbackParameters)) {
                setMediaClockPlaybackParameters(playbackParameters);
                handlePlaybackParameters(this.playbackInfo.playbackParameters, playbackParameters.speed, false, false);
                return;
            }
            return;
        }
        timeline.getWindow(timeline.getPeriodByUid(mediaPeriodId.periodUid, this.period).windowIndex, this.window);
        this.livePlaybackSpeedControl.setLiveConfiguration((MediaItem.LiveConfiguration) Util.castNonNull(this.window.liveConfiguration));
        if (j != C.TIME_UNSET) {
            this.livePlaybackSpeedControl.setTargetLiveOffsetOverrideUs(getLiveOffsetUs(timeline, mediaPeriodId.periodUid, j));
            return;
        }
        if (!Util.areEqual(!timeline2.isEmpty() ? timeline2.getWindow(timeline2.getPeriodByUid(mediaPeriodId2.periodUid, this.period).windowIndex, this.window).uid : null, this.window.uid) || z) {
            this.livePlaybackSpeedControl.setTargetLiveOffsetOverrideUs(C.TIME_UNSET);
        }
    }

    private long getMaxRendererReadPositionUs() {
        MediaPeriodHolder readingPeriod = this.queue.getReadingPeriod();
        if (readingPeriod == null) {
            return 0;
        }
        long rendererOffset = readingPeriod.getRendererOffset();
        if (!readingPeriod.prepared) {
            return rendererOffset;
        }
        int i = 0;
        while (true) {
            Renderer[] rendererArr = this.renderers;
            if (i >= rendererArr.length) {
                return rendererOffset;
            }
            if (isRendererEnabled(rendererArr[i]) && this.renderers[i].getStream() == readingPeriod.sampleStreams[i]) {
                long readingPositionUs = this.renderers[i].getReadingPositionUs();
                if (readingPositionUs == Long.MIN_VALUE) {
                    return Long.MIN_VALUE;
                }
                rendererOffset = Math.max(readingPositionUs, rendererOffset);
            }
            i++;
        }
    }

    private void updatePeriods() throws ExoPlaybackException {
        if (!this.playbackInfo.timeline.isEmpty() && this.mediaSourceList.isPrepared()) {
            boolean maybeUpdateLoadingPeriod = maybeUpdateLoadingPeriod();
            maybeUpdateReadingPeriod();
            maybeUpdateReadingRenderers();
            maybeUpdatePlayingPeriod();
            maybeUpdatePreloadPeriods(maybeUpdateLoadingPeriod);
        }
    }

    private boolean maybeUpdateLoadingPeriod() throws ExoPlaybackException {
        MediaPeriodInfo nextMediaPeriodInfo;
        this.queue.reevaluateBuffer(this.rendererPositionUs);
        boolean z = false;
        if (this.queue.shouldLoadNextMediaPeriod() && (nextMediaPeriodInfo = this.queue.getNextMediaPeriodInfo(this.rendererPositionUs, this.playbackInfo)) != null) {
            MediaPeriodHolder enqueueNextMediaPeriodHolder = this.queue.enqueueNextMediaPeriodHolder(nextMediaPeriodInfo);
            if (!enqueueNextMediaPeriodHolder.prepareCalled) {
                enqueueNextMediaPeriodHolder.prepare(this, nextMediaPeriodInfo.startPositionUs);
            } else if (enqueueNextMediaPeriodHolder.prepared) {
                this.handler.obtainMessage(8, enqueueNextMediaPeriodHolder.mediaPeriod).sendToTarget();
            }
            if (this.queue.getPlayingPeriod() == enqueueNextMediaPeriodHolder) {
                resetRendererPosition(nextMediaPeriodInfo.startPositionUs);
            }
            handleLoadingMediaPeriodChanged(false);
            z = true;
        }
        if (this.shouldContinueLoading) {
            this.shouldContinueLoading = isLoadingPossible(this.queue.getLoadingPeriod());
            updateIsLoading();
        } else {
            maybeContinueLoading();
        }
        return z;
    }

    private void maybeUpdateReadingPeriod() throws ExoPlaybackException {
        MediaPeriodHolder readingPeriod = this.queue.getReadingPeriod();
        if (readingPeriod != null) {
            int i = 0;
            if (readingPeriod.getNext() == null || this.pendingPauseAtEndOfPeriod) {
                if (readingPeriod.info.isFinal || this.pendingPauseAtEndOfPeriod) {
                    while (true) {
                        Renderer[] rendererArr = this.renderers;
                        if (i < rendererArr.length) {
                            Renderer renderer = rendererArr[i];
                            SampleStream sampleStream = readingPeriod.sampleStreams[i];
                            if (sampleStream != null && renderer.getStream() == sampleStream && renderer.hasReadStreamToEnd()) {
                                setCurrentStreamFinal(renderer, (readingPeriod.info.durationUs == C.TIME_UNSET || readingPeriod.info.durationUs == Long.MIN_VALUE) ? -9223372036854775807L : readingPeriod.getRendererOffset() + readingPeriod.info.durationUs);
                            }
                            i++;
                        } else {
                            return;
                        }
                    }
                }
            } else if (hasReadingPeriodFinishedReading()) {
                if (readingPeriod.getNext().prepared || this.rendererPositionUs >= readingPeriod.getNext().getStartPositionRendererTime()) {
                    TrackSelectorResult trackSelectorResult = readingPeriod.getTrackSelectorResult();
                    MediaPeriodHolder advanceReadingPeriod = this.queue.advanceReadingPeriod();
                    TrackSelectorResult trackSelectorResult2 = advanceReadingPeriod.getTrackSelectorResult();
                    updatePlaybackSpeedSettingsForNewPeriod(this.playbackInfo.timeline, advanceReadingPeriod.info.id, this.playbackInfo.timeline, readingPeriod.info.id, C.TIME_UNSET, false);
                    if (!advanceReadingPeriod.prepared || advanceReadingPeriod.mediaPeriod.readDiscontinuity() == C.TIME_UNSET) {
                        for (int i2 = 0; i2 < this.renderers.length; i2++) {
                            boolean isRendererEnabled = trackSelectorResult.isRendererEnabled(i2);
                            boolean isRendererEnabled2 = trackSelectorResult2.isRendererEnabled(i2);
                            if (isRendererEnabled && !this.renderers[i2].isCurrentStreamFinal()) {
                                boolean z = this.rendererCapabilities[i2].getTrackType() == -2;
                                RendererConfiguration rendererConfiguration = trackSelectorResult.rendererConfigurations[i2];
                                RendererConfiguration rendererConfiguration2 = trackSelectorResult2.rendererConfigurations[i2];
                                if (!isRendererEnabled2 || !rendererConfiguration2.equals(rendererConfiguration) || z) {
                                    setCurrentStreamFinal(this.renderers[i2], advanceReadingPeriod.getStartPositionRendererTime());
                                }
                            }
                        }
                        return;
                    }
                    setAllRendererStreamsFinal(advanceReadingPeriod.getStartPositionRendererTime());
                    if (!advanceReadingPeriod.isFullyBuffered()) {
                        this.queue.removeAfter(advanceReadingPeriod);
                        handleLoadingMediaPeriodChanged(false);
                        maybeContinueLoading();
                    }
                }
            }
        }
    }

    private void maybeUpdateReadingRenderers() throws ExoPlaybackException {
        MediaPeriodHolder readingPeriod = this.queue.getReadingPeriod();
        if (readingPeriod != null && this.queue.getPlayingPeriod() != readingPeriod && !readingPeriod.allRenderersInCorrectState && replaceStreamsOrDisableRendererForTransition()) {
            enableRenderers();
        }
    }

    private void maybeUpdatePreloadPeriods(boolean z) {
        if (this.preloadConfiguration.targetPreloadDurationUs != C.TIME_UNSET) {
            if (z || !this.playbackInfo.timeline.equals(this.lastPreloadPoolInvalidationTimeline)) {
                this.lastPreloadPoolInvalidationTimeline = this.playbackInfo.timeline;
                this.queue.invalidatePreloadPool(this.playbackInfo.timeline);
            }
            maybeContinuePreloading();
        }
    }

    private void maybeContinuePreloading() {
        this.queue.maybeUpdatePreloadMediaPeriodHolder();
        MediaPeriodHolder preloadingPeriod = this.queue.getPreloadingPeriod();
        if (preloadingPeriod == null) {
            return;
        }
        if ((!preloadingPeriod.prepareCalled || preloadingPeriod.prepared) && !preloadingPeriod.mediaPeriod.isLoading()) {
            if (this.loadControl.shouldContinuePreloading(this.playbackInfo.timeline, preloadingPeriod.info.id, preloadingPeriod.prepared ? preloadingPeriod.mediaPeriod.getBufferedPositionUs() : 0)) {
                if (!preloadingPeriod.prepareCalled) {
                    preloadingPeriod.prepare(this, preloadingPeriod.info.startPositionUs);
                } else {
                    preloadingPeriod.continueLoading(new LoadingInfo.Builder().setPlaybackPositionUs(preloadingPeriod.toPeriodTime(this.rendererPositionUs)).setPlaybackSpeed(this.mediaClock.getPlaybackParameters().speed).setLastRebufferRealtimeMs(this.lastRebufferRealtimeMs).build());
                }
            }
        }
    }

    private boolean replaceStreamsOrDisableRendererForTransition() throws ExoPlaybackException {
        MediaPeriodHolder readingPeriod = this.queue.getReadingPeriod();
        TrackSelectorResult trackSelectorResult = readingPeriod.getTrackSelectorResult();
        int i = 0;
        boolean z = false;
        while (true) {
            Renderer[] rendererArr = this.renderers;
            if (i >= rendererArr.length) {
                return !z;
            }
            Renderer renderer = rendererArr[i];
            if (isRendererEnabled(renderer)) {
                boolean z2 = renderer.getStream() != readingPeriod.sampleStreams[i];
                if (!trackSelectorResult.isRendererEnabled(i) || z2) {
                    if (!renderer.isCurrentStreamFinal()) {
                        renderer.replaceStream(getFormats(trackSelectorResult.selections[i]), readingPeriod.sampleStreams[i], readingPeriod.getStartPositionRendererTime(), readingPeriod.getRendererOffset(), readingPeriod.info.id);
                        if (this.offloadSchedulingEnabled) {
                            setOffloadSchedulingEnabled(false);
                        }
                    } else if (renderer.isEnded()) {
                        disableRenderer(i);
                    } else {
                        z = true;
                    }
                }
            }
            i++;
        }
    }

    private void maybeUpdatePlayingPeriod() throws ExoPlaybackException {
        boolean z = false;
        while (shouldAdvancePlayingPeriod()) {
            if (z) {
                maybeNotifyPlaybackInfoChanged();
            }
            MediaPeriodHolder mediaPeriodHolder = (MediaPeriodHolder) Assertions.checkNotNull(this.queue.advancePlayingPeriod());
            this.playbackInfo = handlePositionDiscontinuity(mediaPeriodHolder.info.id, mediaPeriodHolder.info.startPositionUs, mediaPeriodHolder.info.requestedContentPositionUs, mediaPeriodHolder.info.startPositionUs, !(this.playbackInfo.periodId.periodUid.equals(mediaPeriodHolder.info.id.periodUid) && this.playbackInfo.periodId.adGroupIndex == -1 && mediaPeriodHolder.info.id.adGroupIndex == -1 && this.playbackInfo.periodId.nextAdGroupIndex != mediaPeriodHolder.info.id.nextAdGroupIndex), 0);
            resetPendingPauseAtEndOfPeriod();
            updatePlaybackPositions();
            if (this.playbackInfo.playbackState == 3) {
                startRenderers();
            }
            allowRenderersToRenderStartOfStreams();
            z = true;
        }
    }

    private void maybeUpdateOffloadScheduling() {
        boolean z;
        MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod();
        if (playingPeriod != null) {
            TrackSelectorResult trackSelectorResult = playingPeriod.getTrackSelectorResult();
            boolean z2 = false;
            int i = 0;
            boolean z3 = false;
            while (true) {
                if (i >= this.renderers.length) {
                    z = true;
                    break;
                }
                if (trackSelectorResult.isRendererEnabled(i)) {
                    if (this.renderers[i].getTrackType() != 1) {
                        z = false;
                        break;
                    } else if (trackSelectorResult.rendererConfigurations[i].offloadModePreferred != 0) {
                        z3 = true;
                    }
                }
                i++;
            }
            if (z3 && z) {
                z2 = true;
            }
            setOffloadSchedulingEnabled(z2);
        }
    }

    private void allowRenderersToRenderStartOfStreams() {
        TrackSelectorResult trackSelectorResult = this.queue.getPlayingPeriod().getTrackSelectorResult();
        for (int i = 0; i < this.renderers.length; i++) {
            if (trackSelectorResult.isRendererEnabled(i)) {
                this.renderers[i].enableMayRenderStartOfStream();
            }
        }
    }

    private void resetPendingPauseAtEndOfPeriod() {
        MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod();
        this.pendingPauseAtEndOfPeriod = playingPeriod != null && playingPeriod.info.isLastInTimelineWindow && this.pauseAtEndOfWindow;
    }

    private boolean shouldAdvancePlayingPeriod() {
        MediaPeriodHolder playingPeriod;
        MediaPeriodHolder next;
        if (shouldPlayWhenReady() && !this.pendingPauseAtEndOfPeriod && (playingPeriod = this.queue.getPlayingPeriod()) != null && (next = playingPeriod.getNext()) != null && this.rendererPositionUs >= next.getStartPositionRendererTime() && next.allRenderersInCorrectState) {
            return true;
        }
        return false;
    }

    private boolean hasReadingPeriodFinishedReading() {
        MediaPeriodHolder readingPeriod = this.queue.getReadingPeriod();
        if (!readingPeriod.prepared) {
            return false;
        }
        int i = 0;
        while (true) {
            Renderer[] rendererArr = this.renderers;
            if (i >= rendererArr.length) {
                return true;
            }
            Renderer renderer = rendererArr[i];
            SampleStream sampleStream = readingPeriod.sampleStreams[i];
            if (renderer.getStream() != sampleStream || (sampleStream != null && !renderer.hasReadStreamToEnd() && !hasReachedServerSideInsertedAdsTransition(renderer, readingPeriod))) {
                return false;
            }
            i++;
        }
        return false;
    }

    private boolean hasReachedServerSideInsertedAdsTransition(Renderer renderer, MediaPeriodHolder mediaPeriodHolder) {
        MediaPeriodHolder next = mediaPeriodHolder.getNext();
        return mediaPeriodHolder.info.isFollowedByTransitionToSameStream && next.prepared && ((renderer instanceof TextRenderer) || (renderer instanceof MetadataRenderer) || renderer.getReadingPositionUs() >= next.getStartPositionRendererTime());
    }

    private void setAllRendererStreamsFinal(long j) {
        for (Renderer renderer : this.renderers) {
            if (renderer.getStream() != null) {
                setCurrentStreamFinal(renderer, j);
            }
        }
    }

    private void setCurrentStreamFinal(Renderer renderer, long j) {
        renderer.setCurrentStreamFinal();
        if (renderer instanceof TextRenderer) {
            ((TextRenderer) renderer).setFinalStreamEndPositionUs(j);
        }
    }

    private void handlePeriodPrepared(MediaPeriod mediaPeriod) throws ExoPlaybackException {
        if (this.queue.isLoading(mediaPeriod)) {
            handleLoadingPeriodPrepared((MediaPeriodHolder) Assertions.checkNotNull(this.queue.getLoadingPeriod()));
            return;
        }
        MediaPeriodHolder preloadHolderByMediaPeriod = this.queue.getPreloadHolderByMediaPeriod(mediaPeriod);
        if (preloadHolderByMediaPeriod != null) {
            Assertions.checkState(!preloadHolderByMediaPeriod.prepared);
            preloadHolderByMediaPeriod.handlePrepared(this.mediaClock.getPlaybackParameters().speed, this.playbackInfo.timeline, this.playbackInfo.playWhenReady);
            if (this.queue.isPreloading(mediaPeriod)) {
                maybeContinuePreloading();
            }
        }
    }

    private void handleLoadingPeriodPrepared(MediaPeriodHolder mediaPeriodHolder) throws ExoPlaybackException {
        if (!mediaPeriodHolder.prepared) {
            mediaPeriodHolder.handlePrepared(this.mediaClock.getPlaybackParameters().speed, this.playbackInfo.timeline, this.playbackInfo.playWhenReady);
        }
        updateLoadControlTrackSelection(mediaPeriodHolder.info.id, mediaPeriodHolder.getTrackGroups(), mediaPeriodHolder.getTrackSelectorResult());
        if (mediaPeriodHolder == this.queue.getPlayingPeriod()) {
            resetRendererPosition(mediaPeriodHolder.info.startPositionUs);
            enableRenderers();
            this.playbackInfo = handlePositionDiscontinuity(this.playbackInfo.periodId, mediaPeriodHolder.info.startPositionUs, this.playbackInfo.requestedContentPositionUs, mediaPeriodHolder.info.startPositionUs, false, 5);
        }
        maybeContinueLoading();
    }

    private void handleContinueLoadingRequested(MediaPeriod mediaPeriod) {
        if (this.queue.isLoading(mediaPeriod)) {
            this.queue.reevaluateBuffer(this.rendererPositionUs);
            maybeContinueLoading();
        } else if (this.queue.isPreloading(mediaPeriod)) {
            maybeContinuePreloading();
        }
    }

    private void handlePlaybackParameters(PlaybackParameters playbackParameters, boolean z) throws ExoPlaybackException {
        handlePlaybackParameters(playbackParameters, playbackParameters.speed, true, z);
    }

    private void handlePlaybackParameters(PlaybackParameters playbackParameters, float f, boolean z, boolean z2) throws ExoPlaybackException {
        if (z) {
            if (z2) {
                this.playbackInfoUpdate.incrementPendingOperationAcks(1);
            }
            this.playbackInfo = this.playbackInfo.copyWithPlaybackParameters(playbackParameters);
        }
        updateTrackSelectionPlaybackSpeed(playbackParameters.speed);
        for (Renderer renderer : this.renderers) {
            if (renderer != null) {
                renderer.setPlaybackSpeed(f, playbackParameters.speed);
            }
        }
    }

    private void maybeContinueLoading() {
        boolean shouldContinueLoading2 = shouldContinueLoading();
        this.shouldContinueLoading = shouldContinueLoading2;
        if (shouldContinueLoading2) {
            MediaPeriodHolder mediaPeriodHolder = (MediaPeriodHolder) Assertions.checkNotNull(this.queue.getLoadingPeriod());
            mediaPeriodHolder.continueLoading(new LoadingInfo.Builder().setPlaybackPositionUs(mediaPeriodHolder.toPeriodTime(this.rendererPositionUs)).setPlaybackSpeed(this.mediaClock.getPlaybackParameters().speed).setLastRebufferRealtimeMs(this.lastRebufferRealtimeMs).build());
        }
        updateIsLoading();
    }

    private boolean shouldContinueLoading() {
        long j;
        if (!isLoadingPossible(this.queue.getLoadingPeriod())) {
            return false;
        }
        MediaPeriodHolder loadingPeriod = this.queue.getLoadingPeriod();
        long totalBufferedDurationUs = getTotalBufferedDurationUs(loadingPeriod.getNextLoadPositionUs());
        if (loadingPeriod == this.queue.getPlayingPeriod()) {
            j = loadingPeriod.toPeriodTime(this.rendererPositionUs);
        } else {
            j = loadingPeriod.toPeriodTime(this.rendererPositionUs) - loadingPeriod.info.startPositionUs;
        }
        LoadControl.Parameters parameters = r5;
        LoadControl.Parameters parameters2 = new LoadControl.Parameters(this.playerId, this.playbackInfo.timeline, loadingPeriod.info.id, j, totalBufferedDurationUs, this.mediaClock.getPlaybackParameters().speed, this.playbackInfo.playWhenReady, this.isRebuffering, shouldUseLivePlaybackSpeedControl(this.playbackInfo.timeline, loadingPeriod.info.id) ? this.livePlaybackSpeedControl.getTargetLiveOffsetUs() : C.TIME_UNSET);
        boolean shouldContinueLoading2 = this.loadControl.shouldContinueLoading(parameters);
        MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod();
        if (shouldContinueLoading2 || !playingPeriod.prepared || totalBufferedDurationUs >= PLAYBACK_BUFFER_EMPTY_THRESHOLD_US) {
            return shouldContinueLoading2;
        }
        if (this.backBufferDurationUs <= 0 && !this.retainBackBufferFromKeyframe) {
            return shouldContinueLoading2;
        }
        playingPeriod.mediaPeriod.discardBuffer(this.playbackInfo.positionUs, false);
        return this.loadControl.shouldContinueLoading(parameters);
    }

    private boolean isLoadingPossible(MediaPeriodHolder mediaPeriodHolder) {
        return (mediaPeriodHolder == null || mediaPeriodHolder.hasLoadingError() || mediaPeriodHolder.getNextLoadPositionUs() == Long.MIN_VALUE) ? false : true;
    }

    private void updateIsLoading() {
        MediaPeriodHolder loadingPeriod = this.queue.getLoadingPeriod();
        boolean z = this.shouldContinueLoading || (loadingPeriod != null && loadingPeriod.mediaPeriod.isLoading());
        if (z != this.playbackInfo.isLoading) {
            this.playbackInfo = this.playbackInfo.copyWithIsLoading(z);
        }
    }

    private PlaybackInfo handlePositionDiscontinuity(MediaSource.MediaPeriodId mediaPeriodId, long j, long j2, long j3, boolean z, int i) {
        ImmutableList<Metadata> immutableList;
        TrackSelectorResult trackSelectorResult;
        TrackGroupArray trackGroupArray;
        TrackGroupArray trackGroupArray2;
        TrackSelectorResult trackSelectorResult2;
        MediaSource.MediaPeriodId mediaPeriodId2 = mediaPeriodId;
        long j4 = j2;
        this.deliverPendingMessageAtStartPositionRequired = this.deliverPendingMessageAtStartPositionRequired || j != this.playbackInfo.positionUs || !mediaPeriodId.equals(this.playbackInfo.periodId);
        resetPendingPauseAtEndOfPeriod();
        TrackGroupArray trackGroupArray3 = this.playbackInfo.trackGroups;
        TrackSelectorResult trackSelectorResult3 = this.playbackInfo.trackSelectorResult;
        List list = this.playbackInfo.staticMetadata;
        if (this.mediaSourceList.isPrepared()) {
            MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod();
            if (playingPeriod == null) {
                trackGroupArray2 = TrackGroupArray.EMPTY;
            } else {
                trackGroupArray2 = playingPeriod.getTrackGroups();
            }
            if (playingPeriod == null) {
                trackSelectorResult2 = this.emptyTrackSelectorResult;
            } else {
                trackSelectorResult2 = playingPeriod.getTrackSelectorResult();
            }
            ImmutableList<Metadata> extractMetadataFromTrackSelectionArray = extractMetadataFromTrackSelectionArray(trackSelectorResult2.selections);
            if (!(playingPeriod == null || playingPeriod.info.requestedContentPositionUs == j4)) {
                playingPeriod.info = playingPeriod.info.copyWithRequestedContentPositionUs(j4);
            }
            maybeUpdateOffloadScheduling();
            trackGroupArray = trackGroupArray2;
            trackSelectorResult = trackSelectorResult2;
            immutableList = extractMetadataFromTrackSelectionArray;
        } else {
            if (!mediaPeriodId.equals(this.playbackInfo.periodId)) {
                trackGroupArray3 = TrackGroupArray.EMPTY;
                trackSelectorResult3 = this.emptyTrackSelectorResult;
                list = ImmutableList.of();
            }
            trackGroupArray = trackGroupArray3;
            trackSelectorResult = trackSelectorResult3;
            immutableList = list;
        }
        if (z) {
            this.playbackInfoUpdate.setPositionDiscontinuity(i);
        }
        return this.playbackInfo.copyWithNewPosition(mediaPeriodId, j, j2, j3, getTotalBufferedDurationUs(), trackGroupArray, trackSelectorResult, immutableList);
    }

    private ImmutableList<Metadata> extractMetadataFromTrackSelectionArray(ExoTrackSelection[] exoTrackSelectionArr) {
        ImmutableList.Builder builder = new ImmutableList.Builder();
        boolean z = false;
        for (ExoTrackSelection exoTrackSelection : exoTrackSelectionArr) {
            if (exoTrackSelection != null) {
                Format format = exoTrackSelection.getFormat(0);
                if (format.metadata == null) {
                    builder.add((Object) new Metadata(new Metadata.Entry[0]));
                } else {
                    builder.add((Object) format.metadata);
                    z = true;
                }
            }
        }
        return z ? builder.build() : ImmutableList.of();
    }

    private void enableRenderers() throws ExoPlaybackException {
        enableRenderers(new boolean[this.renderers.length], this.queue.getReadingPeriod().getStartPositionRendererTime());
    }

    private void enableRenderers(boolean[] zArr, long j) throws ExoPlaybackException {
        MediaPeriodHolder readingPeriod = this.queue.getReadingPeriod();
        TrackSelectorResult trackSelectorResult = readingPeriod.getTrackSelectorResult();
        for (int i = 0; i < this.renderers.length; i++) {
            if (!trackSelectorResult.isRendererEnabled(i) && this.renderersToReset.remove(this.renderers[i])) {
                this.renderers[i].reset();
            }
        }
        for (int i2 = 0; i2 < this.renderers.length; i2++) {
            if (trackSelectorResult.isRendererEnabled(i2)) {
                enableRenderer(i2, zArr[i2], j);
            }
        }
        readingPeriod.allRenderersInCorrectState = true;
    }

    private void enableRenderer(int i, boolean z, long j) throws ExoPlaybackException {
        Renderer renderer = this.renderers[i];
        if (!isRendererEnabled(renderer)) {
            MediaPeriodHolder readingPeriod = this.queue.getReadingPeriod();
            boolean z2 = readingPeriod == this.queue.getPlayingPeriod();
            TrackSelectorResult trackSelectorResult = readingPeriod.getTrackSelectorResult();
            RendererConfiguration rendererConfiguration = trackSelectorResult.rendererConfigurations[i];
            Format[] formats = getFormats(trackSelectorResult.selections[i]);
            boolean z3 = shouldPlayWhenReady() && this.playbackInfo.playbackState == 3;
            boolean z4 = !z && z3;
            this.enabledRendererCount++;
            this.renderersToReset.add(renderer);
            renderer.enable(rendererConfiguration, formats, readingPeriod.sampleStreams[i], this.rendererPositionUs, z4, z2, j, readingPeriod.getRendererOffset(), readingPeriod.info.id);
            renderer.handleMessage(11, new Renderer.WakeupListener() {
                public void onSleep() {
                    boolean unused = ExoPlayerImplInternal.this.requestForRendererSleep = true;
                }

                public void onWakeup() {
                    if (ExoPlayerImplInternal.this.dynamicSchedulingEnabled || ExoPlayerImplInternal.this.offloadSchedulingEnabled) {
                        ExoPlayerImplInternal.this.handler.sendEmptyMessage(2);
                    }
                }
            });
            this.mediaClock.onRendererEnabled(renderer);
            if (z3 && z2) {
                renderer.start();
            }
        }
    }

    private void releaseRenderers() {
        for (int i = 0; i < this.renderers.length; i++) {
            this.rendererCapabilities[i].clearListener();
            this.renderers[i].release();
        }
    }

    private void handleLoadingMediaPeriodChanged(boolean z) {
        long j;
        MediaPeriodHolder loadingPeriod = this.queue.getLoadingPeriod();
        MediaSource.MediaPeriodId mediaPeriodId = loadingPeriod == null ? this.playbackInfo.periodId : loadingPeriod.info.id;
        boolean z2 = !this.playbackInfo.loadingMediaPeriodId.equals(mediaPeriodId);
        if (z2) {
            this.playbackInfo = this.playbackInfo.copyWithLoadingMediaPeriodId(mediaPeriodId);
        }
        PlaybackInfo playbackInfo2 = this.playbackInfo;
        if (loadingPeriod == null) {
            j = playbackInfo2.positionUs;
        } else {
            j = loadingPeriod.getBufferedPositionUs();
        }
        playbackInfo2.bufferedPositionUs = j;
        this.playbackInfo.totalBufferedDurationUs = getTotalBufferedDurationUs();
        if ((z2 || z) && loadingPeriod != null && loadingPeriod.prepared) {
            updateLoadControlTrackSelection(loadingPeriod.info.id, loadingPeriod.getTrackGroups(), loadingPeriod.getTrackSelectorResult());
        }
    }

    private long getTotalBufferedDurationUs() {
        return getTotalBufferedDurationUs(this.playbackInfo.bufferedPositionUs);
    }

    private long getTotalBufferedDurationUs(long j) {
        MediaPeriodHolder loadingPeriod = this.queue.getLoadingPeriod();
        if (loadingPeriod == null) {
            return 0;
        }
        return Math.max(0, j - loadingPeriod.toPeriodTime(this.rendererPositionUs));
    }

    private void updateLoadControlTrackSelection(MediaSource.MediaPeriodId mediaPeriodId, TrackGroupArray trackGroupArray, TrackSelectorResult trackSelectorResult) {
        long j;
        MediaPeriodHolder mediaPeriodHolder = (MediaPeriodHolder) Assertions.checkNotNull(this.queue.getLoadingPeriod());
        if (mediaPeriodHolder == this.queue.getPlayingPeriod()) {
            j = mediaPeriodHolder.toPeriodTime(this.rendererPositionUs);
        } else {
            j = mediaPeriodHolder.toPeriodTime(this.rendererPositionUs) - mediaPeriodHolder.info.startPositionUs;
        }
        TrackGroupArray trackGroupArray2 = trackGroupArray;
        this.loadControl.onTracksSelected(new LoadControl.Parameters(this.playerId, this.playbackInfo.timeline, mediaPeriodId, j, getTotalBufferedDurationUs(mediaPeriodHolder.getBufferedPositionUs()), this.mediaClock.getPlaybackParameters().speed, this.playbackInfo.playWhenReady, this.isRebuffering, shouldUseLivePlaybackSpeedControl(this.playbackInfo.timeline, mediaPeriodHolder.info.id) ? this.livePlaybackSpeedControl.getTargetLiveOffsetUs() : C.TIME_UNSET), trackGroupArray2, trackSelectorResult.selections);
    }

    private boolean shouldPlayWhenReady() {
        return this.playbackInfo.playWhenReady && this.playbackInfo.playbackSuppressionReason == 0;
    }

    private void maybeThrowRendererStreamError(int i) throws IOException, ExoPlaybackException {
        Renderer renderer = this.renderers[i];
        try {
            renderer.maybeThrowStreamError();
        } catch (IOException | RuntimeException e) {
            int trackType = renderer.getTrackType();
            if (trackType == 3 || trackType == 5) {
                TrackSelectorResult trackSelectorResult = this.queue.getPlayingPeriod().getTrackSelectorResult();
                Log.e(TAG, "Disabling track due to error: " + Format.toLogString(trackSelectorResult.selections[i].getSelectedFormat()), e);
                TrackSelectorResult trackSelectorResult2 = new TrackSelectorResult((RendererConfiguration[]) trackSelectorResult.rendererConfigurations.clone(), (ExoTrackSelection[]) trackSelectorResult.selections.clone(), trackSelectorResult.tracks, trackSelectorResult.info);
                trackSelectorResult2.rendererConfigurations[i] = null;
                trackSelectorResult2.selections[i] = null;
                disableRenderer(i);
                this.queue.getPlayingPeriod().applyTrackSelection(trackSelectorResult2, this.playbackInfo.positionUs, false);
                return;
            }
            throw e;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:47:0x0151  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x016f  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x0186  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0189  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x01bf  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static androidx.media3.exoplayer.ExoPlayerImplInternal.PositionUpdateForPlaylistChange resolvePositionForPlaylistChange(androidx.media3.common.Timeline r30, androidx.media3.exoplayer.PlaybackInfo r31, androidx.media3.exoplayer.ExoPlayerImplInternal.SeekPosition r32, androidx.media3.exoplayer.MediaPeriodQueue r33, int r34, boolean r35, androidx.media3.common.Timeline.Window r36, androidx.media3.common.Timeline.Period r37) {
        /*
            r7 = r30
            r8 = r31
            r9 = r32
            r10 = r35
            r11 = r37
            boolean r0 = r30.isEmpty()
            if (r0 == 0) goto L_0x0025
            androidx.media3.exoplayer.ExoPlayerImplInternal$PositionUpdateForPlaylistChange r0 = new androidx.media3.exoplayer.ExoPlayerImplInternal$PositionUpdateForPlaylistChange
            androidx.media3.exoplayer.source.MediaSource$MediaPeriodId r2 = androidx.media3.exoplayer.PlaybackInfo.getDummyPeriodForEmptyTimeline()
            r3 = 0
            r5 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r7 = 0
            r8 = 1
            r9 = 0
            r1 = r0
            r1.<init>(r2, r3, r5, r7, r8, r9)
            return r0
        L_0x0025:
            androidx.media3.exoplayer.source.MediaSource$MediaPeriodId r14 = r8.periodId
            java.lang.Object r12 = r14.periodUid
            boolean r13 = isUsingPlaceholderPeriod(r8, r11)
            androidx.media3.exoplayer.source.MediaSource$MediaPeriodId r0 = r8.periodId
            boolean r0 = r0.isAd()
            if (r0 != 0) goto L_0x003b
            if (r13 == 0) goto L_0x0038
            goto L_0x003b
        L_0x0038:
            long r0 = r8.positionUs
            goto L_0x003d
        L_0x003b:
            long r0 = r8.requestedContentPositionUs
        L_0x003d:
            r15 = r0
            r17 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r19 = 0
            r6 = -1
            r20 = 1
            if (r9 == 0) goto L_0x00a1
            r2 = 1
            r0 = r30
            r1 = r32
            r3 = r34
            r4 = r35
            r5 = r36
            r21 = r14
            r14 = r6
            r6 = r37
            android.util.Pair r0 = resolveSeekPositionUs(r0, r1, r2, r3, r4, r5, r6)
            if (r0 != 0) goto L_0x006c
            int r0 = r7.getFirstWindowIndex(r10)
            r6 = r0
            r0 = r15
            r2 = r19
            r3 = r2
            r4 = r20
            goto L_0x0097
        L_0x006c:
            long r1 = r9.windowPositionUs
            int r1 = (r1 > r17 ? 1 : (r1 == r17 ? 0 : -1))
            if (r1 != 0) goto L_0x007e
            java.lang.Object r0 = r0.first
            androidx.media3.common.Timeline$Period r0 = r7.getPeriodByUid(r0, r11)
            int r6 = r0.windowIndex
            r0 = r15
            r2 = r19
            goto L_0x008b
        L_0x007e:
            java.lang.Object r12 = r0.first
            java.lang.Object r0 = r0.second
            java.lang.Long r0 = (java.lang.Long) r0
            long r0 = r0.longValue()
            r6 = r14
            r2 = r20
        L_0x008b:
            int r3 = r8.playbackState
            r4 = 4
            if (r3 != r4) goto L_0x0093
            r3 = r20
            goto L_0x0095
        L_0x0093:
            r3 = r19
        L_0x0095:
            r4 = r19
        L_0x0097:
            r9 = r36
            r29 = r2
            r27 = r3
            r28 = r4
            r3 = r6
            goto L_0x00ba
        L_0x00a1:
            r21 = r14
            r14 = r6
            androidx.media3.common.Timeline r0 = r8.timeline
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L_0x00be
            int r0 = r7.getFirstWindowIndex(r10)
        L_0x00b0:
            r9 = r36
            r3 = r0
            r0 = r15
            r27 = r19
            r28 = r27
            r29 = r28
        L_0x00ba:
            r6 = r21
            goto L_0x014f
        L_0x00be:
            int r0 = r7.getIndexOfPeriod(r12)
            if (r0 != r14) goto L_0x00eb
            androidx.media3.common.Timeline r5 = r8.timeline
            r0 = r36
            r1 = r37
            r2 = r34
            r3 = r35
            r4 = r12
            r6 = r30
            int r0 = resolveSubsequentPeriod(r0, r1, r2, r3, r4, r5, r6)
            if (r0 != r14) goto L_0x00de
            int r0 = r7.getFirstWindowIndex(r10)
            r4 = r20
            goto L_0x00e0
        L_0x00de:
            r4 = r19
        L_0x00e0:
            r9 = r36
            r3 = r0
            r28 = r4
            r0 = r15
            r27 = r19
            r29 = r27
            goto L_0x00ba
        L_0x00eb:
            int r0 = (r15 > r17 ? 1 : (r15 == r17 ? 0 : -1))
            if (r0 != 0) goto L_0x00f6
            androidx.media3.common.Timeline$Period r0 = r7.getPeriodByUid(r12, r11)
            int r0 = r0.windowIndex
            goto L_0x00b0
        L_0x00f6:
            if (r13 == 0) goto L_0x0143
            androidx.media3.common.Timeline r0 = r8.timeline
            r6 = r21
            java.lang.Object r1 = r6.periodUid
            r0.getPeriodByUid(r1, r11)
            androidx.media3.common.Timeline r0 = r8.timeline
            int r1 = r11.windowIndex
            r9 = r36
            androidx.media3.common.Timeline$Window r0 = r0.getWindow(r1, r9)
            int r0 = r0.firstPeriodIndex
            androidx.media3.common.Timeline r1 = r8.timeline
            java.lang.Object r2 = r6.periodUid
            int r1 = r1.getIndexOfPeriod(r2)
            if (r0 != r1) goto L_0x013a
            long r0 = r37.getPositionInWindowUs()
            long r4 = r15 + r0
            androidx.media3.common.Timeline$Period r0 = r7.getPeriodByUid(r12, r11)
            int r3 = r0.windowIndex
            r0 = r30
            r1 = r36
            r2 = r37
            android.util.Pair r0 = r0.getPeriodPositionUs(r1, r2, r3, r4)
            java.lang.Object r1 = r0.first
            java.lang.Object r0 = r0.second
            java.lang.Long r0 = (java.lang.Long) r0
            long r2 = r0.longValue()
            r12 = r1
            r0 = r2
            goto L_0x013b
        L_0x013a:
            r0 = r15
        L_0x013b:
            r3 = r14
            r27 = r19
            r28 = r27
            r29 = r20
            goto L_0x014f
        L_0x0143:
            r9 = r36
            r6 = r21
            r3 = r14
            r0 = r15
            r27 = r19
            r28 = r27
            r29 = r28
        L_0x014f:
            if (r3 == r14) goto L_0x016f
            r4 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r0 = r30
            r1 = r36
            r2 = r37
            android.util.Pair r0 = r0.getPeriodPositionUs(r1, r2, r3, r4)
            java.lang.Object r12 = r0.first
            java.lang.Object r0 = r0.second
            java.lang.Long r0 = (java.lang.Long) r0
            long r0 = r0.longValue()
            r2 = r33
            r25 = r17
            goto L_0x0173
        L_0x016f:
            r2 = r33
            r25 = r0
        L_0x0173:
            androidx.media3.exoplayer.source.MediaSource$MediaPeriodId r2 = r2.resolveMediaPeriodIdForAdsAfterPeriodPositionChange(r7, r12, r0)
            int r3 = r2.nextAdGroupIndex
            if (r3 == r14) goto L_0x0189
            int r3 = r6.nextAdGroupIndex
            if (r3 == r14) goto L_0x0186
            int r3 = r2.nextAdGroupIndex
            int r4 = r6.nextAdGroupIndex
            if (r3 < r4) goto L_0x0186
            goto L_0x0189
        L_0x0186:
            r3 = r19
            goto L_0x018b
        L_0x0189:
            r3 = r20
        L_0x018b:
            java.lang.Object r4 = r6.periodUid
            boolean r4 = r4.equals(r12)
            if (r4 == 0) goto L_0x01a2
            boolean r4 = r6.isAd()
            if (r4 != 0) goto L_0x01a2
            boolean r4 = r2.isAd()
            if (r4 != 0) goto L_0x01a2
            if (r3 == 0) goto L_0x01a2
            goto L_0x01a4
        L_0x01a2:
            r20 = r19
        L_0x01a4:
            androidx.media3.common.Timeline$Period r17 = r7.getPeriodByUid(r12, r11)
            r12 = r13
            r13 = r6
            r3 = r6
            r14 = r15
            r16 = r2
            r18 = r25
            boolean r4 = isIgnorableServerSideAdInsertionPeriodChange(r12, r13, r14, r16, r17, r18)
            if (r20 != 0) goto L_0x01b8
            if (r4 == 0) goto L_0x01b9
        L_0x01b8:
            r2 = r3
        L_0x01b9:
            boolean r4 = r2.isAd()
            if (r4 == 0) goto L_0x01de
            boolean r0 = r2.equals(r3)
            if (r0 == 0) goto L_0x01c8
            long r0 = r8.positionUs
            goto L_0x01de
        L_0x01c8:
            java.lang.Object r0 = r2.periodUid
            r7.getPeriodByUid(r0, r11)
            int r0 = r2.adIndexInAdGroup
            int r1 = r2.adGroupIndex
            int r1 = r11.getFirstAdIndexToPlay(r1)
            if (r0 != r1) goto L_0x01dc
            long r0 = r37.getAdResumePositionUs()
            goto L_0x01de
        L_0x01dc:
            r0 = 0
        L_0x01de:
            r23 = r0
            androidx.media3.exoplayer.ExoPlayerImplInternal$PositionUpdateForPlaylistChange r0 = new androidx.media3.exoplayer.ExoPlayerImplInternal$PositionUpdateForPlaylistChange
            r21 = r0
            r22 = r2
            r21.<init>(r22, r23, r25, r27, r28, r29)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.ExoPlayerImplInternal.resolvePositionForPlaylistChange(androidx.media3.common.Timeline, androidx.media3.exoplayer.PlaybackInfo, androidx.media3.exoplayer.ExoPlayerImplInternal$SeekPosition, androidx.media3.exoplayer.MediaPeriodQueue, int, boolean, androidx.media3.common.Timeline$Window, androidx.media3.common.Timeline$Period):androidx.media3.exoplayer.ExoPlayerImplInternal$PositionUpdateForPlaylistChange");
    }

    private static boolean isIgnorableServerSideAdInsertionPeriodChange(boolean z, MediaSource.MediaPeriodId mediaPeriodId, long j, MediaSource.MediaPeriodId mediaPeriodId2, Timeline.Period period2, long j2) {
        if (z || j != j2 || !mediaPeriodId.periodUid.equals(mediaPeriodId2.periodUid)) {
            return false;
        }
        if (!mediaPeriodId.isAd() || !period2.isServerSideInsertedAdGroup(mediaPeriodId.adGroupIndex)) {
            if (!mediaPeriodId2.isAd() || !period2.isServerSideInsertedAdGroup(mediaPeriodId2.adGroupIndex)) {
                return false;
            }
            return true;
        } else if (period2.getAdState(mediaPeriodId.adGroupIndex, mediaPeriodId.adIndexInAdGroup) == 4 || period2.getAdState(mediaPeriodId.adGroupIndex, mediaPeriodId.adIndexInAdGroup) == 2) {
            return false;
        } else {
            return true;
        }
    }

    private static boolean isUsingPlaceholderPeriod(PlaybackInfo playbackInfo2, Timeline.Period period2) {
        MediaSource.MediaPeriodId mediaPeriodId = playbackInfo2.periodId;
        Timeline timeline = playbackInfo2.timeline;
        return timeline.isEmpty() || timeline.getPeriodByUid(mediaPeriodId.periodUid, period2).isPlaceholder;
    }

    private void updateRebufferingState(boolean z, boolean z2) {
        this.isRebuffering = z;
        this.lastRebufferRealtimeMs = (!z || z2) ? C.TIME_UNSET : this.clock.elapsedRealtime();
    }

    private static boolean resolvePendingMessagePosition(PendingMessageInfo pendingMessageInfo, Timeline timeline, Timeline timeline2, int i, boolean z, Timeline.Window window2, Timeline.Period period2) {
        long j;
        PendingMessageInfo pendingMessageInfo2 = pendingMessageInfo;
        Timeline timeline3 = timeline;
        Timeline timeline4 = timeline2;
        Timeline.Window window3 = window2;
        Timeline.Period period3 = period2;
        if (pendingMessageInfo2.resolvedPeriodUid == null) {
            if (pendingMessageInfo2.message.getPositionMs() == Long.MIN_VALUE) {
                j = C.TIME_UNSET;
            } else {
                j = Util.msToUs(pendingMessageInfo2.message.getPositionMs());
            }
            Pair<Object, Long> resolveSeekPositionUs = resolveSeekPositionUs(timeline, new SeekPosition(pendingMessageInfo2.message.getTimeline(), pendingMessageInfo2.message.getMediaItemIndex(), j), false, i, z, window2, period2);
            if (resolveSeekPositionUs == null) {
                return false;
            }
            pendingMessageInfo.setResolvedPosition(timeline3.getIndexOfPeriod(resolveSeekPositionUs.first), ((Long) resolveSeekPositionUs.second).longValue(), resolveSeekPositionUs.first);
            if (pendingMessageInfo2.message.getPositionMs() == Long.MIN_VALUE) {
                resolvePendingMessageEndOfStreamPosition(timeline3, pendingMessageInfo, window3, period3);
            }
            return true;
        }
        int indexOfPeriod = timeline3.getIndexOfPeriod(pendingMessageInfo2.resolvedPeriodUid);
        if (indexOfPeriod == -1) {
            return false;
        }
        if (pendingMessageInfo2.message.getPositionMs() == Long.MIN_VALUE) {
            resolvePendingMessageEndOfStreamPosition(timeline3, pendingMessageInfo, window3, period3);
            return true;
        }
        pendingMessageInfo2.resolvedPeriodIndex = indexOfPeriod;
        timeline4.getPeriodByUid(pendingMessageInfo2.resolvedPeriodUid, period3);
        if (period3.isPlaceholder && timeline4.getWindow(period3.windowIndex, window3).firstPeriodIndex == timeline4.getIndexOfPeriod(pendingMessageInfo2.resolvedPeriodUid)) {
            long positionInWindowUs = pendingMessageInfo2.resolvedPeriodTimeUs + period2.getPositionInWindowUs();
            Pair<Object, Long> periodPositionUs = timeline.getPeriodPositionUs(window2, period2, timeline3.getPeriodByUid(pendingMessageInfo2.resolvedPeriodUid, period3).windowIndex, positionInWindowUs);
            pendingMessageInfo.setResolvedPosition(timeline3.getIndexOfPeriod(periodPositionUs.first), ((Long) periodPositionUs.second).longValue(), periodPositionUs.first);
        }
        return true;
    }

    private static void resolvePendingMessageEndOfStreamPosition(Timeline timeline, PendingMessageInfo pendingMessageInfo, Timeline.Window window2, Timeline.Period period2) {
        int i = timeline.getWindow(timeline.getPeriodByUid(pendingMessageInfo.resolvedPeriodUid, period2).windowIndex, window2).lastPeriodIndex;
        pendingMessageInfo.setResolvedPosition(i, period2.durationUs != C.TIME_UNSET ? period2.durationUs - 1 : Long.MAX_VALUE, timeline.getPeriod(i, period2, true).uid);
    }

    private static Pair<Object, Long> resolveSeekPositionUs(Timeline timeline, SeekPosition seekPosition, boolean z, int i, boolean z2, Timeline.Window window2, Timeline.Period period2) {
        int resolveSubsequentPeriod;
        Timeline timeline2 = timeline;
        SeekPosition seekPosition2 = seekPosition;
        Timeline.Period period3 = period2;
        Timeline timeline3 = seekPosition2.timeline;
        if (timeline.isEmpty()) {
            return null;
        }
        Timeline timeline4 = timeline3.isEmpty() ? timeline2 : timeline3;
        try {
            Pair<Object, Long> periodPositionUs = timeline4.getPeriodPositionUs(window2, period2, seekPosition2.windowIndex, seekPosition2.windowPositionUs);
            if (timeline.equals(timeline4)) {
                return periodPositionUs;
            }
            if (timeline.getIndexOfPeriod(periodPositionUs.first) == -1) {
                Timeline.Window window3 = window2;
                if (z && (resolveSubsequentPeriod = resolveSubsequentPeriod(window2, period2, i, z2, periodPositionUs.first, timeline4, timeline)) != -1) {
                    return timeline.getPeriodPositionUs(window2, period2, resolveSubsequentPeriod, C.TIME_UNSET);
                }
                return null;
            } else if (!timeline4.getPeriodByUid(periodPositionUs.first, period3).isPlaceholder || timeline4.getWindow(period3.windowIndex, window2).firstPeriodIndex != timeline4.getIndexOfPeriod(periodPositionUs.first)) {
                return periodPositionUs;
            } else {
                return timeline.getPeriodPositionUs(window2, period2, timeline.getPeriodByUid(periodPositionUs.first, period3).windowIndex, seekPosition2.windowPositionUs);
            }
        } catch (IndexOutOfBoundsException unused) {
        }
    }

    static int resolveSubsequentPeriod(Timeline.Window window2, Timeline.Period period2, int i, boolean z, Object obj, Timeline timeline, Timeline timeline2) {
        Object obj2 = timeline.getWindow(timeline.getPeriodByUid(obj, period2).windowIndex, window2).uid;
        for (int i2 = 0; i2 < timeline2.getWindowCount(); i2++) {
            if (timeline2.getWindow(i2, window2).uid.equals(obj2)) {
                return i2;
            }
        }
        int indexOfPeriod = timeline.getIndexOfPeriod(obj);
        int periodCount = timeline.getPeriodCount();
        int i3 = indexOfPeriod;
        int i4 = -1;
        for (int i5 = 0; i5 < periodCount && i4 == -1; i5++) {
            i3 = timeline.getNextPeriodIndex(i3, period2, window2, i, z);
            if (i3 == -1) {
                break;
            }
            i4 = timeline2.getIndexOfPeriod(timeline.getUidOfPeriod(i3));
        }
        if (i4 == -1) {
            return -1;
        }
        return timeline2.getPeriod(i4, period2).windowIndex;
    }

    private static Format[] getFormats(ExoTrackSelection exoTrackSelection) {
        int length = exoTrackSelection != null ? exoTrackSelection.length() : 0;
        Format[] formatArr = new Format[length];
        for (int i = 0; i < length; i++) {
            formatArr[i] = exoTrackSelection.getFormat(i);
        }
        return formatArr;
    }

    private static boolean isRendererEnabled(Renderer renderer) {
        return renderer.getState() != 0;
    }

    private static final class SeekPosition {
        public final Timeline timeline;
        public final int windowIndex;
        public final long windowPositionUs;

        public SeekPosition(Timeline timeline2, int i, long j) {
            this.timeline = timeline2;
            this.windowIndex = i;
            this.windowPositionUs = j;
        }
    }

    private static final class PositionUpdateForPlaylistChange {
        public final boolean endPlayback;
        public final boolean forceBufferingState;
        public final MediaSource.MediaPeriodId periodId;
        public final long periodPositionUs;
        public final long requestedContentPositionUs;
        public final boolean setTargetLiveOffset;

        public PositionUpdateForPlaylistChange(MediaSource.MediaPeriodId mediaPeriodId, long j, long j2, boolean z, boolean z2, boolean z3) {
            this.periodId = mediaPeriodId;
            this.periodPositionUs = j;
            this.requestedContentPositionUs = j2;
            this.forceBufferingState = z;
            this.endPlayback = z2;
            this.setTargetLiveOffset = z3;
        }
    }

    private static final class PendingMessageInfo implements Comparable<PendingMessageInfo> {
        public final PlayerMessage message;
        public int resolvedPeriodIndex;
        public long resolvedPeriodTimeUs;
        public Object resolvedPeriodUid;

        public PendingMessageInfo(PlayerMessage playerMessage) {
            this.message = playerMessage;
        }

        public void setResolvedPosition(int i, long j, Object obj) {
            this.resolvedPeriodIndex = i;
            this.resolvedPeriodTimeUs = j;
            this.resolvedPeriodUid = obj;
        }

        public int compareTo(PendingMessageInfo pendingMessageInfo) {
            Object obj = this.resolvedPeriodUid;
            if ((obj == null) != (pendingMessageInfo.resolvedPeriodUid == null)) {
                return obj != null ? -1 : 1;
            }
            if (obj == null) {
                return 0;
            }
            int i = this.resolvedPeriodIndex - pendingMessageInfo.resolvedPeriodIndex;
            if (i != 0) {
                return i;
            }
            return Util.compareLong(this.resolvedPeriodTimeUs, pendingMessageInfo.resolvedPeriodTimeUs);
        }
    }

    private static final class MediaSourceListUpdateMessage {
        /* access modifiers changed from: private */
        public final List<MediaSourceList.MediaSourceHolder> mediaSourceHolders;
        /* access modifiers changed from: private */
        public final long positionUs;
        /* access modifiers changed from: private */
        public final ShuffleOrder shuffleOrder;
        /* access modifiers changed from: private */
        public final int windowIndex;

        private MediaSourceListUpdateMessage(List<MediaSourceList.MediaSourceHolder> list, ShuffleOrder shuffleOrder2, int i, long j) {
            this.mediaSourceHolders = list;
            this.shuffleOrder = shuffleOrder2;
            this.windowIndex = i;
            this.positionUs = j;
        }
    }

    private static class MoveMediaItemsMessage {
        public final int fromIndex;
        public final int newFromIndex;
        public final ShuffleOrder shuffleOrder;
        public final int toIndex;

        public MoveMediaItemsMessage(int i, int i2, int i3, ShuffleOrder shuffleOrder2) {
            this.fromIndex = i;
            this.toIndex = i2;
            this.newFromIndex = i3;
            this.shuffleOrder = shuffleOrder2;
        }
    }
}
