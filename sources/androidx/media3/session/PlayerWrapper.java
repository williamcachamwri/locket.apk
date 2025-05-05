package androidx.media3.session;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.C;
import androidx.media3.common.DeviceInfo;
import androidx.media3.common.ForwardingPlayer;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.Player;
import androidx.media3.common.Timeline;
import androidx.media3.common.TrackSelectionParameters;
import androidx.media3.common.Tracks;
import androidx.media3.common.VideoSize;
import androidx.media3.common.text.CueGroup;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Size;
import androidx.media3.common.util.Util;
import androidx.media3.session.legacy.PlaybackStateCompat;
import androidx.media3.session.legacy.VolumeProviderCompat;
import com.google.common.collect.ImmutableList;
import java.util.List;

final class PlayerWrapper extends ForwardingPlayer {
    private Player.Commands availablePlayerCommands;
    private SessionCommands availableSessionCommands;
    private ImmutableList<CommandButton> customLayout;
    private LegacyError legacyError;
    private Bundle legacyExtras;
    private ImmutableList<CommandButton> mediaButtonPreferences;
    private final boolean playIfSuppressed;

    private static long convertCommandToPlaybackStateActions(int i) {
        if (i == 1) {
            return 518;
        }
        if (i == 2) {
            return 16384;
        }
        if (i == 3) {
            return 1;
        }
        if (i == 31) {
            return 240640;
        }
        switch (i) {
            case 5:
                return 256;
            case 6:
            case 7:
                return 16;
            case 8:
            case 9:
                return 32;
            case 10:
                return 4096;
            case 11:
                return 8;
            case 12:
                return 64;
            case 13:
                return 4194304;
            case 14:
                return 2621440;
            case 15:
                return 262144;
            default:
                return 0;
        }
    }

    public static final class LegacyError {
        public final int code;
        public final Bundle extras;
        public final boolean isFatal;
        public final String message;

        private LegacyError(boolean z, int i, String str, Bundle bundle) {
            this.isFatal = z;
            this.code = i;
            this.message = str;
            this.extras = bundle == null ? Bundle.EMPTY : bundle;
        }
    }

    public PlayerWrapper(Player player, boolean z, ImmutableList<CommandButton> immutableList, ImmutableList<CommandButton> immutableList2, SessionCommands sessionCommands, Player.Commands commands, Bundle bundle) {
        super(player);
        this.playIfSuppressed = z;
        this.customLayout = immutableList;
        this.mediaButtonPreferences = immutableList2;
        this.availableSessionCommands = sessionCommands;
        this.availablePlayerCommands = commands;
        this.legacyExtras = bundle;
    }

    public void setAvailableCommands(SessionCommands sessionCommands, Player.Commands commands) {
        this.availableSessionCommands = sessionCommands;
        this.availablePlayerCommands = commands;
    }

    public SessionCommands getAvailableSessionCommands() {
        return this.availableSessionCommands;
    }

    public Player.Commands getAvailablePlayerCommands() {
        return this.availablePlayerCommands;
    }

    public void setCustomLayout(ImmutableList<CommandButton> immutableList) {
        this.customLayout = immutableList;
    }

    public void setMediaButtonPreferences(ImmutableList<CommandButton> immutableList) {
        this.mediaButtonPreferences = immutableList;
    }

    /* access modifiers changed from: package-private */
    public ImmutableList<CommandButton> getCustomLayout() {
        return this.customLayout;
    }

    /* access modifiers changed from: package-private */
    public ImmutableList<CommandButton> getMediaButtonPreferences() {
        return this.mediaButtonPreferences;
    }

    public void setLegacyExtras(Bundle bundle) {
        Assertions.checkArgument(!bundle.containsKey(MediaConstants.EXTRAS_KEY_PLAYBACK_SPEED_COMPAT));
        Assertions.checkArgument(!bundle.containsKey("androidx.media.PlaybackStateCompat.Extras.KEY_MEDIA_ID"));
        this.legacyExtras = bundle;
    }

    public Bundle getLegacyExtras() {
        return this.legacyExtras;
    }

    public void setLegacyError(boolean z, int i, String str, Bundle bundle) {
        this.legacyError = new LegacyError(z, i, str, bundle);
    }

    public LegacyError getLegacyError() {
        return this.legacyError;
    }

    public void clearLegacyErrorStatus() {
        this.legacyError = null;
    }

    public void addListener(Player.Listener listener) {
        verifyApplicationThread();
        super.addListener(listener);
    }

    public void removeListener(Player.Listener listener) {
        verifyApplicationThread();
        super.removeListener(listener);
    }

    public PlaybackException getPlayerError() {
        verifyApplicationThread();
        return super.getPlayerError();
    }

    public void play() {
        verifyApplicationThread();
        super.play();
    }

    public void playIfCommandAvailable() {
        if (isCommandAvailable(1)) {
            play();
        }
    }

    public void pause() {
        verifyApplicationThread();
        super.pause();
    }

    public void prepare() {
        verifyApplicationThread();
        super.prepare();
    }

    public void prepareIfCommandAvailable() {
        if (isCommandAvailable(2)) {
            prepare();
        }
    }

    public void stop() {
        verifyApplicationThread();
        super.stop();
    }

    public void release() {
        verifyApplicationThread();
        super.release();
    }

    public void seekToDefaultPosition(int i) {
        verifyApplicationThread();
        super.seekToDefaultPosition(i);
    }

    public void seekToDefaultPosition() {
        verifyApplicationThread();
        super.seekToDefaultPosition();
    }

    public void seekToDefaultPositionIfCommandAvailable() {
        if (isCommandAvailable(4)) {
            seekToDefaultPosition();
        }
    }

    public void seekTo(long j) {
        verifyApplicationThread();
        super.seekTo(j);
    }

    public void seekTo(int i, long j) {
        verifyApplicationThread();
        super.seekTo(i, j);
    }

    public long getSeekBackIncrement() {
        verifyApplicationThread();
        return super.getSeekBackIncrement();
    }

    public void seekBack() {
        verifyApplicationThread();
        super.seekBack();
    }

    public long getSeekForwardIncrement() {
        verifyApplicationThread();
        return super.getSeekForwardIncrement();
    }

    public void seekForward() {
        verifyApplicationThread();
        super.seekForward();
    }

    public void setPlaybackParameters(PlaybackParameters playbackParameters) {
        verifyApplicationThread();
        super.setPlaybackParameters(playbackParameters);
    }

    public void setPlaybackSpeed(float f) {
        verifyApplicationThread();
        super.setPlaybackSpeed(f);
    }

    public long getCurrentPosition() {
        verifyApplicationThread();
        return super.getCurrentPosition();
    }

    public long getDuration() {
        verifyApplicationThread();
        return super.getDuration();
    }

    public long getDurationWithCommandCheck() {
        return isCommandAvailable(16) ? getDuration() : C.TIME_UNSET;
    }

    public long getBufferedPosition() {
        verifyApplicationThread();
        return super.getBufferedPosition();
    }

    public int getBufferedPercentage() {
        verifyApplicationThread();
        return super.getBufferedPercentage();
    }

    public long getTotalBufferedDuration() {
        verifyApplicationThread();
        return super.getTotalBufferedDuration();
    }

    public long getCurrentLiveOffset() {
        verifyApplicationThread();
        return super.getCurrentLiveOffset();
    }

    public long getContentDuration() {
        verifyApplicationThread();
        return super.getContentDuration();
    }

    public long getContentPosition() {
        verifyApplicationThread();
        return super.getContentPosition();
    }

    public long getContentBufferedPosition() {
        verifyApplicationThread();
        return super.getContentBufferedPosition();
    }

    public boolean isPlayingAd() {
        verifyApplicationThread();
        return super.isPlayingAd();
    }

    public int getCurrentAdGroupIndex() {
        verifyApplicationThread();
        return super.getCurrentAdGroupIndex();
    }

    public int getCurrentAdIndexInAdGroup() {
        verifyApplicationThread();
        return super.getCurrentAdIndexInAdGroup();
    }

    public PlaybackParameters getPlaybackParameters() {
        verifyApplicationThread();
        return super.getPlaybackParameters();
    }

    public VideoSize getVideoSize() {
        verifyApplicationThread();
        return super.getVideoSize();
    }

    public void clearVideoSurface() {
        verifyApplicationThread();
        super.clearVideoSurface();
    }

    public void clearVideoSurface(Surface surface) {
        verifyApplicationThread();
        super.clearVideoSurface(surface);
    }

    public void setVideoSurface(Surface surface) {
        verifyApplicationThread();
        super.setVideoSurface(surface);
    }

    public void setVideoSurfaceHolder(SurfaceHolder surfaceHolder) {
        verifyApplicationThread();
        super.setVideoSurfaceHolder(surfaceHolder);
    }

    public void clearVideoSurfaceHolder(SurfaceHolder surfaceHolder) {
        verifyApplicationThread();
        super.clearVideoSurfaceHolder(surfaceHolder);
    }

    public void setVideoSurfaceView(SurfaceView surfaceView) {
        verifyApplicationThread();
        super.setVideoSurfaceView(surfaceView);
    }

    public void clearVideoSurfaceView(SurfaceView surfaceView) {
        verifyApplicationThread();
        super.clearVideoSurfaceView(surfaceView);
    }

    public void setVideoTextureView(TextureView textureView) {
        verifyApplicationThread();
        super.setVideoTextureView(textureView);
    }

    public void clearVideoTextureView(TextureView textureView) {
        verifyApplicationThread();
        super.clearVideoTextureView(textureView);
    }

    public AudioAttributes getAudioAttributes() {
        verifyApplicationThread();
        return super.getAudioAttributes();
    }

    public AudioAttributes getAudioAttributesWithCommandCheck() {
        if (isCommandAvailable(21)) {
            return getAudioAttributes();
        }
        return AudioAttributes.DEFAULT;
    }

    public void setMediaItem(MediaItem mediaItem) {
        verifyApplicationThread();
        super.setMediaItem(mediaItem);
    }

    public void setMediaItem(MediaItem mediaItem, long j) {
        verifyApplicationThread();
        super.setMediaItem(mediaItem, j);
    }

    public void setMediaItem(MediaItem mediaItem, boolean z) {
        verifyApplicationThread();
        super.setMediaItem(mediaItem, z);
    }

    public void setMediaItems(List<MediaItem> list) {
        verifyApplicationThread();
        super.setMediaItems(list);
    }

    public void setMediaItems(List<MediaItem> list, boolean z) {
        verifyApplicationThread();
        super.setMediaItems(list, z);
    }

    public void setMediaItems(List<MediaItem> list, int i, long j) {
        verifyApplicationThread();
        super.setMediaItems(list, i, j);
    }

    public void addMediaItem(MediaItem mediaItem) {
        verifyApplicationThread();
        super.addMediaItem(mediaItem);
    }

    public void addMediaItem(int i, MediaItem mediaItem) {
        verifyApplicationThread();
        super.addMediaItem(i, mediaItem);
    }

    public void addMediaItems(List<MediaItem> list) {
        verifyApplicationThread();
        super.addMediaItems(list);
    }

    public void addMediaItems(int i, List<MediaItem> list) {
        verifyApplicationThread();
        super.addMediaItems(i, list);
    }

    public void clearMediaItems() {
        verifyApplicationThread();
        super.clearMediaItems();
    }

    public void removeMediaItem(int i) {
        verifyApplicationThread();
        super.removeMediaItem(i);
    }

    public void removeMediaItems(int i, int i2) {
        verifyApplicationThread();
        super.removeMediaItems(i, i2);
    }

    public void moveMediaItem(int i, int i2) {
        verifyApplicationThread();
        super.moveMediaItem(i, i2);
    }

    public void moveMediaItems(int i, int i2, int i3) {
        verifyApplicationThread();
        super.moveMediaItems(i, i2, i3);
    }

    public void replaceMediaItem(int i, MediaItem mediaItem) {
        verifyApplicationThread();
        super.replaceMediaItem(i, mediaItem);
    }

    public void replaceMediaItems(int i, int i2, List<MediaItem> list) {
        verifyApplicationThread();
        super.replaceMediaItems(i, i2, list);
    }

    @Deprecated
    public boolean hasNext() {
        verifyApplicationThread();
        return super.hasNext();
    }

    @Deprecated
    public boolean hasNextWindow() {
        verifyApplicationThread();
        return super.hasNextWindow();
    }

    public boolean hasPreviousMediaItem() {
        verifyApplicationThread();
        return super.hasPreviousMediaItem();
    }

    public boolean hasNextMediaItem() {
        verifyApplicationThread();
        return super.hasNextMediaItem();
    }

    @Deprecated
    public void next() {
        verifyApplicationThread();
        super.next();
    }

    @Deprecated
    public void seekToPreviousWindow() {
        verifyApplicationThread();
        super.seekToPreviousWindow();
    }

    @Deprecated
    public void seekToNextWindow() {
        verifyApplicationThread();
        super.seekToNextWindow();
    }

    public void seekToPreviousMediaItem() {
        verifyApplicationThread();
        super.seekToPreviousMediaItem();
    }

    public void seekToNextMediaItem() {
        verifyApplicationThread();
        super.seekToNextMediaItem();
    }

    public void setPlaylistMetadata(MediaMetadata mediaMetadata) {
        verifyApplicationThread();
        super.setPlaylistMetadata(mediaMetadata);
    }

    public void setRepeatMode(int i) {
        verifyApplicationThread();
        super.setRepeatMode(i);
    }

    public void setShuffleModeEnabled(boolean z) {
        verifyApplicationThread();
        super.setShuffleModeEnabled(z);
    }

    public Timeline getCurrentTimeline() {
        verifyApplicationThread();
        return super.getCurrentTimeline();
    }

    public Timeline getCurrentTimelineWithCommandCheck() {
        if (isCommandAvailable(17)) {
            return getCurrentTimeline();
        }
        if (isCommandAvailable(16)) {
            return new CurrentMediaItemOnlyTimeline(this);
        }
        return Timeline.EMPTY;
    }

    public MediaMetadata getPlaylistMetadata() {
        verifyApplicationThread();
        return super.getPlaylistMetadata();
    }

    public MediaMetadata getPlaylistMetadataWithCommandCheck() {
        if (isCommandAvailable(18)) {
            return getPlaylistMetadata();
        }
        return MediaMetadata.EMPTY;
    }

    public int getRepeatMode() {
        verifyApplicationThread();
        return super.getRepeatMode();
    }

    public boolean getShuffleModeEnabled() {
        verifyApplicationThread();
        return super.getShuffleModeEnabled();
    }

    public MediaItem getCurrentMediaItem() {
        verifyApplicationThread();
        return super.getCurrentMediaItem();
    }

    public MediaItem getCurrentMediaItemWithCommandCheck() {
        if (isCommandAvailable(16)) {
            return getCurrentMediaItem();
        }
        return null;
    }

    public int getMediaItemCount() {
        verifyApplicationThread();
        return super.getMediaItemCount();
    }

    public MediaItem getMediaItemAt(int i) {
        verifyApplicationThread();
        return super.getMediaItemAt(i);
    }

    @Deprecated
    public int getCurrentWindowIndex() {
        verifyApplicationThread();
        return super.getCurrentWindowIndex();
    }

    public int getCurrentMediaItemIndex() {
        verifyApplicationThread();
        return super.getCurrentMediaItemIndex();
    }

    @Deprecated
    public int getPreviousWindowIndex() {
        verifyApplicationThread();
        return super.getPreviousWindowIndex();
    }

    public int getPreviousMediaItemIndex() {
        verifyApplicationThread();
        return super.getPreviousMediaItemIndex();
    }

    @Deprecated
    public int getNextWindowIndex() {
        verifyApplicationThread();
        return super.getNextWindowIndex();
    }

    public int getNextMediaItemIndex() {
        verifyApplicationThread();
        return super.getNextMediaItemIndex();
    }

    public float getVolume() {
        verifyApplicationThread();
        return super.getVolume();
    }

    public float getVolumeWithCommandCheck() {
        if (isCommandAvailable(22)) {
            return getVolume();
        }
        return 0.0f;
    }

    public void setVolume(float f) {
        verifyApplicationThread();
        super.setVolume(f);
    }

    public CueGroup getCurrentCues() {
        verifyApplicationThread();
        return super.getCurrentCues();
    }

    public CueGroup getCurrentCuesWithCommandCheck() {
        return isCommandAvailable(28) ? getCurrentCues() : CueGroup.EMPTY_TIME_ZERO;
    }

    public DeviceInfo getDeviceInfo() {
        verifyApplicationThread();
        return super.getDeviceInfo();
    }

    public int getDeviceVolume() {
        verifyApplicationThread();
        return super.getDeviceVolume();
    }

    public int getDeviceVolumeWithCommandCheck() {
        if (isCommandAvailable(23)) {
            return getDeviceVolume();
        }
        return 0;
    }

    public boolean isDeviceMuted() {
        verifyApplicationThread();
        return super.isDeviceMuted();
    }

    public boolean isDeviceMutedWithCommandCheck() {
        return isCommandAvailable(23) && isDeviceMuted();
    }

    @Deprecated
    public void setDeviceVolume(int i) {
        verifyApplicationThread();
        super.setDeviceVolume(i);
    }

    public void setDeviceVolume(int i, int i2) {
        verifyApplicationThread();
        super.setDeviceVolume(i, i2);
    }

    @Deprecated
    public void increaseDeviceVolume() {
        verifyApplicationThread();
        super.increaseDeviceVolume();
    }

    public void increaseDeviceVolume(int i) {
        verifyApplicationThread();
        super.increaseDeviceVolume(i);
    }

    @Deprecated
    public void decreaseDeviceVolume() {
        verifyApplicationThread();
        super.decreaseDeviceVolume();
    }

    public void decreaseDeviceVolume(int i) {
        verifyApplicationThread();
        super.decreaseDeviceVolume(i);
    }

    @Deprecated
    public void setDeviceMuted(boolean z) {
        verifyApplicationThread();
        super.setDeviceMuted(z);
    }

    public void setDeviceMuted(boolean z, int i) {
        verifyApplicationThread();
        super.setDeviceMuted(z, i);
    }

    public void setPlayWhenReady(boolean z) {
        verifyApplicationThread();
        super.setPlayWhenReady(z);
    }

    public boolean getPlayWhenReady() {
        verifyApplicationThread();
        return super.getPlayWhenReady();
    }

    public int getPlaybackSuppressionReason() {
        verifyApplicationThread();
        return super.getPlaybackSuppressionReason();
    }

    public int getPlaybackState() {
        verifyApplicationThread();
        return super.getPlaybackState();
    }

    public boolean isPlaying() {
        verifyApplicationThread();
        return super.isPlaying();
    }

    public boolean isLoading() {
        verifyApplicationThread();
        return super.isLoading();
    }

    public MediaMetadata getMediaMetadata() {
        verifyApplicationThread();
        return super.getMediaMetadata();
    }

    public MediaMetadata getMediaMetadataWithCommandCheck() {
        return isCommandAvailable(18) ? getMediaMetadata() : MediaMetadata.EMPTY;
    }

    public boolean isCommandAvailable(int i) {
        verifyApplicationThread();
        return super.isCommandAvailable(i);
    }

    public Player.Commands getAvailableCommands() {
        verifyApplicationThread();
        return super.getAvailableCommands();
    }

    public TrackSelectionParameters getTrackSelectionParameters() {
        verifyApplicationThread();
        return super.getTrackSelectionParameters();
    }

    public void setTrackSelectionParameters(TrackSelectionParameters trackSelectionParameters) {
        verifyApplicationThread();
        super.setTrackSelectionParameters(trackSelectionParameters);
    }

    public void seekToPrevious() {
        verifyApplicationThread();
        super.seekToPrevious();
    }

    public long getMaxSeekToPreviousPosition() {
        verifyApplicationThread();
        return super.getMaxSeekToPreviousPosition();
    }

    public void seekToNext() {
        verifyApplicationThread();
        super.seekToNext();
    }

    public Tracks getCurrentTracks() {
        verifyApplicationThread();
        return super.getCurrentTracks();
    }

    public Tracks getCurrentTracksWithCommandCheck() {
        return isCommandAvailable(30) ? getCurrentTracks() : Tracks.EMPTY;
    }

    public Object getCurrentManifest() {
        verifyApplicationThread();
        return super.getCurrentManifest();
    }

    public int getCurrentPeriodIndex() {
        verifyApplicationThread();
        return super.getCurrentPeriodIndex();
    }

    public boolean isCurrentMediaItemDynamic() {
        verifyApplicationThread();
        return super.isCurrentMediaItemDynamic();
    }

    public boolean isCurrentMediaItemLive() {
        verifyApplicationThread();
        return super.isCurrentMediaItemLive();
    }

    public boolean isCurrentMediaItemLiveWithCommandCheck() {
        return isCommandAvailable(16) && isCurrentMediaItemLive();
    }

    public boolean isCurrentMediaItemSeekable() {
        verifyApplicationThread();
        return super.isCurrentMediaItemSeekable();
    }

    public Size getSurfaceSize() {
        verifyApplicationThread();
        return super.getSurfaceSize();
    }

    public PlaybackStateCompat createPlaybackStateCompat() {
        Bundle bundle;
        LegacyError legacyError2 = this.legacyError;
        if (legacyError2 == null || !legacyError2.isFatal) {
            PlaybackException playerError = getPlayerError();
            int convertToPlaybackStateCompatState = LegacyConversions.convertToPlaybackStateCompatState(this, this.playIfSuppressed);
            Player.Commands intersect = MediaUtils.intersect(this.availablePlayerCommands, getAvailableCommands());
            long j = 128;
            for (int i = 0; i < intersect.size(); i++) {
                j |= convertCommandToPlaybackStateActions(intersect.get(i));
            }
            long j2 = -1;
            long convertToQueueItemId = isCommandAvailable(17) ? LegacyConversions.convertToQueueItemId(getCurrentMediaItemIndex()) : -1;
            float f = getPlaybackParameters().speed;
            float f2 = isPlaying() ? f : 0.0f;
            if (legacyError2 != null) {
                Bundle bundle2 = legacyError2.extras;
            } else {
                bundle = new Bundle();
            }
            Bundle bundle3 = bundle;
            bundle3.putAll(this.legacyExtras);
            bundle3.putFloat(MediaConstants.EXTRAS_KEY_PLAYBACK_SPEED_COMPAT, f);
            MediaItem currentMediaItemWithCommandCheck = getCurrentMediaItemWithCommandCheck();
            if (currentMediaItemWithCommandCheck != null && !"".equals(currentMediaItemWithCommandCheck.mediaId)) {
                bundle3.putString("androidx.media.PlaybackStateCompat.Extras.KEY_MEDIA_ID", currentMediaItemWithCommandCheck.mediaId);
            }
            boolean isCommandAvailable = isCommandAvailable(16);
            if (isCommandAvailable) {
                j2 = getCurrentPosition();
            }
            PlaybackStateCompat.Builder extras = new PlaybackStateCompat.Builder().setState(convertToPlaybackStateCompatState, j2, f2, SystemClock.elapsedRealtime()).setActions(j).setActiveQueueItemId(convertToQueueItemId).setBufferedPosition(isCommandAvailable ? getBufferedPosition() : 0).setExtras(bundle3);
            ImmutableList<CommandButton> immutableList = this.mediaButtonPreferences.isEmpty() ? this.customLayout : this.mediaButtonPreferences;
            for (int i2 = 0; i2 < immutableList.size(); i2++) {
                CommandButton commandButton = immutableList.get(i2);
                SessionCommand sessionCommand = commandButton.sessionCommand;
                if (sessionCommand != null && commandButton.isEnabled && sessionCommand.commandCode == 0 && CommandButton.isButtonCommandAvailable(commandButton, this.availableSessionCommands, this.availablePlayerCommands)) {
                    Bundle bundle4 = sessionCommand.customExtras;
                    if (commandButton.icon != 0) {
                        bundle4 = new Bundle(sessionCommand.customExtras);
                        bundle4.putInt(MediaConstants.EXTRAS_KEY_COMMAND_BUTTON_ICON_COMPAT, commandButton.icon);
                    }
                    extras.addCustomAction(new PlaybackStateCompat.CustomAction.Builder(sessionCommand.customAction, commandButton.displayName, commandButton.iconResId).setExtras(bundle4).build());
                }
            }
            if (playerError != null) {
                extras.setErrorMessage(LegacyConversions.convertToLegacyErrorCode(playerError), playerError.getMessage());
            } else if (legacyError2 != null) {
                extras.setErrorMessage(legacyError2.code, legacyError2.message);
            }
            return extras.build();
        }
        Bundle bundle5 = new Bundle(legacyError2.extras);
        bundle5.putAll(this.legacyExtras);
        return new PlaybackStateCompat.Builder().setState(7, -1, 0.0f, SystemClock.elapsedRealtime()).setActions(0).setBufferedPosition(0).setExtras(bundle5).setErrorMessage(legacyError2.code, (CharSequence) Assertions.checkNotNull(legacyError2.message)).setExtras(legacyError2.extras).build();
    }

    public VolumeProviderCompat createVolumeProviderCompat() {
        if (getDeviceInfo().playbackType == 0) {
            return null;
        }
        Player.Commands availableCommands = getAvailableCommands();
        int i = availableCommands.containsAny(26, 34) ? availableCommands.containsAny(25, 33) ? 2 : 1 : 0;
        final Handler handler = new Handler(getApplicationLooper());
        int deviceVolumeWithCommandCheck = getDeviceVolumeWithCommandCheck();
        DeviceInfo deviceInfo = getDeviceInfo();
        return new VolumeProviderCompat(i, deviceInfo.maxVolume, deviceVolumeWithCommandCheck, deviceInfo.routingControllerId, 1) {
            public void onSetVolumeTo(int i) {
                Util.postOrRun(handler, new PlayerWrapper$1$$ExternalSyntheticLambda1(this, i, 1));
            }

            /* access modifiers changed from: package-private */
            /* renamed from: lambda$onSetVolumeTo$0$androidx-media3-session-PlayerWrapper$1  reason: not valid java name */
            public /* synthetic */ void m1118lambda$onSetVolumeTo$0$androidxmedia3sessionPlayerWrapper$1(int i, int i2) {
                if (!PlayerWrapper.this.isCommandAvailable(25) && !PlayerWrapper.this.isCommandAvailable(33)) {
                    return;
                }
                if (PlayerWrapper.this.isCommandAvailable(33)) {
                    PlayerWrapper.this.setDeviceVolume(i, i2);
                } else {
                    PlayerWrapper.this.setDeviceVolume(i);
                }
            }

            public void onAdjustVolume(int i) {
                Util.postOrRun(handler, new PlayerWrapper$1$$ExternalSyntheticLambda0(this, i, 1));
            }

            /* access modifiers changed from: package-private */
            /* renamed from: lambda$onAdjustVolume$1$androidx-media3-session-PlayerWrapper$1  reason: not valid java name */
            public /* synthetic */ void m1117lambda$onAdjustVolume$1$androidxmedia3sessionPlayerWrapper$1(int i, int i2) {
                if (!PlayerWrapper.this.isCommandAvailable(26) && !PlayerWrapper.this.isCommandAvailable(34)) {
                    return;
                }
                if (i != -100) {
                    if (i != -1) {
                        if (i != 1) {
                            if (i != 100) {
                                if (i != 101) {
                                    Log.w("VolumeProviderCompat", "onAdjustVolume: Ignoring unknown direction: " + i);
                                } else if (PlayerWrapper.this.isCommandAvailable(34)) {
                                    PlayerWrapper playerWrapper = PlayerWrapper.this;
                                    playerWrapper.setDeviceMuted(!playerWrapper.isDeviceMutedWithCommandCheck(), i2);
                                } else {
                                    PlayerWrapper playerWrapper2 = PlayerWrapper.this;
                                    playerWrapper2.setDeviceMuted(!playerWrapper2.isDeviceMutedWithCommandCheck());
                                }
                            } else if (PlayerWrapper.this.isCommandAvailable(34)) {
                                PlayerWrapper.this.setDeviceMuted(false, i2);
                            } else {
                                PlayerWrapper.this.setDeviceMuted(false);
                            }
                        } else if (PlayerWrapper.this.isCommandAvailable(34)) {
                            PlayerWrapper.this.increaseDeviceVolume(i2);
                        } else {
                            PlayerWrapper.this.increaseDeviceVolume();
                        }
                    } else if (PlayerWrapper.this.isCommandAvailable(34)) {
                        PlayerWrapper.this.decreaseDeviceVolume(i2);
                    } else {
                        PlayerWrapper.this.decreaseDeviceVolume();
                    }
                } else if (PlayerWrapper.this.isCommandAvailable(34)) {
                    PlayerWrapper.this.setDeviceMuted(true, i2);
                } else {
                    PlayerWrapper.this.setDeviceMuted(true);
                }
            }
        };
    }

    public Player.PositionInfo createPositionInfoForBundling() {
        boolean isCommandAvailable = isCommandAvailable(16);
        boolean isCommandAvailable2 = isCommandAvailable(17);
        int currentMediaItemIndex = isCommandAvailable2 ? getCurrentMediaItemIndex() : 0;
        MediaItem currentMediaItem = isCommandAvailable ? getCurrentMediaItem() : null;
        int currentPeriodIndex = isCommandAvailable2 ? getCurrentPeriodIndex() : 0;
        long j = 0;
        long currentPosition = isCommandAvailable ? getCurrentPosition() : 0;
        if (isCommandAvailable) {
            j = getContentPosition();
        }
        return new Player.PositionInfo((Object) null, currentMediaItemIndex, currentMediaItem, (Object) null, currentPeriodIndex, currentPosition, j, isCommandAvailable ? getCurrentAdGroupIndex() : -1, isCommandAvailable ? getCurrentAdIndexInAdGroup() : -1);
    }

    public SessionPositionInfo createSessionPositionInfoForBundling() {
        boolean isCommandAvailable = isCommandAvailable(16);
        Player.PositionInfo createPositionInfoForBundling = createPositionInfoForBundling();
        int i = 0;
        boolean z = isCommandAvailable && isPlayingAd();
        long elapsedRealtime = SystemClock.elapsedRealtime();
        long j = C.TIME_UNSET;
        long duration = isCommandAvailable ? getDuration() : -9223372036854775807L;
        long bufferedPosition = isCommandAvailable ? getBufferedPosition() : 0;
        if (isCommandAvailable) {
            i = getBufferedPercentage();
        }
        int i2 = i;
        long totalBufferedDuration = isCommandAvailable ? getTotalBufferedDuration() : 0;
        long currentLiveOffset = isCommandAvailable ? getCurrentLiveOffset() : -9223372036854775807L;
        if (isCommandAvailable) {
            j = getContentDuration();
        }
        return new SessionPositionInfo(createPositionInfoForBundling, z, elapsedRealtime, duration, bufferedPosition, i2, totalBufferedDuration, currentLiveOffset, j, isCommandAvailable ? getContentBufferedPosition() : 0);
    }

    public PlayerInfo createPlayerInfoForBundling() {
        return new PlayerInfo(getPlayerError(), 0, createSessionPositionInfoForBundling(), createPositionInfoForBundling(), createPositionInfoForBundling(), 0, getPlaybackParameters(), getRepeatMode(), getShuffleModeEnabled(), getVideoSize(), getCurrentTimelineWithCommandCheck(), 0, getPlaylistMetadataWithCommandCheck(), getVolumeWithCommandCheck(), getAudioAttributesWithCommandCheck(), getCurrentCuesWithCommandCheck(), getDeviceInfo(), getDeviceVolumeWithCommandCheck(), isDeviceMutedWithCommandCheck(), getPlayWhenReady(), 1, getPlaybackSuppressionReason(), getPlaybackState(), isPlaying(), isLoading(), getMediaMetadataWithCommandCheck(), getSeekBackIncrement(), getSeekForwardIncrement(), getMaxSeekToPreviousPosition(), getCurrentTracksWithCommandCheck(), getTrackSelectionParameters());
    }

    private void verifyApplicationThread() {
        Assertions.checkState(Looper.myLooper() == getApplicationLooper());
    }

    private static final class CurrentMediaItemOnlyTimeline extends Timeline {
        private static final Object UID = new Object();
        private final long durationUs;
        private final boolean isDynamic;
        private final boolean isSeekable;
        private final MediaItem.LiveConfiguration liveConfiguration;
        private final MediaItem mediaItem;

        public int getPeriodCount() {
            return 1;
        }

        public int getWindowCount() {
            return 1;
        }

        public CurrentMediaItemOnlyTimeline(PlayerWrapper playerWrapper) {
            this.mediaItem = playerWrapper.getCurrentMediaItem();
            this.isSeekable = playerWrapper.isCurrentMediaItemSeekable();
            this.isDynamic = playerWrapper.isCurrentMediaItemDynamic();
            this.liveConfiguration = playerWrapper.isCurrentMediaItemLive() ? MediaItem.LiveConfiguration.UNSET : null;
            this.durationUs = Util.msToUs(playerWrapper.getContentDuration());
        }

        public Timeline.Window getWindow(int i, Timeline.Window window, long j) {
            window.set(UID, this.mediaItem, (Object) null, C.TIME_UNSET, C.TIME_UNSET, C.TIME_UNSET, this.isSeekable, this.isDynamic, this.liveConfiguration, 0, this.durationUs, 0, 0, 0);
            return window;
        }

        public Timeline.Period getPeriod(int i, Timeline.Period period, boolean z) {
            Object obj = UID;
            period.set(obj, obj, 0, this.durationUs, 0);
            return period;
        }

        public int getIndexOfPeriod(Object obj) {
            return UID.equals(obj) ? 0 : -1;
        }

        public Object getUidOfPeriod(int i) {
            return UID;
        }
    }
}
