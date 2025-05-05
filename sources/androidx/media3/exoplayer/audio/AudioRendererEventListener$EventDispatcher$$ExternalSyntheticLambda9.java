package androidx.media3.exoplayer.audio;

import androidx.media3.exoplayer.audio.AudioRendererEventListener;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class AudioRendererEventListener$EventDispatcher$$ExternalSyntheticLambda9 implements Runnable {
    public final /* synthetic */ AudioRendererEventListener.EventDispatcher f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ long f$2;
    public final /* synthetic */ long f$3;

    public /* synthetic */ AudioRendererEventListener$EventDispatcher$$ExternalSyntheticLambda9(AudioRendererEventListener.EventDispatcher eventDispatcher, String str, long j, long j2) {
        this.f$0 = eventDispatcher;
        this.f$1 = str;
        this.f$2 = j;
        this.f$3 = j2;
    }

    public final void run() {
        this.f$0.m494lambda$decoderInitialized$1$androidxmedia3exoplayeraudioAudioRendererEventListener$EventDispatcher(this.f$1, this.f$2, this.f$3);
    }
}
