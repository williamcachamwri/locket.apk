package androidx.media3.exoplayer.audio;

import androidx.media3.common.Format;
import androidx.media3.exoplayer.DecoderReuseEvaluation;
import androidx.media3.exoplayer.audio.AudioRendererEventListener;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class AudioRendererEventListener$EventDispatcher$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ AudioRendererEventListener.EventDispatcher f$0;
    public final /* synthetic */ Format f$1;
    public final /* synthetic */ DecoderReuseEvaluation f$2;

    public /* synthetic */ AudioRendererEventListener$EventDispatcher$$ExternalSyntheticLambda1(AudioRendererEventListener.EventDispatcher eventDispatcher, Format format, DecoderReuseEvaluation decoderReuseEvaluation) {
        this.f$0 = eventDispatcher;
        this.f$1 = format;
        this.f$2 = decoderReuseEvaluation;
    }

    public final void run() {
        this.f$0.m498lambda$inputFormatChanged$2$androidxmedia3exoplayeraudioAudioRendererEventListener$EventDispatcher(this.f$1, this.f$2);
    }
}
