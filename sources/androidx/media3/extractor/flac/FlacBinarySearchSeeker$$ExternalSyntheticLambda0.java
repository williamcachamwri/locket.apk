package androidx.media3.extractor.flac;

import androidx.media3.extractor.BinarySearchSeeker;
import androidx.media3.extractor.FlacStreamMetadata;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class FlacBinarySearchSeeker$$ExternalSyntheticLambda0 implements BinarySearchSeeker.SeekTimestampConverter {
    public final /* synthetic */ FlacStreamMetadata f$0;

    public /* synthetic */ FlacBinarySearchSeeker$$ExternalSyntheticLambda0(FlacStreamMetadata flacStreamMetadata) {
        this.f$0 = flacStreamMetadata;
    }

    public final long timeUsToTargetTime(long j) {
        return this.f$0.getSampleNumber(j);
    }
}
