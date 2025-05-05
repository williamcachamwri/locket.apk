package com.facebook.react.views.traceupdateoverlay;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;
import com.facebook.react.uimanager.PixelUtil;
import java.util.ArrayList;
import java.util.List;

public class TraceUpdateOverlay extends View {
    private final Paint mOverlayPaint;
    private List<Overlay> mOverlays = new ArrayList();

    public static class Overlay {
        private final int mColor;
        private final RectF mRect;

        public Overlay(int i, RectF rectF) {
            this.mColor = i;
            this.mRect = rectF;
        }

        public int getColor() {
            return this.mColor;
        }

        public RectF getPixelRect() {
            return new RectF(PixelUtil.toPixelFromDIP(this.mRect.left), PixelUtil.toPixelFromDIP(this.mRect.top), PixelUtil.toPixelFromDIP(this.mRect.right), PixelUtil.toPixelFromDIP(this.mRect.bottom));
        }
    }

    public TraceUpdateOverlay(Context context) {
        super(context);
        Paint paint = new Paint();
        this.mOverlayPaint = paint;
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(6.0f);
    }

    public void setOverlays(List<Overlay> list) {
        this.mOverlays = list;
        invalidate();
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!this.mOverlays.isEmpty()) {
            for (Overlay next : this.mOverlays) {
                this.mOverlayPaint.setColor(next.getColor());
                canvas.drawRect(next.getPixelRect(), this.mOverlayPaint);
            }
        }
    }
}
