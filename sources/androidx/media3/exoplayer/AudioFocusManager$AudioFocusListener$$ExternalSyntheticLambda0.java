package androidx.media3.exoplayer;

import androidx.media3.exoplayer.AudioFocusManager;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class AudioFocusManager$AudioFocusListener$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ AudioFocusManager.AudioFocusListener f$0;
    public final /* synthetic */ int f$1;

    public /* synthetic */ AudioFocusManager$AudioFocusListener$$ExternalSyntheticLambda0(AudioFocusManager.AudioFocusListener audioFocusListener, int i) {
        this.f$0 = audioFocusListener;
        this.f$1 = i;
    }

    public final void run() {
        this.f$0.m464lambda$onAudioFocusChange$0$androidxmedia3exoplayerAudioFocusManager$AudioFocusListener(this.f$1);
    }
}
