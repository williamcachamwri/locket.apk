package com.horcrux.svg;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReactContext;
import java.util.ArrayList;

class EllipseView extends RenderableView {
    private SVGLength mCx;
    private SVGLength mCy;
    private SVGLength mRx;
    private SVGLength mRy;

    public EllipseView(ReactContext reactContext) {
        super(reactContext);
    }

    public void setCx(Dynamic dynamic) {
        this.mCx = SVGLength.from(dynamic);
        invalidate();
    }

    public void setCx(String str) {
        this.mCx = SVGLength.from(str);
        invalidate();
    }

    public void setCx(Double d) {
        this.mCx = SVGLength.from(d);
        invalidate();
    }

    public void setCy(Dynamic dynamic) {
        this.mCy = SVGLength.from(dynamic);
        invalidate();
    }

    public void setCy(String str) {
        this.mCy = SVGLength.from(str);
        invalidate();
    }

    public void setCy(Double d) {
        this.mCy = SVGLength.from(d);
        invalidate();
    }

    public void setRx(Dynamic dynamic) {
        this.mRx = SVGLength.from(dynamic);
        invalidate();
    }

    public void setRx(String str) {
        this.mRx = SVGLength.from(str);
        invalidate();
    }

    public void setRx(Double d) {
        this.mRx = SVGLength.from(d);
        invalidate();
    }

    public void setRy(Dynamic dynamic) {
        this.mRy = SVGLength.from(dynamic);
        invalidate();
    }

    public void setRy(String str) {
        this.mRy = SVGLength.from(str);
        invalidate();
    }

    public void setRy(Double d) {
        this.mRy = SVGLength.from(d);
        invalidate();
    }

    /* access modifiers changed from: package-private */
    public Path getPath(Canvas canvas, Paint paint) {
        Path path = new Path();
        double relativeOnWidth = relativeOnWidth(this.mCx);
        double relativeOnHeight = relativeOnHeight(this.mCy);
        double relativeOnWidth2 = relativeOnWidth(this.mRx);
        double relativeOnHeight2 = relativeOnHeight(this.mRy);
        double d = relativeOnWidth - relativeOnWidth2;
        double d2 = relativeOnHeight - relativeOnHeight2;
        double d3 = relativeOnWidth2 + relativeOnWidth;
        double d4 = relativeOnHeight2 + relativeOnHeight;
        double d5 = relativeOnHeight;
        path.addOval(new RectF((float) d, (float) d2, (float) d3, (float) d4), Path.Direction.CW);
        this.elements = new ArrayList();
        this.elements.add(new PathElement(ElementType.kCGPathElementMoveToPoint, new Point[]{new Point(relativeOnWidth, d2)}));
        double d6 = d2;
        double d7 = d5;
        this.elements.add(new PathElement(ElementType.kCGPathElementAddLineToPoint, new Point[]{new Point(relativeOnWidth, d2), new Point(d3, d7)}));
        this.elements.add(new PathElement(ElementType.kCGPathElementAddLineToPoint, new Point[]{new Point(d3, d7), new Point(relativeOnWidth, d4)}));
        double d8 = d;
        this.elements.add(new PathElement(ElementType.kCGPathElementAddLineToPoint, new Point[]{new Point(relativeOnWidth, d4), new Point(d8, d7)}));
        this.elements.add(new PathElement(ElementType.kCGPathElementAddLineToPoint, new Point[]{new Point(d8, d7), new Point(relativeOnWidth, d6)}));
        return path;
    }
}
