package androidx.media3.effect;

import android.opengl.Matrix;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.GlUtil;
import java.util.Arrays;

public final class RgbAdjustment implements RgbMatrix {
    private final float[] rgbMatrix;

    public static final class Builder {
        private float blueScale = 1.0f;
        private float greenScale = 1.0f;
        private float redScale = 1.0f;

        public Builder setRedScale(float f) {
            Assertions.checkArgument(0.0f <= f, "Red scale needs to be non-negative.");
            this.redScale = f;
            return this;
        }

        public Builder setGreenScale(float f) {
            Assertions.checkArgument(0.0f <= f, "Green scale needs to be non-negative.");
            this.greenScale = f;
            return this;
        }

        public Builder setBlueScale(float f) {
            Assertions.checkArgument(0.0f <= f, "Blue scale needs to be non-negative.");
            this.blueScale = f;
            return this;
        }

        public RgbAdjustment build() {
            float[] create4x4IdentityMatrix = GlUtil.create4x4IdentityMatrix();
            Matrix.scaleM(create4x4IdentityMatrix, 0, this.redScale, this.greenScale, this.blueScale);
            return new RgbAdjustment(create4x4IdentityMatrix);
        }
    }

    private RgbAdjustment(float[] fArr) {
        this.rgbMatrix = fArr;
    }

    public float[] getMatrix(long j, boolean z) {
        return this.rgbMatrix;
    }

    public boolean isNoOp(int i, int i2) {
        return Arrays.equals(this.rgbMatrix, GlUtil.create4x4IdentityMatrix());
    }
}
