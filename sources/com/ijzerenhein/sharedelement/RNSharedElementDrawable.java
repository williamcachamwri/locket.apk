package com.ijzerenhein.sharedelement;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Outline;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import androidx.core.view.ViewCompat;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.react.views.image.ReactImageView;
import com.facebook.react.views.view.ReactViewBackgroundDrawable;
import com.facebook.react.views.view.ReactViewGroup;

class RNSharedElementDrawable extends Drawable {
    private int mAlpha = 255;
    private RNSharedElementContent mContent = null;
    private Path mPathForBorderRadiusOutline = null;
    private float mPosition = 0.0f;
    private ReactViewBackgroundDrawable mReactViewBackgroundDrawableCache = null;
    private RNSharedElementStyle mStyle = null;
    private ViewType mViewType = ViewType.NONE;

    public int getOpacity() {
        return -3;
    }

    public void setColorFilter(ColorFilter colorFilter) {
    }

    RNSharedElementDrawable() {
    }

    enum ViewType {
        NONE("none"),
        REACTIMAGEVIEW("image"),
        IMAGEVIEW("image"),
        PLAIN("view"),
        GENERIC("generic");
        
        private final String value;

        private ViewType(String str) {
            this.value = str;
        }

        public String getValue() {
            return this.value;
        }
    }

    /* access modifiers changed from: package-private */
    public RNSharedElementStyle getStyle() {
        return this.mStyle;
    }

    /* access modifiers changed from: package-private */
    public RNSharedElementContent getContent() {
        return this.mContent;
    }

    /* access modifiers changed from: package-private */
    public float getPosition() {
        return this.mPosition;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0044, code lost:
        if ((r5.mStyle.compare(r7) & (com.ijzerenhein.sharedelement.RNSharedElementStyle.PROP_BORDER | com.ijzerenhein.sharedelement.RNSharedElementStyle.PROP_BACKGROUND_COLOR)) != 0) goto L_0x005a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0056, code lost:
        if ((r5.mStyle.compare(r7) & ((com.ijzerenhein.sharedelement.RNSharedElementStyle.PROP_BORDER | com.ijzerenhein.sharedelement.RNSharedElementStyle.PROP_BACKGROUND_COLOR) | com.ijzerenhein.sharedelement.RNSharedElementStyle.PROP_SCALETYPE)) != 0) goto L_0x005a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.ijzerenhein.sharedelement.RNSharedElementDrawable.ViewType update(com.ijzerenhein.sharedelement.RNSharedElementContent r6, com.ijzerenhein.sharedelement.RNSharedElementStyle r7, float r8) {
        /*
            r5 = this;
            com.ijzerenhein.sharedelement.RNSharedElementContent r0 = r5.mContent
            r1 = 1
            r2 = 0
            if (r0 == r6) goto L_0x000a
            r5.mContent = r6
            r6 = r1
            goto L_0x000b
        L_0x000a:
            r6 = r2
        L_0x000b:
            com.ijzerenhein.sharedelement.RNSharedElementContent r0 = r5.mContent
            if (r0 == 0) goto L_0x0016
            android.view.View r0 = r0.view
            com.ijzerenhein.sharedelement.RNSharedElementDrawable$ViewType r0 = getViewType(r0, r7)
            goto L_0x0018
        L_0x0016:
            com.ijzerenhein.sharedelement.RNSharedElementDrawable$ViewType r0 = com.ijzerenhein.sharedelement.RNSharedElementDrawable.ViewType.NONE
        L_0x0018:
            com.ijzerenhein.sharedelement.RNSharedElementDrawable$ViewType r3 = r5.mViewType
            if (r3 == r0) goto L_0x001f
            r5.mViewType = r0
            r6 = r1
        L_0x001f:
            com.ijzerenhein.sharedelement.RNSharedElementStyle r3 = r5.mStyle
            if (r3 == 0) goto L_0x005b
            if (r7 == 0) goto L_0x005b
            if (r6 != 0) goto L_0x005b
            int[] r3 = com.ijzerenhein.sharedelement.RNSharedElementDrawable.AnonymousClass1.$SwitchMap$com$ijzerenhein$sharedelement$RNSharedElementDrawable$ViewType
            int r4 = r0.ordinal()
            r3 = r3[r4]
            if (r3 == r1) goto L_0x0047
            r4 = 2
            if (r3 == r4) goto L_0x0047
            r4 = 3
            if (r3 == r4) goto L_0x0038
            goto L_0x005b
        L_0x0038:
            com.ijzerenhein.sharedelement.RNSharedElementStyle r6 = r5.mStyle
            int r6 = r6.compare(r7)
            int r3 = com.ijzerenhein.sharedelement.RNSharedElementStyle.PROP_BORDER
            int r4 = com.ijzerenhein.sharedelement.RNSharedElementStyle.PROP_BACKGROUND_COLOR
            r3 = r3 | r4
            r6 = r6 & r3
            if (r6 == 0) goto L_0x0059
            goto L_0x005a
        L_0x0047:
            com.ijzerenhein.sharedelement.RNSharedElementStyle r6 = r5.mStyle
            int r6 = r6.compare(r7)
            int r3 = com.ijzerenhein.sharedelement.RNSharedElementStyle.PROP_BORDER
            int r4 = com.ijzerenhein.sharedelement.RNSharedElementStyle.PROP_BACKGROUND_COLOR
            r3 = r3 | r4
            int r4 = com.ijzerenhein.sharedelement.RNSharedElementStyle.PROP_SCALETYPE
            r3 = r3 | r4
            r6 = r6 & r3
            if (r6 == 0) goto L_0x0059
            goto L_0x005a
        L_0x0059:
            r1 = r2
        L_0x005a:
            r6 = r1
        L_0x005b:
            r5.mStyle = r7
            r5.mPosition = r8
            if (r6 == 0) goto L_0x0064
            r5.invalidateSelf()
        L_0x0064:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ijzerenhein.sharedelement.RNSharedElementDrawable.update(com.ijzerenhein.sharedelement.RNSharedElementContent, com.ijzerenhein.sharedelement.RNSharedElementStyle, float):com.ijzerenhein.sharedelement.RNSharedElementDrawable$ViewType");
    }

    /* renamed from: com.ijzerenhein.sharedelement.RNSharedElementDrawable$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$ijzerenhein$sharedelement$RNSharedElementDrawable$ViewType;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                com.ijzerenhein.sharedelement.RNSharedElementDrawable$ViewType[] r0 = com.ijzerenhein.sharedelement.RNSharedElementDrawable.ViewType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$ijzerenhein$sharedelement$RNSharedElementDrawable$ViewType = r0
                com.ijzerenhein.sharedelement.RNSharedElementDrawable$ViewType r1 = com.ijzerenhein.sharedelement.RNSharedElementDrawable.ViewType.REACTIMAGEVIEW     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$ijzerenhein$sharedelement$RNSharedElementDrawable$ViewType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.ijzerenhein.sharedelement.RNSharedElementDrawable$ViewType r1 = com.ijzerenhein.sharedelement.RNSharedElementDrawable.ViewType.IMAGEVIEW     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$ijzerenhein$sharedelement$RNSharedElementDrawable$ViewType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.ijzerenhein.sharedelement.RNSharedElementDrawable$ViewType r1 = com.ijzerenhein.sharedelement.RNSharedElementDrawable.ViewType.PLAIN     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$ijzerenhein$sharedelement$RNSharedElementDrawable$ViewType     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.ijzerenhein.sharedelement.RNSharedElementDrawable$ViewType r1 = com.ijzerenhein.sharedelement.RNSharedElementDrawable.ViewType.GENERIC     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.ijzerenhein.sharedelement.RNSharedElementDrawable.AnonymousClass1.<clinit>():void");
        }
    }

    public void setAlpha(int i) {
        if (i != this.mAlpha) {
            this.mAlpha = i;
        }
    }

    public int getAlpha() {
        return this.mAlpha;
    }

    public void getOutline(Outline outline) {
        RNSharedElementStyle rNSharedElementStyle = this.mStyle;
        if (rNSharedElementStyle == null) {
            outline.setRect(getBounds());
        } else if (rNSharedElementStyle.borderTopLeftRadius == 0.0f && this.mStyle.borderTopRightRadius == 0.0f && this.mStyle.borderBottomLeftRadius == 0.0f && this.mStyle.borderBottomRightRadius == 0.0f) {
            outline.setRect(getBounds());
        } else {
            Path path = this.mPathForBorderRadiusOutline;
            if (path == null) {
                this.mPathForBorderRadiusOutline = new Path();
            } else {
                path.reset();
            }
            float f = this.mStyle.borderWidth / 2.0f;
            this.mPathForBorderRadiusOutline.addRoundRect(new RectF(getBounds()), new float[]{this.mStyle.borderTopLeftRadius + f, this.mStyle.borderTopLeftRadius + f, this.mStyle.borderTopRightRadius + f, this.mStyle.borderTopRightRadius + f, this.mStyle.borderBottomRightRadius + f, this.mStyle.borderBottomRightRadius + f, this.mStyle.borderBottomLeftRadius + f, this.mStyle.borderBottomLeftRadius + f}, Path.Direction.CW);
            outline.setConvexPath(this.mPathForBorderRadiusOutline);
        }
    }

    static ViewType getViewType(View view, RNSharedElementStyle rNSharedElementStyle) {
        if (view == null) {
            return ViewType.NONE;
        }
        if (view instanceof ReactImageView) {
            return ViewType.REACTIMAGEVIEW;
        }
        if (view instanceof ImageView) {
            return ViewType.IMAGEVIEW;
        }
        if (!(view instanceof ReactViewGroup) || ((ReactViewGroup) view).getChildCount() != 0) {
            return ViewType.GENERIC;
        }
        if (rNSharedElementStyle.isVisible()) {
            return ViewType.PLAIN;
        }
        return ViewType.NONE;
    }

    public void draw(Canvas canvas) {
        int i = AnonymousClass1.$SwitchMap$com$ijzerenhein$sharedelement$RNSharedElementDrawable$ViewType[this.mViewType.ordinal()];
        if (i == 1) {
            drawReactImageView(canvas);
        } else if (i == 2) {
            drawImageView(canvas);
        } else if (i == 3) {
            drawPlainView(canvas);
        } else if (i == 4) {
            drawGenericView(canvas);
        }
    }

    private void drawReactImageView(Canvas canvas) {
        RNSharedElementStyle rNSharedElementStyle = this.mStyle;
        GenericDraweeHierarchy genericDraweeHierarchy = (GenericDraweeHierarchy) ((ReactImageView) this.mContent.view).getHierarchy();
        Drawable topLevelDrawable = genericDraweeHierarchy.getTopLevelDrawable();
        if (topLevelDrawable != null) {
            Rect rect = new Rect(topLevelDrawable.getBounds());
            ScalingUtils.ScaleType actualImageScaleType = genericDraweeHierarchy.getActualImageScaleType();
            RoundingParams roundingParams = genericDraweeHierarchy.getRoundingParams();
            int fadeDuration = genericDraweeHierarchy.getFadeDuration();
            topLevelDrawable.setBounds(getBounds());
            genericDraweeHierarchy.setActualImageScaleType(rNSharedElementStyle.scaleType);
            RoundingParams roundingParams2 = new RoundingParams();
            roundingParams2.setBorderColor(rNSharedElementStyle.borderColor);
            roundingParams2.setBorderWidth(rNSharedElementStyle.borderWidth);
            roundingParams2.setRoundingMethod(RoundingParams.RoundingMethod.BITMAP_ONLY);
            roundingParams2.setCornersRadii(rNSharedElementStyle.borderTopLeftRadius, rNSharedElementStyle.borderTopRightRadius, rNSharedElementStyle.borderBottomRightRadius, rNSharedElementStyle.borderBottomLeftRadius);
            genericDraweeHierarchy.setRoundingParams(roundingParams2);
            genericDraweeHierarchy.setBackgroundImage((Drawable) null);
            genericDraweeHierarchy.setFadeDuration(0);
            topLevelDrawable.draw(canvas);
            genericDraweeHierarchy.setFadeDuration(fadeDuration);
            genericDraweeHierarchy.setBackgroundImage((Drawable) null);
            genericDraweeHierarchy.setRoundingParams(roundingParams);
            genericDraweeHierarchy.setActualImageScaleType(actualImageScaleType);
            topLevelDrawable.setBounds(rect);
        }
    }

    private void drawImageView(Canvas canvas) {
        RNSharedElementStyle rNSharedElementStyle = this.mStyle;
        Drawable drawable = ((ImageView) this.mContent.view).getDrawable();
        if (drawable != null) {
            Rect rect = new Rect(drawable.getBounds());
            int i = (int) this.mContent.size.right;
            int i2 = (int) this.mContent.size.bottom;
            drawable.setBounds(0, 0, i, i2);
            Matrix matrix = new Matrix();
            rNSharedElementStyle.scaleType.getTransform(matrix, getBounds(), i, i2, 0.5f, 0.5f);
            int save = canvas.save();
            canvas.concat(matrix);
            drawable.draw(canvas);
            canvas.restoreToCount(save);
            drawable.setBounds(rect);
        }
    }

    private void drawPlainView(Canvas canvas) {
        RNSharedElementStyle rNSharedElementStyle = this.mStyle;
        ReactViewBackgroundDrawable reactViewBackgroundDrawable = this.mReactViewBackgroundDrawableCache;
        if (reactViewBackgroundDrawable == null) {
            reactViewBackgroundDrawable = new ReactViewBackgroundDrawable(this.mContent.view.getContext());
            this.mReactViewBackgroundDrawableCache = reactViewBackgroundDrawable;
        }
        reactViewBackgroundDrawable.setBounds(getBounds());
        reactViewBackgroundDrawable.setColor(rNSharedElementStyle.backgroundColor);
        float f = (float) (rNSharedElementStyle.borderColor & ViewCompat.MEASURED_SIZE_MASK);
        float f2 = (float) (rNSharedElementStyle.borderColor >>> 24);
        reactViewBackgroundDrawable.setBorderStyle(rNSharedElementStyle.borderStyle);
        for (int i = 0; i < 4; i++) {
            reactViewBackgroundDrawable.setBorderColor(i, f, f2);
            reactViewBackgroundDrawable.setBorderWidth(i, rNSharedElementStyle.borderWidth);
        }
        reactViewBackgroundDrawable.setRadius(rNSharedElementStyle.borderTopLeftRadius, 0);
        reactViewBackgroundDrawable.setRadius(rNSharedElementStyle.borderTopRightRadius, 1);
        reactViewBackgroundDrawable.setRadius(rNSharedElementStyle.borderBottomRightRadius, 2);
        reactViewBackgroundDrawable.setRadius(rNSharedElementStyle.borderBottomLeftRadius, 3);
        reactViewBackgroundDrawable.draw(canvas);
    }

    private void drawGenericView(Canvas canvas) {
        this.mContent.view.draw(canvas);
    }
}
