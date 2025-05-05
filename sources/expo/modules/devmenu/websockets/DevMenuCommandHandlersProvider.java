package expo.modules.devmenu.websockets;

import com.facebook.react.ReactInstanceManager;
import com.facebook.react.packagerconnection.NotificationOnlyHandler;
import expo.interfaces.devmenu.DevMenuManagerInterface;
import java.lang.ref.WeakReference;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000?\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000*\u0003\u000e\u0011\u0014\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00190\u0017R\u001c\u0010\u0007\u001a\u0010\u0012\f\u0012\n \t*\u0004\u0018\u00010\u00050\u00050\bX\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\n\u001a\u0004\u0018\u00010\u00058BX\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u00020\u000eX\u0004¢\u0006\u0004\n\u0002\u0010\u000fR\u0010\u0010\u0010\u001a\u00020\u0011X\u0004¢\u0006\u0004\n\u0002\u0010\u0012R\u0010\u0010\u0013\u001a\u00020\u0014X\u0004¢\u0006\u0004\n\u0002\u0010\u0015¨\u0006\u001a"}, d2 = {"Lexpo/modules/devmenu/websockets/DevMenuCommandHandlersProvider;", "", "manager", "Lexpo/interfaces/devmenu/DevMenuManagerInterface;", "reactInstanceManager", "Lcom/facebook/react/ReactInstanceManager;", "(Lexpo/interfaces/devmenu/DevMenuManagerInterface;Lcom/facebook/react/ReactInstanceManager;)V", "_instanceManager", "Ljava/lang/ref/WeakReference;", "kotlin.jvm.PlatformType", "instanceManager", "getInstanceManager", "()Lcom/facebook/react/ReactInstanceManager;", "onDevCommand", "expo/modules/devmenu/websockets/DevMenuCommandHandlersProvider$onDevCommand$1", "Lexpo/modules/devmenu/websockets/DevMenuCommandHandlersProvider$onDevCommand$1;", "onDevMenu", "expo/modules/devmenu/websockets/DevMenuCommandHandlersProvider$onDevMenu$1", "Lexpo/modules/devmenu/websockets/DevMenuCommandHandlersProvider$onDevMenu$1;", "onReload", "expo/modules/devmenu/websockets/DevMenuCommandHandlersProvider$onReload$1", "Lexpo/modules/devmenu/websockets/DevMenuCommandHandlersProvider$onReload$1;", "createCommandHandlers", "", "", "Lcom/facebook/react/packagerconnection/NotificationOnlyHandler;", "expo-dev-menu_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: DevMenuCommandHandlersProvider.kt */
public final class DevMenuCommandHandlersProvider {
    private final WeakReference<ReactInstanceManager> _instanceManager;
    /* access modifiers changed from: private */
    public final DevMenuManagerInterface manager;
    private final DevMenuCommandHandlersProvider$onDevCommand$1 onDevCommand = new DevMenuCommandHandlersProvider$onDevCommand$1(this);
    private final DevMenuCommandHandlersProvider$onDevMenu$1 onDevMenu = new DevMenuCommandHandlersProvider$onDevMenu$1(this);
    private final DevMenuCommandHandlersProvider$onReload$1 onReload = new DevMenuCommandHandlersProvider$onReload$1(this);

    public DevMenuCommandHandlersProvider(DevMenuManagerInterface devMenuManagerInterface, ReactInstanceManager reactInstanceManager) {
        Intrinsics.checkNotNullParameter(devMenuManagerInterface, "manager");
        Intrinsics.checkNotNullParameter(reactInstanceManager, "reactInstanceManager");
        this.manager = devMenuManagerInterface;
        this._instanceManager = new WeakReference<>(reactInstanceManager);
    }

    /* access modifiers changed from: private */
    public final ReactInstanceManager getInstanceManager() {
        return (ReactInstanceManager) this._instanceManager.get();
    }

    public final Map<String, NotificationOnlyHandler> createCommandHandlers() {
        return MapsKt.mapOf(TuplesKt.to("reload", this.onReload), TuplesKt.to("devMenu", this.onDevMenu), TuplesKt.to("sendDevCommand", this.onDevCommand));
    }
}
