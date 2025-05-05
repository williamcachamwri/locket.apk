package androidx.media3.session;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.C;
import androidx.media3.common.DeviceInfo;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MediaLibraryInfo;
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
import androidx.media3.common.util.Consumer;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Size;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSourceBitmapLoader;
import androidx.media3.session.legacy.MediaBrowserCompat;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.errorprone.annotations.DoNotMock;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.checkerframework.checker.initialization.qual.NotOnlyInitialized;

@DoNotMock
public class MediaController implements Player {
    public static final String KEY_MEDIA_NOTIFICATION_CONTROLLER_FLAG = "androidx.media3.session.MediaNotificationManager";
    public static final long RELEASE_UNBIND_TIMEOUT_MS = 30000;
    private static final String TAG = "MediaController";
    private static final String WRONG_THREAD_ERROR_MESSAGE = "MediaController method is called from a wrong thread. See javadoc of MediaController for details.";
    final Handler applicationHandler;
    final ConnectionCallback connectionCallback;
    private boolean connectionNotified;
    @NotOnlyInitialized
    private final MediaControllerImpl impl;
    final Listener listener;
    private final int maxCommandsForMediaItems;
    private boolean released;
    private long timeDiffMs = C.TIME_UNSET;
    private final Timeline.Window window = new Timeline.Window();

    interface ConnectionCallback {
        void onAccepted();

        void onRejected();
    }

    interface MediaControllerImpl {
        void addListener(Player.Listener listener);

        void addMediaItem(int i, MediaItem mediaItem);

        void addMediaItem(MediaItem mediaItem);

        void addMediaItems(int i, List<MediaItem> list);

        void addMediaItems(List<MediaItem> list);

        void clearMediaItems();

        void clearVideoSurface();

        void clearVideoSurface(Surface surface);

        void clearVideoSurfaceHolder(SurfaceHolder surfaceHolder);

        void clearVideoSurfaceView(SurfaceView surfaceView);

        void clearVideoTextureView(TextureView textureView);

        void connect();

        void decreaseDeviceVolume();

        void decreaseDeviceVolume(int i);

        AudioAttributes getAudioAttributes();

        Player.Commands getAvailableCommands();

        SessionCommands getAvailableSessionCommands();

        IMediaController getBinder();

        MediaBrowserCompat getBrowserCompat();

        int getBufferedPercentage();

        long getBufferedPosition();

        ImmutableList<CommandButton> getCommandButtonsForMediaItem(MediaItem mediaItem);

        SessionToken getConnectedToken();

        Bundle getConnectionHints();

        long getContentBufferedPosition();

        long getContentDuration();

        long getContentPosition();

        Context getContext();

        int getCurrentAdGroupIndex();

        int getCurrentAdIndexInAdGroup();

        CueGroup getCurrentCues();

        long getCurrentLiveOffset();

        int getCurrentMediaItemIndex();

        int getCurrentPeriodIndex();

        long getCurrentPosition();

        Timeline getCurrentTimeline();

        Tracks getCurrentTracks();

        DeviceInfo getDeviceInfo();

        int getDeviceVolume();

        long getDuration();

        long getMaxSeekToPreviousPosition();

        ImmutableList<CommandButton> getMediaButtonPreferences();

        MediaMetadata getMediaMetadata();

        int getNextMediaItemIndex();

        boolean getPlayWhenReady();

        PlaybackParameters getPlaybackParameters();

        int getPlaybackState();

        int getPlaybackSuppressionReason();

        PlaybackException getPlayerError();

        MediaMetadata getPlaylistMetadata();

        int getPreviousMediaItemIndex();

        int getRepeatMode();

        long getSeekBackIncrement();

        long getSeekForwardIncrement();

        PendingIntent getSessionActivity();

        Bundle getSessionExtras();

        boolean getShuffleModeEnabled();

        Size getSurfaceSize();

        long getTotalBufferedDuration();

        TrackSelectionParameters getTrackSelectionParameters();

        VideoSize getVideoSize();

        float getVolume();

        boolean hasNextMediaItem();

        boolean hasPreviousMediaItem();

        void increaseDeviceVolume();

        void increaseDeviceVolume(int i);

        boolean isConnected();

        boolean isDeviceMuted();

        boolean isLoading();

        boolean isPlaying();

        boolean isPlayingAd();

        void moveMediaItem(int i, int i2);

        void moveMediaItems(int i, int i2, int i3);

        void pause();

        void play();

        void prepare();

        void release();

        void removeListener(Player.Listener listener);

        void removeMediaItem(int i);

        void removeMediaItems(int i, int i2);

        void replaceMediaItem(int i, MediaItem mediaItem);

        void replaceMediaItems(int i, int i2, List<MediaItem> list);

        void seekBack();

        void seekForward();

        void seekTo(int i, long j);

        void seekTo(long j);

        void seekToDefaultPosition();

        void seekToDefaultPosition(int i);

        void seekToNext();

        void seekToNextMediaItem();

        void seekToPrevious();

        void seekToPreviousMediaItem();

        ListenableFuture<SessionResult> sendCustomCommand(SessionCommand sessionCommand, Bundle bundle);

        void setAudioAttributes(AudioAttributes audioAttributes, boolean z);

        void setDeviceMuted(boolean z);

        void setDeviceMuted(boolean z, int i);

        void setDeviceVolume(int i);

        void setDeviceVolume(int i, int i2);

        void setMediaItem(MediaItem mediaItem);

        void setMediaItem(MediaItem mediaItem, long j);

        void setMediaItem(MediaItem mediaItem, boolean z);

        void setMediaItems(List<MediaItem> list);

        void setMediaItems(List<MediaItem> list, int i, long j);

        void setMediaItems(List<MediaItem> list, boolean z);

        void setPlayWhenReady(boolean z);

        void setPlaybackParameters(PlaybackParameters playbackParameters);

        void setPlaybackSpeed(float f);

        void setPlaylistMetadata(MediaMetadata mediaMetadata);

        ListenableFuture<SessionResult> setRating(Rating rating);

        ListenableFuture<SessionResult> setRating(String str, Rating rating);

        void setRepeatMode(int i);

        void setShuffleModeEnabled(boolean z);

        void setTrackSelectionParameters(TrackSelectionParameters trackSelectionParameters);

        void setVideoSurface(Surface surface);

        void setVideoSurfaceHolder(SurfaceHolder surfaceHolder);

        void setVideoSurfaceView(SurfaceView surfaceView);

        void setVideoTextureView(TextureView textureView);

        void setVolume(float f);

        void stop();
    }

    public final boolean canAdvertiseSession() {
        return false;
    }

    public final Object getCurrentManifest() {
        return null;
    }

    public static final class Builder {
        private Looper applicationLooper = Util.getCurrentOrMainLooper();
        private BitmapLoader bitmapLoader;
        private Bundle connectionHints = Bundle.EMPTY;
        private final Context context;
        private Listener listener = new Listener() {
        };
        private int maxCommandsForMediaItems;
        private final SessionToken token;

        public Builder(Context context2, SessionToken sessionToken) {
            this.context = (Context) Assertions.checkNotNull(context2);
            this.token = (SessionToken) Assertions.checkNotNull(sessionToken);
        }

        public Builder setConnectionHints(Bundle bundle) {
            this.connectionHints = new Bundle((Bundle) Assertions.checkNotNull(bundle));
            return this;
        }

        public Builder setListener(Listener listener2) {
            this.listener = (Listener) Assertions.checkNotNull(listener2);
            return this;
        }

        public Builder setApplicationLooper(Looper looper) {
            this.applicationLooper = (Looper) Assertions.checkNotNull(looper);
            return this;
        }

        public Builder setBitmapLoader(BitmapLoader bitmapLoader2) {
            this.bitmapLoader = (BitmapLoader) Assertions.checkNotNull(bitmapLoader2);
            return this;
        }

        public Builder setMaxCommandsForMediaItems(int i) {
            Assertions.checkArgument(i >= 0);
            this.maxCommandsForMediaItems = i;
            return this;
        }

        public ListenableFuture<MediaController> buildAsync() {
            MediaControllerHolder mediaControllerHolder = new MediaControllerHolder(this.applicationLooper);
            if (this.token.isLegacySession() && this.bitmapLoader == null) {
                this.bitmapLoader = new CacheBitmapLoader(new DataSourceBitmapLoader(this.context));
            }
            Util.postOrRun(new Handler(this.applicationLooper), new MediaController$Builder$$ExternalSyntheticLambda0(mediaControllerHolder, new MediaController(this.context, this.token, this.connectionHints, this.listener, this.applicationLooper, mediaControllerHolder, this.bitmapLoader, this.maxCommandsForMediaItems)));
            return mediaControllerHolder;
        }
    }

    public interface Listener {
        void onAvailableSessionCommandsChanged(MediaController mediaController, SessionCommands sessionCommands) {
        }

        void onCustomLayoutChanged(MediaController mediaController, List<CommandButton> list) {
        }

        void onDisconnected(MediaController mediaController) {
        }

        void onError(MediaController mediaController, SessionError sessionError) {
        }

        void onExtrasChanged(MediaController mediaController, Bundle bundle) {
        }

        void onMediaButtonPreferencesChanged(MediaController mediaController, List<CommandButton> list) {
        }

        void onSessionActivityChanged(MediaController mediaController, PendingIntent pendingIntent) {
        }

        ListenableFuture<SessionResult> onSetCustomLayout(MediaController mediaController, List<CommandButton> list) {
            return Futures.immediateFuture(new SessionResult(-6));
        }

        ListenableFuture<SessionResult> onCustomCommand(MediaController mediaController, SessionCommand sessionCommand, Bundle bundle) {
            return Futures.immediateFuture(new SessionResult(-6));
        }
    }

    MediaController(Context context, SessionToken sessionToken, Bundle bundle, Listener listener2, Looper looper, ConnectionCallback connectionCallback2, BitmapLoader bitmapLoader, int i) {
        Assertions.checkNotNull(context, "context must not be null");
        Assertions.checkNotNull(sessionToken, "token must not be null");
        Log.i(TAG, "Init " + Integer.toHexString(System.identityHashCode(this)) + " [AndroidXMedia3/1.5.1] [" + Util.DEVICE_DEBUG_INFO + "]");
        this.listener = listener2;
        this.applicationHandler = new Handler(looper);
        this.connectionCallback = connectionCallback2;
        this.maxCommandsForMediaItems = i;
        MediaControllerImpl createImpl = createImpl(context, sessionToken, bundle, looper, bitmapLoader);
        this.impl = createImpl;
        createImpl.connect();
    }

    /* access modifiers changed from: package-private */
    public MediaControllerImpl createImpl(Context context, SessionToken sessionToken, Bundle bundle, Looper looper, BitmapLoader bitmapLoader) {
        if (!sessionToken.isLegacySession()) {
            return new MediaControllerImplBase(context, this, sessionToken, bundle, looper);
        }
        return new MediaControllerImplLegacy(context, this, sessionToken, bundle, looper, (BitmapLoader) Assertions.checkNotNull(bitmapLoader));
    }

    public final void stop() {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring stop().");
        } else {
            this.impl.stop();
        }
    }

    public final void release() {
        verifyApplicationThread();
        if (!this.released) {
            Log.i(TAG, "Release " + Integer.toHexString(System.identityHashCode(this)) + " [AndroidXMedia3/1.5.1] [" + Util.DEVICE_DEBUG_INFO + "] [" + MediaLibraryInfo.registeredModules() + "]");
            this.released = true;
            this.applicationHandler.removeCallbacksAndMessages((Object) null);
            try {
                this.impl.release();
            } catch (Exception e) {
                Log.d(TAG, "Exception while releasing impl", e);
            }
            if (this.connectionNotified) {
                notifyControllerListener(new MediaController$$ExternalSyntheticLambda0(this));
                return;
            }
            this.connectionNotified = true;
            this.connectionCallback.onRejected();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$release$0$androidx-media3-session-MediaController  reason: not valid java name */
    public /* synthetic */ void m921lambda$release$0$androidxmedia3sessionMediaController(Listener listener2) {
        listener2.onDisconnected(this);
    }

    public static void releaseFuture(Future<? extends MediaController> future) {
        if (!future.cancel(false)) {
            try {
                ((MediaController) Futures.getDone(future)).release();
            } catch (CancellationException | ExecutionException e) {
                Log.w(TAG, "MediaController future failed (so we couldn't release it)", e);
            }
        }
    }

    public final SessionToken getConnectedToken() {
        if (isConnected()) {
            return this.impl.getConnectedToken();
        }
        return null;
    }

    public final boolean isConnected() {
        return this.impl.isConnected();
    }

    public final ImmutableList<CommandButton> getCommandButtonsForMediaItem(MediaItem mediaItem) {
        return this.impl.getCommandButtonsForMediaItem(mediaItem);
    }

    public final void play() {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring play().");
        } else {
            this.impl.play();
        }
    }

    public final void pause() {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring pause().");
        } else {
            this.impl.pause();
        }
    }

    public final void prepare() {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring prepare().");
        } else {
            this.impl.prepare();
        }
    }

    public final void seekToDefaultPosition() {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring seekTo().");
        } else {
            this.impl.seekToDefaultPosition();
        }
    }

    public final void seekToDefaultPosition(int i) {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring seekTo().");
        } else {
            this.impl.seekToDefaultPosition(i);
        }
    }

    public final void seekTo(long j) {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring seekTo().");
        } else {
            this.impl.seekTo(j);
        }
    }

    public final void seekTo(int i, long j) {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring seekTo().");
        } else {
            this.impl.seekTo(i, j);
        }
    }

    public final long getSeekBackIncrement() {
        verifyApplicationThread();
        if (isConnected()) {
            return this.impl.getSeekBackIncrement();
        }
        return 0;
    }

    public final void seekBack() {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring seekBack().");
        } else {
            this.impl.seekBack();
        }
    }

    public final long getSeekForwardIncrement() {
        verifyApplicationThread();
        if (isConnected()) {
            return this.impl.getSeekForwardIncrement();
        }
        return 0;
    }

    public final void seekForward() {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring seekForward().");
        } else {
            this.impl.seekForward();
        }
    }

    public final PendingIntent getSessionActivity() {
        if (isConnected()) {
            return this.impl.getSessionActivity();
        }
        return null;
    }

    public final PlaybackException getPlayerError() {
        verifyApplicationThread();
        if (isConnected()) {
            return this.impl.getPlayerError();
        }
        return null;
    }

    public final void setPlayWhenReady(boolean z) {
        verifyApplicationThread();
        if (isConnected()) {
            this.impl.setPlayWhenReady(z);
        }
    }

    public final boolean getPlayWhenReady() {
        verifyApplicationThread();
        return isConnected() && this.impl.getPlayWhenReady();
    }

    public final int getPlaybackSuppressionReason() {
        verifyApplicationThread();
        if (isConnected()) {
            return this.impl.getPlaybackSuppressionReason();
        }
        return 0;
    }

    public final int getPlaybackState() {
        verifyApplicationThread();
        if (isConnected()) {
            return this.impl.getPlaybackState();
        }
        return 1;
    }

    public final boolean isPlaying() {
        verifyApplicationThread();
        return isConnected() && this.impl.isPlaying();
    }

    public final boolean isLoading() {
        verifyApplicationThread();
        return isConnected() && this.impl.isLoading();
    }

    public final long getDuration() {
        verifyApplicationThread();
        return isConnected() ? this.impl.getDuration() : C.TIME_UNSET;
    }

    public final long getCurrentPosition() {
        verifyApplicationThread();
        if (isConnected()) {
            return this.impl.getCurrentPosition();
        }
        return 0;
    }

    public final long getBufferedPosition() {
        verifyApplicationThread();
        if (isConnected()) {
            return this.impl.getBufferedPosition();
        }
        return 0;
    }

    public final int getBufferedPercentage() {
        verifyApplicationThread();
        if (isConnected()) {
            return this.impl.getBufferedPercentage();
        }
        return 0;
    }

    public final long getTotalBufferedDuration() {
        verifyApplicationThread();
        if (isConnected()) {
            return this.impl.getTotalBufferedDuration();
        }
        return 0;
    }

    public final long getCurrentLiveOffset() {
        verifyApplicationThread();
        return isConnected() ? this.impl.getCurrentLiveOffset() : C.TIME_UNSET;
    }

    public final long getContentDuration() {
        verifyApplicationThread();
        return isConnected() ? this.impl.getContentDuration() : C.TIME_UNSET;
    }

    public final long getContentPosition() {
        verifyApplicationThread();
        if (isConnected()) {
            return this.impl.getContentPosition();
        }
        return 0;
    }

    public final long getContentBufferedPosition() {
        verifyApplicationThread();
        if (isConnected()) {
            return this.impl.getContentBufferedPosition();
        }
        return 0;
    }

    public final boolean isPlayingAd() {
        verifyApplicationThread();
        return isConnected() && this.impl.isPlayingAd();
    }

    public final int getCurrentAdGroupIndex() {
        verifyApplicationThread();
        if (isConnected()) {
            return this.impl.getCurrentAdGroupIndex();
        }
        return -1;
    }

    public final int getCurrentAdIndexInAdGroup() {
        verifyApplicationThread();
        if (isConnected()) {
            return this.impl.getCurrentAdIndexInAdGroup();
        }
        return -1;
    }

    public final void setPlaybackParameters(PlaybackParameters playbackParameters) {
        verifyApplicationThread();
        Assertions.checkNotNull(playbackParameters, "playbackParameters must not be null");
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring setPlaybackParameters().");
        } else {
            this.impl.setPlaybackParameters(playbackParameters);
        }
    }

    public final void setPlaybackSpeed(float f) {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring setPlaybackSpeed().");
        } else {
            this.impl.setPlaybackSpeed(f);
        }
    }

    public final PlaybackParameters getPlaybackParameters() {
        verifyApplicationThread();
        return isConnected() ? this.impl.getPlaybackParameters() : PlaybackParameters.DEFAULT;
    }

    public final AudioAttributes getAudioAttributes() {
        verifyApplicationThread();
        if (!isConnected()) {
            return AudioAttributes.DEFAULT;
        }
        return this.impl.getAudioAttributes();
    }

    public final ListenableFuture<SessionResult> setRating(String str, Rating rating) {
        verifyApplicationThread();
        Assertions.checkNotNull(str, "mediaId must not be null");
        Assertions.checkNotEmpty(str, "mediaId must not be empty");
        Assertions.checkNotNull(rating, "rating must not be null");
        if (isConnected()) {
            return this.impl.setRating(str, rating);
        }
        return createDisconnectedFuture();
    }

    public final ListenableFuture<SessionResult> setRating(Rating rating) {
        verifyApplicationThread();
        Assertions.checkNotNull(rating, "rating must not be null");
        if (isConnected()) {
            return this.impl.setRating(rating);
        }
        return createDisconnectedFuture();
    }

    public final ListenableFuture<SessionResult> sendCustomCommand(SessionCommand sessionCommand, Bundle bundle) {
        verifyApplicationThread();
        Assertions.checkNotNull(sessionCommand, "command must not be null");
        Assertions.checkArgument(sessionCommand.commandCode == 0, "command must be a custom command");
        if (isConnected()) {
            return this.impl.sendCustomCommand(sessionCommand, bundle);
        }
        return createDisconnectedFuture();
    }

    public final ListenableFuture<SessionResult> sendCustomCommand(SessionCommand sessionCommand, MediaItem mediaItem, Bundle bundle) {
        Bundle bundle2 = new Bundle(bundle);
        bundle2.putString("androidx.media.utils.extras.KEY_CUSTOM_BROWSER_ACTION_MEDIA_ITEM_ID", mediaItem.mediaId);
        return sendCustomCommand(sessionCommand, bundle2);
    }

    public final ImmutableList<CommandButton> getCustomLayout() {
        return getMediaButtonPreferences();
    }

    public final ImmutableList<CommandButton> getMediaButtonPreferences() {
        verifyApplicationThread();
        return isConnected() ? this.impl.getMediaButtonPreferences() : ImmutableList.of();
    }

    public final Bundle getSessionExtras() {
        verifyApplicationThread();
        return isConnected() ? this.impl.getSessionExtras() : Bundle.EMPTY;
    }

    public final Timeline getCurrentTimeline() {
        verifyApplicationThread();
        return isConnected() ? this.impl.getCurrentTimeline() : Timeline.EMPTY;
    }

    public final void setMediaItem(MediaItem mediaItem) {
        verifyApplicationThread();
        Assertions.checkNotNull(mediaItem, "mediaItems must not be null");
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring setMediaItem().");
        } else {
            this.impl.setMediaItem(mediaItem);
        }
    }

    public final void setMediaItem(MediaItem mediaItem, long j) {
        verifyApplicationThread();
        Assertions.checkNotNull(mediaItem, "mediaItems must not be null");
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring setMediaItem().");
        } else {
            this.impl.setMediaItem(mediaItem, j);
        }
    }

    public final void setMediaItem(MediaItem mediaItem, boolean z) {
        verifyApplicationThread();
        Assertions.checkNotNull(mediaItem, "mediaItems must not be null");
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring setMediaItems().");
        } else {
            this.impl.setMediaItem(mediaItem, z);
        }
    }

    public final void setMediaItems(List<MediaItem> list) {
        verifyApplicationThread();
        Assertions.checkNotNull(list, "mediaItems must not be null");
        for (int i = 0; i < list.size(); i++) {
            Assertions.checkArgument(list.get(i) != null, "items must not contain null, index=" + i);
        }
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring setMediaItems().");
        } else {
            this.impl.setMediaItems(list);
        }
    }

    public final void setMediaItems(List<MediaItem> list, boolean z) {
        verifyApplicationThread();
        Assertions.checkNotNull(list, "mediaItems must not be null");
        for (int i = 0; i < list.size(); i++) {
            Assertions.checkArgument(list.get(i) != null, "items must not contain null, index=" + i);
        }
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring setMediaItems().");
        } else {
            this.impl.setMediaItems(list, z);
        }
    }

    public final void setMediaItems(List<MediaItem> list, int i, long j) {
        verifyApplicationThread();
        Assertions.checkNotNull(list, "mediaItems must not be null");
        for (int i2 = 0; i2 < list.size(); i2++) {
            Assertions.checkArgument(list.get(i2) != null, "items must not contain null, index=" + i2);
        }
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring setMediaItems().");
        } else {
            this.impl.setMediaItems(list, i, j);
        }
    }

    public final void setPlaylistMetadata(MediaMetadata mediaMetadata) {
        verifyApplicationThread();
        Assertions.checkNotNull(mediaMetadata, "playlistMetadata must not be null");
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring setPlaylistMetadata().");
        } else {
            this.impl.setPlaylistMetadata(mediaMetadata);
        }
    }

    public final MediaMetadata getPlaylistMetadata() {
        verifyApplicationThread();
        return isConnected() ? this.impl.getPlaylistMetadata() : MediaMetadata.EMPTY;
    }

    public final void addMediaItem(MediaItem mediaItem) {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring addMediaItem().");
        } else {
            this.impl.addMediaItem(mediaItem);
        }
    }

    public final void addMediaItem(int i, MediaItem mediaItem) {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring addMediaItem().");
        } else {
            this.impl.addMediaItem(i, mediaItem);
        }
    }

    public final void addMediaItems(List<MediaItem> list) {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring addMediaItems().");
        } else {
            this.impl.addMediaItems(list);
        }
    }

    public final void addMediaItems(int i, List<MediaItem> list) {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring addMediaItems().");
        } else {
            this.impl.addMediaItems(i, list);
        }
    }

    public final void removeMediaItem(int i) {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring removeMediaItem().");
        } else {
            this.impl.removeMediaItem(i);
        }
    }

    public final void removeMediaItems(int i, int i2) {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring removeMediaItems().");
        } else {
            this.impl.removeMediaItems(i, i2);
        }
    }

    public final void clearMediaItems() {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring clearMediaItems().");
        } else {
            this.impl.clearMediaItems();
        }
    }

    public final void moveMediaItem(int i, int i2) {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring moveMediaItem().");
        } else {
            this.impl.moveMediaItem(i, i2);
        }
    }

    public final void moveMediaItems(int i, int i2, int i3) {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring moveMediaItems().");
        } else {
            this.impl.moveMediaItems(i, i2, i3);
        }
    }

    public final void replaceMediaItem(int i, MediaItem mediaItem) {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring replaceMediaItem().");
        } else {
            this.impl.replaceMediaItem(i, mediaItem);
        }
    }

    public final void replaceMediaItems(int i, int i2, List<MediaItem> list) {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring replaceMediaItems().");
        } else {
            this.impl.replaceMediaItems(i, i2, list);
        }
    }

    @Deprecated
    public final boolean isCurrentWindowDynamic() {
        return isCurrentMediaItemDynamic();
    }

    public final boolean isCurrentMediaItemDynamic() {
        verifyApplicationThread();
        Timeline currentTimeline = getCurrentTimeline();
        return !currentTimeline.isEmpty() && currentTimeline.getWindow(getCurrentMediaItemIndex(), this.window).isDynamic;
    }

    @Deprecated
    public final boolean isCurrentWindowLive() {
        return isCurrentMediaItemLive();
    }

    public final boolean isCurrentMediaItemLive() {
        verifyApplicationThread();
        Timeline currentTimeline = getCurrentTimeline();
        return !currentTimeline.isEmpty() && currentTimeline.getWindow(getCurrentMediaItemIndex(), this.window).isLive();
    }

    @Deprecated
    public final boolean isCurrentWindowSeekable() {
        return isCurrentMediaItemSeekable();
    }

    public final boolean isCurrentMediaItemSeekable() {
        verifyApplicationThread();
        Timeline currentTimeline = getCurrentTimeline();
        return !currentTimeline.isEmpty() && currentTimeline.getWindow(getCurrentMediaItemIndex(), this.window).isSeekable;
    }

    public final MediaItem getCurrentMediaItem() {
        Timeline currentTimeline = getCurrentTimeline();
        if (currentTimeline.isEmpty()) {
            return null;
        }
        return currentTimeline.getWindow(getCurrentMediaItemIndex(), this.window).mediaItem;
    }

    public final int getMediaItemCount() {
        return getCurrentTimeline().getWindowCount();
    }

    public final MediaItem getMediaItemAt(int i) {
        return getCurrentTimeline().getWindow(i, this.window).mediaItem;
    }

    public final int getCurrentPeriodIndex() {
        verifyApplicationThread();
        if (isConnected()) {
            return this.impl.getCurrentPeriodIndex();
        }
        return -1;
    }

    @Deprecated
    public final int getCurrentWindowIndex() {
        return getCurrentMediaItemIndex();
    }

    public final int getCurrentMediaItemIndex() {
        verifyApplicationThread();
        if (isConnected()) {
            return this.impl.getCurrentMediaItemIndex();
        }
        return -1;
    }

    @Deprecated
    public final int getPreviousWindowIndex() {
        return getPreviousMediaItemIndex();
    }

    public final int getPreviousMediaItemIndex() {
        verifyApplicationThread();
        if (isConnected()) {
            return this.impl.getPreviousMediaItemIndex();
        }
        return -1;
    }

    @Deprecated
    public final int getNextWindowIndex() {
        return getNextMediaItemIndex();
    }

    public final int getNextMediaItemIndex() {
        verifyApplicationThread();
        if (isConnected()) {
            return this.impl.getNextMediaItemIndex();
        }
        return -1;
    }

    @Deprecated
    public final boolean hasNext() {
        return hasNextMediaItem();
    }

    @Deprecated
    public final boolean hasNextWindow() {
        return hasNextMediaItem();
    }

    public final boolean hasPreviousMediaItem() {
        verifyApplicationThread();
        return isConnected() && this.impl.hasPreviousMediaItem();
    }

    public final boolean hasNextMediaItem() {
        verifyApplicationThread();
        return isConnected() && this.impl.hasNextMediaItem();
    }

    @Deprecated
    public final void next() {
        seekToNextMediaItem();
    }

    @Deprecated
    public final void seekToPreviousWindow() {
        seekToPreviousMediaItem();
    }

    public final void seekToPreviousMediaItem() {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring seekToPreviousMediaItem().");
        } else {
            this.impl.seekToPreviousMediaItem();
        }
    }

    @Deprecated
    public final void seekToNextWindow() {
        seekToNextMediaItem();
    }

    public final void seekToNextMediaItem() {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring seekToNextMediaItem().");
        } else {
            this.impl.seekToNextMediaItem();
        }
    }

    public final void seekToPrevious() {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring seekToPrevious().");
        } else {
            this.impl.seekToPrevious();
        }
    }

    public final long getMaxSeekToPreviousPosition() {
        verifyApplicationThread();
        if (isConnected()) {
            return this.impl.getMaxSeekToPreviousPosition();
        }
        return 0;
    }

    public final void seekToNext() {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring seekToNext().");
        } else {
            this.impl.seekToNext();
        }
    }

    public final int getRepeatMode() {
        verifyApplicationThread();
        if (isConnected()) {
            return this.impl.getRepeatMode();
        }
        return 0;
    }

    public final void setRepeatMode(int i) {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring setRepeatMode().");
        } else {
            this.impl.setRepeatMode(i);
        }
    }

    public final boolean getShuffleModeEnabled() {
        verifyApplicationThread();
        return isConnected() && this.impl.getShuffleModeEnabled();
    }

    public final void setShuffleModeEnabled(boolean z) {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring setShuffleMode().");
        } else {
            this.impl.setShuffleModeEnabled(z);
        }
    }

    public final VideoSize getVideoSize() {
        verifyApplicationThread();
        return isConnected() ? this.impl.getVideoSize() : VideoSize.UNKNOWN;
    }

    public final Size getSurfaceSize() {
        verifyApplicationThread();
        return isConnected() ? this.impl.getSurfaceSize() : Size.UNKNOWN;
    }

    public final void clearVideoSurface() {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring clearVideoSurface().");
        } else {
            this.impl.clearVideoSurface();
        }
    }

    public final void clearVideoSurface(Surface surface) {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring clearVideoSurface().");
        } else {
            this.impl.clearVideoSurface(surface);
        }
    }

    public final void setVideoSurface(Surface surface) {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring setVideoSurface().");
        } else {
            this.impl.setVideoSurface(surface);
        }
    }

    public final void setVideoSurfaceHolder(SurfaceHolder surfaceHolder) {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring setVideoSurfaceHolder().");
        } else {
            this.impl.setVideoSurfaceHolder(surfaceHolder);
        }
    }

    public final void clearVideoSurfaceHolder(SurfaceHolder surfaceHolder) {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring clearVideoSurfaceHolder().");
        } else {
            this.impl.clearVideoSurfaceHolder(surfaceHolder);
        }
    }

    public final void setVideoSurfaceView(SurfaceView surfaceView) {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring setVideoSurfaceView().");
        } else {
            this.impl.setVideoSurfaceView(surfaceView);
        }
    }

    public final void clearVideoSurfaceView(SurfaceView surfaceView) {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring clearVideoSurfaceView().");
        } else {
            this.impl.clearVideoSurfaceView(surfaceView);
        }
    }

    public final void setVideoTextureView(TextureView textureView) {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring setVideoTextureView().");
        } else {
            this.impl.setVideoTextureView(textureView);
        }
    }

    public final void clearVideoTextureView(TextureView textureView) {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring clearVideoTextureView().");
        } else {
            this.impl.clearVideoTextureView(textureView);
        }
    }

    public final CueGroup getCurrentCues() {
        verifyApplicationThread();
        return isConnected() ? this.impl.getCurrentCues() : CueGroup.EMPTY_TIME_ZERO;
    }

    public final float getVolume() {
        verifyApplicationThread();
        if (isConnected()) {
            return this.impl.getVolume();
        }
        return 1.0f;
    }

    public final void setVolume(float f) {
        verifyApplicationThread();
        Assertions.checkArgument(f >= 0.0f && f <= 1.0f, "volume must be between 0 and 1");
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring setVolume().");
        } else {
            this.impl.setVolume(f);
        }
    }

    public final DeviceInfo getDeviceInfo() {
        verifyApplicationThread();
        if (!isConnected()) {
            return DeviceInfo.UNKNOWN;
        }
        return this.impl.getDeviceInfo();
    }

    public final int getDeviceVolume() {
        verifyApplicationThread();
        if (!isConnected()) {
            return 0;
        }
        return this.impl.getDeviceVolume();
    }

    public final boolean isDeviceMuted() {
        verifyApplicationThread();
        if (!isConnected()) {
            return false;
        }
        return this.impl.isDeviceMuted();
    }

    @Deprecated
    public final void setDeviceVolume(int i) {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring setDeviceVolume().");
        } else {
            this.impl.setDeviceVolume(i);
        }
    }

    public final void setDeviceVolume(int i, int i2) {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring setDeviceVolume().");
        } else {
            this.impl.setDeviceVolume(i, i2);
        }
    }

    @Deprecated
    public final void increaseDeviceVolume() {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring increaseDeviceVolume().");
        } else {
            this.impl.increaseDeviceVolume();
        }
    }

    public final void increaseDeviceVolume(int i) {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring increaseDeviceVolume().");
        } else {
            this.impl.increaseDeviceVolume(i);
        }
    }

    @Deprecated
    public final void decreaseDeviceVolume() {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring decreaseDeviceVolume().");
        } else {
            this.impl.decreaseDeviceVolume();
        }
    }

    public final void decreaseDeviceVolume(int i) {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring decreaseDeviceVolume().");
        } else {
            this.impl.decreaseDeviceVolume(i);
        }
    }

    @Deprecated
    public final void setDeviceMuted(boolean z) {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring setDeviceMuted().");
        } else {
            this.impl.setDeviceMuted(z);
        }
    }

    public final void setDeviceMuted(boolean z, int i) {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring setDeviceMuted().");
        } else {
            this.impl.setDeviceMuted(z, i);
        }
    }

    public final void setAudioAttributes(AudioAttributes audioAttributes, boolean z) {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring setAudioAttributes().");
        } else {
            this.impl.setAudioAttributes(audioAttributes, z);
        }
    }

    public final MediaMetadata getMediaMetadata() {
        verifyApplicationThread();
        return isConnected() ? this.impl.getMediaMetadata() : MediaMetadata.EMPTY;
    }

    public final Tracks getCurrentTracks() {
        verifyApplicationThread();
        return isConnected() ? this.impl.getCurrentTracks() : Tracks.EMPTY;
    }

    public final TrackSelectionParameters getTrackSelectionParameters() {
        verifyApplicationThread();
        if (!isConnected()) {
            return TrackSelectionParameters.DEFAULT_WITHOUT_CONTEXT;
        }
        return this.impl.getTrackSelectionParameters();
    }

    public final void setTrackSelectionParameters(TrackSelectionParameters trackSelectionParameters) {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring setTrackSelectionParameters().");
        }
        this.impl.setTrackSelectionParameters(trackSelectionParameters);
    }

    public final Looper getApplicationLooper() {
        return this.applicationHandler.getLooper();
    }

    /* access modifiers changed from: package-private */
    public int getMaxCommandsForMediaItems() {
        return this.maxCommandsForMediaItems;
    }

    /* access modifiers changed from: package-private */
    public final long getTimeDiffMs() {
        return this.timeDiffMs;
    }

    /* access modifiers changed from: package-private */
    public final void setTimeDiffMs(long j) {
        verifyApplicationThread();
        this.timeDiffMs = j;
    }

    public final void addListener(Player.Listener listener2) {
        Assertions.checkNotNull(listener2, "listener must not be null");
        this.impl.addListener(listener2);
    }

    public final void removeListener(Player.Listener listener2) {
        verifyApplicationThread();
        Assertions.checkNotNull(listener2, "listener must not be null");
        this.impl.removeListener(listener2);
    }

    public final boolean isCommandAvailable(int i) {
        return getAvailableCommands().contains(i);
    }

    public final Player.Commands getAvailableCommands() {
        verifyApplicationThread();
        if (!isConnected()) {
            return Player.Commands.EMPTY;
        }
        return this.impl.getAvailableCommands();
    }

    public final boolean isSessionCommandAvailable(int i) {
        return getAvailableSessionCommands().contains(i);
    }

    public final boolean isSessionCommandAvailable(SessionCommand sessionCommand) {
        return getAvailableSessionCommands().contains(sessionCommand);
    }

    public final SessionCommands getAvailableSessionCommands() {
        verifyApplicationThread();
        if (!isConnected()) {
            return SessionCommands.EMPTY;
        }
        return this.impl.getAvailableSessionCommands();
    }

    private static ListenableFuture<SessionResult> createDisconnectedFuture() {
        return Futures.immediateFuture(new SessionResult(-100));
    }

    /* access modifiers changed from: package-private */
    public final void runOnApplicationLooper(Runnable runnable) {
        Util.postOrRun(this.applicationHandler, runnable);
    }

    /* access modifiers changed from: package-private */
    public final void notifyControllerListener(Consumer<Listener> consumer) {
        Assertions.checkState(Looper.myLooper() == getApplicationLooper());
        consumer.accept(this.listener);
    }

    /* access modifiers changed from: package-private */
    public final void notifyAccepted() {
        Assertions.checkState(Looper.myLooper() == getApplicationLooper());
        Assertions.checkState(!this.connectionNotified);
        this.connectionNotified = true;
        this.connectionCallback.onAccepted();
    }

    /* access modifiers changed from: package-private */
    public final IMediaController getBinder() {
        return this.impl.getBinder();
    }

    /* access modifiers changed from: package-private */
    public Bundle getConnectionHints() {
        return this.impl.getConnectionHints();
    }

    private void verifyApplicationThread() {
        Assertions.checkState(Looper.myLooper() == getApplicationLooper(), WRONG_THREAD_ERROR_MESSAGE);
    }
}
