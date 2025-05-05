package expo.interfaces.devmenu;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.bridge.ReadableMap;
import expo.interfaces.devmenu.items.DevMenuDataSourceItem;
import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\b\u0010\u0006\u001a\u00020\u0007H&J\u001a\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH&J\u001c\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\u0010\u001a\u00020\nH¦@¢\u0006\u0002\u0010\u0011J\b\u0010\u0012\u001a\u00020\u0013H&J\n\u0010\u0014\u001a\u0004\u0018\u00010\u0015H&J\b\u0010\u0016\u001a\u00020\u0007H&J\u0010\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\u0013H&J\b\u0010\u0019\u001a\u00020\u001aH&J\u0018\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH&J\u0012\u0010 \u001a\u00020\u00072\b\u0010!\u001a\u0004\u0018\u00010\"H&J\u001c\u0010#\u001a\u00020\u00072\u0006\u0010$\u001a\u00020%2\n\b\u0002\u0010&\u001a\u0004\u0018\u00010\nH&J\u001a\u0010'\u001a\u00020\u00072\u0006\u0010(\u001a\u00020\n2\b\u0010)\u001a\u0004\u0018\u00010\u0001H&J\u000e\u0010*\u001a\b\u0012\u0004\u0012\u00020+0\u000eH&J\u000e\u0010,\u001a\b\u0012\u0004\u0012\u00020+0\u000eH&J\u0010\u0010-\u001a\u00020\u00072\u0006\u0010.\u001a\u00020\u001aH&J\u0012\u0010/\u001a\u00020\u00072\b\u0010&\u001a\u0004\u0018\u00010\nH&J\u0010\u00100\u001a\u00020\u00072\u0006\u00101\u001a\u000202H&J\b\u00103\u001a\u00020\u0007H&J\u0010\u00104\u001a\u00020\u00072\u0006\u0010$\u001a\u00020%H&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u00065"}, d2 = {"Lexpo/interfaces/devmenu/DevMenuManagerInterface;", "", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "getCoroutineScope", "()Lkotlinx/coroutines/CoroutineScope;", "closeMenu", "", "dispatchCallable", "actionId", "", "args", "Lcom/facebook/react/bridge/ReadableMap;", "fetchDataSource", "", "Lexpo/interfaces/devmenu/items/DevMenuDataSourceItem;", "id", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getMenuHost", "Lcom/facebook/react/ReactNativeHost;", "getSettings", "Lexpo/interfaces/devmenu/DevMenuPreferencesInterface;", "hideMenu", "initializeWithReactNativeHost", "reactNativeHost", "isInitialized", "", "onKeyEvent", "keyCode", "", "event", "Landroid/view/KeyEvent;", "onTouchEvent", "ev", "Landroid/view/MotionEvent;", "openMenu", "activity", "Landroid/app/Activity;", "screen", "sendEventToDelegateBridge", "eventName", "eventData", "serializedItems", "Landroid/os/Bundle;", "serializedScreens", "setCanLaunchDevMenuOnStart", "shouldAutoLaunch", "setCurrentScreen", "setDelegate", "newDelegate", "Lexpo/interfaces/devmenu/DevMenuDelegateInterface;", "synchronizeDelegate", "toggleMenu", "expo-dev-menu-interface_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: DevMenuManagerInterface.kt */
public interface DevMenuManagerInterface {
    void closeMenu();

    void dispatchCallable(String str, ReadableMap readableMap);

    Object fetchDataSource(String str, Continuation<? super List<? extends DevMenuDataSourceItem>> continuation);

    CoroutineScope getCoroutineScope();

    ReactNativeHost getMenuHost();

    DevMenuPreferencesInterface getSettings();

    void hideMenu();

    void initializeWithReactNativeHost(ReactNativeHost reactNativeHost);

    boolean isInitialized();

    boolean onKeyEvent(int i, KeyEvent keyEvent);

    void onTouchEvent(MotionEvent motionEvent);

    void openMenu(Activity activity, String str);

    void sendEventToDelegateBridge(String str, Object obj);

    List<Bundle> serializedItems();

    List<Bundle> serializedScreens();

    void setCanLaunchDevMenuOnStart(boolean z);

    void setCurrentScreen(String str);

    void setDelegate(DevMenuDelegateInterface devMenuDelegateInterface);

    void synchronizeDelegate();

    void toggleMenu(Activity activity);

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* compiled from: DevMenuManagerInterface.kt */
    public static final class DefaultImpls {
        public static /* synthetic */ void openMenu$default(DevMenuManagerInterface devMenuManagerInterface, Activity activity, String str, int i, Object obj) {
            if (obj == null) {
                if ((i & 2) != 0) {
                    str = null;
                }
                devMenuManagerInterface.openMenu(activity, str);
                return;
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: openMenu");
        }
    }
}
