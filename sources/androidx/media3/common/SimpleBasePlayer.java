package androidx.media3.common;

import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Pair;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.Metadata;
import androidx.media3.common.Player;
import androidx.media3.common.Timeline;
import androidx.media3.common.Tracks;
import androidx.media3.common.text.Cue;
import androidx.media3.common.text.CueGroup;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.HandlerWrapper;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.common.util.Size;
import androidx.media3.common.util.Util;
import com.facebook.imageutils.JfifUtil;
import com.google.common.base.Supplier;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

public abstract class SimpleBasePlayer extends BasePlayer {
    private static final long POSITION_DISCONTINUITY_THRESHOLD_MS = 1000;
    private final HandlerWrapper applicationHandler;
    private final Looper applicationLooper;
    private final ListenerSet<Player.Listener> listeners;
    private final HashSet<ListenableFuture<?>> pendingOperations;
    private final Timeline.Period period;
    private boolean released;
    private State state;

    static /* synthetic */ ListenableFuture lambda$handleReplaceMediaItems$31(ListenableFuture listenableFuture, Object obj) throws Exception {
        return listenableFuture;
    }

    static /* synthetic */ State lambda$release$13(State state2) {
        return state2;
    }

    /* access modifiers changed from: protected */
    public State getPlaceholderState(State state2) {
        return state2;
    }

    /* access modifiers changed from: protected */
    public abstract State getState();

    protected static final class State {
        public final PositionSupplier adBufferedPositionMsSupplier;
        public final PositionSupplier adPositionMsSupplier;
        public final AudioAttributes audioAttributes;
        public final Player.Commands availableCommands;
        public final PositionSupplier contentBufferedPositionMsSupplier;
        public final PositionSupplier contentPositionMsSupplier;
        public final int currentAdGroupIndex;
        public final int currentAdIndexInAdGroup;
        public final CueGroup currentCues;
        public final int currentMediaItemIndex;
        public final MediaMetadata currentMetadata;
        public final Tracks currentTracks;
        public final DeviceInfo deviceInfo;
        public final int deviceVolume;
        public final long discontinuityPositionMs;
        public final boolean hasPositionDiscontinuity;
        public final boolean isDeviceMuted;
        public final boolean isLoading;
        public final long maxSeekToPreviousPositionMs;
        public final boolean newlyRenderedFirstFrame;
        public final boolean playWhenReady;
        public final int playWhenReadyChangeReason;
        public final PlaybackParameters playbackParameters;
        public final int playbackState;
        public final int playbackSuppressionReason;
        public final PlaybackException playerError;
        public final MediaMetadata playlistMetadata;
        public final int positionDiscontinuityReason;
        public final int repeatMode;
        public final long seekBackIncrementMs;
        public final long seekForwardIncrementMs;
        public final boolean shuffleModeEnabled;
        public final Size surfaceSize;
        public final Metadata timedMetadata;
        public final Timeline timeline;
        public final PositionSupplier totalBufferedDurationMsSupplier;
        public final TrackSelectionParameters trackSelectionParameters;
        public final VideoSize videoSize;
        public final float volume;

        public static final class Builder {
            /* access modifiers changed from: private */
            public PositionSupplier adBufferedPositionMsSupplier;
            /* access modifiers changed from: private */
            public Long adPositionMs;
            /* access modifiers changed from: private */
            public PositionSupplier adPositionMsSupplier;
            /* access modifiers changed from: private */
            public AudioAttributes audioAttributes;
            /* access modifiers changed from: private */
            public Player.Commands availableCommands;
            /* access modifiers changed from: private */
            public PositionSupplier contentBufferedPositionMsSupplier;
            /* access modifiers changed from: private */
            public Long contentPositionMs;
            /* access modifiers changed from: private */
            public PositionSupplier contentPositionMsSupplier;
            /* access modifiers changed from: private */
            public int currentAdGroupIndex;
            /* access modifiers changed from: private */
            public int currentAdIndexInAdGroup;
            /* access modifiers changed from: private */
            public CueGroup currentCues;
            /* access modifiers changed from: private */
            public int currentMediaItemIndex;
            /* access modifiers changed from: private */
            public MediaMetadata currentMetadata;
            /* access modifiers changed from: private */
            public Tracks currentTracks;
            /* access modifiers changed from: private */
            public DeviceInfo deviceInfo;
            /* access modifiers changed from: private */
            public int deviceVolume;
            /* access modifiers changed from: private */
            public long discontinuityPositionMs;
            /* access modifiers changed from: private */
            public boolean hasPositionDiscontinuity;
            /* access modifiers changed from: private */
            public boolean isDeviceMuted;
            /* access modifiers changed from: private */
            public boolean isLoading;
            /* access modifiers changed from: private */
            public long maxSeekToPreviousPositionMs;
            /* access modifiers changed from: private */
            public boolean newlyRenderedFirstFrame;
            /* access modifiers changed from: private */
            public boolean playWhenReady;
            /* access modifiers changed from: private */
            public int playWhenReadyChangeReason;
            /* access modifiers changed from: private */
            public PlaybackParameters playbackParameters;
            /* access modifiers changed from: private */
            public int playbackState;
            /* access modifiers changed from: private */
            public int playbackSuppressionReason;
            /* access modifiers changed from: private */
            public PlaybackException playerError;
            /* access modifiers changed from: private */
            public ImmutableList<MediaItemData> playlist;
            /* access modifiers changed from: private */
            public MediaMetadata playlistMetadata;
            /* access modifiers changed from: private */
            public int positionDiscontinuityReason;
            /* access modifiers changed from: private */
            public int repeatMode;
            /* access modifiers changed from: private */
            public long seekBackIncrementMs;
            /* access modifiers changed from: private */
            public long seekForwardIncrementMs;
            /* access modifiers changed from: private */
            public boolean shuffleModeEnabled;
            /* access modifiers changed from: private */
            public Size surfaceSize;
            /* access modifiers changed from: private */
            public Metadata timedMetadata;
            /* access modifiers changed from: private */
            public Timeline timeline;
            /* access modifiers changed from: private */
            public PositionSupplier totalBufferedDurationMsSupplier;
            /* access modifiers changed from: private */
            public TrackSelectionParameters trackSelectionParameters;
            /* access modifiers changed from: private */
            public VideoSize videoSize;
            /* access modifiers changed from: private */
            public float volume;

            public Builder() {
                this.availableCommands = Player.Commands.EMPTY;
                this.playWhenReady = false;
                this.playWhenReadyChangeReason = 1;
                this.playbackState = 1;
                this.playbackSuppressionReason = 0;
                this.playerError = null;
                this.repeatMode = 0;
                this.shuffleModeEnabled = false;
                this.isLoading = false;
                this.seekBackIncrementMs = 5000;
                this.seekForwardIncrementMs = C.DEFAULT_SEEK_FORWARD_INCREMENT_MS;
                this.maxSeekToPreviousPositionMs = C.DEFAULT_MAX_SEEK_TO_PREVIOUS_POSITION_MS;
                this.playbackParameters = PlaybackParameters.DEFAULT;
                this.trackSelectionParameters = TrackSelectionParameters.DEFAULT_WITHOUT_CONTEXT;
                this.audioAttributes = AudioAttributes.DEFAULT;
                this.volume = 1.0f;
                this.videoSize = VideoSize.UNKNOWN;
                this.currentCues = CueGroup.EMPTY_TIME_ZERO;
                this.deviceInfo = DeviceInfo.UNKNOWN;
                this.deviceVolume = 0;
                this.isDeviceMuted = false;
                this.surfaceSize = Size.UNKNOWN;
                this.newlyRenderedFirstFrame = false;
                this.timedMetadata = new Metadata((long) C.TIME_UNSET, new Metadata.Entry[0]);
                this.playlist = ImmutableList.of();
                this.timeline = Timeline.EMPTY;
                this.currentTracks = null;
                this.currentMetadata = null;
                this.playlistMetadata = MediaMetadata.EMPTY;
                this.currentMediaItemIndex = -1;
                this.currentAdGroupIndex = -1;
                this.currentAdIndexInAdGroup = -1;
                this.contentPositionMs = null;
                this.contentPositionMsSupplier = PositionSupplier.getConstant(C.TIME_UNSET);
                this.adPositionMs = null;
                this.adPositionMsSupplier = PositionSupplier.ZERO;
                this.contentBufferedPositionMsSupplier = PositionSupplier.getConstant(C.TIME_UNSET);
                this.adBufferedPositionMsSupplier = PositionSupplier.ZERO;
                this.totalBufferedDurationMsSupplier = PositionSupplier.ZERO;
                this.hasPositionDiscontinuity = false;
                this.positionDiscontinuityReason = 5;
                this.discontinuityPositionMs = 0;
            }

            private Builder(State state) {
                this.availableCommands = state.availableCommands;
                this.playWhenReady = state.playWhenReady;
                this.playWhenReadyChangeReason = state.playWhenReadyChangeReason;
                this.playbackState = state.playbackState;
                this.playbackSuppressionReason = state.playbackSuppressionReason;
                this.playerError = state.playerError;
                this.repeatMode = state.repeatMode;
                this.shuffleModeEnabled = state.shuffleModeEnabled;
                this.isLoading = state.isLoading;
                this.seekBackIncrementMs = state.seekBackIncrementMs;
                this.seekForwardIncrementMs = state.seekForwardIncrementMs;
                this.maxSeekToPreviousPositionMs = state.maxSeekToPreviousPositionMs;
                this.playbackParameters = state.playbackParameters;
                this.trackSelectionParameters = state.trackSelectionParameters;
                this.audioAttributes = state.audioAttributes;
                this.volume = state.volume;
                this.videoSize = state.videoSize;
                this.currentCues = state.currentCues;
                this.deviceInfo = state.deviceInfo;
                this.deviceVolume = state.deviceVolume;
                this.isDeviceMuted = state.isDeviceMuted;
                this.surfaceSize = state.surfaceSize;
                this.newlyRenderedFirstFrame = state.newlyRenderedFirstFrame;
                this.timedMetadata = state.timedMetadata;
                this.timeline = state.timeline;
                if (state.timeline instanceof PlaylistTimeline) {
                    this.playlist = ((PlaylistTimeline) state.timeline).playlist;
                } else {
                    this.currentTracks = state.currentTracks;
                    this.currentMetadata = state.currentMetadata;
                }
                this.playlistMetadata = state.playlistMetadata;
                this.currentMediaItemIndex = state.currentMediaItemIndex;
                this.currentAdGroupIndex = state.currentAdGroupIndex;
                this.currentAdIndexInAdGroup = state.currentAdIndexInAdGroup;
                this.contentPositionMs = null;
                this.contentPositionMsSupplier = state.contentPositionMsSupplier;
                this.adPositionMs = null;
                this.adPositionMsSupplier = state.adPositionMsSupplier;
                this.contentBufferedPositionMsSupplier = state.contentBufferedPositionMsSupplier;
                this.adBufferedPositionMsSupplier = state.adBufferedPositionMsSupplier;
                this.totalBufferedDurationMsSupplier = state.totalBufferedDurationMsSupplier;
                this.hasPositionDiscontinuity = state.hasPositionDiscontinuity;
                this.positionDiscontinuityReason = state.positionDiscontinuityReason;
                this.discontinuityPositionMs = state.discontinuityPositionMs;
            }

            public Builder setAvailableCommands(Player.Commands commands) {
                this.availableCommands = commands;
                return this;
            }

            public Builder setPlayWhenReady(boolean z, int i) {
                this.playWhenReady = z;
                this.playWhenReadyChangeReason = i;
                return this;
            }

            public Builder setPlaybackState(int i) {
                this.playbackState = i;
                return this;
            }

            public Builder setPlaybackSuppressionReason(int i) {
                this.playbackSuppressionReason = i;
                return this;
            }

            public Builder setPlayerError(PlaybackException playbackException) {
                this.playerError = playbackException;
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

            public Builder setIsLoading(boolean z) {
                this.isLoading = z;
                return this;
            }

            public Builder setSeekBackIncrementMs(long j) {
                this.seekBackIncrementMs = j;
                return this;
            }

            public Builder setSeekForwardIncrementMs(long j) {
                this.seekForwardIncrementMs = j;
                return this;
            }

            public Builder setMaxSeekToPreviousPositionMs(long j) {
                this.maxSeekToPreviousPositionMs = j;
                return this;
            }

            public Builder setPlaybackParameters(PlaybackParameters playbackParameters2) {
                this.playbackParameters = playbackParameters2;
                return this;
            }

            public Builder setTrackSelectionParameters(TrackSelectionParameters trackSelectionParameters2) {
                this.trackSelectionParameters = trackSelectionParameters2;
                return this;
            }

            public Builder setAudioAttributes(AudioAttributes audioAttributes2) {
                this.audioAttributes = audioAttributes2;
                return this;
            }

            public Builder setVolume(float f) {
                Assertions.checkArgument(f >= 0.0f && f <= 1.0f);
                this.volume = f;
                return this;
            }

            public Builder setVideoSize(VideoSize videoSize2) {
                this.videoSize = videoSize2;
                return this;
            }

            public Builder setCurrentCues(CueGroup cueGroup) {
                this.currentCues = cueGroup;
                return this;
            }

            public Builder setDeviceInfo(DeviceInfo deviceInfo2) {
                this.deviceInfo = deviceInfo2;
                return this;
            }

            public Builder setDeviceVolume(int i) {
                Assertions.checkArgument(i >= 0);
                this.deviceVolume = i;
                return this;
            }

            public Builder setIsDeviceMuted(boolean z) {
                this.isDeviceMuted = z;
                return this;
            }

            public Builder setSurfaceSize(Size size) {
                this.surfaceSize = size;
                return this;
            }

            public Builder setNewlyRenderedFirstFrame(boolean z) {
                this.newlyRenderedFirstFrame = z;
                return this;
            }

            public Builder setTimedMetadata(Metadata metadata) {
                this.timedMetadata = metadata;
                return this;
            }

            public Builder setPlaylist(List<MediaItemData> list) {
                HashSet hashSet = new HashSet();
                for (int i = 0; i < list.size(); i++) {
                    Assertions.checkArgument(hashSet.add(list.get(i).uid), "Duplicate MediaItemData UID in playlist");
                }
                this.playlist = ImmutableList.copyOf(list);
                this.timeline = new PlaylistTimeline(this.playlist);
                this.currentTracks = null;
                this.currentMetadata = null;
                return this;
            }

            public Builder setPlaylist(Timeline timeline2, Tracks tracks, MediaMetadata mediaMetadata) {
                this.playlist = null;
                this.timeline = timeline2;
                this.currentTracks = tracks;
                this.currentMetadata = mediaMetadata;
                return this;
            }

            public Builder setPlaylistMetadata(MediaMetadata mediaMetadata) {
                this.playlistMetadata = mediaMetadata;
                return this;
            }

            public Builder setCurrentMediaItemIndex(int i) {
                this.currentMediaItemIndex = i;
                return this;
            }

            public Builder setCurrentAd(int i, int i2) {
                boolean z = true;
                if ((i == -1) != (i2 == -1)) {
                    z = false;
                }
                Assertions.checkArgument(z);
                this.currentAdGroupIndex = i;
                this.currentAdIndexInAdGroup = i2;
                return this;
            }

            public Builder setContentPositionMs(long j) {
                this.contentPositionMs = Long.valueOf(j);
                return this;
            }

            public Builder setContentPositionMs(PositionSupplier positionSupplier) {
                this.contentPositionMs = null;
                this.contentPositionMsSupplier = positionSupplier;
                return this;
            }

            public Builder setAdPositionMs(long j) {
                this.adPositionMs = Long.valueOf(j);
                return this;
            }

            public Builder setAdPositionMs(PositionSupplier positionSupplier) {
                this.adPositionMs = null;
                this.adPositionMsSupplier = positionSupplier;
                return this;
            }

            public Builder setContentBufferedPositionMs(PositionSupplier positionSupplier) {
                this.contentBufferedPositionMsSupplier = positionSupplier;
                return this;
            }

            public Builder setAdBufferedPositionMs(PositionSupplier positionSupplier) {
                this.adBufferedPositionMsSupplier = positionSupplier;
                return this;
            }

            public Builder setTotalBufferedDurationMs(PositionSupplier positionSupplier) {
                this.totalBufferedDurationMsSupplier = positionSupplier;
                return this;
            }

            public Builder setPositionDiscontinuity(int i, long j) {
                this.hasPositionDiscontinuity = true;
                this.positionDiscontinuityReason = i;
                this.discontinuityPositionMs = j;
                return this;
            }

            public Builder clearPositionDiscontinuity() {
                this.hasPositionDiscontinuity = false;
                return this;
            }

            public State build() {
                return new State(this);
            }
        }

        private State(Builder builder) {
            long j;
            Tracks access$200 = builder.currentTracks;
            MediaMetadata access$300 = builder.currentMetadata;
            boolean z = false;
            if (builder.timeline.isEmpty()) {
                Assertions.checkArgument(builder.playbackState == 1 || builder.playbackState == 4, "Empty playlist only allowed in STATE_IDLE or STATE_ENDED");
                Assertions.checkArgument(builder.currentAdGroupIndex == -1 && builder.currentAdIndexInAdGroup == -1, "Ads not allowed if playlist is empty");
                access$200 = access$200 == null ? Tracks.EMPTY : access$200;
                if (access$300 == null) {
                    access$300 = MediaMetadata.EMPTY;
                }
            } else {
                int access$800 = builder.currentMediaItemIndex;
                if (access$800 == -1) {
                    access$800 = 0;
                } else {
                    Assertions.checkArgument(builder.currentMediaItemIndex < builder.timeline.getWindowCount(), "currentMediaItemIndex must be less than playlist.size()");
                }
                if (builder.currentAdGroupIndex != -1) {
                    Timeline.Period period = new Timeline.Period();
                    Timeline.Window window = new Timeline.Window();
                    if (builder.contentPositionMs != null) {
                        j = builder.contentPositionMs.longValue();
                    } else {
                        j = builder.contentPositionMsSupplier.get();
                    }
                    builder.timeline.getPeriod(SimpleBasePlayer.getPeriodIndexFromWindowPosition(builder.timeline, access$800, j, window, period), period);
                    Assertions.checkArgument(builder.currentAdGroupIndex < period.getAdGroupCount(), "PeriodData has less ad groups than adGroupIndex");
                    int adCountInAdGroup = period.getAdCountInAdGroup(builder.currentAdGroupIndex);
                    if (adCountInAdGroup != -1) {
                        Assertions.checkArgument(builder.currentAdIndexInAdGroup < adCountInAdGroup, "Ad group has less ads than adIndexInGroupIndex");
                    }
                }
                if (builder.playlist != null) {
                    MediaItemData mediaItemData = (MediaItemData) builder.playlist.get(access$800);
                    Tracks tracks = mediaItemData.tracks;
                    access$300 = mediaItemData.mediaMetadata;
                    access$200 = tracks;
                }
                if (access$300 == null) {
                    access$300 = SimpleBasePlayer.getCombinedMediaMetadata(builder.timeline.getWindow(access$800, new Timeline.Window()).mediaItem, (Tracks) Assertions.checkNotNull(access$200));
                }
            }
            if (builder.playerError != null) {
                Assertions.checkArgument(builder.playbackState == 1 ? true : z, "Player error only allowed in STATE_IDLE");
            }
            if (builder.playbackState == 1 || builder.playbackState == 4) {
                Assertions.checkArgument(!builder.isLoading, "isLoading only allowed when not in STATE_IDLE or STATE_ENDED");
            }
            PositionSupplier access$1000 = builder.contentPositionMsSupplier;
            if (builder.contentPositionMs != null) {
                if (builder.currentAdGroupIndex == -1 && builder.playWhenReady && builder.playbackState == 3 && builder.playbackSuppressionReason == 0 && builder.contentPositionMs.longValue() != C.TIME_UNSET) {
                    access$1000 = PositionSupplier.getExtrapolating(builder.contentPositionMs.longValue(), builder.playbackParameters.speed);
                } else {
                    access$1000 = PositionSupplier.getConstant(builder.contentPositionMs.longValue());
                }
            }
            PositionSupplier access$1900 = builder.adPositionMsSupplier;
            if (builder.adPositionMs != null) {
                if (builder.currentAdGroupIndex == -1 || !builder.playWhenReady || builder.playbackState != 3 || builder.playbackSuppressionReason != 0) {
                    access$1900 = PositionSupplier.getConstant(builder.adPositionMs.longValue());
                } else {
                    access$1900 = PositionSupplier.getExtrapolating(builder.adPositionMs.longValue(), 1.0f);
                }
            }
            this.availableCommands = builder.availableCommands;
            this.playWhenReady = builder.playWhenReady;
            this.playWhenReadyChangeReason = builder.playWhenReadyChangeReason;
            this.playbackState = builder.playbackState;
            this.playbackSuppressionReason = builder.playbackSuppressionReason;
            this.playerError = builder.playerError;
            this.repeatMode = builder.repeatMode;
            this.shuffleModeEnabled = builder.shuffleModeEnabled;
            this.isLoading = builder.isLoading;
            this.seekBackIncrementMs = builder.seekBackIncrementMs;
            this.seekForwardIncrementMs = builder.seekForwardIncrementMs;
            this.maxSeekToPreviousPositionMs = builder.maxSeekToPreviousPositionMs;
            this.playbackParameters = builder.playbackParameters;
            this.trackSelectionParameters = builder.trackSelectionParameters;
            this.audioAttributes = builder.audioAttributes;
            this.volume = builder.volume;
            this.videoSize = builder.videoSize;
            this.currentCues = builder.currentCues;
            this.deviceInfo = builder.deviceInfo;
            this.deviceVolume = builder.deviceVolume;
            this.isDeviceMuted = builder.isDeviceMuted;
            this.surfaceSize = builder.surfaceSize;
            this.newlyRenderedFirstFrame = builder.newlyRenderedFirstFrame;
            this.timedMetadata = builder.timedMetadata;
            this.timeline = builder.timeline;
            this.currentTracks = (Tracks) Assertions.checkNotNull(access$200);
            this.currentMetadata = access$300;
            this.playlistMetadata = builder.playlistMetadata;
            this.currentMediaItemIndex = builder.currentMediaItemIndex;
            this.currentAdGroupIndex = builder.currentAdGroupIndex;
            this.currentAdIndexInAdGroup = builder.currentAdIndexInAdGroup;
            this.contentPositionMsSupplier = access$1000;
            this.adPositionMsSupplier = access$1900;
            this.contentBufferedPositionMsSupplier = builder.contentBufferedPositionMsSupplier;
            this.adBufferedPositionMsSupplier = builder.adBufferedPositionMsSupplier;
            this.totalBufferedDurationMsSupplier = builder.totalBufferedDurationMsSupplier;
            this.hasPositionDiscontinuity = builder.hasPositionDiscontinuity;
            this.positionDiscontinuityReason = builder.positionDiscontinuityReason;
            this.discontinuityPositionMs = builder.discontinuityPositionMs;
        }

        public Builder buildUpon() {
            return new Builder();
        }

        public ImmutableList<MediaItemData> getPlaylist() {
            Timeline timeline2 = this.timeline;
            if (timeline2 instanceof PlaylistTimeline) {
                return ((PlaylistTimeline) timeline2).playlist;
            }
            Timeline.Window window = new Timeline.Window();
            Timeline.Period period = new Timeline.Period();
            ImmutableList.Builder builderWithExpectedSize = ImmutableList.builderWithExpectedSize(this.timeline.getWindowCount());
            for (int i = 0; i < this.timeline.getWindowCount(); i++) {
                builderWithExpectedSize.add((Object) MediaItemData.buildFromState(this, i, period, window));
            }
            return builderWithExpectedSize.build();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof State)) {
                return false;
            }
            State state = (State) obj;
            if (this.playWhenReady == state.playWhenReady && this.playWhenReadyChangeReason == state.playWhenReadyChangeReason && this.availableCommands.equals(state.availableCommands) && this.playbackState == state.playbackState && this.playbackSuppressionReason == state.playbackSuppressionReason && Objects.equals(this.playerError, state.playerError) && this.repeatMode == state.repeatMode && this.shuffleModeEnabled == state.shuffleModeEnabled && this.isLoading == state.isLoading && this.seekBackIncrementMs == state.seekBackIncrementMs && this.seekForwardIncrementMs == state.seekForwardIncrementMs && this.maxSeekToPreviousPositionMs == state.maxSeekToPreviousPositionMs && this.playbackParameters.equals(state.playbackParameters) && this.trackSelectionParameters.equals(state.trackSelectionParameters) && this.audioAttributes.equals(state.audioAttributes) && this.volume == state.volume && this.videoSize.equals(state.videoSize) && this.currentCues.equals(state.currentCues) && this.deviceInfo.equals(state.deviceInfo) && this.deviceVolume == state.deviceVolume && this.isDeviceMuted == state.isDeviceMuted && this.surfaceSize.equals(state.surfaceSize) && this.newlyRenderedFirstFrame == state.newlyRenderedFirstFrame && this.timedMetadata.equals(state.timedMetadata) && this.timeline.equals(state.timeline) && this.currentTracks.equals(state.currentTracks) && this.currentMetadata.equals(state.currentMetadata) && this.playlistMetadata.equals(state.playlistMetadata) && this.currentMediaItemIndex == state.currentMediaItemIndex && this.currentAdGroupIndex == state.currentAdGroupIndex && this.currentAdIndexInAdGroup == state.currentAdIndexInAdGroup && this.contentPositionMsSupplier.equals(state.contentPositionMsSupplier) && this.adPositionMsSupplier.equals(state.adPositionMsSupplier) && this.contentBufferedPositionMsSupplier.equals(state.contentBufferedPositionMsSupplier) && this.adBufferedPositionMsSupplier.equals(state.adBufferedPositionMsSupplier) && this.totalBufferedDurationMsSupplier.equals(state.totalBufferedDurationMsSupplier) && this.hasPositionDiscontinuity == state.hasPositionDiscontinuity && this.positionDiscontinuityReason == state.positionDiscontinuityReason && this.discontinuityPositionMs == state.discontinuityPositionMs) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            int hashCode = (((((((((JfifUtil.MARKER_EOI + this.availableCommands.hashCode()) * 31) + (this.playWhenReady ? 1 : 0)) * 31) + this.playWhenReadyChangeReason) * 31) + this.playbackState) * 31) + this.playbackSuppressionReason) * 31;
            PlaybackException playbackException = this.playerError;
            int hashCode2 = playbackException == null ? 0 : playbackException.hashCode();
            long j = this.seekBackIncrementMs;
            long j2 = this.seekForwardIncrementMs;
            long j3 = this.maxSeekToPreviousPositionMs;
            long j4 = this.discontinuityPositionMs;
            return ((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((hashCode + hashCode2) * 31) + this.repeatMode) * 31) + (this.shuffleModeEnabled ? 1 : 0)) * 31) + (this.isLoading ? 1 : 0)) * 31) + ((int) (j ^ (j >>> 32)))) * 31) + ((int) (j2 ^ (j2 >>> 32)))) * 31) + ((int) (j3 ^ (j3 >>> 32)))) * 31) + this.playbackParameters.hashCode()) * 31) + this.trackSelectionParameters.hashCode()) * 31) + this.audioAttributes.hashCode()) * 31) + Float.floatToRawIntBits(this.volume)) * 31) + this.videoSize.hashCode()) * 31) + this.currentCues.hashCode()) * 31) + this.deviceInfo.hashCode()) * 31) + this.deviceVolume) * 31) + (this.isDeviceMuted ? 1 : 0)) * 31) + this.surfaceSize.hashCode()) * 31) + (this.newlyRenderedFirstFrame ? 1 : 0)) * 31) + this.timedMetadata.hashCode()) * 31) + this.timeline.hashCode()) * 31) + this.currentTracks.hashCode()) * 31) + this.currentMetadata.hashCode()) * 31) + this.playlistMetadata.hashCode()) * 31) + this.currentMediaItemIndex) * 31) + this.currentAdGroupIndex) * 31) + this.currentAdIndexInAdGroup) * 31) + this.contentPositionMsSupplier.hashCode()) * 31) + this.adPositionMsSupplier.hashCode()) * 31) + this.contentBufferedPositionMsSupplier.hashCode()) * 31) + this.adBufferedPositionMsSupplier.hashCode()) * 31) + this.totalBufferedDurationMsSupplier.hashCode()) * 31) + (this.hasPositionDiscontinuity ? 1 : 0)) * 31) + this.positionDiscontinuityReason) * 31) + ((int) (j4 ^ (j4 >>> 32)));
        }
    }

    private static final class PlaylistTimeline extends Timeline {
        private final int[] firstPeriodIndexByWindowIndex;
        private final HashMap<Object, Integer> periodIndexByUid;
        /* access modifiers changed from: private */
        public final ImmutableList<MediaItemData> playlist;
        private final int[] windowIndexByPeriodIndex;

        public PlaylistTimeline(List<MediaItemData> list) {
            int size = list.size();
            this.playlist = ImmutableList.copyOf(list);
            this.firstPeriodIndexByWindowIndex = new int[size];
            int i = 0;
            for (int i2 = 0; i2 < size; i2++) {
                this.firstPeriodIndexByWindowIndex[i2] = i;
                i += getPeriodCountInMediaItem(list.get(i2));
            }
            this.windowIndexByPeriodIndex = new int[i];
            this.periodIndexByUid = new HashMap<>();
            int i3 = 0;
            for (int i4 = 0; i4 < size; i4++) {
                MediaItemData mediaItemData = list.get(i4);
                for (int i5 = 0; i5 < getPeriodCountInMediaItem(mediaItemData); i5++) {
                    this.periodIndexByUid.put(mediaItemData.getPeriodUid(i5), Integer.valueOf(i3));
                    this.windowIndexByPeriodIndex[i3] = i4;
                    i3++;
                }
            }
        }

        public int getWindowCount() {
            return this.playlist.size();
        }

        public int getNextWindowIndex(int i, int i2, boolean z) {
            return super.getNextWindowIndex(i, i2, z);
        }

        public int getPreviousWindowIndex(int i, int i2, boolean z) {
            return super.getPreviousWindowIndex(i, i2, z);
        }

        public int getLastWindowIndex(boolean z) {
            return super.getLastWindowIndex(z);
        }

        public int getFirstWindowIndex(boolean z) {
            return super.getFirstWindowIndex(z);
        }

        public Timeline.Window getWindow(int i, Timeline.Window window, long j) {
            return ((MediaItemData) this.playlist.get(i)).getWindow(this.firstPeriodIndexByWindowIndex[i], window);
        }

        public int getPeriodCount() {
            return this.windowIndexByPeriodIndex.length;
        }

        public Timeline.Period getPeriodByUid(Object obj, Timeline.Period period) {
            return getPeriod(((Integer) Assertions.checkNotNull(this.periodIndexByUid.get(obj))).intValue(), period, true);
        }

        public Timeline.Period getPeriod(int i, Timeline.Period period, boolean z) {
            int i2 = this.windowIndexByPeriodIndex[i];
            return ((MediaItemData) this.playlist.get(i2)).getPeriod(i2, i - this.firstPeriodIndexByWindowIndex[i2], period);
        }

        public int getIndexOfPeriod(Object obj) {
            Integer num = this.periodIndexByUid.get(obj);
            if (num == null) {
                return -1;
            }
            return num.intValue();
        }

        public Object getUidOfPeriod(int i) {
            int i2 = this.windowIndexByPeriodIndex[i];
            return ((MediaItemData) this.playlist.get(i2)).getPeriodUid(i - this.firstPeriodIndexByWindowIndex[i2]);
        }

        private static int getPeriodCountInMediaItem(MediaItemData mediaItemData) {
            if (mediaItemData.periods.isEmpty()) {
                return 1;
            }
            return mediaItemData.periods.size();
        }
    }

    protected static final class MediaItemData {
        public final long defaultPositionUs;
        public final long durationUs;
        public final long elapsedRealtimeEpochOffsetMs;
        public final boolean isDynamic;
        public final boolean isPlaceholder;
        public final boolean isSeekable;
        public final MediaItem.LiveConfiguration liveConfiguration;
        public final Object manifest;
        public final MediaItem mediaItem;
        public final MediaMetadata mediaMetadata;
        private final long[] periodPositionInWindowUs;
        public final ImmutableList<PeriodData> periods;
        public final long positionInFirstPeriodUs;
        public final long presentationStartTimeMs;
        public final Tracks tracks;
        public final Object uid;
        public final long windowStartTimeMs;

        public static final class Builder {
            /* access modifiers changed from: private */
            public long defaultPositionUs;
            /* access modifiers changed from: private */
            public long durationUs;
            /* access modifiers changed from: private */
            public long elapsedRealtimeEpochOffsetMs;
            /* access modifiers changed from: private */
            public boolean isDynamic;
            /* access modifiers changed from: private */
            public boolean isPlaceholder;
            /* access modifiers changed from: private */
            public boolean isSeekable;
            /* access modifiers changed from: private */
            public MediaItem.LiveConfiguration liveConfiguration;
            /* access modifiers changed from: private */
            public Object manifest;
            /* access modifiers changed from: private */
            public MediaItem mediaItem;
            /* access modifiers changed from: private */
            public MediaMetadata mediaMetadata;
            /* access modifiers changed from: private */
            public ImmutableList<PeriodData> periods;
            /* access modifiers changed from: private */
            public long positionInFirstPeriodUs;
            /* access modifiers changed from: private */
            public long presentationStartTimeMs;
            /* access modifiers changed from: private */
            public Tracks tracks;
            /* access modifiers changed from: private */
            public Object uid;
            /* access modifiers changed from: private */
            public long windowStartTimeMs;

            public Builder(Object obj) {
                this.uid = obj;
                this.tracks = Tracks.EMPTY;
                this.mediaItem = MediaItem.EMPTY;
                this.mediaMetadata = null;
                this.manifest = null;
                this.liveConfiguration = null;
                this.presentationStartTimeMs = C.TIME_UNSET;
                this.windowStartTimeMs = C.TIME_UNSET;
                this.elapsedRealtimeEpochOffsetMs = C.TIME_UNSET;
                this.isSeekable = false;
                this.isDynamic = false;
                this.defaultPositionUs = 0;
                this.durationUs = C.TIME_UNSET;
                this.positionInFirstPeriodUs = 0;
                this.isPlaceholder = false;
                this.periods = ImmutableList.of();
            }

            private Builder(MediaItemData mediaItemData) {
                this.uid = mediaItemData.uid;
                this.tracks = mediaItemData.tracks;
                this.mediaItem = mediaItemData.mediaItem;
                this.mediaMetadata = mediaItemData.mediaMetadata;
                this.manifest = mediaItemData.manifest;
                this.liveConfiguration = mediaItemData.liveConfiguration;
                this.presentationStartTimeMs = mediaItemData.presentationStartTimeMs;
                this.windowStartTimeMs = mediaItemData.windowStartTimeMs;
                this.elapsedRealtimeEpochOffsetMs = mediaItemData.elapsedRealtimeEpochOffsetMs;
                this.isSeekable = mediaItemData.isSeekable;
                this.isDynamic = mediaItemData.isDynamic;
                this.defaultPositionUs = mediaItemData.defaultPositionUs;
                this.durationUs = mediaItemData.durationUs;
                this.positionInFirstPeriodUs = mediaItemData.positionInFirstPeriodUs;
                this.isPlaceholder = mediaItemData.isPlaceholder;
                this.periods = mediaItemData.periods;
            }

            public Builder setUid(Object obj) {
                this.uid = obj;
                return this;
            }

            public Builder setTracks(Tracks tracks2) {
                this.tracks = tracks2;
                return this;
            }

            public Builder setMediaItem(MediaItem mediaItem2) {
                this.mediaItem = mediaItem2;
                return this;
            }

            public Builder setMediaMetadata(MediaMetadata mediaMetadata2) {
                this.mediaMetadata = mediaMetadata2;
                return this;
            }

            public Builder setManifest(Object obj) {
                this.manifest = obj;
                return this;
            }

            public Builder setLiveConfiguration(MediaItem.LiveConfiguration liveConfiguration2) {
                this.liveConfiguration = liveConfiguration2;
                return this;
            }

            public Builder setPresentationStartTimeMs(long j) {
                this.presentationStartTimeMs = j;
                return this;
            }

            public Builder setWindowStartTimeMs(long j) {
                this.windowStartTimeMs = j;
                return this;
            }

            public Builder setElapsedRealtimeEpochOffsetMs(long j) {
                this.elapsedRealtimeEpochOffsetMs = j;
                return this;
            }

            public Builder setIsSeekable(boolean z) {
                this.isSeekable = z;
                return this;
            }

            public Builder setIsDynamic(boolean z) {
                this.isDynamic = z;
                return this;
            }

            public Builder setDefaultPositionUs(long j) {
                Assertions.checkArgument(j >= 0);
                this.defaultPositionUs = j;
                return this;
            }

            public Builder setDurationUs(long j) {
                Assertions.checkArgument(j == C.TIME_UNSET || j >= 0);
                this.durationUs = j;
                return this;
            }

            public Builder setPositionInFirstPeriodUs(long j) {
                Assertions.checkArgument(j >= 0);
                this.positionInFirstPeriodUs = j;
                return this;
            }

            public Builder setIsPlaceholder(boolean z) {
                this.isPlaceholder = z;
                return this;
            }

            public Builder setPeriods(List<PeriodData> list) {
                int size = list.size();
                int i = 0;
                while (i < size - 1) {
                    Assertions.checkArgument(list.get(i).durationUs != C.TIME_UNSET, "Periods other than last need a duration");
                    int i2 = i + 1;
                    for (int i3 = i2; i3 < size; i3++) {
                        Assertions.checkArgument(!list.get(i).uid.equals(list.get(i3).uid), "Duplicate PeriodData UIDs in period list");
                    }
                    i = i2;
                }
                this.periods = ImmutableList.copyOf(list);
                return this;
            }

            public MediaItemData build() {
                return new MediaItemData(this);
            }
        }

        private MediaItemData(Builder builder) {
            int i = 0;
            if (builder.liveConfiguration == null) {
                Assertions.checkArgument(builder.presentationStartTimeMs == C.TIME_UNSET, "presentationStartTimeMs can only be set if liveConfiguration != null");
                Assertions.checkArgument(builder.windowStartTimeMs == C.TIME_UNSET, "windowStartTimeMs can only be set if liveConfiguration != null");
                Assertions.checkArgument(builder.elapsedRealtimeEpochOffsetMs == C.TIME_UNSET, "elapsedRealtimeEpochOffsetMs can only be set if liveConfiguration != null");
            } else if (!(builder.presentationStartTimeMs == C.TIME_UNSET || builder.windowStartTimeMs == C.TIME_UNSET)) {
                Assertions.checkArgument(builder.windowStartTimeMs >= builder.presentationStartTimeMs, "windowStartTimeMs can't be less than presentationStartTimeMs");
            }
            int size = builder.periods.size();
            if (builder.durationUs != C.TIME_UNSET) {
                Assertions.checkArgument(builder.defaultPositionUs <= builder.durationUs, "defaultPositionUs can't be greater than durationUs");
            }
            this.uid = builder.uid;
            this.tracks = builder.tracks;
            this.mediaItem = builder.mediaItem;
            this.mediaMetadata = builder.mediaMetadata;
            this.manifest = builder.manifest;
            this.liveConfiguration = builder.liveConfiguration;
            this.presentationStartTimeMs = builder.presentationStartTimeMs;
            this.windowStartTimeMs = builder.windowStartTimeMs;
            this.elapsedRealtimeEpochOffsetMs = builder.elapsedRealtimeEpochOffsetMs;
            this.isSeekable = builder.isSeekable;
            this.isDynamic = builder.isDynamic;
            this.defaultPositionUs = builder.defaultPositionUs;
            this.durationUs = builder.durationUs;
            long access$6600 = builder.positionInFirstPeriodUs;
            this.positionInFirstPeriodUs = access$6600;
            this.isPlaceholder = builder.isPlaceholder;
            ImmutableList<PeriodData> access$5600 = builder.periods;
            this.periods = access$5600;
            long[] jArr = new long[access$5600.size()];
            this.periodPositionInWindowUs = jArr;
            if (!access$5600.isEmpty()) {
                jArr[0] = -access$6600;
                while (i < size - 1) {
                    long[] jArr2 = this.periodPositionInWindowUs;
                    int i2 = i + 1;
                    jArr2[i2] = jArr2[i] + ((PeriodData) this.periods.get(i)).durationUs;
                    i = i2;
                }
            }
        }

        public Builder buildUpon() {
            return new Builder();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof MediaItemData)) {
                return false;
            }
            MediaItemData mediaItemData = (MediaItemData) obj;
            if (this.uid.equals(mediaItemData.uid) && this.tracks.equals(mediaItemData.tracks) && this.mediaItem.equals(mediaItemData.mediaItem) && Util.areEqual(this.mediaMetadata, mediaItemData.mediaMetadata) && Util.areEqual(this.manifest, mediaItemData.manifest) && Util.areEqual(this.liveConfiguration, mediaItemData.liveConfiguration) && this.presentationStartTimeMs == mediaItemData.presentationStartTimeMs && this.windowStartTimeMs == mediaItemData.windowStartTimeMs && this.elapsedRealtimeEpochOffsetMs == mediaItemData.elapsedRealtimeEpochOffsetMs && this.isSeekable == mediaItemData.isSeekable && this.isDynamic == mediaItemData.isDynamic && this.defaultPositionUs == mediaItemData.defaultPositionUs && this.durationUs == mediaItemData.durationUs && this.positionInFirstPeriodUs == mediaItemData.positionInFirstPeriodUs && this.isPlaceholder == mediaItemData.isPlaceholder && this.periods.equals(mediaItemData.periods)) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            int hashCode = (((((JfifUtil.MARKER_EOI + this.uid.hashCode()) * 31) + this.tracks.hashCode()) * 31) + this.mediaItem.hashCode()) * 31;
            MediaMetadata mediaMetadata2 = this.mediaMetadata;
            int i = 0;
            int hashCode2 = (hashCode + (mediaMetadata2 == null ? 0 : mediaMetadata2.hashCode())) * 31;
            Object obj = this.manifest;
            int hashCode3 = (hashCode2 + (obj == null ? 0 : obj.hashCode())) * 31;
            MediaItem.LiveConfiguration liveConfiguration2 = this.liveConfiguration;
            if (liveConfiguration2 != null) {
                i = liveConfiguration2.hashCode();
            }
            long j = this.presentationStartTimeMs;
            long j2 = this.windowStartTimeMs;
            long j3 = this.elapsedRealtimeEpochOffsetMs;
            long j4 = this.defaultPositionUs;
            long j5 = this.durationUs;
            long j6 = this.positionInFirstPeriodUs;
            return ((((((((((((((((((((hashCode3 + i) * 31) + ((int) (j ^ (j >>> 32)))) * 31) + ((int) (j2 ^ (j2 >>> 32)))) * 31) + ((int) (j3 ^ (j3 >>> 32)))) * 31) + (this.isSeekable ? 1 : 0)) * 31) + (this.isDynamic ? 1 : 0)) * 31) + ((int) (j4 ^ (j4 >>> 32)))) * 31) + ((int) (j5 ^ (j5 >>> 32)))) * 31) + ((int) (j6 ^ (j6 >>> 32)))) * 31) + (this.isPlaceholder ? 1 : 0)) * 31) + this.periods.hashCode();
        }

        /* access modifiers changed from: private */
        public static MediaItemData buildFromState(State state, int i, Timeline.Period period, Timeline.Window window) {
            boolean z = SimpleBasePlayer.getCurrentMediaItemIndexInternal(state) == i;
            state.timeline.getWindow(i, window);
            ImmutableList.Builder builder = ImmutableList.builder();
            for (int i2 = window.firstPeriodIndex; i2 <= window.lastPeriodIndex; i2++) {
                state.timeline.getPeriod(i2, period, true);
                builder.add((Object) new PeriodData.Builder(Assertions.checkNotNull(period.uid)).setAdPlaybackState(period.adPlaybackState).setDurationUs(period.durationUs).setIsPlaceholder(period.isPlaceholder).build());
            }
            return new Builder(window.uid).setDefaultPositionUs(window.defaultPositionUs).setDurationUs(window.durationUs).setElapsedRealtimeEpochOffsetMs(window.elapsedRealtimeEpochOffsetMs).setIsDynamic(window.isDynamic).setIsPlaceholder(window.isPlaceholder).setIsSeekable(window.isSeekable).setLiveConfiguration(window.liveConfiguration).setManifest(window.manifest).setMediaItem(window.mediaItem).setMediaMetadata(z ? state.currentMetadata : null).setPeriods(builder.build()).setPositionInFirstPeriodUs(window.positionInFirstPeriodUs).setPresentationStartTimeMs(window.presentationStartTimeMs).setTracks(z ? state.currentTracks : Tracks.EMPTY).setWindowStartTimeMs(window.windowStartTimeMs).build();
        }

        /* access modifiers changed from: private */
        public Timeline.Window getWindow(int i, Timeline.Window window) {
            Timeline.Window window2 = window;
            int size = this.periods.isEmpty() ? 1 : this.periods.size();
            Object obj = this.uid;
            Timeline.Window window3 = window;
            window.set(obj, this.mediaItem, this.manifest, this.presentationStartTimeMs, this.windowStartTimeMs, this.elapsedRealtimeEpochOffsetMs, this.isSeekable, this.isDynamic, this.liveConfiguration, this.defaultPositionUs, this.durationUs, i, (i + size) - 1, this.positionInFirstPeriodUs);
            Timeline.Window window4 = window;
            window4.isPlaceholder = this.isPlaceholder;
            return window4;
        }

        /* access modifiers changed from: private */
        public Timeline.Period getPeriod(int i, int i2, Timeline.Period period) {
            if (this.periods.isEmpty()) {
                Object obj = this.uid;
                period.set(obj, obj, i, this.positionInFirstPeriodUs + this.durationUs, 0, AdPlaybackState.NONE, this.isPlaceholder);
            } else {
                PeriodData periodData = (PeriodData) this.periods.get(i2);
                Object obj2 = periodData.uid;
                period.set(obj2, Pair.create(this.uid, obj2), i, periodData.durationUs, this.periodPositionInWindowUs[i2], periodData.adPlaybackState, periodData.isPlaceholder);
            }
            return period;
        }

        /* access modifiers changed from: private */
        public Object getPeriodUid(int i) {
            if (this.periods.isEmpty()) {
                return this.uid;
            }
            return Pair.create(this.uid, ((PeriodData) this.periods.get(i)).uid);
        }
    }

    protected static final class PeriodData {
        public final AdPlaybackState adPlaybackState;
        public final long durationUs;
        public final boolean isPlaceholder;
        public final Object uid;

        public static final class Builder {
            /* access modifiers changed from: private */
            public AdPlaybackState adPlaybackState;
            /* access modifiers changed from: private */
            public long durationUs;
            /* access modifiers changed from: private */
            public boolean isPlaceholder;
            /* access modifiers changed from: private */
            public Object uid;

            public Builder(Object obj) {
                this.uid = obj;
                this.durationUs = 0;
                this.adPlaybackState = AdPlaybackState.NONE;
                this.isPlaceholder = false;
            }

            private Builder(PeriodData periodData) {
                this.uid = periodData.uid;
                this.durationUs = periodData.durationUs;
                this.adPlaybackState = periodData.adPlaybackState;
                this.isPlaceholder = periodData.isPlaceholder;
            }

            public Builder setUid(Object obj) {
                this.uid = obj;
                return this;
            }

            public Builder setDurationUs(long j) {
                Assertions.checkArgument(j == C.TIME_UNSET || j >= 0);
                this.durationUs = j;
                return this;
            }

            public Builder setAdPlaybackState(AdPlaybackState adPlaybackState2) {
                this.adPlaybackState = adPlaybackState2;
                return this;
            }

            public Builder setIsPlaceholder(boolean z) {
                this.isPlaceholder = z;
                return this;
            }

            public PeriodData build() {
                return new PeriodData(this);
            }
        }

        private PeriodData(Builder builder) {
            this.uid = builder.uid;
            this.durationUs = builder.durationUs;
            this.adPlaybackState = builder.adPlaybackState;
            this.isPlaceholder = builder.isPlaceholder;
        }

        public Builder buildUpon() {
            return new Builder();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof PeriodData)) {
                return false;
            }
            PeriodData periodData = (PeriodData) obj;
            if (!this.uid.equals(periodData.uid) || this.durationUs != periodData.durationUs || !this.adPlaybackState.equals(periodData.adPlaybackState) || this.isPlaceholder != periodData.isPlaceholder) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            long j = this.durationUs;
            return ((((((JfifUtil.MARKER_EOI + this.uid.hashCode()) * 31) + ((int) (j ^ (j >>> 32)))) * 31) + this.adPlaybackState.hashCode()) * 31) + (this.isPlaceholder ? 1 : 0);
        }
    }

    protected interface PositionSupplier {
        public static final PositionSupplier ZERO = getConstant(0);

        static /* synthetic */ long lambda$getConstant$0(long j) {
            return j;
        }

        long get();

        static PositionSupplier getConstant(long j) {
            return new SimpleBasePlayer$PositionSupplier$$ExternalSyntheticLambda1(j);
        }

        static PositionSupplier getExtrapolating(long j, float f) {
            return new SimpleBasePlayer$PositionSupplier$$ExternalSyntheticLambda0(j, SystemClock.elapsedRealtime(), f);
        }

        static /* synthetic */ long lambda$getExtrapolating$1(long j, long j2, float f) {
            return j + ((long) (((float) (SystemClock.elapsedRealtime() - j2)) * f));
        }
    }

    protected SimpleBasePlayer(Looper looper) {
        this(looper, Clock.DEFAULT);
    }

    protected SimpleBasePlayer(Looper looper, Clock clock) {
        this.applicationLooper = looper;
        this.applicationHandler = clock.createHandler(looper, (Handler.Callback) null);
        this.pendingOperations = new HashSet<>();
        this.period = new Timeline.Period();
        this.listeners = new ListenerSet<>(looper, clock, new SimpleBasePlayer$$ExternalSyntheticLambda47(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$new$0$androidx-media3-common-SimpleBasePlayer  reason: not valid java name */
    public /* synthetic */ void m396lambda$new$0$androidxmedia3commonSimpleBasePlayer(Player.Listener listener, FlagSet flagSet) {
        listener.onEvents(this, new Player.Events(flagSet));
    }

    public final void addListener(Player.Listener listener) {
        this.listeners.add((Player.Listener) Assertions.checkNotNull(listener));
    }

    public final void removeListener(Player.Listener listener) {
        verifyApplicationThreadAndInitState();
        this.listeners.remove(listener);
    }

    public final Looper getApplicationLooper() {
        return this.applicationLooper;
    }

    public final Player.Commands getAvailableCommands() {
        verifyApplicationThreadAndInitState();
        return this.state.availableCommands;
    }

    public final void setPlayWhenReady(boolean z) {
        verifyApplicationThreadAndInitState();
        State state2 = this.state;
        if (shouldHandleCommand(1)) {
            updateStateForPendingOperation(handleSetPlayWhenReady(z), new SimpleBasePlayer$$ExternalSyntheticLambda40(state2, z));
        }
    }

    public final boolean getPlayWhenReady() {
        verifyApplicationThreadAndInitState();
        return this.state.playWhenReady;
    }

    public final void setMediaItems(List<MediaItem> list, boolean z) {
        int i;
        long j;
        verifyApplicationThreadAndInitState();
        if (z) {
            i = -1;
        } else {
            i = this.state.currentMediaItemIndex;
        }
        if (z) {
            j = C.TIME_UNSET;
        } else {
            j = this.state.contentPositionMsSupplier.get();
        }
        setMediaItemsInternal(list, i, j);
    }

    public final void setMediaItems(List<MediaItem> list, int i, long j) {
        verifyApplicationThreadAndInitState();
        if (i == -1) {
            i = this.state.currentMediaItemIndex;
            j = this.state.contentPositionMsSupplier.get();
        }
        setMediaItemsInternal(list, i, j);
    }

    @RequiresNonNull({"state"})
    private void setMediaItemsInternal(List<MediaItem> list, int i, long j) {
        Assertions.checkArgument(i == -1 || i >= 0);
        State state2 = this.state;
        if (shouldHandleCommand(20) || (list.size() == 1 && shouldHandleCommand(31))) {
            updateStateForPendingOperation(handleSetMediaItems(list, i, j), new SimpleBasePlayer$$ExternalSyntheticLambda62(this, list, state2, i, j));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setMediaItemsInternal$2$androidx-media3-common-SimpleBasePlayer  reason: not valid java name */
    public /* synthetic */ State m400lambda$setMediaItemsInternal$2$androidxmedia3commonSimpleBasePlayer(List list, State state2, int i, long j) {
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < list.size(); i2++) {
            arrayList.add(getPlaceholderMediaItemData((MediaItem) list.get(i2)));
        }
        return getStateWithNewPlaylistAndPosition(state2, arrayList, i, j, this.window);
    }

    public final void addMediaItems(int i, List<MediaItem> list) {
        verifyApplicationThreadAndInitState();
        Assertions.checkArgument(i >= 0);
        State state2 = this.state;
        int windowCount = state2.timeline.getWindowCount();
        if (shouldHandleCommand(20) && !list.isEmpty()) {
            int min = Math.min(i, windowCount);
            updateStateForPendingOperation(handleAddMediaItems(min, list), new SimpleBasePlayer$$ExternalSyntheticLambda56(this, state2, list, min));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$addMediaItems$3$androidx-media3-common-SimpleBasePlayer  reason: not valid java name */
    public /* synthetic */ State m394lambda$addMediaItems$3$androidxmedia3commonSimpleBasePlayer(State state2, List list, int i) {
        List<MediaItemData> buildMutablePlaylistFromState = buildMutablePlaylistFromState(state2, this.period, this.window);
        for (int i2 = 0; i2 < list.size(); i2++) {
            buildMutablePlaylistFromState.add(i2 + i, getPlaceholderMediaItemData((MediaItem) list.get(i2)));
        }
        if (!state2.timeline.isEmpty()) {
            return getStateWithNewPlaylist(state2, buildMutablePlaylistFromState, this.period, this.window);
        }
        return getStateWithNewPlaylistAndPosition(state2, buildMutablePlaylistFromState, state2.currentMediaItemIndex, state2.contentPositionMsSupplier.get(), this.window);
    }

    public final void moveMediaItems(int i, int i2, int i3) {
        verifyApplicationThreadAndInitState();
        Assertions.checkArgument(i >= 0 && i2 >= i && i3 >= 0);
        State state2 = this.state;
        int windowCount = state2.timeline.getWindowCount();
        if (shouldHandleCommand(20) && windowCount != 0 && i < windowCount) {
            int min = Math.min(i2, windowCount);
            int min2 = Math.min(i3, windowCount - (min - i));
            if (i != min && min2 != i) {
                updateStateForPendingOperation(handleMoveMediaItems(i, min, min2), new SimpleBasePlayer$$ExternalSyntheticLambda51(this, state2, i, min, min2));
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$moveMediaItems$4$androidx-media3-common-SimpleBasePlayer  reason: not valid java name */
    public /* synthetic */ State m395lambda$moveMediaItems$4$androidxmedia3commonSimpleBasePlayer(State state2, int i, int i2, int i3) {
        List<MediaItemData> buildMutablePlaylistFromState = buildMutablePlaylistFromState(state2, this.period, this.window);
        Util.moveItems(buildMutablePlaylistFromState, i, i2, i3);
        return getStateWithNewPlaylist(state2, buildMutablePlaylistFromState, this.period, this.window);
    }

    public final void replaceMediaItems(int i, int i2, List<MediaItem> list) {
        verifyApplicationThreadAndInitState();
        Assertions.checkArgument(i >= 0 && i <= i2);
        State state2 = this.state;
        int windowCount = state2.timeline.getWindowCount();
        if (shouldHandleCommand(20) && i <= windowCount) {
            int min = Math.min(i2, windowCount);
            updateStateForPendingOperation(handleReplaceMediaItems(i, min, list), new SimpleBasePlayer$$ExternalSyntheticLambda33(this, state2, list, min, i));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$replaceMediaItems$5$androidx-media3-common-SimpleBasePlayer  reason: not valid java name */
    public /* synthetic */ State m398lambda$replaceMediaItems$5$androidxmedia3commonSimpleBasePlayer(State state2, List list, int i, int i2) {
        State state3;
        List<MediaItemData> buildMutablePlaylistFromState = buildMutablePlaylistFromState(state2, this.period, this.window);
        for (int i3 = 0; i3 < list.size(); i3++) {
            buildMutablePlaylistFromState.add(i3 + i, getPlaceholderMediaItemData((MediaItem) list.get(i3)));
        }
        if (!state2.timeline.isEmpty()) {
            state3 = getStateWithNewPlaylist(state2, buildMutablePlaylistFromState, this.period, this.window);
        } else {
            state3 = getStateWithNewPlaylistAndPosition(state2, buildMutablePlaylistFromState, state2.currentMediaItemIndex, state2.contentPositionMsSupplier.get(), this.window);
        }
        if (i2 >= i) {
            return state3;
        }
        Util.removeRange(buildMutablePlaylistFromState, i2, i);
        return getStateWithNewPlaylist(state3, buildMutablePlaylistFromState, this.period, this.window);
    }

    public final void removeMediaItems(int i, int i2) {
        int min;
        verifyApplicationThreadAndInitState();
        Assertions.checkArgument(i >= 0 && i2 >= i);
        State state2 = this.state;
        int windowCount = state2.timeline.getWindowCount();
        if (shouldHandleCommand(20) && windowCount != 0 && i < windowCount && i != (min = Math.min(i2, windowCount))) {
            updateStateForPendingOperation(handleRemoveMediaItems(i, min), new SimpleBasePlayer$$ExternalSyntheticLambda57(this, state2, i, min));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$removeMediaItems$6$androidx-media3-common-SimpleBasePlayer  reason: not valid java name */
    public /* synthetic */ State m397lambda$removeMediaItems$6$androidxmedia3commonSimpleBasePlayer(State state2, int i, int i2) {
        List<MediaItemData> buildMutablePlaylistFromState = buildMutablePlaylistFromState(state2, this.period, this.window);
        Util.removeRange(buildMutablePlaylistFromState, i, i2);
        return getStateWithNewPlaylist(state2, buildMutablePlaylistFromState, this.period, this.window);
    }

    public final void prepare() {
        verifyApplicationThreadAndInitState();
        State state2 = this.state;
        if (shouldHandleCommand(2)) {
            updateStateForPendingOperation(handlePrepare(), new SimpleBasePlayer$$ExternalSyntheticLambda53(state2));
        }
    }

    static /* synthetic */ State lambda$prepare$7(State state2) {
        return state2.buildUpon().setPlayerError((PlaybackException) null).setPlaybackState(state2.timeline.isEmpty() ? 4 : 2).build();
    }

    public final int getPlaybackState() {
        verifyApplicationThreadAndInitState();
        return this.state.playbackState;
    }

    public final int getPlaybackSuppressionReason() {
        verifyApplicationThreadAndInitState();
        return this.state.playbackSuppressionReason;
    }

    public final PlaybackException getPlayerError() {
        verifyApplicationThreadAndInitState();
        return this.state.playerError;
    }

    public final void setRepeatMode(int i) {
        verifyApplicationThreadAndInitState();
        State state2 = this.state;
        if (shouldHandleCommand(15)) {
            updateStateForPendingOperation(handleSetRepeatMode(i), new SimpleBasePlayer$$ExternalSyntheticLambda42(state2, i));
        }
    }

    public final int getRepeatMode() {
        verifyApplicationThreadAndInitState();
        return this.state.repeatMode;
    }

    public final void setShuffleModeEnabled(boolean z) {
        verifyApplicationThreadAndInitState();
        State state2 = this.state;
        if (shouldHandleCommand(14)) {
            updateStateForPendingOperation(handleSetShuffleModeEnabled(z), new SimpleBasePlayer$$ExternalSyntheticLambda59(state2, z));
        }
    }

    public final boolean getShuffleModeEnabled() {
        verifyApplicationThreadAndInitState();
        return this.state.shuffleModeEnabled;
    }

    public final boolean isLoading() {
        verifyApplicationThreadAndInitState();
        return this.state.isLoading;
    }

    public final void seekTo(int i, long j, int i2, boolean z) {
        int i3 = i;
        verifyApplicationThreadAndInitState();
        Assertions.checkArgument(i3 == -1 || i3 >= 0);
        State state2 = this.state;
        if (shouldHandleCommand(i2)) {
            boolean z2 = i3 == -1 || isPlayingAd() || (!state2.timeline.isEmpty() && i3 >= state2.timeline.getWindowCount());
            updateStateForPendingOperation(handleSeek(i, j, i2), new SimpleBasePlayer$$ExternalSyntheticLambda11(this, z2, state2, i, j), !z2, z);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$seekTo$10$androidx-media3-common-SimpleBasePlayer  reason: not valid java name */
    public /* synthetic */ State m399lambda$seekTo$10$androidxmedia3commonSimpleBasePlayer(boolean z, State state2, int i, long j) {
        if (z) {
            return state2;
        }
        return getStateWithNewPlaylistAndPosition(state2, (List<MediaItemData>) null, i, j, this.window);
    }

    public final long getSeekBackIncrement() {
        verifyApplicationThreadAndInitState();
        return this.state.seekBackIncrementMs;
    }

    public final long getSeekForwardIncrement() {
        verifyApplicationThreadAndInitState();
        return this.state.seekForwardIncrementMs;
    }

    public final long getMaxSeekToPreviousPosition() {
        verifyApplicationThreadAndInitState();
        return this.state.maxSeekToPreviousPositionMs;
    }

    public final void setPlaybackParameters(PlaybackParameters playbackParameters) {
        verifyApplicationThreadAndInitState();
        State state2 = this.state;
        if (shouldHandleCommand(13)) {
            updateStateForPendingOperation(handleSetPlaybackParameters(playbackParameters), new SimpleBasePlayer$$ExternalSyntheticLambda64(state2, playbackParameters));
        }
    }

    public final PlaybackParameters getPlaybackParameters() {
        verifyApplicationThreadAndInitState();
        return this.state.playbackParameters;
    }

    public final void stop() {
        verifyApplicationThreadAndInitState();
        State state2 = this.state;
        if (shouldHandleCommand(3)) {
            updateStateForPendingOperation(handleStop(), new SimpleBasePlayer$$ExternalSyntheticLambda22(this, state2));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$stop$12$androidx-media3-common-SimpleBasePlayer  reason: not valid java name */
    public /* synthetic */ State m401lambda$stop$12$androidxmedia3commonSimpleBasePlayer(State state2) {
        return state2.buildUpon().setPlaybackState(1).setTotalBufferedDurationMs(PositionSupplier.ZERO).setContentBufferedPositionMs(PositionSupplier.getConstant(getContentPositionMsInternal(state2, this.window))).setAdBufferedPositionMs(state2.adPositionMsSupplier).setIsLoading(false).build();
    }

    public final void release() {
        verifyApplicationThreadAndInitState();
        State state2 = this.state;
        if (shouldHandleCommand(32)) {
            updateStateForPendingOperation(handleRelease(), new SimpleBasePlayer$$ExternalSyntheticLambda54(state2));
            this.released = true;
            this.listeners.release();
            this.state = this.state.buildUpon().setPlaybackState(1).setTotalBufferedDurationMs(PositionSupplier.ZERO).setContentBufferedPositionMs(PositionSupplier.getConstant(getContentPositionMsInternal(state2, this.window))).setAdBufferedPositionMs(state2.adPositionMsSupplier).setIsLoading(false).build();
        }
    }

    public final Tracks getCurrentTracks() {
        verifyApplicationThreadAndInitState();
        return this.state.currentTracks;
    }

    public final TrackSelectionParameters getTrackSelectionParameters() {
        verifyApplicationThreadAndInitState();
        return this.state.trackSelectionParameters;
    }

    public final void setTrackSelectionParameters(TrackSelectionParameters trackSelectionParameters) {
        verifyApplicationThreadAndInitState();
        State state2 = this.state;
        if (shouldHandleCommand(29)) {
            updateStateForPendingOperation(handleSetTrackSelectionParameters(trackSelectionParameters), new SimpleBasePlayer$$ExternalSyntheticLambda60(state2, trackSelectionParameters));
        }
    }

    public final MediaMetadata getMediaMetadata() {
        verifyApplicationThreadAndInitState();
        return this.state.currentMetadata;
    }

    public final MediaMetadata getPlaylistMetadata() {
        verifyApplicationThreadAndInitState();
        return this.state.playlistMetadata;
    }

    public final void setPlaylistMetadata(MediaMetadata mediaMetadata) {
        verifyApplicationThreadAndInitState();
        State state2 = this.state;
        if (shouldHandleCommand(19)) {
            updateStateForPendingOperation(handleSetPlaylistMetadata(mediaMetadata), new SimpleBasePlayer$$ExternalSyntheticLambda4(state2, mediaMetadata));
        }
    }

    public final Timeline getCurrentTimeline() {
        verifyApplicationThreadAndInitState();
        return this.state.timeline;
    }

    public final int getCurrentPeriodIndex() {
        verifyApplicationThreadAndInitState();
        return getCurrentPeriodIndexInternal(this.state, this.window, this.period);
    }

    public final int getCurrentMediaItemIndex() {
        verifyApplicationThreadAndInitState();
        return getCurrentMediaItemIndexInternal(this.state);
    }

    public final long getDuration() {
        verifyApplicationThreadAndInitState();
        if (!isPlayingAd()) {
            return getContentDuration();
        }
        this.state.timeline.getPeriod(getCurrentPeriodIndex(), this.period);
        return Util.usToMs(this.period.getAdDurationUs(this.state.currentAdGroupIndex, this.state.currentAdIndexInAdGroup));
    }

    public final long getCurrentPosition() {
        verifyApplicationThreadAndInitState();
        return isPlayingAd() ? this.state.adPositionMsSupplier.get() : getContentPosition();
    }

    public final long getBufferedPosition() {
        verifyApplicationThreadAndInitState();
        if (isPlayingAd()) {
            return Math.max(this.state.adBufferedPositionMsSupplier.get(), this.state.adPositionMsSupplier.get());
        }
        return getContentBufferedPosition();
    }

    public final long getTotalBufferedDuration() {
        verifyApplicationThreadAndInitState();
        return this.state.totalBufferedDurationMsSupplier.get();
    }

    public final boolean isPlayingAd() {
        verifyApplicationThreadAndInitState();
        return this.state.currentAdGroupIndex != -1;
    }

    public final int getCurrentAdGroupIndex() {
        verifyApplicationThreadAndInitState();
        return this.state.currentAdGroupIndex;
    }

    public final int getCurrentAdIndexInAdGroup() {
        verifyApplicationThreadAndInitState();
        return this.state.currentAdIndexInAdGroup;
    }

    public final long getContentPosition() {
        verifyApplicationThreadAndInitState();
        return getContentPositionMsInternal(this.state, this.window);
    }

    public final long getContentBufferedPosition() {
        verifyApplicationThreadAndInitState();
        return Math.max(getContentBufferedPositionMsInternal(this.state, this.window), getContentPositionMsInternal(this.state, this.window));
    }

    public final AudioAttributes getAudioAttributes() {
        verifyApplicationThreadAndInitState();
        return this.state.audioAttributes;
    }

    public final void setVolume(float f) {
        verifyApplicationThreadAndInitState();
        State state2 = this.state;
        if (shouldHandleCommand(24)) {
            updateStateForPendingOperation(handleSetVolume(f), new SimpleBasePlayer$$ExternalSyntheticLambda48(state2, f));
        }
    }

    public final float getVolume() {
        verifyApplicationThreadAndInitState();
        return this.state.volume;
    }

    public final void setVideoSurface(Surface surface) {
        verifyApplicationThreadAndInitState();
        State state2 = this.state;
        if (shouldHandleCommand(27)) {
            if (surface == null) {
                clearVideoSurface();
            } else {
                updateStateForPendingOperation(handleSetVideoOutput(surface), new SimpleBasePlayer$$ExternalSyntheticLambda63(state2));
            }
        }
    }

    public final void setVideoSurfaceHolder(SurfaceHolder surfaceHolder) {
        verifyApplicationThreadAndInitState();
        State state2 = this.state;
        if (shouldHandleCommand(27)) {
            if (surfaceHolder == null) {
                clearVideoSurface();
            } else {
                updateStateForPendingOperation(handleSetVideoOutput(surfaceHolder), new SimpleBasePlayer$$ExternalSyntheticLambda3(state2, surfaceHolder));
            }
        }
    }

    public final void setVideoSurfaceView(SurfaceView surfaceView) {
        verifyApplicationThreadAndInitState();
        State state2 = this.state;
        if (shouldHandleCommand(27)) {
            if (surfaceView == null) {
                clearVideoSurface();
            } else {
                updateStateForPendingOperation(handleSetVideoOutput(surfaceView), new SimpleBasePlayer$$ExternalSyntheticLambda58(state2, surfaceView));
            }
        }
    }

    public final void setVideoTextureView(TextureView textureView) {
        Size size;
        verifyApplicationThreadAndInitState();
        State state2 = this.state;
        if (shouldHandleCommand(27)) {
            if (textureView == null) {
                clearVideoSurface();
                return;
            }
            if (textureView.isAvailable()) {
                size = new Size(textureView.getWidth(), textureView.getHeight());
            } else {
                size = Size.ZERO;
            }
            updateStateForPendingOperation(handleSetVideoOutput(textureView), new SimpleBasePlayer$$ExternalSyntheticLambda39(state2, size));
        }
    }

    public final void clearVideoSurface() {
        clearVideoOutput((Object) null);
    }

    public final void clearVideoSurface(Surface surface) {
        clearVideoOutput(surface);
    }

    public final void clearVideoSurfaceHolder(SurfaceHolder surfaceHolder) {
        clearVideoOutput(surfaceHolder);
    }

    public final void clearVideoSurfaceView(SurfaceView surfaceView) {
        clearVideoOutput(surfaceView);
    }

    public final void clearVideoTextureView(TextureView textureView) {
        clearVideoOutput(textureView);
    }

    private void clearVideoOutput(Object obj) {
        verifyApplicationThreadAndInitState();
        State state2 = this.state;
        if (shouldHandleCommand(27)) {
            updateStateForPendingOperation(handleClearVideoOutput(obj), new SimpleBasePlayer$$ExternalSyntheticLambda52(state2));
        }
    }

    public final VideoSize getVideoSize() {
        verifyApplicationThreadAndInitState();
        return this.state.videoSize;
    }

    public final Size getSurfaceSize() {
        verifyApplicationThreadAndInitState();
        return this.state.surfaceSize;
    }

    public final CueGroup getCurrentCues() {
        verifyApplicationThreadAndInitState();
        return this.state.currentCues;
    }

    public final DeviceInfo getDeviceInfo() {
        verifyApplicationThreadAndInitState();
        return this.state.deviceInfo;
    }

    public final int getDeviceVolume() {
        verifyApplicationThreadAndInitState();
        return this.state.deviceVolume;
    }

    public final boolean isDeviceMuted() {
        verifyApplicationThreadAndInitState();
        return this.state.isDeviceMuted;
    }

    @Deprecated
    public final void setDeviceVolume(int i) {
        verifyApplicationThreadAndInitState();
        State state2 = this.state;
        if (shouldHandleCommand(25)) {
            updateStateForPendingOperation(handleSetDeviceVolume(i, 1), new SimpleBasePlayer$$ExternalSyntheticLambda0(state2, i));
        }
    }

    public final void setDeviceVolume(int i, int i2) {
        verifyApplicationThreadAndInitState();
        State state2 = this.state;
        if (shouldHandleCommand(33)) {
            updateStateForPendingOperation(handleSetDeviceVolume(i, i2), new SimpleBasePlayer$$ExternalSyntheticLambda61(state2, i));
        }
    }

    @Deprecated
    public final void increaseDeviceVolume() {
        verifyApplicationThreadAndInitState();
        State state2 = this.state;
        if (shouldHandleCommand(26)) {
            updateStateForPendingOperation(handleIncreaseDeviceVolume(1), new SimpleBasePlayer$$ExternalSyntheticLambda55(state2));
        }
    }

    public final void increaseDeviceVolume(int i) {
        verifyApplicationThreadAndInitState();
        State state2 = this.state;
        if (shouldHandleCommand(34)) {
            updateStateForPendingOperation(handleIncreaseDeviceVolume(i), new SimpleBasePlayer$$ExternalSyntheticLambda1(state2));
        }
    }

    @Deprecated
    public final void decreaseDeviceVolume() {
        verifyApplicationThreadAndInitState();
        State state2 = this.state;
        if (shouldHandleCommand(26)) {
            updateStateForPendingOperation(handleDecreaseDeviceVolume(1), new SimpleBasePlayer$$ExternalSyntheticLambda49(state2));
        }
    }

    public final void decreaseDeviceVolume(int i) {
        verifyApplicationThreadAndInitState();
        State state2 = this.state;
        if (shouldHandleCommand(34)) {
            updateStateForPendingOperation(handleDecreaseDeviceVolume(i), new SimpleBasePlayer$$ExternalSyntheticLambda44(state2));
        }
    }

    @Deprecated
    public final void setDeviceMuted(boolean z) {
        verifyApplicationThreadAndInitState();
        State state2 = this.state;
        if (shouldHandleCommand(26)) {
            updateStateForPendingOperation(handleSetDeviceMuted(z, 1), new SimpleBasePlayer$$ExternalSyntheticLambda41(state2, z));
        }
    }

    public final void setDeviceMuted(boolean z, int i) {
        verifyApplicationThreadAndInitState();
        State state2 = this.state;
        if (shouldHandleCommand(34)) {
            updateStateForPendingOperation(handleSetDeviceMuted(z, i), new SimpleBasePlayer$$ExternalSyntheticLambda50(state2, z));
        }
    }

    public final void setAudioAttributes(AudioAttributes audioAttributes, boolean z) {
        verifyApplicationThreadAndInitState();
        State state2 = this.state;
        if (shouldHandleCommand(35)) {
            updateStateForPendingOperation(handleSetAudioAttributes(audioAttributes, z), new SimpleBasePlayer$$ExternalSyntheticLambda2(state2, audioAttributes));
        }
    }

    /* access modifiers changed from: protected */
    public final void invalidateState() {
        verifyApplicationThreadAndInitState();
        if (this.pendingOperations.isEmpty() && !this.released) {
            updateStateAndInformListeners(getState(), false, false);
        }
    }

    /* access modifiers changed from: protected */
    public MediaItemData getPlaceholderMediaItemData(MediaItem mediaItem) {
        return new MediaItemData.Builder((Object) new PlaceholderUid()).setMediaItem(mediaItem).setIsDynamic(true).setIsPlaceholder(true).build();
    }

    /* access modifiers changed from: protected */
    public ListenableFuture<?> handleSetPlayWhenReady(boolean z) {
        throw new IllegalStateException("Missing implementation to handle COMMAND_PLAY_PAUSE");
    }

    /* access modifiers changed from: protected */
    public ListenableFuture<?> handlePrepare() {
        throw new IllegalStateException("Missing implementation to handle COMMAND_PREPARE");
    }

    /* access modifiers changed from: protected */
    public ListenableFuture<?> handleStop() {
        throw new IllegalStateException("Missing implementation to handle COMMAND_STOP");
    }

    /* access modifiers changed from: protected */
    public ListenableFuture<?> handleRelease() {
        throw new IllegalStateException("Missing implementation to handle COMMAND_RELEASE");
    }

    /* access modifiers changed from: protected */
    public ListenableFuture<?> handleSetRepeatMode(int i) {
        throw new IllegalStateException("Missing implementation to handle COMMAND_SET_REPEAT_MODE");
    }

    /* access modifiers changed from: protected */
    public ListenableFuture<?> handleSetShuffleModeEnabled(boolean z) {
        throw new IllegalStateException("Missing implementation to handle COMMAND_SET_SHUFFLE_MODE");
    }

    /* access modifiers changed from: protected */
    public ListenableFuture<?> handleSetPlaybackParameters(PlaybackParameters playbackParameters) {
        throw new IllegalStateException("Missing implementation to handle COMMAND_SET_SPEED_AND_PITCH");
    }

    /* access modifiers changed from: protected */
    public ListenableFuture<?> handleSetTrackSelectionParameters(TrackSelectionParameters trackSelectionParameters) {
        throw new IllegalStateException("Missing implementation to handle COMMAND_SET_TRACK_SELECTION_PARAMETERS");
    }

    /* access modifiers changed from: protected */
    public ListenableFuture<?> handleSetPlaylistMetadata(MediaMetadata mediaMetadata) {
        throw new IllegalStateException("Missing implementation to handle COMMAND_SET_PLAYLIST_METADATA");
    }

    /* access modifiers changed from: protected */
    public ListenableFuture<?> handleSetVolume(float f) {
        throw new IllegalStateException("Missing implementation to handle COMMAND_SET_VOLUME");
    }

    /* access modifiers changed from: protected */
    public ListenableFuture<?> handleSetDeviceVolume(int i, int i2) {
        throw new IllegalStateException("Missing implementation to handle COMMAND_SET_DEVICE_VOLUME or COMMAND_SET_DEVICE_VOLUME_WITH_FLAGS");
    }

    /* access modifiers changed from: protected */
    public ListenableFuture<?> handleIncreaseDeviceVolume(int i) {
        throw new IllegalStateException("Missing implementation to handle COMMAND_ADJUST_DEVICE_VOLUME or COMMAND_ADJUST_DEVICE_VOLUME_WITH_FLAGS");
    }

    /* access modifiers changed from: protected */
    public ListenableFuture<?> handleDecreaseDeviceVolume(int i) {
        throw new IllegalStateException("Missing implementation to handle COMMAND_ADJUST_DEVICE_VOLUME or COMMAND_ADJUST_DEVICE_VOLUME_WITH_FLAGS");
    }

    /* access modifiers changed from: protected */
    public ListenableFuture<?> handleSetDeviceMuted(boolean z, int i) {
        throw new IllegalStateException("Missing implementation to handle COMMAND_ADJUST_DEVICE_VOLUME or COMMAND_ADJUST_DEVICE_VOLUME_WITH_FLAGS");
    }

    /* access modifiers changed from: protected */
    public ListenableFuture<?> handleSetAudioAttributes(AudioAttributes audioAttributes, boolean z) {
        throw new IllegalStateException("Missing implementation to handle COMMAND_SET_AUDIO_ATTRIBUTES");
    }

    /* access modifiers changed from: protected */
    public ListenableFuture<?> handleSetVideoOutput(Object obj) {
        throw new IllegalStateException("Missing implementation to handle COMMAND_SET_VIDEO_SURFACE");
    }

    /* access modifiers changed from: protected */
    public ListenableFuture<?> handleClearVideoOutput(Object obj) {
        throw new IllegalStateException("Missing implementation to handle COMMAND_SET_VIDEO_SURFACE");
    }

    /* access modifiers changed from: protected */
    public ListenableFuture<?> handleSetMediaItems(List<MediaItem> list, int i, long j) {
        throw new IllegalStateException("Missing implementation to handle COMMAND_SET_MEDIA_ITEM(S)");
    }

    /* access modifiers changed from: protected */
    public ListenableFuture<?> handleAddMediaItems(int i, List<MediaItem> list) {
        throw new IllegalStateException("Missing implementation to handle COMMAND_CHANGE_MEDIA_ITEMS");
    }

    /* access modifiers changed from: protected */
    public ListenableFuture<?> handleMoveMediaItems(int i, int i2, int i3) {
        throw new IllegalStateException("Missing implementation to handle COMMAND_CHANGE_MEDIA_ITEMS");
    }

    /* access modifiers changed from: protected */
    public ListenableFuture<?> handleReplaceMediaItems(int i, int i2, List<MediaItem> list) {
        return Util.transformFutureAsync(handleAddMediaItems(i2, list), new SimpleBasePlayer$$ExternalSyntheticLambda43(handleRemoveMediaItems(i, i2)));
    }

    /* access modifiers changed from: protected */
    public ListenableFuture<?> handleRemoveMediaItems(int i, int i2) {
        throw new IllegalStateException("Missing implementation to handle COMMAND_CHANGE_MEDIA_ITEMS");
    }

    /* access modifiers changed from: protected */
    public ListenableFuture<?> handleSeek(int i, long j, int i2) {
        throw new IllegalStateException("Missing implementation to handle one of the COMMAND_SEEK_*");
    }

    /* access modifiers changed from: protected */
    public final void verifyApplicationThread() {
        if (Thread.currentThread() != this.applicationLooper.getThread()) {
            throw new IllegalStateException(Util.formatInvariant("Player is accessed on the wrong thread.\nCurrent thread: '%s'\nExpected thread: '%s'\n", Thread.currentThread().getName(), this.applicationLooper.getThread().getName()));
        }
    }

    @RequiresNonNull({"state"})
    private boolean shouldHandleCommand(int i) {
        return !this.released && this.state.availableCommands.contains(i);
    }

    @RequiresNonNull({"state"})
    private void updateStateAndInformListeners(State state2, boolean z, boolean z2) {
        MediaItem mediaItem;
        State state3 = this.state;
        this.state = state2;
        if (state2.hasPositionDiscontinuity || state2.newlyRenderedFirstFrame) {
            this.state = this.state.buildUpon().clearPositionDiscontinuity().setNewlyRenderedFirstFrame(false).build();
        }
        boolean z3 = state3.playWhenReady != state2.playWhenReady;
        boolean z4 = state3.playbackState != state2.playbackState;
        int positionDiscontinuityReason = getPositionDiscontinuityReason(state3, state2, z, this.window, this.period);
        boolean z5 = !state3.timeline.equals(state2.timeline);
        int mediaItemTransitionReason = getMediaItemTransitionReason(state3, state2, positionDiscontinuityReason, z2, this.window);
        if (z5) {
            this.listeners.queueEvent(0, new SimpleBasePlayer$$ExternalSyntheticLambda5(state2, getTimelineChangeReason(state3.timeline, state2.timeline, this.window)));
        }
        if (positionDiscontinuityReason != -1) {
            this.listeners.queueEvent(11, new SimpleBasePlayer$$ExternalSyntheticLambda17(positionDiscontinuityReason, getPositionInfo(state3, false, this.window, this.period), getPositionInfo(state2, state2.hasPositionDiscontinuity, this.window, this.period)));
        }
        if (mediaItemTransitionReason != -1) {
            if (state2.timeline.isEmpty()) {
                mediaItem = null;
            } else {
                mediaItem = state2.timeline.getWindow(getCurrentMediaItemIndexInternal(state2), this.window).mediaItem;
            }
            this.listeners.queueEvent(1, new SimpleBasePlayer$$ExternalSyntheticLambda29(mediaItem, mediaItemTransitionReason));
        }
        if (!Util.areEqual(state3.playerError, state2.playerError)) {
            this.listeners.queueEvent(10, new SimpleBasePlayer$$ExternalSyntheticLambda31(state2));
            if (state2.playerError != null) {
                this.listeners.queueEvent(10, new SimpleBasePlayer$$ExternalSyntheticLambda32(state2));
            }
        }
        if (!state3.trackSelectionParameters.equals(state2.trackSelectionParameters)) {
            this.listeners.queueEvent(19, new SimpleBasePlayer$$ExternalSyntheticLambda34(state2));
        }
        if (!state3.currentTracks.equals(state2.currentTracks)) {
            this.listeners.queueEvent(2, new SimpleBasePlayer$$ExternalSyntheticLambda35(state2));
        }
        if (!state3.currentMetadata.equals(state2.currentMetadata)) {
            this.listeners.queueEvent(14, new SimpleBasePlayer$$ExternalSyntheticLambda36(state2));
        }
        if (state3.isLoading != state2.isLoading) {
            this.listeners.queueEvent(3, new SimpleBasePlayer$$ExternalSyntheticLambda37(state2));
        }
        if (z3 || z4) {
            this.listeners.queueEvent(-1, new SimpleBasePlayer$$ExternalSyntheticLambda38(state2));
        }
        if (z4) {
            this.listeners.queueEvent(4, new SimpleBasePlayer$$ExternalSyntheticLambda6(state2));
        }
        if (z3 || state3.playWhenReadyChangeReason != state2.playWhenReadyChangeReason) {
            this.listeners.queueEvent(5, new SimpleBasePlayer$$ExternalSyntheticLambda7(state2));
        }
        if (state3.playbackSuppressionReason != state2.playbackSuppressionReason) {
            this.listeners.queueEvent(6, new SimpleBasePlayer$$ExternalSyntheticLambda8(state2));
        }
        if (isPlaying(state3) != isPlaying(state2)) {
            this.listeners.queueEvent(7, new SimpleBasePlayer$$ExternalSyntheticLambda9(state2));
        }
        if (!state3.playbackParameters.equals(state2.playbackParameters)) {
            this.listeners.queueEvent(12, new SimpleBasePlayer$$ExternalSyntheticLambda10(state2));
        }
        if (state3.repeatMode != state2.repeatMode) {
            this.listeners.queueEvent(8, new SimpleBasePlayer$$ExternalSyntheticLambda12(state2));
        }
        if (state3.shuffleModeEnabled != state2.shuffleModeEnabled) {
            this.listeners.queueEvent(9, new SimpleBasePlayer$$ExternalSyntheticLambda13(state2));
        }
        if (state3.seekBackIncrementMs != state2.seekBackIncrementMs) {
            this.listeners.queueEvent(16, new SimpleBasePlayer$$ExternalSyntheticLambda14(state2));
        }
        if (state3.seekForwardIncrementMs != state2.seekForwardIncrementMs) {
            this.listeners.queueEvent(17, new SimpleBasePlayer$$ExternalSyntheticLambda15(state2));
        }
        if (state3.maxSeekToPreviousPositionMs != state2.maxSeekToPreviousPositionMs) {
            this.listeners.queueEvent(18, new SimpleBasePlayer$$ExternalSyntheticLambda16(state2));
        }
        if (!state3.audioAttributes.equals(state2.audioAttributes)) {
            this.listeners.queueEvent(20, new SimpleBasePlayer$$ExternalSyntheticLambda18(state2));
        }
        if (!state3.videoSize.equals(state2.videoSize)) {
            this.listeners.queueEvent(25, new SimpleBasePlayer$$ExternalSyntheticLambda19(state2));
        }
        if (!state3.deviceInfo.equals(state2.deviceInfo)) {
            this.listeners.queueEvent(29, new SimpleBasePlayer$$ExternalSyntheticLambda20(state2));
        }
        if (!state3.playlistMetadata.equals(state2.playlistMetadata)) {
            this.listeners.queueEvent(15, new SimpleBasePlayer$$ExternalSyntheticLambda21(state2));
        }
        if (state2.newlyRenderedFirstFrame) {
            this.listeners.queueEvent(26, new SimpleBasePlayer$$ExternalSyntheticLambda23());
        }
        if (!state3.surfaceSize.equals(state2.surfaceSize)) {
            this.listeners.queueEvent(24, new SimpleBasePlayer$$ExternalSyntheticLambda24(state2));
        }
        if (state3.volume != state2.volume) {
            this.listeners.queueEvent(22, new SimpleBasePlayer$$ExternalSyntheticLambda25(state2));
        }
        if (!(state3.deviceVolume == state2.deviceVolume && state3.isDeviceMuted == state2.isDeviceMuted)) {
            this.listeners.queueEvent(30, new SimpleBasePlayer$$ExternalSyntheticLambda26(state2));
        }
        if (!state3.currentCues.equals(state2.currentCues)) {
            this.listeners.queueEvent(27, new SimpleBasePlayer$$ExternalSyntheticLambda27(state2));
        }
        if (!state3.timedMetadata.equals(state2.timedMetadata) && state2.timedMetadata.presentationTimeUs != C.TIME_UNSET) {
            this.listeners.queueEvent(28, new SimpleBasePlayer$$ExternalSyntheticLambda28(state2));
        }
        if (!state3.availableCommands.equals(state2.availableCommands)) {
            this.listeners.queueEvent(13, new SimpleBasePlayer$$ExternalSyntheticLambda30(state2));
        }
        this.listeners.flushEvents();
    }

    static /* synthetic */ void lambda$updateStateAndInformListeners$33(int i, Player.PositionInfo positionInfo, Player.PositionInfo positionInfo2, Player.Listener listener) {
        listener.onPositionDiscontinuity(i);
        listener.onPositionDiscontinuity(positionInfo, positionInfo2, i);
    }

    static /* synthetic */ void lambda$updateStateAndInformListeners$40(State state2, Player.Listener listener) {
        listener.onLoadingChanged(state2.isLoading);
        listener.onIsLoadingChanged(state2.isLoading);
    }

    static /* synthetic */ void lambda$updateStateAndInformListeners$59(State state2, Player.Listener listener) {
        listener.onCues((List<Cue>) state2.currentCues.cues);
        listener.onCues(state2.currentCues);
    }

    @EnsuresNonNull({"state"})
    private void verifyApplicationThreadAndInitState() {
        verifyApplicationThread();
        if (this.state == null) {
            this.state = getState();
        }
    }

    @RequiresNonNull({"state"})
    private void updateStateForPendingOperation(ListenableFuture<?> listenableFuture, Supplier<State> supplier) {
        updateStateForPendingOperation(listenableFuture, supplier, false, false);
    }

    @RequiresNonNull({"state"})
    private void updateStateForPendingOperation(ListenableFuture<?> listenableFuture, Supplier<State> supplier, boolean z, boolean z2) {
        if (!listenableFuture.isDone() || !this.pendingOperations.isEmpty()) {
            this.pendingOperations.add(listenableFuture);
            updateStateAndInformListeners(getPlaceholderState(supplier.get()), z, z2);
            listenableFuture.addListener(new SimpleBasePlayer$$ExternalSyntheticLambda45(this, listenableFuture), new SimpleBasePlayer$$ExternalSyntheticLambda46(this));
            return;
        }
        updateStateAndInformListeners(getState(), z, z2);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$updateStateForPendingOperation$62$androidx-media3-common-SimpleBasePlayer  reason: not valid java name */
    public /* synthetic */ void m402lambda$updateStateForPendingOperation$62$androidxmedia3commonSimpleBasePlayer(ListenableFuture listenableFuture) {
        Util.castNonNull(this.state);
        this.pendingOperations.remove(listenableFuture);
        if (this.pendingOperations.isEmpty() && !this.released) {
            updateStateAndInformListeners(getState(), false, false);
        }
    }

    /* access modifiers changed from: private */
    public void postOrRunOnApplicationHandler(Runnable runnable) {
        if (this.applicationHandler.getLooper() == Looper.myLooper()) {
            runnable.run();
        } else {
            this.applicationHandler.post(runnable);
        }
    }

    private static boolean isPlaying(State state2) {
        return state2.playWhenReady && state2.playbackState == 3 && state2.playbackSuppressionReason == 0;
    }

    /* access modifiers changed from: private */
    public static int getCurrentMediaItemIndexInternal(State state2) {
        if (state2.currentMediaItemIndex != -1) {
            return state2.currentMediaItemIndex;
        }
        return 0;
    }

    private static long getContentPositionMsInternal(State state2, Timeline.Window window) {
        return getPositionOrDefaultInMediaItem(state2.contentPositionMsSupplier.get(), state2, window);
    }

    private static long getContentBufferedPositionMsInternal(State state2, Timeline.Window window) {
        return getPositionOrDefaultInMediaItem(state2.contentBufferedPositionMsSupplier.get(), state2, window);
    }

    private static long getPositionOrDefaultInMediaItem(long j, State state2, Timeline.Window window) {
        if (j != C.TIME_UNSET) {
            return j;
        }
        if (state2.timeline.isEmpty()) {
            return 0;
        }
        return state2.timeline.getWindow(getCurrentMediaItemIndexInternal(state2), window).getDefaultPositionMs();
    }

    private static int getCurrentPeriodIndexInternal(State state2, Timeline.Window window, Timeline.Period period2) {
        int currentMediaItemIndexInternal = getCurrentMediaItemIndexInternal(state2);
        if (state2.timeline.isEmpty()) {
            return currentMediaItemIndexInternal;
        }
        return getPeriodIndexFromWindowPosition(state2.timeline, currentMediaItemIndexInternal, getContentPositionMsInternal(state2, window), window, period2);
    }

    /* access modifiers changed from: private */
    public static int getPeriodIndexFromWindowPosition(Timeline timeline, int i, long j, Timeline.Window window, Timeline.Period period2) {
        return timeline.getIndexOfPeriod(timeline.getPeriodPositionUs(window, period2, i, Util.msToUs(j)).first);
    }

    private static int getTimelineChangeReason(Timeline timeline, Timeline timeline2, Timeline.Window window) {
        if (timeline.getWindowCount() != timeline2.getWindowCount()) {
            return 0;
        }
        int i = 0;
        while (true) {
            boolean z = true;
            if (i >= timeline.getWindowCount()) {
                return 1;
            }
            Object obj = timeline.getWindow(i, window).uid;
            Object obj2 = timeline2.getWindow(i, window).uid;
            if (!(obj instanceof PlaceholderUid) || (obj2 instanceof PlaceholderUid)) {
                z = false;
            }
            if (!obj.equals(obj2) && !z) {
                return 0;
            }
            i++;
        }
    }

    private static int getPositionDiscontinuityReason(State state2, State state3, boolean z, Timeline.Window window, Timeline.Period period2) {
        if (state3.hasPositionDiscontinuity) {
            return state3.positionDiscontinuityReason;
        }
        if (z) {
            return 1;
        }
        if (state2.timeline.isEmpty()) {
            return -1;
        }
        if (state3.timeline.isEmpty()) {
            return 4;
        }
        Object uidOfPeriod = state2.timeline.getUidOfPeriod(getCurrentPeriodIndexInternal(state2, window, period2));
        Object uidOfPeriod2 = state3.timeline.getUidOfPeriod(getCurrentPeriodIndexInternal(state3, window, period2));
        if ((uidOfPeriod instanceof PlaceholderUid) && !(uidOfPeriod2 instanceof PlaceholderUid)) {
            return -1;
        }
        if (uidOfPeriod2.equals(uidOfPeriod) && state2.currentAdGroupIndex == state3.currentAdGroupIndex && state2.currentAdIndexInAdGroup == state3.currentAdIndexInAdGroup) {
            long currentPeriodOrAdPositionMs = getCurrentPeriodOrAdPositionMs(state2, uidOfPeriod, period2, window);
            if (Math.abs(currentPeriodOrAdPositionMs - getCurrentPeriodOrAdPositionMs(state3, uidOfPeriod2, period2, window)) < 1000) {
                return -1;
            }
            long periodOrAdDurationMs = getPeriodOrAdDurationMs(state2, uidOfPeriod, period2);
            if (periodOrAdDurationMs == C.TIME_UNSET || currentPeriodOrAdPositionMs < periodOrAdDurationMs) {
                return 5;
            }
            return 0;
        } else if (state3.timeline.getIndexOfPeriod(uidOfPeriod) == -1) {
            return 4;
        } else {
            long currentPeriodOrAdPositionMs2 = getCurrentPeriodOrAdPositionMs(state2, uidOfPeriod, period2, window);
            long periodOrAdDurationMs2 = getPeriodOrAdDurationMs(state2, uidOfPeriod, period2);
            if (periodOrAdDurationMs2 == C.TIME_UNSET || currentPeriodOrAdPositionMs2 < periodOrAdDurationMs2) {
                return 3;
            }
            return 0;
        }
    }

    private static long getCurrentPeriodOrAdPositionMs(State state2, Object obj, Timeline.Period period2, Timeline.Window window) {
        if (state2.currentAdGroupIndex != -1) {
            return state2.adPositionMsSupplier.get();
        }
        return getContentPositionMsInternal(state2, window) - state2.timeline.getPeriodByUid(obj, period2).getPositionInWindowMs();
    }

    private static long getPeriodOrAdDurationMs(State state2, Object obj, Timeline.Period period2) {
        long j;
        state2.timeline.getPeriodByUid(obj, period2);
        if (state2.currentAdGroupIndex == -1) {
            j = period2.durationUs;
        } else {
            j = period2.getAdDurationUs(state2.currentAdGroupIndex, state2.currentAdIndexInAdGroup);
        }
        return Util.usToMs(j);
    }

    private static Player.PositionInfo getPositionInfo(State state2, boolean z, Timeline.Window window, Timeline.Period period2) {
        MediaItem mediaItem;
        int i;
        Object obj;
        Object obj2;
        long j;
        long j2;
        State state3 = state2;
        Timeline.Window window2 = window;
        Timeline.Period period3 = period2;
        int currentMediaItemIndexInternal = getCurrentMediaItemIndexInternal(state2);
        if (!state3.timeline.isEmpty()) {
            int currentPeriodIndexInternal = getCurrentPeriodIndexInternal(state3, window2, period3);
            Object obj3 = state3.timeline.getPeriod(currentPeriodIndexInternal, period3, true).uid;
            Object obj4 = state3.timeline.getWindow(currentMediaItemIndexInternal, window2).uid;
            mediaItem = window2.mediaItem;
            int i2 = currentPeriodIndexInternal;
            obj = obj3;
            obj2 = obj4;
            i = i2;
        } else {
            obj2 = null;
            obj = null;
            mediaItem = null;
            i = -1;
        }
        if (z) {
            j2 = state3.discontinuityPositionMs;
            if (state3.currentAdGroupIndex == -1) {
                j = j2;
            } else {
                j = getContentPositionMsInternal(state3, window2);
            }
        } else {
            long contentPositionMsInternal = getContentPositionMsInternal(state3, window2);
            long j3 = contentPositionMsInternal;
            j2 = state3.currentAdGroupIndex != -1 ? state3.adPositionMsSupplier.get() : contentPositionMsInternal;
            j = j3;
        }
        return new Player.PositionInfo(obj2, currentMediaItemIndexInternal, mediaItem, obj, i, j2, j, state3.currentAdGroupIndex, state3.currentAdIndexInAdGroup);
    }

    private static int getMediaItemTransitionReason(State state2, State state3, int i, boolean z, Timeline.Window window) {
        Timeline timeline = state2.timeline;
        Timeline timeline2 = state3.timeline;
        if (timeline2.isEmpty() && timeline.isEmpty()) {
            return -1;
        }
        if (timeline2.isEmpty() != timeline.isEmpty()) {
            return 3;
        }
        Object obj = state2.timeline.getWindow(getCurrentMediaItemIndexInternal(state2), window).uid;
        Object obj2 = state3.timeline.getWindow(getCurrentMediaItemIndexInternal(state3), window).uid;
        if ((obj instanceof PlaceholderUid) && !(obj2 instanceof PlaceholderUid)) {
            return -1;
        }
        if (obj.equals(obj2)) {
            if (i == 0) {
                if (getContentPositionMsInternal(state2, window) > getContentPositionMsInternal(state3, window)) {
                    return 0;
                }
                if (state3.hasPositionDiscontinuity && state3.discontinuityPositionMs == C.TIME_UNSET && z) {
                    return 0;
                }
            }
            if (i != 1 || !z) {
                return -1;
            }
            return 2;
        } else if (i == 0) {
            return 1;
        } else {
            if (i == 1) {
                return 2;
            }
            return 3;
        }
    }

    private static Size getSurfaceHolderSize(SurfaceHolder surfaceHolder) {
        if (!surfaceHolder.getSurface().isValid()) {
            return Size.ZERO;
        }
        Rect surfaceFrame = surfaceHolder.getSurfaceFrame();
        return new Size(surfaceFrame.width(), surfaceFrame.height());
    }

    private static int getMediaItemIndexInNewPlaylist(Timeline timeline, Timeline timeline2, int i, Timeline.Period period2, Timeline.Window window) {
        if (!timeline.isEmpty()) {
            Object checkNotNull = Assertions.checkNotNull(timeline.getPeriod(timeline.getWindow(i, window).firstPeriodIndex, period2, true).uid);
            if (timeline2.getIndexOfPeriod(checkNotNull) == -1) {
                return -1;
            }
            return timeline2.getPeriodByUid(checkNotNull, period2).windowIndex;
        } else if (i < timeline2.getWindowCount()) {
            return i;
        } else {
            return -1;
        }
    }

    private static State getStateWithNewPlaylist(State state2, List<MediaItemData> list, Timeline.Period period2, Timeline.Window window) {
        State.Builder buildUpon = state2.buildUpon();
        PlaylistTimeline playlistTimeline = new PlaylistTimeline(list);
        Timeline timeline = state2.timeline;
        long j = state2.contentPositionMsSupplier.get();
        int currentMediaItemIndexInternal = getCurrentMediaItemIndexInternal(state2);
        int mediaItemIndexInNewPlaylist = getMediaItemIndexInNewPlaylist(timeline, playlistTimeline, currentMediaItemIndexInternal, period2, window);
        long j2 = mediaItemIndexInNewPlaylist == -1 ? C.TIME_UNSET : j;
        int i = currentMediaItemIndexInternal + 1;
        while (mediaItemIndexInNewPlaylist == -1 && i < timeline.getWindowCount()) {
            mediaItemIndexInNewPlaylist = getMediaItemIndexInNewPlaylist(timeline, playlistTimeline, i, period2, window);
            i++;
        }
        if (state2.playbackState != 1 && mediaItemIndexInNewPlaylist == -1) {
            buildUpon.setPlaybackState(4).setIsLoading(false);
        }
        return buildStateForNewPosition(buildUpon, state2, j, playlistTimeline, mediaItemIndexInNewPlaylist, j2, true, window);
    }

    private static State getStateWithNewPlaylistAndPosition(State state2, List<MediaItemData> list, int i, long j, Timeline.Window window) {
        State.Builder buildUpon = state2.buildUpon();
        PlaylistTimeline playlistTimeline = list == null ? state2.timeline : new PlaylistTimeline(list);
        if (state2.playbackState != 1) {
            if (playlistTimeline.isEmpty() || (i != -1 && i >= playlistTimeline.getWindowCount())) {
                buildUpon.setPlaybackState(4).setIsLoading(false);
            } else {
                buildUpon.setPlaybackState(2);
            }
        }
        return buildStateForNewPosition(buildUpon, state2, state2.contentPositionMsSupplier.get(), playlistTimeline, i, j, false, window);
    }

    private static State buildStateForNewPosition(State.Builder builder, State state2, long j, Timeline timeline, int i, long j2, boolean z, Timeline.Window window) {
        long j3;
        int i2;
        State.Builder builder2 = builder;
        State state3 = state2;
        Timeline timeline2 = timeline;
        int i3 = i;
        Timeline.Window window2 = window;
        long positionOrDefaultInMediaItem = getPositionOrDefaultInMediaItem(j, state3, window2);
        boolean z2 = false;
        if (timeline.isEmpty() || (i3 != -1 && i3 < timeline.getWindowCount())) {
            j3 = j2;
        } else {
            j3 = -9223372036854775807L;
            i3 = 0;
        }
        if (!timeline.isEmpty() && j3 == C.TIME_UNSET) {
            j3 = timeline2.getWindow(i3, window2).getDefaultPositionMs();
        }
        boolean z3 = state3.timeline.isEmpty() || timeline.isEmpty();
        boolean z4 = !z3 && !state3.timeline.getWindow(getCurrentMediaItemIndexInternal(state2), window2).uid.equals(timeline2.getWindow(i3, window2).uid);
        MediaMetadata mediaMetadata = null;
        if (timeline.isEmpty()) {
            builder2.setPlaylist(timeline2, Tracks.EMPTY, (MediaMetadata) null);
        } else if (timeline2 instanceof PlaylistTimeline) {
            MediaItemData mediaItemData = (MediaItemData) ((PlaylistTimeline) timeline2).playlist.get(i3);
            builder2.setPlaylist(timeline2, mediaItemData.tracks, mediaItemData.mediaMetadata);
        } else {
            if (!z3 && !z4) {
                z2 = true;
            }
            Tracks tracks = z2 ? state3.currentTracks : Tracks.EMPTY;
            if (z2) {
                mediaMetadata = state3.currentMetadata;
            }
            builder2.setPlaylist(timeline2, tracks, mediaMetadata);
        }
        if (z3 || z4 || j3 < positionOrDefaultInMediaItem) {
            builder2.setCurrentMediaItemIndex(i3).setCurrentAd(-1, -1).setContentPositionMs(j3).setContentBufferedPositionMs(PositionSupplier.getConstant(j3)).setTotalBufferedDurationMs(PositionSupplier.ZERO);
        } else if (i2 == 0) {
            builder2.setCurrentMediaItemIndex(i3);
            if (state3.currentAdGroupIndex == -1 || !z) {
                builder2.setCurrentAd(-1, -1).setTotalBufferedDurationMs(PositionSupplier.getConstant(getContentBufferedPositionMsInternal(state3, window2) - positionOrDefaultInMediaItem));
            } else {
                builder2.setTotalBufferedDurationMs(PositionSupplier.getConstant(state3.adBufferedPositionMsSupplier.get() - state3.adPositionMsSupplier.get()));
            }
        } else {
            builder2.setCurrentMediaItemIndex(i3).setCurrentAd(-1, -1).setContentPositionMs(j3).setContentBufferedPositionMs(PositionSupplier.getConstant(Math.max(getContentBufferedPositionMsInternal(state3, window2), j3))).setTotalBufferedDurationMs(PositionSupplier.getConstant(Math.max(0, state3.totalBufferedDurationMsSupplier.get() - (j3 - positionOrDefaultInMediaItem))));
        }
        return builder.build();
    }

    /* access modifiers changed from: private */
    public static MediaMetadata getCombinedMediaMetadata(MediaItem mediaItem, Tracks tracks) {
        MediaMetadata.Builder builder = new MediaMetadata.Builder();
        int size = tracks.getGroups().size();
        for (int i = 0; i < size; i++) {
            Tracks.Group group = (Tracks.Group) tracks.getGroups().get(i);
            for (int i2 = 0; i2 < group.length; i2++) {
                if (group.isTrackSelected(i2)) {
                    Format trackFormat = group.getTrackFormat(i2);
                    if (trackFormat.metadata != null) {
                        for (int i3 = 0; i3 < trackFormat.metadata.length(); i3++) {
                            trackFormat.metadata.get(i3).populateMediaMetadata(builder);
                        }
                    }
                }
            }
        }
        return builder.populate(mediaItem.mediaMetadata).build();
    }

    private static List<MediaItemData> buildMutablePlaylistFromState(State state2, Timeline.Period period2, Timeline.Window window) {
        if (state2.timeline instanceof PlaylistTimeline) {
            return new ArrayList(((PlaylistTimeline) state2.timeline).playlist);
        }
        ArrayList arrayList = new ArrayList(state2.timeline.getWindowCount());
        for (int i = 0; i < state2.timeline.getWindowCount(); i++) {
            arrayList.add(MediaItemData.buildFromState(state2, i, period2, window));
        }
        return arrayList;
    }

    private static final class PlaceholderUid {
        private PlaceholderUid() {
        }
    }
}
