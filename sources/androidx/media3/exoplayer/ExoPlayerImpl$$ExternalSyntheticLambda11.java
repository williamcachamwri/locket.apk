package androidx.media3.exoplayer;

import androidx.media3.common.Player;
import androidx.media3.common.TrackSelectionParameters;
import androidx.media3.common.util.ListenerSet;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ExoPlayerImpl$$ExternalSyntheticLambda11 implements ListenerSet.Event {
    public final /* synthetic */ TrackSelectionParameters f$0;

    public /* synthetic */ ExoPlayerImpl$$ExternalSyntheticLambda11(TrackSelectionParameters trackSelectionParameters) {
        this.f$0 = trackSelectionParameters;
    }

    public final void invoke(Object obj) {
        ((Player.Listener) obj).onTrackSelectionParametersChanged(this.f$0);
    }
}
