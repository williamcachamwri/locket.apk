package expo.modules.splashscreen.singletons;

import android.app.Activity;
import android.view.View;
import android.view.WindowInsets;
import androidx.core.view.ViewCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001d\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\t¨\u0006\n"}, d2 = {"Lexpo/modules/splashscreen/singletons/SplashScreenStatusBar;", "", "()V", "configureTranslucent", "", "activity", "Landroid/app/Activity;", "translucent", "", "(Landroid/app/Activity;Ljava/lang/Boolean;)V", "expo-splash-screen_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: SplashScreenStatusBar.kt */
public final class SplashScreenStatusBar {
    public static final SplashScreenStatusBar INSTANCE = new SplashScreenStatusBar();

    private SplashScreenStatusBar() {
    }

    public final void configureTranslucent(Activity activity, Boolean bool) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        if (bool != null) {
            activity.runOnUiThread(new SplashScreenStatusBar$$ExternalSyntheticLambda1(activity, bool.booleanValue()));
        }
    }

    /* access modifiers changed from: private */
    public static final void configureTranslucent$lambda$2$lambda$1(Activity activity, boolean z) {
        Intrinsics.checkNotNullParameter(activity, "$activity");
        View decorView = activity.getWindow().getDecorView();
        Intrinsics.checkNotNullExpressionValue(decorView, "getDecorView(...)");
        if (z) {
            decorView.setOnApplyWindowInsetsListener(new SplashScreenStatusBar$$ExternalSyntheticLambda0());
        } else {
            decorView.setOnApplyWindowInsetsListener((View.OnApplyWindowInsetsListener) null);
        }
        ViewCompat.requestApplyInsets(decorView);
    }

    /* access modifiers changed from: private */
    public static final WindowInsets configureTranslucent$lambda$2$lambda$1$lambda$0(View view, WindowInsets windowInsets) {
        Intrinsics.checkNotNullParameter(view, "v");
        Intrinsics.checkNotNullParameter(windowInsets, "insets");
        WindowInsets onApplyWindowInsets = view.onApplyWindowInsets(windowInsets);
        return onApplyWindowInsets.replaceSystemWindowInsets(onApplyWindowInsets.getSystemWindowInsetLeft(), 0, onApplyWindowInsets.getSystemWindowInsetRight(), onApplyWindowInsets.getSystemWindowInsetBottom());
    }
}
