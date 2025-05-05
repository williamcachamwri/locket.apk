package androidx.media3.exoplayer.video;

import android.content.Context;
import android.view.Surface;
import androidx.media3.common.C;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.ExoPlaybackException;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public final class VideoFrameReleaseControl {
    public static final int FRAME_RELEASE_DROP = 2;
    public static final int FRAME_RELEASE_IGNORE = 4;
    public static final int FRAME_RELEASE_IMMEDIATELY = 0;
    public static final int FRAME_RELEASE_SCHEDULED = 1;
    public static final int FRAME_RELEASE_SKIP = 3;
    public static final int FRAME_RELEASE_TRY_AGAIN_LATER = 5;
    private static final long MAX_EARLY_US_THRESHOLD = 50000;
    private final long allowedJoiningTimeMs;
    private Clock clock = Clock.DEFAULT;
    private int firstFrameState = 0;
    private final VideoFrameReleaseHelper frameReleaseHelper;
    private final FrameTimingEvaluator frameTimingEvaluator;
    private long initialPositionUs = C.TIME_UNSET;
    private long joiningDeadlineMs = C.TIME_UNSET;
    private boolean joiningRenderNextFrameImmediately;
    private long lastPresentationTimeUs = C.TIME_UNSET;
    private long lastReleaseRealtimeUs;
    private float playbackSpeed = 1.0f;
    private boolean started;

    @Documented
    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface FrameReleaseAction {
    }

    public interface FrameTimingEvaluator {
        boolean shouldDropFrame(long j, long j2, boolean z);

        boolean shouldForceReleaseFrame(long j, long j2);

        boolean shouldIgnoreFrame(long j, long j2, long j3, boolean z, boolean z2) throws ExoPlaybackException;
    }

    public static class FrameReleaseInfo {
        /* access modifiers changed from: private */
        public long earlyUs = C.TIME_UNSET;
        /* access modifiers changed from: private */
        public long releaseTimeNs = C.TIME_UNSET;

        public long getEarlyUs() {
            return this.earlyUs;
        }

        public long getReleaseTimeNs() {
            return this.releaseTimeNs;
        }

        /* access modifiers changed from: private */
        public void reset() {
            this.earlyUs = C.TIME_UNSET;
            this.releaseTimeNs = C.TIME_UNSET;
        }
    }

    public VideoFrameReleaseControl(Context context, FrameTimingEvaluator frameTimingEvaluator2, long j) {
        this.frameTimingEvaluator = frameTimingEvaluator2;
        this.allowedJoiningTimeMs = j;
        this.frameReleaseHelper = new VideoFrameReleaseHelper(context);
    }

    public void onEnabled(boolean z) {
        this.firstFrameState = z ? 1 : 0;
    }

    public void onDisabled() {
        lowerFirstFrameState(0);
    }

    public void onStarted() {
        this.started = true;
        this.lastReleaseRealtimeUs = Util.msToUs(this.clock.elapsedRealtime());
        this.frameReleaseHelper.onStarted();
    }

    public void onStopped() {
        this.started = false;
        this.joiningDeadlineMs = C.TIME_UNSET;
        this.frameReleaseHelper.onStopped();
    }

    public void onProcessedStreamChange() {
        lowerFirstFrameState(2);
    }

    public void setOutputSurface(Surface surface) {
        this.frameReleaseHelper.onSurfaceChanged(surface);
        lowerFirstFrameState(1);
    }

    public void setFrameRate(float f) {
        this.frameReleaseHelper.onFormatChanged(f);
    }

    public boolean onFrameReleasedIsFirstFrame() {
        boolean z = this.firstFrameState != 3;
        this.firstFrameState = 3;
        this.lastReleaseRealtimeUs = Util.msToUs(this.clock.elapsedRealtime());
        return z;
    }

    public void setClock(Clock clock2) {
        this.clock = clock2;
    }

    public void allowReleaseFirstFrameBeforeStarted() {
        if (this.firstFrameState == 0) {
            this.firstFrameState = 1;
        }
    }

    public boolean isReady(boolean z) {
        if (z && this.firstFrameState == 3) {
            this.joiningDeadlineMs = C.TIME_UNSET;
            return true;
        } else if (this.joiningDeadlineMs == C.TIME_UNSET) {
            return false;
        } else {
            if (this.clock.elapsedRealtime() < this.joiningDeadlineMs) {
                return true;
            }
            this.joiningDeadlineMs = C.TIME_UNSET;
            return false;
        }
    }

    public void join(boolean z) {
        this.joiningRenderNextFrameImmediately = z;
        this.joiningDeadlineMs = this.allowedJoiningTimeMs > 0 ? this.clock.elapsedRealtime() + this.allowedJoiningTimeMs : C.TIME_UNSET;
    }

    public int getFrameReleaseAction(long j, long j2, long j3, long j4, boolean z, FrameReleaseInfo frameReleaseInfo) throws ExoPlaybackException {
        long j5 = j;
        long j6 = j2;
        FrameReleaseInfo frameReleaseInfo2 = frameReleaseInfo;
        frameReleaseInfo.reset();
        if (this.initialPositionUs == C.TIME_UNSET) {
            this.initialPositionUs = j6;
        }
        if (this.lastPresentationTimeUs != j5) {
            this.frameReleaseHelper.onNextFrame(j5);
            this.lastPresentationTimeUs = j5;
        }
        long unused = frameReleaseInfo2.earlyUs = calculateEarlyTimeUs(j2, j3, j);
        boolean z2 = false;
        if (shouldForceRelease(j2, frameReleaseInfo.earlyUs, j4)) {
            return 0;
        }
        if (!this.started || j6 == this.initialPositionUs) {
            return 5;
        }
        long nanoTime = this.clock.nanoTime();
        long unused2 = frameReleaseInfo2.releaseTimeNs = this.frameReleaseHelper.adjustReleaseTime((frameReleaseInfo.earlyUs * 1000) + nanoTime);
        long unused3 = frameReleaseInfo2.earlyUs = (frameReleaseInfo.releaseTimeNs - nanoTime) / 1000;
        if (this.joiningDeadlineMs != C.TIME_UNSET && !this.joiningRenderNextFrameImmediately) {
            z2 = true;
        }
        if (this.frameTimingEvaluator.shouldIgnoreFrame(frameReleaseInfo.earlyUs, j2, j3, z, z2)) {
            return 4;
        }
        if (this.frameTimingEvaluator.shouldDropFrame(frameReleaseInfo.earlyUs, j3, z)) {
            return z2 ? 3 : 2;
        }
        if (frameReleaseInfo.earlyUs > MAX_EARLY_US_THRESHOLD) {
            return 5;
        }
        return 1;
    }

    public void reset() {
        this.frameReleaseHelper.onPositionReset();
        this.lastPresentationTimeUs = C.TIME_UNSET;
        this.initialPositionUs = C.TIME_UNSET;
        lowerFirstFrameState(1);
        this.joiningDeadlineMs = C.TIME_UNSET;
    }

    public void setChangeFrameRateStrategy(int i) {
        this.frameReleaseHelper.setChangeFrameRateStrategy(i);
    }

    public void setPlaybackSpeed(float f) {
        Assertions.checkArgument(f > 0.0f);
        if (f != this.playbackSpeed) {
            this.playbackSpeed = f;
            this.frameReleaseHelper.onPlaybackSpeed(f);
        }
    }

    private void lowerFirstFrameState(int i) {
        this.firstFrameState = Math.min(this.firstFrameState, i);
    }

    private long calculateEarlyTimeUs(long j, long j2, long j3) {
        long j4 = (long) (((double) (j3 - j)) / ((double) this.playbackSpeed));
        return this.started ? j4 - (Util.msToUs(this.clock.elapsedRealtime()) - j2) : j4;
    }

    private boolean shouldForceRelease(long j, long j2, long j3) {
        if (this.joiningDeadlineMs != C.TIME_UNSET && !this.joiningRenderNextFrameImmediately) {
            return false;
        }
        int i = this.firstFrameState;
        if (i == 0) {
            return this.started;
        }
        if (i == 1) {
            return true;
        }
        if (i != 2) {
            if (i == 3) {
                long msToUs = Util.msToUs(this.clock.elapsedRealtime()) - this.lastReleaseRealtimeUs;
                if (!this.started || !this.frameTimingEvaluator.shouldForceReleaseFrame(j2, msToUs)) {
                    return false;
                }
                return true;
            }
            throw new IllegalStateException();
        } else if (j >= j3) {
            return true;
        } else {
            return false;
        }
    }
}
