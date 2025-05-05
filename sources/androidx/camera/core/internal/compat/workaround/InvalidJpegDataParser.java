package androidx.camera.core.internal.compat.workaround;

import androidx.camera.core.internal.compat.quirk.DeviceQuirks;
import androidx.camera.core.internal.compat.quirk.LargeJpegImageQuirk;

public class InvalidJpegDataParser {
    private final LargeJpegImageQuirk mQuirk = ((LargeJpegImageQuirk) DeviceQuirks.get(LargeJpegImageQuirk.class));

    public int getValidDataLength(byte[] bArr) {
        LargeJpegImageQuirk largeJpegImageQuirk = this.mQuirk;
        if (largeJpegImageQuirk == null || !largeJpegImageQuirk.shouldCheckInvalidJpegData(bArr)) {
            return bArr.length;
        }
        int jfifEoiMarkEndPosition = getJfifEoiMarkEndPosition(bArr);
        if (jfifEoiMarkEndPosition != -1) {
            return jfifEoiMarkEndPosition;
        }
        return bArr.length;
    }

    public static int getJfifEoiMarkEndPosition(byte[] bArr) {
        byte b;
        int i = 2;
        while (i + 4 <= bArr.length && (b = bArr[i]) == -1) {
            int i2 = i + 2;
            byte b2 = ((bArr[i2] & 255) << 8) | (bArr[i + 3] & 255);
            if (b == -1 && bArr[i + 1] == -38) {
                while (true) {
                    int i3 = i2 + 2;
                    if (i3 > bArr.length) {
                        return -1;
                    }
                    if (bArr[i2] == -1 && bArr[i2 + 1] == -39) {
                        return i3;
                    }
                    i2++;
                }
            } else {
                i += b2 + 2;
            }
        }
        return -1;
    }
}
