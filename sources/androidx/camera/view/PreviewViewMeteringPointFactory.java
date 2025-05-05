package androidx.camera.view;

import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.Rational;
import android.util.Size;
import androidx.camera.core.MeteringPointFactory;
import androidx.camera.core.impl.utils.Threads;

class PreviewViewMeteringPointFactory extends MeteringPointFactory {
    static final PointF INVALID_POINT = new PointF(2.0f, 2.0f);
    private Matrix mMatrix;
    private final PreviewTransformation mPreviewTransformation;
    private Rect mSensorRect = null;

    PreviewViewMeteringPointFactory(PreviewTransformation previewTransformation) {
        this.mPreviewTransformation = previewTransformation;
    }

    /* access modifiers changed from: protected */
    public PointF convertPoint(float f, float f2) {
        float[] fArr = {f, f2};
        synchronized (this) {
            Matrix matrix = this.mMatrix;
            if (matrix == null) {
                PointF pointF = INVALID_POINT;
                return pointF;
            }
            matrix.mapPoints(fArr);
            return new PointF(fArr[0], fArr[1]);
        }
    }

    public void setSensorRect(Rect rect) {
        setSurfaceAspectRatio(new Rational(rect.width(), rect.height()));
        synchronized (this) {
            this.mSensorRect = rect;
        }
    }

    /* access modifiers changed from: package-private */
    public void recalculate(Size size, int i) {
        Threads.checkMainThread();
        synchronized (this) {
            if (!(size.getWidth() == 0 || size.getHeight() == 0)) {
                Rect rect = this.mSensorRect;
                if (rect != null) {
                    this.mMatrix = this.mPreviewTransformation.getPreviewViewToNormalizedSensorMatrix(size, i, rect);
                    return;
                }
            }
            this.mMatrix = null;
        }
    }
}
