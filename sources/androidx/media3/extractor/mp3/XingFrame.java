package androidx.media3.extractor.mp3;

import androidx.media3.common.C;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.MpegAudioUtil;

final class XingFrame {
    public final long dataSize;
    public final int encoderDelay;
    public final int encoderPadding;
    public final long frameCount;
    public final MpegAudioUtil.Header header;
    public final long[] tableOfContents;

    private XingFrame(MpegAudioUtil.Header header2, long j, long j2, long[] jArr, int i, int i2) {
        this.header = new MpegAudioUtil.Header(header2);
        this.frameCount = j;
        this.dataSize = j2;
        this.tableOfContents = jArr;
        this.encoderDelay = i;
        this.encoderPadding = i2;
    }

    public static XingFrame parse(MpegAudioUtil.Header header2, ParsableByteArray parsableByteArray) {
        long[] jArr;
        int i;
        int i2;
        int readInt = parsableByteArray.readInt();
        int readUnsignedIntToInt = (readInt & 1) != 0 ? parsableByteArray.readUnsignedIntToInt() : -1;
        long readUnsignedInt = (readInt & 2) != 0 ? parsableByteArray.readUnsignedInt() : -1;
        if ((readInt & 4) == 4) {
            long[] jArr2 = new long[100];
            for (int i3 = 0; i3 < 100; i3++) {
                jArr2[i3] = (long) parsableByteArray.readUnsignedByte();
            }
            jArr = jArr2;
        } else {
            jArr = null;
        }
        if ((readInt & 8) != 0) {
            parsableByteArray.skipBytes(4);
        }
        if (parsableByteArray.bytesLeft() >= 24) {
            parsableByteArray.skipBytes(21);
            int readUnsignedInt24 = parsableByteArray.readUnsignedInt24();
            i = readUnsignedInt24 & 4095;
            i2 = (16773120 & readUnsignedInt24) >> 12;
        } else {
            i2 = -1;
            i = -1;
        }
        return new XingFrame(header2, (long) readUnsignedIntToInt, readUnsignedInt, jArr, i2, i);
    }

    public long computeDurationUs() {
        long j = this.frameCount;
        return (j == -1 || j == 0) ? C.TIME_UNSET : Util.sampleCountToDurationUs((j * ((long) this.header.samplesPerFrame)) - 1, this.header.sampleRate);
    }
}
