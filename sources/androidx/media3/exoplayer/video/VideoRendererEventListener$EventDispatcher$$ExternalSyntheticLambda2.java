package androidx.media3.exoplayer.video;

import androidx.media3.exoplayer.video.VideoRendererEventListener;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class VideoRendererEventListener$EventDispatcher$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ VideoRendererEventListener.EventDispatcher f$0;
    public final /* synthetic */ Exception f$1;

    public /* synthetic */ VideoRendererEventListener$EventDispatcher$$ExternalSyntheticLambda2(VideoRendererEventListener.EventDispatcher eventDispatcher, Exception exc) {
        this.f$0 = eventDispatcher;
        this.f$1 = exc;
    }

    public final void run() {
        this.f$0.m900lambda$videoCodecError$9$androidxmedia3exoplayervideoVideoRendererEventListener$EventDispatcher(this.f$1);
    }
}
