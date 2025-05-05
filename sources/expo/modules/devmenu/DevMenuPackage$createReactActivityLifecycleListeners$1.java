package expo.modules.devmenu;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import com.facebook.react.ReactApplication;
import expo.modules.core.interfaces.ReactActivityLifecycleListener;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016¨\u0006\b"}, d2 = {"expo/modules/devmenu/DevMenuPackage$createReactActivityLifecycleListeners$1", "Lexpo/modules/core/interfaces/ReactActivityLifecycleListener;", "onCreate", "", "activity", "Landroid/app/Activity;", "savedInstanceState", "Landroid/os/Bundle;", "expo-dev-menu_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: DevMenuPackage.kt */
public final class DevMenuPackage$createReactActivityLifecycleListeners$1 implements ReactActivityLifecycleListener {
    DevMenuPackage$createReactActivityLifecycleListeners$1() {
    }

    public void onCreate(Activity activity, Bundle bundle) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        if (!DevMenuManager.INSTANCE.isInitialized()) {
            DevMenuManager devMenuManager = DevMenuManager.INSTANCE;
            Application application = activity.getApplication();
            Intrinsics.checkNotNull(application, "null cannot be cast to non-null type com.facebook.react.ReactApplication");
            devMenuManager.initializeWithReactNativeHost(((ReactApplication) application).getReactNativeHost());
            return;
        }
        DevMenuManager.INSTANCE.synchronizeDelegate();
    }
}
