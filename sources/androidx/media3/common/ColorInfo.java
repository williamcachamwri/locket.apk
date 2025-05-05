package androidx.media3.common;

import android.os.Bundle;
import androidx.media3.common.util.Util;
import java.util.Arrays;
import org.checkerframework.dataflow.qual.Pure;

public final class ColorInfo {
    private static final String FIELD_CHROMA_BITDEPTH = Util.intToStringMaxRadix(5);
    private static final String FIELD_COLOR_RANGE = Util.intToStringMaxRadix(1);
    private static final String FIELD_COLOR_SPACE = Util.intToStringMaxRadix(0);
    private static final String FIELD_COLOR_TRANSFER = Util.intToStringMaxRadix(2);
    private static final String FIELD_HDR_STATIC_INFO = Util.intToStringMaxRadix(3);
    private static final String FIELD_LUMA_BITDEPTH = Util.intToStringMaxRadix(4);
    public static final ColorInfo SDR_BT709_LIMITED = new Builder().setColorSpace(1).setColorRange(2).setColorTransfer(3).build();
    public static final ColorInfo SRGB_BT709_FULL = new Builder().setColorSpace(1).setColorRange(1).setColorTransfer(2).build();
    public final int chromaBitdepth;
    public final int colorRange;
    public final int colorSpace;
    public final int colorTransfer;
    private int hashCode;
    public final byte[] hdrStaticInfo;
    public final int lumaBitdepth;

    public static int colorSpaceToIsoColorPrimaries(int i) {
        if (i != 2) {
            return i != 6 ? 1 : 9;
        }
        return 5;
    }

    public static int colorSpaceToIsoMatrixCoefficients(int i) {
        if (i != 2) {
            return i != 6 ? 1 : 9;
        }
        return 6;
    }

    public static int colorTransferToIsoTransferCharacteristics(int i) {
        if (i == 1) {
            return 8;
        }
        if (i == 2) {
            return 13;
        }
        if (i == 6) {
            return 16;
        }
        if (i != 7) {
            return i != 10 ? 1 : 4;
        }
        return 18;
    }

    @Pure
    public static int isoColorPrimariesToColorSpace(int i) {
        if (i == 1) {
            return 1;
        }
        if (i != 9) {
            return (i == 4 || i == 5 || i == 6 || i == 7) ? 2 : -1;
        }
        return 6;
    }

    @Pure
    public static int isoTransferCharacteristicsToColorTransfer(int i) {
        if (i == 1) {
            return 3;
        }
        if (i == 4) {
            return 10;
        }
        if (i == 13) {
            return 2;
        }
        if (i == 16) {
            return 6;
        }
        if (i != 18) {
            return (i == 6 || i == 7) ? 3 : -1;
        }
        return 7;
    }

    public static final class Builder {
        private int chromaBitdepth;
        private int colorRange;
        private int colorSpace;
        private int colorTransfer;
        private byte[] hdrStaticInfo;
        private int lumaBitdepth;

        public Builder() {
            this.colorSpace = -1;
            this.colorRange = -1;
            this.colorTransfer = -1;
            this.lumaBitdepth = -1;
            this.chromaBitdepth = -1;
        }

        private Builder(ColorInfo colorInfo) {
            this.colorSpace = colorInfo.colorSpace;
            this.colorRange = colorInfo.colorRange;
            this.colorTransfer = colorInfo.colorTransfer;
            this.hdrStaticInfo = colorInfo.hdrStaticInfo;
            this.lumaBitdepth = colorInfo.lumaBitdepth;
            this.chromaBitdepth = colorInfo.chromaBitdepth;
        }

        public Builder setColorSpace(int i) {
            this.colorSpace = i;
            return this;
        }

        public Builder setColorRange(int i) {
            this.colorRange = i;
            return this;
        }

        public Builder setColorTransfer(int i) {
            this.colorTransfer = i;
            return this;
        }

        public Builder setHdrStaticInfo(byte[] bArr) {
            this.hdrStaticInfo = bArr;
            return this;
        }

        public Builder setLumaBitdepth(int i) {
            this.lumaBitdepth = i;
            return this;
        }

        public Builder setChromaBitdepth(int i) {
            this.chromaBitdepth = i;
            return this;
        }

        public ColorInfo build() {
            return new ColorInfo(this.colorSpace, this.colorRange, this.colorTransfer, this.hdrStaticInfo, this.lumaBitdepth, this.chromaBitdepth);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0014, code lost:
        r1 = r4.colorTransfer;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x001f, code lost:
        r1 = r4.chromaBitdepth;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0027, code lost:
        r4 = r4.lumaBitdepth;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x000e, code lost:
        r1 = r4.colorRange;
     */
    @org.checkerframework.checker.nullness.qual.EnsuresNonNullIf(expression = {"#1"}, result = false)
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean isEquivalentToAssumedSdrDefault(androidx.media3.common.ColorInfo r4) {
        /*
            r0 = 1
            if (r4 != 0) goto L_0x0004
            return r0
        L_0x0004:
            int r1 = r4.colorSpace
            r2 = 2
            r3 = -1
            if (r1 == r3) goto L_0x000e
            if (r1 == r0) goto L_0x000e
            if (r1 != r2) goto L_0x002e
        L_0x000e:
            int r1 = r4.colorRange
            if (r1 == r3) goto L_0x0014
            if (r1 != r2) goto L_0x002e
        L_0x0014:
            int r1 = r4.colorTransfer
            if (r1 == r3) goto L_0x001b
            r2 = 3
            if (r1 != r2) goto L_0x002e
        L_0x001b:
            byte[] r1 = r4.hdrStaticInfo
            if (r1 != 0) goto L_0x002e
            int r1 = r4.chromaBitdepth
            r2 = 8
            if (r1 == r3) goto L_0x0027
            if (r1 != r2) goto L_0x002e
        L_0x0027:
            int r4 = r4.lumaBitdepth
            if (r4 == r3) goto L_0x002f
            if (r4 != r2) goto L_0x002e
            goto L_0x002f
        L_0x002e:
            r0 = 0
        L_0x002f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.common.ColorInfo.isEquivalentToAssumedSdrDefault(androidx.media3.common.ColorInfo):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:1:0x0002, code lost:
        r1 = r1.colorTransfer;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean isTransferHdr(androidx.media3.common.ColorInfo r1) {
        /*
            if (r1 == 0) goto L_0x000c
            int r1 = r1.colorTransfer
            r0 = 7
            if (r1 == r0) goto L_0x000a
            r0 = 6
            if (r1 != r0) goto L_0x000c
        L_0x000a:
            r1 = 1
            goto L_0x000d
        L_0x000c:
            r1 = 0
        L_0x000d:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.common.ColorInfo.isTransferHdr(androidx.media3.common.ColorInfo):boolean");
    }

    private ColorInfo(int i, int i2, int i3, byte[] bArr, int i4, int i5) {
        this.colorSpace = i;
        this.colorRange = i2;
        this.colorTransfer = i3;
        this.hdrStaticInfo = bArr;
        this.lumaBitdepth = i4;
        this.chromaBitdepth = i5;
    }

    public Builder buildUpon() {
        return new Builder();
    }

    public boolean isValid() {
        return isBitdepthValid() || isDataSpaceValid();
    }

    public boolean isBitdepthValid() {
        return (this.lumaBitdepth == -1 || this.chromaBitdepth == -1) ? false : true;
    }

    public boolean isDataSpaceValid() {
        return (this.colorSpace == -1 || this.colorRange == -1 || this.colorTransfer == -1) ? false : true;
    }

    public String toLogString() {
        return (isDataSpaceValid() ? Util.formatInvariant("%s/%s/%s", colorSpaceToString(this.colorSpace), colorRangeToString(this.colorRange), colorTransferToString(this.colorTransfer)) : "NA/NA/NA") + "/" + (isBitdepthValid() ? this.lumaBitdepth + "/" + this.chromaBitdepth : "NA/NA");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ColorInfo colorInfo = (ColorInfo) obj;
        if (this.colorSpace == colorInfo.colorSpace && this.colorRange == colorInfo.colorRange && this.colorTransfer == colorInfo.colorTransfer && Arrays.equals(this.hdrStaticInfo, colorInfo.hdrStaticInfo) && this.lumaBitdepth == colorInfo.lumaBitdepth && this.chromaBitdepth == colorInfo.chromaBitdepth) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        if (this.hashCode == 0) {
            this.hashCode = ((((((((((527 + this.colorSpace) * 31) + this.colorRange) * 31) + this.colorTransfer) * 31) + Arrays.hashCode(this.hdrStaticInfo)) * 31) + this.lumaBitdepth) * 31) + this.chromaBitdepth;
        }
        return this.hashCode;
    }

    public String toString() {
        return "ColorInfo(" + colorSpaceToString(this.colorSpace) + ", " + colorRangeToString(this.colorRange) + ", " + colorTransferToString(this.colorTransfer) + ", " + (this.hdrStaticInfo != null) + ", " + lumaBitdepthToString(this.lumaBitdepth) + ", " + chromaBitdepthToString(this.chromaBitdepth) + ")";
    }

    private static String lumaBitdepthToString(int i) {
        return i != -1 ? i + "bit Luma" : "NA";
    }

    private static String chromaBitdepthToString(int i) {
        return i != -1 ? i + "bit Chroma" : "NA";
    }

    private static String colorSpaceToString(int i) {
        if (i == -1) {
            return "Unset color space";
        }
        if (i == 6) {
            return "BT2020";
        }
        if (i != 1) {
            return i != 2 ? "Undefined color space " + i : "BT601";
        }
        return "BT709";
    }

    private static String colorTransferToString(int i) {
        if (i == -1) {
            return "Unset color transfer";
        }
        if (i == 10) {
            return "Gamma 2.2";
        }
        if (i == 1) {
            return "Linear";
        }
        if (i == 2) {
            return "sRGB";
        }
        if (i == 3) {
            return "SDR SMPTE 170M";
        }
        if (i != 6) {
            return i != 7 ? "Undefined color transfer " + i : "HLG";
        }
        return "ST2084 PQ";
    }

    private static String colorRangeToString(int i) {
        if (i == -1) {
            return "Unset color range";
        }
        if (i != 1) {
            return i != 2 ? "Undefined color range " + i : "Limited range";
        }
        return "Full range";
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putInt(FIELD_COLOR_SPACE, this.colorSpace);
        bundle.putInt(FIELD_COLOR_RANGE, this.colorRange);
        bundle.putInt(FIELD_COLOR_TRANSFER, this.colorTransfer);
        bundle.putByteArray(FIELD_HDR_STATIC_INFO, this.hdrStaticInfo);
        bundle.putInt(FIELD_LUMA_BITDEPTH, this.lumaBitdepth);
        bundle.putInt(FIELD_CHROMA_BITDEPTH, this.chromaBitdepth);
        return bundle;
    }

    public static ColorInfo fromBundle(Bundle bundle) {
        return new ColorInfo(bundle.getInt(FIELD_COLOR_SPACE, -1), bundle.getInt(FIELD_COLOR_RANGE, -1), bundle.getInt(FIELD_COLOR_TRANSFER, -1), bundle.getByteArray(FIELD_HDR_STATIC_INFO), bundle.getInt(FIELD_LUMA_BITDEPTH, -1), bundle.getInt(FIELD_CHROMA_BITDEPTH, -1));
    }
}
