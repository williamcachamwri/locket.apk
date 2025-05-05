package androidx.media3.exoplayer.dash;

import androidx.media3.exoplayer.source.chunk.ChunkSampleStream;
import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DashMediaPeriod$$ExternalSyntheticLambda0 implements Function {
    public final Object apply(Object obj) {
        return ImmutableList.of(Integer.valueOf(((ChunkSampleStream) obj).primaryTrackType));
    }
}
