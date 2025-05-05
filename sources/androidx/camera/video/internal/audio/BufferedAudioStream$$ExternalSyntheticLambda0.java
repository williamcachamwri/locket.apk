package androidx.camera.video.internal.audio;

import androidx.camera.video.internal.audio.AudioStream;
import java.util.concurrent.Executor;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class BufferedAudioStream$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ BufferedAudioStream f$0;
    public final /* synthetic */ AudioStream.AudioStreamCallback f$1;
    public final /* synthetic */ Executor f$2;

    public /* synthetic */ BufferedAudioStream$$ExternalSyntheticLambda0(BufferedAudioStream bufferedAudioStream, AudioStream.AudioStreamCallback audioStreamCallback, Executor executor) {
        this.f$0 = bufferedAudioStream;
        this.f$1 = audioStreamCallback;
        this.f$2 = executor;
    }

    public final void run() {
        this.f$0.m277lambda$setCallback$3$androidxcameravideointernalaudioBufferedAudioStream(this.f$1, this.f$2);
    }
}
