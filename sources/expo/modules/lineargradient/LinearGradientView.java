package expo.modules.lineargradient;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.TypedValue;
import android.view.View;

public class LinearGradientView extends View {
    private float[] mBorderRadii = {0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f};
    private int[] mColors;
    private float[] mEndPos = {0.5f, 1.0f};
    private float[] mLocations;
    private final Paint mPaint = new Paint(1);
    private Path mPathForBorderRadius;
    private int[] mSize = {0, 0};
    private float[] mStartPos = {0.5f, 0.0f};
    private RectF mTempRectForBorderRadius;

    public LinearGradientView(Context context) {
        super(context);
    }

    public void setStartPosition(float f, float f2) {
        this.mStartPos = new float[]{f, f2};
        drawGradient();
    }

    public void setEndPosition(float f, float f2) {
        this.mEndPos = new float[]{f, f2};
        drawGradient();
    }

    public void setColors(int[] iArr) {
        this.mColors = iArr;
        drawGradient();
    }

    public void setLocations(float[] fArr) {
        this.mLocations = fArr;
        drawGradient();
    }

    public void setBorderRadii(float[] fArr) {
        for (int i = 0; i < fArr.length; i++) {
            fArr[i] = toPixelFromDIP(fArr[i]);
        }
        this.mBorderRadii = fArr;
        updatePath();
        drawGradient();
    }

    private float toPixelFromDIP(float f) {
        return TypedValue.applyDimension(1, f, getContext().getResources().getDisplayMetrics());
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        this.mSize = new int[]{i, i2};
        updatePath();
        drawGradient();
    }

    private void drawGradient() {
        int[] iArr = this.mColors;
        if (iArr != null) {
            float[] fArr = this.mLocations;
            if (fArr == null || iArr.length == fArr.length) {
                float[] fArr2 = this.mStartPos;
                float f = fArr2[0];
                int[] iArr2 = this.mSize;
                int i = iArr2[0];
                float f2 = fArr2[1];
                int i2 = iArr2[1];
                float[] fArr3 = this.mEndPos;
                float f3 = f2 * ((float) i2);
                this.mPaint.setShader(new LinearGradient(f * ((float) i), f3, ((float) i) * fArr3[0], fArr3[1] * ((float) i2), this.mColors, this.mLocations, Shader.TileMode.CLAMP));
                invalidate();
            }
        }
    }

    private void updatePath() {
        if (this.mPathForBorderRadius == null) {
            this.mPathForBorderRadius = new Path();
            this.mTempRectForBorderRadius = new RectF();
        }
        this.mPathForBorderRadius.reset();
        RectF rectF = this.mTempRectForBorderRadius;
        int[] iArr = this.mSize;
        rectF.set(0.0f, 0.0f, (float) iArr[0], (float) iArr[1]);
        this.mPathForBorderRadius.addRoundRect(this.mTempRectForBorderRadius, this.mBorderRadii, Path.Direction.CW);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Path path = this.mPathForBorderRadius;
        if (path == null) {
            canvas.drawPaint(this.mPaint);
        } else {
            canvas.drawPath(path, this.mPaint);
        }
    }
}
