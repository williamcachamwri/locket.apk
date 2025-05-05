package androidx.media3.effect;

import android.content.Context;
import androidx.media3.common.VideoFrameProcessingException;

public abstract class SeparableConvolution implements GlEffect {
    private final float scaleHeight;
    private final float scaleWidth;

    public abstract ConvolutionFunction1D getConvolution(long j);

    public SeparableConvolution() {
        this(1.0f, 1.0f);
    }

    public SeparableConvolution(float f, float f2) {
        this.scaleWidth = f;
        this.scaleHeight = f2;
    }

    public GlShaderProgram toGlShaderProgram(Context context, boolean z) throws VideoFrameProcessingException {
        return new SeparableConvolutionShaderProgram(context, z, this, this.scaleWidth, this.scaleHeight);
    }
}
