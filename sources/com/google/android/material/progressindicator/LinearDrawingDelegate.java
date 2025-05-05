package com.google.android.material.progressindicator;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import com.google.android.material.color.MaterialColors;

final class LinearDrawingDelegate extends DrawingDelegate<LinearProgressIndicatorSpec> {
    private float displayedCornerRadius;
    private Path displayedTrackPath;
    private float displayedTrackThickness;
    private float trackLength = 300.0f;

    public int getPreferredWidth() {
        return -1;
    }

    public LinearDrawingDelegate(LinearProgressIndicatorSpec linearProgressIndicatorSpec) {
        super(linearProgressIndicatorSpec);
    }

    public int getPreferredHeight() {
        return ((LinearProgressIndicatorSpec) this.spec).trackThickness;
    }

    public void adjustCanvas(Canvas canvas, Rect rect, float f) {
        this.trackLength = (float) rect.width();
        float f2 = (float) ((LinearProgressIndicatorSpec) this.spec).trackThickness;
        canvas.translate(((float) rect.left) + (((float) rect.width()) / 2.0f), ((float) rect.top) + (((float) rect.height()) / 2.0f) + Math.max(0.0f, ((float) (rect.height() - ((LinearProgressIndicatorSpec) this.spec).trackThickness)) / 2.0f));
        if (((LinearProgressIndicatorSpec) this.spec).drawHorizontallyInverse) {
            canvas.scale(-1.0f, 1.0f);
        }
        if ((this.drawable.isShowing() && ((LinearProgressIndicatorSpec) this.spec).showAnimationBehavior == 1) || (this.drawable.isHiding() && ((LinearProgressIndicatorSpec) this.spec).hideAnimationBehavior == 2)) {
            canvas.scale(1.0f, -1.0f);
        }
        if (this.drawable.isShowing() || this.drawable.isHiding()) {
            canvas.translate(0.0f, (((float) ((LinearProgressIndicatorSpec) this.spec).trackThickness) * (f - 1.0f)) / 2.0f);
        }
        float f3 = this.trackLength;
        canvas.clipRect((-f3) / 2.0f, (-f2) / 2.0f, f3 / 2.0f, f2 / 2.0f);
        this.displayedTrackThickness = ((float) ((LinearProgressIndicatorSpec) this.spec).trackThickness) * f;
        this.displayedCornerRadius = ((float) ((LinearProgressIndicatorSpec) this.spec).trackCornerRadius) * f;
    }

    public void fillIndicator(Canvas canvas, Paint paint, float f, float f2, int i) {
        if (f != f2) {
            float f3 = this.trackLength;
            float f4 = (-f3) / 2.0f;
            paint.setStyle(Paint.Style.FILL);
            paint.setAntiAlias(true);
            paint.setColor(i);
            canvas.save();
            canvas.clipPath(this.displayedTrackPath);
            float f5 = this.displayedTrackThickness;
            RectF rectF = new RectF(((f * f3) + f4) - (this.displayedCornerRadius * 2.0f), (-f5) / 2.0f, f4 + (f2 * f3), f5 / 2.0f);
            float f6 = this.displayedCornerRadius;
            canvas.drawRoundRect(rectF, f6, f6, paint);
            canvas.restore();
        }
    }

    /* access modifiers changed from: package-private */
    public void fillTrack(Canvas canvas, Paint paint) {
        int compositeARGBWithAlpha = MaterialColors.compositeARGBWithAlpha(((LinearProgressIndicatorSpec) this.spec).trackColor, this.drawable.getAlpha());
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setColor(compositeARGBWithAlpha);
        Path path = new Path();
        this.displayedTrackPath = path;
        float f = this.trackLength;
        float f2 = this.displayedTrackThickness;
        RectF rectF = new RectF((-f) / 2.0f, (-f2) / 2.0f, f / 2.0f, f2 / 2.0f);
        float f3 = this.displayedCornerRadius;
        path.addRoundRect(rectF, f3, f3, Path.Direction.CCW);
        canvas.drawPath(this.displayedTrackPath, paint);
    }
}
