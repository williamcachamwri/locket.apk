package androidx.media3.effect;

import android.content.Context;
import androidx.media3.common.VideoFrameProcessingException;
import androidx.media3.common.util.Assertions;

public final class AlphaScale implements GlEffect {
    private final float alphaScale;

    public AlphaScale(float f) {
        Assertions.checkArgument(0.0f <= f);
        this.alphaScale = f;
    }

    public AlphaScaleShaderProgram toGlShaderProgram(Context context, boolean z) throws VideoFrameProcessingException {
        return new AlphaScaleShaderProgram(context, z, this.alphaScale);
    }

    public boolean isNoOp(int i, int i2) {
        return this.alphaScale == 1.0f;
    }
}
