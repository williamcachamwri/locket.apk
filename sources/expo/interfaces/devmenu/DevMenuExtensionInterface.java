package expo.interfaces.devmenu;

import expo.interfaces.devmenu.items.DevMenuDataSourceInterface;
import expo.interfaces.devmenu.items.DevMenuItemsContainerInterface;
import expo.interfaces.devmenu.items.DevMenuScreen;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0005\u001a\u00020\u0006H&J\u0018\u0010\t\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u000b\u001a\u00020\fH&¨\u0006\r"}, d2 = {"Lexpo/interfaces/devmenu/DevMenuExtensionInterface;", "", "devMenuDataSources", "", "Lexpo/interfaces/devmenu/items/DevMenuDataSourceInterface;", "settings", "Lexpo/interfaces/devmenu/DevMenuExtensionSettingsInterface;", "devMenuItems", "Lexpo/interfaces/devmenu/items/DevMenuItemsContainerInterface;", "devMenuScreens", "Lexpo/interfaces/devmenu/items/DevMenuScreen;", "getName", "", "expo-dev-menu-interface_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: DevMenuExtensionInterface.kt */
public interface DevMenuExtensionInterface {

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* compiled from: DevMenuExtensionInterface.kt */
    public static final class DefaultImpls {
        public static List<DevMenuDataSourceInterface> devMenuDataSources(DevMenuExtensionInterface devMenuExtensionInterface, DevMenuExtensionSettingsInterface devMenuExtensionSettingsInterface) {
            Intrinsics.checkNotNullParameter(devMenuExtensionSettingsInterface, "settings");
            return null;
        }

        public static List<DevMenuScreen> devMenuScreens(DevMenuExtensionInterface devMenuExtensionInterface, DevMenuExtensionSettingsInterface devMenuExtensionSettingsInterface) {
            Intrinsics.checkNotNullParameter(devMenuExtensionSettingsInterface, "settings");
            return null;
        }
    }

    List<DevMenuDataSourceInterface> devMenuDataSources(DevMenuExtensionSettingsInterface devMenuExtensionSettingsInterface);

    DevMenuItemsContainerInterface devMenuItems(DevMenuExtensionSettingsInterface devMenuExtensionSettingsInterface);

    List<DevMenuScreen> devMenuScreens(DevMenuExtensionSettingsInterface devMenuExtensionSettingsInterface);

    String getName();
}
