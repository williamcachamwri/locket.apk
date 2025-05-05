package androidx.media3.exoplayer.hls;

import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.source.chunk.MediaChunk;
import java.io.IOException;

final class UnexpectedSampleTimestampException extends IOException {
    public final long lastAcceptedSampleTimeUs;
    public final MediaChunk mediaChunk;
    public final long rejectedSampleTimeUs;

    public UnexpectedSampleTimestampException(MediaChunk mediaChunk2, long j, long j2) {
        super("Unexpected sample timestamp: " + Util.usToMs(j2) + " in chunk [" + mediaChunk2.startTimeUs + ", " + mediaChunk2.endTimeUs + "]");
        this.mediaChunk = mediaChunk2;
        this.lastAcceptedSampleTimeUs = j;
        this.rejectedSampleTimeUs = j2;
    }
}
