package androidx.media3.exoplayer;

import androidx.media3.common.Timeline;
import androidx.media3.common.util.Log;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.TrackGroupArray;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import androidx.media3.exoplayer.upstream.Allocator;

public interface LoadControl {
    @Deprecated
    public static final MediaSource.MediaPeriodId EMPTY_MEDIA_PERIOD_ID = new MediaSource.MediaPeriodId(new Object());

    Allocator getAllocator();

    public static final class Parameters {
        public final long bufferedDurationUs;
        public final MediaSource.MediaPeriodId mediaPeriodId;
        public final boolean playWhenReady;
        public final long playbackPositionUs;
        public final float playbackSpeed;
        public final PlayerId playerId;
        public final boolean rebuffering;
        public final long targetLiveOffsetUs;
        public final Timeline timeline;

        public Parameters(PlayerId playerId2, Timeline timeline2, MediaSource.MediaPeriodId mediaPeriodId2, long j, long j2, float f, boolean z, boolean z2, long j3) {
            this.playerId = playerId2;
            this.timeline = timeline2;
            this.mediaPeriodId = mediaPeriodId2;
            this.playbackPositionUs = j;
            this.bufferedDurationUs = j2;
            this.playbackSpeed = f;
            this.playWhenReady = z;
            this.rebuffering = z2;
            this.targetLiveOffsetUs = j3;
        }
    }

    void onPrepared(PlayerId playerId) {
        onPrepared();
    }

    @Deprecated
    void onPrepared() {
        throw new IllegalStateException("onPrepared not implemented");
    }

    void onTracksSelected(Parameters parameters, TrackGroupArray trackGroupArray, ExoTrackSelection[] exoTrackSelectionArr) {
        throw new IllegalStateException("onTracksSelected not implemented");
    }

    @Deprecated
    void onTracksSelected(PlayerId playerId, Timeline timeline, MediaSource.MediaPeriodId mediaPeriodId, Renderer[] rendererArr, TrackGroupArray trackGroupArray, ExoTrackSelection[] exoTrackSelectionArr) {
        onTracksSelected(timeline, mediaPeriodId, rendererArr, trackGroupArray, exoTrackSelectionArr);
    }

    @Deprecated
    void onTracksSelected(Timeline timeline, MediaSource.MediaPeriodId mediaPeriodId, Renderer[] rendererArr, TrackGroupArray trackGroupArray, ExoTrackSelection[] exoTrackSelectionArr) {
        onTracksSelected(rendererArr, trackGroupArray, exoTrackSelectionArr);
    }

    @Deprecated
    void onTracksSelected(Renderer[] rendererArr, TrackGroupArray trackGroupArray, ExoTrackSelection[] exoTrackSelectionArr) {
        throw new IllegalStateException("onTracksSelected not implemented");
    }

    void onStopped(PlayerId playerId) {
        onStopped();
    }

    @Deprecated
    void onStopped() {
        throw new IllegalStateException("onStopped not implemented");
    }

    void onReleased(PlayerId playerId) {
        onReleased();
    }

    @Deprecated
    void onReleased() {
        throw new IllegalStateException("onReleased not implemented");
    }

    long getBackBufferDurationUs(PlayerId playerId) {
        return getBackBufferDurationUs();
    }

    @Deprecated
    long getBackBufferDurationUs() {
        throw new IllegalStateException("getBackBufferDurationUs not implemented");
    }

    boolean retainBackBufferFromKeyframe(PlayerId playerId) {
        return retainBackBufferFromKeyframe();
    }

    @Deprecated
    boolean retainBackBufferFromKeyframe() {
        throw new IllegalStateException("retainBackBufferFromKeyframe not implemented");
    }

    boolean shouldContinueLoading(Parameters parameters) {
        return shouldContinueLoading(parameters.playbackPositionUs, parameters.bufferedDurationUs, parameters.playbackSpeed);
    }

    @Deprecated
    boolean shouldContinueLoading(long j, long j2, float f) {
        throw new IllegalStateException("shouldContinueLoading not implemented");
    }

    boolean shouldContinuePreloading(Timeline timeline, MediaSource.MediaPeriodId mediaPeriodId, long j) {
        Log.w("LoadControl", "shouldContinuePreloading needs to be implemented when playlist preloading is enabled");
        return false;
    }

    boolean shouldStartPlayback(Parameters parameters) {
        return shouldStartPlayback(parameters.timeline, parameters.mediaPeriodId, parameters.bufferedDurationUs, parameters.playbackSpeed, parameters.rebuffering, parameters.targetLiveOffsetUs);
    }

    @Deprecated
    boolean shouldStartPlayback(Timeline timeline, MediaSource.MediaPeriodId mediaPeriodId, long j, float f, boolean z, long j2) {
        return shouldStartPlayback(j, f, z, j2);
    }

    @Deprecated
    boolean shouldStartPlayback(long j, float f, boolean z, long j2) {
        throw new IllegalStateException("shouldStartPlayback not implemented");
    }
}
