package androidx.media3.extractor;

public final class NoOpExtractorOutput implements ExtractorOutput {
    public void endTracks() {
    }

    public void seekMap(SeekMap seekMap) {
    }

    public TrackOutput track(int i, int i2) {
        return new DiscardingTrackOutput();
    }
}
