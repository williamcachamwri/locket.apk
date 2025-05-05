package androidx.media3.extractor;

import androidx.media3.common.C;
import androidx.media3.common.DrmInitData;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.ParsableBitArray;
import androidx.media3.common.util.Util;
import com.google.android.material.internal.ViewUtils;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public final class DtsUtil {
    private static final int[] CHANNELS_BY_AMODE = {1, 2, 2, 2, 2, 3, 3, 4, 4, 5, 6, 6, 6, 7, 8, 8};
    public static final int DTS_EXPRESS_MAX_RATE_BITS_PER_SECOND = 768000;
    public static final int DTS_HD_MAX_RATE_BYTES_PER_SECOND = 2250000;
    public static final int DTS_MAX_RATE_BYTES_PER_SECOND = 192000;
    private static final byte FIRST_BYTE_14B_BE = 31;
    private static final byte FIRST_BYTE_14B_LE = -1;
    private static final byte FIRST_BYTE_BE = Byte.MAX_VALUE;
    private static final byte FIRST_BYTE_EXTSS_BE = 100;
    private static final byte FIRST_BYTE_EXTSS_LE = 37;
    private static final byte FIRST_BYTE_LE = -2;
    private static final byte FIRST_BYTE_UHD_FTOC_NONSYNC_BE = 113;
    private static final byte FIRST_BYTE_UHD_FTOC_NONSYNC_LE = -24;
    private static final byte FIRST_BYTE_UHD_FTOC_SYNC_BE = 64;
    private static final byte FIRST_BYTE_UHD_FTOC_SYNC_LE = -14;
    public static final int FRAME_TYPE_CORE = 1;
    public static final int FRAME_TYPE_EXTENSION_SUBSTREAM = 2;
    public static final int FRAME_TYPE_UHD_NON_SYNC = 4;
    public static final int FRAME_TYPE_UHD_SYNC = 3;
    public static final int FRAME_TYPE_UNKNOWN = 0;
    private static final int[] SAMPLE_RATE_BY_INDEX = {8000, AacUtil.AAC_HE_V1_MAX_RATE_BYTES_PER_SECOND, 32000, 64000, 128000, 22050, 44100, 88200, 176400, 352800, 12000, 24000, OpusUtil.SAMPLE_RATE, 96000, DTS_MAX_RATE_BYTES_PER_SECOND, 384000};
    private static final int[] SAMPLE_RATE_BY_SFREQ = {-1, 8000, AacUtil.AAC_HE_V1_MAX_RATE_BYTES_PER_SECOND, 32000, -1, -1, 11025, 22050, 44100, -1, -1, 12000, 24000, OpusUtil.SAMPLE_RATE, -1, -1};
    private static final int SYNC_VALUE_14B_BE = 536864768;
    private static final int SYNC_VALUE_14B_LE = -14745368;
    private static final int SYNC_VALUE_BE = 2147385345;
    private static final int SYNC_VALUE_EXTSS_BE = 1683496997;
    private static final int SYNC_VALUE_EXTSS_LE = 622876772;
    private static final int SYNC_VALUE_LE = -25230976;
    private static final int SYNC_VALUE_UHD_FTOC_NONSYNC_BE = 1908687592;
    private static final int SYNC_VALUE_UHD_FTOC_NONSYNC_LE = -398277519;
    private static final int SYNC_VALUE_UHD_FTOC_SYNC_BE = 1078008818;
    private static final int SYNC_VALUE_UHD_FTOC_SYNC_LE = -233094848;
    private static final int[] TWICE_BITRATE_KBPS_BY_RATE = {64, 112, 128, 192, 224, 256, 384, 448, 512, 640, ViewUtils.EDGE_TO_EDGE_FLAGS, 896, 1024, 1152, 1280, 1536, 1920, 2048, 2304, 2560, 2688, 2816, 2823, 2944, 3072, 3840, 4096, 6144, 7680};
    private static final int[] UHD_AUDIO_CHUNK_ID_LENGTH_TABLE = {2, 4, 6, 8};
    private static final int[] UHD_AUDIO_CHUNK_SIZE_LENGTH_TABLE = {9, 11, 13, 16};
    private static final int[] UHD_FTOC_PAYLOAD_LENGTH_TABLE = {5, 8, 10, 12};
    private static final int[] UHD_HEADER_SIZE_LENGTH_TABLE = {5, 8, 10, 12};
    private static final int[] UHD_METADATA_CHUNK_SIZE_LENGTH_TABLE = {6, 9, 12, 15};

    @Documented
    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface DtsAudioMimeType {
    }

    @Documented
    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface FrameType {
    }

    public static int getFrameType(int i) {
        if (i == SYNC_VALUE_BE || i == SYNC_VALUE_LE || i == SYNC_VALUE_14B_BE || i == SYNC_VALUE_14B_LE) {
            return 1;
        }
        if (i == SYNC_VALUE_EXTSS_BE || i == SYNC_VALUE_EXTSS_LE) {
            return 2;
        }
        if (i == SYNC_VALUE_UHD_FTOC_SYNC_BE || i == SYNC_VALUE_UHD_FTOC_SYNC_LE) {
            return 3;
        }
        return (i == SYNC_VALUE_UHD_FTOC_NONSYNC_BE || i == SYNC_VALUE_UHD_FTOC_NONSYNC_LE) ? 4 : 0;
    }

    public static final class DtsHeader {
        public final int bitrate;
        public final int channelCount;
        public final long frameDurationUs;
        public final int frameSize;
        public final String mimeType;
        public final int sampleRate;

        private DtsHeader(String str, int i, int i2, int i3, long j, int i4) {
            this.mimeType = str;
            this.channelCount = i;
            this.sampleRate = i2;
            this.frameSize = i3;
            this.frameDurationUs = j;
            this.bitrate = i4;
        }
    }

    public static Format parseDtsFormat(byte[] bArr, String str, String str2, int i, DrmInitData drmInitData) {
        int i2;
        ParsableBitArray normalizedFrame = getNormalizedFrame(bArr);
        normalizedFrame.skipBits(60);
        int i3 = CHANNELS_BY_AMODE[normalizedFrame.readBits(6)];
        int i4 = SAMPLE_RATE_BY_SFREQ[normalizedFrame.readBits(4)];
        int readBits = normalizedFrame.readBits(5);
        int[] iArr = TWICE_BITRATE_KBPS_BY_RATE;
        if (readBits >= iArr.length) {
            i2 = -1;
        } else {
            i2 = (iArr[readBits] * 1000) / 2;
        }
        normalizedFrame.skipBits(10);
        return new Format.Builder().setId(str).setSampleMimeType(MimeTypes.AUDIO_DTS).setAverageBitrate(i2).setChannelCount(i3 + (normalizedFrame.readBits(2) > 0 ? 1 : 0)).setSampleRate(i4).setDrmInitData(drmInitData).setLanguage(str2).setRoleFlags(i).build();
    }

    public static int parseDtsAudioSampleCount(byte[] bArr) {
        byte b;
        int i;
        byte b2;
        int i2;
        byte b3;
        byte b4 = bArr[0];
        if (b4 != -2) {
            if (b4 == -1) {
                i = (bArr[4] & 7) << 4;
                b3 = bArr[7];
            } else if (b4 != 31) {
                i2 = (bArr[4] & 1) << 6;
                b2 = bArr[5];
            } else {
                i = (bArr[5] & 7) << 4;
                b3 = bArr[6];
            }
            b = b3 & 60;
            return (((b >> 2) | i) + 1) * 32;
        }
        i2 = (bArr[5] & 1) << 6;
        b2 = bArr[4];
        b = b2 & 252;
        return (((b >> 2) | i) + 1) * 32;
    }

    public static int parseDtsAudioSampleCount(ByteBuffer byteBuffer) {
        byte b;
        int i;
        byte b2;
        int i2;
        byte b3;
        if (byteBuffer.getInt(0) == SYNC_VALUE_UHD_FTOC_SYNC_LE || byteBuffer.getInt(0) == SYNC_VALUE_UHD_FTOC_NONSYNC_LE) {
            return 1024;
        }
        if (byteBuffer.getInt(0) == SYNC_VALUE_EXTSS_LE) {
            return 4096;
        }
        int position = byteBuffer.position();
        byte b4 = byteBuffer.get(position);
        if (b4 != -2) {
            if (b4 == -1) {
                i = (byteBuffer.get(position + 4) & 7) << 4;
                b3 = byteBuffer.get(position + 7);
            } else if (b4 != 31) {
                i2 = (byteBuffer.get(position + 4) & 1) << 6;
                b2 = byteBuffer.get(position + 5);
            } else {
                i = (byteBuffer.get(position + 5) & 7) << 4;
                b3 = byteBuffer.get(position + 6);
            }
            b = b3 & 60;
            return (((b >> 2) | i) + 1) * 32;
        }
        i2 = (byteBuffer.get(position + 5) & 1) << 6;
        b2 = byteBuffer.get(position + 4);
        b = b2 & 252;
        return (((b >> 2) | i) + 1) * 32;
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x005f  */
    /* JADX WARNING: Removed duplicated region for block: B:15:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int getDtsFrameSize(byte[] r7) {
        /*
            r0 = 0
            byte r1 = r7[r0]
            r2 = -2
            r3 = 6
            r4 = 7
            r5 = 1
            r6 = 4
            if (r1 == r2) goto L_0x004a
            r2 = -1
            if (r1 == r2) goto L_0x0032
            r2 = 31
            if (r1 == r2) goto L_0x0021
            r1 = 5
            byte r1 = r7[r1]
            r1 = r1 & 3
            int r1 = r1 << 12
            byte r2 = r7[r3]
            r2 = r2 & 255(0xff, float:3.57E-43)
            int r2 = r2 << r6
            r1 = r1 | r2
            byte r7 = r7[r4]
            goto L_0x0058
        L_0x0021:
            byte r0 = r7[r3]
            r0 = r0 & 3
            int r0 = r0 << 12
            byte r1 = r7[r4]
            r1 = r1 & 255(0xff, float:3.57E-43)
            int r1 = r1 << r6
            r0 = r0 | r1
            r1 = 8
            byte r7 = r7[r1]
            goto L_0x0042
        L_0x0032:
            byte r0 = r7[r4]
            r0 = r0 & 3
            int r0 = r0 << 12
            byte r1 = r7[r3]
            r1 = r1 & 255(0xff, float:3.57E-43)
            int r1 = r1 << r6
            r0 = r0 | r1
            r1 = 9
            byte r7 = r7[r1]
        L_0x0042:
            r7 = r7 & 60
            int r7 = r7 >> 2
            r7 = r7 | r0
            int r7 = r7 + r5
            r0 = r5
            goto L_0x005d
        L_0x004a:
            byte r1 = r7[r6]
            r1 = r1 & 3
            int r1 = r1 << 12
            byte r2 = r7[r4]
            r2 = r2 & 255(0xff, float:3.57E-43)
            int r2 = r2 << r6
            r1 = r1 | r2
            byte r7 = r7[r3]
        L_0x0058:
            r7 = r7 & 240(0xf0, float:3.36E-43)
            int r7 = r7 >> r6
            r7 = r7 | r1
            int r7 = r7 + r5
        L_0x005d:
            if (r0 == 0) goto L_0x0063
            int r7 = r7 * 16
            int r7 = r7 / 14
        L_0x0063:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.DtsUtil.getDtsFrameSize(byte[]):int");
    }

    public static DtsHeader parseDtsHdHeader(byte[] bArr) throws ParserException {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        long j;
        int i6;
        ParsableBitArray normalizedFrame = getNormalizedFrame(bArr);
        normalizedFrame.skipBits(40);
        int readBits = normalizedFrame.readBits(2);
        if (!normalizedFrame.readBit()) {
            i2 = 16;
            i = 8;
        } else {
            i2 = 20;
            i = 12;
        }
        normalizedFrame.skipBits(i);
        int readBits2 = normalizedFrame.readBits(i2) + 1;
        boolean readBit = normalizedFrame.readBit();
        int i7 = 0;
        if (readBit) {
            i3 = normalizedFrame.readBits(2);
            int readBits3 = (normalizedFrame.readBits(3) + 1) * 512;
            if (normalizedFrame.readBit()) {
                normalizedFrame.skipBits(36);
            }
            int readBits4 = normalizedFrame.readBits(3) + 1;
            int readBits5 = normalizedFrame.readBits(3) + 1;
            if (readBits4 == 1 && readBits5 == 1) {
                int i8 = readBits + 1;
                int readBits6 = normalizedFrame.readBits(i8);
                for (int i9 = 0; i9 < i8; i9++) {
                    if (((readBits6 >> i9) & 1) == 1) {
                        normalizedFrame.skipBits(8);
                    }
                }
                if (normalizedFrame.readBit()) {
                    normalizedFrame.skipBits(2);
                    int readBits7 = (normalizedFrame.readBits(2) + 1) << 2;
                    int readBits8 = normalizedFrame.readBits(2) + 1;
                    while (i7 < readBits8) {
                        normalizedFrame.skipBits(readBits7);
                        i7++;
                    }
                }
                i7 = readBits3;
            } else {
                throw ParserException.createForUnsupportedContainerFeature("Multiple audio presentations or assets not supported");
            }
        } else {
            i3 = -1;
        }
        normalizedFrame.skipBits(i2);
        normalizedFrame.skipBits(12);
        if (readBit) {
            if (normalizedFrame.readBit()) {
                normalizedFrame.skipBits(4);
            }
            if (normalizedFrame.readBit()) {
                normalizedFrame.skipBits(24);
            }
            if (normalizedFrame.readBit()) {
                normalizedFrame.skipBytes(normalizedFrame.readBits(10) + 1);
            }
            normalizedFrame.skipBits(5);
            int i10 = SAMPLE_RATE_BY_INDEX[normalizedFrame.readBits(4)];
            i5 = normalizedFrame.readBits(8) + 1;
            i4 = i10;
        } else {
            i4 = -2147483647;
            i5 = -1;
        }
        if (readBit) {
            if (i3 == 0) {
                i6 = 32000;
            } else if (i3 == 1) {
                i6 = 44100;
            } else if (i3 == 2) {
                i6 = OpusUtil.SAMPLE_RATE;
            } else {
                throw ParserException.createForMalformedContainer("Unsupported reference clock code in DTS HD header: " + i3, (Throwable) null);
            }
            j = Util.scaleLargeTimestamp((long) i7, 1000000, (long) i6);
        } else {
            j = C.TIME_UNSET;
        }
        return new DtsHeader(MimeTypes.AUDIO_DTS_EXPRESS, i5, i4, readBits2, j, 0);
    }

    public static int parseDtsHdHeaderSize(byte[] bArr) {
        ParsableBitArray normalizedFrame = getNormalizedFrame(bArr);
        normalizedFrame.skipBits(42);
        return normalizedFrame.readBits(normalizedFrame.readBit() ? 12 : 8) + 1;
    }

    public static DtsHeader parseDtsUhdHeader(byte[] bArr, AtomicInteger atomicInteger) throws ParserException {
        long j;
        int i;
        int i2;
        int i3;
        ParsableBitArray normalizedFrame = getNormalizedFrame(bArr);
        int i4 = 0;
        int i5 = normalizedFrame.readBits(32) == SYNC_VALUE_UHD_FTOC_SYNC_BE ? 1 : 0;
        int parseUnsignedVarInt = parseUnsignedVarInt(normalizedFrame, UHD_FTOC_PAYLOAD_LENGTH_TABLE, true) + 1;
        if (i5 == 0) {
            i = C.RATE_UNSET_INT;
            j = C.TIME_UNSET;
        } else if (normalizedFrame.readBit()) {
            checkCrc(bArr, parseUnsignedVarInt);
            int readBits = normalizedFrame.readBits(2);
            if (readBits == 0) {
                i2 = 512;
            } else if (readBits == 1) {
                i2 = 480;
            } else if (readBits == 2) {
                i2 = 384;
            } else {
                throw ParserException.createForMalformedContainer("Unsupported base duration index in DTS UHD header: " + readBits, (Throwable) null);
            }
            int readBits2 = i2 * (normalizedFrame.readBits(3) + 1);
            int readBits3 = normalizedFrame.readBits(2);
            if (readBits3 == 0) {
                i3 = 32000;
            } else if (readBits3 == 1) {
                i3 = 44100;
            } else if (readBits3 == 2) {
                i3 = OpusUtil.SAMPLE_RATE;
            } else {
                throw ParserException.createForMalformedContainer("Unsupported clock rate index in DTS UHD header: " + readBits3, (Throwable) null);
            }
            if (normalizedFrame.readBit()) {
                normalizedFrame.skipBits(36);
            }
            i = (1 << normalizedFrame.readBits(2)) * i3;
            j = Util.scaleLargeTimestamp((long) readBits2, 1000000, (long) i3);
        } else {
            throw ParserException.createForUnsupportedContainerFeature("Only supports full channel mask-based audio presentation");
        }
        int i6 = i;
        long j2 = j;
        int i7 = 0;
        for (int i8 = 0; i8 < i5; i8++) {
            i7 += parseUnsignedVarInt(normalizedFrame, UHD_METADATA_CHUNK_SIZE_LENGTH_TABLE, true);
        }
        if (i5 != 0) {
            atomicInteger.set(parseUnsignedVarInt(normalizedFrame, UHD_AUDIO_CHUNK_ID_LENGTH_TABLE, true));
        } else {
            AtomicInteger atomicInteger2 = atomicInteger;
        }
        if (atomicInteger.get() != 0) {
            i4 = parseUnsignedVarInt(normalizedFrame, UHD_AUDIO_CHUNK_SIZE_LENGTH_TABLE, true);
        }
        return new DtsHeader(MimeTypes.AUDIO_DTS_X, 2, i6, parseUnsignedVarInt + i7 + i4, j2, 0);
    }

    public static int parseDtsUhdHeaderSize(byte[] bArr) {
        ParsableBitArray normalizedFrame = getNormalizedFrame(bArr);
        normalizedFrame.skipBits(32);
        return parseUnsignedVarInt(normalizedFrame, UHD_HEADER_SIZE_LENGTH_TABLE, true) + 1;
    }

    private static void checkCrc(byte[] bArr, int i) throws ParserException {
        int i2 = i - 2;
        if (((bArr[i - 1] & 255) | ((bArr[i2] << 8) & 65535)) != Util.crc16(bArr, 0, i2, 65535)) {
            throw ParserException.createForMalformedContainer("CRC check failed", (Throwable) null);
        }
    }

    private static int parseUnsignedVarInt(ParsableBitArray parsableBitArray, int[] iArr, boolean z) {
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < 3 && parsableBitArray.readBit(); i3++) {
            i2++;
        }
        if (z) {
            int i4 = 0;
            while (i < i2) {
                i4 += 1 << iArr[i];
                i++;
            }
            i = i4;
        }
        return i + parsableBitArray.readBits(iArr[i2]);
    }

    private static ParsableBitArray getNormalizedFrame(byte[] bArr) {
        byte b = bArr[0];
        if (b == Byte.MAX_VALUE || b == 100 || b == 64 || b == 113) {
            return new ParsableBitArray(bArr);
        }
        byte[] copyOf = Arrays.copyOf(bArr, bArr.length);
        if (isLittleEndianFrameHeader(copyOf)) {
            for (int i = 0; i < copyOf.length - 1; i += 2) {
                byte b2 = copyOf[i];
                int i2 = i + 1;
                copyOf[i] = copyOf[i2];
                copyOf[i2] = b2;
            }
        }
        ParsableBitArray parsableBitArray = new ParsableBitArray(copyOf);
        if (copyOf[0] == 31) {
            ParsableBitArray parsableBitArray2 = new ParsableBitArray(copyOf);
            while (parsableBitArray2.bitsLeft() >= 16) {
                parsableBitArray2.skipBits(2);
                parsableBitArray.putInt(parsableBitArray2.readBits(14), 14);
            }
        }
        parsableBitArray.reset(copyOf);
        return parsableBitArray;
    }

    private static boolean isLittleEndianFrameHeader(byte[] bArr) {
        byte b = bArr[0];
        return b == -2 || b == -1 || b == 37 || b == -14 || b == -24;
    }

    private DtsUtil() {
    }
}
