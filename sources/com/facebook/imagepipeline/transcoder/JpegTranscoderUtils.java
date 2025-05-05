package com.facebook.imagepipeline.transcoder;

import android.graphics.Matrix;
import com.facebook.common.internal.ImmutableList;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.common.RotationOptions;
import com.facebook.imagepipeline.image.EncodedImage;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0004H\u0007J\"\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u0004H\u0007J\u0010\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u0018\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0018\u001a\u00020\u0019H\u0007J\u0018\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0018\u001a\u00020\u0019H\u0007J*\u0010\u001e\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u001c2\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001f\u001a\u00020 H\u0007J\u001a\u0010!\u001a\u0004\u0018\u00010\"2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u001cH\u0007J\u0012\u0010#\u001a\u0004\u0018\u00010\"2\u0006\u0010$\u001a\u00020\u0004H\u0002J\u0010\u0010%\u001a\u00020 2\u0006\u0010&\u001a\u00020\u0004H\u0007J\u0010\u0010'\u001a\u00020 2\u0006\u0010(\u001a\u00020\u0004H\u0007J\u0018\u0010)\u001a\u00020\u00042\u0006\u0010*\u001a\u00020\u00122\u0006\u0010+\u001a\u00020\u0012H\u0007R\u0016\u0010\u0003\u001a\u00020\u00048\u0006XT¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0002R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u001e\u0010\u0007\u001a\u0010\u0012\f\u0012\n \t*\u0004\u0018\u00010\u00040\u00040\b8\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006,"}, d2 = {"Lcom/facebook/imagepipeline/transcoder/JpegTranscoderUtils;", "", "()V", "DEFAULT_JPEG_QUALITY", "", "getDEFAULT_JPEG_QUALITY$annotations", "FULL_ROUND", "INVERTED_EXIF_ORIENTATIONS", "Lcom/facebook/common/internal/ImmutableList;", "kotlin.jvm.PlatformType", "MAX_QUALITY", "MAX_SCALE_NUMERATOR", "MIN_QUALITY", "MIN_SCALE_NUMERATOR", "SCALE_DENOMINATOR", "calculateDownsampleNumerator", "downsampleRatio", "determineResizeRatio", "", "resizeOptions", "Lcom/facebook/imagepipeline/common/ResizeOptions;", "width", "height", "extractOrientationFromMetadata", "encodedImage", "Lcom/facebook/imagepipeline/image/EncodedImage;", "getForceRotatedInvertedExifOrientation", "rotationOptions", "Lcom/facebook/imagepipeline/common/RotationOptions;", "getRotationAngle", "getSoftwareNumerator", "resizingEnabled", "", "getTransformationMatrix", "Landroid/graphics/Matrix;", "getTransformationMatrixFromInvertedExif", "orientation", "isExifOrientationAllowed", "exifOrientation", "isRotationAngleAllowed", "degrees", "roundNumerator", "maxRatio", "roundUpFraction", "imagepipeline-base_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: JpegTranscoderUtils.kt */
public final class JpegTranscoderUtils {
    public static final int DEFAULT_JPEG_QUALITY = 85;
    private static final int FULL_ROUND = 360;
    public static final JpegTranscoderUtils INSTANCE = new JpegTranscoderUtils();
    public static final ImmutableList<Integer> INVERTED_EXIF_ORIENTATIONS;
    public static final int MAX_QUALITY = 100;
    public static final int MAX_SCALE_NUMERATOR = 16;
    public static final int MIN_QUALITY = 0;
    public static final int MIN_SCALE_NUMERATOR = 1;
    public static final int SCALE_DENOMINATOR = 8;

    public static /* synthetic */ void getDEFAULT_JPEG_QUALITY$annotations() {
    }

    @JvmStatic
    public static final boolean isExifOrientationAllowed(int i) {
        switch (i) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
                return true;
            default:
                return false;
        }
    }

    @JvmStatic
    public static final int roundNumerator(float f, float f2) {
        return (int) (f2 + (f * ((float) 8)));
    }

    private JpegTranscoderUtils() {
    }

    static {
        ImmutableList<Integer> of = ImmutableList.of(2, 7, 4, 5);
        Intrinsics.checkNotNullExpressionValue(of, "of(\n          ExifInterf…ce.ORIENTATION_TRANSPOSE)");
        INVERTED_EXIF_ORIENTATIONS = of;
    }

    @JvmStatic
    public static final boolean isRotationAngleAllowed(int i) {
        return i >= 0 && i <= 270 && i % 90 == 0;
    }

    @JvmStatic
    public static final int getSoftwareNumerator(RotationOptions rotationOptions, ResizeOptions resizeOptions, EncodedImage encodedImage, boolean z) {
        Intrinsics.checkNotNullParameter(rotationOptions, "rotationOptions");
        Intrinsics.checkNotNullParameter(encodedImage, "encodedImage");
        if (!z || resizeOptions == null) {
            return 8;
        }
        int rotationAngle = getRotationAngle(rotationOptions, encodedImage);
        boolean z2 = false;
        int forceRotatedInvertedExifOrientation = INVERTED_EXIF_ORIENTATIONS.contains(Integer.valueOf(encodedImage.getExifOrientation())) ? getForceRotatedInvertedExifOrientation(rotationOptions, encodedImage) : 0;
        if (rotationAngle == 90 || rotationAngle == 270 || forceRotatedInvertedExifOrientation == 5 || forceRotatedInvertedExifOrientation == 7) {
            z2 = true;
        }
        int roundNumerator = roundNumerator(determineResizeRatio(resizeOptions, z2 ? encodedImage.getHeight() : encodedImage.getWidth(), z2 ? encodedImage.getWidth() : encodedImage.getHeight()), resizeOptions.roundUpFraction);
        if (roundNumerator > 8) {
            return 8;
        }
        if (roundNumerator < 1) {
            return 1;
        }
        return roundNumerator;
    }

    @JvmStatic
    public static final int getRotationAngle(RotationOptions rotationOptions, EncodedImage encodedImage) {
        Intrinsics.checkNotNullParameter(rotationOptions, "rotationOptions");
        Intrinsics.checkNotNullParameter(encodedImage, "encodedImage");
        if (!rotationOptions.rotationEnabled()) {
            return 0;
        }
        int extractOrientationFromMetadata = INSTANCE.extractOrientationFromMetadata(encodedImage);
        return rotationOptions.useImageMetadata() ? extractOrientationFromMetadata : (extractOrientationFromMetadata + rotationOptions.getForcedAngle()) % 360;
    }

    @JvmStatic
    public static final int getForceRotatedInvertedExifOrientation(RotationOptions rotationOptions, EncodedImage encodedImage) {
        Intrinsics.checkNotNullParameter(rotationOptions, "rotationOptions");
        Intrinsics.checkNotNullParameter(encodedImage, "encodedImage");
        int exifOrientation = encodedImage.getExifOrientation();
        ImmutableList<Integer> immutableList = INVERTED_EXIF_ORIENTATIONS;
        int indexOf = immutableList.indexOf(Integer.valueOf(exifOrientation));
        int i = 0;
        if (indexOf >= 0) {
            if (!rotationOptions.useImageMetadata()) {
                i = rotationOptions.getForcedAngle();
            }
            Object obj = immutableList.get((indexOf + (i / 90)) % immutableList.size());
            Intrinsics.checkNotNullExpressionValue(obj, "INVERTED_EXIF_ORIENTATIO…D_EXIF_ORIENTATIONS.size]");
            return ((Number) obj).intValue();
        }
        throw new IllegalArgumentException("Only accepts inverted exif orientations".toString());
    }

    @JvmStatic
    public static final float determineResizeRatio(ResizeOptions resizeOptions, int i, int i2) {
        if (resizeOptions == null) {
            return 1.0f;
        }
        float f = (float) i;
        float f2 = (float) i2;
        float max = Math.max(((float) resizeOptions.width) / f, ((float) resizeOptions.height) / f2);
        if (f * max > resizeOptions.maxBitmapSize) {
            max = resizeOptions.maxBitmapSize / f;
        }
        return f2 * max > resizeOptions.maxBitmapSize ? resizeOptions.maxBitmapSize / f2 : max;
    }

    @JvmStatic
    public static final int calculateDownsampleNumerator(int i) {
        return Math.max(1, 8 / i);
    }

    @JvmStatic
    public static final Matrix getTransformationMatrix(EncodedImage encodedImage, RotationOptions rotationOptions) {
        Intrinsics.checkNotNullParameter(encodedImage, "encodedImage");
        Intrinsics.checkNotNullParameter(rotationOptions, "rotationOptions");
        if (INVERTED_EXIF_ORIENTATIONS.contains(Integer.valueOf(encodedImage.getExifOrientation()))) {
            return INSTANCE.getTransformationMatrixFromInvertedExif(getForceRotatedInvertedExifOrientation(rotationOptions, encodedImage));
        }
        int rotationAngle = getRotationAngle(rotationOptions, encodedImage);
        if (rotationAngle == 0) {
            return null;
        }
        Matrix matrix = new Matrix();
        matrix.setRotate((float) rotationAngle);
        return matrix;
    }

    private final Matrix getTransformationMatrixFromInvertedExif(int i) {
        Matrix matrix = new Matrix();
        if (i == 2) {
            matrix.setScale(-1.0f, 1.0f);
        } else if (i == 7) {
            matrix.setRotate(-90.0f);
            matrix.postScale(-1.0f, 1.0f);
        } else if (i == 4) {
            matrix.setRotate(180.0f);
            matrix.postScale(-1.0f, 1.0f);
        } else if (i != 5) {
            return null;
        } else {
            matrix.setRotate(90.0f);
            matrix.postScale(-1.0f, 1.0f);
        }
        return matrix;
    }

    private final int extractOrientationFromMetadata(EncodedImage encodedImage) {
        int rotationAngle = encodedImage.getRotationAngle();
        if (rotationAngle == 90 || rotationAngle == 180 || rotationAngle == 270) {
            return encodedImage.getRotationAngle();
        }
        return 0;
    }
}
