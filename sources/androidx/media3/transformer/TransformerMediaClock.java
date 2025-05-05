package androidx.media3.transformer;

import android.util.SparseLongArray;
import androidx.media3.common.C;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.MediaClock;

final class TransformerMediaClock implements MediaClock {
    private long minTrackTimeUs;
    private final SparseLongArray trackTypeToTimeUs = new SparseLongArray();

    public void setPlaybackParameters(PlaybackParameters playbackParameters) {
    }

    public void updateTimeForTrackType(int i, long j) {
        long j2 = this.trackTypeToTimeUs.get(i, C.TIME_UNSET);
        int i2 = (j2 > C.TIME_UNSET ? 1 : (j2 == C.TIME_UNSET ? 0 : -1));
        if (i2 == 0 || j > j2) {
            this.trackTypeToTimeUs.put(i, j);
            if (i2 == 0 || j2 == this.minTrackTimeUs) {
                this.minTrackTimeUs = Util.minValue(this.trackTypeToTimeUs);
            }
        }
    }

    public long getPositionUs() {
        return this.minTrackTimeUs;
    }

    public PlaybackParameters getPlaybackParameters() {
        return PlaybackParameters.DEFAULT;
    }
}
