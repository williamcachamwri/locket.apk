package androidx.media3.extractor.mp3;

import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.MpegAudioUtil;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.SeekPoint;

final class VbriSeeker implements Seeker {
    private static final String TAG = "VbriSeeker";
    private final int bitrate;
    private final long dataEndPosition;
    private final long durationUs;
    private final long[] positions;
    private final long[] timesUs;

    public boolean isSeekable() {
        return true;
    }

    public static VbriSeeker create(long j, long j2, MpegAudioUtil.Header header, ParsableByteArray parsableByteArray) {
        int i;
        long j3 = j;
        MpegAudioUtil.Header header2 = header;
        ParsableByteArray parsableByteArray2 = parsableByteArray;
        parsableByteArray2.skipBytes(6);
        long readInt = j2 + ((long) header2.frameSize) + ((long) parsableByteArray.readInt());
        int readInt2 = parsableByteArray.readInt();
        if (readInt2 <= 0) {
            return null;
        }
        int i2 = header2.sampleRate;
        long scaleLargeTimestamp = Util.scaleLargeTimestamp((long) readInt2, ((long) (i2 >= 32000 ? 1152 : 576)) * 1000000, (long) i2);
        int readUnsignedShort = parsableByteArray.readUnsignedShort();
        int readUnsignedShort2 = parsableByteArray.readUnsignedShort();
        int readUnsignedShort3 = parsableByteArray.readUnsignedShort();
        parsableByteArray2.skipBytes(2);
        long j4 = j2 + ((long) header2.frameSize);
        long[] jArr = new long[readUnsignedShort];
        long[] jArr2 = new long[readUnsignedShort];
        int i3 = 0;
        while (i3 < readUnsignedShort) {
            int i4 = readUnsignedShort2;
            jArr[i3] = (((long) i3) * scaleLargeTimestamp) / ((long) readUnsignedShort);
            long j5 = j4;
            jArr2[i3] = j5;
            if (readUnsignedShort3 == 1) {
                i = parsableByteArray.readUnsignedByte();
            } else if (readUnsignedShort3 == 2) {
                i = parsableByteArray.readUnsignedShort();
            } else if (readUnsignedShort3 == 3) {
                i = parsableByteArray.readUnsignedInt24();
            } else if (readUnsignedShort3 != 4) {
                return null;
            } else {
                i = parsableByteArray.readUnsignedIntToInt();
            }
            long j6 = (long) i;
            int i5 = i4;
            j4 = j5 + (j6 * ((long) i5));
            i3++;
            ParsableByteArray parsableByteArray3 = parsableByteArray;
            readUnsignedShort2 = i5;
            readUnsignedShort = readUnsignedShort;
        }
        if (!(j3 == -1 || j3 == readInt)) {
            Log.w(TAG, "VBRI data size mismatch: " + j3 + ", " + readInt);
        }
        if (readInt != j4) {
            Log.w(TAG, "VBRI bytes and ToC mismatch (using max): " + readInt + ", " + j4 + "\nSeeking will be inaccurate.");
            readInt = Math.max(readInt, j4);
        }
        return new VbriSeeker(jArr, jArr2, scaleLargeTimestamp, readInt, header2.bitrate);
    }

    private VbriSeeker(long[] jArr, long[] jArr2, long j, long j2, int i) {
        this.timesUs = jArr;
        this.positions = jArr2;
        this.durationUs = j;
        this.dataEndPosition = j2;
        this.bitrate = i;
    }

    public SeekMap.SeekPoints getSeekPoints(long j) {
        int binarySearchFloor = Util.binarySearchFloor(this.timesUs, j, true, true);
        SeekPoint seekPoint = new SeekPoint(this.timesUs[binarySearchFloor], this.positions[binarySearchFloor]);
        if (seekPoint.timeUs >= j || binarySearchFloor == this.timesUs.length - 1) {
            return new SeekMap.SeekPoints(seekPoint);
        }
        int i = binarySearchFloor + 1;
        return new SeekMap.SeekPoints(seekPoint, new SeekPoint(this.timesUs[i], this.positions[i]));
    }

    public long getTimeUs(long j) {
        return this.timesUs[Util.binarySearchFloor(this.positions, j, true, true)];
    }

    public long getDurationUs() {
        return this.durationUs;
    }

    public long getDataEndPosition() {
        return this.dataEndPosition;
    }

    public int getAverageBitrate() {
        return this.bitrate;
    }
}
