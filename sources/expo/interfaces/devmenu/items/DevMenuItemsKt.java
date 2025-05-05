package expo.interfaces.devmenu.items;

import android.view.KeyCharacterMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0011\u0010\u0000\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"keyCharacterMap", "Landroid/view/KeyCharacterMap;", "getKeyCharacterMap", "()Landroid/view/KeyCharacterMap;", "expo-dev-menu-interface_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: DevMenuItems.kt */
public final class DevMenuItemsKt {
    private static final KeyCharacterMap keyCharacterMap;

    static {
        KeyCharacterMap load = KeyCharacterMap.load(-1);
        Intrinsics.checkNotNullExpressionValue(load, "load(...)");
        keyCharacterMap = load;
    }

    public static final KeyCharacterMap getKeyCharacterMap() {
        return keyCharacterMap;
    }
}
