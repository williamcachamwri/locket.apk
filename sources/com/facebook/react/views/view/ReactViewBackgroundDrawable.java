package com.facebook.react.views.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.DashPathEffect;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import androidx.core.view.ViewCompat;
import com.facebook.react.common.annotations.VisibleForTesting;
import com.facebook.react.config.ReactFeatureFlags;
import com.facebook.react.modules.i18nmanager.I18nUtil;
import com.facebook.react.uimanager.FloatUtil;
import com.facebook.react.uimanager.Spacing;
import com.facebook.yoga.YogaConstants;
import java.util.Arrays;
import java.util.Locale;

public class ReactViewBackgroundDrawable extends Drawable {
    private static final int ALL_BITS_SET = -1;
    private static final int ALL_BITS_UNSET = 0;
    private static final int DEFAULT_BORDER_ALPHA = 255;
    private static final int DEFAULT_BORDER_COLOR = -16777216;
    private static final int DEFAULT_BORDER_RGB = 0;
    private int mAlpha = 255;
    private Path mBackgroundColorRenderPath;
    private Spacing mBorderAlpha;
    private float[] mBorderCornerRadii;
    private Spacing mBorderRGB;
    private float mBorderRadius = Float.NaN;
    private BorderStyle mBorderStyle;
    private Spacing mBorderWidth;
    private Path mCenterDrawPath;
    private int mColor = 0;
    private final Context mContext;
    private final float mGapBetweenPaths;
    private PointF mInnerBottomLeftCorner;
    private PointF mInnerBottomRightCorner;
    private Path mInnerClipPathForBorderRadius;
    private RectF mInnerClipTempRectForBorderRadius;
    private PointF mInnerTopLeftCorner;
    private PointF mInnerTopRightCorner;
    private int mLayoutDirection;
    private boolean mNeedUpdatePathForBorderRadius = false;
    private Path mOuterClipPathForBorderRadius;
    private RectF mOuterClipTempRectForBorderRadius;
    private final Paint mPaint = new Paint(1);
    private Path mPathForBorder;
    private Path mPathForBorderRadiusOutline;
    private final Path mPathForSingleBorder = new Path();
    private RectF mTempRectForBorderRadiusOutline;
    private RectF mTempRectForCenterDrawPath;

    public enum BorderRadiusLocation {
        TOP_LEFT,
        TOP_RIGHT,
        BOTTOM_RIGHT,
        BOTTOM_LEFT,
        TOP_START,
        TOP_END,
        BOTTOM_START,
        BOTTOM_END,
        END_END,
        END_START,
        START_END,
        START_START
    }

    private static int colorFromAlphaAndRGBComponents(float f, float f2) {
        return ((((int) f) << 24) & -16777216) | (((int) f2) & ViewCompat.MEASURED_SIZE_MASK);
    }

    private static int fastBorderCompatibleColorOrZero(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        int i9 = -1;
        int i10 = (i > 0 ? i5 : -1) & (i2 > 0 ? i6 : -1) & (i3 > 0 ? i7 : -1);
        if (i4 > 0) {
            i9 = i8;
        }
        int i11 = i9 & i10;
        if (i <= 0) {
            i5 = 0;
        }
        if (i2 <= 0) {
            i6 = 0;
        }
        int i12 = i5 | i6;
        if (i3 <= 0) {
            i7 = 0;
        }
        int i13 = i12 | i7;
        if (i4 <= 0) {
            i8 = 0;
        }
        if (i11 == (i13 | i8)) {
            return i11;
        }
        return 0;
    }

    public boolean onResolvedLayoutDirectionChanged(int i) {
        return false;
    }

    public void setColorFilter(ColorFilter colorFilter) {
    }

    /* renamed from: com.facebook.react.views.view.ReactViewBackgroundDrawable$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$react$views$view$ReactViewBackgroundDrawable$BorderStyle;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.facebook.react.views.view.ReactViewBackgroundDrawable$BorderStyle[] r0 = com.facebook.react.views.view.ReactViewBackgroundDrawable.BorderStyle.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$facebook$react$views$view$ReactViewBackgroundDrawable$BorderStyle = r0
                com.facebook.react.views.view.ReactViewBackgroundDrawable$BorderStyle r1 = com.facebook.react.views.view.ReactViewBackgroundDrawable.BorderStyle.SOLID     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$facebook$react$views$view$ReactViewBackgroundDrawable$BorderStyle     // Catch:{ NoSuchFieldError -> 0x001d }
                com.facebook.react.views.view.ReactViewBackgroundDrawable$BorderStyle r1 = com.facebook.react.views.view.ReactViewBackgroundDrawable.BorderStyle.DASHED     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$facebook$react$views$view$ReactViewBackgroundDrawable$BorderStyle     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.facebook.react.views.view.ReactViewBackgroundDrawable$BorderStyle r1 = com.facebook.react.views.view.ReactViewBackgroundDrawable.BorderStyle.DOTTED     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.view.ReactViewBackgroundDrawable.AnonymousClass1.<clinit>():void");
        }
    }

    private enum BorderStyle {
        SOLID,
        DASHED,
        DOTTED;

        public static PathEffect getPathEffect(BorderStyle borderStyle, float f) {
            int i = AnonymousClass1.$SwitchMap$com$facebook$react$views$view$ReactViewBackgroundDrawable$BorderStyle[borderStyle.ordinal()];
            if (i == 2) {
                float f2 = f * 3.0f;
                return new DashPathEffect(new float[]{f2, f2, f2, f2}, 0.0f);
            } else if (i != 3) {
                return null;
            } else {
                return new DashPathEffect(new float[]{f, f, f, f}, 0.0f);
            }
        }
    }

    public ReactViewBackgroundDrawable(Context context) {
        this.mGapBetweenPaths = ReactFeatureFlags.enableCloseVisibleGapBetweenPaths ? 0.8f : 0.0f;
        this.mContext = context;
    }

    public void draw(Canvas canvas) {
        updatePathEffect();
        if (!hasRoundedBorders()) {
            drawRectangularBackgroundWithBorders(canvas);
        } else {
            drawRoundedBackgroundWithBorders(canvas);
        }
    }

    public boolean hasRoundedBorders() {
        if (!YogaConstants.isUndefined(this.mBorderRadius) && this.mBorderRadius > 0.0f) {
            return true;
        }
        float[] fArr = this.mBorderCornerRadii;
        if (fArr != null) {
            for (float f : fArr) {
                if (!YogaConstants.isUndefined(f) && f > 0.0f) {
                    return true;
                }
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.mNeedUpdatePathForBorderRadius = true;
    }

    public void setAlpha(int i) {
        if (i != this.mAlpha) {
            this.mAlpha = i;
            invalidateSelf();
        }
    }

    public int getAlpha() {
        return this.mAlpha;
    }

    public int getOpacity() {
        return ColorUtil.getOpacityFromColor(ColorUtil.multiplyColorAlpha(this.mColor, this.mAlpha));
    }

    public void getOutline(Outline outline) {
        if ((YogaConstants.isUndefined(this.mBorderRadius) || this.mBorderRadius <= 0.0f) && this.mBorderCornerRadii == null) {
            outline.setRect(getBounds());
            return;
        }
        updatePath();
        outline.setConvexPath(this.mPathForBorderRadiusOutline);
    }

    public void setBorderWidth(int i, float f) {
        if (this.mBorderWidth == null) {
            this.mBorderWidth = new Spacing();
        }
        if (!FloatUtil.floatsEqual(this.mBorderWidth.getRaw(i), f)) {
            this.mBorderWidth.set(i, f);
            if (i == 0 || i == 1 || i == 2 || i == 3 || i == 4 || i == 5 || i == 8) {
                this.mNeedUpdatePathForBorderRadius = true;
            }
            invalidateSelf();
        }
    }

    public void setBorderColor(int i, float f, float f2) {
        setBorderRGB(i, f);
        setBorderAlpha(i, f2);
        this.mNeedUpdatePathForBorderRadius = true;
    }

    private void setBorderRGB(int i, float f) {
        if (this.mBorderRGB == null) {
            this.mBorderRGB = new Spacing(0.0f);
        }
        if (!FloatUtil.floatsEqual(this.mBorderRGB.getRaw(i), f)) {
            this.mBorderRGB.set(i, f);
            invalidateSelf();
        }
    }

    private void setBorderAlpha(int i, float f) {
        if (this.mBorderAlpha == null) {
            this.mBorderAlpha = new Spacing(255.0f);
        }
        if (!FloatUtil.floatsEqual(this.mBorderAlpha.getRaw(i), f)) {
            this.mBorderAlpha.set(i, f);
            invalidateSelf();
        }
    }

    public void setBorderStyle(String str) {
        BorderStyle valueOf = str == null ? null : BorderStyle.valueOf(str.toUpperCase(Locale.US));
        if (this.mBorderStyle != valueOf) {
            this.mBorderStyle = valueOf;
            this.mNeedUpdatePathForBorderRadius = true;
            invalidateSelf();
        }
    }

    public void setRadius(float f) {
        if (!FloatUtil.floatsEqual(this.mBorderRadius, f)) {
            this.mBorderRadius = f;
            this.mNeedUpdatePathForBorderRadius = true;
            invalidateSelf();
        }
    }

    public void setRadius(float f, int i) {
        if (this.mBorderCornerRadii == null) {
            float[] fArr = new float[12];
            this.mBorderCornerRadii = fArr;
            Arrays.fill(fArr, Float.NaN);
        }
        if (!FloatUtil.floatsEqual(this.mBorderCornerRadii[i], f)) {
            this.mBorderCornerRadii[i] = f;
            this.mNeedUpdatePathForBorderRadius = true;
            invalidateSelf();
        }
    }

    public float getFullBorderRadius() {
        if (YogaConstants.isUndefined(this.mBorderRadius)) {
            return 0.0f;
        }
        return this.mBorderRadius;
    }

    public float getBorderRadius(BorderRadiusLocation borderRadiusLocation) {
        return getBorderRadiusOrDefaultTo(Float.NaN, borderRadiusLocation);
    }

    public float getBorderRadiusOrDefaultTo(float f, BorderRadiusLocation borderRadiusLocation) {
        float[] fArr = this.mBorderCornerRadii;
        if (fArr == null) {
            return f;
        }
        float f2 = fArr[borderRadiusLocation.ordinal()];
        return YogaConstants.isUndefined(f2) ? f : f2;
    }

    public void setColor(int i) {
        this.mColor = i;
        invalidateSelf();
    }

    public int getResolvedLayoutDirection() {
        return this.mLayoutDirection;
    }

    public boolean setResolvedLayoutDirection(int i) {
        if (this.mLayoutDirection == i) {
            return false;
        }
        this.mLayoutDirection = i;
        return onResolvedLayoutDirectionChanged(i);
    }

    @VisibleForTesting
    public int getColor() {
        return this.mColor;
    }

    private void drawRoundedBackgroundWithBorders(Canvas canvas) {
        int i;
        int i2;
        float f;
        float f2;
        float f3;
        float f4;
        Canvas canvas2 = canvas;
        updatePath();
        canvas.save();
        canvas2.clipPath(this.mOuterClipPathForBorderRadius, Region.Op.INTERSECT);
        int multiplyColorAlpha = ColorUtil.multiplyColorAlpha(this.mColor, this.mAlpha);
        if (Color.alpha(multiplyColorAlpha) != 0) {
            this.mPaint.setColor(multiplyColorAlpha);
            this.mPaint.setStyle(Paint.Style.FILL);
            canvas2.drawPath(this.mBackgroundColorRenderPath, this.mPaint);
        }
        RectF directionAwareBorderInsets = getDirectionAwareBorderInsets();
        boolean z = false;
        int borderColor = getBorderColor(0);
        int borderColor2 = getBorderColor(1);
        int borderColor3 = getBorderColor(2);
        int borderColor4 = getBorderColor(3);
        int borderColor5 = getBorderColor(9);
        int borderColor6 = getBorderColor(11);
        int borderColor7 = getBorderColor(10);
        if (isBorderColorDefined(9)) {
            borderColor2 = borderColor5;
            borderColor4 = borderColor2;
        }
        if (!isBorderColorDefined(10)) {
            borderColor7 = borderColor4;
        }
        int i3 = isBorderColorDefined(11) ? borderColor6 : borderColor2;
        if (directionAwareBorderInsets.top > 0.0f || directionAwareBorderInsets.bottom > 0.0f || directionAwareBorderInsets.left > 0.0f || directionAwareBorderInsets.right > 0.0f) {
            float fullBorderWidth = getFullBorderWidth();
            int borderColor8 = getBorderColor(8);
            if (directionAwareBorderInsets.top != fullBorderWidth || directionAwareBorderInsets.bottom != fullBorderWidth || directionAwareBorderInsets.left != fullBorderWidth || directionAwareBorderInsets.right != fullBorderWidth || borderColor != borderColor8 || i3 != borderColor8 || borderColor3 != borderColor8 || borderColor7 != borderColor8) {
                this.mPaint.setStyle(Paint.Style.FILL);
                canvas2.clipPath(this.mInnerClipPathForBorderRadius, Region.Op.DIFFERENCE);
                if (getResolvedLayoutDirection() == 1) {
                    z = true;
                }
                int borderColor9 = getBorderColor(4);
                int borderColor10 = getBorderColor(5);
                if (I18nUtil.getInstance().doLeftAndRightSwapInRTL(this.mContext)) {
                    if (isBorderColorDefined(4)) {
                        borderColor = borderColor9;
                    }
                    if (isBorderColorDefined(5)) {
                        borderColor3 = borderColor10;
                    }
                    i2 = z ? borderColor3 : borderColor;
                    if (!z) {
                        borderColor = borderColor3;
                    }
                    i = borderColor;
                } else {
                    int i4 = z ? borderColor10 : borderColor9;
                    if (!z) {
                        borderColor9 = borderColor10;
                    }
                    boolean isBorderColorDefined = isBorderColorDefined(4);
                    boolean isBorderColorDefined2 = isBorderColorDefined(5);
                    boolean z2 = z ? isBorderColorDefined2 : isBorderColorDefined;
                    if (!z) {
                        isBorderColorDefined = isBorderColorDefined2;
                    }
                    if (z2) {
                        borderColor = i4;
                    }
                    if (isBorderColorDefined) {
                        i2 = borderColor;
                        i = borderColor9;
                    } else {
                        i2 = borderColor;
                        i = borderColor3;
                    }
                }
                float f5 = this.mOuterClipTempRectForBorderRadius.left;
                float f6 = this.mOuterClipTempRectForBorderRadius.right;
                float f7 = this.mOuterClipTempRectForBorderRadius.top;
                float f8 = this.mOuterClipTempRectForBorderRadius.bottom;
                if (directionAwareBorderInsets.left > 0.0f) {
                    float f9 = f7 - this.mGapBetweenPaths;
                    float f10 = this.mInnerTopLeftCorner.x;
                    float f11 = this.mInnerTopLeftCorner.y - this.mGapBetweenPaths;
                    float f12 = this.mInnerBottomLeftCorner.x;
                    float f13 = this.mInnerBottomLeftCorner.y;
                    float f14 = this.mGapBetweenPaths;
                    f2 = f8;
                    f3 = f7;
                    f4 = f6;
                    f = f5;
                    drawQuadrilateral(canvas, i2, f5, f9, f10, f11, f12, f13 + f14, f5, f8 + f14);
                } else {
                    f2 = f8;
                    f3 = f7;
                    f4 = f6;
                    f = f5;
                }
                if (directionAwareBorderInsets.top > 0.0f) {
                    drawQuadrilateral(canvas, i3, f - this.mGapBetweenPaths, f3, this.mInnerTopLeftCorner.x - this.mGapBetweenPaths, this.mInnerTopLeftCorner.y, this.mInnerTopRightCorner.x + this.mGapBetweenPaths, this.mInnerTopRightCorner.y, f4 + this.mGapBetweenPaths, f3);
                }
                if (directionAwareBorderInsets.right > 0.0f) {
                    float f15 = f3 - this.mGapBetweenPaths;
                    float f16 = this.mInnerTopRightCorner.x;
                    float f17 = this.mInnerTopRightCorner.y - this.mGapBetweenPaths;
                    float f18 = this.mInnerBottomRightCorner.x;
                    float f19 = this.mInnerBottomRightCorner.y;
                    float f20 = this.mGapBetweenPaths;
                    drawQuadrilateral(canvas, i, f4, f15, f16, f17, f18, f19 + f20, f4, f2 + f20);
                }
                if (directionAwareBorderInsets.bottom > 0.0f) {
                    drawQuadrilateral(canvas, borderColor7, f - this.mGapBetweenPaths, f2, this.mInnerBottomLeftCorner.x - this.mGapBetweenPaths, this.mInnerBottomLeftCorner.y, this.mInnerBottomRightCorner.x + this.mGapBetweenPaths, this.mInnerBottomRightCorner.y, f4 + this.mGapBetweenPaths, f2);
                }
            } else if (fullBorderWidth > 0.0f) {
                this.mPaint.setColor(ColorUtil.multiplyColorAlpha(borderColor8, this.mAlpha));
                this.mPaint.setStyle(Paint.Style.STROKE);
                this.mPaint.setStrokeWidth(fullBorderWidth);
                canvas2.drawPath(this.mCenterDrawPath, this.mPaint);
            }
        }
        canvas.restore();
    }

    private void updatePath() {
        float f;
        float f2;
        float f3;
        if (this.mNeedUpdatePathForBorderRadius) {
            this.mNeedUpdatePathForBorderRadius = false;
            if (this.mInnerClipPathForBorderRadius == null) {
                this.mInnerClipPathForBorderRadius = new Path();
            }
            if (this.mBackgroundColorRenderPath == null) {
                this.mBackgroundColorRenderPath = new Path();
            }
            if (this.mOuterClipPathForBorderRadius == null) {
                this.mOuterClipPathForBorderRadius = new Path();
            }
            if (this.mPathForBorderRadiusOutline == null) {
                this.mPathForBorderRadiusOutline = new Path();
            }
            if (this.mCenterDrawPath == null) {
                this.mCenterDrawPath = new Path();
            }
            if (this.mInnerClipTempRectForBorderRadius == null) {
                this.mInnerClipTempRectForBorderRadius = new RectF();
            }
            if (this.mOuterClipTempRectForBorderRadius == null) {
                this.mOuterClipTempRectForBorderRadius = new RectF();
            }
            if (this.mTempRectForBorderRadiusOutline == null) {
                this.mTempRectForBorderRadiusOutline = new RectF();
            }
            if (this.mTempRectForCenterDrawPath == null) {
                this.mTempRectForCenterDrawPath = new RectF();
            }
            this.mInnerClipPathForBorderRadius.reset();
            this.mBackgroundColorRenderPath.reset();
            this.mOuterClipPathForBorderRadius.reset();
            this.mPathForBorderRadiusOutline.reset();
            this.mCenterDrawPath.reset();
            this.mInnerClipTempRectForBorderRadius.set(getBounds());
            this.mOuterClipTempRectForBorderRadius.set(getBounds());
            this.mTempRectForBorderRadiusOutline.set(getBounds());
            this.mTempRectForCenterDrawPath.set(getBounds());
            RectF directionAwareBorderInsets = getDirectionAwareBorderInsets();
            int borderColor = getBorderColor(0);
            int borderColor2 = getBorderColor(1);
            int borderColor3 = getBorderColor(2);
            int borderColor4 = getBorderColor(3);
            int borderColor5 = getBorderColor(8);
            int borderColor6 = getBorderColor(9);
            int borderColor7 = getBorderColor(11);
            int borderColor8 = getBorderColor(10);
            if (isBorderColorDefined(9)) {
                borderColor2 = borderColor6;
                borderColor4 = borderColor2;
            }
            if (!isBorderColorDefined(10)) {
                borderColor8 = borderColor4;
            }
            if (!isBorderColorDefined(11)) {
                borderColor7 = borderColor2;
            }
            if (!(Color.alpha(borderColor) == 0 || Color.alpha(borderColor7) == 0 || Color.alpha(borderColor3) == 0 || Color.alpha(borderColor8) == 0 || Color.alpha(borderColor5) == 0)) {
                this.mInnerClipTempRectForBorderRadius.top += directionAwareBorderInsets.top;
                this.mInnerClipTempRectForBorderRadius.bottom -= directionAwareBorderInsets.bottom;
                this.mInnerClipTempRectForBorderRadius.left += directionAwareBorderInsets.left;
                this.mInnerClipTempRectForBorderRadius.right -= directionAwareBorderInsets.right;
            }
            this.mTempRectForCenterDrawPath.top += directionAwareBorderInsets.top * 0.5f;
            this.mTempRectForCenterDrawPath.bottom -= directionAwareBorderInsets.bottom * 0.5f;
            this.mTempRectForCenterDrawPath.left += directionAwareBorderInsets.left * 0.5f;
            this.mTempRectForCenterDrawPath.right -= directionAwareBorderInsets.right * 0.5f;
            float fullBorderRadius = getFullBorderRadius();
            float borderRadiusOrDefaultTo = getBorderRadiusOrDefaultTo(fullBorderRadius, BorderRadiusLocation.TOP_LEFT);
            float borderRadiusOrDefaultTo2 = getBorderRadiusOrDefaultTo(fullBorderRadius, BorderRadiusLocation.TOP_RIGHT);
            float borderRadiusOrDefaultTo3 = getBorderRadiusOrDefaultTo(fullBorderRadius, BorderRadiusLocation.BOTTOM_LEFT);
            float borderRadiusOrDefaultTo4 = getBorderRadiusOrDefaultTo(fullBorderRadius, BorderRadiusLocation.BOTTOM_RIGHT);
            boolean z = getResolvedLayoutDirection() == 1;
            float borderRadius = getBorderRadius(BorderRadiusLocation.TOP_START);
            float borderRadius2 = getBorderRadius(BorderRadiusLocation.TOP_END);
            float borderRadius3 = getBorderRadius(BorderRadiusLocation.BOTTOM_START);
            float borderRadius4 = getBorderRadius(BorderRadiusLocation.BOTTOM_END);
            float borderRadius5 = getBorderRadius(BorderRadiusLocation.END_END);
            float borderRadius6 = getBorderRadius(BorderRadiusLocation.END_START);
            float borderRadius7 = getBorderRadius(BorderRadiusLocation.START_END);
            float borderRadius8 = getBorderRadius(BorderRadiusLocation.START_START);
            if (I18nUtil.getInstance().doLeftAndRightSwapInRTL(this.mContext)) {
                if (!YogaConstants.isUndefined(borderRadius)) {
                    borderRadiusOrDefaultTo = borderRadius;
                }
                if (!YogaConstants.isUndefined(borderRadius2)) {
                    borderRadiusOrDefaultTo2 = borderRadius2;
                }
                if (!YogaConstants.isUndefined(borderRadius3)) {
                    borderRadiusOrDefaultTo3 = borderRadius3;
                }
                if (!YogaConstants.isUndefined(borderRadius4)) {
                    borderRadiusOrDefaultTo4 = borderRadius4;
                }
                f3 = YogaConstants.isUndefined(borderRadiusOrDefaultTo) ? borderRadius8 : borderRadiusOrDefaultTo;
                if (!YogaConstants.isUndefined(borderRadiusOrDefaultTo2)) {
                    borderRadius7 = borderRadiusOrDefaultTo2;
                }
                if (!YogaConstants.isUndefined(borderRadiusOrDefaultTo3)) {
                    borderRadius6 = borderRadiusOrDefaultTo3;
                }
                if (!YogaConstants.isUndefined(borderRadiusOrDefaultTo4)) {
                    borderRadius5 = borderRadiusOrDefaultTo4;
                }
                f2 = z ? borderRadius7 : f3;
                if (!z) {
                    f3 = borderRadius7;
                }
                f = z ? borderRadius5 : borderRadius6;
                if (!z) {
                    borderRadius6 = borderRadius5;
                }
            } else {
                if (YogaConstants.isUndefined(borderRadius)) {
                    borderRadius = borderRadius8;
                }
                if (YogaConstants.isUndefined(borderRadius2)) {
                    borderRadius2 = borderRadius7;
                }
                if (YogaConstants.isUndefined(borderRadius3)) {
                    borderRadius3 = borderRadius6;
                }
                if (YogaConstants.isUndefined(borderRadius4)) {
                    borderRadius4 = borderRadius5;
                }
                float f4 = z ? borderRadius2 : borderRadius;
                if (!z) {
                    borderRadius = borderRadius2;
                }
                float f5 = z ? borderRadius4 : borderRadius3;
                if (!z) {
                    borderRadius3 = borderRadius4;
                }
                if (!YogaConstants.isUndefined(f4)) {
                    borderRadiusOrDefaultTo = f4;
                }
                if (!YogaConstants.isUndefined(borderRadius)) {
                    borderRadiusOrDefaultTo2 = borderRadius;
                }
                if (!YogaConstants.isUndefined(f5)) {
                    borderRadiusOrDefaultTo3 = f5;
                }
                if (!YogaConstants.isUndefined(borderRadius3)) {
                    f2 = borderRadiusOrDefaultTo;
                    f3 = borderRadiusOrDefaultTo2;
                    f = borderRadiusOrDefaultTo3;
                    borderRadius6 = borderRadius3;
                } else {
                    borderRadius6 = borderRadiusOrDefaultTo4;
                    f2 = borderRadiusOrDefaultTo;
                    f3 = borderRadiusOrDefaultTo2;
                    f = borderRadiusOrDefaultTo3;
                }
            }
            float max = Math.max(f2 - directionAwareBorderInsets.left, 0.0f);
            float max2 = Math.max(f2 - directionAwareBorderInsets.top, 0.0f);
            float max3 = Math.max(f3 - directionAwareBorderInsets.right, 0.0f);
            float max4 = Math.max(f3 - directionAwareBorderInsets.top, 0.0f);
            float max5 = Math.max(borderRadius6 - directionAwareBorderInsets.right, 0.0f);
            float max6 = Math.max(borderRadius6 - directionAwareBorderInsets.bottom, 0.0f);
            float max7 = Math.max(f - directionAwareBorderInsets.left, 0.0f);
            float max8 = Math.max(f - directionAwareBorderInsets.bottom, 0.0f);
            RectF rectF = directionAwareBorderInsets;
            float f6 = f;
            this.mInnerClipPathForBorderRadius.addRoundRect(this.mInnerClipTempRectForBorderRadius, new float[]{max, max2, max3, max4, max5, max6, max7, max8}, Path.Direction.CW);
            this.mBackgroundColorRenderPath.addRoundRect(this.mInnerClipTempRectForBorderRadius.left - this.mGapBetweenPaths, this.mInnerClipTempRectForBorderRadius.top - this.mGapBetweenPaths, this.mInnerClipTempRectForBorderRadius.right + this.mGapBetweenPaths, this.mInnerClipTempRectForBorderRadius.bottom + this.mGapBetweenPaths, new float[]{max, max2, max3, max4, max5, max6, max7, max8}, Path.Direction.CW);
            this.mOuterClipPathForBorderRadius.addRoundRect(this.mOuterClipTempRectForBorderRadius, new float[]{f2, f2, f3, f3, borderRadius6, borderRadius6, f6, f6}, Path.Direction.CW);
            Spacing spacing = this.mBorderWidth;
            float f7 = spacing != null ? spacing.get(8) / 2.0f : 0.0f;
            float f8 = max6;
            float f9 = f2 + f7;
            float f10 = f3 + f7;
            float f11 = borderRadius6 + f7;
            float f12 = f6 + f7;
            this.mPathForBorderRadiusOutline.addRoundRect(this.mTempRectForBorderRadiusOutline, new float[]{f9, f9, f10, f10, f11, f11, f12, f12}, Path.Direction.CW);
            Path path = this.mCenterDrawPath;
            RectF rectF2 = this.mTempRectForCenterDrawPath;
            float[] fArr = new float[8];
            RectF rectF3 = rectF;
            float f13 = max5;
            fArr[0] = Math.max(f2 - (rectF3.left * 0.5f), rectF3.left > 0.0f ? f2 / rectF3.left : 0.0f);
            fArr[1] = Math.max(f2 - (rectF3.top * 0.5f), rectF3.top > 0.0f ? f2 / rectF3.top : 0.0f);
            fArr[2] = Math.max(f3 - (rectF3.right * 0.5f), rectF3.right > 0.0f ? f3 / rectF3.right : 0.0f);
            fArr[3] = Math.max(f3 - (rectF3.top * 0.5f), rectF3.top > 0.0f ? f3 / rectF3.top : 0.0f);
            fArr[4] = Math.max(borderRadius6 - (rectF3.right * 0.5f), rectF3.right > 0.0f ? borderRadius6 / rectF3.right : 0.0f);
            fArr[5] = Math.max(borderRadius6 - (rectF3.bottom * 0.5f), rectF3.bottom > 0.0f ? borderRadius6 / rectF3.bottom : 0.0f);
            fArr[6] = Math.max(f6 - (rectF3.left * 0.5f), rectF3.left > 0.0f ? f6 / rectF3.left : 0.0f);
            fArr[7] = Math.max(f6 - (rectF3.bottom * 0.5f), rectF3.bottom > 0.0f ? f6 / rectF3.bottom : 0.0f);
            path.addRoundRect(rectF2, fArr, Path.Direction.CW);
            if (this.mInnerTopLeftCorner == null) {
                this.mInnerTopLeftCorner = new PointF();
            }
            this.mInnerTopLeftCorner.x = this.mInnerClipTempRectForBorderRadius.left;
            this.mInnerTopLeftCorner.y = this.mInnerClipTempRectForBorderRadius.top;
            getEllipseIntersectionWithLine((double) this.mInnerClipTempRectForBorderRadius.left, (double) this.mInnerClipTempRectForBorderRadius.top, (double) (this.mInnerClipTempRectForBorderRadius.left + (max * 2.0f)), (double) (this.mInnerClipTempRectForBorderRadius.top + (max2 * 2.0f)), (double) this.mOuterClipTempRectForBorderRadius.left, (double) this.mOuterClipTempRectForBorderRadius.top, (double) this.mInnerClipTempRectForBorderRadius.left, (double) this.mInnerClipTempRectForBorderRadius.top, this.mInnerTopLeftCorner);
            if (this.mInnerBottomLeftCorner == null) {
                this.mInnerBottomLeftCorner = new PointF();
            }
            this.mInnerBottomLeftCorner.x = this.mInnerClipTempRectForBorderRadius.left;
            this.mInnerBottomLeftCorner.y = this.mInnerClipTempRectForBorderRadius.bottom;
            getEllipseIntersectionWithLine((double) this.mInnerClipTempRectForBorderRadius.left, (double) (this.mInnerClipTempRectForBorderRadius.bottom - (max8 * 2.0f)), (double) (this.mInnerClipTempRectForBorderRadius.left + (max7 * 2.0f)), (double) this.mInnerClipTempRectForBorderRadius.bottom, (double) this.mOuterClipTempRectForBorderRadius.left, (double) this.mOuterClipTempRectForBorderRadius.bottom, (double) this.mInnerClipTempRectForBorderRadius.left, (double) this.mInnerClipTempRectForBorderRadius.bottom, this.mInnerBottomLeftCorner);
            if (this.mInnerTopRightCorner == null) {
                this.mInnerTopRightCorner = new PointF();
            }
            this.mInnerTopRightCorner.x = this.mInnerClipTempRectForBorderRadius.right;
            this.mInnerTopRightCorner.y = this.mInnerClipTempRectForBorderRadius.top;
            getEllipseIntersectionWithLine((double) (this.mInnerClipTempRectForBorderRadius.right - (max3 * 2.0f)), (double) this.mInnerClipTempRectForBorderRadius.top, (double) this.mInnerClipTempRectForBorderRadius.right, (double) (this.mInnerClipTempRectForBorderRadius.top + (max4 * 2.0f)), (double) this.mOuterClipTempRectForBorderRadius.right, (double) this.mOuterClipTempRectForBorderRadius.top, (double) this.mInnerClipTempRectForBorderRadius.right, (double) this.mInnerClipTempRectForBorderRadius.top, this.mInnerTopRightCorner);
            if (this.mInnerBottomRightCorner == null) {
                this.mInnerBottomRightCorner = new PointF();
            }
            this.mInnerBottomRightCorner.x = this.mInnerClipTempRectForBorderRadius.right;
            this.mInnerBottomRightCorner.y = this.mInnerClipTempRectForBorderRadius.bottom;
            getEllipseIntersectionWithLine((double) (this.mInnerClipTempRectForBorderRadius.right - (f13 * 2.0f)), (double) (this.mInnerClipTempRectForBorderRadius.bottom - (f8 * 2.0f)), (double) this.mInnerClipTempRectForBorderRadius.right, (double) this.mInnerClipTempRectForBorderRadius.bottom, (double) this.mOuterClipTempRectForBorderRadius.right, (double) this.mOuterClipTempRectForBorderRadius.bottom, (double) this.mInnerClipTempRectForBorderRadius.right, (double) this.mInnerClipTempRectForBorderRadius.bottom, this.mInnerBottomRightCorner);
        }
    }

    private static void getEllipseIntersectionWithLine(double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8, PointF pointF) {
        PointF pointF2 = pointF;
        double d9 = (d + d3) / 2.0d;
        double d10 = (d2 + d4) / 2.0d;
        double d11 = d5 - d9;
        double d12 = d6 - d10;
        double abs = Math.abs(d3 - d) / 2.0d;
        double abs2 = Math.abs(d4 - d2) / 2.0d;
        double d13 = ((d8 - d10) - d12) / ((d7 - d9) - d11);
        double d14 = d12 - (d11 * d13);
        double d15 = abs2 * abs2;
        double d16 = abs * abs;
        double d17 = d15 + (d16 * d13 * d13);
        double d18 = abs * 2.0d * abs * d14 * d13;
        double d19 = (-(d16 * ((d14 * d14) - d15))) / d17;
        double d20 = d17 * 2.0d;
        double sqrt = ((-d18) / d20) - Math.sqrt(d19 + Math.pow(d18 / d20, 2.0d));
        double d21 = (d13 * sqrt) + d14;
        double d22 = sqrt + d9;
        double d23 = d21 + d10;
        if (!Double.isNaN(d22) && !Double.isNaN(d23)) {
            PointF pointF3 = pointF;
            pointF3.x = (float) d22;
            pointF3.y = (float) d23;
        }
    }

    public float getBorderWidthOrDefaultTo(float f, int i) {
        Spacing spacing = this.mBorderWidth;
        if (spacing == null) {
            return f;
        }
        float raw = spacing.getRaw(i);
        return YogaConstants.isUndefined(raw) ? f : raw;
    }

    private void updatePathEffect() {
        BorderStyle borderStyle = this.mBorderStyle;
        this.mPaint.setPathEffect(borderStyle != null ? BorderStyle.getPathEffect(borderStyle, getFullBorderWidth()) : null);
    }

    private void updatePathEffect(int i) {
        BorderStyle borderStyle = this.mBorderStyle;
        this.mPaint.setPathEffect(borderStyle != null ? BorderStyle.getPathEffect(borderStyle, (float) i) : null);
    }

    public float getFullBorderWidth() {
        Spacing spacing = this.mBorderWidth;
        if (spacing == null || YogaConstants.isUndefined(spacing.getRaw(8))) {
            return 0.0f;
        }
        return this.mBorderWidth.getRaw(8);
    }

    private void drawRectangularBackgroundWithBorders(Canvas canvas) {
        int i;
        int i2;
        int i3;
        Canvas canvas2 = canvas;
        this.mPaint.setStyle(Paint.Style.FILL);
        int multiplyColorAlpha = ColorUtil.multiplyColorAlpha(this.mColor, this.mAlpha);
        if (Color.alpha(multiplyColorAlpha) != 0) {
            this.mPaint.setColor(multiplyColorAlpha);
            canvas2.drawRect(getBounds(), this.mPaint);
        }
        RectF directionAwareBorderInsets = getDirectionAwareBorderInsets();
        int round = Math.round(directionAwareBorderInsets.left);
        int round2 = Math.round(directionAwareBorderInsets.top);
        int round3 = Math.round(directionAwareBorderInsets.right);
        int round4 = Math.round(directionAwareBorderInsets.bottom);
        if (round > 0 || round3 > 0 || round2 > 0 || round4 > 0) {
            Rect bounds = getBounds();
            int borderColor = getBorderColor(0);
            int borderColor2 = getBorderColor(1);
            int borderColor3 = getBorderColor(2);
            int borderColor4 = getBorderColor(3);
            int borderColor5 = getBorderColor(9);
            int borderColor6 = getBorderColor(11);
            int borderColor7 = getBorderColor(10);
            if (isBorderColorDefined(9)) {
                borderColor2 = borderColor5;
                borderColor4 = borderColor2;
            }
            if (!isBorderColorDefined(10)) {
                borderColor7 = borderColor4;
            }
            if (!isBorderColorDefined(11)) {
                borderColor6 = borderColor2;
            }
            boolean z = getResolvedLayoutDirection() == 1;
            int borderColor8 = getBorderColor(4);
            int borderColor9 = getBorderColor(5);
            if (I18nUtil.getInstance().doLeftAndRightSwapInRTL(this.mContext)) {
                if (isBorderColorDefined(4)) {
                    borderColor = borderColor8;
                }
                if (isBorderColorDefined(5)) {
                    borderColor3 = borderColor9;
                }
                int i4 = z ? borderColor3 : borderColor;
                if (!z) {
                    borderColor = borderColor3;
                }
                i = borderColor;
                i2 = i4;
            } else {
                int i5 = z ? borderColor9 : borderColor8;
                if (!z) {
                    borderColor8 = borderColor9;
                }
                boolean isBorderColorDefined = isBorderColorDefined(4);
                boolean isBorderColorDefined2 = isBorderColorDefined(5);
                boolean z2 = z ? isBorderColorDefined2 : isBorderColorDefined;
                if (!z) {
                    isBorderColorDefined = isBorderColorDefined2;
                }
                if (z2) {
                    borderColor = i5;
                }
                i2 = borderColor;
                i = isBorderColorDefined ? borderColor8 : borderColor3;
            }
            int i6 = bounds.left;
            int i7 = i2;
            int i8 = bounds.top;
            int i9 = round3;
            int i10 = i6;
            int fastBorderCompatibleColorOrZero = fastBorderCompatibleColorOrZero(round, round2, round3, round4, i2, borderColor6, i, borderColor7);
            if (fastBorderCompatibleColorOrZero == 0) {
                this.mPaint.setAntiAlias(false);
                int width = bounds.width();
                int height = bounds.height();
                if (round > 0) {
                    float f = (float) i10;
                    float f2 = (float) (i10 + round);
                    int i11 = i8 + height;
                    i3 = i8;
                    drawQuadrilateral(canvas, i7, f, (float) i8, f2, (float) (i8 + round2), f2, (float) (i11 - round4), f, (float) i11);
                } else {
                    i3 = i8;
                }
                if (round2 > 0) {
                    float f3 = (float) i3;
                    float f4 = (float) (i3 + round2);
                    int i12 = i10 + width;
                    drawQuadrilateral(canvas, borderColor6, (float) i10, f3, (float) (i10 + round), f4, (float) (i12 - i9), f4, (float) i12, f3);
                }
                if (i9 > 0) {
                    int i13 = i10 + width;
                    float f5 = (float) i13;
                    int i14 = i3 + height;
                    float f6 = (float) (i13 - i9);
                    drawQuadrilateral(canvas, i, f5, (float) i3, f5, (float) i14, f6, (float) (i14 - round4), f6, (float) (i3 + round2));
                }
                if (round4 > 0) {
                    int i15 = i3 + height;
                    float f7 = (float) i15;
                    int i16 = i10 + width;
                    float f8 = (float) (i15 - round4);
                    drawQuadrilateral(canvas, borderColor7, (float) i10, f7, (float) i16, f7, (float) (i16 - i9), f8, (float) (i10 + round), f8);
                }
                this.mPaint.setAntiAlias(true);
            } else if (Color.alpha(fastBorderCompatibleColorOrZero) != 0) {
                int i17 = bounds.right;
                int i18 = bounds.bottom;
                this.mPaint.setColor(fastBorderCompatibleColorOrZero);
                this.mPaint.setStyle(Paint.Style.STROKE);
                if (round > 0) {
                    this.mPathForSingleBorder.reset();
                    int round5 = Math.round(directionAwareBorderInsets.left);
                    updatePathEffect(round5);
                    this.mPaint.setStrokeWidth((float) round5);
                    float f9 = (float) (i10 + (round5 / 2));
                    this.mPathForSingleBorder.moveTo(f9, (float) i8);
                    this.mPathForSingleBorder.lineTo(f9, (float) i18);
                    canvas2.drawPath(this.mPathForSingleBorder, this.mPaint);
                }
                if (round2 > 0) {
                    this.mPathForSingleBorder.reset();
                    int round6 = Math.round(directionAwareBorderInsets.top);
                    updatePathEffect(round6);
                    this.mPaint.setStrokeWidth((float) round6);
                    float f10 = (float) (i8 + (round6 / 2));
                    this.mPathForSingleBorder.moveTo((float) i10, f10);
                    this.mPathForSingleBorder.lineTo((float) i17, f10);
                    canvas2.drawPath(this.mPathForSingleBorder, this.mPaint);
                }
                if (i9 > 0) {
                    this.mPathForSingleBorder.reset();
                    int round7 = Math.round(directionAwareBorderInsets.right);
                    updatePathEffect(round7);
                    this.mPaint.setStrokeWidth((float) round7);
                    float f11 = (float) (i17 - (round7 / 2));
                    this.mPathForSingleBorder.moveTo(f11, (float) i8);
                    this.mPathForSingleBorder.lineTo(f11, (float) i18);
                    canvas2.drawPath(this.mPathForSingleBorder, this.mPaint);
                }
                if (round4 > 0) {
                    this.mPathForSingleBorder.reset();
                    int round8 = Math.round(directionAwareBorderInsets.bottom);
                    updatePathEffect(round8);
                    this.mPaint.setStrokeWidth((float) round8);
                    float f12 = (float) (i18 - (round8 / 2));
                    this.mPathForSingleBorder.moveTo((float) i10, f12);
                    this.mPathForSingleBorder.lineTo((float) i17, f12);
                    canvas2.drawPath(this.mPathForSingleBorder, this.mPaint);
                }
            }
        }
    }

    private void drawQuadrilateral(Canvas canvas, int i, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        if (i != 0) {
            if (this.mPathForBorder == null) {
                this.mPathForBorder = new Path();
            }
            this.mPaint.setColor(i);
            this.mPathForBorder.reset();
            this.mPathForBorder.moveTo(f, f2);
            this.mPathForBorder.lineTo(f3, f4);
            this.mPathForBorder.lineTo(f5, f6);
            this.mPathForBorder.lineTo(f7, f8);
            this.mPathForBorder.lineTo(f, f2);
            canvas.drawPath(this.mPathForBorder, this.mPaint);
        }
    }

    private int getBorderWidth(int i) {
        Spacing spacing = this.mBorderWidth;
        if (spacing == null) {
            return 0;
        }
        float f = spacing.get(i);
        if (YogaConstants.isUndefined(f)) {
            return -1;
        }
        return Math.round(f);
    }

    private boolean isBorderColorDefined(int i) {
        Spacing spacing = this.mBorderRGB;
        float f = Float.NaN;
        float f2 = spacing != null ? spacing.get(i) : Float.NaN;
        Spacing spacing2 = this.mBorderAlpha;
        if (spacing2 != null) {
            f = spacing2.get(i);
        }
        return !YogaConstants.isUndefined(f2) && !YogaConstants.isUndefined(f);
    }

    public int getBorderColor(int i) {
        Spacing spacing = this.mBorderRGB;
        float f = spacing != null ? spacing.get(i) : 0.0f;
        Spacing spacing2 = this.mBorderAlpha;
        return colorFromAlphaAndRGBComponents(spacing2 != null ? spacing2.get(i) : 255.0f, f);
    }

    public RectF getDirectionAwareBorderInsets() {
        float borderWidthOrDefaultTo = getBorderWidthOrDefaultTo(0.0f, 8);
        boolean z = true;
        float borderWidthOrDefaultTo2 = getBorderWidthOrDefaultTo(borderWidthOrDefaultTo, 1);
        float borderWidthOrDefaultTo3 = getBorderWidthOrDefaultTo(borderWidthOrDefaultTo, 3);
        float borderWidthOrDefaultTo4 = getBorderWidthOrDefaultTo(borderWidthOrDefaultTo, 0);
        float borderWidthOrDefaultTo5 = getBorderWidthOrDefaultTo(borderWidthOrDefaultTo, 2);
        if (this.mBorderWidth != null) {
            if (getResolvedLayoutDirection() != 1) {
                z = false;
            }
            float raw = this.mBorderWidth.getRaw(4);
            float raw2 = this.mBorderWidth.getRaw(5);
            if (I18nUtil.getInstance().doLeftAndRightSwapInRTL(this.mContext)) {
                if (!YogaConstants.isUndefined(raw)) {
                    borderWidthOrDefaultTo4 = raw;
                }
                if (!YogaConstants.isUndefined(raw2)) {
                    borderWidthOrDefaultTo5 = raw2;
                }
                float f = z ? borderWidthOrDefaultTo5 : borderWidthOrDefaultTo4;
                if (z) {
                    borderWidthOrDefaultTo5 = borderWidthOrDefaultTo4;
                }
                borderWidthOrDefaultTo4 = f;
            } else {
                float f2 = z ? raw2 : raw;
                if (!z) {
                    raw = raw2;
                }
                if (!YogaConstants.isUndefined(f2)) {
                    borderWidthOrDefaultTo4 = f2;
                }
                if (!YogaConstants.isUndefined(raw)) {
                    borderWidthOrDefaultTo5 = raw;
                }
            }
        }
        return new RectF(borderWidthOrDefaultTo4, borderWidthOrDefaultTo2, borderWidthOrDefaultTo5, borderWidthOrDefaultTo3);
    }
}
