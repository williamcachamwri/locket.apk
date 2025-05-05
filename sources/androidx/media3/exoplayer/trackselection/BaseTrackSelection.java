package androidx.media3.exoplayer.trackselection;

import android.os.SystemClock;
import androidx.media3.common.Format;
import androidx.media3.common.TrackGroup;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.source.chunk.MediaChunk;
import java.util.Arrays;
import java.util.List;

public abstract class BaseTrackSelection implements ExoTrackSelection {
    private final long[] excludeUntilTimes;
    private final Format[] formats;
    protected final TrackGroup group;
    private int hashCode;
    protected final int length;
    private boolean playWhenReady;
    protected final int[] tracks;
    private final int type;

    public void disable() {
    }

    public void enable() {
    }

    public void onPlaybackSpeed(float f) {
    }

    public BaseTrackSelection(TrackGroup trackGroup, int... iArr) {
        this(trackGroup, iArr, 0);
    }

    public BaseTrackSelection(TrackGroup trackGroup, int[] iArr, int i) {
        Assertions.checkState(iArr.length > 0);
        this.type = i;
        this.group = (TrackGroup) Assertions.checkNotNull(trackGroup);
        int length2 = iArr.length;
        this.length = length2;
        this.formats = new Format[length2];
        for (int i2 = 0; i2 < iArr.length; i2++) {
            this.formats[i2] = trackGroup.getFormat(iArr[i2]);
        }
        Arrays.sort(this.formats, new BaseTrackSelection$$ExternalSyntheticLambda0());
        this.tracks = new int[this.length];
        int i3 = 0;
        while (true) {
            int i4 = this.length;
            if (i3 < i4) {
                this.tracks[i3] = trackGroup.indexOf(this.formats[i3]);
                i3++;
            } else {
                this.excludeUntilTimes = new long[i4];
                this.playWhenReady = false;
                return;
            }
        }
    }

    static /* synthetic */ int lambda$new$0(Format format, Format format2) {
        return format2.bitrate - format.bitrate;
    }

    public final int getType() {
        return this.type;
    }

    public final TrackGroup getTrackGroup() {
        return this.group;
    }

    public final int length() {
        return this.tracks.length;
    }

    public final Format getFormat(int i) {
        return this.formats[i];
    }

    public final int getIndexInTrackGroup(int i) {
        return this.tracks[i];
    }

    public final int indexOf(Format format) {
        for (int i = 0; i < this.length; i++) {
            if (this.formats[i] == format) {
                return i;
            }
        }
        return -1;
    }

    public final int indexOf(int i) {
        for (int i2 = 0; i2 < this.length; i2++) {
            if (this.tracks[i2] == i) {
                return i2;
            }
        }
        return -1;
    }

    public final Format getSelectedFormat() {
        return this.formats[getSelectedIndex()];
    }

    public final int getSelectedIndexInTrackGroup() {
        return this.tracks[getSelectedIndex()];
    }

    public int evaluateQueueSize(long j, List<? extends MediaChunk> list) {
        return list.size();
    }

    public boolean excludeTrack(int i, long j) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        boolean isTrackExcluded = isTrackExcluded(i, elapsedRealtime);
        int i2 = 0;
        while (i2 < this.length && !isTrackExcluded) {
            isTrackExcluded = i2 != i && !isTrackExcluded(i2, elapsedRealtime);
            i2++;
        }
        if (!isTrackExcluded) {
            return false;
        }
        long[] jArr = this.excludeUntilTimes;
        jArr[i] = Math.max(jArr[i], Util.addWithOverflowDefault(elapsedRealtime, j, Long.MAX_VALUE));
        return true;
    }

    public boolean isTrackExcluded(int i, long j) {
        return this.excludeUntilTimes[i] > j;
    }

    public void onPlayWhenReadyChanged(boolean z) {
        this.playWhenReady = z;
    }

    /* access modifiers changed from: protected */
    public final boolean getPlayWhenReady() {
        return this.playWhenReady;
    }

    public int hashCode() {
        if (this.hashCode == 0) {
            this.hashCode = (System.identityHashCode(this.group) * 31) + Arrays.hashCode(this.tracks);
        }
        return this.hashCode;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        BaseTrackSelection baseTrackSelection = (BaseTrackSelection) obj;
        if (!this.group.equals(baseTrackSelection.group) || !Arrays.equals(this.tracks, baseTrackSelection.tracks)) {
            return false;
        }
        return true;
    }
}
