package androidx.media3.session;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.media.session.MediaSession;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Pair;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;
import androidx.collection.ArraySet;
import androidx.fragment.app.FragmentTransaction;
import androidx.media3.common.AdPlaybackState;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.BundleListRetriever;
import androidx.media3.common.C;
import androidx.media3.common.DeviceInfo;
import androidx.media3.common.FlagSet;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.Player;
import androidx.media3.common.Rating;
import androidx.media3.common.SimpleBasePlayer$$ExternalSyntheticLambda23;
import androidx.media3.common.Timeline;
import androidx.media3.common.TrackSelectionParameters;
import androidx.media3.common.Tracks;
import androidx.media3.common.VideoSize;
import androidx.media3.common.text.CueGroup;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.BundleCollectionUtil;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Size;
import androidx.media3.common.util.Util;
import androidx.media3.session.IMediaSession;
import androidx.media3.session.MediaController;
import androidx.media3.session.PlayerInfo;
import androidx.media3.session.SequencedFutureManager;
import androidx.media3.session.legacy.MediaBrowserCompat;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

class MediaControllerImplBase implements MediaController.MediaControllerImpl {
    private static final long RELEASE_TIMEOUT_MS = 30000;
    public static final String TAG = "MCImplBase";
    private ImmutableMap<String, CommandButton> commandButtonsForMediaItemsMap = ImmutableMap.of();
    private SessionToken connectedToken;
    private final Bundle connectionHints;
    private final Context context;
    protected final MediaControllerStub controllerStub;
    private long currentPositionMs;
    private ImmutableList<CommandButton> customLayoutOriginal = ImmutableList.of();
    private final IBinder.DeathRecipient deathRecipient;
    private final FlushCommandQueueHandler flushCommandQueueHandler;
    /* access modifiers changed from: private */
    public IMediaSession iSession;
    /* access modifiers changed from: private */
    public final MediaController instance;
    private Player.Commands intersectedPlayerCommands;
    private long lastSetPlayWhenReadyCalledTimeMs;
    private final ListenerSet<Player.Listener> listeners;
    private ImmutableList<CommandButton> mediaButtonPreferencesOriginal = ImmutableList.of();
    private PlayerInfo.BundlingExclusions pendingBundlingExclusions;
    private final ArraySet<Integer> pendingMaskingSequencedFutureNumbers;
    private PlayerInfo pendingPlayerInfo;
    private android.media.session.MediaController platformController;
    private Player.Commands playerCommandsFromPlayer;
    private Player.Commands playerCommandsFromSession = Player.Commands.EMPTY;
    private PlayerInfo playerInfo = PlayerInfo.DEFAULT;
    private boolean released;
    private ImmutableList<CommandButton> resolvedMediaButtonPreferences = ImmutableList.of();
    protected final SequencedFutureManager sequencedFutureManager;
    private SessionServiceConnection serviceConnection;
    private PendingIntent sessionActivity;
    private SessionCommands sessionCommands = SessionCommands.EMPTY;
    private Bundle sessionExtras;
    private final SurfaceCallback surfaceCallback;
    private Size surfaceSize = Size.UNKNOWN;
    /* access modifiers changed from: private */
    public final SessionToken token;
    /* access modifiers changed from: private */
    public Surface videoSurface;
    /* access modifiers changed from: private */
    public SurfaceHolder videoSurfaceHolder;
    /* access modifiers changed from: private */
    public TextureView videoTextureView;

    private interface RemoteSessionTask {
        void run(IMediaSession iMediaSession, int i) throws RemoteException;
    }

    private static int convertRepeatModeForNavigation(int i) {
        if (i == 1) {
            return 0;
        }
        return i;
    }

    public MediaBrowserCompat getBrowserCompat() {
        return null;
    }

    public MediaControllerImplBase(Context context2, MediaController mediaController, SessionToken sessionToken, Bundle bundle, Looper looper) {
        Player.Commands commands = Player.Commands.EMPTY;
        this.playerCommandsFromPlayer = commands;
        this.intersectedPlayerCommands = createIntersectedCommandsEnsuringCommandReleaseAvailable(this.playerCommandsFromSession, commands);
        this.listeners = new ListenerSet<>(looper, Clock.DEFAULT, new MediaControllerImplBase$$ExternalSyntheticLambda93(this));
        this.instance = mediaController;
        Assertions.checkNotNull(context2, "context must not be null");
        Assertions.checkNotNull(sessionToken, "token must not be null");
        this.context = context2;
        this.sequencedFutureManager = new SequencedFutureManager();
        this.controllerStub = new MediaControllerStub(this);
        this.pendingMaskingSequencedFutureNumbers = new ArraySet<>();
        this.token = sessionToken;
        this.connectionHints = bundle;
        this.deathRecipient = new MediaControllerImplBase$$ExternalSyntheticLambda94(this);
        SessionServiceConnection sessionServiceConnection = null;
        this.surfaceCallback = new SurfaceCallback();
        this.sessionExtras = Bundle.EMPTY;
        this.serviceConnection = sessionToken.getType() != 0 ? new SessionServiceConnection(bundle) : sessionServiceConnection;
        this.flushCommandQueueHandler = new FlushCommandQueueHandler(looper);
        this.currentPositionMs = C.TIME_UNSET;
        this.lastSetPlayWhenReadyCalledTimeMs = C.TIME_UNSET;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$new$0$androidx-media3-session-MediaControllerImplBase  reason: not valid java name */
    public /* synthetic */ void m941lambda$new$0$androidxmedia3sessionMediaControllerImplBase(Player.Listener listener, FlagSet flagSet) {
        listener.onEvents(getInstance(), new Player.Events(flagSet));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$new$1$androidx-media3-session-MediaControllerImplBase  reason: not valid java name */
    public /* synthetic */ void m942lambda$new$1$androidxmedia3sessionMediaControllerImplBase() {
        MediaController instance2 = getInstance();
        MediaController instance3 = getInstance();
        Objects.requireNonNull(instance3);
        instance2.runOnApplicationLooper(new MediaControllerImplBase$$ExternalSyntheticLambda46(instance3));
    }

    /* access modifiers changed from: package-private */
    public MediaController getInstance() {
        return this.instance;
    }

    public void connect() {
        boolean z;
        if (this.token.getType() == 0) {
            this.serviceConnection = null;
            z = requestConnectToSession(this.connectionHints);
        } else {
            this.serviceConnection = new SessionServiceConnection(this.connectionHints);
            z = requestConnectToService();
        }
        if (!z) {
            MediaController instance2 = getInstance();
            MediaController instance3 = getInstance();
            Objects.requireNonNull(instance3);
            instance2.runOnApplicationLooper(new MediaControllerImplBase$$ExternalSyntheticLambda46(instance3));
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
        if (isPlayerCommandAvailable(3)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new MediaControllerImplBase$$ExternalSyntheticLambda47(this));
            PlayerInfo copyWithSessionPositionInfo = this.playerInfo.copyWithSessionPositionInfo(new SessionPositionInfo(this.playerInfo.sessionPositionInfo.positionInfo, this.playerInfo.sessionPositionInfo.isPlayingAd, SystemClock.elapsedRealtime(), this.playerInfo.sessionPositionInfo.durationMs, this.playerInfo.sessionPositionInfo.positionInfo.positionMs, MediaUtils.calculateBufferedPercentage(this.playerInfo.sessionPositionInfo.positionInfo.positionMs, this.playerInfo.sessionPositionInfo.durationMs), 0, this.playerInfo.sessionPositionInfo.currentLiveOffsetMs, this.playerInfo.sessionPositionInfo.contentDurationMs, this.playerInfo.sessionPositionInfo.positionInfo.positionMs));
            this.playerInfo = copyWithSessionPositionInfo;
            if (copyWithSessionPositionInfo.playbackState != 1) {
                PlayerInfo playerInfo2 = this.playerInfo;
                this.playerInfo = playerInfo2.copyWithPlaybackState(1, playerInfo2.playerError);
                this.listeners.queueEvent(4, new MediaControllerImplBase$$ExternalSyntheticLambda48());
                this.listeners.flushEvents();
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$stop$2$androidx-media3-session-MediaControllerImplBase  reason: not valid java name */
    public /* synthetic */ void m1005lambda$stop$2$androidxmedia3sessionMediaControllerImplBase(IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.stop(this.controllerStub, i);
    }

    public void release() {
        IMediaSession iMediaSession = this.iSession;
        if (!this.released) {
            this.released = true;
            this.connectedToken = null;
            this.flushCommandQueueHandler.release();
            this.iSession = null;
            if (iMediaSession != null) {
                int obtainNextSequenceNumber = this.sequencedFutureManager.obtainNextSequenceNumber();
                try {
                    iMediaSession.asBinder().unlinkToDeath(this.deathRecipient, 0);
                    iMediaSession.release(this.controllerStub, obtainNextSequenceNumber);
                } catch (RemoteException unused) {
                }
            }
            this.listeners.release();
            this.sequencedFutureManager.lazyRelease(30000, new MediaControllerImplBase$$ExternalSyntheticLambda1(this));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$release$4$androidx-media3-session-MediaControllerImplBase  reason: not valid java name */
    public /* synthetic */ void m957lambda$release$4$androidxmedia3sessionMediaControllerImplBase() {
        SessionServiceConnection sessionServiceConnection = this.serviceConnection;
        if (sessionServiceConnection != null) {
            this.context.unbindService(sessionServiceConnection);
            this.serviceConnection = null;
        }
        this.controllerStub.destroy();
    }

    public SessionToken getConnectedToken() {
        return this.connectedToken;
    }

    public boolean isConnected() {
        return this.iSession != null;
    }

    /* access modifiers changed from: package-private */
    public boolean isReleased() {
        return this.released;
    }

    private void dispatchRemoteSessionTaskWithPlayerCommand(RemoteSessionTask remoteSessionTask) {
        this.flushCommandQueueHandler.sendFlushCommandQueueMessage();
        dispatchRemoteSessionTask(this.iSession, remoteSessionTask, true);
    }

    /* access modifiers changed from: private */
    public void dispatchRemoteSessionTaskWithPlayerCommandAndWaitForFuture(RemoteSessionTask remoteSessionTask) {
        this.flushCommandQueueHandler.sendFlushCommandQueueMessage();
        ListenableFuture<SessionResult> dispatchRemoteSessionTask = dispatchRemoteSessionTask(this.iSession, remoteSessionTask, true);
        try {
            LegacyConversions.getFutureResult(dispatchRemoteSessionTask, C.DEFAULT_MAX_SEEK_TO_PREVIOUS_POSITION_MS);
        } catch (ExecutionException e) {
            throw new IllegalStateException(e);
        } catch (TimeoutException e2) {
            if (dispatchRemoteSessionTask instanceof SequencedFutureManager.SequencedFuture) {
                int sequenceNumber = ((SequencedFutureManager.SequencedFuture) dispatchRemoteSessionTask).getSequenceNumber();
                this.pendingMaskingSequencedFutureNumbers.remove(Integer.valueOf(sequenceNumber));
                this.sequencedFutureManager.setFutureResult(sequenceNumber, new SessionResult(-1));
            }
            Log.w(TAG, "Synchronous command takes too long on the session side.", e2);
        }
    }

    private ListenableFuture<SessionResult> dispatchRemoteSessionTaskWithSessionCommand(int i, RemoteSessionTask remoteSessionTask) {
        return dispatchRemoteSessionTaskWithSessionCommandInternal(i, (SessionCommand) null, remoteSessionTask);
    }

    private ListenableFuture<SessionResult> dispatchRemoteSessionTaskWithSessionCommand(SessionCommand sessionCommand, RemoteSessionTask remoteSessionTask) {
        return dispatchRemoteSessionTaskWithSessionCommandInternal(0, sessionCommand, remoteSessionTask);
    }

    private ListenableFuture<SessionResult> dispatchRemoteSessionTaskWithSessionCommandInternal(int i, SessionCommand sessionCommand, RemoteSessionTask remoteSessionTask) {
        IMediaSession iMediaSession;
        if (sessionCommand != null) {
            iMediaSession = getSessionInterfaceWithSessionCommandIfAble(sessionCommand);
        } else {
            iMediaSession = getSessionInterfaceWithSessionCommandIfAble(i);
        }
        return dispatchRemoteSessionTask(iMediaSession, remoteSessionTask, false);
    }

    private ListenableFuture<SessionResult> dispatchRemoteSessionTask(IMediaSession iMediaSession, RemoteSessionTask remoteSessionTask, boolean z) {
        if (iMediaSession == null) {
            return Futures.immediateFuture(new SessionResult(-4));
        }
        SequencedFutureManager.SequencedFuture createSequencedFuture = this.sequencedFutureManager.createSequencedFuture(new SessionResult(1));
        int sequenceNumber = createSequencedFuture.getSequenceNumber();
        if (z) {
            this.pendingMaskingSequencedFutureNumbers.add(Integer.valueOf(sequenceNumber));
        }
        try {
            remoteSessionTask.run(iMediaSession, sequenceNumber);
        } catch (RemoteException e) {
            Log.w(TAG, "Cannot connect to the service or the session is gone", e);
            this.pendingMaskingSequencedFutureNumbers.remove(Integer.valueOf(sequenceNumber));
            this.sequencedFutureManager.setFutureResult(sequenceNumber, new SessionResult(-100));
        }
        return createSequencedFuture;
    }

    public void play() {
        android.media.session.MediaController mediaController;
        if (!isPlayerCommandAvailable(1)) {
            Log.w(TAG, "Calling play() omitted due to COMMAND_PLAY_PAUSE not being available. If this play command has started the service for instance for playback resumption, this may prevent the service from being started into the foreground.");
            return;
        }
        if (Util.SDK_INT >= 31 && (mediaController = this.platformController) != null) {
            mediaController.getTransportControls().sendCustomAction("androidx.media3.session.SESSION_COMMAND_MEDIA3_PLAY_REQUEST", (Bundle) null);
        }
        dispatchRemoteSessionTaskWithPlayerCommand(new MediaControllerImplBase$$ExternalSyntheticLambda111(this));
        setPlayWhenReady(true, 1);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$play$5$androidx-media3-session-MediaControllerImplBase  reason: not valid java name */
    public /* synthetic */ void m955lambda$play$5$androidxmedia3sessionMediaControllerImplBase(IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.play(this.controllerStub, i);
    }

    public void pause() {
        if (isPlayerCommandAvailable(1)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new MediaControllerImplBase$$ExternalSyntheticLambda53(this));
            setPlayWhenReady(false, 1);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$pause$6$androidx-media3-session-MediaControllerImplBase  reason: not valid java name */
    public /* synthetic */ void m954lambda$pause$6$androidxmedia3sessionMediaControllerImplBase(IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.pause(this.controllerStub, i);
    }

    public void prepare() {
        int i = 2;
        if (isPlayerCommandAvailable(2)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new MediaControllerImplBase$$ExternalSyntheticLambda54(this));
            if (this.playerInfo.playbackState == 1) {
                PlayerInfo playerInfo2 = this.playerInfo;
                if (playerInfo2.timeline.isEmpty()) {
                    i = 4;
                }
                updatePlayerInfo(playerInfo2.copyWithPlaybackState(i, (PlaybackException) null), (Integer) null, (Integer) null, (Integer) null, (Integer) null);
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$prepare$7$androidx-media3-session-MediaControllerImplBase  reason: not valid java name */
    public /* synthetic */ void m956lambda$prepare$7$androidxmedia3sessionMediaControllerImplBase(IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.prepare(this.controllerStub, i);
    }

    public void seekToDefaultPosition() {
        if (isPlayerCommandAvailable(4)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new MediaControllerImplBase$$ExternalSyntheticLambda55(this));
            seekToInternal(getCurrentMediaItemIndex(), C.TIME_UNSET);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$seekToDefaultPosition$8$androidx-media3-session-MediaControllerImplBase  reason: not valid java name */
    public /* synthetic */ void m966lambda$seekToDefaultPosition$8$androidxmedia3sessionMediaControllerImplBase(IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.seekToDefaultPosition(this.controllerStub, i);
    }

    public void seekToDefaultPosition(int i) {
        if (isPlayerCommandAvailable(10)) {
            Assertions.checkArgument(i >= 0);
            dispatchRemoteSessionTaskWithPlayerCommand(new MediaControllerImplBase$$ExternalSyntheticLambda99(this, i));
            seekToInternal(i, C.TIME_UNSET);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$seekToDefaultPosition$9$androidx-media3-session-MediaControllerImplBase  reason: not valid java name */
    public /* synthetic */ void m967lambda$seekToDefaultPosition$9$androidxmedia3sessionMediaControllerImplBase(int i, IMediaSession iMediaSession, int i2) throws RemoteException {
        iMediaSession.seekToDefaultPositionWithMediaItemIndex(this.controllerStub, i2, i);
    }

    public void seekTo(long j) {
        if (isPlayerCommandAvailable(5)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new MediaControllerImplBase$$ExternalSyntheticLambda57(this, j));
            seekToInternal(getCurrentMediaItemIndex(), j);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$seekTo$10$androidx-media3-session-MediaControllerImplBase  reason: not valid java name */
    public /* synthetic */ void m964lambda$seekTo$10$androidxmedia3sessionMediaControllerImplBase(long j, IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.seekTo(this.controllerStub, i, j);
    }

    public void seekTo(int i, long j) {
        if (isPlayerCommandAvailable(10)) {
            Assertions.checkArgument(i >= 0);
            dispatchRemoteSessionTaskWithPlayerCommand(new MediaControllerImplBase$$ExternalSyntheticLambda105(this, i, j));
            seekToInternal(i, j);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$seekTo$11$androidx-media3-session-MediaControllerImplBase  reason: not valid java name */
    public /* synthetic */ void m965lambda$seekTo$11$androidxmedia3sessionMediaControllerImplBase(int i, long j, IMediaSession iMediaSession, int i2) throws RemoteException {
        iMediaSession.seekToWithMediaItemIndex(this.controllerStub, i2, i, j);
    }

    public long getSeekBackIncrement() {
        return this.playerInfo.seekBackIncrementMs;
    }

    public void seekBack() {
        if (isPlayerCommandAvailable(11)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new MediaControllerImplBase$$ExternalSyntheticLambda92(this));
            seekToInternalByOffset(-getSeekBackIncrement());
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$seekBack$12$androidx-media3-session-MediaControllerImplBase  reason: not valid java name */
    public /* synthetic */ void m962lambda$seekBack$12$androidxmedia3sessionMediaControllerImplBase(IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.seekBack(this.controllerStub, i);
    }

    public long getSeekForwardIncrement() {
        return this.playerInfo.seekForwardIncrementMs;
    }

    public void seekForward() {
        if (isPlayerCommandAvailable(12)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new MediaControllerImplBase$$ExternalSyntheticLambda95(this));
            seekToInternalByOffset(getSeekForwardIncrement());
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$seekForward$13$androidx-media3-session-MediaControllerImplBase  reason: not valid java name */
    public /* synthetic */ void m963lambda$seekForward$13$androidxmedia3sessionMediaControllerImplBase(IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.seekForward(this.controllerStub, i);
    }

    public void setPlayWhenReady(boolean z) {
        if (isPlayerCommandAvailable(1)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new MediaControllerImplBase$$ExternalSyntheticLambda97(this, z));
            setPlayWhenReady(z, 1);
        } else if (z) {
            Log.w(TAG, "Calling play() omitted due to COMMAND_PLAY_PAUSE not being available. If this play command has started the service for instance for playback resumption, this may prevent the service from being started into the foreground.");
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setPlayWhenReady$14$androidx-media3-session-MediaControllerImplBase  reason: not valid java name */
    public /* synthetic */ void m990lambda$setPlayWhenReady$14$androidxmedia3sessionMediaControllerImplBase(boolean z, IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.setPlayWhenReady(this.controllerStub, i, z);
    }

    public boolean getPlayWhenReady() {
        return this.playerInfo.playWhenReady;
    }

    public int getPlaybackSuppressionReason() {
        return this.playerInfo.playbackSuppressionReason;
    }

    public PlaybackException getPlayerError() {
        return this.playerInfo.playerError;
    }

    public int getPlaybackState() {
        return this.playerInfo.playbackState;
    }

    public boolean isPlaying() {
        return this.playerInfo.isPlaying;
    }

    public boolean isLoading() {
        return this.playerInfo.isLoading;
    }

    public long getDuration() {
        return this.playerInfo.sessionPositionInfo.durationMs;
    }

    public long getCurrentPosition() {
        long updatedCurrentPositionMs = MediaUtils.getUpdatedCurrentPositionMs(this.playerInfo, this.currentPositionMs, this.lastSetPlayWhenReadyCalledTimeMs, getInstance().getTimeDiffMs());
        this.currentPositionMs = updatedCurrentPositionMs;
        return updatedCurrentPositionMs;
    }

    public long getBufferedPosition() {
        return this.playerInfo.sessionPositionInfo.bufferedPositionMs;
    }

    public int getBufferedPercentage() {
        return this.playerInfo.sessionPositionInfo.bufferedPercentage;
    }

    public long getTotalBufferedDuration() {
        return this.playerInfo.sessionPositionInfo.totalBufferedDurationMs;
    }

    public long getCurrentLiveOffset() {
        return this.playerInfo.sessionPositionInfo.currentLiveOffsetMs;
    }

    public long getContentDuration() {
        return this.playerInfo.sessionPositionInfo.contentDurationMs;
    }

    public long getContentPosition() {
        if (!this.playerInfo.sessionPositionInfo.isPlayingAd) {
            return getCurrentPosition();
        }
        return this.playerInfo.sessionPositionInfo.positionInfo.contentPositionMs;
    }

    public long getContentBufferedPosition() {
        return this.playerInfo.sessionPositionInfo.contentBufferedPositionMs;
    }

    public boolean isPlayingAd() {
        return this.playerInfo.sessionPositionInfo.isPlayingAd;
    }

    public int getCurrentAdGroupIndex() {
        return this.playerInfo.sessionPositionInfo.positionInfo.adGroupIndex;
    }

    public int getCurrentAdIndexInAdGroup() {
        return this.playerInfo.sessionPositionInfo.positionInfo.adIndexInAdGroup;
    }

    public void setPlaybackParameters(PlaybackParameters playbackParameters) {
        if (isPlayerCommandAvailable(13)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new MediaControllerImplBase$$ExternalSyntheticLambda31(this, playbackParameters));
            if (!this.playerInfo.playbackParameters.equals(playbackParameters)) {
                this.playerInfo = this.playerInfo.copyWithPlaybackParameters(playbackParameters);
                this.listeners.queueEvent(12, new MediaControllerImplBase$$ExternalSyntheticLambda33(playbackParameters));
                this.listeners.flushEvents();
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setPlaybackParameters$15$androidx-media3-session-MediaControllerImplBase  reason: not valid java name */
    public /* synthetic */ void m991lambda$setPlaybackParameters$15$androidxmedia3sessionMediaControllerImplBase(PlaybackParameters playbackParameters, IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.setPlaybackParameters(this.controllerStub, i, playbackParameters.toBundle());
    }

    public PlaybackParameters getPlaybackParameters() {
        return this.playerInfo.playbackParameters;
    }

    public void setPlaybackSpeed(float f) {
        if (isPlayerCommandAvailable(13)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new MediaControllerImplBase$$ExternalSyntheticLambda100(this, f));
            if (this.playerInfo.playbackParameters.speed != f) {
                PlaybackParameters withSpeed = this.playerInfo.playbackParameters.withSpeed(f);
                this.playerInfo = this.playerInfo.copyWithPlaybackParameters(withSpeed);
                this.listeners.queueEvent(12, new MediaControllerImplBase$$ExternalSyntheticLambda101(withSpeed));
                this.listeners.flushEvents();
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setPlaybackSpeed$17$androidx-media3-session-MediaControllerImplBase  reason: not valid java name */
    public /* synthetic */ void m992lambda$setPlaybackSpeed$17$androidxmedia3sessionMediaControllerImplBase(float f, IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.setPlaybackSpeed(this.controllerStub, i, f);
    }

    public AudioAttributes getAudioAttributes() {
        return this.playerInfo.audioAttributes;
    }

    public ListenableFuture<SessionResult> setRating(String str, Rating rating) {
        return dispatchRemoteSessionTaskWithSessionCommand((int) SessionCommand.COMMAND_CODE_SESSION_SET_RATING, (RemoteSessionTask) new MediaControllerImplBase$$ExternalSyntheticLambda0(this, str, rating));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setRating$19$androidx-media3-session-MediaControllerImplBase  reason: not valid java name */
    public /* synthetic */ void m994lambda$setRating$19$androidxmedia3sessionMediaControllerImplBase(String str, Rating rating, IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.setRatingWithMediaId(this.controllerStub, i, str, rating.toBundle());
    }

    public ListenableFuture<SessionResult> setRating(Rating rating) {
        return dispatchRemoteSessionTaskWithSessionCommand((int) SessionCommand.COMMAND_CODE_SESSION_SET_RATING, (RemoteSessionTask) new MediaControllerImplBase$$ExternalSyntheticLambda108(this, rating));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setRating$20$androidx-media3-session-MediaControllerImplBase  reason: not valid java name */
    public /* synthetic */ void m995lambda$setRating$20$androidxmedia3sessionMediaControllerImplBase(Rating rating, IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.setRating(this.controllerStub, i, rating.toBundle());
    }

    public ListenableFuture<SessionResult> sendCustomCommand(SessionCommand sessionCommand, Bundle bundle) {
        return dispatchRemoteSessionTaskWithSessionCommand(sessionCommand, (RemoteSessionTask) new MediaControllerImplBase$$ExternalSyntheticLambda96(this, sessionCommand, bundle));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$sendCustomCommand$21$androidx-media3-session-MediaControllerImplBase  reason: not valid java name */
    public /* synthetic */ void m973lambda$sendCustomCommand$21$androidxmedia3sessionMediaControllerImplBase(SessionCommand sessionCommand, Bundle bundle, IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.onCustomCommand(this.controllerStub, i, sessionCommand.toBundle(), bundle);
    }

    public PendingIntent getSessionActivity() {
        return this.sessionActivity;
    }

    public ImmutableList<CommandButton> getMediaButtonPreferences() {
        return this.resolvedMediaButtonPreferences;
    }

    public ImmutableList<CommandButton> getCommandButtonsForMediaItem(MediaItem mediaItem) {
        ImmutableList<String> immutableList = mediaItem.mediaMetadata.supportedCommands;
        SessionCommands availableSessionCommands = getAvailableSessionCommands();
        ImmutableList.Builder builder = new ImmutableList.Builder();
        for (int i = 0; i < immutableList.size(); i++) {
            CommandButton commandButton = this.commandButtonsForMediaItemsMap.get(immutableList.get(i));
            if (!(commandButton == null || commandButton.sessionCommand == null || !availableSessionCommands.contains(commandButton.sessionCommand))) {
                builder.add((Object) commandButton);
            }
        }
        return builder.build();
    }

    public Bundle getSessionExtras() {
        return this.sessionExtras;
    }

    public Timeline getCurrentTimeline() {
        return this.playerInfo.timeline;
    }

    public void setMediaItem(MediaItem mediaItem) {
        if (isPlayerCommandAvailable(31)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new MediaControllerImplBase$$ExternalSyntheticLambda116(this, mediaItem));
            setMediaItemsInternal(Collections.singletonList(mediaItem), -1, C.TIME_UNSET, true);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setMediaItem$22$androidx-media3-session-MediaControllerImplBase  reason: not valid java name */
    public /* synthetic */ void m984lambda$setMediaItem$22$androidxmedia3sessionMediaControllerImplBase(MediaItem mediaItem, IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.setMediaItem(this.controllerStub, i, mediaItem.toBundleIncludeLocalConfiguration());
    }

    public void setMediaItem(MediaItem mediaItem, long j) {
        if (isPlayerCommandAvailable(31)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new MediaControllerImplBase$$ExternalSyntheticLambda86(this, mediaItem, j));
            setMediaItemsInternal(Collections.singletonList(mediaItem), -1, j, false);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setMediaItem$23$androidx-media3-session-MediaControllerImplBase  reason: not valid java name */
    public /* synthetic */ void m985lambda$setMediaItem$23$androidxmedia3sessionMediaControllerImplBase(MediaItem mediaItem, long j, IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.setMediaItemWithStartPosition(this.controllerStub, i, mediaItem.toBundleIncludeLocalConfiguration(), j);
    }

    public void setMediaItem(MediaItem mediaItem, boolean z) {
        if (isPlayerCommandAvailable(31)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new MediaControllerImplBase$$ExternalSyntheticLambda112(this, mediaItem, z));
            setMediaItemsInternal(Collections.singletonList(mediaItem), -1, C.TIME_UNSET, z);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setMediaItem$24$androidx-media3-session-MediaControllerImplBase  reason: not valid java name */
    public /* synthetic */ void m986lambda$setMediaItem$24$androidxmedia3sessionMediaControllerImplBase(MediaItem mediaItem, boolean z, IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.setMediaItemWithResetPosition(this.controllerStub, i, mediaItem.toBundleIncludeLocalConfiguration(), z);
    }

    public void setMediaItems(List<MediaItem> list) {
        if (isPlayerCommandAvailable(20)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new MediaControllerImplBase$$ExternalSyntheticLambda98(this, list));
            setMediaItemsInternal(list, -1, C.TIME_UNSET, true);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setMediaItems$25$androidx-media3-session-MediaControllerImplBase  reason: not valid java name */
    public /* synthetic */ void m987lambda$setMediaItems$25$androidxmedia3sessionMediaControllerImplBase(List list, IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.setMediaItems(this.controllerStub, i, new BundleListRetriever(BundleCollectionUtil.toBundleList(list, new MediaControllerImplBase$$ExternalSyntheticLambda44())));
    }

    public void setMediaItems(List<MediaItem> list, boolean z) {
        if (isPlayerCommandAvailable(20)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new MediaControllerImplBase$$ExternalSyntheticLambda49(this, list, z));
            setMediaItemsInternal(list, -1, C.TIME_UNSET, z);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setMediaItems$26$androidx-media3-session-MediaControllerImplBase  reason: not valid java name */
    public /* synthetic */ void m988lambda$setMediaItems$26$androidxmedia3sessionMediaControllerImplBase(List list, boolean z, IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.setMediaItemsWithResetPosition(this.controllerStub, i, new BundleListRetriever(BundleCollectionUtil.toBundleList(list, new MediaControllerImplBase$$ExternalSyntheticLambda44())), z);
    }

    public void setMediaItems(List<MediaItem> list, int i, long j) {
        if (isPlayerCommandAvailable(20)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new MediaControllerImplBase$$ExternalSyntheticLambda32(this, list, i, j));
            setMediaItemsInternal(list, i, j, false);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setMediaItems$27$androidx-media3-session-MediaControllerImplBase  reason: not valid java name */
    public /* synthetic */ void m989lambda$setMediaItems$27$androidxmedia3sessionMediaControllerImplBase(List list, int i, long j, IMediaSession iMediaSession, int i2) throws RemoteException {
        iMediaSession.setMediaItemsWithStartIndex(this.controllerStub, i2, new BundleListRetriever(BundleCollectionUtil.toBundleList(list, new MediaControllerImplBase$$ExternalSyntheticLambda44())), i, j);
    }

    public void setPlaylistMetadata(MediaMetadata mediaMetadata) {
        if (isPlayerCommandAvailable(19)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new MediaControllerImplBase$$ExternalSyntheticLambda17(this, mediaMetadata));
            if (!this.playerInfo.playlistMetadata.equals(mediaMetadata)) {
                this.playerInfo = this.playerInfo.copyWithPlaylistMetadata(mediaMetadata);
                this.listeners.queueEvent(15, new MediaControllerImplBase$$ExternalSyntheticLambda18(mediaMetadata));
                this.listeners.flushEvents();
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setPlaylistMetadata$28$androidx-media3-session-MediaControllerImplBase  reason: not valid java name */
    public /* synthetic */ void m993lambda$setPlaylistMetadata$28$androidxmedia3sessionMediaControllerImplBase(MediaMetadata mediaMetadata, IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.setPlaylistMetadata(this.controllerStub, i, mediaMetadata.toBundle());
    }

    public MediaMetadata getPlaylistMetadata() {
        return this.playerInfo.playlistMetadata;
    }

    public void addMediaItem(MediaItem mediaItem) {
        if (isPlayerCommandAvailable(20)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new MediaControllerImplBase$$ExternalSyntheticLambda107(this, mediaItem));
            addMediaItemsInternal(getCurrentTimeline().getWindowCount(), Collections.singletonList(mediaItem));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$addMediaItem$30$androidx-media3-session-MediaControllerImplBase  reason: not valid java name */
    public /* synthetic */ void m925lambda$addMediaItem$30$androidxmedia3sessionMediaControllerImplBase(MediaItem mediaItem, IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.addMediaItem(this.controllerStub, i, mediaItem.toBundleIncludeLocalConfiguration());
    }

    public void addMediaItem(int i, MediaItem mediaItem) {
        if (isPlayerCommandAvailable(20)) {
            Assertions.checkArgument(i >= 0);
            dispatchRemoteSessionTaskWithPlayerCommand(new MediaControllerImplBase$$ExternalSyntheticLambda38(this, i, mediaItem));
            addMediaItemsInternal(i, Collections.singletonList(mediaItem));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$addMediaItem$31$androidx-media3-session-MediaControllerImplBase  reason: not valid java name */
    public /* synthetic */ void m926lambda$addMediaItem$31$androidxmedia3sessionMediaControllerImplBase(int i, MediaItem mediaItem, IMediaSession iMediaSession, int i2) throws RemoteException {
        iMediaSession.addMediaItemWithIndex(this.controllerStub, i2, i, mediaItem.toBundleIncludeLocalConfiguration());
    }

    public void addMediaItems(List<MediaItem> list) {
        if (isPlayerCommandAvailable(20)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new MediaControllerImplBase$$ExternalSyntheticLambda102(this, list));
            addMediaItemsInternal(getCurrentTimeline().getWindowCount(), list);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$addMediaItems$32$androidx-media3-session-MediaControllerImplBase  reason: not valid java name */
    public /* synthetic */ void m927lambda$addMediaItems$32$androidxmedia3sessionMediaControllerImplBase(List list, IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.addMediaItems(this.controllerStub, i, new BundleListRetriever(BundleCollectionUtil.toBundleList(list, new MediaControllerImplBase$$ExternalSyntheticLambda44())));
    }

    public void addMediaItems(int i, List<MediaItem> list) {
        if (isPlayerCommandAvailable(20)) {
            Assertions.checkArgument(i >= 0);
            dispatchRemoteSessionTaskWithPlayerCommand(new MediaControllerImplBase$$ExternalSyntheticLambda22(this, i, list));
            addMediaItemsInternal(i, list);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$addMediaItems$33$androidx-media3-session-MediaControllerImplBase  reason: not valid java name */
    public /* synthetic */ void m928lambda$addMediaItems$33$androidxmedia3sessionMediaControllerImplBase(int i, List list, IMediaSession iMediaSession, int i2) throws RemoteException {
        iMediaSession.addMediaItemsWithIndex(this.controllerStub, i2, i, new BundleListRetriever(BundleCollectionUtil.toBundleList(list, new MediaControllerImplBase$$ExternalSyntheticLambda44())));
    }

    private void addMediaItemsInternal(int i, List<MediaItem> list) {
        if (!list.isEmpty()) {
            if (this.playerInfo.timeline.isEmpty()) {
                setMediaItemsInternal(list, -1, C.TIME_UNSET, false);
                return;
            }
            int i2 = i;
            updatePlayerInfo(maskPlayerInfoForAddedItems(this.playerInfo, Math.min(i, this.playerInfo.timeline.getWindowCount()), list, getCurrentPosition(), getContentPosition()), 0, (Integer) null, (Integer) null, this.playerInfo.timeline.isEmpty() ? 3 : null);
        }
    }

    private static PlayerInfo maskPlayerInfoForAddedItems(PlayerInfo playerInfo2, int i, List<MediaItem> list, long j, long j2) {
        int i2;
        int i3;
        PlayerInfo playerInfo3 = playerInfo2;
        int i4 = i;
        Timeline timeline = playerInfo3.timeline;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        int i5 = 0;
        for (int i6 = 0; i6 < timeline.getWindowCount(); i6++) {
            arrayList.add(timeline.getWindow(i6, new Timeline.Window()));
        }
        for (int i7 = 0; i7 < list.size(); i7++) {
            List<MediaItem> list2 = list;
            arrayList.add(i7 + i4, createNewWindow(list.get(i7)));
        }
        List<MediaItem> list3 = list;
        rebuildPeriods(timeline, arrayList, arrayList2);
        Timeline createMaskingTimeline = createMaskingTimeline(arrayList, arrayList2);
        if (playerInfo3.timeline.isEmpty()) {
            i2 = 0;
        } else {
            if (playerInfo3.sessionPositionInfo.positionInfo.mediaItemIndex >= i4) {
                i2 = playerInfo3.sessionPositionInfo.positionInfo.mediaItemIndex + list.size();
            } else {
                i2 = playerInfo3.sessionPositionInfo.positionInfo.mediaItemIndex;
            }
            if (playerInfo3.sessionPositionInfo.positionInfo.periodIndex >= i4) {
                i3 = playerInfo3.sessionPositionInfo.positionInfo.periodIndex + list.size();
            } else {
                i3 = playerInfo3.sessionPositionInfo.positionInfo.periodIndex;
            }
            i5 = i3;
        }
        return maskTimelineAndPositionInfo(playerInfo2, createMaskingTimeline, i2, i5, j, j2, 5);
    }

    public void removeMediaItem(int i) {
        if (isPlayerCommandAvailable(20)) {
            Assertions.checkArgument(i >= 0);
            dispatchRemoteSessionTaskWithPlayerCommand(new MediaControllerImplBase$$ExternalSyntheticLambda12(this, i));
            removeMediaItemsInternal(i, i + 1);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$removeMediaItem$34$androidx-media3-session-MediaControllerImplBase  reason: not valid java name */
    public /* synthetic */ void m958lambda$removeMediaItem$34$androidxmedia3sessionMediaControllerImplBase(int i, IMediaSession iMediaSession, int i2) throws RemoteException {
        iMediaSession.removeMediaItem(this.controllerStub, i2, i);
    }

    public void removeMediaItems(int i, int i2) {
        if (isPlayerCommandAvailable(20)) {
            Assertions.checkArgument(i >= 0 && i2 >= i);
            dispatchRemoteSessionTaskWithPlayerCommand(new MediaControllerImplBase$$ExternalSyntheticLambda9(this, i, i2));
            removeMediaItemsInternal(i, i2);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$removeMediaItems$35$androidx-media3-session-MediaControllerImplBase  reason: not valid java name */
    public /* synthetic */ void m959lambda$removeMediaItems$35$androidxmedia3sessionMediaControllerImplBase(int i, int i2, IMediaSession iMediaSession, int i3) throws RemoteException {
        iMediaSession.removeMediaItems(this.controllerStub, i3, i, i2);
    }

    public void clearMediaItems() {
        if (isPlayerCommandAvailable(20)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new MediaControllerImplBase$$ExternalSyntheticLambda45(this));
            removeMediaItemsInternal(0, Integer.MAX_VALUE);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$clearMediaItems$36$androidx-media3-session-MediaControllerImplBase  reason: not valid java name */
    public /* synthetic */ void m929lambda$clearMediaItems$36$androidxmedia3sessionMediaControllerImplBase(IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.clearMediaItems(this.controllerStub, i);
    }

    private void removeMediaItemsInternal(int i, int i2) {
        int i3 = i;
        int windowCount = this.playerInfo.timeline.getWindowCount();
        int min = Math.min(i2, windowCount);
        if (i3 < windowCount && i3 != min && windowCount != 0) {
            boolean z = true;
            boolean z2 = getCurrentMediaItemIndex() >= i3 && getCurrentMediaItemIndex() < min;
            PlayerInfo maskPlayerInfoForRemovedItems = maskPlayerInfoForRemovedItems(this.playerInfo, i, min, false, getCurrentPosition(), getContentPosition());
            if (this.playerInfo.sessionPositionInfo.positionInfo.mediaItemIndex < i3 || this.playerInfo.sessionPositionInfo.positionInfo.mediaItemIndex >= min) {
                z = false;
            }
            int i4 = null;
            Integer num = z2 ? 4 : null;
            if (z) {
                i4 = 3;
            }
            updatePlayerInfo(maskPlayerInfoForRemovedItems, 0, (Integer) null, num, i4);
        }
    }

    private static PlayerInfo maskPlayerInfoForRemovedItems(PlayerInfo playerInfo2, int i, int i2, boolean z, long j, long j2) {
        int i3;
        int i4;
        int i5;
        int i6;
        PlayerInfo playerInfo3;
        PlayerInfo playerInfo4 = playerInfo2;
        int i7 = i;
        int i8 = i2;
        Timeline timeline = playerInfo4.timeline;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (int i9 = 0; i9 < timeline.getWindowCount(); i9++) {
            if (i9 < i7 || i9 >= i8) {
                arrayList.add(timeline.getWindow(i9, new Timeline.Window()));
            }
        }
        rebuildPeriods(timeline, arrayList, arrayList2);
        Timeline createMaskingTimeline = createMaskingTimeline(arrayList, arrayList2);
        int currentMediaItemIndexInternal = getCurrentMediaItemIndexInternal(playerInfo2);
        int i10 = playerInfo4.sessionPositionInfo.positionInfo.periodIndex;
        Timeline.Window window = new Timeline.Window();
        boolean z2 = currentMediaItemIndexInternal >= i7 && currentMediaItemIndexInternal < i8;
        if (createMaskingTimeline.isEmpty()) {
            i5 = -1;
            i4 = -1;
            i3 = 0;
        } else if (z2) {
            i4 = -1;
            int resolveSubsequentMediaItemIndex = resolveSubsequentMediaItemIndex(playerInfo4.repeatMode, playerInfo4.shuffleModeEnabled, currentMediaItemIndexInternal, timeline, i, i2);
            if (resolveSubsequentMediaItemIndex == -1) {
                resolveSubsequentMediaItemIndex = createMaskingTimeline.getFirstWindowIndex(playerInfo4.shuffleModeEnabled);
            } else if (resolveSubsequentMediaItemIndex >= i8) {
                resolveSubsequentMediaItemIndex -= i8 - i7;
            }
            i3 = createMaskingTimeline.getWindow(resolveSubsequentMediaItemIndex, window).firstPeriodIndex;
            i5 = resolveSubsequentMediaItemIndex;
        } else {
            i4 = -1;
            if (currentMediaItemIndexInternal >= i8) {
                i5 = currentMediaItemIndexInternal - (i8 - i7);
                i3 = getNewPeriodIndexWithoutRemovedPeriods(timeline, i10, i7, i8);
            } else {
                i3 = i10;
                i5 = currentMediaItemIndexInternal;
            }
        }
        if (!z2) {
            i6 = 4;
            playerInfo3 = maskTimelineAndPositionInfo(playerInfo2, createMaskingTimeline, i5, i3, j, j2, 4);
        } else if (i5 == i4) {
            playerInfo3 = maskTimelineAndPositionInfo(playerInfo4, createMaskingTimeline, SessionPositionInfo.DEFAULT_POSITION_INFO, SessionPositionInfo.DEFAULT, 4);
            i6 = 4;
        } else if (z) {
            i6 = 4;
            playerInfo3 = maskTimelineAndPositionInfo(playerInfo2, createMaskingTimeline, i5, i3, j, j2, 4);
        } else {
            i6 = 4;
            Timeline.Window window2 = createMaskingTimeline.getWindow(i5, new Timeline.Window());
            long defaultPositionMs = window2.getDefaultPositionMs();
            long durationMs = window2.getDurationMs();
            Player.PositionInfo positionInfo = r16;
            Player.PositionInfo positionInfo2 = new Player.PositionInfo((Object) null, i5, window2.mediaItem, (Object) null, i3, defaultPositionMs, defaultPositionMs, -1, -1);
            playerInfo3 = maskTimelineAndPositionInfo(playerInfo4, createMaskingTimeline, positionInfo2, new SessionPositionInfo(positionInfo, false, SystemClock.elapsedRealtime(), durationMs, defaultPositionMs, MediaUtils.calculateBufferedPercentage(defaultPositionMs, durationMs), 0, C.TIME_UNSET, durationMs, defaultPositionMs), 4);
        }
        return playerInfo3.playbackState != 1 && playerInfo3.playbackState != i6 && i7 < i8 && i8 == timeline.getWindowCount() && currentMediaItemIndexInternal >= i7 ? playerInfo3.copyWithPlaybackState(i6, (PlaybackException) null) : playerInfo3;
    }

    public void moveMediaItem(int i, int i2) {
        if (isPlayerCommandAvailable(20)) {
            Assertions.checkArgument(i >= 0 && i2 >= 0);
            dispatchRemoteSessionTaskWithPlayerCommand(new MediaControllerImplBase$$ExternalSyntheticLambda50(this, i, i2));
            moveMediaItemsInternal(i, i + 1, i2);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$moveMediaItem$37$androidx-media3-session-MediaControllerImplBase  reason: not valid java name */
    public /* synthetic */ void m939lambda$moveMediaItem$37$androidxmedia3sessionMediaControllerImplBase(int i, int i2, IMediaSession iMediaSession, int i3) throws RemoteException {
        iMediaSession.moveMediaItem(this.controllerStub, i3, i, i2);
    }

    public void moveMediaItems(int i, int i2, int i3) {
        if (isPlayerCommandAvailable(20)) {
            Assertions.checkArgument(i >= 0 && i <= i2 && i3 >= 0);
            dispatchRemoteSessionTaskWithPlayerCommand(new MediaControllerImplBase$$ExternalSyntheticLambda40(this, i, i2, i3));
            moveMediaItemsInternal(i, i2, i3);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$moveMediaItems$38$androidx-media3-session-MediaControllerImplBase  reason: not valid java name */
    public /* synthetic */ void m940lambda$moveMediaItems$38$androidxmedia3sessionMediaControllerImplBase(int i, int i2, int i3, IMediaSession iMediaSession, int i4) throws RemoteException {
        iMediaSession.moveMediaItems(this.controllerStub, i4, i, i2, i3);
    }

    public void replaceMediaItem(int i, MediaItem mediaItem) {
        if (isPlayerCommandAvailable(20)) {
            Assertions.checkArgument(i >= 0);
            dispatchRemoteSessionTaskWithPlayerCommand(new MediaControllerImplBase$$ExternalSyntheticLambda110(this, i, mediaItem));
            replaceMediaItemsInternal(i, i + 1, ImmutableList.of(mediaItem));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$replaceMediaItem$39$androidx-media3-session-MediaControllerImplBase  reason: not valid java name */
    public /* synthetic */ void m960lambda$replaceMediaItem$39$androidxmedia3sessionMediaControllerImplBase(int i, MediaItem mediaItem, IMediaSession iMediaSession, int i2) throws RemoteException {
        if (((SessionToken) Assertions.checkNotNull(this.connectedToken)).getInterfaceVersion() >= 2) {
            iMediaSession.replaceMediaItem(this.controllerStub, i2, i, mediaItem.toBundleIncludeLocalConfiguration());
            return;
        }
        iMediaSession.addMediaItemWithIndex(this.controllerStub, i2, i + 1, mediaItem.toBundleIncludeLocalConfiguration());
        iMediaSession.removeMediaItem(this.controllerStub, i2, i);
    }

    public void replaceMediaItems(int i, int i2, List<MediaItem> list) {
        if (isPlayerCommandAvailable(20)) {
            Assertions.checkArgument(i >= 0 && i <= i2);
            dispatchRemoteSessionTaskWithPlayerCommand(new MediaControllerImplBase$$ExternalSyntheticLambda39(this, list, i, i2));
            replaceMediaItemsInternal(i, i2, list);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$replaceMediaItems$40$androidx-media3-session-MediaControllerImplBase  reason: not valid java name */
    public /* synthetic */ void m961lambda$replaceMediaItems$40$androidxmedia3sessionMediaControllerImplBase(List list, int i, int i2, IMediaSession iMediaSession, int i3) throws RemoteException {
        BundleListRetriever bundleListRetriever = new BundleListRetriever(BundleCollectionUtil.toBundleList(list, new MediaControllerImplBase$$ExternalSyntheticLambda44()));
        if (((SessionToken) Assertions.checkNotNull(this.connectedToken)).getInterfaceVersion() >= 2) {
            iMediaSession.replaceMediaItems(this.controllerStub, i3, i, i2, bundleListRetriever);
            return;
        }
        iMediaSession.addMediaItemsWithIndex(this.controllerStub, i3, i2, bundleListRetriever);
        iMediaSession.removeMediaItems(this.controllerStub, i3, i, i2);
    }

    private void replaceMediaItemsInternal(int i, int i2, List<MediaItem> list) {
        int i3 = i;
        int windowCount = this.playerInfo.timeline.getWindowCount();
        if (i3 <= windowCount) {
            if (this.playerInfo.timeline.isEmpty()) {
                setMediaItemsInternal(list, -1, C.TIME_UNSET, false);
                return;
            }
            int min = Math.min(i2, windowCount);
            PlayerInfo maskPlayerInfoForRemovedItems = maskPlayerInfoForRemovedItems(maskPlayerInfoForAddedItems(this.playerInfo, min, list, getCurrentPosition(), getContentPosition()), i, min, true, getCurrentPosition(), getContentPosition());
            boolean z = this.playerInfo.sessionPositionInfo.positionInfo.mediaItemIndex >= i3 && this.playerInfo.sessionPositionInfo.positionInfo.mediaItemIndex < min;
            updatePlayerInfo(maskPlayerInfoForRemovedItems, (Integer) null, (Integer) null, z ? 4 : null, z ? 3 : null);
        }
    }

    public int getCurrentPeriodIndex() {
        return this.playerInfo.sessionPositionInfo.positionInfo.periodIndex;
    }

    public int getCurrentMediaItemIndex() {
        return getCurrentMediaItemIndexInternal(this.playerInfo);
    }

    public int getPreviousMediaItemIndex() {
        if (this.playerInfo.timeline.isEmpty()) {
            return -1;
        }
        return this.playerInfo.timeline.getPreviousWindowIndex(getCurrentMediaItemIndex(), convertRepeatModeForNavigation(this.playerInfo.repeatMode), this.playerInfo.shuffleModeEnabled);
    }

    public int getNextMediaItemIndex() {
        if (this.playerInfo.timeline.isEmpty()) {
            return -1;
        }
        return this.playerInfo.timeline.getNextWindowIndex(getCurrentMediaItemIndex(), convertRepeatModeForNavigation(this.playerInfo.repeatMode), this.playerInfo.shuffleModeEnabled);
    }

    public boolean hasPreviousMediaItem() {
        return getPreviousMediaItemIndex() != -1;
    }

    public boolean hasNextMediaItem() {
        return getNextMediaItemIndex() != -1;
    }

    public void seekToPreviousMediaItem() {
        if (isPlayerCommandAvailable(6)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new MediaControllerImplBase$$ExternalSyntheticLambda42(this));
            if (getPreviousMediaItemIndex() != -1) {
                seekToInternal(getPreviousMediaItemIndex(), C.TIME_UNSET);
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$seekToPreviousMediaItem$41$androidx-media3-session-MediaControllerImplBase  reason: not valid java name */
    public /* synthetic */ void m971lambda$seekToPreviousMediaItem$41$androidxmedia3sessionMediaControllerImplBase(IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.seekToPreviousMediaItem(this.controllerStub, i);
    }

    public void seekToNextMediaItem() {
        if (isPlayerCommandAvailable(8)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new MediaControllerImplBase$$ExternalSyntheticLambda56(this));
            if (getNextMediaItemIndex() != -1) {
                seekToInternal(getNextMediaItemIndex(), C.TIME_UNSET);
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$seekToNextMediaItem$42$androidx-media3-session-MediaControllerImplBase  reason: not valid java name */
    public /* synthetic */ void m969lambda$seekToNextMediaItem$42$androidxmedia3sessionMediaControllerImplBase(IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.seekToNextMediaItem(this.controllerStub, i);
    }

    public void seekToPrevious() {
        if (isPlayerCommandAvailable(7)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new MediaControllerImplBase$$ExternalSyntheticLambda106(this));
            Timeline currentTimeline = getCurrentTimeline();
            if (!currentTimeline.isEmpty() && !isPlayingAd()) {
                boolean hasPreviousMediaItem = hasPreviousMediaItem();
                Timeline.Window window = currentTimeline.getWindow(getCurrentMediaItemIndex(), new Timeline.Window());
                if (!window.isDynamic || !window.isLive()) {
                    if (!hasPreviousMediaItem || getCurrentPosition() > getMaxSeekToPreviousPosition()) {
                        seekToInternal(getCurrentMediaItemIndex(), 0);
                    } else {
                        seekToInternal(getPreviousMediaItemIndex(), C.TIME_UNSET);
                    }
                } else if (hasPreviousMediaItem) {
                    seekToInternal(getPreviousMediaItemIndex(), C.TIME_UNSET);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$seekToPrevious$43$androidx-media3-session-MediaControllerImplBase  reason: not valid java name */
    public /* synthetic */ void m970lambda$seekToPrevious$43$androidxmedia3sessionMediaControllerImplBase(IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.seekToPrevious(this.controllerStub, i);
    }

    public long getMaxSeekToPreviousPosition() {
        return this.playerInfo.maxSeekToPreviousPositionMs;
    }

    public void seekToNext() {
        if (isPlayerCommandAvailable(9)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new MediaControllerImplBase$$ExternalSyntheticLambda20(this));
            Timeline currentTimeline = getCurrentTimeline();
            if (!currentTimeline.isEmpty() && !isPlayingAd()) {
                if (hasNextMediaItem()) {
                    seekToInternal(getNextMediaItemIndex(), C.TIME_UNSET);
                    return;
                }
                Timeline.Window window = currentTimeline.getWindow(getCurrentMediaItemIndex(), new Timeline.Window());
                if (window.isDynamic && window.isLive()) {
                    seekToInternal(getCurrentMediaItemIndex(), C.TIME_UNSET);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$seekToNext$44$androidx-media3-session-MediaControllerImplBase  reason: not valid java name */
    public /* synthetic */ void m968lambda$seekToNext$44$androidxmedia3sessionMediaControllerImplBase(IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.seekToNext(this.controllerStub, i);
    }

    public int getRepeatMode() {
        return this.playerInfo.repeatMode;
    }

    public void setRepeatMode(int i) {
        if (isPlayerCommandAvailable(15)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new MediaControllerImplBase$$ExternalSyntheticLambda27(this, i));
            if (this.playerInfo.repeatMode != i) {
                this.playerInfo = this.playerInfo.copyWithRepeatMode(i);
                this.listeners.queueEvent(8, new MediaControllerImplBase$$ExternalSyntheticLambda28(i));
                this.listeners.flushEvents();
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setRepeatMode$45$androidx-media3-session-MediaControllerImplBase  reason: not valid java name */
    public /* synthetic */ void m996lambda$setRepeatMode$45$androidxmedia3sessionMediaControllerImplBase(int i, IMediaSession iMediaSession, int i2) throws RemoteException {
        iMediaSession.setRepeatMode(this.controllerStub, i2, i);
    }

    public boolean getShuffleModeEnabled() {
        return this.playerInfo.shuffleModeEnabled;
    }

    public void setShuffleModeEnabled(boolean z) {
        if (isPlayerCommandAvailable(14)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new MediaControllerImplBase$$ExternalSyntheticLambda13(this, z));
            if (this.playerInfo.shuffleModeEnabled != z) {
                this.playerInfo = this.playerInfo.copyWithShuffleModeEnabled(z);
                this.listeners.queueEvent(9, new MediaControllerImplBase$$ExternalSyntheticLambda14(z));
                this.listeners.flushEvents();
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setShuffleModeEnabled$47$androidx-media3-session-MediaControllerImplBase  reason: not valid java name */
    public /* synthetic */ void m997lambda$setShuffleModeEnabled$47$androidxmedia3sessionMediaControllerImplBase(boolean z, IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.setShuffleModeEnabled(this.controllerStub, i, z);
    }

    public CueGroup getCurrentCues() {
        return this.playerInfo.cueGroup;
    }

    public float getVolume() {
        return this.playerInfo.volume;
    }

    public void setVolume(float f) {
        if (isPlayerCommandAvailable(24)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new MediaControllerImplBase$$ExternalSyntheticLambda34(this, f));
            if (this.playerInfo.volume != f) {
                this.playerInfo = this.playerInfo.copyWithVolume(f);
                this.listeners.queueEvent(22, new MediaControllerImplBase$$ExternalSyntheticLambda35(f));
                this.listeners.flushEvents();
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setVolume$49$androidx-media3-session-MediaControllerImplBase  reason: not valid java name */
    public /* synthetic */ void m1004lambda$setVolume$49$androidxmedia3sessionMediaControllerImplBase(float f, IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.setVolume(this.controllerStub, i, f);
    }

    public DeviceInfo getDeviceInfo() {
        return this.playerInfo.deviceInfo;
    }

    public int getDeviceVolume() {
        return this.playerInfo.deviceVolume;
    }

    public boolean isDeviceMuted() {
        return this.playerInfo.deviceMuted;
    }

    @Deprecated
    public void setDeviceVolume(int i) {
        if (isPlayerCommandAvailable(25)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new MediaControllerImplBase$$ExternalSyntheticLambda21(this, i));
            DeviceInfo deviceInfo = getDeviceInfo();
            if (this.playerInfo.deviceVolume != i && deviceInfo.minVolume <= i) {
                if (deviceInfo.maxVolume == 0 || i <= deviceInfo.maxVolume) {
                    PlayerInfo playerInfo2 = this.playerInfo;
                    this.playerInfo = playerInfo2.copyWithDeviceVolume(i, playerInfo2.deviceMuted);
                    this.listeners.queueEvent(30, new MediaControllerImplBase$$ExternalSyntheticLambda23(this, i));
                    this.listeners.flushEvents();
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setDeviceVolume$51$androidx-media3-session-MediaControllerImplBase  reason: not valid java name */
    public /* synthetic */ void m979lambda$setDeviceVolume$51$androidxmedia3sessionMediaControllerImplBase(int i, IMediaSession iMediaSession, int i2) throws RemoteException {
        iMediaSession.setDeviceVolume(this.controllerStub, i2, i);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setDeviceVolume$52$androidx-media3-session-MediaControllerImplBase  reason: not valid java name */
    public /* synthetic */ void m980lambda$setDeviceVolume$52$androidxmedia3sessionMediaControllerImplBase(int i, Player.Listener listener) {
        listener.onDeviceVolumeChanged(i, this.playerInfo.deviceMuted);
    }

    public void setDeviceVolume(int i, int i2) {
        if (isPlayerCommandAvailable(33)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new MediaControllerImplBase$$ExternalSyntheticLambda5(this, i, i2));
            DeviceInfo deviceInfo = getDeviceInfo();
            if (this.playerInfo.deviceVolume != i && deviceInfo.minVolume <= i) {
                if (deviceInfo.maxVolume == 0 || i <= deviceInfo.maxVolume) {
                    PlayerInfo playerInfo2 = this.playerInfo;
                    this.playerInfo = playerInfo2.copyWithDeviceVolume(i, playerInfo2.deviceMuted);
                    this.listeners.queueEvent(30, new MediaControllerImplBase$$ExternalSyntheticLambda6(this, i));
                    this.listeners.flushEvents();
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setDeviceVolume$53$androidx-media3-session-MediaControllerImplBase  reason: not valid java name */
    public /* synthetic */ void m981lambda$setDeviceVolume$53$androidxmedia3sessionMediaControllerImplBase(int i, int i2, IMediaSession iMediaSession, int i3) throws RemoteException {
        iMediaSession.setDeviceVolumeWithFlags(this.controllerStub, i3, i, i2);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setDeviceVolume$54$androidx-media3-session-MediaControllerImplBase  reason: not valid java name */
    public /* synthetic */ void m982lambda$setDeviceVolume$54$androidxmedia3sessionMediaControllerImplBase(int i, Player.Listener listener) {
        listener.onDeviceVolumeChanged(i, this.playerInfo.deviceMuted);
    }

    @Deprecated
    public void increaseDeviceVolume() {
        if (isPlayerCommandAvailable(26)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new MediaControllerImplBase$$ExternalSyntheticLambda103(this));
            int i = this.playerInfo.deviceVolume + 1;
            int i2 = getDeviceInfo().maxVolume;
            if (i2 == 0 || i <= i2) {
                PlayerInfo playerInfo2 = this.playerInfo;
                this.playerInfo = playerInfo2.copyWithDeviceVolume(i, playerInfo2.deviceMuted);
                this.listeners.queueEvent(30, new MediaControllerImplBase$$ExternalSyntheticLambda104(this, i));
                this.listeners.flushEvents();
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$increaseDeviceVolume$55$androidx-media3-session-MediaControllerImplBase  reason: not valid java name */
    public /* synthetic */ void m935lambda$increaseDeviceVolume$55$androidxmedia3sessionMediaControllerImplBase(IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.increaseDeviceVolume(this.controllerStub, i);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$increaseDeviceVolume$56$androidx-media3-session-MediaControllerImplBase  reason: not valid java name */
    public /* synthetic */ void m936lambda$increaseDeviceVolume$56$androidxmedia3sessionMediaControllerImplBase(int i, Player.Listener listener) {
        listener.onDeviceVolumeChanged(i, this.playerInfo.deviceMuted);
    }

    public void increaseDeviceVolume(int i) {
        if (isPlayerCommandAvailable(34)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new MediaControllerImplBase$$ExternalSyntheticLambda117(this, i));
            int i2 = this.playerInfo.deviceVolume + 1;
            int i3 = getDeviceInfo().maxVolume;
            if (i3 == 0 || i2 <= i3) {
                PlayerInfo playerInfo2 = this.playerInfo;
                this.playerInfo = playerInfo2.copyWithDeviceVolume(i2, playerInfo2.deviceMuted);
                this.listeners.queueEvent(30, new MediaControllerImplBase$$ExternalSyntheticLambda118(this, i2));
                this.listeners.flushEvents();
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$increaseDeviceVolume$57$androidx-media3-session-MediaControllerImplBase  reason: not valid java name */
    public /* synthetic */ void m937lambda$increaseDeviceVolume$57$androidxmedia3sessionMediaControllerImplBase(int i, IMediaSession iMediaSession, int i2) throws RemoteException {
        iMediaSession.increaseDeviceVolumeWithFlags(this.controllerStub, i2, i);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$increaseDeviceVolume$58$androidx-media3-session-MediaControllerImplBase  reason: not valid java name */
    public /* synthetic */ void m938lambda$increaseDeviceVolume$58$androidxmedia3sessionMediaControllerImplBase(int i, Player.Listener listener) {
        listener.onDeviceVolumeChanged(i, this.playerInfo.deviceMuted);
    }

    @Deprecated
    public void decreaseDeviceVolume() {
        if (isPlayerCommandAvailable(26)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new MediaControllerImplBase$$ExternalSyntheticLambda24(this));
            int i = this.playerInfo.deviceVolume - 1;
            if (i >= getDeviceInfo().minVolume) {
                PlayerInfo playerInfo2 = this.playerInfo;
                this.playerInfo = playerInfo2.copyWithDeviceVolume(i, playerInfo2.deviceMuted);
                this.listeners.queueEvent(30, new MediaControllerImplBase$$ExternalSyntheticLambda25(this, i));
                this.listeners.flushEvents();
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$decreaseDeviceVolume$59$androidx-media3-session-MediaControllerImplBase  reason: not valid java name */
    public /* synthetic */ void m931lambda$decreaseDeviceVolume$59$androidxmedia3sessionMediaControllerImplBase(IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.decreaseDeviceVolume(this.controllerStub, i);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$decreaseDeviceVolume$60$androidx-media3-session-MediaControllerImplBase  reason: not valid java name */
    public /* synthetic */ void m932lambda$decreaseDeviceVolume$60$androidxmedia3sessionMediaControllerImplBase(int i, Player.Listener listener) {
        listener.onDeviceVolumeChanged(i, this.playerInfo.deviceMuted);
    }

    public void decreaseDeviceVolume(int i) {
        if (isPlayerCommandAvailable(34)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new MediaControllerImplBase$$ExternalSyntheticLambda119(this, i));
            int i2 = this.playerInfo.deviceVolume - 1;
            if (i2 >= getDeviceInfo().minVolume) {
                PlayerInfo playerInfo2 = this.playerInfo;
                this.playerInfo = playerInfo2.copyWithDeviceVolume(i2, playerInfo2.deviceMuted);
                this.listeners.queueEvent(30, new MediaControllerImplBase$$ExternalSyntheticLambda11(this, i2));
                this.listeners.flushEvents();
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$decreaseDeviceVolume$61$androidx-media3-session-MediaControllerImplBase  reason: not valid java name */
    public /* synthetic */ void m933lambda$decreaseDeviceVolume$61$androidxmedia3sessionMediaControllerImplBase(int i, IMediaSession iMediaSession, int i2) throws RemoteException {
        iMediaSession.decreaseDeviceVolumeWithFlags(this.controllerStub, i2, i);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$decreaseDeviceVolume$62$androidx-media3-session-MediaControllerImplBase  reason: not valid java name */
    public /* synthetic */ void m934lambda$decreaseDeviceVolume$62$androidxmedia3sessionMediaControllerImplBase(int i, Player.Listener listener) {
        listener.onDeviceVolumeChanged(i, this.playerInfo.deviceMuted);
    }

    @Deprecated
    public void setDeviceMuted(boolean z) {
        if (isPlayerCommandAvailable(26)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new MediaControllerImplBase$$ExternalSyntheticLambda41(this, z));
            if (this.playerInfo.deviceMuted != z) {
                PlayerInfo playerInfo2 = this.playerInfo;
                this.playerInfo = playerInfo2.copyWithDeviceVolume(playerInfo2.deviceVolume, z);
                this.listeners.queueEvent(30, new MediaControllerImplBase$$ExternalSyntheticLambda43(this, z));
                this.listeners.flushEvents();
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setDeviceMuted$63$androidx-media3-session-MediaControllerImplBase  reason: not valid java name */
    public /* synthetic */ void m975lambda$setDeviceMuted$63$androidxmedia3sessionMediaControllerImplBase(boolean z, IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.setDeviceMuted(this.controllerStub, i, z);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setDeviceMuted$64$androidx-media3-session-MediaControllerImplBase  reason: not valid java name */
    public /* synthetic */ void m976lambda$setDeviceMuted$64$androidxmedia3sessionMediaControllerImplBase(boolean z, Player.Listener listener) {
        listener.onDeviceVolumeChanged(this.playerInfo.deviceVolume, z);
    }

    public void setDeviceMuted(boolean z, int i) {
        if (isPlayerCommandAvailable(34)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new MediaControllerImplBase$$ExternalSyntheticLambda64(this, z, i));
            if (this.playerInfo.deviceMuted != z) {
                PlayerInfo playerInfo2 = this.playerInfo;
                this.playerInfo = playerInfo2.copyWithDeviceVolume(playerInfo2.deviceVolume, z);
                this.listeners.queueEvent(30, new MediaControllerImplBase$$ExternalSyntheticLambda75(this, z));
                this.listeners.flushEvents();
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setDeviceMuted$65$androidx-media3-session-MediaControllerImplBase  reason: not valid java name */
    public /* synthetic */ void m977lambda$setDeviceMuted$65$androidxmedia3sessionMediaControllerImplBase(boolean z, int i, IMediaSession iMediaSession, int i2) throws RemoteException {
        iMediaSession.setDeviceMutedWithFlags(this.controllerStub, i2, z, i);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setDeviceMuted$66$androidx-media3-session-MediaControllerImplBase  reason: not valid java name */
    public /* synthetic */ void m978lambda$setDeviceMuted$66$androidxmedia3sessionMediaControllerImplBase(boolean z, Player.Listener listener) {
        listener.onDeviceVolumeChanged(this.playerInfo.deviceVolume, z);
    }

    public void setAudioAttributes(AudioAttributes audioAttributes, boolean z) {
        if (isPlayerCommandAvailable(35)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new MediaControllerImplBase$$ExternalSyntheticLambda7(this, audioAttributes, z));
            if (!this.playerInfo.audioAttributes.equals(audioAttributes)) {
                this.playerInfo = this.playerInfo.copyWithAudioAttributes(audioAttributes);
                this.listeners.queueEvent(20, new MediaControllerImplBase$$ExternalSyntheticLambda8(audioAttributes));
                this.listeners.flushEvents();
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setAudioAttributes$67$androidx-media3-session-MediaControllerImplBase  reason: not valid java name */
    public /* synthetic */ void m974lambda$setAudioAttributes$67$androidxmedia3sessionMediaControllerImplBase(AudioAttributes audioAttributes, boolean z, IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.setAudioAttributes(this.controllerStub, i, audioAttributes.toBundle(), z);
    }

    public VideoSize getVideoSize() {
        return this.playerInfo.videoSize;
    }

    public Size getSurfaceSize() {
        return this.surfaceSize;
    }

    public void clearVideoSurface() {
        if (isPlayerCommandAvailable(27)) {
            clearSurfacesAndCallbacks();
            dispatchRemoteSessionTaskWithPlayerCommandAndWaitForFuture(new MediaControllerImplBase$$ExternalSyntheticLambda90(this));
            maybeNotifySurfaceSizeChanged(0, 0);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$clearVideoSurface$69$androidx-media3-session-MediaControllerImplBase  reason: not valid java name */
    public /* synthetic */ void m930lambda$clearVideoSurface$69$androidxmedia3sessionMediaControllerImplBase(IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.setVideoSurface(this.controllerStub, i, (Surface) null);
    }

    public void clearVideoSurface(Surface surface) {
        if (isPlayerCommandAvailable(27) && surface != null && this.videoSurface == surface) {
            clearVideoSurface();
        }
    }

    public void setVideoSurface(Surface surface) {
        if (isPlayerCommandAvailable(27)) {
            clearSurfacesAndCallbacks();
            this.videoSurface = surface;
            dispatchRemoteSessionTaskWithPlayerCommandAndWaitForFuture(new MediaControllerImplBase$$ExternalSyntheticLambda91(this, surface));
            int i = surface == null ? 0 : -1;
            maybeNotifySurfaceSizeChanged(i, i);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setVideoSurface$70$androidx-media3-session-MediaControllerImplBase  reason: not valid java name */
    public /* synthetic */ void m999lambda$setVideoSurface$70$androidxmedia3sessionMediaControllerImplBase(Surface surface, IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.setVideoSurface(this.controllerStub, i, surface);
    }

    public void setVideoSurfaceHolder(SurfaceHolder surfaceHolder) {
        if (isPlayerCommandAvailable(27)) {
            if (surfaceHolder == null) {
                clearVideoSurface();
            } else if (this.videoSurfaceHolder != surfaceHolder) {
                clearSurfacesAndCallbacks();
                this.videoSurfaceHolder = surfaceHolder;
                surfaceHolder.addCallback(this.surfaceCallback);
                Surface surface = surfaceHolder.getSurface();
                if (surface == null || !surface.isValid()) {
                    this.videoSurface = null;
                    dispatchRemoteSessionTaskWithPlayerCommandAndWaitForFuture(new MediaControllerImplBase$$ExternalSyntheticLambda4(this));
                    maybeNotifySurfaceSizeChanged(0, 0);
                    return;
                }
                this.videoSurface = surface;
                dispatchRemoteSessionTaskWithPlayerCommandAndWaitForFuture(new MediaControllerImplBase$$ExternalSyntheticLambda3(this, surface));
                Rect surfaceFrame = surfaceHolder.getSurfaceFrame();
                maybeNotifySurfaceSizeChanged(surfaceFrame.width(), surfaceFrame.height());
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setVideoSurfaceHolder$71$androidx-media3-session-MediaControllerImplBase  reason: not valid java name */
    public /* synthetic */ void m1000lambda$setVideoSurfaceHolder$71$androidxmedia3sessionMediaControllerImplBase(Surface surface, IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.setVideoSurface(this.controllerStub, i, surface);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setVideoSurfaceHolder$72$androidx-media3-session-MediaControllerImplBase  reason: not valid java name */
    public /* synthetic */ void m1001lambda$setVideoSurfaceHolder$72$androidxmedia3sessionMediaControllerImplBase(IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.setVideoSurface(this.controllerStub, i, (Surface) null);
    }

    public void clearVideoSurfaceHolder(SurfaceHolder surfaceHolder) {
        if (isPlayerCommandAvailable(27) && surfaceHolder != null && this.videoSurfaceHolder == surfaceHolder) {
            clearVideoSurface();
        }
    }

    public void setVideoSurfaceView(SurfaceView surfaceView) {
        SurfaceHolder surfaceHolder;
        if (isPlayerCommandAvailable(27)) {
            if (surfaceView == null) {
                surfaceHolder = null;
            } else {
                surfaceHolder = surfaceView.getHolder();
            }
            setVideoSurfaceHolder(surfaceHolder);
        }
    }

    public void clearVideoSurfaceView(SurfaceView surfaceView) {
        SurfaceHolder surfaceHolder;
        if (isPlayerCommandAvailable(27)) {
            if (surfaceView == null) {
                surfaceHolder = null;
            } else {
                surfaceHolder = surfaceView.getHolder();
            }
            clearVideoSurfaceHolder(surfaceHolder);
        }
    }

    public void setVideoTextureView(TextureView textureView) {
        if (isPlayerCommandAvailable(27)) {
            if (textureView == null) {
                clearVideoSurface();
            } else if (this.videoTextureView != textureView) {
                clearSurfacesAndCallbacks();
                this.videoTextureView = textureView;
                textureView.setSurfaceTextureListener(this.surfaceCallback);
                SurfaceTexture surfaceTexture = textureView.getSurfaceTexture();
                if (surfaceTexture == null) {
                    dispatchRemoteSessionTaskWithPlayerCommandAndWaitForFuture(new MediaControllerImplBase$$ExternalSyntheticLambda15(this));
                    maybeNotifySurfaceSizeChanged(0, 0);
                    return;
                }
                this.videoSurface = new Surface(surfaceTexture);
                dispatchRemoteSessionTaskWithPlayerCommandAndWaitForFuture(new MediaControllerImplBase$$ExternalSyntheticLambda16(this));
                maybeNotifySurfaceSizeChanged(textureView.getWidth(), textureView.getHeight());
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setVideoTextureView$73$androidx-media3-session-MediaControllerImplBase  reason: not valid java name */
    public /* synthetic */ void m1002lambda$setVideoTextureView$73$androidxmedia3sessionMediaControllerImplBase(IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.setVideoSurface(this.controllerStub, i, (Surface) null);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setVideoTextureView$74$androidx-media3-session-MediaControllerImplBase  reason: not valid java name */
    public /* synthetic */ void m1003lambda$setVideoTextureView$74$androidxmedia3sessionMediaControllerImplBase(IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.setVideoSurface(this.controllerStub, i, this.videoSurface);
    }

    public void clearVideoTextureView(TextureView textureView) {
        if (isPlayerCommandAvailable(27) && textureView != null && this.videoTextureView == textureView) {
            clearVideoSurface();
        }
    }

    public MediaMetadata getMediaMetadata() {
        return this.playerInfo.mediaMetadata;
    }

    public Player.Commands getAvailableCommands() {
        return this.intersectedPlayerCommands;
    }

    public Tracks getCurrentTracks() {
        return this.playerInfo.currentTracks;
    }

    public TrackSelectionParameters getTrackSelectionParameters() {
        return this.playerInfo.trackSelectionParameters;
    }

    public void setTrackSelectionParameters(TrackSelectionParameters trackSelectionParameters) {
        if (isPlayerCommandAvailable(29)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new MediaControllerImplBase$$ExternalSyntheticLambda29(this, trackSelectionParameters));
            if (trackSelectionParameters != this.playerInfo.trackSelectionParameters) {
                this.playerInfo = this.playerInfo.copyWithTrackSelectionParameters(trackSelectionParameters);
                this.listeners.queueEvent(19, new MediaControllerImplBase$$ExternalSyntheticLambda30(trackSelectionParameters));
                this.listeners.flushEvents();
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setTrackSelectionParameters$75$androidx-media3-session-MediaControllerImplBase  reason: not valid java name */
    public /* synthetic */ void m998lambda$setTrackSelectionParameters$75$androidxmedia3sessionMediaControllerImplBase(TrackSelectionParameters trackSelectionParameters, IMediaSession iMediaSession, int i) throws RemoteException {
        iMediaSession.setTrackSelectionParameters(this.controllerStub, i, trackSelectionParameters.toBundle());
    }

    public SessionCommands getAvailableSessionCommands() {
        return this.sessionCommands;
    }

    public Context getContext() {
        return this.context;
    }

    public IMediaController getBinder() {
        return this.controllerStub;
    }

    private static Timeline createMaskingTimeline(List<Timeline.Window> list, List<Timeline.Period> list2) {
        return new Timeline.RemotableTimeline(new ImmutableList.Builder().addAll((Iterable) list).build(), new ImmutableList.Builder().addAll((Iterable) list2).build(), MediaUtils.generateUnshuffledIndices(list.size()));
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0090  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00e3  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0171  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0177  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x018b  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x018d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void setMediaItemsInternal(java.util.List<androidx.media3.common.MediaItem> r62, int r63, long r64, boolean r66) {
        /*
            r61 = this;
            r6 = r61
            r0 = r62
            r1 = r63
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            r4 = 0
            r5 = r4
        L_0x0012:
            int r7 = r62.size()
            if (r5 >= r7) goto L_0x002f
            java.lang.Object r7 = r0.get(r5)
            androidx.media3.common.MediaItem r7 = (androidx.media3.common.MediaItem) r7
            androidx.media3.common.Timeline$Window r7 = androidx.media3.session.LegacyConversions.convertToWindow(r7, r5)
            r2.add(r7)
            androidx.media3.common.Timeline$Period r7 = androidx.media3.session.LegacyConversions.convertToPeriod(r5)
            r3.add(r7)
            int r5 = r5 + 1
            goto L_0x0012
        L_0x002f:
            androidx.media3.common.Timeline r2 = createMaskingTimeline(r2, r3)
            boolean r3 = r2.isEmpty()
            if (r3 != 0) goto L_0x0048
            int r3 = r2.getWindowCount()
            if (r1 >= r3) goto L_0x0040
            goto L_0x0048
        L_0x0040:
            androidx.media3.common.IllegalSeekPositionException r0 = new androidx.media3.common.IllegalSeekPositionException
            r7 = r64
            r0.<init>(r2, r1, r7)
            throw r0
        L_0x0048:
            r7 = r64
            r3 = -1
            r9 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r5 = 1
            if (r66 == 0) goto L_0x005f
            androidx.media3.session.PlayerInfo r1 = r6.playerInfo
            boolean r1 = r1.shuffleModeEnabled
            int r1 = r2.getFirstWindowIndex(r1)
            r23 = r4
        L_0x005d:
            r7 = r9
            goto L_0x008a
        L_0x005f:
            if (r1 != r3) goto L_0x0088
            androidx.media3.session.PlayerInfo r1 = r6.playerInfo
            androidx.media3.session.SessionPositionInfo r1 = r1.sessionPositionInfo
            androidx.media3.common.Player$PositionInfo r1 = r1.positionInfo
            int r1 = r1.mediaItemIndex
            androidx.media3.session.PlayerInfo r7 = r6.playerInfo
            androidx.media3.session.SessionPositionInfo r7 = r7.sessionPositionInfo
            androidx.media3.common.Player$PositionInfo r7 = r7.positionInfo
            long r7 = r7.positionMs
            boolean r11 = r2.isEmpty()
            if (r11 != 0) goto L_0x0088
            int r11 = r2.getWindowCount()
            if (r1 < r11) goto L_0x0088
            androidx.media3.session.PlayerInfo r1 = r6.playerInfo
            boolean r1 = r1.shuffleModeEnabled
            int r1 = r2.getFirstWindowIndex(r1)
            r23 = r5
            goto L_0x005d
        L_0x0088:
            r23 = r4
        L_0x008a:
            androidx.media3.session.MediaControllerImplBase$PeriodInfo r24 = r6.getPeriodInfo(r2, r1, r7)
            if (r24 != 0) goto L_0x00e3
            androidx.media3.common.Player$PositionInfo r0 = new androidx.media3.common.Player$PositionInfo
            r12 = 0
            r14 = 0
            r15 = 0
            int r9 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            r24 = 0
            if (r9 != 0) goto L_0x009e
            r17 = r24
            goto L_0x00a0
        L_0x009e:
            r17 = r7
        L_0x00a0:
            if (r9 != 0) goto L_0x00a5
            r19 = r24
            goto L_0x00a7
        L_0x00a5:
            r19 = r7
        L_0x00a7:
            r21 = -1
            r22 = -1
            r11 = r0
            r13 = r1
            r16 = r1
            r11.<init>(r12, r13, r14, r15, r16, r17, r19, r21, r22)
            androidx.media3.session.SessionPositionInfo r10 = new androidx.media3.session.SessionPositionInfo
            r27 = 0
            long r28 = android.os.SystemClock.elapsedRealtime()
            r30 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            if (r9 != 0) goto L_0x00c4
            r32 = r24
            goto L_0x00c6
        L_0x00c4:
            r32 = r7
        L_0x00c6:
            r34 = 0
            r35 = 0
            r37 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r39 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            if (r9 != 0) goto L_0x00d9
            r41 = r24
            goto L_0x00db
        L_0x00d9:
            r41 = r7
        L_0x00db:
            r25 = r10
            r26 = r0
            r25.<init>(r26, r27, r28, r30, r32, r34, r35, r37, r39, r41)
            goto L_0x013e
        L_0x00e3:
            androidx.media3.common.Player$PositionInfo r7 = new androidx.media3.common.Player$PositionInfo
            r44 = r7
            r12 = 0
            java.lang.Object r0 = r0.get(r1)
            r14 = r0
            androidx.media3.common.MediaItem r14 = (androidx.media3.common.MediaItem) r14
            r15 = 0
            int r16 = r24.index
            long r8 = r24.periodPositionUs
            long r17 = androidx.media3.common.util.Util.usToMs(r8)
            long r8 = r24.periodPositionUs
            long r19 = androidx.media3.common.util.Util.usToMs(r8)
            r21 = -1
            r22 = -1
            r11 = r7
            r13 = r1
            r11.<init>(r12, r13, r14, r15, r16, r17, r19, r21, r22)
            androidx.media3.session.SessionPositionInfo r10 = new androidx.media3.session.SessionPositionInfo
            r43 = r10
            r45 = 0
            long r46 = android.os.SystemClock.elapsedRealtime()
            r48 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            long r8 = r24.periodPositionUs
            long r50 = androidx.media3.common.util.Util.usToMs(r8)
            r52 = 0
            r53 = 0
            r55 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r57 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            long r8 = r24.periodPositionUs
            long r59 = androidx.media3.common.util.Util.usToMs(r8)
            r43.<init>(r44, r45, r46, r48, r50, r52, r53, r55, r57, r59)
            r0 = r7
        L_0x013e:
            androidx.media3.session.PlayerInfo r7 = r6.playerInfo
            r8 = 4
            androidx.media3.session.PlayerInfo r0 = maskTimelineAndPositionInfo(r7, r2, r0, r10, r8)
            int r7 = r0.playbackState
            if (r1 == r3) goto L_0x0159
            int r1 = r0.playbackState
            if (r1 == r5) goto L_0x0159
            boolean r1 = r2.isEmpty()
            if (r1 != 0) goto L_0x0158
            if (r23 == 0) goto L_0x0156
            goto L_0x0158
        L_0x0156:
            r7 = 2
            goto L_0x0159
        L_0x0158:
            r7 = r8
        L_0x0159:
            androidx.media3.session.PlayerInfo r1 = r6.playerInfo
            androidx.media3.common.PlaybackException r1 = r1.playerError
            androidx.media3.session.PlayerInfo r1 = r0.copyWithPlaybackState(r7, r1)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r4)
            r3 = 0
            androidx.media3.session.PlayerInfo r0 = r6.playerInfo
            androidx.media3.common.Timeline r0 = r0.timeline
            boolean r0 = r0.isEmpty()
            r4 = 0
            if (r0 != 0) goto L_0x0177
            java.lang.Integer r0 = java.lang.Integer.valueOf(r8)
            r5 = r0
            goto L_0x0178
        L_0x0177:
            r5 = r4
        L_0x0178:
            androidx.media3.session.PlayerInfo r0 = r6.playerInfo
            androidx.media3.common.Timeline r0 = r0.timeline
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L_0x018d
            androidx.media3.common.Timeline r0 = r1.timeline
            boolean r0 = r0.isEmpty()
            if (r0 != 0) goto L_0x018b
            goto L_0x018d
        L_0x018b:
            r7 = r4
            goto L_0x0193
        L_0x018d:
            r0 = 3
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r7 = r0
        L_0x0193:
            r0 = r61
            r4 = r5
            r5 = r7
            r0.updatePlayerInfo(r1, r2, r3, r4, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.session.MediaControllerImplBase.setMediaItemsInternal(java.util.List, int, long, boolean):void");
    }

    private void moveMediaItemsInternal(int i, int i2, int i3) {
        int i4;
        int i5;
        int i6 = i;
        Timeline timeline = this.playerInfo.timeline;
        int windowCount = this.playerInfo.timeline.getWindowCount();
        int min = Math.min(i2, windowCount);
        int i7 = min - i6;
        int min2 = Math.min(i3, windowCount - i7);
        if (i6 < windowCount && i6 != min && i6 != min2) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            for (int i8 = 0; i8 < windowCount; i8++) {
                arrayList.add(timeline.getWindow(i8, new Timeline.Window()));
            }
            Util.moveItems(arrayList, i6, min, min2);
            rebuildPeriods(timeline, arrayList, arrayList2);
            Timeline createMaskingTimeline = createMaskingTimeline(arrayList, arrayList2);
            if (!createMaskingTimeline.isEmpty()) {
                int currentMediaItemIndex = getCurrentMediaItemIndex();
                if (currentMediaItemIndex >= i6 && currentMediaItemIndex < min) {
                    i5 = (currentMediaItemIndex - i6) + min2;
                } else if (min > currentMediaItemIndex || min2 <= currentMediaItemIndex) {
                    i4 = (min <= currentMediaItemIndex || min2 > currentMediaItemIndex) ? currentMediaItemIndex : i7 + currentMediaItemIndex;
                    Timeline.Window window = new Timeline.Window();
                    updatePlayerInfo(maskTimelineAndPositionInfo(this.playerInfo, createMaskingTimeline, i4, createMaskingTimeline.getWindow(i4, window).firstPeriodIndex + (this.playerInfo.sessionPositionInfo.positionInfo.periodIndex - timeline.getWindow(currentMediaItemIndex, window).firstPeriodIndex), getCurrentPosition(), getContentPosition(), 5), 0, (Integer) null, (Integer) null, (Integer) null);
                } else {
                    i5 = currentMediaItemIndex - i7;
                }
                i4 = i5;
                Timeline.Window window2 = new Timeline.Window();
                updatePlayerInfo(maskTimelineAndPositionInfo(this.playerInfo, createMaskingTimeline, i4, createMaskingTimeline.getWindow(i4, window2).firstPeriodIndex + (this.playerInfo.sessionPositionInfo.positionInfo.periodIndex - timeline.getWindow(currentMediaItemIndex, window2).firstPeriodIndex), getCurrentPosition(), getContentPosition(), 5), 0, (Integer) null, (Integer) null, (Integer) null);
            }
        }
    }

    private void seekToInternalByOffset(long j) {
        long currentPosition = getCurrentPosition() + j;
        long duration = getDuration();
        if (duration != C.TIME_UNSET) {
            currentPosition = Math.min(currentPosition, duration);
        }
        seekToInternal(getCurrentMediaItemIndex(), Math.max(currentPosition, 0));
    }

    private void seekToInternal(int i, long j) {
        PlayerInfo playerInfo2;
        int i2;
        MediaControllerImplBase mediaControllerImplBase = this;
        int i3 = i;
        long j2 = j;
        Timeline timeline = mediaControllerImplBase.playerInfo.timeline;
        if ((timeline.isEmpty() || i3 < timeline.getWindowCount()) && !isPlayingAd()) {
            int i4 = getPlaybackState() == 1 ? 1 : 2;
            PlayerInfo playerInfo3 = mediaControllerImplBase.playerInfo;
            PlayerInfo copyWithPlaybackState = playerInfo3.copyWithPlaybackState(i4, playerInfo3.playerError);
            PeriodInfo periodInfo = mediaControllerImplBase.getPeriodInfo(timeline, i3, j2);
            if (periodInfo == null) {
                int i5 = (j2 > C.TIME_UNSET ? 1 : (j2 == C.TIME_UNSET ? 0 : -1));
                Player.PositionInfo positionInfo = new Player.PositionInfo((Object) null, i, (MediaItem) null, (Object) null, i, i5 == 0 ? 0 : j2, i5 == 0 ? 0 : j2, -1, -1);
                PlayerInfo playerInfo4 = mediaControllerImplBase.playerInfo;
                playerInfo2 = maskTimelineAndPositionInfo(playerInfo4, playerInfo4.timeline, positionInfo, new SessionPositionInfo(positionInfo, mediaControllerImplBase.playerInfo.sessionPositionInfo.isPlayingAd, SystemClock.elapsedRealtime(), mediaControllerImplBase.playerInfo.sessionPositionInfo.durationMs, i5 == 0 ? 0 : j2, 0, 0, mediaControllerImplBase.playerInfo.sessionPositionInfo.currentLiveOffsetMs, mediaControllerImplBase.playerInfo.sessionPositionInfo.contentDurationMs, i5 == 0 ? 0 : j), 1);
                mediaControllerImplBase = this;
            } else {
                playerInfo2 = mediaControllerImplBase.maskPositionInfo(copyWithPlaybackState, timeline, periodInfo);
            }
            boolean z = false;
            boolean z2 = !mediaControllerImplBase.playerInfo.timeline.isEmpty() && playerInfo2.sessionPositionInfo.positionInfo.mediaItemIndex != mediaControllerImplBase.playerInfo.sessionPositionInfo.positionInfo.mediaItemIndex;
            if (z2 || playerInfo2.sessionPositionInfo.positionInfo.positionMs != mediaControllerImplBase.playerInfo.sessionPositionInfo.positionInfo.positionMs) {
                z = true;
            }
            if (z) {
                if (z2) {
                    i2 = 2;
                } else {
                    i2 = null;
                }
                updatePlayerInfo(playerInfo2, (Integer) null, (Integer) null, 1, i2);
            }
        }
    }

    private void setPlayWhenReady(boolean z, int i) {
        int playbackSuppressionReason = getPlaybackSuppressionReason();
        if (playbackSuppressionReason == 1) {
            playbackSuppressionReason = 0;
        }
        if (this.playerInfo.playWhenReady != z || this.playerInfo.playbackSuppressionReason != playbackSuppressionReason) {
            this.currentPositionMs = MediaUtils.getUpdatedCurrentPositionMs(this.playerInfo, this.currentPositionMs, this.lastSetPlayWhenReadyCalledTimeMs, getInstance().getTimeDiffMs());
            this.lastSetPlayWhenReadyCalledTimeMs = SystemClock.elapsedRealtime();
            updatePlayerInfo(this.playerInfo.copyWithPlayWhenReady(z, i, playbackSuppressionReason), (Integer) null, Integer.valueOf(i), (Integer) null, (Integer) null);
        }
    }

    private void updatePlayerInfo(PlayerInfo playerInfo2, Integer num, Integer num2, Integer num3, Integer num4) {
        PlayerInfo playerInfo3 = this.playerInfo;
        this.playerInfo = playerInfo2;
        notifyPlayerInfoListenersWithReasons(playerInfo3, playerInfo2, num, num2, num3, num4);
    }

    private void notifyPlayerInfoListenersWithReasons(PlayerInfo playerInfo2, PlayerInfo playerInfo3, Integer num, Integer num2, Integer num3, Integer num4) {
        boolean z = false;
        if (num != null) {
            this.listeners.queueEvent(0, new MediaControllerImplBase$$ExternalSyntheticLambda59(playerInfo3, num));
        }
        if (num3 != null) {
            this.listeners.queueEvent(11, new MediaControllerImplBase$$ExternalSyntheticLambda71(playerInfo3, num3));
        }
        MediaItem currentMediaItem = playerInfo3.getCurrentMediaItem();
        if (num4 != null) {
            this.listeners.queueEvent(1, new MediaControllerImplBase$$ExternalSyntheticLambda80(currentMediaItem, num4));
        }
        PlaybackException playbackException = playerInfo2.playerError;
        PlaybackException playbackException2 = playerInfo3.playerError;
        if (playbackException == playbackException2 || (playbackException != null && playbackException.errorInfoEquals(playbackException2))) {
            z = true;
        }
        if (!z) {
            this.listeners.queueEvent(10, new MediaControllerImplBase$$ExternalSyntheticLambda81(playbackException2));
            if (playbackException2 != null) {
                this.listeners.queueEvent(10, new MediaControllerImplBase$$ExternalSyntheticLambda82(playbackException2));
            }
        }
        if (!playerInfo2.currentTracks.equals(playerInfo3.currentTracks)) {
            this.listeners.queueEvent(2, new MediaControllerImplBase$$ExternalSyntheticLambda83(playerInfo3));
        }
        if (!playerInfo2.mediaMetadata.equals(playerInfo3.mediaMetadata)) {
            this.listeners.queueEvent(14, new MediaControllerImplBase$$ExternalSyntheticLambda84(playerInfo3));
        }
        if (playerInfo2.isLoading != playerInfo3.isLoading) {
            this.listeners.queueEvent(3, new MediaControllerImplBase$$ExternalSyntheticLambda85(playerInfo3));
        }
        if (playerInfo2.playbackState != playerInfo3.playbackState) {
            this.listeners.queueEvent(4, new MediaControllerImplBase$$ExternalSyntheticLambda87(playerInfo3));
        }
        if (num2 != null) {
            this.listeners.queueEvent(5, new MediaControllerImplBase$$ExternalSyntheticLambda88(playerInfo3, num2));
        }
        if (playerInfo2.playbackSuppressionReason != playerInfo3.playbackSuppressionReason) {
            this.listeners.queueEvent(6, new MediaControllerImplBase$$ExternalSyntheticLambda60(playerInfo3));
        }
        if (playerInfo2.isPlaying != playerInfo3.isPlaying) {
            this.listeners.queueEvent(7, new MediaControllerImplBase$$ExternalSyntheticLambda61(playerInfo3));
        }
        if (!playerInfo2.playbackParameters.equals(playerInfo3.playbackParameters)) {
            this.listeners.queueEvent(12, new MediaControllerImplBase$$ExternalSyntheticLambda62(playerInfo3));
        }
        if (playerInfo2.repeatMode != playerInfo3.repeatMode) {
            this.listeners.queueEvent(8, new MediaControllerImplBase$$ExternalSyntheticLambda63(playerInfo3));
        }
        if (playerInfo2.shuffleModeEnabled != playerInfo3.shuffleModeEnabled) {
            this.listeners.queueEvent(9, new MediaControllerImplBase$$ExternalSyntheticLambda65(playerInfo3));
        }
        if (!playerInfo2.playlistMetadata.equals(playerInfo3.playlistMetadata)) {
            this.listeners.queueEvent(15, new MediaControllerImplBase$$ExternalSyntheticLambda66(playerInfo3));
        }
        if (playerInfo2.volume != playerInfo3.volume) {
            this.listeners.queueEvent(22, new MediaControllerImplBase$$ExternalSyntheticLambda67(playerInfo3));
        }
        if (!playerInfo2.audioAttributes.equals(playerInfo3.audioAttributes)) {
            this.listeners.queueEvent(20, new MediaControllerImplBase$$ExternalSyntheticLambda68(playerInfo3));
        }
        if (!playerInfo2.cueGroup.cues.equals(playerInfo3.cueGroup.cues)) {
            this.listeners.queueEvent(27, new MediaControllerImplBase$$ExternalSyntheticLambda69(playerInfo3));
            this.listeners.queueEvent(27, new MediaControllerImplBase$$ExternalSyntheticLambda70(playerInfo3));
        }
        if (!playerInfo2.deviceInfo.equals(playerInfo3.deviceInfo)) {
            this.listeners.queueEvent(29, new MediaControllerImplBase$$ExternalSyntheticLambda72(playerInfo3));
        }
        if (!(playerInfo2.deviceVolume == playerInfo3.deviceVolume && playerInfo2.deviceMuted == playerInfo3.deviceMuted)) {
            this.listeners.queueEvent(30, new MediaControllerImplBase$$ExternalSyntheticLambda73(playerInfo3));
        }
        if (!playerInfo2.videoSize.equals(playerInfo3.videoSize)) {
            this.listeners.queueEvent(25, new MediaControllerImplBase$$ExternalSyntheticLambda74(playerInfo3));
        }
        if (playerInfo2.seekBackIncrementMs != playerInfo3.seekBackIncrementMs) {
            this.listeners.queueEvent(16, new MediaControllerImplBase$$ExternalSyntheticLambda76(playerInfo3));
        }
        if (playerInfo2.seekForwardIncrementMs != playerInfo3.seekForwardIncrementMs) {
            this.listeners.queueEvent(17, new MediaControllerImplBase$$ExternalSyntheticLambda77(playerInfo3));
        }
        if (playerInfo2.maxSeekToPreviousPositionMs != playerInfo3.maxSeekToPreviousPositionMs) {
            this.listeners.queueEvent(18, new MediaControllerImplBase$$ExternalSyntheticLambda78(playerInfo3));
        }
        if (!playerInfo2.trackSelectionParameters.equals(playerInfo3.trackSelectionParameters)) {
            this.listeners.queueEvent(19, new MediaControllerImplBase$$ExternalSyntheticLambda79(playerInfo3));
        }
        this.listeners.flushEvents();
    }

    private boolean requestConnectToService() {
        int i = Util.SDK_INT >= 29 ? FragmentTransaction.TRANSIT_FRAGMENT_OPEN : 1;
        Intent intent = new Intent(MediaSessionService.SERVICE_INTERFACE);
        intent.setClassName(this.token.getPackageName(), this.token.getServiceName());
        if (this.context.bindService(intent, this.serviceConnection, i)) {
            return true;
        }
        Log.w(TAG, "bind to " + this.token + " failed");
        return false;
    }

    private boolean requestConnectToSession(Bundle bundle) {
        try {
            IMediaSession.Stub.asInterface((IBinder) Assertions.checkStateNotNull(this.token.getBinder())).connect(this.controllerStub, this.sequencedFutureManager.obtainNextSequenceNumber(), new ConnectionRequest(this.context.getPackageName(), Process.myPid(), bundle, this.instance.getMaxCommandsForMediaItems()).toBundle());
            return true;
        } catch (RemoteException e) {
            Log.w(TAG, "Failed to call connection request.", e);
            return false;
        }
    }

    private void clearSurfacesAndCallbacks() {
        TextureView textureView = this.videoTextureView;
        if (textureView != null) {
            textureView.setSurfaceTextureListener((TextureView.SurfaceTextureListener) null);
            this.videoTextureView = null;
        }
        SurfaceHolder surfaceHolder = this.videoSurfaceHolder;
        if (surfaceHolder != null) {
            surfaceHolder.removeCallback(this.surfaceCallback);
            this.videoSurfaceHolder = null;
        }
        if (this.videoSurface != null) {
            this.videoSurface = null;
        }
    }

    /* access modifiers changed from: private */
    public void maybeNotifySurfaceSizeChanged(int i, int i2) {
        if (this.surfaceSize.getWidth() != i || this.surfaceSize.getHeight() != i2) {
            this.surfaceSize = new Size(i, i2);
            this.listeners.sendEvent(24, new MediaControllerImplBase$$ExternalSyntheticLambda10(i, i2));
        }
    }

    /* access modifiers changed from: package-private */
    public IMediaSession getSessionInterfaceWithSessionCommandIfAble(int i) {
        Assertions.checkArgument(i != 0);
        if (this.sessionCommands.contains(i)) {
            return this.iSession;
        }
        Log.w(TAG, "Controller isn't allowed to call command, commandCode=" + i);
        return null;
    }

    /* access modifiers changed from: package-private */
    public IMediaSession getSessionInterfaceWithSessionCommandIfAble(SessionCommand sessionCommand) {
        Assertions.checkArgument(sessionCommand.commandCode == 0);
        if (this.sessionCommands.contains(sessionCommand)) {
            return this.iSession;
        }
        Log.w(TAG, "Controller isn't allowed to call custom session command:" + sessionCommand.customAction);
        return null;
    }

    /* access modifiers changed from: package-private */
    public void notifyPeriodicSessionPositionInfoChanged(SessionPositionInfo sessionPositionInfo) {
        if (isConnected()) {
            updateSessionPositionInfoIfNeeded(sessionPositionInfo);
        }
    }

    /* access modifiers changed from: package-private */
    public <T> void setFutureResult(int i, T t) {
        this.sequencedFutureManager.setFutureResult(i, t);
        getInstance().runOnApplicationLooper(new MediaControllerImplBase$$ExternalSyntheticLambda2(this, i));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setFutureResult$105$androidx-media3-session-MediaControllerImplBase  reason: not valid java name */
    public /* synthetic */ void m983lambda$setFutureResult$105$androidxmedia3sessionMediaControllerImplBase(int i) {
        this.pendingMaskingSequencedFutureNumbers.remove(Integer.valueOf(i));
    }

    /* access modifiers changed from: package-private */
    public void onConnected(ConnectionState connectionState) {
        if (this.iSession != null) {
            Log.e(TAG, "Cannot be notified about the connection result many times. Probably a bug or malicious app.");
            getInstance().release();
            return;
        }
        this.iSession = connectionState.sessionBinder;
        this.sessionActivity = connectionState.sessionActivity;
        this.sessionCommands = connectionState.sessionCommands;
        this.playerCommandsFromSession = connectionState.playerCommandsFromSession;
        Player.Commands commands = connectionState.playerCommandsFromPlayer;
        this.playerCommandsFromPlayer = commands;
        this.intersectedPlayerCommands = createIntersectedCommandsEnsuringCommandReleaseAvailable(this.playerCommandsFromSession, commands);
        this.customLayoutOriginal = connectionState.customLayout;
        ImmutableList<CommandButton> immutableList = connectionState.mediaButtonPreferences;
        this.mediaButtonPreferencesOriginal = immutableList;
        this.resolvedMediaButtonPreferences = resolveMediaButtonPreferences(immutableList, this.customLayoutOriginal, this.sessionCommands, this.intersectedPlayerCommands);
        ImmutableMap.Builder builder = new ImmutableMap.Builder();
        for (int i = 0; i < connectionState.commandButtonsForMediaItems.size(); i++) {
            CommandButton commandButton = (CommandButton) connectionState.commandButtonsForMediaItems.get(i);
            if (commandButton.sessionCommand != null && commandButton.sessionCommand.commandCode == 0) {
                builder.put(commandButton.sessionCommand.customAction, commandButton);
            }
        }
        this.commandButtonsForMediaItemsMap = builder.buildOrThrow();
        this.playerInfo = connectionState.playerInfo;
        MediaSession.Token platformToken = connectionState.platformToken == null ? this.token.getPlatformToken() : connectionState.platformToken;
        if (platformToken != null) {
            this.platformController = new android.media.session.MediaController(this.context, platformToken);
        }
        try {
            connectionState.sessionBinder.asBinder().linkToDeath(this.deathRecipient, 0);
            this.connectedToken = new SessionToken(this.token.getUid(), 0, connectionState.libraryVersion, connectionState.sessionInterfaceVersion, this.token.getPackageName(), connectionState.sessionBinder, connectionState.tokenExtras, platformToken);
            this.sessionExtras = connectionState.sessionExtras;
            getInstance().notifyAccepted();
        } catch (RemoteException unused) {
            getInstance().release();
        }
    }

    private void sendControllerResult(int i, SessionResult sessionResult) {
        IMediaSession iMediaSession = this.iSession;
        if (iMediaSession != null) {
            try {
                iMediaSession.onControllerResult(this.controllerStub, i, sessionResult.toBundle());
            } catch (RemoteException unused) {
                Log.w(TAG, "Error in sending");
            }
        }
    }

    private void sendControllerResultWhenReady(int i, ListenableFuture<SessionResult> listenableFuture) {
        listenableFuture.addListener(new MediaControllerImplBase$$ExternalSyntheticLambda36(this, listenableFuture, i), MoreExecutors.directExecutor());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$sendControllerResultWhenReady$106$androidx-media3-session-MediaControllerImplBase  reason: not valid java name */
    public /* synthetic */ void m972lambda$sendControllerResultWhenReady$106$androidxmedia3sessionMediaControllerImplBase(ListenableFuture listenableFuture, int i) {
        SessionResult sessionResult;
        try {
            sessionResult = (SessionResult) Assertions.checkNotNull((SessionResult) listenableFuture.get(), "SessionResult must not be null");
        } catch (CancellationException e) {
            Log.w(TAG, "Session operation cancelled", e);
            sessionResult = new SessionResult(1);
        } catch (InterruptedException | ExecutionException e2) {
            Log.w(TAG, "Session operation failed", e2);
            sessionResult = new SessionResult(-1);
        }
        sendControllerResult(i, sessionResult);
    }

    /* access modifiers changed from: package-private */
    public void onCustomCommand(int i, SessionCommand sessionCommand, Bundle bundle) {
        if (isConnected()) {
            getInstance().notifyControllerListener(new MediaControllerImplBase$$ExternalSyntheticLambda58(this, sessionCommand, bundle, i));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onCustomCommand$107$androidx-media3-session-MediaControllerImplBase  reason: not valid java name */
    public /* synthetic */ void m948lambda$onCustomCommand$107$androidxmedia3sessionMediaControllerImplBase(SessionCommand sessionCommand, Bundle bundle, int i, MediaController.Listener listener) {
        sendControllerResultWhenReady(i, (ListenableFuture) Assertions.checkNotNull(listener.onCustomCommand(getInstance(), sessionCommand, bundle), "ControllerCallback#onCustomCommand() must not return null"));
    }

    /* access modifiers changed from: package-private */
    public void onPlayerInfoChanged(PlayerInfo playerInfo2, PlayerInfo.BundlingExclusions bundlingExclusions) {
        Integer num;
        PlayerInfo.BundlingExclusions bundlingExclusions2;
        if (isConnected()) {
            PlayerInfo playerInfo3 = this.pendingPlayerInfo;
            if (!(playerInfo3 == null || (bundlingExclusions2 = this.pendingBundlingExclusions) == null)) {
                Pair<PlayerInfo, PlayerInfo.BundlingExclusions> mergePlayerInfo = MediaUtils.mergePlayerInfo(playerInfo3, bundlingExclusions2, playerInfo2, bundlingExclusions, this.intersectedPlayerCommands);
                PlayerInfo playerInfo4 = (PlayerInfo) mergePlayerInfo.first;
                bundlingExclusions = (PlayerInfo.BundlingExclusions) mergePlayerInfo.second;
                playerInfo2 = playerInfo4;
            }
            Integer num2 = null;
            this.pendingPlayerInfo = null;
            this.pendingBundlingExclusions = null;
            if (!this.pendingMaskingSequencedFutureNumbers.isEmpty()) {
                this.pendingPlayerInfo = playerInfo2;
                this.pendingBundlingExclusions = bundlingExclusions;
                return;
            }
            PlayerInfo playerInfo5 = this.playerInfo;
            PlayerInfo playerInfo6 = (PlayerInfo) MediaUtils.mergePlayerInfo(playerInfo5, PlayerInfo.BundlingExclusions.NONE, playerInfo2, bundlingExclusions, this.intersectedPlayerCommands).first;
            this.playerInfo = playerInfo6;
            if (!playerInfo5.oldPositionInfo.equals(playerInfo2.oldPositionInfo) || !playerInfo5.newPositionInfo.equals(playerInfo2.newPositionInfo)) {
                num = Integer.valueOf(playerInfo6.discontinuityReason);
            } else {
                num = null;
            }
            Integer valueOf = !Util.areEqual(playerInfo5.getCurrentMediaItem(), playerInfo6.getCurrentMediaItem()) ? Integer.valueOf(playerInfo6.mediaItemTransitionReason) : null;
            Integer valueOf2 = !playerInfo5.timeline.equals(playerInfo6.timeline) ? Integer.valueOf(playerInfo6.timelineChangeReason) : null;
            if (!(playerInfo5.playWhenReadyChangeReason == playerInfo6.playWhenReadyChangeReason && playerInfo5.playWhenReady == playerInfo6.playWhenReady)) {
                num2 = Integer.valueOf(playerInfo6.playWhenReadyChangeReason);
            }
            notifyPlayerInfoListenersWithReasons(playerInfo5, playerInfo6, valueOf2, num2, num, valueOf);
        }
    }

    /* access modifiers changed from: package-private */
    public void onAvailableCommandsChangedFromSession(SessionCommands sessionCommands2, Player.Commands commands) {
        boolean z;
        if (isConnected()) {
            boolean z2 = !Util.areEqual(this.playerCommandsFromSession, commands);
            boolean z3 = !Util.areEqual(this.sessionCommands, sessionCommands2);
            if (z2 || z3) {
                this.sessionCommands = sessionCommands2;
                boolean z4 = false;
                if (z2) {
                    this.playerCommandsFromSession = commands;
                    Player.Commands commands2 = this.intersectedPlayerCommands;
                    Player.Commands createIntersectedCommandsEnsuringCommandReleaseAvailable = createIntersectedCommandsEnsuringCommandReleaseAvailable(commands, this.playerCommandsFromPlayer);
                    this.intersectedPlayerCommands = createIntersectedCommandsEnsuringCommandReleaseAvailable;
                    z = !Util.areEqual(createIntersectedCommandsEnsuringCommandReleaseAvailable, commands2);
                } else {
                    z = false;
                }
                if (z3 || z) {
                    ImmutableList<CommandButton> immutableList = this.resolvedMediaButtonPreferences;
                    ImmutableList<CommandButton> resolveMediaButtonPreferences = resolveMediaButtonPreferences(this.mediaButtonPreferencesOriginal, this.customLayoutOriginal, sessionCommands2, this.intersectedPlayerCommands);
                    this.resolvedMediaButtonPreferences = resolveMediaButtonPreferences;
                    z4 = !resolveMediaButtonPreferences.equals(immutableList);
                }
                if (z) {
                    this.listeners.sendEvent(13, new MediaControllerImplBase$$ExternalSyntheticLambda113(this));
                }
                if (z3) {
                    getInstance().notifyControllerListener(new MediaControllerImplBase$$ExternalSyntheticLambda114(this, sessionCommands2));
                }
                if (z4) {
                    getInstance().notifyControllerListener(new MediaControllerImplBase$$ExternalSyntheticLambda115(this));
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onAvailableCommandsChangedFromSession$108$androidx-media3-session-MediaControllerImplBase  reason: not valid java name */
    public /* synthetic */ void m945lambda$onAvailableCommandsChangedFromSession$108$androidxmedia3sessionMediaControllerImplBase(Player.Listener listener) {
        listener.onAvailableCommandsChanged(this.intersectedPlayerCommands);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onAvailableCommandsChangedFromSession$109$androidx-media3-session-MediaControllerImplBase  reason: not valid java name */
    public /* synthetic */ void m946lambda$onAvailableCommandsChangedFromSession$109$androidxmedia3sessionMediaControllerImplBase(SessionCommands sessionCommands2, MediaController.Listener listener) {
        listener.onAvailableSessionCommandsChanged(getInstance(), sessionCommands2);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onAvailableCommandsChangedFromSession$110$androidx-media3-session-MediaControllerImplBase  reason: not valid java name */
    public /* synthetic */ void m947lambda$onAvailableCommandsChangedFromSession$110$androidxmedia3sessionMediaControllerImplBase(MediaController.Listener listener) {
        listener.onCustomLayoutChanged(getInstance(), this.resolvedMediaButtonPreferences);
        listener.onMediaButtonPreferencesChanged(getInstance(), this.resolvedMediaButtonPreferences);
    }

    /* access modifiers changed from: package-private */
    public void onAvailableCommandsChangedFromPlayer(Player.Commands commands) {
        boolean z;
        if (isConnected() && !Util.areEqual(this.playerCommandsFromPlayer, commands)) {
            this.playerCommandsFromPlayer = commands;
            Player.Commands commands2 = this.intersectedPlayerCommands;
            Player.Commands createIntersectedCommandsEnsuringCommandReleaseAvailable = createIntersectedCommandsEnsuringCommandReleaseAvailable(this.playerCommandsFromSession, commands);
            this.intersectedPlayerCommands = createIntersectedCommandsEnsuringCommandReleaseAvailable;
            if (!Util.areEqual(createIntersectedCommandsEnsuringCommandReleaseAvailable, commands2)) {
                ImmutableList<CommandButton> immutableList = this.resolvedMediaButtonPreferences;
                ImmutableList<CommandButton> resolveMediaButtonPreferences = resolveMediaButtonPreferences(this.mediaButtonPreferencesOriginal, this.customLayoutOriginal, this.sessionCommands, this.intersectedPlayerCommands);
                this.resolvedMediaButtonPreferences = resolveMediaButtonPreferences;
                z = !resolveMediaButtonPreferences.equals(immutableList);
                this.listeners.sendEvent(13, new MediaControllerImplBase$$ExternalSyntheticLambda51(this));
            } else {
                z = false;
            }
            if (z) {
                getInstance().notifyControllerListener(new MediaControllerImplBase$$ExternalSyntheticLambda52(this));
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onAvailableCommandsChangedFromPlayer$111$androidx-media3-session-MediaControllerImplBase  reason: not valid java name */
    public /* synthetic */ void m943lambda$onAvailableCommandsChangedFromPlayer$111$androidxmedia3sessionMediaControllerImplBase(Player.Listener listener) {
        listener.onAvailableCommandsChanged(this.intersectedPlayerCommands);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onAvailableCommandsChangedFromPlayer$112$androidx-media3-session-MediaControllerImplBase  reason: not valid java name */
    public /* synthetic */ void m944lambda$onAvailableCommandsChangedFromPlayer$112$androidxmedia3sessionMediaControllerImplBase(MediaController.Listener listener) {
        listener.onCustomLayoutChanged(getInstance(), this.resolvedMediaButtonPreferences);
        listener.onMediaButtonPreferencesChanged(getInstance(), this.resolvedMediaButtonPreferences);
    }

    /* access modifiers changed from: package-private */
    public void onSetCustomLayout(int i, List<CommandButton> list) {
        if (isConnected()) {
            ImmutableList<CommandButton> immutableList = this.resolvedMediaButtonPreferences;
            this.customLayoutOriginal = ImmutableList.copyOf(list);
            ImmutableList<CommandButton> resolveMediaButtonPreferences = resolveMediaButtonPreferences(this.mediaButtonPreferencesOriginal, list, this.sessionCommands, this.intersectedPlayerCommands);
            this.resolvedMediaButtonPreferences = resolveMediaButtonPreferences;
            getInstance().notifyControllerListener(new MediaControllerImplBase$$ExternalSyntheticLambda89(this, !Objects.equals(resolveMediaButtonPreferences, immutableList), i));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onSetCustomLayout$113$androidx-media3-session-MediaControllerImplBase  reason: not valid java name */
    public /* synthetic */ void m951lambda$onSetCustomLayout$113$androidxmedia3sessionMediaControllerImplBase(boolean z, int i, MediaController.Listener listener) {
        ListenableFuture listenableFuture = (ListenableFuture) Assertions.checkNotNull(listener.onSetCustomLayout(getInstance(), this.resolvedMediaButtonPreferences), "MediaController.Listener#onSetCustomLayout() must not return null");
        if (z) {
            listener.onCustomLayoutChanged(getInstance(), this.resolvedMediaButtonPreferences);
            listener.onMediaButtonPreferencesChanged(getInstance(), this.resolvedMediaButtonPreferences);
        }
        sendControllerResultWhenReady(i, listenableFuture);
    }

    /* access modifiers changed from: package-private */
    public void onSetMediaButtonPreferences(int i, List<CommandButton> list) {
        if (isConnected()) {
            ImmutableList<CommandButton> immutableList = this.resolvedMediaButtonPreferences;
            this.mediaButtonPreferencesOriginal = ImmutableList.copyOf(list);
            ImmutableList<CommandButton> resolveMediaButtonPreferences = resolveMediaButtonPreferences(list, this.customLayoutOriginal, this.sessionCommands, this.intersectedPlayerCommands);
            this.resolvedMediaButtonPreferences = resolveMediaButtonPreferences;
            getInstance().notifyControllerListener(new MediaControllerImplBase$$ExternalSyntheticLambda37(this, !Objects.equals(resolveMediaButtonPreferences, immutableList), i));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onSetMediaButtonPreferences$114$androidx-media3-session-MediaControllerImplBase  reason: not valid java name */
    public /* synthetic */ void m952lambda$onSetMediaButtonPreferences$114$androidxmedia3sessionMediaControllerImplBase(boolean z, int i, MediaController.Listener listener) {
        ListenableFuture listenableFuture = (ListenableFuture) Assertions.checkNotNull(listener.onSetCustomLayout(getInstance(), this.resolvedMediaButtonPreferences), "MediaController.Listener#onSetCustomLayout() must not return null");
        if (z) {
            listener.onCustomLayoutChanged(getInstance(), this.resolvedMediaButtonPreferences);
            listener.onMediaButtonPreferencesChanged(getInstance(), this.resolvedMediaButtonPreferences);
        }
        sendControllerResultWhenReady(i, listenableFuture);
    }

    public void onExtrasChanged(Bundle bundle) {
        if (isConnected()) {
            this.sessionExtras = bundle;
            getInstance().notifyControllerListener(new MediaControllerImplBase$$ExternalSyntheticLambda109(this, bundle));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onExtrasChanged$115$androidx-media3-session-MediaControllerImplBase  reason: not valid java name */
    public /* synthetic */ void m950lambda$onExtrasChanged$115$androidxmedia3sessionMediaControllerImplBase(Bundle bundle, MediaController.Listener listener) {
        listener.onExtrasChanged(getInstance(), bundle);
    }

    public void onSetSessionActivity(int i, PendingIntent pendingIntent) {
        if (isConnected()) {
            this.sessionActivity = pendingIntent;
            getInstance().notifyControllerListener(new MediaControllerImplBase$$ExternalSyntheticLambda19(this, pendingIntent));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onSetSessionActivity$116$androidx-media3-session-MediaControllerImplBase  reason: not valid java name */
    public /* synthetic */ void m953lambda$onSetSessionActivity$116$androidxmedia3sessionMediaControllerImplBase(PendingIntent pendingIntent, MediaController.Listener listener) {
        listener.onSessionActivityChanged(getInstance(), pendingIntent);
    }

    public void onError(int i, SessionError sessionError) {
        if (isConnected()) {
            getInstance().notifyControllerListener(new MediaControllerImplBase$$ExternalSyntheticLambda26(this, sessionError));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onError$117$androidx-media3-session-MediaControllerImplBase  reason: not valid java name */
    public /* synthetic */ void m949lambda$onError$117$androidxmedia3sessionMediaControllerImplBase(SessionError sessionError, MediaController.Listener listener) {
        listener.onError(getInstance(), sessionError);
    }

    public void onRenderedFirstFrame() {
        this.listeners.sendEvent(26, new SimpleBasePlayer$$ExternalSyntheticLambda23());
    }

    private void updateSessionPositionInfoIfNeeded(SessionPositionInfo sessionPositionInfo) {
        if (this.pendingMaskingSequencedFutureNumbers.isEmpty() && this.playerInfo.sessionPositionInfo.eventTimeMs < sessionPositionInfo.eventTimeMs && MediaUtils.areSessionPositionInfosInSamePeriodOrAd(sessionPositionInfo, this.playerInfo.sessionPositionInfo)) {
            this.playerInfo = this.playerInfo.copyWithSessionPositionInfo(sessionPositionInfo);
        }
    }

    private boolean isPlayerCommandAvailable(int i) {
        if (this.intersectedPlayerCommands.contains(i)) {
            return true;
        }
        Log.w(TAG, "Controller isn't allowed to call command= " + i);
        return false;
    }

    private PlayerInfo maskPositionInfo(PlayerInfo playerInfo2, Timeline timeline, PeriodInfo periodInfo) {
        PlayerInfo playerInfo3 = playerInfo2;
        Timeline timeline2 = timeline;
        int i = playerInfo3.sessionPositionInfo.positionInfo.periodIndex;
        int access$100 = periodInfo.index;
        Timeline.Period period = new Timeline.Period();
        timeline2.getPeriod(i, period);
        Timeline.Period period2 = new Timeline.Period();
        timeline2.getPeriod(access$100, period2);
        boolean z = false;
        boolean z2 = i != access$100;
        long access$200 = periodInfo.periodPositionUs;
        long msToUs = Util.msToUs(getCurrentPosition()) - period.getPositionInWindowUs();
        if (!z2 && access$200 == msToUs) {
            return playerInfo3;
        }
        if (playerInfo3.sessionPositionInfo.positionInfo.adGroupIndex == -1) {
            z = true;
        }
        Assertions.checkState(z);
        Player.PositionInfo positionInfo = r3;
        Player.PositionInfo positionInfo2 = new Player.PositionInfo((Object) null, period.windowIndex, playerInfo3.sessionPositionInfo.positionInfo.mediaItem, (Object) null, i, Util.usToMs(period.positionInWindowUs + msToUs), Util.usToMs(period.positionInWindowUs + msToUs), -1, -1);
        timeline2.getPeriod(access$100, period2);
        Timeline.Window window = new Timeline.Window();
        timeline2.getWindow(period2.windowIndex, window);
        int i2 = access$100;
        Timeline.Period period3 = period2;
        Player.PositionInfo positionInfo3 = new Player.PositionInfo((Object) null, period2.windowIndex, window.mediaItem, (Object) null, i2, Util.usToMs(period2.positionInWindowUs + access$200), Util.usToMs(period2.positionInWindowUs + access$200), -1, -1);
        PlayerInfo copyWithPositionInfos = playerInfo3.copyWithPositionInfos(positionInfo, positionInfo3, 1);
        if (z2 || access$200 < msToUs) {
            return copyWithPositionInfos.copyWithSessionPositionInfo(new SessionPositionInfo(positionInfo3, false, SystemClock.elapsedRealtime(), window.getDurationMs(), Util.usToMs(period3.positionInWindowUs + access$200), MediaUtils.calculateBufferedPercentage(Util.usToMs(period3.positionInWindowUs + access$200), window.getDurationMs()), 0, C.TIME_UNSET, C.TIME_UNSET, Util.usToMs(period3.positionInWindowUs + access$200)));
        }
        long max = Math.max(0, Util.msToUs(copyWithPositionInfos.sessionPositionInfo.totalBufferedDurationMs) - (access$200 - msToUs));
        long j = access$200 + max;
        return copyWithPositionInfos.copyWithSessionPositionInfo(new SessionPositionInfo(positionInfo3, false, SystemClock.elapsedRealtime(), window.getDurationMs(), Util.usToMs(j), MediaUtils.calculateBufferedPercentage(Util.usToMs(j), window.getDurationMs()), Util.usToMs(max), C.TIME_UNSET, C.TIME_UNSET, Util.usToMs(j)));
    }

    private PeriodInfo getPeriodInfo(Timeline timeline, int i, long j) {
        if (timeline.isEmpty()) {
            return null;
        }
        Timeline.Window window = new Timeline.Window();
        Timeline.Period period = new Timeline.Period();
        if (i == -1 || i >= timeline.getWindowCount()) {
            i = timeline.getFirstWindowIndex(getShuffleModeEnabled());
            j = timeline.getWindow(i, window).getDefaultPositionMs();
        }
        return getPeriodInfo(timeline, window, period, i, Util.msToUs(j));
    }

    private static PeriodInfo getPeriodInfo(Timeline timeline, Timeline.Window window, Timeline.Period period, int i, long j) {
        Assertions.checkIndex(i, 0, timeline.getWindowCount());
        timeline.getWindow(i, window);
        if (j == C.TIME_UNSET) {
            j = window.getDefaultPositionUs();
            if (j == C.TIME_UNSET) {
                return null;
            }
        }
        int i2 = window.firstPeriodIndex;
        timeline.getPeriod(i2, period);
        while (i2 < window.lastPeriodIndex && period.positionInWindowUs != j) {
            int i3 = i2 + 1;
            if (timeline.getPeriod(i3, period).positionInWindowUs > j) {
                break;
            }
            i2 = i3;
        }
        timeline.getPeriod(i2, period);
        return new PeriodInfo(i2, j - period.positionInWindowUs);
    }

    private static int getCurrentMediaItemIndexInternal(PlayerInfo playerInfo2) {
        if (playerInfo2.sessionPositionInfo.positionInfo.mediaItemIndex == -1) {
            return 0;
        }
        return playerInfo2.sessionPositionInfo.positionInfo.mediaItemIndex;
    }

    private static PlayerInfo maskTimelineAndPositionInfo(PlayerInfo playerInfo2, Timeline timeline, int i, int i2, long j, long j2, int i3) {
        PlayerInfo playerInfo3 = playerInfo2;
        Timeline timeline2 = timeline;
        Player.PositionInfo positionInfo = r2;
        int i4 = i;
        Player.PositionInfo positionInfo2 = new Player.PositionInfo((Object) null, i4, timeline2.getWindow(i4, new Timeline.Window()).mediaItem, (Object) null, i2, j, j2, playerInfo3.sessionPositionInfo.positionInfo.adGroupIndex, playerInfo3.sessionPositionInfo.positionInfo.adIndexInAdGroup);
        return maskTimelineAndPositionInfo(playerInfo3, timeline2, positionInfo2, new SessionPositionInfo(positionInfo, playerInfo3.sessionPositionInfo.isPlayingAd, SystemClock.elapsedRealtime(), playerInfo3.sessionPositionInfo.durationMs, playerInfo3.sessionPositionInfo.bufferedPositionMs, playerInfo3.sessionPositionInfo.bufferedPercentage, playerInfo3.sessionPositionInfo.totalBufferedDurationMs, playerInfo3.sessionPositionInfo.currentLiveOffsetMs, playerInfo3.sessionPositionInfo.contentDurationMs, playerInfo3.sessionPositionInfo.contentBufferedPositionMs), i3);
    }

    private static PlayerInfo maskTimelineAndPositionInfo(PlayerInfo playerInfo2, Timeline timeline, Player.PositionInfo positionInfo, SessionPositionInfo sessionPositionInfo, int i) {
        return new PlayerInfo.Builder(playerInfo2).setTimeline(timeline).setOldPositionInfo(playerInfo2.sessionPositionInfo.positionInfo).setNewPositionInfo(positionInfo).setSessionPositionInfo(sessionPositionInfo).setDiscontinuityReason(i).build();
    }

    private static Timeline.Period getPeriodWithNewWindowIndex(Timeline timeline, int i, int i2) {
        Timeline.Period period = new Timeline.Period();
        timeline.getPeriod(i, period);
        period.windowIndex = i2;
        return period;
    }

    private static int getNewPeriodIndexWithoutRemovedPeriods(Timeline timeline, int i, int i2, int i3) {
        if (i == -1) {
            return i;
        }
        while (i2 < i3) {
            Timeline.Window window = new Timeline.Window();
            timeline.getWindow(i2, window);
            i -= (window.lastPeriodIndex - window.firstPeriodIndex) + 1;
            i2++;
        }
        return i;
    }

    private static Timeline.Window createNewWindow(MediaItem mediaItem) {
        Timeline.Window window = r1;
        Timeline.Window window2 = new Timeline.Window();
        return window.set(0, mediaItem, (Object) null, 0, 0, 0, true, false, (MediaItem.LiveConfiguration) null, 0, C.TIME_UNSET, -1, -1, 0);
    }

    private static Timeline.Period createNewPeriod(int i) {
        return new Timeline.Period().set((Object) null, (Object) null, i, C.TIME_UNSET, 0, AdPlaybackState.NONE, true);
    }

    private static void rebuildPeriods(Timeline timeline, List<Timeline.Window> list, List<Timeline.Period> list2) {
        for (int i = 0; i < list.size(); i++) {
            Timeline.Window window = list.get(i);
            int i2 = window.firstPeriodIndex;
            int i3 = window.lastPeriodIndex;
            if (i2 == -1 || i3 == -1) {
                window.firstPeriodIndex = list2.size();
                window.lastPeriodIndex = list2.size();
                list2.add(createNewPeriod(i));
            } else {
                window.firstPeriodIndex = list2.size();
                window.lastPeriodIndex = list2.size() + (i3 - i2);
                while (i2 <= i3) {
                    list2.add(getPeriodWithNewWindowIndex(timeline, i2, i));
                    i2++;
                }
            }
        }
    }

    private static int resolveSubsequentMediaItemIndex(int i, boolean z, int i2, Timeline timeline, int i3, int i4) {
        int windowCount = timeline.getWindowCount();
        for (int i5 = 0; i5 < windowCount && (i2 = timeline.getNextWindowIndex(i2, i, z)) != -1; i5++) {
            if (i2 < i3 || i2 >= i4) {
                return i2;
            }
        }
        return -1;
    }

    private static ImmutableList<CommandButton> resolveMediaButtonPreferences(List<CommandButton> list, List<CommandButton> list2, SessionCommands sessionCommands2, Player.Commands commands) {
        if (list.isEmpty()) {
            list = list2;
        }
        return CommandButton.copyWithUnavailableButtonsDisabled(list, sessionCommands2, commands);
    }

    private static Player.Commands createIntersectedCommandsEnsuringCommandReleaseAvailable(Player.Commands commands, Player.Commands commands2) {
        Player.Commands intersect = MediaUtils.intersect(commands, commands2);
        return intersect.contains(32) ? intersect : intersect.buildUpon().add(32).build();
    }

    private class SessionServiceConnection implements ServiceConnection {
        private final Bundle connectionHints;

        public SessionServiceConnection(Bundle bundle) {
            this.connectionHints = bundle;
        }

        /* JADX WARNING: Can't wrap try/catch for region: R(6:13|14|16|17|18|21) */
        /* JADX WARNING: Code restructure failed: missing block: B:15:0x00a4, code lost:
            r8 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:0x00d4, code lost:
            r9 = r7.this$0.getInstance();
            r0 = r7.this$0.getInstance();
            java.util.Objects.requireNonNull(r0);
            r9.runOnApplicationLooper(new androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda46(r0));
         */
        /* JADX WARNING: Code restructure failed: missing block: B:20:0x00eb, code lost:
            throw r8;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:16:0x00a6 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onServiceConnected(android.content.ComponentName r8, android.os.IBinder r9) {
            /*
                r7 = this;
                java.lang.String r0 = "MCImplBase"
                java.lang.String r1 = "Expected connection to "
                java.lang.String r2 = "Service "
                androidx.media3.session.MediaControllerImplBase r3 = androidx.media3.session.MediaControllerImplBase.this     // Catch:{ RemoteException -> 0x00a6 }
                androidx.media3.session.SessionToken r3 = r3.token     // Catch:{ RemoteException -> 0x00a6 }
                java.lang.String r3 = r3.getPackageName()     // Catch:{ RemoteException -> 0x00a6 }
                java.lang.String r4 = r8.getPackageName()     // Catch:{ RemoteException -> 0x00a6 }
                boolean r3 = r3.equals(r4)     // Catch:{ RemoteException -> 0x00a6 }
                if (r3 != 0) goto L_0x0056
                java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ RemoteException -> 0x00a6 }
                r9.<init>(r1)     // Catch:{ RemoteException -> 0x00a6 }
                androidx.media3.session.MediaControllerImplBase r1 = androidx.media3.session.MediaControllerImplBase.this     // Catch:{ RemoteException -> 0x00a6 }
                androidx.media3.session.SessionToken r1 = r1.token     // Catch:{ RemoteException -> 0x00a6 }
                java.lang.String r1 = r1.getPackageName()     // Catch:{ RemoteException -> 0x00a6 }
                java.lang.StringBuilder r9 = r9.append(r1)     // Catch:{ RemoteException -> 0x00a6 }
                java.lang.String r1 = " but is connected to "
                java.lang.StringBuilder r9 = r9.append(r1)     // Catch:{ RemoteException -> 0x00a6 }
                java.lang.StringBuilder r9 = r9.append(r8)     // Catch:{ RemoteException -> 0x00a6 }
                java.lang.String r9 = r9.toString()     // Catch:{ RemoteException -> 0x00a6 }
                androidx.media3.common.util.Log.e(r0, r9)     // Catch:{ RemoteException -> 0x00a6 }
                androidx.media3.session.MediaControllerImplBase r8 = androidx.media3.session.MediaControllerImplBase.this
                androidx.media3.session.MediaController r8 = r8.getInstance()
                androidx.media3.session.MediaControllerImplBase r9 = androidx.media3.session.MediaControllerImplBase.this
                androidx.media3.session.MediaController r9 = r9.getInstance()
                java.util.Objects.requireNonNull(r9)
                androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda46 r0 = new androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda46
                r0.<init>(r9)
                r8.runOnApplicationLooper(r0)
                return
            L_0x0056:
                androidx.media3.session.IMediaSessionService r9 = androidx.media3.session.IMediaSessionService.Stub.asInterface(r9)     // Catch:{ RemoteException -> 0x00a6 }
                if (r9 != 0) goto L_0x0079
                java.lang.String r9 = "Service interface is missing."
                androidx.media3.common.util.Log.e(r0, r9)     // Catch:{ RemoteException -> 0x00a6 }
                androidx.media3.session.MediaControllerImplBase r8 = androidx.media3.session.MediaControllerImplBase.this
                androidx.media3.session.MediaController r8 = r8.getInstance()
                androidx.media3.session.MediaControllerImplBase r9 = androidx.media3.session.MediaControllerImplBase.this
                androidx.media3.session.MediaController r9 = r9.getInstance()
                java.util.Objects.requireNonNull(r9)
                androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda46 r0 = new androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda46
                r0.<init>(r9)
                r8.runOnApplicationLooper(r0)
                return
            L_0x0079:
                androidx.media3.session.ConnectionRequest r1 = new androidx.media3.session.ConnectionRequest     // Catch:{ RemoteException -> 0x00a6 }
                androidx.media3.session.MediaControllerImplBase r3 = androidx.media3.session.MediaControllerImplBase.this     // Catch:{ RemoteException -> 0x00a6 }
                android.content.Context r3 = r3.getContext()     // Catch:{ RemoteException -> 0x00a6 }
                java.lang.String r3 = r3.getPackageName()     // Catch:{ RemoteException -> 0x00a6 }
                int r4 = android.os.Process.myPid()     // Catch:{ RemoteException -> 0x00a6 }
                android.os.Bundle r5 = r7.connectionHints     // Catch:{ RemoteException -> 0x00a6 }
                androidx.media3.session.MediaControllerImplBase r6 = androidx.media3.session.MediaControllerImplBase.this     // Catch:{ RemoteException -> 0x00a6 }
                androidx.media3.session.MediaController r6 = r6.instance     // Catch:{ RemoteException -> 0x00a6 }
                int r6 = r6.getMaxCommandsForMediaItems()     // Catch:{ RemoteException -> 0x00a6 }
                r1.<init>(r3, r4, r5, r6)     // Catch:{ RemoteException -> 0x00a6 }
                androidx.media3.session.MediaControllerImplBase r3 = androidx.media3.session.MediaControllerImplBase.this     // Catch:{ RemoteException -> 0x00a6 }
                androidx.media3.session.MediaControllerStub r3 = r3.controllerStub     // Catch:{ RemoteException -> 0x00a6 }
                android.os.Bundle r1 = r1.toBundle()     // Catch:{ RemoteException -> 0x00a6 }
                r9.connect(r3, r1)     // Catch:{ RemoteException -> 0x00a6 }
                goto L_0x00d3
            L_0x00a4:
                r8 = move-exception
                goto L_0x00d4
            L_0x00a6:
                java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ all -> 0x00a4 }
                r9.<init>(r2)     // Catch:{ all -> 0x00a4 }
                java.lang.StringBuilder r8 = r9.append(r8)     // Catch:{ all -> 0x00a4 }
                java.lang.String r9 = " has died prematurely"
                java.lang.StringBuilder r8 = r8.append(r9)     // Catch:{ all -> 0x00a4 }
                java.lang.String r8 = r8.toString()     // Catch:{ all -> 0x00a4 }
                androidx.media3.common.util.Log.w(r0, r8)     // Catch:{ all -> 0x00a4 }
                androidx.media3.session.MediaControllerImplBase r8 = androidx.media3.session.MediaControllerImplBase.this
                androidx.media3.session.MediaController r8 = r8.getInstance()
                androidx.media3.session.MediaControllerImplBase r9 = androidx.media3.session.MediaControllerImplBase.this
                androidx.media3.session.MediaController r9 = r9.getInstance()
                java.util.Objects.requireNonNull(r9)
                androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda46 r0 = new androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda46
                r0.<init>(r9)
                r8.runOnApplicationLooper(r0)
            L_0x00d3:
                return
            L_0x00d4:
                androidx.media3.session.MediaControllerImplBase r9 = androidx.media3.session.MediaControllerImplBase.this
                androidx.media3.session.MediaController r9 = r9.getInstance()
                androidx.media3.session.MediaControllerImplBase r0 = androidx.media3.session.MediaControllerImplBase.this
                androidx.media3.session.MediaController r0 = r0.getInstance()
                java.util.Objects.requireNonNull(r0)
                androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda46 r1 = new androidx.media3.session.MediaControllerImplBase$$ExternalSyntheticLambda46
                r1.<init>(r0)
                r9.runOnApplicationLooper(r1)
                throw r8
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.media3.session.MediaControllerImplBase.SessionServiceConnection.onServiceConnected(android.content.ComponentName, android.os.IBinder):void");
        }

        public void onServiceDisconnected(ComponentName componentName) {
            MediaController instance = MediaControllerImplBase.this.getInstance();
            MediaController instance2 = MediaControllerImplBase.this.getInstance();
            Objects.requireNonNull(instance2);
            instance.runOnApplicationLooper(new MediaControllerImplBase$$ExternalSyntheticLambda46(instance2));
        }

        public void onBindingDied(ComponentName componentName) {
            MediaController instance = MediaControllerImplBase.this.getInstance();
            MediaController instance2 = MediaControllerImplBase.this.getInstance();
            Objects.requireNonNull(instance2);
            instance.runOnApplicationLooper(new MediaControllerImplBase$$ExternalSyntheticLambda46(instance2));
        }
    }

    private class SurfaceCallback implements SurfaceHolder.Callback, TextureView.SurfaceTextureListener {
        public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        }

        private SurfaceCallback() {
        }

        public void surfaceCreated(SurfaceHolder surfaceHolder) {
            if (MediaControllerImplBase.this.videoSurfaceHolder == surfaceHolder) {
                Surface unused = MediaControllerImplBase.this.videoSurface = surfaceHolder.getSurface();
                MediaControllerImplBase.this.dispatchRemoteSessionTaskWithPlayerCommandAndWaitForFuture(new MediaControllerImplBase$SurfaceCallback$$ExternalSyntheticLambda3(this));
                Rect surfaceFrame = surfaceHolder.getSurfaceFrame();
                MediaControllerImplBase.this.maybeNotifySurfaceSizeChanged(surfaceFrame.width(), surfaceFrame.height());
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$surfaceCreated$0$androidx-media3-session-MediaControllerImplBase$SurfaceCallback  reason: not valid java name */
        public /* synthetic */ void m1008lambda$surfaceCreated$0$androidxmedia3sessionMediaControllerImplBase$SurfaceCallback(IMediaSession iMediaSession, int i) throws RemoteException {
            iMediaSession.setVideoSurface(MediaControllerImplBase.this.controllerStub, i, MediaControllerImplBase.this.videoSurface);
        }

        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
            if (MediaControllerImplBase.this.videoSurfaceHolder == surfaceHolder) {
                MediaControllerImplBase.this.maybeNotifySurfaceSizeChanged(i2, i3);
            }
        }

        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            if (MediaControllerImplBase.this.videoSurfaceHolder == surfaceHolder) {
                Surface unused = MediaControllerImplBase.this.videoSurface = null;
                MediaControllerImplBase.this.dispatchRemoteSessionTaskWithPlayerCommandAndWaitForFuture(new MediaControllerImplBase$SurfaceCallback$$ExternalSyntheticLambda1(this));
                MediaControllerImplBase.this.maybeNotifySurfaceSizeChanged(0, 0);
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$surfaceDestroyed$1$androidx-media3-session-MediaControllerImplBase$SurfaceCallback  reason: not valid java name */
        public /* synthetic */ void m1009lambda$surfaceDestroyed$1$androidxmedia3sessionMediaControllerImplBase$SurfaceCallback(IMediaSession iMediaSession, int i) throws RemoteException {
            iMediaSession.setVideoSurface(MediaControllerImplBase.this.controllerStub, i, (Surface) null);
        }

        public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
            if (MediaControllerImplBase.this.videoTextureView != null && MediaControllerImplBase.this.videoTextureView.getSurfaceTexture() == surfaceTexture) {
                Surface unused = MediaControllerImplBase.this.videoSurface = new Surface(surfaceTexture);
                MediaControllerImplBase.this.dispatchRemoteSessionTaskWithPlayerCommandAndWaitForFuture(new MediaControllerImplBase$SurfaceCallback$$ExternalSyntheticLambda0(this));
                MediaControllerImplBase.this.maybeNotifySurfaceSizeChanged(i, i2);
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onSurfaceTextureAvailable$2$androidx-media3-session-MediaControllerImplBase$SurfaceCallback  reason: not valid java name */
        public /* synthetic */ void m1006lambda$onSurfaceTextureAvailable$2$androidxmedia3sessionMediaControllerImplBase$SurfaceCallback(IMediaSession iMediaSession, int i) throws RemoteException {
            iMediaSession.setVideoSurface(MediaControllerImplBase.this.controllerStub, i, MediaControllerImplBase.this.videoSurface);
        }

        public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
            if (MediaControllerImplBase.this.videoTextureView != null && MediaControllerImplBase.this.videoTextureView.getSurfaceTexture() == surfaceTexture) {
                MediaControllerImplBase.this.maybeNotifySurfaceSizeChanged(i, i2);
            }
        }

        public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
            if (MediaControllerImplBase.this.videoTextureView != null && MediaControllerImplBase.this.videoTextureView.getSurfaceTexture() == surfaceTexture) {
                Surface unused = MediaControllerImplBase.this.videoSurface = null;
                MediaControllerImplBase.this.dispatchRemoteSessionTaskWithPlayerCommandAndWaitForFuture(new MediaControllerImplBase$SurfaceCallback$$ExternalSyntheticLambda2(this));
                MediaControllerImplBase.this.maybeNotifySurfaceSizeChanged(0, 0);
            }
            return true;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onSurfaceTextureDestroyed$3$androidx-media3-session-MediaControllerImplBase$SurfaceCallback  reason: not valid java name */
        public /* synthetic */ void m1007lambda$onSurfaceTextureDestroyed$3$androidxmedia3sessionMediaControllerImplBase$SurfaceCallback(IMediaSession iMediaSession, int i) throws RemoteException {
            iMediaSession.setVideoSurface(MediaControllerImplBase.this.controllerStub, i, (Surface) null);
        }
    }

    private class FlushCommandQueueHandler {
        private static final int MSG_FLUSH_COMMAND_QUEUE = 1;
        private final Handler handler;

        public FlushCommandQueueHandler(Looper looper) {
            this.handler = new Handler(looper, new MediaControllerImplBase$FlushCommandQueueHandler$$ExternalSyntheticLambda0(this));
        }

        public void sendFlushCommandQueueMessage() {
            if (MediaControllerImplBase.this.iSession != null && !this.handler.hasMessages(1)) {
                this.handler.sendEmptyMessage(1);
            }
        }

        public void release() {
            if (this.handler.hasMessages(1)) {
                flushCommandQueue();
            }
            this.handler.removeCallbacksAndMessages((Object) null);
        }

        /* access modifiers changed from: private */
        public boolean handleMessage(Message message) {
            if (message.what == 1) {
                flushCommandQueue();
            }
            return true;
        }

        private void flushCommandQueue() {
            try {
                MediaControllerImplBase.this.iSession.flushCommandQueue(MediaControllerImplBase.this.controllerStub);
            } catch (RemoteException unused) {
                Log.w(MediaControllerImplBase.TAG, "Error in sending flushCommandQueue");
            }
        }
    }

    private static final class PeriodInfo {
        /* access modifiers changed from: private */
        public final int index;
        /* access modifiers changed from: private */
        public final long periodPositionUs;

        public PeriodInfo(int i, long j) {
            this.index = i;
            this.periodPositionUs = j;
        }
    }
}
