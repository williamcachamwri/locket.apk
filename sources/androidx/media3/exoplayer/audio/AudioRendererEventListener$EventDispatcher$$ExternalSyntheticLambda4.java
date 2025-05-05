package androidx.media3.exoplayer.audio;

import androidx.media3.exoplayer.audio.AudioRendererEventListener;
import androidx.media3.exoplayer.audio.AudioSink;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class AudioRendererEventListener$EventDispatcher$$ExternalSyntheticLambda4 implements Runnable {
    public final /* synthetic */ AudioRendererEventListener.EventDispatcher f$0;
    public final /* synthetic */ AudioSink.AudioTrackConfig f$1;

    public /* synthetic */ AudioRendererEventListener$EventDispatcher$$ExternalSyntheticLambda4(AudioRendererEventListener.EventDispatcher eventDispatcher, AudioSink.AudioTrackConfig audioTrackConfig) {
        this.f$0 = eventDispatcher;
        this.f$1 = audioTrackConfig;
    }

    public final void run() {
        this.f$0.m492lambda$audioTrackInitialized$10$androidxmedia3exoplayeraudioAudioRendererEventListener$EventDispatcher(this.f$1);
    }
}
