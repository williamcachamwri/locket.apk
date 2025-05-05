package androidx.media3.common;

import androidx.media3.common.SimpleBasePlayer;
import androidx.media3.common.util.Size;
import com.google.common.base.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SimpleBasePlayer$$ExternalSyntheticLambda39 implements Supplier {
    public final /* synthetic */ SimpleBasePlayer.State f$0;
    public final /* synthetic */ Size f$1;

    public /* synthetic */ SimpleBasePlayer$$ExternalSyntheticLambda39(SimpleBasePlayer.State state, Size size) {
        this.f$0 = state;
        this.f$1 = size;
    }

    public final Object get() {
        return this.f$0.buildUpon().setSurfaceSize(this.f$1).build();
    }
}
