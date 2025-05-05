package androidx.media3.exoplayer.video;

import androidx.media3.common.VideoSize;
import androidx.media3.exoplayer.video.VideoRendererEventListener;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class VideoRendererEventListener$EventDispatcher$$ExternalSyntheticLambda8 implements Runnable {
    public final /* synthetic */ VideoRendererEventListener.EventDispatcher f$0;
    public final /* synthetic */ VideoSize f$1;

    public /* synthetic */ VideoRendererEventListener$EventDispatcher$$ExternalSyntheticLambda8(VideoRendererEventListener.EventDispatcher eventDispatcher, VideoSize videoSize) {
        this.f$0 = eventDispatcher;
        this.f$1 = videoSize;
    }

    public final void run() {
        this.f$0.m901lambda$videoSizeChanged$5$androidxmedia3exoplayervideoVideoRendererEventListener$EventDispatcher(this.f$1);
    }
}
