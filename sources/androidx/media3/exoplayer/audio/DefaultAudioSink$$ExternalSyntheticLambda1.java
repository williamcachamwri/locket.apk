package androidx.media3.exoplayer.audio;

import androidx.media3.exoplayer.audio.AudioSink;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DefaultAudioSink$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ AudioSink.Listener f$0;
    public final /* synthetic */ AudioSink.AudioTrackConfig f$1;

    public /* synthetic */ DefaultAudioSink$$ExternalSyntheticLambda1(AudioSink.Listener listener, AudioSink.AudioTrackConfig audioTrackConfig) {
        this.f$0 = listener;
        this.f$1 = audioTrackConfig;
    }

    public final void run() {
        this.f$0.onAudioTrackReleased(this.f$1);
    }
}
