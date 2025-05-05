package androidx.media3.common;

import androidx.media3.common.SimpleBasePlayer;
import com.google.common.base.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SimpleBasePlayer$$ExternalSyntheticLambda2 implements Supplier {
    public final /* synthetic */ SimpleBasePlayer.State f$0;
    public final /* synthetic */ AudioAttributes f$1;

    public /* synthetic */ SimpleBasePlayer$$ExternalSyntheticLambda2(SimpleBasePlayer.State state, AudioAttributes audioAttributes) {
        this.f$0 = state;
        this.f$1 = audioAttributes;
    }

    public final Object get() {
        return this.f$0.buildUpon().setAudioAttributes(this.f$1).build();
    }
}
