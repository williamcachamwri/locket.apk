package androidx.media3.exoplayer.source;

import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.source.ProgressiveMediaExtractor;
import androidx.media3.exoplayer.source.ProgressiveMediaSource;
import androidx.media3.extractor.ExtractorsFactory;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ProgressiveMediaSource$Factory$$ExternalSyntheticLambda0 implements ProgressiveMediaExtractor.Factory {
    public final /* synthetic */ ExtractorsFactory f$0;

    public /* synthetic */ ProgressiveMediaSource$Factory$$ExternalSyntheticLambda0(ExtractorsFactory extractorsFactory) {
        this.f$0 = extractorsFactory;
    }

    public final ProgressiveMediaExtractor createProgressiveMediaExtractor(PlayerId playerId) {
        return ProgressiveMediaSource.Factory.lambda$new$0(this.f$0, playerId);
    }
}
