package androidx.media3.exoplayer.video;

import androidx.media3.common.Format;
import androidx.media3.exoplayer.DecoderReuseEvaluation;
import androidx.media3.exoplayer.video.VideoRendererEventListener;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class VideoRendererEventListener$EventDispatcher$$ExternalSyntheticLambda7 implements Runnable {
    public final /* synthetic */ VideoRendererEventListener.EventDispatcher f$0;
    public final /* synthetic */ Format f$1;
    public final /* synthetic */ DecoderReuseEvaluation f$2;

    public /* synthetic */ VideoRendererEventListener$EventDispatcher$$ExternalSyntheticLambda7(VideoRendererEventListener.EventDispatcher eventDispatcher, Format format, DecoderReuseEvaluation decoderReuseEvaluation) {
        this.f$0 = eventDispatcher;
        this.f$1 = format;
        this.f$2 = decoderReuseEvaluation;
    }

    public final void run() {
        this.f$0.m897lambda$inputFormatChanged$2$androidxmedia3exoplayervideoVideoRendererEventListener$EventDispatcher(this.f$1, this.f$2);
    }
}
