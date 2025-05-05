package expo.modules.devmenu;

import android.content.Context;
import android.view.View;
import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ReactShadowNode;
import com.facebook.react.uimanager.ViewManager;
import expo.modules.core.interfaces.Package;
import expo.modules.core.interfaces.ReactActivityHandler;
import expo.modules.core.interfaces.ReactActivityLifecycleListener;
import expo.modules.devmenu.extensions.DevMenuExtension;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0018\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u00052\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016J\u0018\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00052\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016J&\u0010\u000f\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u0011\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00120\u00100\u00052\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\u0013"}, d2 = {"Lexpo/modules/devmenu/DevMenuPackage;", "Lexpo/modules/core/interfaces/Package;", "Lcom/facebook/react/ReactPackage;", "()V", "createNativeModules", "", "Lcom/facebook/react/bridge/NativeModule;", "reactContext", "Lcom/facebook/react/bridge/ReactApplicationContext;", "createReactActivityHandlers", "Lexpo/modules/core/interfaces/ReactActivityHandler;", "activityContext", "Landroid/content/Context;", "createReactActivityLifecycleListeners", "Lexpo/modules/core/interfaces/ReactActivityLifecycleListener;", "createViewManagers", "Lcom/facebook/react/uimanager/ViewManager;", "Landroid/view/View;", "Lcom/facebook/react/uimanager/ReactShadowNode;", "expo-dev-menu_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: DevMenuPackage.kt */
public final class DevMenuPackage implements Package, ReactPackage {
    public List<NativeModule> createNativeModules(ReactApplicationContext reactApplicationContext) {
        Intrinsics.checkNotNullParameter(reactApplicationContext, "reactContext");
        return CollectionsKt.listOf(new DevMenuExtension(reactApplicationContext));
    }

    public List<ViewManager<View, ReactShadowNode<?>>> createViewManagers(ReactApplicationContext reactApplicationContext) {
        Intrinsics.checkNotNullParameter(reactApplicationContext, "reactContext");
        return CollectionsKt.emptyList();
    }

    public List<ReactActivityLifecycleListener> createReactActivityLifecycleListeners(Context context) {
        DevMenuPackageDelegate.INSTANCE.shouldEnableAutoSetup$expo_dev_menu_release(context);
        return CollectionsKt.emptyList();
    }

    public List<ReactActivityHandler> createReactActivityHandlers(Context context) {
        DevMenuPackageDelegate.INSTANCE.shouldEnableAutoSetup$expo_dev_menu_release(context);
        return CollectionsKt.emptyList();
    }
}
