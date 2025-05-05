package androidx.camera.video.internal.audio;

import androidx.camera.video.internal.audio.AudioSource;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class AudioSource$$ExternalSyntheticLambda3 implements Runnable {
    public final /* synthetic */ AudioSource.AudioSourceCallback f$0;
    public final /* synthetic */ Throwable f$1;

    public /* synthetic */ AudioSource$$ExternalSyntheticLambda3(AudioSource.AudioSourceCallback audioSourceCallback, Throwable th) {
        this.f$0 = audioSourceCallback;
        this.f$1 = th;
    }

    public final void run() {
        this.f$0.onError(this.f$1);
    }
}
