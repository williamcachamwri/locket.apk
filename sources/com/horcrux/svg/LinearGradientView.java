package com.horcrux.svg;

import android.graphics.Matrix;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.ReactConstants;
import com.horcrux.svg.Brush;
import javax.annotation.Nullable;

class LinearGradientView extends DefinitionView {
    private static final float[] sRawMatrix = {1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    private ReadableArray mGradient;
    private Brush.BrushUnits mGradientUnits;
    private Matrix mMatrix = null;
    private SVGLength mX1;
    private SVGLength mX2;
    private SVGLength mY1;
    private SVGLength mY2;

    public LinearGradientView(ReactContext reactContext) {
        super(reactContext);
    }

    public void setX1(Dynamic dynamic) {
        this.mX1 = SVGLength.from(dynamic);
        invalidate();
    }

    public void setX1(String str) {
        this.mX1 = SVGLength.from(str);
        invalidate();
    }

    public void setX1(Double d) {
        this.mX1 = SVGLength.from(d);
        invalidate();
    }

    public void setY1(Dynamic dynamic) {
        this.mY1 = SVGLength.from(dynamic);
        invalidate();
    }

    public void setY1(String str) {
        this.mY1 = SVGLength.from(str);
        invalidate();
    }

    public void setY1(Double d) {
        this.mY1 = SVGLength.from(d);
        invalidate();
    }

    public void setX2(Dynamic dynamic) {
        this.mX2 = SVGLength.from(dynamic);
        invalidate();
    }

    public void setX2(String str) {
        this.mX2 = SVGLength.from(str);
        invalidate();
    }

    public void setX2(Double d) {
        this.mX2 = SVGLength.from(d);
        invalidate();
    }

    public void setY2(Dynamic dynamic) {
        this.mY2 = SVGLength.from(dynamic);
        invalidate();
    }

    public void setY2(String str) {
        this.mY2 = SVGLength.from(str);
        invalidate();
    }

    public void setY2(Double d) {
        this.mY2 = SVGLength.from(d);
        invalidate();
    }

    public void setGradient(ReadableArray readableArray) {
        this.mGradient = readableArray;
        invalidate();
    }

    public void setGradientUnits(int i) {
        if (i == 0) {
            this.mGradientUnits = Brush.BrushUnits.OBJECT_BOUNDING_BOX;
        } else if (i == 1) {
            this.mGradientUnits = Brush.BrushUnits.USER_SPACE_ON_USE;
        }
        invalidate();
    }

    public void setGradientTransform(@Nullable ReadableArray readableArray) {
        if (readableArray != null) {
            float[] fArr = sRawMatrix;
            int matrixData = PropHelper.toMatrixData(readableArray, fArr, this.mScale);
            if (matrixData == 6) {
                if (this.mMatrix == null) {
                    this.mMatrix = new Matrix();
                }
                this.mMatrix.setValues(fArr);
            } else if (matrixData != -1) {
                FLog.w(ReactConstants.TAG, "RNSVG: Transform matrices must be of size 6");
            }
        } else {
            this.mMatrix = null;
        }
        invalidate();
    }

    /* access modifiers changed from: package-private */
    public void saveDefinition() {
        if (this.mName != null) {
            Brush brush = new Brush(Brush.BrushType.LINEAR_GRADIENT, new SVGLength[]{this.mX1, this.mY1, this.mX2, this.mY2}, this.mGradientUnits);
            brush.setGradientColors(this.mGradient);
            Matrix matrix = this.mMatrix;
            if (matrix != null) {
                brush.setGradientTransform(matrix);
            }
            SvgView svgView = getSvgView();
            if (this.mGradientUnits == Brush.BrushUnits.USER_SPACE_ON_USE) {
                brush.setUserSpaceBoundingBox(svgView.getCanvasBounds());
            }
            svgView.defineBrush(brush, this.mName);
        }
    }
}
