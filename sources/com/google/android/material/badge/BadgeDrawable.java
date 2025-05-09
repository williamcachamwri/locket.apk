package com.google.android.material.badge;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.core.view.ViewCompat;
import com.google.android.material.R;
import com.google.android.material.badge.BadgeState;
import com.google.android.material.internal.TextDrawableHelper;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.resources.TextAppearance;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.text.NumberFormat;
import java.util.Locale;

public class BadgeDrawable extends Drawable implements TextDrawableHelper.TextDrawableDelegate {
    static final int BADGE_RADIUS_NOT_SPECIFIED = -1;
    public static final int BOTTOM_END = 8388693;
    public static final int BOTTOM_START = 8388691;
    static final String DEFAULT_EXCEED_MAX_BADGE_NUMBER_SUFFIX = "+";
    private static final int DEFAULT_STYLE = R.style.Widget_MaterialComponents_Badge;
    private static final int DEFAULT_THEME_ATTR = R.attr.badgeStyle;
    private static final int MAX_CIRCULAR_BADGE_NUMBER_COUNT = 9;
    static final int OFFSET_ALIGNMENT_MODE_EDGE = 0;
    static final int OFFSET_ALIGNMENT_MODE_LEGACY = 1;
    public static final int TOP_END = 8388661;
    public static final int TOP_START = 8388659;
    private WeakReference<View> anchorViewRef;
    private final Rect badgeBounds = new Rect();
    private float badgeCenterX;
    private float badgeCenterY;
    private final WeakReference<Context> contextRef;
    private float cornerRadius;
    private WeakReference<FrameLayout> customBadgeParentRef;
    private float halfBadgeHeight;
    private float halfBadgeWidth;
    private int maxBadgeNumber;
    private final MaterialShapeDrawable shapeDrawable;
    private final BadgeState state;
    private final TextDrawableHelper textDrawableHelper;

    @Retention(RetentionPolicy.SOURCE)
    public @interface BadgeGravity {
    }

    public int getOpacity() {
        return -3;
    }

    public boolean isStateful() {
        return false;
    }

    public void setColorFilter(ColorFilter colorFilter) {
    }

    /* access modifiers changed from: package-private */
    public BadgeState.State getSavedState() {
        return this.state.getOverridingState();
    }

    static BadgeDrawable createFromSavedState(Context context, BadgeState.State state2) {
        return new BadgeDrawable(context, 0, DEFAULT_THEME_ATTR, DEFAULT_STYLE, state2);
    }

    public static BadgeDrawable create(Context context) {
        return new BadgeDrawable(context, 0, DEFAULT_THEME_ATTR, DEFAULT_STYLE, (BadgeState.State) null);
    }

    public static BadgeDrawable createFromResource(Context context, int i) {
        return new BadgeDrawable(context, i, DEFAULT_THEME_ATTR, DEFAULT_STYLE, (BadgeState.State) null);
    }

    public void setVisible(boolean z) {
        this.state.setVisible(z);
        onVisibilityUpdated();
    }

    private void onVisibilityUpdated() {
        boolean isVisible = this.state.isVisible();
        setVisible(isVisible, false);
        if (BadgeUtils.USE_COMPAT_PARENT && getCustomBadgeParent() != null && !isVisible) {
            ((ViewGroup) getCustomBadgeParent().getParent()).invalidate();
        }
    }

    private void restoreState() {
        onBadgeShapeAppearanceUpdated();
        onBadgeTextAppearanceUpdated();
        onMaxCharacterCountUpdated();
        onNumberUpdated();
        onAlphaUpdated();
        onBackgroundColorUpdated();
        onBadgeTextColorUpdated();
        onBadgeGravityUpdated();
        updateCenterAndBounds();
        onVisibilityUpdated();
    }

    private BadgeDrawable(Context context, int i, int i2, int i3, BadgeState.State state2) {
        int i4;
        int i5;
        this.contextRef = new WeakReference<>(context);
        ThemeEnforcement.checkMaterialTheme(context);
        TextDrawableHelper textDrawableHelper2 = new TextDrawableHelper(this);
        this.textDrawableHelper = textDrawableHelper2;
        textDrawableHelper2.getTextPaint().setTextAlign(Paint.Align.CENTER);
        BadgeState badgeState = new BadgeState(context, i, i2, i3, state2);
        this.state = badgeState;
        if (badgeState.hasNumber()) {
            i4 = badgeState.getBadgeWithTextShapeAppearanceResId();
        } else {
            i4 = badgeState.getBadgeShapeAppearanceResId();
        }
        if (badgeState.hasNumber()) {
            i5 = badgeState.getBadgeWithTextShapeAppearanceOverlayResId();
        } else {
            i5 = badgeState.getBadgeShapeAppearanceOverlayResId();
        }
        this.shapeDrawable = new MaterialShapeDrawable(ShapeAppearanceModel.builder(context, i4, i5).build());
        restoreState();
    }

    @Deprecated
    public void updateBadgeCoordinates(View view, ViewGroup viewGroup) {
        if (viewGroup instanceof FrameLayout) {
            updateBadgeCoordinates(view, (FrameLayout) viewGroup);
            return;
        }
        throw new IllegalArgumentException("customBadgeParent must be a FrameLayout");
    }

    public void updateBadgeCoordinates(View view) {
        updateBadgeCoordinates(view, (FrameLayout) null);
    }

    public void updateBadgeCoordinates(View view, FrameLayout frameLayout) {
        this.anchorViewRef = new WeakReference<>(view);
        if (!BadgeUtils.USE_COMPAT_PARENT || frameLayout != null) {
            this.customBadgeParentRef = new WeakReference<>(frameLayout);
        } else {
            tryWrapAnchorInCompatParent(view);
        }
        if (!BadgeUtils.USE_COMPAT_PARENT) {
            updateAnchorParentToNotClip(view);
        }
        updateCenterAndBounds();
        invalidateSelf();
    }

    public FrameLayout getCustomBadgeParent() {
        WeakReference<FrameLayout> weakReference = this.customBadgeParentRef;
        if (weakReference != null) {
            return (FrameLayout) weakReference.get();
        }
        return null;
    }

    private void tryWrapAnchorInCompatParent(final View view) {
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        if (viewGroup == null || viewGroup.getId() != R.id.mtrl_anchor_parent) {
            WeakReference<FrameLayout> weakReference = this.customBadgeParentRef;
            if (weakReference == null || weakReference.get() != viewGroup) {
                updateAnchorParentToNotClip(view);
                final FrameLayout frameLayout = new FrameLayout(view.getContext());
                frameLayout.setId(R.id.mtrl_anchor_parent);
                frameLayout.setClipChildren(false);
                frameLayout.setClipToPadding(false);
                frameLayout.setLayoutParams(view.getLayoutParams());
                frameLayout.setMinimumWidth(view.getWidth());
                frameLayout.setMinimumHeight(view.getHeight());
                int indexOfChild = viewGroup.indexOfChild(view);
                viewGroup.removeViewAt(indexOfChild);
                view.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
                frameLayout.addView(view);
                viewGroup.addView(frameLayout, indexOfChild);
                this.customBadgeParentRef = new WeakReference<>(frameLayout);
                frameLayout.post(new Runnable() {
                    public void run() {
                        BadgeDrawable.this.updateBadgeCoordinates(view, frameLayout);
                    }
                });
            }
        }
    }

    private static void updateAnchorParentToNotClip(View view) {
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        viewGroup.setClipChildren(false);
        viewGroup.setClipToPadding(false);
    }

    public int getBackgroundColor() {
        return this.shapeDrawable.getFillColor().getDefaultColor();
    }

    public void setBackgroundColor(int i) {
        this.state.setBackgroundColor(i);
        onBackgroundColorUpdated();
    }

    private void onBackgroundColorUpdated() {
        ColorStateList valueOf = ColorStateList.valueOf(this.state.getBackgroundColor());
        if (this.shapeDrawable.getFillColor() != valueOf) {
            this.shapeDrawable.setFillColor(valueOf);
            invalidateSelf();
        }
    }

    public int getBadgeTextColor() {
        return this.textDrawableHelper.getTextPaint().getColor();
    }

    public void setBadgeTextColor(int i) {
        if (this.textDrawableHelper.getTextPaint().getColor() != i) {
            this.state.setBadgeTextColor(i);
            onBadgeTextColorUpdated();
        }
    }

    private void onBadgeTextColorUpdated() {
        this.textDrawableHelper.getTextPaint().setColor(this.state.getBadgeTextColor());
        invalidateSelf();
    }

    public Locale getBadgeNumberLocale() {
        return this.state.getNumberLocale();
    }

    public void setBadgeNumberLocale(Locale locale) {
        if (!locale.equals(this.state.getNumberLocale())) {
            this.state.setNumberLocale(locale);
            invalidateSelf();
        }
    }

    public boolean hasNumber() {
        return this.state.hasNumber();
    }

    public int getNumber() {
        if (hasNumber()) {
            return this.state.getNumber();
        }
        return 0;
    }

    public void setNumber(int i) {
        int max = Math.max(0, i);
        if (this.state.getNumber() != max) {
            this.state.setNumber(max);
            onNumberUpdated();
        }
    }

    public void clearNumber() {
        if (hasNumber()) {
            this.state.clearNumber();
            onNumberUpdated();
        }
    }

    private void onNumberUpdated() {
        this.textDrawableHelper.setTextWidthDirty(true);
        onBadgeShapeAppearanceUpdated();
        updateCenterAndBounds();
        invalidateSelf();
    }

    public int getMaxCharacterCount() {
        return this.state.getMaxCharacterCount();
    }

    public void setMaxCharacterCount(int i) {
        if (this.state.getMaxCharacterCount() != i) {
            this.state.setMaxCharacterCount(i);
            onMaxCharacterCountUpdated();
        }
    }

    private void onMaxCharacterCountUpdated() {
        updateMaxBadgeNumber();
        this.textDrawableHelper.setTextWidthDirty(true);
        updateCenterAndBounds();
        invalidateSelf();
    }

    public int getBadgeGravity() {
        return this.state.getBadgeGravity();
    }

    public void setBadgeGravity(int i) {
        if (this.state.getBadgeGravity() != i) {
            this.state.setBadgeGravity(i);
            onBadgeGravityUpdated();
        }
    }

    private void onBadgeGravityUpdated() {
        WeakReference<View> weakReference = this.anchorViewRef;
        if (weakReference != null && weakReference.get() != null) {
            View view = (View) this.anchorViewRef.get();
            WeakReference<FrameLayout> weakReference2 = this.customBadgeParentRef;
            updateBadgeCoordinates(view, weakReference2 != null ? (FrameLayout) weakReference2.get() : null);
        }
    }

    public int getAlpha() {
        return this.state.getAlpha();
    }

    public void setAlpha(int i) {
        this.state.setAlpha(i);
        onAlphaUpdated();
    }

    private void onAlphaUpdated() {
        this.textDrawableHelper.getTextPaint().setAlpha(getAlpha());
        invalidateSelf();
    }

    public int getIntrinsicHeight() {
        return this.badgeBounds.height();
    }

    public int getIntrinsicWidth() {
        return this.badgeBounds.width();
    }

    public void draw(Canvas canvas) {
        if (!getBounds().isEmpty() && getAlpha() != 0 && isVisible()) {
            this.shapeDrawable.draw(canvas);
            if (hasNumber()) {
                drawText(canvas);
            }
        }
    }

    public void onTextSizeChange() {
        invalidateSelf();
    }

    public boolean onStateChange(int[] iArr) {
        return super.onStateChange(iArr);
    }

    public void setContentDescriptionNumberless(CharSequence charSequence) {
        this.state.setContentDescriptionNumberless(charSequence);
    }

    public void setContentDescriptionQuantityStringsResource(int i) {
        this.state.setContentDescriptionQuantityStringsResource(i);
    }

    public void setContentDescriptionExceedsMaxBadgeNumberStringResource(int i) {
        this.state.setContentDescriptionExceedsMaxBadgeNumberStringResource(i);
    }

    public CharSequence getContentDescription() {
        Context context;
        if (!isVisible()) {
            return null;
        }
        if (!hasNumber()) {
            return this.state.getContentDescriptionNumberless();
        }
        if (this.state.getContentDescriptionQuantityStrings() == 0 || (context = (Context) this.contextRef.get()) == null) {
            return null;
        }
        if (getNumber() <= this.maxBadgeNumber) {
            return context.getResources().getQuantityString(this.state.getContentDescriptionQuantityStrings(), getNumber(), new Object[]{Integer.valueOf(getNumber())});
        }
        return context.getString(this.state.getContentDescriptionExceedsMaxBadgeNumberStringResource(), new Object[]{Integer.valueOf(this.maxBadgeNumber)});
    }

    public void setHorizontalOffset(int i) {
        setHorizontalOffsetWithoutText(i);
        setHorizontalOffsetWithText(i);
    }

    public int getHorizontalOffset() {
        return this.state.getHorizontalOffsetWithoutText();
    }

    public void setHorizontalOffsetWithoutText(int i) {
        this.state.setHorizontalOffsetWithoutText(i);
        updateCenterAndBounds();
    }

    public int getHorizontalOffsetWithoutText() {
        return this.state.getHorizontalOffsetWithoutText();
    }

    public void setHorizontalOffsetWithText(int i) {
        this.state.setHorizontalOffsetWithText(i);
        updateCenterAndBounds();
    }

    public int getHorizontalOffsetWithText() {
        return this.state.getHorizontalOffsetWithText();
    }

    /* access modifiers changed from: package-private */
    public void setAdditionalHorizontalOffset(int i) {
        this.state.setAdditionalHorizontalOffset(i);
        updateCenterAndBounds();
    }

    /* access modifiers changed from: package-private */
    public int getAdditionalHorizontalOffset() {
        return this.state.getAdditionalHorizontalOffset();
    }

    public void setVerticalOffset(int i) {
        setVerticalOffsetWithoutText(i);
        setVerticalOffsetWithText(i);
    }

    public int getVerticalOffset() {
        return this.state.getVerticalOffsetWithoutText();
    }

    public void setVerticalOffsetWithoutText(int i) {
        this.state.setVerticalOffsetWithoutText(i);
        updateCenterAndBounds();
    }

    public int getVerticalOffsetWithoutText() {
        return this.state.getVerticalOffsetWithoutText();
    }

    public void setVerticalOffsetWithText(int i) {
        this.state.setVerticalOffsetWithText(i);
        updateCenterAndBounds();
    }

    public int getVerticalOffsetWithText() {
        return this.state.getVerticalOffsetWithText();
    }

    /* access modifiers changed from: package-private */
    public void setAdditionalVerticalOffset(int i) {
        this.state.setAdditionalVerticalOffset(i);
        updateCenterAndBounds();
    }

    /* access modifiers changed from: package-private */
    public int getAdditionalVerticalOffset() {
        return this.state.getAdditionalVerticalOffset();
    }

    public void setTextAppearance(int i) {
        this.state.setTextAppearanceResId(i);
        onBadgeTextAppearanceUpdated();
    }

    private void onBadgeTextAppearanceUpdated() {
        TextAppearance textAppearance;
        Context context = (Context) this.contextRef.get();
        if (context != null && this.textDrawableHelper.getTextAppearance() != (textAppearance = new TextAppearance(context, this.state.getTextAppearanceResId()))) {
            this.textDrawableHelper.setTextAppearance(textAppearance, context);
            onBadgeTextColorUpdated();
            updateCenterAndBounds();
            invalidateSelf();
        }
    }

    public void setBadgeWithoutTextShapeAppearance(int i) {
        this.state.setBadgeShapeAppearanceResId(i);
        onBadgeShapeAppearanceUpdated();
    }

    public void setBadgeWithoutTextShapeAppearanceOverlay(int i) {
        this.state.setBadgeShapeAppearanceOverlayResId(i);
        onBadgeShapeAppearanceUpdated();
    }

    public void setBadgeWithTextShapeAppearance(int i) {
        this.state.setBadgeWithTextShapeAppearanceResId(i);
        onBadgeShapeAppearanceUpdated();
    }

    public void setBadgeWithTextShapeAppearanceOverlay(int i) {
        this.state.setBadgeWithTextShapeAppearanceOverlayResId(i);
        onBadgeShapeAppearanceUpdated();
    }

    private void onBadgeShapeAppearanceUpdated() {
        int i;
        int i2;
        Context context = (Context) this.contextRef.get();
        if (context != null) {
            MaterialShapeDrawable materialShapeDrawable = this.shapeDrawable;
            if (this.state.hasNumber()) {
                i = this.state.getBadgeWithTextShapeAppearanceResId();
            } else {
                i = this.state.getBadgeShapeAppearanceResId();
            }
            if (this.state.hasNumber()) {
                i2 = this.state.getBadgeWithTextShapeAppearanceOverlayResId();
            } else {
                i2 = this.state.getBadgeShapeAppearanceOverlayResId();
            }
            materialShapeDrawable.setShapeAppearanceModel(ShapeAppearanceModel.builder(context, i, i2).build());
            invalidateSelf();
        }
    }

    private void updateCenterAndBounds() {
        Context context = (Context) this.contextRef.get();
        WeakReference<View> weakReference = this.anchorViewRef;
        ViewGroup viewGroup = null;
        View view = weakReference != null ? (View) weakReference.get() : null;
        if (context != null && view != null) {
            Rect rect = new Rect();
            rect.set(this.badgeBounds);
            Rect rect2 = new Rect();
            view.getDrawingRect(rect2);
            WeakReference<FrameLayout> weakReference2 = this.customBadgeParentRef;
            if (weakReference2 != null) {
                viewGroup = (ViewGroup) weakReference2.get();
            }
            if (viewGroup != null || BadgeUtils.USE_COMPAT_PARENT) {
                if (viewGroup == null) {
                    viewGroup = (ViewGroup) view.getParent();
                }
                viewGroup.offsetDescendantRectToMyCoords(view, rect2);
            }
            calculateCenterAndBounds(rect2, view);
            BadgeUtils.updateBadgeBounds(this.badgeBounds, this.badgeCenterX, this.badgeCenterY, this.halfBadgeWidth, this.halfBadgeHeight);
            float f = this.cornerRadius;
            if (f != -1.0f) {
                this.shapeDrawable.setCornerSize(f);
            }
            if (!rect.equals(this.badgeBounds)) {
                this.shapeDrawable.setBounds(this.badgeBounds);
            }
        }
    }

    private int getTotalVerticalOffsetForState() {
        int verticalOffsetWithText = hasNumber() ? this.state.getVerticalOffsetWithText() : this.state.getVerticalOffsetWithoutText();
        if (this.state.offsetAlignmentMode == 0) {
            verticalOffsetWithText -= Math.round(this.halfBadgeHeight);
        }
        return verticalOffsetWithText + this.state.getAdditionalVerticalOffset();
    }

    private int getTotalHorizontalOffsetForState() {
        int horizontalOffsetWithText = hasNumber() ? this.state.getHorizontalOffsetWithText() : this.state.getHorizontalOffsetWithoutText();
        if (this.state.offsetAlignmentMode == 1) {
            horizontalOffsetWithText += hasNumber() ? this.state.horizontalInsetWithText : this.state.horizontalInset;
        }
        return horizontalOffsetWithText + this.state.getAdditionalHorizontalOffset();
    }

    private void calculateCenterAndBounds(Rect rect, View view) {
        float f;
        float f2;
        float f3 = !hasNumber() ? this.state.badgeRadius : this.state.badgeWithTextRadius;
        this.cornerRadius = f3;
        if (f3 != -1.0f) {
            this.halfBadgeHeight = f3;
            this.halfBadgeWidth = f3;
        } else {
            this.halfBadgeHeight = (float) Math.round((!hasNumber() ? this.state.badgeHeight : this.state.badgeWithTextHeight) / 2.0f);
            this.halfBadgeWidth = (float) Math.round((!hasNumber() ? this.state.badgeWidth : this.state.badgeWithTextWidth) / 2.0f);
        }
        if (getNumber() > 9) {
            this.halfBadgeWidth = Math.max(this.halfBadgeWidth, (this.textDrawableHelper.getTextWidth(getBadgeText()) / 2.0f) + this.state.badgeWidePadding);
        }
        int totalVerticalOffsetForState = getTotalVerticalOffsetForState();
        int badgeGravity = this.state.getBadgeGravity();
        if (badgeGravity == 8388691 || badgeGravity == 8388693) {
            this.badgeCenterY = (float) (rect.bottom - totalVerticalOffsetForState);
        } else {
            this.badgeCenterY = (float) (rect.top + totalVerticalOffsetForState);
        }
        int totalHorizontalOffsetForState = getTotalHorizontalOffsetForState();
        int badgeGravity2 = this.state.getBadgeGravity();
        if (badgeGravity2 == 8388659 || badgeGravity2 == 8388691) {
            if (ViewCompat.getLayoutDirection(view) == 0) {
                f = (((float) rect.left) - this.halfBadgeWidth) + ((float) totalHorizontalOffsetForState);
            } else {
                f = (((float) rect.right) + this.halfBadgeWidth) - ((float) totalHorizontalOffsetForState);
            }
            this.badgeCenterX = f;
            return;
        }
        if (ViewCompat.getLayoutDirection(view) == 0) {
            f2 = (((float) rect.right) + this.halfBadgeWidth) - ((float) totalHorizontalOffsetForState);
        } else {
            f2 = (((float) rect.left) - this.halfBadgeWidth) + ((float) totalHorizontalOffsetForState);
        }
        this.badgeCenterX = f2;
    }

    private void drawText(Canvas canvas) {
        Rect rect = new Rect();
        String badgeText = getBadgeText();
        this.textDrawableHelper.getTextPaint().getTextBounds(badgeText, 0, badgeText.length(), rect);
        canvas.drawText(badgeText, this.badgeCenterX, this.badgeCenterY + ((float) (rect.height() / 2)), this.textDrawableHelper.getTextPaint());
    }

    private String getBadgeText() {
        if (getNumber() <= this.maxBadgeNumber) {
            return NumberFormat.getInstance(this.state.getNumberLocale()).format((long) getNumber());
        }
        Context context = (Context) this.contextRef.get();
        if (context == null) {
            return "";
        }
        return String.format(this.state.getNumberLocale(), context.getString(R.string.mtrl_exceed_max_badge_number_suffix), new Object[]{Integer.valueOf(this.maxBadgeNumber), DEFAULT_EXCEED_MAX_BADGE_NUMBER_SUFFIX});
    }

    private void updateMaxBadgeNumber() {
        this.maxBadgeNumber = ((int) Math.pow(10.0d, ((double) getMaxCharacterCount()) - 1.0d)) - 1;
    }
}
