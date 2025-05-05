package expo.interfaces.devmenu.items;

import java.util.List;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H&J\u000e\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H&¨\u0006\u0006"}, d2 = {"Lexpo/interfaces/devmenu/items/DevMenuItemsContainerInterface;", "", "getAllItems", "", "Lexpo/interfaces/devmenu/items/DevMenuScreenItem;", "getRootItems", "expo-dev-menu-interface_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: DevMenuItemsContainerInterface.kt */
public interface DevMenuItemsContainerInterface {
    List<DevMenuScreenItem> getAllItems();

    List<DevMenuScreenItem> getRootItems();
}
