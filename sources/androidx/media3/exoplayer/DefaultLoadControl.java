package androidx.media3.exoplayer;

import androidx.media3.common.C;
import androidx.media3.common.Timeline;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.LoadControl;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.TrackGroupArray;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import androidx.media3.exoplayer.upstream.Allocator;
import androidx.media3.exoplayer.upstream.DefaultAllocator;
import java.util.HashMap;

public class DefaultLoadControl implements LoadControl {
    public static final int DEFAULT_AUDIO_BUFFER_SIZE = 13107200;
    public static final int DEFAULT_BACK_BUFFER_DURATION_MS = 0;
    public static final int DEFAULT_BUFFER_FOR_PLAYBACK_AFTER_REBUFFER_MS = 5000;
    public static final int DEFAULT_BUFFER_FOR_PLAYBACK_MS = 2500;
    public static final int DEFAULT_CAMERA_MOTION_BUFFER_SIZE = 131072;
    public static final int DEFAULT_IMAGE_BUFFER_SIZE = 131072;
    public static final int DEFAULT_MAX_BUFFER_MS = 50000;
    public static final int DEFAULT_METADATA_BUFFER_SIZE = 131072;
    public static final int DEFAULT_MIN_BUFFER_MS = 50000;
    public static final int DEFAULT_MIN_BUFFER_SIZE = 13107200;
    public static final int DEFAULT_MUXED_BUFFER_SIZE = 144310272;
    public static final boolean DEFAULT_PRIORITIZE_TIME_OVER_SIZE_THRESHOLDS = false;
    public static final boolean DEFAULT_RETAIN_BACK_BUFFER_FROM_KEYFRAME = false;
    public static final int DEFAULT_TARGET_BUFFER_BYTES = -1;
    public static final int DEFAULT_TEXT_BUFFER_SIZE = 131072;
    public static final int DEFAULT_VIDEO_BUFFER_SIZE = 131072000;
    private final DefaultAllocator allocator;
    private final long backBufferDurationUs;
    private final long bufferForPlaybackAfterRebufferUs;
    private final long bufferForPlaybackUs;
    private final HashMap<PlayerId, PlayerLoadingState> loadingStates;
    private final long maxBufferUs;
    private final long minBufferUs;
    private final boolean prioritizeTimeOverSizeThresholds;
    private final boolean retainBackBufferFromKeyframe;
    private final int targetBufferBytesOverwrite;
    private long threadId;

    public static final class Builder {
        private DefaultAllocator allocator;
        private int backBufferDurationMs = 0;
        private int bufferForPlaybackAfterRebufferMs = 5000;
        private int bufferForPlaybackMs = DefaultLoadControl.DEFAULT_BUFFER_FOR_PLAYBACK_MS;
        private boolean buildCalled;
        private int maxBufferMs = 50000;
        private int minBufferMs = 50000;
        private boolean prioritizeTimeOverSizeThresholds = false;
        private boolean retainBackBufferFromKeyframe = false;
        private int targetBufferBytes = -1;

        public Builder setAllocator(DefaultAllocator defaultAllocator) {
            Assertions.checkState(!this.buildCalled);
            this.allocator = defaultAllocator;
            return this;
        }

        public Builder setBufferDurationsMs(int i, int i2, int i3, int i4) {
            Assertions.checkState(!this.buildCalled);
            DefaultLoadControl.assertGreaterOrEqual(i3, 0, "bufferForPlaybackMs", "0");
            DefaultLoadControl.assertGreaterOrEqual(i4, 0, "bufferForPlaybackAfterRebufferMs", "0");
            DefaultLoadControl.assertGreaterOrEqual(i, i3, "minBufferMs", "bufferForPlaybackMs");
            DefaultLoadControl.assertGreaterOrEqual(i, i4, "minBufferMs", "bufferForPlaybackAfterRebufferMs");
            DefaultLoadControl.assertGreaterOrEqual(i2, i, "maxBufferMs", "minBufferMs");
            this.minBufferMs = i;
            this.maxBufferMs = i2;
            this.bufferForPlaybackMs = i3;
            this.bufferForPlaybackAfterRebufferMs = i4;
            return this;
        }

        public Builder setTargetBufferBytes(int i) {
            Assertions.checkState(!this.buildCalled);
            this.targetBufferBytes = i;
            return this;
        }

        public Builder setPrioritizeTimeOverSizeThresholds(boolean z) {
            Assertions.checkState(!this.buildCalled);
            this.prioritizeTimeOverSizeThresholds = z;
            return this;
        }

        public Builder setBackBuffer(int i, boolean z) {
            Assertions.checkState(!this.buildCalled);
            DefaultLoadControl.assertGreaterOrEqual(i, 0, "backBufferDurationMs", "0");
            this.backBufferDurationMs = i;
            this.retainBackBufferFromKeyframe = z;
            return this;
        }

        public DefaultLoadControl build() {
            Assertions.checkState(!this.buildCalled);
            this.buildCalled = true;
            if (this.allocator == null) {
                this.allocator = new DefaultAllocator(true, 65536);
            }
            return new DefaultLoadControl(this.allocator, this.minBufferMs, this.maxBufferMs, this.bufferForPlaybackMs, this.bufferForPlaybackAfterRebufferMs, this.targetBufferBytes, this.prioritizeTimeOverSizeThresholds, this.backBufferDurationMs, this.retainBackBufferFromKeyframe);
        }
    }

    public DefaultLoadControl() {
        this(new DefaultAllocator(true, 65536), 50000, 50000, DEFAULT_BUFFER_FOR_PLAYBACK_MS, 5000, -1, false, 0, false);
    }

    protected DefaultLoadControl(DefaultAllocator defaultAllocator, int i, int i2, int i3, int i4, int i5, boolean z, int i6, boolean z2) {
        assertGreaterOrEqual(i3, 0, "bufferForPlaybackMs", "0");
        assertGreaterOrEqual(i4, 0, "bufferForPlaybackAfterRebufferMs", "0");
        assertGreaterOrEqual(i, i3, "minBufferMs", "bufferForPlaybackMs");
        assertGreaterOrEqual(i, i4, "minBufferMs", "bufferForPlaybackAfterRebufferMs");
        assertGreaterOrEqual(i2, i, "maxBufferMs", "minBufferMs");
        assertGreaterOrEqual(i6, 0, "backBufferDurationMs", "0");
        this.allocator = defaultAllocator;
        this.minBufferUs = Util.msToUs((long) i);
        this.maxBufferUs = Util.msToUs((long) i2);
        this.bufferForPlaybackUs = Util.msToUs((long) i3);
        this.bufferForPlaybackAfterRebufferUs = Util.msToUs((long) i4);
        this.targetBufferBytesOverwrite = i5;
        this.prioritizeTimeOverSizeThresholds = z;
        this.backBufferDurationUs = Util.msToUs((long) i6);
        this.retainBackBufferFromKeyframe = z2;
        this.loadingStates = new HashMap<>();
        this.threadId = -1;
    }

    public void onPrepared(PlayerId playerId) {
        long id = Thread.currentThread().getId();
        long j = this.threadId;
        Assertions.checkState(j == -1 || j == id, "Players that share the same LoadControl must share the same playback thread. See ExoPlayer.Builder.setPlaybackLooper(Looper).");
        this.threadId = id;
        if (!this.loadingStates.containsKey(playerId)) {
            this.loadingStates.put(playerId, new PlayerLoadingState());
        }
        resetPlayerLoadingState(playerId);
    }

    public void onTracksSelected(LoadControl.Parameters parameters, TrackGroupArray trackGroupArray, ExoTrackSelection[] exoTrackSelectionArr) {
        PlayerLoadingState playerLoadingState = (PlayerLoadingState) Assertions.checkNotNull(this.loadingStates.get(parameters.playerId));
        int i = this.targetBufferBytesOverwrite;
        if (i == -1) {
            i = calculateTargetBufferBytes(exoTrackSelectionArr);
        }
        playerLoadingState.targetBufferBytes = i;
        updateAllocator();
    }

    public void onStopped(PlayerId playerId) {
        removePlayer(playerId);
    }

    public void onReleased(PlayerId playerId) {
        removePlayer(playerId);
        if (this.loadingStates.isEmpty()) {
            this.threadId = -1;
        }
    }

    public Allocator getAllocator() {
        return this.allocator;
    }

    public long getBackBufferDurationUs(PlayerId playerId) {
        return this.backBufferDurationUs;
    }

    public boolean retainBackBufferFromKeyframe(PlayerId playerId) {
        return this.retainBackBufferFromKeyframe;
    }

    public boolean shouldContinueLoading(LoadControl.Parameters parameters) {
        PlayerLoadingState playerLoadingState = (PlayerLoadingState) Assertions.checkNotNull(this.loadingStates.get(parameters.playerId));
        boolean z = true;
        boolean z2 = this.allocator.getTotalBytesAllocated() >= calculateTotalTargetBufferBytes();
        long j = this.minBufferUs;
        if (parameters.playbackSpeed > 1.0f) {
            j = Math.min(Util.getMediaDurationForPlayoutDuration(j, parameters.playbackSpeed), this.maxBufferUs);
        }
        if (parameters.bufferedDurationUs < Math.max(j, 500000)) {
            if (!this.prioritizeTimeOverSizeThresholds && z2) {
                z = false;
            }
            playerLoadingState.isLoading = z;
            if (!playerLoadingState.isLoading && parameters.bufferedDurationUs < 500000) {
                Log.w("DefaultLoadControl", "Target buffer size reached with less than 500ms of buffered media data.");
            }
        } else if (parameters.bufferedDurationUs >= this.maxBufferUs || z2) {
            playerLoadingState.isLoading = false;
        }
        return playerLoadingState.isLoading;
    }

    public boolean shouldStartPlayback(LoadControl.Parameters parameters) {
        long playoutDurationForMediaDuration = Util.getPlayoutDurationForMediaDuration(parameters.bufferedDurationUs, parameters.playbackSpeed);
        long j = parameters.rebuffering ? this.bufferForPlaybackAfterRebufferUs : this.bufferForPlaybackUs;
        if (parameters.targetLiveOffsetUs != C.TIME_UNSET) {
            j = Math.min(parameters.targetLiveOffsetUs / 2, j);
        }
        return j <= 0 || playoutDurationForMediaDuration >= j || (!this.prioritizeTimeOverSizeThresholds && this.allocator.getTotalBytesAllocated() >= calculateTotalTargetBufferBytes());
    }

    public boolean shouldContinuePreloading(Timeline timeline, MediaSource.MediaPeriodId mediaPeriodId, long j) {
        for (PlayerLoadingState playerLoadingState : this.loadingStates.values()) {
            if (playerLoadingState.isLoading) {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public int calculateTargetBufferBytes(ExoTrackSelection[] exoTrackSelectionArr) {
        int i = 0;
        for (ExoTrackSelection exoTrackSelection : exoTrackSelectionArr) {
            if (exoTrackSelection != null) {
                i += getDefaultBufferSize(exoTrackSelection.getTrackGroup().type);
            }
        }
        return Math.max(13107200, i);
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public final int calculateTargetBufferBytes(Renderer[] rendererArr, ExoTrackSelection[] exoTrackSelectionArr) {
        return calculateTargetBufferBytes(exoTrackSelectionArr);
    }

    /* access modifiers changed from: package-private */
    public int calculateTotalTargetBufferBytes() {
        int i = 0;
        for (PlayerLoadingState playerLoadingState : this.loadingStates.values()) {
            i += playerLoadingState.targetBufferBytes;
        }
        return i;
    }

    private void resetPlayerLoadingState(PlayerId playerId) {
        PlayerLoadingState playerLoadingState = (PlayerLoadingState) Assertions.checkNotNull(this.loadingStates.get(playerId));
        int i = this.targetBufferBytesOverwrite;
        if (i == -1) {
            i = 13107200;
        }
        playerLoadingState.targetBufferBytes = i;
        playerLoadingState.isLoading = false;
    }

    private void removePlayer(PlayerId playerId) {
        if (this.loadingStates.remove(playerId) != null) {
            updateAllocator();
        }
    }

    private void updateAllocator() {
        if (this.loadingStates.isEmpty()) {
            this.allocator.reset();
        } else {
            this.allocator.setTargetBufferSize(calculateTotalTargetBufferBytes());
        }
    }

    private static int getDefaultBufferSize(int i) {
        switch (i) {
            case -2:
                return 0;
            case -1:
                return 13107200;
            case 0:
                return DEFAULT_MUXED_BUFFER_SIZE;
            case 1:
                return 13107200;
            case 2:
                return DEFAULT_VIDEO_BUFFER_SIZE;
            case 3:
            case 4:
            case 5:
            case 6:
                return 131072;
            default:
                throw new IllegalArgumentException();
        }
    }

    /* access modifiers changed from: private */
    public static void assertGreaterOrEqual(int i, int i2, String str, String str2) {
        Assertions.checkArgument(i >= i2, str + " cannot be less than " + str2);
    }

    private static class PlayerLoadingState {
        public boolean isLoading;
        public int targetBufferBytes;

        private PlayerLoadingState() {
        }
    }
}
