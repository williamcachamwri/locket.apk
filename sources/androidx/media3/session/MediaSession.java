package androidx.media3.session;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.session.MediaSession;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.support.v4.media.session.MediaSessionCompat;
import androidx.media.MediaSessionManager;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.DeviceInfo;
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
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.BitmapLoader;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSourceBitmapLoader;
import androidx.media3.session.MediaLibraryService;
import androidx.media3.session.SessionCommands;
import androidx.media3.session.legacy.LegacyParcelableUtil;
import androidx.media3.session.legacy.MediaSessionCompat;
import androidx.media3.session.legacy.MediaSessionManager;
import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;
import com.google.common.primitives.Longs;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.errorprone.annotations.DoNotMock;
import java.util.HashMap;
import java.util.List;

@DoNotMock
public class MediaSession {
    private static final HashMap<String, MediaSession> SESSION_ID_TO_SESSION_MAP = new HashMap<>();
    private static final Object STATIC_LOCK = new Object();
    private final MediaSessionImpl impl;

    interface ControllerCb {
        void onAudioAttributesChanged(int i, AudioAttributes audioAttributes) throws RemoteException {
        }

        void onAvailableCommandsChangedFromPlayer(int i, Player.Commands commands) throws RemoteException {
        }

        void onAvailableCommandsChangedFromSession(int i, SessionCommands sessionCommands, Player.Commands commands) throws RemoteException {
        }

        void onChildrenChanged(int i, String str, int i2, MediaLibraryService.LibraryParams libraryParams) throws RemoteException {
        }

        void onDeviceInfoChanged(int i, DeviceInfo deviceInfo) throws RemoteException {
        }

        void onDeviceVolumeChanged(int i, int i2, boolean z) throws RemoteException {
        }

        void onDisconnected(int i) throws RemoteException {
        }

        void onError(int i, SessionError sessionError) throws RemoteException {
        }

        void onIsLoadingChanged(int i, boolean z) throws RemoteException {
        }

        void onIsPlayingChanged(int i, boolean z) throws RemoteException {
        }

        void onLibraryResult(int i, LibraryResult<?> libraryResult) throws RemoteException {
        }

        void onMediaItemTransition(int i, MediaItem mediaItem, int i2) throws RemoteException {
        }

        void onMediaMetadataChanged(int i, MediaMetadata mediaMetadata) throws RemoteException {
        }

        void onPeriodicSessionPositionInfoChanged(int i, SessionPositionInfo sessionPositionInfo, boolean z, boolean z2, int i2) throws RemoteException {
        }

        void onPlayWhenReadyChanged(int i, boolean z, int i2) throws RemoteException {
        }

        void onPlaybackParametersChanged(int i, PlaybackParameters playbackParameters) throws RemoteException {
        }

        void onPlaybackStateChanged(int i, int i2, PlaybackException playbackException) throws RemoteException {
        }

        void onPlaybackSuppressionReasonChanged(int i, int i2) throws RemoteException {
        }

        void onPlayerChanged(int i, PlayerWrapper playerWrapper, PlayerWrapper playerWrapper2) throws RemoteException {
        }

        void onPlayerError(int i, PlaybackException playbackException) throws RemoteException {
        }

        void onPlayerInfoChanged(int i, PlayerInfo playerInfo, Player.Commands commands, boolean z, boolean z2) throws RemoteException {
        }

        void onPlaylistMetadataChanged(int i, MediaMetadata mediaMetadata) throws RemoteException {
        }

        void onPositionDiscontinuity(int i, Player.PositionInfo positionInfo, Player.PositionInfo positionInfo2, int i2) throws RemoteException {
        }

        void onRenderedFirstFrame(int i) throws RemoteException {
        }

        void onRepeatModeChanged(int i, int i2) throws RemoteException {
        }

        void onSearchResultChanged(int i, String str, int i2, MediaLibraryService.LibraryParams libraryParams) throws RemoteException {
        }

        void onSeekBackIncrementChanged(int i, long j) throws RemoteException {
        }

        void onSeekForwardIncrementChanged(int i, long j) throws RemoteException {
        }

        void onSessionActivityChanged(int i, PendingIntent pendingIntent) throws RemoteException {
        }

        void onSessionExtrasChanged(int i, Bundle bundle) throws RemoteException {
        }

        void onSessionResult(int i, SessionResult sessionResult) throws RemoteException {
        }

        void onShuffleModeEnabledChanged(int i, boolean z) throws RemoteException {
        }

        void onTimelineChanged(int i, Timeline timeline, int i2) throws RemoteException {
        }

        void onTrackSelectionParametersChanged(int i, TrackSelectionParameters trackSelectionParameters) throws RemoteException {
        }

        void onTracksChanged(int i, Tracks tracks) throws RemoteException {
        }

        void onVideoSizeChanged(int i, VideoSize videoSize) throws RemoteException {
        }

        void onVolumeChanged(int i, float f) throws RemoteException {
        }

        void sendCustomCommand(int i, SessionCommand sessionCommand, Bundle bundle) throws RemoteException {
        }

        void setCustomLayout(int i, List<CommandButton> list) throws RemoteException {
        }

        void setMediaButtonPreferences(int i, List<CommandButton> list) throws RemoteException {
        }
    }

    interface Listener {
        void onNotificationRefreshRequired(MediaSession mediaSession);

        boolean onPlayRequested(MediaSession mediaSession);
    }

    public static final class Builder extends BuilderBase<MediaSession, Builder, Callback> {
        public Builder(Context context, Player player) {
            super(context, player, new Callback() {
            });
        }

        public Builder setSessionActivity(PendingIntent pendingIntent) {
            return (Builder) super.setSessionActivity(pendingIntent);
        }

        public Builder setId(String str) {
            return (Builder) super.setId(str);
        }

        public Builder setCallback(Callback callback) {
            return (Builder) super.setCallback(callback);
        }

        public Builder setExtras(Bundle bundle) {
            return (Builder) super.setExtras(bundle);
        }

        public Builder setSessionExtras(Bundle bundle) {
            return (Builder) super.setSessionExtras(bundle);
        }

        public Builder setBitmapLoader(BitmapLoader bitmapLoader) {
            return (Builder) super.setBitmapLoader(bitmapLoader);
        }

        public Builder setCustomLayout(List<CommandButton> list) {
            return (Builder) super.setCustomLayout(list);
        }

        public Builder setMediaButtonPreferences(List<CommandButton> list) {
            return (Builder) super.setMediaButtonPreferences(list);
        }

        public Builder setPeriodicPositionUpdateEnabled(boolean z) {
            return (Builder) super.setPeriodicPositionUpdateEnabled(z);
        }

        public Builder setShowPlayButtonIfPlaybackIsSuppressed(boolean z) {
            return (Builder) super.setShowPlayButtonIfPlaybackIsSuppressed(z);
        }

        public Builder setCommandButtonsForMediaItems(List<CommandButton> list) {
            return (Builder) super.setCommandButtonsForMediaItems(list);
        }

        public MediaSession build() {
            if (this.bitmapLoader == null) {
                this.bitmapLoader = new CacheBitmapLoader(new DataSourceBitmapLoader(this.context));
            }
            return new MediaSession(this.context, this.id, this.player, this.sessionActivity, this.customLayout, this.mediaButtonPreferences, this.commandButtonsForMediaItems, this.callback, this.tokenExtras, this.sessionExtras, (BitmapLoader) Assertions.checkNotNull(this.bitmapLoader), this.playIfSuppressed, this.isPeriodicPositionUpdateEnabled, 0);
        }
    }

    public static final class ControllerInfo {
        public static final int LEGACY_CONTROLLER_INTERFACE_VERSION = 0;
        public static final String LEGACY_CONTROLLER_PACKAGE_NAME = "android.media.session.MediaController";
        public static final int LEGACY_CONTROLLER_VERSION = 0;
        private final Bundle connectionHints;
        private final ControllerCb controllerCb;
        private final int interfaceVersion;
        private final boolean isTrusted;
        private final int libraryVersion;
        private final int maxCommandsForMediaItems;
        private final MediaSessionManager.RemoteUserInfo remoteUserInfo;

        ControllerInfo(MediaSessionManager.RemoteUserInfo remoteUserInfo2, int i, int i2, boolean z, ControllerCb controllerCb2, Bundle bundle, int i3) {
            this.remoteUserInfo = remoteUserInfo2;
            this.libraryVersion = i;
            this.interfaceVersion = i2;
            this.isTrusted = z;
            this.controllerCb = controllerCb2;
            this.connectionHints = bundle;
            this.maxCommandsForMediaItems = i3;
        }

        /* access modifiers changed from: package-private */
        public MediaSessionManager.RemoteUserInfo getRemoteUserInfo() {
            return this.remoteUserInfo;
        }

        public int getControllerVersion() {
            return this.libraryVersion;
        }

        public int getInterfaceVersion() {
            return this.interfaceVersion;
        }

        public String getPackageName() {
            return this.remoteUserInfo.getPackageName();
        }

        public int getUid() {
            return this.remoteUserInfo.getUid();
        }

        public Bundle getConnectionHints() {
            return new Bundle(this.connectionHints);
        }

        public int getMaxCommandsForMediaItems() {
            return this.maxCommandsForMediaItems;
        }

        public boolean isTrusted() {
            return this.isTrusted;
        }

        public int hashCode() {
            return Objects.hashCode(this.controllerCb, this.remoteUserInfo);
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof ControllerInfo)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            ControllerInfo controllerInfo = (ControllerInfo) obj;
            ControllerCb controllerCb2 = this.controllerCb;
            if (controllerCb2 == null && controllerInfo.controllerCb == null) {
                return this.remoteUserInfo.equals(controllerInfo.remoteUserInfo);
            }
            return Util.areEqual(controllerCb2, controllerInfo.controllerCb);
        }

        public String toString() {
            return "ControllerInfo {pkg=" + this.remoteUserInfo.getPackageName() + ", uid=" + this.remoteUserInfo.getUid() + "}";
        }

        /* access modifiers changed from: package-private */
        public ControllerCb getControllerCb() {
            return this.controllerCb;
        }

        static ControllerInfo createLegacyControllerInfo() {
            return new ControllerInfo(new MediaSessionManager.RemoteUserInfo("android.media.session.MediaController", -1, -1), 0, 0, false, (ControllerCb) null, Bundle.EMPTY, 0);
        }

        @Deprecated
        public static ControllerInfo createTestOnlyControllerInfo(MediaSessionManager.RemoteUserInfo remoteUserInfo2, int i, int i2, boolean z, Bundle bundle) {
            return createTestOnlyControllerInfo(remoteUserInfo2.getPackageName(), remoteUserInfo2.getPid(), remoteUserInfo2.getUid(), i, i2, z, bundle);
        }

        public static ControllerInfo createTestOnlyControllerInfo(String str, int i, int i2, int i3, int i4, boolean z, Bundle bundle) {
            return new ControllerInfo(new MediaSessionManager.RemoteUserInfo(str, i, i2), i3, i4, z, (ControllerCb) null, bundle, 0);
        }
    }

    MediaSession(Context context, String str, Player player, PendingIntent pendingIntent, ImmutableList<CommandButton> immutableList, ImmutableList<CommandButton> immutableList2, ImmutableList<CommandButton> immutableList3, Callback callback, Bundle bundle, Bundle bundle2, BitmapLoader bitmapLoader, boolean z, boolean z2, int i) {
        String str2 = str;
        synchronized (STATIC_LOCK) {
            HashMap<String, MediaSession> hashMap = SESSION_ID_TO_SESSION_MAP;
            if (!hashMap.containsKey(str)) {
                hashMap.put(str, this);
            } else {
                throw new IllegalStateException("Session ID must be unique. ID=" + str);
            }
        }
        this.impl = createImpl(context, str, player, pendingIntent, immutableList, immutableList2, immutableList3, callback, bundle, bundle2, bitmapLoader, z, z2, i);
    }

    /* access modifiers changed from: package-private */
    public MediaSessionImpl createImpl(Context context, String str, Player player, PendingIntent pendingIntent, ImmutableList<CommandButton> immutableList, ImmutableList<CommandButton> immutableList2, ImmutableList<CommandButton> immutableList3, Callback callback, Bundle bundle, Bundle bundle2, BitmapLoader bitmapLoader, boolean z, boolean z2, int i) {
        return new MediaSessionImpl(this, context, str, player, pendingIntent, immutableList, immutableList2, immutableList3, callback, bundle, bundle2, bitmapLoader, z, z2);
    }

    /* access modifiers changed from: package-private */
    public MediaSessionImpl getImpl() {
        return this.impl;
    }

    static MediaSession getSession(Uri uri) {
        synchronized (STATIC_LOCK) {
            for (MediaSession next : SESSION_ID_TO_SESSION_MAP.values()) {
                if (Util.areEqual(next.getUri(), uri)) {
                    return next;
                }
            }
            return null;
        }
    }

    public final PendingIntent getSessionActivity() {
        return this.impl.getSessionActivity();
    }

    public final void setSessionActivity(PendingIntent pendingIntent) {
        if (Util.SDK_INT >= 31) {
            Assertions.checkArgument(Api31.isActivity(pendingIntent));
        }
        this.impl.setSessionActivity(pendingIntent);
    }

    public final void setSessionActivity(ControllerInfo controllerInfo, PendingIntent pendingIntent) {
        if (Util.SDK_INT >= 31) {
            Assertions.checkArgument(Api31.isActivity(pendingIntent));
        }
        this.impl.setSessionActivity(controllerInfo, pendingIntent);
    }

    public final void setPlayer(Player player) {
        Assertions.checkNotNull(player);
        Assertions.checkArgument(player.canAdvertiseSession());
        boolean z = true;
        Assertions.checkArgument(player.getApplicationLooper() == getPlayer().getApplicationLooper());
        if (player.getApplicationLooper() != Looper.myLooper()) {
            z = false;
        }
        Assertions.checkState(z);
        this.impl.setPlayer(player);
    }

    public final void release() {
        try {
            synchronized (STATIC_LOCK) {
                SESSION_ID_TO_SESSION_MAP.remove(this.impl.getId());
            }
            this.impl.release();
        } catch (Exception unused) {
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean isReleased() {
        return this.impl.isReleased();
    }

    public final Player getPlayer() {
        return this.impl.getPlayerWrapper().getWrappedPlayer();
    }

    public final String getId() {
        return this.impl.getId();
    }

    public final SessionToken getToken() {
        return this.impl.getToken();
    }

    public final List<ControllerInfo> getConnectedControllers() {
        return this.impl.getConnectedControllers();
    }

    public final ControllerInfo getControllerForCurrentRequest() {
        return this.impl.getControllerForCurrentRequest();
    }

    public boolean isMediaNotificationController(ControllerInfo controllerInfo) {
        return this.impl.isMediaNotificationController(controllerInfo);
    }

    public ControllerInfo getMediaNotificationControllerInfo() {
        return this.impl.getMediaNotificationControllerInfo();
    }

    public final boolean isAutomotiveController(ControllerInfo controllerInfo) {
        return this.impl.isAutomotiveController(controllerInfo);
    }

    public final boolean isAutoCompanionController(ControllerInfo controllerInfo) {
        return this.impl.isAutoCompanionController(controllerInfo);
    }

    public final ListenableFuture<SessionResult> setCustomLayout(ControllerInfo controllerInfo, List<CommandButton> list) {
        Assertions.checkNotNull(controllerInfo, "controller must not be null");
        Assertions.checkNotNull(list, "layout must not be null");
        return this.impl.setCustomLayout(controllerInfo, ImmutableList.copyOf(list));
    }

    public final void setCustomLayout(List<CommandButton> list) {
        Assertions.checkNotNull(list, "layout must not be null");
        this.impl.setCustomLayout(ImmutableList.copyOf(list));
    }

    public final ListenableFuture<SessionResult> setMediaButtonPreferences(ControllerInfo controllerInfo, List<CommandButton> list) {
        Assertions.checkNotNull(controllerInfo, "controller must not be null");
        Assertions.checkNotNull(list, "media button preferences must not be null");
        return this.impl.setMediaButtonPreferences(controllerInfo, ImmutableList.copyOf(list));
    }

    public final void setMediaButtonPreferences(List<CommandButton> list) {
        Assertions.checkNotNull(list, "media button preferences must not be null");
        this.impl.setMediaButtonPreferences(ImmutableList.copyOf(list));
    }

    public final void setAvailableCommands(ControllerInfo controllerInfo, SessionCommands sessionCommands, Player.Commands commands) {
        Assertions.checkNotNull(controllerInfo, "controller must not be null");
        Assertions.checkNotNull(sessionCommands, "sessionCommands must not be null");
        Assertions.checkNotNull(commands, "playerCommands must not be null");
        this.impl.setAvailableCommands(controllerInfo, sessionCommands, commands);
    }

    public ImmutableList<CommandButton> getCustomLayout() {
        return this.impl.getCustomLayout();
    }

    public ImmutableList<CommandButton> getMediaButtonPreferences() {
        return this.impl.getMediaButtonPreferences();
    }

    public final void broadcastCustomCommand(SessionCommand sessionCommand, Bundle bundle) {
        Assertions.checkNotNull(sessionCommand);
        Assertions.checkNotNull(bundle);
        Assertions.checkArgument(sessionCommand.commandCode == 0, "command must be a custom command");
        this.impl.broadcastCustomCommand(sessionCommand, bundle);
    }

    public Bundle getSessionExtras() {
        return this.impl.getSessionExtras();
    }

    public final void setSessionExtras(Bundle bundle) {
        this.impl.setSessionExtras(new Bundle(bundle));
    }

    public final void setSessionExtras(ControllerInfo controllerInfo, Bundle bundle) {
        Assertions.checkNotNull(controllerInfo, "controller must not be null");
        this.impl.setSessionExtras(controllerInfo, new Bundle(bundle));
    }

    public final BitmapLoader getBitmapLoader() {
        return this.impl.getBitmapLoader();
    }

    public final boolean getShowPlayButtonIfPlaybackIsSuppressed() {
        return this.impl.shouldPlayIfSuppressed();
    }

    public final ListenableFuture<SessionResult> sendCustomCommand(ControllerInfo controllerInfo, SessionCommand sessionCommand, Bundle bundle) {
        Assertions.checkNotNull(controllerInfo);
        Assertions.checkNotNull(sessionCommand);
        Assertions.checkNotNull(bundle);
        Assertions.checkArgument(sessionCommand.commandCode == 0, "command must be a custom command");
        return this.impl.sendCustomCommand(controllerInfo, sessionCommand, bundle);
    }

    public final void sendError(ControllerInfo controllerInfo, SessionError sessionError) {
        this.impl.sendError(controllerInfo, sessionError);
    }

    public final void sendError(SessionError sessionError) {
        this.impl.sendError(sessionError);
    }

    /* access modifiers changed from: package-private */
    public final MediaSessionCompat getSessionCompat() {
        return this.impl.getSessionCompat();
    }

    @Deprecated
    public final MediaSessionCompat.Token getSessionCompatToken() {
        return (MediaSessionCompat.Token) LegacyParcelableUtil.convert(this.impl.getSessionCompat().getSessionToken(), MediaSessionCompat.Token.CREATOR);
    }

    public final MediaSession.Token getPlatformToken() {
        return (MediaSession.Token) this.impl.getSessionCompat().getSessionToken().getToken();
    }

    /* access modifiers changed from: package-private */
    public final void setLegacyControllerConnectionTimeoutMs(long j) {
        this.impl.setLegacyControllerConnectionTimeoutMs(j);
    }

    /* access modifiers changed from: package-private */
    public final void handleControllerConnectionFromService(IMediaController iMediaController, ControllerInfo controllerInfo) {
        this.impl.connectFromService(iMediaController, controllerInfo);
    }

    /* access modifiers changed from: package-private */
    public final IBinder getLegacyBrowserServiceBinder() {
        return this.impl.getLegacyBrowserServiceBinder();
    }

    /* access modifiers changed from: package-private */
    public final void setSessionPositionUpdateDelayMs(long j) {
        this.impl.setSessionPositionUpdateDelayMsOnHandler(j);
    }

    /* access modifiers changed from: package-private */
    public final void setListener(Listener listener) {
        this.impl.setMediaSessionListener(listener);
    }

    /* access modifiers changed from: package-private */
    public final void clearListener() {
        this.impl.clearMediaSessionListener();
    }

    /* access modifiers changed from: package-private */
    public final Uri getUri() {
        return this.impl.getUri();
    }

    public interface Callback {
        void onDisconnected(MediaSession mediaSession, ControllerInfo controllerInfo) {
        }

        boolean onMediaButtonEvent(MediaSession mediaSession, ControllerInfo controllerInfo, Intent intent) {
            return false;
        }

        @Deprecated
        int onPlayerCommandRequest(MediaSession mediaSession, ControllerInfo controllerInfo, int i) {
            return 0;
        }

        void onPlayerInteractionFinished(MediaSession mediaSession, ControllerInfo controllerInfo, Player.Commands commands) {
        }

        void onPostConnect(MediaSession mediaSession, ControllerInfo controllerInfo) {
        }

        ConnectionResult onConnect(MediaSession mediaSession, ControllerInfo controllerInfo) {
            return new ConnectionResult.AcceptedResultBuilder(mediaSession).build();
        }

        ListenableFuture<SessionResult> onSetRating(MediaSession mediaSession, ControllerInfo controllerInfo, String str, Rating rating) {
            return Futures.immediateFuture(new SessionResult(-6));
        }

        ListenableFuture<SessionResult> onSetRating(MediaSession mediaSession, ControllerInfo controllerInfo, Rating rating) {
            return Futures.immediateFuture(new SessionResult(-6));
        }

        ListenableFuture<SessionResult> onCustomCommand(MediaSession mediaSession, ControllerInfo controllerInfo, SessionCommand sessionCommand, Bundle bundle) {
            return Futures.immediateFuture(new SessionResult(-6));
        }

        ListenableFuture<List<MediaItem>> onAddMediaItems(MediaSession mediaSession, ControllerInfo controllerInfo, List<MediaItem> list) {
            for (MediaItem mediaItem : list) {
                if (mediaItem.localConfiguration == null) {
                    return Futures.immediateFailedFuture(new UnsupportedOperationException());
                }
            }
            return Futures.immediateFuture(list);
        }

        ListenableFuture<MediaItemsWithStartPosition> onSetMediaItems(MediaSession mediaSession, ControllerInfo controllerInfo, List<MediaItem> list, int i, long j) {
            return Util.transformFutureAsync(onAddMediaItems(mediaSession, controllerInfo, list), new MediaSession$Callback$$ExternalSyntheticLambda0(i, j));
        }

        ListenableFuture<MediaItemsWithStartPosition> onPlaybackResumption(MediaSession mediaSession, ControllerInfo controllerInfo) {
            return Futures.immediateFailedFuture(new UnsupportedOperationException());
        }
    }

    public static final class MediaItemsWithStartPosition {
        public final ImmutableList<MediaItem> mediaItems;
        public final int startIndex;
        public final long startPositionMs;

        public MediaItemsWithStartPosition(List<MediaItem> list, int i, long j) {
            this.mediaItems = ImmutableList.copyOf(list);
            this.startIndex = i;
            this.startPositionMs = j;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof MediaItemsWithStartPosition)) {
                return false;
            }
            MediaItemsWithStartPosition mediaItemsWithStartPosition = (MediaItemsWithStartPosition) obj;
            if (!this.mediaItems.equals(mediaItemsWithStartPosition.mediaItems) || !Util.areEqual(Integer.valueOf(this.startIndex), Integer.valueOf(mediaItemsWithStartPosition.startIndex)) || !Util.areEqual(Long.valueOf(this.startPositionMs), Long.valueOf(mediaItemsWithStartPosition.startPositionMs))) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return (((this.mediaItems.hashCode() * 31) + this.startIndex) * 31) + Longs.hashCode(this.startPositionMs);
        }
    }

    public static final class ConnectionResult {
        public static final Player.Commands DEFAULT_PLAYER_COMMANDS = new Player.Commands.Builder().addAllCommands().build();
        public static final SessionCommands DEFAULT_SESSION_AND_LIBRARY_COMMANDS = new SessionCommands.Builder().addAllLibraryCommands().addAllSessionCommands().build();
        public static final SessionCommands DEFAULT_SESSION_COMMANDS = new SessionCommands.Builder().addAllSessionCommands().build();
        public final Player.Commands availablePlayerCommands;
        public final SessionCommands availableSessionCommands;
        public final ImmutableList<CommandButton> customLayout;
        public final boolean isAccepted;
        public final ImmutableList<CommandButton> mediaButtonPreferences;
        public final PendingIntent sessionActivity;
        public final Bundle sessionExtras;

        public static class AcceptedResultBuilder {
            private Player.Commands availablePlayerCommands = ConnectionResult.DEFAULT_PLAYER_COMMANDS;
            private SessionCommands availableSessionCommands;
            private ImmutableList<CommandButton> customLayout;
            private ImmutableList<CommandButton> mediaButtonPreferences;
            private PendingIntent sessionActivity;
            private Bundle sessionExtras;

            public AcceptedResultBuilder(MediaSession mediaSession) {
                SessionCommands sessionCommands;
                if (mediaSession instanceof MediaLibraryService.MediaLibrarySession) {
                    sessionCommands = ConnectionResult.DEFAULT_SESSION_AND_LIBRARY_COMMANDS;
                } else {
                    sessionCommands = ConnectionResult.DEFAULT_SESSION_COMMANDS;
                }
                this.availableSessionCommands = sessionCommands;
            }

            public AcceptedResultBuilder setAvailableSessionCommands(SessionCommands sessionCommands) {
                this.availableSessionCommands = (SessionCommands) Assertions.checkNotNull(sessionCommands);
                return this;
            }

            public AcceptedResultBuilder setAvailablePlayerCommands(Player.Commands commands) {
                this.availablePlayerCommands = (Player.Commands) Assertions.checkNotNull(commands);
                return this;
            }

            public AcceptedResultBuilder setCustomLayout(List<CommandButton> list) {
                this.customLayout = list == null ? null : ImmutableList.copyOf(list);
                return this;
            }

            public AcceptedResultBuilder setMediaButtonPreferences(List<CommandButton> list) {
                this.mediaButtonPreferences = list == null ? null : ImmutableList.copyOf(list);
                return this;
            }

            public AcceptedResultBuilder setSessionExtras(Bundle bundle) {
                this.sessionExtras = bundle;
                return this;
            }

            public AcceptedResultBuilder setSessionActivity(PendingIntent pendingIntent) {
                this.sessionActivity = pendingIntent;
                return this;
            }

            public ConnectionResult build() {
                return new ConnectionResult(true, this.availableSessionCommands, this.availablePlayerCommands, this.customLayout, this.mediaButtonPreferences, this.sessionExtras, this.sessionActivity);
            }
        }

        private ConnectionResult(boolean z, SessionCommands sessionCommands, Player.Commands commands, ImmutableList<CommandButton> immutableList, ImmutableList<CommandButton> immutableList2, Bundle bundle, PendingIntent pendingIntent) {
            this.isAccepted = z;
            this.availableSessionCommands = sessionCommands;
            this.availablePlayerCommands = commands;
            this.customLayout = immutableList;
            this.mediaButtonPreferences = immutableList2;
            this.sessionExtras = bundle;
            this.sessionActivity = pendingIntent;
        }

        public static ConnectionResult accept(SessionCommands sessionCommands, Player.Commands commands) {
            return new ConnectionResult(true, sessionCommands, commands, (ImmutableList<CommandButton>) null, (ImmutableList<CommandButton>) null, (Bundle) null, (PendingIntent) null);
        }

        public static ConnectionResult reject() {
            return new ConnectionResult(false, SessionCommands.EMPTY, Player.Commands.EMPTY, ImmutableList.of(), ImmutableList.of(), Bundle.EMPTY, (PendingIntent) null);
        }
    }

    static abstract class BuilderBase<SessionT extends MediaSession, BuilderT extends BuilderBase<SessionT, BuilderT, CallbackT>, CallbackT extends Callback> {
        BitmapLoader bitmapLoader;
        CallbackT callback;
        ImmutableList<CommandButton> commandButtonsForMediaItems;
        final Context context;
        ImmutableList<CommandButton> customLayout;
        String id = "";
        boolean isPeriodicPositionUpdateEnabled;
        ImmutableList<CommandButton> mediaButtonPreferences;
        boolean playIfSuppressed;
        final Player player;
        PendingIntent sessionActivity;
        Bundle sessionExtras;
        Bundle tokenExtras;

        public abstract SessionT build();

        public BuilderBase(Context context2, Player player2, CallbackT callbackt) {
            this.context = (Context) Assertions.checkNotNull(context2);
            this.player = (Player) Assertions.checkNotNull(player2);
            Assertions.checkArgument(player2.canAdvertiseSession());
            this.callback = callbackt;
            this.tokenExtras = new Bundle();
            this.sessionExtras = new Bundle();
            this.customLayout = ImmutableList.of();
            this.mediaButtonPreferences = ImmutableList.of();
            this.playIfSuppressed = true;
            this.isPeriodicPositionUpdateEnabled = true;
            this.commandButtonsForMediaItems = ImmutableList.of();
        }

        public BuilderT setSessionActivity(PendingIntent pendingIntent) {
            if (Util.SDK_INT >= 31) {
                Assertions.checkArgument(Api31.isActivity(pendingIntent));
            }
            this.sessionActivity = (PendingIntent) Assertions.checkNotNull(pendingIntent);
            return this;
        }

        public BuilderT setId(String str) {
            this.id = (String) Assertions.checkNotNull(str);
            return this;
        }

        /* access modifiers changed from: package-private */
        public BuilderT setCallback(CallbackT callbackt) {
            this.callback = (Callback) Assertions.checkNotNull(callbackt);
            return this;
        }

        public BuilderT setExtras(Bundle bundle) {
            this.tokenExtras = new Bundle((Bundle) Assertions.checkNotNull(bundle));
            return this;
        }

        public BuilderT setSessionExtras(Bundle bundle) {
            this.sessionExtras = new Bundle((Bundle) Assertions.checkNotNull(bundle));
            return this;
        }

        public BuilderT setBitmapLoader(BitmapLoader bitmapLoader2) {
            this.bitmapLoader = (BitmapLoader) Assertions.checkNotNull(bitmapLoader2);
            return this;
        }

        public BuilderT setCustomLayout(List<CommandButton> list) {
            this.customLayout = ImmutableList.copyOf(list);
            return this;
        }

        public BuilderT setMediaButtonPreferences(List<CommandButton> list) {
            this.mediaButtonPreferences = ImmutableList.copyOf(list);
            return this;
        }

        public BuilderT setShowPlayButtonIfPlaybackIsSuppressed(boolean z) {
            this.playIfSuppressed = z;
            return this;
        }

        public BuilderT setCommandButtonsForMediaItems(List<CommandButton> list) {
            this.commandButtonsForMediaItems = ImmutableList.copyOf(list);
            return this;
        }

        public BuilderT setPeriodicPositionUpdateEnabled(boolean z) {
            this.isPeriodicPositionUpdateEnabled = z;
            return this;
        }
    }

    private static final class Api31 {
        private Api31() {
        }

        public static boolean isActivity(PendingIntent pendingIntent) {
            return pendingIntent.isActivity();
        }
    }
}
