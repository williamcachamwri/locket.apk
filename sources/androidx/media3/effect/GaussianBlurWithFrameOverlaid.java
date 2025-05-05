package androidx.media3.effect;

import android.content.Context;
import androidx.media3.common.VideoFrameProcessingException;

public final class GaussianBlurWithFrameOverlaid extends SeparableConvolution {
    private final float numStandardDeviations;
    private final float scaleSharpX;
    private final float scaleSharpY;
    private final float sigma;

    public GaussianBlurWithFrameOverlaid(float f, float f2, float f3, float f4) {
        this.sigma = f;
        this.numStandardDeviations = f2;
        this.scaleSharpX = f3;
        this.scaleSharpY = f4;
    }

    public GaussianBlurWithFrameOverlaid(float f, float f2, float f3) {
        this(f, 2.0f, f2, f3);
    }

    public ConvolutionFunction1D getConvolution(long j) {
        return new GaussianFunction(this.sigma, this.numStandardDeviations);
    }

    public GlShaderProgram toGlShaderProgram(Context context, boolean z) throws VideoFrameProcessingException {
        return new SharpSeparableConvolutionShaderProgram(context, z, this, this.scaleSharpX, this.scaleSharpY);
    }
}
