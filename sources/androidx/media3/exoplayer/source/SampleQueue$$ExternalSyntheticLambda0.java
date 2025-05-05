package androidx.media3.exoplayer.source;

import androidx.media3.common.util.Consumer;
import androidx.media3.exoplayer.source.SampleQueue;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SampleQueue$$ExternalSyntheticLambda0 implements Consumer {
    public final void accept(Object obj) {
        ((SampleQueue.SharedSampleMetadata) obj).drmSessionReference.release();
    }
}
