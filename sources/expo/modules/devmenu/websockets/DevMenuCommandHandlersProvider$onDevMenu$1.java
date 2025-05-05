package expo.modules.devmenu.websockets;

import android.app.Activity;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.packagerconnection.NotificationOnlyHandler;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016¨\u0006\u0006"}, d2 = {"expo/modules/devmenu/websockets/DevMenuCommandHandlersProvider$onDevMenu$1", "Lcom/facebook/react/packagerconnection/NotificationOnlyHandler;", "onNotification", "", "params", "", "expo-dev-menu_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: DevMenuCommandHandlersProvider.kt */
public final class DevMenuCommandHandlersProvider$onDevMenu$1 extends NotificationOnlyHandler {
    final /* synthetic */ DevMenuCommandHandlersProvider this$0;

    DevMenuCommandHandlersProvider$onDevMenu$1(DevMenuCommandHandlersProvider devMenuCommandHandlersProvider) {
        this.this$0 = devMenuCommandHandlersProvider;
    }

    public void onNotification(Object obj) {
        ReactContext currentReactContext;
        Activity currentActivity;
        ReactInstanceManager access$getInstanceManager = this.this$0.getInstanceManager();
        if (access$getInstanceManager != null && (currentReactContext = access$getInstanceManager.getCurrentReactContext()) != null && (currentActivity = currentReactContext.getCurrentActivity()) != null) {
            this.this$0.manager.toggleMenu(currentActivity);
        }
    }
}
