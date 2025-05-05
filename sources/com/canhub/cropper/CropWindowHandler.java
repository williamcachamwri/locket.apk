package com.canhub.cropper;

import android.graphics.RectF;
import com.canhub.cropper.CropImageView;
import com.canhub.cropper.CropWindowMoveHandler;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J(\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u0007H\u0002J\b\u0010\u0016\u001a\u00020\u0017H\u0002J\u0006\u0010\u0018\u001a\u00020\u0007J\u0006\u0010\u0019\u001a\u00020\u0007J\u0006\u0010\u001a\u001a\u00020\u0007J\u0006\u0010\u001b\u001a\u00020\u0007J0\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u001e\u001a\u00020\u00072\u0006\u0010\u001f\u001a\u00020\u00072\u0006\u0010 \u001a\u00020\u00072\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u0017J\"\u0010$\u001a\u0004\u0018\u00010%2\u0006\u0010\u001e\u001a\u00020\u00072\u0006\u0010\u001f\u001a\u00020\u00072\u0006\u0010#\u001a\u00020\u0017H\u0002J\u0006\u0010&\u001a\u00020\u0004J*\u0010'\u001a\u0004\u0018\u00010%2\u0006\u0010\u001e\u001a\u00020\u00072\u0006\u0010\u001f\u001a\u00020\u00072\u0006\u0010 \u001a\u00020\u00072\u0006\u0010#\u001a\u00020\u0017H\u0002J*\u0010(\u001a\u0004\u0018\u00010%2\u0006\u0010\u001e\u001a\u00020\u00072\u0006\u0010\u001f\u001a\u00020\u00072\u0006\u0010 \u001a\u00020\u00072\u0006\u0010#\u001a\u00020\u0017H\u0002J*\u0010)\u001a\u0004\u0018\u00010%2\u0006\u0010\u001e\u001a\u00020\u00072\u0006\u0010\u001f\u001a\u00020\u00072\u0006\u0010 \u001a\u00020\u00072\u0006\u0010#\u001a\u00020\u0017H\u0002J\u0006\u0010*\u001a\u00020\u0007J\u0006\u0010+\u001a\u00020\u0007J8\u0010,\u001a\u00020\u00172\u0006\u0010\u001e\u001a\u00020\u00072\u0006\u0010\u001f\u001a\u00020\u00072\u0006\u0010-\u001a\u00020\u00072\u0006\u0010.\u001a\u00020\u00072\u0006\u0010/\u001a\u00020\u00072\u0006\u00100\u001a\u00020\u0007H\u0002J0\u00101\u001a\u00020\u00172\u0006\u0010\u001e\u001a\u00020\u00072\u0006\u0010\u001f\u001a\u00020\u00072\u0006\u00102\u001a\u00020\u00072\u0006\u00103\u001a\u00020\u00072\u0006\u0010 \u001a\u00020\u0007H\u0002J8\u00104\u001a\u00020\u00172\u0006\u0010\u001e\u001a\u00020\u00072\u0006\u0010\u001f\u001a\u00020\u00072\u0006\u00105\u001a\u00020\u00072\u0006\u00106\u001a\u00020\u00072\u0006\u00103\u001a\u00020\u00072\u0006\u0010 \u001a\u00020\u0007H\u0002J8\u00107\u001a\u00020\u00172\u0006\u0010\u001e\u001a\u00020\u00072\u0006\u0010\u001f\u001a\u00020\u00072\u0006\u00102\u001a\u00020\u00072\u0006\u00108\u001a\u00020\u00072\u0006\u00109\u001a\u00020\u00072\u0006\u0010 \u001a\u00020\u0007H\u0002J&\u0010:\u001a\u00020;2\u0006\u0010<\u001a\u00020\u00072\u0006\u0010=\u001a\u00020\u00072\u0006\u0010>\u001a\u00020\u00072\u0006\u0010?\u001a\u00020\u0007J\u000e\u0010@\u001a\u00020;2\u0006\u0010A\u001a\u00020BJ\u0016\u0010C\u001a\u00020;2\u0006\u0010D\u001a\u00020E2\u0006\u0010F\u001a\u00020EJ\u0016\u0010G\u001a\u00020;2\u0006\u0010H\u001a\u00020E2\u0006\u0010I\u001a\u00020EJ\u000e\u0010J\u001a\u00020;2\u0006\u0010K\u001a\u00020\u0004J\u0006\u0010L\u001a\u00020\u0017R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000¨\u0006M"}, d2 = {"Lcom/canhub/cropper/CropWindowHandler;", "", "()V", "mEdges", "Landroid/graphics/RectF;", "mGetEdges", "mMaxCropResultHeight", "", "mMaxCropResultWidth", "mMaxCropWindowHeight", "mMaxCropWindowWidth", "mMinCropResultHeight", "mMinCropResultWidth", "mMinCropWindowHeight", "mMinCropWindowWidth", "mScaleFactorHeight", "mScaleFactorWidth", "distance", "x1", "y1", "x2", "y2", "focusCenter", "", "getMaxCropHeight", "getMaxCropWidth", "getMinCropHeight", "getMinCropWidth", "getMoveHandler", "Lcom/canhub/cropper/CropWindowMoveHandler;", "x", "y", "targetRadius", "cropShape", "Lcom/canhub/cropper/CropImageView$CropShape;", "isCenterMoveEnabled", "getOvalPressedMoveType", "Lcom/canhub/cropper/CropWindowMoveHandler$Type;", "getRect", "getRectangleHorizontalOnlyPressedMoveType", "getRectanglePressedMoveType", "getRectangleVerticalOnlyPressedMoveType", "getScaleFactorHeight", "getScaleFactorWidth", "isInCenterTargetZone", "left", "top", "right", "bottom", "isInCornerTargetZone", "handleX", "handleY", "isInHorizontalTargetZone", "handleXStart", "handleXEnd", "isInVerticalTargetZone", "handleYStart", "handleYEnd", "setCropWindowLimits", "", "maxWidth", "maxHeight", "scaleFactorWidth", "scaleFactorHeight", "setInitialAttributeValues", "options", "Lcom/canhub/cropper/CropImageOptions;", "setMaxCropResultSize", "maxCropResultWidth", "", "maxCropResultHeight", "setMinCropResultSize", "minCropResultWidth", "minCropResultHeight", "setRect", "rect", "showGuidelines", "cropper_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* compiled from: CropWindowHandler.kt */
public final class CropWindowHandler {
    private final RectF mEdges = new RectF();
    private final RectF mGetEdges = new RectF();
    private float mMaxCropResultHeight;
    private float mMaxCropResultWidth;
    private float mMaxCropWindowHeight;
    private float mMaxCropWindowWidth;
    private float mMinCropResultHeight;
    private float mMinCropResultWidth;
    private float mMinCropWindowHeight;
    private float mMinCropWindowWidth;
    private float mScaleFactorHeight = 1.0f;
    private float mScaleFactorWidth = 1.0f;

    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    /* compiled from: CropWindowHandler.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[CropImageView.CropShape.values().length];
            iArr[CropImageView.CropShape.RECTANGLE.ordinal()] = 1;
            iArr[CropImageView.CropShape.OVAL.ordinal()] = 2;
            iArr[CropImageView.CropShape.RECTANGLE_VERTICAL_ONLY.ordinal()] = 3;
            iArr[CropImageView.CropShape.RECTANGLE_HORIZONTAL_ONLY.ordinal()] = 4;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private final boolean isInCenterTargetZone(float f, float f2, float f3, float f4, float f5, float f6) {
        return f > f3 && f < f5 && f2 > f4 && f2 < f6;
    }

    public final RectF getRect() {
        this.mGetEdges.set(this.mEdges);
        return this.mGetEdges;
    }

    public final float getMinCropWidth() {
        return RangesKt.coerceAtLeast(this.mMinCropWindowWidth, this.mMinCropResultWidth / this.mScaleFactorWidth);
    }

    public final float getMinCropHeight() {
        return RangesKt.coerceAtLeast(this.mMinCropWindowHeight, this.mMinCropResultHeight / this.mScaleFactorHeight);
    }

    public final float getMaxCropWidth() {
        return RangesKt.coerceAtMost(this.mMaxCropWindowWidth, this.mMaxCropResultWidth / this.mScaleFactorWidth);
    }

    public final float getMaxCropHeight() {
        return RangesKt.coerceAtMost(this.mMaxCropWindowHeight, this.mMaxCropResultHeight / this.mScaleFactorHeight);
    }

    public final float getScaleFactorWidth() {
        return this.mScaleFactorWidth;
    }

    public final float getScaleFactorHeight() {
        return this.mScaleFactorHeight;
    }

    public final void setMinCropResultSize(int i, int i2) {
        this.mMinCropResultWidth = (float) i;
        this.mMinCropResultHeight = (float) i2;
    }

    public final void setMaxCropResultSize(int i, int i2) {
        this.mMaxCropResultWidth = (float) i;
        this.mMaxCropResultHeight = (float) i2;
    }

    public final void setCropWindowLimits(float f, float f2, float f3, float f4) {
        this.mMaxCropWindowWidth = f;
        this.mMaxCropWindowHeight = f2;
        this.mScaleFactorWidth = f3;
        this.mScaleFactorHeight = f4;
    }

    public final void setInitialAttributeValues(CropImageOptions cropImageOptions) {
        Intrinsics.checkNotNullParameter(cropImageOptions, "options");
        this.mMinCropWindowWidth = (float) cropImageOptions.minCropWindowWidth;
        this.mMinCropWindowHeight = (float) cropImageOptions.minCropWindowHeight;
        this.mMinCropResultWidth = (float) cropImageOptions.minCropResultWidth;
        this.mMinCropResultHeight = (float) cropImageOptions.minCropResultHeight;
        this.mMaxCropResultWidth = (float) cropImageOptions.maxCropResultWidth;
        this.mMaxCropResultHeight = (float) cropImageOptions.maxCropResultHeight;
    }

    public final void setRect(RectF rectF) {
        Intrinsics.checkNotNullParameter(rectF, "rect");
        this.mEdges.set(rectF);
    }

    public final boolean showGuidelines() {
        return this.mEdges.width() >= 100.0f && this.mEdges.height() >= 100.0f;
    }

    public final CropWindowMoveHandler getMoveHandler(float f, float f2, float f3, CropImageView.CropShape cropShape, boolean z) {
        CropWindowMoveHandler.Type type;
        Intrinsics.checkNotNullParameter(cropShape, "cropShape");
        int i = WhenMappings.$EnumSwitchMapping$0[cropShape.ordinal()];
        if (i == 1) {
            type = getRectanglePressedMoveType(f, f2, f3, z);
        } else if (i == 2) {
            type = getOvalPressedMoveType(f, f2, z);
        } else if (i == 3) {
            type = getRectangleVerticalOnlyPressedMoveType(f, f2, f3, z);
        } else if (i == 4) {
            type = getRectangleHorizontalOnlyPressedMoveType(f, f2, f3, z);
        } else {
            throw new NoWhenBranchMatchedException();
        }
        if (type != null) {
            return new CropWindowMoveHandler(type, this, f, f2);
        }
        return null;
    }

    private final CropWindowMoveHandler.Type getRectanglePressedMoveType(float f, float f2, float f3, boolean z) {
        if (isInCornerTargetZone(f, f2, this.mEdges.left, this.mEdges.top, f3)) {
            return CropWindowMoveHandler.Type.TOP_LEFT;
        }
        if (isInCornerTargetZone(f, f2, this.mEdges.right, this.mEdges.top, f3)) {
            return CropWindowMoveHandler.Type.TOP_RIGHT;
        }
        if (isInCornerTargetZone(f, f2, this.mEdges.left, this.mEdges.bottom, f3)) {
            return CropWindowMoveHandler.Type.BOTTOM_LEFT;
        }
        if (isInCornerTargetZone(f, f2, this.mEdges.right, this.mEdges.bottom, f3)) {
            return CropWindowMoveHandler.Type.BOTTOM_RIGHT;
        }
        if (z) {
            if (isInCenterTargetZone(f, f2, this.mEdges.left, this.mEdges.top, this.mEdges.right, this.mEdges.bottom) && focusCenter()) {
                return CropWindowMoveHandler.Type.CENTER;
            }
        }
        if (isInHorizontalTargetZone(f, f2, this.mEdges.left, this.mEdges.right, this.mEdges.top, f3)) {
            return CropWindowMoveHandler.Type.TOP;
        }
        if (isInHorizontalTargetZone(f, f2, this.mEdges.left, this.mEdges.right, this.mEdges.bottom, f3)) {
            return CropWindowMoveHandler.Type.BOTTOM;
        }
        if (isInVerticalTargetZone(f, f2, this.mEdges.left, this.mEdges.top, this.mEdges.bottom, f3)) {
            return CropWindowMoveHandler.Type.LEFT;
        }
        if (isInVerticalTargetZone(f, f2, this.mEdges.right, this.mEdges.top, this.mEdges.bottom, f3)) {
            return CropWindowMoveHandler.Type.RIGHT;
        }
        if (z) {
            if (isInCenterTargetZone(f, f2, this.mEdges.left, this.mEdges.top, this.mEdges.right, this.mEdges.bottom) && !focusCenter()) {
                return CropWindowMoveHandler.Type.CENTER;
            }
        }
        return null;
    }

    private final CropWindowMoveHandler.Type getOvalPressedMoveType(float f, float f2, boolean z) {
        float f3 = (float) 6;
        float width = this.mEdges.width() / f3;
        float f4 = this.mEdges.left + width;
        float f5 = (float) 5;
        float f6 = this.mEdges.left + (width * f5);
        float height = this.mEdges.height() / f3;
        float f7 = this.mEdges.top + height;
        float f8 = this.mEdges.top + (f5 * height);
        if (f < f4) {
            if (f2 < f7) {
                return CropWindowMoveHandler.Type.TOP_LEFT;
            }
            if (f2 < f8) {
                return CropWindowMoveHandler.Type.LEFT;
            }
            return CropWindowMoveHandler.Type.BOTTOM_LEFT;
        } else if (f < f6) {
            if (f2 < f7) {
                return CropWindowMoveHandler.Type.TOP;
            }
            if (f2 >= f8) {
                return CropWindowMoveHandler.Type.BOTTOM;
            }
            if (z) {
                return CropWindowMoveHandler.Type.CENTER;
            }
            return null;
        } else if (f2 < f7) {
            return CropWindowMoveHandler.Type.TOP_RIGHT;
        } else {
            if (f2 < f8) {
                return CropWindowMoveHandler.Type.RIGHT;
            }
            return CropWindowMoveHandler.Type.BOTTOM_RIGHT;
        }
    }

    private final CropWindowMoveHandler.Type getRectangleVerticalOnlyPressedMoveType(float f, float f2, float f3, boolean z) {
        if (distance(f, f2, this.mEdges.centerX(), this.mEdges.top) <= f3) {
            return CropWindowMoveHandler.Type.TOP;
        }
        if (distance(f, f2, this.mEdges.centerX(), this.mEdges.bottom) <= f3) {
            return CropWindowMoveHandler.Type.BOTTOM;
        }
        if (z) {
            if (isInCenterTargetZone(f, f2, this.mEdges.left, this.mEdges.top, this.mEdges.right, this.mEdges.bottom)) {
                return CropWindowMoveHandler.Type.CENTER;
            }
        }
        return null;
    }

    private final CropWindowMoveHandler.Type getRectangleHorizontalOnlyPressedMoveType(float f, float f2, float f3, boolean z) {
        if (distance(f, f2, this.mEdges.left, this.mEdges.centerY()) <= f3) {
            return CropWindowMoveHandler.Type.LEFT;
        }
        if (distance(f, f2, this.mEdges.right, this.mEdges.centerY()) <= f3) {
            return CropWindowMoveHandler.Type.RIGHT;
        }
        if (z) {
            if (isInCenterTargetZone(f, f2, this.mEdges.left, this.mEdges.top, this.mEdges.right, this.mEdges.bottom)) {
                return CropWindowMoveHandler.Type.CENTER;
            }
        }
        return null;
    }

    private final boolean isInCornerTargetZone(float f, float f2, float f3, float f4, float f5) {
        return distance(f, f2, f3, f4) <= f5;
    }

    private final float distance(float f, float f2, float f3, float f4) {
        return Math.max(Math.abs(f - f3), Math.abs(f2 - f4));
    }

    private final boolean isInHorizontalTargetZone(float f, float f2, float f3, float f4, float f5, float f6) {
        return f > f3 && f < f4 && Math.abs(f2 - f5) <= f6;
    }

    private final boolean isInVerticalTargetZone(float f, float f2, float f3, float f4, float f5, float f6) {
        return Math.abs(f - f3) <= f6 && f2 > f4 && f2 < f5;
    }

    private final boolean focusCenter() {
        return !showGuidelines();
    }
}
