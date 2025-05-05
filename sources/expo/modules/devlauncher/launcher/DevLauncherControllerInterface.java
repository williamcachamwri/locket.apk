package expo.modules.devlauncher.launcher;

import android.content.Intent;
import android.net.Uri;
import com.facebook.react.ReactActivity;
import com.facebook.react.ReactActivityDelegate;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.bridge.ReactContext;
import expo.modules.devlauncher.DevLauncherController;
import expo.modules.manifests.core.Manifest;
import expo.modules.updatesinterface.UpdatesInterface;
import expo.modules.updatesinterface.UpdatesInterfaceCallbacks;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010&\u001a\u00020'H&J\u0018\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H&J\u000e\u0010.\u001a\b\u0012\u0004\u0012\u0002000/H&J\u001c\u00101\u001a\u00020#2\b\u00102\u001a\u0004\u0018\u0001032\b\u00104\u001a\u0004\u0018\u00010+H&J,\u00105\u001a\u00020'2\u0006\u00106\u001a\u00020\u000f2\b\u00107\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u00108\u001a\u0004\u0018\u00010+H¦@¢\u0006\u0002\u00109J\"\u00105\u001a\u00020'2\u0006\u00106\u001a\u00020\u000f2\n\b\u0002\u00108\u001a\u0004\u0018\u00010+H¦@¢\u0006\u0002\u0010:J\b\u0010;\u001a\u00020'H&J\u0010\u0010<\u001a\u00020'2\u0006\u0010=\u001a\u00020>H&J\b\u0010?\u001a\u00020'H&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0012\u0010\n\u001a\u00020\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u0004\u0018\u00010\u000fX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\u0004\u0018\u00010\u0013X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0016\u001a\u0004\u0018\u00010\u000fX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0011R\u0012\u0010\u0018\u001a\u00020\u0019X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001c\u001a\u0004\u0018\u00010\u001dX¦\u000e¢\u0006\f\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u0012\u0010\"\u001a\u00020#X¦\u0004¢\u0006\u0006\u001a\u0004\b$\u0010%¨\u0006@"}, d2 = {"Lexpo/modules/devlauncher/launcher/DevLauncherControllerInterface;", "Lexpo/modules/updatesinterface/UpdatesInterfaceCallbacks;", "appHost", "Lcom/facebook/react/ReactNativeHost;", "getAppHost", "()Lcom/facebook/react/ReactNativeHost;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "getCoroutineScope", "()Lkotlinx/coroutines/CoroutineScope;", "devClientHost", "Lexpo/modules/devlauncher/launcher/DevLauncherClientHost;", "getDevClientHost", "()Lexpo/modules/devlauncher/launcher/DevLauncherClientHost;", "latestLoadedApp", "Landroid/net/Uri;", "getLatestLoadedApp", "()Landroid/net/Uri;", "manifest", "Lexpo/modules/manifests/core/Manifest;", "getManifest", "()Lexpo/modules/manifests/core/Manifest;", "manifestURL", "getManifestURL", "mode", "Lexpo/modules/devlauncher/DevLauncherController$Mode;", "getMode", "()Lexpo/modules/devlauncher/DevLauncherController$Mode;", "updatesInterface", "Lexpo/modules/updatesinterface/UpdatesInterface;", "getUpdatesInterface", "()Lexpo/modules/updatesinterface/UpdatesInterface;", "setUpdatesInterface", "(Lexpo/modules/updatesinterface/UpdatesInterface;)V", "useDeveloperSupport", "", "getUseDeveloperSupport", "()Z", "clearRecentlyOpenedApps", "", "getCurrentReactActivityDelegate", "Lcom/facebook/react/ReactActivityDelegate;", "activity", "Lcom/facebook/react/ReactActivity;", "delegateSupplierDevLauncher", "Lexpo/modules/devlauncher/launcher/DevLauncherReactActivityDelegateSupplier;", "getRecentlyOpenedApps", "", "Lexpo/modules/devlauncher/launcher/DevLauncherAppEntry;", "handleIntent", "intent", "Landroid/content/Intent;", "activityToBeInvalidated", "loadApp", "url", "projectUrl", "mainActivity", "(Landroid/net/Uri;Landroid/net/Uri;Lcom/facebook/react/ReactActivity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Landroid/net/Uri;Lcom/facebook/react/ReactActivity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "navigateToLauncher", "onAppLoaded", "context", "Lcom/facebook/react/bridge/ReactContext;", "onAppLoadedWithError", "expo-dev-launcher_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: DevLauncherControllerInterface.kt */
public interface DevLauncherControllerInterface extends UpdatesInterfaceCallbacks {
    void clearRecentlyOpenedApps();

    ReactNativeHost getAppHost();

    CoroutineScope getCoroutineScope();

    ReactActivityDelegate getCurrentReactActivityDelegate(ReactActivity reactActivity, DevLauncherReactActivityDelegateSupplier devLauncherReactActivityDelegateSupplier);

    DevLauncherClientHost getDevClientHost();

    Uri getLatestLoadedApp();

    Manifest getManifest();

    Uri getManifestURL();

    DevLauncherController.Mode getMode();

    List<DevLauncherAppEntry> getRecentlyOpenedApps();

    UpdatesInterface getUpdatesInterface();

    boolean getUseDeveloperSupport();

    boolean handleIntent(Intent intent, ReactActivity reactActivity);

    Object loadApp(Uri uri, Uri uri2, ReactActivity reactActivity, Continuation<? super Unit> continuation);

    Object loadApp(Uri uri, ReactActivity reactActivity, Continuation<? super Unit> continuation);

    void navigateToLauncher();

    void onAppLoaded(ReactContext reactContext);

    void onAppLoadedWithError();

    void setUpdatesInterface(UpdatesInterface updatesInterface);

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* compiled from: DevLauncherControllerInterface.kt */
    public static final class DefaultImpls {
        public static /* synthetic */ Object loadApp$default(DevLauncherControllerInterface devLauncherControllerInterface, Uri uri, ReactActivity reactActivity, Continuation continuation, int i, Object obj) {
            if (obj == null) {
                if ((i & 2) != 0) {
                    reactActivity = null;
                }
                return devLauncherControllerInterface.loadApp(uri, reactActivity, continuation);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: loadApp");
        }

        public static /* synthetic */ Object loadApp$default(DevLauncherControllerInterface devLauncherControllerInterface, Uri uri, Uri uri2, ReactActivity reactActivity, Continuation continuation, int i, Object obj) {
            if (obj == null) {
                if ((i & 4) != 0) {
                    reactActivity = null;
                }
                return devLauncherControllerInterface.loadApp(uri, uri2, reactActivity, continuation);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: loadApp");
        }
    }
}
