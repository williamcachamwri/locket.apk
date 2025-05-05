package androidx.camera.video.internal.audio;

import androidx.camera.video.internal.audio.AudioSource;
import java.util.concurrent.Executor;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class AudioSource$$ExternalSyntheticLambda9 implements Runnable {
    public final /* synthetic */ AudioSource f$0;
    public final /* synthetic */ Executor f$1;
    public final /* synthetic */ AudioSource.AudioSourceCallback f$2;

    public /* synthetic */ AudioSource$$ExternalSyntheticLambda9(AudioSource audioSource, Executor executor, AudioSource.AudioSourceCallback audioSourceCallback) {
        this.f$0 = audioSource;
        this.f$1 = executor;
        this.f$2 = audioSourceCallback;
    }

    public final void run() {
        this.f$0.m270lambda$setAudioSourceCallback$6$androidxcameravideointernalaudioAudioSource(this.f$1, this.f$2);
    }
}
