package expo.modules.devmenu;

import android.app.Activity;
import android.view.KeyEvent;
import android.view.ViewGroup;
import androidx.core.app.NotificationCompat;
import expo.modules.core.interfaces.ReactActivityHandler;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000)\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016¨\u0006\f"}, d2 = {"expo/modules/devmenu/DevMenuPackage$createReactActivityHandlers$1", "Lexpo/modules/core/interfaces/ReactActivityHandler;", "createReactRootViewContainer", "Landroid/view/ViewGroup;", "activity", "Landroid/app/Activity;", "onKeyUp", "", "keyCode", "", "event", "Landroid/view/KeyEvent;", "expo-dev-menu_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: DevMenuPackage.kt */
public final class DevMenuPackage$createReactActivityHandlers$1 implements ReactActivityHandler {
    DevMenuPackage$createReactActivityHandlers$1() {
    }

    public ViewGroup createReactRootViewContainer(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        return new DevMenuReactRootViewContainer(activity);
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        Intrinsics.checkNotNullParameter(keyEvent, NotificationCompat.CATEGORY_EVENT);
        return DevMenuManager.INSTANCE.onKeyEvent(i, keyEvent);
    }
}
