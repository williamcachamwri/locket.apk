package androidx.media3.effect;

import android.content.Context;
import androidx.media3.common.VideoFrameProcessingException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Size;
import androidx.media3.effect.ConvolutionFunction1D;

public final class LanczosResample implements GlEffect {
    private static final float DEFAULT_RADIUS = 3.0f;
    private static final float NO_OP_THRESHOLD = 0.01f;
    private final int height;
    private final float radius;
    private final int width;

    public static LanczosResample scaleToFit(int i, int i2) {
        boolean z = true;
        Assertions.checkArgument(i > 0);
        if (i2 <= 0) {
            z = false;
        }
        Assertions.checkArgument(z);
        return new LanczosResample(DEFAULT_RADIUS, i, i2);
    }

    private LanczosResample(float f, int i, int i2) {
        this.radius = f;
        this.width = i;
        this.height = i2;
    }

    public GlShaderProgram toGlShaderProgram(Context context, boolean z) throws VideoFrameProcessingException {
        return new SeparableConvolutionShaderProgram(context, z, new LanczosResampleScaledFunctionProvider(this.radius, this.width, this.height));
    }

    public boolean isNoOp(int i, int i2) {
        return Math.abs(scalingFactorToFit(i, i2, this.width, this.height) - 1.0f) < NO_OP_THRESHOLD;
    }

    /* access modifiers changed from: private */
    public static float scalingFactorToFit(int i, int i2, int i3, int i4) {
        boolean z = true;
        Assertions.checkArgument(i > 0);
        if (i2 <= 0) {
            z = false;
        }
        Assertions.checkArgument(z);
        return i2 * i3 <= i4 * i ? ((float) i3) / ((float) i) : ((float) i4) / ((float) i2);
    }

    private static class LanczosResampleScaledFunctionProvider implements ConvolutionFunction1D.Provider {
        private static final float SCALE_UNSET = -3.4028235E38f;
        private final int height;
        private final float radius;
        private float scale;
        private final int width;

        private LanczosResampleScaledFunctionProvider(float f, int i, int i2) {
            boolean z = true;
            Assertions.checkArgument(f > 0.0f);
            Assertions.checkArgument(i > 0);
            Assertions.checkArgument(i2 <= 0 ? false : z);
            this.radius = f;
            this.width = i;
            this.height = i2;
            this.scale = -3.4028235E38f;
        }

        public ConvolutionFunction1D getConvolution(long j) {
            return new ScaledLanczosFunction(this.radius, Math.min(this.scale, 1.0f));
        }

        public Size configure(Size size) {
            this.scale = LanczosResample.scalingFactorToFit(size.getWidth(), size.getHeight(), this.width, this.height);
            return new Size(Math.round(((float) size.getWidth()) * this.scale), Math.round(((float) size.getHeight()) * this.scale));
        }
    }
}
