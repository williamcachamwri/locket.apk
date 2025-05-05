package expo.modules.navigationbar.singletons;

import android.app.Activity;
import android.os.Build;
import android.view.View;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import com.facebook.react.uimanager.ViewProps;
import expo.modules.devlauncher.launcher.manifest.DevLauncherNavigationBarVisibility;
import expo.modules.devlauncher.launcher.manifest.DevLauncherUserInterface;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JG\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\f2!\u0010\r\u001a\u001d\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u00060\u000eJ\u0018\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\u0004H\u0007JG\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\u00042\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\f2!\u0010\r\u001a\u001d\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u00060\u000eJ\u0018\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0007JG\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\f2!\u0010\r\u001a\u001d\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u00060\u000eJG\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\u00042\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\f2!\u0010\r\u001a\u001d\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u00060\u000eJ\u0018\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00020\u0004H\u0007JG\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00020\u00042\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\f2!\u0010\r\u001a\u001d\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u00060\u000eJ\u0018\u0010\u0019\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u001a\u001a\u00020\u0004H\u0007JG\u0010\u0019\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u001a\u001a\u00020\u00042\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\f2!\u0010\r\u001a\u001d\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u00060\u000eJ\u0018\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u001c\u001a\u00020\u0004H\u0007JG\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u001c\u001a\u00020\u00042\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\f2!\u0010\r\u001a\u001d\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u00060\u000eR\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lexpo/modules/navigationbar/singletons/NavigationBar;", "", "()V", "TAG", "", "setBackgroundColor", "", "activity", "Landroid/app/Activity;", "color", "", "successCallback", "Lkotlin/Function0;", "failureCallback", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "reason", "setBehavior", "behavior", "setBorderColor", "setButtonStyle", "buttonStyle", "setLegacyVisible", "visible", "setPosition", "position", "setVisibility", "visibility", "expo-navigation-bar_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: NavigationBar.kt */
public final class NavigationBar {
    public static final NavigationBar INSTANCE = new NavigationBar();
    private static final String TAG = "NavigationBar";

    private NavigationBar() {
    }

    public final void setBackgroundColor(Activity activity, int i, Function0<Unit> function0, Function1<? super String, Unit> function1) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(function0, "successCallback");
        Intrinsics.checkNotNullParameter(function1, "failureCallback");
        activity.getWindow().setNavigationBarColor(i);
        function0.invoke();
    }

    public final void setButtonStyle(Activity activity, String str, Function0<Unit> function0, Function1<? super String, Unit> function1) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(str, "buttonStyle");
        Intrinsics.checkNotNullParameter(function0, "successCallback");
        Intrinsics.checkNotNullParameter(function1, "failureCallback");
        WindowInsetsControllerCompat windowInsetsControllerCompat = new WindowInsetsControllerCompat(activity.getWindow(), activity.getWindow().getDecorView());
        if (Intrinsics.areEqual((Object) str, (Object) DevLauncherUserInterface.LIGHT)) {
            windowInsetsControllerCompat.setAppearanceLightNavigationBars(false);
        } else if (Intrinsics.areEqual((Object) str, (Object) DevLauncherUserInterface.DARK)) {
            windowInsetsControllerCompat.setAppearanceLightNavigationBars(true);
        } else {
            function1.invoke("Invalid style: \"" + str + "\"");
            return;
        }
        function0.invoke();
    }

    public final void setBorderColor(Activity activity, int i, Function0<Unit> function0, Function1<? super String, Unit> function1) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(function0, "successCallback");
        Intrinsics.checkNotNullParameter(function1, "failureCallback");
        if (Build.VERSION.SDK_INT >= 28) {
            activity.getWindow().setNavigationBarDividerColor(i);
            function0.invoke();
            return;
        }
        function1.invoke("'setBorderColorAsync' is only available on Android API 28 or higher");
    }

    @JvmStatic
    public static final void setBorderColor(Activity activity, int i) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        INSTANCE.setBorderColor(activity, i, NavigationBar$setBorderColor$1.INSTANCE, NavigationBar$setBorderColor$2.INSTANCE);
    }

    public final void setVisibility(Activity activity, String str, Function0<Unit> function0, Function1<? super String, Unit> function1) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(str, "visibility");
        Intrinsics.checkNotNullParameter(function0, "successCallback");
        Intrinsics.checkNotNullParameter(function1, "failureCallback");
        WindowInsetsControllerCompat windowInsetsControllerCompat = new WindowInsetsControllerCompat(activity.getWindow(), activity.getWindow().getDecorView());
        if (Intrinsics.areEqual((Object) str, (Object) ViewProps.VISIBLE)) {
            windowInsetsControllerCompat.show(WindowInsetsCompat.Type.navigationBars());
        } else if (Intrinsics.areEqual((Object) str, (Object) "hidden")) {
            windowInsetsControllerCompat.hide(WindowInsetsCompat.Type.navigationBars());
        } else {
            function1.invoke("Invalid visibility: \"" + str + "\"");
            return;
        }
        function0.invoke();
    }

    @JvmStatic
    public static final void setVisibility(Activity activity, String str) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(str, "visibility");
        INSTANCE.setVisibility(activity, str, NavigationBar$setVisibility$2.INSTANCE, NavigationBar$setVisibility$3.INSTANCE);
    }

    public final void setPosition(Activity activity, String str, Function0<Unit> function0, Function1<? super String, Unit> function1) {
        boolean z;
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(str, ViewProps.POSITION);
        Intrinsics.checkNotNullParameter(function0, "successCallback");
        Intrinsics.checkNotNullParameter(function1, "failureCallback");
        if (Intrinsics.areEqual((Object) str, (Object) "absolute")) {
            z = false;
        } else if (Intrinsics.areEqual((Object) str, (Object) "relative")) {
            z = true;
        } else {
            function1.invoke("Invalid position: \"" + str + "\"");
            return;
        }
        WindowCompat.setDecorFitsSystemWindows(activity.getWindow(), z);
        activity.getWindow().getDecorView().setFitsSystemWindows(z);
        function0.invoke();
    }

    @JvmStatic
    public static final void setPosition(Activity activity, String str) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(str, ViewProps.POSITION);
        INSTANCE.setPosition(activity, str, NavigationBar$setPosition$1.INSTANCE, NavigationBar$setPosition$2.INSTANCE);
    }

    public final void setBehavior(Activity activity, String str, Function0<Unit> function0, Function1<? super String, Unit> function1) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(str, "behavior");
        Intrinsics.checkNotNullParameter(function0, "successCallback");
        Intrinsics.checkNotNullParameter(function1, "failureCallback");
        WindowInsetsControllerCompat windowInsetsControllerCompat = new WindowInsetsControllerCompat(activity.getWindow(), activity.getWindow().getDecorView());
        int hashCode = str.hashCode();
        if (hashCode != -1158014083) {
            if (hashCode != -342250870) {
                if (hashCode == -341554545 && str.equals("inset-touch")) {
                    windowInsetsControllerCompat.setSystemBarsBehavior(0);
                    function0.invoke();
                    return;
                }
            } else if (str.equals("inset-swipe")) {
                windowInsetsControllerCompat.setSystemBarsBehavior(1);
                function0.invoke();
                return;
            }
        } else if (str.equals("overlay-swipe")) {
            windowInsetsControllerCompat.setSystemBarsBehavior(2);
            function0.invoke();
            return;
        }
        function1.invoke("Invalid behavior: \"" + str + "\"");
    }

    @JvmStatic
    public static final void setBehavior(Activity activity, String str) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(str, "behavior");
        INSTANCE.setBehavior(activity, str, NavigationBar$setBehavior$2.INSTANCE, NavigationBar$setBehavior$3.INSTANCE);
    }

    public final void setLegacyVisible(Activity activity, String str, Function0<Unit> function0, Function1<? super String, Unit> function1) {
        int i;
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(str, ViewProps.VISIBLE);
        Intrinsics.checkNotNullParameter(function0, "successCallback");
        Intrinsics.checkNotNullParameter(function1, "failureCallback");
        View decorView = activity.getWindow().getDecorView();
        Intrinsics.checkNotNullExpressionValue(decorView, "getDecorView(...)");
        int systemUiVisibility = decorView.getSystemUiVisibility();
        int hashCode = str.hashCode();
        if (hashCode != -1197068329) {
            if (hashCode != 1137617595) {
                if (hashCode == 1570144589 && str.equals(DevLauncherNavigationBarVisibility.LEANBACK)) {
                    i = systemUiVisibility | 6;
                    decorView.setSystemUiVisibility(i);
                    function0.invoke();
                    return;
                }
            } else if (str.equals(DevLauncherNavigationBarVisibility.IMMERSIVE)) {
                i = systemUiVisibility | 2054;
                decorView.setSystemUiVisibility(i);
                function0.invoke();
                return;
            }
        } else if (str.equals(DevLauncherNavigationBarVisibility.STICKY_IMMERSIVE)) {
            i = systemUiVisibility | 4102;
            decorView.setSystemUiVisibility(i);
            function0.invoke();
            return;
        }
        function1.invoke("Invalid behavior: \"" + str + "\"");
    }

    @JvmStatic
    public static final void setLegacyVisible(Activity activity, String str) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(str, ViewProps.VISIBLE);
        INSTANCE.setBehavior(activity, str, NavigationBar$setLegacyVisible$1.INSTANCE, NavigationBar$setLegacyVisible$2.INSTANCE);
    }
}
