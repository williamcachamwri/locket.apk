package androidx.media3.exoplayer.video;

import androidx.media3.exoplayer.video.PlaybackVideoGraphWrapper;
import androidx.media3.exoplayer.video.VideoSink;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class PlaybackVideoGraphWrapper$InputVideoSink$$ExternalSyntheticLambda3 implements Runnable {
    public final /* synthetic */ PlaybackVideoGraphWrapper.InputVideoSink f$0;
    public final /* synthetic */ VideoSink.Listener f$1;

    public /* synthetic */ PlaybackVideoGraphWrapper$InputVideoSink$$ExternalSyntheticLambda3(PlaybackVideoGraphWrapper.InputVideoSink inputVideoSink, VideoSink.Listener listener) {
        this.f$0 = inputVideoSink;
        this.f$1 = listener;
    }

    public final void run() {
        this.f$0.m889lambda$onFirstFrameRendered$0$androidxmedia3exoplayervideoPlaybackVideoGraphWrapper$InputVideoSink(this.f$1);
    }
}
