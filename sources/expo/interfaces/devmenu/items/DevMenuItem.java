package expo.interfaces.devmenu.items;

import android.os.Bundle;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001B\u0007\b\u0004¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H\u0016\u0001\u0002\u0007\b¨\u0006\t"}, d2 = {"Lexpo/interfaces/devmenu/items/DevMenuItem;", "", "()V", "getExportedType", "", "serialize", "Landroid/os/Bundle;", "Lexpo/interfaces/devmenu/items/DevMenuScreen;", "Lexpo/interfaces/devmenu/items/DevMenuScreenItem;", "expo-dev-menu-interface_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: DevMenuItems.kt */
public abstract class DevMenuItem {
    public /* synthetic */ DevMenuItem(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public abstract int getExportedType();

    private DevMenuItem() {
    }

    public Bundle serialize() {
        Bundle bundle = new Bundle();
        bundle.putInt("type", getExportedType());
        return bundle;
    }
}
