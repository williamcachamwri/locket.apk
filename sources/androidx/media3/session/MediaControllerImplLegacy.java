package androidx.media3.session;

import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.session.MediaController;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.util.Pair;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.C;
import androidx.media3.common.DeviceInfo;
import androidx.media3.common.FlagSet;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.Player;
import androidx.media3.common.Rating;
import androidx.media3.common.Timeline;
import androidx.media3.common.TrackSelectionParameters;
import androidx.media3.common.Tracks;
import androidx.media3.common.VideoSize;
import androidx.media3.common.text.CueGroup;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.BitmapLoader;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Size;
import androidx.media3.common.util.Util;
import androidx.media3.session.LegacyConversions;
import androidx.media3.session.MediaController;
import androidx.media3.session.legacy.MediaBrowserCompat;
import androidx.media3.session.legacy.MediaControllerCompat;
import androidx.media3.session.legacy.MediaMetadataCompat;
import androidx.media3.session.legacy.MediaSessionCompat;
import androidx.media3.session.legacy.PlaybackStateCompat;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

class MediaControllerImplLegacy implements MediaController.MediaControllerImpl {
    private static final long AGGREGATES_CALLBACKS_WITHIN_TIMEOUT_MS = 500;
    private static final String TAG = "MCImplLegacy";
    private final BitmapLoader bitmapLoader;
    private MediaBrowserCompat browserCompat;
    private final ImmutableList<CommandButton> commandButtonsForMediaItems;
    /* access modifiers changed from: private */
    public boolean connected;
    private final Bundle connectionHints;
    final Context context;
    /* access modifiers changed from: private */
    public MediaControllerCompat controllerCompat;
    private final ControllerCompatCallback controllerCompatCallback;
    /* access modifiers changed from: private */
    public ControllerInfo controllerInfo = new ControllerInfo();
    private long currentPositionMs;
    private final MediaController instance;
    private long lastSetPlayWhenReadyCalledTimeMs;
    private LegacyPlayerInfo legacyPlayerInfo = new LegacyPlayerInfo();
    private final ListenerSet<Player.Listener> listeners;
    /* access modifiers changed from: private */
    public LegacyPlayerInfo pendingLegacyPlayerInfo = new LegacyPlayerInfo();
    private boolean released;
    private final SessionToken token;

    private static int calculateCurrentItemIndexAfterAddItems(int i, int i2, int i3) {
        return i < i2 ? i : i + i3;
    }

    private static int calculateCurrentItemIndexAfterRemoveItems(int i, int i2, int i3) {
        int i4 = i3 - i2;
        if (i < i2) {
            return i;
        }
        if (i < i3) {
            return -1;
        }
        return i - i4;
    }

    /* access modifiers changed from: private */
    public static <T> void ignoreFuture(Future<T> future) {
    }

    public IMediaController getBinder() {
        return null;
    }

    public int getCurrentAdGroupIndex() {
        return -1;
    }

    public int getCurrentAdIndexInAdGroup() {
        return -1;
    }

    public long getCurrentLiveOffset() {
        return C.TIME_UNSET;
    }

    public int getNextMediaItemIndex() {
        return -1;
    }

    public int getPlaybackSuppressionReason() {
        return 0;
    }

    public int getPreviousMediaItemIndex() {
        return -1;
    }

    public float getVolume() {
        return 1.0f;
    }

    public boolean isLoading() {
        return false;
    }

    public void setTrackSelectionParameters(TrackSelectionParameters trackSelectionParameters) {
    }

    public MediaControllerImplLegacy(Context context2, MediaController mediaController, SessionToken sessionToken, Bundle bundle, Looper looper, BitmapLoader bitmapLoader2) {
        this.listeners = new ListenerSet<>(looper, Clock.DEFAULT, new MediaControllerImplLegacy$$ExternalSyntheticLambda17(this));
        this.context = context2;
        this.instance = mediaController;
        this.controllerCompatCallback = new ControllerCompatCallback(looper);
        this.token = sessionToken;
        this.connectionHints = bundle;
        this.bitmapLoader = bitmapLoader2;
        this.currentPositionMs = C.TIME_UNSET;
        this.lastSetPlayWhenReadyCalledTimeMs = C.TIME_UNSET;
        this.commandButtonsForMediaItems = ImmutableList.of();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$new$0$androidx-media3-session-MediaControllerImplLegacy  reason: not valid java name */
    public /* synthetic */ void m1014lambda$new$0$androidxmedia3sessionMediaControllerImplLegacy(Player.Listener listener, FlagSet flagSet) {
        listener.onEvents(getInstance(), new Player.Events(flagSet));
    }

    /* access modifiers changed from: package-private */
    public MediaController getInstance() {
        return this.instance;
    }

    public void connect() {
        if (this.token.getType() == 0) {
            connectToSession((MediaSessionCompat.Token) Assertions.checkStateNotNull(this.token.getBinder()));
        } else {
            connectToService();
        }
    }

    public Bundle getConnectionHints() {
        return this.connectionHints;
    }

    public void addListener(Player.Listener listener) {
        this.listeners.add(listener);
    }

    public void removeListener(Player.Listener listener) {
        this.listeners.remove(listener);
    }

    public void stop() {
        if (this.controllerInfo.playerInfo.playbackState != 1) {
            PlayerInfo copyWithSessionPositionInfo = this.controllerInfo.playerInfo.copyWithSessionPositionInfo(createSessionPositionInfo(this.controllerInfo.playerInfo.sessionPositionInfo.positionInfo, false, this.controllerInfo.playerInfo.sessionPositionInfo.durationMs, this.controllerInfo.playerInfo.sessionPositionInfo.positionInfo.positionMs, MediaUtils.calculateBufferedPercentage(this.controllerInfo.playerInfo.sessionPositionInfo.positionInfo.positionMs, this.controllerInfo.playerInfo.sessionPositionInfo.durationMs), 0));
            if (this.controllerInfo.playerInfo.playbackState != 1) {
                copyWithSessionPositionInfo = copyWithSessionPositionInfo.copyWithPlaybackState(1, this.controllerInfo.playerInfo.playerError);
            }
            updateStateMaskedControllerInfo(new ControllerInfo(copyWithSessionPositionInfo, this.controllerInfo.availableSessionCommands, this.controllerInfo.availablePlayerCommands, this.controllerInfo.mediaButtonPreferences, this.controllerInfo.sessionExtras, (SessionError) null), (Integer) null, (Integer) null);
            this.controllerCompat.getTransportControls().stop();
        }
    }

    public void release() {
        if (!this.released) {
            this.released = true;
            MediaBrowserCompat mediaBrowserCompat = this.browserCompat;
            if (mediaBrowserCompat != null) {
                mediaBrowserCompat.disconnect();
                this.browserCompat = null;
            }
            MediaControllerCompat mediaControllerCompat = this.controllerCompat;
            if (mediaControllerCompat != null) {
                mediaControllerCompat.unregisterCallback(this.controllerCompatCallback);
                this.controllerCompatCallback.release();
                this.controllerCompat = null;
            }
            this.connected = false;
            this.listeners.release();
        }
    }

    public SessionToken getConnectedToken() {
        if (this.connected) {
            return this.token;
        }
        return null;
    }

    public boolean isConnected() {
        return this.connected;
    }

    public void play() {
        setPlayWhenReady(true);
    }

    public void pause() {
        setPlayWhenReady(false);
    }

    public void prepare() {
        if (this.controllerInfo.playerInfo.playbackState == 1) {
            updateStateMaskedControllerInfo(new ControllerInfo(this.controllerInfo.playerInfo.copyWithPlaybackState(this.controllerInfo.playerInfo.timeline.isEmpty() ? 4 : 2, (PlaybackException) null), this.controllerInfo.availableSessionCommands, this.controllerInfo.availablePlayerCommands, this.controllerInfo.mediaButtonPreferences, this.controllerInfo.sessionExtras, (SessionError) null), (Integer) null, (Integer) null);
            if (hasMedia()) {
                initializeLegacyPlaylist();
            }
        }
    }

    public void seekToDefaultPosition() {
        seekToInternal(getCurrentMediaItemIndex(), 0);
    }

    public void seekToDefaultPosition(int i) {
        seekToInternal(i, 0);
    }

    public void seekTo(long j) {
        seekToInternal(getCurrentMediaItemIndex(), j);
    }

    public void seekTo(int i, long j) {
        seekToInternal(i, j);
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0073  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0076  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0085  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00ad  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00bd  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00c9  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00e0  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void seekToInternal(int r25, long r26) {
        /*
            r24 = this;
            r0 = r24
            r1 = r25
            r2 = r26
            r4 = 1
            r5 = 0
            if (r1 < 0) goto L_0x000c
            r6 = r4
            goto L_0x000d
        L_0x000c:
            r6 = r5
        L_0x000d:
            androidx.media3.common.util.Assertions.checkArgument(r6)
            int r6 = r24.getCurrentMediaItemIndex()
            androidx.media3.session.MediaControllerImplLegacy$ControllerInfo r7 = r0.controllerInfo
            androidx.media3.session.PlayerInfo r7 = r7.playerInfo
            androidx.media3.common.Timeline r7 = r7.timeline
            boolean r8 = r7.isEmpty()
            if (r8 != 0) goto L_0x0026
            int r8 = r7.getWindowCount()
            if (r1 >= r8) goto L_0x002c
        L_0x0026:
            boolean r8 = r24.isPlayingAd()
            if (r8 == 0) goto L_0x002d
        L_0x002c:
            return
        L_0x002d:
            r8 = 2
            if (r1 == r6) goto L_0x0064
            androidx.media3.session.MediaControllerImplLegacy$ControllerInfo r10 = r0.controllerInfo
            androidx.media3.session.PlayerInfo r10 = r10.playerInfo
            androidx.media3.common.Timeline r10 = r10.timeline
            androidx.media3.session.QueueTimeline r10 = (androidx.media3.session.QueueTimeline) r10
            long r10 = r10.getQueueId(r1)
            r12 = -1
            int r12 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r12 == 0) goto L_0x0050
            androidx.media3.session.legacy.MediaControllerCompat r6 = r0.controllerCompat
            androidx.media3.session.legacy.MediaControllerCompat$TransportControls r6 = r6.getTransportControls()
            r6.skipToQueueItem(r10)
            java.lang.Integer r6 = java.lang.Integer.valueOf(r8)
            goto L_0x0066
        L_0x0050:
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            java.lang.String r11 = "Cannot seek to new media item due to the missing queue Id at media item, mediaItemIndex="
            r10.<init>(r11)
            java.lang.StringBuilder r1 = r10.append(r1)
            java.lang.String r1 = r1.toString()
            java.lang.String r10 = "MCImplLegacy"
            androidx.media3.common.util.Log.w(r10, r1)
        L_0x0064:
            r1 = r6
            r6 = 0
        L_0x0066:
            long r10 = r24.getCurrentPosition()
            r12 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r14 = (r2 > r12 ? 1 : (r2 == r12 ? 0 : -1))
            if (r14 != 0) goto L_0x0076
            r2 = r10
            r14 = 0
            goto L_0x0083
        L_0x0076:
            androidx.media3.session.legacy.MediaControllerCompat r14 = r0.controllerCompat
            androidx.media3.session.legacy.MediaControllerCompat$TransportControls r14 = r14.getTransportControls()
            r14.seekTo(r2)
            java.lang.Integer r14 = java.lang.Integer.valueOf(r4)
        L_0x0083:
            if (r6 != 0) goto L_0x00ad
            long r8 = r24.getBufferedPosition()
            long r15 = r24.getDuration()
            int r10 = (r2 > r10 ? 1 : (r2 == r10 ? 0 : -1))
            if (r10 >= 0) goto L_0x0093
            r8 = r2
            goto L_0x0097
        L_0x0093:
            long r8 = java.lang.Math.max(r2, r8)
        L_0x0097:
            int r10 = (r15 > r12 ? 1 : (r15 == r12 ? 0 : -1))
            if (r10 != 0) goto L_0x009d
            r10 = r5
            goto L_0x00a2
        L_0x009d:
            r10 = 100
            long r10 = r10 * r8
            long r10 = r10 / r15
            int r10 = (int) r10
        L_0x00a2:
            long r11 = r8 - r2
            r19 = r8
            r21 = r10
            r22 = r11
            r17 = r15
            goto L_0x00b7
        L_0x00ad:
            r8 = 0
            r21 = r5
            r19 = r8
            r22 = r19
            r17 = r12
        L_0x00b7:
            boolean r8 = r7.isEmpty()
            if (r8 != 0) goto L_0x00c9
            androidx.media3.common.Timeline$Window r8 = new androidx.media3.common.Timeline$Window
            r8.<init>()
            androidx.media3.common.Timeline$Window r7 = r7.getWindow(r1, r8)
            androidx.media3.common.MediaItem r7 = r7.mediaItem
            goto L_0x00ca
        L_0x00c9:
            r7 = 0
        L_0x00ca:
            androidx.media3.common.Player$PositionInfo r15 = createPositionInfo(r1, r7, r2, r5)
            androidx.media3.session.MediaControllerImplLegacy$ControllerInfo r1 = r0.controllerInfo
            androidx.media3.session.PlayerInfo r1 = r1.playerInfo
            r16 = 0
            androidx.media3.session.SessionPositionInfo r2 = createSessionPositionInfo(r15, r16, r17, r19, r21, r22)
            androidx.media3.session.PlayerInfo r1 = r1.copyWithSessionPositionInfo(r2)
            int r2 = r1.playbackState
            if (r2 == r4) goto L_0x00e6
            r2 = 2
            r3 = 0
            androidx.media3.session.PlayerInfo r1 = r1.copyWithPlaybackState(r2, r3)
        L_0x00e6:
            r8 = r1
            androidx.media3.session.MediaControllerImplLegacy$ControllerInfo r1 = new androidx.media3.session.MediaControllerImplLegacy$ControllerInfo
            androidx.media3.session.MediaControllerImplLegacy$ControllerInfo r2 = r0.controllerInfo
            androidx.media3.session.SessionCommands r9 = r2.availableSessionCommands
            androidx.media3.session.MediaControllerImplLegacy$ControllerInfo r2 = r0.controllerInfo
            androidx.media3.common.Player$Commands r10 = r2.availablePlayerCommands
            androidx.media3.session.MediaControllerImplLegacy$ControllerInfo r2 = r0.controllerInfo
            com.google.common.collect.ImmutableList<androidx.media3.session.CommandButton> r11 = r2.mediaButtonPreferences
            androidx.media3.session.MediaControllerImplLegacy$ControllerInfo r2 = r0.controllerInfo
            android.os.Bundle r12 = r2.sessionExtras
            r13 = 0
            r7 = r1
            r7.<init>(r8, r9, r10, r11, r12, r13)
            r0.updateStateMaskedControllerInfo(r1, r14, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.session.MediaControllerImplLegacy.seekToInternal(int, long):void");
    }

    public long getSeekBackIncrement() {
        return this.controllerInfo.playerInfo.seekBackIncrementMs;
    }

    public void seekBack() {
        this.controllerCompat.getTransportControls().rewind();
    }

    public long getSeekForwardIncrement() {
        return this.controllerInfo.playerInfo.seekForwardIncrementMs;
    }

    public void seekForward() {
        this.controllerCompat.getTransportControls().fastForward();
    }

    public ImmutableList<CommandButton> getCommandButtonsForMediaItem(MediaItem mediaItem) {
        return this.commandButtonsForMediaItems;
    }

    public PendingIntent getSessionActivity() {
        return this.controllerCompat.getSessionActivity();
    }

    public ImmutableList<CommandButton> getMediaButtonPreferences() {
        return this.controllerInfo.mediaButtonPreferences;
    }

    public Bundle getSessionExtras() {
        return this.controllerInfo.sessionExtras;
    }

    public PlaybackException getPlayerError() {
        return this.controllerInfo.playerInfo.playerError;
    }

    public long getDuration() {
        return this.controllerInfo.playerInfo.sessionPositionInfo.durationMs;
    }

    public long getCurrentPosition() {
        long updatedCurrentPositionMs = MediaUtils.getUpdatedCurrentPositionMs(this.controllerInfo.playerInfo, this.currentPositionMs, this.lastSetPlayWhenReadyCalledTimeMs, getInstance().getTimeDiffMs());
        this.currentPositionMs = updatedCurrentPositionMs;
        return updatedCurrentPositionMs;
    }

    public long getBufferedPosition() {
        return this.controllerInfo.playerInfo.sessionPositionInfo.bufferedPositionMs;
    }

    public int getBufferedPercentage() {
        return this.controllerInfo.playerInfo.sessionPositionInfo.bufferedPercentage;
    }

    public long getTotalBufferedDuration() {
        return this.controllerInfo.playerInfo.sessionPositionInfo.totalBufferedDurationMs;
    }

    public long getContentDuration() {
        return getDuration();
    }

    public long getContentPosition() {
        return getCurrentPosition();
    }

    public long getContentBufferedPosition() {
        return getBufferedPosition();
    }

    public boolean isPlayingAd() {
        return this.controllerInfo.playerInfo.sessionPositionInfo.isPlayingAd;
    }

    public PlaybackParameters getPlaybackParameters() {
        return this.controllerInfo.playerInfo.playbackParameters;
    }

    public AudioAttributes getAudioAttributes() {
        return this.controllerInfo.playerInfo.audioAttributes;
    }

    public ListenableFuture<SessionResult> setRating(String str, Rating rating) {
        if (str.equals(this.legacyPlayerInfo.mediaMetadataCompat.getString("android.media.metadata.MEDIA_ID"))) {
            this.controllerCompat.getTransportControls().setRating(LegacyConversions.convertToRatingCompat(rating));
        }
        return Futures.immediateFuture(new SessionResult(0));
    }

    public ListenableFuture<SessionResult> setRating(Rating rating) {
        this.controllerCompat.getTransportControls().setRating(LegacyConversions.convertToRatingCompat(rating));
        return Futures.immediateFuture(new SessionResult(0));
    }

    public void setPlaybackParameters(PlaybackParameters playbackParameters) {
        if (!playbackParameters.equals(getPlaybackParameters())) {
            updateStateMaskedControllerInfo(new ControllerInfo(this.controllerInfo.playerInfo.copyWithPlaybackParameters(playbackParameters), this.controllerInfo.availableSessionCommands, this.controllerInfo.availablePlayerCommands, this.controllerInfo.mediaButtonPreferences, this.controllerInfo.sessionExtras, (SessionError) null), (Integer) null, (Integer) null);
        }
        this.controllerCompat.getTransportControls().setPlaybackSpeed(playbackParameters.speed);
    }

    public void setPlaybackSpeed(float f) {
        if (f != getPlaybackParameters().speed) {
            updateStateMaskedControllerInfo(new ControllerInfo(this.controllerInfo.playerInfo.copyWithPlaybackParameters(new PlaybackParameters(f)), this.controllerInfo.availableSessionCommands, this.controllerInfo.availablePlayerCommands, this.controllerInfo.mediaButtonPreferences, this.controllerInfo.sessionExtras, (SessionError) null), (Integer) null, (Integer) null);
        }
        this.controllerCompat.getTransportControls().setPlaybackSpeed(f);
    }

    public ListenableFuture<SessionResult> sendCustomCommand(SessionCommand sessionCommand, Bundle bundle) {
        if (this.controllerInfo.availableSessionCommands.contains(sessionCommand)) {
            this.controllerCompat.getTransportControls().sendCustomAction(sessionCommand.customAction, bundle);
            return Futures.immediateFuture(new SessionResult(0));
        }
        final SettableFuture create = SettableFuture.create();
        this.controllerCompat.sendCommand(sessionCommand.customAction, bundle, new ResultReceiver(getInstance().applicationHandler) {
            /* access modifiers changed from: protected */
            public void onReceiveResult(int i, Bundle bundle) {
                SettableFuture settableFuture = create;
                if (bundle == null) {
                    bundle = Bundle.EMPTY;
                }
                settableFuture.set(new SessionResult(i, bundle));
            }
        });
        return create;
    }

    public Timeline getCurrentTimeline() {
        return this.controllerInfo.playerInfo.timeline;
    }

    public void setMediaItem(MediaItem mediaItem) {
        setMediaItem(mediaItem, (long) C.TIME_UNSET);
    }

    public void setMediaItem(MediaItem mediaItem, long j) {
        setMediaItems(ImmutableList.of(mediaItem), 0, j);
    }

    public void setMediaItem(MediaItem mediaItem, boolean z) {
        setMediaItem(mediaItem);
    }

    public void setMediaItems(List<MediaItem> list) {
        setMediaItems(list, 0, C.TIME_UNSET);
    }

    public void setMediaItems(List<MediaItem> list, boolean z) {
        setMediaItems(list);
    }

    public void setMediaItems(List<MediaItem> list, int i, long j) {
        if (list.isEmpty()) {
            clearMediaItems();
            return;
        }
        updateStateMaskedControllerInfo(new ControllerInfo(this.controllerInfo.playerInfo.copyWithTimelineAndSessionPositionInfo(QueueTimeline.DEFAULT.copyWithNewMediaItems(0, list), createSessionPositionInfo(createPositionInfo(i, list.get(i), j == C.TIME_UNSET ? 0 : j, false), false, C.TIME_UNSET, 0, 0, 0), 0), this.controllerInfo.availableSessionCommands, this.controllerInfo.availablePlayerCommands, this.controllerInfo.mediaButtonPreferences, this.controllerInfo.sessionExtras, (SessionError) null), (Integer) null, (Integer) null);
        if (isPrepared()) {
            initializeLegacyPlaylist();
        }
    }

    public void setPlaylistMetadata(MediaMetadata mediaMetadata) {
        Log.w(TAG, "Session doesn't support setting playlist metadata");
    }

    public MediaMetadata getPlaylistMetadata() {
        return this.controllerInfo.playerInfo.playlistMetadata;
    }

    public void addMediaItem(MediaItem mediaItem) {
        addMediaItems(Integer.MAX_VALUE, Collections.singletonList(mediaItem));
    }

    public void addMediaItem(int i, MediaItem mediaItem) {
        addMediaItems(i, Collections.singletonList(mediaItem));
    }

    public void addMediaItems(List<MediaItem> list) {
        addMediaItems(Integer.MAX_VALUE, list);
    }

    public void addMediaItems(int i, List<MediaItem> list) {
        Assertions.checkArgument(i >= 0);
        if (!list.isEmpty()) {
            QueueTimeline queueTimeline = (QueueTimeline) this.controllerInfo.playerInfo.timeline;
            if (queueTimeline.isEmpty()) {
                setMediaItems(list);
                return;
            }
            int min = Math.min(i, getCurrentTimeline().getWindowCount());
            updateStateMaskedControllerInfo(new ControllerInfo(this.controllerInfo.playerInfo.copyWithTimelineAndMediaItemIndex(queueTimeline.copyWithNewMediaItems(min, list), calculateCurrentItemIndexAfterAddItems(getCurrentMediaItemIndex(), min, list.size()), 0), this.controllerInfo.availableSessionCommands, this.controllerInfo.availablePlayerCommands, this.controllerInfo.mediaButtonPreferences, this.controllerInfo.sessionExtras, (SessionError) null), (Integer) null, (Integer) null);
            if (isPrepared()) {
                addQueueItems(list, min);
            }
        }
    }

    public void removeMediaItem(int i) {
        removeMediaItems(i, i + 1);
    }

    public void removeMediaItems(int i, int i2) {
        Assertions.checkArgument(i >= 0 && i2 >= i);
        int windowCount = getCurrentTimeline().getWindowCount();
        int min = Math.min(i2, windowCount);
        if (i < windowCount && i != min) {
            QueueTimeline copyWithRemovedMediaItems = ((QueueTimeline) this.controllerInfo.playerInfo.timeline).copyWithRemovedMediaItems(i, min);
            int calculateCurrentItemIndexAfterRemoveItems = calculateCurrentItemIndexAfterRemoveItems(getCurrentMediaItemIndex(), i, min);
            if (calculateCurrentItemIndexAfterRemoveItems == -1) {
                calculateCurrentItemIndexAfterRemoveItems = Util.constrainValue(i, 0, copyWithRemovedMediaItems.getWindowCount() - 1);
                Log.w(TAG, "Currently playing item is removed. Assumes item at " + calculateCurrentItemIndexAfterRemoveItems + " is the new current item");
            }
            updateStateMaskedControllerInfo(new ControllerInfo(this.controllerInfo.playerInfo.copyWithTimelineAndMediaItemIndex(copyWithRemovedMediaItems, calculateCurrentItemIndexAfterRemoveItems, 0), this.controllerInfo.availableSessionCommands, this.controllerInfo.availablePlayerCommands, this.controllerInfo.mediaButtonPreferences, this.controllerInfo.sessionExtras, (SessionError) null), (Integer) null, (Integer) null);
            if (isPrepared()) {
                while (i < min && i < this.legacyPlayerInfo.queue.size()) {
                    this.controllerCompat.removeQueueItem(this.legacyPlayerInfo.queue.get(i).getDescription());
                    i++;
                }
            }
        }
    }

    public void clearMediaItems() {
        removeMediaItems(0, Integer.MAX_VALUE);
    }

    public void moveMediaItem(int i, int i2) {
        moveMediaItems(i, i + 1, i2);
    }

    public void moveMediaItems(int i, int i2, int i3) {
        Assertions.checkArgument(i >= 0 && i <= i2 && i3 >= 0);
        QueueTimeline queueTimeline = (QueueTimeline) this.controllerInfo.playerInfo.timeline;
        int windowCount = queueTimeline.getWindowCount();
        int min = Math.min(i2, windowCount);
        int i4 = min - i;
        int i5 = (windowCount - i4) - 1;
        int min2 = Math.min(i3, i5 + 1);
        if (i < windowCount && i != min && i != min2) {
            int calculateCurrentItemIndexAfterRemoveItems = calculateCurrentItemIndexAfterRemoveItems(getCurrentMediaItemIndex(), i, min);
            if (calculateCurrentItemIndexAfterRemoveItems == -1) {
                calculateCurrentItemIndexAfterRemoveItems = Util.constrainValue(i, 0, i5);
                Log.w(TAG, "Currently playing item will be removed and added back to mimic move. Assumes item at " + calculateCurrentItemIndexAfterRemoveItems + " would be the new current item");
            }
            updateStateMaskedControllerInfo(new ControllerInfo(this.controllerInfo.playerInfo.copyWithTimelineAndMediaItemIndex(queueTimeline.copyWithMovedMediaItems(i, min, min2), calculateCurrentItemIndexAfterAddItems(calculateCurrentItemIndexAfterRemoveItems, min2, i4), 0), this.controllerInfo.availableSessionCommands, this.controllerInfo.availablePlayerCommands, this.controllerInfo.mediaButtonPreferences, this.controllerInfo.sessionExtras, (SessionError) null), (Integer) null, (Integer) null);
            if (isPrepared()) {
                ArrayList arrayList = new ArrayList();
                for (int i6 = 0; i6 < i4; i6++) {
                    arrayList.add(this.legacyPlayerInfo.queue.get(i));
                    this.controllerCompat.removeQueueItem(this.legacyPlayerInfo.queue.get(i).getDescription());
                }
                for (int i7 = 0; i7 < arrayList.size(); i7++) {
                    this.controllerCompat.addQueueItem(((MediaSessionCompat.QueueItem) arrayList.get(i7)).getDescription(), i7 + min2);
                }
            }
        }
    }

    public void replaceMediaItem(int i, MediaItem mediaItem) {
        replaceMediaItems(i, i + 1, ImmutableList.of(mediaItem));
    }

    public void replaceMediaItems(int i, int i2, List<MediaItem> list) {
        Assertions.checkArgument(i >= 0 && i <= i2);
        int windowCount = ((QueueTimeline) this.controllerInfo.playerInfo.timeline).getWindowCount();
        if (i <= windowCount) {
            int min = Math.min(i2, windowCount);
            addMediaItems(min, list);
            removeMediaItems(i, min);
        }
    }

    public int getCurrentPeriodIndex() {
        return getCurrentMediaItemIndex();
    }

    public int getCurrentMediaItemIndex() {
        return this.controllerInfo.playerInfo.sessionPositionInfo.positionInfo.mediaItemIndex;
    }

    public boolean hasPreviousMediaItem() {
        return this.connected;
    }

    public boolean hasNextMediaItem() {
        return this.connected;
    }

    public void seekToPreviousMediaItem() {
        this.controllerCompat.getTransportControls().skipToPrevious();
    }

    public void seekToNextMediaItem() {
        this.controllerCompat.getTransportControls().skipToNext();
    }

    public void seekToPrevious() {
        this.controllerCompat.getTransportControls().skipToPrevious();
    }

    public void seekToNext() {
        this.controllerCompat.getTransportControls().skipToNext();
    }

    public long getMaxSeekToPreviousPosition() {
        return this.controllerInfo.playerInfo.maxSeekToPreviousPositionMs;
    }

    public int getRepeatMode() {
        return this.controllerInfo.playerInfo.repeatMode;
    }

    public void setRepeatMode(int i) {
        if (i != getRepeatMode()) {
            updateStateMaskedControllerInfo(new ControllerInfo(this.controllerInfo.playerInfo.copyWithRepeatMode(i), this.controllerInfo.availableSessionCommands, this.controllerInfo.availablePlayerCommands, this.controllerInfo.mediaButtonPreferences, this.controllerInfo.sessionExtras, (SessionError) null), (Integer) null, (Integer) null);
        }
        this.controllerCompat.getTransportControls().setRepeatMode(LegacyConversions.convertToPlaybackStateCompatRepeatMode(i));
    }

    public boolean getShuffleModeEnabled() {
        return this.controllerInfo.playerInfo.shuffleModeEnabled;
    }

    public void setShuffleModeEnabled(boolean z) {
        if (z != getShuffleModeEnabled()) {
            updateStateMaskedControllerInfo(new ControllerInfo(this.controllerInfo.playerInfo.copyWithShuffleModeEnabled(z), this.controllerInfo.availableSessionCommands, this.controllerInfo.availablePlayerCommands, this.controllerInfo.mediaButtonPreferences, this.controllerInfo.sessionExtras, (SessionError) null), (Integer) null, (Integer) null);
        }
        this.controllerCompat.getTransportControls().setShuffleMode(LegacyConversions.convertToPlaybackStateCompatShuffleMode(z));
    }

    public VideoSize getVideoSize() {
        Log.w(TAG, "Session doesn't support getting VideoSize");
        return VideoSize.UNKNOWN;
    }

    public Size getSurfaceSize() {
        Log.w(TAG, "Session doesn't support getting VideoSurfaceSize");
        return Size.UNKNOWN;
    }

    public void clearVideoSurface() {
        Log.w(TAG, "Session doesn't support clearing Surface");
    }

    public void clearVideoSurface(Surface surface) {
        Log.w(TAG, "Session doesn't support clearing Surface");
    }

    public void setVideoSurface(Surface surface) {
        Log.w(TAG, "Session doesn't support setting Surface");
    }

    public void setVideoSurfaceHolder(SurfaceHolder surfaceHolder) {
        Log.w(TAG, "Session doesn't support setting SurfaceHolder");
    }

    public void clearVideoSurfaceHolder(SurfaceHolder surfaceHolder) {
        Log.w(TAG, "Session doesn't support clearing SurfaceHolder");
    }

    public void setVideoSurfaceView(SurfaceView surfaceView) {
        Log.w(TAG, "Session doesn't support setting SurfaceView");
    }

    public void clearVideoSurfaceView(SurfaceView surfaceView) {
        Log.w(TAG, "Session doesn't support clearing SurfaceView");
    }

    public void setVideoTextureView(TextureView textureView) {
        Log.w(TAG, "Session doesn't support setting TextureView");
    }

    public void clearVideoTextureView(TextureView textureView) {
        Log.w(TAG, "Session doesn't support clearing TextureView");
    }

    public CueGroup getCurrentCues() {
        Log.w(TAG, "Session doesn't support getting Cue");
        return CueGroup.EMPTY_TIME_ZERO;
    }

    public void setVolume(float f) {
        Log.w(TAG, "Session doesn't support setting player volume");
    }

    public DeviceInfo getDeviceInfo() {
        return this.controllerInfo.playerInfo.deviceInfo;
    }

    public int getDeviceVolume() {
        if (this.controllerInfo.playerInfo.deviceInfo.playbackType == 1) {
            return this.controllerInfo.playerInfo.deviceVolume;
        }
        MediaControllerCompat mediaControllerCompat = this.controllerCompat;
        if (mediaControllerCompat != null) {
            return LegacyConversions.convertToDeviceVolume(mediaControllerCompat.getPlaybackInfo());
        }
        return 0;
    }

    public boolean isDeviceMuted() {
        if (this.controllerInfo.playerInfo.deviceInfo.playbackType == 1) {
            return this.controllerInfo.playerInfo.deviceMuted;
        }
        MediaControllerCompat mediaControllerCompat = this.controllerCompat;
        if (mediaControllerCompat == null || !LegacyConversions.convertToIsDeviceMuted(mediaControllerCompat.getPlaybackInfo())) {
            return false;
        }
        return true;
    }

    @Deprecated
    public void setDeviceVolume(int i) {
        setDeviceVolume(i, 1);
    }

    public void setDeviceVolume(int i, int i2) {
        DeviceInfo deviceInfo = getDeviceInfo();
        int i3 = deviceInfo.minVolume;
        int i4 = deviceInfo.maxVolume;
        if (i3 <= i && (i4 == 0 || i <= i4)) {
            updateStateMaskedControllerInfo(new ControllerInfo(this.controllerInfo.playerInfo.copyWithDeviceVolume(i, isDeviceMuted()), this.controllerInfo.availableSessionCommands, this.controllerInfo.availablePlayerCommands, this.controllerInfo.mediaButtonPreferences, this.controllerInfo.sessionExtras, (SessionError) null), (Integer) null, (Integer) null);
        }
        this.controllerCompat.setVolumeTo(i, i2);
    }

    @Deprecated
    public void increaseDeviceVolume() {
        increaseDeviceVolume(1);
    }

    public void increaseDeviceVolume(int i) {
        int deviceVolume = getDeviceVolume();
        int i2 = getDeviceInfo().maxVolume;
        if (i2 == 0 || deviceVolume + 1 <= i2) {
            updateStateMaskedControllerInfo(new ControllerInfo(this.controllerInfo.playerInfo.copyWithDeviceVolume(deviceVolume + 1, isDeviceMuted()), this.controllerInfo.availableSessionCommands, this.controllerInfo.availablePlayerCommands, this.controllerInfo.mediaButtonPreferences, this.controllerInfo.sessionExtras, (SessionError) null), (Integer) null, (Integer) null);
        }
        this.controllerCompat.adjustVolume(1, i);
    }

    @Deprecated
    public void decreaseDeviceVolume() {
        decreaseDeviceVolume(1);
    }

    public void decreaseDeviceVolume(int i) {
        int deviceVolume = getDeviceVolume() - 1;
        if (deviceVolume >= getDeviceInfo().minVolume) {
            updateStateMaskedControllerInfo(new ControllerInfo(this.controllerInfo.playerInfo.copyWithDeviceVolume(deviceVolume, isDeviceMuted()), this.controllerInfo.availableSessionCommands, this.controllerInfo.availablePlayerCommands, this.controllerInfo.mediaButtonPreferences, this.controllerInfo.sessionExtras, (SessionError) null), (Integer) null, (Integer) null);
        }
        this.controllerCompat.adjustVolume(-1, i);
    }

    @Deprecated
    public void setDeviceMuted(boolean z) {
        setDeviceMuted(z, 1);
    }

    public void setDeviceMuted(boolean z, int i) {
        if (Util.SDK_INT < 23) {
            Log.w(TAG, "Session doesn't support setting mute state at API level less than 23");
            return;
        }
        if (z != isDeviceMuted()) {
            updateStateMaskedControllerInfo(new ControllerInfo(this.controllerInfo.playerInfo.copyWithDeviceVolume(getDeviceVolume(), z), this.controllerInfo.availableSessionCommands, this.controllerInfo.availablePlayerCommands, this.controllerInfo.mediaButtonPreferences, this.controllerInfo.sessionExtras, (SessionError) null), (Integer) null, (Integer) null);
        }
        this.controllerCompat.adjustVolume(z ? -100 : 100, i);
    }

    public void setAudioAttributes(AudioAttributes audioAttributes, boolean z) {
        Log.w(TAG, "Legacy session doesn't support setting audio attributes remotely");
    }

    public void setPlayWhenReady(boolean z) {
        if (this.controllerInfo.playerInfo.playWhenReady != z) {
            this.currentPositionMs = MediaUtils.getUpdatedCurrentPositionMs(this.controllerInfo.playerInfo, this.currentPositionMs, this.lastSetPlayWhenReadyCalledTimeMs, getInstance().getTimeDiffMs());
            this.lastSetPlayWhenReadyCalledTimeMs = SystemClock.elapsedRealtime();
            updateStateMaskedControllerInfo(new ControllerInfo(this.controllerInfo.playerInfo.copyWithPlayWhenReady(z, 1, 0), this.controllerInfo.availableSessionCommands, this.controllerInfo.availablePlayerCommands, this.controllerInfo.mediaButtonPreferences, this.controllerInfo.sessionExtras, (SessionError) null), (Integer) null, (Integer) null);
            if (isPrepared() && hasMedia()) {
                if (z) {
                    this.controllerCompat.getTransportControls().play();
                } else {
                    this.controllerCompat.getTransportControls().pause();
                }
            }
        }
    }

    public boolean getPlayWhenReady() {
        return this.controllerInfo.playerInfo.playWhenReady;
    }

    public int getPlaybackState() {
        return this.controllerInfo.playerInfo.playbackState;
    }

    public boolean isPlaying() {
        return this.controllerInfo.playerInfo.isPlaying;
    }

    public MediaMetadata getMediaMetadata() {
        MediaItem currentMediaItem = this.controllerInfo.playerInfo.getCurrentMediaItem();
        return currentMediaItem == null ? MediaMetadata.EMPTY : currentMediaItem.mediaMetadata;
    }

    public Player.Commands getAvailableCommands() {
        return this.controllerInfo.availablePlayerCommands;
    }

    public Tracks getCurrentTracks() {
        return Tracks.EMPTY;
    }

    public TrackSelectionParameters getTrackSelectionParameters() {
        return TrackSelectionParameters.DEFAULT_WITHOUT_CONTEXT;
    }

    public SessionCommands getAvailableSessionCommands() {
        return this.controllerInfo.availableSessionCommands;
    }

    public Context getContext() {
        return this.context;
    }

    public MediaBrowserCompat getBrowserCompat() {
        return this.browserCompat;
    }

    /* access modifiers changed from: package-private */
    public void onConnected() {
        if (!this.released && !this.connected) {
            this.connected = true;
            handleNewLegacyParameters(true, new LegacyPlayerInfo(this.controllerCompat.getPlaybackInfo(), convertToSafePlaybackStateCompat(this.controllerCompat.getPlaybackState()), this.controllerCompat.getMetadata(), convertToNonNullQueueItemList(this.controllerCompat.getQueue()), this.controllerCompat.getQueueTitle(), this.controllerCompat.getRepeatMode(), this.controllerCompat.getShuffleMode(), this.controllerCompat.getExtras()));
        }
    }

    /* access modifiers changed from: private */
    public void connectToSession(MediaSessionCompat.Token token2) {
        getInstance().runOnApplicationLooper(new MediaControllerImplLegacy$$ExternalSyntheticLambda19(this, token2));
        getInstance().applicationHandler.post(new MediaControllerImplLegacy$$ExternalSyntheticLambda20(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$connectToSession$1$androidx-media3-session-MediaControllerImplLegacy  reason: not valid java name */
    public /* synthetic */ void m1012lambda$connectToSession$1$androidxmedia3sessionMediaControllerImplLegacy(MediaSessionCompat.Token token2) {
        MediaControllerCompat mediaControllerCompat = new MediaControllerCompat(this.context, token2);
        this.controllerCompat = mediaControllerCompat;
        mediaControllerCompat.registerCallback(this.controllerCompatCallback, getInstance().applicationHandler);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$connectToSession$2$androidx-media3-session-MediaControllerImplLegacy  reason: not valid java name */
    public /* synthetic */ void m1013lambda$connectToSession$2$androidxmedia3sessionMediaControllerImplLegacy() {
        if (!this.controllerCompat.isSessionReady()) {
            onConnected();
        }
    }

    private void connectToService() {
        getInstance().runOnApplicationLooper(new MediaControllerImplLegacy$$ExternalSyntheticLambda18(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$connectToService$3$androidx-media3-session-MediaControllerImplLegacy  reason: not valid java name */
    public /* synthetic */ void m1011lambda$connectToService$3$androidxmedia3sessionMediaControllerImplLegacy() {
        MediaBrowserCompat mediaBrowserCompat = new MediaBrowserCompat(this.context, this.token.getComponentName(), new ConnectionCallback(), this.instance.getConnectionHints());
        this.browserCompat = mediaBrowserCompat;
        mediaBrowserCompat.connect();
    }

    private boolean isPrepared() {
        return this.controllerInfo.playerInfo.playbackState != 1;
    }

    private boolean hasMedia() {
        return !this.controllerInfo.playerInfo.timeline.isEmpty();
    }

    private void initializeLegacyPlaylist() {
        Timeline.Window window = new Timeline.Window();
        Assertions.checkState(isPrepared() && hasMedia());
        QueueTimeline queueTimeline = (QueueTimeline) this.controllerInfo.playerInfo.timeline;
        int i = this.controllerInfo.playerInfo.sessionPositionInfo.positionInfo.mediaItemIndex;
        MediaItem mediaItem = queueTimeline.getWindow(i, window).mediaItem;
        if (queueTimeline.getQueueId(i) != -1) {
            if (this.controllerInfo.playerInfo.playWhenReady) {
                this.controllerCompat.getTransportControls().play();
            } else {
                this.controllerCompat.getTransportControls().prepare();
            }
        } else if (mediaItem.requestMetadata.mediaUri != null) {
            if (this.controllerInfo.playerInfo.playWhenReady) {
                this.controllerCompat.getTransportControls().playFromUri(mediaItem.requestMetadata.mediaUri, getOrEmptyBundle(mediaItem.requestMetadata.extras));
            } else {
                this.controllerCompat.getTransportControls().prepareFromUri(mediaItem.requestMetadata.mediaUri, getOrEmptyBundle(mediaItem.requestMetadata.extras));
            }
        } else if (mediaItem.requestMetadata.searchQuery != null) {
            if (this.controllerInfo.playerInfo.playWhenReady) {
                this.controllerCompat.getTransportControls().playFromSearch(mediaItem.requestMetadata.searchQuery, getOrEmptyBundle(mediaItem.requestMetadata.extras));
            } else {
                this.controllerCompat.getTransportControls().prepareFromSearch(mediaItem.requestMetadata.searchQuery, getOrEmptyBundle(mediaItem.requestMetadata.extras));
            }
        } else if (this.controllerInfo.playerInfo.playWhenReady) {
            this.controllerCompat.getTransportControls().playFromMediaId(mediaItem.mediaId, getOrEmptyBundle(mediaItem.requestMetadata.extras));
        } else {
            this.controllerCompat.getTransportControls().prepareFromMediaId(mediaItem.mediaId, getOrEmptyBundle(mediaItem.requestMetadata.extras));
        }
        if (this.controllerInfo.playerInfo.sessionPositionInfo.positionInfo.positionMs != 0) {
            this.controllerCompat.getTransportControls().seekTo(this.controllerInfo.playerInfo.sessionPositionInfo.positionInfo.positionMs);
        }
        if (getAvailableCommands().contains(20)) {
            ArrayList arrayList = new ArrayList();
            for (int i2 = 0; i2 < queueTimeline.getWindowCount(); i2++) {
                if (i2 != i && queueTimeline.getQueueId(i2) == -1) {
                    arrayList.add(queueTimeline.getWindow(i2, window).mediaItem);
                }
            }
            addQueueItems(arrayList, 0);
        }
    }

    private void addQueueItems(List<MediaItem> list, int i) {
        ArrayList arrayList = new ArrayList();
        MediaControllerImplLegacy$$ExternalSyntheticLambda0 mediaControllerImplLegacy$$ExternalSyntheticLambda0 = new MediaControllerImplLegacy$$ExternalSyntheticLambda0(this, new AtomicInteger(0), list, arrayList, i);
        for (int i2 = 0; i2 < list.size(); i2++) {
            MediaMetadata mediaMetadata = list.get(i2).mediaMetadata;
            if (mediaMetadata.artworkData == null) {
                arrayList.add((Object) null);
                mediaControllerImplLegacy$$ExternalSyntheticLambda0.run();
            } else {
                ListenableFuture<Bitmap> decodeBitmap = this.bitmapLoader.decodeBitmap(mediaMetadata.artworkData);
                arrayList.add(decodeBitmap);
                Handler handler = getInstance().applicationHandler;
                Objects.requireNonNull(handler);
                decodeBitmap.addListener(mediaControllerImplLegacy$$ExternalSyntheticLambda0, new MediaControllerImplLegacy$$ExternalSyntheticLambda11(handler));
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$addQueueItems$4$androidx-media3-session-MediaControllerImplLegacy  reason: not valid java name */
    public /* synthetic */ void m1010lambda$addQueueItems$4$androidxmedia3sessionMediaControllerImplLegacy(AtomicInteger atomicInteger, List list, List list2, int i) {
        if (atomicInteger.incrementAndGet() == list.size()) {
            handleBitmapFuturesAllCompletedAndAddQueueItems(list2, list, i);
        }
    }

    private void handleBitmapFuturesAllCompletedAndAddQueueItems(List<ListenableFuture<Bitmap>> list, List<MediaItem> list2, int i) {
        Bitmap bitmap;
        for (int i2 = 0; i2 < list.size(); i2++) {
            ListenableFuture listenableFuture = list.get(i2);
            if (listenableFuture != null) {
                try {
                    bitmap = (Bitmap) Futures.getDone(listenableFuture);
                } catch (CancellationException | ExecutionException e) {
                    Log.d(TAG, "Failed to get bitmap", e);
                }
                this.controllerCompat.addQueueItem(LegacyConversions.convertToMediaDescriptionCompat(list2.get(i2), bitmap), i + i2);
            }
            bitmap = null;
            this.controllerCompat.addQueueItem(LegacyConversions.convertToMediaDescriptionCompat(list2.get(i2), bitmap), i + i2);
        }
    }

    /* access modifiers changed from: private */
    public void handleNewLegacyParameters(boolean z, LegacyPlayerInfo legacyPlayerInfo2) {
        if (!this.released && this.connected) {
            ControllerInfo buildNewControllerInfo = buildNewControllerInfo(z, this.legacyPlayerInfo, this.controllerInfo, legacyPlayerInfo2, this.controllerCompat.getPackageName(), this.controllerCompat.getFlags(), this.controllerCompat.isSessionReady(), this.controllerCompat.getRatingType(), getInstance().getTimeDiffMs(), getRoutingControllerId(this.controllerCompat), this.context);
            LegacyPlayerInfo legacyPlayerInfo3 = this.legacyPlayerInfo;
            Pair<Integer, Integer> calculateDiscontinuityAndTransitionReason = calculateDiscontinuityAndTransitionReason(legacyPlayerInfo3, this.controllerInfo, legacyPlayerInfo2, buildNewControllerInfo, getInstance().getTimeDiffMs());
            updateControllerInfo(z, legacyPlayerInfo2, buildNewControllerInfo, (Integer) calculateDiscontinuityAndTransitionReason.first, (Integer) calculateDiscontinuityAndTransitionReason.second);
        }
    }

    private void updateStateMaskedControllerInfo(ControllerInfo controllerInfo2, Integer num, Integer num2) {
        updateControllerInfo(false, this.legacyPlayerInfo, controllerInfo2, num, num2);
    }

    private void updateControllerInfo(boolean z, LegacyPlayerInfo legacyPlayerInfo2, ControllerInfo controllerInfo2, Integer num, Integer num2) {
        LegacyPlayerInfo legacyPlayerInfo3 = this.legacyPlayerInfo;
        ControllerInfo controllerInfo3 = this.controllerInfo;
        if (legacyPlayerInfo3 != legacyPlayerInfo2) {
            this.legacyPlayerInfo = new LegacyPlayerInfo(legacyPlayerInfo2);
        }
        this.pendingLegacyPlayerInfo = this.legacyPlayerInfo;
        this.controllerInfo = controllerInfo2;
        if (z) {
            getInstance().notifyAccepted();
            if (!controllerInfo3.mediaButtonPreferences.equals(controllerInfo2.mediaButtonPreferences)) {
                getInstance().notifyControllerListener(new MediaControllerImplLegacy$$ExternalSyntheticLambda21(this, controllerInfo2));
                return;
            }
            return;
        }
        if (!controllerInfo3.playerInfo.timeline.equals(controllerInfo2.playerInfo.timeline)) {
            this.listeners.queueEvent(0, new MediaControllerImplLegacy$$ExternalSyntheticLambda6(controllerInfo2));
        }
        if (!Util.areEqual(legacyPlayerInfo3.queueTitle, legacyPlayerInfo2.queueTitle)) {
            this.listeners.queueEvent(15, new MediaControllerImplLegacy$$ExternalSyntheticLambda8(controllerInfo2));
        }
        if (num != null) {
            this.listeners.queueEvent(11, new MediaControllerImplLegacy$$ExternalSyntheticLambda9(controllerInfo3, controllerInfo2, num));
        }
        if (num2 != null) {
            this.listeners.queueEvent(1, new MediaControllerImplLegacy$$ExternalSyntheticLambda10(controllerInfo2, num2));
        }
        if (!MediaUtils.areEqualError(legacyPlayerInfo3.playbackStateCompat, legacyPlayerInfo2.playbackStateCompat)) {
            PlaybackException convertToPlaybackException = LegacyConversions.convertToPlaybackException(legacyPlayerInfo2.playbackStateCompat);
            this.listeners.queueEvent(10, new MediaControllerImplLegacy$$ExternalSyntheticLambda12(convertToPlaybackException));
            if (convertToPlaybackException != null) {
                this.listeners.queueEvent(10, new MediaControllerImplLegacy$$ExternalSyntheticLambda13(convertToPlaybackException));
            }
        }
        if (legacyPlayerInfo3.mediaMetadataCompat != legacyPlayerInfo2.mediaMetadataCompat) {
            this.listeners.queueEvent(14, new MediaControllerImplLegacy$$ExternalSyntheticLambda14(this));
        }
        if (controllerInfo3.playerInfo.playbackState != controllerInfo2.playerInfo.playbackState) {
            this.listeners.queueEvent(4, new MediaControllerImplLegacy$$ExternalSyntheticLambda15(controllerInfo2));
        }
        if (controllerInfo3.playerInfo.playWhenReady != controllerInfo2.playerInfo.playWhenReady) {
            this.listeners.queueEvent(5, new MediaControllerImplLegacy$$ExternalSyntheticLambda16(controllerInfo2));
        }
        if (controllerInfo3.playerInfo.isPlaying != controllerInfo2.playerInfo.isPlaying) {
            this.listeners.queueEvent(7, new MediaControllerImplLegacy$$ExternalSyntheticLambda22(controllerInfo2));
        }
        if (!controllerInfo3.playerInfo.playbackParameters.equals(controllerInfo2.playerInfo.playbackParameters)) {
            this.listeners.queueEvent(12, new MediaControllerImplLegacy$$ExternalSyntheticLambda23(controllerInfo2));
        }
        if (controllerInfo3.playerInfo.repeatMode != controllerInfo2.playerInfo.repeatMode) {
            this.listeners.queueEvent(8, new MediaControllerImplLegacy$$ExternalSyntheticLambda24(controllerInfo2));
        }
        if (controllerInfo3.playerInfo.shuffleModeEnabled != controllerInfo2.playerInfo.shuffleModeEnabled) {
            this.listeners.queueEvent(9, new MediaControllerImplLegacy$$ExternalSyntheticLambda25(controllerInfo2));
        }
        if (!controllerInfo3.playerInfo.audioAttributes.equals(controllerInfo2.playerInfo.audioAttributes)) {
            this.listeners.queueEvent(20, new MediaControllerImplLegacy$$ExternalSyntheticLambda26(controllerInfo2));
        }
        if (!controllerInfo3.playerInfo.deviceInfo.equals(controllerInfo2.playerInfo.deviceInfo)) {
            this.listeners.queueEvent(29, new MediaControllerImplLegacy$$ExternalSyntheticLambda1(controllerInfo2));
        }
        if (!(controllerInfo3.playerInfo.deviceVolume == controllerInfo2.playerInfo.deviceVolume && controllerInfo3.playerInfo.deviceMuted == controllerInfo2.playerInfo.deviceMuted)) {
            this.listeners.queueEvent(30, new MediaControllerImplLegacy$$ExternalSyntheticLambda2(controllerInfo2));
        }
        if (!controllerInfo3.availablePlayerCommands.equals(controllerInfo2.availablePlayerCommands)) {
            this.listeners.queueEvent(13, new MediaControllerImplLegacy$$ExternalSyntheticLambda3(controllerInfo2));
        }
        if (!controllerInfo3.availableSessionCommands.equals(controllerInfo2.availableSessionCommands)) {
            getInstance().notifyControllerListener(new MediaControllerImplLegacy$$ExternalSyntheticLambda4(this, controllerInfo2));
        }
        if (!controllerInfo3.mediaButtonPreferences.equals(controllerInfo2.mediaButtonPreferences)) {
            getInstance().notifyControllerListener(new MediaControllerImplLegacy$$ExternalSyntheticLambda5(this, controllerInfo2));
        }
        if (controllerInfo2.sessionError != null) {
            getInstance().notifyControllerListener(new MediaControllerImplLegacy$$ExternalSyntheticLambda7(this, controllerInfo2));
        }
        this.listeners.flushEvents();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$updateControllerInfo$5$androidx-media3-session-MediaControllerImplLegacy  reason: not valid java name */
    public /* synthetic */ void m1019lambda$updateControllerInfo$5$androidxmedia3sessionMediaControllerImplLegacy(ControllerInfo controllerInfo2, MediaController.Listener listener) {
        ignoreFuture(listener.onSetCustomLayout(getInstance(), controllerInfo2.mediaButtonPreferences));
        listener.onCustomLayoutChanged(getInstance(), controllerInfo2.mediaButtonPreferences);
        listener.onMediaButtonPreferencesChanged(getInstance(), controllerInfo2.mediaButtonPreferences);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$updateControllerInfo$12$androidx-media3-session-MediaControllerImplLegacy  reason: not valid java name */
    public /* synthetic */ void m1015lambda$updateControllerInfo$12$androidxmedia3sessionMediaControllerImplLegacy(Player.Listener listener) {
        listener.onMediaMetadataChanged(this.controllerInfo.playerInfo.mediaMetadata);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$updateControllerInfo$23$androidx-media3-session-MediaControllerImplLegacy  reason: not valid java name */
    public /* synthetic */ void m1016lambda$updateControllerInfo$23$androidxmedia3sessionMediaControllerImplLegacy(ControllerInfo controllerInfo2, MediaController.Listener listener) {
        listener.onAvailableSessionCommandsChanged(getInstance(), controllerInfo2.availableSessionCommands);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$updateControllerInfo$24$androidx-media3-session-MediaControllerImplLegacy  reason: not valid java name */
    public /* synthetic */ void m1017lambda$updateControllerInfo$24$androidxmedia3sessionMediaControllerImplLegacy(ControllerInfo controllerInfo2, MediaController.Listener listener) {
        ignoreFuture(listener.onSetCustomLayout(getInstance(), controllerInfo2.mediaButtonPreferences));
        listener.onCustomLayoutChanged(getInstance(), controllerInfo2.mediaButtonPreferences);
        listener.onMediaButtonPreferencesChanged(getInstance(), controllerInfo2.mediaButtonPreferences);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$updateControllerInfo$25$androidx-media3-session-MediaControllerImplLegacy  reason: not valid java name */
    public /* synthetic */ void m1018lambda$updateControllerInfo$25$androidxmedia3sessionMediaControllerImplLegacy(ControllerInfo controllerInfo2, MediaController.Listener listener) {
        listener.onError(getInstance(), controllerInfo2.sessionError);
    }

    private static String getRoutingControllerId(MediaControllerCompat mediaControllerCompat) {
        MediaController.PlaybackInfo playbackInfo;
        if (Util.SDK_INT >= 30 && (playbackInfo = ((android.media.session.MediaController) mediaControllerCompat.getMediaController()).getPlaybackInfo()) != null) {
            return playbackInfo.getVolumeControlId();
        }
        return null;
    }

    private class ConnectionCallback extends MediaBrowserCompat.ConnectionCallback {
        private ConnectionCallback() {
        }

        public void onConnected() {
            MediaBrowserCompat browserCompat = MediaControllerImplLegacy.this.getBrowserCompat();
            if (browserCompat != null) {
                MediaControllerImplLegacy.this.connectToSession(browserCompat.getSessionToken());
            }
        }

        public void onConnectionSuspended() {
            MediaControllerImplLegacy.this.getInstance().release();
        }

        public void onConnectionFailed() {
            MediaControllerImplLegacy.this.getInstance().release();
        }
    }

    private final class ControllerCompatCallback extends MediaControllerCompat.Callback {
        private static final int MSG_HANDLE_PENDING_UPDATES = 1;
        private final Handler pendingChangesHandler;

        public ControllerCompatCallback(Looper looper) {
            this.pendingChangesHandler = new Handler(looper, new MediaControllerImplLegacy$ControllerCompatCallback$$ExternalSyntheticLambda3(this));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$new$0$androidx-media3-session-MediaControllerImplLegacy$ControllerCompatCallback  reason: not valid java name */
        public /* synthetic */ boolean m1020lambda$new$0$androidxmedia3sessionMediaControllerImplLegacy$ControllerCompatCallback(Message message) {
            if (message.what == 1) {
                MediaControllerImplLegacy mediaControllerImplLegacy = MediaControllerImplLegacy.this;
                mediaControllerImplLegacy.handleNewLegacyParameters(false, mediaControllerImplLegacy.pendingLegacyPlayerInfo);
            }
            return true;
        }

        public void release() {
            this.pendingChangesHandler.removeCallbacksAndMessages((Object) null);
        }

        public void onSessionReady() {
            if (!MediaControllerImplLegacy.this.connected) {
                MediaControllerImplLegacy.this.onConnected();
                return;
            }
            MediaControllerImplLegacy mediaControllerImplLegacy = MediaControllerImplLegacy.this;
            LegacyPlayerInfo unused = mediaControllerImplLegacy.pendingLegacyPlayerInfo = mediaControllerImplLegacy.pendingLegacyPlayerInfo.copyWithExtraBinderGetters(MediaControllerImplLegacy.convertToSafePlaybackStateCompat(MediaControllerImplLegacy.this.controllerCompat.getPlaybackState()), MediaControllerImplLegacy.this.controllerCompat.getRepeatMode(), MediaControllerImplLegacy.this.controllerCompat.getShuffleMode());
            onCaptioningEnabledChanged(MediaControllerImplLegacy.this.controllerCompat.isCaptioningEnabled());
            this.pendingChangesHandler.removeMessages(1);
            MediaControllerImplLegacy mediaControllerImplLegacy2 = MediaControllerImplLegacy.this;
            mediaControllerImplLegacy2.handleNewLegacyParameters(false, mediaControllerImplLegacy2.pendingLegacyPlayerInfo);
        }

        public void onSessionDestroyed() {
            MediaControllerImplLegacy.this.getInstance().release();
        }

        public void onSessionEvent(String str, Bundle bundle) {
            if (str != null) {
                MediaControllerImplLegacy.this.getInstance().notifyControllerListener(new MediaControllerImplLegacy$ControllerCompatCallback$$ExternalSyntheticLambda1(this, str, bundle));
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onSessionEvent$1$androidx-media3-session-MediaControllerImplLegacy$ControllerCompatCallback  reason: not valid java name */
        public /* synthetic */ void m1023lambda$onSessionEvent$1$androidxmedia3sessionMediaControllerImplLegacy$ControllerCompatCallback(String str, Bundle bundle, MediaController.Listener listener) {
            MediaController instance = MediaControllerImplLegacy.this.getInstance();
            SessionCommand sessionCommand = new SessionCommand(str, Bundle.EMPTY);
            if (bundle == null) {
                bundle = Bundle.EMPTY;
            }
            MediaControllerImplLegacy.ignoreFuture(listener.onCustomCommand(instance, sessionCommand, bundle));
        }

        public void onPlaybackStateChanged(PlaybackStateCompat playbackStateCompat) {
            MediaControllerImplLegacy mediaControllerImplLegacy = MediaControllerImplLegacy.this;
            LegacyPlayerInfo unused = mediaControllerImplLegacy.pendingLegacyPlayerInfo = mediaControllerImplLegacy.pendingLegacyPlayerInfo.copyWithPlaybackStateCompat(MediaControllerImplLegacy.convertToSafePlaybackStateCompat(playbackStateCompat));
            startWaitingForPendingChanges();
        }

        public void onMetadataChanged(MediaMetadataCompat mediaMetadataCompat) {
            MediaControllerImplLegacy mediaControllerImplLegacy = MediaControllerImplLegacy.this;
            LegacyPlayerInfo unused = mediaControllerImplLegacy.pendingLegacyPlayerInfo = mediaControllerImplLegacy.pendingLegacyPlayerInfo.copyWithMediaMetadataCompat(mediaMetadataCompat);
            startWaitingForPendingChanges();
        }

        public void onQueueChanged(List<MediaSessionCompat.QueueItem> list) {
            MediaControllerImplLegacy mediaControllerImplLegacy = MediaControllerImplLegacy.this;
            LegacyPlayerInfo unused = mediaControllerImplLegacy.pendingLegacyPlayerInfo = mediaControllerImplLegacy.pendingLegacyPlayerInfo.copyWithQueue(MediaControllerImplLegacy.convertToNonNullQueueItemList(list));
            startWaitingForPendingChanges();
        }

        public void onQueueTitleChanged(CharSequence charSequence) {
            MediaControllerImplLegacy mediaControllerImplLegacy = MediaControllerImplLegacy.this;
            LegacyPlayerInfo unused = mediaControllerImplLegacy.pendingLegacyPlayerInfo = mediaControllerImplLegacy.pendingLegacyPlayerInfo.copyWithQueueTitle(charSequence);
            startWaitingForPendingChanges();
        }

        public void onExtrasChanged(Bundle bundle) {
            ControllerInfo unused = MediaControllerImplLegacy.this.controllerInfo = new ControllerInfo(MediaControllerImplLegacy.this.controllerInfo.playerInfo, MediaControllerImplLegacy.this.controllerInfo.availableSessionCommands, MediaControllerImplLegacy.this.controllerInfo.availablePlayerCommands, MediaControllerImplLegacy.this.controllerInfo.mediaButtonPreferences, bundle, (SessionError) null);
            MediaControllerImplLegacy.this.getInstance().notifyControllerListener(new MediaControllerImplLegacy$ControllerCompatCallback$$ExternalSyntheticLambda2(this, bundle));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onExtrasChanged$2$androidx-media3-session-MediaControllerImplLegacy$ControllerCompatCallback  reason: not valid java name */
        public /* synthetic */ void m1022lambda$onExtrasChanged$2$androidxmedia3sessionMediaControllerImplLegacy$ControllerCompatCallback(Bundle bundle, MediaController.Listener listener) {
            listener.onExtrasChanged(MediaControllerImplLegacy.this.getInstance(), bundle);
        }

        public void onAudioInfoChanged(MediaControllerCompat.PlaybackInfo playbackInfo) {
            MediaControllerImplLegacy mediaControllerImplLegacy = MediaControllerImplLegacy.this;
            LegacyPlayerInfo unused = mediaControllerImplLegacy.pendingLegacyPlayerInfo = mediaControllerImplLegacy.pendingLegacyPlayerInfo.copyWithPlaybackInfoCompat(playbackInfo);
            startWaitingForPendingChanges();
        }

        public void onCaptioningEnabledChanged(boolean z) {
            MediaControllerImplLegacy.this.getInstance().notifyControllerListener(new MediaControllerImplLegacy$ControllerCompatCallback$$ExternalSyntheticLambda0(this, z));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onCaptioningEnabledChanged$3$androidx-media3-session-MediaControllerImplLegacy$ControllerCompatCallback  reason: not valid java name */
        public /* synthetic */ void m1021lambda$onCaptioningEnabledChanged$3$androidxmedia3sessionMediaControllerImplLegacy$ControllerCompatCallback(boolean z, MediaController.Listener listener) {
            Bundle bundle = new Bundle();
            bundle.putBoolean("androidx.media3.session.ARGUMENT_CAPTIONING_ENABLED", z);
            MediaControllerImplLegacy.ignoreFuture(listener.onCustomCommand(MediaControllerImplLegacy.this.getInstance(), new SessionCommand("androidx.media3.session.SESSION_COMMAND_ON_CAPTIONING_ENABLED_CHANGED", Bundle.EMPTY), bundle));
        }

        public void onRepeatModeChanged(int i) {
            MediaControllerImplLegacy mediaControllerImplLegacy = MediaControllerImplLegacy.this;
            LegacyPlayerInfo unused = mediaControllerImplLegacy.pendingLegacyPlayerInfo = mediaControllerImplLegacy.pendingLegacyPlayerInfo.copyWithRepeatMode(i);
            startWaitingForPendingChanges();
        }

        public void onShuffleModeChanged(int i) {
            MediaControllerImplLegacy mediaControllerImplLegacy = MediaControllerImplLegacy.this;
            LegacyPlayerInfo unused = mediaControllerImplLegacy.pendingLegacyPlayerInfo = mediaControllerImplLegacy.pendingLegacyPlayerInfo.copyWithShuffleMode(i);
            startWaitingForPendingChanges();
        }

        private void startWaitingForPendingChanges() {
            if (!this.pendingChangesHandler.hasMessages(1)) {
                this.pendingChangesHandler.sendEmptyMessageDelayed(1, 500);
            }
        }
    }

    private static ControllerInfo buildNewControllerInfo(boolean z, LegacyPlayerInfo legacyPlayerInfo2, ControllerInfo controllerInfo2, LegacyPlayerInfo legacyPlayerInfo3, String str, long j, boolean z2, int i, long j2, String str2, Context context2) {
        QueueTimeline queueTimeline;
        int i2;
        QueueTimeline queueTimeline2;
        MediaMetadata mediaMetadata;
        MediaMetadata mediaMetadata2;
        ImmutableList<CommandButton> immutableList;
        SessionCommands sessionCommands;
        int i3;
        MediaMetadata mediaMetadata3;
        LegacyPlayerInfo legacyPlayerInfo4 = legacyPlayerInfo2;
        ControllerInfo controllerInfo3 = controllerInfo2;
        LegacyPlayerInfo legacyPlayerInfo5 = legacyPlayerInfo3;
        boolean z3 = z2;
        int i4 = i;
        long j3 = j2;
        boolean z4 = legacyPlayerInfo4.queue != legacyPlayerInfo5.queue;
        if (z4) {
            queueTimeline = QueueTimeline.create(legacyPlayerInfo5.queue);
        } else {
            queueTimeline = ((QueueTimeline) controllerInfo3.playerInfo.timeline).copy();
        }
        boolean z5 = legacyPlayerInfo4.mediaMetadataCompat != legacyPlayerInfo5.mediaMetadataCompat || z;
        long activeQueueId = getActiveQueueId(legacyPlayerInfo4.playbackStateCompat);
        long activeQueueId2 = getActiveQueueId(legacyPlayerInfo5.playbackStateCompat);
        boolean z6 = activeQueueId != activeQueueId2 || z;
        long convertToDurationMs = LegacyConversions.convertToDurationMs(legacyPlayerInfo5.mediaMetadataCompat);
        if (z5 || z6 || z4) {
            int findQueueItemIndex = findQueueItemIndex(legacyPlayerInfo5.queue, activeQueueId2);
            boolean z7 = legacyPlayerInfo5.mediaMetadataCompat != null;
            if (z7 && z5) {
                mediaMetadata3 = LegacyConversions.convertToMediaMetadata(legacyPlayerInfo5.mediaMetadataCompat, i4);
            } else if (z7 || !z6) {
                mediaMetadata3 = controllerInfo3.playerInfo.mediaMetadata;
            } else if (findQueueItemIndex == -1) {
                mediaMetadata3 = MediaMetadata.EMPTY;
            } else {
                mediaMetadata3 = LegacyConversions.convertToMediaMetadata(legacyPlayerInfo5.queue.get(findQueueItemIndex).getDescription(), i4);
            }
            if (findQueueItemIndex != -1 || !z5) {
                if (findQueueItemIndex != -1) {
                    queueTimeline = queueTimeline.copyWithClearedFakeMediaItem();
                    if (z7) {
                        queueTimeline = queueTimeline.copyWithNewMediaItem(findQueueItemIndex, LegacyConversions.convertToMediaItem(((MediaItem) Assertions.checkNotNull(queueTimeline.getMediaItemAt(findQueueItemIndex))).mediaId, legacyPlayerInfo5.mediaMetadataCompat, i4), convertToDurationMs);
                    }
                    i2 = findQueueItemIndex;
                    queueTimeline2 = queueTimeline;
                    mediaMetadata = mediaMetadata3;
                }
            } else if (z7) {
                Log.w(TAG, "Adding a fake MediaItem at the end of the list because there's no QueueItem with the active queue id and current Timeline should have currently playing MediaItem.");
                queueTimeline = queueTimeline.copyWithFakeMediaItem(LegacyConversions.convertToMediaItem(legacyPlayerInfo5.mediaMetadataCompat, i4), convertToDurationMs);
                findQueueItemIndex = queueTimeline.getWindowCount() - 1;
                i2 = findQueueItemIndex;
                queueTimeline2 = queueTimeline;
                mediaMetadata = mediaMetadata3;
            } else {
                queueTimeline = queueTimeline.copyWithClearedFakeMediaItem();
            }
            findQueueItemIndex = 0;
            i2 = findQueueItemIndex;
            queueTimeline2 = queueTimeline;
            mediaMetadata = mediaMetadata3;
        } else {
            int i5 = controllerInfo3.playerInfo.sessionPositionInfo.positionInfo.mediaItemIndex;
            mediaMetadata = controllerInfo3.playerInfo.mediaMetadata;
            i2 = i5;
            queueTimeline2 = queueTimeline;
        }
        if (legacyPlayerInfo4.queueTitle == legacyPlayerInfo5.queueTitle) {
            mediaMetadata2 = controllerInfo3.playerInfo.playlistMetadata;
        } else {
            mediaMetadata2 = LegacyConversions.convertToMediaMetadata(legacyPlayerInfo5.queueTitle);
        }
        MediaMetadata mediaMetadata4 = mediaMetadata2;
        int convertToRepeatMode = LegacyConversions.convertToRepeatMode(legacyPlayerInfo5.repeatMode);
        boolean convertToShuffleModeEnabled = LegacyConversions.convertToShuffleModeEnabled(legacyPlayerInfo5.shuffleMode);
        if (legacyPlayerInfo4.playbackStateCompat != legacyPlayerInfo5.playbackStateCompat) {
            sessionCommands = LegacyConversions.convertToSessionCommands(legacyPlayerInfo5.playbackStateCompat, z3);
            immutableList = LegacyConversions.convertToMediaButtonPreferences(legacyPlayerInfo5.playbackStateCompat);
        } else {
            sessionCommands = controllerInfo3.availableSessionCommands;
            immutableList = controllerInfo3.mediaButtonPreferences;
        }
        SessionCommands sessionCommands2 = sessionCommands;
        ImmutableList<CommandButton> immutableList2 = immutableList;
        Player.Commands convertToPlayerCommands = LegacyConversions.convertToPlayerCommands(legacyPlayerInfo5.playbackStateCompat, legacyPlayerInfo5.playbackInfoCompat != null ? legacyPlayerInfo5.playbackInfoCompat.getVolumeControl() : 0, j, z3);
        PlaybackException convertToPlaybackException = LegacyConversions.convertToPlaybackException(legacyPlayerInfo5.playbackStateCompat);
        SessionError convertToSessionError = LegacyConversions.convertToSessionError(legacyPlayerInfo5.playbackStateCompat, context2);
        long convertToCurrentPositionMs = LegacyConversions.convertToCurrentPositionMs(legacyPlayerInfo5.playbackStateCompat, legacyPlayerInfo5.mediaMetadataCompat, j3);
        long convertToBufferedPositionMs = LegacyConversions.convertToBufferedPositionMs(legacyPlayerInfo5.playbackStateCompat, legacyPlayerInfo5.mediaMetadataCompat, j3);
        int convertToBufferedPercentage = LegacyConversions.convertToBufferedPercentage(legacyPlayerInfo5.playbackStateCompat, legacyPlayerInfo5.mediaMetadataCompat, j3);
        long convertToTotalBufferedDurationMs = LegacyConversions.convertToTotalBufferedDurationMs(legacyPlayerInfo5.playbackStateCompat, legacyPlayerInfo5.mediaMetadataCompat, j3);
        boolean convertToIsPlayingAd = LegacyConversions.convertToIsPlayingAd(legacyPlayerInfo5.mediaMetadataCompat);
        PlaybackParameters convertToPlaybackParameters = LegacyConversions.convertToPlaybackParameters(legacyPlayerInfo5.playbackStateCompat);
        AudioAttributes convertToAudioAttributes = LegacyConversions.convertToAudioAttributes(legacyPlayerInfo5.playbackInfoCompat);
        boolean convertToPlayWhenReady = LegacyConversions.convertToPlayWhenReady(legacyPlayerInfo5.playbackStateCompat);
        try {
            i3 = LegacyConversions.convertToPlaybackState(legacyPlayerInfo5.playbackStateCompat, legacyPlayerInfo5.mediaMetadataCompat, j3);
        } catch (LegacyConversions.ConversionException unused) {
            Log.e(TAG, String.format("Received invalid playback state %s from package %s. Keeping the previous state.", new Object[]{Integer.valueOf(legacyPlayerInfo5.playbackStateCompat.getState()), str}));
            i3 = controllerInfo3.playerInfo.playbackState;
        }
        return createControllerInfo(queueTimeline2, mediaMetadata, i2, mediaMetadata4, convertToRepeatMode, convertToShuffleModeEnabled, sessionCommands2, convertToPlayerCommands, immutableList2, legacyPlayerInfo5.sessionExtras, convertToPlaybackException, convertToSessionError, convertToDurationMs, convertToCurrentPositionMs, convertToBufferedPositionMs, convertToBufferedPercentage, convertToTotalBufferedDurationMs, convertToIsPlayingAd, convertToPlaybackParameters, convertToAudioAttributes, convertToPlayWhenReady, i3, LegacyConversions.convertToIsPlaying(legacyPlayerInfo5.playbackStateCompat), LegacyConversions.convertToDeviceInfo(legacyPlayerInfo5.playbackInfoCompat, str2), LegacyConversions.convertToDeviceVolume(legacyPlayerInfo5.playbackInfoCompat), LegacyConversions.convertToIsDeviceMuted(legacyPlayerInfo5.playbackInfoCompat), controllerInfo3.playerInfo.seekBackIncrementMs, controllerInfo3.playerInfo.seekForwardIncrementMs, controllerInfo3.playerInfo.maxSeekToPreviousPositionMs);
    }

    private static Pair<Integer, Integer> calculateDiscontinuityAndTransitionReason(LegacyPlayerInfo legacyPlayerInfo2, ControllerInfo controllerInfo2, LegacyPlayerInfo legacyPlayerInfo3, ControllerInfo controllerInfo3, long j) {
        int i;
        int i2;
        int i3;
        boolean isEmpty = controllerInfo2.playerInfo.timeline.isEmpty();
        boolean isEmpty2 = controllerInfo3.playerInfo.timeline.isEmpty();
        int i4 = null;
        if (isEmpty && isEmpty2) {
            i = null;
        } else if (!isEmpty || isEmpty2) {
            MediaItem mediaItem = (MediaItem) Assertions.checkStateNotNull(controllerInfo2.playerInfo.getCurrentMediaItem());
            if (!((QueueTimeline) controllerInfo3.playerInfo.timeline).contains(mediaItem)) {
                i4 = 4;
                i = 3;
            } else if (mediaItem.equals(controllerInfo3.playerInfo.getCurrentMediaItem())) {
                long convertToCurrentPositionMs = LegacyConversions.convertToCurrentPositionMs(legacyPlayerInfo2.playbackStateCompat, legacyPlayerInfo2.mediaMetadataCompat, j);
                long convertToCurrentPositionMs2 = LegacyConversions.convertToCurrentPositionMs(legacyPlayerInfo3.playbackStateCompat, legacyPlayerInfo3.mediaMetadataCompat, j);
                if (convertToCurrentPositionMs2 == 0 && controllerInfo3.playerInfo.repeatMode == 1) {
                    i3 = 0;
                    i2 = 0;
                } else if (Math.abs(convertToCurrentPositionMs - convertToCurrentPositionMs2) > 100) {
                    i3 = 5;
                    i2 = null;
                } else {
                    i2 = null;
                    i = i2;
                }
                i4 = i3;
                i = i2;
            } else {
                i4 = 0;
                i = 1;
            }
        } else {
            i4 = 0;
            i = 3;
        }
        return Pair.create(i4, i);
    }

    /* access modifiers changed from: private */
    public static List<MediaSessionCompat.QueueItem> convertToNonNullQueueItemList(List<MediaSessionCompat.QueueItem> list) {
        return list == null ? Collections.emptyList() : MediaUtils.removeNullElements(list);
    }

    /* access modifiers changed from: private */
    public static PlaybackStateCompat convertToSafePlaybackStateCompat(PlaybackStateCompat playbackStateCompat) {
        if (playbackStateCompat == null) {
            return null;
        }
        if (playbackStateCompat.getPlaybackSpeed() > 0.0f) {
            return playbackStateCompat;
        }
        Log.w(TAG, "Adjusting playback speed to 1.0f because negative playback speed isn't supported.");
        return new PlaybackStateCompat.Builder(playbackStateCompat).setState(playbackStateCompat.getState(), playbackStateCompat.getPosition(), 1.0f, playbackStateCompat.getLastPositionUpdateTime()).build();
    }

    private static Bundle getOrEmptyBundle(Bundle bundle) {
        return bundle == null ? Bundle.EMPTY : bundle;
    }

    private static long getActiveQueueId(PlaybackStateCompat playbackStateCompat) {
        if (playbackStateCompat == null) {
            return -1;
        }
        return playbackStateCompat.getActiveQueueItemId();
    }

    private static int findQueueItemIndex(List<MediaSessionCompat.QueueItem> list, long j) {
        if (!(list == null || j == -1)) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getQueueId() == j) {
                    return i;
                }
            }
        }
        return -1;
    }

    private static ControllerInfo createControllerInfo(QueueTimeline queueTimeline, MediaMetadata mediaMetadata, int i, MediaMetadata mediaMetadata2, int i2, boolean z, SessionCommands sessionCommands, Player.Commands commands, ImmutableList<CommandButton> immutableList, Bundle bundle, PlaybackException playbackException, SessionError sessionError, long j, long j2, long j3, int i3, long j4, boolean z2, PlaybackParameters playbackParameters, AudioAttributes audioAttributes, boolean z3, int i4, boolean z4, DeviceInfo deviceInfo, int i5, boolean z5, long j5, long j6, long j7) {
        int i6 = i;
        MediaMetadata mediaMetadata3 = mediaMetadata2;
        int i7 = i2;
        boolean z6 = z;
        PlaybackException playbackException2 = playbackException;
        PlaybackParameters playbackParameters2 = playbackParameters;
        AudioAttributes audioAttributes2 = audioAttributes;
        boolean z7 = z3;
        int i8 = i4;
        boolean z8 = z4;
        DeviceInfo deviceInfo2 = deviceInfo;
        int i9 = i5;
        boolean z9 = z5;
        boolean z10 = z2;
        Player.PositionInfo createPositionInfo = createPositionInfo(i6, queueTimeline.getMediaItemAt(i6), j2, z2);
        SessionPositionInfo sessionPositionInfo = r34;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        SessionPositionInfo sessionPositionInfo2 = new SessionPositionInfo(createPositionInfo, z10, elapsedRealtime, j, j3, i3, j4, C.TIME_UNSET, j, j3);
        return new ControllerInfo(new PlayerInfo(playbackException2, 0, sessionPositionInfo, SessionPositionInfo.DEFAULT_POSITION_INFO, SessionPositionInfo.DEFAULT_POSITION_INFO, 0, playbackParameters2, i7, z6, VideoSize.UNKNOWN, queueTimeline, 0, mediaMetadata3, 1.0f, audioAttributes2, CueGroup.EMPTY_TIME_ZERO, deviceInfo2, i9, z9, z7, 1, 0, i8, z8, false, mediaMetadata, j5, j6, j7, Tracks.EMPTY, TrackSelectionParameters.DEFAULT_WITHOUT_CONTEXT), sessionCommands, commands, immutableList, bundle, sessionError);
    }

    private static Player.PositionInfo createPositionInfo(int i, MediaItem mediaItem, long j, boolean z) {
        return new Player.PositionInfo((Object) null, i, mediaItem, (Object) null, i, j, j, z ? 0 : -1, z ? 0 : -1);
    }

    private static SessionPositionInfo createSessionPositionInfo(Player.PositionInfo positionInfo, boolean z, long j, long j2, int i, long j3) {
        long j4 = j2;
        int i2 = i;
        long j5 = j3;
        return new SessionPositionInfo(positionInfo, z, SystemClock.elapsedRealtime(), j, j4, i2, j5, C.TIME_UNSET, j, j2);
    }

    private static final class LegacyPlayerInfo {
        public final MediaMetadataCompat mediaMetadataCompat;
        public final MediaControllerCompat.PlaybackInfo playbackInfoCompat;
        public final PlaybackStateCompat playbackStateCompat;
        public final List<MediaSessionCompat.QueueItem> queue;
        public final CharSequence queueTitle;
        public final int repeatMode;
        public final Bundle sessionExtras;
        public final int shuffleMode;

        public LegacyPlayerInfo() {
            this.playbackInfoCompat = null;
            this.playbackStateCompat = null;
            this.mediaMetadataCompat = null;
            this.queue = Collections.emptyList();
            this.queueTitle = null;
            this.repeatMode = 0;
            this.shuffleMode = 0;
            this.sessionExtras = Bundle.EMPTY;
        }

        public LegacyPlayerInfo(MediaControllerCompat.PlaybackInfo playbackInfo, PlaybackStateCompat playbackStateCompat2, MediaMetadataCompat mediaMetadataCompat2, List<MediaSessionCompat.QueueItem> list, CharSequence charSequence, int i, int i2, Bundle bundle) {
            this.playbackInfoCompat = playbackInfo;
            this.playbackStateCompat = playbackStateCompat2;
            this.mediaMetadataCompat = mediaMetadataCompat2;
            this.queue = (List) Assertions.checkNotNull(list);
            this.queueTitle = charSequence;
            this.repeatMode = i;
            this.shuffleMode = i2;
            this.sessionExtras = bundle == null ? Bundle.EMPTY : bundle;
        }

        public LegacyPlayerInfo(LegacyPlayerInfo legacyPlayerInfo) {
            this.playbackInfoCompat = legacyPlayerInfo.playbackInfoCompat;
            this.playbackStateCompat = legacyPlayerInfo.playbackStateCompat;
            this.mediaMetadataCompat = legacyPlayerInfo.mediaMetadataCompat;
            this.queue = legacyPlayerInfo.queue;
            this.queueTitle = legacyPlayerInfo.queueTitle;
            this.repeatMode = legacyPlayerInfo.repeatMode;
            this.shuffleMode = legacyPlayerInfo.shuffleMode;
            this.sessionExtras = legacyPlayerInfo.sessionExtras;
        }

        public LegacyPlayerInfo copyWithExtraBinderGetters(PlaybackStateCompat playbackStateCompat2, int i, int i2) {
            return new LegacyPlayerInfo(this.playbackInfoCompat, playbackStateCompat2, this.mediaMetadataCompat, this.queue, this.queueTitle, i, i2, this.sessionExtras);
        }

        public LegacyPlayerInfo copyWithPlaybackStateCompat(PlaybackStateCompat playbackStateCompat2) {
            return new LegacyPlayerInfo(this.playbackInfoCompat, playbackStateCompat2, this.mediaMetadataCompat, this.queue, this.queueTitle, this.repeatMode, this.shuffleMode, this.sessionExtras);
        }

        public LegacyPlayerInfo copyWithMediaMetadataCompat(MediaMetadataCompat mediaMetadataCompat2) {
            return new LegacyPlayerInfo(this.playbackInfoCompat, this.playbackStateCompat, mediaMetadataCompat2, this.queue, this.queueTitle, this.repeatMode, this.shuffleMode, this.sessionExtras);
        }

        public LegacyPlayerInfo copyWithQueue(List<MediaSessionCompat.QueueItem> list) {
            return new LegacyPlayerInfo(this.playbackInfoCompat, this.playbackStateCompat, this.mediaMetadataCompat, list, this.queueTitle, this.repeatMode, this.shuffleMode, this.sessionExtras);
        }

        public LegacyPlayerInfo copyWithQueueTitle(CharSequence charSequence) {
            return new LegacyPlayerInfo(this.playbackInfoCompat, this.playbackStateCompat, this.mediaMetadataCompat, this.queue, charSequence, this.repeatMode, this.shuffleMode, this.sessionExtras);
        }

        public LegacyPlayerInfo copyWithPlaybackInfoCompat(MediaControllerCompat.PlaybackInfo playbackInfo) {
            return new LegacyPlayerInfo(playbackInfo, this.playbackStateCompat, this.mediaMetadataCompat, this.queue, this.queueTitle, this.repeatMode, this.shuffleMode, this.sessionExtras);
        }

        public LegacyPlayerInfo copyWithRepeatMode(int i) {
            return new LegacyPlayerInfo(this.playbackInfoCompat, this.playbackStateCompat, this.mediaMetadataCompat, this.queue, this.queueTitle, i, this.shuffleMode, this.sessionExtras);
        }

        public LegacyPlayerInfo copyWithShuffleMode(int i) {
            return new LegacyPlayerInfo(this.playbackInfoCompat, this.playbackStateCompat, this.mediaMetadataCompat, this.queue, this.queueTitle, this.repeatMode, i, this.sessionExtras);
        }
    }

    private static class ControllerInfo {
        public final Player.Commands availablePlayerCommands;
        public final SessionCommands availableSessionCommands;
        public final ImmutableList<CommandButton> mediaButtonPreferences;
        public final PlayerInfo playerInfo;
        public final SessionError sessionError;
        public final Bundle sessionExtras;

        public ControllerInfo() {
            this.playerInfo = PlayerInfo.DEFAULT.copyWithTimeline(QueueTimeline.DEFAULT);
            this.availableSessionCommands = SessionCommands.EMPTY;
            this.availablePlayerCommands = Player.Commands.EMPTY;
            this.mediaButtonPreferences = ImmutableList.of();
            this.sessionExtras = Bundle.EMPTY;
            this.sessionError = null;
        }

        public ControllerInfo(PlayerInfo playerInfo2, SessionCommands sessionCommands, Player.Commands commands, ImmutableList<CommandButton> immutableList, Bundle bundle, SessionError sessionError2) {
            this.playerInfo = playerInfo2;
            this.availableSessionCommands = sessionCommands;
            this.availablePlayerCommands = commands;
            this.mediaButtonPreferences = immutableList;
            this.sessionExtras = bundle == null ? Bundle.EMPTY : bundle;
            this.sessionError = sessionError2;
        }
    }
}
