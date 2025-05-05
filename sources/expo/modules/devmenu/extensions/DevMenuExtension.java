package expo.modules.devmenu.extensions;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import expo.interfaces.devmenu.DevMenuExtensionInterface;
import expo.interfaces.devmenu.DevMenuExtensionSettingsInterface;
import expo.interfaces.devmenu.items.DevMenuDataSourceInterface;
import expo.interfaces.devmenu.items.DevMenuItemsContainer;
import expo.interfaces.devmenu.items.DevMenuScreen;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0018\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00072\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0018\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u00072\u0006\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0016¨\u0006\u0011"}, d2 = {"Lexpo/modules/devmenu/extensions/DevMenuExtension;", "Lcom/facebook/react/bridge/ReactContextBaseJavaModule;", "Lexpo/interfaces/devmenu/DevMenuExtensionInterface;", "reactContext", "Lcom/facebook/react/bridge/ReactApplicationContext;", "(Lcom/facebook/react/bridge/ReactApplicationContext;)V", "devMenuDataSources", "", "Lexpo/interfaces/devmenu/items/DevMenuDataSourceInterface;", "settings", "Lexpo/interfaces/devmenu/DevMenuExtensionSettingsInterface;", "devMenuItems", "Lexpo/interfaces/devmenu/items/DevMenuItemsContainer;", "devMenuScreens", "Lexpo/interfaces/devmenu/items/DevMenuScreen;", "getName", "", "expo-dev-menu_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: DevMenuExtension.kt */
public final class DevMenuExtension extends ReactContextBaseJavaModule implements DevMenuExtensionInterface {
    public List<DevMenuDataSourceInterface> devMenuDataSources(DevMenuExtensionSettingsInterface devMenuExtensionSettingsInterface) {
        Intrinsics.checkNotNullParameter(devMenuExtensionSettingsInterface, "settings");
        return null;
    }

    public List<DevMenuScreen> devMenuScreens(DevMenuExtensionSettingsInterface devMenuExtensionSettingsInterface) {
        Intrinsics.checkNotNullParameter(devMenuExtensionSettingsInterface, "settings");
        return null;
    }

    public String getName() {
        return "ExpoDevMenuExtensions";
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DevMenuExtension(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        Intrinsics.checkNotNullParameter(reactApplicationContext, "reactContext");
    }

    public DevMenuItemsContainer devMenuItems(DevMenuExtensionSettingsInterface devMenuExtensionSettingsInterface) {
        Intrinsics.checkNotNullParameter(devMenuExtensionSettingsInterface, "settings");
        return DevMenuItemsContainer.Companion.export(new DevMenuExtension$devMenuItems$1(devMenuExtensionSettingsInterface, this));
    }
}
