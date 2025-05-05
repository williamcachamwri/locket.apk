package androidx.camera.video.internal.audio;

import android.content.Context;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class AudioSource$$ExternalSyntheticLambda8 implements AudioStreamFactory {
    public final AudioStream create(AudioSettings audioSettings, Context context) {
        return new AudioStreamImpl(audioSettings, context);
    }
}
