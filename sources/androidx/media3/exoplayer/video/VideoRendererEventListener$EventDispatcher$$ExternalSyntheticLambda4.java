package androidx.media3.exoplayer.video;

import androidx.media3.exoplayer.DecoderCounters;
import androidx.media3.exoplayer.video.VideoRendererEventListener;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class VideoRendererEventListener$EventDispatcher$$ExternalSyntheticLambda4 implements Runnable {
    public final /* synthetic */ VideoRendererEventListener.EventDispatcher f$0;
    public final /* synthetic */ DecoderCounters f$1;

    public /* synthetic */ VideoRendererEventListener$EventDispatcher$$ExternalSyntheticLambda4(VideoRendererEventListener.EventDispatcher eventDispatcher, DecoderCounters decoderCounters) {
        this.f$0 = eventDispatcher;
        this.f$1 = decoderCounters;
    }

    public final void run() {
        this.f$0.m896lambda$enabled$0$androidxmedia3exoplayervideoVideoRendererEventListener$EventDispatcher(this.f$1);
    }
}
