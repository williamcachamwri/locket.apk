package androidx.media3.exoplayer;

import androidx.media3.common.AudioAttributes;
import androidx.media3.common.Player;
import androidx.media3.common.util.ListenerSet;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ExoPlayerImpl$$ExternalSyntheticLambda23 implements ListenerSet.Event {
    public final /* synthetic */ AudioAttributes f$0;

    public /* synthetic */ ExoPlayerImpl$$ExternalSyntheticLambda23(AudioAttributes audioAttributes) {
        this.f$0 = audioAttributes;
    }

    public final void invoke(Object obj) {
        ((Player.Listener) obj).onAudioAttributesChanged(this.f$0);
    }
}
