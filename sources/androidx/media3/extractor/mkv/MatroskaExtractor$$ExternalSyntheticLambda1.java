package androidx.media3.extractor.mkv;

import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorsFactory;
import androidx.media3.extractor.text.SubtitleParser;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MatroskaExtractor$$ExternalSyntheticLambda1 implements ExtractorsFactory {
    public final /* synthetic */ SubtitleParser.Factory f$0;

    public /* synthetic */ MatroskaExtractor$$ExternalSyntheticLambda1(SubtitleParser.Factory factory) {
        this.f$0 = factory;
    }

    public final Extractor[] createExtractors() {
        return MatroskaExtractor.lambda$newFactory$0(this.f$0);
    }
}
