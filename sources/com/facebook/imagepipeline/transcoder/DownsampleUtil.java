package com.facebook.imagepipeline.transcoder;

import com.facebook.common.logging.FLog;
import com.facebook.imageformat.DefaultImageFormats;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.common.RotationOptions;
import com.facebook.imagepipeline.image.EncodedImage;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\"\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\rH\u0007J*\u0010\u000e\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u0004H\u0007J \u0010\u0010\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u0004H\u0007J\u0018\u0010\u0013\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\rH\u0002J\u0010\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u0006H\u0007J\u0010\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u0006H\u0007J\u0010\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/facebook/imagepipeline/transcoder/DownsampleUtil;", "", "()V", "DEFAULT_SAMPLE_SIZE", "", "INTERVAL_ROUNDING", "", "determineDownsampleRatio", "rotationOptions", "Lcom/facebook/imagepipeline/common/RotationOptions;", "resizeOptions", "Lcom/facebook/imagepipeline/common/ResizeOptions;", "encodedImage", "Lcom/facebook/imagepipeline/image/EncodedImage;", "determineSampleSize", "maxBitmapSize", "determineSampleSizeJPEG", "pixelSize", "maxBitmapSizeInBytes", "getRotationAngle", "ratioToSampleSize", "ratio", "ratioToSampleSizeJPEG", "roundToPowerOfTwo", "sampleSize", "imagepipeline-base_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: DownsampleUtil.kt */
public final class DownsampleUtil {
    public static final int DEFAULT_SAMPLE_SIZE = 1;
    public static final DownsampleUtil INSTANCE = new DownsampleUtil();
    private static final float INTERVAL_ROUNDING = 0.33333334f;

    @JvmStatic
    public static final int ratioToSampleSizeJPEG(float f) {
        if (f > 0.6666667f) {
            return 1;
        }
        int i = 2;
        while (true) {
            int i2 = i * 2;
            double d = 1.0d / ((double) i2);
            if (d + (((double) INTERVAL_ROUNDING) * d) <= ((double) f)) {
                return i;
            }
            i = i2;
        }
    }

    @JvmStatic
    public static final int roundToPowerOfTwo(int i) {
        int i2 = 1;
        while (i2 < i) {
            i2 *= 2;
        }
        return i2;
    }

    private DownsampleUtil() {
    }

    @JvmStatic
    public static final int determineSampleSize(RotationOptions rotationOptions, ResizeOptions resizeOptions, EncodedImage encodedImage, int i) {
        int i2;
        Intrinsics.checkNotNullParameter(rotationOptions, "rotationOptions");
        Intrinsics.checkNotNullParameter(encodedImage, "encodedImage");
        if (!EncodedImage.isMetaDataAvailable(encodedImage)) {
            return 1;
        }
        float determineDownsampleRatio = determineDownsampleRatio(rotationOptions, resizeOptions, encodedImage);
        if (encodedImage.getImageFormat() == DefaultImageFormats.JPEG) {
            i2 = ratioToSampleSizeJPEG(determineDownsampleRatio);
        } else {
            i2 = ratioToSampleSize(determineDownsampleRatio);
        }
        int max = Math.max(encodedImage.getHeight(), encodedImage.getWidth());
        float f = resizeOptions != null ? resizeOptions.maxBitmapSize : (float) i;
        while (((float) (max / i2)) > f) {
            i2 = encodedImage.getImageFormat() == DefaultImageFormats.JPEG ? i2 * 2 : i2 + 1;
        }
        return i2;
    }

    @JvmStatic
    public static final int determineSampleSizeJPEG(EncodedImage encodedImage, int i, int i2) {
        Intrinsics.checkNotNullParameter(encodedImage, "encodedImage");
        int sampleSize = encodedImage.getSampleSize();
        while ((((encodedImage.getWidth() * encodedImage.getHeight()) * i) / sampleSize) / sampleSize > i2) {
            sampleSize *= 2;
        }
        return sampleSize;
    }

    @JvmStatic
    public static final float determineDownsampleRatio(RotationOptions rotationOptions, ResizeOptions resizeOptions, EncodedImage encodedImage) {
        Intrinsics.checkNotNullParameter(rotationOptions, "rotationOptions");
        Intrinsics.checkNotNullParameter(encodedImage, "encodedImage");
        if (!EncodedImage.isMetaDataAvailable(encodedImage)) {
            throw new IllegalStateException("Check failed.".toString());
        } else if (resizeOptions == null || resizeOptions.height <= 0 || resizeOptions.width <= 0 || encodedImage.getWidth() == 0 || encodedImage.getHeight() == 0) {
            return 1.0f;
        } else {
            int rotationAngle = INSTANCE.getRotationAngle(rotationOptions, encodedImage);
            boolean z = rotationAngle == 90 || rotationAngle == 270;
            int height = z ? encodedImage.getHeight() : encodedImage.getWidth();
            int width = z ? encodedImage.getWidth() : encodedImage.getHeight();
            float f = ((float) resizeOptions.width) / ((float) height);
            float f2 = ((float) resizeOptions.height) / ((float) width);
            float coerceAtLeast = RangesKt.coerceAtLeast(f, f2);
            FLog.v("DownsampleUtil", "Downsample - Specified size: %dx%d, image size: %dx%d ratio: %.1f x %.1f, ratio: %.3f", Integer.valueOf(resizeOptions.width), Integer.valueOf(resizeOptions.height), Integer.valueOf(height), Integer.valueOf(width), Float.valueOf(f), Float.valueOf(f2), Float.valueOf(coerceAtLeast));
            return coerceAtLeast;
        }
    }

    @JvmStatic
    public static final int ratioToSampleSize(float f) {
        if (f > 0.6666667f) {
            return 1;
        }
        int i = 2;
        while (true) {
            double d = (double) i;
            if ((1.0d / d) + ((1.0d / (Math.pow(d, 2.0d) - d)) * ((double) INTERVAL_ROUNDING)) <= ((double) f)) {
                return i - 1;
            }
            i++;
        }
    }

    private final int getRotationAngle(RotationOptions rotationOptions, EncodedImage encodedImage) {
        boolean z = false;
        if (!rotationOptions.useImageMetadata()) {
            return 0;
        }
        int rotationAngle = encodedImage.getRotationAngle();
        if (rotationAngle == 0 || rotationAngle == 90 || rotationAngle == 180 || rotationAngle == 270) {
            z = true;
        }
        if (z) {
            return rotationAngle;
        }
        throw new IllegalStateException("Check failed.".toString());
    }
}
