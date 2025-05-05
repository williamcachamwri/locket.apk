package androidx.media3.exoplayer.audio;

import android.media.AudioTrack;
import android.os.Handler;
import androidx.media3.exoplayer.audio.AudioSink;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DefaultAudioSink$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ AudioTrack f$0;
    public final /* synthetic */ AudioSink.Listener f$1;
    public final /* synthetic */ Handler f$2;
    public final /* synthetic */ AudioSink.AudioTrackConfig f$3;

    public /* synthetic */ DefaultAudioSink$$ExternalSyntheticLambda2(AudioTrack audioTrack, AudioSink.Listener listener, Handler handler, AudioSink.AudioTrackConfig audioTrackConfig) {
        this.f$0 = audioTrack;
        this.f$1 = listener;
        this.f$2 = handler;
        this.f$3 = audioTrackConfig;
    }

    public final void run() {
        DefaultAudioSink.lambda$releaseAudioTrackAsync$1(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
