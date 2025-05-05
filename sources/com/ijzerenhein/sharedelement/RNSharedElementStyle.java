package com.ijzerenhein.sharedelement;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewParent;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.modules.i18nmanager.I18nUtil;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.views.image.ImageResizeMode;

public class RNSharedElementStyle {
    static Rect EMPTY_RECT = new Rect();
    static RectF EMPTY_RECTF = new RectF();
    static int PROP_BACKGROUND_COLOR = 4;
    static int PROP_BORDER = ((((((8 + 16) + 32) + 64) + 128) + 256) + 512);
    static int PROP_BORDER_BOTTOMLEFTRADIUS = 256;
    static int PROP_BORDER_BOTTOMRIGHT_RADIUS = 512;
    static int PROP_BORDER_COLOR = 8;
    static int PROP_BORDER_STYLE = 32;
    static int PROP_BORDER_TOPLEFTRADIUS = 64;
    static int PROP_BORDER_TOPRIGHTRADIUS = 128;
    static int PROP_BORDER_WIDTH = 16;
    static int PROP_ELEVATION = 2;
    static int PROP_OPACITY = 1;
    static int PROP_SCALETYPE = 1024;
    RectF ancestorLayout = new RectF();
    Matrix ancestorTransform = new Matrix();
    int backgroundColor = 0;
    float borderBottomLeftRadius = 0.0f;
    float borderBottomRightRadius = 0.0f;
    int borderColor = 0;
    String borderStyle = "solid";
    float borderTopLeftRadius = 0.0f;
    float borderTopRightRadius = 0.0f;
    float borderWidth = 0.0f;
    float elevation = 0.0f;
    Rect frame = new Rect();
    RectF layout = new RectF();
    float opacity = 1.0f;
    ScalingUtils.ScaleType scaleType = ScalingUtils.ScaleType.FIT_XY;

    static boolean equalsScaleType(ScalingUtils.ScaleType scaleType2, ScalingUtils.ScaleType scaleType3) {
        return scaleType2 == scaleType3;
    }

    RNSharedElementStyle() {
    }

    RNSharedElementStyle(ReadableMap readableMap, Context context) {
        if (readableMap.hasKey(ViewProps.OPACITY)) {
            this.opacity = (float) readableMap.getDouble(ViewProps.OPACITY);
        }
        if (readableMap.hasKey("backgroundColor")) {
            this.backgroundColor = readableMap.getInt("backgroundColor");
        }
        if (readableMap.hasKey(ViewProps.BORDER_COLOR)) {
            this.borderColor = readableMap.getInt(ViewProps.BORDER_COLOR);
        }
        if (readableMap.hasKey(ViewProps.BORDER_WIDTH)) {
            this.borderWidth = PixelUtil.toPixelFromDIP((float) readableMap.getDouble(ViewProps.BORDER_WIDTH));
        }
        if (readableMap.hasKey("borderStyle")) {
            this.borderStyle = readableMap.getString("borderStyle");
        }
        if (readableMap.hasKey(ViewProps.RESIZE_MODE)) {
            this.scaleType = ImageResizeMode.toScaleType(readableMap.getString(ViewProps.RESIZE_MODE));
        }
        if (readableMap.hasKey("elevation")) {
            this.elevation = PixelUtil.toPixelFromDIP((float) readableMap.getDouble("elevation"));
        }
        boolean isRTL = I18nUtil.getInstance().isRTL(context);
        if (readableMap.hasKey("borderRadius")) {
            float pixelFromDIP = PixelUtil.toPixelFromDIP((float) readableMap.getDouble("borderRadius"));
            this.borderTopLeftRadius = pixelFromDIP;
            this.borderTopRightRadius = pixelFromDIP;
            this.borderBottomLeftRadius = pixelFromDIP;
            this.borderBottomRightRadius = pixelFromDIP;
        }
        if (readableMap.hasKey(ViewProps.BORDER_TOP_END_RADIUS)) {
            float pixelFromDIP2 = PixelUtil.toPixelFromDIP((float) readableMap.getDouble(ViewProps.BORDER_TOP_END_RADIUS));
            if (isRTL) {
                this.borderTopLeftRadius = pixelFromDIP2;
            } else {
                this.borderTopRightRadius = pixelFromDIP2;
            }
        }
        if (readableMap.hasKey(ViewProps.BORDER_TOP_START_RADIUS)) {
            float pixelFromDIP3 = PixelUtil.toPixelFromDIP((float) readableMap.getDouble(ViewProps.BORDER_TOP_START_RADIUS));
            if (isRTL) {
                this.borderTopRightRadius = pixelFromDIP3;
            } else {
                this.borderTopLeftRadius = pixelFromDIP3;
            }
        }
        if (readableMap.hasKey(ViewProps.BORDER_BOTTOM_END_RADIUS)) {
            float pixelFromDIP4 = PixelUtil.toPixelFromDIP((float) readableMap.getDouble(ViewProps.BORDER_BOTTOM_END_RADIUS));
            if (isRTL) {
                this.borderBottomLeftRadius = pixelFromDIP4;
            } else {
                this.borderBottomRightRadius = pixelFromDIP4;
            }
        }
        if (readableMap.hasKey(ViewProps.BORDER_BOTTOM_START_RADIUS)) {
            float pixelFromDIP5 = PixelUtil.toPixelFromDIP((float) readableMap.getDouble(ViewProps.BORDER_BOTTOM_START_RADIUS));
            if (isRTL) {
                this.borderBottomRightRadius = pixelFromDIP5;
            } else {
                this.borderBottomLeftRadius = pixelFromDIP5;
            }
        }
        if (readableMap.hasKey(ViewProps.BORDER_TOP_LEFT_RADIUS)) {
            this.borderTopLeftRadius = PixelUtil.toPixelFromDIP((float) readableMap.getDouble(ViewProps.BORDER_TOP_LEFT_RADIUS));
        }
        if (readableMap.hasKey(ViewProps.BORDER_TOP_RIGHT_RADIUS)) {
            this.borderTopRightRadius = PixelUtil.toPixelFromDIP((float) readableMap.getDouble(ViewProps.BORDER_TOP_RIGHT_RADIUS));
        }
        if (readableMap.hasKey(ViewProps.BORDER_BOTTOM_LEFT_RADIUS)) {
            this.borderBottomLeftRadius = PixelUtil.toPixelFromDIP((float) readableMap.getDouble(ViewProps.BORDER_BOTTOM_LEFT_RADIUS));
        }
        if (readableMap.hasKey(ViewProps.BORDER_BOTTOM_RIGHT_RADIUS)) {
            this.borderBottomRightRadius = PixelUtil.toPixelFromDIP((float) readableMap.getDouble(ViewProps.BORDER_BOTTOM_RIGHT_RADIUS));
        }
    }

    /* access modifiers changed from: package-private */
    public int compare(RNSharedElementStyle rNSharedElementStyle) {
        int i = 0;
        if (this.opacity != rNSharedElementStyle.opacity) {
            i = 0 + PROP_OPACITY;
        }
        if (this.backgroundColor != rNSharedElementStyle.backgroundColor) {
            i += PROP_BACKGROUND_COLOR;
        }
        if (this.borderColor != rNSharedElementStyle.borderColor) {
            i += PROP_BORDER_COLOR;
        }
        if (this.borderWidth != rNSharedElementStyle.borderWidth) {
            i += PROP_BORDER_WIDTH;
        }
        if (!this.borderStyle.equals(rNSharedElementStyle.borderStyle)) {
            i += PROP_BORDER_STYLE;
        }
        if (this.borderTopLeftRadius != rNSharedElementStyle.borderTopLeftRadius) {
            i += PROP_BORDER_TOPLEFTRADIUS;
        }
        if (this.borderTopRightRadius != rNSharedElementStyle.borderTopRightRadius) {
            i += PROP_BORDER_TOPRIGHTRADIUS;
        }
        if (this.borderBottomLeftRadius != rNSharedElementStyle.borderBottomLeftRadius) {
            i += PROP_BORDER_BOTTOMLEFTRADIUS;
        }
        if (this.borderBottomRightRadius != rNSharedElementStyle.borderBottomRightRadius) {
            i += PROP_BORDER_BOTTOMRIGHT_RADIUS;
        }
        if (this.elevation != rNSharedElementStyle.elevation) {
            i += PROP_ELEVATION;
        }
        return !equalsScaleType(this.scaleType, rNSharedElementStyle.scaleType) ? i + PROP_SCALETYPE : i;
    }

    /* access modifiers changed from: package-private */
    public boolean isVisible() {
        if (this.opacity <= 0.0f) {
            return false;
        }
        if (this.elevation > 0.0f) {
            return true;
        }
        if (Color.alpha(this.backgroundColor) > 0 || Color.alpha(this.borderColor) > 0) {
            return true;
        }
        return false;
    }

    static RectF normalizeLayout(boolean z, RNSharedElementStyle rNSharedElementStyle, int[] iArr) {
        if (rNSharedElementStyle == null) {
            return EMPTY_RECTF;
        }
        return normalizeLayout(z, rNSharedElementStyle.layout, rNSharedElementStyle, iArr);
    }

    static RectF normalizeLayout(boolean z, RectF rectF, RNSharedElementStyle rNSharedElementStyle, int[] iArr) {
        if (rectF == null) {
            return EMPTY_RECTF;
        }
        if (!z || rNSharedElementStyle == null) {
            return rectF;
        }
        RectF rectF2 = new RectF(rectF);
        rectF2.offset((float) (-iArr[0]), (float) (-iArr[1]));
        Matrix matrix = new Matrix();
        rNSharedElementStyle.ancestorTransform.invert(matrix);
        matrix.mapRect(rectF2);
        rectF2.offset((float) iArr[0], (float) iArr[1]);
        return rectF2;
    }

    static ScalingUtils.ScaleType getInterpolatingScaleType(RNSharedElementStyle rNSharedElementStyle, RectF rectF, RNSharedElementStyle rNSharedElementStyle2, RectF rectF2, float f) {
        ScalingUtils.ScaleType scaleType2 = rNSharedElementStyle.scaleType;
        if (scaleType2 == rNSharedElementStyle2.scaleType) {
            return scaleType2;
        }
        ScalingUtils.InterpolatingScaleType interpolatingScaleType = new ScalingUtils.InterpolatingScaleType(rNSharedElementStyle.scaleType, rNSharedElementStyle2.scaleType, new Rect(0, 0, (int) rectF.width(), (int) rectF.height()), new Rect(0, 0, (int) rectF2.width(), (int) rectF2.height()));
        interpolatingScaleType.setValue(f);
        return interpolatingScaleType;
    }

    static RectF getInterpolatedLayout(RectF rectF, RectF rectF2, float f) {
        return new RectF(rectF.left + ((rectF2.left - rectF.left) * f), rectF.top + ((rectF2.top - rectF.top) * f), rectF.right + ((rectF2.right - rectF.right) * f), rectF.bottom + ((rectF2.bottom - rectF.bottom) * f));
    }

    static int getInterpolatedColor(int i, int i2, float f) {
        int red = Color.red(i);
        int green = Color.green(i);
        int blue = Color.blue(i);
        int alpha = Color.alpha(i);
        return Color.argb((int) (((float) alpha) + (((float) (Color.alpha(i2) - alpha)) * f)), (int) (((float) red) + (((float) (Color.red(i2) - red)) * f)), (int) (((float) green) + (((float) (Color.green(i2) - green)) * f)), (int) (((float) blue) + (((float) (Color.blue(i2) - blue)) * f)));
    }

    static RNSharedElementStyle getInterpolatedStyle(RNSharedElementStyle rNSharedElementStyle, RectF rectF, RNSharedElementStyle rNSharedElementStyle2, RectF rectF2, float f) {
        RNSharedElementStyle rNSharedElementStyle3 = new RNSharedElementStyle();
        rNSharedElementStyle3.scaleType = getInterpolatingScaleType(rNSharedElementStyle, rectF, rNSharedElementStyle2, rectF2, f);
        float f2 = rNSharedElementStyle.opacity;
        rNSharedElementStyle3.opacity = f2 + ((rNSharedElementStyle2.opacity - f2) * f);
        rNSharedElementStyle3.backgroundColor = getInterpolatedColor(rNSharedElementStyle.backgroundColor, rNSharedElementStyle2.backgroundColor, f);
        float f3 = rNSharedElementStyle.borderTopLeftRadius;
        rNSharedElementStyle3.borderTopLeftRadius = f3 + ((rNSharedElementStyle2.borderTopLeftRadius - f3) * f);
        float f4 = rNSharedElementStyle.borderTopRightRadius;
        rNSharedElementStyle3.borderTopRightRadius = f4 + ((rNSharedElementStyle2.borderTopRightRadius - f4) * f);
        float f5 = rNSharedElementStyle.borderBottomLeftRadius;
        rNSharedElementStyle3.borderBottomLeftRadius = f5 + ((rNSharedElementStyle2.borderBottomLeftRadius - f5) * f);
        float f6 = rNSharedElementStyle.borderBottomRightRadius;
        rNSharedElementStyle3.borderBottomRightRadius = f6 + ((rNSharedElementStyle2.borderBottomRightRadius - f6) * f);
        float f7 = rNSharedElementStyle.borderWidth;
        rNSharedElementStyle3.borderWidth = f7 + ((rNSharedElementStyle2.borderWidth - f7) * f);
        rNSharedElementStyle3.borderColor = getInterpolatedColor(rNSharedElementStyle.borderColor, rNSharedElementStyle2.borderColor, f);
        rNSharedElementStyle3.borderStyle = rNSharedElementStyle.borderStyle;
        float f8 = rNSharedElementStyle.elevation;
        rNSharedElementStyle3.elevation = f8 + ((rNSharedElementStyle2.elevation - f8) * f);
        return rNSharedElementStyle3;
    }

    static Matrix getAbsoluteViewTransform(View view) {
        Matrix matrix = new Matrix(view.getMatrix());
        float[] fArr = new float[9];
        matrix.getValues(fArr);
        float[] fArr2 = new float[9];
        ViewParent parent = view.getParent();
        while ((parent instanceof View) && parent != null) {
            ((View) parent).getMatrix().getValues(fArr2);
            fArr[0] = fArr[0] * fArr2[0];
            fArr[4] = fArr[4] * fArr2[4];
            fArr[1] = fArr[1] + fArr2[1];
            fArr[3] = fArr[3] + fArr2[3];
            fArr[2] = fArr[2] + fArr2[2];
            fArr[5] = fArr[5] + fArr2[5];
            parent = parent.getParent();
        }
        if (parent == null) {
            return null;
        }
        matrix.setValues(fArr);
        return matrix;
    }

    static float getAncestorVisibility(View view, RNSharedElementStyle rNSharedElementStyle) {
        RectF rectF = new RectF();
        getLayoutOnScreen(view, rectF);
        RectF rectF2 = new RectF();
        if (!rectF2.setIntersect(rectF, rNSharedElementStyle.ancestorLayout)) {
            return 0.0f;
        }
        float width = rectF.width() * rectF.height();
        float width2 = rectF2.width() * rectF2.height();
        return (width2 / width) * (width2 / (rNSharedElementStyle.ancestorLayout.width() * rNSharedElementStyle.ancestorLayout.height()));
    }

    static void getLayoutOnScreen(View view, RectF rectF) {
        rectF.set(0.0f, 0.0f, (float) view.getWidth(), (float) view.getHeight());
        view.getMatrix().mapRect(rectF);
        rectF.offset((float) view.getLeft(), (float) view.getTop());
        ViewParent parent = view.getParent();
        while (parent instanceof View) {
            View view2 = (View) parent;
            rectF.offset((float) (-view2.getScrollX()), (float) (-view2.getScrollY()));
            view2.getMatrix().mapRect(rectF);
            rectF.offset((float) view2.getLeft(), (float) view2.getTop());
            parent = view2.getParent();
        }
    }
}
