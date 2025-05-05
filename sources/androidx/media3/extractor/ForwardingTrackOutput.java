package androidx.media3.extractor;

import androidx.media3.common.DataReader;
import androidx.media3.common.Format;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.TrackOutput;
import java.io.IOException;

public class ForwardingTrackOutput implements TrackOutput {
    private final TrackOutput trackOutput;

    public ForwardingTrackOutput(TrackOutput trackOutput2) {
        this.trackOutput = trackOutput2;
    }

    public void format(Format format) {
        this.trackOutput.format(format);
    }

    public int sampleData(DataReader dataReader, int i, boolean z) throws IOException {
        return this.trackOutput.sampleData(dataReader, i, z);
    }

    public void sampleData(ParsableByteArray parsableByteArray, int i) {
        this.trackOutput.sampleData(parsableByteArray, i);
    }

    public int sampleData(DataReader dataReader, int i, boolean z, int i2) throws IOException {
        return this.trackOutput.sampleData(dataReader, i, z, i2);
    }

    public void sampleData(ParsableByteArray parsableByteArray, int i, int i2) {
        this.trackOutput.sampleData(parsableByteArray, i, i2);
    }

    public void sampleMetadata(long j, int i, int i2, int i3, TrackOutput.CryptoData cryptoData) {
        this.trackOutput.sampleMetadata(j, i, i2, i3, cryptoData);
    }
}
