package androidx.media3.exoplayer.source;

import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.exoplayer.FormatHolder;

public final class EmptySampleStream implements SampleStream {
    public boolean isReady() {
        return true;
    }

    public void maybeThrowError() {
    }

    public int skipData(long j) {
        return 0;
    }

    public int readData(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, int i) {
        decoderInputBuffer.setFlags(4);
        return -4;
    }
}
