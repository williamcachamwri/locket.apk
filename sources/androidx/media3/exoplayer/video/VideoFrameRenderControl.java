package androidx.media3.exoplayer.video;

import androidx.media3.common.C;
import androidx.media3.common.VideoSize;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.LongArrayQueue;
import androidx.media3.common.util.TimedValueQueue;
import androidx.media3.exoplayer.ExoPlaybackException;
import androidx.media3.exoplayer.video.VideoFrameReleaseControl;

final class VideoFrameRenderControl {
    private final FrameRenderer frameRenderer;
    private long lastPresentationTimeUs = C.TIME_UNSET;
    private long outputStreamOffsetUs;
    private VideoSize pendingOutputVideoSize;
    private final LongArrayQueue presentationTimestampsUs = new LongArrayQueue();
    private VideoSize reportedVideoSize = VideoSize.UNKNOWN;
    private final TimedValueQueue<Long> streamOffsets = new TimedValueQueue<>();
    private final VideoFrameReleaseControl videoFrameReleaseControl;
    private final VideoFrameReleaseControl.FrameReleaseInfo videoFrameReleaseInfo = new VideoFrameReleaseControl.FrameReleaseInfo();
    private final TimedValueQueue<VideoSize> videoSizeChanges = new TimedValueQueue<>();

    interface FrameRenderer {
        void dropFrame();

        void onVideoSizeChanged(VideoSize videoSize);

        void renderFrame(long j, long j2, long j3, boolean z);
    }

    public VideoFrameRenderControl(FrameRenderer frameRenderer2, VideoFrameReleaseControl videoFrameReleaseControl2) {
        this.frameRenderer = frameRenderer2;
        this.videoFrameReleaseControl = videoFrameReleaseControl2;
    }

    public void flush() {
        this.presentationTimestampsUs.clear();
        this.lastPresentationTimeUs = C.TIME_UNSET;
        if (this.streamOffsets.size() > 0) {
            this.streamOffsets.add(0, Long.valueOf(((Long) getLastAndClear(this.streamOffsets)).longValue()));
        }
        if (this.pendingOutputVideoSize != null) {
            this.videoSizeChanges.clear();
        } else if (this.videoSizeChanges.size() > 0) {
            this.pendingOutputVideoSize = (VideoSize) getLastAndClear(this.videoSizeChanges);
        }
    }

    public boolean hasReleasedFrame(long j) {
        long j2 = this.lastPresentationTimeUs;
        return j2 != C.TIME_UNSET && j2 >= j;
    }

    public void render(long j, long j2) throws ExoPlaybackException {
        while (!this.presentationTimestampsUs.isEmpty()) {
            long element = this.presentationTimestampsUs.element();
            if (maybeUpdateOutputStreamOffset(element)) {
                this.videoFrameReleaseControl.onProcessedStreamChange();
            }
            int frameReleaseAction = this.videoFrameReleaseControl.getFrameReleaseAction(element, j, j2, this.outputStreamOffsetUs, false, this.videoFrameReleaseInfo);
            boolean z = true;
            if (frameReleaseAction == 0 || frameReleaseAction == 1) {
                this.lastPresentationTimeUs = element;
                if (frameReleaseAction != 0) {
                    z = false;
                }
                renderFrame(z);
            } else if (frameReleaseAction == 2 || frameReleaseAction == 3 || frameReleaseAction == 4) {
                this.lastPresentationTimeUs = element;
                dropFrame();
            } else if (frameReleaseAction != 5) {
                throw new IllegalStateException(String.valueOf(frameReleaseAction));
            } else {
                return;
            }
        }
    }

    public void onOutputSizeChanged(int i, int i2) {
        this.pendingOutputVideoSize = new VideoSize(i, i2);
    }

    public void onOutputFrameAvailableForRendering(long j) {
        VideoSize videoSize = this.pendingOutputVideoSize;
        if (videoSize != null) {
            this.videoSizeChanges.add(j, videoSize);
            this.pendingOutputVideoSize = null;
        }
        this.presentationTimestampsUs.add(j);
    }

    public void onStreamOffsetChange(long j, long j2) {
        this.streamOffsets.add(j, Long.valueOf(j2));
    }

    private void dropFrame() {
        Assertions.checkStateNotNull(Long.valueOf(this.presentationTimestampsUs.remove()));
        this.frameRenderer.dropFrame();
    }

    private void renderFrame(boolean z) {
        long j;
        long longValue = ((Long) Assertions.checkStateNotNull(Long.valueOf(this.presentationTimestampsUs.remove()))).longValue();
        if (maybeUpdateVideoSize(longValue)) {
            this.frameRenderer.onVideoSizeChanged(this.reportedVideoSize);
        }
        if (z) {
            j = -1;
        } else {
            j = this.videoFrameReleaseInfo.getReleaseTimeNs();
        }
        this.frameRenderer.renderFrame(j, longValue, this.outputStreamOffsetUs, this.videoFrameReleaseControl.onFrameReleasedIsFirstFrame());
    }

    private boolean maybeUpdateOutputStreamOffset(long j) {
        Long pollFloor = this.streamOffsets.pollFloor(j);
        if (pollFloor == null || pollFloor.longValue() == this.outputStreamOffsetUs) {
            return false;
        }
        this.outputStreamOffsetUs = pollFloor.longValue();
        return true;
    }

    private boolean maybeUpdateVideoSize(long j) {
        VideoSize pollFloor = this.videoSizeChanges.pollFloor(j);
        if (pollFloor == null || pollFloor.equals(VideoSize.UNKNOWN) || pollFloor.equals(this.reportedVideoSize)) {
            return false;
        }
        this.reportedVideoSize = pollFloor;
        return true;
    }

    private static <T> T getLastAndClear(TimedValueQueue<T> timedValueQueue) {
        Assertions.checkArgument(timedValueQueue.size() > 0);
        while (timedValueQueue.size() > 1) {
            timedValueQueue.pollFirst();
        }
        return Assertions.checkNotNull(timedValueQueue.pollFirst());
    }
}
