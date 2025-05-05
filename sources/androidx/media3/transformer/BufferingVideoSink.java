package androidx.media3.transformer;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.Surface;
import androidx.media3.common.Effect;
import androidx.media3.common.Format;
import androidx.media3.common.util.Size;
import androidx.media3.common.util.TimestampIterator;
import androidx.media3.exoplayer.video.PlaceholderSurface;
import androidx.media3.exoplayer.video.VideoFrameMetadataListener;
import androidx.media3.exoplayer.video.VideoSink;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

final class BufferingVideoSink implements VideoSink {
    private final Context context;
    private boolean isInitialized;
    private final List<ThrowingVideoSinkOperation> pendingOperations = new ArrayList();
    private PlaceholderSurface placeholderSurface;
    private VideoSink videoSink;

    private interface ThrowingVideoSinkOperation {
        void execute(VideoSink videoSink) throws VideoSink.VideoSinkException;
    }

    private interface VideoSinkOperation extends ThrowingVideoSinkOperation {
        void execute(VideoSink videoSink);
    }

    public BufferingVideoSink(Context context2) {
        this.context = context2;
    }

    public void setVideoSink(VideoSink videoSink2) throws VideoSink.VideoSinkException {
        this.videoSink = videoSink2;
        for (int i = 0; i < this.pendingOperations.size(); i++) {
            this.pendingOperations.get(i).execute(videoSink2);
        }
        this.pendingOperations.clear();
    }

    public void removeVideoSink() {
        this.videoSink = null;
    }

    public VideoSink getVideoSink() {
        return this.videoSink;
    }

    public void clearPendingOperations() {
        this.pendingOperations.clear();
    }

    public void onRendererEnabled(boolean z) {
        executeOrDelay(new BufferingVideoSink$$ExternalSyntheticLambda0(z));
    }

    public void onRendererDisabled() {
        executeOrDelay(new BufferingVideoSink$$ExternalSyntheticLambda1());
    }

    public void onRendererStarted() {
        executeOrDelay(new BufferingVideoSink$$ExternalSyntheticLambda7());
    }

    public void onRendererStopped() {
        executeOrDelay(new BufferingVideoSink$$ExternalSyntheticLambda15());
    }

    public void setListener(VideoSink.Listener listener, Executor executor) {
        executeOrDelay(new BufferingVideoSink$$ExternalSyntheticLambda3(listener, executor));
    }

    public void initialize(Format format) throws VideoSink.VideoSinkException {
        executeOrDelayThrowing(new BufferingVideoSink$$ExternalSyntheticLambda10(format));
        this.isInitialized = true;
    }

    static /* synthetic */ void lambda$initialize$2(Format format, VideoSink videoSink2) throws VideoSink.VideoSinkException {
        if (!videoSink2.isInitialized()) {
            videoSink2.initialize(format);
        }
    }

    public boolean isInitialized() {
        return this.isInitialized;
    }

    public void flush(boolean z) {
        executeOrDelay(new BufferingVideoSink$$ExternalSyntheticLambda17(z));
    }

    public boolean isReady(boolean z) {
        VideoSink videoSink2 = this.videoSink;
        return videoSink2 == null || videoSink2.isReady(z);
    }

    public boolean isEnded() {
        VideoSink videoSink2 = this.videoSink;
        return videoSink2 != null && videoSink2.isEnded();
    }

    public Surface getInputSurface() {
        VideoSink videoSink2 = this.videoSink;
        return videoSink2 == null ? getPlaceholderSurface() : videoSink2.getInputSurface();
    }

    public void setVideoFrameMetadataListener(VideoFrameMetadataListener videoFrameMetadataListener) {
        executeOrDelay(new BufferingVideoSink$$ExternalSyntheticLambda16(videoFrameMetadataListener));
    }

    public void setPlaybackSpeed(float f) {
        executeOrDelay(new BufferingVideoSink$$ExternalSyntheticLambda14(f));
    }

    public void setVideoEffects(List<Effect> list) {
        executeOrDelay(new BufferingVideoSink$$ExternalSyntheticLambda12(list));
    }

    public void setPendingVideoEffects(List<Effect> list) {
        executeOrDelay(new BufferingVideoSink$$ExternalSyntheticLambda8(list));
    }

    public void setStreamTimestampInfo(long j, long j2, long j3, long j4) {
        executeOrDelay(new BufferingVideoSink$$ExternalSyntheticLambda13(j, j2, j3, j4));
    }

    public void setOutputSurfaceInfo(Surface surface, Size size) {
        executeOrDelay(new BufferingVideoSink$$ExternalSyntheticLambda4(surface, size));
    }

    public void clearOutputSurfaceInfo() {
        executeOrDelay(new BufferingVideoSink$$ExternalSyntheticLambda18());
    }

    public void setChangeFrameRateStrategy(int i) {
        executeOrDelay(new BufferingVideoSink$$ExternalSyntheticLambda5(i));
    }

    public void enableMayRenderStartOfStream() {
        executeOrDelay(new BufferingVideoSink$$ExternalSyntheticLambda11());
    }

    public void onInputStreamChanged(int i, Format format) {
        executeOrDelay(new BufferingVideoSink$$ExternalSyntheticLambda2(i, format));
    }

    public boolean handleInputFrame(long j, boolean z, long j2, long j3, VideoSink.VideoFrameHandler videoFrameHandler) throws VideoSink.VideoSinkException {
        VideoSink videoSink2 = this.videoSink;
        return videoSink2 != null && videoSink2.handleInputFrame(j, z, j2, j3, videoFrameHandler);
    }

    public boolean handleInputBitmap(Bitmap bitmap, TimestampIterator timestampIterator) {
        VideoSink videoSink2 = this.videoSink;
        return videoSink2 != null && videoSink2.handleInputBitmap(bitmap, timestampIterator);
    }

    public void render(long j, long j2) throws VideoSink.VideoSinkException {
        VideoSink videoSink2 = this.videoSink;
        if (videoSink2 != null) {
            videoSink2.render(j, j2);
        }
    }

    public void join(boolean z) {
        executeOrDelay(new BufferingVideoSink$$ExternalSyntheticLambda6(z));
    }

    public void release() {
        executeOrDelay(new BufferingVideoSink$$ExternalSyntheticLambda9());
        PlaceholderSurface placeholderSurface2 = this.placeholderSurface;
        if (placeholderSurface2 != null) {
            placeholderSurface2.release();
        }
    }

    private void executeOrDelay(VideoSinkOperation videoSinkOperation) {
        VideoSink videoSink2 = this.videoSink;
        if (videoSink2 != null) {
            videoSinkOperation.execute(videoSink2);
        } else {
            this.pendingOperations.add(videoSinkOperation);
        }
    }

    private void executeOrDelayThrowing(ThrowingVideoSinkOperation throwingVideoSinkOperation) throws VideoSink.VideoSinkException {
        VideoSink videoSink2 = this.videoSink;
        if (videoSink2 != null) {
            throwingVideoSinkOperation.execute(videoSink2);
        } else {
            this.pendingOperations.add(throwingVideoSinkOperation);
        }
    }

    private PlaceholderSurface getPlaceholderSurface() {
        if (this.placeholderSurface == null) {
            this.placeholderSurface = PlaceholderSurface.newInstance(this.context, false);
        }
        return this.placeholderSurface;
    }
}
