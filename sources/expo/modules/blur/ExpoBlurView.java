package expo.modules.blur;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import eightbitlab.com.blurview.BlurView;
import eightbitlab.com.blurview.RenderEffectBlur;
import eightbitlab.com.blurview.RenderScriptBlur;
import expo.modules.blur.enums.BlurMethod;
import expo.modules.blur.enums.TintStyle;
import expo.modules.kotlin.AppContext;
import expo.modules.kotlin.exception.Exceptions;
import expo.modules.kotlin.views.ExpoView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\nJ\u0006\u0010\u0017\u001a\u00020\u0015J\u000e\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\bJ\u000e\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\nR\u000e\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u00020\u000fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013¨\u0006\u001c"}, d2 = {"Lexpo/modules/blur/ExpoBlurView;", "Lexpo/modules/kotlin/views/ExpoView;", "context", "Landroid/content/Context;", "appContext", "Lexpo/modules/kotlin/AppContext;", "(Landroid/content/Context;Lexpo/modules/kotlin/AppContext;)V", "blurMethod", "Lexpo/modules/blur/enums/BlurMethod;", "blurRadius", "", "blurReduction", "blurView", "Leightbitlab/com/blurview/BlurView;", "tint", "Lexpo/modules/blur/enums/TintStyle;", "getTint$expo_blur_release", "()Lexpo/modules/blur/enums/TintStyle;", "setTint$expo_blur_release", "(Lexpo/modules/blur/enums/TintStyle;)V", "applyBlurReduction", "", "reductionFactor", "applyTint", "setBlurMethod", "method", "setBlurRadius", "radius", "expo-blur_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ExpoBlurView.kt */
public final class ExpoBlurView extends ExpoView {
    private BlurMethod blurMethod = BlurMethod.NONE;
    private float blurRadius = 50.0f;
    private float blurReduction = 4.0f;
    private final BlurView blurView;
    private TintStyle tint = TintStyle.DEFAULT;

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* compiled from: ExpoBlurView.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        static {
            /*
                expo.modules.blur.enums.BlurMethod[] r0 = expo.modules.blur.enums.BlurMethod.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                expo.modules.blur.enums.BlurMethod r1 = expo.modules.blur.enums.BlurMethod.NONE     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                expo.modules.blur.enums.BlurMethod r1 = expo.modules.blur.enums.BlurMethod.DIMEZIS_BLUR_VIEW     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: expo.modules.blur.ExpoBlurView.WhenMappings.<clinit>():void");
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ExpoBlurView(Context context, AppContext appContext) {
        super(context, appContext);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        BlurView blurView2 = new BlurView(context);
        blurView2.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        Activity currentActivity = appContext.getCurrentActivity();
        if (currentActivity != null) {
            Window window = currentActivity.getWindow();
            ViewGroup viewGroup = null;
            View decorView = window != null ? window.getDecorView() : null;
            viewGroup = decorView != null ? (ViewGroup) decorView.findViewById(16908290) : viewGroup;
            Intrinsics.checkNotNull(viewGroup, "null cannot be cast to non-null type android.view.ViewGroup");
            if (Build.VERSION.SDK_INT >= 31) {
                blurView2.setupWith(viewGroup, new RenderEffectBlur()).setFrameClearDrawable(decorView.getBackground());
            } else {
                blurView2.setupWith(viewGroup, new RenderScriptBlur(context)).setFrameClearDrawable(decorView.getBackground());
            }
            addView(blurView2);
            this.blurView = blurView2;
            return;
        }
        throw new Exceptions.MissingActivity();
    }

    public final TintStyle getTint$expo_blur_release() {
        return this.tint;
    }

    public final void setTint$expo_blur_release(TintStyle tintStyle) {
        Intrinsics.checkNotNullParameter(tintStyle, "<set-?>");
        this.tint = tintStyle;
    }

    public final void setBlurRadius(float f) {
        int i = WhenMappings.$EnumSwitchMapping$0[this.blurMethod.ordinal()];
        if (i == 1) {
            setBackgroundColor(this.tint.toBlurEffect(this.blurRadius));
        } else if (i == 2) {
            this.blurView.setBlurEnabled(true ^ (f == 0.0f));
            if (f > 0.0f) {
                this.blurView.setBlurRadius(f / this.blurReduction);
                this.blurView.invalidate();
            }
        }
        this.blurRadius = f;
    }

    public final void setBlurMethod(BlurMethod blurMethod2) {
        Intrinsics.checkNotNullParameter(blurMethod2, "method");
        this.blurMethod = blurMethod2;
        int i = WhenMappings.$EnumSwitchMapping$0[blurMethod2.ordinal()];
        if (i == 1) {
            this.blurView.setBlurEnabled(false);
        } else if (i == 2) {
            this.blurView.setBlurEnabled(true);
            setBackgroundColor(0);
        }
        setBlurRadius(this.blurRadius);
    }

    public final void applyBlurReduction(float f) {
        this.blurReduction = f;
        setBlurRadius(this.blurRadius);
    }

    public final void applyTint() {
        int i = WhenMappings.$EnumSwitchMapping$0[this.blurMethod.ordinal()];
        if (i == 1) {
            setBackgroundColor(this.tint.toBlurEffect(this.blurRadius));
        } else if (i == 2) {
            this.blurView.setOverlayColor(this.tint.toBlurEffect(this.blurRadius));
        }
        this.blurView.invalidate();
    }
}
