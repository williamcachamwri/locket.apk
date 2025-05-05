package com.horcrux.svg;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReactContext;
import java.util.ArrayList;

class CircleView extends RenderableView {
    private SVGLength mCx;
    private SVGLength mCy;
    private SVGLength mR;

    public CircleView(ReactContext reactContext) {
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

    public void setR(Dynamic dynamic) {
        this.mR = SVGLength.from(dynamic);
        invalidate();
    }

    public void setR(String str) {
        this.mR = SVGLength.from(str);
        invalidate();
    }

    public void setR(Double d) {
        this.mR = SVGLength.from(d);
        invalidate();
    }

    /* access modifiers changed from: package-private */
    public Path getPath(Canvas canvas, Paint paint) {
        Path path = new Path();
        double relativeOnWidth = relativeOnWidth(this.mCx);
        double relativeOnHeight = relativeOnHeight(this.mCy);
        double relativeOnOther = relativeOnOther(this.mR);
        path.addCircle((float) relativeOnWidth, (float) relativeOnHeight, (float) relativeOnOther, Path.Direction.CW);
        this.elements = new ArrayList();
        double d = relativeOnHeight - relativeOnOther;
        this.elements.add(new PathElement(ElementType.kCGPathElementMoveToPoint, new Point[]{new Point(relativeOnWidth, d)}));
        double d2 = d;
        double d3 = relativeOnWidth + relativeOnOther;
        this.elements.add(new PathElement(ElementType.kCGPathElementAddLineToPoint, new Point[]{new Point(relativeOnWidth, d), new Point(d3, relativeOnHeight)}));
        double d4 = relativeOnHeight + relativeOnOther;
        this.elements.add(new PathElement(ElementType.kCGPathElementAddLineToPoint, new Point[]{new Point(d3, relativeOnHeight), new Point(relativeOnWidth, d4)}));
        double d5 = relativeOnWidth - relativeOnOther;
        this.elements.add(new PathElement(ElementType.kCGPathElementAddLineToPoint, new Point[]{new Point(relativeOnWidth, d4), new Point(d5, relativeOnHeight)}));
        this.elements.add(new PathElement(ElementType.kCGPathElementAddLineToPoint, new Point[]{new Point(d5, relativeOnHeight), new Point(relativeOnWidth, d2)}));
        return path;
    }
}
