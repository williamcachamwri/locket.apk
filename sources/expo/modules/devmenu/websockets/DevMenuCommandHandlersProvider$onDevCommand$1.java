package expo.modules.devmenu.websockets;

import android.app.Activity;
import com.facebook.fbreact.specs.NativeDevMenuSpec;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.modules.core.RCTNativeAppEventEmitter;
import com.facebook.react.packagerconnection.NotificationOnlyHandler;
import expo.modules.devmenu.devtools.DevMenuDevToolsDelegate;
import io.sentry.android.core.SentryLogcatAdapter;
import kotlin.Metadata;
import org.json.JSONObject;

@Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016¨\u0006\u0006"}, d2 = {"expo/modules/devmenu/websockets/DevMenuCommandHandlersProvider$onDevCommand$1", "Lcom/facebook/react/packagerconnection/NotificationOnlyHandler;", "onNotification", "", "params", "", "expo-dev-menu_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: DevMenuCommandHandlersProvider.kt */
public final class DevMenuCommandHandlersProvider$onDevCommand$1 extends NotificationOnlyHandler {
    final /* synthetic */ DevMenuCommandHandlersProvider this$0;

    DevMenuCommandHandlersProvider$onDevCommand$1(DevMenuCommandHandlersProvider devMenuCommandHandlersProvider) {
        this.this$0 = devMenuCommandHandlersProvider;
    }

    public void onNotification(Object obj) {
        String optString;
        RCTNativeAppEventEmitter rCTNativeAppEventEmitter;
        Activity currentActivity;
        Activity currentActivity2;
        ReactInstanceManager access$getInstanceManager = this.this$0.getInstanceManager();
        if (access$getInstanceManager != null) {
            DevMenuDevToolsDelegate devMenuDevToolsDelegate = new DevMenuDevToolsDelegate(this.this$0.manager, access$getInstanceManager);
            if ((obj instanceof JSONObject) && (optString = ((JSONObject) obj).optString("name")) != null) {
                switch (optString.hashCode()) {
                    case -1662852274:
                        if (optString.equals("reconnectReactDevTools")) {
                            ReactContext currentReactContext = access$getInstanceManager.getCurrentReactContext();
                            if (currentReactContext != null && (rCTNativeAppEventEmitter = (RCTNativeAppEventEmitter) currentReactContext.getJSModule(RCTNativeAppEventEmitter.class)) != null) {
                                rCTNativeAppEventEmitter.emit("RCTDevMenuShown", (Object) null);
                                return;
                            }
                            return;
                        }
                        break;
                    case -1551749425:
                        if (optString.equals("toggleElementInspector")) {
                            devMenuDevToolsDelegate.toggleElementInspector();
                            return;
                        }
                        break;
                    case -1197573762:
                        if (optString.equals("togglePerformanceMonitor")) {
                            ReactContext currentReactContext2 = access$getInstanceManager.getCurrentReactContext();
                            if (currentReactContext2 != null && (currentActivity = currentReactContext2.getCurrentActivity()) != null) {
                                devMenuDevToolsDelegate.togglePerformanceMonitor(currentActivity);
                                return;
                            }
                            return;
                        }
                        break;
                    case -934641255:
                        if (optString.equals("reload")) {
                            devMenuDevToolsDelegate.reload();
                            return;
                        }
                        break;
                    case -542401756:
                        if (optString.equals("openJSInspector")) {
                            devMenuDevToolsDelegate.openJSInspector();
                            return;
                        }
                        break;
                    case -472895200:
                        if (optString.equals("toggleDevMenu")) {
                            ReactContext currentReactContext3 = access$getInstanceManager.getCurrentReactContext();
                            if (currentReactContext3 != null && (currentActivity2 = currentReactContext3.getCurrentActivity()) != null) {
                                this.this$0.manager.toggleMenu(currentActivity2);
                                return;
                            }
                            return;
                        }
                        break;
                    case 231612244:
                        if (optString.equals("toggleRemoteDebugging")) {
                            devMenuDevToolsDelegate.toggleRemoteDebugging();
                            return;
                        }
                        break;
                }
                SentryLogcatAdapter.w(NativeDevMenuSpec.NAME, "Unknown command: " + optString);
            }
        }
    }
}
