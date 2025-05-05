package expo.modules.splashscreen;

import android.app.Activity;
import android.content.Context;
import com.facebook.react.ReactRootView;
import expo.modules.core.interfaces.ReactActivityLifecycleListener;
import expo.modules.splashscreen.SplashScreenImageResizeMode;
import expo.modules.splashscreen.singletons.SplashScreen;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0003H\u0002J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\u0007\u001a\u00020\u0003H\u0002J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016¨\u0006\u000e"}, d2 = {"Lexpo/modules/splashscreen/SplashScreenReactActivityLifecycleListener;", "Lexpo/modules/core/interfaces/ReactActivityLifecycleListener;", "activityContext", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getResizeMode", "Lexpo/modules/splashscreen/SplashScreenImageResizeMode;", "context", "getStatusBarTranslucent", "", "onContentChanged", "", "activity", "Landroid/app/Activity;", "expo-splash-screen_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: SplashScreenReactActivityLifecycleListener.kt */
public final class SplashScreenReactActivityLifecycleListener implements ReactActivityLifecycleListener {
    public SplashScreenReactActivityLifecycleListener(Context context) {
        Intrinsics.checkNotNullParameter(context, "activityContext");
    }

    public void onContentChanged(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Context context = activity;
        SplashScreen.show$default(activity, getResizeMode(context), ReactRootView.class, getStatusBarTranslucent(context), (SplashScreenViewProvider) null, (Function0) null, (Function1) null, 112, (Object) null);
    }

    private final SplashScreenImageResizeMode getResizeMode(Context context) {
        SplashScreenImageResizeMode.Companion companion = SplashScreenImageResizeMode.Companion;
        String string = context.getString(R.string.expo_splash_screen_resize_mode);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        String lowerCase = string.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        SplashScreenImageResizeMode fromString = companion.fromString(lowerCase);
        return fromString == null ? SplashScreenImageResizeMode.CONTAIN : fromString;
    }

    private final boolean getStatusBarTranslucent(Context context) {
        return Boolean.parseBoolean(context.getString(R.string.expo_splash_screen_status_bar_translucent));
    }
}
