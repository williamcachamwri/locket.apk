package androidx.camera.video.internal.audio;

import androidx.camera.video.internal.audio.AudioStream;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class AudioStreamImpl$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ AudioStream.AudioStreamCallback f$0;
    public final /* synthetic */ boolean f$1;

    public /* synthetic */ AudioStreamImpl$$ExternalSyntheticLambda0(AudioStream.AudioStreamCallback audioStreamCallback, boolean z) {
        this.f$0 = audioStreamCallback;
        this.f$1 = z;
    }

    public final void run() {
        this.f$0.onSilenceStateChanged(this.f$1);
    }
}
