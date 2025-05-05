package com.facebook.imageutils;

import com.facebook.common.logging.FLog;
import com.facebook.imagepipeline.common.RotationOptions;
import java.io.IOException;
import java.io.InputStream;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001:\u0001\u0018B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0006H\u0007J \u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J(\u0010\u0012\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u0006H\u0002J\u0018\u0010\u0014\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0006H\u0007J \u0010\u0015\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u0017H\u0002R\u0012\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/facebook/imageutils/TiffUtil;", "", "()V", "TAG", "Ljava/lang/Class;", "TIFF_BYTE_ORDER_BIG_END", "", "TIFF_BYTE_ORDER_LITTLE_END", "TIFF_TAG_ORIENTATION", "TIFF_TYPE_SHORT", "getAutoRotateAngleFromOrientation", "orientation", "getOrientationFromTiffEntry", "stream", "Ljava/io/InputStream;", "length", "isLittleEndian", "", "moveToTiffEntryWithTag", "tagToFind", "readOrientationFromTIFF", "readTiffHeader", "tiffHeader", "Lcom/facebook/imageutils/TiffUtil$TiffHeader;", "TiffHeader", "imagepipeline-base_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: TiffUtil.kt */
public final class TiffUtil {
    public static final TiffUtil INSTANCE = new TiffUtil();
    private static final Class<?> TAG = TiffUtil.class;
    public static final int TIFF_BYTE_ORDER_BIG_END = 1296891946;
    public static final int TIFF_BYTE_ORDER_LITTLE_END = 1229531648;
    public static final int TIFF_TAG_ORIENTATION = 274;
    public static final int TIFF_TYPE_SHORT = 3;

    @JvmStatic
    public static final int getAutoRotateAngleFromOrientation(int i) {
        if (i == 0 || i == 1) {
            return 0;
        }
        if (i == 3) {
            return RotationOptions.ROTATE_180;
        }
        if (i != 6) {
            return i != 8 ? 0 : 270;
        }
        return 90;
    }

    private TiffUtil() {
    }

    @JvmStatic
    public static final int readOrientationFromTIFF(InputStream inputStream, int i) throws IOException {
        Intrinsics.checkNotNullParameter(inputStream, "stream");
        TiffHeader tiffHeader = new TiffHeader();
        TiffUtil tiffUtil = INSTANCE;
        int readTiffHeader = tiffUtil.readTiffHeader(inputStream, i, tiffHeader);
        int firstIfdOffset = tiffHeader.getFirstIfdOffset() - 8;
        if (readTiffHeader == 0 || firstIfdOffset > readTiffHeader) {
            return 0;
        }
        inputStream.skip((long) firstIfdOffset);
        return tiffUtil.getOrientationFromTiffEntry(inputStream, tiffUtil.moveToTiffEntryWithTag(inputStream, readTiffHeader - firstIfdOffset, tiffHeader.isLittleEndian(), TIFF_TAG_ORIENTATION), tiffHeader.isLittleEndian());
    }

    private final int readTiffHeader(InputStream inputStream, int i, TiffHeader tiffHeader) throws IOException {
        if (i <= 8) {
            return 0;
        }
        tiffHeader.setByteOrder(StreamProcessor.readPackedInt(inputStream, 4, false));
        int i2 = i - 4;
        if (tiffHeader.getByteOrder() == 1229531648 || tiffHeader.getByteOrder() == 1296891946) {
            tiffHeader.setLittleEndian(tiffHeader.getByteOrder() == 1229531648);
            tiffHeader.setFirstIfdOffset(StreamProcessor.readPackedInt(inputStream, 4, tiffHeader.isLittleEndian()));
            int i3 = i2 - 4;
            if (tiffHeader.getFirstIfdOffset() >= 8 && tiffHeader.getFirstIfdOffset() - 8 <= i3) {
                return i3;
            }
            FLog.e(TAG, "Invalid offset");
            return 0;
        }
        FLog.e(TAG, "Invalid TIFF header");
        return 0;
    }

    private final int moveToTiffEntryWithTag(InputStream inputStream, int i, boolean z, int i2) throws IOException {
        if (i < 14) {
            return 0;
        }
        int readPackedInt = StreamProcessor.readPackedInt(inputStream, 2, z);
        int i3 = i - 2;
        while (true) {
            int i4 = readPackedInt - 1;
            if (readPackedInt <= 0 || i3 < 12) {
                return 0;
            }
            int i5 = i3 - 2;
            if (StreamProcessor.readPackedInt(inputStream, 2, z) == i2) {
                return i5;
            }
            inputStream.skip(10);
            i3 = i5 - 10;
            readPackedInt = i4;
        }
        return 0;
    }

    private final int getOrientationFromTiffEntry(InputStream inputStream, int i, boolean z) throws IOException {
        if (i >= 10 && StreamProcessor.readPackedInt(inputStream, 2, z) == 3 && StreamProcessor.readPackedInt(inputStream, 4, z) == 1) {
            return StreamProcessor.readPackedInt(inputStream, 2, z);
        }
        return 0;
    }

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001a\u0010\f\u001a\u00020\rX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Lcom/facebook/imageutils/TiffUtil$TiffHeader;", "", "()V", "byteOrder", "", "getByteOrder", "()I", "setByteOrder", "(I)V", "firstIfdOffset", "getFirstIfdOffset", "setFirstIfdOffset", "isLittleEndian", "", "()Z", "setLittleEndian", "(Z)V", "imagepipeline-base_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: TiffUtil.kt */
    private static final class TiffHeader {
        private int byteOrder;
        private int firstIfdOffset;
        private boolean isLittleEndian;

        public final boolean isLittleEndian() {
            return this.isLittleEndian;
        }

        public final void setLittleEndian(boolean z) {
            this.isLittleEndian = z;
        }

        public final int getByteOrder() {
            return this.byteOrder;
        }

        public final void setByteOrder(int i) {
            this.byteOrder = i;
        }

        public final int getFirstIfdOffset() {
            return this.firstIfdOffset;
        }

        public final void setFirstIfdOffset(int i) {
            this.firstIfdOffset = i;
        }
    }
}
