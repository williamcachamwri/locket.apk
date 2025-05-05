package androidx.media3.common;

import androidx.media3.common.SimpleBasePlayer;
import com.google.common.base.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SimpleBasePlayer$$ExternalSyntheticLambda60 implements Supplier {
    public final /* synthetic */ SimpleBasePlayer.State f$0;
    public final /* synthetic */ TrackSelectionParameters f$1;

    public /* synthetic */ SimpleBasePlayer$$ExternalSyntheticLambda60(SimpleBasePlayer.State state, TrackSelectionParameters trackSelectionParameters) {
        this.f$0 = state;
        this.f$1 = trackSelectionParameters;
    }

    public final Object get() {
        return this.f$0.buildUpon().setTrackSelectionParameters(this.f$1).build();
    }
}
