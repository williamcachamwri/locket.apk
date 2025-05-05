package androidx.media3.effect;

import android.content.Context;
import androidx.media3.common.VideoFrameProcessingException;
import androidx.media3.common.util.Assertions;

public final class RgbFilter implements RgbMatrix {
    private static final int COLOR_FILTER_GRAYSCALE_INDEX = 1;
    private static final int COLOR_FILTER_INVERTED_INDEX = 2;
    private static final float[] FILTER_MATRIX_GRAYSCALE_HDR = {0.2627f, 0.2627f, 0.2627f, 0.0f, 0.678f, 0.678f, 0.678f, 0.0f, 0.0593f, 0.0593f, 0.0593f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    private static final float[] FILTER_MATRIX_GRAYSCALE_SDR = {0.2126f, 0.2126f, 0.2126f, 0.0f, 0.7152f, 0.7152f, 0.7152f, 0.0f, 0.0722f, 0.0722f, 0.0722f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    private static final float[] FILTER_MATRIX_INVERTED = {-1.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 1.0f};
    private final int colorFilter;
    private Boolean useHdr;

    public static RgbFilter createGrayscaleFilter() {
        return new RgbFilter(1);
    }

    public static RgbFilter createInvertedFilter() {
        return new RgbFilter(2);
    }

    private RgbFilter(int i) {
        this.colorFilter = i;
    }

    private void checkForConsistentHdrSetting(boolean z) {
        Boolean bool = this.useHdr;
        if (bool == null) {
            this.useHdr = Boolean.valueOf(z);
        } else {
            Assertions.checkState(bool.booleanValue() == z, "Changing HDR setting is not supported.");
        }
    }

    public float[] getMatrix(long j, boolean z) {
        checkForConsistentHdrSetting(z);
        int i = this.colorFilter;
        if (i == 1) {
            return z ? FILTER_MATRIX_GRAYSCALE_HDR : FILTER_MATRIX_GRAYSCALE_SDR;
        }
        if (i == 2) {
            return FILTER_MATRIX_INVERTED;
        }
        throw new IllegalStateException("Invalid color filter " + this.colorFilter);
    }

    public BaseGlShaderProgram toGlShaderProgram(Context context, boolean z) throws VideoFrameProcessingException {
        checkForConsistentHdrSetting(z);
        return super.toGlShaderProgram(context, z);
    }
}
