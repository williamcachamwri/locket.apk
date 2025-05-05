package androidx.media3.effect;

import android.graphics.Matrix;

public interface MatrixTransformation extends GlMatrixTransformation {
    Matrix getMatrix(long j);

    float[] getGlMatrixArray(long j) {
        return MatrixUtils.getGlMatrixArray(getMatrix(j));
    }
}
