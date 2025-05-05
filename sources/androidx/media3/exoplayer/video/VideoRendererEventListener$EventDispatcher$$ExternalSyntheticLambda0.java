package androidx.media3.exoplayer.video;

import androidx.media3.exoplayer.video.VideoRendererEventListener;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class VideoRendererEventListener$EventDispatcher$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ VideoRendererEventListener.EventDispatcher f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ long f$2;

    public /* synthetic */ VideoRendererEventListener$EventDispatcher$$ExternalSyntheticLambda0(VideoRendererEventListener.EventDispatcher eventDispatcher, int i, long j) {
        this.f$0 = eventDispatcher;
        this.f$1 = i;
        this.f$2 = j;
    }

    public final void run() {
        this.f$0.m895lambda$droppedFrames$3$androidxmedia3exoplayervideoVideoRendererEventListener$EventDispatcher(this.f$1, this.f$2);
    }
}
