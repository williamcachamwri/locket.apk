package org.reactnative.maskedview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.view.View;
import com.facebook.react.views.view.ReactViewGroup;

public class RNCMaskedView extends ReactViewGroup {
    private static final String TAG = "RNCMaskedView";
    private Bitmap mBitmapMask = null;
    private boolean mBitmapMaskInvalidated = false;
    private Paint mPaint;
    private PorterDuffXfermode mPorterDuffXferMode;

    public RNCMaskedView(Context context) {
        super(context);
        setRenderingMode("hardware");
        this.mPaint = new Paint(1);
        this.mPorterDuffXferMode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (this.mBitmapMaskInvalidated) {
            updateBitmapMask();
            this.mBitmapMaskInvalidated = false;
        }
        if (this.mBitmapMask != null) {
            this.mPaint.setXfermode(this.mPorterDuffXferMode);
            canvas.drawBitmap(this.mBitmapMask, 0.0f, 0.0f, this.mPaint);
            this.mPaint.setXfermode((Xfermode) null);
        }
    }

    public void onDescendantInvalidated(View view, View view2) {
        View childAt;
        super.onDescendantInvalidated(view, view2);
        if (!this.mBitmapMaskInvalidated && (childAt = getChildAt(0)) != null && childAt.equals(view)) {
            this.mBitmapMaskInvalidated = true;
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (z) {
            this.mBitmapMaskInvalidated = true;
        }
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mBitmapMaskInvalidated = true;
    }

    private void updateBitmapMask() {
        View childAt = getChildAt(0);
        if (childAt != null) {
            childAt.setVisibility(0);
            Bitmap bitmap = this.mBitmapMask;
            if (bitmap != null) {
                bitmap.recycle();
            }
            this.mBitmapMask = getBitmapFromView(childAt);
            childAt.setVisibility(4);
        }
    }

    public static Bitmap getBitmapFromView(View view) {
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        if (view.getMeasuredWidth() <= 0 || view.getMeasuredHeight() <= 0) {
            return null;
        }
        Bitmap createBitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        view.draw(new Canvas(createBitmap));
        return createBitmap;
    }

    public void setRenderingMode(String str) {
        if (str.equals("software")) {
            setLayerType(1, (Paint) null);
        } else {
            setLayerType(2, (Paint) null);
        }
    }
}
