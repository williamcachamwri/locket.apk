package androidx.media3.exoplayer.video;

import androidx.media3.exoplayer.video.VideoRendererEventListener;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class VideoRendererEventListener$EventDispatcher$$ExternalSyntheticLambda6 implements Runnable {
    public final /* synthetic */ VideoRendererEventListener.EventDispatcher f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ long f$2;
    public final /* synthetic */ long f$3;

    public /* synthetic */ VideoRendererEventListener$EventDispatcher$$ExternalSyntheticLambda6(VideoRendererEventListener.EventDispatcher eventDispatcher, String str, long j, long j2) {
        this.f$0 = eventDispatcher;
        this.f$1 = str;
        this.f$2 = j;
        this.f$3 = j2;
    }

    public final void run() {
        this.f$0.m892lambda$decoderInitialized$1$androidxmedia3exoplayervideoVideoRendererEventListener$EventDispatcher(this.f$1, this.f$2, this.f$3);
    }
}
