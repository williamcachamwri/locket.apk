package androidx.media3.session;

import androidx.media3.common.AudioAttributes;
import androidx.media3.common.Player;
import androidx.media3.common.util.ListenerSet;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaControllerImplBase$$ExternalSyntheticLambda8 implements ListenerSet.Event {
    public final /* synthetic */ AudioAttributes f$0;

    public /* synthetic */ MediaControllerImplBase$$ExternalSyntheticLambda8(AudioAttributes audioAttributes) {
        this.f$0 = audioAttributes;
    }

    public final void invoke(Object obj) {
        ((Player.Listener) obj).onAudioAttributesChanged(this.f$0);
    }
}
