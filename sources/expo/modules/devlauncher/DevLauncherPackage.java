package expo.modules.devlauncher;

import android.content.Context;
import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
import expo.modules.core.interfaces.ApplicationLifecycleListener;
import expo.modules.core.interfaces.Package;
import expo.modules.core.interfaces.ReactActivityHandler;
import expo.modules.core.interfaces.ReactActivityLifecycleListener;
import expo.modules.core.interfaces.ReactNativeHostHandler;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016J\u0016\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u00052\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0018\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00052\b\u0010\u000f\u001a\u0004\u0018\u00010\bH\u0016J\u0018\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u00052\b\u0010\u000f\u001a\u0004\u0018\u00010\bH\u0016J\u0016\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u00052\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u001e\u0010\u0014\u001a\u0010\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00150\u00052\u0006\u0010\u000b\u001a\u00020\fH\u0016¨\u0006\u0016"}, d2 = {"Lexpo/modules/devlauncher/DevLauncherPackage;", "Lexpo/modules/core/interfaces/Package;", "Lcom/facebook/react/ReactPackage;", "()V", "createApplicationLifecycleListeners", "", "Lexpo/modules/core/interfaces/ApplicationLifecycleListener;", "context", "Landroid/content/Context;", "createNativeModules", "Lcom/facebook/react/bridge/NativeModule;", "reactContext", "Lcom/facebook/react/bridge/ReactApplicationContext;", "createReactActivityHandlers", "Lexpo/modules/core/interfaces/ReactActivityHandler;", "activityContext", "createReactActivityLifecycleListeners", "Lexpo/modules/core/interfaces/ReactActivityLifecycleListener;", "createReactNativeHostHandlers", "Lexpo/modules/core/interfaces/ReactNativeHostHandler;", "createViewManagers", "Lcom/facebook/react/uimanager/ViewManager;", "expo-dev-launcher_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: DevLauncherPackage.kt */
public final class DevLauncherPackage implements Package, ReactPackage {
    public List<NativeModule> createNativeModules(ReactApplicationContext reactApplicationContext) {
        Intrinsics.checkNotNullParameter(reactApplicationContext, "reactContext");
        return DevLauncherPackageDelegate.INSTANCE.createNativeModules(reactApplicationContext);
    }

    public List<ViewManager<?, ?>> createViewManagers(ReactApplicationContext reactApplicationContext) {
        Intrinsics.checkNotNullParameter(reactApplicationContext, "reactContext");
        return CollectionsKt.emptyList();
    }

    public List<ApplicationLifecycleListener> createApplicationLifecycleListeners(Context context) {
        return DevLauncherPackageDelegate.INSTANCE.createApplicationLifecycleListeners(context);
    }

    public List<ReactActivityLifecycleListener> createReactActivityLifecycleListeners(Context context) {
        return DevLauncherPackageDelegate.INSTANCE.createReactActivityLifecycleListeners(context);
    }

    public List<ReactActivityHandler> createReactActivityHandlers(Context context) {
        return DevLauncherPackageDelegate.INSTANCE.createReactActivityHandlers(context);
    }

    public List<ReactNativeHostHandler> createReactNativeHostHandlers(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return DevLauncherPackageDelegate.INSTANCE.createReactNativeHostHandlers(context);
    }
}
