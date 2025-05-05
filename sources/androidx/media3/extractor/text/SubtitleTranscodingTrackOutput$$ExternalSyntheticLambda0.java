package androidx.media3.extractor.text;

import androidx.media3.common.util.Consumer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SubtitleTranscodingTrackOutput$$ExternalSyntheticLambda0 implements Consumer {
    public final /* synthetic */ SubtitleTranscodingTrackOutput f$0;
    public final /* synthetic */ long f$1;
    public final /* synthetic */ int f$2;

    public /* synthetic */ SubtitleTranscodingTrackOutput$$ExternalSyntheticLambda0(SubtitleTranscodingTrackOutput subtitleTranscodingTrackOutput, long j, int i) {
        this.f$0 = subtitleTranscodingTrackOutput;
        this.f$1 = j;
        this.f$2 = i;
    }

    public final void accept(Object obj) {
        this.f$0.m909lambda$sampleMetadata$0$androidxmedia3extractortextSubtitleTranscodingTrackOutput(this.f$1, this.f$2, (CuesWithTiming) obj);
    }
}
