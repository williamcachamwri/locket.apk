package androidx.media3.extractor;

import android.net.Uri;
import androidx.media3.extractor.text.SubtitleParser;
import java.util.List;
import java.util.Map;

public interface ExtractorsFactory {
    public static final ExtractorsFactory EMPTY = new ExtractorsFactory$$ExternalSyntheticLambda0();

    static /* synthetic */ Extractor[] lambda$static$0() {
        return new Extractor[0];
    }

    Extractor[] createExtractors();

    @Deprecated
    ExtractorsFactory experimentalSetTextTrackTranscodingEnabled(boolean z) {
        return this;
    }

    ExtractorsFactory setSubtitleParserFactory(SubtitleParser.Factory factory) {
        return this;
    }

    Extractor[] createExtractors(Uri uri, Map<String, List<String>> map) {
        return createExtractors();
    }
}
