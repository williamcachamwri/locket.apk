package expo.modules.devlauncher.splashscreen;

import android.app.Activity;
import android.view.ViewGroup;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lexpo/modules/devlauncher/splashscreen/DevLauncherSplashScreenProvider;", "", "()V", "attachSplashScreenViewAsync", "Lexpo/modules/devlauncher/splashscreen/DevLauncherSplashScreen;", "activity", "Landroid/app/Activity;", "expo-dev-launcher_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: DevLauncherSplashScreenProvider.kt */
public final class DevLauncherSplashScreenProvider {
    public final DevLauncherSplashScreen attachSplashScreenViewAsync(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        ViewGroup viewGroup = (ViewGroup) activity.findViewById(16908290);
        if (viewGroup == null) {
            return null;
        }
        DevLauncherSplashScreen devLauncherSplashScreen = new DevLauncherSplashScreen(activity);
        viewGroup.addView(devLauncherSplashScreen);
        return devLauncherSplashScreen;
    }
}
