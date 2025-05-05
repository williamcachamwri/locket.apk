package androidx.media3.effect;

import android.graphics.Matrix;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Size;

public final class ScaleAndRotateTransformation implements MatrixTransformation {
    private Matrix adjustedTransformationMatrix;
    public final float rotationDegrees;
    public final float scaleX;
    public final float scaleY;
    private final Matrix transformationMatrix;

    public static final class Builder {
        private float rotationDegrees = 0.0f;
        private float scaleX = 1.0f;
        private float scaleY = 1.0f;

        public Builder setScale(float f, float f2) {
            this.scaleX = f;
            this.scaleY = f2;
            return this;
        }

        public Builder setRotationDegrees(float f) {
            float f2 = f % 360.0f;
            this.rotationDegrees = f2;
            if (f2 < 0.0f) {
                this.rotationDegrees = f2 + 360.0f;
            }
            return this;
        }

        public ScaleAndRotateTransformation build() {
            return new ScaleAndRotateTransformation(this.scaleX, this.scaleY, this.rotationDegrees);
        }
    }

    private ScaleAndRotateTransformation(float f, float f2, float f3) {
        this.scaleX = f;
        this.scaleY = f2;
        this.rotationDegrees = f3;
        Matrix matrix = new Matrix();
        this.transformationMatrix = matrix;
        matrix.postScale(f, f2);
        matrix.postRotate(f3);
    }

    public Size configure(int i, int i2) {
        Assertions.checkArgument(i > 0, "inputWidth must be positive");
        Assertions.checkArgument(i2 > 0, "inputHeight must be positive");
        this.adjustedTransformationMatrix = new Matrix(this.transformationMatrix);
        if (this.transformationMatrix.isIdentity()) {
            return new Size(i, i2);
        }
        float f = (float) i;
        float f2 = (float) i2;
        float f3 = f / f2;
        this.adjustedTransformationMatrix.preScale(f3, 1.0f);
        this.adjustedTransformationMatrix.postScale(1.0f / f3, 1.0f);
        float[][] fArr = {new float[]{-1.0f, -1.0f, 0.0f, 1.0f}, new float[]{-1.0f, 1.0f, 0.0f, 1.0f}, new float[]{1.0f, -1.0f, 0.0f, 1.0f}, new float[]{1.0f, 1.0f, 0.0f, 1.0f}};
        float f4 = Float.MIN_VALUE;
        float f5 = Float.MAX_VALUE;
        float f6 = Float.MAX_VALUE;
        float f7 = Float.MIN_VALUE;
        for (int i3 = 0; i3 < 4; i3++) {
            float[] fArr2 = fArr[i3];
            this.adjustedTransformationMatrix.mapPoints(fArr2);
            f5 = Math.min(f5, fArr2[0]);
            f4 = Math.max(f4, fArr2[0]);
            f6 = Math.min(f6, fArr2[1]);
            f7 = Math.max(f7, fArr2[1]);
        }
        float f8 = (f4 - f5) / 2.0f;
        float f9 = (f7 - f6) / 2.0f;
        this.adjustedTransformationMatrix.postScale(1.0f / f8, 1.0f / f9);
        return new Size(Math.round(f * f8), Math.round(f2 * f9));
    }

    public Matrix getMatrix(long j) {
        return (Matrix) Assertions.checkStateNotNull(this.adjustedTransformationMatrix, "configure must be called first");
    }

    public boolean isNoOp(int i, int i2) {
        Size configure = configure(i, i2);
        return ((Matrix) Assertions.checkStateNotNull(this.adjustedTransformationMatrix)).isIdentity() && i == configure.getWidth() && i2 == configure.getHeight();
    }
}
