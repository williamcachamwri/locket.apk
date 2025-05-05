package com.facebook.imagepipeline.producers;

import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.image.EncodedImage;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006H\u0007J\u001c\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0007J\"\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u00062\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/facebook/imagepipeline/producers/ThumbnailSizeChecker;", "", "()V", "ACCEPTABLE_REQUESTED_TO_ACTUAL_SIZE_RATIO", "", "ROTATED_90_DEGREES_CLOCKWISE", "", "ROTATED_90_DEGREES_COUNTER_CLOCKWISE", "getAcceptableSize", "size", "isImageBigEnough", "", "encodedImage", "Lcom/facebook/imagepipeline/image/EncodedImage;", "resizeOptions", "Lcom/facebook/imagepipeline/common/ResizeOptions;", "width", "height", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ThumbnailSizeChecker.kt */
public final class ThumbnailSizeChecker {
    public static final float ACCEPTABLE_REQUESTED_TO_ACTUAL_SIZE_RATIO = 1.3333334f;
    public static final ThumbnailSizeChecker INSTANCE = new ThumbnailSizeChecker();
    private static final int ROTATED_90_DEGREES_CLOCKWISE = 90;
    private static final int ROTATED_90_DEGREES_COUNTER_CLOCKWISE = 270;

    @JvmStatic
    public static final int getAcceptableSize(int i) {
        return (int) (((float) i) * 1.3333334f);
    }

    private ThumbnailSizeChecker() {
    }

    @JvmStatic
    public static final boolean isImageBigEnough(int i, int i2, ResizeOptions resizeOptions) {
        if (resizeOptions == null) {
            if (((float) getAcceptableSize(i)) >= 2048.0f && getAcceptableSize(i2) >= 2048) {
                return true;
            }
        } else if (getAcceptableSize(i) >= resizeOptions.width && getAcceptableSize(i2) >= resizeOptions.height) {
            return true;
        }
        return false;
    }

    @JvmStatic
    public static final boolean isImageBigEnough(EncodedImage encodedImage, ResizeOptions resizeOptions) {
        if (encodedImage == null) {
            return false;
        }
        int rotationAngle = encodedImage.getRotationAngle();
        if (rotationAngle == 90 || rotationAngle == 270) {
            return isImageBigEnough(encodedImage.getHeight(), encodedImage.getWidth(), resizeOptions);
        }
        return isImageBigEnough(encodedImage.getWidth(), encodedImage.getHeight(), resizeOptions);
    }
}
