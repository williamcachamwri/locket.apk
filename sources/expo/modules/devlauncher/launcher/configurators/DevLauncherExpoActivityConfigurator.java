package expo.modules.devlauncher.launcher.configurators;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.View;
import android.view.WindowInsets;
import androidx.core.view.ViewCompat;
import androidx.media3.common.C;
import com.facebook.react.ReactActivity;
import com.facebook.react.uimanager.ViewProps;
import expo.modules.devlauncher.helpers.DevLauncherColorsHelperKt;
import expo.modules.devlauncher.launcher.manifest.DevLauncherNavigationBarVisibility;
import expo.modules.devlauncher.launcher.manifest.DevLauncherOrientation;
import expo.modules.manifests.core.Manifest;
import io.sentry.android.core.SentryLogcatAdapter;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u000b\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\r\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u000eJ\u0018\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u000eH\u0007J\u0018\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\u000eH\u0003J\u001a\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u00162\u0006\u0010\t\u001a\u00020\u000eH\u0003J\u0018\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\u000eH\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lexpo/modules/devlauncher/launcher/configurators/DevLauncherExpoActivityConfigurator;", "", "manifest", "Lexpo/modules/manifests/core/Manifest;", "context", "Landroid/content/Context;", "(Lexpo/modules/manifests/core/Manifest;Landroid/content/Context;)V", "applyNavigationBarConfiguration", "", "activity", "Lcom/facebook/react/ReactActivity;", "applyOrientation", "applyStatusBarConfiguration", "applyTaskDescription", "Landroid/app/Activity;", "setColor", "color", "", "setHidden", "hidden", "", "setStyle", "", "style", "setTranslucent", "translucent", "Companion", "expo-dev-launcher_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: DevLauncherExpoActivityConfigurator.kt */
public final class DevLauncherExpoActivityConfigurator {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String TAG = "DevLauncherExpoActivityConfigurator";
    private final Context context;
    private Manifest manifest;

    public DevLauncherExpoActivityConfigurator(Manifest manifest2, Context context2) {
        Intrinsics.checkNotNullParameter(manifest2, "manifest");
        Intrinsics.checkNotNullParameter(context2, "context");
        this.manifest = manifest2;
        this.context = context2;
    }

    public final void applyTaskDescription(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        if (DevLauncherColorsHelperKt.isValidColor(this.manifest.getPrimaryColor())) {
            int parseColor = Color.parseColor(this.manifest.getPrimaryColor());
            activity.setTaskDescription(new ActivityManager.TaskDescription(this.manifest.getName(), BitmapFactory.decodeResource(this.context.getResources(), this.context.getApplicationInfo().icon), parseColor));
        }
    }

    public final void applyOrientation(ReactActivity reactActivity) {
        Intrinsics.checkNotNullParameter(reactActivity, "activity");
        String orientation = this.manifest.getOrientation();
        if (orientation != null) {
            int hashCode = orientation.hashCode();
            if (hashCode != 729267099) {
                if (hashCode != 1430647483) {
                    if (hashCode == 1544803905 && orientation.equals("default")) {
                        reactActivity.setRequestedOrientation(-1);
                        return;
                    }
                } else if (orientation.equals(DevLauncherOrientation.LANDSCAPE)) {
                    reactActivity.setRequestedOrientation(0);
                    return;
                }
            } else if (orientation.equals(DevLauncherOrientation.PORTRAIT)) {
                reactActivity.setRequestedOrientation(1);
                return;
            }
        }
        reactActivity.setRequestedOrientation(-1);
    }

    public final void applyStatusBarConfiguration(ReactActivity reactActivity) {
        Intrinsics.checkNotNullParameter(reactActivity, "activity");
        JSONObject androidStatusBarOptions = this.manifest.getAndroidStatusBarOptions();
        String str = null;
        String optString = androidStatusBarOptions != null ? androidStatusBarOptions.optString("barStyle") : null;
        if (androidStatusBarOptions != null) {
            str = androidStatusBarOptions.optString("backgroundColor");
        }
        String str2 = str;
        reactActivity.runOnUiThread(new DevLauncherExpoActivityConfigurator$$ExternalSyntheticLambda0(reactActivity, this, androidStatusBarOptions != null && androidStatusBarOptions.optBoolean("hidden", false), androidStatusBarOptions == null || androidStatusBarOptions.optBoolean("translucent", true), optString, str2));
    }

    /* access modifiers changed from: private */
    public static final void applyStatusBarConfiguration$lambda$0(ReactActivity reactActivity, DevLauncherExpoActivityConfigurator devLauncherExpoActivityConfigurator, boolean z, boolean z2, String str, String str2) {
        int i;
        Intrinsics.checkNotNullParameter(reactActivity, "$activity");
        Intrinsics.checkNotNullParameter(devLauncherExpoActivityConfigurator, "this$0");
        reactActivity.getWindow().clearFlags(67108864);
        Activity activity = reactActivity;
        devLauncherExpoActivityConfigurator.setHidden(z, activity);
        devLauncherExpoActivityConfigurator.setTranslucent(z2, activity);
        String style = devLauncherExpoActivityConfigurator.setStyle(str, activity);
        String RGBAtoARGB = DevLauncherColorsHelperKt.RGBAtoARGB(str2);
        if (RGBAtoARGB == null || !DevLauncherColorsHelperKt.isValidColor(RGBAtoARGB)) {
            i = Intrinsics.areEqual((Object) style, (Object) "light-content") ? Color.parseColor("#88000000") : 0;
        } else {
            i = Color.parseColor(RGBAtoARGB);
        }
        devLauncherExpoActivityConfigurator.setColor(i, activity);
    }

    private final String setStyle(String str, Activity activity) {
        int i;
        View decorView = activity.getWindow().getDecorView();
        Intrinsics.checkNotNullExpressionValue(decorView, "getDecorView(...)");
        int systemUiVisibility = decorView.getSystemUiVisibility();
        String str2 = "light-content";
        if (Intrinsics.areEqual((Object) str, (Object) str2)) {
            i = systemUiVisibility & -8193;
        } else {
            str2 = "dark-content";
            boolean areEqual = Intrinsics.areEqual((Object) str, (Object) str2);
            i = systemUiVisibility | 8192;
        }
        decorView.setSystemUiVisibility(i);
        return str2;
    }

    private final void setHidden(boolean z, Activity activity) {
        if (z) {
            activity.getWindow().addFlags(1024);
            activity.getWindow().clearFlags(2048);
            return;
        }
        activity.getWindow().addFlags(2048);
        activity.getWindow().clearFlags(1024);
    }

    private final void setTranslucent(boolean z, Activity activity) {
        View decorView = activity.getWindow().getDecorView();
        Intrinsics.checkNotNullExpressionValue(decorView, "getDecorView(...)");
        if (z) {
            decorView.setOnApplyWindowInsetsListener(new DevLauncherExpoActivityConfigurator$$ExternalSyntheticLambda1());
        } else {
            decorView.setOnApplyWindowInsetsListener((View.OnApplyWindowInsetsListener) null);
        }
        ViewCompat.requestApplyInsets(decorView);
    }

    /* access modifiers changed from: private */
    public static final WindowInsets setTranslucent$lambda$1(View view, WindowInsets windowInsets) {
        Intrinsics.checkNotNullParameter(view, "v");
        WindowInsets onApplyWindowInsets = view.onApplyWindowInsets(windowInsets);
        return onApplyWindowInsets.replaceSystemWindowInsets(onApplyWindowInsets.getSystemWindowInsetLeft(), 0, onApplyWindowInsets.getSystemWindowInsetRight(), onApplyWindowInsets.getSystemWindowInsetBottom());
    }

    public final void setColor(int i, Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        activity.getWindow().addFlags(Integer.MIN_VALUE);
        activity.getWindow().setStatusBarColor(i);
    }

    public final void applyNavigationBarConfiguration(ReactActivity reactActivity) {
        int i;
        Intrinsics.checkNotNullParameter(reactActivity, "activity");
        JSONObject androidNavigationBarOptions = this.manifest.getAndroidNavigationBarOptions();
        if (androidNavigationBarOptions != null) {
            String optString = androidNavigationBarOptions.optString("backgroundColor");
            if (DevLauncherColorsHelperKt.isValidColor(optString)) {
                try {
                    reactActivity.getWindow().clearFlags(C.BUFFER_FLAG_FIRST_SAMPLE);
                    reactActivity.getWindow().setNavigationBarColor(Color.parseColor(optString));
                } catch (Throwable th) {
                    SentryLogcatAdapter.e(TAG, "Failed to configure androidNavigationBar.backgroundColor", th);
                }
            }
            String optString2 = androidNavigationBarOptions.optString("barStyle");
            if (!Intrinsics.areEqual((Object) optString2, (Object) "")) {
                try {
                    reactActivity.getWindow().clearFlags(C.BUFFER_FLAG_FIRST_SAMPLE);
                    reactActivity.getWindow().addFlags(Integer.MIN_VALUE);
                    if (Intrinsics.areEqual((Object) optString2, (Object) "dark-content")) {
                        View decorView = reactActivity.getWindow().getDecorView();
                        Intrinsics.checkNotNullExpressionValue(decorView, "getDecorView(...)");
                        decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() | 16);
                    }
                } catch (Throwable th2) {
                    SentryLogcatAdapter.e(TAG, "Failed to configure androidNavigationBar.barStyle", th2);
                }
            }
            String optString3 = androidNavigationBarOptions.optString(ViewProps.VISIBLE);
            if (!Intrinsics.areEqual((Object) optString3, (Object) "")) {
                View decorView2 = reactActivity.getWindow().getDecorView();
                Intrinsics.checkNotNullExpressionValue(decorView2, "getDecorView(...)");
                int systemUiVisibility = decorView2.getSystemUiVisibility();
                if (optString3 != null) {
                    int hashCode = optString3.hashCode();
                    if (hashCode != -1197068329) {
                        if (hashCode != 1137617595) {
                            if (hashCode == 1570144589 && optString3.equals(DevLauncherNavigationBarVisibility.LEANBACK)) {
                                i = 6;
                                decorView2.setSystemUiVisibility(i | systemUiVisibility);
                            }
                        } else if (optString3.equals(DevLauncherNavigationBarVisibility.IMMERSIVE)) {
                            i = 2054;
                            decorView2.setSystemUiVisibility(i | systemUiVisibility);
                        }
                    } else if (optString3.equals(DevLauncherNavigationBarVisibility.STICKY_IMMERSIVE)) {
                        i = 4102;
                        decorView2.setSystemUiVisibility(i | systemUiVisibility);
                    }
                }
                i = 0;
                decorView2.setSystemUiVisibility(i | systemUiVisibility);
            }
        }
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lexpo/modules/devlauncher/launcher/configurators/DevLauncherExpoActivityConfigurator$Companion;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "expo-dev-launcher_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: DevLauncherExpoActivityConfigurator.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
