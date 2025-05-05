package androidx.media3.exoplayer.trackselection;

import androidx.media3.common.TrackGroup;
import androidx.media3.exoplayer.trackselection.DefaultTrackSelector;
import java.util.List;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DefaultTrackSelector$$ExternalSyntheticLambda0 implements DefaultTrackSelector.TrackInfo.Factory {
    public final /* synthetic */ DefaultTrackSelector f$0;
    public final /* synthetic */ DefaultTrackSelector.Parameters f$1;
    public final /* synthetic */ boolean f$2;
    public final /* synthetic */ int[] f$3;

    public /* synthetic */ DefaultTrackSelector$$ExternalSyntheticLambda0(DefaultTrackSelector defaultTrackSelector, DefaultTrackSelector.Parameters parameters, boolean z, int[] iArr) {
        this.f$0 = defaultTrackSelector;
        this.f$1 = parameters;
        this.f$2 = z;
        this.f$3 = iArr;
    }

    public final List create(int i, TrackGroup trackGroup, int[] iArr) {
        return this.f$0.m882lambda$selectAudioTrack$2$androidxmedia3exoplayertrackselectionDefaultTrackSelector(this.f$1, this.f$2, this.f$3, i, trackGroup, iArr);
    }
}
