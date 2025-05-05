package androidx.media3.extractor;

import androidx.media3.common.ParserException;
import androidx.media3.common.util.ParsableBitArray;
import androidx.media3.common.util.ParsableByteArray;
import com.google.common.primitives.SignedBytes;
import com.google.logging.type.LogSeverity;
import java.nio.ByteBuffer;

public final class Ac4Util {
    public static final int AC40_SYNCWORD = 44096;
    public static final int AC41_SYNCWORD = 44097;
    private static final int CHANNEL_COUNT_2 = 2;
    private static final int CHANNEL_MODE_22_2 = 15;
    private static final int CHANNEL_MODE_3_0 = 2;
    private static final int CHANNEL_MODE_5_0 = 3;
    private static final int CHANNEL_MODE_5_1 = 4;
    private static final int CHANNEL_MODE_7_0_322 = 9;
    private static final int CHANNEL_MODE_7_0_34 = 5;
    private static final int CHANNEL_MODE_7_0_4 = 11;
    private static final int CHANNEL_MODE_7_0_52 = 7;
    private static final int CHANNEL_MODE_7_1_322 = 10;
    private static final int CHANNEL_MODE_7_1_34 = 6;
    private static final int CHANNEL_MODE_7_1_4 = 12;
    private static final int CHANNEL_MODE_7_1_52 = 8;
    private static final int CHANNEL_MODE_9_0_4 = 13;
    private static final int CHANNEL_MODE_9_1_4 = 14;
    private static final int CHANNEL_MODE_MONO = 0;
    private static final int CHANNEL_MODE_STEREO = 1;
    private static final int CHANNEL_MODE_UNKNOWN = -1;
    public static final int HEADER_SIZE_FOR_PARSER = 16;
    public static final int MAX_RATE_BYTES_PER_SECOND = 336000;
    private static final int[] SAMPLE_COUNT = {2002, 2000, 1920, 1601, 1600, 1001, 1000, 960, LogSeverity.EMERGENCY_VALUE, LogSeverity.EMERGENCY_VALUE, 480, 400, 400, 2048};
    public static final int SAMPLE_HEADER_SIZE = 7;

    private static int getChannelCountFromChannelMode(int i) {
        switch (i) {
            case 0:
                return 1;
            case 1:
                return 2;
            case 2:
                return 3;
            case 3:
                return 5;
            case 4:
                return 6;
            case 5:
            case 7:
            case 9:
                return 7;
            case 6:
            case 8:
            case 10:
                return 8;
            case 11:
                return 11;
            case 12:
                return 12;
            case 13:
                return 13;
            case 14:
                return 14;
            case 15:
                return 24;
            default:
                return -1;
        }
    }

    public static final class SyncFrameInfo {
        public final int bitstreamVersion;
        public final int channelCount;
        public final int frameSize;
        public final int sampleCount;
        public final int sampleRate;

        private SyncFrameInfo(int i, int i2, int i3, int i4, int i5) {
            this.bitstreamVersion = i;
            this.channelCount = i2;
            this.sampleRate = i3;
            this.frameSize = i4;
            this.sampleCount = i5;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:100:0x018d, code lost:
        r4 = r0.readBits(3);
        r2 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x0195, code lost:
        if (r2 >= (r4 + 2)) goto L_0x01d1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x0197, code lost:
        parseDsiSubstreamGroup(r0, r6);
        r2 = r2 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:104:0x019d, code lost:
        if (r14 != 0) goto L_0x01a9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x019f, code lost:
        r2 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x01a1, code lost:
        if (r2 >= 3) goto L_0x01d1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:0x01a3, code lost:
        parseDsiSubstream(r0, r6);
        r2 = r2 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x01a9, code lost:
        r4 = 3;
        r2 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x01ab, code lost:
        if (r2 >= r4) goto L_0x01d1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x01ad, code lost:
        parseDsiSubstreamGroup(r0, r6);
        r2 = r2 + 1;
        r4 = 3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:111:0x01b4, code lost:
        if (r14 != 0) goto L_0x01bf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x01b6, code lost:
        r2 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:113:0x01b7, code lost:
        if (r2 >= 2) goto L_0x01d1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:114:0x01b9, code lost:
        parseDsiSubstream(r0, r6);
        r2 = r2 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:115:0x01bf, code lost:
        r2 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x01c0, code lost:
        if (r2 >= 2) goto L_0x01d1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:117:0x01c2, code lost:
        parseDsiSubstreamGroup(r0, r6);
        r2 = r2 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:118:0x01c8, code lost:
        if (r14 != 0) goto L_0x01ce;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:119:0x01ca, code lost:
        parseDsiSubstream(r0, r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:120:0x01ce, code lost:
        parseDsiSubstreamGroup(r0, r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:121:0x01d1, code lost:
        r0.skipBit();
        r2 = r0.readBit();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:122:0x01d8, code lost:
        if (r2 == false) goto L_0x01ea;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:123:0x01da, code lost:
        r2 = r0.readBits(7);
        r4 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:124:0x01e0, code lost:
        if (r4 >= r2) goto L_0x01ea;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:125:0x01e2, code lost:
        r0.skipBits(15);
        r4 = r4 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:126:0x01ea, code lost:
        if (r14 <= 0) goto L_0x0226;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:128:0x01f0, code lost:
        if (r0.readBit() == false) goto L_0x0200;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:130:0x01f6, code lost:
        if (skipDsiBitrate(r0) == false) goto L_0x01f9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:132:0x01ff, code lost:
        throw androidx.media3.common.ParserException.createForUnsupportedContainerFeature("Can't parse bitrate DSI.");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:134:0x0204, code lost:
        if (r0.readBit() == false) goto L_0x0226;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:135:0x0206, code lost:
        r0.byteAlign();
        r0.skipBytes(r0.readBits(16));
        r2 = r0.readBits(5);
        r11 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:136:0x0218, code lost:
        if (r11 >= r2) goto L_0x0226;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:137:0x021a, code lost:
        r0.skipBits(3);
        r0.skipBits(8);
        r11 = r11 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:138:0x0226, code lost:
        r0.byteAlign();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:139:0x022c, code lost:
        if (r3 != 1) goto L_0x0243;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:140:0x022e, code lost:
        r1 = ((r1 - r0.bitsLeft()) / 8) - r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:141:0x0235, code lost:
        if (r5 < r1) goto L_0x023c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:142:0x0237, code lost:
        r0.skipBytes(r5 - r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:144:0x0242, code lost:
        throw androidx.media3.common.ParserException.createForUnsupportedContainerFeature("pres_bytes is smaller than presentation bytes read.");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:146:0x0245, code lost:
        if (r6.isChannelCoded == false) goto L_0x0261;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:148:0x024a, code lost:
        if (r6.channelMode == -1) goto L_0x024d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:150:0x0260, code lost:
        throw androidx.media3.common.ParserException.createForUnsupportedContainerFeature("Can't determine channel mode of presentation " + r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00c9, code lost:
        if (r9 != false) goto L_0x00d3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00cb, code lost:
        if (r10 != false) goto L_0x00d3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00ce, code lost:
        if (r11 != 6) goto L_0x00d3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00d0, code lost:
        r2 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00d3, code lost:
        r6.level = r0.readBits(3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00dd, code lost:
        if (r0.readBit() == false) goto L_0x00e2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00df, code lost:
        r0.skipBits(5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00e2, code lost:
        r0.skipBits(2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00e6, code lost:
        if (r3 != 1) goto L_0x00ef;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00e8, code lost:
        if (r14 == 1) goto L_0x00ec;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00ea, code lost:
        if (r14 != 2) goto L_0x00ef;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00ec, code lost:
        r0.skipBits(2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00ef, code lost:
        r0.skipBits(5);
        r0.skipBits(10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00f7, code lost:
        if (r3 != 1) goto L_0x015e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00f9, code lost:
        if (r14 <= 0) goto L_0x0101;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00fb, code lost:
        r6.isChannelCoded = r0.readBit();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0103, code lost:
        if (r6.isChannelCoded == false) goto L_0x0130;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x0105, code lost:
        if (r14 == 1) goto L_0x0109;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0107, code lost:
        if (r14 != 2) goto L_0x0129;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0109, code lost:
        r2 = r0.readBits(5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x010d, code lost:
        if (r2 < 0) goto L_0x0115;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0111, code lost:
        if (r2 > 15) goto L_0x0115;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0113, code lost:
        r6.channelMode = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x0117, code lost:
        if (r2 < 11) goto L_0x0129;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x011b, code lost:
        if (r2 > 14) goto L_0x0129;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x011d, code lost:
        r6.hasBackChannels = r0.readBit();
        r6.topChannelPairs = r0.readBits(2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x0129, code lost:
        r0.skipBits(24);
        r2 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x0130, code lost:
        r2 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0131, code lost:
        if (r14 == r2) goto L_0x0135;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x0133, code lost:
        if (r14 != 2) goto L_0x015e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x0139, code lost:
        if (r0.readBit() == false) goto L_0x0144;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x013f, code lost:
        if (r0.readBit() == false) goto L_0x0144;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x0141, code lost:
        r0.skipBits(2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x0148, code lost:
        if (r0.readBit() == false) goto L_0x015e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x014a, code lost:
        r0.skipBit();
        r2 = 8;
        r4 = r0.readBits(8);
        r13 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x0154, code lost:
        if (r13 >= r4) goto L_0x015e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x0156, code lost:
        r0.skipBits(r2);
        r13 = r13 + 1;
        r2 = 8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x015e, code lost:
        if (r9 != false) goto L_0x01c8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x0160, code lost:
        if (r10 == false) goto L_0x0164;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x0164, code lost:
        r0.skipBit();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x0167, code lost:
        if (r11 == 0) goto L_0x01b4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x016a, code lost:
        if (r11 == 1) goto L_0x01b4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x016c, code lost:
        if (r11 == 2) goto L_0x01b4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x016f, code lost:
        if (r11 == 3) goto L_0x019d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x0172, code lost:
        if (r11 == 4) goto L_0x019d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x0175, code lost:
        if (r11 == 5) goto L_0x0187;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x0177, code lost:
        r4 = r0.readBits(7);
        r2 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x017d, code lost:
        if (r2 >= r4) goto L_0x01d1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x017f, code lost:
        r0.skipBits(8);
        r2 = r2 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x0187, code lost:
        if (r14 != 0) goto L_0x018d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x0189, code lost:
        parseDsiSubstream(r0, r6);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static androidx.media3.common.Format parseAc4AnnexEFormat(androidx.media3.common.util.ParsableByteArray r18, java.lang.String r19, java.lang.String r20, androidx.media3.common.DrmInitData r21) throws androidx.media3.common.ParserException {
        /*
            androidx.media3.common.util.ParsableBitArray r0 = new androidx.media3.common.util.ParsableBitArray
            r0.<init>()
            r1 = r18
            r0.reset((androidx.media3.common.util.ParsableByteArray) r1)
            int r1 = r0.bitsLeft()
            r2 = 3
            int r3 = r0.readBits(r2)
            r4 = 1
            if (r3 > r4) goto L_0x02b2
            r5 = 7
            int r6 = r0.readBits(r5)
            boolean r7 = r0.readBit()
            if (r7 == 0) goto L_0x0025
            r7 = 48000(0xbb80, float:6.7262E-41)
            goto L_0x0028
        L_0x0025:
            r7 = 44100(0xac44, float:6.1797E-41)
        L_0x0028:
            r8 = 4
            r0.skipBits(r8)
            r9 = 9
            int r9 = r0.readBits(r9)
            r10 = 16
            if (r6 <= r4) goto L_0x0061
            if (r3 == 0) goto L_0x004d
            boolean r6 = r0.readBit()
            if (r6 == 0) goto L_0x0061
            r0.skipBits(r10)
            boolean r6 = r0.readBit()
            if (r6 == 0) goto L_0x0061
            r6 = 128(0x80, float:1.794E-43)
            r0.skipBits(r6)
            goto L_0x0061
        L_0x004d:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Invalid AC-4 DSI version: "
            r0.<init>(r1)
            java.lang.StringBuilder r0 = r0.append(r3)
            java.lang.String r0 = r0.toString()
            androidx.media3.common.ParserException r0 = androidx.media3.common.ParserException.createForUnsupportedContainerFeature(r0)
            throw r0
        L_0x0061:
            if (r3 != r4) goto L_0x0074
            boolean r6 = skipDsiBitrate(r0)
            if (r6 == 0) goto L_0x006d
            r0.byteAlign()
            goto L_0x0074
        L_0x006d:
            java.lang.String r0 = "Invalid AC-4 DSI bitrate."
            androidx.media3.common.ParserException r0 = androidx.media3.common.ParserException.createForUnsupportedContainerFeature(r0)
            throw r0
        L_0x0074:
            androidx.media3.extractor.Ac4Util$Ac4Presentation r6 = new androidx.media3.extractor.Ac4Util$Ac4Presentation
            r11 = 0
            r6.<init>()
            r12 = 0
        L_0x007b:
            if (r12 >= r9) goto L_0x0261
            r13 = 5
            r14 = 8
            r15 = 2
            if (r3 != 0) goto L_0x0097
            boolean r9 = r0.readBit()
            int r16 = r0.readBits(r13)
            int r17 = r0.readBits(r13)
            r11 = r16
            r14 = r17
            r5 = 0
            r8 = 0
            r10 = 0
            goto L_0x00c9
        L_0x0097:
            int r11 = r0.readBits(r14)
            int r5 = r0.readBits(r14)
            r8 = 255(0xff, float:3.57E-43)
            if (r5 != r8) goto L_0x00a8
            int r8 = r0.readBits(r10)
            int r5 = r5 + r8
        L_0x00a8:
            if (r11 <= r15) goto L_0x00b4
            int r5 = r5 * 8
            r0.skipBits(r5)
            int r12 = r12 + 1
            r5 = 7
            r8 = 4
            goto L_0x007b
        L_0x00b4:
            int r8 = r0.bitsLeft()
            int r8 = r1 - r8
            int r8 = r8 / r14
            int r9 = r0.readBits(r13)
            r10 = 31
            if (r9 != r10) goto L_0x00c5
            r10 = r4
            goto L_0x00c6
        L_0x00c5:
            r10 = 0
        L_0x00c6:
            r14 = r11
            r11 = r9
            r9 = 0
        L_0x00c9:
            if (r9 != 0) goto L_0x00d3
            if (r10 != 0) goto L_0x00d3
            r4 = 6
            if (r11 != r4) goto L_0x00d3
            r2 = 1
            goto L_0x01d8
        L_0x00d3:
            int r4 = r0.readBits(r2)
            r6.level = r4
            boolean r4 = r0.readBit()
            if (r4 == 0) goto L_0x00e2
            r0.skipBits(r13)
        L_0x00e2:
            r0.skipBits(r15)
            r4 = 1
            if (r3 != r4) goto L_0x00ef
            if (r14 == r4) goto L_0x00ec
            if (r14 != r15) goto L_0x00ef
        L_0x00ec:
            r0.skipBits(r15)
        L_0x00ef:
            r0.skipBits(r13)
            r2 = 10
            r0.skipBits(r2)
            if (r3 != r4) goto L_0x015e
            if (r14 <= 0) goto L_0x0101
            boolean r2 = r0.readBit()
            r6.isChannelCoded = r2
        L_0x0101:
            boolean r2 = r6.isChannelCoded
            if (r2 == 0) goto L_0x0130
            if (r14 == r4) goto L_0x0109
            if (r14 != r15) goto L_0x0129
        L_0x0109:
            int r2 = r0.readBits(r13)
            if (r2 < 0) goto L_0x0115
            r4 = 15
            if (r2 > r4) goto L_0x0115
            r6.channelMode = r2
        L_0x0115:
            r4 = 11
            if (r2 < r4) goto L_0x0129
            r4 = 14
            if (r2 > r4) goto L_0x0129
            boolean r2 = r0.readBit()
            r6.hasBackChannels = r2
            int r2 = r0.readBits(r15)
            r6.topChannelPairs = r2
        L_0x0129:
            r2 = 24
            r0.skipBits(r2)
            r2 = 1
            goto L_0x0131
        L_0x0130:
            r2 = r4
        L_0x0131:
            if (r14 == r2) goto L_0x0135
            if (r14 != r15) goto L_0x015e
        L_0x0135:
            boolean r2 = r0.readBit()
            if (r2 == 0) goto L_0x0144
            boolean r2 = r0.readBit()
            if (r2 == 0) goto L_0x0144
            r0.skipBits(r15)
        L_0x0144:
            boolean r2 = r0.readBit()
            if (r2 == 0) goto L_0x015e
            r0.skipBit()
            r2 = 8
            int r4 = r0.readBits(r2)
            r13 = 0
        L_0x0154:
            if (r13 >= r4) goto L_0x015e
            r0.skipBits(r2)
            int r13 = r13 + 1
            r2 = 8
            goto L_0x0154
        L_0x015e:
            if (r9 != 0) goto L_0x01c8
            if (r10 == 0) goto L_0x0164
            goto L_0x01c8
        L_0x0164:
            r0.skipBit()
            if (r11 == 0) goto L_0x01b4
            r2 = 1
            if (r11 == r2) goto L_0x01b4
            if (r11 == r15) goto L_0x01b4
            r2 = 3
            if (r11 == r2) goto L_0x019d
            r2 = 4
            if (r11 == r2) goto L_0x019d
            r2 = 5
            if (r11 == r2) goto L_0x0187
            r2 = 7
            int r4 = r0.readBits(r2)
            r2 = 0
        L_0x017d:
            if (r2 >= r4) goto L_0x01d1
            r9 = 8
            r0.skipBits(r9)
            int r2 = r2 + 1
            goto L_0x017d
        L_0x0187:
            if (r14 != 0) goto L_0x018d
            parseDsiSubstream(r0, r6)
            goto L_0x01d1
        L_0x018d:
            r2 = 3
            int r4 = r0.readBits(r2)
            r2 = 0
        L_0x0193:
            int r9 = r4 + 2
            if (r2 >= r9) goto L_0x01d1
            parseDsiSubstreamGroup(r0, r6)
            int r2 = r2 + 1
            goto L_0x0193
        L_0x019d:
            if (r14 != 0) goto L_0x01a9
            r2 = 0
            r4 = 3
        L_0x01a1:
            if (r2 >= r4) goto L_0x01d1
            parseDsiSubstream(r0, r6)
            int r2 = r2 + 1
            goto L_0x01a1
        L_0x01a9:
            r4 = 3
            r2 = 0
        L_0x01ab:
            if (r2 >= r4) goto L_0x01d1
            parseDsiSubstreamGroup(r0, r6)
            int r2 = r2 + 1
            r4 = 3
            goto L_0x01ab
        L_0x01b4:
            if (r14 != 0) goto L_0x01bf
            r2 = 0
        L_0x01b7:
            if (r2 >= r15) goto L_0x01d1
            parseDsiSubstream(r0, r6)
            int r2 = r2 + 1
            goto L_0x01b7
        L_0x01bf:
            r2 = 0
        L_0x01c0:
            if (r2 >= r15) goto L_0x01d1
            parseDsiSubstreamGroup(r0, r6)
            int r2 = r2 + 1
            goto L_0x01c0
        L_0x01c8:
            if (r14 != 0) goto L_0x01ce
            parseDsiSubstream(r0, r6)
            goto L_0x01d1
        L_0x01ce:
            parseDsiSubstreamGroup(r0, r6)
        L_0x01d1:
            r0.skipBit()
            boolean r2 = r0.readBit()
        L_0x01d8:
            if (r2 == 0) goto L_0x01ea
            r2 = 7
            int r2 = r0.readBits(r2)
            r4 = 0
        L_0x01e0:
            if (r4 >= r2) goto L_0x01ea
            r9 = 15
            r0.skipBits(r9)
            int r4 = r4 + 1
            goto L_0x01e0
        L_0x01ea:
            if (r14 <= 0) goto L_0x0226
            boolean r2 = r0.readBit()
            if (r2 == 0) goto L_0x0200
            boolean r2 = skipDsiBitrate(r0)
            if (r2 == 0) goto L_0x01f9
            goto L_0x0200
        L_0x01f9:
            java.lang.String r0 = "Can't parse bitrate DSI."
            androidx.media3.common.ParserException r0 = androidx.media3.common.ParserException.createForUnsupportedContainerFeature(r0)
            throw r0
        L_0x0200:
            boolean r2 = r0.readBit()
            if (r2 == 0) goto L_0x0226
            r0.byteAlign()
            r2 = 16
            int r2 = r0.readBits(r2)
            r0.skipBytes(r2)
            r2 = 5
            int r2 = r0.readBits(r2)
            r11 = 0
        L_0x0218:
            if (r11 >= r2) goto L_0x0226
            r4 = 3
            r0.skipBits(r4)
            r9 = 8
            r0.skipBits(r9)
            int r11 = r11 + 1
            goto L_0x0218
        L_0x0226:
            r9 = 8
            r0.byteAlign()
            r2 = 1
            if (r3 != r2) goto L_0x0243
            int r2 = r0.bitsLeft()
            int r1 = r1 - r2
            int r1 = r1 / r9
            int r1 = r1 - r8
            if (r5 < r1) goto L_0x023c
            int r5 = r5 - r1
            r0.skipBytes(r5)
            goto L_0x0243
        L_0x023c:
            java.lang.String r0 = "pres_bytes is smaller than presentation bytes read."
            androidx.media3.common.ParserException r0 = androidx.media3.common.ParserException.createForUnsupportedContainerFeature(r0)
            throw r0
        L_0x0243:
            boolean r0 = r6.isChannelCoded
            if (r0 == 0) goto L_0x0261
            int r0 = r6.channelMode
            r1 = -1
            if (r0 == r1) goto L_0x024d
            goto L_0x0261
        L_0x024d:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Can't determine channel mode of presentation "
            r0.<init>(r1)
            java.lang.StringBuilder r0 = r0.append(r12)
            java.lang.String r0 = r0.toString()
            androidx.media3.common.ParserException r0 = androidx.media3.common.ParserException.createForUnsupportedContainerFeature(r0)
            throw r0
        L_0x0261:
            boolean r0 = r6.isChannelCoded
            if (r0 == 0) goto L_0x0270
            int r0 = r6.channelMode
            boolean r1 = r6.hasBackChannels
            int r2 = r6.topChannelPairs
            int r0 = getAdjustedChannelCount(r0, r1, r2)
            goto L_0x027f
        L_0x0270:
            int r0 = r6.numOfUmxObjects
            r1 = 1
            int r0 = r0 + r1
            int r1 = r6.level
            r2 = 4
            if (r1 != r2) goto L_0x027f
            r1 = 17
            if (r0 != r1) goto L_0x027f
            r0 = 21
        L_0x027f:
            if (r0 <= 0) goto L_0x02ab
            androidx.media3.common.Format$Builder r1 = new androidx.media3.common.Format$Builder
            r1.<init>()
            r2 = r19
            androidx.media3.common.Format$Builder r1 = r1.setId((java.lang.String) r2)
            java.lang.String r2 = "audio/ac4"
            androidx.media3.common.Format$Builder r1 = r1.setSampleMimeType(r2)
            androidx.media3.common.Format$Builder r0 = r1.setChannelCount(r0)
            androidx.media3.common.Format$Builder r0 = r0.setSampleRate(r7)
            r1 = r21
            androidx.media3.common.Format$Builder r0 = r0.setDrmInitData(r1)
            r1 = r20
            androidx.media3.common.Format$Builder r0 = r0.setLanguage(r1)
            androidx.media3.common.Format r0 = r0.build()
            return r0
        L_0x02ab:
            java.lang.String r0 = "Can't determine channel count of presentation."
            androidx.media3.common.ParserException r0 = androidx.media3.common.ParserException.createForUnsupportedContainerFeature(r0)
            throw r0
        L_0x02b2:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Unsupported AC-4 DSI version: "
            r0.<init>(r1)
            java.lang.StringBuilder r0 = r0.append(r3)
            java.lang.String r0 = r0.toString()
            androidx.media3.common.ParserException r0 = androidx.media3.common.ParserException.createForUnsupportedContainerFeature(r0)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.Ac4Util.parseAc4AnnexEFormat(androidx.media3.common.util.ParsableByteArray, java.lang.String, java.lang.String, androidx.media3.common.DrmInitData):androidx.media3.common.Format");
    }

    private static void parseDsiSubstream(ParsableBitArray parsableBitArray, Ac4Presentation ac4Presentation) throws ParserException {
        int readBits = parsableBitArray.readBits(5);
        parsableBitArray.skipBits(2);
        if (parsableBitArray.readBit()) {
            parsableBitArray.skipBits(5);
        }
        if (readBits >= 7 && readBits <= 10) {
            parsableBitArray.skipBit();
        }
        if (parsableBitArray.readBit()) {
            int readBits2 = parsableBitArray.readBits(3);
            if (ac4Presentation.channelMode == -1 && readBits >= 0 && readBits <= 15 && (readBits2 == 0 || readBits2 == 1)) {
                ac4Presentation.channelMode = readBits;
            }
            if (parsableBitArray.readBit()) {
                skipDsiLanguage(parsableBitArray);
            }
        }
    }

    private static void parseDsiSubstreamGroup(ParsableBitArray parsableBitArray, Ac4Presentation ac4Presentation) throws ParserException {
        parsableBitArray.skipBits(2);
        boolean readBit = parsableBitArray.readBit();
        int readBits = parsableBitArray.readBits(8);
        for (int i = 0; i < readBits; i++) {
            parsableBitArray.skipBits(2);
            if (parsableBitArray.readBit()) {
                parsableBitArray.skipBits(5);
            }
            if (readBit) {
                parsableBitArray.skipBits(24);
            } else {
                if (parsableBitArray.readBit()) {
                    if (!parsableBitArray.readBit()) {
                        parsableBitArray.skipBits(4);
                    }
                    ac4Presentation.numOfUmxObjects = parsableBitArray.readBits(6) + 1;
                }
                parsableBitArray.skipBits(4);
            }
        }
        if (parsableBitArray.readBit()) {
            parsableBitArray.skipBits(3);
            if (parsableBitArray.readBit()) {
                skipDsiLanguage(parsableBitArray);
            }
        }
    }

    private static void skipDsiLanguage(ParsableBitArray parsableBitArray) throws ParserException {
        int readBits = parsableBitArray.readBits(6);
        if (readBits < 2 || readBits > 42) {
            throw ParserException.createForUnsupportedContainerFeature(String.format("Invalid language tag bytes number: %d. Must be between 2 and 42.", new Object[]{Integer.valueOf(readBits)}));
        }
        parsableBitArray.skipBits(readBits * 8);
    }

    private static boolean skipDsiBitrate(ParsableBitArray parsableBitArray) {
        if (parsableBitArray.bitsLeft() < 66) {
            return false;
        }
        parsableBitArray.skipBits(66);
        return true;
    }

    private static int getAdjustedChannelCount(int i, boolean z, int i2) {
        int channelCountFromChannelMode = getChannelCountFromChannelMode(i);
        if (i != 11 && i != 12 && i != 13 && i != 14) {
            return channelCountFromChannelMode;
        }
        if (!z) {
            channelCountFromChannelMode -= 2;
        }
        if (i2 != 0) {
            return i2 != 1 ? channelCountFromChannelMode : channelCountFromChannelMode - 2;
        }
        return channelCountFromChannelMode - 4;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0080, code lost:
        if (r11 != 11) goto L_0x008d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0085, code lost:
        if (r11 != 11) goto L_0x008d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x008a, code lost:
        if (r11 != 8) goto L_0x008d;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static androidx.media3.extractor.Ac4Util.SyncFrameInfo parseAc4SyncframeInfo(androidx.media3.common.util.ParsableBitArray r11) {
        /*
            r0 = 16
            int r1 = r11.readBits(r0)
            int r0 = r11.readBits(r0)
            r2 = 65535(0xffff, float:9.1834E-41)
            r3 = 4
            if (r0 != r2) goto L_0x0018
            r0 = 24
            int r0 = r11.readBits(r0)
            r2 = 7
            goto L_0x0019
        L_0x0018:
            r2 = r3
        L_0x0019:
            int r0 = r0 + r2
            r2 = 44097(0xac41, float:6.1793E-41)
            if (r1 != r2) goto L_0x0021
            int r0 = r0 + 2
        L_0x0021:
            r8 = r0
            r0 = 2
            int r1 = r11.readBits(r0)
            r2 = 3
            if (r1 != r2) goto L_0x002f
            int r4 = readVariableBits(r11, r0)
            int r1 = r1 + r4
        L_0x002f:
            r5 = r1
            r1 = 10
            int r1 = r11.readBits(r1)
            boolean r4 = r11.readBit()
            if (r4 == 0) goto L_0x0045
            int r4 = r11.readBits(r2)
            if (r4 <= 0) goto L_0x0045
            r11.skipBits(r0)
        L_0x0045:
            boolean r4 = r11.readBit()
            r6 = 48000(0xbb80, float:6.7262E-41)
            r7 = 44100(0xac44, float:6.1797E-41)
            if (r4 == 0) goto L_0x0053
            r9 = r6
            goto L_0x0054
        L_0x0053:
            r9 = r7
        L_0x0054:
            int r11 = r11.readBits(r3)
            if (r9 != r7) goto L_0x0063
            r4 = 13
            if (r11 != r4) goto L_0x0063
            int[] r0 = SAMPLE_COUNT
            r11 = r0[r11]
            goto L_0x0093
        L_0x0063:
            if (r9 != r6) goto L_0x0092
            int[] r4 = SAMPLE_COUNT
            int r6 = r4.length
            if (r11 >= r6) goto L_0x0092
            r4 = r4[r11]
            int r1 = r1 % 5
            r6 = 8
            r7 = 1
            if (r1 == r7) goto L_0x0088
            r7 = 11
            if (r1 == r0) goto L_0x0083
            if (r1 == r2) goto L_0x0088
            if (r1 == r3) goto L_0x007c
            goto L_0x008d
        L_0x007c:
            if (r11 == r2) goto L_0x008f
            if (r11 == r6) goto L_0x008f
            if (r11 != r7) goto L_0x008d
            goto L_0x008f
        L_0x0083:
            if (r11 == r6) goto L_0x008f
            if (r11 != r7) goto L_0x008d
            goto L_0x008f
        L_0x0088:
            if (r11 == r2) goto L_0x008f
            if (r11 != r6) goto L_0x008d
            goto L_0x008f
        L_0x008d:
            r11 = r4
            goto L_0x0093
        L_0x008f:
            int r4 = r4 + 1
            goto L_0x008d
        L_0x0092:
            r11 = 0
        L_0x0093:
            androidx.media3.extractor.Ac4Util$SyncFrameInfo r0 = new androidx.media3.extractor.Ac4Util$SyncFrameInfo
            r6 = 2
            r10 = 0
            r4 = r0
            r7 = r9
            r9 = r11
            r4.<init>(r5, r6, r7, r8, r9)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.Ac4Util.parseAc4SyncframeInfo(androidx.media3.common.util.ParsableBitArray):androidx.media3.extractor.Ac4Util$SyncFrameInfo");
    }

    public static int parseAc4SyncframeSize(byte[] bArr, int i) {
        int i2 = 7;
        if (bArr.length < 7) {
            return -1;
        }
        byte b = ((bArr[2] & 255) << 8) | (bArr[3] & 255);
        if (b == 65535) {
            b = ((bArr[4] & 255) << 16) | ((bArr[5] & 255) << 8) | (bArr[6] & 255);
        } else {
            i2 = 4;
        }
        if (i == 44097) {
            i2 += 2;
        }
        return b + i2;
    }

    public static int parseAc4SyncframeAudioSampleCount(ByteBuffer byteBuffer) {
        byte[] bArr = new byte[16];
        int position = byteBuffer.position();
        byteBuffer.get(bArr);
        byteBuffer.position(position);
        return parseAc4SyncframeInfo(new ParsableBitArray(bArr)).sampleCount;
    }

    public static void getAc4SampleHeader(int i, ParsableByteArray parsableByteArray) {
        parsableByteArray.reset(7);
        byte[] data = parsableByteArray.getData();
        data[0] = -84;
        data[1] = SignedBytes.MAX_POWER_OF_TWO;
        data[2] = -1;
        data[3] = -1;
        data[4] = (byte) ((i >> 16) & 255);
        data[5] = (byte) ((i >> 8) & 255);
        data[6] = (byte) (i & 255);
    }

    private static int readVariableBits(ParsableBitArray parsableBitArray, int i) {
        int i2 = 0;
        while (true) {
            int readBits = i2 + parsableBitArray.readBits(i);
            if (!parsableBitArray.readBit()) {
                return readBits;
            }
            i2 = (readBits + 1) << i;
        }
    }

    private static final class Ac4Presentation {
        public int channelMode;
        public boolean hasBackChannels;
        public boolean isChannelCoded;
        public int level;
        public int numOfUmxObjects;
        public int topChannelPairs;

        private Ac4Presentation() {
            this.isChannelCoded = true;
            this.channelMode = -1;
            this.numOfUmxObjects = -1;
            this.hasBackChannels = true;
            this.topChannelPairs = 2;
            this.level = 0;
        }
    }

    private Ac4Util() {
    }
}
