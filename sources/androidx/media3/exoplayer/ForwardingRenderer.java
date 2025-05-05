package androidx.media3.exoplayer;

import androidx.media3.common.Format;
import androidx.media3.common.Timeline;
import androidx.media3.common.util.Clock;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.SampleStream;
import java.io.IOException;

public class ForwardingRenderer implements Renderer {
    private final Renderer renderer;

    public ForwardingRenderer(Renderer renderer2) {
        this.renderer = renderer2;
    }

    public void handleMessage(int i, Object obj) throws ExoPlaybackException {
        this.renderer.handleMessage(i, obj);
    }

    public String getName() {
        return this.renderer.getName();
    }

    public int getTrackType() {
        return this.renderer.getTrackType();
    }

    public RendererCapabilities getCapabilities() {
        return this.renderer.getCapabilities();
    }

    public void init(int i, PlayerId playerId, Clock clock) {
        this.renderer.init(i, playerId, clock);
    }

    public MediaClock getMediaClock() {
        return this.renderer.getMediaClock();
    }

    public int getState() {
        return this.renderer.getState();
    }

    public void enable(RendererConfiguration rendererConfiguration, Format[] formatArr, SampleStream sampleStream, long j, boolean z, boolean z2, long j2, long j3, MediaSource.MediaPeriodId mediaPeriodId) throws ExoPlaybackException {
        this.renderer.enable(rendererConfiguration, formatArr, sampleStream, j, z, z2, j2, j3, mediaPeriodId);
    }

    public void start() throws ExoPlaybackException {
        this.renderer.start();
    }

    public void replaceStream(Format[] formatArr, SampleStream sampleStream, long j, long j2, MediaSource.MediaPeriodId mediaPeriodId) throws ExoPlaybackException {
        this.renderer.replaceStream(formatArr, sampleStream, j, j2, mediaPeriodId);
    }

    public SampleStream getStream() {
        return this.renderer.getStream();
    }

    public boolean hasReadStreamToEnd() {
        return this.renderer.hasReadStreamToEnd();
    }

    public long getReadingPositionUs() {
        return this.renderer.getReadingPositionUs();
    }

    public long getDurationToProgressUs(long j, long j2) {
        return this.renderer.getDurationToProgressUs(j, j2);
    }

    public void setCurrentStreamFinal() {
        this.renderer.setCurrentStreamFinal();
    }

    public boolean isCurrentStreamFinal() {
        return this.renderer.isCurrentStreamFinal();
    }

    public void maybeThrowStreamError() throws IOException {
        this.renderer.maybeThrowStreamError();
    }

    public void resetPosition(long j) throws ExoPlaybackException {
        this.renderer.resetPosition(j);
    }

    public void setPlaybackSpeed(float f, float f2) throws ExoPlaybackException {
        this.renderer.setPlaybackSpeed(f, f2);
    }

    public void enableMayRenderStartOfStream() {
        this.renderer.enableMayRenderStartOfStream();
    }

    public void setTimeline(Timeline timeline) {
        this.renderer.setTimeline(timeline);
    }

    public void render(long j, long j2) throws ExoPlaybackException {
        this.renderer.render(j, j2);
    }

    public boolean isReady() {
        return this.renderer.isReady();
    }

    public boolean isEnded() {
        return this.renderer.isEnded();
    }

    public void stop() {
        this.renderer.stop();
    }

    public void disable() {
        this.renderer.disable();
    }

    public void reset() {
        this.renderer.reset();
    }

    public void release() {
        this.renderer.release();
    }
}
