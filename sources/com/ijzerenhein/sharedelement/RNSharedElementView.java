package com.ijzerenhein.sharedelement;

import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;
import com.facebook.react.uimanager.ThemedReactContext;
import com.ijzerenhein.sharedelement.RNSharedElementDrawable;

class RNSharedElementView extends View {
    private final RNSharedElementDrawable mDrawable;
    private RNSharedElementDrawable.ViewType mViewType = RNSharedElementDrawable.ViewType.NONE;

    RNSharedElementView(ThemedReactContext themedReactContext) {
        super(themedReactContext);
        RNSharedElementDrawable rNSharedElementDrawable = new RNSharedElementDrawable();
        this.mDrawable = rNSharedElementDrawable;
        setBackground(rNSharedElementDrawable);
    }

    public boolean hasOverlappingRendering() {
        return this.mViewType == RNSharedElementDrawable.ViewType.GENERIC;
    }

    /* access modifiers changed from: package-private */
    public void reset() {
        setAlpha(0.0f);
    }

    /* access modifiers changed from: package-private */
    public void updateViewAndDrawable(RectF rectF, RectF rectF2, RectF rectF3, Rect rect, RNSharedElementContent rNSharedElementContent, RNSharedElementStyle rNSharedElementStyle, float f, RNSharedElementResize rNSharedElementResize, RNSharedElementAlign rNSharedElementAlign, float f2) {
        RNSharedElementDrawable.ViewType update = this.mDrawable.update(rNSharedElementContent, rNSharedElementStyle, f2);
        boolean z = rNSharedElementResize != RNSharedElementResize.CLIP && (update == RNSharedElementDrawable.ViewType.GENERIC || update == RNSharedElementDrawable.ViewType.PLAIN);
        if (this.mViewType != update) {
            this.mViewType = update;
            setLayerType(z ? 2 : 0, (Paint) null);
        }
        float width = rectF.width();
        float height = rectF.height();
        if (z) {
            int width2 = rect.width();
            int height2 = rect.height();
            layout(0, 0, width2, height2);
            setTranslationX(rectF.left - rectF2.left);
            setTranslationY(rectF.top - rectF2.top);
            float f3 = (float) width2;
            float f4 = width / f3;
            float f5 = (float) height2;
            float f6 = height / f5;
            if (!Float.isInfinite(f4) && !Float.isNaN(f4) && !Float.isInfinite(f6) && !Float.isNaN(f6)) {
                int i = AnonymousClass1.$SwitchMap$com$ijzerenhein$sharedelement$RNSharedElementResize[rNSharedElementResize.ordinal()];
                if (i == 3 || i == 4) {
                    f4 = f3 / rectF3.width();
                    f6 = f5 / rectF3.height();
                }
                setScaleX(f4);
                setScaleY(f6);
            }
            setPivotX(0.0f);
            setPivotY(0.0f);
        } else {
            layout(0, 0, (int) Math.ceil((double) width), (int) Math.ceil((double) height));
            setTranslationX(rectF.left - rectF2.left);
            setTranslationY(rectF.top - rectF2.top);
        }
        setAlpha(f);
        setElevation(rNSharedElementStyle.elevation);
    }

    /* renamed from: com.ijzerenhein.sharedelement.RNSharedElementView$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$ijzerenhein$sharedelement$RNSharedElementResize;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                com.ijzerenhein.sharedelement.RNSharedElementResize[] r0 = com.ijzerenhein.sharedelement.RNSharedElementResize.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$ijzerenhein$sharedelement$RNSharedElementResize = r0
                com.ijzerenhein.sharedelement.RNSharedElementResize r1 = com.ijzerenhein.sharedelement.RNSharedElementResize.AUTO     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$ijzerenhein$sharedelement$RNSharedElementResize     // Catch:{ NoSuchFieldError -> 0x001d }
                com.ijzerenhein.sharedelement.RNSharedElementResize r1 = com.ijzerenhein.sharedelement.RNSharedElementResize.STRETCH     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$ijzerenhein$sharedelement$RNSharedElementResize     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.ijzerenhein.sharedelement.RNSharedElementResize r1 = com.ijzerenhein.sharedelement.RNSharedElementResize.CLIP     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$ijzerenhein$sharedelement$RNSharedElementResize     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.ijzerenhein.sharedelement.RNSharedElementResize r1 = com.ijzerenhein.sharedelement.RNSharedElementResize.NONE     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.ijzerenhein.sharedelement.RNSharedElementView.AnonymousClass1.<clinit>():void");
        }
    }
}
