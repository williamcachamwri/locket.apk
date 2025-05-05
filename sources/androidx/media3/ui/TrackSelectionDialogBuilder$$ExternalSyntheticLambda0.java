package androidx.media3.ui;

import androidx.media3.common.Player;
import androidx.media3.ui.TrackSelectionDialogBuilder;
import java.util.Map;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class TrackSelectionDialogBuilder$$ExternalSyntheticLambda0 implements TrackSelectionDialogBuilder.DialogCallback {
    public final /* synthetic */ Player f$0;
    public final /* synthetic */ int f$1;

    public /* synthetic */ TrackSelectionDialogBuilder$$ExternalSyntheticLambda0(Player player, int i) {
        this.f$0 = player;
        this.f$1 = i;
    }

    public final void onTracksSelected(boolean z, Map map) {
        TrackSelectionDialogBuilder.lambda$new$0(this.f$0, this.f$1, z, map);
    }
}
