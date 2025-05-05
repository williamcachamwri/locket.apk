package androidx.media3.session;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.C;
import androidx.media3.common.DeviceInfo;
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
import androidx.media3.common.util.Util;
import com.google.common.base.Objects;

class PlayerInfo {
    public static final PlayerInfo DEFAULT = new PlayerInfo((PlaybackException) null, 0, SessionPositionInfo.DEFAULT, SessionPositionInfo.DEFAULT_POSITION_INFO, SessionPositionInfo.DEFAULT_POSITION_INFO, 0, PlaybackParameters.DEFAULT, 0, false, VideoSize.UNKNOWN, Timeline.EMPTY, 0, MediaMetadata.EMPTY, 1.0f, AudioAttributes.DEFAULT, CueGroup.EMPTY_TIME_ZERO, DeviceInfo.UNKNOWN, 0, false, false, 1, 0, 1, false, false, MediaMetadata.EMPTY, 5000, C.DEFAULT_SEEK_FORWARD_INCREMENT_MS, C.DEFAULT_MAX_SEEK_TO_PREVIOUS_POSITION_MS, Tracks.EMPTY, TrackSelectionParameters.DEFAULT_WITHOUT_CONTEXT);
    public static final int DISCONTINUITY_REASON_DEFAULT = 0;
    private static final String FIELD_AUDIO_ATTRIBUTES = Util.intToStringMaxRadix(8);
    private static final String FIELD_CUE_GROUP = Util.intToStringMaxRadix(24);
    private static final String FIELD_CURRENT_TRACKS = Util.intToStringMaxRadix(30);
    private static final String FIELD_DEVICE_INFO = Util.intToStringMaxRadix(9);
    private static final String FIELD_DEVICE_MUTED = Util.intToStringMaxRadix(11);
    private static final String FIELD_DEVICE_VOLUME = Util.intToStringMaxRadix(10);
    private static final String FIELD_DISCONTINUITY_REASON = Util.intToStringMaxRadix(23);
    private static final String FIELD_IN_PROCESS_BINDER = Util.intToStringMaxRadix(32);
    private static final String FIELD_IS_LOADING = Util.intToStringMaxRadix(17);
    private static final String FIELD_IS_PLAYING = Util.intToStringMaxRadix(16);
    static final String FIELD_MAX_SEEK_TO_PREVIOUS_POSITION_MS = Util.intToStringMaxRadix(28);
    private static final String FIELD_MEDIA_ITEM_TRANSITION_REASON = Util.intToStringMaxRadix(20);
    private static final String FIELD_MEDIA_METADATA = Util.intToStringMaxRadix(25);
    static final String FIELD_NEW_POSITION_INFO = Util.intToStringMaxRadix(22);
    static final String FIELD_OLD_POSITION_INFO = Util.intToStringMaxRadix(21);
    private static final String FIELD_PLAYBACK_ERROR = Util.intToStringMaxRadix(18);
    private static final String FIELD_PLAYBACK_PARAMETERS = Util.intToStringMaxRadix(1);
    private static final String FIELD_PLAYBACK_STATE = Util.intToStringMaxRadix(15);
    private static final String FIELD_PLAYBACK_SUPPRESSION_REASON = Util.intToStringMaxRadix(14);
    private static final String FIELD_PLAYLIST_METADATA = Util.intToStringMaxRadix(6);
    private static final String FIELD_PLAY_WHEN_READY = Util.intToStringMaxRadix(12);
    private static final String FIELD_PLAY_WHEN_READY_CHANGE_REASON = Util.intToStringMaxRadix(13);
    private static final String FIELD_REPEAT_MODE = Util.intToStringMaxRadix(2);
    static final String FIELD_SEEK_BACK_INCREMENT_MS = Util.intToStringMaxRadix(26);
    static final String FIELD_SEEK_FORWARD_INCREMENT_MS = Util.intToStringMaxRadix(27);
    static final String FIELD_SESSION_POSITION_INFO = Util.intToStringMaxRadix(19);
    private static final String FIELD_SHUFFLE_MODE_ENABLED = Util.intToStringMaxRadix(3);
    private static final String FIELD_TIMELINE = Util.intToStringMaxRadix(4);
    private static final String FIELD_TIMELINE_CHANGE_REASON = Util.intToStringMaxRadix(31);
    private static final String FIELD_TRACK_SELECTION_PARAMETERS = Util.intToStringMaxRadix(29);
    private static final String FIELD_VIDEO_SIZE = Util.intToStringMaxRadix(5);
    private static final String FIELD_VOLUME = Util.intToStringMaxRadix(7);
    public static final int MEDIA_ITEM_TRANSITION_REASON_DEFAULT = 0;
    public static final int PLAY_WHEN_READY_CHANGE_REASON_DEFAULT = 1;
    public static final int TIMELINE_CHANGE_REASON_DEFAULT = 0;
    public final AudioAttributes audioAttributes;
    public final CueGroup cueGroup;
    public final Tracks currentTracks;
    public final DeviceInfo deviceInfo;
    public final boolean deviceMuted;
    public final int deviceVolume;
    public final int discontinuityReason;
    public final boolean isLoading;
    public final boolean isPlaying;
    public final long maxSeekToPreviousPositionMs;
    public final int mediaItemTransitionReason;
    public final MediaMetadata mediaMetadata;
    public final Player.PositionInfo newPositionInfo;
    public final Player.PositionInfo oldPositionInfo;
    public final boolean playWhenReady;
    public final int playWhenReadyChangeReason;
    public final PlaybackParameters playbackParameters;
    public final int playbackState;
    public final int playbackSuppressionReason;
    public final PlaybackException playerError;
    public final MediaMetadata playlistMetadata;
    public final int repeatMode;
    public final long seekBackIncrementMs;
    public final long seekForwardIncrementMs;
    public final SessionPositionInfo sessionPositionInfo;
    public final boolean shuffleModeEnabled;
    public final Timeline timeline;
    public final int timelineChangeReason;
    public final TrackSelectionParameters trackSelectionParameters;
    public final VideoSize videoSize;
    public final float volume;

    private boolean isPlaying(int i, boolean z, int i2) {
        return i == 3 && z && i2 == 0;
    }

    public static class BundlingExclusions {
        private static final String FIELD_ARE_CURRENT_TRACKS_EXCLUDED = Util.intToStringMaxRadix(1);
        private static final String FIELD_IS_TIMELINE_EXCLUDED = Util.intToStringMaxRadix(0);
        public static final BundlingExclusions NONE = new BundlingExclusions(false, false);
        public final boolean areCurrentTracksExcluded;
        public final boolean isTimelineExcluded;

        public BundlingExclusions(boolean z, boolean z2) {
            this.isTimelineExcluded = z;
            this.areCurrentTracksExcluded = z2;
        }

        public Bundle toBundle() {
            Bundle bundle = new Bundle();
            bundle.putBoolean(FIELD_IS_TIMELINE_EXCLUDED, this.isTimelineExcluded);
            bundle.putBoolean(FIELD_ARE_CURRENT_TRACKS_EXCLUDED, this.areCurrentTracksExcluded);
            return bundle;
        }

        public static BundlingExclusions fromBundle(Bundle bundle) {
            return new BundlingExclusions(bundle.getBoolean(FIELD_IS_TIMELINE_EXCLUDED, false), bundle.getBoolean(FIELD_ARE_CURRENT_TRACKS_EXCLUDED, false));
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof BundlingExclusions)) {
                return false;
            }
            BundlingExclusions bundlingExclusions = (BundlingExclusions) obj;
            if (this.isTimelineExcluded == bundlingExclusions.isTimelineExcluded && this.areCurrentTracksExcluded == bundlingExclusions.areCurrentTracksExcluded) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return Objects.hashCode(Boolean.valueOf(this.isTimelineExcluded), Boolean.valueOf(this.areCurrentTracksExcluded));
        }
    }

    public static class Builder {
        private AudioAttributes audioAttributes;
        private CueGroup cueGroup;
        private Tracks currentTracks;
        private DeviceInfo deviceInfo;
        private boolean deviceMuted;
        private int deviceVolume;
        private int discontinuityReason;
        private boolean isLoading;
        private boolean isPlaying;
        private long maxSeekToPreviousPositionMs;
        private int mediaItemTransitionReason;
        private MediaMetadata mediaMetadata;
        private Player.PositionInfo newPositionInfo;
        private Player.PositionInfo oldPositionInfo;
        private boolean playWhenReady;
        private int playWhenReadyChangeReason;
        private PlaybackParameters playbackParameters;
        private int playbackState;
        private int playbackSuppressionReason;
        private PlaybackException playerError;
        private MediaMetadata playlistMetadata;
        private int repeatMode;
        private long seekBackIncrementMs;
        private long seekForwardIncrementMs;
        private SessionPositionInfo sessionPositionInfo;
        private boolean shuffleModeEnabled;
        private Timeline timeline;
        private int timelineChangeReason;
        private TrackSelectionParameters trackSelectionParameters;
        private VideoSize videoSize;
        private float volume;

        public Builder(PlayerInfo playerInfo) {
            this.playerError = playerInfo.playerError;
            this.mediaItemTransitionReason = playerInfo.mediaItemTransitionReason;
            this.sessionPositionInfo = playerInfo.sessionPositionInfo;
            this.oldPositionInfo = playerInfo.oldPositionInfo;
            this.newPositionInfo = playerInfo.newPositionInfo;
            this.discontinuityReason = playerInfo.discontinuityReason;
            this.playbackParameters = playerInfo.playbackParameters;
            this.repeatMode = playerInfo.repeatMode;
            this.shuffleModeEnabled = playerInfo.shuffleModeEnabled;
            this.timeline = playerInfo.timeline;
            this.timelineChangeReason = playerInfo.timelineChangeReason;
            this.videoSize = playerInfo.videoSize;
            this.playlistMetadata = playerInfo.playlistMetadata;
            this.volume = playerInfo.volume;
            this.audioAttributes = playerInfo.audioAttributes;
            this.cueGroup = playerInfo.cueGroup;
            this.deviceInfo = playerInfo.deviceInfo;
            this.deviceVolume = playerInfo.deviceVolume;
            this.deviceMuted = playerInfo.deviceMuted;
            this.playWhenReady = playerInfo.playWhenReady;
            this.playWhenReadyChangeReason = playerInfo.playWhenReadyChangeReason;
            this.isPlaying = playerInfo.isPlaying;
            this.isLoading = playerInfo.isLoading;
            this.playbackSuppressionReason = playerInfo.playbackSuppressionReason;
            this.playbackState = playerInfo.playbackState;
            this.mediaMetadata = playerInfo.mediaMetadata;
            this.seekBackIncrementMs = playerInfo.seekBackIncrementMs;
            this.seekForwardIncrementMs = playerInfo.seekForwardIncrementMs;
            this.maxSeekToPreviousPositionMs = playerInfo.maxSeekToPreviousPositionMs;
            this.currentTracks = playerInfo.currentTracks;
            this.trackSelectionParameters = playerInfo.trackSelectionParameters;
        }

        public Builder setPlayerError(PlaybackException playbackException) {
            this.playerError = playbackException;
            return this;
        }

        public Builder setMediaItemTransitionReason(int i) {
            this.mediaItemTransitionReason = i;
            return this;
        }

        public Builder setSessionPositionInfo(SessionPositionInfo sessionPositionInfo2) {
            this.sessionPositionInfo = sessionPositionInfo2;
            return this;
        }

        public Builder setOldPositionInfo(Player.PositionInfo positionInfo) {
            this.oldPositionInfo = positionInfo;
            return this;
        }

        public Builder setNewPositionInfo(Player.PositionInfo positionInfo) {
            this.newPositionInfo = positionInfo;
            return this;
        }

        public Builder setDiscontinuityReason(int i) {
            this.discontinuityReason = i;
            return this;
        }

        public Builder setPlaybackParameters(PlaybackParameters playbackParameters2) {
            this.playbackParameters = playbackParameters2;
            return this;
        }

        public Builder setRepeatMode(int i) {
            this.repeatMode = i;
            return this;
        }

        public Builder setShuffleModeEnabled(boolean z) {
            this.shuffleModeEnabled = z;
            return this;
        }

        public Builder setTimeline(Timeline timeline2) {
            this.timeline = timeline2;
            return this;
        }

        public Builder setTimelineChangeReason(int i) {
            this.timelineChangeReason = i;
            return this;
        }

        public Builder setVideoSize(VideoSize videoSize2) {
            this.videoSize = videoSize2;
            return this;
        }

        public Builder setPlaylistMetadata(MediaMetadata mediaMetadata2) {
            this.playlistMetadata = mediaMetadata2;
            return this;
        }

        public Builder setVolume(float f) {
            this.volume = f;
            return this;
        }

        public Builder setAudioAttributes(AudioAttributes audioAttributes2) {
            this.audioAttributes = audioAttributes2;
            return this;
        }

        public Builder setCues(CueGroup cueGroup2) {
            this.cueGroup = cueGroup2;
            return this;
        }

        public Builder setDeviceInfo(DeviceInfo deviceInfo2) {
            this.deviceInfo = deviceInfo2;
            return this;
        }

        public Builder setDeviceVolume(int i) {
            this.deviceVolume = i;
            return this;
        }

        public Builder setDeviceMuted(boolean z) {
            this.deviceMuted = z;
            return this;
        }

        public Builder setPlayWhenReady(boolean z) {
            this.playWhenReady = z;
            return this;
        }

        public Builder setPlayWhenReadyChangeReason(int i) {
            this.playWhenReadyChangeReason = i;
            return this;
        }

        public Builder setIsPlaying(boolean z) {
            this.isPlaying = z;
            return this;
        }

        public Builder setIsLoading(boolean z) {
            this.isLoading = z;
            return this;
        }

        public Builder setPlaybackSuppressionReason(int i) {
            this.playbackSuppressionReason = i;
            return this;
        }

        public Builder setPlaybackState(int i) {
            this.playbackState = i;
            return this;
        }

        public Builder setMediaMetadata(MediaMetadata mediaMetadata2) {
            this.mediaMetadata = mediaMetadata2;
            return this;
        }

        public Builder setSeekBackIncrement(long j) {
            this.seekBackIncrementMs = j;
            return this;
        }

        public Builder setSeekForwardIncrement(long j) {
            this.seekForwardIncrementMs = j;
            return this;
        }

        public Builder setMaxSeekToPreviousPositionMs(long j) {
            this.maxSeekToPreviousPositionMs = j;
            return this;
        }

        public Builder setCurrentTracks(Tracks tracks) {
            this.currentTracks = tracks;
            return this;
        }

        public Builder setTrackSelectionParameters(TrackSelectionParameters trackSelectionParameters2) {
            this.trackSelectionParameters = trackSelectionParameters2;
            return this;
        }

        public PlayerInfo build() {
            Assertions.checkState(this.timeline.isEmpty() || this.sessionPositionInfo.positionInfo.mediaItemIndex < this.timeline.getWindowCount());
            PlayerInfo playerInfo = r2;
            PlayerInfo playerInfo2 = new PlayerInfo(this.playerError, this.mediaItemTransitionReason, this.sessionPositionInfo, this.oldPositionInfo, this.newPositionInfo, this.discontinuityReason, this.playbackParameters, this.repeatMode, this.shuffleModeEnabled, this.videoSize, this.timeline, this.timelineChangeReason, this.playlistMetadata, this.volume, this.audioAttributes, this.cueGroup, this.deviceInfo, this.deviceVolume, this.deviceMuted, this.playWhenReady, this.playWhenReadyChangeReason, this.playbackSuppressionReason, this.playbackState, this.isPlaying, this.isLoading, this.mediaMetadata, this.seekBackIncrementMs, this.seekForwardIncrementMs, this.maxSeekToPreviousPositionMs, this.currentTracks, this.trackSelectionParameters);
            return playerInfo;
        }
    }

    public PlayerInfo copyWithPlayWhenReady(boolean z, int i, int i2) {
        return new Builder(this).setPlayWhenReady(z).setPlayWhenReadyChangeReason(i).setPlaybackSuppressionReason(i2).setIsPlaying(isPlaying(this.playbackState, z, i2)).build();
    }

    public PlayerInfo copyWithMediaItemTransitionReason(int i) {
        return new Builder(this).setMediaItemTransitionReason(i).build();
    }

    public PlayerInfo copyWithPlayerError(PlaybackException playbackException) {
        return new Builder(this).setPlayerError(playbackException).build();
    }

    public PlayerInfo copyWithPlaybackState(int i, PlaybackException playbackException) {
        return new Builder(this).setPlayerError(playbackException).setPlaybackState(i).setIsPlaying(isPlaying(i, this.playWhenReady, this.playbackSuppressionReason)).build();
    }

    public PlayerInfo copyWithIsPlaying(boolean z) {
        return new Builder(this).setIsPlaying(z).build();
    }

    public PlayerInfo copyWithIsLoading(boolean z) {
        return new Builder(this).setIsLoading(z).build();
    }

    public PlayerInfo copyWithPlaybackParameters(PlaybackParameters playbackParameters2) {
        return new Builder(this).setPlaybackParameters(playbackParameters2).build();
    }

    public PlayerInfo copyWithPositionInfos(Player.PositionInfo positionInfo, Player.PositionInfo positionInfo2, int i) {
        return new Builder(this).setOldPositionInfo(positionInfo).setNewPositionInfo(positionInfo2).setDiscontinuityReason(i).build();
    }

    public PlayerInfo copyWithSessionPositionInfo(SessionPositionInfo sessionPositionInfo2) {
        return new Builder(this).setSessionPositionInfo(sessionPositionInfo2).build();
    }

    public PlayerInfo copyWithTimeline(Timeline timeline2) {
        return new Builder(this).setTimeline(timeline2).build();
    }

    public PlayerInfo copyWithTimelineAndSessionPositionInfo(Timeline timeline2, SessionPositionInfo sessionPositionInfo2, int i) {
        return new Builder(this).setTimeline(timeline2).setSessionPositionInfo(sessionPositionInfo2).setTimelineChangeReason(i).build();
    }

    public PlayerInfo copyWithTimelineAndMediaItemIndex(Timeline timeline2, int i, int i2) {
        Builder timelineChangeReason2 = new Builder(this).setTimeline(timeline2).setTimelineChangeReason(i2);
        Player.PositionInfo positionInfo = r15;
        Player.PositionInfo positionInfo2 = new Player.PositionInfo(this.sessionPositionInfo.positionInfo.windowUid, i, this.sessionPositionInfo.positionInfo.mediaItem, this.sessionPositionInfo.positionInfo.periodUid, this.sessionPositionInfo.positionInfo.periodIndex, this.sessionPositionInfo.positionInfo.positionMs, this.sessionPositionInfo.positionInfo.contentPositionMs, this.sessionPositionInfo.positionInfo.adGroupIndex, this.sessionPositionInfo.positionInfo.adIndexInAdGroup);
        SessionPositionInfo sessionPositionInfo2 = r2;
        Builder builder = timelineChangeReason2;
        SessionPositionInfo sessionPositionInfo3 = new SessionPositionInfo(positionInfo, this.sessionPositionInfo.isPlayingAd, this.sessionPositionInfo.eventTimeMs, this.sessionPositionInfo.durationMs, this.sessionPositionInfo.bufferedPositionMs, this.sessionPositionInfo.bufferedPercentage, this.sessionPositionInfo.totalBufferedDurationMs, this.sessionPositionInfo.currentLiveOffsetMs, this.sessionPositionInfo.contentDurationMs, this.sessionPositionInfo.contentBufferedPositionMs);
        return builder.setSessionPositionInfo(sessionPositionInfo2).build();
    }

    public PlayerInfo copyWithPlaylistMetadata(MediaMetadata mediaMetadata2) {
        return new Builder(this).setPlaylistMetadata(mediaMetadata2).build();
    }

    public PlayerInfo copyWithRepeatMode(int i) {
        return new Builder(this).setRepeatMode(i).build();
    }

    public PlayerInfo copyWithShuffleModeEnabled(boolean z) {
        return new Builder(this).setShuffleModeEnabled(z).build();
    }

    public PlayerInfo copyWithAudioAttributes(AudioAttributes audioAttributes2) {
        return new Builder(this).setAudioAttributes(audioAttributes2).build();
    }

    public PlayerInfo copyWithVideoSize(VideoSize videoSize2) {
        return new Builder(this).setVideoSize(videoSize2).build();
    }

    public PlayerInfo copyWithVolume(float f) {
        return new Builder(this).setVolume(f).build();
    }

    public PlayerInfo copyWithDeviceInfo(DeviceInfo deviceInfo2) {
        return new Builder(this).setDeviceInfo(deviceInfo2).build();
    }

    public PlayerInfo copyWithDeviceVolume(int i, boolean z) {
        return new Builder(this).setDeviceVolume(i).setDeviceMuted(z).build();
    }

    public PlayerInfo copyWithMediaMetadata(MediaMetadata mediaMetadata2) {
        return new Builder(this).setMediaMetadata(mediaMetadata2).build();
    }

    public PlayerInfo copyWithSeekBackIncrement(long j) {
        return new Builder(this).setSeekBackIncrement(j).build();
    }

    public PlayerInfo copyWithSeekForwardIncrement(long j) {
        return new Builder(this).setSeekForwardIncrement(j).build();
    }

    public PlayerInfo copyWithMaxSeekToPreviousPositionMs(long j) {
        return new Builder(this).setMaxSeekToPreviousPositionMs(j).build();
    }

    public PlayerInfo copyWithCurrentTracks(Tracks tracks) {
        return new Builder(this).setCurrentTracks(tracks).build();
    }

    public PlayerInfo copyWithTrackSelectionParameters(TrackSelectionParameters trackSelectionParameters2) {
        return new Builder(this).setTrackSelectionParameters(trackSelectionParameters2).build();
    }

    public PlayerInfo(PlaybackException playbackException, int i, SessionPositionInfo sessionPositionInfo2, Player.PositionInfo positionInfo, Player.PositionInfo positionInfo2, int i2, PlaybackParameters playbackParameters2, int i3, boolean z, VideoSize videoSize2, Timeline timeline2, int i4, MediaMetadata mediaMetadata2, float f, AudioAttributes audioAttributes2, CueGroup cueGroup2, DeviceInfo deviceInfo2, int i5, boolean z2, boolean z3, int i6, int i7, int i8, boolean z4, boolean z5, MediaMetadata mediaMetadata3, long j, long j2, long j3, Tracks tracks, TrackSelectionParameters trackSelectionParameters2) {
        this.playerError = playbackException;
        this.mediaItemTransitionReason = i;
        this.sessionPositionInfo = sessionPositionInfo2;
        this.oldPositionInfo = positionInfo;
        this.newPositionInfo = positionInfo2;
        this.discontinuityReason = i2;
        this.playbackParameters = playbackParameters2;
        this.repeatMode = i3;
        this.shuffleModeEnabled = z;
        this.videoSize = videoSize2;
        this.timeline = timeline2;
        this.timelineChangeReason = i4;
        this.playlistMetadata = mediaMetadata2;
        this.volume = f;
        this.audioAttributes = audioAttributes2;
        this.cueGroup = cueGroup2;
        this.deviceInfo = deviceInfo2;
        this.deviceVolume = i5;
        this.deviceMuted = z2;
        this.playWhenReady = z3;
        this.playWhenReadyChangeReason = i6;
        this.playbackSuppressionReason = i7;
        this.playbackState = i8;
        this.isPlaying = z4;
        this.isLoading = z5;
        this.mediaMetadata = mediaMetadata3;
        this.seekBackIncrementMs = j;
        this.seekForwardIncrementMs = j2;
        this.maxSeekToPreviousPositionMs = j3;
        this.currentTracks = tracks;
        this.trackSelectionParameters = trackSelectionParameters2;
    }

    public MediaItem getCurrentMediaItem() {
        if (this.timeline.isEmpty()) {
            return null;
        }
        return this.timeline.getWindow(this.sessionPositionInfo.positionInfo.mediaItemIndex, new Timeline.Window()).mediaItem;
    }

    public PlayerInfo filterByAvailableCommands(Player.Commands commands, boolean z, boolean z2) {
        Builder builder = new Builder(this);
        boolean contains = commands.contains(16);
        boolean contains2 = commands.contains(17);
        builder.setSessionPositionInfo(this.sessionPositionInfo.filterByAvailableCommands(contains, contains2));
        builder.setOldPositionInfo(this.oldPositionInfo.filterByAvailableCommands(contains, contains2));
        builder.setNewPositionInfo(this.newPositionInfo.filterByAvailableCommands(contains, contains2));
        if (!contains2 && contains && !this.timeline.isEmpty()) {
            builder.setTimeline(this.timeline.copyWithSingleWindow(this.sessionPositionInfo.positionInfo.mediaItemIndex));
        } else if (z || !contains2) {
            builder.setTimeline(Timeline.EMPTY);
        }
        if (!commands.contains(18)) {
            builder.setPlaylistMetadata(MediaMetadata.EMPTY);
        }
        if (!commands.contains(22)) {
            builder.setVolume(1.0f);
        }
        if (!commands.contains(21)) {
            builder.setAudioAttributes(AudioAttributes.DEFAULT);
        }
        if (!commands.contains(28)) {
            builder.setCues(CueGroup.EMPTY_TIME_ZERO);
        }
        if (!commands.contains(23)) {
            builder.setDeviceVolume(0).setDeviceMuted(false);
        }
        if (!commands.contains(18)) {
            builder.setMediaMetadata(MediaMetadata.EMPTY);
        }
        if (z2 || !commands.contains(30)) {
            builder.setCurrentTracks(Tracks.EMPTY);
        }
        return builder.build();
    }

    public Bundle toBundleInProcess() {
        Bundle bundle = new Bundle();
        bundle.putBinder(FIELD_IN_PROCESS_BINDER, new InProcessBinder());
        return bundle;
    }

    public Bundle toBundleForRemoteProcess(int i) {
        Bundle bundle = new Bundle();
        PlaybackException playbackException = this.playerError;
        if (playbackException != null) {
            bundle.putBundle(FIELD_PLAYBACK_ERROR, playbackException.toBundle());
        }
        int i2 = this.mediaItemTransitionReason;
        if (i2 != 0) {
            bundle.putInt(FIELD_MEDIA_ITEM_TRANSITION_REASON, i2);
        }
        if (i < 3 || !this.sessionPositionInfo.equals(SessionPositionInfo.DEFAULT)) {
            bundle.putBundle(FIELD_SESSION_POSITION_INFO, this.sessionPositionInfo.toBundle(i));
        }
        if (i < 3 || !SessionPositionInfo.DEFAULT_POSITION_INFO.equalsForBundling(this.oldPositionInfo)) {
            bundle.putBundle(FIELD_OLD_POSITION_INFO, this.oldPositionInfo.toBundle(i));
        }
        if (i < 3 || !SessionPositionInfo.DEFAULT_POSITION_INFO.equalsForBundling(this.newPositionInfo)) {
            bundle.putBundle(FIELD_NEW_POSITION_INFO, this.newPositionInfo.toBundle(i));
        }
        int i3 = this.discontinuityReason;
        if (i3 != 0) {
            bundle.putInt(FIELD_DISCONTINUITY_REASON, i3);
        }
        if (!this.playbackParameters.equals(PlaybackParameters.DEFAULT)) {
            bundle.putBundle(FIELD_PLAYBACK_PARAMETERS, this.playbackParameters.toBundle());
        }
        int i4 = this.repeatMode;
        if (i4 != 0) {
            bundle.putInt(FIELD_REPEAT_MODE, i4);
        }
        boolean z = this.shuffleModeEnabled;
        if (z) {
            bundle.putBoolean(FIELD_SHUFFLE_MODE_ENABLED, z);
        }
        if (!this.timeline.equals(Timeline.EMPTY)) {
            bundle.putBundle(FIELD_TIMELINE, this.timeline.toBundle());
        }
        int i5 = this.timelineChangeReason;
        if (i5 != 0) {
            bundle.putInt(FIELD_TIMELINE_CHANGE_REASON, i5);
        }
        if (!this.videoSize.equals(VideoSize.UNKNOWN)) {
            bundle.putBundle(FIELD_VIDEO_SIZE, this.videoSize.toBundle());
        }
        if (!this.playlistMetadata.equals(MediaMetadata.EMPTY)) {
            bundle.putBundle(FIELD_PLAYLIST_METADATA, this.playlistMetadata.toBundle());
        }
        float f = this.volume;
        if (f != 1.0f) {
            bundle.putFloat(FIELD_VOLUME, f);
        }
        if (!this.audioAttributes.equals(AudioAttributes.DEFAULT)) {
            bundle.putBundle(FIELD_AUDIO_ATTRIBUTES, this.audioAttributes.toBundle());
        }
        if (!this.cueGroup.equals(CueGroup.EMPTY_TIME_ZERO)) {
            bundle.putBundle(FIELD_CUE_GROUP, this.cueGroup.toBundle());
        }
        if (!this.deviceInfo.equals(DeviceInfo.UNKNOWN)) {
            bundle.putBundle(FIELD_DEVICE_INFO, this.deviceInfo.toBundle());
        }
        int i6 = this.deviceVolume;
        if (i6 != 0) {
            bundle.putInt(FIELD_DEVICE_VOLUME, i6);
        }
        boolean z2 = this.deviceMuted;
        if (z2) {
            bundle.putBoolean(FIELD_DEVICE_MUTED, z2);
        }
        boolean z3 = this.playWhenReady;
        if (z3) {
            bundle.putBoolean(FIELD_PLAY_WHEN_READY, z3);
        }
        int i7 = this.playWhenReadyChangeReason;
        if (i7 != 1) {
            bundle.putInt(FIELD_PLAY_WHEN_READY_CHANGE_REASON, i7);
        }
        int i8 = this.playbackSuppressionReason;
        if (i8 != 0) {
            bundle.putInt(FIELD_PLAYBACK_SUPPRESSION_REASON, i8);
        }
        int i9 = this.playbackState;
        if (i9 != 1) {
            bundle.putInt(FIELD_PLAYBACK_STATE, i9);
        }
        boolean z4 = this.isPlaying;
        if (z4) {
            bundle.putBoolean(FIELD_IS_PLAYING, z4);
        }
        boolean z5 = this.isLoading;
        if (z5) {
            bundle.putBoolean(FIELD_IS_LOADING, z5);
        }
        if (!this.mediaMetadata.equals(MediaMetadata.EMPTY)) {
            bundle.putBundle(FIELD_MEDIA_METADATA, this.mediaMetadata.toBundle());
        }
        long j = 0;
        long j2 = i < 6 ? 0 : 5000;
        long j3 = this.seekBackIncrementMs;
        if (j3 != j2) {
            bundle.putLong(FIELD_SEEK_BACK_INCREMENT_MS, j3);
        }
        long j4 = i < 6 ? 0 : C.DEFAULT_SEEK_FORWARD_INCREMENT_MS;
        long j5 = this.seekForwardIncrementMs;
        if (j5 != j4) {
            bundle.putLong(FIELD_SEEK_FORWARD_INCREMENT_MS, j5);
        }
        if (i >= 6) {
            j = C.DEFAULT_MAX_SEEK_TO_PREVIOUS_POSITION_MS;
        }
        long j6 = this.maxSeekToPreviousPositionMs;
        if (j6 != j) {
            bundle.putLong(FIELD_MAX_SEEK_TO_PREVIOUS_POSITION_MS, j6);
        }
        if (!this.currentTracks.equals(Tracks.EMPTY)) {
            bundle.putBundle(FIELD_CURRENT_TRACKS, this.currentTracks.toBundle());
        }
        if (!this.trackSelectionParameters.equals(TrackSelectionParameters.DEFAULT_WITHOUT_CONTEXT)) {
            bundle.putBundle(FIELD_TRACK_SELECTION_PARAMETERS, this.trackSelectionParameters.toBundle());
        }
        return bundle;
    }

    public static PlayerInfo fromBundle(Bundle bundle, int i) {
        PlaybackException playbackException;
        SessionPositionInfo sessionPositionInfo2;
        Player.PositionInfo positionInfo;
        Player.PositionInfo positionInfo2;
        PlaybackParameters playbackParameters2;
        MediaMetadata mediaMetadata2;
        AudioAttributes audioAttributes2;
        MediaMetadata mediaMetadata3;
        int i2;
        Timeline timeline2;
        long j;
        TrackSelectionParameters trackSelectionParameters2;
        Bundle bundle2 = bundle;
        int i3 = i;
        IBinder binder = bundle2.getBinder(FIELD_IN_PROCESS_BINDER);
        if (binder instanceof InProcessBinder) {
            return ((InProcessBinder) binder).getPlayerInfo();
        }
        Bundle bundle3 = bundle2.getBundle(FIELD_PLAYBACK_ERROR);
        if (bundle3 == null) {
            playbackException = null;
        } else {
            playbackException = PlaybackException.fromBundle(bundle3);
        }
        PlaybackException playbackException2 = playbackException;
        int i4 = bundle2.getInt(FIELD_MEDIA_ITEM_TRANSITION_REASON, 0);
        Bundle bundle4 = bundle2.getBundle(FIELD_SESSION_POSITION_INFO);
        if (bundle4 == null) {
            sessionPositionInfo2 = SessionPositionInfo.DEFAULT;
        } else {
            sessionPositionInfo2 = SessionPositionInfo.fromBundle(bundle4);
        }
        SessionPositionInfo sessionPositionInfo3 = sessionPositionInfo2;
        Bundle bundle5 = bundle2.getBundle(FIELD_OLD_POSITION_INFO);
        if (bundle5 == null) {
            positionInfo = SessionPositionInfo.DEFAULT_POSITION_INFO;
        } else {
            positionInfo = Player.PositionInfo.fromBundle(bundle5);
        }
        Player.PositionInfo positionInfo3 = positionInfo;
        Bundle bundle6 = bundle2.getBundle(FIELD_NEW_POSITION_INFO);
        if (bundle6 == null) {
            positionInfo2 = SessionPositionInfo.DEFAULT_POSITION_INFO;
        } else {
            positionInfo2 = Player.PositionInfo.fromBundle(bundle6);
        }
        Player.PositionInfo positionInfo4 = positionInfo2;
        int i5 = bundle2.getInt(FIELD_DISCONTINUITY_REASON, 0);
        Bundle bundle7 = bundle2.getBundle(FIELD_PLAYBACK_PARAMETERS);
        if (bundle7 == null) {
            playbackParameters2 = PlaybackParameters.DEFAULT;
        } else {
            playbackParameters2 = PlaybackParameters.fromBundle(bundle7);
        }
        PlaybackParameters playbackParameters3 = playbackParameters2;
        int i6 = bundle2.getInt(FIELD_REPEAT_MODE, 0);
        boolean z = bundle2.getBoolean(FIELD_SHUFFLE_MODE_ENABLED, false);
        Bundle bundle8 = bundle2.getBundle(FIELD_TIMELINE);
        Timeline fromBundle = bundle8 == null ? Timeline.EMPTY : Timeline.fromBundle(bundle8);
        int i7 = bundle2.getInt(FIELD_TIMELINE_CHANGE_REASON, 0);
        Bundle bundle9 = bundle2.getBundle(FIELD_VIDEO_SIZE);
        VideoSize fromBundle2 = bundle9 == null ? VideoSize.UNKNOWN : VideoSize.fromBundle(bundle9);
        Bundle bundle10 = bundle2.getBundle(FIELD_PLAYLIST_METADATA);
        if (bundle10 == null) {
            mediaMetadata2 = MediaMetadata.EMPTY;
        } else {
            mediaMetadata2 = MediaMetadata.fromBundle(bundle10);
        }
        MediaMetadata mediaMetadata4 = mediaMetadata2;
        float f = bundle2.getFloat(FIELD_VOLUME, 1.0f);
        Bundle bundle11 = bundle2.getBundle(FIELD_AUDIO_ATTRIBUTES);
        if (bundle11 == null) {
            audioAttributes2 = AudioAttributes.DEFAULT;
        } else {
            audioAttributes2 = AudioAttributes.fromBundle(bundle11);
        }
        AudioAttributes audioAttributes3 = audioAttributes2;
        Bundle bundle12 = bundle2.getBundle(FIELD_CUE_GROUP);
        CueGroup fromBundle3 = bundle12 == null ? CueGroup.EMPTY_TIME_ZERO : CueGroup.fromBundle(bundle12);
        Bundle bundle13 = bundle2.getBundle(FIELD_DEVICE_INFO);
        DeviceInfo fromBundle4 = bundle13 == null ? DeviceInfo.UNKNOWN : DeviceInfo.fromBundle(bundle13);
        float f2 = f;
        int i8 = bundle2.getInt(FIELD_DEVICE_VOLUME, 0);
        boolean z2 = bundle2.getBoolean(FIELD_DEVICE_MUTED, false);
        boolean z3 = bundle2.getBoolean(FIELD_PLAY_WHEN_READY, false);
        int i9 = bundle2.getInt(FIELD_PLAY_WHEN_READY_CHANGE_REASON, 1);
        int i10 = bundle2.getInt(FIELD_PLAYBACK_SUPPRESSION_REASON, 0);
        int i11 = bundle2.getInt(FIELD_PLAYBACK_STATE, 1);
        boolean z4 = bundle2.getBoolean(FIELD_IS_PLAYING, false);
        boolean z5 = bundle2.getBoolean(FIELD_IS_LOADING, false);
        Bundle bundle14 = bundle2.getBundle(FIELD_MEDIA_METADATA);
        if (bundle14 == null) {
            mediaMetadata3 = MediaMetadata.EMPTY;
        } else {
            mediaMetadata3 = MediaMetadata.fromBundle(bundle14);
        }
        MediaMetadata mediaMetadata5 = mediaMetadata3;
        String str = FIELD_SEEK_BACK_INCREMENT_MS;
        long j2 = 0;
        boolean z6 = z5;
        if (i3 < 4) {
            timeline2 = fromBundle;
            i2 = i7;
            j = 0;
        } else {
            timeline2 = fromBundle;
            i2 = i7;
            j = 5000;
        }
        long j3 = bundle2.getLong(str, j);
        long j4 = bundle2.getLong(FIELD_SEEK_FORWARD_INCREMENT_MS, i3 < 4 ? 0 : C.DEFAULT_SEEK_FORWARD_INCREMENT_MS);
        String str2 = FIELD_MAX_SEEK_TO_PREVIOUS_POSITION_MS;
        if (i3 >= 4) {
            j2 = C.DEFAULT_MAX_SEEK_TO_PREVIOUS_POSITION_MS;
        }
        long j5 = bundle2.getLong(str2, j2);
        Bundle bundle15 = bundle2.getBundle(FIELD_CURRENT_TRACKS);
        Tracks fromBundle5 = bundle15 == null ? Tracks.EMPTY : Tracks.fromBundle(bundle15);
        Bundle bundle16 = bundle2.getBundle(FIELD_TRACK_SELECTION_PARAMETERS);
        if (bundle16 == null) {
            trackSelectionParameters2 = TrackSelectionParameters.DEFAULT_WITHOUT_CONTEXT;
        } else {
            trackSelectionParameters2 = TrackSelectionParameters.fromBundle(bundle16);
        }
        return new PlayerInfo(playbackException2, i4, sessionPositionInfo3, positionInfo3, positionInfo4, i5, playbackParameters3, i6, z, fromBundle2, timeline2, i2, mediaMetadata4, f2, audioAttributes3, fromBundle3, fromBundle4, i8, z2, z3, i9, i10, i11, z4, z6, mediaMetadata5, j3, j4, j5, fromBundle5, trackSelectionParameters2);
    }

    private final class InProcessBinder extends Binder {
        private InProcessBinder() {
        }

        public PlayerInfo getPlayerInfo() {
            return PlayerInfo.this;
        }
    }
}
