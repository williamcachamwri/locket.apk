package androidx.camera.video.internal.audio;

import androidx.camera.video.internal.audio.AudioSource;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class AudioSource$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ AudioSource.AudioSourceCallback f$0;
    public final /* synthetic */ boolean f$1;

    public /* synthetic */ AudioSource$$ExternalSyntheticLambda1(AudioSource.AudioSourceCallback audioSourceCallback, boolean z) {
        this.f$0 = audioSourceCallback;
        this.f$1 = z;
    }

    public final void run() {
        this.f$0.onSilenceStateChanged(this.f$1);
    }
}
