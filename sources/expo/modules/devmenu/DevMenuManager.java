package expo.modules.devmenu;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import androidx.core.app.NotificationCompat;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.bridge.ReadableMap;
import expo.interfaces.devmenu.DevMenuDelegateInterface;
import expo.interfaces.devmenu.DevMenuManagerInterface;
import expo.interfaces.devmenu.DevMenuPreferencesInterface;
import expo.interfaces.devmenu.items.DevMenuDataSourceItem;
import expo.modules.devmenu.api.DevMenuMetroClient;
import expo.modules.manifests.core.Manifest;
import java.util.ArrayList;
import java.util.List;
import kotlin.KotlinNothingValueException;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000²\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u000b\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001]B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010'\u001a\u00020(H\u0016J\u001a\u0010)\u001a\u00020(2\u0006\u0010*\u001a\u00020\u000e2\b\u0010+\u001a\u0004\u0018\u00010,H\u0016J\u001c\u0010-\u001a\b\u0012\u0004\u0012\u00020/0.2\u0006\u00100\u001a\u00020\u000eH@¢\u0006\u0002\u00101J\u0006\u00102\u001a\u000203J\u0006\u00104\u001a\u000203J\b\u00105\u001a\u000206H\u0016J\u0006\u00107\u001a\u000203J\b\u00108\u001a\u0004\u0018\u000109J\n\u0010:\u001a\u0004\u0018\u00010;H\u0016J\b\u0010<\u001a\u00020(H\u0016J\u0010\u0010=\u001a\u00020(2\u0006\u0010>\u001a\u000206H\u0016J\b\u0010?\u001a\u00020@H\u0016J\u000e\u0010A\u001a\u00020(2\u0006\u0010B\u001a\u00020CJ\u0018\u0010D\u001a\u00020@2\u0006\u0010E\u001a\u00020F2\u0006\u0010G\u001a\u00020HH\u0016J\u0012\u0010I\u001a\u00020(2\b\u0010J\u001a\u0004\u0018\u00010KH\u0016J\u001a\u0010L\u001a\u00020(2\u0006\u0010M\u001a\u00020N2\b\u0010O\u001a\u0004\u0018\u00010\u000eH\u0016J\u001a\u0010P\u001a\u00020(2\u0006\u0010Q\u001a\u00020\u000e2\b\u0010R\u001a\u0004\u0018\u00010SH\u0016J\u000e\u0010T\u001a\b\u0012\u0004\u0012\u0002030.H\u0016J\u000e\u0010U\u001a\b\u0012\u0004\u0012\u0002030.H\u0016J\u0010\u0010V\u001a\u00020(2\u0006\u0010W\u001a\u00020@H\u0016J\u0012\u0010X\u001a\u00020(2\b\u0010O\u001a\u0004\u0018\u00010\u000eH\u0016J\u0010\u0010Y\u001a\u00020(2\u0006\u0010Z\u001a\u00020\u0014H\u0016J\b\u0010[\u001a\u00020(H\u0016J\u0010\u0010\\\u001a\u00020(2\u0006\u0010M\u001a\u00020NH\u0016R\u0014\u0010\u0003\u001a\u00020\u00048VX\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001c\u0010\r\u001a\u0004\u0018\u00010\u000eX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001b\u0010\u0019\u001a\u00020\u001a8FX\u0002¢\u0006\f\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001b\u0010\u001cR*\u0010\u001f\u001a\u0012\u0012\u0004\u0012\u00020!0 j\b\u0012\u0004\u0012\u00020!`\"X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&¨\u0006^"}, d2 = {"Lexpo/modules/devmenu/DevMenuManager;", "Lexpo/interfaces/devmenu/DevMenuManagerInterface;", "()V", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "getCoroutineScope", "()Lkotlinx/coroutines/CoroutineScope;", "currentManifest", "Lexpo/modules/manifests/core/Manifest;", "getCurrentManifest", "()Lexpo/modules/manifests/core/Manifest;", "setCurrentManifest", "(Lexpo/modules/manifests/core/Manifest;)V", "currentManifestURL", "", "getCurrentManifestURL", "()Ljava/lang/String;", "setCurrentManifestURL", "(Ljava/lang/String;)V", "delegate", "Lexpo/interfaces/devmenu/DevMenuDelegateInterface;", "getDelegate$expo_dev_menu_release", "()Lexpo/interfaces/devmenu/DevMenuDelegateInterface;", "setDelegate$expo_dev_menu_release", "(Lexpo/interfaces/devmenu/DevMenuDelegateInterface;)V", "metroClient", "Lexpo/modules/devmenu/api/DevMenuMetroClient;", "getMetroClient", "()Lexpo/modules/devmenu/api/DevMenuMetroClient;", "metroClient$delegate", "Lkotlin/Lazy;", "registeredCallbacks", "Ljava/util/ArrayList;", "Lexpo/modules/devmenu/DevMenuManager$Callback;", "Lkotlin/collections/ArrayList;", "getRegisteredCallbacks", "()Ljava/util/ArrayList;", "setRegisteredCallbacks", "(Ljava/util/ArrayList;)V", "closeMenu", "", "dispatchCallable", "actionId", "args", "Lcom/facebook/react/bridge/ReadableMap;", "fetchDataSource", "", "Lexpo/interfaces/devmenu/items/DevMenuDataSourceItem;", "id", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAppInfo", "Landroid/os/Bundle;", "getDevSettings", "getMenuHost", "Lcom/facebook/react/ReactNativeHost;", "getMenuPreferences", "getReactInstanceManager", "Lcom/facebook/react/ReactInstanceManager;", "getSettings", "Lexpo/interfaces/devmenu/DevMenuPreferencesInterface;", "hideMenu", "initializeWithReactNativeHost", "reactNativeHost", "isInitialized", "", "loadFonts", "context", "Landroid/content/Context;", "onKeyEvent", "keyCode", "", "event", "Landroid/view/KeyEvent;", "onTouchEvent", "ev", "Landroid/view/MotionEvent;", "openMenu", "activity", "Landroid/app/Activity;", "screen", "sendEventToDelegateBridge", "eventName", "eventData", "", "serializedItems", "serializedScreens", "setCanLaunchDevMenuOnStart", "canLaunchDevMenuOnStart", "setCurrentScreen", "setDelegate", "newDelegate", "synchronizeDelegate", "toggleMenu", "Callback", "expo-dev-menu_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: DevMenuManager.kt */
public final class DevMenuManager implements DevMenuManagerInterface {
    public static final DevMenuManager INSTANCE = new DevMenuManager();
    private static Manifest currentManifest;
    private static String currentManifestURL;
    private static DevMenuDelegateInterface delegate;
    private static final Lazy metroClient$delegate = LazyKt.lazy(DevMenuManager$metroClient$2.INSTANCE);
    private static ArrayList<Callback> registeredCallbacks = new ArrayList<>();

    public final ReactInstanceManager getReactInstanceManager() {
        return null;
    }

    public void initializeWithReactNativeHost(ReactNativeHost reactNativeHost) {
        Intrinsics.checkNotNullParameter(reactNativeHost, "reactNativeHost");
    }

    public boolean onKeyEvent(int i, KeyEvent keyEvent) {
        Intrinsics.checkNotNullParameter(keyEvent, NotificationCompat.CATEGORY_EVENT);
        return false;
    }

    public void onTouchEvent(MotionEvent motionEvent) {
    }

    public void setDelegate(DevMenuDelegateInterface devMenuDelegateInterface) {
        Intrinsics.checkNotNullParameter(devMenuDelegateInterface, "newDelegate");
    }

    public void synchronizeDelegate() {
    }

    private DevMenuManager() {
    }

    public final DevMenuDelegateInterface getDelegate$expo_dev_menu_release() {
        return delegate;
    }

    public final void setDelegate$expo_dev_menu_release(DevMenuDelegateInterface devMenuDelegateInterface) {
        delegate = devMenuDelegateInterface;
    }

    public final Manifest getCurrentManifest() {
        return currentManifest;
    }

    public final void setCurrentManifest(Manifest manifest) {
        currentManifest = manifest;
    }

    public final String getCurrentManifestURL() {
        return currentManifestURL;
    }

    public final void setCurrentManifestURL(String str) {
        currentManifestURL = str;
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u00052\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0013"}, d2 = {"Lexpo/modules/devmenu/DevMenuManager$Callback;", "", "name", "", "shouldCollapse", "", "(Ljava/lang/String;Z)V", "getName", "()Ljava/lang/String;", "getShouldCollapse", "()Z", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "expo-dev-menu_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: DevMenuManager.kt */
    public static final class Callback {
        private final String name;
        private final boolean shouldCollapse;

        public static /* synthetic */ Callback copy$default(Callback callback, String str, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                str = callback.name;
            }
            if ((i & 2) != 0) {
                z = callback.shouldCollapse;
            }
            return callback.copy(str, z);
        }

        public final String component1() {
            return this.name;
        }

        public final boolean component2() {
            return this.shouldCollapse;
        }

        public final Callback copy(String str, boolean z) {
            Intrinsics.checkNotNullParameter(str, "name");
            return new Callback(str, z);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Callback)) {
                return false;
            }
            Callback callback = (Callback) obj;
            return Intrinsics.areEqual((Object) this.name, (Object) callback.name) && this.shouldCollapse == callback.shouldCollapse;
        }

        public int hashCode() {
            return (this.name.hashCode() * 31) + Boolean.hashCode(this.shouldCollapse);
        }

        public String toString() {
            String str = this.name;
            return "Callback(name=" + str + ", shouldCollapse=" + this.shouldCollapse + ")";
        }

        public Callback(String str, boolean z) {
            Intrinsics.checkNotNullParameter(str, "name");
            this.name = str;
            this.shouldCollapse = z;
        }

        public final String getName() {
            return this.name;
        }

        public final boolean getShouldCollapse() {
            return this.shouldCollapse;
        }
    }

    public final ArrayList<Callback> getRegisteredCallbacks() {
        return registeredCallbacks;
    }

    public final void setRegisteredCallbacks(ArrayList<Callback> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        registeredCallbacks = arrayList;
    }

    public final Bundle getAppInfo() {
        throw new IllegalStateException("DevMenu isn't available in release builds");
    }

    public final Bundle getDevSettings() {
        throw new IllegalStateException("DevMenu isn't available in release builds");
    }

    public final DevMenuMetroClient getMetroClient() {
        metroClient$delegate.getValue();
        throw new KotlinNothingValueException();
    }

    public void openMenu(Activity activity, String str) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        throw new IllegalStateException("DevMenu isn't available in release builds");
    }

    public void closeMenu() {
        throw new IllegalStateException("DevMenu isn't available in release builds");
    }

    public void hideMenu() {
        throw new IllegalStateException("DevMenu isn't available in release builds");
    }

    public void toggleMenu(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        throw new IllegalStateException("DevMenu isn't available in release builds");
    }

    public void dispatchCallable(String str, ReadableMap readableMap) {
        Intrinsics.checkNotNullParameter(str, "actionId");
        throw new IllegalStateException("DevMenu isn't available in release builds");
    }

    public List<Bundle> serializedItems() {
        throw new IllegalStateException("DevMenu isn't available in release builds");
    }

    public List<Bundle> serializedScreens() {
        throw new IllegalStateException("DevMenu isn't available in release builds");
    }

    public DevMenuPreferencesInterface getSettings() {
        throw new IllegalStateException("DevMenu isn't available in release builds");
    }

    public final Bundle getMenuPreferences() {
        throw new IllegalStateException("DevMenu isn't available in release builds");
    }

    public ReactNativeHost getMenuHost() {
        throw new IllegalStateException("DevMenu isn't available in release builds");
    }

    public void setCurrentScreen(String str) {
        throw new IllegalStateException("DevMenu isn't available in release builds");
    }

    public void setCanLaunchDevMenuOnStart(boolean z) {
        throw new IllegalStateException("DevMenu isn't available in release builds");
    }

    public void sendEventToDelegateBridge(String str, Object obj) {
        Intrinsics.checkNotNullParameter(str, "eventName");
        throw new IllegalStateException("DevMenu isn't available in release builds");
    }

    public boolean isInitialized() {
        throw new IllegalStateException("DevMenu isn't available in release builds");
    }

    public Object fetchDataSource(String str, Continuation<? super List<? extends DevMenuDataSourceItem>> continuation) {
        throw new IllegalStateException("DevMenu isn't available in release builds");
    }

    public final void loadFonts(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        throw new IllegalStateException("DevMenu isn't available in release builds");
    }

    public CoroutineScope getCoroutineScope() {
        throw new IllegalStateException("DevMenu isn't available in release builds");
    }
}
