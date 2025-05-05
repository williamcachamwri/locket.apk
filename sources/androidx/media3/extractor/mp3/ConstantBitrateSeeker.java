package androidx.media3.extractor.mp3;

import androidx.media3.extractor.ConstantBitrateSeekMap;
import androidx.media3.extractor.MpegAudioUtil;

final class ConstantBitrateSeeker extends ConstantBitrateSeekMap implements Seeker {
    private final boolean allowSeeksIfLengthUnknown;
    private final int bitrate;
    private final long dataEndPosition;
    private final long firstFramePosition;
    private final int frameSize;

    public ConstantBitrateSeeker(long j, long j2, MpegAudioUtil.Header header, boolean z) {
        this(j, j2, header.bitrate, header.frameSize, z);
    }

    public ConstantBitrateSeeker(long j, long j2, int i, int i2, boolean z) {
        super(j, j2, i, i2, z);
        this.firstFramePosition = j2;
        this.bitrate = i;
        this.frameSize = i2;
        this.allowSeeksIfLengthUnknown = z;
        this.dataEndPosition = j == -1 ? -1 : j;
    }

    public long getTimeUs(long j) {
        return getTimeUsAtPosition(j);
    }

    public long getDataEndPosition() {
        return this.dataEndPosition;
    }

    public int getAverageBitrate() {
        return this.bitrate;
    }

    public ConstantBitrateSeeker copyWithNewDataEndPosition(long j) {
        return new ConstantBitrateSeeker(j, this.firstFramePosition, this.bitrate, this.frameSize, this.allowSeeksIfLengthUnknown);
    }
}
