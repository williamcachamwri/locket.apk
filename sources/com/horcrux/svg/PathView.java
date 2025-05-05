package com.horcrux.svg;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import com.facebook.react.bridge.ReactContext;
import java.util.Iterator;

class PathView extends RenderableView {
    private Path mPath = new Path();

    public PathView(ReactContext reactContext) {
        super(reactContext);
        PathParser.mScale = this.mScale;
    }

    public void setD(String str) {
        this.mPath = PathParser.parse(str);
        this.elements = PathParser.elements;
        Iterator it = this.elements.iterator();
        while (it.hasNext()) {
            for (Point point : ((PathElement) it.next()).points) {
                point.x *= (double) this.mScale;
                point.y *= (double) this.mScale;
            }
        }
        invalidate();
    }

    /* access modifiers changed from: package-private */
    public Path getPath(Canvas canvas, Paint paint) {
        return this.mPath;
    }
}
