package androidx.media3.extractor;

import androidx.media3.extractor.SeekMap;

public class ForwardingSeekMap implements SeekMap {
    private final SeekMap seekMap;

    public ForwardingSeekMap(SeekMap seekMap2) {
        this.seekMap = seekMap2;
    }

    public boolean isSeekable() {
        return this.seekMap.isSeekable();
    }

    public long getDurationUs() {
        return this.seekMap.getDurationUs();
    }

    public SeekMap.SeekPoints getSeekPoints(long j) {
        return this.seekMap.getSeekPoints(j);
    }
}
