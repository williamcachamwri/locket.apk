package androidx.media3.exoplayer.audio;

import androidx.media3.exoplayer.audio.AudioRendererEventListener;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class AudioRendererEventListener$EventDispatcher$$ExternalSyntheticLambda8 implements Runnable {
    public final /* synthetic */ AudioRendererEventListener.EventDispatcher f$0;
    public final /* synthetic */ boolean f$1;

    public /* synthetic */ AudioRendererEventListener$EventDispatcher$$ExternalSyntheticLambda8(AudioRendererEventListener.EventDispatcher eventDispatcher, boolean z) {
        this.f$0 = eventDispatcher;
        this.f$1 = z;
    }

    public final void run() {
        this.f$0.m500lambda$skipSilenceEnabledChanged$7$androidxmedia3exoplayeraudioAudioRendererEventListener$EventDispatcher(this.f$1);
    }
}
