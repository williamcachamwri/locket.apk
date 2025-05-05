package androidx.camera.video.internal.audio;

import androidx.camera.video.internal.BufferProvider;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class AudioSource$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ AudioSource f$0;
    public final /* synthetic */ BufferProvider f$1;

    public /* synthetic */ AudioSource$$ExternalSyntheticLambda0(AudioSource audioSource, BufferProvider bufferProvider) {
        this.f$0 = audioSource;
        this.f$1 = bufferProvider;
    }

    public final void run() {
        this.f$0.m271lambda$setBufferProvider$0$androidxcameravideointernalaudioAudioSource(this.f$1);
    }
}
