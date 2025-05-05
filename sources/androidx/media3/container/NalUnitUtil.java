package androidx.media3.container;

import androidx.media3.common.ColorInfo;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.CodecSpecificDataUtil;
import com.google.common.base.Ascii;
import com.google.common.collect.ImmutableList;
import com.google.common.math.DoubleMath;
import java.math.RoundingMode;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public final class NalUnitUtil {
    public static final float[] ASPECT_RATIO_IDC_VALUES = {1.0f, 1.0f, 1.0909091f, 0.90909094f, 1.4545455f, 1.2121212f, 2.1818182f, 1.8181819f, 2.909091f, 2.4242425f, 1.6363636f, 1.3636364f, 1.939394f, 1.6161616f, 1.3333334f, 1.5f, 2.0f};
    public static final int EXTENDED_SAR = 255;
    public static final int H264_NAL_UNIT_TYPE_AUD = 9;
    public static final int H264_NAL_UNIT_TYPE_IDR = 5;
    public static final int H264_NAL_UNIT_TYPE_NON_IDR = 1;
    public static final int H264_NAL_UNIT_TYPE_PARTITION_A = 2;
    public static final int H264_NAL_UNIT_TYPE_PPS = 8;
    public static final int H264_NAL_UNIT_TYPE_PREFIX = 14;
    public static final int H264_NAL_UNIT_TYPE_SEI = 6;
    public static final int H264_NAL_UNIT_TYPE_SPS = 7;
    public static final int H265_NAL_UNIT_TYPE_AUD = 35;
    public static final int H265_NAL_UNIT_TYPE_BLA_W_LP = 16;
    public static final int H265_NAL_UNIT_TYPE_CRA = 21;
    public static final int H265_NAL_UNIT_TYPE_PPS = 34;
    public static final int H265_NAL_UNIT_TYPE_PREFIX_SEI = 39;
    public static final int H265_NAL_UNIT_TYPE_RASL_R = 9;
    public static final int H265_NAL_UNIT_TYPE_SPS = 33;
    public static final int H265_NAL_UNIT_TYPE_SUFFIX_SEI = 40;
    public static final int H265_NAL_UNIT_TYPE_VPS = 32;
    private static final int INVALID_ID = -1;
    public static final byte[] NAL_START_CODE = {0, 0, 0, 1};
    @Deprecated
    public static final int NAL_UNIT_TYPE_AUD = 9;
    @Deprecated
    public static final int NAL_UNIT_TYPE_IDR = 5;
    @Deprecated
    public static final int NAL_UNIT_TYPE_NON_IDR = 1;
    @Deprecated
    public static final int NAL_UNIT_TYPE_PARTITION_A = 2;
    @Deprecated
    public static final int NAL_UNIT_TYPE_PPS = 8;
    @Deprecated
    public static final int NAL_UNIT_TYPE_PREFIX = 14;
    @Deprecated
    public static final int NAL_UNIT_TYPE_SEI = 6;
    @Deprecated
    public static final int NAL_UNIT_TYPE_SPS = 7;
    private static final String TAG = "NalUnitUtil";
    private static int[] scratchEscapePositions = new int[10];
    private static final Object scratchEscapePositionsLock = new Object();

    private static int applyConformanceWindowToHeight(int i, int i2, int i3, int i4) {
        int i5 = 1;
        if (i2 == 1) {
            i5 = 2;
        }
        return i - (i5 * (i3 + i4));
    }

    private static int applyConformanceWindowToWidth(int i, int i2, int i3, int i4) {
        int i5 = 2;
        if (!(i2 == 1 || i2 == 2)) {
            i5 = 1;
        }
        return i - (i5 * (i3 + i4));
    }

    public static boolean isH264NalUnitDependedOn(byte b) {
        if (((b & 96) >> 5) != 0) {
            return true;
        }
        byte b2 = b & Ascii.US;
        return (b2 == 1 || b2 == 9 || b2 == 14) ? false : true;
    }

    public static final class SpsData {
        public final int bitDepthChromaMinus8;
        public final int bitDepthLumaMinus8;
        public final int colorRange;
        public final int colorSpace;
        public final int colorTransfer;
        public final int constraintsFlagsAndReservedZero2Bits;
        public final boolean deltaPicOrderAlwaysZeroFlag;
        public final boolean frameMbsOnlyFlag;
        public final int frameNumLength;
        public final int height;
        public final int levelIdc;
        public final int maxNumRefFrames;
        public final int maxNumReorderFrames;
        public final int picOrderCntLsbLength;
        public final int picOrderCountType;
        public final float pixelWidthHeightRatio;
        public final int profileIdc;
        public final boolean separateColorPlaneFlag;
        public final int seqParameterSetId;
        public final int width;

        public SpsData(int i, int i2, int i3, int i4, int i5, int i6, int i7, float f, int i8, int i9, boolean z, boolean z2, int i10, int i11, int i12, boolean z3, int i13, int i14, int i15, int i16) {
            this.profileIdc = i;
            this.constraintsFlagsAndReservedZero2Bits = i2;
            this.levelIdc = i3;
            this.seqParameterSetId = i4;
            this.maxNumRefFrames = i5;
            this.width = i6;
            this.height = i7;
            this.pixelWidthHeightRatio = f;
            this.bitDepthLumaMinus8 = i8;
            this.bitDepthChromaMinus8 = i9;
            this.separateColorPlaneFlag = z;
            this.frameMbsOnlyFlag = z2;
            this.frameNumLength = i10;
            this.picOrderCountType = i11;
            this.picOrderCntLsbLength = i12;
            this.deltaPicOrderAlwaysZeroFlag = z3;
            this.colorSpace = i13;
            this.colorRange = i14;
            this.colorTransfer = i15;
            this.maxNumReorderFrames = i16;
        }
    }

    public static final class H265NalHeader {
        public final int layerId;
        public final int nalUnitType;
        public final int temporalId;

        public H265NalHeader(int i, int i2, int i3) {
            this.nalUnitType = i;
            this.layerId = i2;
            this.temporalId = i3;
        }
    }

    public static final class H265LayerInfo {
        public final int layerIdInVps;
        public final int viewId;

        public H265LayerInfo(int i, int i2) {
            this.layerIdInVps = i;
            this.viewId = i2;
        }
    }

    public static final class H265ProfileTierLevel {
        public final int[] constraintBytes;
        public final int generalLevelIdc;
        public final int generalProfileCompatibilityFlags;
        public final int generalProfileIdc;
        public final int generalProfileSpace;
        public final boolean generalTierFlag;

        public H265ProfileTierLevel(int i, boolean z, int i2, int i3, int[] iArr, int i4) {
            this.generalProfileSpace = i;
            this.generalTierFlag = z;
            this.generalProfileIdc = i2;
            this.generalProfileCompatibilityFlags = i3;
            this.constraintBytes = iArr;
            this.generalLevelIdc = i4;
        }
    }

    public static final class H265ProfileTierLevelsAndIndices {
        public final int[] indices;
        public final ImmutableList<H265ProfileTierLevel> profileTierLevels;

        public H265ProfileTierLevelsAndIndices(List<H265ProfileTierLevel> list, int[] iArr) {
            this.profileTierLevels = ImmutableList.copyOf(list);
            this.indices = iArr;
        }
    }

    public static final class H265RepFormat {
        public final int bitDepthChromaMinus8;
        public final int bitDepthLumaMinus8;
        public final int chromaFormatIdc;
        public final int height;
        public final int width;

        public H265RepFormat(int i, int i2, int i3, int i4, int i5) {
            this.chromaFormatIdc = i;
            this.bitDepthLumaMinus8 = i2;
            this.bitDepthChromaMinus8 = i3;
            this.width = i4;
            this.height = i5;
        }
    }

    public static final class H265RepFormatsAndIndices {
        public final int[] indices;
        public final ImmutableList<H265RepFormat> repFormats;

        public H265RepFormatsAndIndices(List<H265RepFormat> list, int[] iArr) {
            this.repFormats = ImmutableList.copyOf(list);
            this.indices = iArr;
        }
    }

    public static final class H265VideoSignalInfo {
        public final int colorRange;
        public final int colorSpace;
        public final int colorTransfer;

        public H265VideoSignalInfo(int i, int i2, int i3) {
            this.colorSpace = i;
            this.colorRange = i2;
            this.colorTransfer = i3;
        }
    }

    public static final class H265VideoSignalInfosAndIndices {
        public final int[] indices;
        public final ImmutableList<H265VideoSignalInfo> videoSignalInfos;

        public H265VideoSignalInfosAndIndices(List<H265VideoSignalInfo> list, int[] iArr) {
            this.videoSignalInfos = ImmutableList.copyOf(list);
            this.indices = iArr;
        }
    }

    public static final class H265VpsData {
        public final ImmutableList<H265LayerInfo> layerInfos;
        public final H265NalHeader nalHeader;
        public final H265ProfileTierLevelsAndIndices profileTierLevelsAndIndices;
        public final H265RepFormatsAndIndices repFormatsAndIndices;
        public final H265VideoSignalInfosAndIndices videoSignalInfosAndIndices;

        public H265VpsData(H265NalHeader h265NalHeader, List<H265LayerInfo> list, H265ProfileTierLevelsAndIndices h265ProfileTierLevelsAndIndices, H265RepFormatsAndIndices h265RepFormatsAndIndices, H265VideoSignalInfosAndIndices h265VideoSignalInfosAndIndices) {
            this.nalHeader = h265NalHeader;
            this.layerInfos = list != null ? ImmutableList.copyOf(list) : ImmutableList.of();
            this.profileTierLevelsAndIndices = h265ProfileTierLevelsAndIndices;
            this.repFormatsAndIndices = h265RepFormatsAndIndices;
            this.videoSignalInfosAndIndices = h265VideoSignalInfosAndIndices;
        }
    }

    public static final class H265SpsData {
        public final int bitDepthChromaMinus8;
        public final int bitDepthLumaMinus8;
        public final int chromaFormatIdc;
        public final int colorRange;
        public final int colorSpace;
        public final int colorTransfer;
        public final int height;
        public final int maxNumReorderPics;
        public final H265NalHeader nalHeader;
        public final float pixelWidthHeightRatio;
        public final H265ProfileTierLevel profileTierLevel;
        public final int seqParameterSetId;
        public final int width;

        public H265SpsData(H265NalHeader h265NalHeader, H265ProfileTierLevel h265ProfileTierLevel, int i, int i2, int i3, int i4, int i5, int i6, float f, int i7, int i8, int i9, int i10) {
            this.nalHeader = h265NalHeader;
            this.profileTierLevel = h265ProfileTierLevel;
            this.chromaFormatIdc = i;
            this.bitDepthLumaMinus8 = i2;
            this.bitDepthChromaMinus8 = i3;
            this.seqParameterSetId = i4;
            this.width = i5;
            this.height = i6;
            this.pixelWidthHeightRatio = f;
            this.maxNumReorderPics = i7;
            this.colorSpace = i8;
            this.colorRange = i9;
            this.colorTransfer = i10;
        }
    }

    public static final class PpsData {
        public final boolean bottomFieldPicOrderInFramePresentFlag;
        public final int picParameterSetId;
        public final int seqParameterSetId;

        public PpsData(int i, int i2, boolean z) {
            this.picParameterSetId = i;
            this.seqParameterSetId = i2;
            this.bottomFieldPicOrderInFramePresentFlag = z;
        }
    }

    public static final class H265Sei3dRefDisplayInfoData {
        public final int exponentRefDisplayWidth;
        public final int exponentRefViewingDist;
        public final int leftViewId;
        public final int mantissaRefDisplayWidth;
        public final int mantissaRefViewingDist;
        public final int numRefDisplays;
        public final int precRefDisplayWidth;
        public final int precRefViewingDist;
        public final int rightViewId;

        public H265Sei3dRefDisplayInfoData(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
            this.precRefDisplayWidth = i;
            this.precRefViewingDist = i2;
            this.numRefDisplays = i3;
            this.leftViewId = i4;
            this.rightViewId = i5;
            this.exponentRefDisplayWidth = i6;
            this.mantissaRefDisplayWidth = i7;
            this.exponentRefViewingDist = i8;
            this.mantissaRefViewingDist = i9;
        }
    }

    public static int unescapeStream(byte[] bArr, int i) {
        int i2;
        synchronized (scratchEscapePositionsLock) {
            int i3 = 0;
            int i4 = 0;
            while (i3 < i) {
                try {
                    i3 = findNextUnescapeIndex(bArr, i3, i);
                    if (i3 < i) {
                        int[] iArr = scratchEscapePositions;
                        if (iArr.length <= i4) {
                            scratchEscapePositions = Arrays.copyOf(iArr, iArr.length * 2);
                        }
                        scratchEscapePositions[i4] = i3;
                        i3 += 3;
                        i4++;
                    }
                } finally {
                }
            }
            i2 = i - i4;
            int i5 = 0;
            int i6 = 0;
            for (int i7 = 0; i7 < i4; i7++) {
                int i8 = scratchEscapePositions[i7] - i6;
                System.arraycopy(bArr, i6, bArr, i5, i8);
                int i9 = i5 + i8;
                int i10 = i9 + 1;
                bArr[i9] = 0;
                i5 = i10 + 1;
                bArr[i10] = 0;
                i6 += i8 + 3;
            }
            System.arraycopy(bArr, i6, bArr, i5, i2 - i5);
        }
        return i2;
    }

    public static void discardToSps(ByteBuffer byteBuffer) {
        int position = byteBuffer.position();
        int i = 0;
        int i2 = 0;
        while (true) {
            int i3 = i + 1;
            if (i3 < position) {
                byte b = byteBuffer.get(i) & 255;
                if (i2 == 3) {
                    if (b == 1 && (byteBuffer.get(i3) & Ascii.US) == 7) {
                        ByteBuffer duplicate = byteBuffer.duplicate();
                        duplicate.position(i - 3);
                        duplicate.limit(position);
                        byteBuffer.position(0);
                        byteBuffer.put(duplicate);
                        return;
                    }
                } else if (b == 0) {
                    i2++;
                }
                if (b != 0) {
                    i2 = 0;
                }
                i = i3;
            } else {
                byteBuffer.clear();
                return;
            }
        }
    }

    public static boolean isNalUnitSei(String str, byte b) {
        if (MimeTypes.VIDEO_H264.equals(str) && (b & Ascii.US) == 6) {
            return true;
        }
        if (!MimeTypes.VIDEO_H265.equals(str) || ((b & 126) >> 1) != 39) {
            return false;
        }
        return true;
    }

    public static boolean isNalUnitSei(Format format, byte b) {
        if ((Objects.equals(format.sampleMimeType, MimeTypes.VIDEO_H264) || MimeTypes.containsCodecsCorrespondingToMimeType(format.codecs, MimeTypes.VIDEO_H264)) && (b & Ascii.US) == 6) {
            return true;
        }
        if ((Objects.equals(format.sampleMimeType, MimeTypes.VIDEO_H265) || MimeTypes.containsCodecsCorrespondingToMimeType(format.codecs, MimeTypes.VIDEO_H265)) && ((b & 126) >> 1) == 39) {
            return true;
        }
        return false;
    }

    public static int getNalUnitType(byte[] bArr, int i) {
        return bArr[i + 3] & Ascii.US;
    }

    public static int getH265NalUnitType(byte[] bArr, int i) {
        return (bArr[i + 3] & 126) >> 1;
    }

    public static SpsData parseSpsNalUnit(byte[] bArr, int i, int i2) {
        return parseSpsNalUnitPayload(bArr, i + 1, i2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:122:0x0228  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00ec  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00fa  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0157  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static androidx.media3.container.NalUnitUtil.SpsData parseSpsNalUnitPayload(byte[] r32, int r33, int r34) {
        /*
            androidx.media3.container.ParsableNalUnitBitArray r0 = new androidx.media3.container.ParsableNalUnitBitArray
            r1 = r32
            r2 = r33
            r3 = r34
            r0.<init>(r1, r2, r3)
            r1 = 8
            int r3 = r0.readBits(r1)
            int r4 = r0.readBits(r1)
            int r5 = r0.readBits(r1)
            int r6 = r0.readUnsignedExpGolombCodedInt()
            r2 = 86
            r7 = 44
            r8 = 244(0xf4, float:3.42E-43)
            r9 = 122(0x7a, float:1.71E-43)
            r10 = 110(0x6e, float:1.54E-43)
            r11 = 3
            r12 = 16
            r13 = 1
            r15 = 100
            if (r3 == r15) goto L_0x0052
            if (r3 == r10) goto L_0x0052
            if (r3 == r9) goto L_0x0052
            if (r3 == r8) goto L_0x0052
            if (r3 == r7) goto L_0x0052
            r14 = 83
            if (r3 == r14) goto L_0x0052
            if (r3 == r2) goto L_0x0052
            r14 = 118(0x76, float:1.65E-43)
            if (r3 == r14) goto L_0x0052
            r14 = 128(0x80, float:1.794E-43)
            if (r3 == r14) goto L_0x0052
            r14 = 138(0x8a, float:1.93E-43)
            if (r3 != r14) goto L_0x004a
            goto L_0x0052
        L_0x004a:
            r14 = r13
            r16 = 0
            r17 = 0
            r18 = 0
            goto L_0x008f
        L_0x0052:
            int r14 = r0.readUnsignedExpGolombCodedInt()
            if (r14 != r11) goto L_0x005d
            boolean r16 = r0.readBit()
            goto L_0x005f
        L_0x005d:
            r16 = 0
        L_0x005f:
            int r17 = r0.readUnsignedExpGolombCodedInt()
            int r18 = r0.readUnsignedExpGolombCodedInt()
            r0.skipBit()
            boolean r19 = r0.readBit()
            if (r19 == 0) goto L_0x008f
            if (r14 == r11) goto L_0x0073
            goto L_0x0077
        L_0x0073:
            r19 = 12
            r1 = r19
        L_0x0077:
            r8 = 0
        L_0x0078:
            if (r8 >= r1) goto L_0x008f
            boolean r19 = r0.readBit()
            if (r19 == 0) goto L_0x008a
            r9 = 6
            if (r8 >= r9) goto L_0x0085
            r9 = r12
            goto L_0x0087
        L_0x0085:
            r9 = 64
        L_0x0087:
            skipScalingList(r0, r9)
        L_0x008a:
            int r8 = r8 + 1
            r9 = 122(0x7a, float:1.71E-43)
            goto L_0x0078
        L_0x008f:
            int r1 = r0.readUnsignedExpGolombCodedInt()
            int r1 = r1 + 4
            int r9 = r0.readUnsignedExpGolombCodedInt()
            if (r9 != 0) goto L_0x00a6
            int r8 = r0.readUnsignedExpGolombCodedInt()
            int r8 = r8 + 4
            r21 = r3
            r24 = r8
            goto L_0x00d0
        L_0x00a6:
            if (r9 != r13) goto L_0x00cc
            boolean r8 = r0.readBit()
            r0.readSignedExpGolombCodedInt()
            r0.readSignedExpGolombCodedInt()
            int r10 = r0.readUnsignedExpGolombCodedInt()
            r21 = r3
            long r2 = (long) r10
            r24 = r8
            r10 = 0
        L_0x00bc:
            long r7 = (long) r10
            int r7 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
            if (r7 >= 0) goto L_0x00c7
            r0.readUnsignedExpGolombCodedInt()
            int r10 = r10 + 1
            goto L_0x00bc
        L_0x00c7:
            r25 = r24
            r24 = 0
            goto L_0x00d2
        L_0x00cc:
            r21 = r3
            r24 = 0
        L_0x00d0:
            r25 = 0
        L_0x00d2:
            int r7 = r0.readUnsignedExpGolombCodedInt()
            r0.skipBit()
            int r2 = r0.readUnsignedExpGolombCodedInt()
            int r2 = r2 + r13
            int r3 = r0.readUnsignedExpGolombCodedInt()
            int r3 = r3 + r13
            boolean r26 = r0.readBit()
            int r8 = 2 - r26
            int r8 = r8 * r3
            if (r26 != 0) goto L_0x00ef
            r0.skipBit()
        L_0x00ef:
            r0.skipBit()
            int r2 = r2 * r12
            int r8 = r8 * r12
            boolean r3 = r0.readBit()
            if (r3 == 0) goto L_0x012c
            int r3 = r0.readUnsignedExpGolombCodedInt()
            int r27 = r0.readUnsignedExpGolombCodedInt()
            int r28 = r0.readUnsignedExpGolombCodedInt()
            int r29 = r0.readUnsignedExpGolombCodedInt()
            if (r14 != 0) goto L_0x0111
            int r14 = 2 - r26
            r30 = r13
            goto L_0x0121
        L_0x0111:
            if (r14 != r11) goto L_0x0116
            r30 = r13
            goto L_0x0118
        L_0x0116:
            r30 = 2
        L_0x0118:
            if (r14 != r13) goto L_0x011c
            r14 = 2
            goto L_0x011d
        L_0x011c:
            r14 = r13
        L_0x011d:
            int r31 = 2 - r26
            int r14 = r14 * r31
        L_0x0121:
            int r3 = r3 + r27
            int r3 = r3 * r30
            int r2 = r2 - r3
            int r28 = r28 + r29
            int r28 = r28 * r14
            int r8 = r8 - r28
        L_0x012c:
            r14 = r8
            r3 = r21
            r8 = r2
            r2 = 44
            if (r3 == r2) goto L_0x0146
            r2 = 86
            if (r3 == r2) goto L_0x0146
            if (r3 == r15) goto L_0x0146
            r2 = 110(0x6e, float:1.54E-43)
            if (r3 == r2) goto L_0x0146
            r2 = 122(0x7a, float:1.71E-43)
            if (r3 == r2) goto L_0x0146
            r2 = 244(0xf4, float:3.42E-43)
            if (r3 != r2) goto L_0x014c
        L_0x0146:
            r2 = r4 & 16
            if (r2 == 0) goto L_0x014c
            r2 = 0
            goto L_0x014d
        L_0x014c:
            r2 = r12
        L_0x014d:
            boolean r15 = r0.readBit()
            r19 = -1
            r20 = 1065353216(0x3f800000, float:1.0)
            if (r15 == 0) goto L_0x0228
            boolean r15 = r0.readBit()
            if (r15 == 0) goto L_0x0194
            r15 = 8
            int r10 = r0.readBits(r15)
            r15 = 255(0xff, float:3.57E-43)
            if (r10 != r15) goto L_0x0178
            int r10 = r0.readBits(r12)
            int r12 = r0.readBits(r12)
            if (r10 == 0) goto L_0x0194
            if (r12 == 0) goto L_0x0194
            float r10 = (float) r10
            float r12 = (float) r12
            float r20 = r10 / r12
            goto L_0x0194
        L_0x0178:
            float[] r12 = ASPECT_RATIO_IDC_VALUES
            int r15 = r12.length
            if (r10 >= r15) goto L_0x0180
            r20 = r12[r10]
            goto L_0x0194
        L_0x0180:
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            java.lang.String r15 = "Unexpected aspect_ratio_idc value: "
            r12.<init>(r15)
            java.lang.StringBuilder r10 = r12.append(r10)
            java.lang.String r10 = r10.toString()
            java.lang.String r12 = "NalUnitUtil"
            androidx.media3.common.util.Log.w(r12, r10)
        L_0x0194:
            boolean r10 = r0.readBit()
            if (r10 == 0) goto L_0x019d
            r0.skipBit()
        L_0x019d:
            boolean r10 = r0.readBit()
            if (r10 == 0) goto L_0x01cd
            r0.skipBits(r11)
            boolean r10 = r0.readBit()
            if (r10 == 0) goto L_0x01ad
            goto L_0x01ae
        L_0x01ad:
            r13 = 2
        L_0x01ae:
            boolean r10 = r0.readBit()
            if (r10 == 0) goto L_0x01ca
            r10 = 8
            int r11 = r0.readBits(r10)
            int r12 = r0.readBits(r10)
            r0.skipBits(r10)
            int r19 = androidx.media3.common.ColorInfo.isoColorPrimariesToColorSpace(r11)
            int r10 = androidx.media3.common.ColorInfo.isoTransferCharacteristicsToColorTransfer(r12)
            goto L_0x01d0
        L_0x01ca:
            r10 = r19
            goto L_0x01d0
        L_0x01cd:
            r10 = r19
            r13 = r10
        L_0x01d0:
            boolean r11 = r0.readBit()
            if (r11 == 0) goto L_0x01dc
            r0.readUnsignedExpGolombCodedInt()
            r0.readUnsignedExpGolombCodedInt()
        L_0x01dc:
            boolean r11 = r0.readBit()
            if (r11 == 0) goto L_0x01e7
            r11 = 65
            r0.skipBits(r11)
        L_0x01e7:
            boolean r11 = r0.readBit()
            if (r11 == 0) goto L_0x01f0
            skipHrdParameters(r0)
        L_0x01f0:
            boolean r12 = r0.readBit()
            if (r12 == 0) goto L_0x01f9
            skipHrdParameters(r0)
        L_0x01f9:
            if (r11 != 0) goto L_0x01fd
            if (r12 == 0) goto L_0x0200
        L_0x01fd:
            r0.skipBit()
        L_0x0200:
            r0.skipBit()
            boolean r11 = r0.readBit()
            if (r11 == 0) goto L_0x021f
            r0.skipBit()
            r0.readUnsignedExpGolombCodedInt()
            r0.readUnsignedExpGolombCodedInt()
            r0.readUnsignedExpGolombCodedInt()
            r0.readUnsignedExpGolombCodedInt()
            int r2 = r0.readUnsignedExpGolombCodedInt()
            r0.readUnsignedExpGolombCodedInt()
        L_0x021f:
            r22 = r2
            r21 = r10
            r10 = r20
            r20 = r13
            goto L_0x0230
        L_0x0228:
            r22 = r2
            r21 = r19
            r10 = r20
            r20 = r21
        L_0x0230:
            androidx.media3.container.NalUnitUtil$SpsData r0 = new androidx.media3.container.NalUnitUtil$SpsData
            r2 = r0
            r23 = r9
            r9 = r14
            r11 = r17
            r12 = r18
            r13 = r16
            r14 = r26
            r15 = r1
            r16 = r23
            r17 = r24
            r18 = r25
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.container.NalUnitUtil.parseSpsNalUnitPayload(byte[], int, int):androidx.media3.container.NalUnitUtil$SpsData");
    }

    public static H265VpsData parseH265VpsNalUnit(byte[] bArr, int i, int i2) {
        ParsableNalUnitBitArray parsableNalUnitBitArray = new ParsableNalUnitBitArray(bArr, i, i2);
        return parseH265VpsNalUnitPayload(parsableNalUnitBitArray, parseH265NalHeader(parsableNalUnitBitArray));
    }

    private static H265NalHeader parseH265NalHeader(ParsableNalUnitBitArray parsableNalUnitBitArray) {
        parsableNalUnitBitArray.skipBit();
        return new H265NalHeader(parsableNalUnitBitArray.readBits(6), parsableNalUnitBitArray.readBits(6), parsableNalUnitBitArray.readBits(3) - 1);
    }

    /* JADX WARNING: Removed duplicated region for block: B:272:0x051c  */
    /* JADX WARNING: Removed duplicated region for block: B:383:0x0531 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static androidx.media3.container.NalUnitUtil.H265VpsData parseH265VpsNalUnitPayload(androidx.media3.container.ParsableNalUnitBitArray r36, androidx.media3.container.NalUnitUtil.H265NalHeader r37) {
        /*
            r0 = r36
            r1 = 4
            r0.skipBits(r1)
            boolean r2 = r36.readBit()
            boolean r3 = r36.readBit()
            r4 = 6
            int r5 = r0.readBits(r4)
            r6 = 1
            int r5 = r5 + r6
            r7 = 3
            int r8 = r0.readBits(r7)
            r9 = 17
            r0.skipBits(r9)
            r9 = 0
            androidx.media3.container.NalUnitUtil$H265ProfileTierLevel r10 = parseH265ProfileTierLevel(r0, r6, r8, r9)
            boolean r11 = r36.readBit()
            r12 = 0
            if (r11 == 0) goto L_0x002d
            r11 = r12
            goto L_0x002e
        L_0x002d:
            r11 = r8
        L_0x002e:
            if (r11 > r8) goto L_0x003c
            r36.readUnsignedExpGolombCodedInt()
            r36.readUnsignedExpGolombCodedInt()
            r36.readUnsignedExpGolombCodedInt()
            int r11 = r11 + 1
            goto L_0x002e
        L_0x003c:
            int r11 = r0.readBits(r4)
            int r13 = r36.readUnsignedExpGolombCodedInt()
            int r13 = r13 + r6
            com.google.common.collect.ImmutableList r14 = com.google.common.collect.ImmutableList.of(r10)
            androidx.media3.container.NalUnitUtil$H265ProfileTierLevelsAndIndices r15 = new androidx.media3.container.NalUnitUtil$H265ProfileTierLevelsAndIndices
            int[] r9 = new int[r6]
            r15.<init>(r14, r9)
            r9 = 2
            if (r5 < r9) goto L_0x0057
            if (r13 < r9) goto L_0x0057
            r14 = r6
            goto L_0x0058
        L_0x0057:
            r14 = r12
        L_0x0058:
            if (r2 == 0) goto L_0x005e
            if (r3 == 0) goto L_0x005e
            r2 = r6
            goto L_0x005f
        L_0x005e:
            r2 = r12
        L_0x005f:
            int r3 = r11 + 1
            if (r3 < r5) goto L_0x0066
            r17 = r6
            goto L_0x0068
        L_0x0066:
            r17 = r12
        L_0x0068:
            if (r14 == 0) goto L_0x05e0
            if (r2 == 0) goto L_0x05e0
            if (r17 != 0) goto L_0x0070
            goto L_0x05e0
        L_0x0070:
            int[] r2 = new int[]{r13, r3}
            java.lang.Class r14 = java.lang.Integer.TYPE
            java.lang.Object r2 = java.lang.reflect.Array.newInstance(r14, r2)
            int[][] r2 = (int[][]) r2
            int[] r14 = new int[r13]
            int[] r9 = new int[r13]
            r18 = r2[r12]
            r18[r12] = r12
            r14[r12] = r6
            r9[r12] = r12
            r1 = r6
        L_0x0089:
            if (r1 >= r13) goto L_0x00a9
            r4 = r12
            r20 = r4
        L_0x008e:
            if (r4 > r11) goto L_0x00a5
            boolean r21 = r36.readBit()
            if (r21 == 0) goto L_0x00a0
            r21 = r2[r1]
            int r22 = r20 + 1
            r21[r20] = r4
            r9[r1] = r4
            r20 = r22
        L_0x00a0:
            r14[r1] = r20
            int r4 = r4 + 1
            goto L_0x008e
        L_0x00a5:
            int r1 = r1 + 1
            r4 = 6
            goto L_0x0089
        L_0x00a9:
            boolean r1 = r36.readBit()
            if (r1 == 0) goto L_0x00da
            r1 = 64
            r0.skipBits(r1)
            boolean r1 = r36.readBit()
            if (r1 == 0) goto L_0x00bd
            r36.readUnsignedExpGolombCodedInt()
        L_0x00bd:
            int r1 = r36.readUnsignedExpGolombCodedInt()
            r4 = r12
        L_0x00c2:
            if (r4 >= r1) goto L_0x00da
            r36.readUnsignedExpGolombCodedInt()
            if (r4 == 0) goto L_0x00d2
            boolean r20 = r36.readBit()
            if (r20 == 0) goto L_0x00d0
            goto L_0x00d2
        L_0x00d0:
            r7 = r12
            goto L_0x00d3
        L_0x00d2:
            r7 = r6
        L_0x00d3:
            skipH265HrdParameters(r0, r7, r8)
            int r4 = r4 + 1
            r7 = 3
            goto L_0x00c2
        L_0x00da:
            boolean r1 = r36.readBit()
            if (r1 != 0) goto L_0x00f2
            androidx.media3.container.NalUnitUtil$H265VpsData r0 = new androidx.media3.container.NalUnitUtil$H265VpsData
            r17 = 0
            r19 = 0
            r20 = 0
            r1 = r15
            r15 = r0
            r16 = r37
            r18 = r1
            r15.<init>(r16, r17, r18, r19, r20)
            return r0
        L_0x00f2:
            r1 = r15
            r36.byteAlign()
            androidx.media3.container.NalUnitUtil$H265ProfileTierLevel r4 = parseH265ProfileTierLevel(r0, r12, r8, r10)
            boolean r7 = r36.readBit()
            r15 = 16
            boolean[] r12 = new boolean[r15]
            r23 = r9
            r6 = 0
            r9 = 0
        L_0x0106:
            if (r6 >= r15) goto L_0x0115
            boolean r24 = r36.readBit()
            r12[r6] = r24
            if (r24 == 0) goto L_0x0112
            int r9 = r9 + 1
        L_0x0112:
            int r6 = r6 + 1
            goto L_0x0106
        L_0x0115:
            if (r9 == 0) goto L_0x05cd
            r6 = 1
            boolean r24 = r12[r6]
            if (r24 != 0) goto L_0x011e
            goto L_0x05cd
        L_0x011e:
            int[] r6 = new int[r9]
            r15 = 0
        L_0x0121:
            r25 = r4
            int r4 = r9 - r7
            if (r15 >= r4) goto L_0x0133
            r4 = 3
            int r26 = r0.readBits(r4)
            r6[r15] = r26
            int r15 = r15 + 1
            r4 = r25
            goto L_0x0121
        L_0x0133:
            int r4 = r9 + 1
            int[] r4 = new int[r4]
            if (r7 == 0) goto L_0x015b
            r15 = 1
        L_0x013a:
            if (r15 >= r9) goto L_0x0155
            r26 = r10
            r10 = 0
        L_0x013f:
            if (r10 >= r15) goto L_0x0150
            r27 = r4[r15]
            r28 = r6[r10]
            r22 = 1
            int r28 = r28 + 1
            int r27 = r27 + r28
            r4[r15] = r27
            int r10 = r10 + 1
            goto L_0x013f
        L_0x0150:
            int r15 = r15 + 1
            r10 = r26
            goto L_0x013a
        L_0x0155:
            r26 = r10
            r10 = 6
            r4[r9] = r10
            goto L_0x015d
        L_0x015b:
            r26 = r10
        L_0x015d:
            int[] r10 = new int[]{r5, r9}
            java.lang.Class r15 = java.lang.Integer.TYPE
            java.lang.Object r10 = java.lang.reflect.Array.newInstance(r15, r10)
            int[][] r10 = (int[][]) r10
            int[] r15 = new int[r5]
            r21 = 0
            r15[r21] = r21
            boolean r27 = r36.readBit()
            r28 = r2
            r2 = 1
        L_0x0176:
            if (r2 >= r5) goto L_0x01cb
            if (r27 == 0) goto L_0x0184
            r29 = r14
            r14 = 6
            int r19 = r0.readBits(r14)
            r15[r2] = r19
            goto L_0x0189
        L_0x0184:
            r29 = r14
            r14 = 6
            r15[r2] = r2
        L_0x0189:
            if (r7 != 0) goto L_0x01a6
            r14 = 0
        L_0x018c:
            if (r14 >= r9) goto L_0x01a3
            r30 = r10[r2]
            r31 = r6[r14]
            r32 = r6
            r22 = 1
            int r6 = r31 + 1
            int r6 = r0.readBits(r6)
            r30[r14] = r6
            int r14 = r14 + 1
            r6 = r32
            goto L_0x018c
        L_0x01a3:
            r32 = r6
            goto L_0x01c4
        L_0x01a6:
            r32 = r6
            r6 = 0
        L_0x01a9:
            if (r6 >= r9) goto L_0x01c4
            r14 = r10[r2]
            r30 = r15[r2]
            int r31 = r6 + 1
            r33 = r4[r31]
            r22 = 1
            int r33 = r22 << r33
            int r33 = r33 + -1
            r30 = r30 & r33
            r33 = r4[r6]
            int r30 = r30 >> r33
            r14[r6] = r30
            r6 = r31
            goto L_0x01a9
        L_0x01c4:
            int r2 = r2 + 1
            r14 = r29
            r6 = r32
            goto L_0x0176
        L_0x01cb:
            r29 = r14
            int[] r2 = new int[r3]
            r4 = 0
            r6 = 1
        L_0x01d1:
            r7 = -1
            if (r4 >= r5) goto L_0x020e
            r9 = r15[r4]
            r2[r9] = r7
            r7 = 0
            r9 = 0
        L_0x01da:
            r14 = 16
            if (r7 >= r14) goto L_0x01f2
            boolean r19 = r12[r7]
            if (r19 == 0) goto L_0x01ef
            r14 = 1
            if (r7 != r14) goto L_0x01ed
            r14 = r15[r4]
            r19 = r10[r4]
            r19 = r19[r9]
            r2[r14] = r19
        L_0x01ed:
            int r9 = r9 + 1
        L_0x01ef:
            int r7 = r7 + 1
            goto L_0x01da
        L_0x01f2:
            if (r4 <= 0) goto L_0x020b
            r7 = 0
        L_0x01f5:
            if (r7 >= r4) goto L_0x0206
            r9 = r15[r4]
            r9 = r2[r9]
            r14 = r15[r7]
            r14 = r2[r14]
            if (r9 != r14) goto L_0x0203
            r7 = 0
            goto L_0x0207
        L_0x0203:
            int r7 = r7 + 1
            goto L_0x01f5
        L_0x0206:
            r7 = 1
        L_0x0207:
            if (r7 == 0) goto L_0x020b
            int r6 = r6 + 1
        L_0x020b:
            int r4 = r4 + 1
            goto L_0x01d1
        L_0x020e:
            r4 = 4
            int r4 = r0.readBits(r4)
            r9 = 2
            if (r6 < r9) goto L_0x05ba
            if (r4 != 0) goto L_0x021a
            goto L_0x05ba
        L_0x021a:
            int[] r9 = new int[r6]
            r10 = 0
        L_0x021d:
            if (r10 >= r6) goto L_0x0228
            int r12 = r0.readBits(r4)
            r9[r10] = r12
            int r10 = r10 + 1
            goto L_0x021d
        L_0x0228:
            int[] r4 = new int[r3]
            r10 = 0
        L_0x022b:
            if (r10 >= r5) goto L_0x0238
            r12 = r15[r10]
            int r12 = java.lang.Math.min(r12, r11)
            r4[r12] = r10
            int r10 = r10 + 1
            goto L_0x022b
        L_0x0238:
            com.google.common.collect.ImmutableList$Builder r10 = com.google.common.collect.ImmutableList.builder()
            r12 = 0
        L_0x023d:
            if (r12 > r11) goto L_0x0261
            r14 = r2[r12]
            r18 = 1
            int r7 = r6 + -1
            int r7 = java.lang.Math.min(r14, r7)
            if (r7 < 0) goto L_0x024e
            r7 = r9[r7]
            goto L_0x024f
        L_0x024e:
            r7 = -1
        L_0x024f:
            androidx.media3.container.NalUnitUtil$H265LayerInfo r14 = new androidx.media3.container.NalUnitUtil$H265LayerInfo
            r18 = r2
            r2 = r4[r12]
            r14.<init>(r2, r7)
            r10.add((java.lang.Object) r14)
            int r12 = r12 + 1
            r2 = r18
            r7 = -1
            goto L_0x023d
        L_0x0261:
            com.google.common.collect.ImmutableList r2 = r10.build()
            r4 = 0
            java.lang.Object r6 = r2.get(r4)
            androidx.media3.container.NalUnitUtil$H265LayerInfo r6 = (androidx.media3.container.NalUnitUtil.H265LayerInfo) r6
            int r4 = r6.viewId
            r6 = -1
            if (r4 != r6) goto L_0x0282
            androidx.media3.container.NalUnitUtil$H265VpsData r0 = new androidx.media3.container.NalUnitUtil$H265VpsData
            r17 = 0
            r19 = 0
            r20 = 0
            r15 = r0
            r16 = r37
            r18 = r1
            r15.<init>(r16, r17, r18, r19, r20)
            return r0
        L_0x0282:
            r4 = 1
        L_0x0283:
            if (r4 > r11) goto L_0x0295
            java.lang.Object r6 = r2.get(r4)
            androidx.media3.container.NalUnitUtil$H265LayerInfo r6 = (androidx.media3.container.NalUnitUtil.H265LayerInfo) r6
            int r6 = r6.viewId
            r7 = -1
            if (r6 == r7) goto L_0x0292
            r6 = r4
            goto L_0x0297
        L_0x0292:
            int r4 = r4 + 1
            goto L_0x0283
        L_0x0295:
            r7 = -1
            r6 = r7
        L_0x0297:
            if (r6 != r7) goto L_0x02aa
            androidx.media3.container.NalUnitUtil$H265VpsData r0 = new androidx.media3.container.NalUnitUtil$H265VpsData
            r17 = 0
            r19 = 0
            r20 = 0
            r15 = r0
            r16 = r37
            r18 = r1
            r15.<init>(r16, r17, r18, r19, r20)
            return r0
        L_0x02aa:
            int[] r4 = new int[]{r5, r5}
            java.lang.Class r7 = java.lang.Boolean.TYPE
            java.lang.Object r4 = java.lang.reflect.Array.newInstance(r7, r4)
            boolean[][] r4 = (boolean[][]) r4
            int[] r7 = new int[]{r5, r5}
            java.lang.Class r9 = java.lang.Boolean.TYPE
            java.lang.Object r7 = java.lang.reflect.Array.newInstance(r9, r7)
            boolean[][] r7 = (boolean[][]) r7
            r9 = 1
        L_0x02c3:
            if (r9 >= r5) goto L_0x02da
            r10 = 0
        L_0x02c6:
            if (r10 >= r9) goto L_0x02d7
            r11 = r4[r9]
            r12 = r7[r9]
            boolean r14 = r36.readBit()
            r12[r10] = r14
            r11[r10] = r14
            int r10 = r10 + 1
            goto L_0x02c6
        L_0x02d7:
            int r9 = r9 + 1
            goto L_0x02c3
        L_0x02da:
            r9 = 1
        L_0x02db:
            if (r9 >= r5) goto L_0x02fe
            r10 = 0
        L_0x02de:
            int r11 = r5 + -1
            if (r10 >= r11) goto L_0x02fb
            r11 = 0
        L_0x02e3:
            if (r11 >= r9) goto L_0x02f8
            r12 = r7[r9]
            boolean r14 = r12[r11]
            if (r14 == 0) goto L_0x02f5
            r14 = r7[r11]
            boolean r14 = r14[r10]
            if (r14 == 0) goto L_0x02f5
            r14 = 1
            r12[r10] = r14
            goto L_0x02f8
        L_0x02f5:
            int r11 = r11 + 1
            goto L_0x02e3
        L_0x02f8:
            int r10 = r10 + 1
            goto L_0x02de
        L_0x02fb:
            int r9 = r9 + 1
            goto L_0x02db
        L_0x02fe:
            int[] r9 = new int[r3]
            r10 = 0
        L_0x0301:
            if (r10 >= r5) goto L_0x0316
            r11 = 0
            r12 = 0
        L_0x0305:
            if (r11 >= r10) goto L_0x030f
            r14 = r4[r10]
            boolean r14 = r14[r11]
            int r12 = r12 + r14
            int r11 = r11 + 1
            goto L_0x0305
        L_0x030f:
            r11 = r15[r10]
            r9[r11] = r12
            int r10 = r10 + 1
            goto L_0x0301
        L_0x0316:
            r10 = 0
            r11 = 0
        L_0x0318:
            if (r10 >= r5) goto L_0x0325
            r12 = r15[r10]
            r12 = r9[r12]
            if (r12 != 0) goto L_0x0322
            int r11 = r11 + 1
        L_0x0322:
            int r10 = r10 + 1
            goto L_0x0318
        L_0x0325:
            r10 = 1
            if (r11 <= r10) goto L_0x0339
            androidx.media3.container.NalUnitUtil$H265VpsData r0 = new androidx.media3.container.NalUnitUtil$H265VpsData
            r17 = 0
            r19 = 0
            r20 = 0
            r15 = r0
            r16 = r37
            r18 = r1
            r15.<init>(r16, r17, r18, r19, r20)
            return r0
        L_0x0339:
            int[] r10 = new int[r5]
            int[] r11 = new int[r13]
            boolean r12 = r36.readBit()
            if (r12 == 0) goto L_0x0350
            r12 = 0
        L_0x0344:
            if (r12 >= r5) goto L_0x0354
            r14 = 3
            int r18 = r0.readBits(r14)
            r10[r12] = r18
            int r12 = r12 + 1
            goto L_0x0344
        L_0x0350:
            r12 = 0
            java.util.Arrays.fill(r10, r12, r5, r8)
        L_0x0354:
            r12 = 0
        L_0x0355:
            if (r12 >= r13) goto L_0x0381
            r19 = r9
            r18 = r15
            r14 = 0
            r15 = 0
        L_0x035d:
            r9 = r29[r12]
            if (r14 >= r9) goto L_0x0376
            r9 = r28[r12]
            r9 = r9[r14]
            java.lang.Object r9 = r2.get(r9)
            androidx.media3.container.NalUnitUtil$H265LayerInfo r9 = (androidx.media3.container.NalUnitUtil.H265LayerInfo) r9
            int r9 = r9.layerIdInVps
            r9 = r10[r9]
            int r15 = java.lang.Math.max(r15, r9)
            int r14 = r14 + 1
            goto L_0x035d
        L_0x0376:
            int r15 = r15 + 1
            r11[r12] = r15
            int r12 = r12 + 1
            r15 = r18
            r9 = r19
            goto L_0x0355
        L_0x0381:
            r19 = r9
            r18 = r15
            boolean r9 = r36.readBit()
            if (r9 == 0) goto L_0x03a6
            r9 = 0
        L_0x038c:
            int r10 = r5 + -1
            if (r9 >= r10) goto L_0x03a6
            int r10 = r9 + 1
            r12 = r10
        L_0x0393:
            if (r12 >= r5) goto L_0x03a4
            r14 = r4[r12]
            boolean r14 = r14[r9]
            if (r14 == 0) goto L_0x03a0
            r14 = 3
            r0.skipBits(r14)
            goto L_0x03a1
        L_0x03a0:
            r14 = 3
        L_0x03a1:
            int r12 = r12 + 1
            goto L_0x0393
        L_0x03a4:
            r9 = r10
            goto L_0x038c
        L_0x03a6:
            r36.skipBit()
            int r9 = r36.readUnsignedExpGolombCodedInt()
            r10 = 1
            int r9 = r9 + r10
            com.google.common.collect.ImmutableList$Builder r12 = com.google.common.collect.ImmutableList.builder()
            r14 = r26
            r12.add((java.lang.Object) r14)
            if (r9 <= r10) goto L_0x03d0
            r10 = r25
            r12.add((java.lang.Object) r10)
            r14 = 2
        L_0x03c0:
            if (r14 >= r9) goto L_0x03d0
            boolean r15 = r36.readBit()
            androidx.media3.container.NalUnitUtil$H265ProfileTierLevel r10 = parseH265ProfileTierLevel(r0, r15, r8, r10)
            r12.add((java.lang.Object) r10)
            int r14 = r14 + 1
            goto L_0x03c0
        L_0x03d0:
            com.google.common.collect.ImmutableList r8 = r12.build()
            int r10 = r36.readUnsignedExpGolombCodedInt()
            int r10 = r10 + r13
            if (r10 <= r13) goto L_0x03ec
            androidx.media3.container.NalUnitUtil$H265VpsData r0 = new androidx.media3.container.NalUnitUtil$H265VpsData
            r17 = 0
            r19 = 0
            r20 = 0
            r15 = r0
            r16 = r37
            r18 = r1
            r15.<init>(r16, r17, r18, r19, r20)
            return r0
        L_0x03ec:
            r12 = 2
            int r14 = r0.readBits(r12)
            int[] r12 = new int[]{r10, r3}
            java.lang.Class r15 = java.lang.Boolean.TYPE
            java.lang.Object r12 = java.lang.reflect.Array.newInstance(r15, r12)
            boolean[][] r12 = (boolean[][]) r12
            int[] r15 = new int[r10]
            r20 = r8
            int[] r8 = new int[r10]
            r24 = r4
            r4 = 0
        L_0x0406:
            if (r4 >= r13) goto L_0x045d
            r25 = r13
            r13 = 0
            r15[r4] = r13
            r21 = r23[r4]
            r8[r4] = r21
            if (r14 != 0) goto L_0x0426
            r26 = r11
            r11 = r12[r4]
            r27 = r5
            r5 = r29[r4]
            r30 = r1
            r1 = 1
            java.util.Arrays.fill(r11, r13, r5, r1)
            r5 = r29[r4]
            r15[r4] = r5
            goto L_0x0452
        L_0x0426:
            r30 = r1
            r27 = r5
            r26 = r11
            r1 = 1
            if (r14 != r1) goto L_0x044a
            r1 = r23[r4]
            r5 = 0
        L_0x0432:
            r11 = r29[r4]
            if (r5 >= r11) goto L_0x0446
            r11 = r12[r4]
            r13 = r28[r4]
            r13 = r13[r5]
            if (r13 != r1) goto L_0x0440
            r13 = 1
            goto L_0x0441
        L_0x0440:
            r13 = 0
        L_0x0441:
            r11[r5] = r13
            int r5 = r5 + 1
            goto L_0x0432
        L_0x0446:
            r5 = 1
            r15[r4] = r5
            goto L_0x0452
        L_0x044a:
            r5 = r1
            r1 = 0
            r11 = r12[r1]
            r11[r1] = r5
            r15[r1] = r5
        L_0x0452:
            int r4 = r4 + 1
            r13 = r25
            r11 = r26
            r5 = r27
            r1 = r30
            goto L_0x0406
        L_0x045d:
            r30 = r1
            r27 = r5
            r26 = r11
            r25 = r13
            int[] r1 = new int[r3]
            int[] r3 = new int[]{r10, r3}
            java.lang.Class r4 = java.lang.Boolean.TYPE
            java.lang.Object r3 = java.lang.reflect.Array.newInstance(r4, r3)
            boolean[][] r3 = (boolean[][]) r3
            r4 = 1
            r5 = 0
        L_0x0475:
            if (r4 >= r10) goto L_0x0558
            r11 = 2
            if (r14 != r11) goto L_0x049c
            r11 = 0
        L_0x047b:
            r13 = r29[r4]
            if (r11 >= r13) goto L_0x049c
            r13 = r12[r4]
            boolean r23 = r36.readBit()
            r13[r11] = r23
            r13 = r15[r4]
            r23 = r12[r4]
            boolean r23 = r23[r11]
            int r13 = r13 + r23
            r15[r4] = r13
            if (r23 == 0) goto L_0x0499
            r13 = r28[r4]
            r13 = r13[r11]
            r8[r4] = r13
        L_0x0499:
            int r11 = r11 + 1
            goto L_0x047b
        L_0x049c:
            if (r5 != 0) goto L_0x04c1
            r11 = r28[r4]
            r21 = 0
            r11 = r11[r21]
            if (r11 != 0) goto L_0x04c3
            r11 = r12[r4]
            boolean r11 = r11[r21]
            if (r11 == 0) goto L_0x04c3
            r11 = 1
        L_0x04ad:
            r13 = r29[r4]
            if (r11 >= r13) goto L_0x04c3
            r13 = r28[r4]
            r13 = r13[r11]
            if (r13 != r6) goto L_0x04be
            r13 = r12[r4]
            boolean r13 = r13[r6]
            if (r13 == 0) goto L_0x04be
            r5 = r4
        L_0x04be:
            int r11 = r11 + 1
            goto L_0x04ad
        L_0x04c1:
            r21 = 0
        L_0x04c3:
            r11 = r21
        L_0x04c5:
            r13 = r29[r4]
            if (r11 >= r13) goto L_0x053a
            r13 = 1
            if (r9 <= r13) goto L_0x052b
            r13 = r3[r4]
            r23 = r12[r4]
            boolean r23 = r23[r11]
            r13[r11] = r23
            r23 = r12
            double r12 = (double) r9
            r31 = r6
            java.math.RoundingMode r6 = java.math.RoundingMode.CEILING
            int r6 = com.google.common.math.DoubleMath.log2(r12, r6)
            r12 = r3[r4]
            boolean r12 = r12[r11]
            if (r12 != 0) goto L_0x0514
            r12 = r28[r4]
            r12 = r12[r11]
            java.lang.Object r12 = r2.get(r12)
            androidx.media3.container.NalUnitUtil$H265LayerInfo r12 = (androidx.media3.container.NalUnitUtil.H265LayerInfo) r12
            int r12 = r12.layerIdInVps
            r13 = r21
        L_0x04f3:
            if (r13 >= r11) goto L_0x0514
            r32 = r28[r4]
            r33 = r9
            r9 = r32[r13]
            java.lang.Object r9 = r2.get(r9)
            androidx.media3.container.NalUnitUtil$H265LayerInfo r9 = (androidx.media3.container.NalUnitUtil.H265LayerInfo) r9
            int r9 = r9.layerIdInVps
            r32 = r7[r12]
            boolean r9 = r32[r9]
            if (r9 == 0) goto L_0x050f
            r9 = r3[r4]
            r12 = 1
            r9[r11] = r12
            goto L_0x0516
        L_0x050f:
            int r13 = r13 + 1
            r9 = r33
            goto L_0x04f3
        L_0x0514:
            r33 = r9
        L_0x0516:
            r9 = r3[r4]
            boolean r9 = r9[r11]
            if (r9 == 0) goto L_0x0531
            if (r5 <= 0) goto L_0x0527
            if (r4 != r5) goto L_0x0527
            int r6 = r0.readBits(r6)
            r1[r11] = r6
            goto L_0x0531
        L_0x0527:
            r0.skipBits(r6)
            goto L_0x0531
        L_0x052b:
            r31 = r6
            r33 = r9
            r23 = r12
        L_0x0531:
            int r11 = r11 + 1
            r12 = r23
            r6 = r31
            r9 = r33
            goto L_0x04c5
        L_0x053a:
            r31 = r6
            r33 = r9
            r23 = r12
            r6 = r15[r4]
            r9 = 1
            if (r6 != r9) goto L_0x054e
            r6 = r8[r4]
            r6 = r19[r6]
            if (r6 <= 0) goto L_0x054e
            r36.skipBit()
        L_0x054e:
            int r4 = r4 + 1
            r12 = r23
            r6 = r31
            r9 = r33
            goto L_0x0475
        L_0x0558:
            r9 = 1
            if (r5 != 0) goto L_0x056c
            androidx.media3.container.NalUnitUtil$H265VpsData r0 = new androidx.media3.container.NalUnitUtil$H265VpsData
            r17 = 0
            r19 = 0
            r20 = 0
            r15 = r0
            r16 = r37
            r18 = r30
            r15.<init>(r16, r17, r18, r19, r20)
            return r0
        L_0x056c:
            r5 = r27
            androidx.media3.container.NalUnitUtil$H265RepFormatsAndIndices r34 = parseH265RepFormatsAndIndices(r0, r5)
            r4 = 2
            r0.skipBits(r4)
            r6 = r9
        L_0x0577:
            if (r6 >= r5) goto L_0x0585
            r4 = r18[r6]
            r4 = r19[r4]
            if (r4 != 0) goto L_0x0582
            r36.skipBit()
        L_0x0582:
            int r6 = r6 + 1
            goto L_0x0577
        L_0x0585:
            r6 = r26
            r4 = r29
            skipH265DpbSize(r0, r10, r6, r4, r3)
            r4 = r24
            skipToH265VuiPresentFlagAfterDpbSize(r0, r5, r4)
            boolean r3 = r36.readBit()
            if (r3 == 0) goto L_0x05a3
            r36.byteAlign()
            r13 = r25
            androidx.media3.container.NalUnitUtil$H265VideoSignalInfosAndIndices r9 = parseH265VideoSignalInfosAndIndices(r0, r5, r13, r6)
            r35 = r9
            goto L_0x05a5
        L_0x05a3:
            r35 = 0
        L_0x05a5:
            androidx.media3.container.NalUnitUtil$H265VpsData r0 = new androidx.media3.container.NalUnitUtil$H265VpsData
            androidx.media3.container.NalUnitUtil$H265ProfileTierLevelsAndIndices r3 = new androidx.media3.container.NalUnitUtil$H265ProfileTierLevelsAndIndices
            r4 = r20
            r3.<init>(r4, r1)
            r30 = r0
            r31 = r37
            r32 = r2
            r33 = r3
            r30.<init>(r31, r32, r33, r34, r35)
            return r0
        L_0x05ba:
            r30 = r1
            androidx.media3.container.NalUnitUtil$H265VpsData r0 = new androidx.media3.container.NalUnitUtil$H265VpsData
            r17 = 0
            r19 = 0
            r20 = 0
            r15 = r0
            r16 = r37
            r18 = r30
            r15.<init>(r16, r17, r18, r19, r20)
            return r0
        L_0x05cd:
            r30 = r1
            androidx.media3.container.NalUnitUtil$H265VpsData r0 = new androidx.media3.container.NalUnitUtil$H265VpsData
            r17 = 0
            r19 = 0
            r20 = 0
            r15 = r0
            r16 = r37
            r18 = r30
            r15.<init>(r16, r17, r18, r19, r20)
            return r0
        L_0x05e0:
            r30 = r15
            androidx.media3.container.NalUnitUtil$H265VpsData r0 = new androidx.media3.container.NalUnitUtil$H265VpsData
            r17 = 0
            r19 = 0
            r20 = 0
            r15 = r0
            r16 = r37
            r18 = r30
            r15.<init>(r16, r17, r18, r19, r20)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.container.NalUnitUtil.parseH265VpsNalUnitPayload(androidx.media3.container.ParsableNalUnitBitArray, androidx.media3.container.NalUnitUtil$H265NalHeader):androidx.media3.container.NalUnitUtil$H265VpsData");
    }

    public static H265SpsData parseH265SpsNalUnit(byte[] bArr, int i, int i2, H265VpsData h265VpsData) {
        return parseH265SpsNalUnitPayload(bArr, i + 2, i2, parseH265NalHeader(new ParsableNalUnitBitArray(bArr, i, i2)), h265VpsData);
    }

    /* JADX WARNING: Removed duplicated region for block: B:105:0x023b  */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x024a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static androidx.media3.container.NalUnitUtil.H265SpsData parseH265SpsNalUnitPayload(byte[] r20, int r21, int r22, androidx.media3.container.NalUnitUtil.H265NalHeader r23, androidx.media3.container.NalUnitUtil.H265VpsData r24) {
        /*
            r1 = r23
            r0 = r24
            androidx.media3.container.ParsableNalUnitBitArray r2 = new androidx.media3.container.ParsableNalUnitBitArray
            r3 = r20
            r4 = r21
            r5 = r22
            r2.<init>(r3, r4, r5)
            r3 = 4
            r2.skipBits(r3)
            r4 = 3
            int r5 = r2.readBits(r4)
            int r6 = r1.layerId
            r7 = 1
            if (r6 == 0) goto L_0x0022
            r6 = 7
            if (r5 != r6) goto L_0x0022
            r6 = r7
            goto L_0x0023
        L_0x0022:
            r6 = 0
        L_0x0023:
            if (r0 == 0) goto L_0x0045
            com.google.common.collect.ImmutableList<androidx.media3.container.NalUnitUtil$H265LayerInfo> r9 = r0.layerInfos
            boolean r9 = r9.isEmpty()
            if (r9 != 0) goto L_0x0045
            int r9 = r1.layerId
            com.google.common.collect.ImmutableList<androidx.media3.container.NalUnitUtil$H265LayerInfo> r10 = r0.layerInfos
            int r10 = r10.size()
            int r10 = r10 - r7
            int r9 = java.lang.Math.min(r9, r10)
            com.google.common.collect.ImmutableList<androidx.media3.container.NalUnitUtil$H265LayerInfo> r10 = r0.layerInfos
            java.lang.Object r9 = r10.get(r9)
            androidx.media3.container.NalUnitUtil$H265LayerInfo r9 = (androidx.media3.container.NalUnitUtil.H265LayerInfo) r9
            int r9 = r9.layerIdInVps
            goto L_0x0046
        L_0x0045:
            r9 = 0
        L_0x0046:
            r10 = 0
            if (r6 != 0) goto L_0x0051
            r2.skipBit()
            androidx.media3.container.NalUnitUtil$H265ProfileTierLevel r10 = parseH265ProfileTierLevel(r2, r7, r5, r10)
            goto L_0x006d
        L_0x0051:
            if (r0 == 0) goto L_0x006d
            androidx.media3.container.NalUnitUtil$H265ProfileTierLevelsAndIndices r11 = r0.profileTierLevelsAndIndices
            int[] r11 = r11.indices
            r11 = r11[r9]
            androidx.media3.container.NalUnitUtil$H265ProfileTierLevelsAndIndices r12 = r0.profileTierLevelsAndIndices
            com.google.common.collect.ImmutableList<androidx.media3.container.NalUnitUtil$H265ProfileTierLevel> r12 = r12.profileTierLevels
            int r12 = r12.size()
            if (r12 <= r11) goto L_0x006d
            androidx.media3.container.NalUnitUtil$H265ProfileTierLevelsAndIndices r10 = r0.profileTierLevelsAndIndices
            com.google.common.collect.ImmutableList<androidx.media3.container.NalUnitUtil$H265ProfileTierLevel> r10 = r10.profileTierLevels
            java.lang.Object r10 = r10.get(r11)
            androidx.media3.container.NalUnitUtil$H265ProfileTierLevel r10 = (androidx.media3.container.NalUnitUtil.H265ProfileTierLevel) r10
        L_0x006d:
            int r11 = r2.readUnsignedExpGolombCodedInt()
            r12 = 8
            r13 = -1
            if (r6 == 0) goto L_0x00ba
            boolean r14 = r2.readBit()
            if (r14 == 0) goto L_0x0081
            int r14 = r2.readBits(r12)
            goto L_0x0082
        L_0x0081:
            r14 = r13
        L_0x0082:
            if (r0 == 0) goto L_0x00b4
            androidx.media3.container.NalUnitUtil$H265RepFormatsAndIndices r15 = r0.repFormatsAndIndices
            if (r15 == 0) goto L_0x00b4
            if (r14 != r13) goto L_0x0090
            androidx.media3.container.NalUnitUtil$H265RepFormatsAndIndices r14 = r0.repFormatsAndIndices
            int[] r14 = r14.indices
            r14 = r14[r9]
        L_0x0090:
            if (r14 == r13) goto L_0x00b4
            androidx.media3.container.NalUnitUtil$H265RepFormatsAndIndices r15 = r0.repFormatsAndIndices
            com.google.common.collect.ImmutableList<androidx.media3.container.NalUnitUtil$H265RepFormat> r15 = r15.repFormats
            int r15 = r15.size()
            if (r15 <= r14) goto L_0x00b4
            androidx.media3.container.NalUnitUtil$H265RepFormatsAndIndices r15 = r0.repFormatsAndIndices
            com.google.common.collect.ImmutableList<androidx.media3.container.NalUnitUtil$H265RepFormat> r15 = r15.repFormats
            java.lang.Object r14 = r15.get(r14)
            androidx.media3.container.NalUnitUtil$H265RepFormat r14 = (androidx.media3.container.NalUnitUtil.H265RepFormat) r14
            int r15 = r14.chromaFormatIdc
            int r8 = r14.width
            int r13 = r14.height
            int r7 = r14.bitDepthLumaMinus8
            int r14 = r14.bitDepthChromaMinus8
            r4 = r7
            r7 = r8
            r3 = r15
            goto L_0x00f6
        L_0x00b4:
            r3 = 0
            r4 = 0
            r7 = 0
            r13 = 0
            r14 = 0
            goto L_0x00f6
        L_0x00ba:
            int r7 = r2.readUnsignedExpGolombCodedInt()
            if (r7 != r4) goto L_0x00c3
            r2.skipBit()
        L_0x00c3:
            int r8 = r2.readUnsignedExpGolombCodedInt()
            int r13 = r2.readUnsignedExpGolombCodedInt()
            boolean r14 = r2.readBit()
            if (r14 == 0) goto L_0x00ea
            int r14 = r2.readUnsignedExpGolombCodedInt()
            int r15 = r2.readUnsignedExpGolombCodedInt()
            int r4 = r2.readUnsignedExpGolombCodedInt()
            int r3 = r2.readUnsignedExpGolombCodedInt()
            int r8 = applyConformanceWindowToWidth(r8, r7, r14, r15)
            int r3 = applyConformanceWindowToHeight(r13, r7, r4, r3)
            r13 = r3
        L_0x00ea:
            int r3 = r2.readUnsignedExpGolombCodedInt()
            int r4 = r2.readUnsignedExpGolombCodedInt()
            r14 = r4
            r4 = r3
            r3 = r7
            r7 = r8
        L_0x00f6:
            int r8 = r2.readUnsignedExpGolombCodedInt()
            if (r6 != 0) goto L_0x011b
            boolean r15 = r2.readBit()
            if (r15 == 0) goto L_0x0104
            r15 = 0
            goto L_0x0105
        L_0x0104:
            r15 = r5
        L_0x0105:
            r12 = -1
        L_0x0106:
            if (r15 > r5) goto L_0x011c
            r2.readUnsignedExpGolombCodedInt()
            int r1 = r2.readUnsignedExpGolombCodedInt()
            int r12 = java.lang.Math.max(r1, r12)
            r2.readUnsignedExpGolombCodedInt()
            int r15 = r15 + 1
            r1 = r23
            goto L_0x0106
        L_0x011b:
            r12 = -1
        L_0x011c:
            r2.readUnsignedExpGolombCodedInt()
            r2.readUnsignedExpGolombCodedInt()
            r2.readUnsignedExpGolombCodedInt()
            r2.readUnsignedExpGolombCodedInt()
            r2.readUnsignedExpGolombCodedInt()
            r2.readUnsignedExpGolombCodedInt()
            boolean r1 = r2.readBit()
            if (r1 == 0) goto L_0x014c
            if (r6 == 0) goto L_0x013b
            boolean r1 = r2.readBit()
            goto L_0x013c
        L_0x013b:
            r1 = 0
        L_0x013c:
            if (r1 == 0) goto L_0x0143
            r1 = 6
            r2.skipBits(r1)
            goto L_0x014c
        L_0x0143:
            boolean r1 = r2.readBit()
            if (r1 == 0) goto L_0x014c
            skipH265ScalingList(r2)
        L_0x014c:
            r1 = 2
            r2.skipBits(r1)
            boolean r5 = r2.readBit()
            if (r5 == 0) goto L_0x0164
            r5 = 8
            r2.skipBits(r5)
            r2.readUnsignedExpGolombCodedInt()
            r2.readUnsignedExpGolombCodedInt()
            r2.skipBit()
        L_0x0164:
            skipH265ShortTermReferencePictureSets(r2)
            boolean r5 = r2.readBit()
            if (r5 == 0) goto L_0x0181
            int r5 = r2.readUnsignedExpGolombCodedInt()
            r6 = 0
        L_0x0172:
            if (r6 >= r5) goto L_0x0181
            r15 = 4
            int r17 = r8 + 4
            r18 = 1
            int r15 = r17 + 1
            r2.skipBits(r15)
            int r6 = r6 + 1
            goto L_0x0172
        L_0x0181:
            r18 = 1
            r2.skipBits(r1)
            boolean r5 = r2.readBit()
            r6 = 1065353216(0x3f800000, float:1.0)
            if (r5 == 0) goto L_0x0253
            boolean r5 = r2.readBit()
            if (r5 == 0) goto L_0x01cc
            r5 = 8
            int r8 = r2.readBits(r5)
            r5 = 255(0xff, float:3.57E-43)
            if (r8 != r5) goto L_0x01b0
            r5 = 16
            int r8 = r2.readBits(r5)
            int r5 = r2.readBits(r5)
            if (r8 == 0) goto L_0x01cc
            if (r5 == 0) goto L_0x01cc
            float r6 = (float) r8
            float r5 = (float) r5
            float r6 = r6 / r5
            goto L_0x01cc
        L_0x01b0:
            float[] r5 = ASPECT_RATIO_IDC_VALUES
            int r15 = r5.length
            if (r8 >= r15) goto L_0x01b8
            r6 = r5[r8]
            goto L_0x01cc
        L_0x01b8:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r15 = "Unexpected aspect_ratio_idc value: "
            r5.<init>(r15)
            java.lang.StringBuilder r5 = r5.append(r8)
            java.lang.String r5 = r5.toString()
            java.lang.String r8 = "NalUnitUtil"
            androidx.media3.common.util.Log.w(r8, r5)
        L_0x01cc:
            boolean r5 = r2.readBit()
            if (r5 == 0) goto L_0x01d5
            r2.skipBit()
        L_0x01d5:
            boolean r5 = r2.readBit()
            if (r5 == 0) goto L_0x0205
            r5 = 3
            r2.skipBits(r5)
            boolean r0 = r2.readBit()
            if (r0 == 0) goto L_0x01e7
            r1 = r18
        L_0x01e7:
            boolean r0 = r2.readBit()
            if (r0 == 0) goto L_0x0203
            r0 = 8
            int r5 = r2.readBits(r0)
            int r8 = r2.readBits(r0)
            r2.skipBits(r0)
            int r0 = androidx.media3.common.ColorInfo.isoColorPrimariesToColorSpace(r5)
            int r5 = androidx.media3.common.ColorInfo.isoTransferCharacteristicsToColorTransfer(r8)
            goto L_0x0235
        L_0x0203:
            r0 = -1
            goto L_0x0234
        L_0x0205:
            if (r0 == 0) goto L_0x0232
            androidx.media3.container.NalUnitUtil$H265VideoSignalInfosAndIndices r1 = r0.videoSignalInfosAndIndices
            if (r1 == 0) goto L_0x0232
            androidx.media3.container.NalUnitUtil$H265VideoSignalInfosAndIndices r1 = r0.videoSignalInfosAndIndices
            int[] r1 = r1.indices
            r1 = r1[r9]
            androidx.media3.container.NalUnitUtil$H265VideoSignalInfosAndIndices r5 = r0.videoSignalInfosAndIndices
            com.google.common.collect.ImmutableList<androidx.media3.container.NalUnitUtil$H265VideoSignalInfo> r5 = r5.videoSignalInfos
            int r5 = r5.size()
            if (r5 <= r1) goto L_0x0232
            androidx.media3.container.NalUnitUtil$H265VideoSignalInfosAndIndices r0 = r0.videoSignalInfosAndIndices
            com.google.common.collect.ImmutableList<androidx.media3.container.NalUnitUtil$H265VideoSignalInfo> r0 = r0.videoSignalInfos
            java.lang.Object r0 = r0.get(r1)
            androidx.media3.container.NalUnitUtil$H265VideoSignalInfo r0 = (androidx.media3.container.NalUnitUtil.H265VideoSignalInfo) r0
            int r1 = r0.colorSpace
            int r5 = r0.colorRange
            int r0 = r0.colorTransfer
            r19 = r5
            r5 = r0
            r0 = r1
            r1 = r19
            goto L_0x0235
        L_0x0232:
            r0 = -1
            r1 = -1
        L_0x0234:
            r5 = -1
        L_0x0235:
            boolean r8 = r2.readBit()
            if (r8 == 0) goto L_0x0241
            r2.readUnsignedExpGolombCodedInt()
            r2.readUnsignedExpGolombCodedInt()
        L_0x0241:
            r2.skipBit()
            boolean r2 = r2.readBit()
            if (r2 == 0) goto L_0x024c
            int r13 = r13 * 2
        L_0x024c:
            r15 = r1
            r16 = r5
            r9 = r6
            r8 = r13
            r13 = r0
            goto L_0x0259
        L_0x0253:
            r9 = r6
            r8 = r13
            r13 = -1
            r15 = -1
            r16 = -1
        L_0x0259:
            androidx.media3.container.NalUnitUtil$H265SpsData r17 = new androidx.media3.container.NalUnitUtil$H265SpsData
            r0 = r17
            r1 = r23
            r2 = r10
            r5 = r14
            r6 = r11
            r10 = r12
            r11 = r13
            r12 = r15
            r13 = r16
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13)
            return r17
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.container.NalUnitUtil.parseH265SpsNalUnitPayload(byte[], int, int, androidx.media3.container.NalUnitUtil$H265NalHeader, androidx.media3.container.NalUnitUtil$H265VpsData):androidx.media3.container.NalUnitUtil$H265SpsData");
    }

    public static PpsData parsePpsNalUnit(byte[] bArr, int i, int i2) {
        return parsePpsNalUnitPayload(bArr, i + 1, i2);
    }

    public static PpsData parsePpsNalUnitPayload(byte[] bArr, int i, int i2) {
        ParsableNalUnitBitArray parsableNalUnitBitArray = new ParsableNalUnitBitArray(bArr, i, i2);
        int readUnsignedExpGolombCodedInt = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        int readUnsignedExpGolombCodedInt2 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        parsableNalUnitBitArray.skipBit();
        return new PpsData(readUnsignedExpGolombCodedInt, readUnsignedExpGolombCodedInt2, parsableNalUnitBitArray.readBit());
    }

    public static H265Sei3dRefDisplayInfoData parseH265Sei3dRefDisplayInfo(byte[] bArr, int i, int i2) {
        byte b;
        int i3;
        int i4;
        int i5 = i + 2;
        while (true) {
            i2--;
            b = bArr[i2];
            if (b != 0 || i2 <= i5) {
            }
        }
        if (b != 0 && i2 > i5) {
            ParsableNalUnitBitArray parsableNalUnitBitArray = new ParsableNalUnitBitArray(bArr, i5, i2 + 1);
            while (parsableNalUnitBitArray.canReadBits(16)) {
                int readBits = parsableNalUnitBitArray.readBits(8);
                int i6 = 0;
                while (readBits == 255) {
                    i6 += 255;
                    readBits = parsableNalUnitBitArray.readBits(8);
                }
                int i7 = i6 + readBits;
                int readBits2 = parsableNalUnitBitArray.readBits(8);
                int i8 = 0;
                while (readBits2 == 255) {
                    i8 += 255;
                    readBits2 = parsableNalUnitBitArray.readBits(8);
                }
                int i9 = i8 + readBits2;
                if (i9 == 0 || !parsableNalUnitBitArray.canReadBits(i9)) {
                    break;
                } else if (i7 == 176) {
                    int readUnsignedExpGolombCodedInt = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                    boolean readBit = parsableNalUnitBitArray.readBit();
                    int readUnsignedExpGolombCodedInt2 = readBit ? parsableNalUnitBitArray.readUnsignedExpGolombCodedInt() : 0;
                    int readUnsignedExpGolombCodedInt3 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                    int i10 = -1;
                    int i11 = -1;
                    int i12 = -1;
                    int i13 = -1;
                    int i14 = -1;
                    int i15 = -1;
                    for (int i16 = 0; i16 <= readUnsignedExpGolombCodedInt3; i16++) {
                        i10 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                        i11 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                        i12 = parsableNalUnitBitArray.readBits(6);
                        if (i12 == 63) {
                            return null;
                        }
                        if (i12 == 0) {
                            i3 = Math.max(0, readUnsignedExpGolombCodedInt - 30);
                        } else {
                            i3 = Math.max(0, (i12 + readUnsignedExpGolombCodedInt) - 31);
                        }
                        i13 = parsableNalUnitBitArray.readBits(i3);
                        if (readBit) {
                            i14 = parsableNalUnitBitArray.readBits(6);
                            if (i14 == 63) {
                                return null;
                            }
                            if (i14 == 0) {
                                i4 = Math.max(0, readUnsignedExpGolombCodedInt2 - 30);
                            } else {
                                i4 = Math.max(0, (i14 + readUnsignedExpGolombCodedInt2) - 31);
                            }
                            i15 = parsableNalUnitBitArray.readBits(i4);
                        }
                        if (parsableNalUnitBitArray.readBit()) {
                            parsableNalUnitBitArray.skipBits(10);
                        }
                    }
                    return new H265Sei3dRefDisplayInfoData(readUnsignedExpGolombCodedInt, readUnsignedExpGolombCodedInt2, readUnsignedExpGolombCodedInt3 + 1, i10, i11, i12, i13, i14, i15);
                }
            }
        }
        return null;
    }

    public static int findNalUnit(byte[] bArr, int i, int i2, boolean[] zArr) {
        boolean z;
        int i3 = i2 - i;
        boolean z2 = false;
        Assertions.checkState(i3 >= 0);
        if (i3 == 0) {
            return i2;
        }
        if (zArr[0]) {
            clearPrefixFlags(zArr);
            return i - 3;
        } else if (i3 > 1 && zArr[1] && bArr[i] == 1) {
            clearPrefixFlags(zArr);
            return i - 2;
        } else if (i3 <= 2 || !zArr[2] || bArr[i] != 0 || bArr[i + 1] != 1) {
            int i4 = i2 - 1;
            int i5 = i + 2;
            while (i5 < i4) {
                byte b = bArr[i5];
                if ((b & 254) == 0) {
                    int i6 = i5 - 2;
                    if (bArr[i6] == 0 && bArr[i5 - 1] == 0 && b == 1) {
                        clearPrefixFlags(zArr);
                        return i6;
                    }
                    i5 -= 2;
                }
                i5 += 3;
            }
            if (i3 <= 2 ? i3 != 2 ? !zArr[1] || bArr[i4] != 1 : !(zArr[2] && bArr[i2 - 2] == 0 && bArr[i4] == 1) : !(bArr[i2 - 3] == 0 && bArr[i2 - 2] == 0 && bArr[i4] == 1)) {
                z = false;
            } else {
                z = true;
            }
            zArr[0] = z;
            zArr[1] = i3 <= 1 ? !(!zArr[2] || bArr[i4] != 0) : bArr[i2 + -2] == 0 && bArr[i4] == 0;
            if (bArr[i4] == 0) {
                z2 = true;
            }
            zArr[2] = z2;
            return i2;
        } else {
            clearPrefixFlags(zArr);
            return i - 1;
        }
    }

    public static void clearPrefixFlags(boolean[] zArr) {
        zArr[0] = false;
        zArr[1] = false;
        zArr[2] = false;
    }

    public static String getH265BaseLayerCodecsString(List<byte[]> list) {
        for (int i = 0; i < list.size(); i++) {
            byte[] bArr = list.get(i);
            int length = bArr.length;
            if (length > 3) {
                ImmutableList<Integer> findNalUnitPositions = findNalUnitPositions(bArr);
                for (int i2 = 0; i2 < findNalUnitPositions.size(); i2++) {
                    if (((Integer) findNalUnitPositions.get(i2)).intValue() + 3 < length) {
                        ParsableNalUnitBitArray parsableNalUnitBitArray = new ParsableNalUnitBitArray(bArr, ((Integer) findNalUnitPositions.get(i2)).intValue() + 3, length);
                        H265NalHeader parseH265NalHeader = parseH265NalHeader(parsableNalUnitBitArray);
                        if (parseH265NalHeader.nalUnitType == 33 && parseH265NalHeader.layerId == 0) {
                            return createCodecStringFromH265SpsPalyoad(parsableNalUnitBitArray);
                        }
                    }
                }
                continue;
            }
        }
        return null;
    }

    private static ImmutableList<Integer> findNalUnitPositions(byte[] bArr) {
        boolean[] zArr = new boolean[3];
        ImmutableList.Builder builder = ImmutableList.builder();
        int i = 0;
        while (i < bArr.length) {
            int findNalUnit = findNalUnit(bArr, i, bArr.length, zArr);
            if (findNalUnit != bArr.length) {
                builder.add((Object) Integer.valueOf(findNalUnit));
            }
            i = findNalUnit + 3;
        }
        return builder.build();
    }

    private static String createCodecStringFromH265SpsPalyoad(ParsableNalUnitBitArray parsableNalUnitBitArray) {
        parsableNalUnitBitArray.skipBits(4);
        int readBits = parsableNalUnitBitArray.readBits(3);
        parsableNalUnitBitArray.skipBit();
        H265ProfileTierLevel parseH265ProfileTierLevel = parseH265ProfileTierLevel(parsableNalUnitBitArray, true, readBits, (H265ProfileTierLevel) null);
        return CodecSpecificDataUtil.buildHevcCodecString(parseH265ProfileTierLevel.generalProfileSpace, parseH265ProfileTierLevel.generalTierFlag, parseH265ProfileTierLevel.generalProfileIdc, parseH265ProfileTierLevel.generalProfileCompatibilityFlags, parseH265ProfileTierLevel.constraintBytes, parseH265ProfileTierLevel.generalLevelIdc);
    }

    private static int findNextUnescapeIndex(byte[] bArr, int i, int i2) {
        while (i < i2 - 2) {
            if (bArr[i] == 0 && bArr[i + 1] == 0 && bArr[i + 2] == 3) {
                return i;
            }
            i++;
        }
        return i2;
    }

    private static void skipH265HrdParameters(ParsableNalUnitBitArray parsableNalUnitBitArray, boolean z, int i) {
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        if (z) {
            z2 = parsableNalUnitBitArray.readBit();
            z4 = parsableNalUnitBitArray.readBit();
            if (z2 || z4) {
                z3 = parsableNalUnitBitArray.readBit();
                if (z3) {
                    parsableNalUnitBitArray.skipBits(19);
                }
                parsableNalUnitBitArray.skipBits(8);
                if (z3) {
                    parsableNalUnitBitArray.skipBits(4);
                }
                parsableNalUnitBitArray.skipBits(15);
            } else {
                z3 = false;
            }
        } else {
            z2 = false;
            z4 = false;
            z3 = false;
        }
        for (int i2 = 0; i2 <= i; i2++) {
            boolean readBit = parsableNalUnitBitArray.readBit();
            if (!readBit) {
                readBit = parsableNalUnitBitArray.readBit();
            }
            if (readBit) {
                parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                z5 = false;
            } else {
                z5 = parsableNalUnitBitArray.readBit();
            }
            int readUnsignedExpGolombCodedInt = !z5 ? parsableNalUnitBitArray.readUnsignedExpGolombCodedInt() : 0;
            int i3 = (z2 ? 1 : 0) + false + (z4 ? 1 : 0);
            for (int i4 = 0; i4 < i3; i4++) {
                for (int i5 = 0; i5 <= readUnsignedExpGolombCodedInt; i5++) {
                    parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                    parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                    if (z3) {
                        parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                        parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                    }
                    parsableNalUnitBitArray.skipBit();
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x005e  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0076  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static androidx.media3.container.NalUnitUtil.H265ProfileTierLevel parseH265ProfileTierLevel(androidx.media3.container.ParsableNalUnitBitArray r19, boolean r20, int r21, androidx.media3.container.NalUnitUtil.H265ProfileTierLevel r22) {
        /*
            r0 = r19
            r1 = r21
            r2 = r22
            r3 = 6
            int[] r4 = new int[r3]
            r5 = 2
            r6 = 8
            r7 = 0
            if (r20 == 0) goto L_0x003c
            int r2 = r0.readBits(r5)
            boolean r8 = r19.readBit()
            r9 = 5
            int r9 = r0.readBits(r9)
            r10 = r7
            r11 = r10
        L_0x001e:
            r12 = 32
            if (r10 >= r12) goto L_0x002e
            boolean r12 = r19.readBit()
            if (r12 == 0) goto L_0x002b
            r12 = 1
            int r12 = r12 << r10
            r11 = r11 | r12
        L_0x002b:
            int r10 = r10 + 1
            goto L_0x001e
        L_0x002e:
            r10 = r7
        L_0x002f:
            if (r10 >= r3) goto L_0x003a
            int r12 = r0.readBits(r6)
            r4[r10] = r12
            int r10 = r10 + 1
            goto L_0x002f
        L_0x003a:
            r13 = r2
            goto L_0x0049
        L_0x003c:
            if (r2 == 0) goto L_0x0050
            int r3 = r2.generalProfileSpace
            boolean r8 = r2.generalTierFlag
            int r9 = r2.generalProfileIdc
            int r11 = r2.generalProfileCompatibilityFlags
            int[] r4 = r2.constraintBytes
            r13 = r3
        L_0x0049:
            r17 = r4
            r14 = r8
            r15 = r9
            r16 = r11
            goto L_0x0057
        L_0x0050:
            r17 = r4
            r13 = r7
            r14 = r13
            r15 = r14
            r16 = r15
        L_0x0057:
            int r18 = r0.readBits(r6)
            r2 = r7
        L_0x005c:
            if (r7 >= r1) goto L_0x0071
            boolean r3 = r19.readBit()
            if (r3 == 0) goto L_0x0066
            int r2 = r2 + 88
        L_0x0066:
            boolean r3 = r19.readBit()
            if (r3 == 0) goto L_0x006e
            int r2 = r2 + 8
        L_0x006e:
            int r7 = r7 + 1
            goto L_0x005c
        L_0x0071:
            r0.skipBits(r2)
            if (r1 <= 0) goto L_0x007b
            int r6 = r6 - r1
            int r6 = r6 * r5
            r0.skipBits(r6)
        L_0x007b:
            androidx.media3.container.NalUnitUtil$H265ProfileTierLevel r0 = new androidx.media3.container.NalUnitUtil$H265ProfileTierLevel
            r12 = r0
            r12.<init>(r13, r14, r15, r16, r17, r18)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.container.NalUnitUtil.parseH265ProfileTierLevel(androidx.media3.container.ParsableNalUnitBitArray, boolean, int, androidx.media3.container.NalUnitUtil$H265ProfileTierLevel):androidx.media3.container.NalUnitUtil$H265ProfileTierLevel");
    }

    private static H265RepFormatsAndIndices parseH265RepFormatsAndIndices(ParsableNalUnitBitArray parsableNalUnitBitArray, int i) {
        int readUnsignedExpGolombCodedInt = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt() + 1;
        ImmutableList.Builder builderWithExpectedSize = ImmutableList.builderWithExpectedSize(readUnsignedExpGolombCodedInt);
        int[] iArr = new int[i];
        for (int i2 = 0; i2 < readUnsignedExpGolombCodedInt; i2++) {
            builderWithExpectedSize.add((Object) parseH265RepFormat(parsableNalUnitBitArray));
        }
        if (readUnsignedExpGolombCodedInt <= 1 || !parsableNalUnitBitArray.readBit()) {
            for (int i3 = 1; i3 < i; i3++) {
                iArr[i3] = Math.min(i3, readUnsignedExpGolombCodedInt - 1);
            }
        } else {
            int log2 = DoubleMath.log2((double) readUnsignedExpGolombCodedInt, RoundingMode.CEILING);
            for (int i4 = 1; i4 < i; i4++) {
                iArr[i4] = parsableNalUnitBitArray.readBits(log2);
            }
        }
        return new H265RepFormatsAndIndices(builderWithExpectedSize.build(), iArr);
    }

    private static H265RepFormat parseH265RepFormat(ParsableNalUnitBitArray parsableNalUnitBitArray) {
        int i;
        int i2;
        int i3;
        int readBits = parsableNalUnitBitArray.readBits(16);
        int readBits2 = parsableNalUnitBitArray.readBits(16);
        if (parsableNalUnitBitArray.readBit()) {
            int readBits3 = parsableNalUnitBitArray.readBits(2);
            if (readBits3 == 3) {
                parsableNalUnitBitArray.skipBit();
            }
            int readBits4 = parsableNalUnitBitArray.readBits(4);
            i = parsableNalUnitBitArray.readBits(4);
            i2 = readBits4;
            i3 = readBits3;
        } else {
            i3 = 0;
            i2 = 0;
            i = 0;
        }
        if (parsableNalUnitBitArray.readBit()) {
            int readUnsignedExpGolombCodedInt = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            int readUnsignedExpGolombCodedInt2 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            int readUnsignedExpGolombCodedInt3 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            int readUnsignedExpGolombCodedInt4 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            readBits = applyConformanceWindowToWidth(readBits, i3, readUnsignedExpGolombCodedInt, readUnsignedExpGolombCodedInt2);
            readBits2 = applyConformanceWindowToHeight(readBits2, i3, readUnsignedExpGolombCodedInt3, readUnsignedExpGolombCodedInt4);
        }
        return new H265RepFormat(i3, i2, i, readBits, readBits2);
    }

    private static void skipH265DpbSize(ParsableNalUnitBitArray parsableNalUnitBitArray, int i, int[] iArr, int[] iArr2, boolean[][] zArr) {
        for (int i2 = 1; i2 < i; i2++) {
            boolean readBit = parsableNalUnitBitArray.readBit();
            int i3 = 0;
            while (i3 < iArr[i2]) {
                if ((i3 <= 0 || !readBit) ? i3 == 0 : parsableNalUnitBitArray.readBit()) {
                    for (int i4 = 0; i4 < iArr2[i2]; i4++) {
                        if (zArr[i2][i4]) {
                            parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                        }
                    }
                    parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                    parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                }
                i3++;
            }
        }
    }

    private static void skipToH265VuiPresentFlagAfterDpbSize(ParsableNalUnitBitArray parsableNalUnitBitArray, int i, boolean[][] zArr) {
        int readUnsignedExpGolombCodedInt = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt() + 2;
        if (parsableNalUnitBitArray.readBit()) {
            parsableNalUnitBitArray.skipBits(readUnsignedExpGolombCodedInt);
        } else {
            for (int i2 = 1; i2 < i; i2++) {
                for (int i3 = 0; i3 < i2; i3++) {
                    if (zArr[i2][i3]) {
                        parsableNalUnitBitArray.skipBits(readUnsignedExpGolombCodedInt);
                    }
                }
            }
        }
        int readUnsignedExpGolombCodedInt2 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        for (int i4 = 1; i4 <= readUnsignedExpGolombCodedInt2; i4++) {
            parsableNalUnitBitArray.skipBits(8);
        }
    }

    private static H265VideoSignalInfosAndIndices parseH265VideoSignalInfosAndIndices(ParsableNalUnitBitArray parsableNalUnitBitArray, int i, int i2, int[] iArr) {
        if (!parsableNalUnitBitArray.readBit() ? parsableNalUnitBitArray.readBit() : true) {
            parsableNalUnitBitArray.skipBit();
        }
        boolean readBit = parsableNalUnitBitArray.readBit();
        boolean readBit2 = parsableNalUnitBitArray.readBit();
        if (readBit || readBit2) {
            for (int i3 = 0; i3 < i2; i3++) {
                for (int i4 = 0; i4 < iArr[i3]; i4++) {
                    boolean readBit3 = readBit ? parsableNalUnitBitArray.readBit() : false;
                    boolean readBit4 = readBit2 ? parsableNalUnitBitArray.readBit() : false;
                    if (readBit3) {
                        parsableNalUnitBitArray.skipBits(32);
                    }
                    if (readBit4) {
                        parsableNalUnitBitArray.skipBits(18);
                    }
                }
            }
        }
        boolean readBit5 = parsableNalUnitBitArray.readBit();
        int readBits = readBit5 ? parsableNalUnitBitArray.readBits(4) + 1 : i;
        ImmutableList.Builder builderWithExpectedSize = ImmutableList.builderWithExpectedSize(readBits);
        int[] iArr2 = new int[i];
        for (int i5 = 0; i5 < readBits; i5++) {
            builderWithExpectedSize.add((Object) parseH265VideoSignalInfo(parsableNalUnitBitArray));
        }
        if (readBit5 && readBits > 1) {
            for (int i6 = 0; i6 < i; i6++) {
                iArr2[i6] = parsableNalUnitBitArray.readBits(4);
            }
        }
        return new H265VideoSignalInfosAndIndices(builderWithExpectedSize.build(), iArr2);
    }

    private static H265VideoSignalInfo parseH265VideoSignalInfo(ParsableNalUnitBitArray parsableNalUnitBitArray) {
        parsableNalUnitBitArray.skipBits(3);
        int i = parsableNalUnitBitArray.readBit() ? 1 : 2;
        int isoColorPrimariesToColorSpace = ColorInfo.isoColorPrimariesToColorSpace(parsableNalUnitBitArray.readBits(8));
        int isoTransferCharacteristicsToColorTransfer = ColorInfo.isoTransferCharacteristicsToColorTransfer(parsableNalUnitBitArray.readBits(8));
        parsableNalUnitBitArray.skipBits(8);
        return new H265VideoSignalInfo(isoColorPrimariesToColorSpace, i, isoTransferCharacteristicsToColorTransfer);
    }

    private static void skipScalingList(ParsableNalUnitBitArray parsableNalUnitBitArray, int i) {
        int i2 = 8;
        int i3 = 8;
        for (int i4 = 0; i4 < i; i4++) {
            if (i2 != 0) {
                i2 = ((parsableNalUnitBitArray.readSignedExpGolombCodedInt() + i3) + 256) % 256;
            }
            if (i2 != 0) {
                i3 = i2;
            }
        }
    }

    private static void skipHrdParameters(ParsableNalUnitBitArray parsableNalUnitBitArray) {
        int readUnsignedExpGolombCodedInt = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt() + 1;
        parsableNalUnitBitArray.skipBits(8);
        for (int i = 0; i < readUnsignedExpGolombCodedInt; i++) {
            parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            parsableNalUnitBitArray.skipBit();
        }
        parsableNalUnitBitArray.skipBits(20);
    }

    private static void skipH265ScalingList(ParsableNalUnitBitArray parsableNalUnitBitArray) {
        for (int i = 0; i < 4; i++) {
            int i2 = 0;
            while (i2 < 6) {
                int i3 = 1;
                if (!parsableNalUnitBitArray.readBit()) {
                    parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                } else {
                    int min = Math.min(64, 1 << ((i << 1) + 4));
                    if (i > 1) {
                        parsableNalUnitBitArray.readSignedExpGolombCodedInt();
                    }
                    for (int i4 = 0; i4 < min; i4++) {
                        parsableNalUnitBitArray.readSignedExpGolombCodedInt();
                    }
                }
                if (i == 3) {
                    i3 = 3;
                }
                i2 += i3;
            }
        }
    }

    private static void skipH265ShortTermReferencePictureSets(ParsableNalUnitBitArray parsableNalUnitBitArray) {
        int readUnsignedExpGolombCodedInt = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        int[] iArr = new int[0];
        int[] iArr2 = new int[0];
        int i = -1;
        int i2 = 0;
        int i3 = -1;
        while (i2 < readUnsignedExpGolombCodedInt) {
            if (i2 != 0 && parsableNalUnitBitArray.readBit()) {
                int i4 = i + i3;
                int readUnsignedExpGolombCodedInt2 = (1 - ((parsableNalUnitBitArray.readBit() ? 1 : 0) * true)) * (parsableNalUnitBitArray.readUnsignedExpGolombCodedInt() + 1);
                int i5 = i4 + 1;
                boolean[] zArr = new boolean[i5];
                for (int i6 = 0; i6 <= i4; i6++) {
                    if (!parsableNalUnitBitArray.readBit()) {
                        zArr[i6] = parsableNalUnitBitArray.readBit();
                    } else {
                        zArr[i6] = true;
                    }
                }
                int[] iArr3 = new int[i5];
                int[] iArr4 = new int[i5];
                int i7 = 0;
                for (int i8 = i3 - 1; i8 >= 0; i8--) {
                    int i9 = iArr2[i8] + readUnsignedExpGolombCodedInt2;
                    if (i9 < 0 && zArr[i + i8]) {
                        iArr3[i7] = i9;
                        i7++;
                    }
                }
                if (readUnsignedExpGolombCodedInt2 < 0 && zArr[i4]) {
                    iArr3[i7] = readUnsignedExpGolombCodedInt2;
                    i7++;
                }
                for (int i10 = 0; i10 < i; i10++) {
                    int i11 = iArr[i10] + readUnsignedExpGolombCodedInt2;
                    if (i11 < 0 && zArr[i10]) {
                        iArr3[i7] = i11;
                        i7++;
                    }
                }
                int[] copyOf = Arrays.copyOf(iArr3, i7);
                int i12 = 0;
                for (int i13 = i - 1; i13 >= 0; i13--) {
                    int i14 = iArr[i13] + readUnsignedExpGolombCodedInt2;
                    if (i14 > 0 && zArr[i13]) {
                        iArr4[i12] = i14;
                        i12++;
                    }
                }
                if (readUnsignedExpGolombCodedInt2 > 0 && zArr[i4]) {
                    iArr4[i12] = readUnsignedExpGolombCodedInt2;
                    i12++;
                }
                for (int i15 = 0; i15 < i3; i15++) {
                    int i16 = iArr2[i15] + readUnsignedExpGolombCodedInt2;
                    if (i16 > 0 && zArr[i + i15]) {
                        iArr4[i12] = i16;
                        i12++;
                    }
                }
                iArr2 = Arrays.copyOf(iArr4, i12);
                iArr = copyOf;
                i = i7;
                i3 = i12;
            } else {
                int readUnsignedExpGolombCodedInt3 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                int readUnsignedExpGolombCodedInt4 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                int[] iArr5 = new int[readUnsignedExpGolombCodedInt3];
                int i17 = 0;
                while (i17 < readUnsignedExpGolombCodedInt3) {
                    iArr5[i17] = (i17 > 0 ? iArr5[i17 - 1] : 0) - (parsableNalUnitBitArray.readUnsignedExpGolombCodedInt() + 1);
                    parsableNalUnitBitArray.skipBit();
                    i17++;
                }
                int[] iArr6 = new int[readUnsignedExpGolombCodedInt4];
                int i18 = 0;
                while (i18 < readUnsignedExpGolombCodedInt4) {
                    iArr6[i18] = (i18 > 0 ? iArr6[i18 - 1] : 0) + parsableNalUnitBitArray.readUnsignedExpGolombCodedInt() + 1;
                    parsableNalUnitBitArray.skipBit();
                    i18++;
                }
                int[] iArr7 = iArr5;
                i = readUnsignedExpGolombCodedInt3;
                iArr = iArr7;
                int[] iArr8 = iArr6;
                i3 = readUnsignedExpGolombCodedInt4;
                iArr2 = iArr8;
            }
            i2++;
        }
    }

    private NalUnitUtil() {
    }
}
