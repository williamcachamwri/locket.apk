package androidx.media3.extractor;

import androidx.media3.common.C;
import androidx.media3.exoplayer.DefaultLoadControl;
import com.google.common.base.Ascii;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;
import okio.Utf8;

public class OpusUtil {
    private static final int DEFAULT_SEEK_PRE_ROLL_SAMPLES = 3840;
    private static final int FULL_CODEC_INITIALIZATION_DATA_BUFFER_COUNT = 3;
    public static final int MAX_BYTES_PER_SECOND = 63750;
    public static final int SAMPLE_RATE = 48000;

    private static long getPacketDurationUs(byte b, byte b2) {
        byte b3;
        byte b4 = b & 255;
        byte b5 = b4 & 3;
        if (b5 != 0) {
            b3 = 2;
            if (!(b5 == 1 || b5 == 2)) {
                b3 = b2 & Utf8.REPLACEMENT_BYTE;
            }
        } else {
            b3 = 1;
        }
        int i = b4 >> 3;
        int i2 = i & 3;
        return ((long) b3) * ((long) (i >= 16 ? DefaultLoadControl.DEFAULT_BUFFER_FOR_PLAYBACK_MS << i2 : i >= 12 ? 10000 << (i2 & 1) : i2 == 3 ? 60000 : 10000 << i2));
    }

    private OpusUtil() {
    }

    public static int getChannelCount(byte[] bArr) {
        return bArr[9] & 255;
    }

    public static List<byte[]> buildInitializationData(byte[] bArr) {
        long sampleCountToNanoseconds = sampleCountToNanoseconds((long) getPreSkipSamples(bArr));
        long sampleCountToNanoseconds2 = sampleCountToNanoseconds(3840);
        ArrayList arrayList = new ArrayList(3);
        arrayList.add(bArr);
        arrayList.add(buildNativeOrderByteArray(sampleCountToNanoseconds));
        arrayList.add(buildNativeOrderByteArray(sampleCountToNanoseconds2));
        return arrayList;
    }

    public static int parseOggPacketAudioSampleCount(ByteBuffer byteBuffer) {
        int parseOggPacketForPreAudioSampleByteCount = parseOggPacketForPreAudioSampleByteCount(byteBuffer);
        int i = byteBuffer.get(parseOggPacketForPreAudioSampleByteCount + 26) + Ascii.ESC + parseOggPacketForPreAudioSampleByteCount;
        return (int) ((getPacketDurationUs(byteBuffer.get(i), byteBuffer.limit() - i > 1 ? byteBuffer.get(i + 1) : 0) * 48000) / 1000000);
    }

    public static int parseOggPacketForPreAudioSampleByteCount(ByteBuffer byteBuffer) {
        if ((byteBuffer.get(5) & 2) == 0) {
            return 0;
        }
        byte b = byteBuffer.get(26);
        int i = 28;
        int i2 = 28;
        for (int i3 = 0; i3 < b; i3++) {
            i2 += byteBuffer.get(i3 + 27);
        }
        byte b2 = byteBuffer.get(i2 + 26);
        for (int i4 = 0; i4 < b2; i4++) {
            i += byteBuffer.get(i2 + 27 + i4);
        }
        return i2 + i;
    }

    public static int parsePacketAudioSampleCount(ByteBuffer byteBuffer) {
        byte b = 0;
        byte b2 = byteBuffer.get(0);
        if (byteBuffer.limit() > 1) {
            b = byteBuffer.get(1);
        }
        return (int) ((getPacketDurationUs(b2, b) * 48000) / 1000000);
    }

    public static long getPacketDurationUs(byte[] bArr) {
        byte b = 0;
        byte b2 = bArr[0];
        if (bArr.length > 1) {
            b = bArr[1];
        }
        return getPacketDurationUs(b2, b);
    }

    public static int getPreSkipSamples(byte[] bArr) {
        return (bArr[10] & 255) | ((bArr[11] & 255) << 8);
    }

    public static boolean needToDecodeOpusFrame(long j, long j2) {
        return j - j2 <= sampleCountToNanoseconds(3840) / 1000;
    }

    private static byte[] buildNativeOrderByteArray(long j) {
        return ByteBuffer.allocate(8).order(ByteOrder.nativeOrder()).putLong(j).array();
    }

    private static long sampleCountToNanoseconds(long j) {
        return (j * C.NANOS_PER_SECOND) / 48000;
    }
}
