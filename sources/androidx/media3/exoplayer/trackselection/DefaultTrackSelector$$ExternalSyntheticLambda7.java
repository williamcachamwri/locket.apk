package androidx.media3.exoplayer.trackselection;

import androidx.media3.common.TrackGroup;
import androidx.media3.exoplayer.trackselection.DefaultTrackSelector;
import java.util.List;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DefaultTrackSelector$$ExternalSyntheticLambda7 implements DefaultTrackSelector.TrackInfo.Factory {
    public final /* synthetic */ DefaultTrackSelector.Parameters f$0;

    public /* synthetic */ DefaultTrackSelector$$ExternalSyntheticLambda7(DefaultTrackSelector.Parameters parameters) {
        this.f$0 = parameters;
    }

    public final List create(int i, TrackGroup trackGroup, int[] iArr) {
        return DefaultTrackSelector.ImageTrackInfo.createForTrackGroup(i, trackGroup, this.f$0, iArr);
    }
}
