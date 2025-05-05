package expo.modules.devmenu;

import android.content.Context;
import expo.modules.devmenu.react.DevMenuAwareReactActivity;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0017\u0010\u0006\u001a\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0000¢\u0006\u0002\b\tR\u0016\u0010\u0003\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u000e¢\u0006\u0004\n\u0002\u0010\u0005¨\u0006\n"}, d2 = {"Lexpo/modules/devmenu/DevMenuPackageDelegate;", "", "()V", "enableAutoSetup", "", "Ljava/lang/Boolean;", "shouldEnableAutoSetup", "activityContext", "Landroid/content/Context;", "shouldEnableAutoSetup$expo_dev_menu_release", "expo-dev-menu_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: DevMenuPackage.kt */
public final class DevMenuPackageDelegate {
    public static final DevMenuPackageDelegate INSTANCE = new DevMenuPackageDelegate();
    public static Boolean enableAutoSetup;

    private DevMenuPackageDelegate() {
    }

    public final boolean shouldEnableAutoSetup$expo_dev_menu_release(Context context) {
        Boolean bool = enableAutoSetup;
        if (bool == null) {
            return context == null || !(context instanceof DevMenuAwareReactActivity);
        }
        Intrinsics.checkNotNull(bool);
        return bool.booleanValue();
    }
}
