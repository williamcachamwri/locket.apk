package androidx.media3.effect;

import android.opengl.Matrix;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.GlUtil;
import java.util.Arrays;

public final class Brightness implements RgbMatrix {
    private final float[] rgbMatrix;

    public Brightness(float f) {
        Assertions.checkArgument(f >= -1.0f && f <= 1.0f, "brightness value outside of range from -1f to 1f, inclusive");
        float[] create4x4IdentityMatrix = GlUtil.create4x4IdentityMatrix();
        this.rgbMatrix = create4x4IdentityMatrix;
        Matrix.translateM(create4x4IdentityMatrix, 0, f, f, f);
    }

    public float[] getMatrix(long j, boolean z) {
        Assertions.checkArgument(!z, "HDR is not supported.");
        return this.rgbMatrix;
    }

    public boolean isNoOp(int i, int i2) {
        return Arrays.equals(this.rgbMatrix, GlUtil.create4x4IdentityMatrix());
    }
}
