package androidx.media3.exoplayer.source;

import androidx.media3.common.Format;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorsFactory;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DefaultMediaSourceFactory$$ExternalSyntheticLambda0 implements ExtractorsFactory {
    public final /* synthetic */ DefaultMediaSourceFactory f$0;
    public final /* synthetic */ Format f$1;

    public /* synthetic */ DefaultMediaSourceFactory$$ExternalSyntheticLambda0(DefaultMediaSourceFactory defaultMediaSourceFactory, Format format) {
        this.f$0 = defaultMediaSourceFactory;
        this.f$1 = format;
    }

    public final Extractor[] createExtractors() {
        return this.f$0.m855lambda$createMediaSource$0$androidxmedia3exoplayersourceDefaultMediaSourceFactory(this.f$1);
    }
}
