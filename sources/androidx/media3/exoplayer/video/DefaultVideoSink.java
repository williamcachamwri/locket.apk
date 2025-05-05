package androidx.media3.exoplayer.video;

import android.graphics.Bitmap;
import android.view.Surface;
import androidx.media3.common.Effect;
import androidx.media3.common.Format;
import androidx.media3.common.util.Size;
import androidx.media3.common.util.TimestampIterator;
import androidx.media3.exoplayer.video.VideoSink;
import java.util.List;
import java.util.concurrent.Executor;

final class DefaultVideoSink implements VideoSink {
    private Format inputFormat = new Format.Builder().build();
    private final VideoFrameReleaseControl videoFrameReleaseControl;
    private final VideoFrameRenderControl videoFrameRenderControl;

    public void initialize(Format format) {
    }

    public boolean isInitialized() {
        return true;
    }

    public void release() {
    }

    public DefaultVideoSink(VideoFrameReleaseControl videoFrameReleaseControl2, VideoFrameRenderControl videoFrameRenderControl2) {
        this.videoFrameReleaseControl = videoFrameReleaseControl2;
        this.videoFrameRenderControl = videoFrameRenderControl2;
    }

    public void onRendererEnabled(boolean z) {
        this.videoFrameReleaseControl.onEnabled(z);
    }

    public void onRendererDisabled() {
        this.videoFrameReleaseControl.onDisabled();
    }

    public void onRendererStarted() {
        this.videoFrameReleaseControl.onStarted();
    }

    public void onRendererStopped() {
        this.videoFrameReleaseControl.onStopped();
    }

    public void setListener(VideoSink.Listener listener, Executor executor) {
        throw new UnsupportedOperationException();
    }

    public void flush(boolean z) {
        if (z) {
            this.videoFrameReleaseControl.reset();
        }
        this.videoFrameRenderControl.flush();
    }

    public boolean isReady(boolean z) {
        return this.videoFrameReleaseControl.isReady(z);
    }

    public boolean isEnded() {
        throw new UnsupportedOperationException();
    }

    public Surface getInputSurface() {
        throw new UnsupportedOperationException();
    }

    public void setVideoFrameMetadataListener(VideoFrameMetadataListener videoFrameMetadataListener) {
        throw new UnsupportedOperationException();
    }

    public void setPlaybackSpeed(float f) {
        this.videoFrameReleaseControl.setPlaybackSpeed(f);
    }

    public void setVideoEffects(List<Effect> list) {
        throw new UnsupportedOperationException();
    }

    public void setPendingVideoEffects(List<Effect> list) {
        throw new UnsupportedOperationException();
    }

    public void setStreamTimestampInfo(long j, long j2, long j3, long j4) {
        throw new UnsupportedOperationException();
    }

    public void setOutputSurfaceInfo(Surface surface, Size size) {
        this.videoFrameReleaseControl.setOutputSurface(surface);
    }

    public void clearOutputSurfaceInfo() {
        this.videoFrameReleaseControl.setOutputSurface((Surface) null);
    }

    public void setChangeFrameRateStrategy(int i) {
        this.videoFrameReleaseControl.setChangeFrameRateStrategy(i);
    }

    public void enableMayRenderStartOfStream() {
        this.videoFrameReleaseControl.allowReleaseFirstFrameBeforeStarted();
    }

    public void onInputStreamChanged(int i, Format format) {
        if (!(format.width == this.inputFormat.width && format.height == this.inputFormat.height)) {
            this.videoFrameRenderControl.onOutputSizeChanged(format.width, format.height);
        }
        this.inputFormat = format;
    }

    public boolean handleInputFrame(long j, boolean z, long j2, long j3, VideoSink.VideoFrameHandler videoFrameHandler) {
        throw new UnsupportedOperationException();
    }

    public boolean handleInputBitmap(Bitmap bitmap, TimestampIterator timestampIterator) {
        throw new UnsupportedOperationException();
    }

    public void render(long j, long j2) {
        throw new UnsupportedOperationException();
    }

    public void join(boolean z) {
        this.videoFrameReleaseControl.join(z);
    }
}
