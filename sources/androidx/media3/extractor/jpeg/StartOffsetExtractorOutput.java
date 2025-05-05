package androidx.media3.extractor.jpeg;

import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.ForwardingSeekMap;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.SeekPoint;
import androidx.media3.extractor.TrackOutput;

public final class StartOffsetExtractorOutput implements ExtractorOutput {
    private final ExtractorOutput extractorOutput;
    /* access modifiers changed from: private */
    public final long startOffset;

    public StartOffsetExtractorOutput(long j, ExtractorOutput extractorOutput2) {
        this.startOffset = j;
        this.extractorOutput = extractorOutput2;
    }

    public TrackOutput track(int i, int i2) {
        return this.extractorOutput.track(i, i2);
    }

    public void endTracks() {
        this.extractorOutput.endTracks();
    }

    public void seekMap(final SeekMap seekMap) {
        this.extractorOutput.seekMap(new ForwardingSeekMap(seekMap) {
            public SeekMap.SeekPoints getSeekPoints(long j) {
                SeekMap.SeekPoints seekPoints = seekMap.getSeekPoints(j);
                return new SeekMap.SeekPoints(new SeekPoint(seekPoints.first.timeUs, seekPoints.first.position + StartOffsetExtractorOutput.this.startOffset), new SeekPoint(seekPoints.second.timeUs, seekPoints.second.position + StartOffsetExtractorOutput.this.startOffset));
            }
        });
    }
}
