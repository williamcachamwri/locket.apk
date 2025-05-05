package expo.interfaces.devmenu.items;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001B\u000f\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0001\u0002\u0007\b¨\u0006\t"}, d2 = {"Lexpo/interfaces/devmenu/items/DevMenuExportedCallable;", "", "id", "", "(Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "Lexpo/interfaces/devmenu/items/DevMenuExportedAction;", "Lexpo/interfaces/devmenu/items/DevMenuExportedFunction;", "expo-dev-menu-interface_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: DevMenuExportedCallable.kt */
public abstract class DevMenuExportedCallable {
    private final String id;

    public /* synthetic */ DevMenuExportedCallable(String str, DefaultConstructorMarker defaultConstructorMarker) {
        this(str);
    }

    private DevMenuExportedCallable(String str) {
        this.id = str;
    }

    public final String getId() {
        return this.id;
    }
}
