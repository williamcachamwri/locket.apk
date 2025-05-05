package androidx.media3.effect;

import android.graphics.Matrix;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Size;
import com.google.common.collect.ImmutableList;
import java.util.Arrays;
import java.util.List;

final class MatrixUtils {
    private static final float[][] NDC_CUBE = {new float[]{1.0f, 0.0f, 0.0f, 1.0f}, new float[]{-1.0f, 0.0f, 0.0f, 1.0f}, new float[]{0.0f, 1.0f, 0.0f, 1.0f}, new float[]{0.0f, -1.0f, 0.0f, 1.0f}, new float[]{0.0f, 0.0f, 1.0f, 1.0f}, new float[]{0.0f, 0.0f, -1.0f, 1.0f}};

    public static float[] getGlMatrixArray(Matrix matrix) {
        float[] fArr = new float[9];
        matrix.getValues(fArr);
        float[] matrix4x4Array = getMatrix4x4Array(fArr);
        float[] fArr2 = new float[16];
        android.opengl.Matrix.transposeM(fArr2, 0, matrix4x4Array, 0);
        return fArr2;
    }

    private static float[] getMatrix4x4Array(float[] fArr) {
        float[] fArr2 = new float[16];
        fArr2[10] = 1.0f;
        int i = 0;
        while (i < 3) {
            int i2 = 0;
            while (i2 < 3) {
                fArr2[((i == 2 ? 3 : i) * 4) + (i2 == 2 ? 3 : i2)] = fArr[(i * 3) + i2];
                i2++;
            }
            i++;
        }
        return fArr2;
    }

    public static ImmutableList<float[]> clipConvexPolygonToNdcRange(ImmutableList<float[]> immutableList) {
        Assertions.checkArgument(immutableList.size() >= 3, "A polygon must have at least 3 vertices.");
        ImmutableList.Builder addAll = new ImmutableList.Builder().addAll((Iterable) immutableList);
        float[][] fArr = NDC_CUBE;
        int length = fArr.length;
        int i = 0;
        while (i < length) {
            float[] fArr2 = fArr[i];
            ImmutableList build = addAll.build();
            ImmutableList.Builder builder = new ImmutableList.Builder();
            for (int i2 = 0; i2 < build.size(); i2++) {
                float[] fArr3 = (float[]) build.get(i2);
                float[] fArr4 = (float[]) build.get(((build.size() + i2) - 1) % build.size());
                if (isInsideClippingHalfSpace(fArr3, fArr2)) {
                    if (!isInsideClippingHalfSpace(fArr4, fArr2)) {
                        float[] computeIntersectionPoint = computeIntersectionPoint(fArr2, fArr2, fArr4, fArr3);
                        if (!Arrays.equals(fArr3, computeIntersectionPoint)) {
                            builder.add((Object) computeIntersectionPoint);
                        }
                    }
                    builder.add((Object) fArr3);
                } else if (isInsideClippingHalfSpace(fArr4, fArr2)) {
                    float[] computeIntersectionPoint2 = computeIntersectionPoint(fArr2, fArr2, fArr4, fArr3);
                    if (!Arrays.equals(fArr4, computeIntersectionPoint2)) {
                        builder.add((Object) computeIntersectionPoint2);
                    }
                }
            }
            i++;
            addAll = builder;
        }
        return addAll.build();
    }

    private static boolean isInsideClippingHalfSpace(float[] fArr, float[] fArr2) {
        Assertions.checkArgument(fArr2.length == 4, "Expecting 4 plane parameters");
        if ((fArr2[0] * fArr[0]) + (fArr2[1] * fArr[1]) + (fArr2[2] * fArr[2]) <= fArr2[3]) {
            return true;
        }
        return false;
    }

    private static float[] computeIntersectionPoint(float[] fArr, float[] fArr2, float[] fArr3, float[] fArr4) {
        Assertions.checkArgument(fArr2.length == 4, "Expecting 4 plane parameters");
        float f = fArr[0];
        float f2 = fArr3[0];
        float f3 = fArr2[0];
        float f4 = fArr[1];
        float f5 = fArr3[1];
        float f6 = fArr2[1];
        float f7 = fArr[2];
        float f8 = fArr3[2];
        float f9 = fArr2[2];
        float f10 = ((f - f2) * f3) + ((f4 - f5) * f6) + ((f7 - f8) * f9);
        float f11 = fArr4[0];
        float f12 = (f11 - f2) * f3;
        float f13 = fArr4[1];
        float f14 = fArr4[2];
        float f15 = f10 / ((f12 + ((f13 - f5) * f6)) + ((f14 - f8) * f9));
        return new float[]{f2 + ((f11 - f2) * f15), f5 + ((f13 - f5) * f15), f8 + ((f14 - f8) * f15), 1.0f};
    }

    public static ImmutableList<float[]> transformPoints(float[] fArr, ImmutableList<float[]> immutableList) {
        ImmutableList.Builder builder = new ImmutableList.Builder();
        for (int i = 0; i < immutableList.size(); i++) {
            float[] fArr2 = new float[4];
            android.opengl.Matrix.multiplyMV(fArr2, 0, fArr, 0, (float[]) immutableList.get(i), 0);
            float f = fArr2[0];
            float f2 = fArr2[3];
            fArr2[0] = f / f2;
            fArr2[1] = fArr2[1] / f2;
            fArr2[2] = fArr2[2] / f2;
            fArr2[3] = 1.0f;
            builder.add((Object) fArr2);
        }
        return builder.build();
    }

    public static Size configureAndGetOutputSize(int i, int i2, List<GlMatrixTransformation> list) {
        boolean z = true;
        Assertions.checkArgument(i > 0, "inputWidth must be positive");
        if (i2 <= 0) {
            z = false;
        }
        Assertions.checkArgument(z, "inputHeight must be positive");
        Size size = new Size(i, i2);
        for (int i3 = 0; i3 < list.size(); i3++) {
            size = list.get(i3).configure(size.getWidth(), size.getHeight());
        }
        return size;
    }

    private MatrixUtils() {
    }
}
