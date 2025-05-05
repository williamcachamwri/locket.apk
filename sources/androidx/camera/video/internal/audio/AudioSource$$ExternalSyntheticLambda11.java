package androidx.camera.video.internal.audio;

import androidx.concurrent.futures.CallbackToFutureAdapter;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class AudioSource$$ExternalSyntheticLambda11 implements Runnable {
    public final /* synthetic */ AudioSource f$0;
    public final /* synthetic */ CallbackToFutureAdapter.Completer f$1;

    public /* synthetic */ AudioSource$$ExternalSyntheticLambda11(AudioSource audioSource, CallbackToFutureAdapter.Completer completer) {
        this.f$0 = audioSource;
        this.f$1 = completer;
    }

    public final void run() {
        this.f$0.m268lambda$release$4$androidxcameravideointernalaudioAudioSource(this.f$1);
    }
}
