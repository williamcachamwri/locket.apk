package androidx.media3.ui;

import android.view.View;
import androidx.media3.common.Player;
import androidx.media3.common.TrackGroup;
import androidx.media3.ui.PlayerControlView;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class PlayerControlView$TrackSelectionAdapter$$ExternalSyntheticLambda0 implements View.OnClickListener {
    public final /* synthetic */ PlayerControlView.TrackSelectionAdapter f$0;
    public final /* synthetic */ Player f$1;
    public final /* synthetic */ TrackGroup f$2;
    public final /* synthetic */ PlayerControlView.TrackInformation f$3;

    public /* synthetic */ PlayerControlView$TrackSelectionAdapter$$ExternalSyntheticLambda0(PlayerControlView.TrackSelectionAdapter trackSelectionAdapter, Player player, TrackGroup trackGroup, PlayerControlView.TrackInformation trackInformation) {
        this.f$0 = trackSelectionAdapter;
        this.f$1 = player;
        this.f$2 = trackGroup;
        this.f$3 = trackInformation;
    }

    public final void onClick(View view) {
        this.f$0.m1144lambda$onBindViewHolder$0$androidxmedia3uiPlayerControlView$TrackSelectionAdapter(this.f$1, this.f$2, this.f$3, view);
    }
}
