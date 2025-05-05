package com.facebook.imageutils;

import com.google.mlkit.common.MlKitException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0004H\u0007J\u0010\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u0013H\u0007J\u0012\u0010\u0011\u001a\u00020\u00042\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0007J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0004H\u0002J\u0010\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u0018\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u001b\u001a\u00020\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/facebook/imageutils/JfifUtil;", "", "()V", "APP1_EXIF_MAGIC", "", "MARKER_APP1", "MARKER_EOI", "MARKER_ESCAPE_BYTE", "MARKER_FIRST_BYTE", "MARKER_RST0", "MARKER_RST7", "MARKER_SOFn", "MARKER_SOI", "MARKER_SOS", "MARKER_TEM", "getAutoRotateAngleFromOrientation", "orientation", "getOrientation", "inputStream", "Ljava/io/InputStream;", "jpeg", "", "isSOFn", "", "marker", "moveToAPP1EXIF", "moveToMarker", "markerToFind", "imagepipeline-base_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: JfifUtil.kt */
public final class JfifUtil {
    public static final int APP1_EXIF_MAGIC = 1165519206;
    public static final JfifUtil INSTANCE = new JfifUtil();
    public static final int MARKER_APP1 = 225;
    public static final int MARKER_EOI = 217;
    public static final int MARKER_ESCAPE_BYTE = 0;
    public static final int MARKER_FIRST_BYTE = 255;
    public static final int MARKER_RST0 = 208;
    public static final int MARKER_RST7 = 215;
    public static final int MARKER_SOFn = 192;
    public static final int MARKER_SOI = 216;
    public static final int MARKER_SOS = 218;
    public static final int MARKER_TEM = 1;

    private final boolean isSOFn(int i) {
        switch (i) {
            case 192:
            case 193:
            case 194:
            case 195:
            case 197:
            case 198:
            case 199:
            case 201:
            case 202:
            case 203:
            case MlKitException.CODE_SCANNER_PIPELINE_INITIALIZATION_ERROR:
            case MlKitException.CODE_SCANNER_PIPELINE_INFERENCE_ERROR:
            case MlKitException.CODE_SCANNER_GOOGLE_PLAY_SERVICES_VERSION_TOO_OLD:
                return true;
            default:
                return false;
        }
    }

    private JfifUtil() {
    }

    @JvmStatic
    public static final int getAutoRotateAngleFromOrientation(int i) {
        return TiffUtil.getAutoRotateAngleFromOrientation(i);
    }

    @JvmStatic
    public static final int getOrientation(byte[] bArr) {
        return getOrientation((InputStream) new ByteArrayInputStream(bArr));
    }

    @JvmStatic
    public static final int getOrientation(InputStream inputStream) {
        Intrinsics.checkNotNullParameter(inputStream, "inputStream");
        try {
            int moveToAPP1EXIF = INSTANCE.moveToAPP1EXIF(inputStream);
            if (moveToAPP1EXIF == 0) {
                return 0;
            }
            return TiffUtil.readOrientationFromTIFF(inputStream, moveToAPP1EXIF);
        } catch (IOException unused) {
            return 0;
        }
    }

    @JvmStatic
    public static final boolean moveToMarker(InputStream inputStream, int i) throws IOException {
        Intrinsics.checkNotNullParameter(inputStream, "inputStream");
        while (StreamProcessor.readPackedInt(inputStream, 1, false) == 255) {
            int i2 = 255;
            while (i2 == 255) {
                i2 = StreamProcessor.readPackedInt(inputStream, 1, false);
            }
            if ((i != 192 || !INSTANCE.isSOFn(i2)) && i2 != i) {
                if (!(i2 == 1 || i2 == 216)) {
                    if (i2 == 217 || i2 == 218) {
                        break;
                    }
                    inputStream.skip((long) (StreamProcessor.readPackedInt(inputStream, 2, false) - 2));
                }
            } else {
                return true;
            }
        }
        return false;
    }

    private final int moveToAPP1EXIF(InputStream inputStream) throws IOException {
        int readPackedInt;
        if (moveToMarker(inputStream, MARKER_APP1) && (readPackedInt = StreamProcessor.readPackedInt(inputStream, 2, false) - 2) > 6) {
            int readPackedInt2 = StreamProcessor.readPackedInt(inputStream, 4, false);
            int readPackedInt3 = StreamProcessor.readPackedInt(inputStream, 2, false);
            int i = (readPackedInt - 4) - 2;
            if (readPackedInt2 == 1165519206 && readPackedInt3 == 0) {
                return i;
            }
            return 0;
        }
        return 0;
    }
}
