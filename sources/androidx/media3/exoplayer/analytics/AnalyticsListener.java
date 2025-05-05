package androidx.media3.exoplayer.analytics;

import android.util.SparseArray;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.DeviceInfo;
import androidx.media3.common.FlagSet;
import androidx.media3.common.Format;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.Metadata;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.Player;
import androidx.media3.common.Timeline;
import androidx.media3.common.TrackSelectionParameters;
import androidx.media3.common.Tracks;
import androidx.media3.common.VideoSize;
import androidx.media3.common.text.Cue;
import androidx.media3.common.text.CueGroup;
import androidx.media3.common.util.Assertions;
import androidx.media3.exoplayer.DecoderCounters;
import androidx.media3.exoplayer.DecoderReuseEvaluation;
import androidx.media3.exoplayer.audio.AudioSink;
import androidx.media3.exoplayer.source.LoadEventInfo;
import androidx.media3.exoplayer.source.MediaLoadData;
import androidx.media3.exoplayer.source.MediaSource;
import com.google.common.base.Objects;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;

public interface AnalyticsListener {
    public static final int EVENT_AUDIO_ATTRIBUTES_CHANGED = 20;
    public static final int EVENT_AUDIO_CODEC_ERROR = 1029;
    public static final int EVENT_AUDIO_DECODER_INITIALIZED = 1008;
    public static final int EVENT_AUDIO_DECODER_RELEASED = 1012;
    public static final int EVENT_AUDIO_DISABLED = 1013;
    public static final int EVENT_AUDIO_ENABLED = 1007;
    public static final int EVENT_AUDIO_INPUT_FORMAT_CHANGED = 1009;
    public static final int EVENT_AUDIO_POSITION_ADVANCING = 1010;
    public static final int EVENT_AUDIO_SESSION_ID = 21;
    public static final int EVENT_AUDIO_SINK_ERROR = 1014;
    public static final int EVENT_AUDIO_TRACK_INITIALIZED = 1031;
    public static final int EVENT_AUDIO_TRACK_RELEASED = 1032;
    public static final int EVENT_AUDIO_UNDERRUN = 1011;
    public static final int EVENT_AVAILABLE_COMMANDS_CHANGED = 13;
    public static final int EVENT_BANDWIDTH_ESTIMATE = 1006;
    public static final int EVENT_CUES = 27;
    public static final int EVENT_DEVICE_INFO_CHANGED = 29;
    public static final int EVENT_DEVICE_VOLUME_CHANGED = 30;
    public static final int EVENT_DOWNSTREAM_FORMAT_CHANGED = 1004;
    public static final int EVENT_DRM_KEYS_LOADED = 1023;
    public static final int EVENT_DRM_KEYS_REMOVED = 1026;
    public static final int EVENT_DRM_KEYS_RESTORED = 1025;
    public static final int EVENT_DRM_SESSION_ACQUIRED = 1022;
    public static final int EVENT_DRM_SESSION_MANAGER_ERROR = 1024;
    public static final int EVENT_DRM_SESSION_RELEASED = 1027;
    public static final int EVENT_DROPPED_VIDEO_FRAMES = 1018;
    public static final int EVENT_IS_LOADING_CHANGED = 3;
    public static final int EVENT_IS_PLAYING_CHANGED = 7;
    public static final int EVENT_LOAD_CANCELED = 1002;
    public static final int EVENT_LOAD_COMPLETED = 1001;
    public static final int EVENT_LOAD_ERROR = 1003;
    public static final int EVENT_LOAD_STARTED = 1000;
    public static final int EVENT_MAX_SEEK_TO_PREVIOUS_POSITION_CHANGED = 18;
    public static final int EVENT_MEDIA_ITEM_TRANSITION = 1;
    public static final int EVENT_MEDIA_METADATA_CHANGED = 14;
    public static final int EVENT_METADATA = 28;
    public static final int EVENT_PLAYBACK_PARAMETERS_CHANGED = 12;
    public static final int EVENT_PLAYBACK_STATE_CHANGED = 4;
    public static final int EVENT_PLAYBACK_SUPPRESSION_REASON_CHANGED = 6;
    public static final int EVENT_PLAYER_ERROR = 10;
    public static final int EVENT_PLAYER_RELEASED = 1028;
    public static final int EVENT_PLAYLIST_METADATA_CHANGED = 15;
    public static final int EVENT_PLAY_WHEN_READY_CHANGED = 5;
    public static final int EVENT_POSITION_DISCONTINUITY = 11;
    public static final int EVENT_RENDERED_FIRST_FRAME = 26;
    public static final int EVENT_RENDERER_READY_CHANGED = 1033;
    public static final int EVENT_REPEAT_MODE_CHANGED = 8;
    public static final int EVENT_SEEK_BACK_INCREMENT_CHANGED = 16;
    public static final int EVENT_SEEK_FORWARD_INCREMENT_CHANGED = 17;
    public static final int EVENT_SHUFFLE_MODE_ENABLED_CHANGED = 9;
    public static final int EVENT_SKIP_SILENCE_ENABLED_CHANGED = 23;
    public static final int EVENT_SURFACE_SIZE_CHANGED = 24;
    public static final int EVENT_TIMELINE_CHANGED = 0;
    public static final int EVENT_TRACKS_CHANGED = 2;
    public static final int EVENT_TRACK_SELECTION_PARAMETERS_CHANGED = 19;
    public static final int EVENT_UPSTREAM_DISCARDED = 1005;
    public static final int EVENT_VIDEO_CODEC_ERROR = 1030;
    public static final int EVENT_VIDEO_DECODER_INITIALIZED = 1016;
    public static final int EVENT_VIDEO_DECODER_RELEASED = 1019;
    public static final int EVENT_VIDEO_DISABLED = 1020;
    public static final int EVENT_VIDEO_ENABLED = 1015;
    public static final int EVENT_VIDEO_FRAME_PROCESSING_OFFSET = 1021;
    public static final int EVENT_VIDEO_INPUT_FORMAT_CHANGED = 1017;
    public static final int EVENT_VIDEO_SIZE_CHANGED = 25;
    public static final int EVENT_VOLUME_CHANGED = 22;

    @Documented
    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface EventFlags {
    }

    void onAudioAttributesChanged(EventTime eventTime, AudioAttributes audioAttributes) {
    }

    void onAudioCodecError(EventTime eventTime, Exception exc) {
    }

    @Deprecated
    void onAudioDecoderInitialized(EventTime eventTime, String str, long j) {
    }

    void onAudioDecoderInitialized(EventTime eventTime, String str, long j, long j2) {
    }

    void onAudioDecoderReleased(EventTime eventTime, String str) {
    }

    void onAudioDisabled(EventTime eventTime, DecoderCounters decoderCounters) {
    }

    void onAudioEnabled(EventTime eventTime, DecoderCounters decoderCounters) {
    }

    void onAudioInputFormatChanged(EventTime eventTime, Format format, DecoderReuseEvaluation decoderReuseEvaluation) {
    }

    void onAudioPositionAdvancing(EventTime eventTime, long j) {
    }

    void onAudioSessionIdChanged(EventTime eventTime, int i) {
    }

    void onAudioSinkError(EventTime eventTime, Exception exc) {
    }

    void onAudioTrackInitialized(EventTime eventTime, AudioSink.AudioTrackConfig audioTrackConfig) {
    }

    void onAudioTrackReleased(EventTime eventTime, AudioSink.AudioTrackConfig audioTrackConfig) {
    }

    void onAudioUnderrun(EventTime eventTime, int i, long j, long j2) {
    }

    void onAvailableCommandsChanged(EventTime eventTime, Player.Commands commands) {
    }

    void onBandwidthEstimate(EventTime eventTime, int i, long j, long j2) {
    }

    void onCues(EventTime eventTime, CueGroup cueGroup) {
    }

    @Deprecated
    void onCues(EventTime eventTime, List<Cue> list) {
    }

    void onDeviceInfoChanged(EventTime eventTime, DeviceInfo deviceInfo) {
    }

    void onDeviceVolumeChanged(EventTime eventTime, int i, boolean z) {
    }

    void onDownstreamFormatChanged(EventTime eventTime, MediaLoadData mediaLoadData) {
    }

    void onDrmKeysLoaded(EventTime eventTime) {
    }

    void onDrmKeysRemoved(EventTime eventTime) {
    }

    void onDrmKeysRestored(EventTime eventTime) {
    }

    @Deprecated
    void onDrmSessionAcquired(EventTime eventTime) {
    }

    void onDrmSessionAcquired(EventTime eventTime, int i) {
    }

    void onDrmSessionManagerError(EventTime eventTime, Exception exc) {
    }

    void onDrmSessionReleased(EventTime eventTime) {
    }

    void onDroppedVideoFrames(EventTime eventTime, int i, long j) {
    }

    void onEvents(Player player, Events events) {
    }

    void onIsLoadingChanged(EventTime eventTime, boolean z) {
    }

    void onIsPlayingChanged(EventTime eventTime, boolean z) {
    }

    void onLoadCanceled(EventTime eventTime, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
    }

    void onLoadCompleted(EventTime eventTime, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
    }

    void onLoadError(EventTime eventTime, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, IOException iOException, boolean z) {
    }

    void onLoadStarted(EventTime eventTime, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
    }

    @Deprecated
    void onLoadingChanged(EventTime eventTime, boolean z) {
    }

    void onMaxSeekToPreviousPositionChanged(EventTime eventTime, long j) {
    }

    void onMediaItemTransition(EventTime eventTime, MediaItem mediaItem, int i) {
    }

    void onMediaMetadataChanged(EventTime eventTime, MediaMetadata mediaMetadata) {
    }

    void onMetadata(EventTime eventTime, Metadata metadata) {
    }

    void onPlayWhenReadyChanged(EventTime eventTime, boolean z, int i) {
    }

    void onPlaybackParametersChanged(EventTime eventTime, PlaybackParameters playbackParameters) {
    }

    void onPlaybackStateChanged(EventTime eventTime, int i) {
    }

    void onPlaybackSuppressionReasonChanged(EventTime eventTime, int i) {
    }

    void onPlayerError(EventTime eventTime, PlaybackException playbackException) {
    }

    void onPlayerErrorChanged(EventTime eventTime, PlaybackException playbackException) {
    }

    void onPlayerReleased(EventTime eventTime) {
    }

    @Deprecated
    void onPlayerStateChanged(EventTime eventTime, boolean z, int i) {
    }

    void onPlaylistMetadataChanged(EventTime eventTime, MediaMetadata mediaMetadata) {
    }

    @Deprecated
    void onPositionDiscontinuity(EventTime eventTime, int i) {
    }

    void onPositionDiscontinuity(EventTime eventTime, Player.PositionInfo positionInfo, Player.PositionInfo positionInfo2, int i) {
    }

    void onRenderedFirstFrame(EventTime eventTime, Object obj, long j) {
    }

    void onRendererReadyChanged(EventTime eventTime, int i, int i2, boolean z) {
    }

    void onRepeatModeChanged(EventTime eventTime, int i) {
    }

    void onSeekBackIncrementChanged(EventTime eventTime, long j) {
    }

    void onSeekForwardIncrementChanged(EventTime eventTime, long j) {
    }

    @Deprecated
    void onSeekStarted(EventTime eventTime) {
    }

    void onShuffleModeChanged(EventTime eventTime, boolean z) {
    }

    void onSkipSilenceEnabledChanged(EventTime eventTime, boolean z) {
    }

    void onSurfaceSizeChanged(EventTime eventTime, int i, int i2) {
    }

    void onTimelineChanged(EventTime eventTime, int i) {
    }

    void onTrackSelectionParametersChanged(EventTime eventTime, TrackSelectionParameters trackSelectionParameters) {
    }

    void onTracksChanged(EventTime eventTime, Tracks tracks) {
    }

    void onUpstreamDiscarded(EventTime eventTime, MediaLoadData mediaLoadData) {
    }

    void onVideoCodecError(EventTime eventTime, Exception exc) {
    }

    @Deprecated
    void onVideoDecoderInitialized(EventTime eventTime, String str, long j) {
    }

    void onVideoDecoderInitialized(EventTime eventTime, String str, long j, long j2) {
    }

    void onVideoDecoderReleased(EventTime eventTime, String str) {
    }

    void onVideoDisabled(EventTime eventTime, DecoderCounters decoderCounters) {
    }

    void onVideoEnabled(EventTime eventTime, DecoderCounters decoderCounters) {
    }

    void onVideoFrameProcessingOffset(EventTime eventTime, long j, int i) {
    }

    void onVideoInputFormatChanged(EventTime eventTime, Format format, DecoderReuseEvaluation decoderReuseEvaluation) {
    }

    @Deprecated
    void onVideoSizeChanged(EventTime eventTime, int i, int i2, int i3, float f) {
    }

    void onVideoSizeChanged(EventTime eventTime, VideoSize videoSize) {
    }

    void onVolumeChanged(EventTime eventTime, float f) {
    }

    public static final class Events {
        private final SparseArray<EventTime> eventTimes;
        private final FlagSet flags;

        public Events(FlagSet flagSet, SparseArray<EventTime> sparseArray) {
            this.flags = flagSet;
            SparseArray<EventTime> sparseArray2 = new SparseArray<>(flagSet.size());
            for (int i = 0; i < flagSet.size(); i++) {
                int i2 = flagSet.get(i);
                sparseArray2.append(i2, (EventTime) Assertions.checkNotNull(sparseArray.get(i2)));
            }
            this.eventTimes = sparseArray2;
        }

        public EventTime getEventTime(int i) {
            return (EventTime) Assertions.checkNotNull(this.eventTimes.get(i));
        }

        public boolean contains(int i) {
            return this.flags.contains(i);
        }

        public boolean containsAny(int... iArr) {
            return this.flags.containsAny(iArr);
        }

        public int size() {
            return this.flags.size();
        }

        public int get(int i) {
            return this.flags.get(i);
        }
    }

    public static final class EventTime {
        public final MediaSource.MediaPeriodId currentMediaPeriodId;
        public final long currentPlaybackPositionMs;
        public final Timeline currentTimeline;
        public final int currentWindowIndex;
        public final long eventPlaybackPositionMs;
        public final MediaSource.MediaPeriodId mediaPeriodId;
        public final long realtimeMs;
        public final Timeline timeline;
        public final long totalBufferedDurationMs;
        public final int windowIndex;

        public EventTime(long j, Timeline timeline2, int i, MediaSource.MediaPeriodId mediaPeriodId2, long j2, Timeline timeline3, int i2, MediaSource.MediaPeriodId mediaPeriodId3, long j3, long j4) {
            this.realtimeMs = j;
            this.timeline = timeline2;
            this.windowIndex = i;
            this.mediaPeriodId = mediaPeriodId2;
            this.eventPlaybackPositionMs = j2;
            this.currentTimeline = timeline3;
            this.currentWindowIndex = i2;
            this.currentMediaPeriodId = mediaPeriodId3;
            this.currentPlaybackPositionMs = j3;
            this.totalBufferedDurationMs = j4;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            EventTime eventTime = (EventTime) obj;
            if (this.realtimeMs == eventTime.realtimeMs && this.windowIndex == eventTime.windowIndex && this.eventPlaybackPositionMs == eventTime.eventPlaybackPositionMs && this.currentWindowIndex == eventTime.currentWindowIndex && this.currentPlaybackPositionMs == eventTime.currentPlaybackPositionMs && this.totalBufferedDurationMs == eventTime.totalBufferedDurationMs && Objects.equal(this.timeline, eventTime.timeline) && Objects.equal(this.mediaPeriodId, eventTime.mediaPeriodId) && Objects.equal(this.currentTimeline, eventTime.currentTimeline) && Objects.equal(this.currentMediaPeriodId, eventTime.currentMediaPeriodId)) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return Objects.hashCode(Long.valueOf(this.realtimeMs), this.timeline, Integer.valueOf(this.windowIndex), this.mediaPeriodId, Long.valueOf(this.eventPlaybackPositionMs), this.currentTimeline, Integer.valueOf(this.currentWindowIndex), this.currentMediaPeriodId, Long.valueOf(this.currentPlaybackPositionMs), Long.valueOf(this.totalBufferedDurationMs));
        }
    }
}
