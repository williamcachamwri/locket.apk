package androidx.camera.video.internal.audio;

import androidx.camera.video.internal.audio.AudioStream;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SilentAudioStream$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ AudioStream.AudioStreamCallback f$0;

    public /* synthetic */ SilentAudioStream$$ExternalSyntheticLambda0(AudioStream.AudioStreamCallback audioStreamCallback) {
        this.f$0 = audioStreamCallback;
    }

    public final void run() {
        this.f$0.onSilenceStateChanged(true);
    }
}
