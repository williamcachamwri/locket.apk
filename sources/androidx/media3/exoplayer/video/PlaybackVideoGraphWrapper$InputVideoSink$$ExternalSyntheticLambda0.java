package androidx.media3.exoplayer.video;

import androidx.media3.common.VideoSize;
import androidx.media3.exoplayer.video.PlaybackVideoGraphWrapper;
import androidx.media3.exoplayer.video.VideoSink;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class PlaybackVideoGraphWrapper$InputVideoSink$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ PlaybackVideoGraphWrapper.InputVideoSink f$0;
    public final /* synthetic */ VideoSink.Listener f$1;
    public final /* synthetic */ VideoSize f$2;

    public /* synthetic */ PlaybackVideoGraphWrapper$InputVideoSink$$ExternalSyntheticLambda0(PlaybackVideoGraphWrapper.InputVideoSink inputVideoSink, VideoSink.Listener listener, VideoSize videoSize) {
        this.f$0 = inputVideoSink;
        this.f$1 = listener;
        this.f$2 = videoSize;
    }

    public final void run() {
        this.f$0.m891lambda$onVideoSizeChanged$2$androidxmedia3exoplayervideoPlaybackVideoGraphWrapper$InputVideoSink(this.f$1, this.f$2);
    }
}
