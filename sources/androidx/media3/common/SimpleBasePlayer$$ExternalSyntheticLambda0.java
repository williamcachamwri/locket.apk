package androidx.media3.common;

import androidx.media3.common.SimpleBasePlayer;
import com.google.common.base.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SimpleBasePlayer$$ExternalSyntheticLambda0 implements Supplier {
    public final /* synthetic */ SimpleBasePlayer.State f$0;
    public final /* synthetic */ int f$1;

    public /* synthetic */ SimpleBasePlayer$$ExternalSyntheticLambda0(SimpleBasePlayer.State state, int i) {
        this.f$0 = state;
        this.f$1 = i;
    }

    public final Object get() {
        return this.f$0.buildUpon().setDeviceVolume(this.f$1).build();
    }
}
