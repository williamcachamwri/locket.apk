package androidx.media3.extractor.mp4;

import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorsFactory;
import androidx.media3.extractor.text.SubtitleParser;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Mp4Extractor$$ExternalSyntheticLambda0 implements ExtractorsFactory {
    public final /* synthetic */ SubtitleParser.Factory f$0;

    public /* synthetic */ Mp4Extractor$$ExternalSyntheticLambda0(SubtitleParser.Factory factory) {
        this.f$0 = factory;
    }

    public final Extractor[] createExtractors() {
        return Mp4Extractor.lambda$newFactory$0(this.f$0);
    }
}
