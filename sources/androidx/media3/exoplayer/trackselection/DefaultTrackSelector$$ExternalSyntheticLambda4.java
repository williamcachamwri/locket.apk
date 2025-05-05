package androidx.media3.exoplayer.trackselection;

import androidx.media3.common.Format;
import com.google.common.base.Predicate;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DefaultTrackSelector$$ExternalSyntheticLambda4 implements Predicate {
    public final /* synthetic */ DefaultTrackSelector f$0;

    public /* synthetic */ DefaultTrackSelector$$ExternalSyntheticLambda4(DefaultTrackSelector defaultTrackSelector) {
        this.f$0 = defaultTrackSelector;
    }

    public final boolean apply(Object obj) {
        return this.f$0.isAudioFormatWithinAudioChannelCountConstraints((Format) obj);
    }
}
