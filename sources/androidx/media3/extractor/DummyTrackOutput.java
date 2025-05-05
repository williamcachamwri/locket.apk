package androidx.media3.extractor;

import androidx.media3.common.DataReader;
import androidx.media3.common.Format;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.TrackOutput;
import java.io.IOException;

@Deprecated
public final class DummyTrackOutput implements TrackOutput {
    private final DiscardingTrackOutput discardingTrackOutput = new DiscardingTrackOutput();

    public void format(Format format) {
        this.discardingTrackOutput.format(format);
    }

    public int sampleData(DataReader dataReader, int i, boolean z) throws IOException {
        return this.discardingTrackOutput.sampleData(dataReader, i, z);
    }

    public void sampleData(ParsableByteArray parsableByteArray, int i) {
        this.discardingTrackOutput.sampleData(parsableByteArray, i);
    }

    public int sampleData(DataReader dataReader, int i, boolean z, int i2) throws IOException {
        return this.discardingTrackOutput.sampleData(dataReader, i, z, i2);
    }

    public void sampleData(ParsableByteArray parsableByteArray, int i, int i2) {
        this.discardingTrackOutput.sampleData(parsableByteArray, i, i2);
    }

    public void sampleMetadata(long j, int i, int i2, int i3, TrackOutput.CryptoData cryptoData) {
        this.discardingTrackOutput.sampleMetadata(j, i, i2, i3, cryptoData);
    }
}
