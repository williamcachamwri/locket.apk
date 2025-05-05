package androidx.media3.extractor.webp;

import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.SingleSampleExtractor;
import java.io.IOException;

public final class WebpExtractor implements Extractor {
    private static final int FILE_SIGNATURE_SEGMENT_LENGTH = 4;
    private static final int RIFF_FILE_SIGNATURE = 1380533830;
    private static final int WEBP_FILE_SIGNATURE = 1464156752;
    private final SingleSampleExtractor imageExtractor = new SingleSampleExtractor(-1, -1, "image/webp");
    private final ParsableByteArray scratch = new ParsableByteArray(4);

    public void release() {
    }

    public boolean sniff(ExtractorInput extractorInput) throws IOException {
        this.scratch.reset(4);
        extractorInput.peekFully(this.scratch.getData(), 0, 4);
        if (this.scratch.readUnsignedInt() != 1380533830) {
            return false;
        }
        extractorInput.advancePeekPosition(4);
        this.scratch.reset(4);
        extractorInput.peekFully(this.scratch.getData(), 0, 4);
        if (this.scratch.readUnsignedInt() == 1464156752) {
            return true;
        }
        return false;
    }

    public void init(ExtractorOutput extractorOutput) {
        this.imageExtractor.init(extractorOutput);
    }

    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException {
        return this.imageExtractor.read(extractorInput, positionHolder);
    }

    public void seek(long j, long j2) {
        this.imageExtractor.seek(j, j2);
    }
}
