package com.horcrux.svg;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReactContext;

class MarkerView extends GroupView {
    String mAlign;
    private SVGLength mMarkerHeight;
    private String mMarkerUnits;
    private SVGLength mMarkerWidth;
    int mMeetOrSlice;
    private float mMinX;
    private float mMinY;
    private String mOrient;
    private SVGLength mRefX;
    private SVGLength mRefY;
    private float mVbHeight;
    private float mVbWidth;
    Matrix markerTransform = new Matrix();

    public MarkerView(ReactContext reactContext) {
        super(reactContext);
    }

    public void setRefX(Dynamic dynamic) {
        this.mRefX = SVGLength.from(dynamic);
        invalidate();
    }

    public void setRefX(String str) {
        this.mRefX = SVGLength.from(str);
        invalidate();
    }

    public void setRefX(Double d) {
        this.mRefX = SVGLength.from(d);
        invalidate();
    }

    public void setRefY(Dynamic dynamic) {
        this.mRefY = SVGLength.from(dynamic);
        invalidate();
    }

    public void setRefY(String str) {
        this.mRefY = SVGLength.from(str);
        invalidate();
    }

    public void setRefY(Double d) {
        this.mRefY = SVGLength.from(d);
        invalidate();
    }

    public void setMarkerWidth(Dynamic dynamic) {
        this.mMarkerWidth = SVGLength.from(dynamic);
        invalidate();
    }

    public void setMarkerWidth(String str) {
        this.mMarkerWidth = SVGLength.from(str);
        invalidate();
    }

    public void setMarkerWidth(Double d) {
        this.mMarkerWidth = SVGLength.from(d);
        invalidate();
    }

    public void setMarkerHeight(Dynamic dynamic) {
        this.mMarkerHeight = SVGLength.from(dynamic);
        invalidate();
    }

    public void setMarkerHeight(String str) {
        this.mMarkerHeight = SVGLength.from(str);
        invalidate();
    }

    public void setMarkerHeight(Double d) {
        this.mMarkerHeight = SVGLength.from(d);
        invalidate();
    }

    public void setMarkerUnits(String str) {
        this.mMarkerUnits = str;
        invalidate();
    }

    public void setOrient(String str) {
        this.mOrient = str;
        invalidate();
    }

    public void setMinX(float f) {
        this.mMinX = f;
        invalidate();
    }

    public void setMinY(float f) {
        this.mMinY = f;
        invalidate();
    }

    public void setVbWidth(float f) {
        this.mVbWidth = f;
        invalidate();
    }

    public void setVbHeight(float f) {
        this.mVbHeight = f;
        invalidate();
    }

    public void setAlign(String str) {
        this.mAlign = str;
        invalidate();
    }

    public void setMeetOrSlice(int i) {
        this.mMeetOrSlice = i;
        invalidate();
    }

    /* access modifiers changed from: package-private */
    public void saveDefinition() {
        if (this.mName != null) {
            getSvgView().defineMarker(this, this.mName);
            for (int i = 0; i < getChildCount(); i++) {
                View childAt = getChildAt(i);
                if (childAt instanceof VirtualView) {
                    ((VirtualView) childAt).saveDefinition();
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void renderMarker(Canvas canvas, Paint paint, float f, RNSVGMarkerPosition rNSVGMarkerPosition, float f2) {
        int saveAndSetupCanvas = saveAndSetupCanvas(canvas, this.mCTM);
        this.markerTransform.reset();
        Point point = rNSVGMarkerPosition.origin;
        this.markerTransform.setTranslate((float) point.x, (float) point.y);
        double parseDouble = "auto".equals(this.mOrient) ? -1.0d : Double.parseDouble(this.mOrient);
        if (parseDouble == -1.0d) {
            parseDouble = rNSVGMarkerPosition.angle;
        }
        this.markerTransform.preRotate(((float) parseDouble) + 180.0f);
        if ("strokeWidth".equals(this.mMarkerUnits)) {
            this.markerTransform.preScale(f2 / this.mScale, f2 / this.mScale);
        }
        RectF rectF = new RectF(0.0f, 0.0f, (float) (relativeOnWidth(this.mMarkerWidth) / ((double) this.mScale)), (float) (relativeOnHeight(this.mMarkerHeight) / ((double) this.mScale)));
        if (this.mAlign != null) {
            float[] fArr = new float[9];
            ViewBox.getTransform(new RectF(this.mMinX * this.mScale, this.mMinY * this.mScale, (this.mMinX + this.mVbWidth) * this.mScale, (this.mMinY + this.mVbHeight) * this.mScale), rectF, this.mAlign, this.mMeetOrSlice).getValues(fArr);
            this.markerTransform.preScale(fArr[0], fArr[4]);
        }
        this.markerTransform.preTranslate((float) (-relativeOnWidth(this.mRefX)), (float) (-relativeOnHeight(this.mRefY)));
        canvas.concat(this.markerTransform);
        drawGroup(canvas, paint, f);
        restoreCanvas(canvas, saveAndSetupCanvas);
    }
}
