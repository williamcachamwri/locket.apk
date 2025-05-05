package androidx.media3.exoplayer.hls;

import android.net.Uri;
import androidx.media3.common.Format;
import androidx.media3.common.util.TimestampAdjuster;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.text.SubtitleParser;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface HlsExtractorFactory {
    @Deprecated
    public static final HlsExtractorFactory DEFAULT = new DefaultHlsExtractorFactory();

    HlsMediaChunkExtractor createExtractor(Uri uri, Format format, List<Format> list, TimestampAdjuster timestampAdjuster, Map<String, List<String>> map, ExtractorInput extractorInput, PlayerId playerId) throws IOException;

    HlsExtractorFactory experimentalParseSubtitlesDuringExtraction(boolean z) {
        return this;
    }

    Format getOutputTextFormat(Format format) {
        return format;
    }

    HlsExtractorFactory setSubtitleParserFactory(SubtitleParser.Factory factory) {
        return this;
    }
}
