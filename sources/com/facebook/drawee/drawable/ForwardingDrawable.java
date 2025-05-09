package com.facebook.drawee.drawable;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import javax.annotation.Nullable;

public class ForwardingDrawable extends Drawable implements Drawable.Callback, TransformCallback, TransformAwareDrawable, DrawableParent {
    private static final Matrix sTempTransform = new Matrix();
    @Nullable
    private Drawable mCurrentDelegate;
    private final DrawableProperties mDrawableProperties = new DrawableProperties();
    @Nullable
    protected TransformCallback mTransformCallback;

    public ForwardingDrawable(@Nullable Drawable drawable) {
        this.mCurrentDelegate = drawable;
        DrawableUtils.setCallbacks(drawable, this, this);
    }

    @Nullable
    public Drawable setCurrent(@Nullable Drawable drawable) {
        Drawable currentWithoutInvalidate = setCurrentWithoutInvalidate(drawable);
        invalidateSelf();
        return currentWithoutInvalidate;
    }

    /* access modifiers changed from: protected */
    @Nullable
    public Drawable setCurrentWithoutInvalidate(@Nullable Drawable drawable) {
        Drawable drawable2 = this.mCurrentDelegate;
        DrawableUtils.setCallbacks(drawable2, (Drawable.Callback) null, (TransformCallback) null);
        DrawableUtils.setCallbacks(drawable, (Drawable.Callback) null, (TransformCallback) null);
        DrawableUtils.setDrawableProperties(drawable, this.mDrawableProperties);
        DrawableUtils.copyProperties(drawable, this);
        DrawableUtils.setCallbacks(drawable, this, this);
        this.mCurrentDelegate = drawable;
        return drawable2;
    }

    public int getOpacity() {
        Drawable drawable = this.mCurrentDelegate;
        if (drawable == null) {
            return 0;
        }
        return drawable.getOpacity();
    }

    public void setAlpha(int i) {
        this.mDrawableProperties.setAlpha(i);
        Drawable drawable = this.mCurrentDelegate;
        if (drawable != null) {
            drawable.setAlpha(i);
        }
    }

    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        this.mDrawableProperties.setColorFilter(colorFilter);
        Drawable drawable = this.mCurrentDelegate;
        if (drawable != null) {
            drawable.setColorFilter(colorFilter);
        }
    }

    public void setDither(boolean z) {
        this.mDrawableProperties.setDither(z);
        Drawable drawable = this.mCurrentDelegate;
        if (drawable != null) {
            drawable.setDither(z);
        }
    }

    public void setFilterBitmap(boolean z) {
        this.mDrawableProperties.setFilterBitmap(z);
        Drawable drawable = this.mCurrentDelegate;
        if (drawable != null) {
            drawable.setFilterBitmap(z);
        }
    }

    public boolean setVisible(boolean z, boolean z2) {
        boolean visible = super.setVisible(z, z2);
        Drawable drawable = this.mCurrentDelegate;
        if (drawable == null) {
            return visible;
        }
        return drawable.setVisible(z, z2);
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        Drawable drawable = this.mCurrentDelegate;
        if (drawable != null) {
            drawable.setBounds(rect);
        }
    }

    @Nullable
    public Drawable.ConstantState getConstantState() {
        Drawable drawable = this.mCurrentDelegate;
        if (drawable == null) {
            return super.getConstantState();
        }
        return drawable.getConstantState();
    }

    public boolean isStateful() {
        Drawable drawable = this.mCurrentDelegate;
        if (drawable == null) {
            return false;
        }
        return drawable.isStateful();
    }

    /* access modifiers changed from: protected */
    public boolean onStateChange(int[] iArr) {
        Drawable drawable = this.mCurrentDelegate;
        if (drawable == null) {
            return super.onStateChange(iArr);
        }
        return drawable.setState(iArr);
    }

    /* access modifiers changed from: protected */
    public boolean onLevelChange(int i) {
        Drawable drawable = this.mCurrentDelegate;
        if (drawable == null) {
            return super.onLevelChange(i);
        }
        return drawable.setLevel(i);
    }

    public void draw(Canvas canvas) {
        Drawable drawable = this.mCurrentDelegate;
        if (drawable != null) {
            drawable.draw(canvas);
        }
    }

    public int getIntrinsicWidth() {
        Drawable drawable = this.mCurrentDelegate;
        if (drawable == null) {
            return super.getIntrinsicWidth();
        }
        return drawable.getIntrinsicWidth();
    }

    public int getIntrinsicHeight() {
        Drawable drawable = this.mCurrentDelegate;
        if (drawable == null) {
            return super.getIntrinsicHeight();
        }
        return drawable.getIntrinsicHeight();
    }

    public boolean getPadding(Rect rect) {
        Drawable drawable = this.mCurrentDelegate;
        if (drawable == null) {
            return super.getPadding(rect);
        }
        return drawable.getPadding(rect);
    }

    public Drawable mutate() {
        Drawable drawable = this.mCurrentDelegate;
        if (drawable != null) {
            drawable.mutate();
        }
        return this;
    }

    @Nullable
    public Drawable getCurrent() {
        return this.mCurrentDelegate;
    }

    @Nullable
    public Drawable setDrawable(@Nullable Drawable drawable) {
        return setCurrent(drawable);
    }

    @Nullable
    public Drawable getDrawable() {
        return getCurrent();
    }

    public void invalidateDrawable(Drawable drawable) {
        invalidateSelf();
    }

    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        scheduleSelf(runnable, j);
    }

    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        unscheduleSelf(runnable);
    }

    public void setTransformCallback(TransformCallback transformCallback) {
        this.mTransformCallback = transformCallback;
    }

    /* access modifiers changed from: protected */
    public void getParentTransform(Matrix matrix) {
        TransformCallback transformCallback = this.mTransformCallback;
        if (transformCallback != null) {
            transformCallback.getTransform(matrix);
        } else {
            matrix.reset();
        }
    }

    public void getTransform(Matrix matrix) {
        getParentTransform(matrix);
    }

    public void getRootBounds(RectF rectF) {
        TransformCallback transformCallback = this.mTransformCallback;
        if (transformCallback != null) {
            transformCallback.getRootBounds(rectF);
        } else {
            rectF.set(getBounds());
        }
    }

    public void getTransformedBounds(RectF rectF) {
        Matrix matrix = sTempTransform;
        getParentTransform(matrix);
        rectF.set(getBounds());
        matrix.mapRect(rectF);
    }

    public void setHotspot(float f, float f2) {
        Drawable drawable = this.mCurrentDelegate;
        if (drawable != null) {
            drawable.setHotspot(f, f2);
        }
    }
}
