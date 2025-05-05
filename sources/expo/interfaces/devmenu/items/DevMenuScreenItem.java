package expo.interfaces.devmenu.items;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lexpo/interfaces/devmenu/items/DevMenuScreenItem;", "Lexpo/interfaces/devmenu/items/DevMenuItem;", "()V", "importance", "", "getImportance", "()I", "setImportance", "(I)V", "expo-dev-menu-interface_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: DevMenuItems.kt */
public abstract class DevMenuScreenItem extends DevMenuItem {
    private int importance = DevMenuItemImportance.MEDIUM.getValue();

    public DevMenuScreenItem() {
        super((DefaultConstructorMarker) null);
    }

    public final int getImportance() {
        return this.importance;
    }

    public final void setImportance(int i) {
        this.importance = i;
    }
}
