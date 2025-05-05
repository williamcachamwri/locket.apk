package androidx.media3.exoplayer.hls;

import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import java.io.IOException;

public interface HlsMediaChunkExtractor {
    void init(ExtractorOutput extractorOutput);

    boolean isPackedAudioExtractor();

    boolean isReusable();

    void onTruncatedSegmentParsed();

    boolean read(ExtractorInput extractorInput) throws IOException;

    HlsMediaChunkExtractor recreate();
}
