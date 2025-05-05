package androidx.media3.exoplayer.audio;

import androidx.media3.exoplayer.audio.AudioRendererEventListener;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class AudioRendererEventListener$EventDispatcher$$ExternalSyntheticLambda10 implements Runnable {
    public final /* synthetic */ AudioRendererEventListener.EventDispatcher f$0;
    public final /* synthetic */ Exception f$1;

    public /* synthetic */ AudioRendererEventListener$EventDispatcher$$ExternalSyntheticLambda10(AudioRendererEventListener.EventDispatcher eventDispatcher, Exception exc) {
        this.f$0 = eventDispatcher;
        this.f$1 = exc;
    }

    public final void run() {
        this.f$0.m491lambda$audioSinkError$8$androidxmedia3exoplayeraudioAudioRendererEventListener$EventDispatcher(this.f$1);
    }
}
